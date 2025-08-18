package org.apache.poi.common.usermodel.fonts;

import java.util.Collections;
import java.util.List;

public interface FontInfo {
    Integer getIndex() {
        return null;
    }

    byte[] getPanose() {
        return null;
    }

    FontPitch getPitch() {
        return null;
    }

    String getTypeface();

    void setIndex(int i) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    void setTypeface(String str) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    FontCharset getCharset() {
        return FontCharset.ANSI;
    }

    void setCharset(FontCharset fontCharset) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    FontFamily getFamily() {
        return FontFamily.FF_DONTCARE;
    }

    void setFamily(FontFamily fontFamily) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    void setPitch(FontPitch fontPitch) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    void setPanose(byte[] bArr) {
        throw new UnsupportedOperationException("FontInfo is read-only.");
    }

    List<? extends FontFacet> getFacets() {
        return Collections.emptyList();
    }
}
