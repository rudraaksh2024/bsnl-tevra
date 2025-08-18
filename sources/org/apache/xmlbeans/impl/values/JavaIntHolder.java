package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public abstract class JavaIntHolder extends XmlObjectBase {
    static final BigInteger _max = BigInteger.valueOf(2147483647L);
    static final BigInteger _min = BigInteger.valueOf(-2147483648L);
    private int _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_INT;
    }

    public String compute_text(NamespaceManager namespaceManager) {
        return Long.toString((long) this._value);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        try {
            set_int(XsTypeConverter.lexInt(str));
        } catch (Exception unused) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.INT, new Object[]{str});
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = 0;
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        return BigDecimal.valueOf((double) this._value);
    }

    public BigInteger getBigIntegerValue() {
        check_dated();
        return BigInteger.valueOf((long) this._value);
    }

    public long getLongValue() {
        check_dated();
        return (long) this._value;
    }

    public int getIntValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        set_BigInteger(bigDecimal.toBigInteger());
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        if (bigInteger.compareTo(_max) > 0 || bigInteger.compareTo(_min) < 0) {
            throw new XmlValueOutOfRangeException();
        }
        set_int(bigInteger.intValue());
    }

    /* access modifiers changed from: protected */
    public void set_long(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            throw new XmlValueOutOfRangeException();
        }
        set_int((int) j);
    }

    /* access modifiers changed from: protected */
    public void set_int(int i) {
        this._value = i;
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        if (((SimpleValue) xmlObject).instanceType().getDecimalSize() > 32) {
            return -xmlObject.compareTo(this);
        }
        return Integer.compare(this._value, ((XmlObjectBase) xmlObject).getIntValue());
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        if (((SimpleValue) xmlObject).instanceType().getDecimalSize() > 32) {
            return xmlObject.valueEquals(this);
        }
        return this._value == ((XmlObjectBase) xmlObject).getIntValue();
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value;
    }
}
