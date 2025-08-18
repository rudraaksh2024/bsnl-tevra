package org.apache.poi.openxml4j.opc.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.w3c.dom.Document;

public class ZipContentTypeManager extends ContentTypeManager {
    private static final Logger LOG = LogManager.getLogger((Class<?>) ZipContentTypeManager.class);

    public ZipContentTypeManager(InputStream inputStream, OPCPackage oPCPackage) throws InvalidFormatException {
        super(inputStream, oPCPackage);
    }

    public boolean saveImpl(Document document, OutputStream outputStream) {
        ZipArchiveOutputStream zipArchiveOutputStream = outputStream instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream) outputStream : new ZipArchiveOutputStream(outputStream);
        try {
            zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(ContentTypeManager.CONTENT_TYPES_PART_NAME));
            boolean saveXmlInStream = StreamHelper.saveXmlInStream(document, zipArchiveOutputStream);
            zipArchiveOutputStream.closeArchiveEntry();
            return saveXmlInStream;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("Cannot write: [Content_Types].xml in Zip !");
            return false;
        } catch (Throwable th) {
            zipArchiveOutputStream.closeArchiveEntry();
            throw th;
        }
    }
}
