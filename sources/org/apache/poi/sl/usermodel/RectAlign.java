package org.apache.poi.sl.usermodel;

import org.apache.commons.compress.compressors.CompressorStreamFactory;

public enum RectAlign {
    TOP_LEFT("tl"),
    TOP("t"),
    TOP_RIGHT("tr"),
    LEFT("l"),
    CENTER("ctr"),
    RIGHT("r"),
    BOTTOM_LEFT("bl"),
    BOTTOM("b"),
    BOTTOM_RIGHT(CompressorStreamFactory.BROTLI);
    
    private final String dir;

    private RectAlign(String str) {
        this.dir = str;
    }

    public String toString() {
        return this.dir;
    }
}
