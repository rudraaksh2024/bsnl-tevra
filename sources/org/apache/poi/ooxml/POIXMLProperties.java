package org.apache.poi.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.StreamHelper;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;

public class POIXMLProperties {
    private static final PropertiesDocument NEW_CUST_INSTANCE;
    private static final org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument NEW_EXT_INSTANCE;
    private CoreProperties core = new CoreProperties((PackagePropertiesPart) this.pkg.getPackageProperties());
    private CustomProperties cust;
    private PackagePart custPart;
    private ExtendedProperties ext;
    private PackagePart extPart;
    private OPCPackage pkg;

    static {
        org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument newInstance = org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument.Factory.newInstance();
        NEW_EXT_INSTANCE = newInstance;
        newInstance.addNewProperties();
        PropertiesDocument newInstance2 = PropertiesDocument.Factory.newInstance();
        NEW_CUST_INSTANCE = newInstance2;
        newInstance2.addNewProperties();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0060, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0061, code lost:
        if (r6 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0067, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r5.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c7, code lost:
        if (r6 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cd, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ce, code lost:
        r5.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public POIXMLProperties(org.apache.poi.openxml4j.opc.OPCPackage r6) throws java.io.IOException, org.apache.poi.openxml4j.exceptions.OpenXML4JException, org.apache.xmlbeans.XmlException {
        /*
            r5 = this;
            r5.<init>()
            r5.pkg = r6
            org.apache.poi.ooxml.POIXMLProperties$CoreProperties r6 = new org.apache.poi.ooxml.POIXMLProperties$CoreProperties
            org.apache.poi.openxml4j.opc.OPCPackage r0 = r5.pkg
            org.apache.poi.openxml4j.opc.PackageProperties r0 = r0.getPackageProperties()
            org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart r0 = (org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart) r0
            r1 = 0
            r6.<init>(r0)
            r5.core = r6
            org.apache.poi.openxml4j.opc.OPCPackage r6 = r5.pkg
            java.lang.String r0 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties"
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r6 = r6.getRelationshipsByType(r0)
            int r0 = r6.size()
            r2 = 0
            r3 = 1
            if (r0 != r3) goto L_0x006c
            org.apache.poi.openxml4j.opc.OPCPackage r0 = r5.pkg
            org.apache.poi.openxml4j.opc.PackageRelationship r6 = r6.getRelationship(r2)
            org.apache.poi.openxml4j.opc.PackagePart r6 = r0.getPart((org.apache.poi.openxml4j.opc.PackageRelationship) r6)
            r5.extPart = r6
            if (r6 != 0) goto L_0x0043
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r6 = new org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = NEW_EXT_INSTANCE
            org.apache.xmlbeans.XmlObject r0 = r0.copy()
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument) r0
            r6.<init>(r0)
            r5.ext = r6
            goto L_0x007d
        L_0x0043:
            java.io.InputStream r6 = r6.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument> r0 = org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument.Factory     // Catch:{ all -> 0x005e }
            org.apache.xmlbeans.XmlOptions r4 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r6, (org.apache.xmlbeans.XmlOptions) r4)     // Catch:{ all -> 0x005e }
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument) r0     // Catch:{ all -> 0x005e }
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r4 = new org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties     // Catch:{ all -> 0x005e }
            r4.<init>(r0)     // Catch:{ all -> 0x005e }
            r5.ext = r4     // Catch:{ all -> 0x005e }
            if (r6 == 0) goto L_0x007d
            r6.close()
            goto L_0x007d
        L_0x005e:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r0 = move-exception
            if (r6 == 0) goto L_0x006b
            r6.close()     // Catch:{ all -> 0x0067 }
            goto L_0x006b
        L_0x0067:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x006b:
            throw r0
        L_0x006c:
            r5.extPart = r1
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r6 = new org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = NEW_EXT_INSTANCE
            org.apache.xmlbeans.XmlObject r0 = r0.copy()
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument) r0
            r6.<init>(r0)
            r5.ext = r6
        L_0x007d:
            org.apache.poi.openxml4j.opc.OPCPackage r6 = r5.pkg
            java.lang.String r0 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties"
            org.apache.poi.openxml4j.opc.PackageRelationshipCollection r6 = r6.getRelationshipsByType(r0)
            int r0 = r6.size()
            if (r0 != r3) goto L_0x00d2
            org.apache.poi.openxml4j.opc.OPCPackage r0 = r5.pkg
            org.apache.poi.openxml4j.opc.PackageRelationship r6 = r6.getRelationship(r2)
            org.apache.poi.openxml4j.opc.PackagePart r6 = r0.getPart((org.apache.poi.openxml4j.opc.PackageRelationship) r6)
            r5.custPart = r6
            if (r6 != 0) goto L_0x00a9
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r6 = new org.apache.poi.ooxml.POIXMLProperties$CustomProperties
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = NEW_CUST_INSTANCE
            org.apache.xmlbeans.XmlObject r0 = r0.copy()
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument) r0
            r6.<init>(r0)
            r5.cust = r6
            goto L_0x00e3
        L_0x00a9:
            java.io.InputStream r6 = r6.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument> r0 = org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument.Factory     // Catch:{ all -> 0x00c4 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00c4 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r6, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x00c4 }
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument) r0     // Catch:{ all -> 0x00c4 }
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r2 = new org.apache.poi.ooxml.POIXMLProperties$CustomProperties     // Catch:{ all -> 0x00c4 }
            r2.<init>(r0)     // Catch:{ all -> 0x00c4 }
            r5.cust = r2     // Catch:{ all -> 0x00c4 }
            if (r6 == 0) goto L_0x00e3
            r6.close()
            goto L_0x00e3
        L_0x00c4:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00c6 }
        L_0x00c6:
            r0 = move-exception
            if (r6 == 0) goto L_0x00d1
            r6.close()     // Catch:{ all -> 0x00cd }
            goto L_0x00d1
        L_0x00cd:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x00d1:
            throw r0
        L_0x00d2:
            r5.custPart = r1
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r6 = new org.apache.poi.ooxml.POIXMLProperties$CustomProperties
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = NEW_CUST_INSTANCE
            org.apache.xmlbeans.XmlObject r0 = r0.copy()
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = (org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument) r0
            r6.<init>(r0)
            r5.cust = r6
        L_0x00e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.POIXMLProperties.<init>(org.apache.poi.openxml4j.opc.OPCPackage):void");
    }

    public CoreProperties getCoreProperties() {
        return this.core;
    }

    public ExtendedProperties getExtendedProperties() {
        return this.ext;
    }

    public CustomProperties getCustomProperties() {
        return this.cust;
    }

    /* access modifiers changed from: protected */
    public PackagePart getThumbnailPart() {
        PackageRelationshipCollection relationshipsByType = this.pkg.getRelationshipsByType(PackageRelationshipTypes.THUMBNAIL);
        if (relationshipsByType.size() == 1) {
            return this.pkg.getPart(relationshipsByType.getRelationship(0));
        }
        return null;
    }

    public String getThumbnailFilename() {
        PackagePart thumbnailPart = getThumbnailPart();
        if (thumbnailPart == null) {
            return null;
        }
        String name = thumbnailPart.getPartName().getName();
        return name.substring(name.lastIndexOf(47));
    }

    public InputStream getThumbnailImage() throws IOException {
        PackagePart thumbnailPart = getThumbnailPart();
        if (thumbnailPart == null) {
            return null;
        }
        return thumbnailPart.getInputStream();
    }

    public void setThumbnail(String str, InputStream inputStream) throws IOException {
        PackagePart thumbnailPart = getThumbnailPart();
        if (thumbnailPart == null) {
            this.pkg.addThumbnail(str, inputStream);
            return;
        }
        String contentTypeFromFileExtension = ContentTypes.getContentTypeFromFileExtension(str);
        if (contentTypeFromFileExtension.equals(thumbnailPart.getContentType())) {
            StreamHelper.copyStream(inputStream, thumbnailPart.getOutputStream());
            return;
        }
        throw new IllegalArgumentException("Can't set a Thumbnail of type " + contentTypeFromFileExtension + " when existing one is of a different type " + thumbnailPart.getContentType());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c3, code lost:
        if (r0 != null) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ca, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cd, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fa, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00fb, code lost:
        if (r0 != null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0101, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0102, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0105, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r5 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.extPart
            if (r0 != 0) goto L_0x0045
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r0 = r5.ext
            if (r0 == 0) goto L_0x0045
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = r0.props
            if (r0 == 0) goto L_0x0045
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = NEW_EXT_INSTANCE
            java.lang.String r0 = r0.toString()
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r1 = r5.ext
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r1 = r1.props
            java.lang.String r1 = r1.toString()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0045
            java.lang.String r0 = "/docProps/app.xml"
            org.apache.poi.openxml4j.opc.PackagePartName r0 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r0)     // Catch:{ InvalidFormatException -> 0x003e }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r5.pkg     // Catch:{ InvalidFormatException -> 0x003e }
            org.apache.poi.openxml4j.opc.TargetMode r2 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ InvalidFormatException -> 0x003e }
            java.lang.String r3 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties"
            r1.addRelationship(r0, r2, r3)     // Catch:{ InvalidFormatException -> 0x003e }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r5.pkg     // Catch:{ InvalidFormatException -> 0x003e }
            java.lang.String r2 = "application/vnd.openxmlformats-officedocument.extended-properties+xml"
            org.apache.poi.openxml4j.opc.PackagePart r0 = r1.createPart(r0, r2)     // Catch:{ InvalidFormatException -> 0x003e }
            r5.extPart = r0     // Catch:{ InvalidFormatException -> 0x003e }
            goto L_0x0045
        L_0x003e:
            r5 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r5)
            throw r0
        L_0x0045:
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.custPart
            if (r0 != 0) goto L_0x008a
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r0 = r5.cust
            if (r0 == 0) goto L_0x008a
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = r0.props
            if (r0 == 0) goto L_0x008a
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = NEW_CUST_INSTANCE
            java.lang.String r0 = r0.toString()
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r1 = r5.cust
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r1 = r1.props
            java.lang.String r1 = r1.toString()
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = "/docProps/custom.xml"
            org.apache.poi.openxml4j.opc.PackagePartName r0 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.lang.String) r0)     // Catch:{ InvalidFormatException -> 0x0083 }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r5.pkg     // Catch:{ InvalidFormatException -> 0x0083 }
            org.apache.poi.openxml4j.opc.TargetMode r2 = org.apache.poi.openxml4j.opc.TargetMode.INTERNAL     // Catch:{ InvalidFormatException -> 0x0083 }
            java.lang.String r3 = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties"
            r1.addRelationship(r0, r2, r3)     // Catch:{ InvalidFormatException -> 0x0083 }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r5.pkg     // Catch:{ InvalidFormatException -> 0x0083 }
            java.lang.String r2 = "application/vnd.openxmlformats-officedocument.custom-properties+xml"
            org.apache.poi.openxml4j.opc.PackagePart r0 = r1.createPart(r0, r2)     // Catch:{ InvalidFormatException -> 0x0083 }
            r5.custPart = r0     // Catch:{ InvalidFormatException -> 0x0083 }
            goto L_0x008a
        L_0x0083:
            r5 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            r0.<init>((java.lang.Throwable) r5)
            throw r0
        L_0x008a:
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.extPart
            if (r0 == 0) goto L_0x00ce
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r0 = r5.ext
            if (r0 == 0) goto L_0x00ce
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r0 = r0.props
            if (r0 == 0) goto L_0x00ce
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.extPart
            java.io.OutputStream r0 = r0.getOutputStream()
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.extPart     // Catch:{ all -> 0x00c0 }
            long r1 = r1.getSize()     // Catch:{ all -> 0x00c0 }
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x00af
            org.apache.poi.openxml4j.opc.PackagePart r1 = r5.extPart     // Catch:{ all -> 0x00c0 }
            r1.clear()     // Catch:{ all -> 0x00c0 }
        L_0x00af:
            org.apache.poi.ooxml.POIXMLProperties$ExtendedProperties r1 = r5.ext     // Catch:{ all -> 0x00c0 }
            org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument r1 = r1.props     // Catch:{ all -> 0x00c0 }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00c0 }
            r1.save((java.io.OutputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x00c0 }
            if (r0 == 0) goto L_0x00ce
            r0.close()
            goto L_0x00ce
        L_0x00c0:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r1 = move-exception
            if (r0 == 0) goto L_0x00cd
            r0.close()     // Catch:{ all -> 0x00c9 }
            goto L_0x00cd
        L_0x00c9:
            r0 = move-exception
            r5.addSuppressed(r0)
        L_0x00cd:
            throw r1
        L_0x00ce:
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.custPart
            if (r0 == 0) goto L_0x0106
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r0 = r5.cust
            if (r0 == 0) goto L_0x0106
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r0 = r0.props
            if (r0 == 0) goto L_0x0106
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.custPart
            r0.clear()
            org.apache.poi.openxml4j.opc.PackagePart r0 = r5.custPart
            java.io.OutputStream r0 = r0.getOutputStream()
            org.apache.poi.ooxml.POIXMLProperties$CustomProperties r5 = r5.cust     // Catch:{ all -> 0x00f8 }
            org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument r5 = r5.props     // Catch:{ all -> 0x00f8 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x00f8 }
            r5.save((java.io.OutputStream) r0, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x00f8 }
            if (r0 == 0) goto L_0x0106
            r0.close()
            goto L_0x0106
        L_0x00f8:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00fa }
        L_0x00fa:
            r1 = move-exception
            if (r0 == 0) goto L_0x0105
            r0.close()     // Catch:{ all -> 0x0101 }
            goto L_0x0105
        L_0x0101:
            r0 = move-exception
            r5.addSuppressed(r0)
        L_0x0105:
            throw r1
        L_0x0106:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.POIXMLProperties.commit():void");
    }

    public static class CoreProperties {
        private PackagePropertiesPart part;

        private CoreProperties(PackagePropertiesPart packagePropertiesPart) {
            this.part = packagePropertiesPart;
        }

        public String getCategory() {
            return this.part.getCategoryProperty().orElse((Object) null);
        }

        public void setCategory(String str) {
            this.part.setCategoryProperty(str);
        }

        public String getContentStatus() {
            return this.part.getContentStatusProperty().orElse((Object) null);
        }

        public void setContentStatus(String str) {
            this.part.setContentStatusProperty(str);
        }

        public String getContentType() {
            return this.part.getContentTypeProperty().orElse((Object) null);
        }

        public void setContentType(String str) {
            this.part.setContentTypeProperty(str);
        }

        public Date getCreated() {
            return this.part.getCreatedProperty().orElse((Object) null);
        }

        public void setCreated(Optional<Date> optional) {
            this.part.setCreatedProperty(optional);
        }

        public void setCreated(String str) throws InvalidFormatException {
            this.part.setCreatedProperty(str);
        }

        public String getCreator() {
            return this.part.getCreatorProperty().orElse((Object) null);
        }

        public void setCreator(String str) {
            this.part.setCreatorProperty(str);
        }

        public String getDescription() {
            return this.part.getDescriptionProperty().orElse((Object) null);
        }

        public void setDescription(String str) {
            this.part.setDescriptionProperty(str);
        }

        public String getIdentifier() {
            return this.part.getIdentifierProperty().orElse((Object) null);
        }

        public void setIdentifier(String str) {
            this.part.setIdentifierProperty(str);
        }

        public String getKeywords() {
            return this.part.getKeywordsProperty().orElse((Object) null);
        }

        public void setKeywords(String str) {
            this.part.setKeywordsProperty(str);
        }

        public Date getLastPrinted() {
            return this.part.getLastPrintedProperty().orElse((Object) null);
        }

        public void setLastPrinted(Optional<Date> optional) {
            this.part.setLastPrintedProperty(optional);
        }

        public void setLastPrinted(String str) throws InvalidFormatException {
            this.part.setLastPrintedProperty(str);
        }

        public String getLastModifiedByUser() {
            return this.part.getLastModifiedByProperty().orElse((Object) null);
        }

        public void setLastModifiedByUser(String str) {
            this.part.setLastModifiedByProperty(str);
        }

        public Date getModified() {
            return this.part.getModifiedProperty().orElse((Object) null);
        }

        public void setModified(Optional<Date> optional) {
            this.part.setModifiedProperty(optional);
        }

        public void setModified(String str) throws InvalidFormatException {
            this.part.setModifiedProperty(str);
        }

        public String getSubject() {
            return this.part.getSubjectProperty().orElse((Object) null);
        }

        public void setSubjectProperty(String str) {
            this.part.setSubjectProperty(str);
        }

        public void setTitle(String str) {
            this.part.setTitleProperty(str);
        }

        public String getTitle() {
            return this.part.getTitleProperty().orElse((Object) null);
        }

        public String getRevision() {
            return this.part.getRevisionProperty().orElse((Object) null);
        }

        public void setRevision(String str) {
            try {
                Long.valueOf(str);
                this.part.setRevisionProperty(str);
            } catch (NumberFormatException unused) {
            }
        }

        public PackagePropertiesPart getUnderlyingProperties() {
            return this.part;
        }
    }

    public static class ExtendedProperties {
        /* access modifiers changed from: private */
        public org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument props;

        private ExtendedProperties(org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument propertiesDocument) {
            this.props = propertiesDocument;
        }

        public CTProperties getUnderlyingProperties() {
            return this.props.getProperties();
        }

        public String getTemplate() {
            if (this.props.getProperties().isSetTemplate()) {
                return this.props.getProperties().getTemplate();
            }
            return null;
        }

        public void setTemplate(String str) {
            this.props.getProperties().setTemplate(str);
        }

        public String getManager() {
            if (this.props.getProperties().isSetManager()) {
                return this.props.getProperties().getManager();
            }
            return null;
        }

        public void setManager(String str) {
            this.props.getProperties().setManager(str);
        }

        public String getCompany() {
            if (this.props.getProperties().isSetCompany()) {
                return this.props.getProperties().getCompany();
            }
            return null;
        }

        public void setCompany(String str) {
            this.props.getProperties().setCompany(str);
        }

        public String getPresentationFormat() {
            if (this.props.getProperties().isSetPresentationFormat()) {
                return this.props.getProperties().getPresentationFormat();
            }
            return null;
        }

        public void setPresentationFormat(String str) {
            this.props.getProperties().setPresentationFormat(str);
        }

        public String getApplication() {
            if (this.props.getProperties().isSetApplication()) {
                return this.props.getProperties().getApplication();
            }
            return null;
        }

        public void setApplication(String str) {
            this.props.getProperties().setApplication(str);
        }

        public String getAppVersion() {
            if (this.props.getProperties().isSetAppVersion()) {
                return this.props.getProperties().getAppVersion();
            }
            return null;
        }

        public void setAppVersion(String str) {
            this.props.getProperties().setAppVersion(str);
        }

        public int getPages() {
            if (this.props.getProperties().isSetPages()) {
                return this.props.getProperties().getPages();
            }
            return -1;
        }

        public void setPages(int i) {
            this.props.getProperties().setPages(i);
        }

        public int getWords() {
            if (this.props.getProperties().isSetWords()) {
                return this.props.getProperties().getWords();
            }
            return -1;
        }

        public void setWords(int i) {
            this.props.getProperties().setWords(i);
        }

        public int getCharacters() {
            if (this.props.getProperties().isSetCharacters()) {
                return this.props.getProperties().getCharacters();
            }
            return -1;
        }

        public void setCharacters(int i) {
            this.props.getProperties().setCharacters(i);
        }

        public int getCharactersWithSpaces() {
            if (this.props.getProperties().isSetCharactersWithSpaces()) {
                return this.props.getProperties().getCharactersWithSpaces();
            }
            return -1;
        }

        public void setCharactersWithSpaces(int i) {
            this.props.getProperties().setCharactersWithSpaces(i);
        }

        public int getLines() {
            if (this.props.getProperties().isSetLines()) {
                return this.props.getProperties().getLines();
            }
            return -1;
        }

        public void setLines(int i) {
            this.props.getProperties().setLines(i);
        }

        public int getParagraphs() {
            if (this.props.getProperties().isSetParagraphs()) {
                return this.props.getProperties().getParagraphs();
            }
            return -1;
        }

        public void setParagraphs(int i) {
            this.props.getProperties().setParagraphs(i);
        }

        public int getSlides() {
            if (this.props.getProperties().isSetSlides()) {
                return this.props.getProperties().getSlides();
            }
            return -1;
        }

        public void setSlides(int i) {
            this.props.getProperties().setSlides(i);
        }

        public int getNotes() {
            if (this.props.getProperties().isSetNotes()) {
                return this.props.getProperties().getNotes();
            }
            return -1;
        }

        public void setNotes(int i) {
            this.props.getProperties().setNotes(i);
        }

        public int getTotalTime() {
            if (this.props.getProperties().isSetTotalTime()) {
                return this.props.getProperties().getTotalTime();
            }
            return -1;
        }

        public void setTotalTime(int i) {
            this.props.getProperties().setTotalTime(i);
        }

        public int getHiddenSlides() {
            if (this.props.getProperties().isSetHiddenSlides()) {
                return this.props.getProperties().getHiddenSlides();
            }
            return -1;
        }

        public void setHiddenSlides(int i) {
            this.props.getProperties().setHiddenSlides(i);
        }

        public int getMMClips() {
            if (this.props.getProperties().isSetMMClips()) {
                return this.props.getProperties().getMMClips();
            }
            return -1;
        }

        public void setMMClips(int i) {
            this.props.getProperties().setMMClips(i);
        }

        public String getHyperlinkBase() {
            if (this.props.getProperties().isSetHyperlinkBase()) {
                return this.props.getProperties().getHyperlinkBase();
            }
            return null;
        }

        public void setHyperlinkBase(String str) {
            this.props.getProperties().setHyperlinkBase(str);
        }
    }

    public static class CustomProperties {
        public static final String FORMAT_ID = "{D5CDD505-2E9C-101B-9397-08002B2CF9AE}";
        private Integer lastPid;
        /* access modifiers changed from: private */
        public PropertiesDocument props;

        private CustomProperties(PropertiesDocument propertiesDocument) {
            this.lastPid = null;
            this.props = propertiesDocument;
        }

        public org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties getUnderlyingProperties() {
            return this.props.getProperties();
        }

        private CTProperty add(String str) {
            if (!contains(str)) {
                CTProperty addNewProperty = this.props.getProperties().addNewProperty();
                addNewProperty.setPid(nextPid());
                addNewProperty.setFmtid(FORMAT_ID);
                addNewProperty.setName(str);
                return addNewProperty;
            }
            throw new IllegalArgumentException("A property with this name already exists in the custom properties");
        }

        public void addProperty(String str, String str2) {
            add(str).setLpwstr(str2);
        }

        public void addProperty(String str, double d) {
            add(str).setR8(d);
        }

        public void addProperty(String str, int i) {
            add(str).setI4(i);
        }

        public void addProperty(String str, boolean z) {
            add(str).setBool(z);
        }

        /* access modifiers changed from: protected */
        public int nextPid() {
            Integer num = this.lastPid;
            int lastPid2 = (num == null ? getLastPid() : num.intValue()) + 1;
            this.lastPid = Integer.valueOf(lastPid2);
            return lastPid2;
        }

        /* access modifiers changed from: protected */
        public int getLastPid() {
            int i = 1;
            for (CTProperty next : this.props.getProperties().getPropertyList()) {
                if (next.getPid() > i) {
                    i = next.getPid();
                }
            }
            return i;
        }

        public boolean contains(String str) {
            for (CTProperty name : this.props.getProperties().getPropertyList()) {
                if (name.getName().equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public CTProperty getProperty(String str) {
            for (CTProperty next : this.props.getProperties().getPropertyList()) {
                if (next.getName().equals(str)) {
                    return next;
                }
            }
            return null;
        }
    }
}
