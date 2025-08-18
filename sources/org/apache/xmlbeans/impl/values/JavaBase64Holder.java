package org.apache.xmlbeans.impl.values;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;

public abstract class JavaBase64Holder extends XmlObjectBase {
    protected static final MessageDigest md5;
    protected boolean _hashcached = false;
    protected byte[] _value;
    protected int hashcode = 0;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_BASE_64_BINARY;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return Base64.getEncoder().encodeToString(this._value);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        this._hashcached = false;
        if (_validateOnSet()) {
            this._value = validateLexical(str, schemaType(), _voorVc);
        } else {
            this._value = lex(str, _voorVc);
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._hashcached = false;
        this._value = null;
    }

    public static byte[] lex(String str, ValidationContext validationContext) {
        try {
            return Base64.getDecoder().decode(str);
        } catch (IllegalArgumentException unused) {
            validationContext.invalid(XmlErrorCodes.BASE64BINARY, new Object[]{"not encoded properly"});
            return null;
        }
    }

    public static byte[] validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        byte[] lex = lex(str, validationContext);
        if (lex == null) {
            return null;
        }
        if (schemaType.matchPatternFacet(str)) {
            return lex;
        }
        validationContext.invalid(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID$NO_VALUE, new Object[]{"base 64", QNameHelper.readable(schemaType)});
        return null;
    }

    public byte[] getByteArrayValue() {
        check_dated();
        byte[] bArr = this._value;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    public void set_ByteArray(byte[] bArr) {
        this._hashcached = false;
        byte[] bArr2 = new byte[bArr.length];
        this._value = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return Arrays.equals(this._value, ((XmlBase64Binary) xmlObject).getByteArrayValue());
    }

    static {
        try {
            md5 = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalStateException("Cannot find MD5 hash Algorithm");
        }
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        if (this._hashcached) {
            return this.hashcode;
        }
        this._hashcached = true;
        byte[] bArr = this._value;
        if (bArr == null) {
            this.hashcode = 0;
            return 0;
        }
        byte[] digest = md5.digest(bArr);
        byte b = (digest[1] << UnionPtg.sid) | (digest[0] << 24) | (digest[2] << 8) | digest[3];
        this.hashcode = b;
        return b;
    }
}
