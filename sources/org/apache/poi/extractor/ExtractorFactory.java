package org.apache.poi.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;

public final class ExtractorFactory {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) ExtractorFactory.class);
    public static final String OOXML_PACKAGE = "Package";
    private static Boolean allPreferEventExtractors;
    private static final ThreadLocal<Boolean> threadPreferEventExtractors = ThreadLocal.withInitial(new ExtractorFactory$$ExternalSyntheticLambda1());
    private final List<ExtractorProvider> provider;

    private interface ProviderMethod {
        POITextExtractor create(ExtractorProvider extractorProvider) throws IOException;
    }

    private static class Singleton {
        /* access modifiers changed from: private */
        public static final ExtractorFactory INSTANCE = new ExtractorFactory();

        private Singleton() {
        }
    }

    private ExtractorFactory() {
        ArrayList arrayList = new ArrayList();
        this.provider = arrayList;
        ServiceLoader.load(ExtractorProvider.class, ExtractorFactory.class.getClassLoader()).forEach(new ExtractorFactory$$ExternalSyntheticLambda8(arrayList));
    }

    public static boolean getThreadPrefersEventExtractors() {
        return threadPreferEventExtractors.get().booleanValue();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return allPreferEventExtractors;
    }

    public static void setThreadPrefersEventExtractors(boolean z) {
        threadPreferEventExtractors.set(Boolean.valueOf(z));
    }

    public static void setAllThreadsPreferEventExtractors(Boolean bool) {
        allPreferEventExtractors = bool;
    }

    public static boolean getPreferEventExtractor() {
        Boolean bool = allPreferEventExtractors;
        if (bool == null) {
            bool = threadPreferEventExtractors.get();
        }
        return bool.booleanValue();
    }

    public static POITextExtractor createExtractor(POIFSFileSystem pOIFSFileSystem) throws IOException {
        return createExtractor(pOIFSFileSystem, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(POIFSFileSystem pOIFSFileSystem, String str) throws IOException {
        return createExtractor(pOIFSFileSystem.getRoot(), str);
    }

    public static POITextExtractor createExtractor(InputStream inputStream) throws IOException {
        return createExtractor(inputStream, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(InputStream inputStream, String str) throws IOException {
        InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(inputStream);
        boolean z = true;
        prepareToCheckMagic.mark(1);
        if (prepareToCheckMagic.read(new byte[1]) >= 1) {
            prepareToCheckMagic.reset();
            FileMagic valueOf = FileMagic.valueOf(prepareToCheckMagic);
            if (FileMagic.OOXML == valueOf) {
                return wp(valueOf, new ExtractorFactory$$ExternalSyntheticLambda5(prepareToCheckMagic, str));
            }
            if (FileMagic.OLE2 == valueOf) {
                DirectoryNode root = new POIFSFileSystem(prepareToCheckMagic).getRoot();
                if (!root.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY) && !root.hasEntry(OOXML_PACKAGE)) {
                    z = false;
                }
                if (z) {
                    valueOf = FileMagic.OOXML;
                }
                return wp(valueOf, new ExtractorFactory$$ExternalSyntheticLambda6(root, str));
            }
            throw new IOException("Can't create extractor - unsupported file type: " + valueOf);
        }
        throw new EmptyFileException();
    }

    public static POITextExtractor createExtractor(File file) throws IOException {
        return createExtractor(file, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(File file, String str) throws IOException {
        if (file.length() != 0) {
            FileMagic valueOf = FileMagic.valueOf(file);
            if (FileMagic.OOXML == valueOf) {
                return wp(valueOf, new ExtractorFactory$$ExternalSyntheticLambda0(file, str));
            }
            if (FileMagic.OLE2 == valueOf) {
                POIFSFileSystem pOIFSFileSystem = null;
                try {
                    boolean z = true;
                    POIFSFileSystem pOIFSFileSystem2 = new POIFSFileSystem(file, true);
                    try {
                        DirectoryNode root = pOIFSFileSystem2.getRoot();
                        if (!root.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY)) {
                            if (!root.hasEntry(OOXML_PACKAGE)) {
                                z = false;
                            }
                        }
                        if (z) {
                            valueOf = FileMagic.OOXML;
                        }
                        return wp(valueOf, new ExtractorFactory$$ExternalSyntheticLambda2(root, str));
                    } catch (IOException | RuntimeException e) {
                        e = e;
                        pOIFSFileSystem = pOIFSFileSystem2;
                        IOUtils.closeQuietly(pOIFSFileSystem);
                        throw e;
                    }
                } catch (IOException | RuntimeException e2) {
                    e = e2;
                    IOUtils.closeQuietly(pOIFSFileSystem);
                    throw e;
                }
            } else {
                throw new IOException("Can't create extractor - unsupported file type: " + valueOf);
            }
        } else {
            throw new EmptyFileException(file);
        }
    }

    public static POITextExtractor createExtractor(DirectoryNode directoryNode) throws IOException {
        return createExtractor(directoryNode, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(DirectoryNode directoryNode, String str) throws IOException {
        if (directoryNode.hasEntry(Decryptor.DEFAULT_POIFS_ENTRY) || directoryNode.hasEntry(OOXML_PACKAGE)) {
            return wp(FileMagic.OOXML, new ExtractorFactory$$ExternalSyntheticLambda9(directoryNode, str));
        }
        return wp(FileMagic.OLE2, new ExtractorFactory$$ExternalSyntheticLambda10(directoryNode, str));
    }

    public static POITextExtractor[] getEmbeddedDocsTextExtractors(POIOLE2TextExtractor pOIOLE2TextExtractor) throws IOException {
        if (pOIOLE2TextExtractor != null) {
            ArrayList<Entry> arrayList = new ArrayList<>();
            ArrayList<InputStream> arrayList2 = new ArrayList<>();
            DirectoryEntry root = pOIOLE2TextExtractor.getRoot();
            if (root != null) {
                if (!(pOIOLE2TextExtractor instanceof ExcelExtractor)) {
                    Iterator<ExtractorProvider> it = Singleton.INSTANCE.provider.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ExtractorProvider next = it.next();
                        if (next.accepts(FileMagic.OLE2)) {
                            next.identifyEmbeddedResources(pOIOLE2TextExtractor, arrayList, arrayList2);
                            break;
                        }
                    }
                } else {
                    StreamSupport.stream(root.spliterator(), false).filter(new ExtractorFactory$$ExternalSyntheticLambda3()).forEach(new ExtractorFactory$$ExternalSyntheticLambda4(arrayList));
                }
                if (arrayList.isEmpty() && arrayList2.isEmpty()) {
                    return new POITextExtractor[0];
                }
                ArrayList arrayList3 = new ArrayList();
                for (Entry entry : arrayList) {
                    arrayList3.add(createExtractor((DirectoryNode) entry));
                }
                for (InputStream createExtractor : arrayList2) {
                    try {
                        arrayList3.add(createExtractor(createExtractor));
                    } catch (IOException e) {
                        LOGGER.atInfo().log("Format not supported yet ({})", (Object) e.getLocalizedMessage());
                    }
                }
                return (POITextExtractor[]) arrayList3.toArray(new POITextExtractor[0]);
            }
            throw new IllegalStateException("The extractor didn't know which POIFS it came from!");
        }
        throw new IllegalStateException("extractor must be given");
    }

    private static POITextExtractor wp(FileMagic fileMagic, ProviderMethod providerMethod) throws IOException {
        POITextExtractor create;
        for (ExtractorProvider next : Singleton.INSTANCE.provider) {
            if (next.accepts(fileMagic) && (create = providerMethod.create(next)) != null) {
                return create;
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream or you haven't provide the poi-ooxml*.jar and/or poi-scratchpad*.jar in the classpath/modulepath - FileMagic: " + fileMagic + ", providers: " + Singleton.INSTANCE.provider);
    }

    public static void addProvider(ExtractorProvider extractorProvider) {
        Singleton.INSTANCE.provider.add(extractorProvider);
    }

    public static void removeProvider(Class<? extends ExtractorProvider> cls) {
        Singleton.INSTANCE.provider.removeIf(new ExtractorFactory$$ExternalSyntheticLambda7(cls));
    }
}
