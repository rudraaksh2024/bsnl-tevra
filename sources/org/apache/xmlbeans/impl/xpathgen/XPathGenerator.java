package org.apache.xmlbeans.impl.xpathgen;

import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.NameUtil;

public class XPathGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static String generateXPath(XmlCursor xmlCursor, XmlCursor xmlCursor2, NamespaceContext namespaceContext) throws XPathGenerationException {
        if (xmlCursor == null) {
            throw new IllegalArgumentException("Null node");
        } else if (namespaceContext != null) {
            XmlCursor.TokenType currentTokenType = xmlCursor.currentTokenType();
            if (xmlCursor2 != null && xmlCursor.isAtSamePositionAs(xmlCursor2)) {
                return ".";
            }
            int intValue = currentTokenType.intValue();
            if (intValue == 1 || intValue == 3) {
                return generateInternal(xmlCursor, xmlCursor2, namespaceContext);
            }
            if (intValue == 5) {
                int countTextTokens = countTextTokens(xmlCursor);
                xmlCursor.toParent();
                String generateInternal = generateInternal(xmlCursor, xmlCursor2, namespaceContext);
                if (countTextTokens == 0) {
                    return generateInternal + "/text()";
                }
                return generateInternal + "/text()[position()=" + countTextTokens + ']';
            } else if (intValue == 6) {
                QName name = xmlCursor.getName();
                xmlCursor.toParent();
                return generateInternal(xmlCursor, xmlCursor2, namespaceContext) + "/@" + qnameToString(name, namespaceContext);
            } else if (intValue == 7) {
                QName name2 = xmlCursor.getName();
                xmlCursor.toParent();
                String generateInternal2 = generateInternal(xmlCursor, xmlCursor2, namespaceContext);
                String localPart = name2.getLocalPart();
                if (localPart.length() == 0) {
                    return generateInternal2 + "/@xmlns";
                }
                return generateInternal2 + "/@xmlns:" + localPart;
            } else {
                throw new XPathGenerationException("Cannot generate XPath for cursor position: " + currentTokenType.toString());
            }
        } else {
            throw new IllegalArgumentException("Null namespace context");
        }
    }

    private static String generateInternal(XmlCursor xmlCursor, XmlCursor xmlCursor2, NamespaceContext namespaceContext) throws XPathGenerationException {
        StringBuilder sb;
        if (xmlCursor.isStartdoc()) {
            return "";
        }
        if (xmlCursor2 != null && xmlCursor.isAtSamePositionAs(xmlCursor2)) {
            return ".";
        }
        QName name = xmlCursor.getName();
        XmlCursor newCursor = xmlCursor.newCursor();
        if (!xmlCursor.toParent()) {
            return PackagingURIHelper.FORWARD_SLASH_STRING + name;
        }
        xmlCursor.push();
        if (xmlCursor.toChild(name)) {
            int i = 0;
            int i2 = 1;
            do {
                if (xmlCursor.isAtSamePositionAs(newCursor)) {
                    i = i2;
                } else {
                    i2++;
                }
            } while (xmlCursor.toNextSibling(name));
            xmlCursor.pop();
            newCursor.dispose();
            String generateInternal = generateInternal(xmlCursor, xmlCursor2, namespaceContext);
            if (i2 == 1) {
                return sb.append(generateInternal).append('/').append(qnameToString(name, namespaceContext)).toString();
            }
            sb = new StringBuilder();
            return sb.append(generateInternal).append('/').append(qnameToString(name, namespaceContext)).append('[').append(i).append(']').toString();
        }
        throw new IllegalStateException("Must have at least one child with name: " + name);
    }

    private static String qnameToString(QName qName, NamespaceContext namespaceContext) throws XPathGenerationException {
        String localPart = qName.getLocalPart();
        String namespaceURI = qName.getNamespaceURI();
        if (namespaceURI.length() == 0) {
            return localPart;
        }
        String prefix = qName.getPrefix();
        if (prefix != null && prefix.length() > 0 && namespaceURI.equals(namespaceContext.getNamespaceURI(prefix))) {
            return prefix + NameUtil.COLON + localPart;
        }
        String prefix2 = namespaceContext.getPrefix(namespaceURI);
        if (prefix2 == null) {
            throw new XPathGenerationException("Could not obtain a prefix for URI: " + namespaceURI);
        } else if (prefix2.length() != 0) {
            return prefix2 + NameUtil.COLON + localPart;
        } else {
            throw new XPathGenerationException("Can not use default prefix in XPath for URI: " + namespaceURI);
        }
    }

    private static int countTextTokens(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        xmlCursor.push();
        xmlCursor.toParent();
        XmlCursor.TokenType firstContentToken = xmlCursor.toFirstContentToken();
        int i = 0;
        int i2 = 0;
        while (!firstContentToken.isEnd()) {
            if (firstContentToken.isText()) {
                if (xmlCursor.comparePosition(newCursor) > 0) {
                    i++;
                } else {
                    i2++;
                }
            } else if (firstContentToken.isStart()) {
                xmlCursor.toEndToken();
            }
            firstContentToken = xmlCursor.toNextToken();
        }
        xmlCursor.pop();
        if (i == 0) {
            return 0;
        }
        return i2;
    }

    public static void main(String[] strArr) throws XmlException {
        AnonymousClass1 r5 = new NamespaceContext() {
            public String getPrefix(String str) {
                return null;
            }

            public Iterator getPrefixes(String str) {
                return null;
            }

            public String getNamespaceURI(String str) {
                if ("ns".equals(str)) {
                    return "http://a.com";
                }
                return null;
            }
        };
        XmlCursor newCursor = XmlObject.Factory.parse("<root>\n<ns:a xmlns:ns=\"http://a.com\"><b foo=\"value\">text1<c/>text2<c/>text3<c>text</c>text4</b></ns:a>\n</root>").newCursor();
        newCursor.toFirstContentToken();
        newCursor.toFirstContentToken();
        newCursor.toFirstChild();
        newCursor.toFirstChild();
        newCursor.push();
        System.out.println(generateXPath(newCursor, (XmlCursor) null, r5));
        newCursor.pop();
        newCursor.toNextSibling();
        newCursor.toNextSibling();
        newCursor.push();
        System.out.println(generateXPath(newCursor, (XmlCursor) null, r5));
        newCursor.pop();
        XmlCursor newCursor2 = newCursor.newCursor();
        newCursor2.toParent();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor2.toParent();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.toFirstContentToken();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.toParent();
        newCursor.toPrevToken();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.toParent();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.toFirstAttribute();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.toParent();
        newCursor.toParent();
        newCursor.toNextToken();
        newCursor.push();
        System.out.println(generateXPath(newCursor, newCursor2, r5));
        newCursor.pop();
        newCursor.push();
        System.out.println(generateXPath(newCursor, (XmlCursor) null, r5));
        newCursor.pop();
    }
}
