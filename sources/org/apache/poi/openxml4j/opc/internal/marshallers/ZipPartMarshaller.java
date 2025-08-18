package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class ZipPartMarshaller implements PartMarshaller {
    private static final Logger LOG = LogManager.getLogger((Class<?>) ZipPartMarshaller.class);

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        if (r4 != null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0070, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean marshall(org.apache.poi.openxml4j.opc.PackagePart r5, java.io.OutputStream r6) throws org.apache.poi.openxml4j.exceptions.OpenXML4JException {
        /*
            r4 = this;
            boolean r4 = r6 instanceof org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
            if (r4 == 0) goto L_0x008c
            long r0 = r5.getSize()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r0 = 1
            if (r4 != 0) goto L_0x0024
            org.apache.poi.openxml4j.opc.PackagePartName r4 = r5.getPartName()
            java.lang.String r4 = r4.getName()
            org.apache.poi.xssf.usermodel.XSSFRelation r1 = org.apache.poi.xssf.usermodel.XSSFRelation.SHARED_STRINGS
            java.lang.String r1 = r1.getDefaultFileName()
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0024
            return r0
        L_0x0024:
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r6 = (org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream) r6
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r4 = new org.apache.commons.compress.archivers.zip.ZipArchiveEntry
            org.apache.poi.openxml4j.opc.PackagePartName r1 = r5.getPartName()
            java.net.URI r1 = r1.getURI()
            java.lang.String r1 = r1.getPath()
            java.lang.String r1 = org.apache.poi.openxml4j.opc.internal.ZipHelper.getZipItemNameFromOPCName(r1)
            r4.<init>((java.lang.String) r1)
            r6.putArchiveEntry(r4)     // Catch:{ IOException -> 0x0076 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ all -> 0x0071 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r4, (java.io.OutputStream) r6)     // Catch:{ all -> 0x0063 }
            if (r4 == 0) goto L_0x004a
            r4.close()     // Catch:{ all -> 0x0071 }
        L_0x004a:
            r6.closeArchiveEntry()     // Catch:{ IOException -> 0x0076 }
            boolean r4 = r5.hasRelationships()
            if (r4 == 0) goto L_0x0062
            org.apache.poi.openxml4j.opc.PackagePartName r4 = r5.getPartName()
            org.apache.poi.openxml4j.opc.PackagePartName r4 = org.apache.poi.openxml4j.opc.PackagingURIHelper.getRelationshipPartName(r4)
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r5 = r5.getRelationships()
            marshallRelationshipPart(r5, r4, r6)
        L_0x0062:
            return r0
        L_0x0063:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r1 = move-exception
            if (r4 == 0) goto L_0x0070
            r4.close()     // Catch:{ all -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r4 = move-exception
            r0.addSuppressed(r4)     // Catch:{ all -> 0x0071 }
        L_0x0070:
            throw r1     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r4 = move-exception
            r6.closeArchiveEntry()     // Catch:{ IOException -> 0x0076 }
            throw r4     // Catch:{ IOException -> 0x0076 }
        L_0x0076:
            r4 = move-exception
            org.apache.logging.log4j.Logger r6 = LOG
            org.apache.logging.log4j.LogBuilder r6 = r6.atError()
            org.apache.logging.log4j.LogBuilder r4 = r6.withThrowable(r4)
            java.lang.String r6 = "Cannot write: {}: in ZIP"
            org.apache.poi.openxml4j.opc.PackagePartName r5 = r5.getPartName()
            r4.log((java.lang.String) r6, (java.lang.Object) r5)
            r4 = 0
            return r4
        L_0x008c:
            org.apache.logging.log4j.Logger r4 = LOG
            org.apache.logging.log4j.LogBuilder r4 = r4.atError()
            java.lang.Class r5 = r6.getClass()
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = "Unexpected class {}"
            r4.log((java.lang.String) r6, (java.lang.Object) r5)
            org.apache.poi.openxml4j.exceptions.OpenXML4JException r4 = new org.apache.poi.openxml4j.exceptions.OpenXML4JException
            java.lang.String r5 = "ZipOutputStream expected !"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller.marshall(org.apache.poi.openxml4j.opc.PackagePart, java.io.OutputStream):boolean");
    }

    public static boolean marshallRelationshipPart(PackageRelationshipCollection packageRelationshipCollection, PackagePartName packagePartName, ZipArchiveOutputStream zipArchiveOutputStream) {
        String str;
        Document createDocument = DocumentHelper.createDocument();
        Element createElementNS = createDocument.createElementNS(PackageNamespaces.RELATIONSHIPS, PackageRelationship.RELATIONSHIPS_TAG_NAME);
        createDocument.appendChild(createElementNS);
        URI sourcePartUriFromRelationshipPartUri = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(packagePartName.getURI());
        Iterator<PackageRelationship> it = packageRelationshipCollection.iterator();
        while (it.hasNext()) {
            PackageRelationship next = it.next();
            Element createElementNS2 = createDocument.createElementNS(PackageNamespaces.RELATIONSHIPS, PackageRelationship.RELATIONSHIP_TAG_NAME);
            createElementNS.appendChild(createElementNS2);
            createElementNS2.setAttribute(PackageRelationship.ID_ATTRIBUTE_NAME, next.getId());
            createElementNS2.setAttribute(PackageRelationship.TYPE_ATTRIBUTE_NAME, next.getRelationshipType());
            URI targetURI = next.getTargetURI();
            if (next.getTargetMode() == TargetMode.EXTERNAL) {
                str = targetURI.toString();
                createElementNS2.setAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME, "External");
            } else {
                str = PackagingURIHelper.relativizeURI(sourcePartUriFromRelationshipPartUri, next.getTargetURI(), true).toString();
            }
            createElementNS2.setAttribute(PackageRelationship.TARGET_ATTRIBUTE_NAME, str);
        }
        createDocument.normalize();
        try {
            zipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(ZipHelper.getZipURIFromOPCName(packagePartName.getURI().toASCIIString()).getPath()));
            boolean saveXmlInStream = StreamHelper.saveXmlInStream(createDocument, zipArchiveOutputStream);
            zipArchiveOutputStream.closeArchiveEntry();
            return saveXmlInStream;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("Cannot create zip entry {}", (Object) packagePartName);
            return false;
        } catch (Throwable th) {
            zipArchiveOutputStream.closeArchiveEntry();
            throw th;
        }
    }
}
