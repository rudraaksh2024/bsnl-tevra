package org.apache.poi.openxml4j.opc;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;

public final class PackagePartName implements Comparable<PackagePartName> {
    private static final String RFC3986_PCHAR_AUTHORIZED_SUP = ":@";
    private static final String RFC3986_PCHAR_SUB_DELIMS = "!$&'()*+,;=";
    private static final String RFC3986_PCHAR_UNRESERVED_SUP = "-._~";
    private final boolean isRelationship;
    private final URI partNameURI;

    private static boolean isDigitOrLetter(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    PackagePartName(URI uri, boolean z) throws InvalidFormatException {
        if (z) {
            throwExceptionIfInvalidPartUri(uri);
        } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(uri)) {
            throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
        }
        this.partNameURI = uri;
        this.isRelationship = isRelationshipPartURI(uri);
    }

    PackagePartName(String str, boolean z) throws InvalidFormatException {
        try {
            URI uri = new URI(str);
            if (z) {
                throwExceptionIfInvalidPartUri(uri);
            } else if (!PackagingURIHelper.PACKAGE_ROOT_URI.equals(uri)) {
                throw new OpenXML4JRuntimeException("OCP conformance must be check for ALL part name except special cases : ['/']");
            }
            this.partNameURI = uri;
            this.isRelationship = isRelationshipPartURI(uri);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("partName argmument is not a valid OPC part name !");
        }
    }

    private boolean isRelationshipPartURI(URI uri) {
        if (uri != null) {
            return uri.getPath().matches("^.*/_rels/.*\\.rels$");
        }
        throw new IllegalArgumentException("partUri");
    }

    public boolean isRelationshipPartURI() {
        return this.isRelationship;
    }

    private static void throwExceptionIfInvalidPartUri(URI uri) throws InvalidFormatException {
        if (uri != null) {
            throwExceptionIfEmptyURI(uri);
            throwExceptionIfAbsoluteUri(uri);
            throwExceptionIfPartNameNotStartsWithForwardSlashChar(uri);
            throwExceptionIfPartNameEndsWithForwardSlashChar(uri);
            throwExceptionIfPartNameHaveInvalidSegments(uri);
            return;
        }
        throw new IllegalArgumentException("partUri");
    }

    private static void throwExceptionIfEmptyURI(URI uri) throws InvalidFormatException {
        if (uri != null) {
            String path = uri.getPath();
            if (path.length() == 0 || (path.length() == 1 && path.charAt(0) == '/')) {
                throw new InvalidFormatException("A part name shall not be empty [M1.1]: " + uri.getPath());
            }
            return;
        }
        throw new IllegalArgumentException("partURI");
    }

    private static void throwExceptionIfPartNameHaveInvalidSegments(URI uri) throws InvalidFormatException {
        if (uri != null) {
            String[] split = uri.toASCIIString().replaceFirst("^/", "").split(PackagingURIHelper.FORWARD_SLASH_STRING);
            if (split.length >= 1) {
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String str = split[i];
                    if (str == null || str.isEmpty()) {
                        throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + uri.getPath());
                    } else if (str.endsWith(".")) {
                        throw new InvalidFormatException("A segment shall not end with a dot ('.') character [M1.9]: " + uri.getPath());
                    } else if (!str.replaceAll("\\\\.", "").isEmpty()) {
                        checkPCharCompliance(str);
                        i++;
                    } else {
                        throw new InvalidFormatException("A segment shall include at least one non-dot character. [M1.10]: " + uri.getPath());
                    }
                }
                return;
            }
            throw new InvalidFormatException("A part name shall not have empty segments [M1.3]: " + uri.getPath());
        }
        throw new IllegalArgumentException("partUri");
    }

    private static void checkPCharCompliance(String str) throws InvalidFormatException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (!isDigitOrLetter(charAt) && RFC3986_PCHAR_UNRESERVED_SUP.indexOf(charAt) <= -1 && RFC3986_PCHAR_AUTHORIZED_SUP.indexOf(charAt) <= -1 && RFC3986_PCHAR_SUB_DELIMS.indexOf(charAt) <= -1) {
                if (charAt == '%') {
                    if (length - i >= 2) {
                        int i2 = i + 1;
                        if (isHexDigit(str.charAt(i2))) {
                            int i3 = i + 2;
                            if (isHexDigit(str.charAt(i3))) {
                                char parseInt = (char) Integer.parseInt(str.substring(i2, i + 3), 16);
                                if (parseInt == '/' || parseInt == '\\') {
                                    throw new InvalidFormatException("A segment shall not contain percent-encoded forward slash ('/'), or backward slash ('') characters. [M1.7]");
                                } else if (isDigitOrLetter(parseInt) || RFC3986_PCHAR_UNRESERVED_SUP.indexOf(parseInt) > -1) {
                                    throw new InvalidFormatException("A segment shall not contain percent-encoded unreserved characters. [M1.8]");
                                } else {
                                    i = i3;
                                }
                            }
                        }
                    }
                    throw new InvalidFormatException("The segment " + str + " contain invalid encoded character !");
                }
                throw new InvalidFormatException("A segment shall not hold any characters other than pchar characters. [M1.6]");
            }
            i++;
        }
    }

    private static void throwExceptionIfPartNameNotStartsWithForwardSlashChar(URI uri) throws InvalidFormatException {
        String path = uri.getPath();
        if (path.length() > 0 && path.charAt(0) != '/') {
            throw new InvalidFormatException("A part name shall start with a forward slash ('/') character [M1.4]: " + uri.getPath());
        }
    }

    private static void throwExceptionIfPartNameEndsWithForwardSlashChar(URI uri) throws InvalidFormatException {
        String path = uri.getPath();
        if (path.length() > 0 && path.charAt(path.length() - 1) == '/') {
            throw new InvalidFormatException("A part name shall not have a forward slash as the last character [M1.5]: " + uri.getPath());
        }
    }

    private static void throwExceptionIfAbsoluteUri(URI uri) throws InvalidFormatException {
        if (uri.isAbsolute()) {
            throw new InvalidFormatException("Absolute URI forbidden: " + uri);
        }
    }

    public int compareTo(PackagePartName packagePartName) {
        return compare(this, packagePartName);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r2.lastIndexOf(46);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getExtension() {
        /*
            r2 = this;
            java.net.URI r2 = r2.partNameURI
            java.lang.String r2 = r2.getPath()
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x001c
            r0 = 46
            int r0 = r2.lastIndexOf(r0)
            r1 = -1
            if (r0 <= r1) goto L_0x001c
            int r0 = r0 + 1
            java.lang.String r2 = r2.substring(r0)
            return r2
        L_0x001c:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.openxml4j.opc.PackagePartName.getExtension():java.lang.String");
    }

    public String getName() {
        return getURI().toASCIIString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof PackagePartName) && compare(getName(), ((PackagePartName) obj).getName()) == 0;
    }

    public int hashCode() {
        return getName().toLowerCase(Locale.ROOT).hashCode();
    }

    public String toString() {
        return getName();
    }

    public URI getURI() {
        return this.partNameURI;
    }

    public static int compare(PackagePartName packagePartName, PackagePartName packagePartName2) {
        String str = null;
        String name = packagePartName == null ? null : packagePartName.getName();
        if (packagePartName2 != null) {
            str = packagePartName2.getName();
        }
        return compare(name, str);
    }

    public static int compare(String str, String str2) {
        int i = 0;
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        if (str.equalsIgnoreCase(str2)) {
            return 0;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String lowerCase2 = str2.toLowerCase(Locale.ROOT);
        int length = lowerCase.length();
        int length2 = lowerCase2.length();
        int i2 = 0;
        while (i < length && i2 < length2) {
            int i3 = i + 1;
            char charAt = lowerCase.charAt(i);
            int i4 = i2 + 1;
            char charAt2 = lowerCase2.charAt(i2);
            if (Character.isDigit(charAt) && Character.isDigit(charAt2)) {
                int i5 = i3 - 1;
                while (i3 < length && Character.isDigit(lowerCase.charAt(i3))) {
                    i3++;
                }
                int i6 = i4 - 1;
                while (i4 < length2 && Character.isDigit(lowerCase2.charAt(i4))) {
                    i4++;
                }
                int compareTo = new BigInteger(lowerCase.substring(i5, i3)).compareTo(new BigInteger(lowerCase2.substring(i6, i4)));
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (charAt != charAt2) {
                return charAt - charAt2;
            }
            i = i3;
            i2 = i4;
        }
        return length - length2;
    }
}
