package org.apache.poi.xslf.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterators;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.usermodel.ObjectData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.PPTX2PNG;

@Internal
class PPTHandler extends MFProxy {
    private static final String RANGE_PATTERN = "(^|,)(?<from>\\d+)?(-(?<to>\\d+))?";
    private SlideShow<?, ?> ppt;
    private Slide<?, ?> slide;

    public static /* synthetic */ TreeSet $r8$lambda$F_awikBn4LD_0Zvo3lkWD8uDmOs() {
        return new TreeSet();
    }

    static /* synthetic */ boolean lambda$range$0(int i, int i2) {
        return i2 <= i;
    }

    /* access modifiers changed from: package-private */
    public void setDefaultCharset(Charset charset) {
    }

    PPTHandler() {
    }

    public void parse(File file) throws IOException {
        try {
            SlideShow<?, ?> create = SlideShowFactory.create(file, (String) null, true);
            this.ppt = create;
            if (create != null) {
                this.slide = (Slide) create.getSlides().get(0);
                return;
            }
            throw new IOException("Unknown file format or missing poi-scratchpad.jar / poi-ooxml.jar");
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
    }

    public void parse(InputStream inputStream) throws IOException {
        try {
            SlideShow<?, ?> create = SlideShowFactory.create(inputStream, (String) null);
            this.ppt = create;
            if (create != null) {
                this.slide = (Slide) create.getSlides().get(0);
                return;
            }
            throw new IOException("Unknown file format or missing poi-scratchpad.jar / poi-ooxml.jar");
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
    }

    public Dimension2D getSize() {
        return this.ppt.getPageSize();
    }

    public int getSlideCount() {
        return this.ppt.getSlides().size();
    }

    public void setSlideNo(int i) {
        this.slide = (Slide) this.ppt.getSlides().get(i - 1);
    }

    public String getTitle() {
        return this.slide.getTitle();
    }

    public Set<Integer> slideIndexes(String str) {
        final Matcher matcher = Pattern.compile(RANGE_PATTERN).matcher(str);
        return (Set) StreamSupport.stream(new Spliterators.AbstractSpliterator<Matcher>((long) str.length(), 272) {
            public boolean tryAdvance(Consumer<? super Matcher> consumer) {
                boolean find = matcher.find();
                if (find) {
                    consumer.accept(matcher);
                }
                return find;
            }
        }, false).flatMap(new PPTHandler$$ExternalSyntheticLambda2(this)).collect(Collectors.toCollection(new PPTHandler$$ExternalSyntheticLambda3()));
    }

    public void draw(Graphics2D graphics2D) {
        this.slide.draw(graphics2D);
    }

    public void close() throws IOException {
        SlideShow<?, ?> slideShow = this.ppt;
        if (slideShow != null) {
            slideShow.close();
        }
    }

    public GenericRecord getRoot() {
        SlideShow<?, ?> slideShow = this.ppt;
        if (slideShow instanceof GenericRecord) {
            return (GenericRecord) slideShow;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Stream<Integer> range(Matcher matcher) {
        int i;
        int size = this.ppt.getSlides().size();
        String group = matcher.group("from");
        String group2 = matcher.group(TypedValues.TransitionType.S_TO);
        int parseInt = (group == null || group.isEmpty()) ? 1 : Integer.parseInt(group);
        if (group2 == null) {
            i = parseInt;
        } else {
            i = (group2.isEmpty() || ((group == null || group.isEmpty()) && "1".equals(group2))) ? size : Integer.parseInt(group2);
        }
        return IntStream.rangeClosed(parseInt, i).filter(new PPTHandler$$ExternalSyntheticLambda6(size)).boxed();
    }

    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int i) {
        return new PPTHandler$$ExternalSyntheticLambda0(this, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getEmbeddings$2$org-apache-poi-xslf-util-PPTHandler  reason: not valid java name */
    public /* synthetic */ Iterator m2311lambda$getEmbeddings$2$orgapachepoixslfutilPPTHandler(int i) {
        return ((Slide) this.ppt.getSlides().get(i)).getShapes().stream().filter(new PPTHandler$$ExternalSyntheticLambda4()).map(new PPTHandler$$ExternalSyntheticLambda5()).iterator();
    }

    static /* synthetic */ boolean lambda$null$1(Shape shape) {
        return shape instanceof ObjectShape;
    }

    /* access modifiers changed from: private */
    public static EmbeddedExtractor.EmbeddedPart fromObjectShape(Shape<?, ?> shape) {
        ObjectData objectData = ((ObjectShape) shape).getObjectData();
        EmbeddedExtractor.EmbeddedPart embeddedPart = new EmbeddedExtractor.EmbeddedPart();
        embeddedPart.setName(objectData.getFileName());
        embeddedPart.setData(new PPTHandler$$ExternalSyntheticLambda1(objectData));
        return embeddedPart;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0011, code lost:
        if (r2 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001b, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ byte[] lambda$fromObjectShape$3(org.apache.poi.sl.usermodel.ObjectData r2) {
        /*
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ IOException -> 0x001c }
            byte[] r0 = org.apache.poi.util.IOUtils.toByteArray(r2)     // Catch:{ all -> 0x000e }
            if (r2 == 0) goto L_0x000d
            r2.close()     // Catch:{ IOException -> 0x001c }
        L_0x000d:
            return r0
        L_0x000e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r1 = move-exception
            if (r2 == 0) goto L_0x001b
            r2.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException -> 0x001c }
        L_0x001b:
            throw r1     // Catch:{ IOException -> 0x001c }
        L_0x001c:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTHandler.lambda$fromObjectShape$3(org.apache.poi.sl.usermodel.ObjectData):byte[]");
    }
}
