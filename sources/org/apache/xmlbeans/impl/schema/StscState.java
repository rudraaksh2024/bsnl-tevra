package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javax.xml.namespace.QName;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;

public class StscState {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final SchemaType[] EMPTY_ST_ARRAY = new SchemaType[0];
    static final XmlValueRef[] FACETS_LIST;
    private static final XmlValueRef[] FACETS_NONE;
    static final XmlValueRef[] FACETS_UNION;
    private static final XmlValueRef[] FACETS_WS_COLLAPSE;
    static final boolean[] FIXED_FACETS_LIST;
    private static final boolean[] FIXED_FACETS_NONE;
    static final boolean[] FIXED_FACETS_UNION;
    private static final boolean[] FIXED_FACETS_WS;
    private static final String PROJECT_URL_PREFIX = "project://local";
    private static final XmlValueRef XMLSTR_COLLAPSE = buildString("preserve");
    private static final XmlValueRef XMLSTR_PRESERVE = buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = buildString("preserve");
    private static final ThreadLocal<StscStack> tl_stscStack = new ThreadLocal<>();
    private boolean _allowPartial;
    private final List<SchemaAnnotation> _annotations;
    private final Map<QName, SchemaAttributeGroup> _attributeGroups;
    private final Map<QName, SchemaType> _attributeTypes;
    private URI _baseURI;
    private Map<QName, QName> _compatMap;
    private BindingConfig _config;
    private final Map<String, SchemaContainer> _containers;
    private SchemaDependencies _dependencies;
    private byte[] _digest;
    private final Map<QName, SchemaType> _documentTypes;
    private boolean _doingDownloads;
    private EntityResolver _entityResolver;
    private Collection<XmlError> _errorListener;
    private String _givenStsName;
    private final Map<QName, SchemaGlobalAttribute> _globalAttributes;
    private final Map<QName, SchemaGlobalElement> _globalElements;
    private final Map<QName, SchemaType> _globalTypes;
    private final Map<QName, SchemaIdentityConstraint> _idConstraints;
    private SchemaTypeLoader _importingLoader;
    private boolean _mdefAll;
    private final Set<String> _mdefNamespaces;
    private final Map<String, SchemaComponent> _misspelledNames;
    private final Map<QName, SchemaModelGroup> _modelGroups;
    private final Set<String> _namespaces;
    private boolean _noAnn;
    private boolean _noDigest;
    private boolean _noPvr;
    private boolean _noUpa;
    private final Set<SchemaComponent> _processingGroups;
    private int _recoveredErrors;
    private final Map<SchemaAttributeGroupImpl, SchemaAttributeGroupImpl> _redefinedAttributeGroups;
    private final Map<SchemaTypeImpl, SchemaTypeImpl> _redefinedGlobalTypes;
    private final Map<SchemaModelGroupImpl, SchemaModelGroupImpl> _redefinedModelGroups;
    private final SchemaTypeLoader _s4sloader;
    private File _schemasDir;
    private final Map<String, String> _sourceForUri;
    private SchemaTypeSystemImpl _target;
    private final Map<String, SchemaType> _typesByClassname;

    static {
        XmlValueRef[] xmlValueRefArr = new XmlValueRef[12];
        FACETS_NONE = xmlValueRefArr;
        boolean[] zArr = new boolean[12];
        FIXED_FACETS_NONE = zArr;
        boolean[] zArr2 = new boolean[12];
        FIXED_FACETS_WS = zArr2;
        XmlValueRef[] xmlValueRefArr2 = {null, null, null, null, null, null, null, null, null, build_wsstring(3), null, null};
        FACETS_WS_COLLAPSE = xmlValueRefArr2;
        FACETS_UNION = xmlValueRefArr;
        FIXED_FACETS_UNION = zArr;
        FACETS_LIST = xmlValueRefArr2;
        FIXED_FACETS_LIST = zArr2;
    }

    private static Set<String> buildDefaultMdefNamespaces() {
        return new HashSet(Collections.singletonList("http://www.openuri.org/2002/04/soap/conversation/"));
    }

    private StscState() {
        this._digest = null;
        this._noDigest = false;
        this._allowPartial = false;
        this._recoveredErrors = 0;
        this._containers = new LinkedHashMap();
        this._redefinedGlobalTypes = new LinkedHashMap();
        this._redefinedModelGroups = new LinkedHashMap();
        this._redefinedAttributeGroups = new LinkedHashMap();
        this._globalTypes = new LinkedHashMap();
        this._globalElements = new LinkedHashMap();
        this._globalAttributes = new LinkedHashMap();
        this._modelGroups = new LinkedHashMap();
        this._attributeGroups = new LinkedHashMap();
        this._documentTypes = new LinkedHashMap();
        this._attributeTypes = new LinkedHashMap();
        this._typesByClassname = new LinkedHashMap();
        this._misspelledNames = new HashMap();
        this._processingGroups = new LinkedHashSet();
        this._idConstraints = new LinkedHashMap();
        this._namespaces = new HashSet();
        this._annotations = new ArrayList();
        this._mdefNamespaces = buildDefaultMdefNamespaces();
        this._sourceForUri = new HashMap();
        this._baseURI = URI.create("project://local/");
        this._s4sloader = XmlBeans.typeLoaderForClassLoader(SchemaDocument.class.getClassLoader());
    }

    public void initFromTypeSystem(SchemaTypeSystemImpl schemaTypeSystemImpl, Set<String> set) {
        for (SchemaContainer schemaContainer : schemaTypeSystemImpl.containers()) {
            if (!set.contains(schemaContainer.getNamespace())) {
                addContainer(schemaContainer);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addNewContainer(String str) {
        if (!this._containers.containsKey(str)) {
            SchemaContainer schemaContainer = new SchemaContainer(str);
            schemaContainer.setTypeSystem(sts());
            addNamespace(str);
            this._containers.put(str, schemaContainer);
        }
    }

    private void addContainer(SchemaContainer schemaContainer) {
        this._containers.put(schemaContainer.getNamespace(), schemaContainer);
        schemaContainer.globalElements().forEach(new StscState$$ExternalSyntheticLambda1(this));
        schemaContainer.globalAttributes().forEach(new StscState$$ExternalSyntheticLambda2(this));
        schemaContainer.modelGroups().forEach(new StscState$$ExternalSyntheticLambda3(this));
        schemaContainer.attributeGroups().forEach(new StscState$$ExternalSyntheticLambda4(this));
        schemaContainer.globalTypes().forEach(mapTypes(this._globalTypes, false));
        schemaContainer.documentTypes().forEach(mapTypes(this._documentTypes, true));
        schemaContainer.attributeTypes().forEach(mapTypes(this._attributeTypes, true));
        schemaContainer.identityConstraints().forEach(new StscState$$ExternalSyntheticLambda5(this));
        this._annotations.addAll(schemaContainer.annotations());
        this._namespaces.add(schemaContainer.getNamespace());
        schemaContainer.unsetImmutable();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$0$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2322lambda$addContainer$0$orgapachexmlbeansimplschemaStscState(SchemaGlobalElement schemaGlobalElement) {
        SchemaGlobalElement put = this._globalElements.put(schemaGlobalElement.getName(), schemaGlobalElement);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$1$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2323lambda$addContainer$1$orgapachexmlbeansimplschemaStscState(SchemaGlobalAttribute schemaGlobalAttribute) {
        SchemaGlobalAttribute put = this._globalAttributes.put(schemaGlobalAttribute.getName(), schemaGlobalAttribute);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$2$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2324lambda$addContainer$2$orgapachexmlbeansimplschemaStscState(SchemaModelGroup schemaModelGroup) {
        SchemaModelGroup put = this._modelGroups.put(schemaModelGroup.getName(), schemaModelGroup);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$3$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2325lambda$addContainer$3$orgapachexmlbeansimplschemaStscState(SchemaAttributeGroup schemaAttributeGroup) {
        SchemaAttributeGroup put = this._attributeGroups.put(schemaAttributeGroup.getName(), schemaAttributeGroup);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addContainer$4$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2326lambda$addContainer$4$orgapachexmlbeansimplschemaStscState(SchemaIdentityConstraint schemaIdentityConstraint) {
        SchemaIdentityConstraint put = this._idConstraints.put(schemaIdentityConstraint.getName(), schemaIdentityConstraint);
    }

    private Consumer<SchemaType> mapTypes(Map<QName, SchemaType> map, boolean z) {
        return new StscState$$ExternalSyntheticLambda0(this, z, map);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$mapTypes$5$org-apache-xmlbeans-impl-schema-StscState  reason: not valid java name */
    public /* synthetic */ void m2327lambda$mapTypes$5$orgapachexmlbeansimplschemaStscState(boolean z, Map map, SchemaType schemaType) {
        map.put(z ? schemaType.getProperties()[0].getName() : schemaType.getName(), schemaType);
        if (schemaType.getFullJavaName() != null) {
            addClassname(schemaType.getFullJavaName(), schemaType);
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaContainer getContainer(String str) {
        return this._containers.get(str);
    }

    /* access modifiers changed from: package-private */
    public Map<String, SchemaContainer> getContainerMap() {
        return Collections.unmodifiableMap(this._containers);
    }

    /* access modifiers changed from: package-private */
    public void registerDependency(String str, String str2) {
        this._dependencies.registerDependency(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void registerContribution(String str, String str2) {
        this._dependencies.registerContribution(str, str2);
    }

    /* access modifiers changed from: package-private */
    public SchemaDependencies getDependencies() {
        return this._dependencies;
    }

    /* access modifiers changed from: package-private */
    public void setDependencies(SchemaDependencies schemaDependencies) {
        this._dependencies = schemaDependencies;
    }

    /* access modifiers changed from: package-private */
    public boolean isFileProcessed(String str) {
        return this._dependencies.isFileRepresented(str);
    }

    public void setImportingTypeLoader(SchemaTypeLoader schemaTypeLoader) {
        this._importingLoader = schemaTypeLoader;
    }

    public void setErrorListener(Collection<XmlError> collection) {
        this._errorListener = collection;
    }

    public void error(String str, int i, XmlObject xmlObject) {
        addError(this._errorListener, str, i, xmlObject);
    }

    public void error(String str, Object[] objArr, XmlObject xmlObject) {
        addError(this._errorListener, str, objArr, xmlObject);
    }

    public void recover(String str, Object[] objArr, XmlObject xmlObject) {
        addError(this._errorListener, str, objArr, xmlObject);
        this._recoveredErrors++;
    }

    public void warning(String str, int i, XmlObject xmlObject) {
        addWarning(this._errorListener, str, i, xmlObject);
    }

    public void warning(String str, Object[] objArr, XmlObject xmlObject) {
        if (!XmlErrorCodes.RESERVED_TYPE_NAME.equals(str) || xmlObject.documentProperties().getSourceName() == null || xmlObject.documentProperties().getSourceName().indexOf("XMLSchema.xsd") <= 0) {
            addWarning(this._errorListener, str, objArr, xmlObject);
        }
    }

    public void info(String str) {
        addInfo(this._errorListener, str);
    }

    public void info(String str, Object[] objArr) {
        addInfo(this._errorListener, str, objArr);
    }

    public static void addError(Collection<XmlError> collection, String str, int i, XmlObject xmlObject) {
        collection.add(XmlError.forObject(str, 0, xmlObject));
    }

    public static void addError(Collection<XmlError> collection, String str, Object[] objArr, XmlObject xmlObject) {
        collection.add(XmlError.forObject(str, objArr, 0, xmlObject));
    }

    public static void addError(Collection<XmlError> collection, String str, Object[] objArr, File file) {
        collection.add(XmlError.forLocation(str, objArr, 0, file.toURI().toString(), 0, 0, 0));
    }

    public static void addError(Collection<XmlError> collection, String str, Object[] objArr, URL url) {
        collection.add(XmlError.forLocation(str, objArr, 0, url.toString(), 0, 0, 0));
    }

    public static void addWarning(Collection<XmlError> collection, String str, int i, XmlObject xmlObject) {
        collection.add(XmlError.forObject(str, 1, xmlObject));
    }

    public static void addWarning(Collection<XmlError> collection, String str, Object[] objArr, XmlObject xmlObject) {
        collection.add(XmlError.forObject(str, objArr, 1, xmlObject));
    }

    public static void addInfo(Collection<XmlError> collection, String str) {
        collection.add(XmlError.forMessage(str, 2));
    }

    public static void addInfo(Collection<XmlError> collection, String str, Object[] objArr) {
        collection.add(XmlError.forMessage(str, objArr, 2));
    }

    public void setGivenTypeSystemName(String str) {
        this._givenStsName = str;
    }

    public void setTargetSchemaTypeSystem(SchemaTypeSystemImpl schemaTypeSystemImpl) {
        this._target = schemaTypeSystemImpl;
    }

    public void addSchemaDigest(byte[] bArr) {
        if (!this._noDigest) {
            if (bArr == null) {
                this._noDigest = true;
                this._digest = null;
                return;
            }
            if (this._digest == null) {
                this._digest = new byte[16];
            }
            int length = this._digest.length;
            if (bArr.length < length) {
                length = bArr.length;
            }
            for (int i = 0; i < length; i++) {
                byte[] bArr2 = this._digest;
                bArr2[i] = (byte) (bArr2[i] ^ bArr[i]);
            }
        }
    }

    public SchemaTypeSystemImpl sts() {
        SchemaTypeSystemImpl schemaTypeSystemImpl = this._target;
        if (schemaTypeSystemImpl != null) {
            return schemaTypeSystemImpl;
        }
        String str = this._givenStsName;
        if (str == null && this._digest != null) {
            str = "s".concat(new String(HexBin.encode(this._digest), StandardCharsets.ISO_8859_1));
        }
        SchemaTypeSystemImpl schemaTypeSystemImpl2 = new SchemaTypeSystemImpl(str);
        this._target = schemaTypeSystemImpl2;
        return schemaTypeSystemImpl2;
    }

    public boolean shouldDownloadURI(String str) {
        if (this._doingDownloads) {
            return true;
        }
        if (str == null) {
            return false;
        }
        try {
            URI uri = new URI(str);
            if (!uri.getScheme().equalsIgnoreCase(ArchiveStreamFactory.JAR)) {
                if (!uri.getScheme().equalsIgnoreCase(ArchiveStreamFactory.ZIP)) {
                    return uri.getScheme().equalsIgnoreCase("file");
                }
            }
            String schemeSpecificPart = uri.getSchemeSpecificPart();
            int lastIndexOf = schemeSpecificPart.lastIndexOf(33);
            if (lastIndexOf > 0) {
                schemeSpecificPart = schemeSpecificPart.substring(0, lastIndexOf);
            }
            return shouldDownloadURI(schemeSpecificPart);
        } catch (Exception unused) {
            return false;
        }
    }

    public void setOptions(XmlOptions xmlOptions) {
        if (xmlOptions != null) {
            this._allowPartial = xmlOptions.isCompilePartialTypesystem();
            this._compatMap = xmlOptions.getCompileSubstituteNames();
            boolean z = false;
            this._noUpa = xmlOptions.isCompileNoUpaRule() || !"true".equals(SystemProperties.getProperty("xmlbean.uniqueparticleattribution", "true"));
            this._noPvr = xmlOptions.isCompileNoPvrRule() || !"true".equals(SystemProperties.getProperty("xmlbean.particlerestriction", "true"));
            this._noAnn = xmlOptions.isCompileNoAnnotations() || !"true".equals(SystemProperties.getProperty("xmlbean.schemaannotations", "true"));
            if (xmlOptions.isCompileDownloadUrls() || "true".equals(SystemProperties.getProperty("xmlbean.downloadurls", "false"))) {
                z = true;
            }
            this._doingDownloads = z;
            EntityResolver entityResolver = xmlOptions.getEntityResolver();
            this._entityResolver = entityResolver;
            if (entityResolver == null) {
                this._entityResolver = ResolverUtil.getGlobalEntityResolver();
            }
            if (this._entityResolver != null) {
                this._doingDownloads = true;
            }
            Set<String> compileMdefNamespaces = xmlOptions.getCompileMdefNamespaces();
            if (compileMdefNamespaces != null) {
                this._mdefNamespaces.addAll(compileMdefNamespaces);
                if (this._mdefNamespaces.contains("##local")) {
                    this._mdefNamespaces.remove("##local");
                    this._mdefNamespaces.add("");
                }
                if (this._mdefNamespaces.contains("##any")) {
                    this._mdefNamespaces.remove("##any");
                    this._mdefAll = true;
                }
            }
        }
    }

    public EntityResolver getEntityResolver() {
        return this._entityResolver;
    }

    public boolean noUpa() {
        return this._noUpa;
    }

    public boolean noPvr() {
        return this._noPvr;
    }

    public boolean noAnn() {
        return this._noAnn;
    }

    public boolean allowPartial() {
        return this._allowPartial;
    }

    public int getRecovered() {
        return this._recoveredErrors;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r1 = r1.get(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.xml.namespace.QName compatName(javax.xml.namespace.QName r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = r2.getNamespaceURI()
            int r0 = r0.length()
            if (r0 != 0) goto L_0x001c
            if (r3 == 0) goto L_0x001c
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x001c
            javax.xml.namespace.QName r0 = new javax.xml.namespace.QName
            java.lang.String r2 = r2.getLocalPart()
            r0.<init>(r3, r2)
            r2 = r0
        L_0x001c:
            java.util.Map<javax.xml.namespace.QName, javax.xml.namespace.QName> r1 = r1._compatMap
            if (r1 != 0) goto L_0x0021
            return r2
        L_0x0021:
            java.lang.Object r1 = r1.get(r2)
            javax.xml.namespace.QName r1 = (javax.xml.namespace.QName) r1
            if (r1 != 0) goto L_0x002a
            return r2
        L_0x002a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscState.compatName(javax.xml.namespace.QName, java.lang.String):javax.xml.namespace.QName");
    }

    public void setBindingConfig(BindingConfig bindingConfig) throws IllegalArgumentException {
        this._config = bindingConfig;
    }

    public BindingConfig getBindingConfig() throws IllegalArgumentException {
        return this._config;
    }

    public String getPackageOverride(String str) {
        BindingConfig bindingConfig = this._config;
        if (bindingConfig == null) {
            return null;
        }
        return bindingConfig.lookupPackageForNamespace(str);
    }

    public String getJavaPrefix(String str) {
        BindingConfig bindingConfig = this._config;
        if (bindingConfig == null) {
            return null;
        }
        return bindingConfig.lookupPrefixForNamespace(str);
    }

    public String getJavaSuffix(String str) {
        BindingConfig bindingConfig = this._config;
        if (bindingConfig == null) {
            return null;
        }
        return bindingConfig.lookupSuffixForNamespace(str);
    }

    public String getJavaname(QName qName, int i) {
        BindingConfig bindingConfig = this._config;
        if (bindingConfig == null) {
            return null;
        }
        return bindingConfig.lookupJavanameForQName(qName, i);
    }

    private static String crunchName(QName qName) {
        return qName.getLocalPart().toLowerCase(Locale.ROOT);
    }

    /* access modifiers changed from: package-private */
    public void addSpelling(QName qName, SchemaComponent schemaComponent) {
        this._misspelledNames.put(crunchName(qName), schemaComponent);
    }

    /* access modifiers changed from: package-private */
    public SchemaComponent findSpelling(QName qName) {
        return this._misspelledNames.get(crunchName(qName));
    }

    /* access modifiers changed from: package-private */
    public void addNamespace(String str) {
        this._namespaces.add(str);
    }

    /* access modifiers changed from: package-private */
    public String[] getNamespaces() {
        return (String[]) this._namespaces.toArray(new String[0]);
    }

    /* access modifiers changed from: package-private */
    public boolean linkerDefinesNamespace(String str) {
        return this._importingLoader.isNamespaceDefined(str);
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeImpl findGlobalType(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaTypeImpl schemaTypeImpl = (SchemaTypeImpl) this._globalTypes.get(compatName);
        boolean z = false;
        if (schemaTypeImpl == null && (schemaTypeImpl = (SchemaTypeImpl) this._importingLoader.findType(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaTypeImpl;
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeImpl findRedefinedGlobalType(QName qName, String str, SchemaTypeImpl schemaTypeImpl) {
        QName name = schemaTypeImpl.getName();
        QName compatName = compatName(qName, str);
        if (compatName.equals(name)) {
            return this._redefinedGlobalTypes.get(schemaTypeImpl);
        }
        SchemaTypeImpl schemaTypeImpl2 = (SchemaTypeImpl) this._globalTypes.get(compatName);
        return schemaTypeImpl2 == null ? (SchemaTypeImpl) this._importingLoader.findType(compatName) : schemaTypeImpl2;
    }

    /* access modifiers changed from: package-private */
    public void addGlobalType(SchemaTypeImpl schemaTypeImpl, SchemaTypeImpl schemaTypeImpl2) {
        if (schemaTypeImpl != null) {
            QName name = schemaTypeImpl.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (schemaTypeImpl2 != null) {
                if (!this._redefinedGlobalTypes.containsKey(schemaTypeImpl2)) {
                    this._redefinedGlobalTypes.put(schemaTypeImpl2, schemaTypeImpl);
                    container.addRedefinedType(schemaTypeImpl.getRef());
                } else if (ignoreMdef(name)) {
                } else {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._redefinedGlobalTypes.get(schemaTypeImpl2).getSourceName()}, schemaTypeImpl.getParseObject());
                        return;
                    }
                    error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._redefinedGlobalTypes.get(schemaTypeImpl2).getSourceName()}, schemaTypeImpl.getParseObject());
                }
            } else if (!this._globalTypes.containsKey(name)) {
                this._globalTypes.put(name, schemaTypeImpl);
                container.addGlobalType(schemaTypeImpl.getRef());
                addSpelling(name, schemaTypeImpl);
            } else if (ignoreMdef(name)) {
            } else {
                if (this._mdefAll) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._globalTypes.get(name).getSourceName()}, schemaTypeImpl.getParseObject());
                    return;
                }
                error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global type", QNameHelper.pretty(name), this._globalTypes.get(name).getSourceName()}, schemaTypeImpl.getParseObject());
            }
        }
    }

    private boolean ignoreMdef(QName qName) {
        return this._mdefNamespaces.contains(qName.getNamespaceURI());
    }

    /* access modifiers changed from: package-private */
    public SchemaType[] globalTypes() {
        return (SchemaType[]) this._globalTypes.values().toArray(new SchemaType[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaType[] redefinedGlobalTypes() {
        return (SchemaType[]) this._redefinedGlobalTypes.values().toArray(new SchemaType[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeImpl findDocumentType(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaTypeImpl schemaTypeImpl = (SchemaTypeImpl) this._documentTypes.get(compatName);
        boolean z = false;
        if (schemaTypeImpl == null && (schemaTypeImpl = (SchemaTypeImpl) this._importingLoader.findDocumentType(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaTypeImpl;
    }

    /* access modifiers changed from: package-private */
    public void addDocumentType(SchemaTypeImpl schemaTypeImpl, QName qName) {
        if (!this._documentTypes.containsKey(qName)) {
            this._documentTypes.put(qName, schemaTypeImpl);
            getContainer(qName.getNamespaceURI()).addDocumentType(schemaTypeImpl.getRef());
        } else if (ignoreMdef(qName)) {
        } else {
            if (this._mdefAll) {
                warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global element", QNameHelper.pretty(qName), this._documentTypes.get(qName).getSourceName()}, schemaTypeImpl.getParseObject());
                return;
            }
            error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global element", QNameHelper.pretty(qName), this._documentTypes.get(qName).getSourceName()}, schemaTypeImpl.getParseObject());
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaType[] documentTypes() {
        return (SchemaType[]) this._documentTypes.values().toArray(new SchemaType[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeImpl findAttributeType(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaTypeImpl schemaTypeImpl = (SchemaTypeImpl) this._attributeTypes.get(compatName);
        boolean z = false;
        if (schemaTypeImpl == null && (schemaTypeImpl = (SchemaTypeImpl) this._importingLoader.findAttributeType(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaTypeImpl;
    }

    /* access modifiers changed from: package-private */
    public void addAttributeType(SchemaTypeImpl schemaTypeImpl, QName qName) {
        if (!this._attributeTypes.containsKey(qName)) {
            this._attributeTypes.put(qName, schemaTypeImpl);
            getContainer(qName.getNamespaceURI()).addAttributeType(schemaTypeImpl.getRef());
        } else if (ignoreMdef(qName)) {
        } else {
            if (this._mdefAll) {
                warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global attribute", QNameHelper.pretty(qName), this._attributeTypes.get(qName).getSourceName()}, schemaTypeImpl.getParseObject());
                return;
            }
            error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"global attribute", QNameHelper.pretty(qName), this._attributeTypes.get(qName).getSourceName()}, schemaTypeImpl.getParseObject());
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaType[] attributeTypes() {
        return (SchemaType[]) this._attributeTypes.values().toArray(new SchemaType[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaGlobalAttributeImpl findGlobalAttribute(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl = (SchemaGlobalAttributeImpl) this._globalAttributes.get(compatName);
        boolean z = false;
        if (schemaGlobalAttributeImpl == null && (schemaGlobalAttributeImpl = (SchemaGlobalAttributeImpl) this._importingLoader.findAttribute(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaGlobalAttributeImpl;
    }

    /* access modifiers changed from: package-private */
    public void addGlobalAttribute(SchemaGlobalAttributeImpl schemaGlobalAttributeImpl) {
        if (schemaGlobalAttributeImpl != null) {
            QName name = schemaGlobalAttributeImpl.getName();
            this._globalAttributes.put(name, schemaGlobalAttributeImpl);
            addSpelling(name, schemaGlobalAttributeImpl);
            getContainer(name.getNamespaceURI()).addGlobalAttribute(schemaGlobalAttributeImpl.getRef());
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaGlobalAttribute[] globalAttributes() {
        return (SchemaGlobalAttribute[]) this._globalAttributes.values().toArray(new SchemaGlobalAttribute[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaGlobalElementImpl findGlobalElement(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaGlobalElementImpl schemaGlobalElementImpl = (SchemaGlobalElementImpl) this._globalElements.get(compatName);
        boolean z = false;
        if (schemaGlobalElementImpl == null && (schemaGlobalElementImpl = (SchemaGlobalElementImpl) this._importingLoader.findElement(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaGlobalElementImpl;
    }

    /* access modifiers changed from: package-private */
    public void addGlobalElement(SchemaGlobalElementImpl schemaGlobalElementImpl) {
        if (schemaGlobalElementImpl != null) {
            QName name = schemaGlobalElementImpl.getName();
            this._globalElements.put(name, schemaGlobalElementImpl);
            getContainer(name.getNamespaceURI()).addGlobalElement(schemaGlobalElementImpl.getRef());
            addSpelling(name, schemaGlobalElementImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaGlobalElement[] globalElements() {
        return (SchemaGlobalElement[]) this._globalElements.values().toArray(new SchemaGlobalElement[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaAttributeGroupImpl findAttributeGroup(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaAttributeGroupImpl schemaAttributeGroupImpl = (SchemaAttributeGroupImpl) this._attributeGroups.get(compatName);
        boolean z = false;
        if (schemaAttributeGroupImpl == null && (schemaAttributeGroupImpl = (SchemaAttributeGroupImpl) this._importingLoader.findAttributeGroup(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaAttributeGroupImpl;
    }

    /* access modifiers changed from: package-private */
    public SchemaAttributeGroupImpl findRedefinedAttributeGroup(QName qName, String str, SchemaAttributeGroupImpl schemaAttributeGroupImpl) {
        QName name = schemaAttributeGroupImpl.getName();
        QName compatName = compatName(qName, str);
        if (compatName.equals(name)) {
            return this._redefinedAttributeGroups.get(schemaAttributeGroupImpl);
        }
        SchemaAttributeGroupImpl schemaAttributeGroupImpl2 = (SchemaAttributeGroupImpl) this._attributeGroups.get(compatName);
        return schemaAttributeGroupImpl2 == null ? (SchemaAttributeGroupImpl) this._importingLoader.findAttributeGroup(compatName) : schemaAttributeGroupImpl2;
    }

    /* access modifiers changed from: package-private */
    public void addAttributeGroup(SchemaAttributeGroupImpl schemaAttributeGroupImpl, SchemaAttributeGroupImpl schemaAttributeGroupImpl2) {
        if (schemaAttributeGroupImpl != null) {
            QName name = schemaAttributeGroupImpl.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (schemaAttributeGroupImpl2 != null) {
                if (!this._redefinedAttributeGroups.containsKey(schemaAttributeGroupImpl2)) {
                    this._redefinedAttributeGroups.put(schemaAttributeGroupImpl2, schemaAttributeGroupImpl);
                    container.addRedefinedAttributeGroup(schemaAttributeGroupImpl.getRef());
                } else if (ignoreMdef(name)) {
                } else {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._redefinedAttributeGroups.get(schemaAttributeGroupImpl2).getSourceName()}, schemaAttributeGroupImpl.getParseObject());
                        return;
                    }
                    error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._redefinedAttributeGroups.get(schemaAttributeGroupImpl2).getSourceName()}, schemaAttributeGroupImpl.getParseObject());
                }
            } else if (!this._attributeGroups.containsKey(name)) {
                this._attributeGroups.put(schemaAttributeGroupImpl.getName(), schemaAttributeGroupImpl);
                addSpelling(schemaAttributeGroupImpl.getName(), schemaAttributeGroupImpl);
                container.addAttributeGroup(schemaAttributeGroupImpl.getRef());
            } else if (ignoreMdef(name)) {
            } else {
                if (this._mdefAll) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._attributeGroups.get(name).getSourceName()}, schemaAttributeGroupImpl.getParseObject());
                    return;
                }
                error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"attribute group", QNameHelper.pretty(name), this._attributeGroups.get(name).getSourceName()}, schemaAttributeGroupImpl.getParseObject());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaAttributeGroup[] attributeGroups() {
        return (SchemaAttributeGroup[]) this._attributeGroups.values().toArray(new SchemaAttributeGroup[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaAttributeGroup[] redefinedAttributeGroups() {
        return (SchemaAttributeGroup[]) this._redefinedAttributeGroups.values().toArray(new SchemaAttributeGroup[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaModelGroupImpl findModelGroup(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        SchemaModelGroupImpl schemaModelGroupImpl = (SchemaModelGroupImpl) this._modelGroups.get(compatName);
        boolean z = false;
        if (schemaModelGroupImpl == null && (schemaModelGroupImpl = (SchemaModelGroupImpl) this._importingLoader.findModelGroup(compatName)) != null) {
            z = true;
        }
        if (!z && str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return schemaModelGroupImpl;
    }

    /* access modifiers changed from: package-private */
    public SchemaModelGroupImpl findRedefinedModelGroup(QName qName, String str, SchemaModelGroupImpl schemaModelGroupImpl) {
        QName name = schemaModelGroupImpl.getName();
        QName compatName = compatName(qName, str);
        if (compatName.equals(name)) {
            return this._redefinedModelGroups.get(schemaModelGroupImpl);
        }
        SchemaModelGroupImpl schemaModelGroupImpl2 = (SchemaModelGroupImpl) this._modelGroups.get(compatName);
        return schemaModelGroupImpl2 == null ? (SchemaModelGroupImpl) this._importingLoader.findModelGroup(compatName) : schemaModelGroupImpl2;
    }

    /* access modifiers changed from: package-private */
    public void addModelGroup(SchemaModelGroupImpl schemaModelGroupImpl, SchemaModelGroupImpl schemaModelGroupImpl2) {
        if (schemaModelGroupImpl != null) {
            QName name = schemaModelGroupImpl.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (schemaModelGroupImpl2 != null) {
                if (!this._redefinedModelGroups.containsKey(schemaModelGroupImpl2)) {
                    this._redefinedModelGroups.put(schemaModelGroupImpl2, schemaModelGroupImpl);
                    container.addRedefinedModelGroup(schemaModelGroupImpl.getRef());
                } else if (ignoreMdef(name)) {
                } else {
                    if (this._mdefAll) {
                        warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._redefinedModelGroups.get(schemaModelGroupImpl2).getSourceName()}, schemaModelGroupImpl.getParseObject());
                        return;
                    }
                    error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._redefinedModelGroups.get(schemaModelGroupImpl2).getSourceName()}, schemaModelGroupImpl.getParseObject());
                }
            } else if (!this._modelGroups.containsKey(name)) {
                this._modelGroups.put(schemaModelGroupImpl.getName(), schemaModelGroupImpl);
                addSpelling(schemaModelGroupImpl.getName(), schemaModelGroupImpl);
                container.addModelGroup(schemaModelGroupImpl.getRef());
            } else if (ignoreMdef(name)) {
            } else {
                if (this._mdefAll) {
                    warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._modelGroups.get(name).getSourceName()}, schemaModelGroupImpl.getParseObject());
                    return;
                }
                error(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"model group", QNameHelper.pretty(name), this._modelGroups.get(name).getSourceName()}, schemaModelGroupImpl.getParseObject());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaModelGroup[] modelGroups() {
        return (SchemaModelGroup[]) this._modelGroups.values().toArray(new SchemaModelGroup[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaModelGroup[] redefinedModelGroups() {
        return (SchemaModelGroup[]) this._redefinedModelGroups.values().toArray(new SchemaModelGroup[0]);
    }

    /* access modifiers changed from: package-private */
    public SchemaIdentityConstraintImpl findIdConstraint(QName qName, String str, String str2) {
        QName compatName = compatName(qName, str);
        if (str2 != null) {
            registerDependency(str2, compatName.getNamespaceURI());
        }
        return (SchemaIdentityConstraintImpl) this._idConstraints.get(compatName);
    }

    /* access modifiers changed from: package-private */
    public void addIdConstraint(SchemaIdentityConstraintImpl schemaIdentityConstraintImpl) {
        if (schemaIdentityConstraintImpl != null) {
            QName name = schemaIdentityConstraintImpl.getName();
            SchemaContainer container = getContainer(name.getNamespaceURI());
            if (!this._idConstraints.containsKey(name)) {
                this._idConstraints.put(name, schemaIdentityConstraintImpl);
                addSpelling(schemaIdentityConstraintImpl.getName(), schemaIdentityConstraintImpl);
                container.addIdentityConstraint(schemaIdentityConstraintImpl.getRef());
            } else if (!ignoreMdef(name)) {
                warning(XmlErrorCodes.SCHEMA_PROPERTIES$DUPLICATE, new Object[]{"identity constraint", QNameHelper.pretty(name), this._idConstraints.get(name).getSourceName()}, schemaIdentityConstraintImpl.getParseObject());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SchemaIdentityConstraintImpl[] idConstraints() {
        return (SchemaIdentityConstraintImpl[]) this._idConstraints.values().toArray(new SchemaIdentityConstraintImpl[0]);
    }

    /* access modifiers changed from: package-private */
    public void addAnnotation(SchemaAnnotationImpl schemaAnnotationImpl, String str) {
        if (schemaAnnotationImpl != null) {
            SchemaContainer container = getContainer(str);
            this._annotations.add(schemaAnnotationImpl);
            container.addAnnotation(schemaAnnotationImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public List<SchemaAnnotation> annotations() {
        return this._annotations;
    }

    /* access modifiers changed from: package-private */
    public boolean isProcessing(SchemaComponent schemaComponent) {
        return this._processingGroups.contains(schemaComponent);
    }

    /* access modifiers changed from: package-private */
    public void startProcessing(SchemaComponent schemaComponent) {
        this._processingGroups.add(schemaComponent);
    }

    /* access modifiers changed from: package-private */
    public void finishProcessing(SchemaComponent schemaComponent) {
        this._processingGroups.remove(schemaComponent);
    }

    /* access modifiers changed from: package-private */
    public SchemaComponent[] getCurrentProcessing() {
        return (SchemaComponent[]) this._processingGroups.toArray(new SchemaComponent[0]);
    }

    /* access modifiers changed from: package-private */
    public Map<String, SchemaType> typesByClassname() {
        return Collections.unmodifiableMap(this._typesByClassname);
    }

    /* access modifiers changed from: package-private */
    public void addClassname(String str, SchemaType schemaType) {
        this._typesByClassname.put(str, schemaType);
    }

    private static final class StscStack {
        StscState current;
        List<StscState> stack;

        private StscStack() {
            this.stack = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public final StscState push() {
            this.stack.add(this.current);
            StscState stscState = new StscState();
            this.current = stscState;
            return stscState;
        }

        /* access modifiers changed from: package-private */
        public final void pop() {
            List<StscState> list = this.stack;
            this.current = list.get(list.size() - 1);
            List<StscState> list2 = this.stack;
            list2.remove(list2.size() - 1);
        }
    }

    public static void clearThreadLocals() {
        tl_stscStack.remove();
    }

    public static StscState start() {
        ThreadLocal<StscStack> threadLocal = tl_stscStack;
        StscStack stscStack = threadLocal.get();
        if (stscStack == null) {
            stscStack = new StscStack();
            threadLocal.set(stscStack);
        }
        return stscStack.push();
    }

    public static StscState get() {
        return tl_stscStack.get().current;
    }

    public static void end() {
        ThreadLocal<StscStack> threadLocal = tl_stscStack;
        StscStack stscStack = threadLocal.get();
        stscStack.pop();
        if (stscStack.stack.size() == 0) {
            threadLocal.remove();
        }
    }

    static XmlValueRef build_wsstring(int i) {
        if (i == 1) {
            return XMLSTR_PRESERVE;
        }
        if (i == 2) {
            return XMLSTR_REPLACE;
        }
        if (i != 3) {
            return null;
        }
        return XMLSTR_COLLAPSE;
    }

    static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl xmlStringImpl = new XmlStringImpl();
            xmlStringImpl.setStringValue(str);
            xmlStringImpl.setImmutable();
            return new XmlValueRef(xmlStringImpl);
        } catch (XmlValueOutOfRangeException unused) {
            return null;
        }
    }

    public void notFoundError(QName qName, int i, XmlObject xmlObject, boolean z) {
        String str;
        String str2;
        String str3;
        QName name;
        String str4;
        int i2 = i;
        XmlObject xmlObject2 = xmlObject;
        String pretty = QNameHelper.pretty(qName);
        int i3 = 1;
        if (z) {
            this._recoveredErrors++;
        }
        String str5 = "attribute group";
        String str6 = i2 != 0 ? i2 != 1 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? "definition" : "model group" : "identity constraint" : str5 : "attribute" : "element" : "type";
        SchemaComponent findSpelling = findSpelling(qName);
        if (findSpelling == null || (name = findSpelling.getName()) == null) {
            str3 = null;
            str2 = null;
            str = null;
        } else {
            int componentType = findSpelling.getComponentType();
            if (componentType == 0) {
                str4 = findSpelling.getSourceName();
                str5 = "type";
            } else if (componentType == 1) {
                str4 = findSpelling.getSourceName();
                str5 = "element";
            } else if (componentType != 3) {
                if (componentType != 4) {
                    if (componentType != 6) {
                        str4 = null;
                        str5 = null;
                    } else {
                        str5 = "model group";
                    }
                }
                str4 = null;
            } else {
                str4 = findSpelling.getSourceName();
                str5 = "attribute";
            }
            if (str4 != null) {
                str4 = str4.substring(str4.lastIndexOf(47) + 1);
            }
            if (!name.equals(qName)) {
                String str7 = str5;
                str2 = str4;
                str3 = QNameHelper.pretty(name);
                str = str7;
            } else {
                str = str5;
                str2 = str4;
                str3 = null;
            }
        }
        if (str == null) {
            error(XmlErrorCodes.SCHEMA_QNAME_RESOLVE, new Object[]{str6, pretty}, xmlObject2);
            return;
        }
        Object[] objArr = new Object[7];
        objArr[0] = str6;
        objArr[1] = pretty;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(str3 == null ? 0 : 1);
        objArr[4] = str3;
        if (str2 == null) {
            i3 = 0;
        }
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = str2;
        error(XmlErrorCodes.SCHEMA_QNAME_RESOLVE$HELP, objArr, xmlObject2);
    }

    public String sourceNameForUri(String str) {
        return this._sourceForUri.get(str);
    }

    public Map<String, String> sourceCopyMap() {
        return Collections.unmodifiableMap(this._sourceForUri);
    }

    public void setBaseUri(URI uri) {
        this._baseURI = uri;
    }

    public String relativize(String str) {
        return relativize(str, false);
    }

    public String computeSavedFilename(String str) {
        return relativize(str, true);
    }

    private String relativize(String str, boolean z) {
        String str2;
        if (str == null) {
            return null;
        }
        if (str.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            str = PROJECT_URL_PREFIX + str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        } else {
            int indexOf = str.indexOf(58);
            if (indexOf <= 1 || !str.substring(0, indexOf).matches("^\\w+$")) {
                str = "project://local/" + str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
            }
        }
        URI uri = this._baseURI;
        if (uri != null) {
            try {
                URI relativize = uri.relativize(new URI(str));
                if (!relativize.isAbsolute()) {
                    return relativize.toString();
                }
                str = relativize.toString();
            } catch (URISyntaxException unused) {
            }
        }
        if (!z) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf == -1) {
            str2 = "";
        } else {
            str2 = str.substring(0, lastIndexOf);
        }
        String hexsafe = QNameHelper.hexsafe(str2);
        int i = lastIndexOf + 1;
        int indexOf2 = str.indexOf(63, i);
        if (indexOf2 == -1) {
            return hexsafe + PackagingURIHelper.FORWARD_SLASH_STRING + str.substring(i);
        }
        String hexsafe2 = QNameHelper.hexsafe(str.substring(indexOf2));
        if (hexsafe2.startsWith(QNameHelper.URI_SHA1_PREFIX)) {
            return hexsafe + PackagingURIHelper.FORWARD_SLASH_STRING + str.substring(i, indexOf2);
        }
        return hexsafe + PackagingURIHelper.FORWARD_SLASH_STRING + str.substring(i, indexOf2) + hexsafe2;
    }

    public void addSourceUri(String str, String str2) {
        if (str != null) {
            if (str2 == null) {
                str2 = computeSavedFilename(str);
            }
            this._sourceForUri.put(str, str2);
        }
    }

    public Collection<XmlError> getErrorListener() {
        return this._errorListener;
    }

    public SchemaTypeLoader getS4SLoader() {
        return this._s4sloader;
    }

    public File getSchemasDir() {
        return this._schemasDir;
    }

    public void setSchemasDir(File file) {
        this._schemasDir = file;
    }
}
