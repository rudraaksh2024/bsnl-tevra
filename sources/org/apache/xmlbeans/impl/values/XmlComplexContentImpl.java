package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;

public class XmlComplexContentImpl extends XmlObjectBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final SchemaTypeImpl _schemaType;

    public String compute_text(NamespaceManager namespaceManager) {
        return null;
    }

    public void set_nil() {
    }

    public void set_text(String str) {
    }

    /* access modifiers changed from: protected */
    public void update_from_complex_content() {
    }

    public XmlComplexContentImpl(SchemaType schemaType) {
        this._schemaType = (SchemaTypeImpl) schemaType;
        initComplexType(true, true);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    /* access modifiers changed from: protected */
    public final void set_String(String str) {
        if (this._schemaType.getContentType() == 4 || this._schemaType.isNoType()) {
            super.set_String(str);
            return;
        }
        throw new IllegalArgumentException("Type does not allow for textual content: " + this._schemaType);
    }

    public boolean equal_to(XmlObject xmlObject) {
        return this._schemaType.equals(xmlObject.schemaType());
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        throw new IllegalStateException("Complex types cannot be used as hash keys");
    }

    public TypeStoreVisitor new_visitor() {
        return new SchemaTypeVisitorImpl(this._schemaType.getContentModel());
    }

    public boolean is_child_element_order_sensitive() {
        return schemaType().isOrderSensitive();
    }

    public int get_elementflags(QName qName) {
        SchemaProperty elementProperty = schemaType().getElementProperty(qName);
        int i = 0;
        if (elementProperty == null) {
            return 0;
        }
        if (elementProperty.hasDefault() == 1 || elementProperty.hasFixed() == 1 || elementProperty.hasNillable() == 1) {
            return -1;
        }
        int i2 = (elementProperty.hasDefault() == 0 ? 0 : 2) | (elementProperty.hasFixed() == 0 ? 0 : 4);
        if (elementProperty.hasNillable() != 0) {
            i = 1;
        }
        return i2 | i;
    }

    public String get_default_attribute_text(QName qName) {
        return super.get_default_attribute_text(qName);
    }

    public String get_default_element_text(QName qName) {
        SchemaProperty elementProperty = schemaType().getElementProperty(qName);
        if (elementProperty == null) {
            return "";
        }
        return elementProperty.getDefaultText();
    }

    /* access modifiers changed from: protected */
    public void unionArraySetterHelper(Object[] objArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, objArr, new XmlComplexContentImpl$$ExternalSyntheticLambda6());
    }

    /* access modifiers changed from: protected */
    public SimpleValue[] arraySetterHelper(int i, QName qName) {
        SimpleValue[] simpleValueArr = new SimpleValue[i];
        commonSetterHelper(qName, (QNameSet) null, (T[]) simpleValueArr, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda5(simpleValueArr));
        return simpleValueArr;
    }

    static /* synthetic */ void lambda$arraySetterHelper$0(SimpleValue[] simpleValueArr, XmlObjectBase xmlObjectBase, Integer num) {
        simpleValueArr[num.intValue()] = xmlObjectBase;
    }

    /* access modifiers changed from: protected */
    public SimpleValue[] arraySetterHelper(int i, QName qName, QNameSet qNameSet) {
        SimpleValue[] simpleValueArr = new SimpleValue[i];
        commonSetterHelper(qName, qNameSet, (T[]) simpleValueArr, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda16(simpleValueArr));
        return simpleValueArr;
    }

    static /* synthetic */ void lambda$arraySetterHelper$1(SimpleValue[] simpleValueArr, XmlObjectBase xmlObjectBase, Integer num) {
        simpleValueArr[num.intValue()] = xmlObjectBase;
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(boolean[] zArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, zArr == null ? 0 : zArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda21(zArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(float[] fArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, fArr == null ? 0 : fArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda0(fArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(double[] dArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, dArr == null ? 0 : dArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda19(dArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(byte[] bArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, bArr == null ? 0 : bArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda12(bArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(short[] sArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, sArr == null ? 0 : sArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda10(sArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(int[] iArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, iArr == null ? 0 : iArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda23(iArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(long[] jArr, QName qName) {
        commonSetterHelper(qName, (QNameSet) null, jArr == null ? 0 : jArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda24(jArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(BigDecimal[] bigDecimalArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, bigDecimalArr, new XmlComplexContentImpl$$ExternalSyntheticLambda15());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(BigInteger[] bigIntegerArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, bigIntegerArr, new XmlComplexContentImpl$$ExternalSyntheticLambda2());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(String[] strArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, strArr, new XmlComplexContentImpl$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(byte[][] bArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, bArr, new XmlComplexContentImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(GDate[] gDateArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, gDateArr, new XmlComplexContentImpl$$ExternalSyntheticLambda3());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(GDuration[] gDurationArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, gDurationArr, new XmlComplexContentImpl$$ExternalSyntheticLambda27());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(Calendar[] calendarArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, calendarArr, new XmlComplexContentImpl$$ExternalSyntheticLambda17());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(Date[] dateArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, dateArr, new XmlComplexContentImpl$$ExternalSyntheticLambda14());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(QName[] qNameArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, qNameArr, new XmlComplexContentImpl$$ExternalSyntheticLambda8());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(StringEnumAbstractBase[] stringEnumAbstractBaseArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, stringEnumAbstractBaseArr, new XmlComplexContentImpl$$ExternalSyntheticLambda22());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(List<?>[] listArr, QName qName) {
        commonSetterHelper2(qName, (QNameSet) null, listArr, new XmlComplexContentImpl$$ExternalSyntheticLambda20());
    }

    /* access modifiers changed from: protected */
    public void unionArraySetterHelper(Object[] objArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, objArr, new XmlComplexContentImpl$$ExternalSyntheticLambda6());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(boolean[] zArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, zArr == null ? 0 : zArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda7(zArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(float[] fArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, fArr == null ? 0 : fArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda18(fArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(double[] dArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, dArr == null ? 0 : dArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda9(dArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(byte[] bArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, bArr == null ? 0 : bArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda26(bArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(short[] sArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, sArr == null ? 0 : sArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda13(sArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(int[] iArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, iArr == null ? 0 : iArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda11(iArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(long[] jArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper(qName, qNameSet, jArr == null ? 0 : jArr.length, (BiConsumer<XmlObjectBase, Integer>) new XmlComplexContentImpl$$ExternalSyntheticLambda25(jArr));
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(BigDecimal[] bigDecimalArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, bigDecimalArr, new XmlComplexContentImpl$$ExternalSyntheticLambda15());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(BigInteger[] bigIntegerArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, bigIntegerArr, new XmlComplexContentImpl$$ExternalSyntheticLambda2());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(String[] strArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, strArr, new XmlComplexContentImpl$$ExternalSyntheticLambda4());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(byte[][] bArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, bArr, new XmlComplexContentImpl$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(GDate[] gDateArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, gDateArr, new XmlComplexContentImpl$$ExternalSyntheticLambda3());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(GDuration[] gDurationArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, gDurationArr, new XmlComplexContentImpl$$ExternalSyntheticLambda27());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(Calendar[] calendarArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, calendarArr, new XmlComplexContentImpl$$ExternalSyntheticLambda17());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(Date[] dateArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, dateArr, new XmlComplexContentImpl$$ExternalSyntheticLambda14());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(QName[] qNameArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, qNameArr, new XmlComplexContentImpl$$ExternalSyntheticLambda8());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(StringEnumAbstractBase[] stringEnumAbstractBaseArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, stringEnumAbstractBaseArr, new XmlComplexContentImpl$$ExternalSyntheticLambda22());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(List<?>[] listArr, QName qName, QNameSet qNameSet) {
        commonSetterHelper2(qName, qNameSet, listArr, new XmlComplexContentImpl$$ExternalSyntheticLambda20());
    }

    /* access modifiers changed from: protected */
    public void arraySetterHelper(XmlObject[] xmlObjectArr, QName qName) {
        arraySetterHelper(xmlObjectArr, qName, (QNameSet) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cb A[LOOP:3: B:59:0x00c8->B:61:0x00cb, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void arraySetterHelper(org.apache.xmlbeans.XmlObject[] r8, javax.xml.namespace.QName r9, org.apache.xmlbeans.QNameSet r10) {
        /*
            r7 = this;
            org.apache.xmlbeans.impl.values.TypeStore r0 = r7.get_store()
            r1 = 0
            if (r8 == 0) goto L_0x010f
            int r2 = r8.length
            if (r2 != 0) goto L_0x000c
            goto L_0x010f
        L_0x000c:
            if (r10 != 0) goto L_0x0013
            int r2 = r0.count_elements((javax.xml.namespace.QName) r9)
            goto L_0x0017
        L_0x0013:
            int r2 = r0.count_elements((org.apache.xmlbeans.QNameSet) r10)
        L_0x0017:
            r3 = r1
        L_0x0018:
            int r4 = r8.length
            if (r3 >= r4) goto L_0x0040
            r4 = r8[r3]
            boolean r4 = r4.isImmutable()
            if (r4 == 0) goto L_0x0024
            goto L_0x003d
        L_0x0024:
            r4 = r8[r3]
            org.apache.xmlbeans.XmlCursor r4 = r4.newCursor()
            boolean r5 = r4.toParent()
            if (r5 == 0) goto L_0x003a
            org.apache.xmlbeans.XmlObject r5 = r4.getObject()
            if (r5 != r7) goto L_0x003a
            r4.dispose()
            goto L_0x0040
        L_0x003a:
            r4.dispose()
        L_0x003d:
            int r3 = r3 + 1
            goto L_0x0018
        L_0x0040:
            int r4 = r8.length
            if (r3 >= r4) goto L_0x00c5
            if (r10 != 0) goto L_0x004a
            org.apache.xmlbeans.impl.values.TypeStoreUser r4 = r0.find_element_user((javax.xml.namespace.QName) r9, (int) r1)
            goto L_0x004e
        L_0x004a:
            org.apache.xmlbeans.impl.values.TypeStoreUser r4 = r0.find_element_user((org.apache.xmlbeans.QNameSet) r10, (int) r1)
        L_0x004e:
            r5 = r8[r3]
            if (r4 != r5) goto L_0x00c5
        L_0x0052:
            if (r1 >= r3) goto L_0x0069
            if (r10 != 0) goto L_0x005b
            org.apache.xmlbeans.impl.values.TypeStoreUser r2 = r0.insert_element_user(r9, r1)
            goto L_0x005f
        L_0x005b:
            org.apache.xmlbeans.impl.values.TypeStoreUser r2 = r0.insert_element_user(r10, r9, r1)
        L_0x005f:
            org.apache.xmlbeans.impl.values.XmlObjectBase r2 = (org.apache.xmlbeans.impl.values.XmlObjectBase) r2
            r4 = r8[r1]
            r2.set(r4)
            int r1 = r1 + 1
            goto L_0x0052
        L_0x0069:
            int r3 = r3 + 1
            int r1 = r1 + 1
            r2 = r1
            r1 = r3
        L_0x006f:
            int r3 = r8.length
            if (r1 >= r3) goto L_0x00bf
            r3 = r8[r1]
            boolean r3 = r3.isImmutable()
            if (r3 == 0) goto L_0x007c
            r3 = 0
            goto L_0x0082
        L_0x007c:
            r3 = r8[r1]
            org.apache.xmlbeans.XmlCursor r3 = r3.newCursor()
        L_0x0082:
            if (r3 == 0) goto L_0x00a3
            boolean r4 = r3.toParent()
            if (r4 == 0) goto L_0x00a3
            org.apache.xmlbeans.XmlObject r4 = r3.getObject()
            if (r4 != r7) goto L_0x00a3
            r3.dispose()
            if (r10 != 0) goto L_0x009a
            org.apache.xmlbeans.impl.values.TypeStoreUser r3 = r0.find_element_user((javax.xml.namespace.QName) r9, (int) r2)
            goto L_0x009e
        L_0x009a:
            org.apache.xmlbeans.impl.values.TypeStoreUser r3 = r0.find_element_user((org.apache.xmlbeans.QNameSet) r10, (int) r2)
        L_0x009e:
            r4 = r8[r1]
            if (r3 == r4) goto L_0x00ba
            goto L_0x00bf
        L_0x00a3:
            if (r3 == 0) goto L_0x00a8
            r3.dispose()
        L_0x00a8:
            if (r10 != 0) goto L_0x00af
            org.apache.xmlbeans.impl.values.TypeStoreUser r3 = r0.insert_element_user(r9, r2)
            goto L_0x00b3
        L_0x00af:
            org.apache.xmlbeans.impl.values.TypeStoreUser r3 = r0.insert_element_user(r10, r9, r2)
        L_0x00b3:
            org.apache.xmlbeans.impl.values.XmlObjectBase r3 = (org.apache.xmlbeans.impl.values.XmlObjectBase) r3
            r4 = r8[r1]
            r3.set(r4)
        L_0x00ba:
            int r1 = r1 + 1
            int r2 = r2 + 1
            goto L_0x006f
        L_0x00bf:
            int r7 = r0.count_elements((javax.xml.namespace.QName) r9)
            r3 = r1
            goto L_0x00c7
        L_0x00c5:
            r7 = r2
            r2 = r1
        L_0x00c7:
            r4 = r3
        L_0x00c8:
            int r5 = r8.length
            if (r4 >= r5) goto L_0x00d9
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r0.add_element_user(r9)
            org.apache.xmlbeans.impl.values.XmlObjectBase r5 = (org.apache.xmlbeans.impl.values.XmlObjectBase) r5
            r6 = r8[r4]
            r5.set(r6)
            int r4 = r4 + 1
            goto L_0x00c8
        L_0x00d9:
            int r4 = r3 - r1
            int r4 = r4 + r2
            if (r7 <= r4) goto L_0x00ee
            if (r10 != 0) goto L_0x00e6
            int r4 = r7 + -1
            r0.remove_element((javax.xml.namespace.QName) r9, (int) r4)
            goto L_0x00eb
        L_0x00e6:
            int r4 = r7 + -1
            r0.remove_element((org.apache.xmlbeans.QNameSet) r10, (int) r4)
        L_0x00eb:
            int r7 = r7 + -1
            goto L_0x00d9
        L_0x00ee:
            if (r1 >= r3) goto L_0x010e
            if (r2 < r7) goto L_0x00f7
            org.apache.xmlbeans.impl.values.TypeStoreUser r4 = r0.add_element_user(r9)
            goto L_0x0102
        L_0x00f7:
            if (r10 != 0) goto L_0x00fe
            org.apache.xmlbeans.impl.values.TypeStoreUser r4 = r0.find_element_user((javax.xml.namespace.QName) r9, (int) r2)
            goto L_0x0102
        L_0x00fe:
            org.apache.xmlbeans.impl.values.TypeStoreUser r4 = r0.find_element_user((org.apache.xmlbeans.QNameSet) r10, (int) r2)
        L_0x0102:
            org.apache.xmlbeans.impl.values.XmlObjectBase r4 = (org.apache.xmlbeans.impl.values.XmlObjectBase) r4
            r5 = r8[r1]
            r4.set(r5)
            int r1 = r1 + 1
            int r2 = r2 + 1
            goto L_0x00ee
        L_0x010e:
            return
        L_0x010f:
            if (r10 != 0) goto L_0x0116
            int r7 = r0.count_elements((javax.xml.namespace.QName) r9)
            goto L_0x011a
        L_0x0116:
            int r7 = r0.count_elements((org.apache.xmlbeans.QNameSet) r10)
        L_0x011a:
            if (r7 <= 0) goto L_0x0128
            if (r10 != 0) goto L_0x0122
            r0.remove_element((javax.xml.namespace.QName) r9, (int) r1)
            goto L_0x0125
        L_0x0122:
            r0.remove_element((org.apache.xmlbeans.QNameSet) r10, (int) r1)
        L_0x0125:
            int r7 = r7 + -1
            goto L_0x011a
        L_0x0128:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlComplexContentImpl.arraySetterHelper(org.apache.xmlbeans.XmlObject[], javax.xml.namespace.QName, org.apache.xmlbeans.QNameSet):void");
    }

    private <T> void commonSetterHelper(QName qName, QNameSet qNameSet, T[] tArr, BiConsumer<XmlObjectBase, Integer> biConsumer) {
        commonSetterHelper(qName, qNameSet, tArr == null ? 0 : tArr.length, biConsumer);
    }

    private void commonSetterHelper(QName qName, QNameSet qNameSet, int i, BiConsumer<XmlObjectBase, Integer> biConsumer) {
        TypeStoreUser typeStoreUser;
        TypeStore typeStore = get_store();
        int count_elements = qNameSet == null ? typeStore.count_elements(qName) : typeStore.count_elements(qNameSet);
        while (count_elements > i) {
            if (qNameSet == null) {
                typeStore.remove_element(qName, count_elements - 1);
            } else {
                typeStore.remove_element(qNameSet, count_elements - 1);
            }
            count_elements--;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 >= count_elements) {
                typeStoreUser = typeStore.add_element_user(qName);
            } else if (qNameSet == null) {
                typeStoreUser = typeStore.find_element_user(qName, i2);
            } else {
                typeStoreUser = typeStore.find_element_user(qNameSet, i2);
            }
            biConsumer.accept((XmlObjectBase) typeStoreUser, Integer.valueOf(i2));
        }
    }

    private <T> void commonSetterHelper2(QName qName, QNameSet qNameSet, T[] tArr, BiConsumer<XmlObjectBase, T> biConsumer) {
        TypeStoreUser typeStoreUser;
        int length = tArr == null ? 0 : tArr.length;
        TypeStore typeStore = get_store();
        int count_elements = qNameSet == null ? typeStore.count_elements(qName) : typeStore.count_elements(qNameSet);
        while (count_elements > length) {
            if (qNameSet == null) {
                typeStore.remove_element(qName, count_elements - 1);
            } else {
                typeStore.remove_element(qNameSet, count_elements - 1);
            }
            count_elements--;
        }
        for (int i = 0; i < length; i++) {
            if (i >= count_elements) {
                typeStoreUser = typeStore.add_element_user(qName);
            } else if (qNameSet == null) {
                typeStoreUser = typeStore.find_element_user(qName, i);
            } else {
                typeStoreUser = typeStore.find_element_user(qNameSet, i);
            }
            biConsumer.accept((XmlObjectBase) typeStoreUser, tArr[i]);
        }
    }
}
