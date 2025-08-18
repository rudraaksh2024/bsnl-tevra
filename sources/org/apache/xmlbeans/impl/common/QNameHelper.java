package org.apache.xmlbeans.impl.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.soap.SOAPConstants;
import org.apache.xmlbeans.xml.stream.XMLName;

public class QNameHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_NAME_LENGTH = 64;
    public static final String URI_SHA1_PREFIX = "URI_SHA_1_";
    private static final Map WELL_KNOWN_PREFIXES = buildWKP();
    private static final char[] hexdigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static boolean isSafe(int i) {
        if (i >= 97 && i <= 122) {
            return true;
        }
        if (i < 65 || i > 90) {
            return i >= 48 && i <= 57;
        }
        return true;
    }

    private static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static XMLName getXMLName(QName qName) {
        if (qName == null) {
            return null;
        }
        return XMLNameHelper.forLNS(qName.getLocalPart(), qName.getNamespaceURI());
    }

    public static QName forLNS(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        return new QName(str2, str);
    }

    public static QName forLN(String str) {
        return new QName("", str);
    }

    public static QName forPretty(String str, int i) {
        int indexOf = str.indexOf(64, i);
        if (indexOf < 0) {
            return new QName("", str.substring(i));
        }
        return new QName(str.substring(indexOf + 1), str.substring(i, indexOf));
    }

    public static String pretty(QName qName) {
        if (qName == null) {
            return "null";
        }
        if (qName.getNamespaceURI() == null || qName.getNamespaceURI().length() == 0) {
            return qName.getLocalPart();
        }
        return qName.getLocalPart() + "@" + qName.getNamespaceURI();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:25|26) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r7 = new byte[0];
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0069 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String hexsafe(java.lang.String r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r2 = r1
        L_0x0007:
            int r3 = r7.length()
            java.lang.String r4 = "UTF-8"
            if (r2 >= r3) goto L_0x0051
            char r3 = r7.charAt(r2)
            boolean r5 = isSafe(r3)
            if (r5 == 0) goto L_0x001d
            r0.append(r3)
            goto L_0x004e
        L_0x001d:
            int r3 = r2 + 1
            java.lang.String r3 = r7.substring(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            byte[] r3 = r3.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            r4 = r1
        L_0x0028:
            int r5 = r3.length     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            if (r4 >= r5) goto L_0x004e
            r5 = 95
            r0.append(r5)     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            char[] r5 = hexdigits     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            byte r6 = r3[r4]     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            int r6 = r6 >> 4
            r6 = r6 & 15
            char r6 = r5[r6]     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            r0.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            byte r6 = r3[r4]     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            r6 = r6 & 15
            char r5 = r5[r6]     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            r0.append(r5)     // Catch:{ UnsupportedEncodingException -> 0x0049 }
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0049:
            java.lang.String r3 = "_BAD_UTF8_CHAR"
            r0.append(r3)
        L_0x004e:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x0051:
            int r2 = r0.length()
            r3 = 64
            if (r2 > r3) goto L_0x005e
            java.lang.String r7 = r0.toString()
            return r7
        L_0x005e:
            java.lang.String r0 = "SHA"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            byte[] r7 = r7.getBytes(r4)     // Catch:{ UnsupportedEncodingException -> 0x0069 }
            goto L_0x006b
        L_0x0069:
            byte[] r7 = new byte[r1]     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
        L_0x006b:
            byte[] r7 = r0.digest(r7)     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            java.lang.String r2 = "URI_SHA_1_"
            r0.<init>(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
        L_0x0076:
            int r2 = r7.length     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            if (r1 >= r2) goto L_0x0092
            char[] r2 = hexdigits     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            byte r3 = r7[r1]     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            int r3 = r3 >> 4
            r3 = r3 & 15
            char r3 = r2[r3]     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            r0.append(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            byte r3 = r7[r1]     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            r3 = r3 & 15
            char r2 = r2[r3]     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            r0.append(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            int r1 = r1 + 1
            goto L_0x0076
        L_0x0092:
            java.lang.String r7 = r0.toString()     // Catch:{ NoSuchAlgorithmException -> 0x0097 }
            return r7
        L_0x0097:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Using in a JDK without an SHA implementation"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.QNameHelper.hexsafe(java.lang.String):java.lang.String");
    }

    public static String hexsafedir(QName qName) {
        if (qName.getNamespaceURI() == null || qName.getNamespaceURI().length() == 0) {
            return "_nons/" + hexsafe(qName.getLocalPart());
        }
        return hexsafe(qName.getNamespaceURI()) + PackagingURIHelper.FORWARD_SLASH_STRING + hexsafe(qName.getLocalPart());
    }

    private static Map buildWKP() {
        HashMap hashMap = new HashMap();
        hashMap.put("http://www.w3.org/XML/1998/namespace", "xml");
        hashMap.put("http://www.w3.org/2001/XMLSchema", "xs");
        hashMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        hashMap.put("http://schemas.xmlsoap.org/wsdl/", "wsdl");
        hashMap.put("http://schemas.xmlsoap.org/soap/encoding/", "soapenc");
        hashMap.put(SOAPConstants.URI_NS_SOAP_ENVELOPE, "soapenv");
        return Collections.unmodifiableMap(hashMap);
    }

    public static String readable(SchemaType schemaType) {
        return readable(schemaType, WELL_KNOWN_PREFIXES);
    }

    public static String readable(SchemaType schemaType, Map map) {
        if (schemaType.getName() != null) {
            return readable(schemaType.getName(), map);
        }
        if (schemaType.isAttributeType()) {
            return "attribute type " + readable(schemaType.getAttributeTypeAttributeName(), map);
        }
        if (schemaType.isDocumentType()) {
            return "document type " + readable(schemaType.getDocumentElementName(), map);
        }
        if (schemaType.isNoType() || schemaType.getOuterType() == null) {
            return "invalid type";
        }
        SchemaType outerType = schemaType.getOuterType();
        SchemaField containerField = schemaType.getContainerField();
        if (outerType.isAttributeType()) {
            return "type of attribute " + readable(containerField.getName(), map);
        }
        if (outerType.isDocumentType()) {
            return "type of element " + readable(containerField.getName(), map);
        }
        if (containerField != null) {
            if (containerField.isAttribute()) {
                return "type of " + containerField.getName().getLocalPart() + " attribute in " + readable(outerType, map);
            }
            return "type of " + containerField.getName().getLocalPart() + " element in " + readable(outerType, map);
        } else if (outerType.getBaseType() == schemaType) {
            return "base type of " + readable(outerType, map);
        } else {
            if (outerType.getSimpleVariety() == 3) {
                return "item type of " + readable(outerType, map);
            }
            if (outerType.getSimpleVariety() == 2) {
                return "member type " + schemaType.getAnonymousUnionMemberOrdinal() + " of " + readable(outerType, map);
            }
            return "inner type in " + readable(outerType, map);
        }
    }

    public static String readable(QName qName) {
        return readable(qName, WELL_KNOWN_PREFIXES);
    }

    public static String readable(QName qName, Map map) {
        if (qName.getNamespaceURI().length() == 0) {
            return qName.getLocalPart();
        }
        String str = (String) map.get(qName.getNamespaceURI());
        if (str != null) {
            return str + ParameterizedMessage.ERROR_MSG_SEPARATOR + qName.getLocalPart();
        }
        return qName.getLocalPart() + " in namespace " + qName.getNamespaceURI();
    }

    public static String suggestPrefix(String str) {
        String str2 = (String) WELL_KNOWN_PREFIXES.get(str);
        if (str2 != null) {
            return str2;
        }
        int length = str.length();
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf > 0 && lastIndexOf == str.length() - 1) {
            int i = lastIndexOf;
            lastIndexOf = str.lastIndexOf(47, lastIndexOf - 1);
            length = i;
        }
        int i2 = lastIndexOf + 1;
        if (str.startsWith("www.", i2)) {
            i2 += 4;
        }
        while (i2 < length && !XMLChar.isNCNameStart(str.charAt(i2))) {
            i2++;
        }
        int i3 = i2 + 1;
        while (true) {
            if (i3 >= length) {
                break;
            } else if (!XMLChar.isNCName(str.charAt(i3)) || !Character.isLetterOrDigit(str.charAt(i3))) {
                length = i3;
            } else {
                i3++;
            }
        }
        length = i3;
        int i4 = i2 + 3;
        if (str.length() < i4 || !startsWithXml(str, i2)) {
            if (length - i2 > 4) {
                length = (!isVowel(str.charAt(i2 + 2)) || isVowel(str.charAt(i4))) ? i4 : i2 + 4;
            }
            if (length - i2 == 0) {
                return "ns";
            }
            return str.substring(i2, length).toLowerCase(Locale.ROOT);
        } else if (str.length() >= i2 + 4) {
            return "x" + Character.toLowerCase(str.charAt(i4));
        } else {
            return "ns";
        }
    }

    private static boolean startsWithXml(String str, int i) {
        if (str.length() < i + 3) {
            return false;
        }
        if (str.charAt(i) != 'X' && str.charAt(i) != 'x') {
            return false;
        }
        int i2 = i + 1;
        if (str.charAt(i2) != 'M' && str.charAt(i2) != 'm') {
            return false;
        }
        int i3 = i + 2;
        if (str.charAt(i3) == 'L' || str.charAt(i3) == 'l') {
            return true;
        }
        return false;
    }

    public static String namespace(SchemaType schemaType) {
        while (schemaType != null) {
            if (schemaType.getName() != null) {
                return schemaType.getName().getNamespaceURI();
            }
            if (schemaType.getContainerField() != null && schemaType.getContainerField().getName().getNamespaceURI().length() > 0) {
                return schemaType.getContainerField().getName().getNamespaceURI();
            }
            schemaType = schemaType.getOuterType();
        }
        return "";
    }

    public static String getLocalPart(String str) {
        int indexOf = str.indexOf(58);
        return indexOf < 0 ? str : str.substring(indexOf + 1);
    }

    public static String getPrefixPart(String str) {
        int indexOf = str.indexOf(58);
        return indexOf >= 0 ? str.substring(0, indexOf) : "";
    }
}
