package org.apache.xmlbeans.impl.inst2xsd.util;

import javax.xml.namespace.QName;

public class Element {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int UNBOUNDED = -1;
    private String _comment = null;
    private boolean _isGlobal = false;
    private boolean _isNillable = false;
    private int _maxOccurs = 1;
    private int _minOccurs = 1;
    private QName _name = null;
    private Element _ref = null;
    private Type _type = null;

    public QName getName() {
        return this._name;
    }

    public void setName(QName qName) {
        this._name = qName;
    }

    public boolean isRef() {
        return this._ref != null;
    }

    public Element getRef() {
        return this._ref;
    }

    public void setRef(Element element) {
        this._ref = element;
        this._type = null;
    }

    public boolean isGlobal() {
        return this._isGlobal;
    }

    public void setGlobal(boolean z) {
        this._isGlobal = z;
        this._minOccurs = 1;
        this._maxOccurs = 1;
    }

    public int getMinOccurs() {
        return this._minOccurs;
    }

    public void setMinOccurs(int i) {
        this._minOccurs = i;
    }

    public int getMaxOccurs() {
        return this._maxOccurs;
    }

    public void setMaxOccurs(int i) {
        this._maxOccurs = i;
    }

    public boolean isNillable() {
        return this._isNillable;
    }

    public void setNillable(boolean z) {
        this._isNillable = z;
    }

    public Type getType() {
        return isRef() ? getRef().getType() : this._type;
    }

    public void setType(Type type) {
        this._type = type;
    }

    public String getComment() {
        return this._comment;
    }

    public void setComment(String str) {
        this._comment = str;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder("\n  Element{ _name = ").append(this._name).append(", _ref = ").append(this._ref != null).append(", _isGlobal = ").append(this._isGlobal).append(", _minOccurs = ").append(this._minOccurs).append(", _maxOccurs = ").append(this._maxOccurs).append(", _isNillable = ").append(this._isNillable).append(", _comment = ").append(this._comment).append(",\n    _type = ");
        Type type = this._type;
        if (type == null) {
            str = "null";
        } else {
            boolean isGlobal = type.isGlobal();
            Type type2 = this._type;
            str = isGlobal ? type2.getName().toString() : type2.toString();
        }
        return append.append(str).append("\n  }").toString();
    }
}
