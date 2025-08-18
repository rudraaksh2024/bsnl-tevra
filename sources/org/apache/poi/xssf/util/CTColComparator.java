package org.apache.poi.xssf.util;

import java.util.Comparator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;

public class CTColComparator {
    public static final Comparator<CTCol> BY_MAX = new Comparator<CTCol>() {
        public int compare(CTCol cTCol, CTCol cTCol2) {
            return Long.compare(cTCol.getMax(), cTCol2.getMax());
        }
    };
    public static final Comparator<CTCol> BY_MIN_MAX = new Comparator<CTCol>() {
        public int compare(CTCol cTCol, CTCol cTCol2) {
            int i = (cTCol.getMin() > cTCol2.getMin() ? 1 : (cTCol.getMin() == cTCol2.getMin() ? 0 : -1));
            if (i < 0) {
                return -1;
            }
            if (i > 0) {
                return 1;
            }
            return CTColComparator.BY_MAX.compare(cTCol, cTCol2);
        }
    };

    private CTColComparator() {
    }
}
