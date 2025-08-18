package org.apache.poi.openxml4j.opc;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.opc.internal.ContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.poi.openxml4j.opc.internal.PartUnmarshaller;
import org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager;
import org.apache.poi.openxml4j.opc.internal.marshallers.DefaultMarshaller;
import org.apache.poi.openxml4j.opc.internal.marshallers.ZipPackagePropertiesMarshaller;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.NotImplemented;

public abstract class OPCPackage implements RelationshipSource, Closeable {
    private static final Logger LOG = LogManager.getLogger((Class<?>) OPCPackage.class);
    protected static final PackageAccess defaultPackageAccess = PackageAccess.READ_WRITE;
    protected ContentTypeManager contentTypeManager;
    protected final PartMarshaller defaultPartMarshaller = new DefaultMarshaller();
    protected boolean isDirty;
    protected String originalPackagePath;
    protected OutputStream output;
    private final PackageAccess packageAccess;
    protected PackagePropertiesPart packageProperties;
    private PackagePartCollection partList;
    protected final Map<ContentType, PartMarshaller> partMarshallers;
    protected final Map<ContentType, PartUnmarshaller> partUnmarshallers;
    protected PackageRelationshipCollection relationships;

    /* access modifiers changed from: protected */
    public abstract void closeImpl() throws IOException;

    /* access modifiers changed from: protected */
    public abstract PackagePart createPartImpl(PackagePartName packagePartName, String str, boolean z);

    /* access modifiers changed from: protected */
    public abstract void flushImpl();

    /* access modifiers changed from: protected */
    public abstract PackagePartCollection getPartsImpl() throws InvalidFormatException;

    public abstract boolean isClosed();

    /* access modifiers changed from: protected */
    public abstract void removePartImpl(PackagePartName packagePartName);

    /* access modifiers changed from: protected */
    public abstract void revertImpl();

    /* access modifiers changed from: protected */
    public abstract void saveImpl(OutputStream outputStream) throws IOException;

    OPCPackage(PackageAccess packageAccess2) {
        HashMap hashMap = new HashMap(5);
        this.partMarshallers = hashMap;
        HashMap hashMap2 = new HashMap(2);
        this.partUnmarshallers = hashMap2;
        if (getClass() == ZipPackage.class) {
            this.packageAccess = packageAccess2;
            ContentType newCorePropertiesPart = newCorePropertiesPart();
            hashMap2.put(newCorePropertiesPart, new PackagePropertiesUnmarshaller());
            hashMap.put(newCorePropertiesPart, new ZipPackagePropertiesMarshaller());
            return;
        }
        throw new IllegalArgumentException("PackageBase may not be subclassed");
    }

    private static ContentType newCorePropertiesPart() {
        try {
            return new ContentType(ContentTypes.CORE_PROPERTIES_PART);
        } catch (InvalidFormatException e) {
            throw new OpenXML4JRuntimeException("Package.init() : this exception should never happen, if you read this message please send a mail to the developers team. : " + e.getMessage(), e);
        }
    }

    public static OPCPackage open(String str) throws InvalidFormatException {
        return open(str, defaultPackageAccess);
    }

    public static OPCPackage open(File file) throws InvalidFormatException {
        return open(file, defaultPackageAccess);
    }

    public static OPCPackage open(ZipEntrySource zipEntrySource) throws InvalidFormatException {
        ZipPackage zipPackage = new ZipPackage(zipEntrySource, PackageAccess.READ);
        try {
            if (zipPackage.partList == null) {
                zipPackage.getParts();
            }
            return zipPackage;
        } catch (RuntimeException | InvalidFormatException e) {
            IOUtils.closeQuietly(zipPackage);
            throw e;
        }
    }

    public static OPCPackage open(String str, PackageAccess packageAccess2) throws InvalidFormatException, InvalidOperationException {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("'path' must be given");
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            ZipPackage zipPackage = new ZipPackage(str, packageAccess2);
            if (zipPackage.partList == null && packageAccess2 != PackageAccess.WRITE) {
                try {
                    zipPackage.getParts();
                } catch (Throwable th) {
                    IOUtils.closeQuietly(zipPackage);
                    throw th;
                }
            }
            zipPackage.originalPackagePath = new File(str).getAbsolutePath();
            return zipPackage;
        }
        throw new IllegalArgumentException("path must not be a directory");
    }

    public static OPCPackage open(File file, PackageAccess packageAccess2) throws InvalidFormatException {
        if (file == null) {
            throw new IllegalArgumentException("'file' must be given");
        } else if (!file.exists() || !file.isDirectory()) {
            ZipPackage zipPackage = new ZipPackage(file, packageAccess2);
            try {
                if (zipPackage.partList == null && packageAccess2 != PackageAccess.WRITE) {
                    zipPackage.getParts();
                }
                zipPackage.originalPackagePath = file.getAbsolutePath();
                return zipPackage;
            } catch (RuntimeException | InvalidFormatException e) {
                IOUtils.closeQuietly(zipPackage);
                throw e;
            }
        } else {
            throw new IllegalArgumentException("file must not be a directory");
        }
    }

    public static OPCPackage open(InputStream inputStream) throws InvalidFormatException, IOException {
        ZipPackage zipPackage = new ZipPackage(inputStream, PackageAccess.READ_WRITE);
        try {
            if (zipPackage.partList == null) {
                zipPackage.getParts();
            }
            return zipPackage;
        } catch (RuntimeException | InvalidFormatException e) {
            IOUtils.closeQuietly(zipPackage);
            throw e;
        }
    }

    public static OPCPackage openOrCreate(File file) throws InvalidFormatException {
        if (file.exists()) {
            return open(file.getAbsolutePath());
        }
        return create(file);
    }

    public static OPCPackage create(String str) {
        return create(new File(str));
    }

    public static OPCPackage create(File file) {
        if (file == null || (file.exists() && file.isDirectory())) {
            throw new IllegalArgumentException("file");
        } else if (!file.exists()) {
            ZipPackage zipPackage = new ZipPackage();
            zipPackage.originalPackagePath = file.getAbsolutePath();
            configurePackage(zipPackage);
            return zipPackage;
        } else {
            throw new InvalidOperationException("This package (or file) already exists : use the open() method or delete the file.");
        }
    }

    public static OPCPackage create(OutputStream outputStream) {
        ZipPackage zipPackage = new ZipPackage();
        zipPackage.originalPackagePath = null;
        zipPackage.output = outputStream;
        configurePackage(zipPackage);
        return zipPackage;
    }

    private static void configurePackage(OPCPackage oPCPackage) {
        try {
            ZipContentTypeManager zipContentTypeManager = new ZipContentTypeManager((InputStream) null, oPCPackage);
            oPCPackage.contentTypeManager = zipContentTypeManager;
            zipContentTypeManager.addContentType(PackagingURIHelper.createPartName(PackagingURIHelper.PACKAGE_RELATIONSHIPS_ROOT_URI), ContentTypes.RELATIONSHIPS_PART);
            oPCPackage.contentTypeManager.addContentType(PackagingURIHelper.createPartName("/default.xml"), ContentTypes.PLAIN_OLD_XML);
            PackagePropertiesPart packagePropertiesPart = new PackagePropertiesPart(oPCPackage, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
            oPCPackage.packageProperties = packagePropertiesPart;
            packagePropertiesPart.setCreatorProperty("Generated by Apache POI OpenXML4J");
            oPCPackage.packageProperties.setCreatedProperty((Optional<Date>) Optional.of(new Date()));
        } catch (InvalidFormatException e) {
            throw new IllegalStateException(e);
        }
    }

    public void flush() {
        throwExceptionIfReadOnly();
        PackagePropertiesPart packagePropertiesPart = this.packageProperties;
        if (packagePropertiesPart != null) {
            packagePropertiesPart.flush();
        }
        flushImpl();
    }

    public void close() throws IOException {
        if (!isClosed()) {
            if (this.packageAccess == PackageAccess.READ) {
                LOG.atDebug().log("The close() method is intended to SAVE a package. This package is open in READ ONLY mode, use the revert() method instead!");
                revert();
            } else if (this.contentTypeManager == null) {
                LOG.atWarn().log("Unable to call close() on a package that hasn't been fully opened yet");
                revert();
            } else {
                String str = this.originalPackagePath;
                if (str == null || str.trim().isEmpty()) {
                    OutputStream outputStream = this.output;
                    if (outputStream != null) {
                        try {
                            save(outputStream);
                        } finally {
                            this.output.close();
                        }
                    }
                } else {
                    File file = new File(this.originalPackagePath);
                    if (!file.exists() || !this.originalPackagePath.equalsIgnoreCase(file.getAbsolutePath())) {
                        save(file);
                    } else {
                        closeImpl();
                    }
                }
                revert();
                this.contentTypeManager.clearAll();
            }
        }
    }

    public void revert() {
        revertImpl();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addThumbnail(java.lang.String r3) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x002c
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x002c
            char r0 = java.io.File.separatorChar
            int r0 = r3.lastIndexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r3.substring(r0)
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r3)
            r2.addThumbnail(r0, r1)     // Catch:{ all -> 0x0020 }
            r1.close()
            return
        L_0x0020:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r3 = move-exception
            r1.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002b
        L_0x0027:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x002b:
            throw r3
        L_0x002c:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "path"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.OPCPackage.addThumbnail(java.lang.String):void");
    }

    public void addThumbnail(String str, InputStream inputStream) throws IOException {
        PackagePartName packagePartName;
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("filename");
        }
        String contentTypeFromFileExtension = ContentTypes.getContentTypeFromFileExtension(str);
        try {
            packagePartName = PackagingURIHelper.createPartName("/docProps/" + str);
        } catch (InvalidFormatException unused) {
            try {
                packagePartName = PackagingURIHelper.createPartName("/docProps/thumbnail" + str.substring(str.lastIndexOf(46) + 1));
            } catch (InvalidFormatException e) {
                throw new InvalidOperationException("Can't add a thumbnail file named '" + str + "'", e);
            }
        }
        if (getPart(packagePartName) == null) {
            PackagePart createPart = createPart(packagePartName, contentTypeFromFileExtension, false);
            addRelationship(packagePartName, TargetMode.INTERNAL, PackageRelationshipTypes.THUMBNAIL);
            StreamHelper.copyStream(inputStream, createPart.getOutputStream());
            return;
        }
        throw new InvalidOperationException("You already add a thumbnail named '" + str + "'");
    }

    /* access modifiers changed from: package-private */
    public void throwExceptionIfReadOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.READ) {
            throw new InvalidOperationException("Operation not allowed, document open in read only mode!");
        }
    }

    /* access modifiers changed from: package-private */
    public void throwExceptionIfWriteOnly() throws InvalidOperationException {
        if (this.packageAccess == PackageAccess.WRITE) {
            throw new InvalidOperationException("Operation not allowed, document open in write only mode!");
        }
    }

    public PackageProperties getPackageProperties() throws InvalidFormatException {
        throwExceptionIfWriteOnly();
        if (this.packageProperties == null) {
            this.packageProperties = new PackagePropertiesPart(this, PackagingURIHelper.CORE_PROPERTIES_PART_NAME);
        }
        return this.packageProperties;
    }

    public PackagePart getPart(PackagePartName packagePartName) {
        throwExceptionIfWriteOnly();
        if (packagePartName != null) {
            if (this.partList == null) {
                try {
                    getParts();
                } catch (InvalidFormatException unused) {
                    return null;
                }
            }
            return this.partList.get(packagePartName);
        }
        throw new IllegalArgumentException("partName");
    }

    public ArrayList<PackagePart> getPartsByContentType(String str) {
        ArrayList<PackagePart> arrayList = new ArrayList<>();
        for (PackagePart next : this.partList.sortedValues()) {
            if (next.getContentType().equals(str)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public ArrayList<PackagePart> getPartsByRelationshipType(String str) {
        if (str != null) {
            ArrayList<PackagePart> arrayList = new ArrayList<>();
            Iterator<PackageRelationship> it = getRelationshipsByType(str).iterator();
            while (it.hasNext()) {
                PackagePart part = getPart(it.next());
                if (part != null) {
                    arrayList.add(part);
                }
            }
            Collections.sort(arrayList);
            return arrayList;
        }
        throw new IllegalArgumentException("relationshipType");
    }

    public List<PackagePart> getPartsByName(Pattern pattern) {
        if (pattern != null) {
            Matcher matcher = pattern.matcher("");
            ArrayList arrayList = new ArrayList();
            for (PackagePart next : this.partList.sortedValues()) {
                if (matcher.reset(next.getPartName().getName()).matches()) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("name pattern must not be null");
    }

    public PackagePart getPart(PackageRelationship packageRelationship) {
        ensureRelationships();
        Iterator<PackageRelationship> it = this.relationships.iterator();
        while (it.hasNext()) {
            PackageRelationship next = it.next();
            if (next.getRelationshipType().equals(packageRelationship.getRelationshipType())) {
                try {
                    return getPart(PackagingURIHelper.createPartName(next.getTargetURI()));
                } catch (InvalidFormatException unused) {
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        if (r8 != null) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0095, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<org.apache.poi.openxml4j.opc.PackagePart> getParts() throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r10 = this;
            r10.throwExceptionIfWriteOnly()
            org.apache.poi.openxml4j.opc.PackagePartCollection r0 = r10.partList
            if (r0 != 0) goto L_0x00b0
            org.apache.poi.openxml4j.opc.PackagePartCollection r0 = r10.getPartsImpl()
            r10.partList = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            org.apache.poi.openxml4j.opc.PackagePartCollection r1 = r10.partList
            java.util.Collection r1 = r1.sortedValues()
            r0.<init>(r1)
            java.util.Iterator r0 = r0.iterator()
            r1 = 1
            r2 = 0
            r4 = r1
            r3 = r2
        L_0x0020:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x00b0
            java.lang.Object r5 = r0.next()
            org.apache.poi.openxml4j.opc.PackagePart r5 = (org.apache.poi.openxml4j.opc.PackagePart) r5
            r5.loadRelationships()
            java.lang.String r6 = "application/vnd.openxmlformats-package.core-properties+xml"
            java.lang.String r7 = r5.getContentType()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x004a
            if (r3 != 0) goto L_0x003f
            r3 = r1
            goto L_0x004a
        L_0x003f:
            org.apache.logging.log4j.Logger r6 = LOG
            org.apache.logging.log4j.LogBuilder r6 = r6.atWarn()
            java.lang.String r7 = "OPC Compliance error [M4.1]: there is more than one core properties relationship in the package! POI will use only the first, but other software may reject this file."
            r6.log((java.lang.String) r7)
        L_0x004a:
            java.util.Map<org.apache.poi.openxml4j.opc.internal.ContentType, org.apache.poi.openxml4j.opc.internal.PartUnmarshaller> r6 = r10.partUnmarshallers
            org.apache.poi.openxml4j.opc.internal.ContentType r7 = r5._contentType
            java.lang.Object r6 = r6.get(r7)
            org.apache.poi.openxml4j.opc.internal.PartUnmarshaller r6 = (org.apache.poi.openxml4j.opc.internal.PartUnmarshaller) r6
            if (r6 == 0) goto L_0x0020
            org.apache.poi.openxml4j.opc.internal.unmarshallers.UnmarshallContext r7 = new org.apache.poi.openxml4j.opc.internal.unmarshallers.UnmarshallContext
            org.apache.poi.openxml4j.opc.PackagePartName r8 = r5._partName
            r7.<init>(r10, r8)
            java.io.InputStream r8 = r5.getInputStream()     // Catch:{ IOException -> 0x00a1, InvalidOperationException -> 0x0096 }
            org.apache.poi.openxml4j.opc.PackagePart r6 = r6.unmarshall(r7, r8)     // Catch:{ all -> 0x0088 }
            org.apache.poi.openxml4j.opc.PackagePartCollection r7 = r10.partList     // Catch:{ all -> 0x0088 }
            org.apache.poi.openxml4j.opc.PackagePartName r9 = r5.getPartName()     // Catch:{ all -> 0x0088 }
            r7.remove(r9)     // Catch:{ all -> 0x0088 }
            org.apache.poi.openxml4j.opc.PackagePartCollection r7 = r10.partList     // Catch:{ all -> 0x0088 }
            org.apache.poi.openxml4j.opc.PackagePartName r9 = r6._partName     // Catch:{ all -> 0x0088 }
            r7.put(r9, r6)     // Catch:{ all -> 0x0088 }
            boolean r7 = r6 instanceof org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart     // Catch:{ all -> 0x0088 }
            if (r7 == 0) goto L_0x0082
            if (r3 == 0) goto L_0x0082
            if (r4 == 0) goto L_0x0082
            org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart r6 = (org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart) r6     // Catch:{ all -> 0x0088 }
            r10.packageProperties = r6     // Catch:{ all -> 0x0088 }
            r4 = r2
        L_0x0082:
            if (r8 == 0) goto L_0x0020
            r8.close()     // Catch:{ IOException -> 0x00a1, InvalidOperationException -> 0x0096 }
            goto L_0x0020
        L_0x0088:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x008a }
        L_0x008a:
            r7 = move-exception
            if (r8 == 0) goto L_0x0095
            r8.close()     // Catch:{ all -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r8 = move-exception
            r6.addSuppressed(r8)     // Catch:{ IOException -> 0x00a1, InvalidOperationException -> 0x0096 }
        L_0x0095:
            throw r7     // Catch:{ IOException -> 0x00a1, InvalidOperationException -> 0x0096 }
        L_0x0096:
            r10 = move-exception
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r0 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException
            java.lang.String r1 = r10.getMessage()
            r0.<init>(r1, r10)
            throw r0
        L_0x00a1:
            org.apache.logging.log4j.Logger r6 = LOG
            org.apache.logging.log4j.LogBuilder r6 = r6.atWarn()
            java.lang.String r7 = "Unmarshall operation : IOException for {}"
            org.apache.poi.openxml4j.opc.PackagePartName r5 = r5._partName
            r6.log((java.lang.String) r7, (java.lang.Object) r5)
            goto L_0x0020
        L_0x00b0:
            java.util.ArrayList r0 = new java.util.ArrayList
            org.apache.poi.openxml4j.opc.PackagePartCollection r10 = r10.partList
            java.util.Collection r10 = r10.sortedValues()
            r0.<init>(r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.OPCPackage.getParts():java.util.ArrayList");
    }

    public PackagePart createPart(PackagePartName packagePartName, String str) {
        return createPart(packagePartName, str, true);
    }

    /* access modifiers changed from: package-private */
    public PackagePart createPart(PackagePartName packagePartName, String str, boolean z) {
        throwExceptionIfReadOnly();
        if (packagePartName == null) {
            throw new IllegalArgumentException("partName");
        } else if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("contentType");
        } else if (this.partList.containsKey(packagePartName) && !this.partList.get(packagePartName).isDeleted()) {
            throw new PartAlreadyExistsException("A part with the name '" + packagePartName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
        } else if (!str.equals(ContentTypes.CORE_PROPERTIES_PART) || this.packageProperties == null) {
            PackagePart createPartImpl = createPartImpl(packagePartName, str, z);
            try {
                this.contentTypeManager.addContentType(PackagingURIHelper.createPartName("/.xml"), ContentTypes.PLAIN_OLD_XML);
                this.contentTypeManager.addContentType(PackagingURIHelper.createPartName("/.rels"), ContentTypes.RELATIONSHIPS_PART);
                this.contentTypeManager.addContentType(packagePartName, str);
                this.partList.put(packagePartName, createPartImpl);
                this.isDirty = true;
                return createPartImpl;
            } catch (InvalidFormatException e) {
                throw new InvalidOperationException("unable to create default content-type entries.", e);
            }
        } else {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: you try to add more than one core properties relationship in the package !");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0022, code lost:
        if (r2 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002c, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.openxml4j.opc.PackagePart createPart(org.apache.poi.openxml4j.opc.PackagePartName r1, java.lang.String r2, java.io.ByteArrayOutputStream r3) {
        /*
            r0 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r0.createPart(r1, r2)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            if (r3 == 0) goto L_0x002d
            java.io.OutputStream r2 = r0.getOutputStream()     // Catch:{ IOException -> 0x002d }
            if (r2 != 0) goto L_0x0016
            if (r2 == 0) goto L_0x0015
            r2.close()     // Catch:{ IOException -> 0x002d }
        L_0x0015:
            return r1
        L_0x0016:
            r3.writeTo(r2)     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x001e
            r2.close()     // Catch:{ IOException -> 0x002d }
        L_0x001e:
            return r0
        L_0x001f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r3 = move-exception
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ all -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x002d }
        L_0x002c:
            throw r3     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.OPCPackage.createPart(org.apache.poi.openxml4j.opc.PackagePartName, java.lang.String, java.io.ByteArrayOutputStream):org.apache.poi.openxml4j.opc.PackagePart");
    }

    /* access modifiers changed from: protected */
    public PackagePart addPackagePart(PackagePart packagePart) {
        throwExceptionIfReadOnly();
        if (packagePart != null) {
            if (this.partList.containsKey(packagePart._partName)) {
                if (this.partList.get(packagePart._partName).isDeleted()) {
                    packagePart.setDeleted(false);
                    this.partList.remove(packagePart._partName);
                } else {
                    throw new InvalidOperationException("A part with the name '" + packagePart._partName.getName() + "' already exists : Packages shall not contain equivalent part names and package implementers shall neither create nor recognize packages with equivalent part names. [M1.12]");
                }
            }
            this.partList.put(packagePart._partName, packagePart);
            this.isDirty = true;
            return packagePart;
        }
        throw new IllegalArgumentException("part");
    }

    public void removePart(PackagePart packagePart) {
        if (packagePart != null) {
            removePart(packagePart.getPartName());
        }
    }

    public void removePart(PackagePartName packagePartName) {
        PackagePart part;
        throwExceptionIfReadOnly();
        if (packagePartName == null || !containPart(packagePartName)) {
            throw new IllegalArgumentException("partName");
        }
        if (this.partList.containsKey(packagePartName)) {
            this.partList.get(packagePartName).setDeleted(true);
            removePartImpl(packagePartName);
            this.partList.remove(packagePartName);
        } else {
            removePartImpl(packagePartName);
        }
        this.contentTypeManager.removeContentType(packagePartName);
        if (packagePartName.isRelationshipPartURI()) {
            URI sourcePartUriFromRelationshipPartUri = PackagingURIHelper.getSourcePartUriFromRelationshipPartUri(packagePartName.getURI());
            try {
                PackagePartName createPartName = PackagingURIHelper.createPartName(sourcePartUriFromRelationshipPartUri);
                if (createPartName.getURI().equals(PackagingURIHelper.PACKAGE_ROOT_URI)) {
                    clearRelationships();
                } else if (containPart(createPartName) && (part = getPart(createPartName)) != null) {
                    part.clearRelationships();
                }
            } catch (InvalidFormatException unused) {
                LOG.atError().log("Part name URI '{}' is not valid! This message is not intended to be displayed!", (Object) sourcePartUriFromRelationshipPartUri);
                return;
            }
        }
        this.isDirty = true;
    }

    public void removePartRecursive(PackagePartName packagePartName) throws InvalidFormatException {
        PackagePart packagePart = this.partList.get(PackagingURIHelper.getRelationshipPartName(packagePartName));
        PackagePart packagePart2 = this.partList.get(packagePartName);
        if (packagePart != null) {
            Iterator<PackageRelationship> it = new PackageRelationshipCollection(packagePart2).iterator();
            while (it.hasNext()) {
                PackageRelationship next = it.next();
                removePart(PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(next.getSourceURI(), next.getTargetURI())));
            }
            removePart(packagePart._partName);
        }
        removePart(packagePart2._partName);
    }

    public void deletePart(PackagePartName packagePartName) {
        if (packagePartName != null) {
            removePart(packagePartName);
            removePart(PackagingURIHelper.getRelationshipPartName(packagePartName));
            return;
        }
        throw new IllegalArgumentException("partName");
    }

    public void deletePartRecursive(PackagePartName packagePartName) {
        if (packagePartName == null || !containPart(packagePartName)) {
            throw new IllegalArgumentException("partName");
        }
        PackagePart part = getPart(packagePartName);
        removePart(packagePartName);
        try {
            Iterator<PackageRelationship> it = part.getRelationships().iterator();
            while (it.hasNext()) {
                deletePartRecursive(PackagingURIHelper.createPartName(PackagingURIHelper.resolvePartUri(packagePartName.getURI(), it.next().getTargetURI())));
            }
            PackagePartName relationshipPartName = PackagingURIHelper.getRelationshipPartName(packagePartName);
            if (relationshipPartName != null && containPart(relationshipPartName)) {
                removePart(relationshipPartName);
            }
        } catch (InvalidFormatException e) {
            LOG.atWarn().withThrowable(e).log("An exception occurs while deleting part '{}'. Some parts may remain in the package.", (Object) packagePartName.getName());
        }
    }

    public boolean containPart(PackagePartName packagePartName) {
        return getPart(packagePartName) != null;
    }

    public PackageRelationship addRelationship(PackagePartName packagePartName, TargetMode targetMode, String str, String str2) {
        if (str.equals(PackageRelationshipTypes.CORE_PROPERTIES) && this.packageProperties != null) {
            throw new InvalidOperationException("OPC Compliance error [M4.1]: can't add another core properties part ! Use the built-in package method instead.");
        } else if (!packagePartName.isRelationshipPartURI()) {
            ensureRelationships();
            PackageRelationship addRelationship = this.relationships.addRelationship(packagePartName.getURI(), targetMode, str, str2);
            this.isDirty = true;
            return addRelationship;
        } else {
            throw new InvalidOperationException("Rule M1.25: The Relationships part shall not have relationships to any other part.");
        }
    }

    public PackageRelationship addRelationship(PackagePartName packagePartName, TargetMode targetMode, String str) {
        return addRelationship(packagePartName, targetMode, str, (String) null);
    }

    public PackageRelationship addExternalRelationship(String str, String str2) {
        return addExternalRelationship(str, str2, (String) null);
    }

    public PackageRelationship addExternalRelationship(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException(TypedValues.AttributesType.S_TARGET);
        } else if (str2 != null) {
            try {
                URI uri = new URI(str);
                ensureRelationships();
                PackageRelationship addRelationship = this.relationships.addRelationship(uri, TargetMode.EXTERNAL, str2, str3);
                this.isDirty = true;
                return addRelationship;
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid target - " + e);
            }
        } else {
            throw new IllegalArgumentException("relationshipType");
        }
    }

    public void removeRelationship(String str) {
        PackageRelationshipCollection packageRelationshipCollection = this.relationships;
        if (packageRelationshipCollection != null) {
            packageRelationshipCollection.removeRelationship(str);
            this.isDirty = true;
        }
    }

    public PackageRelationshipCollection getRelationships() {
        return getRelationshipsHelper((String) null);
    }

    public PackageRelationshipCollection getRelationshipsByType(String str) {
        throwExceptionIfWriteOnly();
        if (str != null) {
            return getRelationshipsHelper(str);
        }
        throw new IllegalArgumentException("relationshipType");
    }

    private PackageRelationshipCollection getRelationshipsHelper(String str) {
        throwExceptionIfWriteOnly();
        ensureRelationships();
        return this.relationships.getRelationships(str);
    }

    public void clearRelationships() {
        PackageRelationshipCollection packageRelationshipCollection = this.relationships;
        if (packageRelationshipCollection != null) {
            packageRelationshipCollection.clear();
            this.isDirty = true;
        }
    }

    public void ensureRelationships() {
        if (this.relationships == null) {
            try {
                this.relationships = new PackageRelationshipCollection(this);
            } catch (InvalidFormatException unused) {
                this.relationships = new PackageRelationshipCollection();
            }
        }
    }

    public PackageRelationship getRelationship(String str) {
        return this.relationships.getRelationshipByID(str);
    }

    public boolean hasRelationships() {
        return !this.relationships.isEmpty();
    }

    public boolean isRelationshipExists(PackageRelationship packageRelationship) {
        Iterator<PackageRelationship> it = this.relationships.iterator();
        while (it.hasNext()) {
            if (it.next() == packageRelationship) {
                return true;
            }
        }
        return false;
    }

    public void addMarshaller(String str, PartMarshaller partMarshaller) {
        try {
            this.partMarshallers.put(new ContentType(str), partMarshaller);
        } catch (InvalidFormatException e) {
            LOG.atWarn().log("The specified content type is not valid: '{}'. The marshaller will not be added !", (Object) e.getMessage());
        }
    }

    public void addUnmarshaller(String str, PartUnmarshaller partUnmarshaller) {
        try {
            this.partUnmarshallers.put(new ContentType(str), partUnmarshaller);
        } catch (InvalidFormatException e) {
            LOG.atWarn().log("The specified content type is not valid: '{}'. The unmarshaller will not be added !", (Object) e.getMessage());
        }
    }

    public void removeMarshaller(String str) {
        try {
            this.partMarshallers.remove(new ContentType(str));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUnmarshaller(String str) {
        try {
            this.partUnmarshallers.remove(new ContentType(str));
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public PackageAccess getPackageAccess() {
        return this.packageAccess;
    }

    @NotImplemented
    public boolean validatePackage(OPCPackage oPCPackage) throws InvalidFormatException {
        throw new InvalidOperationException("Not implemented yet !!!");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void save(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0038
            r2.throwExceptionIfReadOnly()
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = r3.getAbsolutePath()
            java.lang.String r1 = r2.originalPackagePath
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0018
            goto L_0x0020
        L_0x0018:
            org.apache.poi.openxml4j.exceptions.InvalidOperationException r2 = new org.apache.poi.openxml4j.exceptions.InvalidOperationException
            java.lang.String r3 = "You can't call save(File) to save to the currently open file. To save to the current file, please just call close()"
            r2.<init>(r3)
            throw r2
        L_0x0020:
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r3)
            r2.save((java.io.OutputStream) r0)     // Catch:{ all -> 0x002c }
            r0.close()
            return
        L_0x002c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002e }
        L_0x002e:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x0037:
            throw r3
        L_0x0038:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "targetFile"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.OPCPackage.save(java.io.File):void");
    }

    public void save(OutputStream outputStream) throws IOException {
        throwExceptionIfReadOnly();
        saveImpl(outputStream);
    }

    public boolean replaceContentType(String str, String str2) {
        Iterator<PackagePart> it = getPartsByContentType(str).iterator();
        boolean z = false;
        while (it.hasNext()) {
            PackagePart next = it.next();
            if (next.getContentType().equals(str)) {
                this.contentTypeManager.addContentType(next.getPartName(), str2);
                try {
                    next.setContentType(str2);
                    z = true;
                    this.isDirty = true;
                } catch (InvalidFormatException e) {
                    throw new OpenXML4JRuntimeException("invalid content type - " + str2, e);
                }
            }
        }
        return z;
    }

    public void registerPartAndContentType(PackagePart packagePart) {
        addPackagePart(packagePart);
        this.contentTypeManager.addContentType(packagePart.getPartName(), packagePart.getContentType());
        this.isDirty = true;
    }

    public void unregisterPartAndContentType(PackagePartName packagePartName) {
        removePart(packagePartName);
        this.contentTypeManager.removeContentType(packagePartName);
        this.isDirty = true;
    }

    public int getUnusedPartIndex(String str) throws InvalidFormatException {
        return this.partList.getUnusedPartIndex(str);
    }

    public boolean isStrictOoxmlFormat() {
        return !getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).isEmpty();
    }

    public String toString() {
        return "OPCPackage{packageAccess=" + this.packageAccess + ", relationships=" + this.relationships + ", packageProperties=" + this.packageProperties + ", isDirty=" + this.isDirty + '}';
    }
}
