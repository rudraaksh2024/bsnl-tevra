package org.apache.xmlbeans.impl.inst2xsd.util;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy$$ExternalSyntheticLambda0;
import org.apache.xmlbeans.impl.values.XmlQNameImpl;

public class Type {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int COMPLEX_TYPE_COMPLEX_CONTENT = 3;
    public static final int COMPLEX_TYPE_EMPTY_CONTENT = 5;
    public static final int COMPLEX_TYPE_MIXED_CONTENT = 4;
    public static final int COMPLEX_TYPE_SIMPLE_CONTENT = 2;
    public static final int PARTICLE_CHOICE_UNBOUNDED = 2;
    public static final int PARTICLE_SEQUENCE = 1;
    public static final int SIMPLE_TYPE_SIMPLE_CONTENT = 1;
    private boolean _acceptsEnumerationValue = true;
    private List<Attribute> _attributes;
    private List<Element> _elements;
    private List<QName> _enumerationQNames;
    private List<String> _enumerationValues;
    private Type _extensionType;
    private boolean _isGlobal = false;
    private int _kind = 1;
    private QName _name;
    private int _topParticleForComplexOrMixedContent = 1;

    protected Type() {
    }

    public static Type createNamedType(QName qName, int i) {
        Type type = new Type();
        type.setName(qName);
        type.setContentType(i);
        return type;
    }

    public static Type createUnnamedType(int i) {
        Type type = new Type();
        type.setContentType(i);
        return type;
    }

    public QName getName() {
        return this._name;
    }

    public void setName(QName qName) {
        this._name = qName;
    }

    public int getContentType() {
        return this._kind;
    }

    public void setContentType(int i) {
        this._kind = i;
    }

    public List<Element> getElements() {
        ensureElements();
        return this._elements;
    }

    public void addElement(Element element) {
        ensureElements();
        this._elements.add(element);
    }

    public void setElements(List<Element> list) {
        ensureElements();
        this._elements.clear();
        this._elements.addAll(list);
    }

    private void ensureElements() {
        if (this._elements == null) {
            this._elements = new ArrayList();
        }
    }

    public List<Attribute> getAttributes() {
        ensureAttributes();
        return this._attributes;
    }

    public void addAttribute(Attribute attribute) {
        ensureAttributes();
        this._attributes.add(attribute);
    }

    public Attribute getAttribute(QName qName) {
        for (Attribute next : this._attributes) {
            if (next.getName().equals(qName)) {
                return next;
            }
        }
        return null;
    }

    private void ensureAttributes() {
        if (this._attributes == null) {
            this._attributes = new ArrayList();
        }
    }

    public boolean isComplexType() {
        int i = this._kind;
        return i == 3 || i == 4 || i == 2;
    }

    public boolean hasSimpleContent() {
        int i = this._kind;
        return i == 1 || i == 2;
    }

    public int getTopParticleForComplexOrMixedContent() {
        return this._topParticleForComplexOrMixedContent;
    }

    public void setTopParticleForComplexOrMixedContent(int i) {
        this._topParticleForComplexOrMixedContent = i;
    }

    public boolean isGlobal() {
        return this._isGlobal;
    }

    public void setGlobal(boolean z) {
        this._isGlobal = z;
    }

    public Type getExtensionType() {
        return this._extensionType;
    }

    public void setExtensionType(Type type) {
        this._extensionType = type;
    }

    public List<String> getEnumerationValues() {
        ensureEnumerationValues();
        return this._enumerationValues;
    }

    public List<QName> getEnumerationQNames() {
        ensureEnumerationValues();
        return this._enumerationQNames;
    }

    public void addEnumerationValue(String str, XmlCursor xmlCursor) {
        ensureEnumerationValues();
        if (this._acceptsEnumerationValue && !this._enumerationValues.contains(str)) {
            this._enumerationValues.add(str);
            if (this._name.equals(XmlQName.type.getName())) {
                xmlCursor.getClass();
                this._enumerationQNames.add(XmlQNameImpl.validateLexical(str, (ValidationContext) null, new RussianDollStrategy$$ExternalSyntheticLambda0(xmlCursor)));
            }
        }
    }

    private void ensureEnumerationValues() {
        if (this._enumerationValues == null) {
            this._enumerationValues = new ArrayList();
            this._enumerationQNames = new ArrayList();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._enumerationValues;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEnumeration() {
        /*
            r1 = this;
            boolean r0 = r1._acceptsEnumerationValue
            if (r0 == 0) goto L_0x0010
            java.util.List<java.lang.String> r1 = r1._enumerationValues
            if (r1 == 0) goto L_0x0010
            int r1 = r1.size()
            r0 = 1
            if (r1 <= r0) goto L_0x0010
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.inst2xsd.util.Type.isEnumeration():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0014, code lost:
        r2 = r2._enumerationQNames;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isQNameEnumeration() {
        /*
            r2 = this;
            boolean r0 = r2.isEnumeration()
            if (r0 == 0) goto L_0x0020
            javax.xml.namespace.QName r0 = r2._name
            org.apache.xmlbeans.SchemaType r1 = org.apache.xmlbeans.XmlQName.type
            javax.xml.namespace.QName r1 = r1.getName()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0020
            java.util.List<javax.xml.namespace.QName> r2 = r2._enumerationQNames
            if (r2 == 0) goto L_0x0020
            int r2 = r2.size()
            r0 = 1
            if (r2 <= r0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.inst2xsd.util.Type.isQNameEnumeration():boolean");
    }

    public void closeEnumeration() {
        this._acceptsEnumerationValue = false;
    }

    public String toString() {
        return "Type{_name = " + this._name + ", _extensionType = " + this._extensionType + ", _kind = " + this._kind + ", _elements = " + this._elements + ", _attributes = " + this._attributes + VectorFormat.DEFAULT_SUFFIX;
    }

    public void addAllEnumerationsFrom(Type type) {
        ensureEnumerationValues();
        int i = 0;
        if (!this._name.equals(XmlQName.type.getName()) || !type._name.equals(XmlQName.type.getName())) {
            while (i < type.getEnumerationValues().size()) {
                String str = type.getEnumerationValues().get(i);
                if (this._acceptsEnumerationValue && !this._enumerationValues.contains(str)) {
                    this._enumerationValues.add(str);
                }
                i++;
            }
            return;
        }
        while (i < type.getEnumerationValues().size()) {
            String str2 = type.getEnumerationValues().get(i);
            QName qName = type.getEnumerationQNames().get(i);
            if (this._acceptsEnumerationValue && !this._enumerationQNames.contains(qName)) {
                this._enumerationValues.add(str2);
                this._enumerationQNames.add(qName);
            }
            i++;
        }
    }
}
