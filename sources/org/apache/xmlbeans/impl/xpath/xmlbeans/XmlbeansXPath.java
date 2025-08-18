package org.apache.xmlbeans.impl.xpath.xmlbeans;

import java.util.Map;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.xpath.Path;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathFactory;

public class XmlbeansXPath implements Path {
    private final XPath _compiledPath;
    private final String _currentVar;
    private final String _pathKey;

    public XmlbeansXPath(String str, String str2, XPath xPath) {
        this._pathKey = str;
        this._currentVar = str2;
        this._compiledPath = xPath;
    }

    public XPathEngine execute(Cur cur, XmlOptions xmlOptions) {
        XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
        if (!cur.isContainer() || this._compiledPath.sawDeepDot()) {
            return XPathFactory.getCompiledPathSaxon(this._pathKey, this._currentVar, (Map<String, String>) null).execute(cur, maskNull);
        }
        return new XmlbeansXPathEngine(this._compiledPath, cur);
    }
}
