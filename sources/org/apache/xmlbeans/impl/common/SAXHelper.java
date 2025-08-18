package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class SAXHelper {
    public static final EntityResolver IGNORING_ENTITY_RESOLVER = new SAXHelper$$ExternalSyntheticLambda0();
    private static final Logger LOG = LogManager.getLogger((Class<?>) SAXHelper.class);
    private static long lastLog;

    private SAXHelper() {
    }

    public static XMLReader newXMLReader(XmlOptions xmlOptions) throws SAXException, ParserConfigurationException {
        XMLReader xMLReader = saxFactory(xmlOptions).newSAXParser().getXMLReader();
        xMLReader.setEntityResolver(IGNORING_ENTITY_RESOLVER);
        trySetSAXFeature(xMLReader, "http://javax.xml.XMLConstants/feature/secure-processing");
        trySetXercesSecurityManager(xMLReader, xmlOptions);
        return xMLReader;
    }

    static /* synthetic */ InputSource lambda$static$0(String str, String str2) throws SAXException, IOException {
        return new InputSource(new StringReader(""));
    }

    static SAXParserFactory saxFactory() {
        return saxFactory(new XmlOptions());
    }

    static SAXParserFactory saxFactory(XmlOptions xmlOptions) {
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        newInstance.setValidating(false);
        newInstance.setNamespaceAware(true);
        trySetSAXFeature(newInstance, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        trySetSAXFeature(newInstance, XMLBeansConstants.FEATURE_LOAD_DTD_GRAMMAR, xmlOptions.isLoadDTDGrammar());
        trySetSAXFeature(newInstance, XMLBeansConstants.FEATURE_LOAD_EXTERNAL_DTD, xmlOptions.isLoadExternalDTD());
        trySetSAXFeature(newInstance, XMLBeansConstants.FEATURE_DISALLOW_DOCTYPE_DECL, xmlOptions.disallowDocTypeDeclaration());
        return newInstance;
    }

    private static void trySetSAXFeature(SAXParserFactory sAXParserFactory, String str, boolean z) {
        try {
            sAXParserFactory.setFeature(str, z);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set SAX feature {} because outdated XML parser in classpath", (Object) str);
        }
    }

    private static void trySetSAXFeature(XMLReader xMLReader, String str) {
        try {
            xMLReader.setFeature(str, true);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set SAX feature {} because outdated XML parser in classpath", (Object) str);
        }
    }

    private static void trySetXercesSecurityManager(XMLReader xMLReader, XmlOptions xmlOptions) {
        try {
            Class<?> cls = Class.forName(new String[]{"org.apache.xerces.util.SecurityManager"}[0]);
            try {
                Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                cls.getMethod("setEntityExpansionLimit", new Class[]{Integer.TYPE}).invoke(newInstance, new Object[]{Integer.valueOf(xmlOptions.getEntityExpansionLimit())});
                xMLReader.setProperty(XMLBeansConstants.SECURITY_MANAGER, newInstance);
            } catch (Throwable th) {
                if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5)) {
                    LOG.atWarn().withThrowable(th).log("SAX Security Manager could not be setup [log suppressed for 5 minutes]");
                    lastLog = System.currentTimeMillis();
                }
                try {
                    xMLReader.setProperty(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(xmlOptions.getEntityExpansionLimit()));
                } catch (SAXException e) {
                    if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5)) {
                        LOG.atWarn().withThrowable(e).log("SAX Security Manager could not be setup [log suppressed for 5 minutes]");
                        lastLog = System.currentTimeMillis();
                    }
                }
            }
        } catch (Throwable unused) {
            xMLReader.setProperty(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(xmlOptions.getEntityExpansionLimit()));
        }
    }
}
