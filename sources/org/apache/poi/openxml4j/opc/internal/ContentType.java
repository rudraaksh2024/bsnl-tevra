package org.apache.poi.openxml4j.opc.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public final class ContentType {
    private static final Pattern patternParams = Pattern.compile(";([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)=(\"?[\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+\"?)");
    private static final Pattern patternTypeSubType = Pattern.compile("^([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)/([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)$");
    private static final Pattern patternTypeSubTypeParams = Pattern.compile("^([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)/([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)(;([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)=(\"?[\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+\"?))*$");
    private final Map<String, String> parameters;
    private final String subType;
    private final String type;

    public ContentType(String str) throws InvalidFormatException {
        Matcher matcher = patternTypeSubType.matcher(str);
        matcher = !matcher.matches() ? patternTypeSubTypeParams.matcher(str) : matcher;
        if (!matcher.matches()) {
            throw new InvalidFormatException("The specified content type '" + str + "' is not compliant with RFC 2616: malformed content type.");
        } else if (matcher.groupCount() >= 2) {
            this.type = matcher.group(1);
            this.subType = matcher.group(2);
            this.parameters = new HashMap();
            if (matcher.groupCount() >= 5) {
                Matcher matcher2 = patternParams.matcher(str.substring(matcher.end(2)));
                while (matcher2.find()) {
                    this.parameters.put(matcher2.group(1), matcher2.group(2));
                }
            }
        } else {
            this.type = "";
            this.subType = "";
            this.parameters = Collections.emptyMap();
        }
    }

    public final String toString() {
        return toString(true);
    }

    public final String toString(boolean z) {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getType());
        sb.append('/');
        sb.append(getSubType());
        if (z) {
            for (Map.Entry next : this.parameters.entrySet()) {
                sb.append(';');
                sb.append((String) next.getKey());
                sb.append(Chars.EQ);
                sb.append((String) next.getValue());
            }
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return !(obj instanceof ContentType) || toString().equalsIgnoreCase(obj.toString());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.type, this.subType, this.parameters});
    }

    public String getSubType() {
        return this.subType;
    }

    public String getType() {
        return this.type;
    }

    public boolean hasParameters() {
        Map<String, String> map = this.parameters;
        return map != null && !map.isEmpty();
    }

    public String[] getParameterKeys() {
        Map<String, String> map = this.parameters;
        if (map == null) {
            return new String[0];
        }
        return (String[]) map.keySet().toArray(new String[0]);
    }

    public String getParameter(String str) {
        return this.parameters.get(str);
    }
}
