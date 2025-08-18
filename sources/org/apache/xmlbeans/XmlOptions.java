package org.apache.xmlbeans;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.store.Saaj;
import org.xml.sax.EntityResolver;
import org.xml.sax.XMLReader;

public class XmlOptions implements Serializable {
    public static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 2048;
    private static final XmlOptions EMPTY_OPTIONS;
    private static final long serialVersionUID = 1;
    private Map<XmlOptionsKeys, Object> _map;

    public enum BeanMethod {
        GET,
        XGET,
        IS_SET,
        IS_NIL,
        IS_NIL_IDX,
        SET,
        SET_NIL,
        SET_NIL_IDX,
        XSET,
        UNSET,
        GET_ARRAY,
        XGET_ARRAY,
        GET_IDX,
        XGET_IDX,
        XSET_ARRAY,
        XSET_IDX,
        SIZE_OF_ARRAY,
        SET_ARRAY,
        SET_IDX,
        INSERT_IDX,
        INSERT_NEW_IDX,
        ADD,
        ADD_NEW,
        REMOVE_IDX,
        GET_LIST,
        XGET_LIST,
        SET_LIST,
        INSTANCE_TYPE
    }

    public enum XmlOptionsKeys {
        SAVE_NAMESPACES_FIRST,
        SAVE_SYNTHETIC_DOCUMENT_ELEMENT,
        SAVE_PRETTY_PRINT,
        SAVE_PRETTY_PRINT_INDENT,
        SAVE_PRETTY_PRINT_OFFSET,
        SAVE_AGGRESSIVE_NAMESPACES,
        SAVE_USE_DEFAULT_NAMESPACE,
        SAVE_IMPLICIT_NAMESPACES,
        SAVE_SUGGESTED_PREFIXES,
        SAVE_FILTER_PROCINST,
        SAVE_USE_OPEN_FRAGMENT,
        SAVE_OUTER,
        SAVE_INNER,
        SAVE_NO_XML_DECL,
        SAVE_SUBSTITUTE_CHARACTERS,
        SAVE_OPTIMIZE_FOR_SPEED,
        SAVE_CDATA_LENGTH_THRESHOLD,
        SAVE_CDATA_ENTITY_COUNT_THRESHOLD,
        SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES,
        LOAD_REPLACE_DOCUMENT_ELEMENT,
        LOAD_STRIP_WHITESPACE,
        LOAD_STRIP_COMMENTS,
        LOAD_STRIP_PROCINSTS,
        LOAD_LINE_NUMBERS,
        LOAD_LINE_NUMBERS_END_ELEMENT,
        LOAD_SAVE_CDATA_BOOKMARKS,
        LOAD_SUBSTITUTE_NAMESPACES,
        LOAD_TRIM_TEXT_BUFFER,
        LOAD_ADDITIONAL_NAMESPACES,
        LOAD_MESSAGE_DIGEST,
        LOAD_USE_DEFAULT_RESOLVER,
        LOAD_USE_XMLREADER,
        XQUERY_CURRENT_NODE_VAR,
        XQUERY_VARIABLE_MAP,
        CHARACTER_ENCODING,
        ERROR_LISTENER,
        DOCUMENT_TYPE,
        DOCUMENT_SOURCE_NAME,
        COMPILE_SUBSTITUTE_NAMES,
        COMPILE_NO_VALIDATION,
        COMPILE_NO_UPA_RULE,
        COMPILE_NO_PVR_RULE,
        COMPILE_NO_ANNOTATIONS,
        COMPILE_DOWNLOAD_URLS,
        COMPILE_MDEF_NAMESPACES,
        COMPILE_PARTIAL_TYPESYSTEM,
        COMPILE_PARTIAL_METHODS,
        COMPILE_ANNOTATION_JAVADOC,
        VALIDATE_ON_SET,
        VALIDATE_TREAT_LAX_AS_SKIP,
        VALIDATE_STRICT,
        VALIDATE_TEXT_ONLY,
        UNSYNCHRONIZED,
        ENTITY_RESOLVER,
        BASE_URI,
        SCHEMA_CODE_PRINTER,
        GENERATE_JAVA_VERSION,
        USE_SAME_LOCALE,
        COPY_USE_NEW_SYNC_DOMAIN,
        LOAD_ENTITY_BYTES_LIMIT,
        ENTITY_EXPANSION_LIMIT,
        LOAD_DTD_GRAMMAR,
        LOAD_EXTERNAL_DTD,
        DISALLOW_DOCTYPE_DECLARATION,
        SAAJ_IMPL,
        LOAD_USE_LOCALE_CHAR_UTIL,
        XPATH_USE_SAXON,
        XPATH_USE_XMLBEANS,
        ATTTRIBUTE_VALIDATION_COMPAT_MODE
    }

    static {
        XmlOptions xmlOptions = new XmlOptions();
        EMPTY_OPTIONS = xmlOptions;
        xmlOptions._map = Collections.unmodifiableMap(xmlOptions._map);
    }

    public XmlOptions() {
        this._map = new HashMap();
    }

    public XmlOptions(XmlOptions xmlOptions) {
        HashMap hashMap = new HashMap();
        this._map = hashMap;
        if (xmlOptions != null) {
            hashMap.putAll(xmlOptions._map);
        }
    }

    public XmlOptions setSaveNamespacesFirst() {
        return setSaveNamespacesFirst(true);
    }

    public XmlOptions setSaveNamespacesFirst(boolean z) {
        return set(XmlOptionsKeys.SAVE_NAMESPACES_FIRST, z);
    }

    public boolean isSaveNamespacesFirst() {
        return hasOption(XmlOptionsKeys.SAVE_NAMESPACES_FIRST);
    }

    public XmlOptions setSavePrettyPrint() {
        return setSavePrettyPrint(true);
    }

    public XmlOptions setSavePrettyPrint(boolean z) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT, z);
    }

    public boolean isSavePrettyPrint() {
        return hasOption(XmlOptionsKeys.SAVE_PRETTY_PRINT);
    }

    public XmlOptions setSavePrettyPrintIndent(int i) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT_INDENT, i);
    }

    public Integer getSavePrettyPrintIndent() {
        return (Integer) get(XmlOptionsKeys.SAVE_PRETTY_PRINT_INDENT);
    }

    public XmlOptions setSavePrettyPrintOffset(int i) {
        return set(XmlOptionsKeys.SAVE_PRETTY_PRINT_OFFSET, i);
    }

    public Integer getSavePrettyPrintOffset() {
        return (Integer) get(XmlOptionsKeys.SAVE_PRETTY_PRINT_OFFSET);
    }

    public XmlOptions setCharacterEncoding(String str) {
        return set(XmlOptionsKeys.CHARACTER_ENCODING, (Object) str);
    }

    public String getCharacterEncoding() {
        return (String) get(XmlOptionsKeys.CHARACTER_ENCODING);
    }

    public XmlOptions setDocumentType(SchemaType schemaType) {
        return set(XmlOptionsKeys.DOCUMENT_TYPE, (Object) schemaType);
    }

    public SchemaType getDocumentType() {
        return (SchemaType) get(XmlOptionsKeys.DOCUMENT_TYPE);
    }

    public XmlOptions setErrorListener(Collection<XmlError> collection) {
        return set(XmlOptionsKeys.ERROR_LISTENER, (Object) collection);
    }

    public Collection<XmlError> getErrorListener() {
        return (Collection) get(XmlOptionsKeys.ERROR_LISTENER);
    }

    public XmlOptions setSaveAggressiveNamespaces() {
        return setSaveAggressiveNamespaces(true);
    }

    public XmlOptions setSaveAggressiveNamespaces(boolean z) {
        return set(XmlOptionsKeys.SAVE_AGGRESSIVE_NAMESPACES, z);
    }

    public boolean isSaveAggressiveNamespaces() {
        return hasOption(XmlOptionsKeys.SAVE_AGGRESSIVE_NAMESPACES);
    }

    public XmlOptions setSaveSyntheticDocumentElement(QName qName) {
        return set(XmlOptionsKeys.SAVE_SYNTHETIC_DOCUMENT_ELEMENT, (Object) qName);
    }

    public QName getSaveSyntheticDocumentElement() {
        return (QName) get(XmlOptionsKeys.SAVE_SYNTHETIC_DOCUMENT_ELEMENT);
    }

    public XmlOptions setUseDefaultNamespace() {
        return setUseDefaultNamespace(true);
    }

    public XmlOptions setUseDefaultNamespace(boolean z) {
        return set(XmlOptionsKeys.SAVE_USE_DEFAULT_NAMESPACE, z);
    }

    public boolean isUseDefaultNamespace() {
        return hasOption(XmlOptionsKeys.SAVE_USE_DEFAULT_NAMESPACE);
    }

    public XmlOptions setSaveImplicitNamespaces(Map<String, String> map) {
        return set(XmlOptionsKeys.SAVE_IMPLICIT_NAMESPACES, (Object) map);
    }

    public Map<String, String> getSaveImplicitNamespaces() {
        return (Map) get(XmlOptionsKeys.SAVE_IMPLICIT_NAMESPACES);
    }

    public XmlOptions setSaveSuggestedPrefixes(Map<String, String> map) {
        return set(XmlOptionsKeys.SAVE_SUGGESTED_PREFIXES, (Object) map);
    }

    public Map<String, String> getSaveSuggestedPrefixes() {
        return (Map) get(XmlOptionsKeys.SAVE_SUGGESTED_PREFIXES);
    }

    public XmlOptions setSaveFilterProcinst(String str) {
        return set(XmlOptionsKeys.SAVE_FILTER_PROCINST, (Object) str);
    }

    public String getSaveFilterProcinst() {
        return (String) get(XmlOptionsKeys.SAVE_FILTER_PROCINST);
    }

    public XmlOptions setSaveSubstituteCharacters(XmlOptionCharEscapeMap xmlOptionCharEscapeMap) {
        return set(XmlOptionsKeys.SAVE_SUBSTITUTE_CHARACTERS, (Object) xmlOptionCharEscapeMap);
    }

    public XmlOptionCharEscapeMap getSaveSubstituteCharacters() {
        return (XmlOptionCharEscapeMap) get(XmlOptionsKeys.SAVE_SUBSTITUTE_CHARACTERS);
    }

    public XmlOptions setSaveUseOpenFrag() {
        return setSaveUseOpenFrag(true);
    }

    public XmlOptions setSaveUseOpenFrag(boolean z) {
        return set(XmlOptionsKeys.SAVE_USE_OPEN_FRAGMENT, z);
    }

    public boolean isSaveUseOpenFrag() {
        return hasOption(XmlOptionsKeys.SAVE_USE_OPEN_FRAGMENT);
    }

    public XmlOptions setSaveOuter() {
        return setSaveOuter(true);
    }

    public XmlOptions setSaveOuter(boolean z) {
        return set(XmlOptionsKeys.SAVE_OUTER, z);
    }

    public boolean isSaveOuter() {
        return hasOption(XmlOptionsKeys.SAVE_OUTER);
    }

    public XmlOptions setSaveInner() {
        return setSaveInner(true);
    }

    public XmlOptions setSaveInner(boolean z) {
        return set(XmlOptionsKeys.SAVE_INNER, z);
    }

    public boolean isSaveInner() {
        return hasOption(XmlOptionsKeys.SAVE_INNER);
    }

    public XmlOptions setSaveNoXmlDecl() {
        return setSaveNoXmlDecl(true);
    }

    public XmlOptions setSaveNoXmlDecl(boolean z) {
        return set(XmlOptionsKeys.SAVE_NO_XML_DECL, z);
    }

    public boolean isSaveNoXmlDecl() {
        return hasOption(XmlOptionsKeys.SAVE_NO_XML_DECL);
    }

    public XmlOptions setSaveCDataLengthThreshold(int i) {
        return set(XmlOptionsKeys.SAVE_CDATA_LENGTH_THRESHOLD, i);
    }

    public Integer getSaveCDataLengthThreshold() {
        return (Integer) get(XmlOptionsKeys.SAVE_CDATA_LENGTH_THRESHOLD);
    }

    public XmlOptions setSaveCDataEntityCountThreshold(int i) {
        return set(XmlOptionsKeys.SAVE_CDATA_ENTITY_COUNT_THRESHOLD, i);
    }

    public Integer getSaveCDataEntityCountThreshold() {
        return (Integer) get(XmlOptionsKeys.SAVE_CDATA_ENTITY_COUNT_THRESHOLD);
    }

    public XmlOptions setUseCDataBookmarks() {
        return set(XmlOptionsKeys.LOAD_SAVE_CDATA_BOOKMARKS);
    }

    public boolean isUseCDataBookmarks() {
        return hasOption(XmlOptionsKeys.LOAD_SAVE_CDATA_BOOKMARKS);
    }

    public XmlOptions setSaveSaxNoNSDeclsInAttributes() {
        return setSaveSaxNoNSDeclsInAttributes(true);
    }

    public XmlOptions setSaveSaxNoNSDeclsInAttributes(boolean z) {
        return set(XmlOptionsKeys.SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES, z);
    }

    public boolean isSaveSaxNoNSDeclsInAttributes() {
        return hasOption(XmlOptionsKeys.SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES);
    }

    public XmlOptions setLoadReplaceDocumentElement(QName qName) {
        return set(XmlOptionsKeys.LOAD_REPLACE_DOCUMENT_ELEMENT, (Object) qName);
    }

    public QName getLoadReplaceDocumentElement() {
        return (QName) get(XmlOptionsKeys.LOAD_REPLACE_DOCUMENT_ELEMENT);
    }

    public XmlOptions setLoadStripWhitespace() {
        return setLoadStripWhitespace(true);
    }

    public XmlOptions setLoadStripWhitespace(boolean z) {
        return set(XmlOptionsKeys.LOAD_STRIP_WHITESPACE, z);
    }

    public boolean isSetLoadStripWhitespace() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_WHITESPACE);
    }

    public XmlOptions setLoadStripComments() {
        return setLoadStripComments(true);
    }

    public XmlOptions setLoadStripComments(boolean z) {
        return set(XmlOptionsKeys.LOAD_STRIP_COMMENTS, z);
    }

    public boolean isLoadStripComments() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_COMMENTS);
    }

    public XmlOptions setLoadStripProcinsts() {
        return setLoadStripProcinsts(true);
    }

    public XmlOptions setLoadStripProcinsts(boolean z) {
        return set(XmlOptionsKeys.LOAD_STRIP_PROCINSTS, z);
    }

    public boolean isLoadStripProcinsts() {
        return hasOption(XmlOptionsKeys.LOAD_STRIP_PROCINSTS);
    }

    public XmlOptions setLoadLineNumbers() {
        return setLoadLineNumbers(true);
    }

    public XmlOptions setLoadLineNumbers(boolean z) {
        return set(XmlOptionsKeys.LOAD_LINE_NUMBERS, z);
    }

    public boolean isLoadLineNumbers() {
        return hasOption(XmlOptionsKeys.LOAD_LINE_NUMBERS);
    }

    public XmlOptions setLoadLineNumbersEndElement() {
        return setLoadLineNumbersEndElement(true);
    }

    public XmlOptions setLoadLineNumbersEndElement(boolean z) {
        setLoadLineNumbers(true);
        return set(XmlOptionsKeys.LOAD_LINE_NUMBERS_END_ELEMENT, z);
    }

    public boolean isLoadLineNumbersEndElement() {
        return hasOption(XmlOptionsKeys.LOAD_LINE_NUMBERS_END_ELEMENT);
    }

    public XmlOptions setLoadSubstituteNamespaces(Map<String, String> map) {
        return set(XmlOptionsKeys.LOAD_SUBSTITUTE_NAMESPACES, (Object) map);
    }

    public Map<String, String> getLoadSubstituteNamespaces() {
        return (Map) get(XmlOptionsKeys.LOAD_SUBSTITUTE_NAMESPACES);
    }

    public XmlOptions setLoadTrimTextBuffer() {
        return setLoadTrimTextBuffer(true);
    }

    public XmlOptions setLoadTrimTextBuffer(boolean z) {
        return set(XmlOptionsKeys.LOAD_TRIM_TEXT_BUFFER, z);
    }

    public boolean isLoadTrimTextBuffer() {
        return hasOption(XmlOptionsKeys.LOAD_TRIM_TEXT_BUFFER);
    }

    public XmlOptions setLoadAdditionalNamespaces(Map<String, String> map) {
        return set(XmlOptionsKeys.LOAD_ADDITIONAL_NAMESPACES, (Object) map);
    }

    public Map<String, String> getLoadAdditionalNamespaces() {
        return (Map) get(XmlOptionsKeys.LOAD_ADDITIONAL_NAMESPACES);
    }

    public XmlOptions setLoadMessageDigest() {
        return setLoadMessageDigest(true);
    }

    public XmlOptions setLoadMessageDigest(boolean z) {
        return set(XmlOptionsKeys.LOAD_MESSAGE_DIGEST, z);
    }

    public boolean isLoadMessageDigest() {
        return hasOption(XmlOptionsKeys.LOAD_MESSAGE_DIGEST);
    }

    public XmlOptions setLoadUseDefaultResolver() {
        return setLoadUseDefaultResolver(true);
    }

    public XmlOptions setLoadUseDefaultResolver(boolean z) {
        return set(XmlOptionsKeys.LOAD_USE_DEFAULT_RESOLVER, z);
    }

    public boolean isLoadUseDefaultResolver() {
        return hasOption(XmlOptionsKeys.LOAD_USE_DEFAULT_RESOLVER);
    }

    public XmlOptions setLoadUseXMLReader(XMLReader xMLReader) {
        return set(XmlOptionsKeys.LOAD_USE_XMLREADER, (Object) xMLReader);
    }

    public XMLReader getLoadUseXMLReader() {
        return (XMLReader) get(XmlOptionsKeys.LOAD_USE_XMLREADER);
    }

    public XmlOptions setXqueryCurrentNodeVar(String str) {
        return set(XmlOptionsKeys.XQUERY_CURRENT_NODE_VAR, (Object) str);
    }

    public String getXqueryCurrentNodeVar() {
        return (String) get(XmlOptionsKeys.XQUERY_CURRENT_NODE_VAR);
    }

    public XmlOptions setXqueryVariables(Map<String, Object> map) {
        return set(XmlOptionsKeys.XQUERY_VARIABLE_MAP, (Object) map);
    }

    public Map<String, Object> getXqueryVariables() {
        return (Map) get(XmlOptionsKeys.XQUERY_VARIABLE_MAP);
    }

    public XmlOptions setDocumentSourceName(String str) {
        return set(XmlOptionsKeys.DOCUMENT_SOURCE_NAME, (Object) str);
    }

    public String getDocumentSourceName() {
        return (String) get(XmlOptionsKeys.DOCUMENT_SOURCE_NAME);
    }

    public XmlOptions setCompileSubstituteNames(Map<QName, QName> map) {
        return set(XmlOptionsKeys.COMPILE_SUBSTITUTE_NAMES, (Object) map);
    }

    public Map<QName, QName> getCompileSubstituteNames() {
        return (Map) get(XmlOptionsKeys.COMPILE_SUBSTITUTE_NAMES);
    }

    public XmlOptions setCompileNoValidation() {
        return set(XmlOptionsKeys.COMPILE_NO_VALIDATION);
    }

    public boolean isCompileNoValidation() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_VALIDATION);
    }

    public XmlOptions setCompileNoUpaRule() {
        return setCompileNoUpaRule(true);
    }

    public XmlOptions setCompileNoUpaRule(boolean z) {
        return set(XmlOptionsKeys.COMPILE_NO_UPA_RULE, z);
    }

    public boolean isCompileNoUpaRule() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_UPA_RULE);
    }

    public XmlOptions setCompileNoPvrRule() {
        return setCompileNoPvrRule(true);
    }

    public XmlOptions setCompileNoPvrRule(boolean z) {
        return set(XmlOptionsKeys.COMPILE_NO_PVR_RULE, z);
    }

    public boolean isCompileNoPvrRule() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_PVR_RULE);
    }

    public XmlOptions setCompileNoAnnotations() {
        return setCompileNoAnnotations(true);
    }

    public XmlOptions setCompileNoAnnotations(boolean z) {
        return set(XmlOptionsKeys.COMPILE_NO_ANNOTATIONS, z);
    }

    public boolean isCompileNoAnnotations() {
        return hasOption(XmlOptionsKeys.COMPILE_NO_ANNOTATIONS);
    }

    public XmlOptions setCompileDownloadUrls() {
        return setCompileDownloadUrls(true);
    }

    public XmlOptions setCompileDownloadUrls(boolean z) {
        return set(XmlOptionsKeys.COMPILE_DOWNLOAD_URLS, z);
    }

    public boolean isCompileDownloadUrls() {
        return hasOption(XmlOptionsKeys.COMPILE_DOWNLOAD_URLS);
    }

    public XmlOptions setCompileMdefNamespaces(Set<String> set) {
        return set(XmlOptionsKeys.COMPILE_MDEF_NAMESPACES, (Object) set);
    }

    public Set<String> getCompileMdefNamespaces() {
        return (Set) get(XmlOptionsKeys.COMPILE_MDEF_NAMESPACES);
    }

    public XmlOptions setCompilePartialTypesystem() {
        return setCompilePartialTypesystem(true);
    }

    public XmlOptions setCompilePartialTypesystem(boolean z) {
        return set(XmlOptionsKeys.COMPILE_PARTIAL_TYPESYSTEM, z);
    }

    public boolean isCompilePartialTypesystem() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.COMPILE_PARTIAL_TYPESYSTEM);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setValidateOnSet() {
        return setValidateOnSet(true);
    }

    public XmlOptions setValidateOnSet(boolean z) {
        return set(XmlOptionsKeys.VALIDATE_ON_SET, z);
    }

    public boolean isValidateOnSet() {
        return hasOption(XmlOptionsKeys.VALIDATE_ON_SET);
    }

    public XmlOptions setValidateTreatLaxAsSkip() {
        return setValidateTreatLaxAsSkip(true);
    }

    public XmlOptions setValidateTreatLaxAsSkip(boolean z) {
        return set(XmlOptionsKeys.VALIDATE_TREAT_LAX_AS_SKIP, z);
    }

    public boolean isValidateTreatLaxAsSkip() {
        return hasOption(XmlOptionsKeys.VALIDATE_TREAT_LAX_AS_SKIP);
    }

    public XmlOptions setValidateStrict() {
        return setValidateStrict(true);
    }

    public XmlOptions setValidateStrict(boolean z) {
        return set(XmlOptionsKeys.VALIDATE_STRICT, z);
    }

    public boolean isValidateStrict() {
        return hasOption(XmlOptionsKeys.VALIDATE_STRICT);
    }

    public XmlOptions setValidateTextOnly() {
        return setValidateTextOnly(true);
    }

    public XmlOptions setValidateTextOnly(boolean z) {
        return set(XmlOptionsKeys.VALIDATE_TEXT_ONLY, z);
    }

    public boolean isValidateTextOnly() {
        return hasOption(XmlOptionsKeys.VALIDATE_TEXT_ONLY);
    }

    public XmlOptions setUnsynchronized() {
        return setUnsynchronized(true);
    }

    public XmlOptions setUnsynchronized(boolean z) {
        return set(XmlOptionsKeys.UNSYNCHRONIZED, z);
    }

    public boolean isUnsynchronized() {
        return hasOption(XmlOptionsKeys.UNSYNCHRONIZED);
    }

    public XmlOptions setEntityResolver(EntityResolver entityResolver) {
        return set(XmlOptionsKeys.ENTITY_RESOLVER, (Object) entityResolver);
    }

    public EntityResolver getEntityResolver() {
        return (EntityResolver) get(XmlOptionsKeys.ENTITY_RESOLVER);
    }

    public XmlOptions setBaseURI(URI uri) {
        return set(XmlOptionsKeys.BASE_URI, (Object) uri);
    }

    public URI getBaseURI() {
        return (URI) get(XmlOptionsKeys.BASE_URI);
    }

    public XmlOptions setSchemaCodePrinter(SchemaCodePrinter schemaCodePrinter) {
        return set(XmlOptionsKeys.SCHEMA_CODE_PRINTER, (Object) schemaCodePrinter);
    }

    public SchemaCodePrinter getSchemaCodePrinter() {
        return (SchemaCodePrinter) get(XmlOptionsKeys.SCHEMA_CODE_PRINTER);
    }

    public XmlOptions setCopyUseNewSynchronizationDomain(boolean z) {
        return set(XmlOptionsKeys.COPY_USE_NEW_SYNC_DOMAIN, z);
    }

    public boolean isCopyUseNewSynchronizationDomain() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.COPY_USE_NEW_SYNC_DOMAIN);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setUseSameLocale(Object obj) {
        return set(XmlOptionsKeys.USE_SAME_LOCALE, obj);
    }

    public Object getUseSameLocale() {
        return get(XmlOptionsKeys.USE_SAME_LOCALE);
    }

    public XmlOptions setLoadEntityBytesLimit(int i) {
        return set(XmlOptionsKeys.LOAD_ENTITY_BYTES_LIMIT, i);
    }

    public Integer getLoadEntityBytesLimit() {
        return (Integer) get(XmlOptionsKeys.LOAD_ENTITY_BYTES_LIMIT);
    }

    public XmlOptions setEntityExpansionLimit(int i) {
        return set(XmlOptionsKeys.ENTITY_EXPANSION_LIMIT, i);
    }

    public int getEntityExpansionLimit() {
        Integer num = (Integer) get(XmlOptionsKeys.ENTITY_EXPANSION_LIMIT);
        if (num == null) {
            return 2048;
        }
        return num.intValue();
    }

    public XmlOptions setLoadDTDGrammar(boolean z) {
        return set(XmlOptionsKeys.LOAD_DTD_GRAMMAR, z);
    }

    public boolean isLoadDTDGrammar() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.LOAD_DTD_GRAMMAR);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setLoadExternalDTD(boolean z) {
        return set(XmlOptionsKeys.LOAD_EXTERNAL_DTD, z);
    }

    public boolean isLoadExternalDTD() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.LOAD_EXTERNAL_DTD);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setDisallowDocTypeDeclaration(boolean z) {
        return set(XmlOptionsKeys.DISALLOW_DOCTYPE_DECLARATION, z);
    }

    public boolean disallowDocTypeDeclaration() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.DISALLOW_DOCTYPE_DECLARATION);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setSaveOptimizeForSpeed(boolean z) {
        return set(XmlOptionsKeys.SAVE_OPTIMIZE_FOR_SPEED, z);
    }

    public boolean isSaveOptimizeForSpeed() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.SAVE_OPTIMIZE_FOR_SPEED);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setSaaj(Saaj saaj) {
        return set(XmlOptionsKeys.SAAJ_IMPL, (Object) saaj);
    }

    public Saaj getSaaj() {
        return (Saaj) get(XmlOptionsKeys.SAAJ_IMPL);
    }

    public XmlOptions setLoadUseLocaleCharUtil(boolean z) {
        return set(XmlOptionsKeys.LOAD_USE_LOCALE_CHAR_UTIL, z);
    }

    public boolean isLoadUseLocaleCharUtil() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.LOAD_USE_LOCALE_CHAR_UTIL);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setXPathUseSaxon() {
        return setXPathUseSaxon(true);
    }

    public XmlOptions setXPathUseSaxon(boolean z) {
        return set(XmlOptionsKeys.XPATH_USE_SAXON, z);
    }

    public boolean isXPathUseSaxon() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.XPATH_USE_SAXON);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setXPathUseXmlBeans() {
        return setXPathUseSaxon(true);
    }

    public XmlOptions setXPathUseXmlBeans(boolean z) {
        return set(XmlOptionsKeys.XPATH_USE_XMLBEANS, z);
    }

    public boolean isXPathUseXmlBeans() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.XPATH_USE_XMLBEANS);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setCompileAnnotationAsJavadoc() {
        return setCompileAnnotationAsJavadoc(true);
    }

    public XmlOptions setCompileAnnotationAsJavadoc(boolean z) {
        return set(XmlOptionsKeys.COMPILE_ANNOTATION_JAVADOC, z);
    }

    public boolean isCompileAnnotationAsJavadoc() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.COMPILE_ANNOTATION_JAVADOC);
        return bool != null && bool.booleanValue();
    }

    public XmlOptions setAttributeValidationCompatMode(boolean z) {
        return set(XmlOptionsKeys.ATTTRIBUTE_VALIDATION_COMPAT_MODE, z);
    }

    public boolean isAttributeValidationCompatMode() {
        Boolean bool = (Boolean) get(XmlOptionsKeys.ATTTRIBUTE_VALIDATION_COMPAT_MODE);
        return bool != null && bool.booleanValue();
    }

    public Set<BeanMethod> getCompilePartialMethod() {
        return (Set) get(XmlOptionsKeys.COMPILE_PARTIAL_METHODS);
    }

    public void setCompilePartialMethod(Set<BeanMethod> set) {
        if (set == null || set.isEmpty()) {
            remove(XmlOptionsKeys.COMPILE_PARTIAL_METHODS);
        } else {
            set(XmlOptionsKeys.COMPILE_PARTIAL_METHODS, (Object) set);
        }
    }

    public static XmlOptions maskNull(XmlOptions xmlOptions) {
        return xmlOptions == null ? EMPTY_OPTIONS : xmlOptions;
    }

    private XmlOptions set(XmlOptionsKeys xmlOptionsKeys) {
        return set(xmlOptionsKeys, true);
    }

    private XmlOptions set(XmlOptionsKeys xmlOptionsKeys, Object obj) {
        this._map.put(xmlOptionsKeys, obj);
        return this;
    }

    private XmlOptions set(XmlOptionsKeys xmlOptionsKeys, int i) {
        return set(xmlOptionsKeys, (Object) Integer.valueOf(i));
    }

    private XmlOptions set(XmlOptionsKeys xmlOptionsKeys, boolean z) {
        if (z) {
            set(xmlOptionsKeys, (Object) Boolean.TRUE);
        } else {
            remove(xmlOptionsKeys);
        }
        return this;
    }

    public boolean hasOption(XmlOptionsKeys xmlOptionsKeys) {
        return this._map.containsKey(xmlOptionsKeys);
    }

    public Object get(XmlOptionsKeys xmlOptionsKeys) {
        return this._map.get(xmlOptionsKeys);
    }

    public void remove(XmlOptionsKeys xmlOptionsKeys) {
        this._map.remove(xmlOptionsKeys);
    }
}
