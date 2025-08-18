package org.apache.poi.ooxml.util;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

public final class XPathHelper {
    private static final QName ALTERNATE_CONTENT_TAG = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "AlternateContent");
    private static final Logger LOG = LogManager.getLogger((Class<?>) XPathHelper.class);
    private static final String MAC_DML_NS = "http://schemas.microsoft.com/office/mac/drawingml/2008/main";
    private static final String MC_NS = "http://schemas.openxmlformats.org/markup-compatibility/2006";
    private static final String OSGI_ERROR = "Schemas (*.xsb) for <CLASS> can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes - please either verify if the <XSB>.xsb is on the classpath or alternatively try to use the poi-ooxml-full-x.x.jar";
    static final XPathFactory xpathFactory;

    static {
        XPathFactory newInstance = XPathFactory.newInstance();
        xpathFactory = newInstance;
        trySetFeature(newInstance, "http://javax.xml.XMLConstants/feature/secure-processing", true);
    }

    private XPathHelper() {
    }

    public static XPathFactory getFactory() {
        return xpathFactory;
    }

    private static void trySetFeature(XPathFactory xPathFactory, String str, boolean z) {
        try {
            xPathFactory.setFeature(str, z);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("XPathFactory Feature ({}) unsupported", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set XPathFactory feature ({}) because outdated XML parser in classpath", (Object) str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074  */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends org.apache.xmlbeans.XmlObject> T selectProperty(org.apache.xmlbeans.XmlObject r4, java.lang.Class<T> r5, org.apache.poi.xslf.usermodel.XSLFShape.ReparseFactory<T> r6, javax.xml.namespace.QName[]... r7) throws org.apache.xmlbeans.XmlException {
        /*
            org.apache.xmlbeans.XmlCursor r4 = r4.newCursor()
            r0 = 0
            if (r6 == 0) goto L_0x0009
            r1 = 1
            goto L_0x000a
        L_0x0009:
            r1 = r0
        L_0x000a:
            r2 = 0
            org.apache.xmlbeans.XmlCursor r7 = selectProperty(r4, r7, r0, r1, r0)     // Catch:{ all -> 0x006e }
            if (r7 != 0) goto L_0x001a
            r4.dispose()
            if (r7 == 0) goto L_0x0019
            r7.dispose()
        L_0x0019:
            return r2
        L_0x001a:
            org.apache.xmlbeans.XmlObject r0 = r7.getObject()     // Catch:{ all -> 0x006b }
            boolean r1 = r0 instanceof org.apache.xmlbeans.impl.values.XmlAnyTypeImpl     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0062
            java.lang.String r0 = "Schemas (*.xsb) for <CLASS> can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes - please either verify if the <XSB>.xsb is on the classpath or alternatively try to use the poi-ooxml-full-x.x.jar"
            java.lang.String r1 = "<CLASS>"
            java.lang.String r2 = r5.getSimpleName()     // Catch:{ all -> 0x006b }
            java.lang.String r0 = r0.replace(r1, r2)     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "<XSB>"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r2.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r5 = r5.getSimpleName()     // Catch:{ all -> 0x006b }
            java.util.Locale r3 = java.util.Locale.ROOT     // Catch:{ all -> 0x006b }
            java.lang.String r5 = r5.toLowerCase(r3)     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r5 = r2.append(r5)     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "*"
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ all -> 0x006b }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006b }
            java.lang.String r5 = r0.replace(r1, r5)     // Catch:{ all -> 0x006b }
            if (r6 == 0) goto L_0x005c
            javax.xml.stream.XMLStreamReader r5 = r7.newXMLStreamReader()     // Catch:{ all -> 0x006b }
            org.apache.xmlbeans.XmlObject r0 = r6.parse(r5)     // Catch:{ all -> 0x006b }
            goto L_0x0062
        L_0x005c:
            org.apache.xmlbeans.XmlException r6 = new org.apache.xmlbeans.XmlException     // Catch:{ all -> 0x006b }
            r6.<init>((java.lang.String) r5)     // Catch:{ all -> 0x006b }
            throw r6     // Catch:{ all -> 0x006b }
        L_0x0062:
            r4.dispose()
            if (r7 == 0) goto L_0x006a
            r7.dispose()
        L_0x006a:
            return r0
        L_0x006b:
            r5 = move-exception
            r2 = r7
            goto L_0x006f
        L_0x006e:
            r5 = move-exception
        L_0x006f:
            r4.dispose()
            if (r2 == 0) goto L_0x0077
            r2.dispose()
        L_0x0077:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.util.XPathHelper.selectProperty(org.apache.xmlbeans.XmlObject, java.lang.Class, org.apache.poi.xslf.usermodel.XSLFShape$ReparseFactory, javax.xml.namespace.QName[][]):org.apache.xmlbeans.XmlObject");
    }

    private static XmlCursor selectProperty(XmlCursor xmlCursor, QName[][] qNameArr, int i, boolean z, boolean z2) throws XmlException {
        AlternateContentDocument.AlternateContent alternateContent;
        int i2 = 0;
        for (QName qName : qNameArr[i]) {
            boolean child = xmlCursor.toChild(qName);
            while (child) {
                if (i == qNameArr.length - 1) {
                    return xmlCursor;
                }
                xmlCursor.push();
                XmlCursor selectProperty = selectProperty(xmlCursor, qNameArr, i + 1, z, false);
                if (selectProperty != null) {
                    return selectProperty;
                }
                xmlCursor.pop();
                child = xmlCursor.toNextSibling(qName);
            }
        }
        if (z2 || !xmlCursor.toChild(ALTERNATE_CONTENT_TAG)) {
            return null;
        }
        XmlObject object = xmlCursor.getObject();
        if (object instanceof AlternateContentDocument.AlternateContent) {
            alternateContent = (AlternateContentDocument.AlternateContent) object;
        } else if (z) {
            try {
                alternateContent = AlternateContentDocument.Factory.parse(xmlCursor.newXMLStreamReader()).getAlternateContent();
            } catch (XmlException e) {
                throw new XmlException("unable to parse AlternateContent element", e);
            }
        } else {
            throw new XmlException(OSGI_ERROR.replace("<CLASS>", "AlternateContent").replace("<XSB>", "alternatecontentelement"));
        }
        int sizeOfChoiceArray = alternateContent.sizeOfChoiceArray();
        while (i2 < sizeOfChoiceArray) {
            AlternateContentDocument.AlternateContent.Choice choiceArray = alternateContent.getChoiceArray(i2);
            XmlCursor newCursor = choiceArray.newCursor();
            try {
                if (!MAC_DML_NS.equalsIgnoreCase(newCursor.namespaceForPrefix(choiceArray.getRequires()))) {
                    XmlCursor selectProperty2 = selectProperty(newCursor, qNameArr, i, z, true);
                    if (selectProperty2 != null) {
                        if (selectProperty2 != newCursor) {
                            newCursor.dispose();
                        }
                        return selectProperty2;
                    } else if (selectProperty2 == newCursor) {
                        i2++;
                    }
                } else if (newCursor == null) {
                    i2++;
                }
                i2++;
            } finally {
                if (newCursor != null) {
                    newCursor.dispose();
                }
            }
        }
        if (!alternateContent.isSetFallback()) {
            return null;
        }
        XmlCursor newCursor2 = alternateContent.getFallback().newCursor();
        try {
            XmlCursor selectProperty3 = selectProperty(newCursor2, qNameArr, i, z, true);
            if (selectProperty3 != newCursor2) {
                newCursor2.dispose();
            }
            return selectProperty3;
        } catch (Throwable th) {
            if (newCursor2 != null) {
                newCursor2.dispose();
            }
            throw th;
        }
    }
}
