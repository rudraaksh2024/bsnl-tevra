package org.apache.logging.log4j.message;

import java.util.Map;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.EnglishEnums;
import org.apache.logging.log4j.util.StringBuilders;

@AsynchronouslyFormattable
public class StructuredDataMessage extends MapMessage<StructuredDataMessage, String> {
    private static final int HASHVAL = 31;
    private static final int MAX_LENGTH = 32;
    private static final long serialVersionUID = 1703221292892071920L;
    private StructuredDataId id;
    private final int maxLength;
    private String message;
    private String type;

    public enum Format {
        XML,
        FULL
    }

    public StructuredDataMessage(String str, String str2, String str3) {
        this(str, str2, str3, 32);
    }

    public StructuredDataMessage(String str, String str2, String str3, int i) {
        this.id = new StructuredDataId(str, (String[]) null, (String[]) null, i);
        this.message = str2;
        this.type = str3;
        this.maxLength = i;
    }

    public StructuredDataMessage(String str, String str2, String str3, Map<String, String> map) {
        this(str, str2, str3, map, 32);
    }

    public StructuredDataMessage(String str, String str2, String str3, Map<String, String> map, int i) {
        super(map);
        this.id = new StructuredDataId(str, (String[]) null, (String[]) null, i);
        this.message = str2;
        this.type = str3;
        this.maxLength = i;
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2) {
        this(structuredDataId, str, str2, 32);
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, int i) {
        this.id = structuredDataId;
        this.message = str;
        this.type = str2;
        this.maxLength = i;
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, Map<String, String> map) {
        this(structuredDataId, str, str2, map, 32);
    }

    public StructuredDataMessage(StructuredDataId structuredDataId, String str, String str2, Map<String, String> map, int i) {
        super(map);
        this.id = structuredDataId;
        this.message = str;
        this.type = str2;
        this.maxLength = i;
    }

    private StructuredDataMessage(StructuredDataMessage structuredDataMessage, Map<String, String> map) {
        super(map);
        this.id = structuredDataMessage.id;
        this.message = structuredDataMessage.message;
        this.type = structuredDataMessage.type;
        this.maxLength = 32;
    }

    protected StructuredDataMessage() {
        this.maxLength = 32;
    }

    public String[] getFormats() {
        String[] strArr = new String[Format.values().length];
        Format[] values = Format.values();
        int length = values.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            strArr[i2] = values[i].name();
            i++;
            i2++;
        }
        return strArr;
    }

    public StructuredDataId getId() {
        return this.id;
    }

    /* access modifiers changed from: protected */
    public void setId(String str) {
        this.id = new StructuredDataId(str, (String[]) null, (String[]) null);
    }

    /* access modifiers changed from: protected */
    public void setId(StructuredDataId structuredDataId) {
        this.id = structuredDataId;
    }

    public String getType() {
        return this.type;
    }

    /* access modifiers changed from: protected */
    public void setType(String str) {
        if (str.length() <= 32) {
            this.type = str;
            return;
        }
        throw new IllegalArgumentException("structured data type exceeds maximum length of 32 characters: " + str);
    }

    public void formatTo(StringBuilder sb) {
        asString(Format.FULL, (StructuredDataId) null, sb);
    }

    public void formatTo(String[] strArr, StringBuilder sb) {
        asString(getFormat(strArr), (StructuredDataId) null, sb);
    }

    public String getFormat() {
        return this.message;
    }

    /* access modifiers changed from: protected */
    public void setMessageFormat(String str) {
        this.message = str;
    }

    public String asString() {
        return asString(Format.FULL, (StructuredDataId) null);
    }

    public String asString(String str) {
        try {
            return asString((Format) EnglishEnums.valueOf(Format.class, str), (StructuredDataId) null);
        } catch (IllegalArgumentException unused) {
            return asString();
        }
    }

    public final String asString(Format format, StructuredDataId structuredDataId) {
        StringBuilder sb = new StringBuilder();
        asString(format, structuredDataId, sb);
        return sb.toString();
    }

    public final void asString(Format format, StructuredDataId structuredDataId, StringBuilder sb) {
        String format2;
        boolean equals = Format.FULL.equals(format);
        if (equals) {
            if (getType() != null) {
                sb.append(getType()).append(Chars.SPACE);
            } else {
                return;
            }
        }
        StructuredDataId id2 = getId();
        if (id2 != null) {
            structuredDataId = id2.makeId(structuredDataId);
        }
        if (structuredDataId != null && structuredDataId.getName() != null) {
            if (Format.XML.equals(format)) {
                asXml(structuredDataId, sb);
                return;
            }
            sb.append('[');
            StringBuilders.appendValue(sb, structuredDataId);
            sb.append(Chars.SPACE);
            appendMap(sb);
            sb.append(']');
            if (equals && (format2 = getFormat()) != null) {
                sb.append(Chars.SPACE).append(format2);
            }
        }
    }

    private void asXml(StructuredDataId structuredDataId, StringBuilder sb) {
        sb.append("<StructuredData>\n");
        sb.append("<type>").append(this.type).append("</type>\n");
        sb.append("<id>").append(structuredDataId).append("</id>\n");
        super.asXml(sb);
        sb.append("\n</StructuredData>\n");
    }

    public String getFormattedMessage() {
        return asString(Format.FULL, (StructuredDataId) null);
    }

    public String getFormattedMessage(String[] strArr) {
        return asString(getFormat(strArr), (StructuredDataId) null);
    }

    private Format getFormat(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return Format.FULL;
        }
        for (String str : strArr) {
            if (Format.XML.name().equalsIgnoreCase(str)) {
                return Format.XML;
            }
            if (Format.FULL.name().equalsIgnoreCase(str)) {
                return Format.FULL;
            }
        }
        return null;
    }

    public String toString() {
        return asString((Format) null, (StructuredDataId) null);
    }

    public StructuredDataMessage newInstance(Map<String, String> map) {
        return new StructuredDataMessage(this, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StructuredDataMessage structuredDataMessage = (StructuredDataMessage) obj;
        if (!super.equals(obj)) {
            return false;
        }
        String str = this.type;
        if (str == null ? structuredDataMessage.type != null : !str.equals(structuredDataMessage.type)) {
            return false;
        }
        StructuredDataId structuredDataId = this.id;
        if (structuredDataId == null ? structuredDataMessage.id != null : !structuredDataId.equals(structuredDataMessage.id)) {
            return false;
        }
        String str2 = this.message;
        return str2 == null ? structuredDataMessage.message == null : str2.equals(structuredDataMessage.message);
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.type;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        StructuredDataId structuredDataId = this.id;
        int hashCode3 = (hashCode2 + (structuredDataId != null ? structuredDataId.hashCode() : 0)) * 31;
        String str2 = this.message;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    public void validate(String str, boolean z) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, byte b) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, char c) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, double d) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, float f) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, int i) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, long j) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, Object obj) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, short s) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validate(String str, String str2) {
        validateKey(str);
    }

    /* access modifiers changed from: protected */
    public void validateKey(String str) {
        if (this.maxLength <= 0 || str.length() <= this.maxLength) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt < '!' || charAt > '~' || charAt == '=' || charAt == ']' || charAt == '\"') {
                    throw new IllegalArgumentException("Structured data keys must contain printable US ASCII charactersand may not contain a space, =, ], or \"");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Structured data keys are limited to " + this.maxLength + " characters. key: " + str);
    }
}
