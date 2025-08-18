package org.apache.poi.util;

import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.SchemaFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Internal
public final class XMLHelper {
    static final String FEATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
    static final String FEATURE_EXTERNAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    static final String FEATURE_LOAD_DTD_GRAMMAR = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar";
    static final String FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    static final String FEATURE_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    /* access modifiers changed from: private */
    public static final Logger LOG = LogManager.getLogger((Class<?>) XMLHelper.class);
    static final String METHOD_ENTITY_EXPANSION_XERCES = "setEntityExpansionLimit";
    static final String PROPERTY_ENTITY_EXPANSION_LIMIT = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit";
    static final String PROPERTY_SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    static final String[] SECURITY_MANAGERS = {"org.apache.xerces.util.SecurityManager"};
    private static final DocumentBuilderFactory documentBuilderFactory = getDocumentBuilderFactory();
    private static long lastLog;
    private static final SAXParserFactory saxFactory = getSaxParserFactory();

    @FunctionalInterface
    private interface SecurityFeature {
        void accept(String str, boolean z) throws ParserConfigurationException, SAXException, TransformerException;
    }

    @FunctionalInterface
    private interface SecurityProperty {
        void accept(String str, Object obj) throws SAXException;
    }

    private XMLHelper() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0097, code lost:
        if (trySet((org.apache.poi.util.XMLHelper.SecurityProperty) new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7(r0), "http://apache.org/xml/properties/security-manager", r2) == false) goto L_0x0099;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static javax.xml.parsers.DocumentBuilderFactory getDocumentBuilderFactory() {
        /*
            javax.xml.parsers.DocumentBuilderFactory r0 = javax.xml.parsers.DocumentBuilderFactory.newInstance()
            r1 = 1
            r0.setNamespaceAware(r1)
            r2 = 0
            r0.setExpandEntityReferences(r2)
            r0.setValidating(r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://javax.xml.XMLConstants/feature/secure-processing"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r1)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7
            r3.<init>(r0)
            java.lang.String r4 = "http://javax.xml.XMLConstants/property/accessExternalSchema"
            java.lang.String r5 = ""
            quietSet(r3, r4, r5)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7
            r3.<init>(r0)
            java.lang.String r4 = "http://javax.xml.XMLConstants/property/accessExternalDTD"
            quietSet(r3, r4, r5)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://xml.org/sax/features/external-general-entities"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://xml.org/sax/features/external-parameter-entities"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://apache.org/xml/features/nonvalidating/load-external-dtd"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>(r0)
            java.lang.String r4 = "http://apache.org/xml/features/disallow-doctype-decl"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r1)
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda8 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda8
            r3.<init>(r0)
            java.lang.String r4 = "XIncludeAware"
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r3, (java.lang.String) r4, (boolean) r2)
            java.lang.Object r2 = getXercesSecurityManager()
            if (r2 == 0) goto L_0x0099
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7
            r3.<init>(r0)
            java.lang.String r4 = "http://apache.org/xml/properties/security-manager"
            boolean r2 = trySet((org.apache.poi.util.XMLHelper.SecurityProperty) r3, (java.lang.String) r4, (java.lang.Object) r2)
            if (r2 != 0) goto L_0x00aa
        L_0x0099:
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7 r2 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7
            r2.<init>(r0)
            java.lang.String r3 = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            trySet((org.apache.poi.util.XMLHelper.SecurityProperty) r2, (java.lang.String) r3, (java.lang.Object) r1)
        L_0x00aa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.XMLHelper.getDocumentBuilderFactory():javax.xml.parsers.DocumentBuilderFactory");
    }

    public static DocumentBuilder newDocumentBuilder() {
        try {
            DocumentBuilder newDocumentBuilder = documentBuilderFactory.newDocumentBuilder();
            newDocumentBuilder.setEntityResolver(new XMLHelper$$ExternalSyntheticLambda10());
            newDocumentBuilder.setErrorHandler(new DocHelperErrorHandler());
            return newDocumentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    public static SAXParserFactory getSaxParserFactory() {
        try {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            newInstance.setValidating(false);
            newInstance.setNamespaceAware(true);
            newInstance.getClass();
            trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda0(newInstance), "http://javax.xml.XMLConstants/feature/secure-processing", true);
            newInstance.getClass();
            trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda0(newInstance), "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            newInstance.getClass();
            trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda0(newInstance), "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            newInstance.getClass();
            trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda0(newInstance), FEATURE_EXTERNAL_ENTITIES, false);
            newInstance.getClass();
            trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda0(newInstance), "http://apache.org/xml/features/disallow-doctype-decl", true);
            return newInstance;
        } catch (Error | RuntimeException e) {
            logThrowable(e, "Failed to create SAXParserFactory", ProcessIdUtil.DEFAULT_PROCESSID);
            throw e;
        } catch (Exception e2) {
            logThrowable(e2, "Failed to create SAXParserFactory", ProcessIdUtil.DEFAULT_PROCESSID);
            throw new RuntimeException("Failed to create SAXParserFactory", e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0042, code lost:
        if (trySet((org.apache.poi.util.XMLHelper.SecurityProperty) new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2(r0), "http://apache.org/xml/properties/security-manager", r1) == false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.xml.sax.XMLReader newXMLReader() throws org.xml.sax.SAXException, javax.xml.parsers.ParserConfigurationException {
        /*
            javax.xml.parsers.SAXParserFactory r0 = saxFactory
            javax.xml.parsers.SAXParser r0 = r0.newSAXParser()
            org.xml.sax.XMLReader r0 = r0.getXMLReader()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda10 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda10
            r1.<init>()
            r0.setEntityResolver(r1)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            r1.<init>(r0)
            java.lang.String r2 = "http://javax.xml.XMLConstants/feature/secure-processing"
            r3 = 1
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r1, (java.lang.String) r2, (boolean) r3)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            r1.<init>(r0)
            java.lang.String r2 = "http://xml.org/sax/features/external-general-entities"
            r4 = 0
            trySet((org.apache.poi.util.XMLHelper.SecurityFeature) r1, (java.lang.String) r2, (boolean) r4)
            java.lang.Object r1 = getXercesSecurityManager()
            if (r1 == 0) goto L_0x0044
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2 r2 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2
            r2.<init>(r0)
            java.lang.String r4 = "http://apache.org/xml/properties/security-manager"
            boolean r1 = trySet((org.apache.poi.util.XMLHelper.SecurityProperty) r2, (java.lang.String) r4, (java.lang.Object) r1)
            if (r1 != 0) goto L_0x0055
        L_0x0044:
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2
            r1.<init>(r0)
            java.lang.String r2 = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            trySet((org.apache.poi.util.XMLHelper.SecurityProperty) r1, (java.lang.String) r2, (java.lang.Object) r3)
        L_0x0055:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.XMLHelper.newXMLReader():org.xml.sax.XMLReader");
    }

    public static XMLInputFactory newXMLInputFactory() {
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda3(newInstance), "javax.xml.stream.isNamespaceAware", true);
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda3(newInstance), "javax.xml.stream.isValidating", false);
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda3(newInstance), "javax.xml.stream.supportDTD", false);
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda3(newInstance), "javax.xml.stream.isSupportingExternalEntities", false);
        return newInstance;
    }

    public static XMLOutputFactory newXMLOutputFactory() {
        XMLOutputFactory newInstance = XMLOutputFactory.newInstance();
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda9(newInstance), "javax.xml.stream.isRepairingNamespaces", true);
        return newInstance;
    }

    public static XMLEventFactory newXMLEventFactory() {
        return XMLEventFactory.newInstance();
    }

    public static TransformerFactory getTransformerFactory() {
        TransformerFactory newInstance = TransformerFactory.newInstance();
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda11(newInstance), "http://javax.xml.XMLConstants/feature/secure-processing", true);
        newInstance.getClass();
        quietSet(new XMLHelper$$ExternalSyntheticLambda12(newInstance), "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        newInstance.getClass();
        trySet((SecurityProperty) new XMLHelper$$ExternalSyntheticLambda12(newInstance), "http://javax.xml.XMLConstants/property/accessExternalStylesheet", (Object) "");
        newInstance.getClass();
        quietSet(new XMLHelper$$ExternalSyntheticLambda12(newInstance), "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
        return newInstance;
    }

    public static Transformer newTransformer() throws TransformerConfigurationException {
        Transformer newTransformer = getTransformerFactory().newTransformer();
        newTransformer.setOutputProperty("encoding", "UTF-8");
        newTransformer.setOutputProperty("indent", "no");
        newTransformer.setOutputProperty("method", "xml");
        return newTransformer;
    }

    public static SchemaFactory getSchemaFactory() {
        SchemaFactory newInstance = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        newInstance.getClass();
        trySet((SecurityFeature) new XMLHelper$$ExternalSyntheticLambda4(newInstance), "http://javax.xml.XMLConstants/feature/secure-processing", true);
        newInstance.getClass();
        trySet((SecurityProperty) new XMLHelper$$ExternalSyntheticLambda5(newInstance), "http://javax.xml.XMLConstants/property/accessExternalDTD", (Object) "");
        newInstance.getClass();
        trySet((SecurityProperty) new XMLHelper$$ExternalSyntheticLambda5(newInstance), "http://javax.xml.XMLConstants/property/accessExternalStylesheet", (Object) "");
        newInstance.getClass();
        trySet((SecurityProperty) new XMLHelper$$ExternalSyntheticLambda5(newInstance), "http://javax.xml.XMLConstants/property/accessExternalSchema", (Object) "");
        return newInstance;
    }

    private static Object getXercesSecurityManager() {
        String[] strArr = SECURITY_MANAGERS;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                Object newInstance = Class.forName(str).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                newInstance.getClass().getMethod(METHOD_ENTITY_EXPANSION_XERCES, new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{1});
                return newInstance;
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                logThrowable(th, "SAX Feature unsupported", str);
            }
        }
        return null;
        i++;
    }

    private static boolean trySet(SecurityFeature securityFeature, String str, boolean z) {
        try {
            securityFeature.accept(str, z);
            return true;
        } catch (Exception e) {
            logThrowable(e, "SAX Feature unsupported", str);
            return false;
        } catch (Error e2) {
            logThrowable(e2, "Cannot set SAX feature because outdated XML parser in classpath", str);
            return false;
        }
    }

    private static boolean trySet(SecurityProperty securityProperty, String str, Object obj) {
        try {
            securityProperty.accept(str, obj);
            return true;
        } catch (Exception e) {
            logThrowable(e, "SAX Feature unsupported", str);
            return false;
        } catch (Error e2) {
            logThrowable(e2, "Cannot set SAX feature because outdated XML parser in classpath", str);
            return false;
        }
    }

    private static boolean quietSet(SecurityProperty securityProperty, String str, Object obj) {
        try {
            securityProperty.accept(str, obj);
            return true;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    private static void logThrowable(Throwable th, String str, String str2) {
        if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5)) {
            LOG.atWarn().withThrowable(th).log("{} [log suppressed for 5 minutes] {}", str, str2);
            lastLog = System.currentTimeMillis();
        }
    }

    private static class DocHelperErrorHandler implements ErrorHandler {
        private DocHelperErrorHandler() {
        }

        public void warning(SAXParseException sAXParseException) {
            printError(Level.WARN, sAXParseException);
        }

        public void error(SAXParseException sAXParseException) {
            printError(Level.ERROR, sAXParseException);
        }

        public void fatalError(SAXParseException sAXParseException) throws SAXException {
            printError(Level.FATAL, sAXParseException);
            throw sAXParseException;
        }

        private void printError(Level level, SAXParseException sAXParseException) {
            int lastIndexOf;
            String systemId = sAXParseException.getSystemId();
            if (!(systemId == null || (lastIndexOf = systemId.lastIndexOf(47)) == -1)) {
                systemId = systemId.substring(lastIndexOf + 1);
            }
            StringBuilder sb = new StringBuilder();
            if (systemId == null) {
                systemId = "";
            }
            XMLHelper.LOG.atLevel(level).withThrowable(sAXParseException).log(sb.append(systemId).append(NameUtil.COLON).append(sAXParseException.getLineNumber()).append(NameUtil.COLON).append(sAXParseException.getColumnNumber()).append(NameUtil.COLON).append(sAXParseException.getMessage()).toString());
        }
    }

    /* access modifiers changed from: private */
    public static InputSource ignoreEntity(String str, String str2) {
        return new InputSource(new StringReader(""));
    }
}
