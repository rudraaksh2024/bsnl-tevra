package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.ZipPackage;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.Internal;

@Internal
public final class ZipHelper {
    private static final String FORWARD_SLASH = "/";

    private ZipHelper() {
    }

    public static ZipArchiveEntry getCorePropertiesZipEntry(ZipPackage zipPackage) {
        PackageRelationship relationship = zipPackage.getRelationshipsByType(PackageRelationshipTypes.CORE_PROPERTIES).getRelationship(0);
        if (relationship == null) {
            return null;
        }
        return new ZipArchiveEntry(relationship.getTargetURI().getPath());
    }

    public static String getOPCNameFromZipItemName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("zipItemName cannot be null");
        } else if (str.startsWith("/")) {
            return str;
        } else {
            return "/" + str;
        }
    }

    public static String getZipItemNameFromOPCName(String str) {
        if (str != null) {
            while (str.startsWith("/")) {
                str = str.substring(1);
            }
            return str;
        }
        throw new IllegalArgumentException("opcItemName cannot be null");
    }

    public static URI getZipURIFromOPCName(String str) {
        if (str != null) {
            while (str.startsWith("/")) {
                str = str.substring(1);
            }
            try {
                return new URI(str);
            } catch (URISyntaxException unused) {
                return null;
            }
        } else {
            throw new IllegalArgumentException("opcItemName");
        }
    }

    private static void verifyZipHeader(InputStream inputStream) throws NotOfficeXmlFileException, IOException {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$poifs$filesystem$FileMagic[FileMagic.valueOf(FileMagic.prepareToCheckMagic(inputStream)).ordinal()];
        if (i == 1) {
            throw new OLE2NotOfficeXmlFileException("The supplied data appears to be in the OLE2 Format. You are calling the part of POI that deals with OOXML (Office Open XML) Documents. You need to call a different part of POI to process this data (eg HSSF instead of XSSF)");
        } else if (i == 2) {
            throw new NotOfficeXmlFileException("The supplied data appears to be a raw XML file. Formats such as Office 2003 XML are not supported");
        }
    }

    /* renamed from: org.apache.poi.openxml4j.opc.internal.ZipHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.poifs.filesystem.FileMagic[] r0 = org.apache.poi.poifs.filesystem.FileMagic.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic = r0
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.XML     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.ZipHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static ZipArchiveThresholdInputStream openZipStream(InputStream inputStream) throws IOException {
        InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(inputStream);
        verifyZipHeader(prepareToCheckMagic);
        return new ZipArchiveThresholdInputStream(new ZipArchiveInputStream(prepareToCheckMagic));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.openxml4j.util.ZipSecureFile openZipFile(java.io.File r2) throws java.io.IOException, org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException {
        /*
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x0031
            boolean r0 = r2.isDirectory()
            if (r0 != 0) goto L_0x0029
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            verifyZipHeader(r0)     // Catch:{ all -> 0x001d }
            r0.close()
            org.apache.poi.openxml4j.util.ZipSecureFile r0 = new org.apache.poi.openxml4j.util.ZipSecureFile
            r0.<init>((java.io.File) r2)
            return r0
        L_0x001d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001f }
        L_0x001f:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0028:
            throw r1
        L_0x0029:
            java.io.IOException r2 = new java.io.IOException
            java.lang.String r0 = "File is a directory"
            r2.<init>(r0)
            throw r2
        L_0x0031:
            java.io.FileNotFoundException r2 = new java.io.FileNotFoundException
            java.lang.String r0 = "File does not exist"
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.internal.ZipHelper.openZipFile(java.io.File):org.apache.poi.openxml4j.util.ZipSecureFile");
    }

    public static ZipSecureFile openZipFile(String str) throws IOException {
        return openZipFile(new File(str));
    }
}
