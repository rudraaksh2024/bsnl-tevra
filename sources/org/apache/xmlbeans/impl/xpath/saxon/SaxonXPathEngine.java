package org.apache.xmlbeans.impl.xpath.saxon;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathExecutionContext;
import org.w3c.dom.Node;

public class SaxonXPathEngine extends XPathExecutionContext implements XPathEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Cur _cur;
    private SaxonXPath _engine;
    private boolean _firstCall = true;
    private final long _version;
    private final DateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);

    SaxonXPathEngine(SaxonXPath saxonXPath, Cur cur) {
        this._engine = saxonXPath;
        this._version = cur.getLocale().version();
        this._cur = cur.weakCur(this);
    }

    public boolean next(Cur cur) {
        Cur cur2;
        String str;
        if (!this._firstCall) {
            return false;
        }
        this._firstCall = false;
        Cur cur3 = this._cur;
        if (cur3 == null || this._version == cur3.getLocale().version()) {
            List selectPath = this._engine.selectPath(this._cur.getDom());
            for (int i = 0; i < selectPath.size(); i++) {
                Object obj = selectPath.get(i);
                if (!(obj instanceof Node)) {
                    Object obj2 = selectPath.get(i);
                    if (obj2 instanceof Date) {
                        str = this.xmlDateFormat.format((Date) obj2);
                    } else if (obj2 instanceof BigDecimal) {
                        str = ((BigDecimal) obj2).toPlainString();
                    } else {
                        str = obj2.toString();
                    }
                    try {
                        cur2 = cur.getLocale().load("<xml-fragment/>").tempCur();
                        cur2.setValue(str);
                        org.apache.xmlbeans.impl.store.Locale.autoTypeDocument(cur2, getType(obj), (XmlOptions) null);
                        cur2.next();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    cur2 = ((DomImpl.Dom) obj).tempCur();
                }
                cur.addToSelection(cur2);
                cur2.release();
            }
            release();
            this._engine = null;
            return true;
        }
        throw new ConcurrentModificationException("Document changed during select");
    }

    private SchemaType getType(Object obj) {
        if (obj instanceof Integer) {
            return XmlInteger.type;
        }
        if (obj instanceof Double) {
            return XmlDouble.type;
        }
        if (obj instanceof Long) {
            return XmlLong.type;
        }
        if (obj instanceof Float) {
            return XmlFloat.type;
        }
        if (obj instanceof BigDecimal) {
            return XmlDecimal.type;
        }
        if (obj instanceof Boolean) {
            return XmlBoolean.type;
        }
        if (obj instanceof String) {
            return XmlString.type;
        }
        if (obj instanceof Date) {
            return XmlDate.type;
        }
        return XmlAnySimpleType.type;
    }

    public void release() {
        Cur cur = this._cur;
        if (cur != null) {
            cur.release();
            this._cur = null;
        }
    }
}
