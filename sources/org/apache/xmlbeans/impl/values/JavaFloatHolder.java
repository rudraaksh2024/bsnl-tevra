package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public abstract class JavaFloatHolder extends XmlObjectBase {
    private float _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_FLOAT;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return serialize(this._value);
    }

    public static String serialize(float f) {
        if (f == Float.POSITIVE_INFINITY) {
            return "INF";
        }
        if (f == Float.NEGATIVE_INFINITY) {
            return "-INF";
        }
        if (Float.isNaN(f)) {
            return "NaN";
        }
        return Float.toString(f);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        set_float(validateLexical(str, _voorVc));
    }

    public static float validateLexical(String str, ValidationContext validationContext) {
        try {
            return XsTypeConverter.lexFloat(str);
        } catch (NumberFormatException unused) {
            validationContext.invalid("float", new Object[]{str});
            return Float.NaN;
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = 0.0f;
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        return new BigDecimal((double) this._value);
    }

    public double getDoubleValue() {
        check_dated();
        return (double) this._value;
    }

    public float getFloatValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_double(double d) {
        set_float((float) d);
    }

    /* access modifiers changed from: protected */
    public void set_float(float f) {
        this._value = f;
    }

    /* access modifiers changed from: protected */
    public void set_long(long j) {
        set_float((float) j);
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        set_float(bigDecimal.floatValue());
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        set_float(bigInteger.floatValue());
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        return compare(this._value, ((XmlObjectBase) xmlObject).getFloatValue());
    }

    static int compare(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        if (f > f2) {
            return 1;
        }
        return Integer.compare(Float.floatToIntBits(f), Float.floatToIntBits(f2));
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return compare(this._value, ((XmlObjectBase) xmlObject).getFloatValue()) == 0;
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return Float.floatToIntBits(this._value);
    }
}
