package org.apache.poi.poifs.macros;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.macros.Module;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RLEDecompressingInputStream;
import org.apache.poi.util.StringUtil;

public class VBAMacroReader implements Closeable {
    private static final int DOC_STRING_RESERVED = 64;
    private static final int HELP_FILE_PATH_RESERVED = 61;
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) VBAMacroReader.class);
    private static final int MAX_STRING_LENGTH = 20000;
    private static final int MODULE_DOCSTRING_RESERVED = 72;
    private static final int PROJECT_CONSTANTS_RESERVED = 60;
    private static final int REFERENCE_NAME_RESERVED = 62;
    private static final int STREAMNAME_RESERVED = 50;
    protected static final String VBA_PROJECT_OOXML = "vbaProject.bin";
    protected static final String VBA_PROJECT_POIFS = "VBA";
    private POIFSFileSystem fs;

    private enum DIR_STATE {
        INFORMATION_RECORD,
        REFERENCES_RECORD,
        MODULES_RECORD
    }

    public VBAMacroReader(InputStream inputStream) throws IOException {
        InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(inputStream);
        if (FileMagic.valueOf(prepareToCheckMagic) == FileMagic.OLE2) {
            this.fs = new POIFSFileSystem(prepareToCheckMagic);
        } else {
            openOOXML(prepareToCheckMagic);
        }
    }

    public VBAMacroReader(File file) throws IOException {
        try {
            this.fs = new POIFSFileSystem(file);
        } catch (OfficeXmlFileException unused) {
            openOOXML(new FileInputStream(file));
        }
    }

    public VBAMacroReader(POIFSFileSystem pOIFSFileSystem) {
        this.fs = pOIFSFileSystem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openOOXML(java.io.InputStream r3) throws java.io.IOException {
        /*
            r2 = this;
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            r0.<init>(r3)
        L_0x0005:
            java.util.zip.ZipEntry r3 = r0.getNextEntry()     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x0027
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = "vbaProject.bin"
            boolean r3 = org.apache.poi.util.StringUtil.endsWithIgnoreCase(r3, r1)     // Catch:{ all -> 0x0032 }
            if (r3 == 0) goto L_0x0005
            org.apache.poi.poifs.filesystem.POIFSFileSystem r3 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ IOException -> 0x0022 }
            r3.<init>((java.io.InputStream) r0)     // Catch:{ IOException -> 0x0022 }
            r2.fs = r3     // Catch:{ IOException -> 0x0022 }
            r0.close()
            return
        L_0x0022:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0032 }
            throw r2     // Catch:{ all -> 0x0032 }
        L_0x0027:
            r0.close()
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "No VBA project found"
            r2.<init>(r3)
            throw r2
        L_0x0032:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.openOOXML(java.io.InputStream):void");
    }

    public void close() throws IOException {
        this.fs.close();
        this.fs = null;
    }

    public Map<String, Module> readMacroModules() throws IOException {
        ModuleMap moduleMap = new ModuleMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        findMacros(this.fs.getRoot(), moduleMap);
        findModuleNameMap(this.fs.getRoot(), linkedHashMap, moduleMap);
        findProjectProperties(this.fs.getRoot(), linkedHashMap, moduleMap);
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : moduleMap.entrySet()) {
            ModuleImpl moduleImpl = (ModuleImpl) entry.getValue();
            moduleImpl.charset = moduleMap.charset;
            hashMap.put(entry.getKey(), moduleImpl);
        }
        return hashMap;
    }

    public Map<String, String> readMacros() throws IOException {
        Map<String, Module> readMacroModules = readMacroModules();
        HashMap hashMap = new HashMap();
        for (Map.Entry next : readMacroModules.entrySet()) {
            hashMap.put(next.getKey(), ((Module) next.getValue()).getContent());
        }
        return hashMap;
    }

    protected static class ModuleImpl implements Module {
        byte[] buf;
        Charset charset;
        Module.ModuleType moduleType;
        Integer offset;

        protected ModuleImpl() {
        }

        /* access modifiers changed from: package-private */
        public void read(InputStream inputStream) throws IOException {
            this.buf = IOUtils.toByteArray(inputStream);
        }

        public String getContent() {
            return new String(this.buf, this.charset);
        }

        public Module.ModuleType geModuleType() {
            return this.moduleType;
        }
    }

    protected static class ModuleMap extends HashMap<String, ModuleImpl> {
        Charset charset = StringUtil.WIN_1252;

        protected ModuleMap() {
        }
    }

    /* access modifiers changed from: protected */
    public void findMacros(DirectoryNode directoryNode, ModuleMap moduleMap) throws IOException {
        if (VBA_PROJECT_POIFS.equalsIgnoreCase(directoryNode.getName())) {
            readMacros(directoryNode, moduleMap);
            return;
        }
        Iterator<Entry> it = directoryNode.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next instanceof DirectoryNode) {
                findMacros((DirectoryNode) next, moduleMap);
            }
        }
    }

    private static void readModuleMetadataFromDirStream(RLEDecompressingInputStream rLEDecompressingInputStream, String str, ModuleMap moduleMap) throws IOException {
        int readInt = rLEDecompressingInputStream.readInt();
        ModuleImpl moduleImpl = (ModuleImpl) moduleMap.get(str);
        if (moduleImpl == null) {
            ModuleImpl moduleImpl2 = new ModuleImpl();
            moduleImpl2.offset = Integer.valueOf(readInt);
            moduleMap.put(str, moduleImpl2);
            return;
        }
        RLEDecompressingInputStream rLEDecompressingInputStream2 = new RLEDecompressingInputStream(new UnsynchronizedByteArrayInputStream(moduleImpl.buf, readInt, moduleImpl.buf.length - readInt));
        moduleImpl.read(rLEDecompressingInputStream2);
        rLEDecompressingInputStream2.close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0056, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r1.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0066, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0080, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0085, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0086, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0089, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void readModuleFromDocumentStream(org.apache.poi.poifs.filesystem.DocumentNode r3, java.lang.String r4, org.apache.poi.poifs.macros.VBAMacroReader.ModuleMap r5) throws java.io.IOException {
        /*
            java.lang.Object r0 = r5.get(r4)
            org.apache.poi.poifs.macros.VBAMacroReader$ModuleImpl r0 = (org.apache.poi.poifs.macros.VBAMacroReader.ModuleImpl) r0
            if (r0 != 0) goto L_0x0029
            org.apache.poi.poifs.macros.VBAMacroReader$ModuleImpl r0 = new org.apache.poi.poifs.macros.VBAMacroReader$ModuleImpl
            r0.<init>()
            r5.put(r4, r0)
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r4.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r3)
            r0.read(r4)     // Catch:{ all -> 0x001d }
            r4.close()
            goto L_0x00a5
        L_0x001d:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001f }
        L_0x001f:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0028:
            throw r5
        L_0x0029:
            byte[] r5 = r0.buf
            if (r5 != 0) goto L_0x00a5
            java.lang.Integer r5 = r0.offset
            if (r5 == 0) goto L_0x008a
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = new org.apache.poi.poifs.filesystem.DocumentInputStream     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0067 }
            r4.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r3)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0067 }
            java.lang.Integer r5 = r0.offset     // Catch:{ all -> 0x005b }
            int r5 = r5.intValue()     // Catch:{ all -> 0x005b }
            long r1 = (long) r5     // Catch:{ all -> 0x005b }
            trySkip(r4, r1)     // Catch:{ all -> 0x005b }
            org.apache.poi.util.RLEDecompressingInputStream r5 = new org.apache.poi.util.RLEDecompressingInputStream     // Catch:{ all -> 0x005b }
            r5.<init>(r4)     // Catch:{ all -> 0x005b }
            r0.read(r5)     // Catch:{ all -> 0x004f }
            r5.close()     // Catch:{ all -> 0x005b }
            r4.close()     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0067 }
            return
        L_0x004f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r2 = move-exception
            r5.close()     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r5 = move-exception
            r1.addSuppressed(r5)     // Catch:{ all -> 0x005b }
        L_0x005a:
            throw r2     // Catch:{ all -> 0x005b }
        L_0x005b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x005d }
        L_0x005d:
            r1 = move-exception
            r4.close()     // Catch:{ all -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0067 }
        L_0x0066:
            throw r1     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0067 }
        L_0x0067:
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r4.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r3)
            byte[] r3 = findCompressedStreamWBruteForce(r4)     // Catch:{ all -> 0x007e }
            r4.close()
            if (r3 == 0) goto L_0x00a5
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r4 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream
            r4.<init>(r3)
            r0.read(r4)
            goto L_0x00a5
        L_0x007e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0089:
            throw r5
        L_0x008a:
            java.io.IOException r3 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Module offset for '"
            r5.<init>(r0)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r5 = "' was never read."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x00a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.readModuleFromDocumentStream(org.apache.poi.poifs.filesystem.DocumentNode, java.lang.String, org.apache.poi.poifs.macros.VBAMacroReader$ModuleMap):void");
    }

    private static void trySkip(InputStream inputStream, long j) throws IOException {
        long skipFully = IOUtils.skipFully(inputStream, j);
        if (skipFully == j) {
            return;
        }
        if (skipFully < 0) {
            throw new IOException("Tried skipping " + j + " bytes, but no bytes were skipped. The end of the stream has been reached or the stream is closed.");
        }
        throw new IOException("Tried skipping " + j + " bytes, but only " + skipFully + " bytes were skipped. This should never happen with a non-corrupt file.");
    }

    /* access modifiers changed from: protected */
    public void readMacros(DirectoryNode directoryNode, ModuleMap moduleMap) throws IOException {
        Iterator<String> it = directoryNode.getEntryNames().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if ("dir".equalsIgnoreCase(next)) {
                processDirStream(directoryNode.getEntry(next), moduleMap);
                break;
            }
        }
        Iterator<Entry> it2 = directoryNode.iterator();
        while (it2.hasNext()) {
            Entry next2 = it2.next();
            if (next2 instanceof DocumentNode) {
                String name = next2.getName();
                DocumentNode documentNode = (DocumentNode) next2;
                if (!"dir".equalsIgnoreCase(name) && !StringUtil.startsWithIgnoreCase(name, "__SRP") && !StringUtil.startsWithIgnoreCase(name, "_VBA_PROJECT")) {
                    readModuleFromDocumentStream(documentNode, name, moduleMap);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findProjectProperties(org.apache.poi.poifs.filesystem.DirectoryNode r4, java.util.Map<java.lang.String, java.lang.String> r5, org.apache.poi.poifs.macros.VBAMacroReader.ModuleMap r6) throws java.io.IOException {
        /*
            r3 = this;
            java.util.Iterator r4 = r4.iterator()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0040
            java.lang.Object r0 = r4.next()
            org.apache.poi.poifs.filesystem.Entry r0 = (org.apache.poi.poifs.filesystem.Entry) r0
            java.lang.String r1 = "project"
            java.lang.String r2 = r0.getName()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x0036
            org.apache.poi.poifs.filesystem.DocumentNode r0 = (org.apache.poi.poifs.filesystem.DocumentNode) r0
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r4.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r0)
            r3.readProjectProperties(r4, r5, r6)     // Catch:{ all -> 0x002a }
            r4.close()
            return
        L_0x002a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002c }
        L_0x002c:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0035:
            throw r5
        L_0x0036:
            boolean r1 = r0 instanceof org.apache.poi.poifs.filesystem.DirectoryNode
            if (r1 == 0) goto L_0x0004
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = (org.apache.poi.poifs.filesystem.DirectoryNode) r0
            r3.findProjectProperties(r0, r5, r6)
            goto L_0x0004
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.findProjectProperties(org.apache.poi.poifs.filesystem.DirectoryNode, java.util.Map, org.apache.poi.poifs.macros.VBAMacroReader$ModuleMap):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        r3.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findModuleNameMap(org.apache.poi.poifs.filesystem.DirectoryNode r4, java.util.Map<java.lang.String, java.lang.String> r5, org.apache.poi.poifs.macros.VBAMacroReader.ModuleMap r6) throws java.io.IOException {
        /*
            r3 = this;
            java.util.Iterator r4 = r4.iterator()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0044
            java.lang.Object r0 = r4.next()
            org.apache.poi.poifs.filesystem.Entry r0 = (org.apache.poi.poifs.filesystem.Entry) r0
            java.lang.String r1 = "projectwm"
            java.lang.String r2 = r0.getName()
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L_0x0038
            org.apache.poi.poifs.filesystem.DocumentNode r0 = (org.apache.poi.poifs.filesystem.DocumentNode) r0
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r4.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r0)
            java.nio.charset.Charset r6 = r6.charset     // Catch:{ all -> 0x002c }
            r3.readNameMapRecords(r4, r5, r6)     // Catch:{ all -> 0x002c }
            r4.close()
            return
        L_0x002c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002e }
        L_0x002e:
            r5 = move-exception
            r4.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            r3.addSuppressed(r4)
        L_0x0037:
            throw r5
        L_0x0038:
            boolean r1 = r0.isDirectoryEntry()
            if (r1 == 0) goto L_0x0004
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = (org.apache.poi.poifs.filesystem.DirectoryNode) r0
            r3.findModuleNameMap(r0, r5, r6)
            goto L_0x0004
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.findModuleNameMap(org.apache.poi.poifs.filesystem.DirectoryNode, java.util.Map, org.apache.poi.poifs.macros.VBAMacroReader$ModuleMap):void");
    }

    private enum RecordType {
        MODULE_OFFSET(49),
        PROJECT_SYS_KIND(1),
        PROJECT_LCID(2),
        PROJECT_LCID_INVOKE(20),
        PROJECT_CODEPAGE(3),
        PROJECT_NAME(4),
        PROJECT_DOC_STRING(5),
        PROJECT_HELP_FILE_PATH(6),
        PROJECT_HELP_CONTEXT(7, 8),
        PROJECT_LIB_FLAGS(8),
        PROJECT_VERSION(9, 10),
        PROJECT_CONSTANTS(12),
        PROJECT_MODULES(15),
        DIR_STREAM_TERMINATOR(16),
        PROJECT_COOKIE(19),
        MODULE_NAME(25),
        MODULE_NAME_UNICODE(71),
        MODULE_STREAM_NAME(26),
        MODULE_DOC_STRING(28),
        MODULE_HELP_CONTEXT(30),
        MODULE_COOKIE(44),
        MODULE_TYPE_PROCEDURAL(33, 4),
        MODULE_TYPE_OTHER(34, 4),
        MODULE_PRIVATE(40, 4),
        REFERENCE_NAME(22),
        REFERENCE_REGISTERED(13),
        REFERENCE_PROJECT(14),
        REFERENCE_CONTROL_A(47),
        REFERENCE_CONTROL_B(51),
        MODULE_TERMINATOR(43),
        EOF(-1),
        UNKNOWN(-2);
        
        private final int constantLength;
        /* access modifiers changed from: private */
        public final int id;

        private RecordType(int i) {
            this.id = i;
            this.constantLength = -1;
        }

        private RecordType(int i, int i2) {
            this.id = i;
            this.constantLength = i2;
        }

        /* access modifiers changed from: package-private */
        public int getConstantLength() {
            return this.constantLength;
        }

        static RecordType lookup(int i) {
            for (RecordType recordType : values()) {
                if (recordType.id == i) {
                    return recordType;
                }
            }
            return UNKNOWN;
        }
    }

    private static class ASCIIUnicodeStringPair {
        private final String ascii;
        private final int pushbackRecordId;
        private final String unicode;

        ASCIIUnicodeStringPair(String str, int i) {
            this.ascii = str;
            this.unicode = "";
            this.pushbackRecordId = i;
        }

        ASCIIUnicodeStringPair(String str, String str2) {
            this.ascii = str;
            this.unicode = str2;
            this.pushbackRecordId = -1;
        }

        /* access modifiers changed from: private */
        public String getAscii() {
            return this.ascii;
        }

        private String getUnicode() {
            return this.unicode;
        }

        /* access modifiers changed from: private */
        public int getPushbackRecordId() {
            return this.pushbackRecordId;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01d8, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dd, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01de, code lost:
        r9.addSuppressed(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e1, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processDirStream(org.apache.poi.poifs.filesystem.Entry r10, org.apache.poi.poifs.macros.VBAMacroReader.ModuleMap r11) throws java.io.IOException {
        /*
            r9 = this;
            org.apache.poi.poifs.filesystem.DocumentNode r10 = (org.apache.poi.poifs.filesystem.DocumentNode) r10
            org.apache.poi.poifs.macros.VBAMacroReader$DIR_STATE r0 = org.apache.poi.poifs.macros.VBAMacroReader.DIR_STATE.INFORMATION_RECORD
            org.apache.poi.poifs.filesystem.DocumentInputStream r1 = new org.apache.poi.poifs.filesystem.DocumentInputStream
            r1.<init>((org.apache.poi.poifs.filesystem.DocumentEntry) r10)
            r10 = 0
            org.apache.poi.util.RLEDecompressingInputStream r2 = new org.apache.poi.util.RLEDecompressingInputStream     // Catch:{ IOException -> 0x01a9 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x01a9 }
            r3 = 0
            r4 = r10
        L_0x0011:
            int r4 = r2.readShort()     // Catch:{ all -> 0x019a }
            r5 = -1
            if (r4 != r5) goto L_0x001a
            goto L_0x0190
        L_0x001a:
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r6 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.lookup(r4)     // Catch:{ all -> 0x019a }
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r7 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.EOF     // Catch:{ all -> 0x019a }
            boolean r7 = r6.equals(r7)     // Catch:{ all -> 0x019a }
            if (r7 != 0) goto L_0x0190
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r7 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.DIR_STREAM_TERMINATOR     // Catch:{ all -> 0x019a }
            boolean r7 = r6.equals(r7)     // Catch:{ all -> 0x019a }
            if (r7 == 0) goto L_0x0030
            goto L_0x0190
        L_0x0030:
            int[] r7 = org.apache.poi.poifs.macros.VBAMacroReader.AnonymousClass1.$SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ all -> 0x019a }
            int r8 = r6.ordinal()     // Catch:{ all -> 0x019a }
            r7 = r7[r8]     // Catch:{ all -> 0x019a }
            r8 = 62
            switch(r7) {
                case 1: goto L_0x016e;
                case 2: goto L_0x015a;
                case 3: goto L_0x014c;
                case 4: goto L_0x0143;
                case 5: goto L_0x013a;
                case 6: goto L_0x0131;
                case 7: goto L_0x00c0;
                case 8: goto L_0x00e4;
                case 9: goto L_0x0089;
                case 10: goto L_0x0082;
                case 11: goto L_0x0079;
                case 12: goto L_0x0047;
                case 13: goto L_0x0043;
                default: goto L_0x003d;
            }     // Catch:{ all -> 0x019a }
        L_0x003d:
            int r7 = r6.getConstantLength()     // Catch:{ all -> 0x019a }
            goto L_0x017a
        L_0x0043:
            r2.readInt()     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0047:
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            int r5 = r2.readShort()     // Catch:{ all -> 0x019a }
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r6 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_NAME     // Catch:{ all -> 0x019a }
            int r6 = r6.id     // Catch:{ all -> 0x019a }
            if (r5 != r6) goto L_0x0064
            java.nio.charset.Charset r5 = r11.charset     // Catch:{ all -> 0x019a }
            r9.readStringPair(r2, r5, r8)     // Catch:{ all -> 0x019a }
            int r5 = r2.readShort()     // Catch:{ all -> 0x019a }
        L_0x0064:
            r6 = 48
            if (r5 != r6) goto L_0x0071
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0071:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r10 = "Expected 0x30 as Reserved3 in a ReferenceControl record"
            r9.<init>(r10)     // Catch:{ all -> 0x019a }
            throw r9     // Catch:{ all -> 0x019a }
        L_0x0079:
            org.apache.poi.poifs.macros.VBAMacroReader$DIR_STATE r0 = org.apache.poi.poifs.macros.VBAMacroReader.DIR_STATE.MODULES_RECORD     // Catch:{ all -> 0x019a }
            r2.readInt()     // Catch:{ all -> 0x019a }
            r2.readShort()     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0082:
            r2.readInt()     // Catch:{ all -> 0x019a }
            readModuleMetadataFromDirStream(r2, r3, r11)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0089:
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            java.nio.charset.Charset r6 = r11.charset     // Catch:{ all -> 0x019a }
            readString(r2, r5, r6)     // Catch:{ all -> 0x019a }
            int r5 = r2.readShort()     // Catch:{ all -> 0x019a }
            r6 = 72
            if (r5 != r6) goto L_0x00a3
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            r9.readUnicodeString(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x00a3:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x019a }
            r10.<init>()     // Catch:{ all -> 0x019a }
            java.lang.String r11 = "Expected x003C after stream name before Unicode stream name, but found: "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r11 = java.lang.Integer.toHexString(r5)     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x019a }
            r9.<init>(r10)     // Catch:{ all -> 0x019a }
            throw r9     // Catch:{ all -> 0x019a }
        L_0x00c0:
            org.apache.poi.poifs.macros.VBAMacroReader$DIR_STATE r6 = org.apache.poi.poifs.macros.VBAMacroReader.DIR_STATE.INFORMATION_RECORD     // Catch:{ all -> 0x019a }
            boolean r6 = r0.equals(r6)     // Catch:{ all -> 0x019a }
            if (r6 == 0) goto L_0x00ca
            org.apache.poi.poifs.macros.VBAMacroReader$DIR_STATE r0 = org.apache.poi.poifs.macros.VBAMacroReader.DIR_STATE.REFERENCES_RECORD     // Catch:{ all -> 0x019a }
        L_0x00ca:
            java.nio.charset.Charset r6 = r11.charset     // Catch:{ all -> 0x019a }
            org.apache.poi.poifs.macros.VBAMacroReader$ASCIIUnicodeStringPair r6 = r9.readStringPair(r2, r6, r8, r10)     // Catch:{ all -> 0x019a }
            int r7 = r6.getPushbackRecordId()     // Catch:{ all -> 0x019a }
            if (r7 != r5) goto L_0x00d8
            goto L_0x0011
        L_0x00d8:
            int r5 = r6.getPushbackRecordId()     // Catch:{ all -> 0x019a }
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r7 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_REGISTERED     // Catch:{ all -> 0x019a }
            int r7 = r7.id     // Catch:{ all -> 0x019a }
            if (r5 != r7) goto L_0x00ee
        L_0x00e4:
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x00ee:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x019a }
            r10.<init>()     // Catch:{ all -> 0x019a }
            java.lang.String r11 = "Unexpected reserved character. Expected "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r11 = java.lang.Integer.toHexString(r8)     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r11 = " or "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r11 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_REGISTERED     // Catch:{ all -> 0x019a }
            int r11 = r11.id     // Catch:{ all -> 0x019a }
            java.lang.String r11 = java.lang.Integer.toHexString(r11)     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r11 = " not: "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            int r11 = r6.getPushbackRecordId()     // Catch:{ all -> 0x019a }
            java.lang.String r11 = java.lang.Integer.toHexString(r11)     // Catch:{ all -> 0x019a }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x019a }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x019a }
            r9.<init>(r10)     // Catch:{ all -> 0x019a }
            throw r9     // Catch:{ all -> 0x019a }
        L_0x0131:
            java.nio.charset.Charset r5 = r11.charset     // Catch:{ all -> 0x019a }
            r6 = 60
            r9.readStringPair(r2, r5, r6)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x013a:
            java.nio.charset.Charset r5 = r11.charset     // Catch:{ all -> 0x019a }
            r6 = 61
            r9.readStringPair(r2, r5, r6)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0143:
            java.nio.charset.Charset r5 = r11.charset     // Catch:{ all -> 0x019a }
            r6 = 64
            r9.readStringPair(r2, r5, r6)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x014c:
            java.nio.charset.Charset r3 = r11.charset     // Catch:{ all -> 0x019a }
            r5 = 50
            org.apache.poi.poifs.macros.VBAMacroReader$ASCIIUnicodeStringPair r3 = r9.readStringPair(r2, r3, r5)     // Catch:{ all -> 0x019a }
            java.lang.String r3 = r3.getAscii()     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x015a:
            r2.readInt()     // Catch:{ all -> 0x019a }
            int r5 = r2.readShort()     // Catch:{ all -> 0x019a }
            r6 = 1
            java.lang.String r5 = org.apache.poi.util.CodePageUtil.codepageToEncoding(r5, r6)     // Catch:{ all -> 0x019a }
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)     // Catch:{ all -> 0x019a }
            r11.charset = r5     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x016e:
            org.apache.poi.poifs.macros.VBAMacroReader$RecordType r5 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_VERSION     // Catch:{ all -> 0x019a }
            int r5 = r5.getConstantLength()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x017a:
            if (r7 <= r5) goto L_0x0186
            int r5 = r6.getConstantLength()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0186:
            int r5 = r2.readInt()     // Catch:{ all -> 0x019a }
            long r5 = (long) r5     // Catch:{ all -> 0x019a }
            trySkip(r2, r5)     // Catch:{ all -> 0x019a }
            goto L_0x0011
        L_0x0190:
            r2.close()     // Catch:{ IOException -> 0x0197 }
            r1.close()
            return
        L_0x0197:
            r9 = move-exception
            r10 = r4
            goto L_0x01aa
        L_0x019a:
            r9 = move-exception
            r10 = r4
            throw r9     // Catch:{ all -> 0x019d }
        L_0x019d:
            r11 = move-exception
            r2.close()     // Catch:{ all -> 0x01a2 }
            goto L_0x01a6
        L_0x01a2:
            r0 = move-exception
            r9.addSuppressed(r0)     // Catch:{ IOException -> 0x01a9 }
        L_0x01a6:
            throw r11     // Catch:{ IOException -> 0x01a9 }
        L_0x01a7:
            r9 = move-exception
            goto L_0x01d7
        L_0x01a9:
            r9 = move-exception
        L_0x01aa:
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x01a7 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a7 }
            r0.<init>()     // Catch:{ all -> 0x01a7 }
            java.lang.String r2 = "Error occurred while reading macros at section id "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a7 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ all -> 0x01a7 }
            java.lang.String r2 = " ("
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a7 }
            java.lang.String r10 = org.apache.poi.util.HexDump.shortToHex(r10)     // Catch:{ all -> 0x01a7 }
            java.lang.StringBuilder r10 = r0.append(r10)     // Catch:{ all -> 0x01a7 }
            java.lang.String r0 = ")"
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x01a7 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x01a7 }
            r11.<init>(r10, r9)     // Catch:{ all -> 0x01a7 }
            throw r11     // Catch:{ all -> 0x01a7 }
        L_0x01d7:
            throw r9     // Catch:{ all -> 0x01d8 }
        L_0x01d8:
            r10 = move-exception
            r1.close()     // Catch:{ all -> 0x01dd }
            goto L_0x01e1
        L_0x01dd:
            r11 = move-exception
            r9.addSuppressed(r11)
        L_0x01e1:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.processDirStream(org.apache.poi.poifs.filesystem.Entry, org.apache.poi.poifs.macros.VBAMacroReader$ModuleMap):void");
    }

    /* renamed from: org.apache.poi.poifs.macros.VBAMacroReader$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType[] r0 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType = r0
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_VERSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_CODEPAGE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.MODULE_STREAM_NAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_DOC_STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_HELP_FILE_PATH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_CONSTANTS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_NAME     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_REGISTERED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.MODULE_DOC_STRING     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.MODULE_OFFSET     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.PROJECT_MODULES     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.REFERENCE_CONTROL_A     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$macros$VBAMacroReader$RecordType     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.poi.poifs.macros.VBAMacroReader$RecordType r1 = org.apache.poi.poifs.macros.VBAMacroReader.RecordType.MODULE_TERMINATOR     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.AnonymousClass1.<clinit>():void");
        }
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream rLEDecompressingInputStream, Charset charset, int i) throws IOException {
        return readStringPair(rLEDecompressingInputStream, charset, i, true);
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream rLEDecompressingInputStream, Charset charset, int i, boolean z) throws IOException {
        String readString = readString(rLEDecompressingInputStream, rLEDecompressingInputStream.readInt(), charset);
        int readShort = rLEDecompressingInputStream.readShort();
        if (readShort == i) {
            return new ASCIIUnicodeStringPair(readString, readUnicodeString(rLEDecompressingInputStream, rLEDecompressingInputStream.readInt()));
        }
        if (!z) {
            return new ASCIIUnicodeStringPair(readString, readShort);
        }
        throw new IOException("Expected " + Integer.toHexString(i) + "after name before Unicode name, but found: " + Integer.toHexString(readShort));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readNameMapRecords(java.io.InputStream r4, java.util.Map<java.lang.String, java.lang.String> r5, java.nio.charset.Charset r6) throws java.io.IOException {
        /*
            r3 = this;
            r3 = 0
        L_0x0001:
            int r3 = r3 + 1
            r0 = 10000(0x2710, float:1.4013E-41)
            if (r3 >= r0) goto L_0x0035
            int r0 = org.apache.poi.util.IOUtils.readByte(r4)     // Catch:{ EOFException -> 0x0034 }
            if (r0 != 0) goto L_0x0014
            int r0 = org.apache.poi.util.IOUtils.readByte(r4)     // Catch:{ EOFException -> 0x0034 }
            if (r0 != 0) goto L_0x0014
            return
        L_0x0014:
            java.lang.String r0 = readMBCS(r0, r4, r6)     // Catch:{ EOFException -> 0x0034 }
            java.lang.String r1 = readUnicode(r4)     // Catch:{  }
            java.lang.String r2 = r0.trim()
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x0001
            java.lang.String r2 = r1.trim()
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x0001
            r5.put(r0, r1)
            goto L_0x0001
        L_0x0034:
            return
        L_0x0035:
            org.apache.logging.log4j.Logger r3 = LOGGER
            org.apache.logging.log4j.LogBuilder r3 = r3.atWarn()
            java.lang.String r4 = "Hit max name records to read (10000). Stopped early."
            r3.log((java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.readNameMapRecords(java.io.InputStream, java.util.Map, java.nio.charset.Charset):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        r6.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readUnicode(java.io.InputStream r6) throws java.io.IOException {
        /*
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            int r1 = org.apache.poi.util.IOUtils.readByte(r6)     // Catch:{ all -> 0x0042 }
            int r2 = org.apache.poi.util.IOUtils.readByte(r6)     // Catch:{ all -> 0x0042 }
            r3 = 2
        L_0x000e:
            int r4 = r1 + r2
            r5 = 20000(0x4e20, float:2.8026E-41)
            if (r4 == 0) goto L_0x0027
            if (r3 >= r5) goto L_0x0027
            r0.write((int) r1)     // Catch:{ all -> 0x0042 }
            r0.write((int) r2)     // Catch:{ all -> 0x0042 }
            int r1 = org.apache.poi.util.IOUtils.readByte(r6)     // Catch:{ all -> 0x0042 }
            int r2 = org.apache.poi.util.IOUtils.readByte(r6)     // Catch:{ all -> 0x0042 }
            int r3 = r3 + 2
            goto L_0x000e
        L_0x0027:
            if (r3 < r5) goto L_0x0038
            org.apache.logging.log4j.Logger r6 = LOGGER     // Catch:{ all -> 0x0042 }
            org.apache.logging.log4j.LogBuilder r6 = r6.atWarn()     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = "stopped reading unicode name after {} bytes"
            java.lang.StringBuilder r2 = org.apache.logging.log4j.util.Unbox.box((int) r3)     // Catch:{ all -> 0x0042 }
            r6.log((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0042 }
        L_0x0038:
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_16LE     // Catch:{ all -> 0x0042 }
            java.lang.String r6 = r0.toString((java.nio.charset.Charset) r6)     // Catch:{ all -> 0x0042 }
            r0.close()
            return r6
        L_0x0042:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r0 = move-exception
            r6.addSuppressed(r0)
        L_0x004d:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.readUnicode(java.io.InputStream):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readMBCS(int r3, java.io.InputStream r4, java.nio.charset.Charset r5) throws java.io.IOException {
        /*
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            r1 = 0
        L_0x0006:
            if (r3 <= 0) goto L_0x0016
            r2 = 20000(0x4e20, float:2.8026E-41)
            if (r1 >= r2) goto L_0x0016
            int r1 = r1 + 1
            r0.write((int) r3)     // Catch:{ all -> 0x001e }
            int r3 = org.apache.poi.util.IOUtils.readByte(r4)     // Catch:{ all -> 0x001e }
            goto L_0x0006
        L_0x0016:
            java.lang.String r3 = r0.toString((java.nio.charset.Charset) r5)     // Catch:{ all -> 0x001e }
            r0.close()
            return r3
        L_0x001e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0020 }
        L_0x0020:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0029:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.readMBCS(int, java.io.InputStream, java.nio.charset.Charset):java.lang.String");
    }

    private static String readString(InputStream inputStream, int i, Charset charset) throws IOException {
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, MAX_STRING_LENGTH);
        int readFully = IOUtils.readFully(inputStream, safelyAllocate);
        if (readFully == i) {
            return new String(safelyAllocate, 0, i, charset);
        }
        throw new IOException("Tried to read: " + i + ", but could only read: " + readFully);
    }

    /* access modifiers changed from: protected */
    public void readProjectProperties(DocumentInputStream documentInputStream, Map<String, String> map, ModuleMap moduleMap) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(documentInputStream, moduleMap.charset);
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[512];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read < 0) {
                break;
            }
            sb.append(cArr, 0, read);
        }
        for (String str : sb.toString().split("\r\n|\n\r")) {
            if (!str.startsWith("[")) {
                String[] split = str.split("=");
                if (split.length > 1 && split[1].length() > 1 && split[1].startsWith("\"") && split[1].endsWith("\"")) {
                    String str2 = split[1];
                    split[1] = str2.substring(1, str2.length() - 1);
                }
                if ("Document".equals(split[0]) && split.length > 1) {
                    String str3 = split[1];
                    String substring = str3.substring(0, str3.indexOf("/&H"));
                    ModuleImpl module = getModule(substring, map, moduleMap);
                    if (module != null) {
                        module.moduleType = Module.ModuleType.Document;
                    } else {
                        LOGGER.atWarn().log("couldn't find module with name: {}", (Object) substring);
                    }
                } else if ("Module".equals(split[0]) && split.length > 1) {
                    ModuleImpl module2 = getModule(split[1], map, moduleMap);
                    if (module2 != null) {
                        module2.moduleType = Module.ModuleType.Module;
                    } else {
                        LOGGER.atWarn().log("couldn't find module with name: {}", (Object) split[1]);
                    }
                } else if ("Class".equals(split[0]) && split.length > 1) {
                    ModuleImpl module3 = getModule(split[1], map, moduleMap);
                    if (module3 != null) {
                        module3.moduleType = Module.ModuleType.Class;
                    } else {
                        LOGGER.atWarn().log("couldn't find module with name: {}", (Object) split[1]);
                    }
                }
            }
        }
    }

    private ModuleImpl getModule(String str, Map<String, String> map, ModuleMap moduleMap) {
        if (map.containsKey(str)) {
            return (ModuleImpl) moduleMap.get(map.get(str));
        }
        return (ModuleImpl) moduleMap.get(str);
    }

    private String readUnicodeString(RLEDecompressingInputStream rLEDecompressingInputStream, int i) throws IOException {
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, MAX_STRING_LENGTH);
        if (IOUtils.readFully((InputStream) rLEDecompressingInputStream, safelyAllocate) == i) {
            return new String(safelyAllocate, StringUtil.UTF16LE);
        }
        throw new EOFException();
    }

    private static byte[] findCompressedStreamWBruteForce(InputStream inputStream) throws IOException {
        int uShort;
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        byte[] bArr = null;
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] == 1 && i < byteArray.length - 1 && (uShort = LittleEndian.getUShort(byteArray, i + 1)) > 0 && (uShort & 28672) == 12288 && (bArr = tryToDecompress(new UnsynchronizedByteArrayInputStream(byteArray, i, byteArray.length - i))) != null && bArr.length > 9 && new String(bArr, 0, Math.min(20, bArr.length), StringUtil.WIN_1252).contains("Attribute")) {
                return bArr;
            }
        }
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0018, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] tryToDecompress(java.io.InputStream r2) {
        /*
            org.apache.poi.util.RLEDecompressingInputStream r0 = new org.apache.poi.util.RLEDecompressingInputStream     // Catch:{ IOException | IllegalArgumentException | IllegalStateException -> 0x0019 }
            r0.<init>(r2)     // Catch:{ IOException | IllegalArgumentException | IllegalStateException -> 0x0019 }
            byte[] r2 = org.apache.poi.util.IOUtils.toByteArray(r0)     // Catch:{ all -> 0x000d }
            r0.close()     // Catch:{ IOException | IllegalArgumentException | IllegalStateException -> 0x0019 }
            return r2
        L_0x000d:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x000f }
        L_0x000f:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0014 }
            goto L_0x0018
        L_0x0014:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ IOException | IllegalArgumentException | IllegalStateException -> 0x0019 }
        L_0x0018:
            throw r1     // Catch:{ IOException | IllegalArgumentException | IllegalStateException -> 0x0019 }
        L_0x0019:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.tryToDecompress(java.io.InputStream):byte[]");
    }
}
