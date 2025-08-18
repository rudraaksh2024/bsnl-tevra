package org.apache.poi.ooxml.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageProperties;

public final class PackageHelper {
    public static OPCPackage open(InputStream inputStream) throws IOException {
        return open(inputStream, false);
    }

    public static OPCPackage open(InputStream inputStream, boolean z) throws IOException {
        try {
            OPCPackage open = OPCPackage.open(inputStream);
            if (z) {
                inputStream.close();
            }
            return open;
        } catch (InvalidFormatException e) {
            throw new POIXMLException((Throwable) e);
        } catch (Throwable th) {
            if (z) {
                inputStream.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        if (r5 != null) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0082, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0083, code lost:
        if (r4 != null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0089, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r6.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008d, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a2, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
        if (r0 != null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00aa, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ad, code lost:
        throw r7;
     */
    @java.lang.Deprecated
    @org.apache.poi.util.Removal(version = "6.0.0")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.openxml4j.opc.OPCPackage clone(org.apache.poi.openxml4j.opc.OPCPackage r6, java.io.File r7) throws org.apache.poi.openxml4j.exceptions.OpenXML4JException, java.io.IOException {
        /*
            java.lang.String r7 = r7.getAbsolutePath()
            org.apache.poi.openxml4j.opc.OPCPackage r0 = org.apache.poi.openxml4j.opc.OPCPackage.create((java.lang.String) r7)
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r1 = r6.getRelationships()     // Catch:{ all -> 0x00a0 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00a0 }
        L_0x0010:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00a0 }
            if (r2 == 0) goto L_0x008e
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.PackageRelationship r2 = (org.apache.poi.openxml4j.opc.PackageRelationship) r2     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.PackagePart r3 = r6.getPart((org.apache.poi.openxml4j.opc.PackageRelationship) r2)     // Catch:{ all -> 0x00a0 }
            java.lang.String r4 = r2.getRelationshipType()     // Catch:{ all -> 0x00a0 }
            java.lang.String r5 = "http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties"
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00a0 }
            if (r4 == 0) goto L_0x0038
            org.apache.poi.openxml4j.opc.PackageProperties r2 = r6.getPackageProperties()     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.PackageProperties r3 = r0.getPackageProperties()     // Catch:{ all -> 0x00a0 }
            copyProperties(r2, r3)     // Catch:{ all -> 0x00a0 }
            goto L_0x0010
        L_0x0038:
            org.apache.poi.openxml4j.opc.PackagePartName r4 = r3.getPartName()     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.TargetMode r5 = r2.getTargetMode()     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = r2.getRelationshipType()     // Catch:{ all -> 0x00a0 }
            r0.addRelationship(r4, r5, r2)     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.PackagePartName r2 = r3.getPartName()     // Catch:{ all -> 0x00a0 }
            java.lang.String r4 = r3.getContentType()     // Catch:{ all -> 0x00a0 }
            org.apache.poi.openxml4j.opc.PackagePart r2 = r0.createPart(r2, r4)     // Catch:{ all -> 0x00a0 }
            java.io.InputStream r4 = r3.getInputStream()     // Catch:{ all -> 0x00a0 }
            java.io.OutputStream r5 = r2.getOutputStream()     // Catch:{ all -> 0x0080 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r4, (java.io.OutputStream) r5)     // Catch:{ all -> 0x0072 }
            if (r5 == 0) goto L_0x0063
            r5.close()     // Catch:{ all -> 0x0080 }
        L_0x0063:
            if (r4 == 0) goto L_0x0068
            r4.close()     // Catch:{ all -> 0x00a0 }
        L_0x0068:
            boolean r4 = r3.hasRelationships()     // Catch:{ all -> 0x00a0 }
            if (r4 == 0) goto L_0x0010
            copy(r6, r3, r0, r2)     // Catch:{ all -> 0x00a0 }
            goto L_0x0010
        L_0x0072:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r7 = move-exception
            if (r5 == 0) goto L_0x007f
            r5.close()     // Catch:{ all -> 0x007b }
            goto L_0x007f
        L_0x007b:
            r1 = move-exception
            r6.addSuppressed(r1)     // Catch:{ all -> 0x0080 }
        L_0x007f:
            throw r7     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r7 = move-exception
            if (r4 == 0) goto L_0x008d
            r4.close()     // Catch:{ all -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r1 = move-exception
            r6.addSuppressed(r1)     // Catch:{ all -> 0x00a0 }
        L_0x008d:
            throw r7     // Catch:{ all -> 0x00a0 }
        L_0x008e:
            if (r0 == 0) goto L_0x0093
            r0.close()
        L_0x0093:
            java.io.File r6 = new java.io.File
            r6.<init>(r7)
            r6.deleteOnExit()
            org.apache.poi.openxml4j.opc.OPCPackage r6 = org.apache.poi.openxml4j.opc.OPCPackage.open((java.lang.String) r7)
            return r6
        L_0x00a0:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r7 = move-exception
            if (r0 == 0) goto L_0x00ad
            r0.close()     // Catch:{ all -> 0x00a9 }
            goto L_0x00ad
        L_0x00a9:
            r0 = move-exception
            r6.addSuppressed(r0)
        L_0x00ad:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.util.PackageHelper.clone(org.apache.poi.openxml4j.opc.OPCPackage, java.io.File):org.apache.poi.openxml4j.opc.OPCPackage");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009e, code lost:
        if (r3 != null) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a4, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a8, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ab, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ac, code lost:
        if (r2 != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b2, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b3, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b6, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void copy(org.apache.poi.openxml4j.opc.OPCPackage r5, org.apache.poi.openxml4j.opc.PackagePart r6, org.apache.poi.openxml4j.opc.OPCPackage r7, org.apache.poi.openxml4j.opc.PackagePart r8) throws org.apache.poi.openxml4j.exceptions.OpenXML4JException, java.io.IOException {
        /*
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r6 = r6.getRelationships()
            if (r6 == 0) goto L_0x00b7
            java.util.Iterator r6 = r6.iterator()
        L_0x000a:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x00b7
            java.lang.Object r0 = r6.next()
            org.apache.poi.openxml4j.opc.PackageRelationship r0 = (org.apache.poi.openxml4j.opc.PackageRelationship) r0
            org.apache.poi.openxml4j.opc.TargetMode r1 = r0.getTargetMode()
            org.apache.poi.openxml4j.opc.TargetMode r2 = org.apache.poi.openxml4j.opc.TargetMode.EXTERNAL
            if (r1 != r2) goto L_0x0032
            java.net.URI r1 = r0.getTargetURI()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = r0.getRelationshipType()
            java.lang.String r0 = r0.getId()
            r8.addExternalRelationship(r1, r2, r0)
            goto L_0x000a
        L_0x0032:
            java.net.URI r1 = r0.getTargetURI()
            java.lang.String r2 = r1.getRawFragment()
            if (r2 == 0) goto L_0x004c
            org.apache.poi.openxml4j.opc.TargetMode r2 = r0.getTargetMode()
            java.lang.String r3 = r0.getRelationshipType()
            java.lang.String r0 = r0.getId()
            r8.addRelationship((java.net.URI) r1, (org.apache.poi.openxml4j.opc.TargetMode) r2, (java.lang.String) r3, (java.lang.String) r0)
            goto L_0x000a
        L_0x004c:
            java.net.URI r1 = r0.getTargetURI()
            org.apache.poi.openxml4j.opc.PackagePartName r1 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.net.URI) r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r1)
            org.apache.poi.openxml4j.opc.PackagePartName r2 = r1.getPartName()
            org.apache.poi.openxml4j.opc.TargetMode r3 = r0.getTargetMode()
            java.lang.String r4 = r0.getRelationshipType()
            java.lang.String r0 = r0.getId()
            r8.addRelationship((org.apache.poi.openxml4j.opc.PackagePartName) r2, (org.apache.poi.openxml4j.opc.TargetMode) r3, (java.lang.String) r4, (java.lang.String) r0)
            org.apache.poi.openxml4j.opc.PackagePartName r0 = r1.getPartName()
            boolean r0 = r7.containPart(r0)
            if (r0 != 0) goto L_0x000a
            org.apache.poi.openxml4j.opc.PackagePartName r0 = r1.getPartName()
            java.lang.String r2 = r1.getContentType()
            org.apache.poi.openxml4j.opc.PackagePart r0 = r7.createPart(r0, r2)
            java.io.InputStream r2 = r1.getInputStream()
            java.io.OutputStream r3 = r0.getOutputStream()     // Catch:{ all -> 0x00a9 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r3)     // Catch:{ all -> 0x009b }
            if (r3 == 0) goto L_0x0091
            r3.close()     // Catch:{ all -> 0x00a9 }
        L_0x0091:
            if (r2 == 0) goto L_0x0096
            r2.close()
        L_0x0096:
            copy(r5, r1, r7, r0)
            goto L_0x000a
        L_0x009b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x009d }
        L_0x009d:
            r6 = move-exception
            if (r3 == 0) goto L_0x00a8
            r3.close()     // Catch:{ all -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch:{ all -> 0x00a9 }
        L_0x00a8:
            throw r6     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00ab }
        L_0x00ab:
            r6 = move-exception
            if (r2 == 0) goto L_0x00b6
            r2.close()     // Catch:{ all -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r7 = move-exception
            r5.addSuppressed(r7)
        L_0x00b6:
            throw r6
        L_0x00b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.util.PackageHelper.copy(org.apache.poi.openxml4j.opc.OPCPackage, org.apache.poi.openxml4j.opc.PackagePart, org.apache.poi.openxml4j.opc.OPCPackage, org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private static void copyProperties(PackageProperties packageProperties, PackageProperties packageProperties2) {
        packageProperties2.setCategoryProperty(packageProperties.getCategoryProperty());
        packageProperties2.setContentStatusProperty(packageProperties.getContentStatusProperty());
        packageProperties2.setContentTypeProperty(packageProperties.getContentTypeProperty());
        packageProperties2.setCreatorProperty(packageProperties.getCreatorProperty());
        packageProperties2.setDescriptionProperty(packageProperties.getDescriptionProperty());
        packageProperties2.setIdentifierProperty(packageProperties.getIdentifierProperty());
        packageProperties2.setKeywordsProperty(packageProperties.getKeywordsProperty());
        packageProperties2.setLanguageProperty(packageProperties.getLanguageProperty());
        packageProperties2.setRevisionProperty(packageProperties.getRevisionProperty());
        packageProperties2.setSubjectProperty(packageProperties.getSubjectProperty());
        packageProperties2.setTitleProperty(packageProperties.getTitleProperty());
        packageProperties2.setVersionProperty(packageProperties.getVersionProperty());
    }
}
