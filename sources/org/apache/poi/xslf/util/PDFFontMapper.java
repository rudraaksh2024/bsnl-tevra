package org.apache.poi.xslf.util;

import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer;
import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class PDFFontMapper extends PdfBoxGraphics2DFontTextDrawer {
    private static final String DEFAULT_TTF_PATTERN = ".*\\.tt[fc]";
    private static final String FONTDIRS_MAC = "$HOME/Library/Fonts;/Library/Fonts;/Network/Library/Fonts;/System/Library/Fonts;/System Folder/Fonts";
    private static final String FONTDIRS_UNX = "/usr/share/fonts;/usr/local/share/fonts;$HOME/.fonts";
    private static final String FONTDIRS_WIN = "C:\\Windows\\Fonts";
    private final Map<String, File> fonts = new HashMap();
    private final Set<String> registered = new HashSet();

    /* renamed from: $r8$lambda$9ujlb-IU2rou71voK9NsDp7pLBI  reason: not valid java name */
    public static /* synthetic */ File m2310$r8$lambda$9ujlbIU2rou71voK9NsDp7pLBI(String str) {
        return new File(str);
    }

    public PDFFontMapper(String str, String str2) {
        registerFonts(str, str2);
    }

    private void registerFonts(String str, String str2) {
        if (str == null) {
            String lowerCase = System.getProperty("os.name", "generic").toLowerCase(Locale.ROOT);
            str = (lowerCase.contains("mac") || lowerCase.contains("darwin")) ? FONTDIRS_MAC : lowerCase.contains("win") ? FONTDIRS_WIN : FONTDIRS_UNX;
        }
        String replace = str.replace("$HOME", System.getProperty("user.home"));
        LinkedList linkedList = new LinkedList();
        Stream.of(replace.split(";")).map(new PDFFontMapper$$ExternalSyntheticLambda0()).filter(new PDFFontMapper$$ExternalSyntheticLambda1()).forEach(new PDFFontMapper$$ExternalSyntheticLambda2(linkedList));
        if (str2 == null) {
            str2 = DEFAULT_TTF_PATTERN;
        }
        Pattern compile = Pattern.compile(str2);
        while (!linkedList.isEmpty()) {
            File[] listFiles = ((File) linkedList.removeFirst()).listFiles(new PDFFontMapper$$ExternalSyntheticLambda3(linkedList, compile));
            if (listFiles != null) {
                for (File file : listFiles) {
                    try {
                        this.fonts.put(Font.createFont(0, file).getFontName(Locale.ROOT), file);
                    } catch (FontFormatException | IOException unused) {
                    }
                }
            }
        }
    }

    static /* synthetic */ boolean lambda$registerFonts$0(LinkedList linkedList, Pattern pattern, File file, String str) {
        File file2 = new File(file, str);
        if (!file2.isDirectory()) {
            return pattern.matcher(str).matches();
        }
        linkedList.add(file2);
        return false;
    }

    /* access modifiers changed from: protected */
    public PDFont mapFont(Font font, IPdfBoxGraphics2DFontTextDrawer.IFontTextDrawerEnv iFontTextDrawerEnv) throws IOException, FontFormatException {
        String fontName = font.getFontName(Locale.ROOT);
        if (!this.registered.contains(fontName)) {
            this.registered.add(fontName);
            File file = this.fonts.get(fontName);
            if (file != null) {
                PDFFontMapper.super.registerFont(fontName, file);
            }
        }
        return PDFFontMapper.super.mapFont(font, iFontTextDrawerEnv);
    }
}
