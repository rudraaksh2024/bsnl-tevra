package org.apache.poi.sl.draw;

import com.google.firebase.messaging.Constants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Units;

public class DrawTextParagraph implements Drawable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final XlinkAttribute HYPERLINK_HREF = new XlinkAttribute("href");
    public static final XlinkAttribute HYPERLINK_LABEL = new XlinkAttribute(Constants.ScionAnalytics.PARAM_LABEL);
    private static final Logger LOG = LogManager.getLogger((Class<?>) DrawTextParagraph.class);
    protected int autoNbrIdx;
    protected DrawTextFragment bullet;
    protected boolean firstParagraph = true;
    protected List<DrawTextFragment> lines = new ArrayList();
    protected TextParagraph<?, ?, ?> paragraph;
    protected String rawText;
    double x;
    double y;

    public void applyTransform(Graphics2D graphics2D) {
    }

    public void drawContent(Graphics2D graphics2D) {
    }

    private static class XlinkAttribute extends AttributedCharacterIterator.Attribute {
        XlinkAttribute(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public Object readResolve() throws InvalidObjectException {
            if (DrawTextParagraph.HYPERLINK_HREF.getName().equals(getName())) {
                return DrawTextParagraph.HYPERLINK_HREF;
            }
            if (DrawTextParagraph.HYPERLINK_LABEL.getName().equals(getName())) {
                return DrawTextParagraph.HYPERLINK_LABEL;
            }
            throw new InvalidObjectException("unknown attribute name");
        }
    }

    public DrawTextParagraph(TextParagraph<?, ?, ?> textParagraph) {
        this.paragraph = textParagraph;
    }

    public void setPosition(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public double getY() {
        return this.y;
    }

    public void setAutoNumberingIdx(int i) {
        this.autoNbrIdx = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0158  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(java.awt.Graphics2D r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            java.util.List<org.apache.poi.sl.draw.DrawTextFragment> r2 = r0.lines
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x000d
            return
        L_0x000d:
            boolean r2 = r21.isHSLF()
            double r3 = r0.y
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r5 = r0.paragraph
            int r5 = r5.getIndentLevel()
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r6 = r0.paragraph
            java.lang.Double r6 = r6.getLeftMargin()
            r7 = 347663(0x54e0f, double:1.717683E-318)
            if (r6 != 0) goto L_0x002e
            long r9 = (long) r5
            long r9 = r9 * r7
            double r9 = org.apache.poi.util.Units.toPoints(r9)
            java.lang.Double r6 = java.lang.Double.valueOf(r9)
        L_0x002e:
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r9 = r0.paragraph
            java.lang.Double r9 = r9.getIndent()
            if (r9 != 0) goto L_0x0040
            long r9 = (long) r5
            long r9 = r9 * r7
            double r7 = org.apache.poi.util.Units.toPoints(r9)
            java.lang.Double r9 = java.lang.Double.valueOf(r7)
        L_0x0040:
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r5 = r0.paragraph
            java.lang.Double r5 = r5.getLineSpacing()
            if (r5 != 0) goto L_0x004e
            r7 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double r5 = java.lang.Double.valueOf(r7)
        L_0x004e:
            java.util.List<org.apache.poi.sl.draw.DrawTextFragment> r7 = r0.lines
            java.util.Iterator r7 = r7.iterator()
            r8 = 0
        L_0x0055:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x018c
            java.lang.Object r10 = r7.next()
            org.apache.poi.sl.draw.DrawTextFragment r10 = (org.apache.poi.sl.draw.DrawTextFragment) r10
            boolean r11 = r21.isFirstParagraph()
            if (r11 == 0) goto L_0x0069
            if (r8 == 0) goto L_0x00a7
        L_0x0069:
            float r11 = r10.getLeading()
            if (r8 != 0) goto L_0x0071
            r12 = 0
            goto L_0x0079
        L_0x0071:
            java.awt.font.TextLayout r12 = r8.getLayout()
            float r12 = r12.getDescent()
        L_0x0079:
            float r11 = r11 + r12
            double r11 = (double) r11
            double r3 = r3 - r11
            double r11 = r5.doubleValue()
            r13 = 0
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 <= 0) goto L_0x0097
            double r11 = r5.doubleValue()
            r13 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r11 = r11 * r13
            float r13 = r10.getHeight()
            double r13 = (double) r13
            double r11 = r11 * r13
            goto L_0x009c
        L_0x0097:
            double r11 = r5.doubleValue()
            double r11 = -r11
        L_0x009c:
            double r3 = r3 + r11
            java.awt.font.TextLayout r11 = r10.getLayout()
            float r11 = r11.getAscent()
            double r11 = (double) r11
            double r3 = r3 - r11
        L_0x00a7:
            double r11 = r0.x
            double r13 = r6.doubleValue()
            double r11 = r11 + r13
            if (r8 != 0) goto L_0x010e
            boolean r8 = r21.isEmptyParagraph()
            if (r8 != 0) goto L_0x00c4
            java.text.AttributedString r8 = r10.getAttributedString()
            java.text.AttributedCharacterIterator r8 = r8.getIterator()
            org.apache.poi.sl.draw.DrawTextFragment r8 = r0.getBullet(r1, r8)
            r0.bullet = r8
        L_0x00c4:
            org.apache.poi.sl.draw.DrawTextFragment r8 = r0.bullet
            if (r8 == 0) goto L_0x010e
            double r11 = r0.x
            if (r2 == 0) goto L_0x00cd
            goto L_0x00d2
        L_0x00cd:
            double r13 = r6.doubleValue()
            double r11 = r11 + r13
        L_0x00d2:
            double r13 = r9.doubleValue()
            double r11 = r11 + r13
            r8.setPosition(r11, r3)
            org.apache.poi.sl.draw.DrawTextFragment r8 = r0.bullet
            r8.draw(r1)
            org.apache.poi.sl.draw.DrawTextFragment r8 = r0.bullet
            java.awt.font.TextLayout r8 = r8.getLayout()
            float r8 = r8.getAdvance()
            r11 = 1065353216(0x3f800000, float:1.0)
            float r8 = r8 + r11
            double r11 = (double) r8
            double r13 = r0.x
            if (r2 == 0) goto L_0x00f7
            double r11 = r6.doubleValue()
            r15 = r7
            goto L_0x010c
        L_0x00f7:
            r15 = r7
            double r7 = r6.doubleValue()
            double r16 = r6.doubleValue()
            double r18 = r9.doubleValue()
            double r16 = r16 + r18
            double r11 = r16 + r11
            double r11 = java.lang.Math.max(r7, r11)
        L_0x010c:
            double r11 = r11 + r13
            goto L_0x010f
        L_0x010e:
            r15 = r7
        L_0x010f:
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r7 = r0.paragraph
            org.apache.poi.sl.usermodel.TextShape r7 = r7.getParentShape()
            java.awt.geom.Rectangle2D r7 = org.apache.poi.sl.draw.DrawShape.getAnchor((java.awt.Graphics2D) r1, (org.apache.poi.sl.usermodel.PlaceableShape<?, ?>) r7)
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r8 = r0.paragraph
            org.apache.poi.sl.usermodel.TextShape r8 = r8.getParentShape()
            org.apache.poi.sl.usermodel.Insets2D r8 = r8.getInsets()
            double r13 = r8.left
            r16 = r9
            double r8 = r8.right
            r17 = r2
            org.apache.poi.sl.usermodel.TextParagraph<?, ?, ?> r2 = r0.paragraph
            org.apache.poi.sl.usermodel.TextParagraph$TextAlign r2 = r2.getTextAlign()
            if (r2 != 0) goto L_0x0135
            org.apache.poi.sl.usermodel.TextParagraph$TextAlign r2 = org.apache.poi.sl.usermodel.TextParagraph.TextAlign.LEFT
        L_0x0135:
            int[] r18 = org.apache.poi.sl.draw.DrawTextParagraph.AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextParagraph$TextAlign
            int r2 = r2.ordinal()
            r2 = r18[r2]
            r18 = r5
            r5 = 1
            if (r2 == r5) goto L_0x0158
            r5 = 2
            if (r2 == r5) goto L_0x0146
            goto L_0x0171
        L_0x0146:
            double r19 = r7.getWidth()
            float r2 = r10.getWidth()
            double r0 = (double) r2
            double r19 = r19 - r0
            double r19 = r19 - r13
            double r19 = r19 - r8
            double r11 = r11 + r19
            goto L_0x0171
        L_0x0158:
            double r0 = r7.getWidth()
            float r2 = r10.getWidth()
            r19 = r3
            double r2 = (double) r2
            double r0 = r0 - r2
            double r0 = r0 - r13
            double r0 = r0 - r8
            double r2 = r6.doubleValue()
            double r0 = r0 - r2
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = r0 / r2
            double r11 = r11 + r0
            r3 = r19
        L_0x0171:
            r10.setPosition(r11, r3)
            r0 = r22
            r10.draw(r0)
            float r1 = r10.getHeight()
            double r1 = (double) r1
            double r3 = r3 + r1
            r1 = r0
            r8 = r10
            r7 = r15
            r9 = r16
            r2 = r17
            r5 = r18
            r0 = r21
            goto L_0x0055
        L_0x018c:
            r1 = r0
            double r5 = r1.y
            double r3 = r3 - r5
            r1.y = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTextParagraph.draw(java.awt.Graphics2D):void");
    }

    public float getFirstLineLeading() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        return this.lines.get(0).getLeading();
    }

    public float getFirstLineHeight() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        return this.lines.get(0).getHeight();
    }

    public float getLastLineHeight() {
        if (this.lines.isEmpty()) {
            return 0.0f;
        }
        List<DrawTextFragment> list = this.lines;
        return list.get(list.size() - 1).getHeight();
    }

    public boolean isEmptyParagraph() {
        return this.lines.isEmpty() || this.rawText.trim().isEmpty();
    }

    /* access modifiers changed from: protected */
    public void breakText(Graphics2D graphics2D) {
        int i;
        TextLayout textLayout;
        this.lines.clear();
        DrawFactory instance = DrawFactory.getInstance(graphics2D);
        StringBuilder sb = new StringBuilder();
        List<AttributedStringData> attributedString = getAttributedString(graphics2D, sb);
        AttributedString attributedString2 = new AttributedString(sb.toString());
        AttributedString attributedString3 = new AttributedString(sb.toString().replaceAll("[\\r\\n]", " "));
        for (AttributedStringData next : attributedString) {
            attributedString2.addAttribute(next.attribute, next.value, next.beginIndex, next.endIndex);
            attributedString3.addAttribute(next.attribute, next.value, next.beginIndex, next.endIndex);
        }
        AttributedCharacterIterator iterator = attributedString2.getIterator();
        AttributedCharacterIterator iterator2 = attributedString3.getIterator();
        LineBreakMeasurer lineBreakMeasurer = new LineBreakMeasurer(iterator, graphics2D.getFontRenderContext());
        do {
            int position = lineBreakMeasurer.getPosition();
            double d = 1.0d;
            double wrappingWidth = getWrappingWidth(this.lines.isEmpty(), graphics2D) + 1.0d;
            if (wrappingWidth >= 0.0d) {
                d = wrappingWidth;
            }
            i = 1;
            if (position != 0 || !sb.toString().startsWith("\n")) {
                int indexOf = sb.indexOf("\n", position + 1);
                if (indexOf == -1) {
                    indexOf = iterator.getEndIndex();
                }
                float f = (float) d;
                TextLayout nextLayout = lineBreakMeasurer.nextLayout(f, indexOf, true);
                textLayout = nextLayout == null ? lineBreakMeasurer.nextLayout(f, indexOf, false) : nextLayout;
                if (textLayout == null) {
                    break;
                }
                i = lineBreakMeasurer.getPosition();
                if (i < iterator.getEndIndex() && sb.charAt(i) == 10) {
                    lineBreakMeasurer.setPosition(i + 1);
                }
                TextParagraph.TextAlign textAlign = this.paragraph.getTextAlign();
                if (textAlign == TextParagraph.TextAlign.JUSTIFY || textAlign == TextParagraph.TextAlign.JUSTIFY_LOW) {
                    textLayout = textLayout.getJustifiedLayout(f);
                }
            } else {
                textLayout = lineBreakMeasurer.nextLayout((float) d, 1, false);
            }
            this.lines.add(instance.getTextFragment(textLayout, new AttributedString(iterator2, position, i)));
        } while (i != iterator.getEndIndex());
        this.rawText = sb.toString();
    }

    /* access modifiers changed from: protected */
    public DrawTextFragment getBullet(Graphics2D graphics2D, AttributedCharacterIterator attributedCharacterIterator) {
        String str;
        Paint paint;
        float f;
        TextParagraph.BulletStyle bulletStyle = this.paragraph.getBulletStyle();
        if (bulletStyle == null) {
            return null;
        }
        AutoNumberingScheme autoNumberingScheme = bulletStyle.getAutoNumberingScheme();
        if (autoNumberingScheme != null) {
            str = autoNumberingScheme.format(this.autoNbrIdx);
        } else {
            str = bulletStyle.getBulletCharacter();
        }
        if (str == null) {
            return null;
        }
        PlaceableShape<?, ?> paragraphShape = getParagraphShape();
        PaintStyle bulletFontColor = bulletStyle.getBulletFontColor();
        if (bulletFontColor == null) {
            paint = (Paint) attributedCharacterIterator.getAttribute(TextAttribute.FOREGROUND);
        } else {
            paint = new DrawPaint(paragraphShape).getPaint(graphics2D, bulletFontColor);
        }
        float floatValue = ((Float) attributedCharacterIterator.getAttribute(TextAttribute.SIZE)).floatValue();
        Double bulletFontSize = bulletStyle.getBulletFontSize();
        if (bulletFontSize == null) {
            bulletFontSize = Double.valueOf(100.0d);
        }
        if (bulletFontSize.doubleValue() > 0.0d) {
            f = (float) (((double) floatValue) * bulletFontSize.doubleValue() * 0.01d);
        } else {
            f = (float) (-bulletFontSize.doubleValue());
        }
        String bulletFont = bulletStyle.getBulletFont();
        if (bulletFont == null) {
            bulletFont = this.paragraph.getDefaultFontFamily();
        }
        DrawFontInfo drawFontInfo = new DrawFontInfo(bulletFont);
        DrawFontManager fontManager = DrawFactory.getInstance(graphics2D).getFontManager(graphics2D);
        FontInfo mappedFont = fontManager.getMappedFont(graphics2D, drawFontInfo);
        HashMap hashMap = new HashMap();
        hashMap.put(TextAttribute.FOREGROUND, paint);
        hashMap.put(TextAttribute.FAMILY, mappedFont.getTypeface());
        hashMap.put(TextAttribute.SIZE, Float.valueOf(f));
        hashMap.put(TextAttribute.FONT, new Font(hashMap));
        AttributedString attributedString = new AttributedString(fontManager.mapFontCharset(graphics2D, mappedFont, str));
        hashMap.forEach(new DrawTextParagraph$$ExternalSyntheticLambda2(attributedString));
        return DrawFactory.getInstance(graphics2D).getTextFragment(new TextLayout(attributedString.getIterator(), graphics2D.getFontRenderContext()), attributedString);
    }

    /* access modifiers changed from: protected */
    public String getRenderableText(Graphics2D graphics2D, TextRun textRun) {
        TextRun.FieldType fieldType = textRun.getFieldType();
        if (fieldType == null) {
            return getRenderableText(textRun);
        }
        if (textRun.getRawText().isEmpty()) {
            return "";
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextRun$FieldType[fieldType.ordinal()];
        if (i == 1) {
            Slide slide = (Slide) graphics2D.getRenderingHint(Drawable.CURRENT_SLIDE);
            if (slide == null) {
                return "";
            }
            return Integer.toString(slide.getSlideNumber());
        } else if (i != 2) {
            return "";
        } else {
            PlaceholderDetails placeholderDetails = ((SimpleShape) getParagraphShape()).getPlaceholderDetails();
            placeholderDetails.getPlaceholder();
            String userDate = placeholderDetails.getUserDate();
            if (userDate != null) {
                return userDate;
            }
            Calendar localeCalendar = LocaleUtil.getLocaleCalendar();
            return LocalDateTime.ofInstant(localeCalendar.toInstant(), localeCalendar.getTimeZone().toZoneId()).format(placeholderDetails.getDateFormat());
        }
    }

    @Internal
    public String getRenderableText(TextRun textRun) {
        String rawText2 = textRun.getRawText();
        if (rawText2 == null) {
            return null;
        }
        if (rawText2.contains("\t")) {
            rawText2 = rawText2.replace("\t", tab2space(textRun));
        }
        String replace = rawText2.replace(11, 10);
        Locale userLocale = LocaleUtil.getUserLocale();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap[textRun.getTextCap().ordinal()];
        if (i == 1) {
            return replace.toUpperCase(userLocale);
        }
        if (i != 2) {
            return replace;
        }
        return replace.toLowerCase(userLocale);
    }

    private String tab2space(TextRun textRun) {
        int i;
        AttributedString attributedString = new AttributedString(" ");
        String fontFamily = textRun.getFontFamily();
        if (fontFamily == null) {
            fontFamily = "Lucida Sans";
        }
        attributedString.addAttribute(TextAttribute.FAMILY, fontFamily);
        Double fontSize = textRun.getFontSize();
        if (fontSize == null) {
            fontSize = Double.valueOf(12.0d);
        }
        attributedString.addAttribute(TextAttribute.SIZE, Float.valueOf(fontSize.floatValue()));
        double advance = (double) new TextLayout(attributedString.getIterator(), new FontRenderContext((AffineTransform) null, true, true)).getAdvance();
        Double defaultTabSize = this.paragraph.getDefaultTabSize();
        if (advance <= 0.0d) {
            i = 4;
        } else {
            if (defaultTabSize == null) {
                defaultTabSize = Double.valueOf(4.0d * advance);
            }
            i = (int) Math.min(Math.ceil(defaultTabSize.doubleValue() / advance), 20.0d);
        }
        char[] cArr = new char[i];
        Arrays.fill(cArr, Chars.SPACE);
        return new String(cArr);
    }

    /* access modifiers changed from: protected */
    public double getWrappingWidth(boolean z, Graphics2D graphics2D) {
        double d;
        double d2;
        double d3;
        double d4;
        TextShape<?, ?> parentShape = this.paragraph.getParentShape();
        Insets2D insets = parentShape.getInsets();
        double d5 = insets.left;
        double d6 = insets.right;
        int indentLevel = this.paragraph.getIndentLevel();
        if (indentLevel == -1) {
            indentLevel = 0;
        }
        Double leftMargin = this.paragraph.getLeftMargin();
        if (leftMargin == null) {
            leftMargin = Double.valueOf(Units.toPoints(((long) indentLevel) * 347663));
        }
        Double indent = this.paragraph.getIndent();
        if (indent == null) {
            indent = Double.valueOf(0.0d);
        }
        Double rightMargin = this.paragraph.getRightMargin();
        if (rightMargin == null) {
            rightMargin = Double.valueOf(0.0d);
        }
        Rectangle2D anchor = DrawShape.getAnchor(graphics2D, (PlaceableShape<?, ?>) parentShape);
        TextShape.TextDirection textDirection = parentShape.getTextDirection();
        if (!parentShape.getWordWrap()) {
            Dimension pageSize = parentShape.getSheet().getSlideShow().getPageSize();
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection[textDirection.ordinal()];
            if (i == 1) {
                d3 = pageSize.getHeight();
                d4 = anchor.getX();
            } else if (i == 2) {
                return anchor.getX();
            } else {
                d3 = pageSize.getWidth();
                d4 = anchor.getX();
            }
            return d3 - d4;
        }
        int i2 = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection[textDirection.ordinal()];
        if (i2 == 1 || i2 == 2) {
            d = ((anchor.getHeight() - d5) - d6) - leftMargin.doubleValue();
            d2 = rightMargin.doubleValue();
        } else {
            d = ((anchor.getWidth() - d5) - d6) - leftMargin.doubleValue();
            d2 = rightMargin.doubleValue();
        }
        double d7 = d - d2;
        if (!z || this.bullet != null) {
            return d7;
        }
        return (isHSLF() ? leftMargin.doubleValue() - indent.doubleValue() : -indent.doubleValue()) + d7;
    }

    /* renamed from: org.apache.poi.sl.draw.DrawTextParagraph$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextParagraph$TextAlign;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextRun$FieldType;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|15|16|(2:17|18)|19|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0064 */
        static {
            /*
                org.apache.poi.sl.usermodel.TextShape$TextDirection[] r0 = org.apache.poi.sl.usermodel.TextShape.TextDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection = r0
                r1 = 1
                org.apache.poi.sl.usermodel.TextShape$TextDirection r2 = org.apache.poi.sl.usermodel.TextShape.TextDirection.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$TextShape$TextDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.TextShape$TextDirection r3 = org.apache.poi.sl.usermodel.TextShape.TextDirection.VERTICAL_270     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                org.apache.poi.sl.usermodel.TextRun$TextCap[] r2 = org.apache.poi.sl.usermodel.TextRun.TextCap.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap = r2
                org.apache.poi.sl.usermodel.TextRun$TextCap r3 = org.apache.poi.sl.usermodel.TextRun.TextCap.ALL     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap     // Catch:{ NoSuchFieldError -> 0x0038 }
                org.apache.poi.sl.usermodel.TextRun$TextCap r3 = org.apache.poi.sl.usermodel.TextRun.TextCap.SMALL     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                org.apache.poi.sl.usermodel.TextRun$FieldType[] r2 = org.apache.poi.sl.usermodel.TextRun.FieldType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$org$apache$poi$sl$usermodel$TextRun$FieldType = r2
                org.apache.poi.sl.usermodel.TextRun$FieldType r3 = org.apache.poi.sl.usermodel.TextRun.FieldType.SLIDE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$TextRun$FieldType     // Catch:{ NoSuchFieldError -> 0x0053 }
                org.apache.poi.sl.usermodel.TextRun$FieldType r3 = org.apache.poi.sl.usermodel.TextRun.FieldType.DATE_TIME     // Catch:{ NoSuchFieldError -> 0x0053 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0053 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0053 }
            L_0x0053:
                org.apache.poi.sl.usermodel.TextParagraph$TextAlign[] r2 = org.apache.poi.sl.usermodel.TextParagraph.TextAlign.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$org$apache$poi$sl$usermodel$TextParagraph$TextAlign = r2
                org.apache.poi.sl.usermodel.TextParagraph$TextAlign r3 = org.apache.poi.sl.usermodel.TextParagraph.TextAlign.CENTER     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$TextParagraph$TextAlign     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.poi.sl.usermodel.TextParagraph$TextAlign r2 = org.apache.poi.sl.usermodel.TextParagraph.TextAlign.RIGHT     // Catch:{ NoSuchFieldError -> 0x006e }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTextParagraph.AnonymousClass1.<clinit>():void");
        }
    }

    private static class AttributedStringData {
        AttributedCharacterIterator.Attribute attribute;
        int beginIndex;
        int endIndex;
        Object value;

        AttributedStringData(AttributedCharacterIterator.Attribute attribute2, Object obj, int i, int i2) {
            this.attribute = attribute2;
            this.value = obj;
            this.beginIndex = i;
            this.endIndex = i2;
        }
    }

    private PlaceableShape<?, ?> getParagraphShape() {
        return this.paragraph.getParentShape();
    }

    /* access modifiers changed from: protected */
    public List<AttributedStringData> getAttributedString(Graphics2D graphics2D, StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        DrawPaint drawPaint = new DrawPaint(getParagraphShape());
        DrawFontManager fontManager = DrawFactory.getInstance(graphics2D).getFontManager(graphics2D);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.paragraph.iterator();
        while (it.hasNext()) {
            TextRun textRun = (TextRun) it.next();
            String renderableText = getRenderableText(graphics2D, textRun);
            if (!renderableText.isEmpty()) {
                hashMap.clear();
                FontInfo fontInfo = textRun.getFontInfo((FontGroup) null);
                String mapFontCharset = fontManager.mapFontCharset(graphics2D, fontInfo, renderableText);
                int length = sb.length();
                sb.append(mapFontCharset);
                int length2 = sb.length();
                hashMap.put(TextAttribute.FOREGROUND, drawPaint.getPaint(graphics2D, textRun.getFontColor()));
                Double fontSize = textRun.getFontSize();
                if (fontSize == null) {
                    fontSize = this.paragraph.getDefaultFontSize();
                }
                hashMap.put(TextAttribute.SIZE, Float.valueOf(fontSize.floatValue()));
                if (textRun.isBold()) {
                    hashMap.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                }
                if (textRun.isItalic()) {
                    hashMap.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
                }
                if (textRun.isUnderlined()) {
                    hashMap.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    hashMap.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_TWO_PIXEL);
                }
                if (textRun.isStrikethrough()) {
                    hashMap.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                }
                if (textRun.isSubscript()) {
                    hashMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB);
                }
                if (textRun.isSuperscript()) {
                    hashMap.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
                }
                Hyperlink<?, ?> hyperlink = textRun.getHyperlink();
                if (hyperlink != null) {
                    hashMap.put(HYPERLINK_HREF, hyperlink.getAddress());
                    hashMap.put(HYPERLINK_LABEL, hyperlink.getLabel());
                }
                if (fontInfo != null) {
                    hashMap.put(TextAttribute.FAMILY, fontInfo.getTypeface());
                } else {
                    hashMap.put(TextAttribute.FAMILY, this.paragraph.getDefaultFontFamily());
                }
                hashMap.put(TextAttribute.FONT, new Font(hashMap));
                hashMap.forEach(new DrawTextParagraph$$ExternalSyntheticLambda0(arrayList, length, length2));
                processGlyphs(graphics2D, fontManager, arrayList, length, textRun, mapFontCharset);
            }
        }
        if (sb.length() == 0) {
            sb.append(" ");
            hashMap.put(TextAttribute.SIZE, Float.valueOf(this.paragraph.getDefaultFontSize().floatValue()));
            hashMap.put(TextAttribute.FAMILY, this.paragraph.getDefaultFontFamily());
            hashMap.put(TextAttribute.FONT, new Font(hashMap));
            hashMap.forEach(new DrawTextParagraph$$ExternalSyntheticLambda1(arrayList));
        }
        return arrayList;
    }

    private void processGlyphs(Graphics2D graphics2D, DrawFontManager drawFontManager, List<AttributedStringData> list, int i, TextRun textRun, String str) {
        int i2;
        int i3;
        Graphics2D graphics2D2 = graphics2D;
        DrawFontManager drawFontManager2 = drawFontManager;
        List<AttributedStringData> list2 = list;
        TextRun textRun2 = textRun;
        String str2 = str;
        int i4 = 0;
        for (FontGroup.FontGroupRange next : FontGroup.getFontGroupRanges(str)) {
            FontInfo fontInfo = textRun2.getFontInfo(next.getFontGroup());
            if (fontInfo == null) {
                fontInfo = textRun2.getFontInfo(FontGroup.LATIN);
            }
            FontInfo mappedFont = drawFontManager2.getMappedFont(graphics2D2, fontInfo);
            FontInfo fallbackFont = drawFontManager2.getFallbackFont(graphics2D2, fontInfo);
            if (mappedFont == null) {
                mappedFont = drawFontManager2.getMappedFont(graphics2D2, new DrawFontInfo(this.paragraph.getDefaultFontFamily()));
            }
            FontInfo fontInfo2 = mappedFont == null ? fallbackFont : mappedFont;
            DrawFontManager drawFontManager3 = drawFontManager;
            Graphics2D graphics2D3 = graphics2D;
            Font createAWTFont = drawFontManager3.createAWTFont(graphics2D3, fontInfo2, 10.0d, textRun.isBold(), textRun.isItalic());
            Font createAWTFont2 = drawFontManager3.createAWTFont(graphics2D3, fallbackFont, 10.0d, textRun.isBold(), textRun.isItalic());
            int length = next.getLength();
            int i5 = i4;
            while (true) {
                i2 = i4 + length;
                if (i5 >= i2) {
                    break;
                }
                int nextPart = nextPart(createAWTFont, str2, i5, i2, true);
                if (i5 < nextPart) {
                    String fontName = createAWTFont.getFontName(Locale.ROOT);
                    int i6 = i + i5;
                    int i7 = i + nextPart;
                    i3 = length;
                    list2.add(new AttributedStringData(TextAttribute.FAMILY, fontName, i6, i7));
                    LOG.atDebug().log("mapped: {} {} {} - {}", fontName, Unbox.box(i6), Unbox.box(i7), str2.substring(i5, nextPart));
                } else {
                    i3 = length;
                }
                i5 = nextPart(createAWTFont, str2, nextPart, i2, false);
                if (nextPart < i5) {
                    String fontName2 = createAWTFont2.getFontName(Locale.ROOT);
                    int i8 = i + nextPart;
                    int i9 = i + i5;
                    list2.add(new AttributedStringData(TextAttribute.FAMILY, fontName2, i8, i9));
                    LOG.atDebug().log("fallback: {} {} {} - {}", fontName2, Unbox.box(i8), Unbox.box(i9), str2.substring(nextPart, i5));
                }
                Graphics2D graphics2D4 = graphics2D;
                length = i3;
            }
            graphics2D2 = graphics2D;
            i4 = i2;
        }
    }

    private static int nextPart(Font font, String str, int i, int i2, boolean z) {
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (font.canDisplay(codePointAt) != z) {
                break;
            }
            i += Character.charCount(codePointAt);
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public boolean isHSLF() {
        return DrawShape.isHSLF(this.paragraph.getParentShape());
    }

    /* access modifiers changed from: protected */
    public boolean isFirstParagraph() {
        return this.firstParagraph;
    }

    /* access modifiers changed from: protected */
    public void setFirstParagraph(boolean z) {
        this.firstParagraph = z;
    }
}
