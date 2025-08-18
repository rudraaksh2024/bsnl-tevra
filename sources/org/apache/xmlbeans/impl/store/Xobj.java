package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreUserFactory;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.xpath.XPathFactory;

abstract class Xobj implements TypeStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int INHIBIT_DISCONNECT = 1024;
    static final int STABLE_USER = 512;
    static final int VACANT = 256;
    int _bits;
    Bookmark _bookmarks;
    int _cchAfter;
    int _cchValue;
    CharNode _charNodesAfter;
    CharNode _charNodesValue;
    Cur _embedded;
    Xobj _firstChild;
    Xobj _lastChild;
    Locale _locale;
    QName _name;
    Xobj _nextSibling;
    int _offAfter;
    int _offValue;
    Xobj _parent;
    Xobj _prevSibling;
    Object _srcAfter;
    Object _srcValue;
    TypeStoreUser _user;

    /* access modifiers changed from: package-private */
    public abstract DomImpl.Dom getDom();

    /* access modifiers changed from: package-private */
    public abstract Xobj newNode(Locale locale);

    Xobj(Locale locale, int i, int i2) {
        this._locale = locale;
        this._bits = (i2 << 4) + i;
    }

    /* access modifiers changed from: package-private */
    public final int kind() {
        return this._bits & 15;
    }

    /* access modifiers changed from: package-private */
    public final int domType() {
        return (this._bits & 240) >> 4;
    }

    /* access modifiers changed from: package-private */
    public final boolean isRoot() {
        return kind() == 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean isAttr() {
        return kind() == 3;
    }

    /* access modifiers changed from: package-private */
    public final boolean isElem() {
        return kind() == 2;
    }

    /* access modifiers changed from: package-private */
    public final boolean isProcinst() {
        return kind() == 5;
    }

    /* access modifiers changed from: package-private */
    public final boolean isComment() {
        return kind() == 4;
    }

    /* access modifiers changed from: package-private */
    public final boolean isContainer() {
        return Cur.kindIsContainer(kind());
    }

    /* access modifiers changed from: package-private */
    public final boolean isUserNode() {
        int kind = kind();
        if (kind == 2 || kind == 1) {
            return true;
        }
        if (kind != 3 || isXmlns()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean isNormalAttr() {
        return isAttr() && !Locale.isXmlns(this._name);
    }

    /* access modifiers changed from: package-private */
    public final boolean isXmlns() {
        return isAttr() && Locale.isXmlns(this._name);
    }

    /* access modifiers changed from: package-private */
    public final int cchAfter() {
        return this._cchAfter;
    }

    /* access modifiers changed from: package-private */
    public final int posAfter() {
        return this._cchValue + 2;
    }

    /* access modifiers changed from: package-private */
    public final int posMax() {
        return this._cchValue + 2 + this._cchAfter;
    }

    /* access modifiers changed from: package-private */
    public final String getXmlnsPrefix() {
        return Locale.xmlnsPrefix(this._name);
    }

    /* access modifiers changed from: package-private */
    public final String getXmlnsUri() {
        return getValueAsString();
    }

    /* access modifiers changed from: package-private */
    public final boolean hasTextEnsureOccupancy() {
        ensureOccupancy();
        return hasTextNoEnsureOccupancy();
    }

    /* access modifiers changed from: package-private */
    public final boolean hasTextNoEnsureOccupancy() {
        if (this._cchValue > 0) {
            return true;
        }
        Xobj lastAttr = lastAttr();
        if (lastAttr == null || lastAttr._cchAfter <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean hasAttrs() {
        Xobj xobj = this._firstChild;
        return xobj != null && xobj.isAttr();
    }

    /* access modifiers changed from: package-private */
    public final boolean hasChildren() {
        Xobj xobj = this._lastChild;
        return xobj != null && !xobj.isAttr();
    }

    /* access modifiers changed from: protected */
    public final int getDomZeroOneChildren() {
        Xobj xobj;
        CharNode charNode;
        if (this._firstChild == null && this._srcValue == null && this._charNodesValue == null) {
            return 0;
        }
        Xobj xobj2 = this._lastChild;
        if (xobj2 != null && xobj2.isAttr()) {
            Xobj xobj3 = this._lastChild;
            if (xobj3._charNodesAfter == null && xobj3._srcAfter == null && this._srcValue == null && this._charNodesValue == null) {
                return 0;
            }
        }
        Xobj xobj4 = this._firstChild;
        if (xobj4 == this._lastChild && xobj4 != null && !xobj4.isAttr() && this._srcValue == null && this._charNodesValue == null && this._firstChild._srcAfter == null) {
            return 1;
        }
        if (this._firstChild == null && this._srcValue != null && ((charNode = this._charNodesValue) == null || (charNode._next == null && this._charNodesValue._cch == this._cchValue))) {
            return 1;
        }
        Xobj lastAttr = lastAttr();
        if (lastAttr == null) {
            xobj = null;
        } else {
            xobj = lastAttr._nextSibling;
        }
        if (lastAttr != null && lastAttr._srcAfter == null && xobj != null && xobj._srcAfter == null && xobj._nextSibling == null) {
            return 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public final boolean isFirstChildPtrDomUsable() {
        Xobj xobj = this._firstChild;
        if (xobj == null && this._srcValue == null && this._charNodesValue == null) {
            return true;
        }
        if (xobj == null || xobj.isAttr() || this._srcValue != null || this._charNodesValue != null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean isNextSiblingPtrDomUsable() {
        return this._charNodesAfter == null && this._srcAfter == null;
    }

    /* access modifiers changed from: protected */
    public final boolean isExistingCharNodesValueUsable() {
        CharNode charNode;
        if (this._srcValue != null && (charNode = this._charNodesValue) != null && charNode._next == null && this._charNodesValue._cch == this._cchValue) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean isCharNodesValueUsable() {
        if (!isExistingCharNodesValueUsable()) {
            CharNode updateCharNodes = Cur.updateCharNodes(this._locale, this, this._charNodesValue, this._cchValue);
            this._charNodesValue = updateCharNodes;
            return updateCharNodes != null;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isCharNodesAfterUsable() {
        if (this._srcAfter == null) {
            return false;
        }
        CharNode charNode = this._charNodesAfter;
        if (charNode != null && charNode._next == null && this._charNodesAfter._cch == this._cchAfter) {
            return true;
        }
        CharNode updateCharNodes = Cur.updateCharNodes(this._locale, this, this._charNodesAfter, this._cchAfter);
        this._charNodesAfter = updateCharNodes;
        if (updateCharNodes != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Xobj lastAttr() {
        Xobj xobj = this._firstChild;
        if (xobj == null || !xobj.isAttr()) {
            return null;
        }
        Xobj xobj2 = this._firstChild;
        while (true) {
            Xobj xobj3 = xobj2._nextSibling;
            if (xobj3 == null || !xobj3.isAttr()) {
                return xobj2;
            }
            xobj2 = xobj2._nextSibling;
        }
        return xobj2;
    }

    /* access modifiers changed from: package-private */
    public final int cchLeft(int i) {
        if (isRoot() && i == 0) {
            return 0;
        }
        Xobj denormal = getDenormal(i);
        int posTemp = posTemp();
        int posAfter = denormal.posAfter();
        if (posTemp < posAfter) {
            posAfter = 1;
        }
        return posTemp - posAfter;
    }

    /* access modifiers changed from: package-private */
    public final int cchRight(int i) {
        if (i <= 0) {
            return 0;
        }
        int posAfter = posAfter();
        if (i < posAfter) {
            return (posAfter - i) - 1;
        }
        return posMax() - i;
    }

    public final Locale locale() {
        return this._locale;
    }

    public final int nodeType() {
        return domType();
    }

    public final QName getQName() {
        return this._name;
    }

    public final Cur tempCur() {
        Cur tempCur = this._locale.tempCur();
        tempCur.moveTo(this);
        return tempCur;
    }

    public void dump(PrintStream printStream, Object obj) {
        Cur.dump(printStream, this, obj);
    }

    public void dump(PrintStream printStream) {
        Cur.dump(printStream, this, this);
    }

    public void dump() {
        dump(System.out);
    }

    /* access modifiers changed from: package-private */
    public final Cur getEmbedded() {
        this._locale.embedCurs();
        return this._embedded;
    }

    /* access modifiers changed from: package-private */
    public final boolean inChars(int i, Xobj xobj, int i2, int i3, boolean z) {
        int i4;
        if (!z) {
            i4 = 0;
        } else if (xobj.isRoot() && i2 == 0) {
            return false;
        } else {
            xobj = xobj.getDenormal(i2);
            i2 = xobj.posTemp();
            i4 = 1;
        }
        if (xobj != this || i2 < i || i2 >= i + i3 + i4) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean isJustAfterEnd(Xobj xobj, int i) {
        if (xobj.isRoot() && i == 0) {
            return false;
        }
        if (xobj == this) {
            if (i != posAfter()) {
                return false;
            }
        } else if (!(xobj.getDenormal(i) == this && xobj.posTemp() == posAfter())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean isInSameTree(Xobj xobj) {
        if (this._locale != xobj._locale) {
            return false;
        }
        Xobj xobj2 = this;
        while (xobj2 != xobj) {
            Xobj xobj3 = xobj2._parent;
            if (xobj3 == null) {
                while (xobj != this) {
                    Xobj xobj4 = xobj._parent;
                    if (xobj4 != null) {
                        xobj = xobj4;
                    } else if (xobj == xobj2) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
            xobj2 = xobj3;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean contains(Cur cur) {
        return contains(cur._xobj, cur._pos);
    }

    /* access modifiers changed from: package-private */
    public final boolean contains(Xobj xobj, int i) {
        if (this == xobj) {
            if (i != -1) {
                return i > 0 && i < posAfter();
            }
            return true;
        } else if (this._firstChild == null) {
            return false;
        } else {
            while (xobj != null) {
                if (xobj == this) {
                    return true;
                }
                xobj = xobj._parent;
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final Bookmark setBookmark(int i, Object obj, Object obj2) {
        Bookmark bookmark = this._bookmarks;
        while (bookmark != null) {
            if (i != bookmark._pos || obj != bookmark._key) {
                bookmark = bookmark._next;
            } else if (obj2 == null) {
                this._bookmarks = bookmark.listRemove(this._bookmarks);
                return null;
            } else {
                bookmark._value = obj2;
                return bookmark;
            }
        }
        if (obj2 == null) {
            return null;
        }
        Bookmark bookmark2 = new Bookmark();
        bookmark2._xobj = this;
        bookmark2._pos = i;
        bookmark2._key = obj;
        bookmark2._value = obj2;
        this._bookmarks = bookmark2.listInsert(this._bookmarks);
        return bookmark2;
    }

    /* access modifiers changed from: package-private */
    public final boolean hasBookmark(Object obj, int i) {
        for (Bookmark bookmark = this._bookmarks; bookmark != null; bookmark = bookmark._next) {
            if (bookmark._pos == i && obj == bookmark._key) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Xobj findXmlnsForPrefix(String str) {
        while (this != null) {
            for (Xobj firstAttr = this.firstAttr(); firstAttr != null; firstAttr = firstAttr.nextAttr()) {
                if (firstAttr.isXmlns() && firstAttr.getXmlnsPrefix().equals(str)) {
                    return firstAttr;
                }
            }
            this = this._parent;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final boolean removeAttr(QName qName) {
        Xobj attr = getAttr(qName);
        if (attr == null) {
            return false;
        }
        Cur tempCur = attr.tempCur();
        while (true) {
            tempCur.moveNode((Cur) null);
            Xobj attr2 = getAttr(qName);
            if (attr2 == null) {
                tempCur.release();
                return true;
            }
            tempCur.moveTo(attr2);
        }
    }

    /* access modifiers changed from: package-private */
    public final Xobj setAttr(QName qName, String str) {
        Cur tempCur = tempCur();
        if (tempCur.toAttr(qName)) {
            tempCur.removeFollowingAttrs();
        } else {
            tempCur.next();
            tempCur.createAttr(qName);
        }
        tempCur.setValue(str);
        Xobj xobj = tempCur._xobj;
        tempCur.release();
        return xobj;
    }

    /* access modifiers changed from: package-private */
    public final void setName(QName qName) {
        Xobj xobj;
        if (!this._name.equals(qName) || !this._name.getPrefix().equals(qName.getPrefix())) {
            this._locale.notifyChange();
            QName qName2 = this._name;
            this._name = qName;
            if (this instanceof NamedNodeXobj) {
                ((NamedNodeXobj) this)._canHavePrefixUri = true;
            }
            if (!isProcinst()) {
                if (!isAttr() || this._parent == null) {
                    xobj = this;
                } else {
                    if (qName2.equals(Locale._xsiType) || qName.equals(Locale._xsiType)) {
                        xobj = this._parent;
                    } else {
                        xobj = this;
                    }
                    if (qName2.equals(Locale._xsiNil) || qName.equals(Locale._xsiNil)) {
                        this._parent.invalidateNil();
                    }
                }
                xobj.disconnectNonRootUsers();
            }
            this._locale._versionAll++;
            this._locale._versionSansText++;
        }
    }

    /* access modifiers changed from: package-private */
    public final Xobj ensureParent() {
        Xobj xobj = this._parent;
        return xobj == null ? new DocumentFragXobj(this._locale).appendXobj(this) : xobj;
    }

    /* access modifiers changed from: package-private */
    public final Xobj firstAttr() {
        Xobj xobj = this._firstChild;
        if (xobj == null || !xobj.isAttr()) {
            return null;
        }
        return this._firstChild;
    }

    /* access modifiers changed from: package-private */
    public final Xobj nextAttr() {
        Xobj xobj = this._firstChild;
        if (xobj != null && xobj.isAttr()) {
            return this._firstChild;
        }
        Xobj xobj2 = this._nextSibling;
        if (xobj2 == null || !xobj2.isAttr()) {
            return null;
        }
        return this._nextSibling;
    }

    /* access modifiers changed from: package-private */
    public final boolean isValid() {
        return !isVacant() || (this._cchValue == 0 && this._user != null);
    }

    /* access modifiers changed from: package-private */
    public final int posTemp() {
        return this._locale._posTemp;
    }

    /* access modifiers changed from: package-private */
    public final Xobj getNormal(int i) {
        Xobj xobj;
        if (i == posMax()) {
            Xobj xobj2 = this._nextSibling;
            if (xobj2 != null) {
                xobj = xobj2;
                i = 0;
                this._locale._posTemp = i;
                return xobj;
            }
            xobj = ensureParent();
        } else if (i == posAfter() - 1) {
            xobj = this;
        } else {
            xobj = this;
            this._locale._posTemp = i;
            return xobj;
        }
        i = -1;
        this._locale._posTemp = i;
        return xobj;
    }

    /* access modifiers changed from: package-private */
    public final Xobj getDenormal(int i) {
        Xobj xobj;
        Xobj xobj2;
        int posMax;
        if (i == 0) {
            xobj2 = this._prevSibling;
            if (xobj2 == null) {
                xobj2 = ensureParent();
                posMax = xobj2.posAfter() - 1;
            } else {
                posMax = xobj2.posMax();
            }
        } else {
            if (i == -1) {
                xobj2 = this._lastChild;
                if (xobj2 == null) {
                    i = posAfter() - 1;
                } else {
                    posMax = xobj2.posMax();
                }
            }
            xobj = this;
            this._locale._posTemp = i;
            return xobj;
        }
        int i2 = posMax;
        xobj = xobj2;
        i = i2;
        this._locale._posTemp = i;
        return xobj;
    }

    /* access modifiers changed from: package-private */
    public final boolean isNormal(int i) {
        Xobj xobj;
        if (!isValid()) {
            return false;
        }
        if (i == -1 || i == 0) {
            return true;
        }
        if (i < 0 || i >= posMax()) {
            return false;
        }
        if (i >= posAfter()) {
            if (isRoot()) {
                return false;
            }
            Xobj xobj2 = this._nextSibling;
            if ((xobj2 != null && xobj2.isAttr()) || (xobj = this._parent) == null || !xobj.isContainer()) {
                return false;
            }
        }
        if (i != posAfter() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Xobj walk(Xobj xobj, boolean z) {
        Xobj xobj2 = this._firstChild;
        if (xobj2 != null && z) {
            return xobj2;
        }
        while (this != xobj) {
            Xobj xobj3 = this._nextSibling;
            if (xobj3 != null) {
                return xobj3;
            }
            this = this._parent;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void removeXobj() {
        Xobj xobj = this._parent;
        if (xobj != null) {
            if (xobj._firstChild == this) {
                xobj._firstChild = this._nextSibling;
            }
            if (xobj._lastChild == this) {
                xobj._lastChild = this._prevSibling;
            }
            Xobj xobj2 = this._prevSibling;
            if (xobj2 != null) {
                xobj2._nextSibling = this._nextSibling;
            }
            Xobj xobj3 = this._nextSibling;
            if (xobj3 != null) {
                xobj3._prevSibling = xobj2;
            }
            this._parent = null;
            this._prevSibling = null;
            this._nextSibling = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void insertXobj(Xobj xobj) {
        ensureParent();
        xobj._parent = this._parent;
        xobj._prevSibling = this._prevSibling;
        xobj._nextSibling = this;
        Xobj xobj2 = this._prevSibling;
        if (xobj2 != null) {
            xobj2._nextSibling = xobj;
        } else {
            this._parent._firstChild = xobj;
        }
        this._prevSibling = xobj;
    }

    /* access modifiers changed from: package-private */
    public final Xobj appendXobj(Xobj xobj) {
        xobj._parent = this;
        Xobj xobj2 = this._lastChild;
        xobj._prevSibling = xobj2;
        if (xobj2 == null) {
            this._firstChild = xobj;
        } else {
            xobj2._nextSibling = xobj;
        }
        this._lastChild = xobj;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void removeXobjs(Xobj xobj, Xobj xobj2) {
        if (this._firstChild == xobj) {
            this._firstChild = xobj2._nextSibling;
        }
        if (this._lastChild == xobj2) {
            this._lastChild = xobj._prevSibling;
        }
        Xobj xobj3 = xobj._prevSibling;
        if (xobj3 != null) {
            xobj3._nextSibling = xobj2._nextSibling;
        }
        Xobj xobj4 = xobj2._nextSibling;
        if (xobj4 != null) {
            xobj4._prevSibling = xobj3;
        }
        xobj._prevSibling = null;
        xobj2._nextSibling = null;
        while (xobj != null) {
            xobj._parent = null;
            xobj = xobj._nextSibling;
        }
    }

    /* access modifiers changed from: package-private */
    public final void insertXobjs(Xobj xobj, Xobj xobj2) {
        xobj._prevSibling = this._prevSibling;
        xobj2._nextSibling = this;
        Xobj xobj3 = this._prevSibling;
        if (xobj3 != null) {
            xobj3._nextSibling = xobj;
        } else {
            this._parent._firstChild = xobj;
        }
        this._prevSibling = xobj2;
        while (xobj != this) {
            xobj._parent = this._parent;
            xobj = xobj._nextSibling;
        }
    }

    /* access modifiers changed from: package-private */
    public final void appendXobjs(Xobj xobj, Xobj xobj2) {
        Xobj xobj3 = this._lastChild;
        xobj._prevSibling = xobj3;
        if (xobj3 == null) {
            this._firstChild = xobj;
        } else {
            xobj3._nextSibling = xobj;
        }
        this._lastChild = xobj2;
        while (xobj != null) {
            xobj._parent = this;
            xobj = xobj._nextSibling;
        }
    }

    /* access modifiers changed from: package-private */
    public final void invalidateSpecialAttr(Xobj xobj) {
        if (isAttr()) {
            if (this._name.equals(Locale._xsiType)) {
                Xobj xobj2 = this._parent;
                if (xobj2 != null) {
                    xobj2.disconnectNonRootUsers();
                }
                if (xobj != null) {
                    xobj.disconnectNonRootUsers();
                }
            }
            if (this._name.equals(Locale._xsiNil)) {
                Xobj xobj3 = this._parent;
                if (xobj3 != null) {
                    xobj3.invalidateNil();
                }
                if (xobj != null) {
                    xobj.invalidateNil();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void removeCharsHelper(int i, int i2, Xobj xobj, int i3, boolean z, boolean z2) {
        Xobj xobj2;
        Cur embedded = getEmbedded();
        while (embedded != null) {
            Cur cur = embedded._next;
            if (embedded._pos >= i && embedded._pos < i + i2) {
                if (z) {
                    embedded.moveToNoCheck(xobj, (embedded._pos + i3) - i);
                } else {
                    embedded.nextChars((i2 - embedded._pos) + i);
                }
            }
            if (embedded._xobj == this && embedded._pos >= i + i2) {
                embedded._pos -= i2;
            }
            embedded = cur;
        }
        for (Bookmark bookmark = this._bookmarks; bookmark != null; bookmark = bookmark._next) {
            if (bookmark._pos >= i && bookmark._pos < i + i2) {
                bookmark.moveTo(xobj, (bookmark._pos + i3) - i);
            }
            if (bookmark._xobj == this && bookmark._pos >= i + i2) {
                bookmark._pos -= i2;
            }
        }
        int posAfter = posAfter();
        CharUtil charUtil = this._locale.getCharUtil();
        if (i < posAfter) {
            this._srcValue = charUtil.removeChars(i - 1, i2, this._srcValue, this._offValue, this._cchValue);
            this._offValue = charUtil._offSrc;
            this._cchValue = charUtil._cchSrc;
            if (z2) {
                invalidateUser();
                invalidateSpecialAttr((Xobj) null);
                return;
            }
            return;
        }
        this._srcAfter = charUtil.removeChars(i - posAfter, i2, this._srcAfter, this._offAfter, this._cchAfter);
        this._offAfter = charUtil._offSrc;
        this._cchAfter = charUtil._cchSrc;
        if (z2 && (xobj2 = this._parent) != null) {
            xobj2.invalidateUser();
        }
    }

    /* access modifiers changed from: package-private */
    public final void insertCharsHelper(int i, Object obj, int i2, int i3, boolean z) {
        Xobj xobj;
        int posAfter = posAfter();
        if (i - (i < posAfter ? 1 : 2) < this._cchValue + this._cchAfter) {
            for (Cur embedded = getEmbedded(); embedded != null; embedded = embedded._next) {
                if (embedded._pos >= i) {
                    embedded._pos += i3;
                }
            }
            for (Bookmark bookmark = this._bookmarks; bookmark != null; bookmark = bookmark._next) {
                if (bookmark._pos >= i) {
                    bookmark._pos += i3;
                }
            }
        }
        CharUtil charUtil = this._locale.getCharUtil();
        if (i < posAfter) {
            this._srcValue = charUtil.insertChars(i - 1, this._srcValue, this._offValue, this._cchValue, obj, i2, i3);
            this._offValue = charUtil._offSrc;
            this._cchValue = charUtil._cchSrc;
            if (z) {
                invalidateUser();
                invalidateSpecialAttr((Xobj) null);
                return;
            }
            return;
        }
        this._srcAfter = charUtil.insertChars(i - posAfter, this._srcAfter, this._offAfter, this._cchAfter, obj, i2, i3);
        this._offAfter = charUtil._offSrc;
        this._cchAfter = charUtil._cchSrc;
        if (z && (xobj = this._parent) != null) {
            xobj.invalidateUser();
        }
    }

    /* access modifiers changed from: package-private */
    public Xobj copyNode(Locale locale) {
        Xobj xobj = this;
        Xobj xobj2 = null;
        Xobj xobj3 = null;
        while (true) {
            xobj.ensureOccupancy();
            Xobj newNode = xobj.newNode(locale);
            newNode._srcValue = xobj._srcValue;
            newNode._offValue = xobj._offValue;
            newNode._cchValue = xobj._cchValue;
            newNode._srcAfter = xobj._srcAfter;
            newNode._offAfter = xobj._offAfter;
            newNode._cchAfter = xobj._cchAfter;
            for (Bookmark bookmark = xobj._bookmarks; bookmark != null; bookmark = bookmark._next) {
                if (xobj.hasBookmark(CDataBookmark.CDATA_BOOKMARK.getKey(), bookmark._pos)) {
                    newNode.setBookmark(bookmark._pos, CDataBookmark.CDATA_BOOKMARK.getKey(), CDataBookmark.CDATA_BOOKMARK);
                }
            }
            if (xobj2 == null) {
                xobj3 = newNode;
            } else {
                xobj2.appendXobj(newNode);
            }
            Xobj walk = xobj.walk(this, true);
            if (walk == null) {
                xobj3._srcAfter = null;
                xobj3._offAfter = 0;
                xobj3._cchAfter = 0;
                return xobj3;
            }
            if (xobj != walk._parent) {
                while (true) {
                    xobj = xobj._parent;
                    if (xobj == walk._parent) {
                        break;
                    }
                    xobj2 = xobj2._parent;
                }
            } else {
                xobj2 = newNode;
            }
            xobj = walk;
        }
    }

    /* access modifiers changed from: package-private */
    public String getCharsAsString(int i, int i2, int i3) {
        if (cchRight(i) == 0) {
            return "";
        }
        Object chars = getChars(i, i2);
        if (i3 == 1) {
            return CharUtil.getString(chars, this._locale._offSrc, this._locale._cchSrc);
        }
        Locale.ScrubBuffer scrubBuffer = Locale.getScrubBuffer(i3);
        scrubBuffer.scrub(chars, this._locale._offSrc, this._locale._cchSrc);
        return scrubBuffer.getResultAsString();
    }

    /* access modifiers changed from: package-private */
    public String getCharsAfterAsString(int i, int i2) {
        int i3 = i + this._cchValue + 2;
        if (i3 == posMax()) {
            i3 = -1;
        }
        return getCharsAsString(i3, i2, 1);
    }

    /* access modifiers changed from: package-private */
    public String getCharsValueAsString(int i, int i2) {
        return getCharsAsString(i + 1, i2, 1);
    }

    /* access modifiers changed from: package-private */
    public String getValueAsString(int i) {
        if (!hasChildren()) {
            Object firstChars = getFirstChars();
            if (i == 1) {
                String string = CharUtil.getString(firstChars, this._locale._offSrc, this._locale._cchSrc);
                if (string.length() > 0) {
                    Xobj lastAttr = lastAttr();
                    if (lastAttr != null) {
                        lastAttr._srcAfter = string;
                        lastAttr._offAfter = 0;
                    } else {
                        this._srcValue = string;
                        this._offValue = 0;
                    }
                }
                return string;
            }
            Locale.ScrubBuffer scrubBuffer = Locale.getScrubBuffer(i);
            scrubBuffer.scrub(firstChars, this._locale._offSrc, this._locale._cchSrc);
            return scrubBuffer.getResultAsString();
        }
        Locale.ScrubBuffer scrubBuffer2 = Locale.getScrubBuffer(i);
        Cur tempCur = tempCur();
        tempCur.push();
        tempCur.next();
        while (!tempCur.isAtEndOfLastPush()) {
            if (tempCur.isText()) {
                scrubBuffer2.scrub(tempCur.getChars(-1), tempCur._offSrc, tempCur._cchSrc);
            }
            if (tempCur.isComment() || tempCur.isProcinst()) {
                tempCur.skip();
            } else {
                tempCur.next();
            }
        }
        String resultAsString = scrubBuffer2.getResultAsString();
        tempCur.release();
        return resultAsString;
    }

    /* access modifiers changed from: package-private */
    public String getValueAsString() {
        return getValueAsString(1);
    }

    /* access modifiers changed from: package-private */
    public Object getFirstChars() {
        ensureOccupancy();
        if (this._cchValue > 0) {
            return getChars(1, -1);
        }
        Xobj lastAttr = lastAttr();
        if (lastAttr != null && lastAttr._cchAfter > 0) {
            return lastAttr.getChars(lastAttr.posAfter(), -1);
        }
        this._locale._offSrc = 0;
        this._locale._cchSrc = 0;
        return null;
    }

    /* access modifiers changed from: package-private */
    public Object getChars(int i, int i2, Cur cur) {
        Object chars = getChars(i, i2);
        cur._offSrc = this._locale._offSrc;
        cur._cchSrc = this._locale._cchSrc;
        return chars;
    }

    /* access modifiers changed from: package-private */
    public Object getChars(int i, int i2) {
        int cchRight = cchRight(i);
        if (i2 < 0 || i2 > cchRight) {
            i2 = cchRight;
        }
        if (i2 != 0) {
            return getCharsHelper(i, i2);
        }
        this._locale._offSrc = 0;
        this._locale._cchSrc = 0;
        return null;
    }

    /* access modifiers changed from: package-private */
    public Object getCharsHelper(int i, int i2) {
        Object obj;
        int posAfter = posAfter();
        if (i >= posAfter) {
            obj = this._srcAfter;
            this._locale._offSrc = (this._offAfter + i) - posAfter;
        } else {
            obj = this._srcValue;
            this._locale._offSrc = (this._offValue + i) - 1;
        }
        this._locale._cchSrc = i2;
        return obj;
    }

    /* access modifiers changed from: package-private */
    public final void setBit(int i) {
        this._bits = i | this._bits;
    }

    /* access modifiers changed from: package-private */
    public final void clearBit(int i) {
        this._bits = (~i) & this._bits;
    }

    /* access modifiers changed from: package-private */
    public final boolean bitIsSet(int i) {
        return (this._bits & i) != 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean bitIsClear(int i) {
        return (this._bits & i) == 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean isVacant() {
        return bitIsSet(256);
    }

    /* access modifiers changed from: package-private */
    public final boolean isOccupied() {
        return bitIsClear(256);
    }

    /* access modifiers changed from: package-private */
    public final boolean inhibitDisconnect() {
        return bitIsSet(1024);
    }

    /* access modifiers changed from: package-private */
    public final boolean isStableUser() {
        return bitIsSet(512);
    }

    /* access modifiers changed from: package-private */
    public void invalidateNil() {
        TypeStoreUser typeStoreUser = this._user;
        if (typeStoreUser != null) {
            typeStoreUser.invalidate_nilvalue();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStableType(SchemaType schemaType) {
        setStableUser(((TypeStoreUserFactory) schemaType).createTypeStoreUser());
    }

    /* access modifiers changed from: package-private */
    public void setStableUser(TypeStoreUser typeStoreUser) {
        disconnectNonRootUsers();
        disconnectUser();
        this._user = typeStoreUser;
        typeStoreUser.attach_store(this);
        setBit(512);
    }

    /* access modifiers changed from: package-private */
    public void disconnectUser() {
        if (this._user != null && !inhibitDisconnect()) {
            ensureOccupancy();
            this._user.disconnect_store();
            this._user = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void disconnectNonRootUsers() {
        Xobj xobj = this;
        while (xobj != null) {
            Xobj walk = xobj.walk(this, xobj._user != null);
            if (!xobj.isRoot()) {
                xobj.disconnectUser();
            }
            xobj = walk;
        }
    }

    /* access modifiers changed from: package-private */
    public void disconnectChildrenUsers() {
        Xobj walk = walk(this, this._user == null);
        while (walk != null) {
            Xobj walk2 = walk.walk(this, walk._user != null);
            walk.disconnectUser();
            walk = walk2;
        }
    }

    /* access modifiers changed from: package-private */
    public final String namespaceForPrefix(String str, boolean z) {
        if (str == null) {
            str = "";
        }
        if (str.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (str.equals(Sax2Dom.XMLNS_PREFIX)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        while (this != null) {
            Xobj xobj = this._firstChild;
            while (xobj != null && xobj.isAttr()) {
                if (xobj.isXmlns() && xobj.getXmlnsPrefix().equals(str)) {
                    return xobj.getXmlnsUri();
                }
                xobj = xobj._nextSibling;
            }
            this = this._parent;
        }
        if (!z || str.length() != 0) {
            return null;
        }
        return "";
    }

    /* access modifiers changed from: package-private */
    public final String prefixForNamespace(String str, String str2, boolean z) {
        if (str == null) {
            str = "";
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return "xml";
        }
        if (str.equals("http://www.w3.org/2000/xmlns/")) {
            return Sax2Dom.XMLNS_PREFIX;
        }
        Xobj xobj = this;
        while (!xobj.isContainer()) {
            xobj = xobj.ensureParent();
        }
        if (str.length() == 0) {
            Xobj findXmlnsForPrefix = xobj.findXmlnsForPrefix("");
            if (!(findXmlnsForPrefix == null || findXmlnsForPrefix.getXmlnsUri().length() == 0)) {
                if (!z) {
                    return null;
                }
                xobj.setAttr(this._locale.createXmlns((String) null), "");
            }
            return "";
        }
        for (Xobj xobj2 = xobj; xobj2 != null; xobj2 = xobj2._parent) {
            for (Xobj firstAttr = xobj2.firstAttr(); firstAttr != null; firstAttr = firstAttr.nextAttr()) {
                if (firstAttr.isXmlns() && firstAttr.getXmlnsUri().equals(str) && xobj.findXmlnsForPrefix(firstAttr.getXmlnsPrefix()) == firstAttr) {
                    return firstAttr.getXmlnsPrefix();
                }
            }
        }
        if (!z) {
            return null;
        }
        if (str2 != null && (str2.length() == 0 || str2.toLowerCase(java.util.Locale.ROOT).startsWith("xml") || xobj.findXmlnsForPrefix(str2) != null)) {
            str2 = null;
        }
        if (r8 == null) {
            String suggestPrefix = QNameHelper.suggestPrefix(str);
            int i = 1;
            r8 = suggestPrefix;
            while (xobj.findXmlnsForPrefix(r8) != null) {
                r8 = suggestPrefix + i;
                i++;
            }
        }
        Xobj xobj3 = xobj;
        while (!xobj3.isRoot() && !xobj3.ensureParent().isRoot()) {
            xobj3 = xobj3._parent;
        }
        xobj.setAttr(this._locale.createXmlns(r8), str);
        return r8;
    }

    /* access modifiers changed from: package-private */
    public final QName getValueAsQName() {
        String str;
        String valueAsString = getValueAsString(3);
        int indexOf = valueAsString.indexOf(58);
        if (indexOf >= 0) {
            str = valueAsString.substring(0, indexOf);
            valueAsString = valueAsString.substring(indexOf + 1);
        } else {
            str = "";
        }
        String namespaceForPrefix = namespaceForPrefix(str, true);
        if (namespaceForPrefix == null) {
            return null;
        }
        return new QName(namespaceForPrefix, valueAsString);
    }

    /* access modifiers changed from: package-private */
    public final Xobj getAttr(QName qName) {
        Xobj xobj = this._firstChild;
        while (xobj != null && xobj.isAttr()) {
            if (xobj._name.equals(qName)) {
                return xobj;
            }
            xobj = xobj._nextSibling;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final QName getXsiTypeName() {
        Xobj attr = getAttr(Locale._xsiType);
        if (attr == null) {
            return null;
        }
        return attr.getValueAsQName();
    }

    /* access modifiers changed from: package-private */
    public final XmlObject getObject() {
        if (isUserNode()) {
            return (XmlObject) getUser();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final TypeStoreUser getUser() {
        TypeStoreUser typeStoreUser;
        TypeStoreUser typeStoreUser2;
        if (this._user == null) {
            Xobj xobj = this._parent;
            if (xobj == null) {
                typeStoreUser = ((TypeStoreUserFactory) XmlBeans.NO_TYPE).createTypeStoreUser();
            } else {
                typeStoreUser = xobj.getUser();
            }
            if (isElem()) {
                typeStoreUser2 = typeStoreUser.create_element_user(this._name, getXsiTypeName());
            } else {
                typeStoreUser2 = typeStoreUser.create_attribute_user(this._name);
            }
            this._user = typeStoreUser2;
            typeStoreUser2.attach_store(this);
        }
        return this._user;
    }

    /* access modifiers changed from: package-private */
    public final void invalidateUser() {
        TypeStoreUser typeStoreUser = this._user;
        if (typeStoreUser != null) {
            typeStoreUser.invalidate_value();
        }
    }

    /* access modifiers changed from: package-private */
    public final void ensureOccupancy() {
        if (isVacant()) {
            clearBit(256);
            TypeStoreUser typeStoreUser = this._user;
            this._user = null;
            String build_text = typeStoreUser.build_text(this);
            long j = this._locale._versionAll;
            long j2 = this._locale._versionSansText;
            setValue(build_text);
            this._locale._versionAll = j;
            this._user = typeStoreUser;
        }
    }

    private void setValue(String str) {
        int i;
        Xobj xobj;
        if (str.length() > 0) {
            this._locale.notifyChange();
            Xobj lastAttr = lastAttr();
            if (lastAttr != null) {
                i = lastAttr.posAfter();
                xobj = lastAttr;
            } else {
                xobj = this;
                i = 1;
            }
            xobj.insertCharsHelper(i, str, 0, str.length(), true);
        }
    }

    public SchemaTypeLoader get_schematypeloader() {
        return this._locale._schemaTypeLoader;
    }

    public XmlLocale get_locale() {
        return this._locale;
    }

    public Object get_root_object() {
        return this._locale;
    }

    public boolean is_attribute() {
        return isAttr();
    }

    public boolean validate_on_set() {
        return this._locale._validateOnSet;
    }

    public void invalidate_text() {
        this._locale.enter();
        try {
            if (isOccupied()) {
                if (hasTextNoEnsureOccupancy() || hasChildren()) {
                    TypeStoreUser typeStoreUser = this._user;
                    this._user = null;
                    Cur tempCur = tempCur();
                    tempCur.moveNodeContents((Cur) null, false);
                    tempCur.release();
                    this._user = typeStoreUser;
                }
                setBit(256);
            }
        } finally {
            this._locale.exit();
        }
    }

    public String fetch_text(int i) {
        this._locale.enter();
        try {
            return getValueAsString(i);
        } finally {
            this._locale.exit();
        }
    }

    public XmlCursor new_cursor() {
        this._locale.enter();
        try {
            Cur tempCur = tempCur();
            Cursor cursor = new Cursor(tempCur);
            tempCur.release();
            return cursor;
        } finally {
            this._locale.exit();
        }
    }

    public SchemaField get_schema_field() {
        if (isRoot()) {
            return null;
        }
        TypeStoreUser user = ensureParent().getUser();
        if (isAttr()) {
            return user.get_attribute_field(this._name);
        }
        TypeStoreVisitor new_visitor = user.new_visitor();
        if (new_visitor == null) {
            return null;
        }
        Xobj xobj = this._parent._firstChild;
        while (true) {
            if (xobj.isElem()) {
                new_visitor.visit(xobj._name);
                if (xobj == this) {
                    return new_visitor.get_schema_field();
                }
            }
            xobj = xobj._nextSibling;
        }
    }

    public void validate(ValidatorListener validatorListener) {
        this._locale.enter();
        try {
            Cur tempCur = tempCur();
            new Validate(tempCur, validatorListener);
            tempCur.release();
        } finally {
            this._locale.exit();
        }
    }

    /* JADX INFO: finally extract failed */
    public TypeStoreUser change_type(SchemaType schemaType) {
        this._locale.enter();
        try {
            Cur tempCur = tempCur();
            tempCur.setType(schemaType, false);
            tempCur.release();
            this._locale.exit();
            return getUser();
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public TypeStoreUser substitute(QName qName, SchemaType schemaType) {
        this._locale.enter();
        try {
            Cur tempCur = tempCur();
            tempCur.setSubstitution(qName, schemaType);
            tempCur.release();
            this._locale.exit();
            return getUser();
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    public QName get_xsi_type() {
        return getXsiTypeName();
    }

    public void store_text(String str) {
        this._locale.enter();
        TypeStoreUser typeStoreUser = this._user;
        this._user = null;
        try {
            Cur tempCur = tempCur();
            tempCur.moveNodeContents((Cur) null, false);
            if (str != null && str.length() > 0) {
                tempCur.next();
                tempCur.insertString(str);
            }
            tempCur.release();
        } finally {
            this._user = typeStoreUser;
            this._locale.exit();
        }
    }

    public int compute_flags() {
        if (isRoot()) {
            return 0;
        }
        TypeStoreUser user = ensureParent().getUser();
        if (isAttr()) {
            return user.get_attributeflags(this._name);
        }
        int i = user.get_elementflags(this._name);
        if (i != -1) {
            return i;
        }
        TypeStoreVisitor new_visitor = user.new_visitor();
        if (new_visitor == null) {
            return 0;
        }
        Xobj xobj = this._parent._firstChild;
        while (true) {
            if (xobj.isElem()) {
                new_visitor.visit(xobj._name);
                if (xobj == this) {
                    return new_visitor.get_elementflags();
                }
            }
            xobj = xobj._nextSibling;
        }
    }

    public String compute_default_text() {
        if (isRoot()) {
            return null;
        }
        TypeStoreUser user = ensureParent().getUser();
        if (isAttr()) {
            return user.get_default_attribute_text(this._name);
        }
        String str = user.get_default_element_text(this._name);
        if (str != null) {
            return str;
        }
        TypeStoreVisitor new_visitor = user.new_visitor();
        if (new_visitor == null) {
            return null;
        }
        Xobj xobj = this._parent._firstChild;
        while (true) {
            if (xobj.isElem()) {
                new_visitor.visit(xobj._name);
                if (xobj == this) {
                    return new_visitor.get_default_text();
                }
            }
            xobj = xobj._nextSibling;
        }
    }

    public boolean find_nil() {
        boolean z = false;
        if (isAttr()) {
            return false;
        }
        this._locale.enter();
        try {
            Xobj attr = getAttr(Locale._xsiNil);
            if (attr != null) {
                String valueAsString = attr.getValueAsString(3);
                if (valueAsString.equals("true") || valueAsString.equals("1")) {
                    z = true;
                }
            }
            return z;
        } finally {
            this._locale.exit();
        }
    }

    public void invalidate_nil() {
        if (!isAttr()) {
            this._locale.enter();
            try {
                if (!this._user.build_nil()) {
                    removeAttr(Locale._xsiNil);
                } else {
                    setAttr(Locale._xsiNil, "true");
                }
            } finally {
                this._locale.exit();
            }
        }
    }

    public int count_elements(QName qName) {
        return this._locale.count(this, qName, (QNameSet) null);
    }

    public int count_elements(QNameSet qNameSet) {
        return this._locale.count(this, (QName) null, qNameSet);
    }

    public TypeStoreUser find_element_user(QName qName, int i) {
        for (Xobj xobj = this._firstChild; xobj != null; xobj = xobj._nextSibling) {
            if (xobj.isElem() && xobj._name.equals(qName) && i - 1 < 0) {
                return xobj.getUser();
            }
        }
        return null;
    }

    public TypeStoreUser find_element_user(QNameSet qNameSet, int i) {
        for (Xobj xobj = this._firstChild; xobj != null; xobj = xobj._nextSibling) {
            if (xobj.isElem() && qNameSet.contains(xobj._name) && i - 1 < 0) {
                return xobj.getUser();
            }
        }
        return null;
    }

    public <T extends XmlObject> void find_all_element_users(QName qName, List<T> list) {
        for (Xobj xobj = this._firstChild; xobj != null; xobj = xobj._nextSibling) {
            if (xobj.isElem() && xobj._name.equals(qName)) {
                list.add((XmlObject) xobj.getUser());
            }
        }
    }

    public <T extends XmlObject> void find_all_element_users(QNameSet qNameSet, List<T> list) {
        for (Xobj xobj = this._firstChild; xobj != null; xobj = xobj._nextSibling) {
            if (xobj.isElem() && qNameSet.contains(xobj._name)) {
                list.add((XmlObject) xobj.getUser());
            }
        }
    }

    private static TypeStoreUser insertElement(QName qName, Xobj xobj, int i) {
        xobj._locale.enter();
        try {
            Cur tempCur = xobj._locale.tempCur();
            tempCur.moveTo(xobj, i);
            tempCur.createElement(qName);
            TypeStoreUser user = tempCur.getUser();
            tempCur.release();
            return user;
        } finally {
            xobj._locale.exit();
        }
    }

    public TypeStoreUser insert_element_user(QName qName, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (isContainer()) {
            Xobj findNthChildElem = this._locale.findNthChildElem(this, qName, (QNameSet) null, i);
            if (findNthChildElem != null) {
                return insertElement(qName, findNthChildElem, 0);
            }
            if (i <= this._locale.count(this, qName, (QNameSet) null) + 1) {
                return add_element_user(qName);
            }
            throw new IndexOutOfBoundsException();
        } else {
            throw new IllegalStateException();
        }
    }

    public TypeStoreUser insert_element_user(QNameSet qNameSet, QName qName, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (isContainer()) {
            Xobj findNthChildElem = this._locale.findNthChildElem(this, (QName) null, qNameSet, i);
            if (findNthChildElem != null) {
                return insertElement(qName, findNthChildElem, 0);
            }
            if (i <= this._locale.count(this, (QName) null, qNameSet) + 1) {
                return add_element_user(qName);
            }
            throw new IndexOutOfBoundsException();
        } else {
            throw new IllegalStateException();
        }
    }

    public TypeStoreUser add_element_user(QName qName) {
        if (isContainer()) {
            Xobj xobj = null;
            boolean z = false;
            QNameSet qNameSet = null;
            for (Xobj xobj2 = this._lastChild; xobj2 != null; xobj2 = xobj2._prevSibling) {
                if (xobj2.isContainer()) {
                    if (xobj2._name.equals(qName)) {
                        break;
                    }
                    if (!z) {
                        qNameSet = this._user.get_element_ending_delimiters(qName);
                        z = true;
                    }
                    if (qNameSet == null || qNameSet.contains(xobj2._name)) {
                        xobj = xobj2;
                    }
                }
            }
            if (xobj == null) {
                return insertElement(qName, this, -1);
            }
            return insertElement(qName, xobj, 0);
        }
        throw new IllegalStateException();
    }

    private static void removeElement(Xobj xobj) {
        if (xobj != null) {
            xobj._locale.enter();
            try {
                Cur tempCur = xobj.tempCur();
                tempCur.moveNode((Cur) null);
                tempCur.release();
            } finally {
                xobj._locale.exit();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void remove_element(QName qName, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (isContainer()) {
            Xobj xobj = this._firstChild;
            while (xobj != null && (!xobj.isElem() || !xobj._name.equals(qName) || i - 1 >= 0)) {
                xobj = xobj._nextSibling;
            }
            removeElement(xobj);
        } else {
            throw new IllegalStateException();
        }
    }

    public void remove_element(QNameSet qNameSet, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (isContainer()) {
            Xobj xobj = this._firstChild;
            while (xobj != null && (!xobj.isElem() || !qNameSet.contains(xobj._name) || i - 1 >= 0)) {
                xobj = xobj._nextSibling;
            }
            removeElement(xobj);
        } else {
            throw new IllegalStateException();
        }
    }

    public TypeStoreUser find_attribute_user(QName qName) {
        Xobj attr = getAttr(qName);
        if (attr == null) {
            return null;
        }
        return attr.getUser();
    }

    public TypeStoreUser add_attribute_user(QName qName) {
        if (getAttr(qName) == null) {
            this._locale.enter();
            try {
                return setAttr(qName, "").getUser();
            } finally {
                this._locale.exit();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void remove_attribute(QName qName) {
        this._locale.enter();
        try {
            if (!removeAttr(qName)) {
                throw new IndexOutOfBoundsException();
            }
        } finally {
            this._locale.exit();
        }
    }

    public TypeStoreUser copy_contents_from(TypeStore typeStore) {
        Cur tempCur;
        Locale locale;
        this = (Xobj) typeStore;
        if (this == this) {
            return getUser();
        }
        this._locale.enter();
        try {
            this._locale.enter();
            tempCur = tempCur();
            Cur tempCur2 = this.tempCur();
            Map<String, String> allNamespaces = Locale.getAllNamespaces(tempCur2, (Map<String, String>) null);
            tempCur2.release();
            if (isAttr()) {
                Cur tempCur3 = this.tempCur();
                String textValue = Locale.getTextValue(tempCur3);
                tempCur3.release();
                tempCur.setValue(textValue);
            } else {
                disconnectChildrenUsers();
                setBit(1024);
                QName xsiTypeName = isContainer() ? getXsiTypeName() : null;
                Xobj copyNode = this.copyNode(this._locale);
                Cur.moveNodeContents(this, (Cur) null, true);
                tempCur.next();
                Cur.moveNodeContents(copyNode, tempCur, true);
                tempCur.moveTo(this);
                if (xsiTypeName != null) {
                    tempCur.setXsiType(xsiTypeName);
                }
                clearBit(1024);
            }
            if (allNamespaces != null) {
                if (!tempCur.isContainer()) {
                    tempCur.toParent();
                }
                Locale.applyNamespaces(tempCur, allNamespaces);
            }
            tempCur.release();
            this._locale.exit();
            return getUser();
        } catch (Throwable th) {
            tempCur.release();
            throw th;
        } finally {
            this._locale.exit();
        }
    }

    /* JADX INFO: finally extract failed */
    public TypeStoreUser copy(SchemaTypeLoader schemaTypeLoader, SchemaType schemaType, XmlOptions xmlOptions) {
        XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
        SchemaType documentType = maskNull.getDocumentType();
        if (documentType == null) {
            documentType = schemaType == null ? XmlObject.type : schemaType;
        }
        Locale locale = locale();
        if (maskNull.isCopyUseNewSynchronizationDomain()) {
            locale = Locale.getLocale(schemaTypeLoader, maskNull);
        }
        Xobj createDomDocumentRootXobj = Cur.createDomDocumentRootXobj(locale, !documentType.isDocumentType() && (!documentType.isNoType() || !(this instanceof DocumentXobj)));
        locale.enter();
        try {
            Cur tempCur = createDomDocumentRootXobj.tempCur();
            tempCur.setType(schemaType);
            tempCur.release();
            locale.exit();
            return createDomDocumentRootXobj.copy_contents_from(this);
        } catch (Throwable th) {
            locale.exit();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void array_setter(org.apache.xmlbeans.XmlObject[] r11, javax.xml.namespace.QName r12) {
        /*
            r10 = this;
            org.apache.xmlbeans.impl.store.Locale r0 = r10._locale
            r0.enter()
            int r0 = r11.length     // Catch:{ all -> 0x00f3 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x00f3 }
            r1.<init>()     // Catch:{ all -> 0x00f3 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00f3 }
            r2.<init>()     // Catch:{ all -> 0x00f3 }
            int r3 = r11.length     // Catch:{ all -> 0x00f3 }
            r4 = 0
            r5 = r4
        L_0x0013:
            r6 = 0
            if (r5 >= r3) goto L_0x0068
            r7 = r11[r5]     // Catch:{ all -> 0x00f3 }
            if (r7 == 0) goto L_0x0060
            boolean r8 = r7.isImmutable()     // Catch:{ all -> 0x00f3 }
            if (r8 == 0) goto L_0x0027
            r1.add(r6)     // Catch:{ all -> 0x00f3 }
            r2.add(r6)     // Catch:{ all -> 0x00f3 }
            goto L_0x0056
        L_0x0027:
            r6 = r7
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = (org.apache.xmlbeans.impl.values.TypeStoreUser) r6     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj r6 = (org.apache.xmlbeans.impl.store.Xobj) r6     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Locale r8 = r6._locale     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Locale r9 = r10._locale     // Catch:{ all -> 0x00f3 }
            if (r8 != r9) goto L_0x003e
            org.apache.xmlbeans.impl.store.Xobj r6 = r6.copyNode(r9)     // Catch:{ all -> 0x00f3 }
            r1.add(r6)     // Catch:{ all -> 0x00f3 }
            goto L_0x004f
        L_0x003e:
            r8.enter()     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Locale r8 = r10._locale     // Catch:{ all -> 0x0059 }
            org.apache.xmlbeans.impl.store.Xobj r8 = r6.copyNode(r8)     // Catch:{ all -> 0x0059 }
            r1.add(r8)     // Catch:{ all -> 0x0059 }
            org.apache.xmlbeans.impl.store.Locale r6 = r6._locale     // Catch:{ all -> 0x00f3 }
            r6.exit()     // Catch:{ all -> 0x00f3 }
        L_0x004f:
            org.apache.xmlbeans.SchemaType r6 = r7.schemaType()     // Catch:{ all -> 0x00f3 }
            r2.add(r6)     // Catch:{ all -> 0x00f3 }
        L_0x0056:
            int r5 = r5 + 1
            goto L_0x0013
        L_0x0059:
            r11 = move-exception
            org.apache.xmlbeans.impl.store.Locale r12 = r6._locale     // Catch:{ all -> 0x00f3 }
            r12.exit()     // Catch:{ all -> 0x00f3 }
            throw r11     // Catch:{ all -> 0x00f3 }
        L_0x0060:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00f3 }
            java.lang.String r12 = "Array element null"
            r11.<init>(r12)     // Catch:{ all -> 0x00f3 }
            throw r11     // Catch:{ all -> 0x00f3 }
        L_0x0068:
            int r3 = r10.count_elements((javax.xml.namespace.QName) r12)     // Catch:{ all -> 0x00f3 }
        L_0x006c:
            if (r3 <= r0) goto L_0x0074
            r10.remove_element((javax.xml.namespace.QName) r12, (int) r0)     // Catch:{ all -> 0x00f3 }
            int r3 = r3 + -1
            goto L_0x006c
        L_0x0074:
            if (r0 <= r3) goto L_0x007c
            r10.add_element_user(r12)     // Catch:{ all -> 0x00f3 }
            int r3 = r3 + 1
            goto L_0x0074
        L_0x007c:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00f3 }
            r0.<init>()     // Catch:{ all -> 0x00f3 }
            r10.find_all_element_users((javax.xml.namespace.QName) r12, r0)     // Catch:{ all -> 0x00f3 }
            java.util.stream.Stream r12 = r0.stream()     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda0 r0 = new org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda0     // Catch:{ all -> 0x00f3 }
            r0.<init>()     // Catch:{ all -> 0x00f3 }
            java.util.stream.Stream r12 = r12.map(r0)     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda1 r0 = new org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda1     // Catch:{ all -> 0x00f3 }
            r0.<init>()     // Catch:{ all -> 0x00f3 }
            java.util.stream.Stream r12 = r12.map(r0)     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda2 r0 = new org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda2     // Catch:{ all -> 0x00f3 }
            r0.<init>()     // Catch:{ all -> 0x00f3 }
            java.util.stream.Stream r12 = r12.map(r0)     // Catch:{ all -> 0x00f3 }
            java.util.stream.Collector r0 = java.util.stream.Collectors.toList()     // Catch:{ all -> 0x00f3 }
            java.lang.Object r12 = r12.collect(r0)     // Catch:{ all -> 0x00f3 }
            java.util.List r12 = (java.util.List) r12     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Cur r0 = r10.tempCur()     // Catch:{ all -> 0x00f3 }
        L_0x00b1:
            if (r4 >= r3) goto L_0x00ea
            java.lang.Object r5 = r12.get(r4)     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj r5 = (org.apache.xmlbeans.impl.store.Xobj) r5     // Catch:{ all -> 0x00f3 }
            r7 = r11[r4]     // Catch:{ all -> 0x00f3 }
            boolean r7 = r7.isImmutable()     // Catch:{ all -> 0x00f3 }
            if (r7 == 0) goto L_0x00cb
            org.apache.xmlbeans.XmlObject r5 = r5.getObject()     // Catch:{ all -> 0x00f3 }
            r7 = r11[r4]     // Catch:{ all -> 0x00f3 }
            r5.set(r7)     // Catch:{ all -> 0x00f3 }
            goto L_0x00e7
        L_0x00cb:
            r7 = 1
            org.apache.xmlbeans.impl.store.Cur.moveNodeContents(r5, r6, r7)     // Catch:{ all -> 0x00f3 }
            r0.moveTo(r5)     // Catch:{ all -> 0x00f3 }
            r0.next()     // Catch:{ all -> 0x00f3 }
            java.lang.Object r8 = r1.get(r4)     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Xobj r8 = (org.apache.xmlbeans.impl.store.Xobj) r8     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Cur.moveNodeContents(r8, r0, r7)     // Catch:{ all -> 0x00f3 }
            java.lang.Object r7 = r2.get(r4)     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.SchemaType r7 = (org.apache.xmlbeans.SchemaType) r7     // Catch:{ all -> 0x00f3 }
            r5.change_type(r7)     // Catch:{ all -> 0x00f3 }
        L_0x00e7:
            int r4 = r4 + 1
            goto L_0x00b1
        L_0x00ea:
            r0.release()     // Catch:{ all -> 0x00f3 }
            org.apache.xmlbeans.impl.store.Locale r10 = r10._locale
            r10.exit()
            return
        L_0x00f3:
            r11 = move-exception
            org.apache.xmlbeans.impl.store.Locale r10 = r10._locale
            r10.exit()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Xobj.array_setter(org.apache.xmlbeans.XmlObject[], javax.xml.namespace.QName):void");
    }

    static /* synthetic */ TypeStoreUser lambda$array_setter$0(XmlObject xmlObject) {
        return (TypeStoreUser) xmlObject;
    }

    static /* synthetic */ Xobj lambda$array_setter$1(TypeStore typeStore) {
        return (Xobj) typeStore;
    }

    public void visit_elements(TypeStoreVisitor typeStoreVisitor) {
        throw new RuntimeException("Not implemeneted");
    }

    public XmlObject[] exec_query(String str, XmlOptions xmlOptions) {
        this._locale.enter();
        try {
            Cur tempCur = tempCur();
            XmlObject[] objectExecQuery = XPathFactory.objectExecQuery(tempCur, str, xmlOptions);
            tempCur.release();
            return objectExecQuery;
        } finally {
            this._locale.exit();
        }
    }

    public String find_prefix_for_nsuri(String str, String str2) {
        this._locale.enter();
        try {
            return prefixForNamespace(str, str2, true);
        } finally {
            this._locale.exit();
        }
    }

    public String getNamespaceForPrefix(String str) {
        return namespaceForPrefix(str, true);
    }
}
