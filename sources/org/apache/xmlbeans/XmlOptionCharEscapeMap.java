package org.apache.xmlbeans;

import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;

public class XmlOptionCharEscapeMap {
    public static final int DECIMAL = 1;
    public static final int HEXADECIMAL = 2;
    public static final int PREDEF_ENTITY = 0;
    private static final Map<Character, String> _predefEntities;
    private final Map<Character, String> _charMap = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        _predefEntities = hashMap;
        hashMap.put(Character.valueOf(Typography.less), "&lt;");
        hashMap.put(Character.valueOf(Typography.greater), "&gt;");
        hashMap.put(Character.valueOf(Typography.amp), "&amp;");
        hashMap.put(Character.valueOf(Chars.QUOTE), "&apos;");
        hashMap.put('\"', "&quot;");
    }

    public boolean containsChar(char c) {
        return this._charMap.containsKey(Character.valueOf(c));
    }

    public void addMapping(char c, int i) throws XmlException {
        Character valueOf = Character.valueOf(c);
        if (i == 0) {
            String str = _predefEntities.get(valueOf);
            if (str != null) {
                this._charMap.put(valueOf, str);
                return;
            }
            throw new XmlException("XmlOptionCharEscapeMap.addMapping(): the PREDEF_ENTITY mode can only be used for the following characters: <, >, &, \" and '");
        } else if (i == 1) {
            this._charMap.put(valueOf, "&#" + c + ";");
        } else if (i == 2) {
            this._charMap.put(valueOf, "&#x" + Integer.toHexString(c) + ";");
        } else {
            throw new XmlException("XmlOptionCharEscapeMap.addMapping(): mode must be PREDEF_ENTITY, DECIMAL or HEXADECIMAL");
        }
    }

    public void addMappings(char c, char c2, int i) throws XmlException {
        if (c <= c2) {
            while (c <= c2) {
                addMapping(c, i);
                c = (char) (c + 1);
            }
            return;
        }
        throw new XmlException("XmlOptionCharEscapeMap.addMappings(): ch1 must be <= ch2");
    }

    public String getEscapedString(char c) {
        return this._charMap.get(Character.valueOf(c));
    }
}
