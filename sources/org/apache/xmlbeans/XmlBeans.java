package org.apache.xmlbeans;

import java.io.File;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.PathResourceLoader;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemCompiler;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.w3c.dom.Node;

public final class XmlBeans {
    private static final String HOLDER_CLASS_NAME = "TypeSystemHolder";
    public static final SchemaType NO_TYPE = getNoType();
    private static final String TYPE_SYSTEM_FIELD = "typeSystem";
    private static String XMLBEANS_TITLE = "org.apache.xmlbeans";
    private static String XMLBEANS_VENDOR = "Apache Software Foundation";
    private static String XMLBEANS_VERSION = "5.0.3";
    private static final ThreadLocal _threadLocalLoaderQNameCache = new ThreadLocal() {
        /* access modifiers changed from: protected */
        public Object initialValue() {
            return new SoftReference(new QNameCache(32));
        }
    };

    static {
        Package packageR = XmlBeans.class.getPackage();
        if (!(packageR == null || packageR.getImplementationVersion() == null)) {
            XMLBEANS_TITLE = packageR.getImplementationTitle();
            XMLBEANS_VERSION = packageR.getImplementationVersion();
            XMLBEANS_VENDOR = packageR.getImplementationVendor();
        }
    }

    public static final String getTitle() {
        return XMLBEANS_TITLE;
    }

    public static final String getVendor() {
        return XMLBEANS_VENDOR;
    }

    public static final String getVersion() {
        return XMLBEANS_VERSION;
    }

    public static void clearThreadLocals() {
        _threadLocalLoaderQNameCache.remove();
    }

    public static QNameCache getQNameCache() {
        ThreadLocal threadLocal = _threadLocalLoaderQNameCache;
        QNameCache qNameCache = (QNameCache) ((SoftReference) threadLocal.get()).get();
        if (qNameCache != null) {
            return qNameCache;
        }
        QNameCache qNameCache2 = new QNameCache(32);
        threadLocal.set(new SoftReference(qNameCache2));
        return qNameCache2;
    }

    public static QName getQName(String str) {
        return getQNameCache().getName("", str);
    }

    public static QName getQName(String str, String str2) {
        return getQNameCache().getName(str, str2);
    }

    private static RuntimeException causedException(RuntimeException runtimeException, Throwable th) {
        runtimeException.initCause(th);
        return runtimeException;
    }

    public static String compilePath(String str) throws XmlException {
        return compilePath(str, (XmlOptions) null);
    }

    public static String compilePath(String str, XmlOptions xmlOptions) throws XmlException {
        return getContextTypeLoader().compilePath(str, xmlOptions);
    }

    public static String compileQuery(String str) throws XmlException {
        return compileQuery(str, (XmlOptions) null);
    }

    public static String compileQuery(String str, XmlOptions xmlOptions) throws XmlException {
        return getContextTypeLoader().compileQuery(str, xmlOptions);
    }

    public static SchemaTypeLoader getContextTypeLoader() {
        return SchemaTypeLoaderImpl.getContextTypeLoader();
    }

    public static SchemaTypeSystem getBuiltinTypeSystem() {
        return BuiltinSchemaTypeSystem.get();
    }

    public static XmlCursor nodeToCursor(Node node) {
        return Locale.nodeToCursor(node);
    }

    public static XmlObject nodeToXmlObject(Node node) {
        return Locale.nodeToXmlObject(node);
    }

    public static XMLStreamReader nodeToXmlStreamReader(Node node) {
        return Locale.nodeToXmlStream(node);
    }

    public static Node streamToNode(XMLStreamReader xMLStreamReader) {
        return Locale.streamToNode(xMLStreamReader);
    }

    public static SchemaTypeLoader loadXsd(XmlObject... xmlObjectArr) throws XmlException {
        return loadXsd(xmlObjectArr, (XmlOptions) null);
    }

    public static SchemaTypeLoader loadXsd(XmlObject[] xmlObjectArr, XmlOptions xmlOptions) throws XmlException {
        SchemaTypeSystemImpl compile = SchemaTypeSystemCompiler.compile((String) null, (SchemaTypeSystem) null, xmlObjectArr, (BindingConfig) null, getContextTypeLoader(), (Filer) null, xmlOptions);
        if (compile == null) {
            return null;
        }
        return typeLoaderUnion(compile, getContextTypeLoader());
    }

    public static SchemaTypeSystem compileXsd(XmlObject[] xmlObjectArr, SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions) throws XmlException {
        return compileXmlBeans((String) null, (SchemaTypeSystem) null, xmlObjectArr, (BindingConfig) null, schemaTypeLoader, (Filer) null, xmlOptions);
    }

    public static SchemaTypeSystem compileXsd(SchemaTypeSystem schemaTypeSystem, XmlObject[] xmlObjectArr, SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions) throws XmlException {
        return compileXmlBeans((String) null, schemaTypeSystem, xmlObjectArr, (BindingConfig) null, schemaTypeLoader, (Filer) null, xmlOptions);
    }

    public static SchemaTypeSystem compileXmlBeans(String str, SchemaTypeSystem schemaTypeSystem, XmlObject[] xmlObjectArr, BindingConfig bindingConfig, SchemaTypeLoader schemaTypeLoader, Filer filer, XmlOptions xmlOptions) throws XmlException {
        if (schemaTypeLoader == null) {
            schemaTypeLoader = getContextTypeLoader();
        }
        return SchemaTypeSystemCompiler.compile(str, schemaTypeSystem, xmlObjectArr, bindingConfig, schemaTypeLoader, filer, xmlOptions);
    }

    public static SchemaTypeLoader typeLoaderUnion(SchemaTypeLoader... schemaTypeLoaderArr) {
        return schemaTypeLoaderArr.length == 1 ? schemaTypeLoaderArr[0] : SchemaTypeLoaderImpl.build(schemaTypeLoaderArr, (ResourceLoader) null, (ClassLoader) null);
    }

    public static SchemaTypeLoader typeLoaderForClassLoader(ClassLoader classLoader) {
        return SchemaTypeLoaderImpl.build((SchemaTypeLoader[]) null, (ResourceLoader) null, classLoader);
    }

    public static SchemaTypeLoader typeLoaderForResource(ResourceLoader resourceLoader) {
        return SchemaTypeLoaderImpl.build((SchemaTypeLoader[]) null, resourceLoader, (ClassLoader) null);
    }

    public static SchemaTypeSystem typeSystemForClassLoader(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            try {
                classLoader = Thread.currentThread().getContextClassLoader();
            } catch (ClassNotFoundException e) {
                throw causedException(new RuntimeException("Cannot load SchemaTypeSystem. Unable to load class with name " + str + ".TypeSystemHolder. Make sure the generated binary files are on the classpath."), e);
            } catch (NoSuchFieldException e2) {
                throw causedException(new RuntimeException("Cannot find field typeSystem on class " + str + ".TypeSystemHolder. Please verify the version of xmlbeans.jar is correct."), e2);
            } catch (IllegalAccessException e3) {
                throw causedException(new RuntimeException("Field typeSystem on class " + str + ".TypeSystemHolderis not accessible. Please verify the version of xmlbeans.jar is correct."), e3);
            }
        }
        SchemaTypeSystem schemaTypeSystem = (SchemaTypeSystem) classLoader.loadClass(str + ".TypeSystemHolder").getDeclaredField(TYPE_SYSTEM_FIELD).get((Object) null);
        SchemaTypeSystem schemaTypeSystem2 = schemaTypeSystem;
        if (schemaTypeSystem != null) {
            return schemaTypeSystem;
        }
        throw new RuntimeException("SchemaTypeSystem is null for field typeSystem on class with name " + str + ".TypeSystemHolder. Please verify the version of xmlbeans.jar is correct.");
    }

    public static ResourceLoader resourceLoaderForPath(File[] fileArr) {
        return new PathResourceLoader(fileArr);
    }

    public static SchemaType typeForClass(Class cls) {
        if (cls != null && XmlObject.class.isAssignableFrom(cls)) {
            try {
                Field field = cls.getField("type");
                if (field == null) {
                    return null;
                }
                return (SchemaType) field.get((Object) null);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static SchemaType getNoType() {
        return BuiltinSchemaTypeSystem.getNoType();
    }

    private XmlBeans() {
    }
}
