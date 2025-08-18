package org.apache.logging.log4j.message;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import java.io.Serializable;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.Strings;

public class StructuredDataId implements Serializable, StringBuilderFormattable {
    private static final String AT_SIGN = "@";
    private static final int MAX_LENGTH = 32;
    public static final StructuredDataId META = new StructuredDataId("meta", (String[]) null, new String[]{"sequenceId", "sysUpTime", "language"});
    public static final StructuredDataId ORIGIN = new StructuredDataId(IFramePlayerOptions.Builder.ORIGIN, (String[]) null, new String[]{"ip", "enterpriseId", "software", "swVersion"});
    public static final int RESERVED = -1;
    public static final StructuredDataId TIME_QUALITY = new StructuredDataId("timeQuality", (String[]) null, new String[]{"tzKnown", "isSynced", "syncAccuracy"});
    private static final long serialVersionUID = 9031746276396249990L;
    private final int enterpriseNumber;
    private final String name;
    private final String[] optional;
    private final String[] required;

    public StructuredDataId(String str) {
        this(str, (String[]) null, (String[]) null, 32);
    }

    public StructuredDataId(String str, int i) {
        this(str, (String[]) null, (String[]) null, i);
    }

    public StructuredDataId(String str, String[] strArr, String[] strArr2) {
        this(str, strArr, strArr2, 32);
    }

    public StructuredDataId(String str, String[] strArr, String[] strArr2, int i) {
        int i2;
        if (str != null) {
            i = i <= 0 ? 32 : i;
            if (str.length() <= i) {
                i2 = str.indexOf(AT_SIGN);
            } else {
                throw new IllegalArgumentException(String.format("Length of id %s exceeds maximum of %d characters", new Object[]{str, Integer.valueOf(i)}));
            }
        } else {
            i2 = -1;
        }
        if (i2 > 0) {
            this.name = str.substring(0, i2);
            this.enterpriseNumber = Integer.parseInt(str.substring(i2 + 1).trim());
        } else {
            this.name = str;
            this.enterpriseNumber = -1;
        }
        this.required = strArr;
        this.optional = strArr2;
    }

    public StructuredDataId(String str, int i, String[] strArr, String[] strArr2) {
        this(str, i, strArr, strArr2, 32);
    }

    public StructuredDataId(String str, int i, String[] strArr, String[] strArr2, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("No structured id name was supplied");
        } else if (str.contains(AT_SIGN)) {
            throw new IllegalArgumentException("Structured id name cannot contain an " + Strings.quote(AT_SIGN));
        } else if (i > 0) {
            this.name = str;
            this.enterpriseNumber = i;
            String str2 = str + AT_SIGN + i;
            if (i2 <= 0 || str2.length() <= i2) {
                this.required = strArr;
                this.optional = strArr2;
                return;
            }
            throw new IllegalArgumentException("Length of id exceeds maximum of " + i2 + " characters: " + str2);
        } else {
            throw new IllegalArgumentException("No enterprise number was supplied");
        }
    }

    public StructuredDataId makeId(StructuredDataId structuredDataId) {
        return structuredDataId == null ? this : makeId(structuredDataId.getName(), structuredDataId.getEnterpriseNumber());
    }

    public StructuredDataId makeId(String str, int i) {
        String[] strArr;
        String[] strArr2;
        if (i <= 0) {
            return this;
        }
        String str2 = this.name;
        if (str2 != null) {
            String[] strArr3 = this.required;
            strArr = this.optional;
            String str3 = str2;
            strArr2 = strArr3;
            str = str3;
        } else {
            strArr = null;
            strArr2 = null;
        }
        return new StructuredDataId(str, i, strArr2, strArr);
    }

    public String[] getRequired() {
        return this.required;
    }

    public String[] getOptional() {
        return this.optional;
    }

    public String getName() {
        return this.name;
    }

    public int getEnterpriseNumber() {
        return this.enterpriseNumber;
    }

    public boolean isReserved() {
        return this.enterpriseNumber <= 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.name.length() + 10);
        formatTo(sb);
        return sb.toString();
    }

    public void formatTo(StringBuilder sb) {
        if (isReserved()) {
            sb.append(this.name);
        } else {
            sb.append(this.name).append(AT_SIGN).append(this.enterpriseNumber);
        }
    }
}
