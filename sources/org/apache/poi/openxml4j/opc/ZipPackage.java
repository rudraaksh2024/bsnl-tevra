package org.apache.poi.openxml4j.opc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.util.Supplier;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.EncryptedTempFilePackagePart;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.apache.poi.openxml4j.opc.internal.MemoryPackagePart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.TempFilePackagePart;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPartMarshaller;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;
import org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.TempFile;

public final class ZipPackage extends OPCPackage {
    /* access modifiers changed from: private */
    public static final Logger LOG = LogManager.getLogger((Class<?>) ZipPackage.class);
    private static final String MIMETYPE = "mimetype";
    private static final String SETTINGS_XML = "settings.xml";
    private static boolean encryptTempFilePackageParts = false;
    private static boolean useTempFilePackageParts = false;
    private final ZipEntrySource zipArchive;

    /* access modifiers changed from: protected */
    public void flushImpl() {
    }

    public static void setUseTempFilePackageParts(boolean z) {
        useTempFilePackageParts = z;
    }

    public static void setEncryptTempFilePackageParts(boolean z) {
        encryptTempFilePackageParts = z;
    }

    public static boolean useTempFilePackageParts() {
        return useTempFilePackageParts;
    }

    public static boolean encryptTempFilePackageParts() {
        return encryptTempFilePackageParts;
    }

    public ZipPackage() {
        super(defaultPackageAccess);
        this.zipArchive = null;
        try {
            this.contentTypeManager = new ZipContentTypeManager((InputStream) null, this);
        } catch (InvalidFormatException e) {
            LOG.atWarn().withThrowable(e).log("Could not parse ZipPackage");
        }
    }

    ZipPackage(InputStream inputStream, PackageAccess packageAccess) throws IOException {
        super(packageAccess);
        ZipArchiveThresholdInputStream openZipStream = ZipHelper.openZipStream(inputStream);
        try {
            this.zipArchive = new ZipInputStreamZipEntrySource(openZipStream);
        } catch (IOException | RuntimeException e) {
            IOUtils.closeQuietly(openZipStream);
            throw e;
        }
    }

    ZipPackage(String str, PackageAccess packageAccess) throws InvalidOperationException {
        this(new File(str), packageAccess);
    }

    ZipPackage(File file, PackageAccess packageAccess) throws InvalidOperationException {
        super(packageAccess);
        ZipEntrySource zipEntrySource;
        try {
            zipEntrySource = new ZipFileZipEntrySource(ZipHelper.openZipFile(file));
        } catch (IOException e) {
            if (packageAccess != PackageAccess.WRITE) {
                LOG.atError().log("Error in zip file {} - falling back to stream processing (i.e. ignoring zip central directory)", (Object) file);
                zipEntrySource = openZipEntrySourceStream(file);
            } else {
                throw new InvalidOperationException("Can't open the specified file: '" + file + "'", e);
            }
        }
        this.zipArchive = zipEntrySource;
    }

    private static ZipEntrySource openZipEntrySourceStream(File file) throws InvalidOperationException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return openZipEntrySourceStream(fileInputStream);
            } catch (UnsupportedFileFormatException | InvalidOperationException e) {
                IOUtils.closeQuietly(fileInputStream);
                throw e;
            } catch (Exception e2) {
                IOUtils.closeQuietly(fileInputStream);
                throw new InvalidOperationException("Failed to read the file input stream from file: '" + file + "'", e2);
            }
        } catch (FileNotFoundException e3) {
            throw new InvalidOperationException("Can't open the specified file input stream from file: '" + file + "'", e3);
        }
    }

    private static ZipEntrySource openZipEntrySourceStream(FileInputStream fileInputStream) throws InvalidOperationException {
        try {
            ZipArchiveThresholdInputStream openZipStream = ZipHelper.openZipStream(fileInputStream);
            try {
                return openZipEntrySourceStream(openZipStream);
            } catch (UnsupportedFileFormatException | InvalidOperationException e) {
                IOUtils.closeQuietly(openZipStream);
                throw e;
            } catch (Exception e2) {
                IOUtils.closeQuietly(openZipStream);
                throw new InvalidOperationException("Failed to read the zip entry source stream", e2);
            }
        } catch (IOException e3) {
            throw new InvalidOperationException("Could not open the file input stream", e3);
        }
    }

    private static ZipEntrySource openZipEntrySourceStream(ZipArchiveThresholdInputStream zipArchiveThresholdInputStream) throws InvalidOperationException {
        try {
            return new ZipInputStreamZipEntrySource(zipArchiveThresholdInputStream);
        } catch (IOException e) {
            throw new InvalidOperationException("Could not open the specified zip entry source stream", e);
        }
    }

    ZipPackage(ZipEntrySource zipEntrySource, PackageAccess packageAccess) {
        super(packageAccess);
        this.zipArchive = zipEntrySource;
    }

    /* access modifiers changed from: protected */
    public PackagePartCollection getPartsImpl() throws InvalidFormatException {
        PackagePartCollection packagePartCollection = new PackagePartCollection();
        ZipEntrySource zipEntrySource = this.zipArchive;
        if (zipEntrySource == null) {
            return packagePartCollection;
        }
        ZipArchiveEntry entry = zipEntrySource.getEntry(ContentTypeManager.CONTENT_TYPES_PART_NAME);
        if (entry == null) {
            boolean z = true;
            boolean z2 = this.zipArchive.getEntry(MIMETYPE) != null;
            if (this.zipArchive.getEntry(SETTINGS_XML) == null) {
                z = false;
            }
            if (z2 && z) {
                throw new ODFNotOfficeXmlFileException("The supplied data appears to be in ODF (Open Document) Format. Formats like these (eg ODS, ODP) are not supported, try Apache ODFToolkit");
            } else if (!this.zipArchive.getEntries().hasMoreElements()) {
                throw new NotOfficeXmlFileException("No valid entries or contents found, this is not a valid OOXML (Office Open XML) file");
            } else {
                throw new InvalidFormatException("Package should contain a content type part [M1.13]");
            }
        } else if (this.contentTypeManager == null) {
            try {
                this.contentTypeManager = new ZipContentTypeManager(this.zipArchive.getInputStream(entry), this);
                for (EntryTriple register : (List) Collections.list(this.zipArchive.getEntries()).stream().filter(new ZipPackage$$ExternalSyntheticLambda1()).map(new ZipPackage$$ExternalSyntheticLambda2(this)).filter(new ZipPackage$$ExternalSyntheticLambda3()).sorted().collect(Collectors.toList())) {
                    register.register(packagePartCollection);
                }
                return packagePartCollection;
            } catch (IOException e) {
                throw new InvalidFormatException(e.getMessage(), e);
            }
        } else {
            throw new InvalidFormatException("ContentTypeManager can only be created once. This must be a cyclic relation?");
        }
    }

    static /* synthetic */ boolean lambda$getPartsImpl$0(ZipArchiveEntry zipArchiveEntry) {
        return !ignoreEntry(zipArchiveEntry);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getPartsImpl$1$org-apache-poi-openxml4j-opc-ZipPackage  reason: not valid java name */
    public /* synthetic */ EntryTriple m2213lambda$getPartsImpl$1$orgapachepoiopenxml4jopcZipPackage(ZipArchiveEntry zipArchiveEntry) {
        return new EntryTriple(zipArchiveEntry, this.contentTypeManager);
    }

    static /* synthetic */ boolean lambda$getPartsImpl$2(EntryTriple entryTriple) {
        return entryTriple.partName != null;
    }

    /* access modifiers changed from: private */
    public static boolean ignoreEntry(ZipArchiveEntry zipArchiveEntry) {
        String name = zipArchiveEntry.getName();
        return name.startsWith("[trash]") || name.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING);
    }

    private class EntryTriple implements Comparable<EntryTriple> {
        final String contentType;
        final PackagePartName partName;
        final ZipArchiveEntry zipArchiveEntry;

        /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        EntryTriple(org.apache.commons.compress.archivers.zip.ZipArchiveEntry r4, org.apache.poi.openxml4j.opc.internal.ContentTypeManager r5) {
            /*
                r2 = this;
                org.apache.poi.openxml4j.opc.ZipPackage.this = r3
                r2.<init>()
                r2.zipArchiveEntry = r4
                java.lang.String r3 = r4.getName()
                boolean r4 = org.apache.poi.openxml4j.opc.ZipPackage.ignoreEntry(r4)
                r0 = 0
                if (r4 != 0) goto L_0x0036
                java.lang.String r4 = "[Content_Types].xml"
                boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0024 }
                if (r4 == 0) goto L_0x001b
                goto L_0x0036
            L_0x001b:
                java.lang.String r4 = org.apache.poi.openxml4j.opc.internal.ZipHelper.getOPCNameFromZipItemName(r3)     // Catch:{ Exception -> 0x0024 }
                org.apache.poi.openxml4j.opc.PackagePartName r3 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r4)     // Catch:{ Exception -> 0x0024 }
                goto L_0x0037
            L_0x0024:
                r4 = move-exception
                org.apache.logging.log4j.Logger r1 = org.apache.poi.openxml4j.opc.ZipPackage.LOG
                org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
                org.apache.logging.log4j.LogBuilder r4 = r1.withThrowable(r4)
                java.lang.String r1 = "Entry {} is not valid, so this part won't be added to the package."
                r4.log((java.lang.String) r1, (java.lang.Object) r3)
            L_0x0036:
                r3 = r0
            L_0x0037:
                r2.partName = r3
                if (r3 != 0) goto L_0x003c
                goto L_0x0040
            L_0x003c:
                java.lang.String r0 = r5.getContentType(r3)
            L_0x0040:
                r2.contentType = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.ZipPackage.EntryTriple.<init>(org.apache.poi.openxml4j.opc.ZipPackage, org.apache.commons.compress.archivers.zip.ZipArchiveEntry, org.apache.poi.openxml4j.opc.internal.ContentTypeManager):void");
        }

        /* access modifiers changed from: package-private */
        public void register(PackagePartCollection packagePartCollection) throws InvalidFormatException {
            if (this.contentType == null) {
                throw new InvalidFormatException("The part " + this.partName.getURI().getPath() + " does not have any content type ! Rule: Package require content types when retrieving a part from a package. [M.1.14]");
            } else if (!packagePartCollection.containsKey(this.partName)) {
                try {
                    packagePartCollection.put(this.partName, new ZipPackagePart(ZipPackage.this, this.zipArchiveEntry, this.partName, this.contentType, false));
                } catch (InvalidOperationException e) {
                    throw new InvalidFormatException(e.getMessage(), e);
                }
            } else {
                throw new InvalidFormatException("A part with the name '" + this.partName + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
            }
        }

        public int compareTo(EntryTriple entryTriple) {
            int i = -1;
            int i2 = ContentTypes.RELATIONSHIPS_PART.equals(this.contentType) ? -1 : 1;
            if (!ContentTypes.RELATIONSHIPS_PART.equals(entryTriple.contentType)) {
                i = 1;
            }
            int compare = Integer.compare(i2, i);
            return compare != 0 ? compare : this.partName.compareTo(entryTriple.partName);
        }
    }

    /* access modifiers changed from: protected */
    public PackagePart createPartImpl(PackagePartName packagePartName, String str, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("contentType");
        } else if (packagePartName != null) {
            try {
                if (!useTempFilePackageParts) {
                    return new MemoryPackagePart(this, packagePartName, str, z);
                }
                if (encryptTempFilePackageParts) {
                    return new EncryptedTempFilePackagePart(this, packagePartName, str, z);
                }
                return new TempFilePackagePart(this, packagePartName, str, z);
            } catch (Exception e) {
                LOG.atWarn().withThrowable(e).log("Failed to create part {}", (Object) packagePartName);
                return null;
            }
        } else {
            throw new IllegalArgumentException("partName");
        }
    }

    /* access modifiers changed from: protected */
    public void removePartImpl(PackagePartName packagePartName) {
        if (packagePartName == null) {
            throw new IllegalArgumentException("partUri");
        }
    }

    /* access modifiers changed from: protected */
    public void closeImpl() throws IOException {
        flush();
        if (this.originalPackagePath != null && !this.originalPackagePath.isEmpty()) {
            File file = new File(this.originalPackagePath);
            if (file.exists()) {
                File createTempFile = TempFile.createTempFile(generateTempFileName(FileHelper.getDirectory(file)), ".tmp");
                try {
                    save(createTempFile);
                    IOUtils.closeQuietly(this.zipArchive);
                    try {
                        FileHelper.copyFile(createTempFile, file);
                    } finally {
                        if (!createTempFile.delete()) {
                            LOG.atWarn().log("The temporary file: '{}' cannot be deleted ! Make sure that no other application use it.", (Object) file.getAbsolutePath());
                        }
                    }
                } catch (Throwable th) {
                    IOUtils.closeQuietly(this.zipArchive);
                    if (!createTempFile.delete()) {
                        LOG.atWarn().log("The temporary file: '{}' cannot be deleted ! Make sure that no other application use it.", (Object) file.getAbsolutePath());
                    }
                    throw th;
                }
            } else {
                throw new InvalidOperationException("Can't close a package not previously open with the open() method !");
            }
        }
    }

    private synchronized String generateTempFileName(File file) {
        File file2;
        do {
            file2 = new File(file.getAbsoluteFile() + File.separator + "OpenXML4J" + System.nanoTime());
        } while (file2.exists());
        return FileHelper.getFilename(file2.getAbsoluteFile());
    }

    /* access modifiers changed from: protected */
    public void revertImpl() {
        try {
            ZipEntrySource zipEntrySource = this.zipArchive;
            if (zipEntrySource != null) {
                zipEntrySource.close();
            }
        } catch (IOException unused) {
        }
    }

    public void saveImpl(OutputStream outputStream) {
        throwExceptionIfReadOnly();
        ZipArchiveOutputStream zipArchiveOutputStream = outputStream instanceof ZipArchiveOutputStream ? (ZipArchiveOutputStream) outputStream : new ZipArchiveOutputStream(outputStream);
        try {
            if (getPartsByRelationshipType(PackageRelationshipTypes.CORE_PROPERTIES).isEmpty() && getPartsByRelationshipType(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376).isEmpty()) {
                LOG.atDebug().log("Save core properties part");
                getPackageProperties();
                addPackagePart(this.packageProperties);
                this.relationships.addRelationship(this.packageProperties.getPartName().getURI(), TargetMode.INTERNAL, PackageRelationshipTypes.CORE_PROPERTIES, (String) null);
                if (!this.contentTypeManager.isContentTypeRegister(ContentTypes.CORE_PROPERTIES_PART)) {
                    this.contentTypeManager.addContentType(this.packageProperties.getPartName(), ContentTypes.CORE_PROPERTIES_PART);
                }
            }
            Logger logger = LOG;
            logger.atDebug().log("Save content types part");
            this.contentTypeManager.save(zipArchiveOutputStream);
            logger.atDebug().log("Save package relationships");
            ZipPartMarshaller.marshallRelationshipPart(getRelationships(), PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_PART_NAME, zipArchiveOutputStream);
            Iterator<PackagePart> it = getParts().iterator();
            while (it.hasNext()) {
                PackagePart next = it.next();
                if (!next.isRelationshipPart()) {
                    PackagePartName partName = next.getPartName();
                    LOG.atDebug().log((Supplier<Message>) new ZipPackage$$ExternalSyntheticLambda0(partName));
                    PartMarshaller partMarshaller = (PartMarshaller) this.partMarshallers.get(next._contentType);
                    if (partMarshaller == null) {
                        partMarshaller = this.defaultPartMarshaller;
                    }
                    if (!partMarshaller.marshall(next, zipArchiveOutputStream)) {
                        throw new OpenXML4JException("The part " + partName.getURI() + " failed to be saved in the stream with marshaller " + partMarshaller + ". Enable logging via Log4j 2 for more details.");
                    }
                }
            }
            zipArchiveOutputStream.finish();
        } catch (OpenXML4JRuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new OpenXML4JRuntimeException("Fail to save: an error occurs while saving the package : " + e2.getMessage(), e2);
        }
    }

    static /* synthetic */ Message lambda$saveImpl$3(PackagePartName packagePartName) {
        return new SimpleMessage("Save part '" + ZipHelper.getZipItemNameFromOPCName(packagePartName.getName()) + "'");
    }

    public ZipEntrySource getZipArchive() {
        return this.zipArchive;
    }

    public boolean isClosed() {
        ZipEntrySource zipEntrySource = this.zipArchive;
        return zipEntrySource != null && zipEntrySource.isClosed();
    }
}
