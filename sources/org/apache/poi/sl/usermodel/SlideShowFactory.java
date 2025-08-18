package org.apache.poi.sl.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public final class SlideShowFactory {
    private final List<SlideShowProvider<?, ?>> provider;

    private interface ProviderMethod {
        SlideShow<?, ?> create(SlideShowProvider<?, ?> slideShowProvider) throws IOException;
    }

    private static class Singleton {
        /* access modifiers changed from: private */
        public static final SlideShowFactory INSTANCE = new SlideShowFactory();

        private Singleton() {
        }
    }

    private SlideShowFactory() {
        ArrayList arrayList = new ArrayList();
        this.provider = arrayList;
        ServiceLoader.load(SlideShowProvider.class, SlideShowFactory.class.getClassLoader()).forEach(new SlideShowFactory$$ExternalSyntheticLambda6(arrayList));
    }

    public static SlideShow<?, ?> create(boolean z) throws IOException {
        return wp(z ? FileMagic.OOXML : FileMagic.OLE2, new SlideShowFactory$$ExternalSyntheticLambda1());
    }

    public static SlideShow<?, ?> create(POIFSFileSystem pOIFSFileSystem) throws IOException {
        return create(pOIFSFileSystem, (String) null);
    }

    private static SlideShow<?, ?> create(POIFSFileSystem pOIFSFileSystem, String str) throws IOException {
        return create(pOIFSFileSystem.getRoot(), str);
    }

    public static SlideShow<?, ?> create(DirectoryNode directoryNode) throws IOException {
        return create(directoryNode, (String) null);
    }

    public static SlideShow<?, ?> create(DirectoryNode directoryNode, String str) throws IOException {
        if (directoryNode.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY) || directoryNode.hasEntry(ExtractorFactory.OOXML_PACKAGE)) {
            return wp(FileMagic.OOXML, new SlideShowFactory$$ExternalSyntheticLambda7(directoryNode, str));
        }
        return wp(FileMagic.OLE2, new SlideShowFactory$$ExternalSyntheticLambda8(directoryNode, str));
    }

    public static SlideShow<?, ?> create(InputStream inputStream) throws IOException, EncryptedDocumentException {
        return create(inputStream, (String) null);
    }

    public static SlideShow<?, ?> create(InputStream inputStream, String str) throws IOException, EncryptedDocumentException {
        InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(inputStream);
        boolean z = true;
        prepareToCheckMagic.mark(1);
        if (prepareToCheckMagic.read(new byte[1]) >= 1) {
            prepareToCheckMagic.reset();
            FileMagic valueOf = FileMagic.valueOf(prepareToCheckMagic);
            if (FileMagic.OOXML == valueOf) {
                return wp(valueOf, new SlideShowFactory$$ExternalSyntheticLambda4(prepareToCheckMagic));
            }
            if (FileMagic.OLE2 == valueOf) {
                POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(prepareToCheckMagic);
                DirectoryNode root = pOIFSFileSystem.getRoot();
                if (!root.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY) && !root.hasEntry(ExtractorFactory.OOXML_PACKAGE)) {
                    z = false;
                }
                if (z) {
                    valueOf = FileMagic.OOXML;
                }
                return wp(valueOf, new SlideShowFactory$$ExternalSyntheticLambda5(pOIFSFileSystem, str));
            }
            throw new IOException("Can't open slideshow - unsupported file type: " + valueOf);
        }
        throw new EmptyFileException();
    }

    public static SlideShow<?, ?> create(File file) throws IOException, EncryptedDocumentException {
        return create(file, (String) null);
    }

    public static SlideShow<?, ?> create(File file, String str) throws IOException, EncryptedDocumentException {
        return create(file, str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0055, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.sl.usermodel.SlideShow<?, ?> create(java.io.File r5, java.lang.String r6, boolean r7) throws java.io.IOException, org.apache.poi.EncryptedDocumentException {
        /*
            boolean r0 = r5.exists()
            if (r0 == 0) goto L_0x007a
            long r0 = r5.length()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x0074
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.File) r5)
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OOXML
            if (r0 != r1) goto L_0x0022
            org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda2 r1 = new org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda2
            r1.<init>(r5, r6, r7)
            org.apache.poi.sl.usermodel.SlideShow r5 = wp(r0, r1)
            return r5
        L_0x0022:
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2
            if (r0 != r1) goto L_0x005f
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r2 = 1
            r1.<init>((java.io.File) r5, (boolean) r2)
            org.apache.poi.poifs.filesystem.DirectoryNode r3 = r1.getRoot()     // Catch:{ all -> 0x0053 }
            java.lang.String r4 = "EncryptedPackage"
            boolean r4 = r3.hasEntry(r4)     // Catch:{ all -> 0x0053 }
            if (r4 != 0) goto L_0x0042
            java.lang.String r4 = "Package"
            boolean r3 = r3.hasEntry(r4)     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r2 = 0
        L_0x0042:
            r1.close()
            if (r2 == 0) goto L_0x0049
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.OOXML
        L_0x0049:
            org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda3 r1 = new org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda3
            r1.<init>(r5, r6, r7)
            org.apache.poi.sl.usermodel.SlideShow r5 = wp(r0, r1)
            return r5
        L_0x0053:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r6 = move-exception
            r1.close()     // Catch:{ all -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r7 = move-exception
            r5.addSuppressed(r7)
        L_0x005e:
            throw r6
        L_0x005f:
            java.io.IOException r5 = new java.io.IOException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Can't open slideshow - unsupported file type: "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0074:
            org.apache.poi.EmptyFileException r6 = new org.apache.poi.EmptyFileException
            r6.<init>(r5)
            throw r6
        L_0x007a:
            java.io.FileNotFoundException r6 = new java.io.FileNotFoundException
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.SlideShowFactory.create(java.io.File, java.lang.String, boolean):org.apache.poi.sl.usermodel.SlideShow");
    }

    private static SlideShow<?, ?> wp(FileMagic fileMagic, ProviderMethod providerMethod) throws IOException {
        for (SlideShowProvider next : Singleton.INSTANCE.provider) {
            if (next.accepts(fileMagic)) {
                return providerMethod.create(next);
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream or you haven't provide the poi-ooxml*.jar in the classpath/modulepath - FileMagic: " + fileMagic);
    }

    public static void addProvider(SlideShowProvider<?, ?> slideShowProvider) {
        Singleton.INSTANCE.provider.add(slideShowProvider);
    }

    public static void removeProvider(Class<? extends SlideShowProvider<?, ?>> cls) {
        Singleton.INSTANCE.provider.removeIf(new SlideShowFactory$$ExternalSyntheticLambda0(cls));
    }
}
