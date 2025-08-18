package org.apache.poi.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FontMetricsDumper {
    public static void main(String[] strArr) throws IOException {
        Throwable th;
        Properties properties = new Properties();
        for (Font fontName : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            String fontName2 = fontName.getFontName();
            FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(new Font(fontName2, 1, 10));
            properties.setProperty("font." + fontName2 + ".height", fontMetrics.getHeight() + "");
            StringBuilder sb = new StringBuilder();
            for (char c = 'a'; c <= 'z'; c = (char) (c + 1)) {
                sb.append(c).append(", ");
            }
            for (char c2 = 'A'; c2 <= 'Z'; c2 = (char) (c2 + 1)) {
                sb.append(c2).append(", ");
            }
            for (char c3 = '0'; c3 <= '9'; c3 = (char) (c3 + 1)) {
                sb.append(c3).append(", ");
            }
            StringBuilder sb2 = new StringBuilder();
            for (char c4 = 'a'; c4 <= 'z'; c4 = (char) (c4 + 1)) {
                sb2.append(fontMetrics.getWidths()[c4]).append(", ");
            }
            for (char c5 = 'A'; c5 <= 'Z'; c5 = (char) (c5 + 1)) {
                sb2.append(fontMetrics.getWidths()[c5]).append(", ");
            }
            for (char c6 = '0'; c6 <= '9'; c6 = (char) (c6 + 1)) {
                sb2.append(fontMetrics.getWidths()[c6]).append(", ");
            }
            properties.setProperty("font." + fontName2 + ".characters", sb.toString());
            properties.setProperty("font." + fontName2 + ".widths", sb2.toString());
        }
        FileOutputStream fileOutputStream = new FileOutputStream("font_metrics.properties");
        try {
            properties.store(fileOutputStream, "Font Metrics");
            fileOutputStream.close();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            try {
                fileOutputStream.close();
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
            throw th3;
        }
    }
}
