package org.apache.logging.log4j.message;

import java.util.Arrays;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;

public class ParameterizedMessage implements Message, StringBuilderFormattable {
    private static final int DEFAULT_STRING_BUILDER_SIZE = 255;
    public static final String ERROR_MSG_SEPARATOR = ":";
    public static final String ERROR_PREFIX = "[!!!";
    public static final String ERROR_SEPARATOR = "=>";
    public static final String ERROR_SUFFIX = "!!!]";
    private static final int HASHVAL = 31;
    public static final String RECURSION_PREFIX = "[...";
    public static final String RECURSION_SUFFIX = "...]";
    private static final long serialVersionUID = -665975803997290697L;
    private static ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<>();
    private transient Object[] argArray;
    private String formattedMessage;
    private int[] indices;
    private String messagePattern;
    private transient Throwable throwable;
    private int usedCount;

    @Deprecated
    public ParameterizedMessage(String str, String[] strArr, Throwable th) {
        this.argArray = strArr;
        this.throwable = th;
        init(str);
    }

    public ParameterizedMessage(String str, Object[] objArr, Throwable th) {
        this.argArray = objArr;
        this.throwable = th;
        init(str);
    }

    public ParameterizedMessage(String str, Object... objArr) {
        this.argArray = objArr;
        init(str);
    }

    public ParameterizedMessage(String str, Object obj) {
        this(str, obj);
    }

    public ParameterizedMessage(String str, Object obj, Object obj2) {
        this(str, obj, obj2);
    }

    private void init(String str) {
        int i;
        this.messagePattern = str;
        int i2 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.length() >> 1;
        }
        int[] iArr = new int[Math.max(1, i)];
        this.indices = iArr;
        int countArgumentPlaceholders2 = ParameterFormatter.countArgumentPlaceholders2(str, iArr);
        initThrowable(this.argArray, countArgumentPlaceholders2);
        Object[] objArr = this.argArray;
        if (objArr != null) {
            i2 = objArr.length;
        }
        this.usedCount = Math.min(countArgumentPlaceholders2, i2);
    }

    private void initThrowable(Object[] objArr, int i) {
        int length;
        if (objArr != null && i < (length = objArr.length) && this.throwable == null) {
            Throwable th = objArr[length - 1];
            if (th instanceof Throwable) {
                this.throwable = th;
            }
        }
    }

    public String getFormat() {
        return this.messagePattern;
    }

    public Object[] getParameters() {
        return this.argArray;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            StringBuilder threadLocalStringBuilder2 = getThreadLocalStringBuilder();
            formatTo(threadLocalStringBuilder2);
            this.formattedMessage = threadLocalStringBuilder2.toString();
            StringBuilders.trimToMaxSize(threadLocalStringBuilder2, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        }
        return this.formattedMessage;
    }

    private static StringBuilder getThreadLocalStringBuilder() {
        StringBuilder sb = threadLocalStringBuilder.get();
        if (sb == null) {
            sb = new StringBuilder(255);
            threadLocalStringBuilder.set(sb);
        }
        sb.setLength(0);
        return sb;
    }

    public void formatTo(StringBuilder sb) {
        String str = this.formattedMessage;
        if (str != null) {
            sb.append(str);
            return;
        }
        int[] iArr = this.indices;
        if (iArr[0] < 0) {
            ParameterFormatter.formatMessage(sb, this.messagePattern, this.argArray, this.usedCount);
        } else {
            ParameterFormatter.formatMessage2(sb, this.messagePattern, this.argArray, this.usedCount, iArr);
        }
    }

    public static String format(String str, Object[] objArr) {
        return ParameterFormatter.format(str, objArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParameterizedMessage parameterizedMessage = (ParameterizedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? parameterizedMessage.messagePattern == null : str.equals(parameterizedMessage.messagePattern)) {
            return Arrays.equals(this.argArray, parameterizedMessage.argArray);
        }
        return false;
    }

    public int hashCode() {
        String str = this.messagePattern;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Object[] objArr = this.argArray;
        if (objArr != null) {
            i = Arrays.hashCode(objArr);
        }
        return hashCode + i;
    }

    public static int countArgumentPlaceholders(String str) {
        return ParameterFormatter.countArgumentPlaceholders(str);
    }

    public static String deepToString(Object obj) {
        return ParameterFormatter.deepToString(obj);
    }

    public static String identityToString(Object obj) {
        return ParameterFormatter.identityToString(obj);
    }

    public String toString() {
        return "ParameterizedMessage[messagePattern=" + this.messagePattern + ", stringArgs=" + Arrays.toString(this.argArray) + ", throwable=" + this.throwable + ']';
    }
}
