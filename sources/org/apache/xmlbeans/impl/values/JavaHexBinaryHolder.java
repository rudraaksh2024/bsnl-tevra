package org.apache.xmlbeans.impl.values;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlHexBinary;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.HexBin;

public abstract class JavaHexBinaryHolder extends XmlObjectBase {
    protected static final MessageDigest md5;
    protected boolean _hashcached = false;
    protected byte[] _value;
    protected int hashcode = 0;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_HEX_BINARY;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return new String(HexBin.encode(this._value), StandardCharsets.ISO_8859_1);
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
        byte[] decode = HexBin.decode(str.getBytes(StandardCharsets.ISO_8859_1));
        if (decode == null) {
            validationContext.invalid(XmlErrorCodes.HEXBINARY, new Object[]{"not encoded properly"});
        }
        return decode;
    }

    public static byte[] validateLexical(String str, SchemaType schemaType, ValidationContext validationContext) {
        byte[] lex = lex(str, validationContext);
        if (lex == null) {
            return null;
        }
        if (schemaType.matchPatternFacet(str)) {
            return lex;
        }
        validationContext.invalid("Hex encoded data does not match pattern for " + QNameHelper.readable(schemaType));
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
        return Arrays.equals(this._value, ((XmlHexBinary) xmlObject).getByteArrayValue());
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
