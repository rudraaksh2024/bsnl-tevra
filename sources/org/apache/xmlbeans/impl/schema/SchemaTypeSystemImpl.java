package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.util.FilerImpl;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.util.LongUTFDataInputStream;
import org.apache.xmlbeans.impl.util.LongUTFDataOutputStream;

public class SchemaTypeSystemImpl extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DATA_BABE = -629491010;
    private static final SchemaAttributeGroup[] EMPTY_AG_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaAnnotation[] EMPTY_ANN_ARRAY = new SchemaAnnotation[0];
    private static final SchemaGlobalAttribute[] EMPTY_GA_ARRAY = new SchemaGlobalAttribute[0];
    private static final SchemaGlobalElement[] EMPTY_GE_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaIdentityConstraint[] EMPTY_IC_ARRAY = new SchemaIdentityConstraint[0];
    private static final SchemaModelGroup[] EMPTY_MG_ARRAY = new SchemaModelGroup[0];
    private static final SchemaType[] EMPTY_ST_ARRAY = new SchemaType[0];
    public static final int FIELD_GLOBAL = 1;
    public static final int FIELD_LOCALATTR = 2;
    public static final int FIELD_LOCALELT = 3;
    public static final int FIELD_NONE = 0;
    public static final int FILETYPE_SCHEMAATTRIBUTE = 4;
    public static final int FILETYPE_SCHEMAATTRIBUTEGROUP = 7;
    public static final int FILETYPE_SCHEMAELEMENT = 3;
    public static final int FILETYPE_SCHEMAIDENTITYCONSTRAINT = 8;
    public static final int FILETYPE_SCHEMAINDEX = 1;
    public static final int FILETYPE_SCHEMAMODELGROUP = 6;
    public static final int FILETYPE_SCHEMAPOINTER = 5;
    public static final int FILETYPE_SCHEMATYPE = 2;
    static final int FLAG_ABSTRACT = 262144;
    static final int FLAG_ATTRIBUTE_TYPE = 524288;
    static final int FLAG_BLOCK_EXT = 4096;
    static final int FLAG_BLOCK_REST = 8192;
    static final int FLAG_BOUNDED = 8;
    static final int FLAG_COMPILED = 2048;
    static final int FLAG_DOCUMENT_TYPE = 2;
    static final int FLAG_FINAL_EXT = 16384;
    static final int FLAG_FINAL_LIST = 131072;
    static final int FLAG_FINAL_REST = 32768;
    static final int FLAG_FINAL_UNION = 65536;
    static final int FLAG_FINITE = 16;
    static final int FLAG_HAS_PATTERN = 256;
    static final int FLAG_NUMERIC = 32;
    static final int FLAG_ORDERED = 4;
    static final int FLAG_ORDER_SENSITIVE = 512;
    public static final int FLAG_PART_ABSTRACT = 128;
    public static final int FLAG_PART_BLOCKEXT = 16;
    public static final int FLAG_PART_BLOCKREST = 32;
    public static final int FLAG_PART_BLOCKSUBST = 64;
    public static final int FLAG_PART_FINALEXT = 256;
    public static final int FLAG_PART_FINALREST = 512;
    public static final int FLAG_PART_FIXED = 4;
    public static final int FLAG_PART_NILLABLE = 8;
    public static final int FLAG_PART_SKIPPABLE = 1;
    public static final int FLAG_PROP_ISATTR = 1;
    public static final int FLAG_PROP_JAVAARRAY = 8;
    public static final int FLAG_PROP_JAVAOPTIONAL = 4;
    public static final int FLAG_PROP_JAVASINGLETON = 2;
    static final int FLAG_SIMPLE_TYPE = 1;
    static final int FLAG_STRINGENUM = 64;
    static final int FLAG_TOTAL_ORDER = 1024;
    static final int FLAG_UNION_OF_LISTS = 128;
    public static final int MAJOR_VERSION = 2;
    public static String METADATA_PACKAGE_GEN = "org/apache/xmlbeans/metadata";
    public static final int MINOR_VERSION = 24;
    public static final int RELEASE_NUMBER = 0;
    static final byte[] SINGLE_ZERO_BYTE = {0};
    private static final byte[] _mask = new byte[16];
    private static Random _random;
    private static final Pattern packPat = Pattern.compile("^(.+)(\\.[^.]+){2}$");
    private boolean _allNonGroupHandlesResolved = false;
    private List<SchemaAnnotation> _annotations;
    private Map<QName, SchemaComponent.Ref> _attributeGroups;
    private Map<QName, SchemaComponent.Ref> _attributeTypes;
    private ClassLoader _classloader;
    private Map<String, SchemaContainer> _containers = new HashMap();
    private SchemaDependencies _deps;
    private Map<QName, SchemaComponent.Ref> _documentTypes;
    private Filer _filer;
    private Map<QName, SchemaComponent.Ref> _globalAttributes;
    private Map<QName, SchemaComponent.Ref> _globalElements;
    private Map<QName, SchemaComponent.Ref> _globalTypes;
    private Map<QName, SchemaComponent.Ref> _identityConstraints = Collections.emptyMap();
    private boolean _incomplete = false;
    SchemaTypeLoader _linker;
    private SchemaTypePool _localHandles;
    private Map<QName, SchemaComponent.Ref> _modelGroups;
    private final String _name;
    private Set<String> _namespaces;
    private List<SchemaComponent.Ref> _redefinedAttributeGroups;
    private List<SchemaComponent.Ref> _redefinedGlobalTypes;
    private List<SchemaComponent.Ref> _redefinedModelGroups;
    private final Map<String, SchemaComponent> _resolvedHandles = new HashMap();
    private ResourceLoader _resourceLoader;
    private Map<String, SchemaComponent.Ref> _typeRefsByClassname = new HashMap();

    public static /* synthetic */ LinkedHashMap $r8$lambda$Rr5wHpoNM5boD4b5K8greFBFLF8() {
        return new LinkedHashMap();
    }

    private void assertContainersSynchronized() {
    }

    static /* synthetic */ SchemaComponent.Ref lambda$buildComponentRefMap$0(SchemaComponent.Ref ref, SchemaComponent.Ref ref2) {
        return ref2;
    }

    static /* synthetic */ Object lambda$refHelper$2(SchemaComponent.Ref ref) {
        return ref;
    }

    /* access modifiers changed from: package-private */
    public final SchemaTypeSystemImpl getTypeSystem() {
        return this;
    }

    static String nameToPathString(String str) {
        String replace = str.replace('.', '/');
        return (replace.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING) || replace.length() <= 0) ? replace : replace + PackagingURIHelper.FORWARD_SLASH_STRING;
    }

    protected SchemaTypeSystemImpl() {
        String name = getClass().getName();
        String substring = name.substring(0, name.lastIndexOf(46));
        this._name = substring;
        XBeanDebug.LOG.atTrace().log("Loading type system {}", (Object) substring);
        this._classloader = getClass().getClassLoader();
        this._linker = this;
        this._resourceLoader = new ClassLoaderResourceLoader(this._classloader);
        try {
            initFromHeader();
            XBeanDebug.LOG.atTrace().log("Finished loading type system {}", (Object) substring);
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    public SchemaTypeSystemImpl(Class<?> cls) {
        String name = cls.getName();
        String substring = name.substring(0, name.lastIndexOf(46));
        this._name = substring;
        XBeanDebug.LOG.atTrace().log("Loading type system {}", (Object) substring);
        ClassLoader classLoader = cls.getClassLoader();
        this._classloader = classLoader;
        this._linker = SchemaTypeLoaderImpl.build((SchemaTypeLoader[]) null, (ResourceLoader) null, classLoader, getMetadataPath());
        this._resourceLoader = new ClassLoaderResourceLoader(this._classloader);
        try {
            initFromHeader();
            XBeanDebug.LOG.atTrace().log("Finished loading type system {}", (Object) substring);
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    public static SchemaTypeSystemImpl forName(String str, ClassLoader classLoader) {
        try {
            return (SchemaTypeSystemImpl) Class.forName(str + ".TypeSystemHolder", true, classLoader).getField("typeSystem").get((Object) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    public SchemaTypeSystemImpl(ResourceLoader resourceLoader, String str, SchemaTypeLoader schemaTypeLoader) {
        this._name = str;
        this._linker = schemaTypeLoader;
        this._resourceLoader = resourceLoader;
        try {
            initFromHeader();
        } catch (Error | RuntimeException e) {
            XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initFromHeader() {
        /*
            r7 = this;
            org.apache.logging.log4j.Logger r0 = org.apache.xmlbeans.impl.common.XBeanDebug.LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atTrace()
            java.lang.String r1 = "Reading unresolved handles for type system {}"
            java.lang.String r2 = r7._name
            r0.log((java.lang.String) r1, (java.lang.Object) r2)
            r0 = 0
            org.apache.xmlbeans.impl.schema.XsbReader r1 = new org.apache.xmlbeans.impl.schema.XsbReader     // Catch:{ all -> 0x00a7 }
            org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl r2 = r7.getTypeSystem()     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = "index"
            r4 = 1
            r1.<init>(r2, r3, r4)     // Catch:{ all -> 0x00a7 }
            org.apache.xmlbeans.impl.schema.SchemaTypePool r0 = new org.apache.xmlbeans.impl.schema.SchemaTypePool     // Catch:{ all -> 0x00a4 }
            org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl r2 = r7.getTypeSystem()     // Catch:{ all -> 0x00a4 }
            r0.<init>(r2)     // Catch:{ all -> 0x00a4 }
            r7._localHandles = r0     // Catch:{ all -> 0x00a4 }
            r0.readHandlePool(r1)     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._globalElements = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._globalAttributes = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._modelGroups = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._attributeGroups = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._identityConstraints = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._globalTypes = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._documentTypes = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readQNameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._attributeTypes = r0     // Catch:{ all -> 0x00a4 }
            java.util.Map r0 = r1.readClassnameRefMap()     // Catch:{ all -> 0x00a4 }
            r7._typeRefsByClassname = r0     // Catch:{ all -> 0x00a4 }
            java.util.Set r0 = r1.readNamespaces()     // Catch:{ all -> 0x00a4 }
            r7._namespaces = r0     // Catch:{ all -> 0x00a4 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00a4 }
            r0.<init>()     // Catch:{ all -> 0x00a4 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00a4 }
            r2.<init>()     // Catch:{ all -> 0x00a4 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x00a4 }
            r3.<init>()     // Catch:{ all -> 0x00a4 }
            r4 = 15
            r5 = 0
            r6 = 2
            boolean r4 = r1.atLeast(r6, r4, r5)     // Catch:{ all -> 0x00a4 }
            if (r4 == 0) goto L_0x008f
            java.util.List r4 = r1.readQNameRefMapAsList(r0)     // Catch:{ all -> 0x00a4 }
            r7._redefinedGlobalTypes = r4     // Catch:{ all -> 0x00a4 }
            java.util.List r4 = r1.readQNameRefMapAsList(r2)     // Catch:{ all -> 0x00a4 }
            r7._redefinedModelGroups = r4     // Catch:{ all -> 0x00a4 }
            java.util.List r4 = r1.readQNameRefMapAsList(r3)     // Catch:{ all -> 0x00a4 }
            r7._redefinedAttributeGroups = r4     // Catch:{ all -> 0x00a4 }
        L_0x008f:
            r4 = 19
            boolean r4 = r1.atLeast(r6, r4, r5)     // Catch:{ all -> 0x00a4 }
            if (r4 == 0) goto L_0x009d
            java.util.List r4 = r1.readAnnotations()     // Catch:{ all -> 0x00a4 }
            r7._annotations = r4     // Catch:{ all -> 0x00a4 }
        L_0x009d:
            r7.buildContainers(r0, r2, r3)     // Catch:{ all -> 0x00a4 }
            r1.readEnd()
            return
        L_0x00a4:
            r7 = move-exception
            r0 = r1
            goto L_0x00a8
        L_0x00a7:
            r7 = move-exception
        L_0x00a8:
            if (r0 == 0) goto L_0x00ad
            r0.readEnd()
        L_0x00ad:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.initFromHeader():void");
    }

    /* access modifiers changed from: package-private */
    public void saveIndex() {
        XsbReader xsbReader = new XsbReader(getTypeSystem(), "index");
        xsbReader.writeIndexData();
        xsbReader.writeRealHeader("index", 1);
        xsbReader.writeIndexData();
        xsbReader.writeEnd();
    }

    /* access modifiers changed from: package-private */
    public void savePointers() {
        savePointersForComponents(globalElements(), getMetadataPath() + "/element/");
        savePointersForComponents(globalAttributes(), getMetadataPath() + "/attribute/");
        savePointersForComponents(modelGroups(), getMetadataPath() + "/modelgroup/");
        savePointersForComponents(attributeGroups(), getMetadataPath() + "/attributegroup/");
        savePointersForComponents(globalTypes(), getMetadataPath() + "/type/");
        savePointersForComponents(identityConstraints(), getMetadataPath() + "/identityconstraint/");
        savePointersForNamespaces(this._namespaces, getMetadataPath() + "/namespace/");
        savePointersForClassnames(this._typeRefsByClassname.keySet(), getMetadataPath() + "/javaname/");
        savePointersForComponents(redefinedModelGroups(), getMetadataPath() + "/redefinedmodelgroup/");
        savePointersForComponents(redefinedAttributeGroups(), getMetadataPath() + "/redefinedattributegroup/");
        savePointersForComponents(redefinedGlobalTypes(), getMetadataPath() + "/redefinedtype/");
    }

    /* access modifiers changed from: package-private */
    public void savePointersForComponents(SchemaComponent[] schemaComponentArr, String str) {
        int length = schemaComponentArr.length;
        for (int i = 0; i < length; i++) {
            savePointerFile(str + QNameHelper.hexsafedir(schemaComponentArr[i].getName()), this._name);
        }
    }

    /* access modifiers changed from: package-private */
    public void savePointersForClassnames(Set<String> set, String str) {
        for (String replace : set) {
            savePointerFile(str + replace.replace('.', '/'), this._name);
        }
    }

    /* access modifiers changed from: package-private */
    public void savePointersForNamespaces(Set<String> set, String str) {
        for (String qName : set) {
            savePointerFile(str + QNameHelper.hexsafedir(new QName(qName, Sax2Dom.XMLNS_PREFIX)), this._name);
        }
    }

    /* access modifiers changed from: package-private */
    public void savePointerFile(String str, String str2) {
        XsbReader xsbReader = new XsbReader(getTypeSystem(), str);
        xsbReader.writeString(str2);
        xsbReader.writeRealHeader(str, 5);
        xsbReader.writeString(str2);
        xsbReader.writeEnd();
    }

    private Map<String, SchemaComponent.Ref> buildTypeRefsByClassname(Map<String, SchemaType> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String next : map.keySet()) {
            linkedHashMap.put(next, map.get(next).getRef());
        }
        return linkedHashMap;
    }

    private static Map<QName, SchemaComponent.Ref> buildComponentRefMap(SchemaComponent[] schemaComponentArr) {
        return buildComponentRefMap((List<? extends SchemaComponent>) Arrays.asList(schemaComponentArr));
    }

    /* access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildComponentRefMap(List<? extends SchemaComponent> list) {
        return (Map) list.stream().collect(Collectors.toMap(new SchemaTypeSystemImpl$$ExternalSyntheticLambda39(), new SchemaTypeSystemImpl$$ExternalSyntheticLambda45(), new SchemaTypeSystemImpl$$ExternalSyntheticLambda46(), new SchemaTypeSystemImpl$$ExternalSyntheticLambda47()));
    }

    private static List<SchemaComponent.Ref> buildComponentRefList(SchemaComponent[] schemaComponentArr) {
        return buildComponentRefList((List<? extends SchemaComponent>) Arrays.asList(schemaComponentArr));
    }

    /* access modifiers changed from: private */
    public static List<SchemaComponent.Ref> buildComponentRefList(List<? extends SchemaComponent> list) {
        return (List) list.stream().map(new SchemaTypeSystemImpl$$ExternalSyntheticLambda45()).collect(Collectors.toList());
    }

    private static Map<QName, SchemaComponent.Ref> buildDocumentMap(SchemaType[] schemaTypeArr) {
        return buildDocumentMap((List<? extends SchemaComponent>) Arrays.asList(schemaTypeArr));
    }

    /* access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildDocumentMap(List<? extends SchemaComponent> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SchemaComponent schemaComponent : list) {
            SchemaType schemaType = (SchemaType) schemaComponent;
            linkedHashMap.put(schemaType.getDocumentElementName(), schemaType.getRef());
        }
        return linkedHashMap;
    }

    private static Map<QName, SchemaComponent.Ref> buildAttributeTypeMap(SchemaType[] schemaTypeArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SchemaType schemaType : schemaTypeArr) {
            linkedHashMap.put(schemaType.getAttributeTypeAttributeName(), schemaType.getRef());
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    public static Map<QName, SchemaComponent.Ref> buildAttributeTypeMap(List<? extends SchemaComponent> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SchemaComponent schemaComponent : list) {
            SchemaType schemaType = (SchemaType) schemaComponent;
            linkedHashMap.put(schemaType.getAttributeTypeAttributeName(), schemaType.getRef());
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: package-private */
    public SchemaContainer getContainer(String str) {
        return this._containers.get(str);
    }

    private void addContainer(String str) {
        SchemaContainer schemaContainer = new SchemaContainer(str);
        schemaContainer.setTypeSystem(this);
        this._containers.put(str, schemaContainer);
    }

    /* access modifiers changed from: package-private */
    public SchemaContainer getContainerNonNull(String str) {
        SchemaContainer container = getContainer(str);
        if (container != null) {
            return container;
        }
        addContainer(str);
        return getContainer(str);
    }

    private <T extends SchemaComponent.Ref> void buildContainersHelper(Map<QName, SchemaComponent.Ref> map, BiConsumer<SchemaContainer, T> biConsumer) {
        map.forEach(new SchemaTypeSystemImpl$$ExternalSyntheticLambda44(this, biConsumer));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$buildContainersHelper$1$org-apache-xmlbeans-impl-schema-SchemaTypeSystemImpl  reason: not valid java name */
    public /* synthetic */ void m2321lambda$buildContainersHelper$1$orgapachexmlbeansimplschemaSchemaTypeSystemImpl(BiConsumer biConsumer, QName qName, SchemaComponent.Ref ref) {
        biConsumer.accept(getContainerNonNull(qName.getNamespaceURI()), ref);
    }

    private <T extends SchemaComponent.Ref> void buildContainersHelper(List<SchemaComponent.Ref> list, List<QName> list2, BiConsumer<SchemaContainer, T> biConsumer) {
        Iterator<QName> it = list2.iterator();
        for (SchemaComponent.Ref accept : list) {
            biConsumer.accept(getContainerNonNull(it.next().getNamespaceURI()), accept);
        }
    }

    private void buildContainers(List<QName> list, List<QName> list2, List<QName> list3) {
        buildContainersHelper(this._globalElements, new SchemaTypeSystemImpl$$ExternalSyntheticLambda6());
        buildContainersHelper(this._globalAttributes, new SchemaTypeSystemImpl$$ExternalSyntheticLambda9());
        buildContainersHelper(this._modelGroups, new SchemaTypeSystemImpl$$ExternalSyntheticLambda10());
        buildContainersHelper(this._attributeGroups, new SchemaTypeSystemImpl$$ExternalSyntheticLambda11());
        buildContainersHelper(this._identityConstraints, new SchemaTypeSystemImpl$$ExternalSyntheticLambda12());
        buildContainersHelper(this._globalTypes, new SchemaTypeSystemImpl$$ExternalSyntheticLambda13());
        buildContainersHelper(this._attributeTypes, new SchemaTypeSystemImpl$$ExternalSyntheticLambda14());
        List<SchemaComponent.Ref> list4 = this._redefinedGlobalTypes;
        if (!(list4 == null || this._redefinedModelGroups == null || this._redefinedAttributeGroups == null)) {
            buildContainersHelper(list4, list, new SchemaTypeSystemImpl$$ExternalSyntheticLambda15());
            buildContainersHelper(this._redefinedModelGroups, list2, new SchemaTypeSystemImpl$$ExternalSyntheticLambda16());
            buildContainersHelper(this._redefinedAttributeGroups, list3, new SchemaTypeSystemImpl$$ExternalSyntheticLambda17());
        }
        List<SchemaAnnotation> list5 = this._annotations;
        if (list5 != null && !list5.isEmpty()) {
            List<SchemaAnnotation> list6 = this._annotations;
            SchemaContainer containerNonNull = getContainerNonNull("");
            containerNonNull.getClass();
            list6.forEach(new SchemaTypeSystemImpl$$ExternalSyntheticLambda7(containerNonNull));
        }
        this._containers.values().forEach(new SchemaTypeSystemImpl$$ExternalSyntheticLambda8());
    }

    private void fixupContainers() {
        for (SchemaContainer next : this._containers.values()) {
            next.setTypeSystem(this);
            next.setImmutable();
        }
    }

    private void assertContainersHelper(Map<QName, SchemaComponent.Ref> map, Function<SchemaContainer, List<? extends SchemaComponent>> function, Function<List<? extends SchemaComponent>, ? extends Map<QName, SchemaComponent.Ref>> function2) {
        Stream<R> map2 = this._containers.values().stream().map(function);
        Function<List<? extends SchemaComponent>, ? extends Map<QName, SchemaComponent.Ref>> function3 = function2;
        if (function2 == null) {
            new SchemaTypeSystemImpl$$ExternalSyntheticLambda48
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR  (r3v2 ? I:org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda48) =  call: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda48.<init>():void type: CONSTRUCTOR in method: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.assertContainersHelper(java.util.Map, java.util.function.Function, java.util.function.Function):void, dex: classes4.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v2 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                this = this;
                java.util.Map<java.lang.String, org.apache.xmlbeans.impl.schema.SchemaContainer> r0 = r0._containers
                java.util.Collection r0 = r0.values()
                java.util.stream.Stream r0 = r0.stream()
                java.util.stream.Stream r0 = r0.map(r2)
                if (r3 != 0) goto L_0x0015
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda48 r3 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda48
                r3.<init>()
            L_0x0015:
                java.util.stream.Stream r0 = r0.map(r3)
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda49 r1 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda49
                r1.<init>()
                java.util.stream.Stream r0 = r0.map(r1)
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda1 r1 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda1
                r1.<init>()
                java.util.stream.Stream r0 = r0.flatMap(r1)
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda2 r1 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda2
                r1.<init>()
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda3 r2 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda3
                r2.<init>()
                java.util.stream.Collector r1 = java.util.stream.Collectors.toMap(r1, r2)
                java.lang.Object r0 = r0.collect(r1)
                java.util.Map r0 = (java.util.Map) r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.assertContainersHelper(java.util.Map, java.util.function.Function, java.util.function.Function):void");
        }

        private void assertContainersHelper(List<? extends SchemaComponent.Ref> list, Function<SchemaContainer, List<? extends SchemaComponent>> function) {
            Set set = (Set) this._containers.values().stream().map(function).map(new SchemaTypeSystemImpl$$ExternalSyntheticLambda0()).flatMap(new SchemaDependencies$$ExternalSyntheticLambda8()).collect(Collectors.toSet());
        }

        private static synchronized void nextBytes(byte[] bArr) {
            synchronized (SchemaTypeSystemImpl.class) {
                if (_random == null) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        LongUTFDataOutputStream longUTFDataOutputStream = new LongUTFDataOutputStream(byteArrayOutputStream);
                        longUTFDataOutputStream.writeInt(System.identityHashCode(SchemaTypeSystemImpl.class));
                        String[] strArr = {"user.name", "user.dir", "user.timezone", "user.country", "java.class.path", "java.home", "java.vendor", "java.version", "os.version"};
                        for (int i = 0; i < 9; i++) {
                            String property = SystemProperties.getProperty(strArr[i]);
                            if (property != null) {
                                longUTFDataOutputStream.writeUTF(property);
                                longUTFDataOutputStream.writeInt(System.identityHashCode(property));
                            }
                        }
                        longUTFDataOutputStream.writeLong(Runtime.getRuntime().freeMemory());
                        longUTFDataOutputStream.close();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        for (int i2 = 0; i2 < byteArray.length; i2++) {
                            byte[] bArr2 = _mask;
                            int length = i2 % bArr2.length;
                            byte b = (byte) (bArr2[length] * ParenthesisPtg.sid);
                            bArr2[length] = b;
                            bArr2[length] = (byte) (b + i2);
                        }
                    } catch (IOException e) {
                        XBeanDebug.LOG.atDebug().withThrowable(e).log(e.getMessage());
                    }
                    _random = new Random(System.currentTimeMillis());
                }
                _random.nextBytes(bArr);
                for (int i3 = 0; i3 < bArr.length; i3++) {
                    byte[] bArr3 = _mask;
                    bArr[i3] = (byte) (bArr3[bArr3.length & i3] ^ bArr[i3]);
                }
            }
        }

        public SchemaTypeSystemImpl(String str) {
            if (str == null) {
                byte[] bArr = new byte[16];
                nextBytes(bArr);
                str = "s".concat(new String(HexBin.encode(bArr), StandardCharsets.ISO_8859_1));
            }
            this._name = METADATA_PACKAGE_GEN.replace('/', '.') + ".system." + str;
            this._classloader = null;
        }

        public void loadFromStscState(StscState stscState) {
            this._localHandles = new SchemaTypePool(getTypeSystem());
            this._globalElements = buildComponentRefMap((SchemaComponent[]) stscState.globalElements());
            this._globalAttributes = buildComponentRefMap((SchemaComponent[]) stscState.globalAttributes());
            this._modelGroups = buildComponentRefMap((SchemaComponent[]) stscState.modelGroups());
            this._redefinedModelGroups = buildComponentRefList((SchemaComponent[]) stscState.redefinedModelGroups());
            this._attributeGroups = buildComponentRefMap((SchemaComponent[]) stscState.attributeGroups());
            this._redefinedAttributeGroups = buildComponentRefList((SchemaComponent[]) stscState.redefinedAttributeGroups());
            this._globalTypes = buildComponentRefMap((SchemaComponent[]) stscState.globalTypes());
            this._redefinedGlobalTypes = buildComponentRefList((SchemaComponent[]) stscState.redefinedGlobalTypes());
            this._documentTypes = buildDocumentMap(stscState.documentTypes());
            this._attributeTypes = buildAttributeTypeMap(stscState.attributeTypes());
            this._typeRefsByClassname = buildTypeRefsByClassname(stscState.typesByClassname());
            this._identityConstraints = buildComponentRefMap((SchemaComponent[]) stscState.idConstraints());
            this._annotations = stscState.annotations();
            this._namespaces = new HashSet(Arrays.asList(stscState.getNamespaces()));
            this._containers = stscState.getContainerMap();
            fixupContainers();
            assertContainersSynchronized();
            setDependencies(stscState.getDependencies());
        }

        /* access modifiers changed from: package-private */
        public void setDependencies(SchemaDependencies schemaDependencies) {
            this._deps = schemaDependencies;
        }

        /* access modifiers changed from: package-private */
        public SchemaDependencies getDependencies() {
            return this._deps;
        }

        public boolean isIncomplete() {
            return this._incomplete;
        }

        /* access modifiers changed from: package-private */
        public void setIncomplete(boolean z) {
            this._incomplete = z;
        }

        static class StringPool {
            private final String _handle;
            private final String _name;
            private final List<String> intsToStrings;
            private final Map<String, Integer> stringsToInts = new HashMap();

            StringPool(String str, String str2) {
                ArrayList arrayList = new ArrayList();
                this.intsToStrings = arrayList;
                this._handle = str;
                this._name = str2;
                arrayList.add((Object) null);
            }

            /* access modifiers changed from: package-private */
            public int codeForString(String str) {
                if (str == null) {
                    return 0;
                }
                Integer num = this.stringsToInts.get(str);
                if (num == null) {
                    num = Integer.valueOf(this.intsToStrings.size());
                    this.intsToStrings.add(str);
                    this.stringsToInts.put(str, num);
                }
                return num.intValue();
            }

            /* access modifiers changed from: package-private */
            public String stringForCode(int i) {
                if (i == 0) {
                    return null;
                }
                return this.intsToStrings.get(i);
            }

            /* access modifiers changed from: package-private */
            public void writeTo(LongUTFDataOutputStream longUTFDataOutputStream) {
                try {
                    longUTFDataOutputStream.writeShortOrInt(this.intsToStrings.size());
                    boolean z = false;
                    for (String next : this.intsToStrings) {
                        if (z) {
                            longUTFDataOutputStream.writeLongUTF(next);
                        }
                        z = true;
                    }
                } catch (IOException e) {
                    IOException iOException = e;
                    throw new SchemaTypeLoaderException(iOException.getMessage(), this._name, this._handle, 9, iOException);
                }
            }

            /* access modifiers changed from: package-private */
            public void readFrom(LongUTFDataInputStream longUTFDataInputStream) {
                int i = 1;
                if (this.intsToStrings.size() == 1 && this.stringsToInts.size() == 0) {
                    try {
                        int readUnsignedShortOrInt = longUTFDataInputStream.readUnsignedShortOrInt();
                        while (i < readUnsignedShortOrInt) {
                            if (codeForString(longUTFDataInputStream.readLongUTF().intern()) == i) {
                                i++;
                            } else {
                                throw new IllegalStateException();
                            }
                        }
                    } catch (IOException e) {
                        IOException iOException = e;
                        throw new SchemaTypeLoaderException(iOException.getMessage() == null ? iOException.getMessage() : "IO Exception", this._name, this._handle, 9, iOException);
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
        }

        public void saveToDirectory(File file) {
            save(new FilerImpl(file, (File) null, (Repackager) null, false, false));
        }

        public void save(Filer filer) {
            if (this._incomplete) {
                throw new IllegalStateException("Incomplete SchemaTypeSystems cannot be saved.");
            } else if (filer != null) {
                this._filer = filer;
                this._localHandles.startWriteMode();
                saveTypesRecursively(globalTypes());
                saveTypesRecursively(documentTypes());
                saveTypesRecursively(attributeTypes());
                saveGlobalElements(globalElements());
                saveGlobalAttributes(globalAttributes());
                saveModelGroups(modelGroups());
                saveAttributeGroups(attributeGroups());
                saveIdentityConstraints(identityConstraints());
                saveTypesRecursively(redefinedGlobalTypes());
                saveModelGroups(redefinedModelGroups());
                saveAttributeGroups(redefinedAttributeGroups());
                saveIndex();
                savePointers();
            } else {
                throw new IllegalArgumentException("filer must not be null");
            }
        }

        /* access modifiers changed from: package-private */
        public void saveTypesRecursively(SchemaType[] schemaTypeArr) {
            for (SchemaType schemaType : schemaTypeArr) {
                if (schemaType.getTypeSystem() == getTypeSystem()) {
                    saveType(schemaType);
                    saveTypesRecursively(schemaType.getAnonymousTypes());
                }
            }
        }

        public void saveGlobalElements(SchemaGlobalElement[] schemaGlobalElementArr) {
            if (!this._incomplete) {
                for (SchemaGlobalElement saveGlobalElement : schemaGlobalElementArr) {
                    saveGlobalElement(saveGlobalElement);
                }
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveGlobalAttributes(SchemaGlobalAttribute[] schemaGlobalAttributeArr) {
            if (!this._incomplete) {
                for (SchemaGlobalAttribute saveGlobalAttribute : schemaGlobalAttributeArr) {
                    saveGlobalAttribute(saveGlobalAttribute);
                }
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveModelGroups(SchemaModelGroup[] schemaModelGroupArr) {
            if (!this._incomplete) {
                for (SchemaModelGroup saveModelGroup : schemaModelGroupArr) {
                    saveModelGroup(saveModelGroup);
                }
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveAttributeGroups(SchemaAttributeGroup[] schemaAttributeGroupArr) {
            if (!this._incomplete) {
                for (SchemaAttributeGroup saveAttributeGroup : schemaAttributeGroupArr) {
                    saveAttributeGroup(saveAttributeGroup);
                }
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveIdentityConstraints(SchemaIdentityConstraint[] schemaIdentityConstraintArr) {
            if (!this._incomplete) {
                for (SchemaIdentityConstraint saveIdentityConstraint : schemaIdentityConstraintArr) {
                    saveIdentityConstraint(saveIdentityConstraint);
                }
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveGlobalElement(SchemaGlobalElement schemaGlobalElement) {
            if (!this._incomplete) {
                String handleForElement = this._localHandles.handleForElement(schemaGlobalElement);
                XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForElement);
                SchemaParticle schemaParticle = (SchemaParticle) schemaGlobalElement;
                xsbReader.writeParticleData(schemaParticle);
                xsbReader.writeString(schemaGlobalElement.getSourceName());
                xsbReader.writeRealHeader(handleForElement, 3);
                xsbReader.writeParticleData(schemaParticle);
                xsbReader.writeString(schemaGlobalElement.getSourceName());
                xsbReader.writeEnd();
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveGlobalAttribute(SchemaGlobalAttribute schemaGlobalAttribute) {
            if (!this._incomplete) {
                String handleForAttribute = this._localHandles.handleForAttribute(schemaGlobalAttribute);
                XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForAttribute);
                xsbReader.writeAttributeData(schemaGlobalAttribute);
                xsbReader.writeString(schemaGlobalAttribute.getSourceName());
                xsbReader.writeRealHeader(handleForAttribute, 4);
                xsbReader.writeAttributeData(schemaGlobalAttribute);
                xsbReader.writeString(schemaGlobalAttribute.getSourceName());
                xsbReader.writeEnd();
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveModelGroup(SchemaModelGroup schemaModelGroup) {
            if (!this._incomplete) {
                String handleForModelGroup = this._localHandles.handleForModelGroup(schemaModelGroup);
                XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForModelGroup);
                xsbReader.writeModelGroupData(schemaModelGroup);
                xsbReader.writeRealHeader(handleForModelGroup, 6);
                xsbReader.writeModelGroupData(schemaModelGroup);
                xsbReader.writeEnd();
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveAttributeGroup(SchemaAttributeGroup schemaAttributeGroup) {
            if (!this._incomplete) {
                String handleForAttributeGroup = this._localHandles.handleForAttributeGroup(schemaAttributeGroup);
                XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForAttributeGroup);
                xsbReader.writeAttributeGroupData(schemaAttributeGroup);
                xsbReader.writeRealHeader(handleForAttributeGroup, 7);
                xsbReader.writeAttributeGroupData(schemaAttributeGroup);
                xsbReader.writeEnd();
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        public void saveIdentityConstraint(SchemaIdentityConstraint schemaIdentityConstraint) {
            if (!this._incomplete) {
                String handleForIdentityConstraint = this._localHandles.handleForIdentityConstraint(schemaIdentityConstraint);
                XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForIdentityConstraint);
                xsbReader.writeIdConstraintData(schemaIdentityConstraint);
                xsbReader.writeRealHeader(handleForIdentityConstraint, 8);
                xsbReader.writeIdConstraintData(schemaIdentityConstraint);
                xsbReader.writeEnd();
                return;
            }
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }

        /* access modifiers changed from: package-private */
        public void saveType(SchemaType schemaType) {
            String handleForType = this._localHandles.handleForType(schemaType);
            XsbReader xsbReader = new XsbReader(getTypeSystem(), handleForType);
            xsbReader.writeTypeData(schemaType);
            xsbReader.writeRealHeader(handleForType, 2);
            xsbReader.writeTypeData(schemaType);
            xsbReader.writeEnd();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0056, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x005f, code lost:
            throw r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String crackPointer(java.io.InputStream r4) {
            /*
                r0 = 0
                org.apache.xmlbeans.impl.util.LongUTFDataInputStream r1 = new org.apache.xmlbeans.impl.util.LongUTFDataInputStream     // Catch:{ IOException -> 0x0060 }
                r1.<init>(r4)     // Catch:{ IOException -> 0x0060 }
                int r4 = r1.readInt()     // Catch:{ all -> 0x0054 }
                r2 = -629491010(0xffffffffda7ababe, float:-1.76435173E16)
                if (r4 == r2) goto L_0x0013
                r1.close()     // Catch:{ IOException -> 0x0060 }
                return r0
            L_0x0013:
                short r4 = r1.readShort()     // Catch:{ all -> 0x0054 }
                short r2 = r1.readShort()     // Catch:{ all -> 0x0054 }
                r3 = 2
                if (r4 == r3) goto L_0x0022
                r1.close()     // Catch:{ IOException -> 0x0060 }
                return r0
            L_0x0022:
                r4 = 24
                if (r2 <= r4) goto L_0x002a
                r1.close()     // Catch:{ IOException -> 0x0060 }
                return r0
            L_0x002a:
                r4 = 18
                if (r2 < r4) goto L_0x0031
                r1.readShort()     // Catch:{ all -> 0x0054 }
            L_0x0031:
                short r4 = r1.readShort()     // Catch:{ all -> 0x0054 }
                r2 = 5
                if (r4 == r2) goto L_0x003c
                r1.close()     // Catch:{ IOException -> 0x0060 }
                return r0
            L_0x003c:
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$StringPool r4 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$StringPool     // Catch:{ all -> 0x0054 }
                java.lang.String r2 = "pointer"
                java.lang.String r3 = "unk"
                r4.<init>(r2, r3)     // Catch:{ all -> 0x0054 }
                r4.readFrom(r1)     // Catch:{ all -> 0x0054 }
                short r2 = r1.readShort()     // Catch:{ all -> 0x0054 }
                java.lang.String r4 = r4.stringForCode(r2)     // Catch:{ all -> 0x0054 }
                r1.close()     // Catch:{ IOException -> 0x0060 }
                return r4
            L_0x0054:
                r4 = move-exception
                throw r4     // Catch:{ all -> 0x0056 }
            L_0x0056:
                r2 = move-exception
                r1.close()     // Catch:{ all -> 0x005b }
                goto L_0x005f
            L_0x005b:
                r1 = move-exception
                r4.addSuppressed(r1)     // Catch:{ IOException -> 0x0060 }
            L_0x005f:
                throw r2     // Catch:{ IOException -> 0x0060 }
            L_0x0060:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.crackPointer(java.io.InputStream):java.lang.String");
        }

        public SchemaType typeForHandle(String str) {
            SchemaType schemaType;
            synchronized (this._resolvedHandles) {
                schemaType = (SchemaType) this._resolvedHandles.get(str);
            }
            return schemaType;
        }

        public SchemaType typeForClassname(String str) {
            SchemaType.Ref ref = (SchemaType.Ref) this._typeRefsByClassname.get(str);
            if (ref != null) {
                return ref.get();
            }
            return null;
        }

        public SchemaComponent resolveHandle(String str) {
            SchemaComponent schemaComponent;
            SchemaComponent schemaComponent2;
            synchronized (this._resolvedHandles) {
                schemaComponent = this._resolvedHandles.get(str);
            }
            if (schemaComponent == null) {
                XsbReader xsbReader = new XsbReader(getTypeSystem(), str, 65535);
                int actualFiletype = xsbReader.getActualFiletype();
                if (actualFiletype == 2) {
                    XBeanDebug.LOG.atTrace().log("Resolving type for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingType();
                } else if (actualFiletype == 3) {
                    XBeanDebug.LOG.atTrace().log("Resolving element for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingElement();
                } else if (actualFiletype == 4) {
                    XBeanDebug.LOG.atTrace().log("Resolving attribute for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingAttribute();
                } else if (actualFiletype == 6) {
                    XBeanDebug.LOG.atTrace().log("Resolving model group for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingModelGroup();
                } else if (actualFiletype == 7) {
                    XBeanDebug.LOG.atTrace().log("Resolving attribute group for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingAttributeGroup();
                } else if (actualFiletype == 8) {
                    XBeanDebug.LOG.atTrace().log("Resolving id constraint for handle {}", (Object) str);
                    schemaComponent2 = xsbReader.finishLoadingIdentityConstraint();
                } else {
                    throw new IllegalStateException("Illegal handle type");
                }
                synchronized (this._resolvedHandles) {
                    if (!this._resolvedHandles.containsKey(str)) {
                        this._resolvedHandles.put(str, schemaComponent2);
                        schemaComponent = schemaComponent2;
                    } else {
                        schemaComponent = this._resolvedHandles.get(str);
                    }
                }
            }
            return schemaComponent;
        }

        public void resolve() {
            XBeanDebug.LOG.atTrace().log("Resolve called type system {}", (Object) this._name);
            if (!this._allNonGroupHandlesResolved) {
                XBeanDebug.LOG.atTrace().log("Resolving all handles for type system {}", (Object) this._name);
                ArrayList<SchemaComponent.Ref> arrayList = new ArrayList<>();
                arrayList.addAll(this._globalElements.values());
                arrayList.addAll(this._globalAttributes.values());
                arrayList.addAll(this._globalTypes.values());
                arrayList.addAll(this._documentTypes.values());
                arrayList.addAll(this._attributeTypes.values());
                arrayList.addAll(this._identityConstraints.values());
                for (SchemaComponent.Ref component : arrayList) {
                    component.getComponent();
                }
                XBeanDebug.LOG.atTrace().log("Finished resolving type system {}", (Object) this._name);
                this._allNonGroupHandlesResolved = true;
            }
        }

        public boolean isNamespaceDefined(String str) {
            return this._namespaces.contains(str);
        }

        public SchemaType.Ref findTypeRef(QName qName) {
            return (SchemaType.Ref) this._globalTypes.get(qName);
        }

        public SchemaType.Ref findDocumentTypeRef(QName qName) {
            return (SchemaType.Ref) this._documentTypes.get(qName);
        }

        public SchemaType.Ref findAttributeTypeRef(QName qName) {
            return (SchemaType.Ref) this._attributeTypes.get(qName);
        }

        public SchemaGlobalElement.Ref findElementRef(QName qName) {
            return (SchemaGlobalElement.Ref) this._globalElements.get(qName);
        }

        public SchemaGlobalAttribute.Ref findAttributeRef(QName qName) {
            return (SchemaGlobalAttribute.Ref) this._globalAttributes.get(qName);
        }

        public SchemaModelGroup.Ref findModelGroupRef(QName qName) {
            return (SchemaModelGroup.Ref) this._modelGroups.get(qName);
        }

        public SchemaAttributeGroup.Ref findAttributeGroupRef(QName qName) {
            return (SchemaAttributeGroup.Ref) this._attributeGroups.get(qName);
        }

        public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName qName) {
            return (SchemaIdentityConstraint.Ref) this._identityConstraints.get(qName);
        }

        private static <T, U> U[] refHelper(Map<QName, SchemaComponent.Ref> map, Function<T, U> function, IntFunction<U[]> intFunction, U[] uArr) {
            return refHelper(map == null ? null : map.values(), function, intFunction, uArr);
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [java.util.function.Function<T, U>, java.util.function.Function] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static <T, U> U[] refHelper(java.util.Collection<org.apache.xmlbeans.SchemaComponent.Ref> r1, java.util.function.Function<T, U> r2, java.util.function.IntFunction<U[]> r3, U[] r4) {
            /*
                if (r1 == 0) goto L_0x001e
                boolean r0 = r1.isEmpty()
                if (r0 == 0) goto L_0x0009
                goto L_0x001e
            L_0x0009:
                java.util.stream.Stream r1 = r1.stream()
                org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda19 r4 = new org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl$$ExternalSyntheticLambda19
                r4.<init>()
                java.util.stream.Stream r1 = r1.map(r4)
                java.util.stream.Stream r1 = r1.map(r2)
                java.lang.Object[] r4 = r1.toArray(r3)
            L_0x001e:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl.refHelper(java.util.Collection, java.util.function.Function, java.util.function.IntFunction, java.lang.Object[]):java.lang.Object[]");
        }

        static /* synthetic */ SchemaType[] lambda$globalTypes$3(int i) {
            return new SchemaType[i];
        }

        public SchemaType[] globalTypes() {
            return (SchemaType[]) refHelper(this._globalTypes, new SchemaContainer$$ExternalSyntheticLambda3(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda18(), (U[]) EMPTY_ST_ARRAY);
        }

        static /* synthetic */ SchemaType[] lambda$redefinedGlobalTypes$4(int i) {
            return new SchemaType[i];
        }

        public SchemaType[] redefinedGlobalTypes() {
            return (SchemaType[]) refHelper((Collection<SchemaComponent.Ref>) this._redefinedGlobalTypes, new SchemaContainer$$ExternalSyntheticLambda3(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda5(), (U[]) EMPTY_ST_ARRAY);
        }

        public InputStream getSourceAsStream(String str) {
            if (!str.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                str = PackagingURIHelper.FORWARD_SLASH_STRING + str;
            }
            return this._resourceLoader.getResourceAsStream(getMetadataPath() + "/src" + str);
        }

        /* access modifiers changed from: package-private */
        public SchemaContainer[] containers() {
            return (SchemaContainer[]) this._containers.values().toArray(new SchemaContainer[0]);
        }

        static /* synthetic */ SchemaType[] lambda$documentTypes$5(int i) {
            return new SchemaType[i];
        }

        public SchemaType[] documentTypes() {
            return (SchemaType[]) refHelper(this._documentTypes, new SchemaContainer$$ExternalSyntheticLambda3(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda20(), (U[]) EMPTY_ST_ARRAY);
        }

        static /* synthetic */ SchemaType[] lambda$attributeTypes$6(int i) {
            return new SchemaType[i];
        }

        public SchemaType[] attributeTypes() {
            return (SchemaType[]) refHelper(this._attributeTypes, new SchemaContainer$$ExternalSyntheticLambda3(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda38(), (U[]) EMPTY_ST_ARRAY);
        }

        static /* synthetic */ SchemaGlobalElement[] lambda$globalElements$7(int i) {
            return new SchemaGlobalElement[i];
        }

        public SchemaGlobalElement[] globalElements() {
            return (SchemaGlobalElement[]) refHelper(this._globalElements, new SchemaContainer$$ExternalSyntheticLambda5(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda4(), (U[]) EMPTY_GE_ARRAY);
        }

        static /* synthetic */ SchemaGlobalAttribute[] lambda$globalAttributes$8(int i) {
            return new SchemaGlobalAttribute[i];
        }

        public SchemaGlobalAttribute[] globalAttributes() {
            return (SchemaGlobalAttribute[]) refHelper(this._globalAttributes, new SchemaContainer$$ExternalSyntheticLambda4(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda41(), (U[]) EMPTY_GA_ARRAY);
        }

        static /* synthetic */ SchemaModelGroup[] lambda$modelGroups$9(int i) {
            return new SchemaModelGroup[i];
        }

        public SchemaModelGroup[] modelGroups() {
            return (SchemaModelGroup[]) refHelper(this._modelGroups, new SchemaContainer$$ExternalSyntheticLambda0(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda21(), (U[]) EMPTY_MG_ARRAY);
        }

        static /* synthetic */ SchemaModelGroup[] lambda$redefinedModelGroups$10(int i) {
            return new SchemaModelGroup[i];
        }

        public SchemaModelGroup[] redefinedModelGroups() {
            return (SchemaModelGroup[]) refHelper((Collection<SchemaComponent.Ref>) this._redefinedModelGroups, new SchemaContainer$$ExternalSyntheticLambda0(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda42(), (U[]) EMPTY_MG_ARRAY);
        }

        static /* synthetic */ SchemaAttributeGroup[] lambda$attributeGroups$11(int i) {
            return new SchemaAttributeGroup[i];
        }

        public SchemaAttributeGroup[] attributeGroups() {
            return (SchemaAttributeGroup[]) refHelper(this._attributeGroups, new SchemaContainer$$ExternalSyntheticLambda1(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda43(), (U[]) EMPTY_AG_ARRAY);
        }

        static /* synthetic */ SchemaAttributeGroup[] lambda$redefinedAttributeGroups$12(int i) {
            return new SchemaAttributeGroup[i];
        }

        public SchemaAttributeGroup[] redefinedAttributeGroups() {
            return (SchemaAttributeGroup[]) refHelper((Collection<SchemaComponent.Ref>) this._redefinedAttributeGroups, new SchemaContainer$$ExternalSyntheticLambda1(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda40(), (U[]) EMPTY_AG_ARRAY);
        }

        public SchemaAnnotation[] annotations() {
            List<SchemaAnnotation> list = this._annotations;
            return (list == null || list.isEmpty()) ? EMPTY_ANN_ARRAY : (SchemaAnnotation[]) this._annotations.toArray(EMPTY_ANN_ARRAY);
        }

        static /* synthetic */ SchemaIdentityConstraint[] lambda$identityConstraints$13(int i) {
            return new SchemaIdentityConstraint[i];
        }

        public SchemaIdentityConstraint[] identityConstraints() {
            return (SchemaIdentityConstraint[]) refHelper(this._identityConstraints, new SchemaContainer$$ExternalSyntheticLambda2(), (IntFunction<U[]>) new SchemaTypeSystemImpl$$ExternalSyntheticLambda28(), (U[]) EMPTY_IC_ARRAY);
        }

        public ClassLoader getClassLoader() {
            return this._classloader;
        }

        public String handleForType(SchemaType schemaType) {
            return this._localHandles.handleForType(schemaType);
        }

        public String getName() {
            return this._name;
        }

        public String getMetadataPath() {
            Matcher matcher = packPat.matcher(this._name);
            return (matcher.find() ? matcher.group(1) : this._name).replace('.', '/');
        }

        /* access modifiers changed from: package-private */
        public String getBasePackage() {
            return nameToPathString(this._name);
        }

        /* access modifiers changed from: package-private */
        public SchemaTypeLoader getLinker() {
            return this._linker;
        }

        /* access modifiers changed from: package-private */
        public SchemaTypePool getTypePool() {
            return this._localHandles;
        }

        /* access modifiers changed from: package-private */
        public Set<String> getNamespaces() {
            return this._namespaces;
        }

        /* access modifiers changed from: package-private */
        public Map<String, SchemaComponent.Ref> getTypeRefsByClassname() {
            return this._typeRefsByClassname;
        }

        /* access modifiers changed from: package-private */
        public OutputStream getSaverStream(String str, String str2) {
            try {
                return this._filer.createBinaryFile(str);
            } catch (IOException e) {
                IOException iOException = e;
                throw new SchemaTypeLoaderException(iOException.getMessage(), getName(), str2, 9, iOException);
            }
        }

        /* access modifiers changed from: package-private */
        public InputStream getLoaderStream(String str) {
            return this._resourceLoader.getResourceAsStream(str);
        }
    }
