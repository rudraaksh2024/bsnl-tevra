package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public class JavaDecimalHolder extends XmlObjectBase {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final BigInteger _maxlong = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger _minlong = BigInteger.valueOf(Long.MIN_VALUE);
    private BigDecimal _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_DECIMAL;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return XsTypeConverter.printDecimal(this._value);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        if (_validateOnSet()) {
            validateLexical(str, _voorVc);
        }
        try {
            set_BigDecimal(new BigDecimal(str));
        } catch (NumberFormatException unused) {
            _voorVc.invalid(XmlErrorCodes.DECIMAL, new Object[]{str});
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r3 = r9.charAt(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validateLexical(java.lang.String r9, org.apache.xmlbeans.impl.common.ValidationContext r10) {
        /*
            int r0 = r9.length()
            r1 = 1
            r2 = 0
            if (r0 <= 0) goto L_0x0016
            char r3 = r9.charAt(r2)
            r4 = 43
            if (r3 == r4) goto L_0x0014
            r4 = 45
            if (r3 != r4) goto L_0x0016
        L_0x0014:
            r3 = r1
            goto L_0x0017
        L_0x0016:
            r3 = r2
        L_0x0017:
            r4 = r2
            r5 = r4
        L_0x0019:
            java.lang.String r6 = "decimal"
            if (r3 >= r0) goto L_0x0069
            char r7 = r9.charAt(r3)
            r8 = 46
            if (r7 != r8) goto L_0x0040
            if (r5 == 0) goto L_0x003e
            java.lang.Object[] r0 = new java.lang.Object[r1]
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "saw '.' more than once: "
            r1.<init>(r3)
            java.lang.StringBuilder r9 = r1.append(r9)
            java.lang.String r9 = r9.toString()
            r0[r2] = r9
            r10.invalid(r6, r0)
            return
        L_0x003e:
            r5 = r1
            goto L_0x0049
        L_0x0040:
            r4 = 48
            if (r7 < r4) goto L_0x004c
            r4 = 57
            if (r7 > r4) goto L_0x004c
            r4 = r1
        L_0x0049:
            int r3 = r3 + 1
            goto L_0x0019
        L_0x004c:
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unexpected char '"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9[r2] = r0
            r10.invalid(r6, r9)
            return
        L_0x0069:
            if (r4 != 0) goto L_0x0074
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r0 = "expected at least one digit"
            r9[r2] = r0
            r10.invalid(r6, r9)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.JavaDecimalHolder.validateLexical(java.lang.String, org.apache.xmlbeans.impl.common.ValidationContext):void");
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return this._value.compareTo(((XmlObjectBase) xmlObject).getBigDecimalValue());
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.compareTo(((XmlObjectBase) xmlObject).getBigDecimalValue()) == 0;
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        if (this._value.scale() > 0 && this._value.setScale(0, RoundingMode.DOWN).compareTo(this._value) != 0) {
            return decimalHashCode();
        }
        BigInteger bigInteger = this._value.toBigInteger();
        if (bigInteger.compareTo(_maxlong) > 0 || bigInteger.compareTo(_minlong) < 0) {
            return bigInteger.hashCode();
        }
        long longValue = bigInteger.longValue();
        return (int) (((longValue >> 32) * 19) + longValue);
    }

    /* access modifiers changed from: protected */
    public int decimalHashCode() {
        String bigDecimal = this._value.toString();
        int length = bigDecimal.length() - 1;
        while (length >= 0 && bigDecimal.charAt(length) == '0') {
            length--;
        }
        return bigDecimal.substring(0, length + 1).hashCode();
    }
}
