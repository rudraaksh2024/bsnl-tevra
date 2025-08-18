package org.apache.poi.sl.draw;

import java.awt.Font;
import java.awt.Graphics2D;
import org.apache.poi.common.usermodel.fonts.FontInfo;

public interface DrawFontManager {
    Font createAWTFont(Graphics2D graphics2D, FontInfo fontInfo, double d, boolean z, boolean z2);

    FontInfo getFallbackFont(Graphics2D graphics2D, FontInfo fontInfo);

    FontInfo getMappedFont(Graphics2D graphics2D, FontInfo fontInfo);

    String mapFontCharset(Graphics2D graphics2D, FontInfo fontInfo, String str);
}
