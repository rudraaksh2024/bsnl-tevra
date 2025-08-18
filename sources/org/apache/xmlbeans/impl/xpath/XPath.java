package org.apache.xmlbeans.impl.xpath;

import java.util.Map;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;

public class XPath {
    public static final String _DEFAULT_ELT_NS = "$xmlbeans!default_uri";
    public static final String _NS_BOUNDARY = "$xmlbeans!ns_boundary";
    private final boolean _sawDeepDot;
    final Selector _selector;

    public static class XPathCompileException extends XmlException {
        XPathCompileException(XmlError xmlError) {
            super(xmlError.toString(), (Throwable) null, xmlError);
        }
    }

    public static XPath compileXPath(String str) throws XPathCompileException {
        return compileXPath(str, "$this", (Map<String, String>) null);
    }

    public static XPath compileXPath(String str, String str2) throws XPathCompileException {
        return compileXPath(str, str2, (Map<String, String>) null);
    }

    public static XPath compileXPath(String str, Map<String, String> map) throws XPathCompileException {
        return compileXPath(str, "$this", map);
    }

    public static XPath compileXPath(String str, String str2, Map<String, String> map) throws XPathCompileException {
        return new XPathCompilationContext(map, str2).compile(str);
    }

    static final class Selector {
        final XPathStep[] _paths;

        Selector(XPathStep[] xPathStepArr) {
            this._paths = xPathStepArr;
        }
    }

    XPath(Selector selector, boolean z) {
        this._selector = selector;
        this._sawDeepDot = z;
    }

    public boolean sawDeepDot() {
        return this._sawDeepDot;
    }
}
