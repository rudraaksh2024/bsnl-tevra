package org.apache.poi.sl.draw;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.util.StringUtil;

public class DrawFontManagerDefault implements DrawFontManager {
    protected final Set<String> knownSymbolFonts;

    /* access modifiers changed from: private */
    public static int mapSymbolChar(int i) {
        return ((32 > i || i > 127) && (160 > i || i > 255)) ? i : i | 61440;
    }

    public DrawFontManagerDefault() {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        this.knownSymbolFonts = treeSet;
        treeSet.add("Wingdings");
        treeSet.add("Symbol");
    }

    public FontInfo getMappedFont(Graphics2D graphics2D, FontInfo fontInfo) {
        return getFontWithFallback(graphics2D, Drawable.FONT_MAP, fontInfo);
    }

    public FontInfo getFallbackFont(Graphics2D graphics2D, FontInfo fontInfo) {
        FontInfo fontWithFallback = getFontWithFallback(graphics2D, Drawable.FONT_FALLBACK, fontInfo);
        return fontWithFallback == null ? new DrawFontInfo("SansSerif") : fontWithFallback;
    }

    public String mapFontCharset(Graphics2D graphics2D, FontInfo fontInfo, String str) {
        if (fontInfo == null || str == null || str.isEmpty()) {
            return str;
        }
        String typeface = fontInfo.getTypeface();
        if (fontInfo.getCharset() != FontCharset.SYMBOL && !this.knownSymbolFonts.contains(typeface)) {
            return str;
        }
        int[] array = str.codePoints().map(new DrawFontManagerDefault$$ExternalSyntheticLambda0()).toArray();
        String str2 = new String(array, 0, array.length);
        return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).contains(typeface) ? str2 : StringUtil.mapMsCodepointString(str2);
    }

    public Font createAWTFont(Graphics2D graphics2D, FontInfo fontInfo, double d, boolean z, boolean z2) {
        boolean z3 = (z2 ? (char) 2 : 0) | z;
        Font font = new Font(fontInfo.getTypeface(), z3 ? 1 : 0, 12);
        if ("Dialog".equals(font.getFamily())) {
            font = new Font("SansSerif", z3, 12);
        }
        return font.deriveFont((float) d);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.common.usermodel.fonts.FontInfo getFontWithFallback(java.awt.Graphics2D r2, org.apache.poi.sl.draw.Drawable.DrawableHint r3, org.apache.poi.common.usermodel.fonts.FontInfo r4) {
        /*
            r1 = this;
            java.lang.Object r1 = r2.getRenderingHint(r3)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x0009
            return r4
        L_0x0009:
            r2 = 0
            if (r4 == 0) goto L_0x0011
            java.lang.String r3 = r4.getTypeface()
            goto L_0x0012
        L_0x0011:
            r3 = r2
        L_0x0012:
            boolean r0 = r1.containsKey(r3)
            if (r0 == 0) goto L_0x0020
            java.lang.Object r1 = r1.get(r3)
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x002f
        L_0x0020:
            java.lang.String r3 = "*"
            boolean r0 = r1.containsKey(r3)
            if (r0 == 0) goto L_0x002f
            java.lang.Object r1 = r1.get(r3)
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
        L_0x002f:
            if (r2 == 0) goto L_0x0036
            org.apache.poi.sl.draw.DrawFontInfo r4 = new org.apache.poi.sl.draw.DrawFontInfo
            r4.<init>(r2)
        L_0x0036:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawFontManagerDefault.getFontWithFallback(java.awt.Graphics2D, org.apache.poi.sl.draw.Drawable$DrawableHint, org.apache.poi.common.usermodel.fonts.FontInfo):org.apache.poi.common.usermodel.fonts.FontInfo");
    }
}
