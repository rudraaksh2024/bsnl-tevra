package org.apache.xmlbeans.impl.schema;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import kotlin.text.Typography;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.SystemCache;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class SchemaTypeLoaderImpl extends SchemaTypeLoaderBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object CACHED_NOT_FOUND = new Object();
    /* access modifiers changed from: private */
    public static final SchemaTypeLoader[] EMPTY_SCHEMATYPELOADER_ARRAY = new SchemaTypeLoader[0];
    public static String METADATA_PACKAGE_LOAD = SchemaTypeSystemImpl.METADATA_PACKAGE_GEN;
    private static final String[] basePackage = {"org.apache.xmlbeans.metadata", "schemaorg_apache_xmlbeans"};
    private static final String[] baseSchemas = {"sXMLCONFIG", "sXMLLANG", "sXMLSCHEMA", "sXMLTOOLS"};
    private Map<QName, Object> _attributeCache;
    private Map<QName, Object> _attributeGroupCache;
    private Map<QName, Object> _attributeTypeCache;
    /* access modifiers changed from: private */
    public final ClassLoader _classLoader;
    private Map<String, SchemaTypeSystemImpl> _classLoaderTypeSystems;
    private Map<String, Object> _classnameCache;
    private Map<String, SchemaTypeSystemImpl> _classpathTypeSystems;
    private Map<QName, Object> _documentCache;
    private Map<QName, Object> _elementCache;
    private Map<QName, Object> _idConstraintCache;
    private final String _metadataPath;
    private Map<QName, Object> _modelGroupCache;
    /* access modifiers changed from: private */
    public final ResourceLoader _resourceLoader;
    /* access modifiers changed from: private */
    public final SchemaTypeLoader[] _searchPath;
    private Map<QName, Object> _typeCache;

    static {
        if (SystemCache.get() != null) {
            SystemCache.set(new SchemaTypeLoaderCache());
        }
    }

    private static class SchemaTypeLoaderCache extends SystemCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ThreadLocal<List<SoftReference<SchemaTypeLoaderImpl>>> _cachedTypeSystems;

        public static /* synthetic */ ArrayList $r8$lambda$gDKEcGxnaIgU7sqXbIS8kj9NQtE() {
            return new ArrayList();
        }

        static {
            Class<SchemaTypeLoaderImpl> cls = SchemaTypeLoaderImpl.class;
        }

        private SchemaTypeLoaderCache() {
            this._cachedTypeSystems = ThreadLocal.withInitial(new SchemaTypeLoaderImpl$SchemaTypeLoaderCache$$ExternalSyntheticLambda0());
        }

        public void clearThreadLocals() {
            this._cachedTypeSystems.remove();
            super.clearThreadLocals();
        }

        public SchemaTypeLoader getFromTypeLoaderCache(ClassLoader classLoader) {
            SchemaTypeLoaderImpl schemaTypeLoaderImpl;
            List list = this._cachedTypeSystems.get();
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    i = -1;
                    schemaTypeLoaderImpl = null;
                    break;
                }
                schemaTypeLoaderImpl = (SchemaTypeLoaderImpl) ((SoftReference) list.get(i)).get();
                if (schemaTypeLoaderImpl == null) {
                    list.remove(i);
                    i--;
                } else if (schemaTypeLoaderImpl._classLoader == classLoader) {
                    break;
                }
                i++;
            }
            if (i > 0) {
                list.set(0, list.get(i));
                list.set(i, (SoftReference) list.get(0));
            }
            return schemaTypeLoaderImpl;
        }

        public void addToTypeLoaderCache(SchemaTypeLoader schemaTypeLoader, ClassLoader classLoader) {
            List list = this._cachedTypeSystems.get();
            if (list.size() > 0) {
                list.set(0, new SoftReference((SchemaTypeLoaderImpl) schemaTypeLoader));
                list.add((SoftReference) list.get(0));
                return;
            }
            list.add(new SoftReference((SchemaTypeLoaderImpl) schemaTypeLoader));
        }
    }

    public static SchemaTypeLoaderImpl getContextTypeLoader() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        SchemaTypeLoaderImpl schemaTypeLoaderImpl = (SchemaTypeLoaderImpl) SystemCache.get().getFromTypeLoaderCache(contextClassLoader);
        if (schemaTypeLoaderImpl != null) {
            return schemaTypeLoaderImpl;
        }
        SchemaTypeLoaderImpl schemaTypeLoaderImpl2 = new SchemaTypeLoaderImpl(new SchemaTypeLoader[]{BuiltinSchemaTypeSystem.get()}, (ResourceLoader) null, contextClassLoader, (String) null);
        SystemCache.get().addToTypeLoaderCache(schemaTypeLoaderImpl2, contextClassLoader);
        return schemaTypeLoaderImpl2;
    }

    public static SchemaTypeLoader build(SchemaTypeLoader[] schemaTypeLoaderArr, ResourceLoader resourceLoader, ClassLoader classLoader) {
        return build(schemaTypeLoaderArr, resourceLoader, classLoader, (String) null);
    }

    public static SchemaTypeLoader build(SchemaTypeLoader[] schemaTypeLoaderArr, ResourceLoader resourceLoader, ClassLoader classLoader, String str) {
        ClassLoader classLoader2 = classLoader;
        SubLoaderList subLoaderList = new SubLoaderList();
        subLoaderList.add(schemaTypeLoaderArr);
        ClassLoader classLoader3 = classLoader2 == null ? SchemaDocument.class.getClassLoader() : classLoader2;
        for (String str2 : basePackage) {
            String[] strArr = baseSchemas;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str3 = str2 + ".system." + strArr[i] + ".TypeSystemHolder";
                if (classLoader3.getResource(str3.replace(".", PackagingURIHelper.FORWARD_SLASH_STRING) + ".class") == null) {
                    break;
                }
                try {
                    subLoaderList.add((SchemaTypeLoader) Class.forName(str3, true, classLoader3).getDeclaredField("typeSystem").get((Object) null));
                    i++;
                } catch (Exception e) {
                    throw new XmlRuntimeException((Throwable) e);
                }
            }
        }
        return new SchemaTypeLoaderImpl(subLoaderList.toArray(), resourceLoader, classLoader2, str);
    }

    private static class SubLoaderList {
        private final Map<SchemaTypeLoader, Object> seen;
        private final List<SchemaTypeLoader> theList;

        private SubLoaderList() {
            this.theList = new ArrayList();
            this.seen = new IdentityHashMap();
        }

        /* access modifiers changed from: package-private */
        public void add(SchemaTypeLoader[] schemaTypeLoaderArr) {
            if (schemaTypeLoaderArr != null) {
                for (SchemaTypeLoaderImpl schemaTypeLoaderImpl : schemaTypeLoaderArr) {
                    if (schemaTypeLoaderImpl instanceof SchemaTypeLoaderImpl) {
                        SchemaTypeLoaderImpl schemaTypeLoaderImpl2 = schemaTypeLoaderImpl;
                        if (schemaTypeLoaderImpl2._classLoader == null && schemaTypeLoaderImpl2._resourceLoader == null) {
                            add(schemaTypeLoaderImpl2._searchPath);
                        } else {
                            add((SchemaTypeLoader) schemaTypeLoaderImpl2);
                        }
                    } else {
                        add((SchemaTypeLoader) schemaTypeLoaderImpl);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void add(SchemaTypeLoader schemaTypeLoader) {
            if (schemaTypeLoader != null && !this.seen.containsKey(schemaTypeLoader)) {
                this.theList.add(schemaTypeLoader);
                this.seen.put(schemaTypeLoader, (Object) null);
            }
        }

        /* access modifiers changed from: package-private */
        public SchemaTypeLoader[] toArray() {
            return (SchemaTypeLoader[]) this.theList.toArray(SchemaTypeLoaderImpl.EMPTY_SCHEMATYPELOADER_ARRAY);
        }
    }

    private SchemaTypeLoaderImpl(SchemaTypeLoader[] schemaTypeLoaderArr, ResourceLoader resourceLoader, ClassLoader classLoader, String str) {
        this._searchPath = schemaTypeLoaderArr == null ? EMPTY_SCHEMATYPELOADER_ARRAY : schemaTypeLoaderArr;
        this._resourceLoader = resourceLoader;
        this._classLoader = classLoader;
        if (str != null) {
            this._metadataPath = str;
        } else {
            this._metadataPath = isPath30(classLoader) ? METADATA_PACKAGE_LOAD : "schema" + METADATA_PACKAGE_LOAD.replace(PackagingURIHelper.FORWARD_SLASH_STRING, "_");
        }
        initCaches();
    }

    private static boolean isPath30(ClassLoader classLoader) {
        String str = METADATA_PACKAGE_LOAD + "/system";
        if (classLoader == null) {
            classLoader = SchemaDocument.class.getClassLoader();
        }
        return classLoader.getResource(str) != null;
    }

    private void initCaches() {
        this._classpathTypeSystems = Collections.synchronizedMap(new HashMap());
        this._classLoaderTypeSystems = Collections.synchronizedMap(new HashMap());
        this._elementCache = Collections.synchronizedMap(new HashMap());
        this._attributeCache = Collections.synchronizedMap(new HashMap());
        this._modelGroupCache = Collections.synchronizedMap(new HashMap());
        this._attributeGroupCache = Collections.synchronizedMap(new HashMap());
        this._idConstraintCache = Collections.synchronizedMap(new HashMap());
        this._typeCache = Collections.synchronizedMap(new HashMap());
        this._documentCache = Collections.synchronizedMap(new HashMap());
        this._attributeTypeCache = Collections.synchronizedMap(new HashMap());
        this._classnameCache = Collections.synchronizedMap(new HashMap());
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeSystemImpl typeSystemForComponent(String str, QName qName) {
        String str2 = str + QNameHelper.hexsafedir(qName) + ".xsb";
        ResourceLoader resourceLoader = this._resourceLoader;
        String crackEntry = resourceLoader != null ? crackEntry(resourceLoader, str2) : null;
        ClassLoader classLoader = this._classLoader;
        if (classLoader != null) {
            crackEntry = crackEntry(classLoader, str2);
        }
        if (crackEntry != null) {
            return (SchemaTypeSystemImpl) typeSystemForName(crackEntry);
        }
        return null;
    }

    public SchemaTypeSystem typeSystemForName(String str) {
        SchemaTypeSystemImpl typeSystemOnClassloader;
        SchemaTypeSystemImpl typeSystemOnClasspath;
        if (this._resourceLoader != null && (typeSystemOnClasspath = getTypeSystemOnClasspath(str)) != null) {
            return typeSystemOnClasspath;
        }
        if (this._classLoader == null || (typeSystemOnClassloader = getTypeSystemOnClassloader(str)) == null) {
            return null;
        }
        return typeSystemOnClassloader;
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeSystemImpl typeSystemForClassname(String str, String str2) {
        String crackEntry;
        String crackEntry2;
        String str3 = str + str2.replace('.', '/') + ".xsb";
        ResourceLoader resourceLoader = this._resourceLoader;
        if (resourceLoader != null && (crackEntry2 = crackEntry(resourceLoader, str3)) != null) {
            return getTypeSystemOnClasspath(crackEntry2);
        }
        ClassLoader classLoader = this._classLoader;
        if (classLoader == null || (crackEntry = crackEntry(classLoader, str3)) == null) {
            return null;
        }
        return getTypeSystemOnClassloader(crackEntry);
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeSystemImpl getTypeSystemOnClasspath(String str) {
        return this._classpathTypeSystems.computeIfAbsent(str, new SchemaTypeLoaderImpl$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getTypeSystemOnClasspath$0$org-apache-xmlbeans-impl-schema-SchemaTypeLoaderImpl  reason: not valid java name */
    public /* synthetic */ SchemaTypeSystemImpl m2319lambda$getTypeSystemOnClasspath$0$orgapachexmlbeansimplschemaSchemaTypeLoaderImpl(String str) {
        return new SchemaTypeSystemImpl(this._resourceLoader, str, this);
    }

    /* access modifiers changed from: package-private */
    public SchemaTypeSystemImpl getTypeSystemOnClassloader(String str) {
        XBeanDebug.LOG.atTrace().log("Finding type system {} on classloader", (Object) str);
        SchemaTypeSystemImpl schemaTypeSystemImpl = this._classLoaderTypeSystems.get(str);
        if (schemaTypeSystemImpl != null) {
            return schemaTypeSystemImpl;
        }
        XBeanDebug.LOG.atTrace().log("Type system {}} not cached - consulting field", (Object) str);
        SchemaTypeSystemImpl forName = SchemaTypeSystemImpl.forName(str, this._classLoader);
        this._classLoaderTypeSystems.put(str, forName);
        return forName;
    }

    static String crackEntry(ResourceLoader resourceLoader, String str) {
        InputStream resourceAsStream = resourceLoader.getResourceAsStream(str);
        if (resourceAsStream == null) {
            return null;
        }
        return crackPointer(resourceAsStream);
    }

    static String crackEntry(ClassLoader classLoader, String str) {
        InputStream resourceAsStream = classLoader.getResourceAsStream(str);
        if (resourceAsStream == null) {
            return null;
        }
        return crackPointer(resourceAsStream);
    }

    static String crackPointer(InputStream inputStream) {
        return SchemaTypeSystemImpl.crackPointer(inputStream);
    }

    public boolean isNamespaceDefined(String str) {
        for (SchemaTypeLoader isNamespaceDefined : this._searchPath) {
            if (isNamespaceDefined.isNamespaceDefined(str)) {
                return true;
            }
        }
        if (typeSystemForComponent(this._metadataPath + "/namespace/", new QName(str, Sax2Dom.XMLNS_PREFIX)) != null) {
            return true;
        }
        return false;
    }

    public SchemaType.Ref findTypeRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._typeCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref ref = (SchemaType.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findTypeRef : this._searchPath) {
                ref = findTypeRef.findTypeRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/type/", qName)) != null) {
                ref = typeSystemForComponent.findTypeRef(qName);
            }
            this._typeCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaType typeForClassname(String str) {
        SchemaTypeSystemImpl typeSystemForClassname;
        String replace = str.replace(Typography.dollar, '.');
        Object obj = this._classnameCache.get(replace);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType schemaType = (SchemaType) obj;
        if (schemaType == null) {
            for (SchemaTypeLoader typeForClassname : this._searchPath) {
                schemaType = typeForClassname.typeForClassname(replace);
                if (schemaType != null) {
                    break;
                }
            }
            if (schemaType == null && (typeSystemForClassname = typeSystemForClassname(this._metadataPath + "/javaname/", replace)) != null) {
                schemaType = typeSystemForClassname.typeForClassname(replace);
            }
            this._classnameCache.put(replace, schemaType == null ? CACHED_NOT_FOUND : schemaType);
        }
        return schemaType;
    }

    public SchemaType.Ref findDocumentTypeRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._documentCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref ref = (SchemaType.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findDocumentTypeRef : this._searchPath) {
                ref = findDocumentTypeRef.findDocumentTypeRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/element/", qName)) != null) {
                ref = typeSystemForComponent.findDocumentTypeRef(qName);
            }
            this._documentCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaType.Ref findAttributeTypeRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._attributeTypeCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaType.Ref ref = (SchemaType.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findAttributeTypeRef : this._searchPath) {
                ref = findAttributeTypeRef.findAttributeTypeRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/attribute/", qName)) != null) {
                ref = typeSystemForComponent.findAttributeTypeRef(qName);
            }
            this._attributeTypeCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaGlobalElement.Ref findElementRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._elementCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaGlobalElement.Ref ref = (SchemaGlobalElement.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findElementRef : this._searchPath) {
                ref = findElementRef.findElementRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/element/", qName)) != null) {
                ref = typeSystemForComponent.findElementRef(qName);
            }
            this._elementCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaGlobalAttribute.Ref findAttributeRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._attributeCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaGlobalAttribute.Ref ref = (SchemaGlobalAttribute.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findAttributeRef : this._searchPath) {
                ref = findAttributeRef.findAttributeRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/attribute/", qName)) != null) {
                ref = typeSystemForComponent.findAttributeRef(qName);
            }
            this._attributeCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaModelGroup.Ref findModelGroupRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._modelGroupCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaModelGroup.Ref ref = (SchemaModelGroup.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findModelGroupRef : this._searchPath) {
                ref = findModelGroupRef.findModelGroupRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/modelgroup/", qName)) != null) {
                ref = typeSystemForComponent.findModelGroupRef(qName);
            }
            this._modelGroupCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._attributeGroupCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaAttributeGroup.Ref ref = (SchemaAttributeGroup.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findAttributeGroupRef : this._searchPath) {
                ref = findAttributeGroupRef.findAttributeGroupRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/attributegroup/", qName)) != null) {
                ref = typeSystemForComponent.findAttributeGroupRef(qName);
            }
            this._attributeGroupCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName qName) {
        SchemaTypeSystemImpl typeSystemForComponent;
        Object obj = this._idConstraintCache.get(qName);
        if (obj == CACHED_NOT_FOUND) {
            return null;
        }
        SchemaIdentityConstraint.Ref ref = (SchemaIdentityConstraint.Ref) obj;
        if (ref == null) {
            for (SchemaTypeLoader findIdentityConstraintRef : this._searchPath) {
                ref = findIdentityConstraintRef.findIdentityConstraintRef(qName);
                if (ref != null) {
                    break;
                }
            }
            if (ref == null && (typeSystemForComponent = typeSystemForComponent(this._metadataPath + "/identityconstraint/", qName)) != null) {
                ref = typeSystemForComponent.findIdentityConstraintRef(qName);
            }
            this._idConstraintCache.put(qName, ref == null ? CACHED_NOT_FOUND : ref);
        }
        return ref;
    }

    public InputStream getSourceAsStream(String str) {
        ClassLoader classLoader;
        if (!str.startsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            str = PackagingURIHelper.FORWARD_SLASH_STRING + str;
        }
        ResourceLoader resourceLoader = this._resourceLoader;
        InputStream resourceAsStream = resourceLoader != null ? resourceLoader.getResourceAsStream(this._metadataPath + "/src" + str) : null;
        return (resourceAsStream != null || (classLoader = this._classLoader) == null) ? resourceAsStream : classLoader.getResourceAsStream(this._metadataPath + "/src" + str);
    }
}
