package org.apache.poi.ooxml;

import java.io.IOException;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.XmlException;

public abstract class POIXMLFactory {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) POIXMLFactory.class);

    /* access modifiers changed from: protected */
    public abstract POIXMLRelation getDescriptor(String str);

    public POIXMLDocumentPart createDocumentPart(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        PackageRelationship packageRelationship = getPackageRelationship(pOIXMLDocumentPart, packagePart);
        String relationshipType = packageRelationship.getRelationshipType();
        POIXMLRelation descriptor = getDescriptor(relationshipType);
        if (descriptor != null) {
            try {
                if (!POIXMLDocument.PACK_OBJECT_REL_TYPE.equals(relationshipType)) {
                    POIXMLRelation.ParentPartConstructor parentPartConstructor = descriptor.getParentPartConstructor();
                    if (parentPartConstructor != null) {
                        return parentPartConstructor.init(pOIXMLDocumentPart, packagePart);
                    }
                    POIXMLRelation.PackagePartConstructor packagePartConstructor = descriptor.getPackagePartConstructor();
                    if (packagePartConstructor != null) {
                        return packagePartConstructor.init(packagePart);
                    }
                }
            } catch (IOException | XmlException e) {
                throw new POIXMLException(e.getMessage(), e);
            }
        }
        LOGGER.atDebug().log("using default POIXMLDocumentPart for {}", (Object) packageRelationship.getRelationshipType());
        return new POIXMLDocumentPart(pOIXMLDocumentPart, packagePart);
    }

    public POIXMLDocumentPart newDocumentPart(POIXMLRelation pOIXMLRelation) {
        if (pOIXMLRelation != null && pOIXMLRelation.getNoArgConstructor() != null) {
            return pOIXMLRelation.getNoArgConstructor().init();
        }
        throw new POIXMLException("can't initialize POIXMLDocumentPart");
    }

    /* access modifiers changed from: protected */
    public PackageRelationship getPackageRelationship(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        try {
            String name = packagePart.getPartName().getName();
            Iterator<PackageRelationship> it = pOIXMLDocumentPart.getPackagePart().getRelationships().iterator();
            while (it.hasNext()) {
                PackageRelationship next = it.next();
                if (next.getTargetURI().toASCIIString().equalsIgnoreCase(name)) {
                    return next;
                }
            }
            throw new POIXMLException("package part isn't a child of the parent document.");
        } catch (InvalidFormatException e) {
            throw new POIXMLException("error while determining package relations", e);
        }
    }
}
