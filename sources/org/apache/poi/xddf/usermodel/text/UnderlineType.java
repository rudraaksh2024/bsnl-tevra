package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public enum UnderlineType {
    DASH(STTextUnderlineType.DASH),
    DASH_HEAVY(STTextUnderlineType.DASH_HEAVY),
    DASH_LONG(STTextUnderlineType.DASH_LONG),
    DASH_LONG_HEAVY(STTextUnderlineType.DASH_LONG_HEAVY),
    DOUBLE(STTextUnderlineType.DBL),
    DOT_DASH(STTextUnderlineType.DOT_DASH),
    DOT_DASH_HEAVY(STTextUnderlineType.DOT_DASH_HEAVY),
    DOT_DOT_DASH(STTextUnderlineType.DOT_DOT_DASH),
    DOT_DOT_DASH_HEAVY(STTextUnderlineType.DOT_DOT_DASH_HEAVY),
    DOTTED(STTextUnderlineType.DOTTED),
    DOTTED_HEAVY(STTextUnderlineType.DOTTED_HEAVY),
    HEAVY(STTextUnderlineType.HEAVY),
    NONE(STTextUnderlineType.NONE),
    SINGLE(STTextUnderlineType.SNG),
    WAVY(STTextUnderlineType.WAVY),
    WAVY_DOUBLE(STTextUnderlineType.WAVY_DBL),
    WAVY_HEAVY(STTextUnderlineType.WAVY_HEAVY),
    WORDS(STTextUnderlineType.WORDS);
    
    private static final HashMap<STTextUnderlineType.Enum, UnderlineType> reverse = null;
    final STTextUnderlineType.Enum underlying;

    static {
        reverse = new HashMap<>();
        for (UnderlineType underlineType : values()) {
            reverse.put(underlineType.underlying, underlineType);
        }
    }

    private UnderlineType(STTextUnderlineType.Enum enumR) {
        this.underlying = enumR;
    }

    static UnderlineType valueOf(STTextUnderlineType.Enum enumR) {
        return reverse.get(enumR);
    }
}
