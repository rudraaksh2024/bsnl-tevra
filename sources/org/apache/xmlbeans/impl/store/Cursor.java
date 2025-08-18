package org.apache.xmlbeans.impl.store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Saver;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public final class Cursor implements XmlCursor, Locale.ChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    private static final int COPY_CHARS = 5;
    private static final int COPY_XML = 1;
    private static final int COPY_XML_CONTENTS = 3;
    static final int ELEM = 2;
    private static final int MOVE_CHARS = 4;
    private static final int MOVE_XML = 0;
    private static final int MOVE_XML_CONTENTS = 2;
    static final int PROCINST = 5;
    static final int ROOT = 1;
    static final int TEXT = 0;
    private Cur _cur;
    private int _currentSelection;
    private Locale.ChangeListener _nextChangeListener;
    private XPathEngine _pathEngine;

    private interface WrapIOEx {
        void run() throws IOException;
    }

    private interface WrapSAXEx {
        void run() throws SAXException;
    }

    Cursor(Xobj xobj, int i) {
        Cur weakCur = xobj._locale.weakCur(this);
        this._cur = weakCur;
        weakCur.moveTo(xobj, i);
        this._currentSelection = -1;
    }

    public Cursor(Cur cur) {
        this(cur._xobj, cur._pos);
    }

    private static boolean isValid(Cur cur) {
        int kind;
        if (cur.kind() > 0) {
            return true;
        }
        cur.push();
        if (cur.toParentRaw() && ((kind = cur.kind()) == 4 || kind == 5 || kind == 3)) {
            return false;
        }
        cur.pop();
        return true;
    }

    private boolean isValid() {
        return isValid(this._cur);
    }

    /* access modifiers changed from: package-private */
    public Locale locale() {
        return this._cur._locale;
    }

    /* access modifiers changed from: package-private */
    public Cur tempCur() {
        return this._cur.tempCur();
    }

    public void dump(PrintStream printStream) {
        this._cur.dump(printStream);
    }

    static void validateLocalName(QName qName) {
        if (qName != null) {
            validateLocalName(qName.getLocalPart());
            return;
        }
        throw new IllegalArgumentException("QName is null");
    }

    static void validateLocalName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name is null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("Name is empty");
        } else if (!XMLChar.isValidNCName(str)) {
            throw new IllegalArgumentException("Name is not valid");
        }
    }

    static void validatePrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Prefix is null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("Prefix is empty");
        } else if (Locale.beginsWithXml(str)) {
            throw new IllegalArgumentException("Prefix begins with 'xml'");
        } else if (!XMLChar.isValidNCName(str)) {
            throw new IllegalArgumentException("Prefix is not valid");
        }
    }

    private static void complain(String str) {
        throw new IllegalArgumentException(str);
    }

    private void checkInsertionValidity(Cur cur) {
        int kind = cur.kind();
        if (kind < 0) {
            complain("Can't move/copy/insert an end token.");
        }
        if (kind == 1) {
            complain("Can't move/copy/insert a whole document.");
        }
        int kind2 = this._cur.kind();
        if (kind2 == 1) {
            complain("Can't insert before the start of the document.");
        }
        if (kind == 3) {
            this._cur.push();
            this._cur.prevWithAttrs();
            int kind3 = this._cur.kind();
            this._cur.pop();
            if (!(kind3 == 2 || kind3 == 1 || kind3 == -3)) {
                complain("Can only insert attributes before other attributes or after containers.");
            }
        }
        if (kind2 == 3 && kind != 3) {
            complain("Can only insert attributes before other attributes or after containers.");
        }
    }

    private void insertNode(Cur cur, String str) {
        if (str != null && str.length() > 0) {
            cur.next();
            cur.insertString(str);
            cur.toParent();
        }
        checkInsertionValidity(cur);
        cur.moveNode(this._cur);
        this._cur.toEnd();
        this._cur.nextWithAttrs();
    }

    public void _dispose() {
        this._cur.release();
        this._cur = null;
    }

    public XmlCursor _newCursor() {
        return new Cursor(this._cur);
    }

    public QName _getName() {
        int kind = this._cur.kind();
        if (kind != 2) {
            if (kind != 3) {
                if (kind != 5) {
                    return null;
                }
            } else if (this._cur.isXmlns()) {
                return this._cur._locale.makeQNameNoCheck(this._cur.getXmlnsUri(), this._cur.getXmlnsPrefix());
            }
        }
        return this._cur.getName();
    }

    /* renamed from: _setName */
    public void m2382lambda$setName$25$orgapachexmlbeansimplstoreCursor(QName qName) {
        if (qName != null) {
            int kind = this._cur.kind();
            if (kind == 2 || kind == 3) {
                validateLocalName(qName.getLocalPart());
            } else if (kind == 5) {
                validatePrefix(qName.getLocalPart());
                if (qName.getNamespaceURI().length() > 0) {
                    throw new IllegalArgumentException("Procinst name must have no URI");
                } else if (qName.getPrefix().length() > 0) {
                    throw new IllegalArgumentException("Procinst name must have no prefix");
                }
            } else {
                throw new IllegalStateException("Can set name on element, atrtribute and procinst only");
            }
            this._cur.setName(qName);
            return;
        }
        throw new IllegalArgumentException("Name is null");
    }

    public XmlCursor.TokenType _currentTokenType() {
        switch (this._cur.kind()) {
            case -2:
                return XmlCursor.TokenType.END;
            case -1:
                return XmlCursor.TokenType.ENDDOC;
            case 0:
                return XmlCursor.TokenType.TEXT;
            case 1:
                return XmlCursor.TokenType.STARTDOC;
            case 2:
                return XmlCursor.TokenType.START;
            case 3:
                return this._cur.isXmlns() ? XmlCursor.TokenType.NAMESPACE : XmlCursor.TokenType.ATTR;
            case 4:
                return XmlCursor.TokenType.COMMENT;
            case 5:
                return XmlCursor.TokenType.PROCINST;
            default:
                throw new IllegalStateException();
        }
    }

    public boolean _isStartdoc() {
        return this._cur.isRoot();
    }

    public boolean _isEnddoc() {
        return this._cur.isEndRoot();
    }

    public boolean _isStart() {
        return this._cur.isElem();
    }

    public boolean _isEnd() {
        return this._cur.isEnd();
    }

    public boolean _isText() {
        return this._cur.isText();
    }

    public boolean _isAttr() {
        return this._cur.isNormalAttr();
    }

    public boolean _isNamespace() {
        return this._cur.isXmlns();
    }

    public boolean _isComment() {
        return this._cur.isComment();
    }

    public boolean _isProcinst() {
        return this._cur.isProcinst();
    }

    public boolean _isContainer() {
        return this._cur.isContainer();
    }

    public boolean _isFinish() {
        return this._cur.isFinish();
    }

    public boolean _isAnyAttr() {
        return this._cur.isAttr();
    }

    public XmlCursor.TokenType _toNextToken() {
        int kind = this._cur.kind();
        if (kind == 1 || kind == 2) {
            if (!this._cur.toFirstAttr()) {
                this._cur.next();
            }
        } else if (kind != 3) {
            if (kind == 4 || kind == 5) {
                this._cur.skip();
            } else if (!this._cur.next()) {
                return XmlCursor.TokenType.NONE;
            }
        } else if (!this._cur.toNextSibling()) {
            this._cur.toParent();
            this._cur.next();
        }
        return _currentTokenType();
    }

    public XmlCursor.TokenType _toPrevToken() {
        boolean isText = this._cur.isText();
        if (this._cur.prev()) {
            int kind = this._cur.kind();
            if (kind == -4 || kind == -5 || kind == -3) {
                this._cur.toParent();
            } else if (this._cur.isContainer()) {
                this._cur.toLastAttr();
            } else if (isText && this._cur.isText()) {
                return _toPrevToken();
            }
        } else if (this._cur.isRoot()) {
            return XmlCursor.TokenType.NONE;
        } else {
            this._cur.toParent();
        }
        return _currentTokenType();
    }

    public Object _monitor() {
        return this._cur._locale;
    }

    public boolean _toParent() {
        Cur tempCur = this._cur.tempCur();
        if (!tempCur.toParent()) {
            return false;
        }
        this._cur.moveToCur(tempCur);
        tempCur.release();
        return true;
    }

    private static final class ChangeStampImpl implements XmlCursor.ChangeStamp {
        private final Locale _locale;
        private final long _versionStamp;

        ChangeStampImpl(Locale locale) {
            this._locale = locale;
            this._versionStamp = locale.version();
        }

        public boolean hasChanged() {
            return this._versionStamp != this._locale.version();
        }
    }

    public XmlCursor.ChangeStamp _getDocChangeStamp() {
        return new ChangeStampImpl(this._cur._locale);
    }

    public XMLStreamReader _newXMLStreamReader() {
        return m2366lambda$newXMLStreamReader$6$orgapachexmlbeansimplstoreCursor((XmlOptions) null);
    }

    public Node _newDomNode() {
        return m2363lambda$newDomNode$14$orgapachexmlbeansimplstoreCursor((XmlOptions) null);
    }

    public InputStream _newInputStream() {
        return m2364lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor((XmlOptions) null);
    }

    public String _xmlText() {
        return m2401lambda$xmlText$11$orgapachexmlbeansimplstoreCursor((XmlOptions) null);
    }

    public Reader _newReader() {
        return m2365lambda$newReader$13$orgapachexmlbeansimplstoreCursor((XmlOptions) null);
    }

    /* renamed from: _save */
    public void m2376lambda$save$8$orgapachexmlbeansimplstoreCursor(File file) throws IOException {
        m2372lambda$save$16$orgapachexmlbeansimplstoreCursor(file, (XmlOptions) null);
    }

    /* renamed from: _save */
    public void m2377lambda$save$9$orgapachexmlbeansimplstoreCursor(OutputStream outputStream) throws IOException {
        m2373lambda$save$17$orgapachexmlbeansimplstoreCursor(outputStream, (XmlOptions) null);
    }

    /* renamed from: _save */
    public void m2370lambda$save$10$orgapachexmlbeansimplstoreCursor(Writer writer) throws IOException {
        m2374lambda$save$18$orgapachexmlbeansimplstoreCursor(writer, (XmlOptions) null);
    }

    /* renamed from: _save */
    public void m2375lambda$save$7$orgapachexmlbeansimplstoreCursor(ContentHandler contentHandler, LexicalHandler lexicalHandler) throws SAXException {
        m2371lambda$save$15$orgapachexmlbeansimplstoreCursor(contentHandler, lexicalHandler, (XmlOptions) null);
    }

    public XmlDocumentProperties _documentProperties() {
        return Locale.getDocProps(this._cur, true);
    }

    /* renamed from: _newXMLStreamReader */
    public XMLStreamReader m2366lambda$newXMLStreamReader$6$orgapachexmlbeansimplstoreCursor(XmlOptions xmlOptions) {
        return Jsr173.newXmlStreamReader(this._cur, xmlOptions);
    }

    /* renamed from: _xmlText */
    public String m2401lambda$xmlText$11$orgapachexmlbeansimplstoreCursor(XmlOptions xmlOptions) {
        return new Saver.TextSaver(this._cur, xmlOptions, (String) null).saveToString();
    }

    /* renamed from: _newInputStream */
    public InputStream m2364lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(XmlOptions xmlOptions) {
        return new Saver.InputStreamSaver(this._cur, xmlOptions);
    }

    /* renamed from: _newReader */
    public Reader m2365lambda$newReader$13$orgapachexmlbeansimplstoreCursor(XmlOptions xmlOptions) {
        return new Saver.TextReader(this._cur, xmlOptions);
    }

    /* renamed from: _save */
    public void m2371lambda$save$15$orgapachexmlbeansimplstoreCursor(ContentHandler contentHandler, LexicalHandler lexicalHandler, XmlOptions xmlOptions) throws SAXException {
        new Saver.SaxSaver(this._cur, xmlOptions, contentHandler, lexicalHandler);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r2 = move-exception;
     */
    /* renamed from: _save */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2372lambda$save$16$orgapachexmlbeansimplstoreCursor(java.io.File r2, org.apache.xmlbeans.XmlOptions r3) throws java.io.IOException {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x001a
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r2)
            r1.m2373lambda$save$17$orgapachexmlbeansimplstoreCursor((java.io.OutputStream) r0, (org.apache.xmlbeans.XmlOptions) r3)     // Catch:{ all -> 0x000e }
            r0.close()
            return
        L_0x000e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0015 }
            goto L_0x0019
        L_0x0015:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0019:
            throw r2
        L_0x001a:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Null file specified"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cursor.m2372lambda$save$16$orgapachexmlbeansimplstoreCursor(java.io.File, org.apache.xmlbeans.XmlOptions):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        if (r2 != null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0024, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0025, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0028, code lost:
        throw r4;
     */
    /* renamed from: _save */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2373lambda$save$17$orgapachexmlbeansimplstoreCursor(java.io.OutputStream r3, org.apache.xmlbeans.XmlOptions r4) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0029
            java.io.InputStream r2 = r2.m2364lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(r4)
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x001b }
        L_0x000a:
            int r0 = r2.read(r4)     // Catch:{ all -> 0x001b }
            if (r0 >= 0) goto L_0x0016
            if (r2 == 0) goto L_0x0015
            r2.close()
        L_0x0015:
            return
        L_0x0016:
            r1 = 0
            r3.write(r4, r1, r0)     // Catch:{ all -> 0x001b }
            goto L_0x000a
        L_0x001b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001d }
        L_0x001d:
            r4 = move-exception
            if (r2 == 0) goto L_0x0028
            r2.close()     // Catch:{ all -> 0x0024 }
            goto L_0x0028
        L_0x0024:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0028:
            throw r4
        L_0x0029:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Null OutputStream specified"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cursor.m2373lambda$save$17$orgapachexmlbeansimplstoreCursor(java.io.OutputStream, org.apache.xmlbeans.XmlOptions):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        if (r2 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0032, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0033, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0036, code lost:
        throw r4;
     */
    /* renamed from: _save */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2374lambda$save$18$orgapachexmlbeansimplstoreCursor(java.io.Writer r3, org.apache.xmlbeans.XmlOptions r4) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0037
            if (r4 == 0) goto L_0x0010
            boolean r0 = r4.isSaveOptimizeForSpeed()
            if (r0 == 0) goto L_0x0010
            org.apache.xmlbeans.impl.store.Cur r2 = r2._cur
            org.apache.xmlbeans.impl.store.Saver.OptimizedForSpeedSaver.save(r2, r3)
            return
        L_0x0010:
            java.io.Reader r2 = r2.m2365lambda$newReader$13$orgapachexmlbeansimplstoreCursor(r4)
            r4 = 8192(0x2000, float:1.14794E-41)
            char[] r4 = new char[r4]     // Catch:{ all -> 0x0029 }
        L_0x0018:
            int r0 = r2.read(r4)     // Catch:{ all -> 0x0029 }
            if (r0 >= 0) goto L_0x0024
            if (r2 == 0) goto L_0x0023
            r2.close()
        L_0x0023:
            return
        L_0x0024:
            r1 = 0
            r3.write(r4, r1, r0)     // Catch:{ all -> 0x0029 }
            goto L_0x0018
        L_0x0029:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002b }
        L_0x002b:
            r4 = move-exception
            if (r2 == 0) goto L_0x0036
            r2.close()     // Catch:{ all -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0036:
            throw r4
        L_0x0037:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Null Writer specified"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cursor.m2374lambda$save$18$orgapachexmlbeansimplstoreCursor(java.io.Writer, org.apache.xmlbeans.XmlOptions):void");
    }

    public Node _getDomNode() {
        return (Node) this._cur.getDom();
    }

    private boolean isDomFragment() {
        if (!isStartdoc()) {
            return true;
        }
        XmlCursor newCursor = newCursor();
        int intValue = newCursor.toNextToken().intValue();
        boolean z = false;
        while (true) {
            switch (intValue) {
                case 0:
                case 1:
                case 2:
                    newCursor.dispose();
                    return !z;
                case 3:
                    if (!z) {
                        intValue = newCursor.toEndToken().intValue();
                        z = true;
                        break;
                    } else {
                        newCursor.dispose();
                        return true;
                    }
                case 4:
                case 8:
                case 9:
                    intValue = newCursor.toNextToken().intValue();
                    break;
                case 5:
                    try {
                        if (Locale.isWhiteSpace(newCursor.getChars())) {
                            intValue = newCursor.toNextToken().intValue();
                            break;
                        } else {
                            return true;
                        }
                    } finally {
                        newCursor.dispose();
                    }
                case 6:
                case 7:
                    newCursor.dispose();
                    return true;
            }
        }
    }

    /* renamed from: _newDomNode */
    public Node m2363lambda$newDomNode$14$orgapachexmlbeansimplstoreCursor(XmlOptions xmlOptions) {
        if (xmlOptions != null && xmlOptions.isSaveInner()) {
            XmlOptions xmlOptions2 = new XmlOptions(xmlOptions);
            xmlOptions2.setSaveInner(false);
            xmlOptions = xmlOptions2;
        }
        return new DomSaver(this._cur, isDomFragment(), xmlOptions).saveDom();
    }

    public boolean _toCursor(Cursor cursor) {
        this._cur.moveToCur(cursor._cur);
        return true;
    }

    public void _push() {
        this._cur.push();
    }

    public boolean _pop() {
        return this._cur.pop();
    }

    public void notifyChange() {
        if (this._cur != null) {
            _getSelectionCount();
        }
    }

    public void setNextChangeListener(Locale.ChangeListener changeListener) {
        this._nextChangeListener = changeListener;
    }

    public Locale.ChangeListener getNextChangeListener() {
        return this._nextChangeListener;
    }

    /* renamed from: _selectPath */
    public void m2378lambda$selectPath$19$orgapachexmlbeansimplstoreCursor(String str) {
        m2379lambda$selectPath$20$orgapachexmlbeansimplstoreCursor(str, (XmlOptions) null);
    }

    /* renamed from: _selectPath */
    public void m2379lambda$selectPath$20$orgapachexmlbeansimplstoreCursor(String str, XmlOptions xmlOptions) {
        _clearSelections();
        this._pathEngine = XPathFactory.getCompiledPath(str, xmlOptions).execute(this._cur, xmlOptions);
        this._cur._locale.registerForChange(this);
    }

    public boolean _hasNextSelection() {
        int i = this._currentSelection;
        push();
        try {
            return _toNextSelection();
        } finally {
            this._currentSelection = i;
            pop();
        }
    }

    public boolean _toNextSelection() {
        return _toSelection(this._currentSelection + 1);
    }

    public boolean _toSelection(int i) {
        if (i < 0) {
            return false;
        }
        while (i >= this._cur.selectionCount()) {
            XPathEngine xPathEngine = this._pathEngine;
            if (xPathEngine == null) {
                return false;
            }
            if (!xPathEngine.next(this._cur)) {
                this._pathEngine.release();
                this._pathEngine = null;
                return false;
            }
        }
        Cur cur = this._cur;
        this._currentSelection = i;
        cur.moveToSelection(i);
        return true;
    }

    public int _getSelectionCount() {
        _toSelection(Integer.MAX_VALUE);
        return this._cur.selectionCount();
    }

    public void _addToSelection() {
        _toSelection(Integer.MAX_VALUE);
        this._cur.addToSelection();
    }

    public void _clearSelections() {
        XPathEngine xPathEngine = this._pathEngine;
        if (xPathEngine != null) {
            xPathEngine.release();
            this._pathEngine = null;
        }
        this._cur.clearSelection();
        this._currentSelection = -1;
    }

    /* renamed from: _namespaceForPrefix */
    public String m2362lambda$namespaceForPrefix$26$orgapachexmlbeansimplstoreCursor(String str) {
        if (this._cur.isContainer()) {
            return this._cur.namespaceForPrefix(str, true);
        }
        throw new IllegalStateException("Not on a container");
    }

    /* renamed from: _prefixForNamespace */
    public String m2367lambda$prefixForNamespace$27$orgapachexmlbeansimplstoreCursor(String str) {
        if (str != null && str.length() != 0) {
            return this._cur.prefixForNamespace(str, (String) null, true);
        }
        throw new IllegalArgumentException("Must specify a namespace");
    }

    /* renamed from: _getAllNamespaces */
    public void m2338lambda$getAllNamespaces$28$orgapachexmlbeansimplstoreCursor(Map<String, String> map) {
        if (!this._cur.isContainer()) {
            throw new IllegalStateException("Not on a container");
        } else if (map != null) {
            Locale.getAllNamespaces(this._cur, map);
        }
    }

    public XmlObject _getObject() {
        return this._cur.getObject();
    }

    public XmlCursor.TokenType _prevTokenType() {
        this._cur.push();
        XmlCursor.TokenType _toPrevToken = _toPrevToken();
        this._cur.pop();
        return _toPrevToken;
    }

    public boolean _hasNextToken() {
        return (this._cur._pos == -1 && this._cur._xobj.kind() == 1) ? false : true;
    }

    public boolean _hasPrevToken() {
        return this._cur.kind() != 1;
    }

    public XmlCursor.TokenType _toFirstContentToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.next();
        return currentTokenType();
    }

    public XmlCursor.TokenType _toEndToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.toEnd();
        return currentTokenType();
    }

    public boolean _toChild(String str) {
        return _toChild((String) null, str);
    }

    public boolean _toChild(QName qName) {
        return _toChild(qName, 0);
    }

    public boolean _toChild(int i) {
        return _toChild((QName) null, i);
    }

    public boolean _toChild(String str, String str2) {
        validateLocalName(str2);
        return _toChild(this._cur._locale.makeQName(str, str2), 0);
    }

    public boolean _toChild(QName qName, int i) {
        return Locale.toChild(this._cur, qName, i);
    }

    public int _toNextChar(int i) {
        return this._cur.nextChars(i);
    }

    public int _toPrevChar(int i) {
        return this._cur.prevChars(i);
    }

    public boolean _toPrevSibling() {
        return Locale.toPrevSiblingElement(this._cur);
    }

    public boolean _toLastChild() {
        return Locale.toLastChildElement(this._cur);
    }

    public boolean _toFirstChild() {
        return Locale.toFirstChildElement(this._cur);
    }

    public boolean _toNextSibling(String str) {
        return _toNextSibling(new QName(str));
    }

    public boolean _toNextSibling(String str, String str2) {
        validateLocalName(str2);
        return _toNextSibling(this._cur._locale._qnameFactory.getQName(str, str2));
    }

    public boolean _toNextSibling(QName qName) {
        this._cur.push();
        while (___toNextSibling()) {
            if (this._cur.getName().equals(qName)) {
                this._cur.popButStay();
                return true;
            }
        }
        this._cur.pop();
        return false;
    }

    public boolean _toFirstAttribute() {
        return this._cur.isContainer() && Locale.toFirstNormalAttr(this._cur);
    }

    public boolean _toLastAttribute() {
        if (this._cur.isContainer()) {
            this._cur.push();
            this._cur.push();
            boolean z = false;
            while (this._cur.toNextAttr()) {
                if (this._cur.isNormalAttr()) {
                    this._cur.popButStay();
                    this._cur.push();
                    z = true;
                }
            }
            this._cur.pop();
            if (z) {
                this._cur.popButStay();
                return true;
            }
            this._cur.pop();
        }
        return false;
    }

    public boolean _toNextAttribute() {
        return this._cur.isAttr() && Locale.toNextNormalAttr(this._cur);
    }

    public boolean _toPrevAttribute() {
        return this._cur.isAttr() && Locale.toPrevNormalAttr(this._cur);
    }

    /* renamed from: _getAttributeText */
    public String m2339lambda$getAttributeText$39$orgapachexmlbeansimplstoreCursor(QName qName) {
        if (qName == null) {
            throw new IllegalArgumentException("Attr name is null");
        } else if (!this._cur.isContainer()) {
            return null;
        } else {
            return this._cur.getAttrValue(qName);
        }
    }

    public boolean _setAttributeText(QName qName, String str) {
        if (qName != null) {
            validateLocalName(qName.getLocalPart());
            if (!this._cur.isContainer()) {
                return false;
            }
            this._cur.setAttrValue(qName, str);
            return true;
        }
        throw new IllegalArgumentException("Attr name is null");
    }

    public boolean _removeAttribute(QName qName) {
        if (qName == null) {
            throw new IllegalArgumentException("Attr name is null");
        } else if (!this._cur.isContainer()) {
            return false;
        } else {
            return this._cur.removeAttr(qName);
        }
    }

    public String _getTextValue() {
        if (this._cur.isText()) {
            return _getChars();
        }
        if (this._cur.isNode()) {
            boolean hasChildren = this._cur.hasChildren();
            Cur cur = this._cur;
            return hasChildren ? Locale.getTextValue(cur) : cur.getValueAsString();
        }
        throw new IllegalStateException("Can't get text value, current token can have no text value");
    }

    public int _getTextValue(char[] cArr, int i, int i2) {
        if (this._cur.isText()) {
            return _getChars(cArr, i, i2);
        }
        if (cArr == null) {
            throw new IllegalArgumentException("char buffer is null");
        } else if (i < 0) {
            throw new IllegalArgumentException("offset < 0");
        } else if (i < cArr.length) {
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            if (i + i2 > cArr.length) {
                i2 = cArr.length - i;
            }
            if (!this._cur.isNode()) {
                throw new IllegalStateException("Can't get text value, current token can have no text value");
            } else if (this._cur.hasChildren()) {
                return Locale.getTextValue(this._cur, cArr, i, i2);
            } else {
                Object firstChars = this._cur.getFirstChars();
                if (this._cur._cchSrc > i2) {
                    this._cur._cchSrc = i2;
                }
                if (this._cur._cchSrc <= 0) {
                    return 0;
                }
                CharUtil.getChars(cArr, i, firstChars, this._cur._offSrc, this._cur._cchSrc);
                return this._cur._cchSrc;
            }
        } else {
            throw new IllegalArgumentException("offset off end");
        }
    }

    private void setTextValue(Object obj, int i, int i2) {
        if (this._cur.isNode()) {
            this._cur.moveNodeContents((Cur) null, false);
            this._cur.next();
            this._cur.insertChars(obj, i, i2);
            this._cur.toParent();
            return;
        }
        throw new IllegalStateException("Can't set text value, current token can have no text value");
    }

    /* renamed from: _setTextValue */
    public void m2383lambda$setTextValue$43$orgapachexmlbeansimplstoreCursor(String str) {
        if (str == null) {
            str = "";
        }
        setTextValue((Object) str, 0, str.length());
    }

    /* renamed from: _setTextValue */
    public void m2384lambda$setTextValue$44$orgapachexmlbeansimplstoreCursor(char[] cArr, int i, int i2) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("setTextValue: length < 0");
        } else if (cArr == null) {
            if (i2 <= 0) {
                setTextValue((char[]) null, 0, 0);
                return;
            }
            throw new IllegalArgumentException("setTextValue: sourceChars == null");
        } else if (i < 0 || i >= cArr.length) {
            throw new IndexOutOfBoundsException("setTextValue: offset out of bounds");
        } else {
            if (i + i2 > cArr.length) {
                i2 = cArr.length - i;
            }
            CharUtil charUtil = this._cur._locale.getCharUtil();
            setTextValue(charUtil.saveChars(cArr, i, i2), charUtil._offSrc, charUtil._cchSrc);
        }
    }

    public String _getChars() {
        return this._cur.getCharsAsString();
    }

    public int _getChars(char[] cArr, int i, int i2) {
        int cchRight = this._cur.cchRight();
        if (i2 < 0 || i2 > cchRight) {
            i2 = cchRight;
        }
        if (cArr == null || i >= cArr.length) {
            return 0;
        }
        if (cArr.length - i < i2) {
            i2 = cArr.length - i;
        }
        CharUtil.getChars(cArr, i, this._cur.getChars(i2), this._cur._offSrc, this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public void _toStartDoc() {
        this._cur.toRoot();
    }

    public void _toEndDoc() {
        _toStartDoc();
        this._cur.toEnd();
    }

    public int _comparePosition(Cursor cursor) {
        int comparePosition = this._cur.comparePosition(cursor._cur);
        if (comparePosition != 2) {
            return comparePosition;
        }
        throw new IllegalArgumentException("Cursors not in same document");
    }

    public boolean _isLeftOf(Cursor cursor) {
        return _comparePosition(cursor) < 0;
    }

    public boolean _isAtSamePositionAs(Cursor cursor) {
        return this._cur.isSamePos(cursor._cur);
    }

    public boolean _isRightOf(Cursor cursor) {
        return _comparePosition(cursor) > 0;
    }

    /* renamed from: _execQuery */
    public XmlCursor m2335lambda$execQuery$46$orgapachexmlbeansimplstoreCursor(String str) {
        return m2336lambda$execQuery$47$orgapachexmlbeansimplstoreCursor(str, (XmlOptions) null);
    }

    /* renamed from: _execQuery */
    public XmlCursor m2336lambda$execQuery$47$orgapachexmlbeansimplstoreCursor(String str, XmlOptions xmlOptions) {
        checkThisCursor();
        return XPathFactory.cursorExecQuery(this._cur, str, xmlOptions);
    }

    public boolean _toBookmark(XmlCursor.XmlBookmark xmlBookmark) {
        if (xmlBookmark != null && (xmlBookmark._currentMark instanceof Bookmark)) {
            Bookmark bookmark = (Bookmark) xmlBookmark._currentMark;
            if (bookmark._xobj != null && bookmark._xobj._locale == this._cur._locale) {
                this._cur.moveTo(bookmark._xobj, bookmark._pos);
                return true;
            }
        }
        return false;
    }

    /* renamed from: _toNextBookmark */
    public XmlCursor.XmlBookmark m2392lambda$toNextBookmark$23$orgapachexmlbeansimplstoreCursor(Object obj) {
        if (obj == null) {
            return null;
        }
        this._cur.push();
        do {
            int cchRight = this._cur.cchRight();
            if (cchRight > 1) {
                this._cur.nextChars(1);
                Cur cur = this._cur;
                int firstBookmarkInChars = cur.firstBookmarkInChars(obj, cchRight - 1);
                if (firstBookmarkInChars < 0) {
                    firstBookmarkInChars = -1;
                }
                cur.nextChars(firstBookmarkInChars);
            } else if (_toNextToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bookmark = getBookmark(obj, this._cur);
            if (bookmark != null) {
                this._cur.popButStay();
                return bookmark;
            }
        } while (this._cur.kind() != -1);
        this._cur.pop();
        return null;
    }

    /* renamed from: _toPrevBookmark */
    public XmlCursor.XmlBookmark m2397lambda$toPrevBookmark$24$orgapachexmlbeansimplstoreCursor(Object obj) {
        if (obj == null) {
            return null;
        }
        this._cur.push();
        do {
            int cchLeft = this._cur.cchLeft();
            if (cchLeft > 1) {
                this._cur.prevChars(1);
                Cur cur = this._cur;
                int firstBookmarkInCharsLeft = cur.firstBookmarkInCharsLeft(obj, cchLeft - 1);
                if (firstBookmarkInCharsLeft < 0) {
                    firstBookmarkInCharsLeft = -1;
                }
                cur.prevChars(firstBookmarkInCharsLeft);
            } else if (cchLeft == 1) {
                this._cur.prevChars(1);
            } else if (_toPrevToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bookmark = getBookmark(obj, this._cur);
            if (bookmark != null) {
                this._cur.popButStay();
                return bookmark;
            }
        } while (this._cur.kind() != 1);
        this._cur.pop();
        return null;
    }

    /* renamed from: _setBookmark */
    public void m2381lambda$setBookmark$48$orgapachexmlbeansimplstoreCursor(XmlCursor.XmlBookmark xmlBookmark) {
        if (xmlBookmark == null) {
            return;
        }
        if (xmlBookmark.getKey() != null) {
            xmlBookmark._currentMark = this._cur.setBookmark(xmlBookmark.getKey(), xmlBookmark);
            return;
        }
        throw new IllegalArgumentException("Annotation key is null");
    }

    static XmlCursor.XmlBookmark getBookmark(Object obj, Cur cur) {
        if (obj == null) {
            return null;
        }
        Object bookmark = cur.getBookmark(obj);
        if (bookmark instanceof XmlCursor.XmlBookmark) {
            return (XmlCursor.XmlBookmark) bookmark;
        }
        return null;
    }

    /* renamed from: _getBookmark */
    public XmlCursor.XmlBookmark m2340lambda$getBookmark$49$orgapachexmlbeansimplstoreCursor(Object obj) {
        if (obj == null) {
            return null;
        }
        return getBookmark(obj, this._cur);
    }

    /* renamed from: _clearBookmark */
    public void m2333lambda$clearBookmark$50$orgapachexmlbeansimplstoreCursor(Object obj) {
        if (obj != null) {
            this._cur.setBookmark(obj, (Object) null);
        }
    }

    /* renamed from: _getAllBookmarkRefs */
    public void m2337lambda$getAllBookmarkRefs$51$orgapachexmlbeansimplstoreCursor(Collection collection) {
        if (collection != null) {
            for (Bookmark bookmark = this._cur._xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
                if (bookmark._value instanceof XmlCursor.XmlBookmark) {
                    collection.add(bookmark._value);
                }
            }
        }
    }

    public boolean _removeXml() {
        if (this._cur.isRoot()) {
            throw new IllegalStateException("Can't remove a whole document.");
        } else if (this._cur.isFinish()) {
            return false;
        } else {
            if (this._cur.isText()) {
                this._cur.moveChars((Cur) null, -1);
                return true;
            }
            this._cur.moveNode((Cur) null);
            return true;
        }
    }

    public boolean _moveXml(Cursor cursor) {
        cursor.checkInsertionValidity(this._cur);
        if (this._cur.isText()) {
            int cchRight = this._cur.cchRight();
            if (this._cur.inChars(cursor._cur, cchRight, true)) {
                return false;
            }
            this._cur.moveChars(cursor._cur, cchRight);
            cursor._cur.nextChars(cchRight);
            return true;
        } else if (this._cur.contains(cursor._cur)) {
            return false;
        } else {
            Cur tempCur = cursor.tempCur();
            this._cur.moveNode(cursor._cur);
            cursor._cur.moveToCur(tempCur);
            tempCur.release();
            return true;
        }
    }

    public boolean _copyXml(Cursor cursor) {
        cursor.checkInsertionValidity(this._cur);
        Cur tempCur = cursor.tempCur();
        if (this._cur.isText()) {
            cursor._cur.insertChars(this._cur.getChars(-1), this._cur._offSrc, this._cur._cchSrc);
        } else {
            this._cur.copyNode(cursor._cur);
        }
        cursor._cur.moveToCur(tempCur);
        tempCur.release();
        return true;
    }

    public boolean _removeXmlContents() {
        if (!this._cur.isContainer()) {
            return false;
        }
        this._cur.moveNodeContents((Cur) null, false);
        return true;
    }

    private boolean checkContentInsertionValidity(Cursor cursor) {
        this._cur.push();
        this._cur.next();
        if (this._cur.isFinish()) {
            this._cur.pop();
            return false;
        }
        try {
            cursor.checkInsertionValidity(this._cur);
            this._cur.pop();
            return true;
        } catch (IllegalArgumentException e) {
            this._cur.pop();
            throw e;
        }
    }

    public boolean _moveXmlContents(Cursor cursor) {
        if (!this._cur.isContainer() || this._cur.contains(cursor._cur) || !checkContentInsertionValidity(cursor)) {
            return false;
        }
        Cur tempCur = cursor.tempCur();
        this._cur.moveNodeContents(cursor._cur, false);
        cursor._cur.moveToCur(tempCur);
        tempCur.release();
        return true;
    }

    public boolean _copyXmlContents(Cursor cursor) {
        if (!this._cur.isContainer() || this._cur.contains(cursor._cur) || !checkContentInsertionValidity(cursor)) {
            return false;
        }
        Cur tempCur = this._cur._locale.tempCur();
        this._cur.copyNode(tempCur);
        Cur tempCur2 = cursor._cur.tempCur();
        tempCur.moveNodeContents(cursor._cur, false);
        tempCur.release();
        cursor._cur.moveToCur(tempCur2);
        tempCur2.release();
        return true;
    }

    public int _removeChars(int i) {
        int cchRight = this._cur.cchRight();
        if (cchRight == 0 || i == 0) {
            return 0;
        }
        if (i < 0 || i > cchRight) {
            i = cchRight;
        }
        this._cur.moveChars((Cur) null, i);
        return this._cur._cchSrc;
    }

    public int _moveChars(int i, Cursor cursor) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || i == 0) {
            return 0;
        }
        if (i < 0 || i > cchRight) {
            i = cchRight;
        }
        cursor.checkInsertionValidity(this._cur);
        this._cur.moveChars(cursor._cur, i);
        cursor._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public int _copyChars(int i, Cursor cursor) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || i == 0) {
            return 0;
        }
        if (i < 0 || i > cchRight) {
            i = cchRight;
        }
        cursor.checkInsertionValidity(this._cur);
        cursor._cur.insertChars(this._cur.getChars(i), this._cur._offSrc, this._cur._cchSrc);
        cursor._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    /* renamed from: _insertChars */
    public void m2349lambda$insertChars$53$orgapachexmlbeansimplstoreCursor(String str) {
        int length = str == null ? 0 : str.length();
        if (length <= 0) {
            return;
        }
        if (this._cur.isRoot() || this._cur.isAttr()) {
            throw new IllegalStateException("Can't insert before the document or an attribute.");
        }
        this._cur.insertChars(str, 0, length);
        this._cur.nextChars(length);
    }

    /* renamed from: _beginElement */
    public void m2331lambda$beginElement$58$orgapachexmlbeansimplstoreCursor(String str) {
        m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(str, (String) null, (String) null);
        _toPrevToken();
    }

    /* renamed from: _beginElement */
    public void m2332lambda$beginElement$59$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(str, str2, (String) null);
        _toPrevToken();
    }

    /* renamed from: _beginElement */
    public void m2330lambda$beginElement$57$orgapachexmlbeansimplstoreCursor(QName qName) {
        m2354lambda$insertElementWithText$60$orgapachexmlbeansimplstoreCursor(qName, (String) null);
        _toPrevToken();
    }

    /* renamed from: _insertElement */
    public void m2352lambda$insertElement$55$orgapachexmlbeansimplstoreCursor(String str) {
        m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(str, (String) null, (String) null);
    }

    /* renamed from: _insertElement */
    public void m2353lambda$insertElement$56$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(str, str2, (String) null);
    }

    /* renamed from: _insertElement */
    public void m2351lambda$insertElement$54$orgapachexmlbeansimplstoreCursor(QName qName) {
        m2354lambda$insertElementWithText$60$orgapachexmlbeansimplstoreCursor(qName, (String) null);
    }

    /* renamed from: _insertElementWithText */
    public void m2355lambda$insertElementWithText$61$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(str, (String) null, str2);
    }

    /* renamed from: _insertElementWithText */
    public void m2356lambda$insertElementWithText$62$orgapachexmlbeansimplstoreCursor(String str, String str2, String str3) {
        validateLocalName(str);
        m2354lambda$insertElementWithText$60$orgapachexmlbeansimplstoreCursor(this._cur._locale.makeQName(str2, str), str3);
    }

    /* renamed from: _insertElementWithText */
    public void m2354lambda$insertElementWithText$60$orgapachexmlbeansimplstoreCursor(QName qName, String str) {
        validateLocalName(qName.getLocalPart());
        Cur tempCur = this._cur._locale.tempCur();
        tempCur.createElement(qName);
        insertNode(tempCur, str);
        tempCur.release();
    }

    /* renamed from: _insertAttribute */
    public void m2343lambda$insertAttribute$63$orgapachexmlbeansimplstoreCursor(String str) {
        m2346lambda$insertAttributeWithValue$66$orgapachexmlbeansimplstoreCursor(str, (String) null);
    }

    /* renamed from: _insertAttribute */
    public void m2344lambda$insertAttribute$64$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2347lambda$insertAttributeWithValue$67$orgapachexmlbeansimplstoreCursor(str, str2, (String) null);
    }

    /* renamed from: _insertAttribute */
    public void m2345lambda$insertAttribute$65$orgapachexmlbeansimplstoreCursor(QName qName) {
        m2348lambda$insertAttributeWithValue$68$orgapachexmlbeansimplstoreCursor(qName, (String) null);
    }

    /* renamed from: _insertAttributeWithValue */
    public void m2346lambda$insertAttributeWithValue$66$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2347lambda$insertAttributeWithValue$67$orgapachexmlbeansimplstoreCursor(str, (String) null, str2);
    }

    /* renamed from: _insertAttributeWithValue */
    public void m2347lambda$insertAttributeWithValue$67$orgapachexmlbeansimplstoreCursor(String str, String str2, String str3) {
        validateLocalName(str);
        m2348lambda$insertAttributeWithValue$68$orgapachexmlbeansimplstoreCursor(this._cur._locale.makeQName(str2, str), str3);
    }

    /* renamed from: _insertAttributeWithValue */
    public void m2348lambda$insertAttributeWithValue$68$orgapachexmlbeansimplstoreCursor(QName qName, String str) {
        validateLocalName(qName.getLocalPart());
        Cur tempCur = this._cur._locale.tempCur();
        tempCur.createAttr(qName);
        insertNode(tempCur, str);
        tempCur.release();
    }

    /* renamed from: _insertNamespace */
    public void m2357lambda$insertNamespace$69$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        m2348lambda$insertAttributeWithValue$68$orgapachexmlbeansimplstoreCursor(this._cur._locale.createXmlns(str), str2);
    }

    /* renamed from: _insertComment */
    public void m2350lambda$insertComment$70$orgapachexmlbeansimplstoreCursor(String str) {
        Cur tempCur = this._cur._locale.tempCur();
        tempCur.createComment();
        insertNode(tempCur, str);
        tempCur.release();
    }

    /* renamed from: _insertProcInst */
    public void m2358lambda$insertProcInst$71$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        validateLocalName(str);
        if (!Locale.beginsWithXml(str) || str.length() != 3) {
            Cur tempCur = this._cur._locale.tempCur();
            tempCur.createProcinst(str);
            insertNode(tempCur, str2);
            tempCur.release();
            return;
        }
        throw new IllegalArgumentException("Target is 'xml'");
    }

    public void _dump() {
        this._cur.dump();
    }

    private void checkThisCursor() {
        if (this._cur == null) {
            throw new IllegalStateException("This cursor has been disposed");
        }
    }

    private Cursor checkCursors(XmlCursor xmlCursor) {
        checkThisCursor();
        if (xmlCursor == null) {
            throw new IllegalArgumentException("Other cursor is <null>");
        } else if (xmlCursor instanceof Cursor) {
            Cursor cursor = (Cursor) xmlCursor;
            if (cursor._cur != null) {
                return cursor;
            }
            throw new IllegalStateException("Other cursor has been disposed");
        } else {
            throw new IllegalArgumentException("Incompatible cursors: " + xmlCursor);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private int twoLocaleOp(org.apache.xmlbeans.XmlCursor r5, int r6, int r7) {
        /*
            r4 = this;
            org.apache.xmlbeans.impl.store.Cursor r5 = r4.checkCursors(r5)
            org.apache.xmlbeans.impl.store.Cur r0 = r4._cur
            org.apache.xmlbeans.impl.store.Locale r0 = r0._locale
            org.apache.xmlbeans.impl.store.Cur r1 = r5._cur
            org.apache.xmlbeans.impl.store.Locale r1 = r1._locale
            if (r0 != r1) goto L_0x001e
            org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda67 r0 = new org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda67
            r0.<init>(r4, r5, r6, r7)
            java.lang.Object r4 = r4.syncWrapNoEnter(r0)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            return r4
        L_0x001e:
            boolean r2 = r0.noSync()
            if (r2 == 0) goto L_0x0039
            boolean r0 = r1.noSync()
            if (r0 == 0) goto L_0x002f
            int r4 = r4.twoLocaleOp((org.apache.xmlbeans.impl.store.Cursor) r5, (int) r6, (int) r7)
            return r4
        L_0x002f:
            monitor-enter(r1)
            int r4 = r4.twoLocaleOp((org.apache.xmlbeans.impl.store.Cursor) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x0036 }
            monitor-exit(r1)     // Catch:{ all -> 0x0036 }
            return r4
        L_0x0036:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0036 }
            throw r4
        L_0x0039:
            boolean r2 = r1.noSync()
            if (r2 == 0) goto L_0x0049
            monitor-enter(r0)
            int r4 = r4.twoLocaleOp((org.apache.xmlbeans.impl.store.Cursor) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x0046 }
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return r4
        L_0x0046:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r4
        L_0x0049:
            r2 = 0
            org.apache.xmlbeans.impl.common.GlobalLock.acquire()     // Catch:{ InterruptedException -> 0x006e }
            r3 = 1
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0069, all -> 0x0066 }
            monitor-enter(r1)     // Catch:{ all -> 0x0060 }
            org.apache.xmlbeans.impl.common.GlobalLock.release()     // Catch:{ all -> 0x005c }
            int r4 = r4.twoLocaleOp((org.apache.xmlbeans.impl.store.Cursor) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x005a }
            monitor-exit(r1)     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            return r4
        L_0x005a:
            r4 = move-exception
            goto L_0x005e
        L_0x005c:
            r4 = move-exception
            r2 = r3
        L_0x005e:
            monitor-exit(r1)     // Catch:{ all -> 0x005a }
            throw r4     // Catch:{ all -> 0x0064 }
        L_0x0060:
            r4 = move-exception
            r2 = r3
        L_0x0062:
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            throw r4     // Catch:{ InterruptedException -> 0x006e }
        L_0x0064:
            r4 = move-exception
            goto L_0x0062
        L_0x0066:
            r4 = move-exception
            r2 = r3
            goto L_0x0079
        L_0x0069:
            r4 = move-exception
            r2 = r3
            goto L_0x006f
        L_0x006c:
            r4 = move-exception
            goto L_0x0079
        L_0x006e:
            r4 = move-exception
        L_0x006f:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r4.getMessage()     // Catch:{ all -> 0x006c }
            r5.<init>(r6, r4)     // Catch:{ all -> 0x006c }
            throw r5     // Catch:{ all -> 0x006c }
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            org.apache.xmlbeans.impl.common.GlobalLock.release()
        L_0x007e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cursor.twoLocaleOp(org.apache.xmlbeans.XmlCursor, int, int):int");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$twoLocaleOp$0$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2400lambda$twoLocaleOp$0$orgapachexmlbeansimplstoreCursor(Cursor cursor, int i, int i2) {
        return Integer.valueOf(twoLocaleOp(cursor, i, i2));
    }

    private int twoLocaleOp(Cursor cursor, int i, int i2) {
        Locale locale = this._cur._locale;
        Locale locale2 = cursor._cur._locale;
        locale.enter(locale2);
        if (i == 0) {
            boolean _moveXml = _moveXml(cursor);
            locale.exit(locale2);
            return _moveXml ? 1 : 0;
        } else if (i == 1) {
            boolean _copyXml = _copyXml(cursor);
            locale.exit(locale2);
            return _copyXml ? 1 : 0;
        } else if (i == 2) {
            boolean _moveXmlContents = _moveXmlContents(cursor);
            locale.exit(locale2);
            return _moveXmlContents ? 1 : 0;
        } else if (i == 3) {
            boolean _copyXmlContents = _copyXmlContents(cursor);
            locale.exit(locale2);
            return _copyXmlContents ? 1 : 0;
        } else if (i == 4) {
            int _moveChars = _moveChars(i2, cursor);
            locale.exit(locale2);
            return _moveChars;
        } else if (i == 5) {
            try {
                return _copyChars(i2, cursor);
            } finally {
                locale.exit(locale2);
            }
        } else {
            throw new RuntimeException("Unknown operation: " + i);
        }
    }

    public boolean moveXml(XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 0, 0) == 1;
    }

    public boolean copyXml(XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 1, 0) == 1;
    }

    public boolean moveXmlContents(XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 2, 0) == 1;
    }

    public boolean copyXmlContents(XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 3, 0) == 1;
    }

    public int moveChars(int i, XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 4, i);
    }

    public int copyChars(int i, XmlCursor xmlCursor) {
        return twoLocaleOp(xmlCursor, 5, i);
    }

    public boolean toCursor(XmlCursor xmlCursor) {
        Cursor checkCursors = checkCursors(xmlCursor);
        return this._cur._locale == checkCursors._cur._locale && ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda73(this, checkCursors))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toCursor$1$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2391lambda$toCursor$1$orgapachexmlbeansimplstoreCursor(Cursor cursor) {
        return Boolean.valueOf(_toCursor(cursor));
    }

    public boolean isInSameDocument(XmlCursor xmlCursor) {
        return xmlCursor != null && this._cur.isInSameTree(checkCursors(xmlCursor)._cur);
    }

    private Cursor preCheck(XmlCursor xmlCursor) {
        Cursor checkCursors = checkCursors(xmlCursor);
        if (this._cur._locale == checkCursors._cur._locale) {
            return checkCursors;
        }
        throw new IllegalArgumentException("Cursors not in same document");
    }

    public int comparePosition(XmlCursor xmlCursor) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda55(this, preCheck(xmlCursor)))).intValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$comparePosition$2$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2334lambda$comparePosition$2$orgapachexmlbeansimplstoreCursor(Cursor cursor) {
        return Integer.valueOf(_comparePosition(cursor));
    }

    public boolean isLeftOf(XmlCursor xmlCursor) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda44(this, preCheck(xmlCursor)))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isLeftOf$3$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2360lambda$isLeftOf$3$orgapachexmlbeansimplstoreCursor(Cursor cursor) {
        return Boolean.valueOf(_isLeftOf(cursor));
    }

    public boolean isAtSamePositionAs(XmlCursor xmlCursor) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda25(this, preCheck(xmlCursor)))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isAtSamePositionAs$4$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2359lambda$isAtSamePositionAs$4$orgapachexmlbeansimplstoreCursor(Cursor cursor) {
        return Boolean.valueOf(_isAtSamePositionAs(cursor));
    }

    public boolean isRightOf(XmlCursor xmlCursor) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda36(this, preCheck(xmlCursor)))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isRightOf$5$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2361lambda$isRightOf$5$orgapachexmlbeansimplstoreCursor(Cursor cursor) {
        return Boolean.valueOf(_isRightOf(cursor));
    }

    public static XmlCursor newCursor(Xobj xobj, int i) {
        Cursor cursor;
        Locale locale = xobj._locale;
        if (locale.noSync()) {
            locale.enter();
            try {
                return new Cursor(xobj, i);
            } finally {
                locale.exit();
            }
        } else {
            synchronized (locale) {
                locale.enter();
                try {
                    cursor = new Cursor(xobj, i);
                } finally {
                    locale.exit();
                }
            }
            return cursor;
        }
    }

    private boolean preCheck() {
        checkThisCursor();
        return this._cur._locale.noSync();
    }

    public void dispose() {
        if (this._cur != null) {
            syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda34(this));
        }
    }

    public Object monitor() {
        return syncWrap(new Cursor$$ExternalSyntheticLambda50(this));
    }

    public XmlDocumentProperties documentProperties() {
        return (XmlDocumentProperties) syncWrap(new Cursor$$ExternalSyntheticLambda41(this));
    }

    public XmlCursor newCursor() {
        return (XmlCursor) syncWrap(new Cursor$$ExternalSyntheticLambda102(this));
    }

    public XMLStreamReader newXMLStreamReader() {
        return (XMLStreamReader) syncWrap(new Cursor$$ExternalSyntheticLambda105(this));
    }

    public XMLStreamReader newXMLStreamReader(XmlOptions xmlOptions) {
        return (XMLStreamReader) syncWrap(new Cursor$$ExternalSyntheticLambda95(this, xmlOptions));
    }

    public String xmlText() {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda61(this));
    }

    public InputStream newInputStream() {
        return (InputStream) syncWrap(new Cursor$$ExternalSyntheticLambda122(this));
    }

    public Reader newReader() {
        return (Reader) syncWrap(new Cursor$$ExternalSyntheticLambda12(this));
    }

    public Node newDomNode() {
        return (Node) syncWrap(new Cursor$$ExternalSyntheticLambda54(this));
    }

    public Node getDomNode() {
        return (Node) syncWrap(new Cursor$$ExternalSyntheticLambda83(this));
    }

    public void save(ContentHandler contentHandler, LexicalHandler lexicalHandler) throws SAXException {
        syncWrapSAXEx(new Cursor$$ExternalSyntheticLambda31(this, contentHandler, lexicalHandler));
    }

    public void save(File file) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda58(this, file));
    }

    public void save(OutputStream outputStream) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda5(this, outputStream));
    }

    public void save(Writer writer) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda68(this, writer));
    }

    public String xmlText(XmlOptions xmlOptions) {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda23(this, xmlOptions));
    }

    public InputStream newInputStream(XmlOptions xmlOptions) {
        return (InputStream) syncWrap(new Cursor$$ExternalSyntheticLambda111(this, xmlOptions));
    }

    public Reader newReader(XmlOptions xmlOptions) {
        return (Reader) syncWrap(new Cursor$$ExternalSyntheticLambda13(this, xmlOptions));
    }

    public Node newDomNode(XmlOptions xmlOptions) {
        return (Node) syncWrap(new Cursor$$ExternalSyntheticLambda17(this, xmlOptions));
    }

    public void save(ContentHandler contentHandler, LexicalHandler lexicalHandler, XmlOptions xmlOptions) throws SAXException {
        syncWrapSAXEx(new Cursor$$ExternalSyntheticLambda126(this, contentHandler, lexicalHandler, xmlOptions));
    }

    public void save(File file, XmlOptions xmlOptions) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda98(this, file, xmlOptions));
    }

    public void save(OutputStream outputStream, XmlOptions xmlOptions) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda74(this, outputStream, xmlOptions));
    }

    public void save(Writer writer, XmlOptions xmlOptions) throws IOException {
        syncWrapIOEx(new Cursor$$ExternalSyntheticLambda101(this, writer, xmlOptions));
    }

    public void push() {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda70(this));
    }

    public boolean pop() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda119(this))).booleanValue();
    }

    public void selectPath(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda81(this, str));
    }

    public void selectPath(String str, XmlOptions xmlOptions) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda53(this, str, xmlOptions));
    }

    public boolean hasNextSelection() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda104(this))).booleanValue();
    }

    public boolean toNextSelection() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda49(this))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toSelection$21$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2399lambda$toSelection$21$orgapachexmlbeansimplstoreCursor(int i) {
        return Boolean.valueOf(_toSelection(i));
    }

    public boolean toSelection(int i) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda124(this, i))).booleanValue();
    }

    public int getSelectionCount() {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda113(this))).intValue();
    }

    public void addToSelection() {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda35(this));
    }

    public void clearSelections() {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda90(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toBookmark$22$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2385lambda$toBookmark$22$orgapachexmlbeansimplstoreCursor(XmlCursor.XmlBookmark xmlBookmark) {
        return Boolean.valueOf(_toBookmark(xmlBookmark));
    }

    public boolean toBookmark(XmlCursor.XmlBookmark xmlBookmark) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda117(this, xmlBookmark))).booleanValue();
    }

    public XmlCursor.XmlBookmark toNextBookmark(Object obj) {
        return (XmlCursor.XmlBookmark) syncWrap(new Cursor$$ExternalSyntheticLambda103(this, obj));
    }

    public XmlCursor.XmlBookmark toPrevBookmark(Object obj) {
        return (XmlCursor.XmlBookmark) syncWrap(new Cursor$$ExternalSyntheticLambda112(this, obj));
    }

    public QName getName() {
        return (QName) syncWrap(new Cursor$$ExternalSyntheticLambda6(this));
    }

    public void setName(QName qName) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda115(this, qName));
    }

    public String namespaceForPrefix(String str) {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda86(this, str));
    }

    public String prefixForNamespace(String str) {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda43(this, str));
    }

    public void getAllNamespaces(Map<String, String> map) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda29(this, map));
    }

    public XmlObject getObject() {
        return (XmlObject) syncWrap(new Cursor$$ExternalSyntheticLambda77(this));
    }

    public XmlCursor.TokenType currentTokenType() {
        return (XmlCursor.TokenType) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda18(this));
    }

    public boolean isStartdoc() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda40(this))).booleanValue();
    }

    public boolean isEnddoc() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda84(this))).booleanValue();
    }

    public boolean isStart() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda114(this))).booleanValue();
    }

    public boolean isEnd() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda62(this))).booleanValue();
    }

    public boolean isText() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda21(this))).booleanValue();
    }

    public boolean isAttr() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda89(this))).booleanValue();
    }

    public boolean isNamespace() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda108(this))).booleanValue();
    }

    public boolean isComment() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda96(this))).booleanValue();
    }

    public boolean isProcinst() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda64(this))).booleanValue();
    }

    public boolean isContainer() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda92(this))).booleanValue();
    }

    public boolean isFinish() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda69(this))).booleanValue();
    }

    public boolean isAnyAttr() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda80(this))).booleanValue();
    }

    public XmlCursor.TokenType prevTokenType() {
        return (XmlCursor.TokenType) syncWrap(new Cursor$$ExternalSyntheticLambda60(this));
    }

    public boolean hasNextToken() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda82(this))).booleanValue();
    }

    public boolean hasPrevToken() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda107(this))).booleanValue();
    }

    public XmlCursor.TokenType toNextToken() {
        return (XmlCursor.TokenType) syncWrap(new Cursor$$ExternalSyntheticLambda51(this));
    }

    public XmlCursor.TokenType toPrevToken() {
        return (XmlCursor.TokenType) syncWrap(new Cursor$$ExternalSyntheticLambda56(this));
    }

    public XmlCursor.TokenType toFirstContentToken() {
        return (XmlCursor.TokenType) syncWrap(new Cursor$$ExternalSyntheticLambda99(this));
    }

    public XmlCursor.TokenType toEndToken() {
        return (XmlCursor.TokenType) syncWrap(new Cursor$$ExternalSyntheticLambda121(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toNextChar$29$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2393lambda$toNextChar$29$orgapachexmlbeansimplstoreCursor(int i) {
        return Integer.valueOf(_toNextChar(i));
    }

    public int toNextChar(int i) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda1(this, i))).intValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toPrevChar$30$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2398lambda$toPrevChar$30$orgapachexmlbeansimplstoreCursor(int i) {
        return Integer.valueOf(_toPrevChar(i));
    }

    public int toPrevChar(int i) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda45(this, i))).intValue();
    }

    public boolean ___toNextSibling() {
        if (!this._cur.hasParent()) {
            return false;
        }
        Xobj parentNoRoot = this._cur.getParentNoRoot();
        if (parentNoRoot == null) {
            this._cur._locale.enter();
            try {
                parentNoRoot = this._cur.getParent();
            } finally {
                this._cur._locale.exit();
            }
        }
        return Locale.toNextSiblingElement(this._cur, parentNoRoot);
    }

    public boolean toNextSibling() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda97(this))).booleanValue();
    }

    public boolean toPrevSibling() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda11(this))).booleanValue();
    }

    public boolean toParent() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda22(this))).booleanValue();
    }

    public boolean toFirstChild() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda15(this))).booleanValue();
    }

    public boolean toLastChild() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda48(this))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$31$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2386lambda$toChild$31$orgapachexmlbeansimplstoreCursor(String str) {
        return Boolean.valueOf(_toChild(str));
    }

    public boolean toChild(String str) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda106(this, str))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$32$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2387lambda$toChild$32$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        return Boolean.valueOf(_toChild(str, str2));
    }

    public boolean toChild(String str, String str2) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda52(this, str, str2))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$33$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2388lambda$toChild$33$orgapachexmlbeansimplstoreCursor(QName qName) {
        return Boolean.valueOf(_toChild(qName));
    }

    public boolean toChild(QName qName) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda75(this, qName))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$34$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2389lambda$toChild$34$orgapachexmlbeansimplstoreCursor(int i) {
        return Boolean.valueOf(_toChild(i));
    }

    public boolean toChild(int i) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda27(this, i))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$35$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2390lambda$toChild$35$orgapachexmlbeansimplstoreCursor(QName qName, int i) {
        return Boolean.valueOf(_toChild(qName, i));
    }

    public boolean toChild(QName qName, int i) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda46(this, qName, i))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$36$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2394lambda$toNextSibling$36$orgapachexmlbeansimplstoreCursor(String str) {
        return Boolean.valueOf(_toNextSibling(str));
    }

    public boolean toNextSibling(String str) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda14(this, str))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$37$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2395lambda$toNextSibling$37$orgapachexmlbeansimplstoreCursor(String str, String str2) {
        return Boolean.valueOf(_toNextSibling(str, str2));
    }

    public boolean toNextSibling(String str, String str2) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda65(this, str, str2))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$38$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2396lambda$toNextSibling$38$orgapachexmlbeansimplstoreCursor(QName qName) {
        return Boolean.valueOf(_toNextSibling(qName));
    }

    public boolean toNextSibling(QName qName) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda91(this, qName))).booleanValue();
    }

    public boolean toFirstAttribute() {
        return ((Boolean) syncWrapNoEnter(new Cursor$$ExternalSyntheticLambda85(this))).booleanValue();
    }

    public boolean toLastAttribute() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda28(this))).booleanValue();
    }

    public boolean toNextAttribute() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda127(this))).booleanValue();
    }

    public boolean toPrevAttribute() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda37(this))).booleanValue();
    }

    public String getAttributeText(QName qName) {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda32(this, qName));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setAttributeText$40$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2380lambda$setAttributeText$40$orgapachexmlbeansimplstoreCursor(QName qName, String str) {
        return Boolean.valueOf(_setAttributeText(qName, str));
    }

    public boolean setAttributeText(QName qName, String str) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda8(this, qName, str))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeAttribute$41$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Boolean m2368lambda$removeAttribute$41$orgapachexmlbeansimplstoreCursor(QName qName) {
        return Boolean.valueOf(_removeAttribute(qName));
    }

    public boolean removeAttribute(QName qName) {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda120(this, qName))).booleanValue();
    }

    public String getTextValue() {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda26(this));
    }

    public int getTextValue(char[] cArr, int i, int i2) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda30(this, cArr, i, i2))).intValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getTextValue$42$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2342lambda$getTextValue$42$orgapachexmlbeansimplstoreCursor(char[] cArr, int i, int i2) {
        return Integer.valueOf(_getTextValue(cArr, i, i2));
    }

    public void setTextValue(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda94(this, str));
    }

    public void setTextValue(char[] cArr, int i, int i2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda87(this, cArr, i, i2));
    }

    public String getChars() {
        return (String) syncWrap(new Cursor$$ExternalSyntheticLambda116(this));
    }

    public int getChars(char[] cArr, int i, int i2) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda72(this, cArr, i, i2))).intValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getChars$45$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2341lambda$getChars$45$orgapachexmlbeansimplstoreCursor(char[] cArr, int i, int i2) {
        return Integer.valueOf(_getChars(cArr, i, i2));
    }

    public void toStartDoc() {
        syncWrapNoEnter((Runnable) new Cursor$$ExternalSyntheticLambda24(this));
    }

    public void toEndDoc() {
        syncWrapNoEnter((Runnable) new Cursor$$ExternalSyntheticLambda0(this));
    }

    public XmlCursor execQuery(String str) {
        return (XmlCursor) syncWrap(new Cursor$$ExternalSyntheticLambda93(this, str));
    }

    public XmlCursor execQuery(String str, XmlOptions xmlOptions) {
        return (XmlCursor) syncWrap(new Cursor$$ExternalSyntheticLambda59(this, str, xmlOptions));
    }

    public XmlCursor.ChangeStamp getDocChangeStamp() {
        return (XmlCursor.ChangeStamp) syncWrap(new Cursor$$ExternalSyntheticLambda110(this));
    }

    public void setBookmark(XmlCursor.XmlBookmark xmlBookmark) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda7(this, xmlBookmark));
    }

    public XmlCursor.XmlBookmark getBookmark(Object obj) {
        return (XmlCursor.XmlBookmark) syncWrap(new Cursor$$ExternalSyntheticLambda10(this, obj));
    }

    public void clearBookmark(Object obj) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda38(this, obj));
    }

    public void getAllBookmarkRefs(Collection collection) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda19(this, collection));
    }

    public boolean removeXml() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda71(this))).booleanValue();
    }

    public boolean removeXmlContents() {
        return ((Boolean) syncWrap(new Cursor$$ExternalSyntheticLambda79(this))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeChars$52$org-apache-xmlbeans-impl-store-Cursor  reason: not valid java name */
    public /* synthetic */ Integer m2369lambda$removeChars$52$orgapachexmlbeansimplstoreCursor(int i) {
        return Integer.valueOf(_removeChars(i));
    }

    public int removeChars(int i) {
        return ((Integer) syncWrap(new Cursor$$ExternalSyntheticLambda57(this, i))).intValue();
    }

    public void insertChars(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda78(this, str));
    }

    public void insertElement(QName qName) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda123(this, qName));
    }

    public void insertElement(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda66(this, str));
    }

    public void insertElement(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda47(this, str, str2));
    }

    public void beginElement(QName qName) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda4(this, qName));
    }

    public void beginElement(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda109(this, str));
    }

    public void beginElement(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda2(this, str, str2));
    }

    public void insertElementWithText(QName qName, String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda63(this, qName, str));
    }

    public void insertElementWithText(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda88(this, str, str2));
    }

    public void insertElementWithText(String str, String str2, String str3) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda125(this, str, str2, str3));
    }

    public void insertAttribute(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda118(this, str));
    }

    public void insertAttribute(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda42(this, str, str2));
    }

    public void insertAttribute(QName qName) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda39(this, qName));
    }

    public void insertAttributeWithValue(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda33(this, str, str2));
    }

    public void insertAttributeWithValue(String str, String str2, String str3) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda16(this, str, str2, str3));
    }

    public void insertAttributeWithValue(QName qName, String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda76(this, qName, str));
    }

    public void insertNamespace(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda3(this, str, str2));
    }

    public void insertComment(String str) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda9(this, str));
    }

    public void insertProcInst(String str, String str2) {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda100(this, str, str2));
    }

    public void dump() {
        syncWrap((Runnable) new Cursor$$ExternalSyntheticLambda20(this));
    }

    private void syncWrap(Runnable runnable) {
        if (preCheck()) {
            syncWrapHelper(runnable, true);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(runnable, true);
        }
    }

    private <T> T syncWrap(Supplier<T> supplier) {
        T syncWrapHelper;
        if (preCheck()) {
            return syncWrapHelper(supplier, true);
        }
        synchronized (this._cur._locale) {
            syncWrapHelper = syncWrapHelper(supplier, true);
        }
        return syncWrapHelper;
    }

    private <T> T syncWrapNoEnter(Supplier<T> supplier) {
        T syncWrapHelper;
        if (preCheck()) {
            return syncWrapHelper(supplier, false);
        }
        synchronized (this._cur._locale) {
            syncWrapHelper = syncWrapHelper(supplier, false);
        }
        return syncWrapHelper;
    }

    private void syncWrapNoEnter(Runnable runnable) {
        if (preCheck()) {
            syncWrapHelper(runnable, false);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(runnable, false);
        }
    }

    private void syncWrapSAXEx(WrapSAXEx wrapSAXEx) throws SAXException {
        if (preCheck()) {
            syncWrapHelper(wrapSAXEx);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(wrapSAXEx);
        }
    }

    private void syncWrapIOEx(WrapIOEx wrapIOEx) throws IOException {
        if (preCheck()) {
            syncWrapHelper(wrapIOEx);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(wrapIOEx);
        }
    }

    private void syncWrapHelper(Runnable runnable, boolean z) {
        Locale locale = this._cur._locale;
        if (z) {
            locale.enter();
        }
        try {
            runnable.run();
        } finally {
            if (z) {
                locale.exit();
            }
        }
    }

    private <T> T syncWrapHelper(Supplier<T> supplier, boolean z) {
        Locale locale = this._cur._locale;
        if (z) {
            locale.enter();
        }
        try {
            return supplier.get();
        } finally {
            if (z) {
                locale.exit();
            }
        }
    }

    private void syncWrapHelper(WrapSAXEx wrapSAXEx) throws SAXException {
        Locale locale = this._cur._locale;
        locale.enter();
        try {
            wrapSAXEx.run();
        } finally {
            locale.exit();
        }
    }

    private void syncWrapHelper(WrapIOEx wrapIOEx) throws IOException {
        Locale locale = this._cur._locale;
        locale.enter();
        try {
            wrapIOEx.run();
        } finally {
            locale.exit();
        }
    }
}
