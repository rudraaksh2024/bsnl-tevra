package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;

public final class StaxHelper {
    private static final Logger LOG = LogManager.getLogger((Class<?>) StaxHelper.class);

    private StaxHelper() {
    }

    public static XMLInputFactory newXMLInputFactory(XmlOptions xmlOptions) {
        XMLInputFactory newFactory = XMLInputFactory.newFactory();
        trySetProperty(newFactory, "javax.xml.stream.isNamespaceAware", true);
        trySetProperty(newFactory, "javax.xml.stream.isValidating", false);
        trySetProperty(newFactory, "javax.xml.stream.supportDTD", xmlOptions.isLoadDTDGrammar());
        trySetProperty(newFactory, "javax.xml.stream.isSupportingExternalEntities", xmlOptions.isLoadExternalDTD());
        return newFactory;
    }

    public static XMLOutputFactory newXMLOutputFactory(XmlOptions xmlOptions) {
        XMLOutputFactory newFactory = XMLOutputFactory.newFactory();
        trySetProperty(newFactory, "javax.xml.stream.isRepairingNamespaces", true);
        return newFactory;
    }

    public static XMLEventFactory newXMLEventFactory(XmlOptions xmlOptions) {
        return XMLEventFactory.newFactory();
    }

    private static void trySetProperty(XMLInputFactory xMLInputFactory, String str, boolean z) {
        try {
            xMLInputFactory.setProperty(str, Boolean.valueOf(z));
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("StAX Property unsupported: {}", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set StAX property {} because outdated StAX parser in classpath", (Object) str);
        }
    }

    private static void trySetProperty(XMLOutputFactory xMLOutputFactory, String str, boolean z) {
        try {
            xMLOutputFactory.setProperty(str, Boolean.valueOf(z));
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("StAX Property unsupported: {}", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set StAX property {} because outdated StAX parser in classpath", (Object) str);
        }
    }
}
