package org.apache.logging.log4j.spi;

import com.google.mlkit.common.MlKitException;
import java.util.EnumSet;
import java.util.Iterator;
import org.apache.poi.common.usermodel.fonts.FontHeader;

public enum StandardLevel {
    OFF(0),
    FATAL(100),
    ERROR(200),
    WARN(MlKitException.LOW_LIGHT_AUTO_EXPOSURE_COMPUTATION_FAILURE),
    INFO(FontHeader.REGULAR_WEIGHT),
    DEBUG(500),
    TRACE(600),
    ALL(Integer.MAX_VALUE);
    
    private static final EnumSet<StandardLevel> LEVELSET = null;
    private final int intLevel;

    static {
        LEVELSET = EnumSet.allOf(StandardLevel.class);
    }

    private StandardLevel(int i) {
        this.intLevel = i;
    }

    public int intLevel() {
        return this.intLevel;
    }

    public static StandardLevel getStandardLevel(int i) {
        StandardLevel standardLevel = OFF;
        Iterator it = LEVELSET.iterator();
        while (it.hasNext()) {
            StandardLevel standardLevel2 = (StandardLevel) it.next();
            if (standardLevel2.intLevel() > i) {
                break;
            }
            standardLevel = standardLevel2;
        }
        return standardLevel;
    }
}
