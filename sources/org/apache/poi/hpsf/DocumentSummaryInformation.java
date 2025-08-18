package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;

public class DocumentSummaryInformation extends PropertySet {
    public static final String DEFAULT_STREAM_NAME = "\u0005DocumentSummaryInformation";
    public static final ClassID[] FORMAT_ID = {ClassIDPredefined.DOC_SUMMARY.getClassID(), ClassIDPredefined.USER_PROPERTIES.getClassID()};

    public PropertyIDMap getPropertySetIDMap() {
        return PropertyIDMap.getDocumentSummaryInformationProperties();
    }

    public DocumentSummaryInformation() {
        getFirstSection().setFormatID(ClassIDPredefined.DOC_SUMMARY.getClassID());
    }

    public DocumentSummaryInformation(PropertySet propertySet) throws UnexpectedPropertySetTypeException {
        super(propertySet);
        if (!isDocumentSummaryInformation()) {
            throw new UnexpectedPropertySetTypeException("Not a " + getClass().getName());
        }
    }

    public DocumentSummaryInformation(InputStream inputStream) throws NoPropertySetStreamException, IOException {
        super(inputStream);
    }

    public String getCategory() {
        return getPropertyStringValue(2);
    }

    public void setCategory(String str) {
        getFirstSection().setProperty(2, str);
    }

    public void removeCategory() {
        remove1stProperty(2);
    }

    public String getPresentationFormat() {
        return getPropertyStringValue(3);
    }

    public void setPresentationFormat(String str) {
        getFirstSection().setProperty(3, str);
    }

    public void removePresentationFormat() {
        remove1stProperty(3);
    }

    public int getByteCount() {
        return getPropertyIntValue(4);
    }

    public void setByteCount(int i) {
        set1stProperty(4, i);
    }

    public void removeByteCount() {
        remove1stProperty(4);
    }

    public int getLineCount() {
        return getPropertyIntValue(5);
    }

    public void setLineCount(int i) {
        set1stProperty(5, i);
    }

    public void removeLineCount() {
        remove1stProperty(5);
    }

    public int getParCount() {
        return getPropertyIntValue(6);
    }

    public void setParCount(int i) {
        set1stProperty(6, i);
    }

    public void removeParCount() {
        remove1stProperty(6);
    }

    public int getSlideCount() {
        return getPropertyIntValue(7);
    }

    public void setSlideCount(int i) {
        set1stProperty(7, i);
    }

    public void removeSlideCount() {
        remove1stProperty(7);
    }

    public int getNoteCount() {
        return getPropertyIntValue(8);
    }

    public void setNoteCount(int i) {
        set1stProperty(8, i);
    }

    public void removeNoteCount() {
        remove1stProperty(8);
    }

    public int getHiddenCount() {
        return getPropertyIntValue(9);
    }

    public void setHiddenCount(int i) {
        set1stProperty(9, i);
    }

    public void removeHiddenCount() {
        remove1stProperty(9);
    }

    public int getMMClipCount() {
        return getPropertyIntValue(10);
    }

    public void setMMClipCount(int i) {
        set1stProperty(10, i);
    }

    public void removeMMClipCount() {
        remove1stProperty(10);
    }

    public boolean getScale() {
        return getPropertyBooleanValue(11);
    }

    public void setScale(boolean z) {
        set1stProperty(11, z);
    }

    public void removeScale() {
        remove1stProperty(11);
    }

    public byte[] getHeadingPair() {
        notYetImplemented("Reading byte arrays ");
        return (byte[]) getProperty(12);
    }

    public void setHeadingPair(byte[] bArr) {
        notYetImplemented("Writing byte arrays ");
    }

    public void removeHeadingPair() {
        remove1stProperty(12);
    }

    public byte[] getDocparts() {
        notYetImplemented("Reading byte arrays");
        return (byte[]) getProperty(13);
    }

    public void setDocparts(byte[] bArr) {
        notYetImplemented("Writing byte arrays");
    }

    public void removeDocparts() {
        remove1stProperty(13);
    }

    public String getManager() {
        return getPropertyStringValue(14);
    }

    public void setManager(String str) {
        set1stProperty(14, str);
    }

    public void removeManager() {
        remove1stProperty(14);
    }

    public String getCompany() {
        return getPropertyStringValue(15);
    }

    public void setCompany(String str) {
        set1stProperty(15, str);
    }

    public void removeCompany() {
        remove1stProperty(15);
    }

    public boolean getLinksDirty() {
        return getPropertyBooleanValue(16);
    }

    public void setLinksDirty(boolean z) {
        set1stProperty(16, z);
    }

    public void removeLinksDirty() {
        remove1stProperty(16);
    }

    public int getCharCountWithSpaces() {
        return getPropertyIntValue(17);
    }

    public void setCharCountWithSpaces(int i) {
        set1stProperty(17, i);
    }

    public void removeCharCountWithSpaces() {
        remove1stProperty(17);
    }

    public boolean getHyperlinksChanged() {
        return getPropertyBooleanValue(22);
    }

    public void setHyperlinksChanged(boolean z) {
        set1stProperty(22, z);
    }

    public void removeHyperlinksChanged() {
        remove1stProperty(22);
    }

    public int getApplicationVersion() {
        return getPropertyIntValue(23);
    }

    public void setApplicationVersion(int i) {
        set1stProperty(23, i);
    }

    public void removeApplicationVersion() {
        remove1stProperty(23);
    }

    public byte[] getVBADigitalSignature() {
        Object property = getProperty(24);
        if (property instanceof byte[]) {
            return (byte[]) property;
        }
        return null;
    }

    public void setVBADigitalSignature(byte[] bArr) {
        set1stProperty(24, bArr);
    }

    public void removeVBADigitalSignature() {
        remove1stProperty(24);
    }

    public String getContentType() {
        return getPropertyStringValue(26);
    }

    public void setContentType(String str) {
        set1stProperty(26, str);
    }

    public void removeContentType() {
        remove1stProperty(26);
    }

    public String getContentStatus() {
        return getPropertyStringValue(27);
    }

    public void setContentStatus(String str) {
        set1stProperty(27, str);
    }

    public void removeContentStatus() {
        remove1stProperty(27);
    }

    public String getLanguage() {
        return getPropertyStringValue(28);
    }

    public void setLanguage(String str) {
        set1stProperty(28, str);
    }

    public void removeLanguage() {
        remove1stProperty(28);
    }

    public String getDocumentVersion() {
        return getPropertyStringValue(29);
    }

    public void setDocumentVersion(String str) {
        set1stProperty(29, str);
    }

    public void removeDocumentVersion() {
        remove1stProperty(29);
    }

    public CustomProperties getCustomProperties() {
        if (getSectionCount() < 2) {
            return null;
        }
        CustomProperties customProperties = new CustomProperties();
        Section section = getSections().get(1);
        Map<Long, String> dictionary = section.getDictionary();
        int i = 0;
        for (Property property : section.getProperties()) {
            long id = property.getID();
            int i2 = (id > 1 ? 1 : (id == 1 ? 0 : -1));
            if (i2 == 0) {
                customProperties.setCodepage(((Integer) property.getValue()).intValue());
            } else if (i2 > 0) {
                i++;
                CustomProperty customProperty = new CustomProperty(property, dictionary.get(Long.valueOf(id)));
                customProperties.put(customProperty.getName(), customProperty);
            }
        }
        if (customProperties.size() == i) {
            return customProperties;
        }
        customProperties.setPure(false);
        return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        ensureSection2();
        Section section = getSections().get(1);
        Map<Long, String> dictionary = customProperties.getDictionary();
        int codepage = customProperties.getCodepage();
        if (codepage < 0) {
            codepage = section.getCodepage();
        }
        if (codepage < 0) {
            codepage = 1252;
        }
        customProperties.setCodepage(codepage);
        section.setCodepage(codepage);
        section.setDictionary(dictionary);
        for (CustomProperty property : customProperties.properties()) {
            section.setProperty(property);
        }
    }

    private void ensureSection2() {
        if (getSectionCount() < 2) {
            Section section = new Section();
            section.setFormatID(ClassIDPredefined.USER_PROPERTIES.getClassID());
            addSection(section);
        }
    }

    public void removeCustomProperties() {
        if (getSectionCount() >= 2) {
            LinkedList<Section> linkedList = new LinkedList<>(getSections());
            clearSections();
            int i = 0;
            for (Section section : linkedList) {
                int i2 = i + 1;
                if (i != 1) {
                    addSection(section);
                }
                i = i2;
            }
            return;
        }
        throw new HPSFRuntimeException("Illegal internal format of Document SummaryInformation stream: second section is missing.");
    }

    private void notYetImplemented(String str) {
        throw new UnsupportedOperationException(str + " is not yet implemented.");
    }
}
