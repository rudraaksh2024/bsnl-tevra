package org.apache.xmlbeans;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.xml.stream.Location;
import org.apache.xmlbeans.impl.common.NameUtil;

public class XmlError implements Serializable {
    public static final int SEVERITY_ERROR = 0;
    public static final int SEVERITY_INFO = 2;
    public static final int SEVERITY_WARNING = 1;
    private static final ResourceBundle _bundle = PropertyResourceBundle.getBundle("org.apache.xmlbeans.message", Locale.ROOT);
    private static final long serialVersionUID = 1;
    private final String _code;
    private final int _column;
    private transient XmlCursor _cursor;
    private final int _line;
    private final String _message;
    private int _offset;
    private final int _severity;
    private final String _source;

    public XmlError(XmlError xmlError) {
        this._offset = -1;
        this._message = xmlError.getMessage();
        this._code = xmlError.getErrorCode();
        this._severity = xmlError.getSeverity();
        this._source = xmlError.getSourceName();
        this._line = xmlError.getLine();
        this._column = xmlError.getColumn();
        this._offset = xmlError.getOffset();
        this._cursor = xmlError.getCursorLocation();
    }

    private XmlError(String str, String str2, int i, String str3, int i2, int i3, int i4, XmlCursor xmlCursor) {
        this._message = str;
        this._code = str2;
        this._severity = i;
        this._source = str3;
        this._line = i2;
        this._column = i3;
        this._offset = i4;
        this._cursor = xmlCursor;
    }

    private XmlError(String str, Object[] objArr, int i, String str2, int i2, int i3, int i4, XmlCursor xmlCursor) {
        this(formattedMessage(str, objArr), str, i, str2, i2, i3, i4, xmlCursor);
    }

    protected XmlError(String str, String str2, int i, XmlCursor xmlCursor) {
        int i2;
        int i3;
        String str3;
        int i4 = -1;
        this._offset = -1;
        if (xmlCursor != null) {
            str3 = xmlCursor.documentProperties().getSourceName();
            XmlCursor newCursor = xmlCursor.newCursor();
            XmlLineNumber xmlLineNumber = (XmlLineNumber) newCursor.getBookmark(XmlLineNumber.class);
            xmlLineNumber = xmlLineNumber == null ? (XmlLineNumber) newCursor.toPrevBookmark(XmlLineNumber.class) : xmlLineNumber;
            if (xmlLineNumber != null) {
                i4 = xmlLineNumber.getLine();
                i2 = xmlLineNumber.getColumn();
                i3 = xmlLineNumber.getOffset();
            } else {
                i3 = -1;
                i2 = -1;
            }
            newCursor.dispose();
        } else {
            str3 = null;
            i3 = -1;
            i2 = -1;
        }
        this._message = str;
        this._code = str2;
        this._severity = i;
        this._source = str3;
        this._line = i4;
        this._column = i2;
        this._offset = i3;
        this._cursor = xmlCursor;
    }

    protected XmlError(String str, Object[] objArr, int i, XmlCursor xmlCursor) {
        this(formattedMessage(str, objArr), str, i, xmlCursor);
    }

    protected XmlError(String str, String str2, int i, Location location) {
        String str3;
        int i2;
        int i3 = -1;
        this._offset = -1;
        if (location != null) {
            i3 = location.getLineNumber();
            i2 = location.getColumnNumber();
            str3 = location.getPublicId();
            if (str3 == null) {
                str3 = location.getSystemId();
            }
        } else {
            str3 = null;
            i2 = -1;
        }
        this._message = str;
        this._code = str2;
        this._severity = i;
        this._source = str3;
        this._line = i3;
        this._column = i2;
    }

    protected XmlError(String str, Object[] objArr, int i, Location location) {
        this(formattedMessage(str, objArr), str, i, location);
    }

    public static XmlError forMessage(String str) {
        return forMessage(str, 0);
    }

    public static XmlError forMessage(String str, int i) {
        return forSource(str, i, (String) null);
    }

    public static XmlError forMessage(String str, Object[] objArr) {
        return forSource(str, objArr, 0, (String) null);
    }

    public static XmlError forMessage(String str, Object[] objArr, int i) {
        return forSource(str, objArr, i, (String) null);
    }

    public static XmlError forSource(String str, String str2) {
        return forLocation(str, 0, str2, -1, -1, -1);
    }

    public static XmlError forSource(String str, int i, String str2) {
        return forLocation(str, i, str2, -1, -1, -1);
    }

    public static XmlError forSource(String str, Object[] objArr, int i, String str2) {
        return forLocation(str, objArr, i, str2, -1, -1, -1);
    }

    public static XmlError forLocation(String str, String str2, Location location) {
        String str3 = null;
        return new XmlError(str, (String) null, 0, str2, location.getLineNumber(), location.getColumnNumber(), -1, (XmlCursor) null);
    }

    public static XmlError forLocation(String str, String str2, int i, int i2, int i3) {
        String str3 = null;
        return new XmlError(str, (String) null, 0, str2, i, i2, i3, (XmlCursor) null);
    }

    public static XmlError forLocation(String str, Object[] objArr, int i, String str2, int i2, int i3, int i4) {
        return new XmlError(str, objArr, i, str2, i2, i3, i4, (XmlCursor) null);
    }

    public static XmlError forLocation(String str, int i, String str2, int i2, int i3, int i4) {
        String str3 = null;
        return new XmlError(str, (String) null, i, str2, i2, i3, i4, (XmlCursor) null);
    }

    public static XmlError forLocationAndCursor(String str, int i, String str2, int i2, int i3, int i4, XmlCursor xmlCursor) {
        String str3 = null;
        return new XmlError(str, (String) null, i, str2, i2, i3, i4, xmlCursor);
    }

    public static XmlError forObject(String str, XmlObject xmlObject) {
        return forObject(str, 0, xmlObject);
    }

    public static XmlError forObject(String str, Object[] objArr, XmlObject xmlObject) {
        return forObject(str, objArr, 0, xmlObject);
    }

    public static XmlError forObject(String str, int i, XmlObject xmlObject) {
        if (xmlObject == null) {
            return forMessage(str, i);
        }
        return forCursor(str, i, xmlObject.newCursor());
    }

    public static XmlError forObject(String str, Object[] objArr, int i, XmlObject xmlObject) {
        if (xmlObject == null) {
            return forMessage(str, objArr, i);
        }
        return forCursor(str, objArr, i, xmlObject.newCursor());
    }

    public static XmlError forCursor(String str, XmlCursor xmlCursor) {
        return forCursor(str, 0, xmlCursor);
    }

    public static XmlError forCursor(String str, Object[] objArr, XmlCursor xmlCursor) {
        return forCursor(str, objArr, 0, xmlCursor);
    }

    public static XmlError forCursor(String str, int i, XmlCursor xmlCursor) {
        String str2 = null;
        return new XmlError(str, (String) null, i, xmlCursor);
    }

    public static XmlError forCursor(String str, Object[] objArr, int i, XmlCursor xmlCursor) {
        return new XmlError(str, objArr, i, xmlCursor);
    }

    protected static String formattedFileName(String str, URI uri) {
        URI uri2 = null;
        if (str == null) {
            return null;
        }
        try {
            URI uri3 = new URI(str);
            if (uri3.isAbsolute()) {
                uri2 = uri3;
            }
        } catch (URISyntaxException unused) {
        }
        if (uri2 == null) {
            uri2 = new File(str).toURI();
        }
        if (uri != null) {
            uri2 = uri.relativize(uri2);
        }
        if (!uri2.isAbsolute() ? !(uri == null || !uri.isAbsolute() || uri.getScheme().compareToIgnoreCase("file") != 0) : uri2.getScheme().compareToIgnoreCase("file") == 0) {
            try {
                return new File(uri2).toString();
            } catch (Exception unused2) {
            }
        }
        return uri2.toString();
    }

    public static String formattedMessage(String str, Object[] objArr) {
        if (str == null) {
            return null;
        }
        try {
            return new MessageFormat(_bundle.getString(str), Locale.ROOT).format(objArr);
        } catch (IllegalArgumentException | MissingResourceException e) {
            return new MessageFormat(_bundle.getString(e instanceof MissingResourceException ? "message.missing.resource" : "message.pattern.invalid"), Locale.ROOT).format(e.getMessage());
        }
    }

    public int getSeverity() {
        return this._severity;
    }

    public String getMessage() {
        return this._message;
    }

    public String getErrorCode() {
        return this._code;
    }

    public String getSourceName() {
        return this._source;
    }

    public int getLine() {
        return this._line;
    }

    public int getColumn() {
        return this._column;
    }

    public int getOffset() {
        return this._offset;
    }

    public Object getLocation(Object obj) {
        XmlCursor xmlCursor;
        if (obj == XmlCursor.class) {
            return this._cursor;
        }
        if (obj != XmlObject.class || (xmlCursor = this._cursor) == null) {
            return null;
        }
        return xmlCursor.getObject();
    }

    public XmlCursor getCursorLocation() {
        return (XmlCursor) getLocation(XmlCursor.class);
    }

    public XmlObject getObjectLocation() {
        return (XmlObject) getLocation(XmlObject.class);
    }

    public String toString() {
        return toString((URI) null);
    }

    public String toString(URI uri) {
        StringBuilder sb = new StringBuilder();
        String formattedFileName = formattedFileName(getSourceName(), uri);
        if (formattedFileName != null) {
            sb.append(formattedFileName);
            int line = getLine();
            if (line < 0) {
                line = 0;
            }
            sb.append(NameUtil.COLON);
            sb.append(line);
            sb.append(NameUtil.COLON);
            if (getColumn() > 0) {
                sb.append(getColumn());
                sb.append(NameUtil.COLON);
            }
            sb.append(" ");
        }
        int severity = getSeverity();
        if (severity == 0) {
            sb.append("error: ");
        } else if (severity == 1) {
            sb.append("warning: ");
        }
        if (getErrorCode() != null) {
            sb.append(getErrorCode()).append(": ");
        }
        String message = getMessage();
        if (message == null) {
            message = "<Unspecified message>";
        }
        sb.append(message);
        return sb.toString();
    }

    public static String severityAsString(int i) {
        if (i == 0) {
            return "error";
        }
        if (i == 1) {
            return "warning";
        }
        if (i == 2) {
            return "info";
        }
        throw new IllegalArgumentException("unknown severity");
    }
}
