package org.apache.poi.hpsf.extractor;

import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hpsf.CustomProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFPropertiesOnlyDocument;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HPSFPropertiesExtractor implements POIOLE2TextExtractor {
    private boolean doCloseFilesystem = true;
    private final POIDocument document;

    public HPSFPropertiesExtractor(POIOLE2TextExtractor pOIOLE2TextExtractor) {
        this.document = pOIOLE2TextExtractor.getDocument();
    }

    public HPSFPropertiesExtractor(POIDocument pOIDocument) {
        this.document = pOIDocument;
    }

    public HPSFPropertiesExtractor(POIFSFileSystem pOIFSFileSystem) {
        this.document = new HPSFPropertiesOnlyDocument(pOIFSFileSystem);
    }

    public String getDocumentSummaryInformationText() {
        CustomProperties customProperties;
        POIDocument pOIDocument = this.document;
        if (pOIDocument == null) {
            return "";
        }
        DocumentSummaryInformation documentSummaryInformation = pOIDocument.getDocumentSummaryInformation();
        StringBuilder sb = new StringBuilder();
        sb.append(getPropertiesText(documentSummaryInformation));
        if (documentSummaryInformation == null) {
            customProperties = null;
        } else {
            customProperties = documentSummaryInformation.getCustomProperties();
        }
        if (customProperties != null) {
            for (String next : customProperties.nameSet()) {
                sb.append(next).append(" = ").append(getPropertyValueText(customProperties.get(next))).append("\n");
            }
        }
        return sb.toString();
    }

    public String getSummaryInformationText() {
        POIDocument pOIDocument = this.document;
        if (pOIDocument == null) {
            return "";
        }
        return getPropertiesText(pOIDocument.getSummaryInformation());
    }

    private static String getPropertiesText(PropertySet propertySet) {
        String str;
        if (propertySet == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        PropertyIDMap propertySetIDMap = propertySet.getPropertySetIDMap();
        for (Property property : propertySet.getProperties()) {
            String l = Long.toString(property.getID());
            if (propertySetIDMap == null) {
                str = null;
            } else {
                str = propertySetIDMap.get((Object) Long.valueOf(property.getID()));
            }
            if (str != null) {
                l = str.toString();
            }
            sb.append(l).append(" = ").append(getPropertyValueText(property.getValue())).append("\n");
        }
        return sb.toString();
    }

    public String getText() {
        return getSummaryInformationText() + getDocumentSummaryInformationText();
    }

    public POITextExtractor getMetadataTextExtractor() {
        throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
    }

    private static String getPropertyValueText(Object obj) {
        return obj == null ? "(not set)" : PropertySet.getPropertyStringValue(obj);
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public POIDocument getDocument() {
        return this.document;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public POIDocument getFilesystem() {
        return this.document;
    }
}
