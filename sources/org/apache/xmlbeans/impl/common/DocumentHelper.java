package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public final class DocumentHelper {
    /* access modifiers changed from: private */
    public static final Logger LOG = LogManager.getLogger((Class<?>) DocumentHelper.class);
    private static final DocumentBuilder documentBuilderSingleton = newDocumentBuilder(new XmlOptions());
    private static long lastLog;

    private DocumentHelper() {
    }

    private static class DocHelperErrorHandler implements ErrorHandler {
        private DocHelperErrorHandler() {
        }

        public void warning(SAXParseException sAXParseException) throws SAXException {
            DocumentHelper.LOG.atWarn().withThrowable(sAXParseException).log(asString(sAXParseException));
        }

        public void error(SAXParseException sAXParseException) throws SAXException {
            DocumentHelper.LOG.atError().withThrowable(sAXParseException).log(asString(sAXParseException));
        }

        public void fatalError(SAXParseException sAXParseException) throws SAXException {
            DocumentHelper.LOG.atFatal().withThrowable(sAXParseException).log(asString(sAXParseException));
            throw sAXParseException;
        }

        private String asString(SAXParseException sAXParseException) {
            StringBuilder sb = new StringBuilder();
            String systemId = sAXParseException.getSystemId();
            if (systemId != null) {
                int lastIndexOf = systemId.lastIndexOf(47);
                if (lastIndexOf != -1) {
                    systemId = systemId.substring(lastIndexOf + 1);
                }
                sb.append(systemId);
            }
            sb.append(NameUtil.COLON);
            sb.append(sAXParseException.getLineNumber());
            sb.append(NameUtil.COLON);
            sb.append(sAXParseException.getColumnNumber());
            sb.append(": ");
            sb.append(sAXParseException.getMessage());
            return sb.toString();
        }
    }

    public static DocumentBuilder newDocumentBuilder(XmlOptions xmlOptions) {
        try {
            DocumentBuilder newDocumentBuilder = documentBuilderFactory(xmlOptions).newDocumentBuilder();
            newDocumentBuilder.setEntityResolver(SAXHelper.IGNORING_ENTITY_RESOLVER);
            newDocumentBuilder.setErrorHandler(new DocHelperErrorHandler());
            return newDocumentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    private static DocumentBuilderFactory documentBuilderFactory(XmlOptions xmlOptions) {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setValidating(false);
        trySetFeature(newInstance, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        trySetFeature(newInstance, XMLBeansConstants.FEATURE_LOAD_DTD_GRAMMAR, xmlOptions.isLoadDTDGrammar());
        trySetFeature(newInstance, XMLBeansConstants.FEATURE_LOAD_EXTERNAL_DTD, xmlOptions.isLoadExternalDTD());
        trySetFeature(newInstance, XMLBeansConstants.FEATURE_DISALLOW_DOCTYPE_DECL, xmlOptions.disallowDocTypeDeclaration());
        trySetXercesSecurityManager(newInstance, xmlOptions);
        return newInstance;
    }

    private static void trySetFeature(DocumentBuilderFactory documentBuilderFactory, String str, boolean z) {
        try {
            documentBuilderFactory.setFeature(str, z);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set SAX feature {} because of outdated XML parser in classpath", (Object) str);
        }
    }

    private static void trySetXercesSecurityManager(DocumentBuilderFactory documentBuilderFactory, XmlOptions xmlOptions) {
        try {
            Object newInstance = Class.forName(new String[]{"org.apache.xerces.util.SecurityManager"}[0]).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            newInstance.getClass().getMethod("setEntityExpansionLimit", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{Integer.valueOf(xmlOptions.getEntityExpansionLimit())});
            documentBuilderFactory.setAttribute(XMLBeansConstants.SECURITY_MANAGER, newInstance);
        } catch (ClassNotFoundException unused) {
            try {
                documentBuilderFactory.setAttribute(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(xmlOptions.getEntityExpansionLimit()));
            } catch (Throwable th) {
                if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5)) {
                    LOG.atWarn().withThrowable(th).log("DocumentBuilderFactory Entity Expansion Limit could not be setup [log suppressed for 5 minutes]");
                    lastLog = System.currentTimeMillis();
                }
            }
        } catch (Throwable th2) {
            if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5)) {
                LOG.atWarn().withThrowable(th2).log("DocumentBuilderFactory Security Manager could not be setup [log suppressed for 5 minutes]");
                lastLog = System.currentTimeMillis();
            }
            documentBuilderFactory.setAttribute(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(xmlOptions.getEntityExpansionLimit()));
        }
    }

    public static Document readDocument(XmlOptions xmlOptions, InputStream inputStream) throws IOException, SAXException {
        return newDocumentBuilder(xmlOptions).parse(inputStream);
    }

    public static Document readDocument(XmlOptions xmlOptions, InputSource inputSource) throws IOException, SAXException {
        return newDocumentBuilder(xmlOptions).parse(inputSource);
    }

    public static Document createDocument() {
        return documentBuilderSingleton.newDocument();
    }
}
