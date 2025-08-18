package org.apache.xmlbeans.impl.xpath.xmlbeans;

import java.util.ConcurrentModificationException;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathExecutionContext;

class XmlbeansXPathEngine extends XPathExecutionContext implements XPathEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Cur _cur;
    private final long _version;

    XmlbeansXPathEngine(XPath xPath, Cur cur) {
        this._version = cur.getLocale().version();
        Cur weakCur = cur.weakCur(this);
        this._cur = weakCur;
        weakCur.push();
        init(xPath);
        int start = start();
        if ((start & 1) != 0) {
            cur.addToSelection();
        }
        doAttrs(start, cur);
        if ((start & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
            release();
        }
    }

    private void advance(Cur cur) {
        if (this._cur.isFinish()) {
            if (this._cur.isAtEndOfLastPush()) {
                release();
                return;
            }
            end();
            this._cur.next();
        } else if (this._cur.isElem()) {
            int element = element(this._cur.getName());
            if ((element & 1) != 0) {
                cur.addToSelection(this._cur);
            }
            doAttrs(element, cur);
            if ((element & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
                end();
                this._cur.skip();
            }
        } else {
            do {
                this._cur.next();
            } while (!this._cur.isContainerOrFinish());
        }
    }

    private void doAttrs(int i, Cur cur) {
        if ((i & 4) != 0 && this._cur.toFirstAttr()) {
            do {
                if (attr(this._cur.getName())) {
                    cur.addToSelection(this._cur);
                }
            } while (this._cur.toNextAttr());
            this._cur.toParent();
        }
    }

    public boolean next(Cur cur) {
        Cur cur2 = this._cur;
        if (cur2 == null || this._version == cur2.getLocale().version()) {
            int selectionCount = cur.selectionCount();
            while (this._cur != null) {
                advance(cur);
                if (selectionCount != cur.selectionCount()) {
                    return true;
                }
            }
            return false;
        }
        throw new ConcurrentModificationException("Document changed during select");
    }

    public void release() {
        Cur cur = this._cur;
        if (cur != null) {
            cur.release();
            this._cur = null;
        }
    }
}
