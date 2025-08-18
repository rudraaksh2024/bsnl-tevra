package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetPatternVal;

public enum PresetPattern {
    CROSS(STPresetPatternVal.CROSS),
    DASH_DOWNWARD_DIAGONAL(STPresetPatternVal.DASH_DN_DIAG),
    DASH_HORIZONTAL(STPresetPatternVal.DASH_HORZ),
    DASH_UPWARD_DIAGONAL(STPresetPatternVal.DASH_UP_DIAG),
    DASH_VERTICAL(STPresetPatternVal.DASH_VERT),
    DIAGONAL_BRICK(STPresetPatternVal.DIAG_BRICK),
    DIAGONAL_CROSS(STPresetPatternVal.DIAG_CROSS),
    DIVOT(STPresetPatternVal.DIVOT),
    DARK_DOWNWARD_DIAGONAL(STPresetPatternVal.DK_DN_DIAG),
    DARK_HORIZONTAL(STPresetPatternVal.DK_HORZ),
    DARK_UPWARD_DIAGONAL(STPresetPatternVal.DK_UP_DIAG),
    DARK_VERTICAL(STPresetPatternVal.DK_VERT),
    DOWNWARD_DIAGONAL(STPresetPatternVal.DN_DIAG),
    DOTTED_DIAMOND(STPresetPatternVal.DOT_DMND),
    DOTTED_GRID(STPresetPatternVal.DOT_GRID),
    HORIZONTAL(STPresetPatternVal.HORZ),
    HORIZONTAL_BRICK(STPresetPatternVal.HORZ_BRICK),
    LARGE_CHECKER_BOARD(STPresetPatternVal.LG_CHECK),
    LARGE_CONFETTI(STPresetPatternVal.LG_CONFETTI),
    LARGE_GRID(STPresetPatternVal.LG_GRID),
    LIGHT_DOWNWARD_DIAGONAL(STPresetPatternVal.LT_DN_DIAG),
    LIGHT_HORIZONTAL(STPresetPatternVal.LT_HORZ),
    LIGHT_UPWARD_DIAGONAL(STPresetPatternVal.LT_UP_DIAG),
    LIGHT_VERTICAL(STPresetPatternVal.LT_VERT),
    NARROW_HORIZONTAL(STPresetPatternVal.NAR_HORZ),
    NARROW_VERTICAL(STPresetPatternVal.NAR_VERT),
    OPEN_DIAMOND(STPresetPatternVal.OPEN_DMND),
    PERCENT_5(STPresetPatternVal.PCT_5),
    PERCENT_10(STPresetPatternVal.PCT_10),
    PERCENT_20(STPresetPatternVal.PCT_20),
    PERCENT_25(STPresetPatternVal.PCT_25),
    PERCENT_30(STPresetPatternVal.PCT_30),
    PERCENT_40(STPresetPatternVal.PCT_40),
    PERCENT_50(STPresetPatternVal.PCT_50),
    PERCENT_60(STPresetPatternVal.PCT_60),
    PERCENT_70(STPresetPatternVal.PCT_70),
    PERCENT_75(STPresetPatternVal.PCT_75),
    PERCENT_80(STPresetPatternVal.PCT_80),
    PERCENT_90(STPresetPatternVal.PCT_90),
    PLAID(STPresetPatternVal.PLAID),
    SHINGLE(STPresetPatternVal.SHINGLE),
    SMALL_CHECKER_BOARD(STPresetPatternVal.SM_CHECK),
    SMALL_CONFETTI(STPresetPatternVal.SM_CONFETTI),
    SMALL_GRID(STPresetPatternVal.SM_GRID),
    SOLID_DIAMOND(STPresetPatternVal.SOLID_DMND),
    SPHERE(STPresetPatternVal.SPHERE),
    TRELLIS(STPresetPatternVal.TRELLIS),
    UPWARD_DIAGONAL(STPresetPatternVal.UP_DIAG),
    VERTICAL(STPresetPatternVal.VERT),
    WAVE(STPresetPatternVal.WAVE),
    WEAVE(STPresetPatternVal.WEAVE),
    WIDE_DOWNWARD_DIAGONAL(STPresetPatternVal.WD_DN_DIAG),
    WIDE_UPWARD_DIAGONAL(STPresetPatternVal.WD_UP_DIAG),
    ZIG_ZAG(STPresetPatternVal.ZIG_ZAG);
    
    private static final HashMap<STPresetPatternVal.Enum, PresetPattern> reverse = null;
    final STPresetPatternVal.Enum underlying;

    static {
        reverse = new HashMap<>();
        for (PresetPattern presetPattern : values()) {
            reverse.put(presetPattern.underlying, presetPattern);
        }
    }

    private PresetPattern(STPresetPatternVal.Enum enumR) {
        this.underlying = enumR;
    }

    static PresetPattern valueOf(STPresetPatternVal.Enum enumR) {
        return reverse.get(enumR);
    }
}
