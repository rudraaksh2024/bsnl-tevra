package org.apache.poi.openxml4j.opc;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import kotlin.UByte;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

public final class PackagingURIHelper {
    public static final PackagePartName CORE_PROPERTIES_PART_NAME;
    public static final URI CORE_PROPERTIES_URI;
    public static final char FORWARD_SLASH_CHAR = '/';
    public static final String FORWARD_SLASH_STRING = "/";
    private static final Logger LOG = LogManager.getLogger((Class<?>) PackagingURIHelper.class);
    public static final String PACKAGE_CORE_PROPERTIES_NAME = "core.xml";
    public static final String PACKAGE_PROPERTIES_SEGMENT_NAME = "docProps";
    public static final PackagePartName PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
    public static final URI PACKAGE_RELATIONSHIPS_ROOT_URI;
    public static final PackagePartName PACKAGE_ROOT_PART_NAME;
    public static final URI PACKAGE_ROOT_URI;
    public static final String RELATIONSHIP_PART_EXTENSION_NAME = ".rels";
    public static final String RELATIONSHIP_PART_SEGMENT_NAME = "_rels";
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Pattern missingAuthPattern = Pattern.compile("\\w+://");
    private static URI packageRootUri;

    static {
        URI uri;
        URI uri2;
        URI uri3;
        PackagePartName packagePartName;
        PackagePartName packagePartName2;
        PackagePartName packagePartName3 = null;
        try {
            uri2 = new URI(FORWARD_SLASH_STRING);
            try {
                uri = new URI("/_rels/.rels");
                try {
                    packageRootUri = new URI(FORWARD_SLASH_STRING);
                    uri3 = new URI("/docProps/core.xml");
                } catch (URISyntaxException unused) {
                }
            } catch (URISyntaxException unused2) {
                uri = null;
                uri3 = null;
                PACKAGE_ROOT_URI = uri2;
                PACKAGE_RELATIONSHIPS_ROOT_URI = uri;
                CORE_PROPERTIES_URI = uri3;
                packagePartName = createPartName(uri);
                try {
                    packagePartName2 = createPartName(uri3);
                    packagePartName3 = new PackagePartName(uri2, false);
                } catch (InvalidFormatException unused3) {
                    packagePartName2 = null;
                }
                PACKAGE_RELATIONSHIPS_ROOT_PART_NAME = packagePartName;
                CORE_PROPERTIES_PART_NAME = packagePartName2;
                PACKAGE_ROOT_PART_NAME = packagePartName3;
            }
        } catch (URISyntaxException unused4) {
            uri2 = null;
            uri = null;
            uri3 = null;
            PACKAGE_ROOT_URI = uri2;
            PACKAGE_RELATIONSHIPS_ROOT_URI = uri;
            CORE_PROPERTIES_URI = uri3;
            packagePartName = createPartName(uri);
            packagePartName2 = createPartName(uri3);
            packagePartName3 = new PackagePartName(uri2, false);
            PACKAGE_RELATIONSHIPS_ROOT_PART_NAME = packagePartName;
            CORE_PROPERTIES_PART_NAME = packagePartName2;
            PACKAGE_ROOT_PART_NAME = packagePartName3;
        }
        PACKAGE_ROOT_URI = uri2;
        PACKAGE_RELATIONSHIPS_ROOT_URI = uri;
        CORE_PROPERTIES_URI = uri3;
        try {
            packagePartName = createPartName(uri);
            packagePartName2 = createPartName(uri3);
            try {
                packagePartName3 = new PackagePartName(uri2, false);
            } catch (InvalidFormatException unused5) {
            }
        } catch (InvalidFormatException unused6) {
            packagePartName2 = null;
            packagePartName = null;
        }
        PACKAGE_RELATIONSHIPS_ROOT_PART_NAME = packagePartName;
        CORE_PROPERTIES_PART_NAME = packagePartName2;
        PACKAGE_ROOT_PART_NAME = packagePartName3;
    }

    public static URI getPackageRootUri() {
        return packageRootUri;
    }

    public static boolean isRelationshipPartURI(URI uri) {
        if (uri != null) {
            return uri.getPath().matches(".*_rels.*.rels$");
        }
        throw new IllegalArgumentException("partUri");
    }

    public static String getFilename(URI uri) {
        if (uri == null) {
            return "";
        }
        String path = uri.getPath();
        int length = path.length();
        int i = length;
        do {
            i--;
            if (i < 0) {
                return "";
            }
        } while (path.charAt(i) != '/');
        return path.substring(i + 1, length);
    }

    public static String getFilenameWithoutExtension(URI uri) {
        String filename = getFilename(uri);
        int lastIndexOf = filename.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return filename;
        }
        return filename.substring(0, lastIndexOf);
    }

    public static URI getPath(URI uri) {
        if (uri != null) {
            String path = uri.getPath();
            int length = path.length();
            do {
                length--;
                if (length >= 0) {
                }
            } while (path.charAt(length) != '/');
            try {
                return new URI(path.substring(0, length));
            } catch (URISyntaxException unused) {
            }
        }
        return null;
    }

    public static URI combine(URI uri, URI uri2) {
        try {
            return new URI(combine(uri.getPath(), uri2.getPath()));
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Prefix and suffix can't be combine !");
        }
    }

    public static String combine(String str, String str2) {
        if (!str.endsWith(FORWARD_SLASH_STRING) && !str2.startsWith(FORWARD_SLASH_STRING)) {
            return str + '/' + str2;
        }
        return str2.startsWith(FORWARD_SLASH_STRING) ^ str.endsWith(FORWARD_SLASH_STRING) ? str + str2 : "";
    }

    public static URI relativizeURI(URI uri, URI uri2, boolean z) {
        StringBuilder sb = new StringBuilder();
        String[] split = uri.getPath().split(FORWARD_SLASH_STRING, -1);
        String[] split2 = uri2.getPath().split(FORWARD_SLASH_STRING, -1);
        if (split.length == 0) {
            throw new IllegalArgumentException("Can't relativize an empty source URI !");
        } else if (split2.length != 0) {
            if (uri.toString().equals(FORWARD_SLASH_STRING)) {
                String path = uri2.getPath();
                if (!z || path.length() <= 0 || path.charAt(0) != '/') {
                    return uri2;
                }
                try {
                    return new URI(path.substring(1));
                } catch (Exception e) {
                    LOG.atWarn().withThrowable(e).log("Failed to relativize");
                    return null;
                }
            } else {
                int i = 0;
                int i2 = 0;
                while (i < split.length && i < split2.length && split[i].equals(split2[i])) {
                    i2++;
                    i++;
                }
                if ((i2 == 0 || i2 == 1) && split[0].isEmpty() && split2[0].isEmpty()) {
                    for (int i3 = 0; i3 < split.length - 2; i3++) {
                        sb.append("../");
                    }
                    for (int i4 = 0; i4 < split2.length; i4++) {
                        if (!split2[i4].isEmpty()) {
                            sb.append(split2[i4]);
                            if (i4 != split2.length - 1) {
                                sb.append(FORWARD_SLASH_STRING);
                            }
                        }
                    }
                    try {
                        return new URI(sb.toString());
                    } catch (Exception e2) {
                        LOG.atWarn().withThrowable(e2).log("Failed to relativize");
                        return null;
                    }
                } else {
                    if (i2 != split.length || i2 != split2.length) {
                        if (i2 == 1) {
                            sb.append(FORWARD_SLASH_STRING);
                        } else {
                            for (int i5 = i2; i5 < split.length - 1; i5++) {
                                sb.append("../");
                            }
                        }
                        while (i2 < split2.length) {
                            if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '/') {
                                sb.append(FORWARD_SLASH_STRING);
                            }
                            sb.append(split2[i2]);
                            i2++;
                        }
                    } else if (uri.equals(uri2)) {
                        sb.append(split[split.length - 1]);
                    }
                    String rawFragment = uri2.getRawFragment();
                    if (rawFragment != null) {
                        sb.append("#").append(rawFragment);
                    }
                    try {
                        return new URI(sb.toString());
                    } catch (Exception e3) {
                        LOG.atWarn().withThrowable(e3).log("Failed to relativize");
                        return null;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Can't relativize an empty target URI !");
        }
    }

    public static URI relativizeURI(URI uri, URI uri2) {
        return relativizeURI(uri, uri2, false);
    }

    public static URI resolvePartUri(URI uri, URI uri2) {
        if (uri == null || uri.isAbsolute()) {
            throw new IllegalArgumentException("sourcePartUri invalid - " + uri);
        } else if (uri2 != null && !uri2.isAbsolute()) {
            return uri.resolve(uri2);
        } else {
            throw new IllegalArgumentException("targetUri invalid - " + uri2);
        }
    }

    public static URI getURIFromPath(String str) {
        try {
            return toURI(str);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("path");
        }
    }

    public static URI getSourcePartUriFromRelationshipPartUri(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Must not be null");
        } else if (!isRelationshipPartURI(uri)) {
            throw new IllegalArgumentException("Must be a relationship part");
        } else if (uri.compareTo(PACKAGE_RELATIONSHIPS_ROOT_URI) == 0) {
            return PACKAGE_ROOT_URI;
        } else {
            String path = uri.getPath();
            String filenameWithoutExtension = getFilenameWithoutExtension(uri);
            String substring = path.substring(0, (path.length() - filenameWithoutExtension.length()) - 5);
            return getURIFromPath(combine(substring.substring(0, (substring.length() - 5) - 1), filenameWithoutExtension));
        }
    }

    public static PackagePartName createPartName(URI uri) throws InvalidFormatException {
        if (uri != null) {
            return new PackagePartName(uri, true);
        }
        throw new IllegalArgumentException("partName");
    }

    public static PackagePartName createPartName(String str) throws InvalidFormatException {
        try {
            return createPartName(toURI(str));
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public static PackagePartName createPartName(String str, PackagePart packagePart) throws InvalidFormatException {
        try {
            return createPartName(resolvePartUri(packagePart.getPartName().getURI(), new URI(str)));
        } catch (URISyntaxException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    public static PackagePartName createPartName(URI uri, PackagePart packagePart) throws InvalidFormatException {
        return createPartName(resolvePartUri(packagePart.getPartName().getURI(), uri));
    }

    public static boolean isValidPartName(URI uri) {
        if (uri != null) {
            try {
                createPartName(uri);
                return true;
            } catch (Exception unused) {
                return false;
            }
        } else {
            throw new IllegalArgumentException("partUri");
        }
    }

    public static String decodeURI(URI uri) {
        StringBuilder sb = new StringBuilder(64);
        String aSCIIString = uri.toASCIIString();
        int length = aSCIIString.length();
        int i = 0;
        while (i < length) {
            char charAt = aSCIIString.charAt(i);
            if (charAt != '%') {
                sb.append(charAt);
            } else if (length - i >= 2) {
                sb.append((char) Integer.parseInt(aSCIIString.substring(i + 1, i + 3), 16));
                i += 2;
            } else {
                throw new IllegalArgumentException("The uri " + aSCIIString + " contain invalid encoded character !");
            }
            i++;
        }
        return sb.toString();
    }

    public static PackagePartName getRelationshipPartName(PackagePartName packagePartName) {
        if (packagePartName == null) {
            throw new IllegalArgumentException("partName");
        } else if (PACKAGE_ROOT_URI.getPath().equals(packagePartName.getURI().getPath())) {
            return PACKAGE_RELATIONSHIPS_ROOT_PART_NAME;
        } else {
            if (!packagePartName.isRelationshipPartURI()) {
                String path = packagePartName.getURI().getPath();
                String filename = getFilename(packagePartName.getURI());
                try {
                    return createPartName(combine(combine(path.substring(0, path.length() - filename.length()), RELATIONSHIP_PART_SEGMENT_NAME), filename) + RELATIONSHIP_PART_EXTENSION_NAME);
                } catch (InvalidFormatException unused) {
                    return null;
                }
            } else {
                throw new InvalidOperationException("Can't be a relationship part");
            }
        }
    }

    public static URI toURI(String str) throws URISyntaxException {
        if (str.contains("\\")) {
            str = str.replace(IOUtils.DIR_SEPARATOR_WINDOWS, '/');
        }
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            str = str.substring(0, indexOf) + "#" + encode(str.substring(indexOf + 1));
        }
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            int length = str.length() - 1;
            while (length >= 0) {
                char charAt = str.charAt(length);
                if (!Character.isWhitespace(charAt) && charAt != 160) {
                    break;
                }
                sb.append(charAt);
                length--;
            }
            if (sb.length() > 0) {
                str = str.substring(0, length + 1) + encode(sb.reverse().toString());
            }
        }
        if (missingAuthPattern.matcher(str).matches()) {
            str = str + FORWARD_SLASH_STRING;
        }
        return new URI(str);
    }

    public static String encode(String str) {
        if (str.length() == 0) {
            return str;
        }
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        while (wrap.hasRemaining()) {
            byte b = wrap.get() & UByte.MAX_VALUE;
            if (isUnsafe(b)) {
                sb.append('%');
                char[] cArr = hexDigits;
                sb.append(cArr[(b >> 4) & 15]);
                sb.append(cArr[(b >> 0) & 15]);
            } else {
                sb.append((char) b);
            }
        }
        return sb.toString();
    }

    private static boolean isUnsafe(int i) {
        return i >= 128 || i == 124 || Character.isWhitespace(i);
    }
}
