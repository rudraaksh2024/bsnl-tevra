package org.apache.poi.ooxml.extractor;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.util.LocaleUtil;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;

public class POIXMLPropertiesTextExtractor implements POIXMLTextExtractor {
    private final DateFormat dateFormat;
    private boolean doCloseFilesystem;
    private final POIXMLDocument doc;

    public POIXMLDocument getFilesystem() {
        return null;
    }

    public POIXMLPropertiesTextExtractor(POIXMLDocument pOIXMLDocument) {
        this.doCloseFilesystem = true;
        this.doc = pOIXMLDocument;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", DateFormatSymbols.getInstance(Locale.ROOT));
        this.dateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
    }

    public POIXMLPropertiesTextExtractor(POIXMLTextExtractor pOIXMLTextExtractor) {
        this(pOIXMLTextExtractor.getDocument());
    }

    private void appendIfPresent(StringBuilder sb, String str, boolean z) {
        appendIfPresent(sb, str, Boolean.toString(z));
    }

    private void appendIfPresent(StringBuilder sb, String str, int i) {
        appendIfPresent(sb, str, Integer.toString(i));
    }

    private void appendDateIfPresent(StringBuilder sb, String str, Optional<Date> optional) {
        if (optional.isPresent()) {
            appendIfPresent(sb, str, this.dateFormat.format(optional.get()));
        }
    }

    private void appendIfPresent(StringBuilder sb, String str, Optional<String> optional) {
        if (optional.isPresent()) {
            appendIfPresent(sb, str, optional.get());
        }
    }

    private void appendIfPresent(StringBuilder sb, String str, String str2) {
        if (str2 != null) {
            sb.append(str);
            sb.append(" = ");
            sb.append(str2);
            sb.append(10);
        }
    }

    public String getCorePropertiesText() {
        POIXMLDocument document = getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(64);
        PackagePropertiesPart underlyingProperties = document.getProperties().getCoreProperties().getUnderlyingProperties();
        appendIfPresent(sb, "Category", underlyingProperties.getCategoryProperty());
        appendIfPresent(sb, "ContentStatus", underlyingProperties.getContentStatusProperty());
        appendIfPresent(sb, "ContentType", underlyingProperties.getContentTypeProperty());
        appendDateIfPresent(sb, "Created", underlyingProperties.getCreatedProperty());
        appendIfPresent(sb, "CreatedString", underlyingProperties.getCreatedPropertyString());
        appendIfPresent(sb, "Creator", underlyingProperties.getCreatorProperty());
        appendIfPresent(sb, "Description", underlyingProperties.getDescriptionProperty());
        appendIfPresent(sb, "Identifier", underlyingProperties.getIdentifierProperty());
        appendIfPresent(sb, "Keywords", underlyingProperties.getKeywordsProperty());
        appendIfPresent(sb, "Language", underlyingProperties.getLanguageProperty());
        appendIfPresent(sb, "LastModifiedBy", underlyingProperties.getLastModifiedByProperty());
        appendDateIfPresent(sb, "LastPrinted", underlyingProperties.getLastPrintedProperty());
        appendIfPresent(sb, "LastPrintedString", underlyingProperties.getLastPrintedPropertyString());
        appendDateIfPresent(sb, "Modified", underlyingProperties.getModifiedProperty());
        appendIfPresent(sb, "ModifiedString", underlyingProperties.getModifiedPropertyString());
        appendIfPresent(sb, "Revision", underlyingProperties.getRevisionProperty());
        appendIfPresent(sb, "Subject", underlyingProperties.getSubjectProperty());
        appendIfPresent(sb, "Title", underlyingProperties.getTitleProperty());
        appendIfPresent(sb, "Version", underlyingProperties.getVersionProperty());
        return sb.toString();
    }

    public String getExtendedPropertiesText() {
        POIXMLDocument document = getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(64);
        CTProperties underlyingProperties = document.getProperties().getExtendedProperties().getUnderlyingProperties();
        appendIfPresent(sb, "Application", underlyingProperties.getApplication());
        appendIfPresent(sb, "AppVersion", underlyingProperties.getAppVersion());
        appendIfPresent(sb, "Characters", underlyingProperties.getCharacters());
        appendIfPresent(sb, "CharactersWithSpaces", underlyingProperties.getCharactersWithSpaces());
        appendIfPresent(sb, "Company", underlyingProperties.getCompany());
        appendIfPresent(sb, "HyperlinkBase", underlyingProperties.getHyperlinkBase());
        appendIfPresent(sb, "HyperlinksChanged", underlyingProperties.getHyperlinksChanged());
        appendIfPresent(sb, "Lines", underlyingProperties.getLines());
        appendIfPresent(sb, "LinksUpToDate", underlyingProperties.getLinksUpToDate());
        appendIfPresent(sb, "Manager", underlyingProperties.getManager());
        appendIfPresent(sb, "Pages", underlyingProperties.getPages());
        appendIfPresent(sb, "Paragraphs", underlyingProperties.getParagraphs());
        appendIfPresent(sb, "PresentationFormat", underlyingProperties.getPresentationFormat());
        appendIfPresent(sb, "Template", underlyingProperties.getTemplate());
        appendIfPresent(sb, "TotalTime", underlyingProperties.getTotalTime());
        return sb.toString();
    }

    public String getCustomPropertiesText() {
        String str;
        POIXMLDocument document = getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (CTProperty next : document.getProperties().getCustomProperties().getUnderlyingProperties().getPropertyList()) {
            if (next.isSetLpwstr()) {
                str = next.getLpwstr();
            } else if (next.isSetLpstr()) {
                str = next.getLpstr();
            } else if (next.isSetDate()) {
                str = next.getDate().toString();
            } else if (next.isSetFiletime()) {
                str = next.getFiletime().toString();
            } else if (next.isSetBool()) {
                str = Boolean.toString(next.getBool());
            } else if (next.isSetI1()) {
                str = Integer.toString(next.getI1());
            } else if (next.isSetI2()) {
                str = Integer.toString(next.getI2());
            } else if (next.isSetI4()) {
                str = Integer.toString(next.getI4());
            } else if (next.isSetI8()) {
                str = Long.toString(next.getI8());
            } else if (next.isSetInt()) {
                str = Integer.toString(next.getInt());
            } else if (next.isSetUi1()) {
                str = Integer.toString(next.getUi1());
            } else if (next.isSetUi2()) {
                str = Integer.toString(next.getUi2());
            } else if (next.isSetUi4()) {
                str = Long.toString(next.getUi4());
            } else if (next.isSetUi8()) {
                str = next.getUi8().toString();
            } else if (next.isSetUint()) {
                str = Long.toString(next.getUint());
            } else if (next.isSetR4()) {
                str = Float.toString(next.getR4());
            } else if (next.isSetR8()) {
                str = Double.toString(next.getR8());
            } else if (next.isSetDecimal()) {
                BigDecimal decimal = next.getDecimal();
                if (decimal == null) {
                    str = null;
                } else {
                    str = decimal.toPlainString();
                }
            } else {
                str = "(not implemented!)";
            }
            sb.append(next.getName()).append(" = ").append(str).append("\n");
        }
        return sb.toString();
    }

    public String getText() {
        try {
            return getCorePropertiesText() + getExtendedPropertiesText() + getCustomPropertiesText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
    }

    public POIXMLDocument getDocument() {
        return this.doc;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }
}
