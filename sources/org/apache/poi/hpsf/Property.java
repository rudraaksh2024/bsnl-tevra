package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LocaleUtil;

public class Property {
    public static final int DEFAULT_CODEPAGE = 1252;
    private static final Logger LOG = LogManager.getLogger((Class<?>) Property.class);
    private long id;
    private long type;
    private Object value;

    private boolean typesAreEqual(long j, long j2) {
        return j == j2 || (j == 30 && j2 == 31) || (j2 == 30 && j == 31);
    }

    public Property() {
    }

    public Property(Property property) {
        this(property.id, property.type, property.value);
    }

    public Property(long j, long j2, Object obj) {
        this.id = j;
        this.type = j2;
        this.value = obj;
    }

    public Property(long j, byte[] bArr, long j2, int i, int i2) throws UnsupportedEncodingException {
        this.id = j;
        if (j != 0) {
            int i3 = (int) j2;
            long uInt = LittleEndian.getUInt(bArr, i3);
            this.type = uInt;
            try {
                this.value = VariantSupport.read(bArr, i3 + 4, i, (long) ((int) uInt), i2);
            } catch (UnsupportedVariantTypeException e) {
                VariantSupport.writeUnsupportedTypeMessage(e);
                this.value = e.getValue();
            }
        } else {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
    }

    public Property(long j, LittleEndianByteArrayInputStream littleEndianByteArrayInputStream, int i, int i2) throws UnsupportedEncodingException {
        this.id = j;
        if (j != 0) {
            long readUInt = littleEndianByteArrayInputStream.readUInt();
            this.type = readUInt;
            try {
                this.value = VariantSupport.read(littleEndianByteArrayInputStream, i, (long) ((int) readUInt), i2);
            } catch (UnsupportedVariantTypeException e) {
                VariantSupport.writeUnsupportedTypeMessage(e);
                this.value = e.getValue();
            }
        } else {
            throw new UnsupportedEncodingException("Dictionary not allowed here");
        }
    }

    public long getID() {
        return this.id;
    }

    public void setID(long j) {
        this.id = j;
    }

    public long getType() {
        return this.type;
    }

    public void setType(long j) {
        this.type = j;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    /* access modifiers changed from: protected */
    public int getSize(int i) throws WritingNotSupportedException {
        int variantLength = Variant.getVariantLength(this.type);
        if (variantLength < 0) {
            long j = this.type;
            if (j != 0) {
                if (variantLength == -2) {
                    throw new WritingNotSupportedException(this.type, (Object) null);
                } else if (j == 30 || j == 31) {
                    try {
                        int write = write(new UnsynchronizedByteArrayOutputStream(), i) - 8;
                        return write + ((4 - (write & 3)) & 3);
                    } catch (IOException unused) {
                        throw new WritingNotSupportedException(this.type, this.value);
                    }
                } else {
                    throw new WritingNotSupportedException(this.type, this.value);
                }
            }
        }
        return variantLength;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Property)) {
            return false;
        }
        Property property = (Property) obj;
        Object value2 = property.getValue();
        long id2 = property.getID();
        long j = this.id;
        if (j == id2 && (j == 0 || typesAreEqual(this.type, property.getType()))) {
            Object obj2 = this.value;
            if (obj2 == null && value2 == null) {
                return true;
            }
            if (!(obj2 == null || value2 == null)) {
                Class<?> cls = obj2.getClass();
                Class<?> cls2 = value2.getClass();
                if (!cls.isAssignableFrom(cls2) && !cls2.isAssignableFrom(cls)) {
                    return false;
                }
                Object obj3 = this.value;
                if (!(obj3 instanceof byte[])) {
                    return obj3.equals(value2);
                }
                byte[] bArr = (byte[]) obj3;
                byte[] bArr2 = (byte[]) value2;
                int unpaddedLength = unpaddedLength(bArr);
                if (unpaddedLength != unpaddedLength(bArr2)) {
                    return false;
                }
                for (int i = 0; i < unpaddedLength; i++) {
                    if (bArr[i] != bArr2[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static int unpaddedLength(byte[] bArr) {
        int length = bArr.length - ((bArr.length + 3) % 4);
        for (int length2 = bArr.length; length2 > length; length2--) {
            if (bArr[length2 - 1] != 0) {
                return length2;
            }
        }
        return length;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.id), Long.valueOf(this.type), this.value});
    }

    public String toString() {
        return toString(1252, (PropertyIDMap) null);
    }

    public String toString(int i, PropertyIDMap propertyIDMap) {
        String str;
        StringBuilder sb = new StringBuilder("Property[id: ");
        sb.append(this.id);
        if (propertyIDMap == null) {
            str = null;
        } else {
            str = propertyIDMap.get((Object) Long.valueOf(this.id));
        }
        if (str == null) {
            str = PropertyIDMap.getFallbackProperties().get((Object) Long.valueOf(this.id));
        }
        if (str != null) {
            sb.append(" (");
            sb.append(str);
            sb.append(")");
        }
        sb.append(", type: ");
        sb.append(getType());
        sb.append(" (");
        sb.append(getVariantName());
        sb.append(") , value: ");
        Object value2 = getValue();
        if (value2 instanceof String) {
            sb.append((String) value2);
            sb.append("\n");
            UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream();
            try {
                write(unsynchronizedByteArrayOutputStream, i);
            } catch (Exception e) {
                LOG.atWarn().withThrowable(e).log("can't serialize string");
            }
            if (unsynchronizedByteArrayOutputStream.size() > 8) {
                sb.append(HexDump.dump(unsynchronizedByteArrayOutputStream.toByteArray(), -8, 8));
            }
        } else if (value2 instanceof byte[]) {
            sb.append("\n");
            byte[] bArr = (byte[]) value2;
            if (bArr.length > 0) {
                sb.append(HexDump.dump(bArr, 0, 0));
            }
        } else if (value2 instanceof Date) {
            Date date = (Date) value2;
            long dateToFileTime = Filetime.dateToFileTime(date);
            if (Filetime.isUndefined(date)) {
                sb.append("<undefined>");
            } else if ((dateToFileTime >>> 32) == 0) {
                long j = dateToFileTime * 100;
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                long hours = timeUnit.toHours(j);
                long nanos = j - TimeUnit.HOURS.toNanos(hours);
                long minutes = timeUnit.toMinutes(nanos);
                long nanos2 = nanos - TimeUnit.MINUTES.toNanos(minutes);
                long seconds = timeUnit.toSeconds(nanos2);
                long millis = timeUnit.toMillis(nanos2 - TimeUnit.SECONDS.toNanos(seconds));
                sb.append(String.format(Locale.ROOT, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(millis)}));
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SignatureConfig.SIGNATURE_TIME_FORMAT, Locale.ROOT);
                simpleDateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
                sb.append(simpleDateFormat.format(date));
            }
        } else {
            long j2 = this.type;
            if (j2 == 0 || j2 == 1 || value2 == null) {
                sb.append("null");
            } else {
                sb.append(value2);
                String decodeValueFromID = decodeValueFromID();
                if (decodeValueFromID != null) {
                    sb.append(" (");
                    sb.append(decodeValueFromID);
                    sb.append(")");
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private String getVariantName() {
        if (getID() == 0) {
            return "dictionary";
        }
        return Variant.getVariantName(getType());
    }

    private String decodeValueFromID() {
        try {
            int id2 = (int) getID();
            if (id2 == Integer.MIN_VALUE) {
                return LocaleUtil.getLocaleFromLCID(((Number) this.value).intValue());
            }
            if (id2 != 1) {
                return null;
            }
            return CodePageUtil.codepageToEncoding(((Number) this.value).intValue());
        } catch (Exception unused) {
            LOG.atWarn().log("Can't decode id {}", (Object) Unbox.box(getID()));
            return null;
        }
    }

    public int write(OutputStream outputStream, int i) throws IOException, WritingNotSupportedException {
        long type2 = getType();
        if (type2 == 30 && i != 1200) {
            if (!Charset.forName(CodePageUtil.codepageToEncoding(i > 0 ? i : 1252)).newEncoder().canEncode((String) this.value)) {
                type2 = 31;
            }
        }
        LittleEndian.putUInt(type2, outputStream);
        return 4 + VariantSupport.write(outputStream, type2, getValue(), i);
    }
}
