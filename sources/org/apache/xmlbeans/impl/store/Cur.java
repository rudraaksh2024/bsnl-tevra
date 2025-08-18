package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlLineNumber;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPFaultElement;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

public final class Cur {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int DISPOSED = 3;
    static final int ELEM = 2;
    static final int EMBEDDED = 2;
    static final int END_POS = -1;
    static final int NO_POS = -2;
    static final int POOLED = 0;
    static final int PROCINST = 5;
    static final int REGISTERED = 1;
    static final int ROOT = 1;
    static final int TEXT = 0;
    int _cchSrc;
    String _id;
    Locale _locale;
    Cur _next;
    Cur _nextTemp;
    int _offSrc;
    int _pos = -2;
    private int _posTemp;
    Cur _prev;
    Cur _prevTemp;
    Locale.Ref _ref;
    int _selectionCount = 0;
    int _selectionFirst = -1;
    int _selectionLoc = -1;
    int _selectionN = -1;
    int _stackTop = -1;
    int _state = 0;
    int _tempFrame = -1;
    Xobj _xobj;

    static boolean kindIsContainer(int i) {
        return i == 2 || i == 1;
    }

    static boolean kindIsFinish(int i) {
        return i == -2 || i == -1;
    }

    Cur(Locale locale) {
        this._locale = locale;
    }

    public boolean isPositioned() {
        return this._xobj != null;
    }

    public int kind() {
        int kind = this._xobj.kind();
        int i = this._pos;
        if (i == 0) {
            return kind;
        }
        if (i == -1) {
            return -kind;
        }
        return 0;
    }

    public boolean isRoot() {
        return this._pos == 0 && this._xobj.kind() == 1;
    }

    public boolean isElem() {
        return this._pos == 0 && this._xobj.kind() == 2;
    }

    public boolean isAttr() {
        return this._pos == 0 && this._xobj.kind() == 3;
    }

    public boolean isComment() {
        return this._pos == 0 && this._xobj.kind() == 4;
    }

    public boolean isProcinst() {
        return this._pos == 0 && this._xobj.kind() == 5;
    }

    public boolean isText() {
        return this._pos > 0;
    }

    public boolean isEnd() {
        return this._pos == -1 && this._xobj.kind() == 2;
    }

    public boolean isEndRoot() {
        return this._pos == -1 && this._xobj.kind() == 1;
    }

    public boolean isNode() {
        return this._pos == 0;
    }

    public boolean isContainer() {
        return this._pos == 0 && kindIsContainer(this._xobj.kind());
    }

    public boolean isFinish() {
        return this._pos == -1 && kindIsContainer(this._xobj.kind());
    }

    public boolean isUserNode() {
        int kind = kind();
        if (kind == 2 || kind == 1) {
            return true;
        }
        if (kind != 3 || isXmlns()) {
            return false;
        }
        return true;
    }

    public boolean isContainerOrFinish() {
        int i = this._pos;
        if (i != 0 && i != -1) {
            return false;
        }
        int kind = this._xobj.kind();
        if (kind == 2 || kind == -2 || kind == 1 || kind == -1) {
            return true;
        }
        return false;
    }

    public boolean isNormalAttr() {
        return isNode() && this._xobj.isNormalAttr();
    }

    public boolean isXmlns() {
        return isNode() && this._xobj.isXmlns();
    }

    public boolean isTextCData() {
        return this._xobj.hasBookmark(CDataBookmark.class, this._pos);
    }

    public QName getName() {
        return this._xobj._name;
    }

    public String getLocal() {
        return getName().getLocalPart();
    }

    public String getUri() {
        return getName().getNamespaceURI();
    }

    public String getXmlnsPrefix() {
        return this._xobj.getXmlnsPrefix();
    }

    public String getXmlnsUri() {
        return this._xobj.getXmlnsUri();
    }

    public boolean isDomDocRoot() {
        return isRoot() && (this._xobj.getDom() instanceof Document);
    }

    public boolean isDomFragRoot() {
        return isRoot() && (this._xobj.getDom() instanceof DocumentFragment);
    }

    public int cchRight() {
        return this._xobj.cchRight(this._pos);
    }

    public int cchLeft() {
        return this._xobj.cchLeft(this._pos);
    }

    /* access modifiers changed from: package-private */
    public void createRoot() {
        createDomDocFragRoot();
    }

    /* access modifiers changed from: package-private */
    public void createDomDocFragRoot() {
        moveTo(new DocumentFragXobj(this._locale));
    }

    /* access modifiers changed from: package-private */
    public void createDomDocumentRoot() {
        moveTo(createDomDocumentRootXobj(this._locale));
    }

    /* access modifiers changed from: package-private */
    public void createAttr(QName qName) {
        createHelper(new AttrXobj(this._locale, qName));
    }

    /* access modifiers changed from: package-private */
    public void createComment() {
        createHelper(new CommentXobj(this._locale));
    }

    /* access modifiers changed from: package-private */
    public void createProcinst(String str) {
        createHelper(new ProcInstXobj(this._locale, str));
    }

    /* access modifiers changed from: package-private */
    public void createElement(QName qName) {
        createElement(qName, (QName) null);
    }

    /* access modifiers changed from: package-private */
    public void createElement(QName qName, QName qName2) {
        createHelper(createElementXobj(this._locale, qName, qName2));
    }

    static Xobj createDomDocumentRootXobj(Locale locale) {
        return createDomDocumentRootXobj(locale, false);
    }

    static Xobj createDomDocumentRootXobj(Locale locale, boolean z) {
        Xobj xobj;
        if (locale._saaj != null) {
            xobj = new SoapPartDocXobj(locale);
        } else if (z) {
            xobj = new DocumentFragXobj(locale);
        } else {
            xobj = new DocumentXobj(locale);
        }
        if (locale._ownerDoc == null) {
            locale._ownerDoc = xobj.getDom();
        }
        return xobj;
    }

    static Xobj createElementXobj(Locale locale, QName qName, QName qName2) {
        if (locale._saaj == null) {
            return new ElementXobj(locale, qName);
        }
        Class<SOAPFault> identifyElement = locale._saaj.identifyElement(qName, qName2);
        if (identifyElement == SOAPElement.class) {
            return new SoapElementXobj(locale, qName);
        }
        if (identifyElement == SOAPBody.class) {
            return new SoapBodyXobj(locale, qName);
        }
        if (identifyElement == SOAPBodyElement.class) {
            return new SoapBodyElementXobj(locale, qName);
        }
        if (identifyElement == SOAPEnvelope.class) {
            return new SoapEnvelopeXobj(locale, qName);
        }
        if (identifyElement == SOAPHeader.class) {
            return new SoapHeaderXobj(locale, qName);
        }
        if (identifyElement == SOAPHeaderElement.class) {
            return new SoapHeaderElementXobj(locale, qName);
        }
        if (identifyElement == SOAPFaultElement.class) {
            return new SoapFaultElementXobj(locale, qName);
        }
        if (identifyElement == Detail.class) {
            return new DetailXobj(locale, qName);
        }
        if (identifyElement == DetailEntry.class) {
            return new DetailEntryXobj(locale, qName);
        }
        if (identifyElement == SOAPFault.class) {
            return new SoapFaultXobj(locale, qName);
        }
        throw new IllegalStateException("Unknown SAAJ element class: " + identifyElement);
    }

    private void createHelper(Xobj xobj) {
        if (isPositioned()) {
            Cur tempCur = tempCur(xobj, 0);
            tempCur.moveNode(this);
            tempCur.release();
        }
        moveTo(xobj);
    }

    /* access modifiers changed from: package-private */
    public boolean isSamePos(Cur cur) {
        return this._xobj == cur._xobj && this._pos == cur._pos;
    }

    /* access modifiers changed from: package-private */
    public boolean isJustAfterEnd(Cur cur) {
        return cur._xobj.isJustAfterEnd(this._xobj, this._pos);
    }

    /* access modifiers changed from: package-private */
    public boolean isJustAfterEnd(Xobj xobj) {
        return xobj.isJustAfterEnd(this._xobj, this._pos);
    }

    /* access modifiers changed from: package-private */
    public boolean isAtEndOf(Cur cur) {
        return this._xobj == cur._xobj && this._pos == -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isInSameTree(Cur cur) {
        return this._xobj.isInSameTree(cur._xobj);
    }

    /* access modifiers changed from: package-private */
    public int comparePosition(Cur cur) {
        if (this._locale != cur._locale) {
            return 2;
        }
        Xobj xobj = this._xobj;
        int i = this._pos;
        if (i == -1) {
            i = xobj.posAfter() - 1;
        }
        Xobj xobj2 = cur._xobj;
        int i2 = cur._pos;
        if (i2 == -1) {
            i2 = xobj2.posAfter() - 1;
        }
        if (xobj == xobj2) {
            return Integer.compare(i, i2);
        }
        Xobj xobj3 = xobj._parent;
        int i3 = 0;
        int i4 = 0;
        while (xobj3 != null) {
            i4++;
            if (xobj3 != xobj2) {
                xobj3 = xobj3._parent;
            } else if (i2 < xobj2.posAfter() - 1) {
                return 1;
            } else {
                return -1;
            }
        }
        Xobj xobj4 = xobj2._parent;
        while (xobj4 != null) {
            i3++;
            if (xobj4 != xobj) {
                xobj4 = xobj4._parent;
            } else if (i < xobj.posAfter() - 1) {
                return -1;
            } else {
                return 1;
            }
        }
        while (i4 > i3) {
            i4--;
            xobj = xobj._parent;
        }
        while (i3 > i4) {
            i3--;
            xobj2 = xobj2._parent;
        }
        if (i3 == 0) {
            return 2;
        }
        while (xobj._parent != xobj2._parent) {
            xobj = xobj._parent;
            if (xobj == null) {
                return 2;
            }
            xobj2 = xobj2._parent;
        }
        if (xobj._prevSibling == null || xobj2._nextSibling == null) {
            return -1;
        }
        if (xobj._nextSibling == null || xobj2._prevSibling == null) {
            return 1;
        }
        while (xobj != null) {
            xobj = xobj._prevSibling;
            if (xobj == xobj2) {
                return 1;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void setName(QName qName) {
        this._xobj.setName(qName);
    }

    /* access modifiers changed from: package-private */
    public void moveTo(Xobj xobj) {
        moveTo(xobj, 0);
    }

    /* access modifiers changed from: package-private */
    public void moveTo(Xobj xobj, int i) {
        moveToNoCheck(xobj, i);
    }

    /* access modifiers changed from: package-private */
    public void moveToNoCheck(Xobj xobj, int i) {
        Xobj xobj2;
        if (this._state == 2 && xobj != (xobj2 = this._xobj)) {
            xobj2._embedded = listRemove(xobj2._embedded);
            Locale locale = this._locale;
            locale._registered = listInsert(locale._registered);
            this._state = 1;
        }
        this._xobj = xobj;
        this._pos = i;
    }

    /* access modifiers changed from: package-private */
    public void moveToCur(Cur cur) {
        if (cur == null) {
            moveTo((Xobj) null, -2);
        } else {
            moveTo(cur._xobj, cur._pos);
        }
    }

    /* access modifiers changed from: package-private */
    public void moveToDom(DomImpl.Dom dom) {
        moveTo(dom instanceof Xobj ? (Xobj) dom : ((SoapPartDom) dom)._docXobj);
    }

    static final class Locations {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int NULL = -1;
        private static final int _initialSize = 32;
        private Cur[] _curs = new Cur[32];
        private int _free;
        private final Locale _locale;
        private int _naked;
        private int[] _next = new int[32];
        private int[] _nextN = new int[32];
        private int[] _poses = new int[32];
        private int[] _prev = new int[32];
        private int[] _prevN = new int[32];
        private Xobj[] _xobjs = new Xobj[32];

        static {
            Class<Cur> cls = Cur.class;
        }

        Locations(Locale locale) {
            this._locale = locale;
            for (int i = 31; i >= 0; i--) {
                this._poses[i] = -2;
                this._next[i] = i + 1;
                this._prev[i] = -1;
                this._nextN[i] = -1;
                this._prevN[i] = -1;
            }
            this._next[31] = -1;
            this._free = 0;
            this._naked = -1;
        }

        /* access modifiers changed from: package-private */
        public boolean isSamePos(int i, Cur cur) {
            Cur cur2 = this._curs[i];
            if (cur2 == null) {
                return cur._xobj == this._xobjs[i] && cur._pos == this._poses[i];
            }
            return cur.isSamePos(cur2);
        }

        /* access modifiers changed from: package-private */
        public boolean isAtEndOf(int i, Cur cur) {
            Cur cur2 = this._curs[i];
            if (cur2 == null) {
                return cur._xobj == this._xobjs[i] && cur._pos == -1;
            }
            return cur.isAtEndOf(cur2);
        }

        /* access modifiers changed from: package-private */
        public void moveTo(int i, Cur cur) {
            Cur cur2 = this._curs[i];
            if (cur2 == null) {
                cur.moveTo(this._xobjs[i], this._poses[i]);
            } else {
                cur.moveToCur(cur2);
            }
        }

        /* access modifiers changed from: package-private */
        public int insert(int i, int i2, int i3) {
            return insert(i, i2, i3, this._next, this._prev);
        }

        /* access modifiers changed from: package-private */
        public int remove(int i, int i2) {
            Cur cur = this._curs[i2];
            if (cur != null) {
                cur.release();
                this._curs[i2] = null;
            } else {
                this._xobjs[i2] = null;
                this._poses[i2] = -2;
                this._naked = remove(this._naked, i2, this._nextN, this._prevN);
            }
            int remove = remove(i, i2, this._next, this._prev);
            this._next[i2] = this._free;
            this._free = i2;
            return remove;
        }

        /* access modifiers changed from: package-private */
        public int allocate(Cur cur) {
            if (this._free == -1) {
                makeRoom();
            }
            int i = this._free;
            int[] iArr = this._next;
            this._free = iArr[i];
            iArr[i] = -1;
            this._xobjs[i] = cur._xobj;
            this._poses[i] = cur._pos;
            this._naked = insert(this._naked, -1, i, this._nextN, this._prevN);
            return i;
        }

        private static int insert(int i, int i2, int i3, int[] iArr, int[] iArr2) {
            if (i == -1) {
                iArr2[i3] = i3;
            } else if (i2 != -1) {
                iArr2[i3] = iArr2[i2];
                iArr[i3] = i2;
                iArr2[i2] = i3;
                if (i != i2) {
                    return i;
                }
            } else {
                iArr2[i3] = iArr2[i];
                iArr[iArr2[i]] = i3;
                iArr2[i] = i3;
                return i;
            }
            return i3;
        }

        private static int remove(int i, int i2, int[] iArr, int[] iArr2) {
            int i3 = iArr2[i2];
            if (i3 == i2) {
                i = -1;
            } else {
                if (i == i2) {
                    i = iArr[i2];
                } else {
                    iArr[i3] = iArr[i2];
                }
                int i4 = iArr[i2];
                if (i4 == -1) {
                    iArr2[i] = iArr2[i2];
                } else {
                    iArr2[i4] = iArr2[i2];
                    iArr[i2] = -1;
                }
            }
            iArr2[i2] = -1;
            return i;
        }

        /* access modifiers changed from: package-private */
        public void notifyChange() {
            while (true) {
                int i = this._naked;
                if (i != -1) {
                    this._naked = remove(i, i, this._nextN, this._prevN);
                    this._curs[i] = this._locale.getCur();
                    this._curs[i].moveTo(this._xobjs[i], this._poses[i]);
                    this._xobjs[i] = null;
                    this._poses[i] = -2;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int next(int i) {
            return this._next[i];
        }

        /* access modifiers changed from: package-private */
        public int prev(int i) {
            return this._prev[i];
        }

        private void makeRoom() {
            Xobj[] xobjArr = this._xobjs;
            int length = xobjArr.length;
            int[] iArr = this._poses;
            Cur[] curArr = this._curs;
            int[] iArr2 = this._next;
            int[] iArr3 = this._prev;
            int[] iArr4 = this._nextN;
            int[] iArr5 = this._prevN;
            int i = length * 2;
            Xobj[] xobjArr2 = new Xobj[i];
            this._xobjs = xobjArr2;
            this._poses = new int[i];
            this._curs = new Cur[i];
            this._next = new int[i];
            this._prev = new int[i];
            this._nextN = new int[i];
            this._prevN = new int[i];
            System.arraycopy(xobjArr, 0, xobjArr2, 0, length);
            System.arraycopy(iArr, 0, this._poses, 0, length);
            System.arraycopy(curArr, 0, this._curs, 0, length);
            System.arraycopy(iArr2, 0, this._next, 0, length);
            System.arraycopy(iArr3, 0, this._prev, 0, length);
            System.arraycopy(iArr4, 0, this._nextN, 0, length);
            System.arraycopy(iArr5, 0, this._prevN, 0, length);
            int i2 = i - 1;
            for (int i3 = i2; i3 >= length; i3--) {
                this._next[i3] = i3 + 1;
                this._prev[i3] = -1;
                this._nextN[i3] = -1;
                this._prevN[i3] = -1;
                this._poses[i3] = -2;
            }
            this._next[i2] = -1;
            this._free = length;
        }
    }

    public void push() {
        int allocate = this._locale._locations.allocate(this);
        Locations locations = this._locale._locations;
        int i = this._stackTop;
        this._stackTop = locations.insert(i, i, allocate);
    }

    /* access modifiers changed from: package-private */
    public void popButStay() {
        if (this._stackTop != -1) {
            Locations locations = this._locale._locations;
            int i = this._stackTop;
            this._stackTop = locations.remove(i, i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean pop() {
        if (this._stackTop == -1) {
            return false;
        }
        this._locale._locations.moveTo(this._stackTop, this);
        Locations locations = this._locale._locations;
        int i = this._stackTop;
        this._stackTop = locations.remove(i, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isAtLastPush() {
        return this._locale._locations.isSamePos(this._stackTop, this);
    }

    public boolean isAtEndOfLastPush() {
        return this._locale._locations.isAtEndOf(this._stackTop, this);
    }

    public void addToSelection(Cur cur) {
        this._selectionFirst = this._locale._locations.insert(this._selectionFirst, -1, this._locale._locations.allocate(cur));
        this._selectionCount++;
    }

    public void addToSelection() {
        this._selectionFirst = this._locale._locations.insert(this._selectionFirst, -1, this._locale._locations.allocate(this));
        this._selectionCount++;
    }

    private int selectionIndex(int i) {
        if (this._selectionN == -1) {
            this._selectionN = 0;
            this._selectionLoc = this._selectionFirst;
        }
        while (this._selectionN < i) {
            this._selectionLoc = this._locale._locations.next(this._selectionLoc);
            this._selectionN++;
        }
        while (this._selectionN > i) {
            this._selectionLoc = this._locale._locations.prev(this._selectionLoc);
            this._selectionN--;
        }
        return this._selectionLoc;
    }

    /* access modifiers changed from: package-private */
    public void removeFirstSelection() {
        int selectionIndex = selectionIndex(0);
        int i = this._selectionN;
        if (i > 0) {
            this._selectionN = i - 1;
        } else if (i == 0) {
            this._selectionN = i - 1;
            this._selectionLoc = -1;
        }
        this._selectionFirst = this._locale._locations.remove(this._selectionFirst, selectionIndex);
        this._selectionCount--;
    }

    public int selectionCount() {
        return this._selectionCount;
    }

    public void moveToSelection(int i) {
        this._locale._locations.moveTo(selectionIndex(i), this);
    }

    public void clearSelection() {
        while (this._selectionCount > 0) {
            removeFirstSelection();
        }
    }

    public boolean toParent() {
        return toParent(false);
    }

    public boolean toParentRaw() {
        return toParent(true);
    }

    public Xobj getParent() {
        return getParent(false);
    }

    public Xobj getParentRaw() {
        return getParent(true);
    }

    public boolean hasParent() {
        int i = this._pos;
        if (i == -1) {
            return true;
        }
        if ((i < 1 || i >= this._xobj.posAfter()) && this._xobj._parent == null) {
            return false;
        }
        return true;
    }

    public Xobj getParentNoRoot() {
        int i = this._pos;
        if (i == -1 || (i >= 1 && i < this._xobj.posAfter())) {
            return this._xobj;
        }
        if (this._xobj._parent != null) {
            return this._xobj._parent;
        }
        return null;
    }

    public Xobj getParent(boolean z) {
        int i = this._pos;
        if (i == -1 || (i >= 1 && i < this._xobj.posAfter())) {
            return this._xobj;
        }
        if (this._xobj._parent != null) {
            return this._xobj._parent;
        }
        if (z || this._xobj.isRoot()) {
            return null;
        }
        Cur tempCur = this._locale.tempCur();
        tempCur.createRoot();
        Xobj xobj = tempCur._xobj;
        tempCur.next();
        moveNode(tempCur);
        tempCur.release();
        return xobj;
    }

    public boolean toParent(boolean z) {
        Xobj parent = getParent(z);
        if (parent == null) {
            return false;
        }
        moveTo(parent);
        return true;
    }

    public void toRoot() {
        Xobj xobj = this._xobj;
        while (true) {
            if (xobj.isRoot()) {
                break;
            } else if (xobj._parent == null) {
                Cur tempCur = this._locale.tempCur();
                tempCur.createRoot();
                Xobj xobj2 = tempCur._xobj;
                tempCur.next();
                moveNode(tempCur);
                tempCur.release();
                xobj = xobj2;
                break;
            } else {
                xobj = xobj._parent;
            }
        }
        moveTo(xobj);
    }

    public boolean hasText() {
        return this._xobj.hasTextEnsureOccupancy();
    }

    public boolean hasAttrs() {
        return this._xobj.hasAttrs();
    }

    public boolean hasChildren() {
        return this._xobj.hasChildren();
    }

    public boolean toFirstChild() {
        if (!this._xobj.hasChildren()) {
            return false;
        }
        Xobj xobj = this._xobj._firstChild;
        while (xobj.isAttr()) {
            xobj = xobj._nextSibling;
        }
        moveTo(xobj);
        return true;
    }

    public boolean toLastChild() {
        if (!this._xobj.hasChildren()) {
            return false;
        }
        moveTo(this._xobj._lastChild);
        return true;
    }

    public boolean toNextSibling() {
        if (this._xobj.isAttr()) {
            if (this._xobj._nextSibling == null || !this._xobj._nextSibling.isAttr()) {
                return false;
            }
            moveTo(this._xobj._nextSibling);
            return true;
        } else if (this._xobj._nextSibling == null) {
            return false;
        } else {
            moveTo(this._xobj._nextSibling);
            return true;
        }
    }

    public void setValueAsQName(QName qName) {
        String localPart = qName.getLocalPart();
        String prefixForNamespace = prefixForNamespace(qName.getNamespaceURI(), qName.getPrefix().length() > 0 ? qName.getPrefix() : null, true);
        if (prefixForNamespace.length() > 0) {
            localPart = prefixForNamespace + ParameterizedMessage.ERROR_MSG_SEPARATOR + localPart;
        }
        setValue(localPart);
    }

    public void setValue(String str) {
        moveNodeContents((Cur) null, false);
        next();
        insertString(str);
        toParent();
    }

    public void removeFollowingAttrs() {
        QName name = getName();
        push();
        if (toNextAttr()) {
            while (isAttr()) {
                if (getName().equals(name)) {
                    moveNode((Cur) null);
                } else if (!toNextAttr()) {
                    break;
                }
            }
        }
        pop();
    }

    public String getAttrValue(QName qName) {
        push();
        String valueAsString = toAttr(qName) ? getValueAsString() : null;
        pop();
        return valueAsString;
    }

    public void setAttrValueAsQName(QName qName) {
        QName qName2 = Locale._xsiType;
        if (qName == null) {
            this._xobj.removeAttr(qName2);
            return;
        }
        if (toAttr(qName2)) {
            removeFollowingAttrs();
        } else {
            next();
            createAttr(qName2);
        }
        setValueAsQName(qName);
        toParent();
    }

    public boolean removeAttr(QName qName) {
        return this._xobj.removeAttr(qName);
    }

    public void setAttrValue(QName qName, String str) {
        this._xobj.setAttr(qName, str);
    }

    public boolean toAttr(QName qName) {
        Xobj attr = this._xobj.getAttr(qName);
        if (attr == null) {
            return false;
        }
        moveTo(attr);
        return true;
    }

    public boolean toFirstAttr() {
        Xobj firstAttr = this._xobj.firstAttr();
        if (firstAttr == null) {
            return false;
        }
        moveTo(firstAttr);
        return true;
    }

    public boolean toLastAttr() {
        if (!toFirstAttr()) {
            return false;
        }
        do {
        } while (toNextAttr());
        return true;
    }

    public boolean toNextAttr() {
        Xobj nextAttr = this._xobj.nextAttr();
        if (nextAttr == null) {
            return false;
        }
        moveTo(nextAttr);
        return true;
    }

    public boolean toPrevAttr() {
        if (!isAttr()) {
            prev();
            if (isContainer()) {
                return toLastAttr();
            }
            next();
            return false;
        } else if (this._xobj._prevSibling == null) {
            moveTo(this._xobj.ensureParent());
            return true;
        } else {
            moveTo(this._xobj._prevSibling);
            return true;
        }
    }

    public boolean skipWithAttrs() {
        if (skip()) {
            return true;
        }
        if (this._xobj.isRoot()) {
            return false;
        }
        toParent();
        next();
        return true;
    }

    public boolean skip() {
        if (this._xobj.isRoot()) {
            return false;
        }
        if (!this._xobj.isAttr()) {
            Xobj xobj = this._xobj;
            moveTo(getNormal(xobj, xobj.posAfter()), this._posTemp);
            return true;
        } else if (this._xobj._nextSibling == null || !this._xobj._nextSibling.isAttr()) {
            return false;
        } else {
            moveTo(this._xobj._nextSibling, 0);
            return true;
        }
    }

    public void toEnd() {
        moveTo(this._xobj, -1);
    }

    public void moveToCharNode(CharNode charNode) {
        moveToDom(charNode.getDom());
        this._xobj.ensureOccupancy();
        Xobj xobj = this._xobj;
        CharNode updateCharNodes = updateCharNodes(this._locale, xobj, xobj._charNodesValue, this._xobj._cchValue);
        xobj._charNodesValue = updateCharNodes;
        while (updateCharNodes != null) {
            if (charNode == updateCharNodes) {
                moveTo(getNormal(this._xobj, updateCharNodes._off + 1), this._posTemp);
                return;
            }
            updateCharNodes = updateCharNodes._next;
        }
        Xobj xobj2 = this._xobj;
        CharNode updateCharNodes2 = updateCharNodes(this._locale, xobj2, xobj2._charNodesAfter, this._xobj._cchAfter);
        xobj2._charNodesAfter = updateCharNodes2;
        while (updateCharNodes2 != null) {
            if (charNode == updateCharNodes2) {
                moveTo(getNormal(this._xobj, updateCharNodes2._off + this._xobj._cchValue + 2), this._posTemp);
                return;
            }
            updateCharNodes2 = updateCharNodes2._next;
        }
    }

    public boolean prevWithAttrs() {
        if (prev()) {
            return true;
        }
        if (!isAttr()) {
            return false;
        }
        toParent();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        if (r0._cchValue > 0) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        if (r2 > 1) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean prev() {
        /*
            r5 = this;
            org.apache.xmlbeans.impl.store.Xobj r0 = r5._xobj
            boolean r0 = r0.isRoot()
            r1 = 0
            if (r0 == 0) goto L_0x000e
            int r0 = r5._pos
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            org.apache.xmlbeans.impl.store.Xobj r0 = r5._xobj
            boolean r0 = r0.isAttr()
            if (r0 == 0) goto L_0x0021
            int r0 = r5._pos
            if (r0 != 0) goto L_0x0021
            org.apache.xmlbeans.impl.store.Xobj r0 = r5._xobj
            org.apache.xmlbeans.impl.store.Xobj r0 = r0._prevSibling
            if (r0 != 0) goto L_0x0021
            return r1
        L_0x0021:
            org.apache.xmlbeans.impl.store.Xobj r0 = r5.getDenormal()
            int r2 = r5._posTemp
            int r3 = r0.posAfter()
            r4 = 1
            if (r2 <= r3) goto L_0x0030
            r1 = r3
            goto L_0x005e
        L_0x0030:
            if (r2 != r3) goto L_0x004f
            boolean r2 = r0.isAttr()
            if (r2 == 0) goto L_0x004d
            int r2 = r0._cchAfter
            if (r2 > 0) goto L_0x0048
            org.apache.xmlbeans.impl.store.Xobj r2 = r0._nextSibling
            if (r2 == 0) goto L_0x0048
            org.apache.xmlbeans.impl.store.Xobj r2 = r0._nextSibling
            boolean r2 = r2.isAttr()
            if (r2 != 0) goto L_0x004d
        L_0x0048:
            org.apache.xmlbeans.impl.store.Xobj r0 = r0.ensureParent()
            goto L_0x005e
        L_0x004d:
            r1 = -1
            goto L_0x005e
        L_0x004f:
            int r3 = r3 + -1
            if (r2 != r3) goto L_0x005b
            r0.ensureOccupancy()
            int r2 = r0._cchValue
            if (r2 <= 0) goto L_0x005e
            goto L_0x005d
        L_0x005b:
            if (r2 <= r4) goto L_0x005e
        L_0x005d:
            r1 = r4
        L_0x005e:
            org.apache.xmlbeans.impl.store.Xobj r0 = r5.getNormal(r0, r1)
            int r1 = r5._posTemp
            r5.moveTo(r0, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cur.prev():boolean");
    }

    public boolean next(boolean z) {
        return z ? nextWithAttrs() : next();
    }

    public boolean nextWithAttrs() {
        int kind = kind();
        if (kindIsContainer(kind)) {
            if (toFirstAttr()) {
                return true;
            }
        } else if (kind == -3) {
            if (next()) {
                return true;
            }
            toParent();
            if (!toParentRaw()) {
                return false;
            }
        }
        return next();
    }

    public boolean next() {
        Xobj xobj = this._xobj;
        int i = this._pos;
        int posAfter = xobj.posAfter();
        if (i >= posAfter) {
            posAfter = this._xobj.posMax();
        } else if (i != -1) {
            if (i <= 0) {
                xobj.ensureOccupancy();
                if (xobj._cchValue == 0 && xobj._firstChild != null) {
                    if (xobj._firstChild.isAttr()) {
                        Xobj xobj2 = xobj._firstChild;
                        while (xobj2._nextSibling != null && xobj2._nextSibling.isAttr()) {
                            xobj2 = xobj2._nextSibling;
                        }
                        if (xobj2._cchAfter > 0) {
                            posAfter = xobj2.posAfter();
                            xobj = xobj2;
                        } else if (xobj2._nextSibling != null) {
                            xobj = xobj2._nextSibling;
                        }
                    } else {
                        xobj = xobj._firstChild;
                    }
                }
                posAfter = 1;
            } else if (xobj._firstChild != null) {
                xobj = xobj._firstChild;
            } else {
                posAfter = -1;
            }
            posAfter = 0;
        } else if (xobj.isRoot() || (xobj.isAttr() && (xobj._nextSibling == null || !xobj._nextSibling.isAttr()))) {
            return false;
        }
        moveTo(getNormal(xobj, posAfter), this._posTemp);
        return true;
    }

    /* access modifiers changed from: package-private */
    public int prevChars(int i) {
        int cchLeft = cchLeft();
        if (i < 0 || i > cchLeft) {
            i = cchLeft;
        }
        if (i != 0) {
            moveTo(getNormal(getDenormal(), this._posTemp - i), this._posTemp);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int nextChars(int i) {
        int cchRight = cchRight();
        if (cchRight == 0) {
            return 0;
        }
        if (i < 0 || i >= cchRight) {
            next();
            return cchRight;
        }
        moveTo(getNormal(this._xobj, this._pos + i), this._posTemp);
        return i;
    }

    /* access modifiers changed from: package-private */
    public void setCharNodes(CharNode charNode) {
        Xobj denormal = getDenormal();
        if (this._posTemp >= denormal.posAfter()) {
            denormal._charNodesAfter = charNode;
        } else {
            denormal._charNodesValue = charNode;
        }
        while (charNode != null) {
            charNode.setDom((DomImpl.Dom) denormal);
            charNode = charNode._next;
        }
    }

    /* access modifiers changed from: package-private */
    public CharNode getCharNodes() {
        Xobj denormal = getDenormal();
        if (this._posTemp >= denormal.posAfter()) {
            CharNode updateCharNodes = updateCharNodes(this._locale, denormal, denormal._charNodesAfter, denormal._cchAfter);
            denormal._charNodesAfter = updateCharNodes;
            return updateCharNodes;
        }
        denormal.ensureOccupancy();
        CharNode updateCharNodes2 = updateCharNodes(this._locale, denormal, denormal._charNodesValue, denormal._cchValue);
        denormal._charNodesValue = updateCharNodes2;
        return updateCharNodes2;
    }

    static CharNode updateCharNodes(Locale locale, Xobj xobj, CharNode charNode, int i) {
        CharNode charNode2 = charNode;
        int i2 = 0;
        while (charNode2 != null && i > 0) {
            if (charNode2._cch > i) {
                charNode2._cch = i;
            }
            charNode2._off = i2;
            i2 += charNode2._cch;
            i -= charNode2._cch;
            charNode2 = charNode2._next;
        }
        if (i <= 0) {
            while (charNode2 != null) {
                if (charNode2._cch != 0) {
                    charNode2._cch = 0;
                }
                charNode2._off = i2;
                charNode2 = charNode2._next;
            }
            return charNode;
        }
        TextNode createTextNode = locale.createTextNode();
        createTextNode.setDom((DomImpl.Dom) xobj);
        createTextNode._cch = i;
        createTextNode._off = i2;
        return CharNode.appendNode(charNode, createTextNode);
    }

    /* access modifiers changed from: package-private */
    public final QName getXsiTypeName() {
        return this._xobj.getXsiTypeName();
    }

    /* access modifiers changed from: package-private */
    public final void setXsiType(QName qName) {
        setAttrValueAsQName(qName);
    }

    /* access modifiers changed from: package-private */
    public final String namespaceForPrefix(String str, boolean z) {
        return this._xobj.namespaceForPrefix(str, z);
    }

    /* access modifiers changed from: package-private */
    public final String prefixForNamespace(String str, String str2, boolean z) {
        return (isContainer() ? this._xobj : getParent()).prefixForNamespace(str, str2, z);
    }

    /* access modifiers changed from: package-private */
    public boolean contains(Cur cur) {
        return this._xobj.contains(cur);
    }

    /* access modifiers changed from: package-private */
    public void insertString(String str) {
        if (str != null) {
            insertChars(str, 0, str.length());
        }
    }

    /* access modifiers changed from: package-private */
    public void insertChars(Object obj, int i, int i2) {
        if (i2 > 0) {
            this._locale.notifyChange();
            if (this._pos == -1) {
                this._xobj.ensureOccupancy();
            }
            Xobj denormal = getDenormal();
            int i3 = this._posTemp;
            denormal.insertCharsHelper(i3, obj, i, i2, true);
            moveTo(denormal, i3);
            this._locale._versionAll++;
        }
    }

    /* access modifiers changed from: package-private */
    public Object moveChars(Cur cur, int i) {
        if (i < 0) {
            i = cchRight();
        }
        if (i == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        Object chars = getChars(i);
        int i2 = this._offSrc;
        if (cur == null) {
            for (Bookmark bookmark = this._xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
                if (inChars(bookmark, i, false)) {
                    Cur tempCur = this._locale.tempCur();
                    tempCur.createRoot();
                    tempCur.next();
                    Object moveChars = moveChars(tempCur, i);
                    tempCur.release();
                    return moveChars;
                }
            }
        } else if (inChars(cur, i, true)) {
            cur.moveToCur(this);
            nextChars(i);
            this._offSrc = i2;
            this._cchSrc = i;
            return chars;
        } else {
            cur.insertChars(chars, i2, i);
        }
        this._locale.notifyChange();
        if (cur == null) {
            this._xobj.removeCharsHelper(this._pos, i, (Xobj) null, -2, false, true);
        } else {
            this._xobj.removeCharsHelper(this._pos, i, cur._xobj, cur._pos, false, true);
        }
        this._locale._versionAll++;
        this._offSrc = i2;
        this._cchSrc = i;
        return chars;
    }

    /* access modifiers changed from: package-private */
    public void moveNode(Cur cur) {
        Xobj xobj = this._xobj;
        skip();
        moveNode(xobj, cur);
    }

    private static void transferChars(Xobj xobj, int i, Xobj xobj2, int i2, int i3) {
        Xobj xobj3 = xobj2;
        int i4 = i2;
        xobj3.insertCharsHelper(i4, xobj.getCharsHelper(i, i3), xobj._locale._offSrc, xobj._locale._cchSrc, false);
        xobj.removeCharsHelper(i, i3, xobj3, i4, true, false);
    }

    static void moveNode(Xobj xobj, Cur cur) {
        Xobj xobj2;
        if (cur != null) {
            if (cur._pos == -1) {
                cur._xobj.ensureOccupancy();
            }
            if ((cur._pos == 0 && cur._xobj == xobj) || cur.isJustAfterEnd(xobj)) {
                cur.moveTo(xobj);
                return;
            }
        }
        xobj._locale.notifyChange();
        xobj._locale._versionAll++;
        xobj._locale._versionSansText++;
        if (!(cur == null || cur._locale == xobj._locale)) {
            cur._locale.notifyChange();
            cur._locale._versionAll++;
            cur._locale._versionSansText++;
        }
        if (xobj.isAttr()) {
            if (cur == null) {
                xobj2 = null;
            } else {
                xobj2 = cur.getParentRaw();
            }
            xobj.invalidateSpecialAttr(xobj2);
        } else {
            if (xobj._parent != null) {
                xobj._parent.invalidateUser();
            }
            if (cur != null && cur.hasParent()) {
                cur.getParent().invalidateUser();
            }
        }
        boolean z = false;
        if (xobj._cchAfter > 0) {
            transferChars(xobj, xobj.posAfter(), xobj.getDenormal(0), xobj.posTemp(), xobj._cchAfter);
        }
        xobj._locale.embedCurs();
        Xobj xobj3 = xobj;
        while (xobj3 != null) {
            while (xobj3._embedded != null) {
                xobj3._embedded.moveTo(xobj.getNormal(xobj.posAfter()));
            }
            xobj3.disconnectUser();
            if (cur != null) {
                xobj3._locale = cur._locale;
            }
            xobj3 = xobj3.walk(xobj, true);
        }
        xobj.removeXobj();
        if (cur != null) {
            Xobj xobj4 = cur._xobj;
            boolean z2 = cur._pos != 0;
            int cchRight = cur.cchRight();
            if (cchRight > 0) {
                cur.push();
                cur.next();
                xobj4 = cur._xobj;
                if (cur._pos != 0) {
                    z = true;
                }
                cur.pop();
                z2 = z;
            }
            if (z2) {
                xobj4.appendXobj(xobj);
            } else {
                xobj4.insertXobj(xobj);
            }
            if (cchRight > 0) {
                transferChars(cur._xobj, cur._pos, xobj, xobj.posAfter(), cchRight);
            }
            cur.moveTo(xobj);
        }
    }

    /* access modifiers changed from: package-private */
    public void moveNodeContents(Cur cur, boolean z) {
        moveNodeContents(this._xobj, cur, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x012c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void moveNodeContents(org.apache.xmlbeans.impl.store.Xobj r11, org.apache.xmlbeans.impl.store.Cur r12, boolean r13) {
        /*
            boolean r0 = r11.hasAttrs()
            boolean r1 = r11.hasChildren()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0012
            if (r13 == 0) goto L_0x0010
            if (r0 != 0) goto L_0x0012
        L_0x0010:
            r1 = r2
            goto L_0x0013
        L_0x0012:
            r1 = r3
        L_0x0013:
            r4 = 0
            r5 = -1
            r6 = 1
            if (r1 == 0) goto L_0x0048
            boolean r13 = r11.isVacant()
            if (r13 == 0) goto L_0x0034
            if (r12 != 0) goto L_0x0034
            r12 = 256(0x100, float:3.59E-43)
            r11.clearBit(r12)
            r11.invalidateUser()
            r11.invalidateSpecialAttr(r4)
            org.apache.xmlbeans.impl.store.Locale r11 = r11._locale
            long r12 = r11._versionAll
            long r12 = r12 + r6
            r11._versionAll = r12
            goto L_0x0047
        L_0x0034:
            boolean r13 = r11.hasTextEnsureOccupancy()
            if (r13 == 0) goto L_0x0047
            org.apache.xmlbeans.impl.store.Cur r11 = r11.tempCur()
            r11.next()
            r11.moveChars(r12, r5)
            r11.release()
        L_0x0047:
            return
        L_0x0048:
            if (r12 == 0) goto L_0x0081
            org.apache.xmlbeans.impl.store.Xobj r1 = r12._xobj
            if (r11 != r1) goto L_0x005f
            int r1 = r12._pos
            if (r1 != r5) goto L_0x005f
            r12.moveTo(r11)
            if (r13 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r2 = r3
        L_0x005b:
            r12.next(r2)
            return
        L_0x005f:
            org.apache.xmlbeans.impl.store.Locale r1 = r12._locale
            org.apache.xmlbeans.impl.store.Locale r8 = r11._locale
            if (r1 != r8) goto L_0x007d
            r12.push()
            r12.moveTo(r11)
            if (r13 == 0) goto L_0x0071
            if (r0 == 0) goto L_0x0071
            r0 = r2
            goto L_0x0072
        L_0x0071:
            r0 = r3
        L_0x0072:
            r12.next(r0)
            boolean r0 = r12.isAtLastPush()
            r12.pop()
            goto L_0x007e
        L_0x007d:
            r0 = r3
        L_0x007e:
            if (r0 == 0) goto L_0x0081
            return
        L_0x0081:
            boolean r0 = r11.hasTextNoEnsureOccupancy()
            if (r0 == 0) goto L_0x009c
            org.apache.xmlbeans.impl.store.Cur r0 = r11.tempCur()
            r0.next()
            r0.moveChars(r12, r5)
            r0.release()
            if (r12 == 0) goto L_0x009c
            int r0 = r0._cchSrc
            r12.nextChars(r0)
            goto L_0x009d
        L_0x009c:
            r0 = r3
        L_0x009d:
            org.apache.xmlbeans.impl.store.Locale r1 = r11._locale
            r1.embedCurs()
            org.apache.xmlbeans.impl.store.Xobj r1 = r11.walk(r11, r2)
            r8 = r1
            r9 = r3
        L_0x00a8:
            if (r1 == 0) goto L_0x00e4
            org.apache.xmlbeans.impl.store.Xobj r10 = r1._parent
            if (r10 != r11) goto L_0x00c4
            boolean r10 = r1.isAttr()
            if (r10 == 0) goto L_0x00c4
            if (r13 != 0) goto L_0x00b9
            org.apache.xmlbeans.impl.store.Xobj r8 = r1._nextSibling
            goto L_0x00df
        L_0x00b9:
            if (r12 != 0) goto L_0x00bd
            r10 = r4
            goto L_0x00c1
        L_0x00bd:
            org.apache.xmlbeans.impl.store.Xobj r10 = r12.getParent()
        L_0x00c1:
            r1.invalidateSpecialAttr(r10)
        L_0x00c4:
            org.apache.xmlbeans.impl.store.Cur r10 = r1._embedded
            if (r10 == 0) goto L_0x00cc
            r10.moveTo(r11, r5)
            goto L_0x00c4
        L_0x00cc:
            r1.disconnectUser()
            if (r12 == 0) goto L_0x00d5
            org.apache.xmlbeans.impl.store.Locale r10 = r12._locale
            r1._locale = r10
        L_0x00d5:
            if (r9 != 0) goto L_0x00de
            org.apache.xmlbeans.impl.store.Bookmark r9 = r1._bookmarks
            if (r9 == 0) goto L_0x00dc
            goto L_0x00de
        L_0x00dc:
            r9 = r3
            goto L_0x00df
        L_0x00de:
            r9 = r2
        L_0x00df:
            org.apache.xmlbeans.impl.store.Xobj r1 = r1.walk(r11, r2)
            goto L_0x00a8
        L_0x00e4:
            org.apache.xmlbeans.impl.store.Xobj r13 = r11._lastChild
            if (r9 == 0) goto L_0x00f7
            if (r12 != 0) goto L_0x00f7
            org.apache.xmlbeans.impl.store.Locale r12 = r11._locale
            org.apache.xmlbeans.impl.store.Cur r12 = r12.tempCur()
            r12.createRoot()
            r12.next()
            r4 = r12
        L_0x00f7:
            boolean r1 = r13.isAttr()
            if (r1 != 0) goto L_0x0100
            r11.invalidateUser()
        L_0x0100:
            org.apache.xmlbeans.impl.store.Locale r1 = r11._locale
            long r9 = r1._versionAll
            long r9 = r9 + r6
            r1._versionAll = r9
            org.apache.xmlbeans.impl.store.Locale r1 = r11._locale
            long r9 = r1._versionSansText
            long r9 = r9 + r6
            r1._versionSansText = r9
            if (r12 == 0) goto L_0x0127
            if (r0 != 0) goto L_0x0127
            org.apache.xmlbeans.impl.store.Xobj r1 = r12.getParent()
            r1.invalidateUser()
            org.apache.xmlbeans.impl.store.Locale r1 = r12._locale
            long r9 = r1._versionAll
            long r9 = r9 + r6
            r1._versionAll = r9
            org.apache.xmlbeans.impl.store.Locale r1 = r12._locale
            long r9 = r1._versionSansText
            long r9 = r9 + r6
            r1._versionSansText = r9
        L_0x0127:
            r11.removeXobjs(r8, r13)
            if (r12 == 0) goto L_0x01ae
            org.apache.xmlbeans.impl.store.Xobj r11 = r12._xobj
            int r1 = r12._pos
            if (r1 == 0) goto L_0x0134
            r1 = r2
            goto L_0x0135
        L_0x0134:
            r1 = r3
        L_0x0135:
            int r5 = r12.cchRight()
            if (r5 <= 0) goto L_0x014c
            r12.push()
            r12.next()
            org.apache.xmlbeans.impl.store.Xobj r11 = r12._xobj
            int r1 = r12._pos
            if (r1 == 0) goto L_0x0148
            r3 = r2
        L_0x0148:
            r12.pop()
            r1 = r3
        L_0x014c:
            boolean r3 = r8.isAttr()
            if (r3 == 0) goto L_0x0192
            r3 = r8
        L_0x0153:
            org.apache.xmlbeans.impl.store.Xobj r6 = r3._nextSibling
            if (r6 == 0) goto L_0x0162
            org.apache.xmlbeans.impl.store.Xobj r6 = r3._nextSibling
            boolean r6 = r6.isAttr()
            if (r6 == 0) goto L_0x0162
            org.apache.xmlbeans.impl.store.Xobj r3 = r3._nextSibling
            goto L_0x0153
        L_0x0162:
            org.apache.xmlbeans.impl.store.Xobj r6 = r12.getParent()
            if (r5 <= 0) goto L_0x0173
            org.apache.xmlbeans.impl.store.Xobj r7 = r12._xobj
            int r9 = r12._pos
            int r10 = r3.posMax()
            transferChars(r7, r9, r3, r10, r5)
        L_0x0173:
            boolean r5 = r6.hasTextNoEnsureOccupancy()
            if (r5 == 0) goto L_0x019f
            int r5 = r6._cchValue
            if (r5 <= 0) goto L_0x0180
            int r5 = r6._cchValue
            goto L_0x018a
        L_0x0180:
            org.apache.xmlbeans.impl.store.Xobj r6 = r6.lastAttr()
            int r2 = r6.posAfter()
            int r5 = r6._cchAfter
        L_0x018a:
            int r7 = r3.posAfter()
            transferChars(r6, r2, r3, r7, r5)
            goto L_0x019f
        L_0x0192:
            if (r5 <= 0) goto L_0x019f
            org.apache.xmlbeans.impl.store.Xobj r2 = r12._xobj
            int r3 = r12._pos
            int r6 = r13.posMax()
            transferChars(r2, r3, r13, r6, r5)
        L_0x019f:
            if (r1 == 0) goto L_0x01a5
            r11.appendXobjs(r8, r13)
            goto L_0x01a8
        L_0x01a5:
            r11.insertXobjs(r8, r13)
        L_0x01a8:
            r12.moveTo(r8)
            r12.prevChars(r0)
        L_0x01ae:
            if (r4 == 0) goto L_0x01b3
            r4.release()
        L_0x01b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cur.moveNodeContents(org.apache.xmlbeans.impl.store.Xobj, org.apache.xmlbeans.impl.store.Cur, boolean):void");
    }

    /* access modifiers changed from: protected */
    public final Bookmark setBookmark(Object obj, Object obj2) {
        return this._xobj.setBookmark(this._pos, obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public Object getBookmark(Object obj) {
        for (Bookmark bookmark = this._xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
            if (bookmark._pos == this._pos && bookmark._key == obj) {
                return bookmark._value;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int firstBookmarkInChars(Object obj, int i) {
        if (!isText()) {
            return -1;
        }
        int i2 = -1;
        for (Bookmark bookmark = this._xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
            if (bookmark._key == obj && inChars(bookmark, i, false) && (i2 == -1 || bookmark._pos - this._pos < i2)) {
                i2 = bookmark._pos - this._pos;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int firstBookmarkInCharsLeft(Object obj, int i) {
        if (cchLeft() <= 0) {
            return -1;
        }
        Xobj denormal = getDenormal();
        int i2 = this._posTemp - i;
        int i3 = -1;
        for (Bookmark bookmark = denormal._bookmarks; bookmark != null; bookmark = bookmark._next) {
            if (bookmark._key == obj) {
                if (denormal.inChars(i2, bookmark._xobj, bookmark._pos, i, false) && (i3 == -1 || bookmark._pos - i2 < i3)) {
                    i3 = bookmark._pos - i2;
                }
            }
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public String getCharsAsString() {
        return getCharsAsString(1);
    }

    /* access modifiers changed from: package-private */
    public String getCharsAsString(int i) {
        return this._xobj.getCharsAsString(this._pos, -1, i);
    }

    /* access modifiers changed from: package-private */
    public String getValueAsString(int i) {
        return this._xobj.getValueAsString(i);
    }

    /* access modifiers changed from: package-private */
    public String getValueAsString() {
        return this._xobj.getValueAsString();
    }

    /* access modifiers changed from: package-private */
    public Object getChars(int i) {
        return this._xobj.getChars(this._pos, i, this);
    }

    /* access modifiers changed from: package-private */
    public Object getFirstChars() {
        Object firstChars = this._xobj.getFirstChars();
        this._offSrc = this._locale._offSrc;
        this._cchSrc = this._locale._cchSrc;
        return firstChars;
    }

    /* access modifiers changed from: package-private */
    public void copyNode(Cur cur) {
        Xobj copyNode = this._xobj.copyNode(cur._locale);
        if (cur.isPositioned()) {
            moveNode(copyNode, cur);
        } else {
            cur.moveTo(copyNode);
        }
    }

    public Cur weakCur(Object obj) {
        Cur weakCur = this._locale.weakCur(obj);
        weakCur.moveToCur(this);
        return weakCur;
    }

    /* access modifiers changed from: package-private */
    public Cur tempCur() {
        Cur tempCur = this._locale.tempCur((String) null);
        tempCur.moveToCur(this);
        return tempCur;
    }

    private Cur tempCur(Xobj xobj, int i) {
        Cur tempCur = this._locale.tempCur();
        if (xobj != null) {
            tempCur.moveTo(getNormal(xobj, i), this._posTemp);
        }
        return tempCur;
    }

    /* access modifiers changed from: package-private */
    public boolean inChars(Cur cur, int i, boolean z) {
        return this._xobj.inChars(this._pos, cur._xobj, cur._pos, i, z);
    }

    /* access modifiers changed from: package-private */
    public boolean inChars(Bookmark bookmark, int i, boolean z) {
        return this._xobj.inChars(this._pos, bookmark._xobj, bookmark._pos, i, z);
    }

    private Xobj getNormal(Xobj xobj, int i) {
        Xobj normal = xobj.getNormal(i);
        this._posTemp = xobj._locale._posTemp;
        return normal;
    }

    private Xobj getDenormal() {
        return getDenormal(this._xobj, this._pos);
    }

    private Xobj getDenormal(Xobj xobj, int i) {
        Xobj denormal = xobj.getDenormal(i);
        this._posTemp = xobj._locale._posTemp;
        return denormal;
    }

    /* access modifiers changed from: package-private */
    public void setType(SchemaType schemaType) {
        setType(schemaType, true);
    }

    /* access modifiers changed from: package-private */
    public void setType(SchemaType schemaType, boolean z) {
        TypeStoreUser peekUser = peekUser();
        if (peekUser != null && peekUser.get_schema_type() == schemaType) {
            return;
        }
        if (isRoot()) {
            this._xobj.setStableType(schemaType);
            return;
        }
        TypeStoreUser user = this._xobj.ensureParent().getUser();
        if (isAttr()) {
            if (z && user.get_attribute_type(getName()) != schemaType) {
                throw new IllegalArgumentException("Can't set type of attribute to " + schemaType.toString());
            }
        } else if (user.get_element_type(getName(), (QName) null) == schemaType) {
            removeAttr(Locale._xsiType);
        } else {
            QName name = schemaType.getName();
            if (name == null) {
                if (z) {
                    throw new IllegalArgumentException("Can't set type of element, type is un-named");
                }
            } else if (user.get_element_type(getName(), name) == schemaType) {
                setAttrValueAsQName(name);
            } else if (z) {
                throw new IllegalArgumentException("Can't set type of element, invalid type");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSubstitution(QName qName, SchemaType schemaType) {
        TypeStoreUser peekUser = peekUser();
        if ((peekUser == null || peekUser.get_schema_type() != schemaType || !qName.equals(getName())) && !isRoot()) {
            TypeStoreUser user = this._xobj.ensureParent().getUser();
            if (!isAttr()) {
                if (user.get_element_type(qName, (QName) null) == schemaType) {
                    setName(qName);
                    removeAttr(Locale._xsiType);
                    return;
                }
                QName name = schemaType.getName();
                if (name != null && user.get_element_type(qName, name) == schemaType) {
                    setName(qName);
                    setAttrValueAsQName(name);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public TypeStoreUser peekUser() {
        return this._xobj._user;
    }

    public XmlObject getObject() {
        if (isUserNode()) {
            return (XmlObject) getUser();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TypeStoreUser getUser() {
        return this._xobj.getUser();
    }

    public DomImpl.Dom getDom() {
        if (!isText()) {
            return this._xobj.getDom();
        }
        int cchLeft = cchLeft();
        CharNode charNodes = getCharNodes();
        while (true) {
            cchLeft -= charNodes._cch;
            if (cchLeft < 0) {
                return charNodes;
            }
            charNodes = charNodes._next;
        }
    }

    public void release() {
        if (this._tempFrame >= 0) {
            Cur cur = this._nextTemp;
            if (cur != null) {
                cur._prevTemp = this._prevTemp;
            }
            Cur cur2 = this._prevTemp;
            if (cur2 == null) {
                this._locale._tempFrames[this._tempFrame] = this._nextTemp;
            } else {
                cur2._nextTemp = cur;
            }
            this._nextTemp = null;
            this._prevTemp = null;
            this._tempFrame = -1;
        }
        int i = this._state;
        if (i != 0 && i != 3) {
            while (this._stackTop != -1) {
                popButStay();
            }
            clearSelection();
            this._id = null;
            moveToCur((Cur) null);
            Locale.Ref ref = this._ref;
            if (ref != null) {
                ref.clear();
                this._ref._cur = null;
            }
            this._ref = null;
            Locale locale = this._locale;
            locale._registered = listRemove(locale._registered);
            if (this._locale._curPoolCount < 16) {
                Locale locale2 = this._locale;
                locale2._curPool = listInsert(locale2._curPool);
                this._state = 0;
                this._locale._curPoolCount++;
                return;
            }
            this._locale = null;
            this._state = 3;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isOnList(Cur cur) {
        while (cur != null) {
            if (cur == this) {
                return true;
            }
            cur = cur._next;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Cur listInsert(Cur cur) {
        if (cur == null) {
            this._prev = this;
            return this;
        }
        this._prev = cur._prev;
        cur._prev._next = this;
        cur._prev = this;
        return cur;
    }

    /* access modifiers changed from: package-private */
    public Cur listRemove(Cur cur) {
        Cur cur2 = this._prev;
        if (cur2 == this) {
            cur = null;
        } else {
            if (cur == this) {
                cur = this._next;
            } else {
                cur2._next = this._next;
            }
            Cur cur3 = this._next;
            if (cur3 != null) {
                cur3._prev = cur2;
                this._next = null;
            } else if (cur != null) {
                cur._prev = cur2;
            }
        }
        this._prev = null;
        return cur;
    }

    /* access modifiers changed from: package-private */
    public boolean isNormal() {
        int i = this._state;
        if (i == 0 || i == 3) {
            return false;
        }
        Xobj xobj = this._xobj;
        if (xobj == null) {
            if (this._pos == -2) {
                return true;
            }
            return false;
        } else if (!xobj.isNormal(this._pos)) {
            return false;
        } else {
            if (this._state == 2) {
                return isOnList(this._xobj._embedded);
            }
            return isOnList(this._locale._registered);
        }
    }

    public static final class CurLoadContext extends Locale.LoadContext {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Map<String, String> _additionalNamespaces;
        private boolean _after;
        private final CharUtil _charUtil;
        private final boolean _discardDocElem;
        private String _doctypeName;
        private String _doctypePublicId;
        private String _doctypeSystemId;
        private Xobj _frontier;
        private int _lastPos;
        private Xobj _lastXobj;
        private final Locale _locale;
        private final QName _replaceDocElem;
        private final boolean _stripComments;
        private boolean _stripLeft = true;
        private final boolean _stripProcinsts;
        private final boolean _stripWhitespace;
        private final Map<String, String> _substituteNamespaces;

        /* access modifiers changed from: protected */
        public void endDTD() {
        }

        static {
            Class<Cur> cls = Cur.class;
        }

        public CurLoadContext(Locale locale, XmlOptions xmlOptions) {
            CharUtil charUtil;
            XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
            this._locale = locale;
            if (maskNull.isLoadUseLocaleCharUtil()) {
                charUtil = locale.getCharUtil();
            } else {
                charUtil = CharUtil.getThreadLocalCharUtil();
            }
            this._charUtil = charUtil;
            Xobj createDomDocumentRootXobj = Cur.createDomDocumentRootXobj(locale);
            this._frontier = createDomDocumentRootXobj;
            this._after = false;
            this._lastXobj = createDomDocumentRootXobj;
            this._lastPos = 0;
            this._replaceDocElem = maskNull.getLoadReplaceDocumentElement();
            this._discardDocElem = maskNull.hasOption(XmlOptions.XmlOptionsKeys.LOAD_REPLACE_DOCUMENT_ELEMENT);
            this._stripWhitespace = maskNull.isSetLoadStripWhitespace();
            this._stripComments = maskNull.isLoadStripComments();
            this._stripProcinsts = maskNull.isLoadStripProcinsts();
            this._substituteNamespaces = maskNull.getLoadSubstituteNamespaces();
            this._additionalNamespaces = maskNull.getLoadAdditionalNamespaces();
            locale._versionAll++;
            locale._versionSansText++;
        }

        private void start(Xobj xobj) {
            flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
                this._after = false;
            }
            this._frontier.appendXobj(xobj);
            this._frontier = xobj;
            this._lastXobj = xobj;
            this._lastPos = 0;
        }

        private void end() {
            flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
            } else {
                this._after = true;
            }
            this._lastXobj = this._frontier;
            this._lastPos = -1;
        }

        private void text(Object obj, int i, int i2) {
            if (i2 > 0) {
                Xobj xobj = this._frontier;
                this._lastXobj = xobj;
                int i3 = xobj._cchValue + 1;
                this._lastPos = i3;
                if (this._after) {
                    this._lastPos = i3 + this._frontier._cchAfter + 1;
                    Xobj xobj2 = this._frontier;
                    xobj2._srcAfter = this._charUtil.saveChars(obj, i, i2, xobj2._srcAfter, this._frontier._offAfter, this._frontier._cchAfter);
                    this._frontier._offAfter = this._charUtil._offSrc;
                    this._frontier._cchAfter = this._charUtil._cchSrc;
                    return;
                }
                Xobj xobj3 = this._frontier;
                xobj3._srcValue = this._charUtil.saveChars(obj, i, i2, xobj3._srcValue, this._frontier._offValue, this._frontier._cchValue);
                this._frontier._offValue = this._charUtil._offSrc;
                this._frontier._cchValue = this._charUtil._cchSrc;
            }
        }

        private void flushText() {
            if (!this._stripWhitespace) {
                return;
            }
            if (this._after) {
                Xobj xobj = this._frontier;
                xobj._srcAfter = this._charUtil.stripRight(xobj._srcAfter, this._frontier._offAfter, this._frontier._cchAfter);
                this._frontier._offAfter = this._charUtil._offSrc;
                this._frontier._cchAfter = this._charUtil._cchSrc;
                return;
            }
            Xobj xobj2 = this._frontier;
            xobj2._srcValue = this._charUtil.stripRight(xobj2._srcValue, this._frontier._offValue, this._frontier._cchValue);
            this._frontier._offValue = this._charUtil._offSrc;
            this._frontier._cchValue = this._charUtil._cchSrc;
        }

        private Xobj parent() {
            boolean z = this._after;
            Xobj xobj = this._frontier;
            return z ? xobj._parent : xobj;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
            r3 = r1._substituteNamespaces.get(r2.getNamespaceURI());
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private javax.xml.namespace.QName checkName(javax.xml.namespace.QName r2, boolean r3) {
            /*
                r1 = this;
                java.util.Map<java.lang.String, java.lang.String> r0 = r1._substituteNamespaces
                if (r0 == 0) goto L_0x002c
                if (r3 == 0) goto L_0x0010
                java.lang.String r3 = r2.getNamespaceURI()
                int r3 = r3.length()
                if (r3 <= 0) goto L_0x002c
            L_0x0010:
                java.util.Map<java.lang.String, java.lang.String> r3 = r1._substituteNamespaces
                java.lang.String r0 = r2.getNamespaceURI()
                java.lang.Object r3 = r3.get(r0)
                java.lang.String r3 = (java.lang.String) r3
                if (r3 == 0) goto L_0x002c
                org.apache.xmlbeans.impl.store.Locale r1 = r1._locale
                java.lang.String r0 = r2.getLocalPart()
                java.lang.String r2 = r2.getPrefix()
                javax.xml.namespace.QName r2 = r1.makeQName(r3, r0, r2)
            L_0x002c:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cur.CurLoadContext.checkName(javax.xml.namespace.QName, boolean):javax.xml.namespace.QName");
        }

        /* access modifiers changed from: protected */
        public void startDTD(String str, String str2, String str3) {
            this._doctypeName = str;
            this._doctypePublicId = str2;
            this._doctypeSystemId = str3;
        }

        /* access modifiers changed from: protected */
        public void startElement(QName qName) {
            start(Cur.createElementXobj(this._locale, checkName(qName, false), parent()._name));
            this._stripLeft = true;
        }

        /* access modifiers changed from: protected */
        public void endElement() {
            end();
            this._stripLeft = true;
        }

        /* access modifiers changed from: protected */
        public void xmlns(String str, String str2) {
            String str3;
            Map<String, String> map = this._substituteNamespaces;
            if (!(map == null || (str3 = map.get(str2)) == null)) {
                str2 = str3;
            }
            Locale locale = this._locale;
            AttrXobj attrXobj = new AttrXobj(locale, locale.createXmlns(str));
            start(attrXobj);
            text((Object) str2, 0, str2.length());
            end();
            this._lastXobj = attrXobj;
            this._lastPos = 0;
        }

        public void attr(QName qName, String str) {
            Xobj xobj;
            Xobj xobj2;
            if (this._after) {
                xobj = this._lastXobj._parent;
            } else {
                xobj = this._lastXobj;
            }
            boolean isAttrOfTypeId = isAttrOfTypeId(qName, xobj.getQName());
            if (isAttrOfTypeId) {
                xobj2 = new AttrIdXobj(this._locale, checkName(qName, true));
            } else {
                xobj2 = new AttrXobj(this._locale, checkName(qName, true));
            }
            start(xobj2);
            text((Object) str, 0, str.length());
            end();
            if (isAttrOfTypeId) {
                Cur tempCur = xobj2.tempCur();
                tempCur.toRoot();
                Xobj xobj3 = tempCur._xobj;
                tempCur.release();
                if (xobj3 instanceof DocumentXobj) {
                    ((DocumentXobj) xobj3).addIdElement(str, xobj2._parent.getDom());
                }
            }
            this._lastXobj = xobj2;
            this._lastPos = 0;
        }

        /* access modifiers changed from: protected */
        public void attr(String str, String str2, String str3, String str4) {
            attr(this._locale.makeQName(str2, str, str3), str4);
        }

        /* access modifiers changed from: protected */
        public void procInst(String str, String str2) {
            if (!this._stripProcinsts) {
                ProcInstXobj procInstXobj = new ProcInstXobj(this._locale, str);
                start(procInstXobj);
                text((Object) str2, 0, str2.length());
                end();
                this._lastXobj = procInstXobj;
                this._lastPos = 0;
            }
            this._stripLeft = true;
        }

        /* access modifiers changed from: protected */
        public void comment(String str) {
            if (!this._stripComments) {
                comment((Object) str, 0, str.length());
            }
            this._stripLeft = true;
        }

        /* access modifiers changed from: protected */
        public void comment(char[] cArr, int i, int i2) {
            if (!this._stripComments) {
                comment(this._charUtil.saveChars(cArr, i, i2), this._charUtil._offSrc, this._charUtil._cchSrc);
            }
            this._stripLeft = true;
        }

        private void comment(Object obj, int i, int i2) {
            CommentXobj commentXobj = new CommentXobj(this._locale);
            start(commentXobj);
            text(obj, i, i2);
            end();
            this._lastXobj = commentXobj;
            this._lastPos = 0;
        }

        private void stripText(Object obj, int i, int i2) {
            if (this._stripWhitespace && this._stripLeft) {
                obj = this._charUtil.stripLeft(obj, i, i2);
                this._stripLeft = false;
                i = this._charUtil._offSrc;
                i2 = this._charUtil._cchSrc;
            }
            text(obj, i, i2);
        }

        /* access modifiers changed from: protected */
        public void text(String str) {
            if (str != null) {
                stripText(str, 0, str.length());
            }
        }

        /* access modifiers changed from: protected */
        public void text(char[] cArr, int i, int i2) {
            stripText(cArr, i, i2);
        }

        /* access modifiers changed from: protected */
        public void bookmark(XmlCursor.XmlBookmark xmlBookmark) {
            this._lastXobj.setBookmark(this._lastPos, xmlBookmark.getKey(), xmlBookmark);
        }

        /* access modifiers changed from: protected */
        public void bookmarkLastNonAttr(XmlCursor.XmlBookmark xmlBookmark) {
            if (this._lastPos > 0 || !this._lastXobj.isAttr()) {
                this._lastXobj.setBookmark(this._lastPos, xmlBookmark.getKey(), xmlBookmark);
            } else {
                this._lastXobj._parent.setBookmark(0, xmlBookmark.getKey(), xmlBookmark);
            }
        }

        /* access modifiers changed from: protected */
        public void bookmarkLastAttr(QName qName, XmlCursor.XmlBookmark xmlBookmark) {
            Xobj attr;
            if (this._lastPos == 0 && this._lastXobj.isAttr() && (attr = this._lastXobj._parent.getAttr(qName)) != null) {
                attr.setBookmark(0, xmlBookmark.getKey(), xmlBookmark);
            }
        }

        /* access modifiers changed from: protected */
        public void lineNumber(int i, int i2, int i3) {
            this._lastXobj.setBookmark(this._lastPos, XmlLineNumber.class, new XmlLineNumber(i, i2, i3));
        }

        /* access modifiers changed from: protected */
        public void abort() {
            this._stripLeft = true;
            while (!parent().isRoot()) {
                end();
            }
            finish().release();
        }

        public Cur finish() {
            flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
            }
            Cur tempCur = this._frontier.tempCur();
            if (!Locale.toFirstChildElement(tempCur)) {
                return tempCur;
            }
            boolean isFragmentQName = Locale.isFragmentQName(tempCur.getName());
            if (this._discardDocElem || isFragmentQName) {
                QName qName = this._replaceDocElem;
                if (qName != null) {
                    tempCur.setName(qName);
                } else {
                    do {
                    } while (tempCur.toParent());
                    tempCur.next();
                    while (!tempCur.isElem()) {
                        if (tempCur.isText()) {
                            tempCur.moveChars((Cur) null, -1);
                        } else {
                            tempCur.moveNode((Cur) null);
                        }
                    }
                    tempCur.skip();
                    while (!tempCur.isFinish()) {
                        if (tempCur.isText()) {
                            tempCur.moveChars((Cur) null, -1);
                        } else {
                            tempCur.moveNode((Cur) null);
                        }
                    }
                    tempCur.toParent();
                    tempCur.next();
                    Cur tempCur2 = tempCur.tempCur();
                    tempCur.moveNodeContents(tempCur, true);
                    tempCur.moveToCur(tempCur2);
                    tempCur2.release();
                    tempCur.moveNode((Cur) null);
                }
                if (isFragmentQName) {
                    tempCur.moveTo(this._frontier);
                    if (tempCur.toFirstAttr()) {
                        while (true) {
                            if (tempCur.isXmlns() && tempCur.getXmlnsUri().equals("http://www.openuri.org/fragment")) {
                                tempCur.moveNode((Cur) null);
                                if (!tempCur.isAttr()) {
                                    break;
                                }
                            } else if (!tempCur.toNextAttr()) {
                                break;
                            }
                        }
                    }
                    tempCur.moveTo(this._frontier);
                    Xobj createDomDocumentRootXobj = Cur.createDomDocumentRootXobj(this._locale, true);
                    this._frontier = createDomDocumentRootXobj;
                    Cur tempCur3 = createDomDocumentRootXobj.tempCur();
                    tempCur3.next();
                    tempCur.moveNodeContents(tempCur3, true);
                    tempCur.moveTo(this._frontier);
                    tempCur3.release();
                }
            }
            if (this._additionalNamespaces != null) {
                tempCur.moveTo(this._frontier);
                Locale.toFirstChildElement(tempCur);
                Locale.applyNamespaces(tempCur, this._additionalNamespaces);
            }
            if (!(this._doctypeName == null || (this._doctypePublicId == null && this._doctypeSystemId == null))) {
                XmlDocumentProperties docProps = Locale.getDocProps(tempCur, true);
                docProps.setDoctypeName(this._doctypeName);
                String str = this._doctypePublicId;
                if (str != null) {
                    docProps.setDoctypePublicId(str);
                }
                String str2 = this._doctypeSystemId;
                if (str2 != null) {
                    docProps.setDoctypeSystemId(str2);
                }
            }
            tempCur.moveTo(this._frontier);
            return tempCur;
        }

        public void dump() {
            this._frontier.dump();
        }
    }

    static String kindName(int i) {
        if (i == 0) {
            return "TEXT";
        }
        if (i == 1) {
            return "ROOT";
        }
        if (i == 2) {
            return "ELEM";
        }
        if (i == 3) {
            return "ATTR";
        }
        if (i != 4) {
            return i != 5 ? "<< Unknown Kind (" + i + ") >>" : "PROCINST";
        }
        return "COMMENT";
    }

    /* access modifiers changed from: package-private */
    public void dump() {
        dump(System.out, this._xobj, this);
    }

    /* access modifiers changed from: package-private */
    public void dump(PrintStream printStream) {
        Xobj xobj = this._xobj;
        if (xobj == null) {
            printStream.println("Unpositioned xptr");
        } else {
            dump(printStream, xobj, this);
        }
    }

    public static void dump(PrintStream printStream, Xobj xobj, Object obj) {
        if (obj == null) {
            obj = xobj;
        }
        while (xobj._parent != null) {
            xobj = xobj._parent;
        }
        dumpXobj(printStream, xobj, 0, obj);
        printStream.println();
    }

    private static void dumpCur(PrintStream printStream, String str, Cur cur, Object obj) {
        printStream.print(" ");
        if (obj == cur) {
            printStream.print("*:");
        }
        StringBuilder append = new StringBuilder().append(str);
        String str2 = cur._id;
        if (str2 == null) {
            str2 = "<cur>";
        }
        printStream.print(append.append(str2).append("[").append(cur._pos).append("]").toString());
    }

    private static void dumpCurs(PrintStream printStream, Xobj xobj, Object obj) {
        for (Cur cur = xobj._embedded; cur != null; cur = cur._next) {
            dumpCur(printStream, "E:", cur, obj);
        }
        for (Cur cur2 = xobj._locale._registered; cur2 != null; cur2 = cur2._next) {
            if (cur2._xobj == xobj) {
                dumpCur(printStream, "R:", cur2, obj);
            }
        }
    }

    private static void dumpBookmarks(PrintStream printStream, Xobj xobj, Object obj) {
        for (Bookmark bookmark = xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
            printStream.print(" ");
            if (obj == bookmark) {
                printStream.print("*:");
            }
            if (bookmark._value instanceof XmlLineNumber) {
                printStream.print("<line:" + ((XmlLineNumber) bookmark._value).getLine() + ">[" + bookmark._pos + "]");
            } else {
                printStream.print("<mark>[" + bookmark._pos + "]");
            }
        }
    }

    private static void dumpCharNodes(PrintStream printStream, CharNode charNode, Object obj) {
        while (charNode != null) {
            printStream.print(" ");
            if (charNode == obj) {
                printStream.print("*");
            }
            printStream.print((charNode instanceof TextNode ? "TEXT" : "CDATA") + "[" + charNode._cch + "]");
            charNode = charNode._next;
        }
    }

    private static void dumpChars(PrintStream printStream, Object obj, int i, int i2) {
        printStream.print("\"");
        String string = CharUtil.getString(obj, i, i2);
        int i3 = 0;
        while (true) {
            if (i3 >= string.length()) {
                break;
            } else if (i3 == 36) {
                printStream.print("...");
                break;
            } else {
                int codePointAt = string.codePointAt(i3);
                char[] chars = Character.toChars(codePointAt);
                if (chars.length == 1) {
                    char c = chars[0];
                    if (c >= ' ' && c < 127 && c != '\"') {
                        printStream.print(c);
                    } else if (c == 10) {
                        printStream.print("\\n");
                    } else if (c == 13) {
                        printStream.print("\\r");
                    } else if (c == 9) {
                        printStream.print("\\t");
                    } else if (c == '\"') {
                        printStream.print("\\\"");
                    } else {
                        printStream.print("<#" + c + ">");
                    }
                } else {
                    printStream.print("<#" + codePointAt + ">");
                }
                i3 += Character.charCount(codePointAt);
            }
        }
        printStream.print("\"");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ea, code lost:
        r0 = r0.substring(r1 + 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void dumpXobj(java.io.PrintStream r4, org.apache.xmlbeans.impl.store.Xobj r5, int r6, java.lang.Object r7) {
        /*
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = "  "
            if (r5 != r7) goto L_0x000d
            java.lang.String r1 = "* "
            r4.print(r1)
            goto L_0x0010
        L_0x000d:
            r4.print(r0)
        L_0x0010:
            r1 = 0
        L_0x0011:
            if (r1 >= r6) goto L_0x0019
            r4.print(r0)
            int r1 = r1 + 1
            goto L_0x0011
        L_0x0019:
            int r0 = r5.kind()
            java.lang.String r0 = kindName(r0)
            r4.print(r0)
            javax.xml.namespace.QName r0 = r5._name
            if (r0 == 0) goto L_0x0082
            java.lang.String r0 = " "
            r4.print(r0)
            javax.xml.namespace.QName r0 = r5._name
            java.lang.String r0 = r0.getPrefix()
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0055
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            javax.xml.namespace.QName r1 = r5._name
            java.lang.String r1 = r1.getPrefix()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ":"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.print(r0)
        L_0x0055:
            javax.xml.namespace.QName r0 = r5._name
            java.lang.String r0 = r0.getLocalPart()
            r4.print(r0)
            javax.xml.namespace.QName r0 = r5._name
            java.lang.String r0 = r0.getNamespaceURI()
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0082
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "@"
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = r5._name
            java.lang.String r1 = r1.getNamespaceURI()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.print(r0)
        L_0x0082:
            java.lang.Object r0 = r5._srcValue
            java.lang.String r1 = " )"
            if (r0 != 0) goto L_0x008c
            org.apache.xmlbeans.impl.store.CharNode r0 = r5._charNodesValue
            if (r0 == 0) goto L_0x00a2
        L_0x008c:
            java.lang.String r0 = " Value( "
            r4.print(r0)
            java.lang.Object r0 = r5._srcValue
            int r2 = r5._offValue
            int r3 = r5._cchValue
            dumpChars(r4, r0, r2, r3)
            org.apache.xmlbeans.impl.store.CharNode r0 = r5._charNodesValue
            dumpCharNodes(r4, r0, r7)
            r4.print(r1)
        L_0x00a2:
            org.apache.xmlbeans.impl.values.TypeStoreUser r0 = r5._user
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = " (USER)"
            r4.print(r0)
        L_0x00ab:
            boolean r0 = r5.isVacant()
            if (r0 == 0) goto L_0x00b6
            java.lang.String r0 = " (VACANT)"
            r4.print(r0)
        L_0x00b6:
            java.lang.Object r0 = r5._srcAfter
            if (r0 != 0) goto L_0x00be
            org.apache.xmlbeans.impl.store.CharNode r0 = r5._charNodesAfter
            if (r0 == 0) goto L_0x00d4
        L_0x00be:
            java.lang.String r0 = " After( "
            r4.print(r0)
            java.lang.Object r0 = r5._srcAfter
            int r2 = r5._offAfter
            int r3 = r5._cchAfter
            dumpChars(r4, r0, r2, r3)
            org.apache.xmlbeans.impl.store.CharNode r0 = r5._charNodesAfter
            dumpCharNodes(r4, r0, r7)
            r4.print(r1)
        L_0x00d4:
            dumpCurs(r4, r5, r7)
            dumpBookmarks(r4, r5, r7)
            java.lang.Class r0 = r5.getClass()
            java.lang.String r0 = r0.getName()
            r1 = 46
            int r1 = r0.lastIndexOf(r1)
            if (r1 <= 0) goto L_0x00fe
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
            r1 = 36
            int r1 = r0.lastIndexOf(r1)
            if (r1 <= 0) goto L_0x00fe
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
        L_0x00fe:
            java.lang.String r1 = " ("
            r4.print(r1)
            r4.print(r0)
            java.lang.String r0 = ")"
            r4.print(r0)
            r4.println()
            org.apache.xmlbeans.impl.store.Xobj r5 = r5._firstChild
        L_0x0110:
            if (r5 == 0) goto L_0x011a
            int r0 = r6 + 1
            dumpXobj(r4, r5, r0, r7)
            org.apache.xmlbeans.impl.store.Xobj r5 = r5._nextSibling
            goto L_0x0110
        L_0x011a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Cur.dumpXobj(java.io.PrintStream, org.apache.xmlbeans.impl.store.Xobj, int, java.lang.Object):void");
    }

    /* access modifiers changed from: package-private */
    public void setId(String str) {
        this._id = str;
    }

    public Locale getLocale() {
        return this._locale;
    }
}
