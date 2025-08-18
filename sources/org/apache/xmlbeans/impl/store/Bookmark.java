package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.XmlCursor;

class Bookmark implements XmlCursor.XmlMark {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Object _key;
    Bookmark _next;
    int _pos;
    Bookmark _prev;
    Object _value;
    Xobj _xobj;

    Bookmark() {
    }

    /* access modifiers changed from: package-private */
    public boolean isOnList(Bookmark bookmark) {
        while (bookmark != null) {
            if (bookmark == this) {
                return true;
            }
            bookmark = bookmark._next;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Bookmark listInsert(Bookmark bookmark) {
        if (bookmark == null) {
            this._prev = this;
            return this;
        }
        this._prev = bookmark._prev;
        bookmark._prev._next = this;
        bookmark._prev = this;
        return bookmark;
    }

    /* access modifiers changed from: package-private */
    public Bookmark listRemove(Bookmark bookmark) {
        Bookmark bookmark2 = this._prev;
        if (bookmark2 == this) {
            bookmark = null;
        } else {
            if (bookmark == this) {
                bookmark = this._next;
            } else {
                bookmark2._next = this._next;
            }
            Bookmark bookmark3 = this._next;
            if (bookmark3 != null) {
                bookmark3._prev = bookmark2;
                this._next = null;
            } else if (bookmark != null) {
                bookmark._prev = bookmark2;
            }
        }
        this._prev = null;
        return bookmark;
    }

    /* access modifiers changed from: package-private */
    public void moveTo(Xobj xobj, int i) {
        Xobj xobj2 = this._xobj;
        if (xobj2 != xobj) {
            xobj2._bookmarks = listRemove(xobj2._bookmarks);
            xobj._bookmarks = listInsert(xobj._bookmarks);
            this._xobj = xobj;
        }
        this._pos = i;
    }

    public XmlCursor createCursor() {
        Xobj xobj = this._xobj;
        if (xobj != null) {
            return Cursor.newCursor(xobj, this._pos);
        }
        throw new IllegalStateException("Attempting to create a cursor on a bookmark that has been cleared or replaced.");
    }
}
