package org.apache.xmlbeans.impl.xpath;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.xpath.XPath;

class XPathCompilationContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int _column;
    private final String _currentNodeVar;
    private String _expr;
    private final Map<String, String> _externalNamespaces;
    private boolean _lastDeepDot;
    private int _line;
    protected final Map<String, String> _namespaces = new HashMap();
    private int _offset;
    private boolean _sawDeepDot;

    private void processNonXpathDecls() {
    }

    XPathCompilationContext(Map<String, String> map, String str) {
        this._currentNodeVar = str == null ? "$this" : str;
        this._externalNamespaces = map == null ? new HashMap<>() : map;
    }

    /* access modifiers changed from: package-private */
    public XPath compile(String str) throws XPath.XPathCompileException {
        this._offset = 0;
        this._line = 1;
        this._column = 1;
        this._expr = str;
        return tokenizeXPath();
    }

    /* access modifiers changed from: package-private */
    public int currChar() {
        return currChar(0);
    }

    /* access modifiers changed from: package-private */
    public int currChar(int i) {
        if (this._offset + i >= this._expr.length()) {
            return -1;
        }
        return this._expr.charAt(this._offset + i);
    }

    /* access modifiers changed from: package-private */
    public void advance() {
        if (this._offset < this._expr.length()) {
            char charAt = this._expr.charAt(this._offset);
            int i = this._offset + 1;
            this._offset = i;
            this._column++;
            if (charAt == 13 || charAt == 10) {
                this._line++;
                this._column = 1;
                if (i + 1 < this._expr.length()) {
                    char charAt2 = this._expr.charAt(this._offset + 1);
                    if ((charAt2 == 13 || charAt2 == 10) && charAt != charAt2) {
                        this._offset++;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void advance(int i) {
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                advance();
                i = i2;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isWhitespace() {
        return isWhitespace(0);
    }

    /* access modifiers changed from: package-private */
    public boolean isWhitespace(int i) {
        int currChar = currChar(i);
        return currChar == 32 || currChar == 9 || currChar == 10 || currChar == 13;
    }

    /* access modifiers changed from: package-private */
    public boolean isNCNameStart() {
        return currChar() != -1 && XMLChar.isNCNameStart(currChar());
    }

    /* access modifiers changed from: package-private */
    public boolean isNCName() {
        return currChar() != -1 && XMLChar.isNCName(currChar());
    }

    /* access modifiers changed from: package-private */
    public boolean startsWith(String str, int i) {
        if (this._offset + i >= this._expr.length()) {
            return false;
        }
        return this._expr.startsWith(str, this._offset + i);
    }

    private XPath.XPathCompileException newError(String str) {
        return new XPath.XPathCompileException(XmlError.forLocation(str, 0, (String) null, this._line, this._column, this._offset));
    }

    /* access modifiers changed from: package-private */
    public String lookupPrefix(String str) throws XPath.XPathCompileException {
        if (this._namespaces.containsKey(str)) {
            return this._namespaces.get(str);
        }
        if (this._externalNamespaces.containsKey(str)) {
            return this._externalNamespaces.get(str);
        }
        String str2 = str != null ? str : "";
        str2.hashCode();
        char c = 65535;
        switch (str2.hashCode()) {
            case 3272:
                if (str2.equals("fn")) {
                    c = 0;
                    break;
                }
                break;
            case 3835:
                if (str2.equals("xs")) {
                    c = 1;
                    break;
                }
                break;
            case 118536:
                if (str2.equals("xdt")) {
                    c = 2;
                    break;
                }
                break;
            case 118807:
                if (str2.equals("xml")) {
                    c = 3;
                    break;
                }
                break;
            case 118990:
                if (str2.equals("xsi")) {
                    c = 4;
                    break;
                }
                break;
            case 103145323:
                if (str2.equals(ImagesContract.LOCAL)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "http://www.w3.org/2002/11/xquery-functions";
            case 1:
                return "http://www.w3.org/2001/XMLSchema";
            case 2:
                return "http://www.w3.org/2003/11/xpath-datatypes";
            case 3:
                return "http://www.w3.org/XML/1998/namespace";
            case 4:
                return "http://www.w3.org/2001/XMLSchema-instance";
            case 5:
                return "http://www.w3.org/2003/11/xquery-local-functions";
            default:
                throw newError("Undefined prefix: " + str);
        }
    }

    private boolean parseWhitespace() {
        boolean z = false;
        while (isWhitespace()) {
            advance();
            z = true;
        }
        return z;
    }

    private boolean tokenize(String... strArr) {
        int i = 0;
        for (String str : strArr) {
            while (isWhitespace(i)) {
                i++;
            }
            if (!startsWith(str, i)) {
                return false;
            }
            i += str.length();
        }
        advance(i);
        return true;
    }

    private String tokenizeNCName() throws XPath.XPathCompileException {
        parseWhitespace();
        if (isNCNameStart()) {
            StringBuilder sb = new StringBuilder();
            sb.append((char) currChar());
            while (true) {
                advance();
                if (!isNCName()) {
                    return sb.toString();
                }
                sb.append((char) currChar());
            }
        } else {
            throw newError("Expected non-colonized name");
        }
    }

    private QName getAnyQName() {
        return new QName("", "");
    }

    private QName tokenizeQName() throws XPath.XPathCompileException {
        if (tokenize("*")) {
            return getAnyQName();
        }
        String str = tokenizeNCName();
        String str2 = "";
        if (!tokenize(ParameterizedMessage.ERROR_MSG_SEPARATOR)) {
            return new QName(lookupPrefix(str2), str);
        }
        String lookupPrefix = lookupPrefix(str);
        if (!tokenize("*")) {
            str2 = tokenizeNCName();
        }
        return new QName(lookupPrefix, str2);
    }

    private String tokenizeQuotedUri() throws XPath.XPathCompileException {
        int i;
        if (tokenize("\"")) {
            i = 34;
        } else if (tokenize("'")) {
            i = 39;
        } else {
            throw newError("Expected quote (\" or ')");
        }
        StringBuilder sb = new StringBuilder();
        while (currChar() != -1) {
            if (currChar() == i) {
                advance();
                if (currChar() != i) {
                    return sb.toString();
                }
            }
            sb.append((char) currChar());
            advance();
        }
        throw newError("Path terminated in URI literal");
    }

    private XPathStep addStep(boolean z, boolean z2, QName qName, XPathStep xPathStep) {
        XPathStep xPathStep2 = new XPathStep(z, z2, qName);
        if (xPathStep == null) {
            return xPathStep2;
        }
        XPathStep xPathStep3 = xPathStep;
        while (xPathStep3._next != null) {
            xPathStep3 = xPathStep3._next;
        }
        xPathStep3._next = xPathStep2;
        xPathStep2._prev = xPathStep3;
        return xPathStep;
    }

    private XPathStep tokenizeSteps() throws XPath.XPathCompileException {
        boolean z;
        if (!tokenize(PackagingURIHelper.FORWARD_SLASH_STRING)) {
            if (tokenize("$", this._currentNodeVar, "//") || tokenize(".", "//")) {
                z = true;
            } else {
                if (!tokenize("$", this._currentNodeVar, PackagingURIHelper.FORWARD_SLASH_STRING) && !tokenize(".", PackagingURIHelper.FORWARD_SLASH_STRING)) {
                    if (tokenize("$", this._currentNodeVar) || tokenize(".")) {
                        return addStep(false, false, (QName) null, (XPathStep) null);
                    }
                }
                z = false;
            }
            boolean z2 = false;
            XPathStep xPathStep = null;
            while (true) {
                if (tokenize("attribute", "::") || tokenize("@")) {
                    xPathStep = addStep(z, true, tokenizeQName(), xPathStep);
                } else {
                    if (tokenize(".")) {
                        z2 = z2 || z;
                    } else {
                        tokenize("child", "::");
                        xPathStep = addStep(z, false, tokenizeQName(), xPathStep);
                        z = false;
                    }
                    if (!tokenize("//")) {
                        if (!tokenize(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                            break;
                        } else if (!z2) {
                        }
                    } else {
                        z2 = false;
                    }
                    z = true;
                }
            }
            xPathStep = addStep(z, true, tokenizeQName(), xPathStep);
            this._lastDeepDot = z2;
            if (z2) {
                this._lastDeepDot = true;
                xPathStep = addStep(true, false, getAnyQName(), xPathStep);
            }
            return addStep(false, false, (QName) null, xPathStep);
        }
        throw newError("Absolute paths unsupported");
    }

    private void computeBacktrack(XPathStep xPathStep) {
        XPathStep xPathStep2;
        while (xPathStep != null) {
            XPathStep xPathStep3 = xPathStep._next;
            while (xPathStep3 != null && !xPathStep3._deep) {
                xPathStep3 = xPathStep3._next;
            }
            if (!xPathStep._deep) {
                while (xPathStep != xPathStep3) {
                    xPathStep._hasBacktrack = true;
                    xPathStep = xPathStep._next;
                }
            } else {
                int i = 0;
                XPathStep xPathStep4 = xPathStep;
                int i2 = 0;
                while (xPathStep4 != xPathStep3 && xPathStep4._name != null && !xPathStep4.isWild() && !xPathStep4._attr) {
                    i2++;
                    xPathStep4 = xPathStep4._next;
                }
                int i3 = i2 + 1;
                QName[] qNameArr = new QName[i3];
                int[] iArr = new int[i3];
                XPathStep xPathStep5 = xPathStep;
                for (int i4 = 0; i4 < i2; i4++) {
                    qNameArr[i4] = xPathStep5._name;
                    xPathStep5 = xPathStep5._next;
                }
                qNameArr[i2] = getAnyQName();
                iArr[0] = -1;
                int i5 = 0;
                int i6 = -1;
                while (i5 < i2) {
                    while (i6 > -1 && !qNameArr[i5].equals(qNameArr[i6])) {
                        i6 = iArr[i6];
                    }
                    i5++;
                    i6++;
                    iArr[i5] = qNameArr[i5].equals(qNameArr[i6]) ? iArr[i6] : i6;
                }
                for (XPathStep xPathStep6 = xPathStep; xPathStep6 != xPathStep4; xPathStep6 = xPathStep6._next) {
                    xPathStep6._hasBacktrack = true;
                    xPathStep6._backtrack = xPathStep;
                    for (int i7 = iArr[i]; i7 > 0; i7--) {
                        xPathStep6._backtrack = xPathStep6._backtrack._next;
                    }
                    i++;
                }
                if (i2 > 1) {
                    xPathStep2 = xPathStep;
                    for (int i8 = iArr[i2 - 1]; i8 > 0; i8--) {
                        xPathStep2 = xPathStep2._next;
                    }
                } else {
                    xPathStep2 = xPathStep;
                }
                if (xPathStep4 != xPathStep3 && xPathStep4._attr) {
                    xPathStep4._hasBacktrack = true;
                    xPathStep4._backtrack = xPathStep2;
                    xPathStep4 = xPathStep4._next;
                }
                if (xPathStep4 != xPathStep3 && xPathStep4._name == null) {
                    xPathStep4._hasBacktrack = true;
                    xPathStep4._backtrack = xPathStep2;
                }
                xPathStep._hasBacktrack = true;
                xPathStep._backtrack = xPathStep;
            }
            xPathStep = xPathStep3;
        }
    }

    private void tokenizePath(ArrayList<XPathStep> arrayList) throws XPath.XPathCompileException {
        this._lastDeepDot = false;
        XPathStep xPathStep = tokenizeSteps();
        computeBacktrack(xPathStep);
        arrayList.add(xPathStep);
        if (this._lastDeepDot) {
            this._sawDeepDot = true;
            XPathStep xPathStep2 = null;
            while (xPathStep != null) {
                xPathStep2 = addStep(xPathStep._deep, (xPathStep._next != null && xPathStep._next._next == null) || xPathStep._attr, xPathStep._name, xPathStep2);
                xPathStep = xPathStep._next;
            }
            computeBacktrack(xPathStep2);
            arrayList.add(xPathStep2);
        }
    }

    private XPath.Selector tokenizeSelector() throws XPath.XPathCompileException {
        ArrayList arrayList = new ArrayList();
        tokenizePath(arrayList);
        while (tokenize("|")) {
            tokenizePath(arrayList);
        }
        return new XPath.Selector((XPathStep[]) arrayList.toArray(new XPathStep[0]));
    }

    private XPath tokenizeXPath() throws XPath.XPathCompileException {
        while (true) {
            if (tokenize("declare", "namespace")) {
                if (parseWhitespace()) {
                    String str = tokenizeNCName();
                    if (tokenize("=")) {
                        String str2 = tokenizeQuotedUri();
                        if (!this._namespaces.containsKey(str)) {
                            this._namespaces.put(str, str2);
                            if (!this._externalNamespaces.containsKey(str)) {
                                this._externalNamespaces.put(str, str2);
                                tokenize(";");
                                this._externalNamespaces.put(XPath._NS_BOUNDARY, Integer.toString(this._offset));
                            } else {
                                throw newError("Redefinition of namespace prefix: " + str);
                            }
                        } else {
                            throw newError("Redefinition of namespace prefix: " + str);
                        }
                    } else {
                        throw newError("Expected '='");
                    }
                } else {
                    throw newError("Expected prefix after 'declare namespace'");
                }
            } else if (tokenize("declare", "default", "element", "namespace")) {
                String str3 = tokenizeQuotedUri();
                if (!this._namespaces.containsKey("")) {
                    this._namespaces.put("", str3);
                    if (!this._externalNamespaces.containsKey(XPath._DEFAULT_ELT_NS)) {
                        this._externalNamespaces.put(XPath._DEFAULT_ELT_NS, str3);
                        if (tokenize(";")) {
                            this._externalNamespaces.put(XPath._NS_BOUNDARY, Integer.toString(this._offset));
                        } else {
                            throw newError("Default Namespace declaration must end with ;");
                        }
                    } else {
                        throw newError("Redefinition of default element namespace : ");
                    }
                } else {
                    throw newError("Redefinition of default element namespace");
                }
            } else {
                if (!this._namespaces.containsKey("")) {
                    this._namespaces.put("", "");
                }
                XPath.Selector selector = tokenizeSelector();
                parseWhitespace();
                if (currChar() == -1) {
                    return new XPath(selector, this._sawDeepDot);
                }
                throw newError("Unexpected char '" + ((char) currChar()) + "'");
            }
        }
    }
}
