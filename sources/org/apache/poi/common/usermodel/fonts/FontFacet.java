package org.apache.poi.common.usermodel.fonts;

public interface FontFacet {
    Object getFontData() {
        return null;
    }

    int getWeight() {
        return FontHeader.REGULAR_WEIGHT;
    }

    boolean isItalic() {
        return false;
    }

    void setWeight(int i) {
        throw new UnsupportedOperationException("FontFacet is read-only.");
    }

    void setItalic(boolean z) {
        throw new UnsupportedOperationException("FontFacet is read-only.");
    }
}
