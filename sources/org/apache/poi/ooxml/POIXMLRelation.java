package org.apache.poi.ooxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;

public abstract class POIXMLRelation {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) POIXMLRelation.class);
    private final String _defaultName;
    private final String _relation;
    private final String _type;
    private final NoArgConstructor noArgConstructor;
    private final PackagePartConstructor packagePartConstructor;
    private final ParentPartConstructor parentPartConstructor;

    @Internal
    public interface NoArgConstructor {
        POIXMLDocumentPart init();
    }

    @Internal
    public interface PackagePartConstructor {
        POIXMLDocumentPart init(PackagePart packagePart) throws IOException, XmlException;
    }

    @Internal
    public interface ParentPartConstructor {
        POIXMLDocumentPart init(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) throws IOException, XmlException;
    }

    protected POIXMLRelation(String str, String str2, String str3, NoArgConstructor noArgConstructor2, PackagePartConstructor packagePartConstructor2, ParentPartConstructor parentPartConstructor2) {
        this._type = str;
        this._relation = str2;
        this._defaultName = str3;
        this.noArgConstructor = noArgConstructor2;
        this.packagePartConstructor = packagePartConstructor2;
        this.parentPartConstructor = parentPartConstructor2;
    }

    protected POIXMLRelation(String str, String str2, String str3) {
        this(str, str2, str3, (NoArgConstructor) null, (PackagePartConstructor) null, (ParentPartConstructor) null);
    }

    public String getContentType() {
        return this._type;
    }

    public String getRelation() {
        return this._relation;
    }

    public String getDefaultFileName() {
        return this._defaultName;
    }

    public String getFileName(int i) {
        if (!this._defaultName.contains("#")) {
            return getDefaultFileName();
        }
        return this._defaultName.replace("#", Integer.toString(i));
    }

    public Integer getFileNameIndex(POIXMLDocumentPart pOIXMLDocumentPart) {
        return Integer.valueOf(pOIXMLDocumentPart.getPackagePart().getPartName().getName().replaceAll(this._defaultName.replace("#", "(\\d+)"), "$1"));
    }

    public NoArgConstructor getNoArgConstructor() {
        return this.noArgConstructor;
    }

    public PackagePartConstructor getPackagePartConstructor() {
        return this.packagePartConstructor;
    }

    public ParentPartConstructor getParentPartConstructor() {
        return this.parentPartConstructor;
    }

    public InputStream getContents(PackagePart packagePart) throws IOException, InvalidFormatException {
        Iterator<PackageRelationship> it = packagePart.getRelationshipsByType(getRelation()).iterator();
        if (it.hasNext()) {
            return packagePart.getPackage().getPart(PackagingURIHelper.createPartName(it.next().getTargetURI())).getInputStream();
        }
        LOGGER.atWarn().log("No part {} found", (Object) getDefaultFileName());
        return null;
    }
}
