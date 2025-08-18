package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;

public final class ZipPackagePropertiesMarshaller extends PackagePropertiesMarshaller {
    public boolean marshall(PackagePart packagePart, OutputStream outputStream) throws OpenXML4JException {
        if (outputStream instanceof ZipArchiveOutputStream) {
            ZipArchiveOutputStream zipArchiveOutputStream = (ZipArchiveOutputStream) outputStream;
            try {
                zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(ZipHelper.getZipItemNameFromOPCName(packagePart.getPartName().getURI().toString())));
                super.marshall(packagePart, outputStream);
                boolean saveXmlInStream = StreamHelper.saveXmlInStream(this.xmlDoc, outputStream);
                zipArchiveOutputStream.closeArchiveEntry();
                return saveXmlInStream;
            } catch (IOException e) {
                throw new OpenXML4JException(e.getLocalizedMessage(), e);
            } catch (Throwable th) {
                zipArchiveOutputStream.closeArchiveEntry();
                throw th;
            }
        } else {
            throw new IllegalArgumentException("ZipOutputStream expected!");
        }
    }
}
