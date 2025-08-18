package org.apache.poi.openxml4j.opc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

public final class PackageRelationshipCollection implements Iterable<PackageRelationship> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) PackageRelationshipCollection.class);
    private OPCPackage container;
    private HashMap<String, PackageRelationship> internalRelationshipsByTargetName;
    private int nextRelationshipId;
    private PackagePartName partName;
    private PackagePart relationshipPart;
    private final TreeMap<String, PackageRelationship> relationshipsByID;
    private final TreeMap<String, PackageRelationship> relationshipsByType;
    private PackagePart sourcePart;

    PackageRelationshipCollection() {
        this.relationshipsByID = new TreeMap<>();
        this.relationshipsByType = new TreeMap<>();
        this.internalRelationshipsByTargetName = new HashMap<>();
        this.nextRelationshipId = -1;
    }

    public PackageRelationshipCollection(PackageRelationshipCollection packageRelationshipCollection, String str) {
        this();
        for (PackageRelationship next : packageRelationshipCollection.relationshipsByID.values()) {
            if (str == null || next.getRelationshipType().equals(str)) {
                addRelationship(next);
            }
        }
    }

    public PackageRelationshipCollection(OPCPackage oPCPackage) throws InvalidFormatException {
        this(oPCPackage, (PackagePart) null);
    }

    public PackageRelationshipCollection(PackagePart packagePart) throws InvalidFormatException {
        this(packagePart._container, packagePart);
    }

    public PackageRelationshipCollection(OPCPackage oPCPackage, PackagePart packagePart) throws InvalidFormatException {
        this.relationshipsByID = new TreeMap<>();
        this.relationshipsByType = new TreeMap<>();
        this.internalRelationshipsByTargetName = new HashMap<>();
        this.nextRelationshipId = -1;
        if (oPCPackage == null) {
            throw new IllegalArgumentException("container needs to be specified");
        } else if (packagePart == null || !packagePart.isRelationshipPart()) {
            this.container = oPCPackage;
            this.sourcePart = packagePart;
            this.partName = getRelationshipPartName(packagePart);
            if (oPCPackage.getPackageAccess() != PackageAccess.WRITE && oPCPackage.containPart(this.partName)) {
                PackagePart part = oPCPackage.getPart(this.partName);
                this.relationshipPart = part;
                parseRelationshipsPart(part);
            }
        } else {
            throw new IllegalArgumentException("part");
        }
    }

    private static PackagePartName getRelationshipPartName(PackagePart packagePart) throws InvalidOperationException {
        PackagePartName packagePartName;
        if (packagePart == null) {
            packagePartName = PackagingURIHelper.PACKAGE_ROOT_PART_NAME;
        } else {
            packagePartName = packagePart.getPartName();
        }
        return PackagingURIHelper.getRelationshipPartName(packagePartName);
    }

    public void addRelationship(PackageRelationship packageRelationship) {
        String str;
        if (packageRelationship == null || packageRelationship.getId() == null || packageRelationship.getId().isEmpty()) {
            StringBuilder sb = new StringBuilder("invalid relationship part/id: ");
            if (packageRelationship == null) {
                str = "<null>";
            } else {
                str = packageRelationship.getId();
            }
            throw new IllegalArgumentException(sb.append(str).append(" for relationship: ").append(packageRelationship).toString());
        }
        this.relationshipsByID.put(packageRelationship.getId(), packageRelationship);
        this.relationshipsByType.put(packageRelationship.getRelationshipType(), packageRelationship);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.openxml4j.opc.PackageRelationship addRelationship(java.net.URI r9, org.apache.poi.openxml4j.opc.TargetMode r10, java.lang.String r11, java.lang.String r12) {
        /*
            r8 = this;
            if (r12 == 0) goto L_0x000b
            int r0 = r12.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            r7 = r12
            goto L_0x0036
        L_0x000b:
            int r12 = r8.nextRelationshipId
            r0 = -1
            if (r12 != r0) goto L_0x0018
            int r12 = r8.size()
            int r12 = r12 + 1
            r8.nextRelationshipId = r12
        L_0x0018:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r0 = "rId"
            r12.<init>(r0)
            int r0 = r8.nextRelationshipId
            int r1 = r0 + 1
            r8.nextRelationshipId = r1
            java.lang.StringBuilder r12 = r12.append(r0)
            java.lang.String r12 = r12.toString()
            java.util.TreeMap<java.lang.String, org.apache.poi.openxml4j.opc.PackageRelationship> r0 = r8.relationshipsByID
            java.lang.Object r0 = r0.get(r12)
            if (r0 != 0) goto L_0x0018
            goto L_0x0009
        L_0x0036:
            org.apache.poi.openxml4j.opc.PackageRelationship r12 = new org.apache.poi.openxml4j.opc.PackageRelationship
            org.apache.poi.openxml4j.opc.OPCPackage r2 = r8.container
            org.apache.poi.openxml4j.opc.PackagePart r3 = r8.sourcePart
            r1 = r12
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.addRelationship(r12)
            org.apache.poi.openxml4j.opc.TargetMode r11 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL
            if (r10 != r11) goto L_0x0053
            java.util.HashMap<java.lang.String, org.apache.poi.openxml4j.opc.PackageRelationship> r8 = r8.internalRelationshipsByTargetName
            java.lang.String r9 = r9.toASCIIString()
            r8.put(r9, r12)
        L_0x0053:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.PackageRelationshipCollection.addRelationship(java.net.URI, org.apache.poi.openxml4j.opc.TargetMode, java.lang.String, java.lang.String):org.apache.poi.openxml4j.opc.PackageRelationship");
    }

    public void removeRelationship(String str) {
        PackageRelationship packageRelationship = this.relationshipsByID.get(str);
        if (packageRelationship != null) {
            this.relationshipsByID.remove(packageRelationship.getId());
            this.relationshipsByType.values().remove(packageRelationship);
            this.internalRelationshipsByTargetName.values().remove(packageRelationship);
        }
    }

    public PackageRelationship getRelationship(int i) {
        if (i < 0 || i > this.relationshipsByID.values().size()) {
            throw new IllegalArgumentException("index");
        }
        int i2 = 0;
        for (PackageRelationship next : this.relationshipsByID.values()) {
            int i3 = i2 + 1;
            if (i == i2) {
                return next;
            }
            i2 = i3;
        }
        return null;
    }

    public PackageRelationship getRelationshipByID(String str) {
        return this.relationshipsByID.get(str);
    }

    public int size() {
        return this.relationshipsByID.size();
    }

    public boolean isEmpty() {
        return this.relationshipsByID.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a3, code lost:
        if (r11 != null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ad, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseRelationshipsPart(org.apache.poi.openxml4j.opc.PackagePart r11) throws org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        /*
            r10 = this;
            org.apache.logging.log4j.Logger r0 = LOG     // Catch:{ Exception -> 0x00ae }
            org.apache.logging.log4j.LogBuilder r0 = r0.atDebug()     // Catch:{ Exception -> 0x00ae }
            java.lang.String r1 = "Parsing relationship: {}"
            org.apache.poi.openxml4j.opc.PackagePartName r2 = r11.getPartName()     // Catch:{ Exception -> 0x00ae }
            r0.log((java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ Exception -> 0x00ae }
            java.io.InputStream r11 = r11.getInputStream()     // Catch:{ Exception -> 0x00ae }
            org.w3c.dom.Document r0 = org.apache.poi.ooxml.util.DocumentHelper.readDocument((java.io.InputStream) r11)     // Catch:{ all -> 0x00a0 }
            if (r11 == 0) goto L_0x001c
            r11.close()     // Catch:{ Exception -> 0x00ae }
        L_0x001c:
            org.w3c.dom.Element r11 = r0.getDocumentElement()     // Catch:{ Exception -> 0x00ae }
            java.lang.String r0 = "http://schemas.openxmlformats.org/package/2006/relationships"
            java.lang.String r1 = "Relationship"
            org.w3c.dom.NodeList r11 = r11.getElementsByTagNameNS(r0, r1)     // Catch:{ Exception -> 0x00ae }
            int r0 = r11.getLength()     // Catch:{ Exception -> 0x00ae }
            r1 = 0
            r2 = r1
        L_0x002e:
            if (r1 >= r0) goto L_0x009f
            org.w3c.dom.Node r3 = r11.item(r1)     // Catch:{ Exception -> 0x00ae }
            org.w3c.dom.Element r3 = (org.w3c.dom.Element) r3     // Catch:{ Exception -> 0x00ae }
            java.lang.String r4 = "Id"
            java.lang.String r4 = r3.getAttribute(r4)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r5 = "Type"
            java.lang.String r5 = r3.getAttribute(r5)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r6 = "http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties"
            boolean r6 = r5.equals(r6)     // Catch:{ Exception -> 0x00ae }
            if (r6 == 0) goto L_0x0056
            if (r2 != 0) goto L_0x004e
            r2 = 1
            goto L_0x0056
        L_0x004e:
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r10 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException     // Catch:{ Exception -> 0x00ae }
            java.lang.String r11 = "OPC Compliance error [M4.1]: there is more than one core properties relationship in the package !"
            r10.<init>(r11)     // Catch:{ Exception -> 0x00ae }
            throw r10     // Catch:{ Exception -> 0x00ae }
        L_0x0056:
            java.lang.String r6 = "TargetMode"
            org.w3c.dom.Attr r6 = r3.getAttributeNode(r6)     // Catch:{ Exception -> 0x00ae }
            org.apache.poi.openxml4j.opc.TargetMode r7 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ Exception -> 0x00ae }
            if (r6 == 0) goto L_0x0078
            java.lang.String r6 = r6.getValue()     // Catch:{ Exception -> 0x00ae }
            java.util.Locale r7 = java.util.Locale.ROOT     // Catch:{ Exception -> 0x00ae }
            java.lang.String r6 = r6.toLowerCase(r7)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r7 = "internal"
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x00ae }
            if (r6 == 0) goto L_0x0075
            org.apache.poi.openxml4j.opc.TargetMode r6 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ Exception -> 0x00ae }
            goto L_0x0077
        L_0x0075:
            org.apache.poi.openxml4j.opc.TargetMode r6 = org.apache.poi.openxml4j.opc.TargetMode.EXTERNAL     // Catch:{ Exception -> 0x00ae }
        L_0x0077:
            r7 = r6
        L_0x0078:
            java.lang.String r6 = "http://invalid.uri"
            java.net.URI r6 = org.apache.poi.openxml4j.opc.PackagingURIHelper.toURI(r6)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r8 = "Target"
            java.lang.String r3 = r3.getAttribute(r8)     // Catch:{ Exception -> 0x00ae }
            java.net.URI r6 = org.apache.poi.openxml4j.opc.PackagingURIHelper.toURI(r3)     // Catch:{ URISyntaxException -> 0x0089 }
            goto L_0x0099
        L_0x0089:
            r8 = move-exception
            org.apache.logging.log4j.Logger r9 = LOG     // Catch:{ Exception -> 0x00ae }
            org.apache.logging.log4j.LogBuilder r9 = r9.atError()     // Catch:{ Exception -> 0x00ae }
            org.apache.logging.log4j.LogBuilder r8 = r9.withThrowable(r8)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r9 = "Cannot convert {} in a valid relationship URI-> dummy-URI used"
            r8.log((java.lang.String) r9, (java.lang.Object) r3)     // Catch:{ Exception -> 0x00ae }
        L_0x0099:
            r10.addRelationship(r6, r7, r5, r4)     // Catch:{ Exception -> 0x00ae }
            int r1 = r1 + 1
            goto L_0x002e
        L_0x009f:
            return
        L_0x00a0:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r0 = move-exception
            if (r11 == 0) goto L_0x00ad
            r11.close()     // Catch:{ all -> 0x00a9 }
            goto L_0x00ad
        L_0x00a9:
            r11 = move-exception
            r10.addSuppressed(r11)     // Catch:{ Exception -> 0x00ae }
        L_0x00ad:
            throw r0     // Catch:{ Exception -> 0x00ae }
        L_0x00ae:
            r10 = move-exception
            org.apache.poi.openxml4j.exceptions.InvalidFormatException r11 = new org.apache.poi.openxml4j.exceptions.InvalidFormatException
            java.lang.String r0 = "Failed to parse relationships"
            r11.<init>(r0, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.PackageRelationshipCollection.parseRelationshipsPart(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public PackageRelationshipCollection getRelationships(String str) {
        return new PackageRelationshipCollection(this, str);
    }

    public Iterator<PackageRelationship> iterator() {
        return this.relationshipsByID.values().iterator();
    }

    public Spliterator<PackageRelationship> spliterator() {
        return this.relationshipsByID.values().spliterator();
    }

    public Iterator<PackageRelationship> iterator(String str) {
        ArrayList arrayList = new ArrayList();
        for (PackageRelationship next : this.relationshipsByID.values()) {
            if (next.getRelationshipType().equals(str)) {
                arrayList.add(next);
            }
        }
        return arrayList.iterator();
    }

    public void clear() {
        this.relationshipsByID.clear();
        this.relationshipsByType.clear();
        this.internalRelationshipsByTargetName.clear();
    }

    public PackageRelationship findExistingInternalRelation(PackagePart packagePart) {
        return this.internalRelationshipsByTargetName.get(packagePart.getPartName().getName());
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4 = this.relationshipsByID.size() + " relationship(s) = [";
        PackagePart packagePart = this.relationshipPart;
        if (packagePart == null || packagePart._partName == null) {
            str = str4 + "relationshipPart=null";
        } else {
            str = str4 + this.relationshipPart._partName;
        }
        PackagePart packagePart2 = this.sourcePart;
        if (packagePart2 == null || packagePart2._partName == null) {
            str2 = str + ",sourcePart=null";
        } else {
            str2 = str + "," + this.sourcePart._partName;
        }
        if (this.partName != null) {
            str3 = str2 + "," + this.partName;
        } else {
            str3 = str2 + ",uri=null)";
        }
        return str3 + "]";
    }
}
