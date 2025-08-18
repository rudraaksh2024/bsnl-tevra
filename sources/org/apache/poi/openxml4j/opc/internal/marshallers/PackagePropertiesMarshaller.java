package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.OutputStream;
import java.util.Optional;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PackagePropertiesMarshaller implements PartMarshaller {
    protected static final String KEYWORD_CATEGORY = "category";
    protected static final String KEYWORD_CONTENT_STATUS = "contentStatus";
    protected static final String KEYWORD_CONTENT_TYPE = "contentType";
    protected static final String KEYWORD_CREATED = "created";
    protected static final String KEYWORD_CREATOR = "creator";
    protected static final String KEYWORD_DESCRIPTION = "description";
    protected static final String KEYWORD_IDENTIFIER = "identifier";
    protected static final String KEYWORD_KEYWORDS = "keywords";
    protected static final String KEYWORD_LANGUAGE = "language";
    protected static final String KEYWORD_LAST_MODIFIED_BY = "lastModifiedBy";
    protected static final String KEYWORD_LAST_PRINTED = "lastPrinted";
    protected static final String KEYWORD_MODIFIED = "modified";
    protected static final String KEYWORD_REVISION = "revision";
    protected static final String KEYWORD_SUBJECT = "subject";
    protected static final String KEYWORD_TITLE = "title";
    protected static final String KEYWORD_VERSION = "version";
    private static final NamespaceImpl namespaceCoreProperties = new NamespaceImpl("cp", "http://schemas.openxmlformats.org/package/2006/metadata/core-properties");
    private static final NamespaceImpl namespaceDC = new NamespaceImpl("dc", "http://purl.org/dc/elements/1.1/");
    private static final NamespaceImpl namespaceDcTerms = new NamespaceImpl("dcterms", "http://purl.org/dc/terms/");
    private static final NamespaceImpl namespaceXSI = new NamespaceImpl("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    PackagePropertiesPart propsPart;
    Document xmlDoc;

    public boolean marshall(PackagePart packagePart, OutputStream outputStream) throws OpenXML4JException {
        if (packagePart instanceof PackagePropertiesPart) {
            this.propsPart = (PackagePropertiesPart) packagePart;
            Document createDocument = DocumentHelper.createDocument();
            this.xmlDoc = createDocument;
            NamespaceImpl namespaceImpl = namespaceCoreProperties;
            Element createElementNS = createDocument.createElementNS(namespaceImpl.getNamespaceURI(), getQName("coreProperties", namespaceImpl));
            DocumentHelper.addNamespaceDeclaration(createElementNS, namespaceImpl.getPrefix(), namespaceImpl.getNamespaceURI());
            NamespaceImpl namespaceImpl2 = namespaceDC;
            DocumentHelper.addNamespaceDeclaration(createElementNS, namespaceImpl2.getPrefix(), namespaceImpl2.getNamespaceURI());
            NamespaceImpl namespaceImpl3 = namespaceDcTerms;
            DocumentHelper.addNamespaceDeclaration(createElementNS, namespaceImpl3.getPrefix(), namespaceImpl3.getNamespaceURI());
            NamespaceImpl namespaceImpl4 = namespaceXSI;
            DocumentHelper.addNamespaceDeclaration(createElementNS, namespaceImpl4.getPrefix(), namespaceImpl4.getNamespaceURI());
            this.xmlDoc.appendChild(createElementNS);
            addCategory();
            addContentStatus();
            addContentType();
            addCreated();
            addCreator();
            addDescription();
            addIdentifier();
            addKeywords();
            addLanguage();
            addLastModifiedBy();
            addLastPrinted();
            addModified();
            addRevision();
            addSubject();
            addTitle();
            addVersion();
            return true;
        }
        throw new IllegalArgumentException("'part' must be a PackagePropertiesPart instance.");
    }

    private Element setElementTextContent(String str, NamespaceImpl namespaceImpl, Optional<String> optional) {
        return setElementTextContent(str, namespaceImpl, optional, optional.orElse((Object) null));
    }

    private String getQName(String str, NamespaceImpl namespaceImpl) {
        return namespaceImpl.getPrefix().isEmpty() ? str : namespaceImpl.getPrefix() + NameUtil.COLON + str;
    }

    private Element setElementTextContent(String str, NamespaceImpl namespaceImpl, Optional<?> optional, String str2) {
        if (!optional.isPresent()) {
            return null;
        }
        Element documentElement = this.xmlDoc.getDocumentElement();
        Element element = (Element) documentElement.getElementsByTagNameNS(namespaceImpl.getNamespaceURI(), str).item(0);
        if (element == null) {
            element = this.xmlDoc.createElementNS(namespaceImpl.getNamespaceURI(), getQName(str, namespaceImpl));
            documentElement.appendChild(element);
        }
        element.setTextContent(str2);
        return element;
    }

    private Element setElementTextContent(String str, NamespaceImpl namespaceImpl, Optional<?> optional, String str2, String str3) {
        Element elementTextContent = setElementTextContent(str, namespaceImpl, optional, str2);
        if (elementTextContent != null) {
            NamespaceImpl namespaceImpl2 = namespaceXSI;
            elementTextContent.setAttributeNS(namespaceImpl2.getNamespaceURI(), getQName("type", namespaceImpl2), str3);
        }
        return elementTextContent;
    }

    private void addCategory() {
        setElementTextContent(KEYWORD_CATEGORY, namespaceCoreProperties, this.propsPart.getCategoryProperty());
    }

    private void addContentStatus() {
        setElementTextContent(KEYWORD_CONTENT_STATUS, namespaceCoreProperties, this.propsPart.getContentStatusProperty());
    }

    private void addContentType() {
        setElementTextContent(KEYWORD_CONTENT_TYPE, namespaceCoreProperties, this.propsPart.getContentTypeProperty());
    }

    private void addCreated() {
        setElementTextContent(KEYWORD_CREATED, namespaceDcTerms, this.propsPart.getCreatedProperty(), this.propsPart.getCreatedPropertyString(), "dcterms:W3CDTF");
    }

    private void addCreator() {
        setElementTextContent(KEYWORD_CREATOR, namespaceDC, this.propsPart.getCreatorProperty());
    }

    private void addDescription() {
        setElementTextContent(KEYWORD_DESCRIPTION, namespaceDC, this.propsPart.getDescriptionProperty());
    }

    private void addIdentifier() {
        setElementTextContent(KEYWORD_IDENTIFIER, namespaceDC, this.propsPart.getIdentifierProperty());
    }

    private void addKeywords() {
        setElementTextContent(KEYWORD_KEYWORDS, namespaceCoreProperties, this.propsPart.getKeywordsProperty());
    }

    private void addLanguage() {
        setElementTextContent(KEYWORD_LANGUAGE, namespaceDC, this.propsPart.getLanguageProperty());
    }

    private void addLastModifiedBy() {
        setElementTextContent(KEYWORD_LAST_MODIFIED_BY, namespaceCoreProperties, this.propsPart.getLastModifiedByProperty());
    }

    private void addLastPrinted() {
        setElementTextContent(KEYWORD_LAST_PRINTED, namespaceCoreProperties, this.propsPart.getLastPrintedProperty(), this.propsPart.getLastPrintedPropertyString());
    }

    private void addModified() {
        setElementTextContent(KEYWORD_MODIFIED, namespaceDcTerms, this.propsPart.getModifiedProperty(), this.propsPart.getModifiedPropertyString(), "dcterms:W3CDTF");
    }

    private void addRevision() {
        setElementTextContent(KEYWORD_REVISION, namespaceCoreProperties, this.propsPart.getRevisionProperty());
    }

    private void addSubject() {
        setElementTextContent(KEYWORD_SUBJECT, namespaceDC, this.propsPart.getSubjectProperty());
    }

    private void addTitle() {
        setElementTextContent(KEYWORD_TITLE, namespaceDC, this.propsPart.getTitleProperty());
    }

    private void addVersion() {
        setElementTextContent(KEYWORD_VERSION, namespaceCoreProperties, this.propsPart.getVersionProperty());
    }

    private static class NamespaceImpl {
        private final String namespaceURI;
        private final String prefix;

        NamespaceImpl(String str, String str2) {
            this.prefix = str;
            this.namespaceURI = str2;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getNamespaceURI() {
            return this.namespaceURI;
        }
    }
}
