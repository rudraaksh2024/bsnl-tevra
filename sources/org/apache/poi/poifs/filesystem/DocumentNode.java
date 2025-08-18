package org.apache.poi.poifs.filesystem;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DocumentProperty;

public class DocumentNode extends EntryNode implements DocumentEntry, POIFSViewable {
    private POIFSDocument _document;

    public Object[] getViewableArray() {
        return new Object[0];
    }

    /* access modifiers changed from: protected */
    public boolean isDeleteOK() {
        return true;
    }

    public boolean isDocumentEntry() {
        return true;
    }

    public boolean preferArray() {
        return false;
    }

    DocumentNode(DocumentProperty documentProperty, DirectoryNode directoryNode) {
        super(documentProperty, directoryNode);
        this._document = documentProperty.getDocument();
    }

    /* access modifiers changed from: package-private */
    public POIFSDocument getDocument() {
        return this._document;
    }

    public int getSize() {
        return getProperty().getSize();
    }

    public Iterator<Object> getViewableIterator() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getProperty());
        POIFSDocument pOIFSDocument = this._document;
        if (pOIFSDocument != null) {
            arrayList.add(pOIFSDocument);
        }
        return arrayList.iterator();
    }

    public String getShortDescription() {
        return getName();
    }
}
