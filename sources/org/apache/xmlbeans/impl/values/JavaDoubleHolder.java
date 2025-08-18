package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public abstract class JavaDoubleHolder extends XmlObjectBase {
    double _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_DOUBLE;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return serialize(this._value);
    }

    public static String serialize(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return "INF";
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return "-INF";
        }
        if (Double.isNaN(d)) {
            return "NaN";
        }
        return Double.toString(d);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        set_double(validateLexical(str, _voorVc));
    }

    public static double validateLexical(String str, ValidationContext validationContext) {
        try {
            return XsTypeConverter.lexDouble(str);
        } catch (NumberFormatException unused) {
            validationContext.invalid(XmlErrorCodes.DOUBLE, new Object[]{str});
            return Double.NaN;
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = 0.0d;
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        return new BigDecimal(this._value);
    }

    public double getDoubleValue() {
        check_dated();
        return this._value;
    }

    public float getFloatValue() {
        check_dated();
        return (float) this._value;
    }

    /* access modifiers changed from: protected */
    public void set_double(double d) {
        this._value = d;
    }

    /* access modifiers changed from: protected */
    public void set_float(float f) {
        set_double((double) f);
    }

    /* access modifiers changed from: protected */
    public void set_long(long j) {
        set_double((double) j);
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        set_double(bigDecimal.doubleValue());
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        set_double(bigInteger.doubleValue());
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return compare(this._value, ((XmlObjectBase) xmlObject).getDoubleValue());
    }

    static int compare(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        return Long.compare(Double.doubleToLongBits(d), Double.doubleToLongBits(d2));
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return compare(this._value, ((XmlObjectBase) xmlObject).getDoubleValue()) == 0;
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        long doubleToLongBits = Double.doubleToLongBits(this._value);
        return (int) (((doubleToLongBits >> 32) * 19) + doubleToLongBits);
    }
}
