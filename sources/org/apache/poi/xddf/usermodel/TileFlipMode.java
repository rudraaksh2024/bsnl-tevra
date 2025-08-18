package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

public enum TileFlipMode {
    NONE(STTileFlipMode.NONE),
    X(STTileFlipMode.X),
    XY(STTileFlipMode.XY),
    Y(STTileFlipMode.Y);
    
    private static final HashMap<STTileFlipMode.Enum, TileFlipMode> reverse = null;
    final STTileFlipMode.Enum underlying;

    static {
        int i;
        reverse = new HashMap<>();
        for (TileFlipMode tileFlipMode : values()) {
            reverse.put(tileFlipMode.underlying, tileFlipMode);
        }
    }

    private TileFlipMode(STTileFlipMode.Enum enumR) {
        this.underlying = enumR;
    }

    static TileFlipMode valueOf(STTileFlipMode.Enum enumR) {
        return reverse.get(enumR);
    }
}
