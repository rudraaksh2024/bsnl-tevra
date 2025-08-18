package org.apache.xmlbeans.impl.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.xmlbeans.XmlErrorCodes;

public class NameUtil {
    public static final char AYAH = '۝';
    public static final char COLON = ':';
    private static final int DIGIT = 2;
    public static final char DOT = '·';
    public static final char ELHIZB = '۞';
    public static final char HYPHEN = '-';
    private static final String JAVA_NS_PREFIX = "java:";
    private static final int LOWER = 5;
    private static final int MARK = 3;
    private static final int NOCASE = 6;
    public static final char PERIOD = '.';
    private static final int PUNCT = 1;
    private static final int START = 0;
    public static final char TELEIA = '·';
    private static final int UPPER = 4;
    public static final char USCORE = '_';
    private static final Set<String> extraWords = new HashSet(Arrays.asList(new String[]{Complex.DEFAULT_SUFFIX, TypedValues.AttributesType.S_TARGET, "org", "com"}));
    private static final Set<String> javaNames = new HashSet(Arrays.asList(new String[]{"CharSequence", "Cloneable", "Comparable", "Runnable", "Boolean", "Byte", "Character", "Class", "ClassLoader", "Compiler", "Double", "Float", "InheritableThreadLocal", "Integer", "Long", "Math", "Number", "Object", ExtractorFactory.OOXML_PACKAGE, "Process", "Runtime", "RuntimePermission", "SecurityManager", "Short", "StackTraceElement", "StrictMath", "String", "StringBuffer", "System", "Thread", "ThreadGroup", "ThreadLocal", "Throwable", "Void", "ArithmeticException", "ArrayIndexOutOfBoundsException", "ArrayStoreException", "ClassCastException", "ClassNotFoundException", "CloneNotSupportedException", "Exception", "IllegalAccessException", "IllegalArgumentException", "IllegalMonitorStateException", "IllegalStateException", "IllegalThreadStateException", "IndexOutOfBoundsException", "InstantiationException", "InterruptedException", "NegativeArraySizeException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException", "RuntimeException", "SecurityException", "StringIndexOutOfBoundsException", "UnsupportedOperationException", "AbstractMethodError", "AssertionError", "ClassCircularityError", "ClassFormatError", "Error", "ExceptionInInitializerError", "IllegalAccessError", "IncompatibleClassChangeError", "InstantiationError", "InternalError", "LinkageError", "NoClassDefFoundError", "NoSuchFieldError", "NoSuchMethodError", "OutOfMemoryError", "StackOverflowError", "ThreadDeath", "UnknownError", "UnsatisfiedLinkError", "UnsupportedClassVersionError", "VerifyError", "VirtualMachineError", "BigInteger", "BigDecimal", "Enum", "Date", "GDate", "GDuration", XmlErrorCodes.QNAME, "List", "XmlObject", "XmlCursor", "XmlBeans", "SchemaType"}));
    private static final Set<String> javaWords = new HashSet(Arrays.asList(new String[]{"assert", "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", XmlErrorCodes.DOUBLE, "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", XmlErrorCodes.INT, "interface", XmlErrorCodes.LONG, "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "threadsafe", "throw", "throws", "transient", "true", "try", "void", "volatile", "while"}));

    private static boolean isLetter(int i) {
        return i == 4 || i == 5 || i == 6;
    }

    public static boolean isPunctuation(char c, boolean z) {
        return c == '-' || c == '.' || c == ':' || c == 183 || (c == '_' && !z) || c == 903 || c == 1757 || c == 1758;
    }

    private static boolean isUriAlphaChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isUriSchemeChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '-' || c == '.' || c == '+');
    }

    public static boolean isValidJavaIdentifier(String str) {
        if (str != null) {
            int length = str.length();
            if (length == 0 || javaWords.contains(str) || !Character.isJavaIdentifierStart(str.charAt(0))) {
                return false;
            }
            for (int i = 1; i < length; i++) {
                if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalArgumentException("id cannot be null");
    }

    public static String getClassNameFromQName(QName qName) {
        return getClassNameFromQName(qName, false);
    }

    public static String getClassNameFromQName(QName qName, boolean z) {
        String upperCamelCase = upperCamelCase(qName.getLocalPart(), z);
        String packageFromNamespace = getPackageFromNamespace(qName.getNamespaceURI(), z);
        return packageFromNamespace != null ? packageFromNamespace + "." + upperCamelCase : upperCamelCase;
    }

    public static String getNamespaceFromPackage(Class<?> cls) {
        String str;
        for (Class<?> cls2 = cls; cls2.isArray(); cls2 = cls2.getComponentType()) {
        }
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            str = "";
        } else {
            str = name.substring(0, lastIndexOf);
        }
        return JAVA_NS_PREFIX + str;
    }

    private static int findSchemeColon(String str) {
        int length = str.length();
        if (length == 0 || !isUriAlphaChar(str.charAt(0))) {
            return -1;
        }
        int i = 1;
        while (i < length && isUriSchemeChar(str.charAt(i))) {
            i++;
        }
        if (i == length || str.charAt(i) != ':') {
            return -1;
        }
        while (i < length && str.charAt(i) == ':') {
            i++;
        }
        return i - 1;
    }

    private static String jls77String(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isJavaIdentifierPart(sb.charAt(i)) || '$' == sb.charAt(i)) {
                sb.setCharAt(i, USCORE);
            }
        }
        if (sb.length() == 0 || !Character.isJavaIdentifierStart(sb.charAt(0))) {
            sb.insert(0, USCORE);
        }
        if (isJavaReservedWord(str)) {
            sb.append(USCORE);
        }
        return sb.toString();
    }

    private static List<String> splitDNS(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        for (int lastIndexOf = str.lastIndexOf(46); lastIndexOf != -1; lastIndexOf--) {
            if (str.charAt(lastIndexOf) == '.') {
                arrayList.add(jls77String(str.substring(lastIndexOf + 1, length)));
                length = lastIndexOf;
            }
        }
        arrayList.add(jls77String(str.substring(0, length)));
        if (arrayList.size() >= 3 && ((String) arrayList.get(arrayList.size() - 1)).toLowerCase(Locale.ROOT).equals("www")) {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    private static String processFilename(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0) {
            return str;
        }
        int i = lastIndexOf + 1;
        return (i + 2 == str.length() || i + 3 == str.length() || "html".equals(str.substring(i).toLowerCase(Locale.ROOT))) ? str.substring(0, lastIndexOf) : str;
    }

    public static String getPackageFromNamespace(String str) {
        return getPackageFromNamespace(str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005c A[LOOP:2: B:18:0x005c->B:21:0x0064, LOOP_START, PHI: r6 
      PHI: (r6v2 int) = (r6v1 int), (r6v4 int) binds: [B:17:0x005b, B:21:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPackageFromNamespace(java.lang.String r9, boolean r10) {
        /*
            java.lang.String r0 = "noNamespace"
            if (r9 == 0) goto L_0x00f2
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000c
            goto L_0x00f2
        L_0x000c:
            int r1 = r9.length()
            int r2 = findSchemeColon(r9)
            int r3 = r1 + -1
            r4 = 0
            r5 = 1
            if (r2 != r3) goto L_0x0028
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r9 = r9.substring(r4, r2)
            r1.add(r9)
            goto L_0x00a3
        L_0x0028:
            if (r2 < 0) goto L_0x0046
            java.lang.String r3 = r9.substring(r4, r2)
            java.lang.String r6 = "java"
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x0046
            int r2 = r2 + r5
            java.lang.String r9 = r9.substring(r2)
            java.lang.String r1 = "\\."
            java.lang.String[] r9 = r9.split(r1)
            java.util.List r1 = java.util.Arrays.asList(r9)
            goto L_0x00a3
        L_0x0046:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r2 = r2 + r5
        L_0x004c:
            if (r2 >= r1) goto L_0x006f
        L_0x004e:
            char r6 = r9.charAt(r2)
            r7 = 47
            if (r6 != r7) goto L_0x005b
            int r2 = r2 + 1
            if (r2 < r1) goto L_0x004e
            goto L_0x006f
        L_0x005b:
            r6 = r2
        L_0x005c:
            char r8 = r9.charAt(r6)
            if (r8 == r7) goto L_0x0066
            int r6 = r6 + 1
            if (r6 < r1) goto L_0x005c
        L_0x0066:
            java.lang.String r2 = r9.substring(r2, r6)
            r3.add(r2)
            r2 = r6
            goto L_0x004c
        L_0x006f:
            int r9 = r3.size()
            if (r9 <= r5) goto L_0x008c
            int r9 = r3.size()
            int r9 = r9 - r5
            int r1 = r3.size()
            int r1 = r1 - r5
            java.lang.Object r1 = r3.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r1 = processFilename(r1)
            r3.set(r9, r1)
        L_0x008c:
            int r9 = r3.size()
            if (r9 <= 0) goto L_0x00a2
            java.lang.Object r9 = r3.get(r4)
            java.lang.String r9 = (java.lang.String) r9
            java.util.List r9 = splitDNS(r9)
            r3.remove(r4)
            r3.addAll(r4, r9)
        L_0x00a2:
            r1 = r3
        L_0x00a3:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x00ac:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00cf
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r2 = lowerCamelCase(r2, r10, r5)
            java.lang.String r2 = nonJavaKeyword(r2)
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x00ac
            r9.append(r2)
            r2 = 46
            r9.append(r2)
            goto L_0x00ac
        L_0x00cf:
            int r1 = r9.length()
            if (r1 != 0) goto L_0x00d6
            return r0
        L_0x00d6:
            if (r10 == 0) goto L_0x00e8
            int r10 = r9.length()
            int r10 = r10 - r5
            java.lang.String r9 = r9.substring(r4, r10)
            java.util.Locale r10 = java.util.Locale.ROOT
            java.lang.String r9 = r9.toLowerCase(r10)
            return r9
        L_0x00e8:
            int r10 = r9.length()
            int r10 = r10 - r5
            java.lang.String r9 = r9.substring(r4, r10)
            return r9
        L_0x00f2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.NameUtil.getPackageFromNamespace(java.lang.String, boolean):java.lang.String");
    }

    public static void main(String[] strArr) {
        for (String upperCaseUnderbar : strArr) {
            System.out.println(upperCaseUnderbar(upperCaseUnderbar));
        }
    }

    public static String upperCaseUnderbar(String str) {
        StringBuilder sb = new StringBuilder();
        List<String> splitWords = splitWords(str, false);
        int size = splitWords.size() - 1;
        if (size >= 0 && !Character.isJavaIdentifierStart(splitWords.get(0).charAt(0))) {
            sb.append("X_");
        }
        for (int i = 0; i < size; i++) {
            sb.append(splitWords.get(i));
            sb.append(USCORE);
        }
        if (size >= 0) {
            sb.append(splitWords.get(size));
        }
        int length = sb.length();
        for (int i2 = 0; i2 < length; i2++) {
            sb.setCharAt(i2, Character.toUpperCase(sb.charAt(i2)));
        }
        return sb.toString();
    }

    public static String upperCamelCase(String str) {
        return upperCamelCase(str, false);
    }

    public static String upperCamelCase(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        List<String> splitWords = splitWords(str, z);
        if (splitWords.size() > 0) {
            if (!Character.isJavaIdentifierStart(splitWords.get(0).charAt(0))) {
                sb.append("X");
            }
            for (String append : splitWords) {
                sb.append(append);
            }
        }
        return sb.toString();
    }

    public static String lowerCamelCase(String str) {
        return lowerCamelCase(str, false, true);
    }

    public static String lowerCamelCase(String str, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        List<String> splitWords = splitWords(str, z);
        if (splitWords.size() > 0) {
            String lowerCase = splitWords.get(0).toLowerCase(Locale.ROOT);
            if (!Character.isJavaIdentifierStart(lowerCase.charAt(0)) && z2) {
                sb.append("x");
            }
            sb.append(lowerCase);
            Iterator<String> it = splitWords.iterator();
            it.next();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }
        return sb.toString();
    }

    public static String upperCaseFirstLetter(String str) {
        if (str.length() == 0 || Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    private static void addCapped(List<String> list, String str) {
        if (str.length() > 0) {
            list.add(upperCaseFirstLetter(str));
        }
    }

    public static List<String> splitWords(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int charClass = getCharClass(str.charAt(i), z);
            if (i3 != 1 && charClass == 1) {
                addCapped(arrayList, str.substring(i2, i));
                while (true) {
                    int charClass2 = getCharClass(str.charAt(i), z);
                    if (charClass2 != 1) {
                        i3 = charClass2;
                        i2 = i;
                        break;
                    }
                    i++;
                    if (i >= length) {
                        return arrayList;
                    }
                }
            } else {
                if ((i3 == 2) != (charClass == 2) || ((i3 == 5 && charClass != 5) || isLetter(i3) != isLetter(charClass))) {
                    addCapped(arrayList, str.substring(i2, i));
                    i2 = i;
                } else if (i3 == 4 && charClass == 5 && i > i2 + 1) {
                    int i4 = i - 1;
                    addCapped(arrayList, str.substring(i2, i4));
                    i2 = i4;
                }
                i3 = charClass;
            }
            i++;
        }
        addCapped(arrayList, str.substring(i2));
        return arrayList;
    }

    public static int getCharClass(char c, boolean z) {
        if (isPunctuation(c, z)) {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        if (Character.isUpperCase(c)) {
            return 4;
        }
        if (Character.isLowerCase(c)) {
            return 5;
        }
        if (Character.isLetter(c)) {
            return 6;
        }
        if (Character.isJavaIdentifierPart(c)) {
            return 3;
        }
        return 1;
    }

    public static String nonJavaKeyword(String str) {
        return isJavaReservedWord(str) ? "x" + str : str;
    }

    public static String nonExtraKeyword(String str) {
        return isExtraReservedWord(str) ? str + "Value" : str;
    }

    public static String nonJavaCommonClassName(String str) {
        return isJavaCommonClassName(str) ? "X" + str : str;
    }

    private static boolean isJavaReservedWord(String str) {
        return javaWords.contains(str.toLowerCase(Locale.ROOT));
    }

    private static boolean isExtraReservedWord(String str) {
        return extraWords.contains(str.toLowerCase(Locale.ROOT));
    }

    public static boolean isJavaCommonClassName(String str) {
        return javaNames.contains(str);
    }
}
