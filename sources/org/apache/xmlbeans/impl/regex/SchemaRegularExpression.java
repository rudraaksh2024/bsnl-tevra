package org.apache.xmlbeans.impl.regex;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmlbeans.impl.common.XMLChar;

public class SchemaRegularExpression extends RegularExpression {
    static final Map knownPatterns = buildKnownPatternMap();

    private SchemaRegularExpression(String str) {
        super(str, "X");
    }

    public static RegularExpression forPattern(String str) {
        SchemaRegularExpression schemaRegularExpression = (SchemaRegularExpression) knownPatterns.get(str);
        if (schemaRegularExpression != null) {
            return schemaRegularExpression;
        }
        return new RegularExpression(str, "X");
    }

    private static Map buildKnownPatternMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("\\c+", new SchemaRegularExpression("\\c+") {
            public boolean matches(String str) {
                return XMLChar.isValidNmtoken(str);
            }
        });
        hashMap.put("\\i\\c*", new SchemaRegularExpression("\\i\\c*") {
            public boolean matches(String str) {
                return XMLChar.isValidName(str);
            }
        });
        hashMap.put("[\\i-[:]][\\c-[:]]*", new SchemaRegularExpression("[\\i-[:]][\\c-[:]]*") {
            public boolean matches(String str) {
                return XMLChar.isValidNCName(str);
            }
        });
        return hashMap;
    }
}
