package org.apache.poi.ooxml;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.xmlbeans.impl.common.SystemCache;

public abstract class POIXMLDocument extends POIXMLDocumentPart implements Closeable {
    public static final String DOCUMENT_CREATOR = "Apache POI";
    public static final String OLE_OBJECT_REL_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/oleObject";
    public static final String PACK_OBJECT_REL_TYPE = "http://schemas.openxmlformats.org/officeDocument/2006/relationships/package";
    private OPCPackage pkg;
    private POIXMLProperties properties;

    public abstract List<PackagePart> getAllEmbeddedParts() throws OpenXML4JException;

    protected POIXMLDocument(OPCPackage oPCPackage) {
        super(oPCPackage);
        init(oPCPackage);
    }

    protected POIXMLDocument(OPCPackage oPCPackage, String str) {
        super(oPCPackage, str);
        init(oPCPackage);
    }

    private void init(OPCPackage oPCPackage) {
        this.pkg = oPCPackage;
        SystemCache.get().setSaxLoader((Object) null);
    }

    public static OPCPackage openPackage(String str) throws IOException {
        try {
            return OPCPackage.open(str);
        } catch (InvalidFormatException e) {
            throw new IOException(e.toString(), e);
        }
    }

    public OPCPackage getPackage() {
        return this.pkg;
    }

    /* access modifiers changed from: protected */
    public PackagePart getCorePart() {
        return getPackagePart();
    }

    /* access modifiers changed from: protected */
    public PackagePart[] getRelatedByType(String str) throws InvalidFormatException {
        PackageRelationshipCollection relationshipsByType = getPackagePart().getRelationshipsByType(str);
        PackagePart[] packagePartArr = new PackagePart[relationshipsByType.size()];
        Iterator<PackageRelationship> it = relationshipsByType.iterator();
        int i = 0;
        while (it.hasNext()) {
            packagePartArr[i] = getPackagePart().getRelatedPart(it.next());
            i++;
        }
        return packagePartArr;
    }

    public POIXMLProperties getProperties() {
        if (this.properties == null) {
            try {
                this.properties = new POIXMLProperties(this.pkg);
            } catch (Exception e) {
                throw new POIXMLException((Throwable) e);
            }
        }
        return this.properties;
    }

    /* access modifiers changed from: protected */
    public final void load(POIXMLFactory pOIXMLFactory) throws IOException {
        HashMap hashMap = new HashMap();
        try {
            read(pOIXMLFactory, hashMap);
            onDocumentRead();
            hashMap.clear();
        } catch (OpenXML4JException e) {
            throw new POIXMLException((Throwable) e);
        }
    }

    public void close() throws IOException {
        OPCPackage oPCPackage = this.pkg;
        if (oPCPackage != null) {
            if (oPCPackage.getPackageAccess() == PackageAccess.READ) {
                this.pkg.revert();
            } else {
                this.pkg.close();
            }
            this.pkg = null;
        }
    }

    public final void write(OutputStream outputStream) throws IOException {
        OPCPackage oPCPackage = getPackage();
        if (oPCPackage != null) {
            HashSet hashSet = new HashSet();
            onSave(hashSet);
            hashSet.clear();
            getProperties().commit();
            oPCPackage.save(outputStream);
            return;
        }
        throw new IOException("Cannot write data, document seems to have been closed already");
    }
}
