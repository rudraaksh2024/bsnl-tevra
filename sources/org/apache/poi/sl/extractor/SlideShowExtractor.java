package org.apache.poi.sl.extractor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;

public class SlideShowExtractor<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> implements POITextExtractor {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SlideShowExtractor.class);
    private static final String SLIDE_NUMBER_PH = "‹#›";
    private boolean commentsByDefault;
    private boolean doCloseFilesystem = true;
    private Predicate<Object> filter = new SlideShowExtractor$$ExternalSyntheticLambda5();
    private boolean masterByDefault;
    private boolean notesByDefault;
    private boolean slidesByDefault = true;
    protected final SlideShow<S, P> slideshow;

    static /* synthetic */ boolean lambda$new$0(Object obj) {
        return true;
    }

    public SlideShowExtractor(SlideShow<S, P> slideShow) {
        this.slideshow = slideShow;
    }

    public SlideShow<S, P> getDocument() {
        return this.slideshow;
    }

    public void setSlidesByDefault(boolean z) {
        this.slidesByDefault = z;
    }

    public void setNotesByDefault(boolean z) {
        this.notesByDefault = z;
    }

    public void setCommentsByDefault(boolean z) {
        this.commentsByDefault = z;
    }

    public void setMasterByDefault(boolean z) {
        this.masterByDefault = z;
    }

    public POITextExtractor getMetadataTextExtractor() {
        return this.slideshow.getMetadataTextExtractor();
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Slide text : this.slideshow.getSlides()) {
            getText(text, new SlideShowExtractor$$ExternalSyntheticLambda12(sb));
        }
        return sb.toString();
    }

    public String getText(Slide<S, P> slide) {
        StringBuilder sb = new StringBuilder();
        getText(slide, new SlideShowExtractor$$ExternalSyntheticLambda12(sb));
        return sb.toString();
    }

    private void getText(Slide<S, P> slide, Consumer<String> consumer) {
        if (this.slidesByDefault) {
            printShapeText(slide, consumer);
        }
        if (this.masterByDefault) {
            MasterSheet<S, P> masterSheet = slide.getMasterSheet();
            printSlideMaster(masterSheet, consumer);
            MasterSheet<S, P> slideLayout = slide.getSlideLayout();
            if (slideLayout != masterSheet) {
                printSlideMaster(slideLayout, consumer);
            }
        }
        if (this.commentsByDefault) {
            printComments(slide, consumer);
        }
        if (this.notesByDefault) {
            printNotes(slide, consumer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r0 = (org.apache.poi.sl.usermodel.TextShape) r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void printSlideMaster(org.apache.poi.sl.usermodel.MasterSheet<S, P> r4, java.util.function.Consumer<java.lang.String> r5) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.Iterator r4 = r4.iterator()
        L_0x0007:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0048
            java.lang.Object r0 = r4.next()
            org.apache.poi.sl.usermodel.Shape r0 = (org.apache.poi.sl.usermodel.Shape) r0
            boolean r1 = r0 instanceof org.apache.poi.sl.usermodel.TextShape
            if (r1 == 0) goto L_0x0007
            org.apache.poi.sl.usermodel.TextShape r0 = (org.apache.poi.sl.usermodel.TextShape) r0
            java.lang.String r1 = r0.getText()
            if (r1 == 0) goto L_0x0007
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x0007
            java.lang.String r2 = "*"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x002e
            goto L_0x0007
        L_0x002e:
            boolean r2 = r0.isPlaceholder()
            if (r2 == 0) goto L_0x0040
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atInfo()
            java.lang.String r2 = "Ignoring boiler plate (placeholder) text on slide master: {}"
            r0.log((java.lang.String) r2, (java.lang.Object) r1)
            goto L_0x0007
        L_0x0040:
            java.util.List r0 = r0.getTextParagraphs()
            r3.printTextParagraphs(r0, r5)
            goto L_0x0007
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.extractor.SlideShowExtractor.printSlideMaster(org.apache.poi.sl.usermodel.MasterSheet, java.util.function.Consumer):void");
    }

    private void printTextParagraphs(List<P> list, Consumer<String> consumer) {
        printTextParagraphs(list, consumer, "\n");
    }

    private void printTextParagraphs(List<P> list, Consumer<String> consumer, String str) {
        printTextParagraphs(list, consumer, str, new SlideShowExtractor$$ExternalSyntheticLambda4());
    }

    private void printTextParagraphs(List<P> list, Consumer<String> consumer, String str, Function<TextRun, String> function) {
        for (P<TextRun> it : list) {
            for (TextRun textRun : it) {
                if (this.filter.test(textRun)) {
                    consumer.accept(function.apply(textRun));
                }
            }
            if (!str.isEmpty() && this.filter.test(str)) {
                consumer.accept(str);
            }
        }
    }

    private void printHeaderFooter(Sheet<S, P> sheet, Consumer<String> consumer, Consumer<String> consumer2) {
        TextShape textShape;
        PlaceholderDetails placeholderDetails;
        MasterSheet<S, P> masterSheet = sheet instanceof Slide ? sheet.getMasterSheet() : sheet;
        addSheetPlaceholderDatails(sheet, Placeholder.HEADER, consumer);
        addSheetPlaceholderDatails(sheet, Placeholder.FOOTER, consumer2);
        if (this.masterByDefault) {
            Iterator it = masterSheet.iterator();
            while (it.hasNext()) {
                Shape shape = (Shape) it.next();
                if ((shape instanceof TextShape) && (placeholderDetails = textShape.getPlaceholderDetails()) != null && placeholderDetails.isVisible() && placeholderDetails.getPlaceholder() != null) {
                    int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder[placeholderDetails.getPlaceholder().ordinal()];
                    if (i == 1) {
                        printTextParagraphs((textShape = (TextShape) shape).getTextParagraphs(), consumer);
                    } else if (i == 2) {
                        printTextParagraphs(textShape.getTextParagraphs(), consumer2);
                    } else if (i == 3) {
                        printTextParagraphs(textShape.getTextParagraphs(), consumer2, "\n", new SlideShowExtractor$$ExternalSyntheticLambda7());
                    }
                }
            }
        }
    }

    private void addSheetPlaceholderDatails(Sheet<S, P> sheet, Placeholder placeholder, Consumer<String> consumer) {
        PlaceholderDetails placeholderDetails = sheet.getPlaceholderDetails(placeholder);
        String text = placeholderDetails != null ? placeholderDetails.getText() : null;
        if (text != null && this.filter.test(placeholderDetails)) {
            consumer.accept(text);
        }
    }

    private void printShapeText(Sheet<S, P> sheet, Consumer<String> consumer) {
        LinkedList linkedList = new LinkedList();
        printHeaderFooter(sheet, consumer, new SlideShowExtractor$$ExternalSyntheticLambda13(linkedList));
        printShapeText(sheet, consumer);
        linkedList.forEach(consumer);
    }

    private void printShapeText(ShapeContainer<S, P> shapeContainer, Consumer<String> consumer) {
        Iterator it = shapeContainer.iterator();
        while (it.hasNext()) {
            Shape shape = (Shape) it.next();
            if (shape instanceof TextShape) {
                printTextParagraphs(((TextShape) shape).getTextParagraphs(), consumer);
            } else if (shape instanceof TableShape) {
                printShapeText((TableShape) shape, consumer);
            } else if (shape instanceof ShapeContainer) {
                printShapeText((ShapeContainer) shape, consumer);
            }
        }
    }

    private void printShapeText(TableShape<S, P> tableShape, Consumer<String> consumer) {
        String str;
        int numberOfRows = tableShape.getNumberOfRows();
        int numberOfColumns = tableShape.getNumberOfColumns();
        for (int i = 0; i < numberOfRows; i++) {
            String str2 = "";
            int i2 = 0;
            while (true) {
                str = "\n";
                if (i2 >= numberOfColumns) {
                    break;
                }
                TableCell<S, P> cell = tableShape.getCell(i, i2);
                if (cell != null) {
                    if (i2 < numberOfColumns - 1) {
                        str = "\t";
                    }
                    printTextParagraphs(cell.getTextParagraphs(), consumer, str);
                    str2 = str;
                }
                i2++;
            }
            if (!str2.equals(str) && this.filter.test(str)) {
                consumer.accept(str);
            }
        }
    }

    static /* synthetic */ String lambda$printComments$1(Comment comment) {
        return comment.getAuthor() + " - " + comment.getText();
    }

    private void printComments(Slide<S, P> slide, Consumer<String> consumer) {
        slide.getComments().stream().filter(this.filter).map(new SlideShowExtractor$$ExternalSyntheticLambda10()).forEach(consumer);
    }

    private void printNotes(Slide<S, P> slide, Consumer<String> consumer) {
        Notes<S, P> notes = slide.getNotes();
        if (notes != null) {
            LinkedList linkedList = new LinkedList();
            printHeaderFooter(notes, consumer, new SlideShowExtractor$$ExternalSyntheticLambda13(linkedList));
            printShapeText(notes, consumer);
            linkedList.forEach(consumer);
        }
    }

    public List<? extends ObjectShape<S, P>> getOLEShapes() {
        ArrayList arrayList = new ArrayList();
        for (Slide addOLEShapes : this.slideshow.getSlides()) {
            addOLEShapes(arrayList, addOLEShapes);
        }
        return arrayList;
    }

    private void addOLEShapes(List<ObjectShape<S, P>> list, ShapeContainer<S, P> shapeContainer) {
        Iterator it = shapeContainer.iterator();
        while (it.hasNext()) {
            Shape shape = (Shape) it.next();
            if (shape instanceof ShapeContainer) {
                addOLEShapes(list, (ShapeContainer) shape);
            } else if (shape instanceof ObjectShape) {
                list.add((ObjectShape) shape);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String replaceSlideNumber(TextRun textRun) {
        String rawText = textRun.getRawText();
        if (!rawText.contains(SLIDE_NUMBER_PH)) {
            return rawText;
        }
        TextParagraph<?, ?, ?> paragraph = textRun.getParagraph();
        Sheet<?, ?> sheet = null;
        TextShape<?, ?> parentShape = paragraph != null ? paragraph.getParentShape() : null;
        if (parentShape != null) {
            sheet = parentShape.getSheet();
        }
        return rawText.replace(SLIDE_NUMBER_PH, sheet instanceof Slide ? Integer.toString(((Slide) sheet).getSlideNumber() + 1) : "");
    }

    /* access modifiers changed from: private */
    public static String replaceTextCap(TextRun textRun) {
        TextParagraph<?, ?, ?> paragraph = textRun.getParagraph();
        Placeholder placeholder = null;
        TextShape<?, ?> parentShape = paragraph != null ? paragraph.getParentShape() : null;
        if (parentShape != null) {
            placeholder = parentShape.getPlaceholder();
        }
        String replace = textRun.getRawText().replace(Chars.CR, 10).replace(11, (placeholder == Placeholder.TITLE || placeholder == Placeholder.CENTERED_TITLE || placeholder == Placeholder.SUBTITLE) ? 10 : Chars.SPACE);
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap[textRun.getTextCap().ordinal()];
        if (i == 1) {
            return replace.toUpperCase(LocaleUtil.getUserLocale());
        }
        if (i != 2) {
            return replace;
        }
        return replace.toLowerCase(LocaleUtil.getUserLocale());
    }

    /* renamed from: org.apache.poi.sl.extractor.SlideShowExtractor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$Placeholder;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        static {
            /*
                org.apache.poi.sl.usermodel.TextRun$TextCap[] r0 = org.apache.poi.sl.usermodel.TextRun.TextCap.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap = r0
                r1 = 1
                org.apache.poi.sl.usermodel.TextRun$TextCap r2 = org.apache.poi.sl.usermodel.TextRun.TextCap.ALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$TextRun$TextCap     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.TextRun$TextCap r3 = org.apache.poi.sl.usermodel.TextRun.TextCap.SMALL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                org.apache.poi.sl.usermodel.Placeholder[] r2 = org.apache.poi.sl.usermodel.Placeholder.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$org$apache$poi$sl$usermodel$Placeholder = r2
                org.apache.poi.sl.usermodel.Placeholder r3 = org.apache.poi.sl.usermodel.Placeholder.HEADER     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0038 }
                org.apache.poi.sl.usermodel.Placeholder r2 = org.apache.poi.sl.usermodel.Placeholder.FOOTER     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.SLIDE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x004e }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.DATETIME     // Catch:{ NoSuchFieldError -> 0x004e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.extractor.SlideShowExtractor.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX INFO: finally extract failed */
    @Deprecated
    @Removal(version = "6.0.0")
    public BitSet getCodepoints(String str, Boolean bool, Boolean bool2) {
        BitSet bitSet = new BitSet();
        Predicate<Object> predicate = this.filter;
        try {
            this.filter = new SlideShowExtractor$$ExternalSyntheticLambda2(str, bool, bool2);
            this.slideshow.getSlides().forEach(new SlideShowExtractor$$ExternalSyntheticLambda3(this, bitSet));
            this.filter = predicate;
            return bitSet;
        } catch (Throwable th) {
            this.filter = predicate;
            throw th;
        }
    }

    static /* synthetic */ void lambda$null$3(BitSet bitSet, String str) {
        IntStream codePoints = str.codePoints();
        bitSet.getClass();
        codePoints.forEach(new SlideShowExtractor$$ExternalSyntheticLambda1(bitSet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCodepoints$4$org-apache-poi-sl-extractor-SlideShowExtractor  reason: not valid java name */
    public /* synthetic */ void m2239lambda$getCodepoints$4$orgapachepoislextractorSlideShowExtractor(BitSet bitSet, Slide slide) {
        getText(slide, new SlideShowExtractor$$ExternalSyntheticLambda6(bitSet));
    }

    /* JADX INFO: finally extract failed */
    @Internal
    public SparseBitSet getCodepointsInSparseBitSet(String str, Boolean bool, Boolean bool2) {
        SparseBitSet sparseBitSet = new SparseBitSet();
        Predicate<Object> predicate = this.filter;
        try {
            this.filter = new SlideShowExtractor$$ExternalSyntheticLambda8(str, bool, bool2);
            this.slideshow.getSlides().forEach(new SlideShowExtractor$$ExternalSyntheticLambda9(this, sparseBitSet));
            this.filter = predicate;
            return sparseBitSet;
        } catch (Throwable th) {
            this.filter = predicate;
            throw th;
        }
    }

    static /* synthetic */ void lambda$null$6(SparseBitSet sparseBitSet, String str) {
        IntStream codePoints = str.codePoints();
        sparseBitSet.getClass();
        codePoints.forEach(new SlideShowExtractor$$ExternalSyntheticLambda11(sparseBitSet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCodepointsInSparseBitSet$7$org-apache-poi-sl-extractor-SlideShowExtractor  reason: not valid java name */
    public /* synthetic */ void m2240lambda$getCodepointsInSparseBitSet$7$orgapachepoislextractorSlideShowExtractor(SparseBitSet sparseBitSet, Slide slide) {
        getText(slide, new SlideShowExtractor$$ExternalSyntheticLambda0(sparseBitSet));
    }

    /* access modifiers changed from: private */
    public static boolean filterFonts(Object obj, String str, Boolean bool, Boolean bool2) {
        if (!(obj instanceof TextRun)) {
            return false;
        }
        TextRun textRun = (TextRun) obj;
        if (!str.equalsIgnoreCase(textRun.getFontFamily())) {
            return false;
        }
        if (bool != null && textRun.isItalic() != bool.booleanValue()) {
            return false;
        }
        if (bool2 == null || textRun.isBold() == bool2.booleanValue()) {
            return true;
        }
        return false;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public SlideShow<S, P> getFilesystem() {
        return getDocument();
    }
}
