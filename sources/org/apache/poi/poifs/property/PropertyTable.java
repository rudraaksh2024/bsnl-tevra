package org.apache.poi.poifs.property;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.filesystem.BATManaged;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSStream;
import org.apache.poi.poifs.storage.HeaderBlock;
import org.apache.poi.util.IOUtils;

public final class PropertyTable implements BATManaged {
    private static final Logger LOG = LogManager.getLogger((Class<?>) PropertyTable.class);
    private final POIFSBigBlockSize _bigBigBlockSize;
    private final HeaderBlock _header_block;
    private final List<Property> _properties;

    public PropertyTable(HeaderBlock headerBlock) {
        this._properties = new ArrayList();
        this._header_block = headerBlock;
        this._bigBigBlockSize = headerBlock.getBigBlockSize();
        addProperty(new RootProperty());
    }

    public PropertyTable(HeaderBlock headerBlock, POIFSFileSystem pOIFSFileSystem) throws IOException {
        this(headerBlock, (Iterable<ByteBuffer>) new POIFSStream(pOIFSFileSystem, headerBlock.getPropertyStart()));
    }

    PropertyTable(HeaderBlock headerBlock, Iterable<ByteBuffer> iterable) throws IOException {
        byte[] bArr;
        this._properties = new ArrayList();
        this._header_block = headerBlock;
        this._bigBigBlockSize = headerBlock.getBigBlockSize();
        for (ByteBuffer next : iterable) {
            if (next.hasArray() && next.arrayOffset() == 0 && next.array().length == this._bigBigBlockSize.getBigBlockSize()) {
                bArr = next.array();
            } else {
                byte[] safelyAllocate = IOUtils.safelyAllocate((long) this._bigBigBlockSize.getBigBlockSize(), POIFSFileSystem.getMaxRecordLength());
                int length = safelyAllocate.length;
                if (next.remaining() < this._bigBigBlockSize.getBigBlockSize()) {
                    LOG.atWarn().log("Short Property Block, {} bytes instead of the expected {}", Unbox.box(next.remaining()), Unbox.box(this._bigBigBlockSize.getBigBlockSize()));
                    length = next.remaining();
                }
                next.get(safelyAllocate, 0, length);
                bArr = safelyAllocate;
            }
            PropertyFactory.convertToProperties(bArr, this._properties);
        }
        if (this._properties.get(0) != null) {
            populatePropertyTree((DirectoryProperty) this._properties.get(0));
        }
    }

    public void addProperty(Property property) {
        this._properties.add(property);
    }

    public void removeProperty(Property property) {
        this._properties.remove(property);
    }

    public RootProperty getRoot() {
        return (RootProperty) this._properties.get(0);
    }

    public int getStartBlock() {
        return this._header_block.getPropertyStart();
    }

    public void setStartBlock(int i) {
        this._header_block.setPropertyStart(i);
    }

    public int countBlocks() {
        long size = ((long) this._properties.size()) * 128;
        long bigBlockSize = (long) this._bigBigBlockSize.getBigBlockSize();
        int i = (int) (size / bigBlockSize);
        return size % bigBlockSize != 0 ? i + 1 : i;
    }

    public void preWrite() {
        ArrayList<Property> arrayList = new ArrayList<>();
        int i = 0;
        for (Property next : this._properties) {
            if (next != null) {
                next.setIndex(i);
                arrayList.add(next);
                i++;
            }
        }
        for (Property preWrite : arrayList) {
            preWrite.preWrite();
        }
    }

    public void write(POIFSStream pOIFSStream) throws IOException {
        OutputStream outputStream = pOIFSStream.getOutputStream();
        for (Property next : this._properties) {
            if (next != null) {
                next.writeData(outputStream);
            }
        }
        outputStream.close();
        if (getStartBlock() != pOIFSStream.getStartBlock()) {
            setStartBlock(pOIFSStream.getStartBlock());
        }
    }

    private void populatePropertyTree(DirectoryProperty directoryProperty) throws IOException {
        int childIndex = directoryProperty.getChildIndex();
        if (Property.isValidIndex(childIndex)) {
            Stack stack = new Stack();
            stack.push(this._properties.get(childIndex));
            while (!stack.empty()) {
                Property property = (Property) stack.pop();
                if (property != null) {
                    directoryProperty.addChild(property);
                    if (property.isDirectory()) {
                        populatePropertyTree((DirectoryProperty) property);
                    }
                    int previousChildIndex = property.getPreviousChildIndex();
                    if (isValidIndex(previousChildIndex)) {
                        stack.push(this._properties.get(previousChildIndex));
                    }
                    int nextChildIndex = property.getNextChildIndex();
                    if (isValidIndex(nextChildIndex)) {
                        stack.push(this._properties.get(nextChildIndex));
                    }
                }
            }
        }
    }

    private boolean isValidIndex(int i) {
        if (!Property.isValidIndex(i)) {
            return false;
        }
        if (i >= 0 && i < this._properties.size()) {
            return true;
        }
        LOG.atWarn().log("Property index {} outside the valid range 0..{}", Unbox.box(i), Unbox.box(this._properties.size()));
        return false;
    }
}
