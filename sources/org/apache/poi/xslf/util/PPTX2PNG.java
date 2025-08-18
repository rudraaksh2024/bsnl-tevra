package org.apache.poi.xslf.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.util.LocaleUtil;

public final class PPTX2PNG {
    private static final Pattern INPUT_PATTERN = Pattern.compile(INPUT_PAT_REGEX);
    private static final String INPUT_PAT_REGEX = "(?<slideno>[^|]+)\\|(?<format>[^|]+)\\|(?<basename>.+)\\.(?<ext>[^.]++)";
    private static final Logger LOG = LogManager.getLogger((Class<?>) PPTX2PNG.class);
    private static final String OUTPUT_PAT_REGEX = "${basename}-${slideno}.${format}";
    private Charset charset = LocaleUtil.CHARSET_1252;
    private FileMagic defaultFileType = FileMagic.OLE2;
    private File dumpfile = null;
    private boolean emfHeaderBounds = false;
    private boolean extractEmbedded = false;
    private File file = null;
    private String fixSide = "scale";
    private String fontDir = null;
    private String fontMap = null;
    private String fontTtf = null;
    private String format = ContentTypes.EXTENSION_PNG;
    private boolean ignoreParse = false;
    private String outPattern = OUTPUT_PAT_REGEX;
    private File outdir = null;
    private String outfile = null;
    private boolean quiet = false;
    private float scale = 1.0f;
    private String slidenumStr = "-1";
    private boolean textAsShapes = false;

    private interface ProxyConsumer {
        void parse(MFProxy mFProxy) throws IOException;
    }

    private static void usage(String str) {
        System.out.println("Usage: PPTX2PNG [options] <.ppt/.pptx/.emf/.wmf file or 'stdin'>\n" + (str == null ? "" : "Error: " + str + "\n") + "Options:\n    -scale <float>    scale factor\n    -fixSide <side>   specify side (long,short,width,height) to fix - use <scale> as amount of pixels\n    -slide <integer>  1-based index of a slide to render\n    -format <type>    png,gif,jpg,svg,pdf (,log,null for testing)\n    -outdir <dir>     output directory, defaults to origin of the ppt/pptx file\n    -outfile <file>   output filename, defaults to '${basename}-${slideno}.${format}'\n    -outpat <pattern> output filename pattern, defaults to '${basename}-${slideno}.${format}'\n                      patterns: basename, slideno, format, ext\n    -dump <file>      dump the annotated records to a file\n    -quiet            do not write to console (for normal processing)\n    -ignoreParse      ignore parsing error and continue with the records read until the error\n    -extractEmbedded  extract embedded parts\n    -inputType <type> default input file type (OLE2,WMF,EMF), default is OLE2 = Powerpoint\n                      some files (usually wmf) don't have a header, i.e. an identifiable file magic\n    -textAsShapes     text elements are saved as shapes in SVG, necessary for variable spacing\n                      often found in math formulas\n    -charset <cs>     sets the default charset to be used, defaults to Windows-1252\n    -emfHeaderBounds  force the usage of the emf header bounds to calculate the bounding box\n    -fontdir <dir>    (PDF only) font directories separated by \";\" - use $HOME for current users home dir\n                      defaults to the usual plattform directories\n    -fontTtf <regex>  (PDF only) regex to match the .ttf filenames\n    -fontMap <map>    \";\"-separated list of font mappings <typeface from>:<typeface to>");
    }

    public static void main(String[] strArr) throws Exception {
        PPTX2PNG pptx2png = new PPTX2PNG();
        if (pptx2png.parseCommandLine(strArr)) {
            pptx2png.processFile();
        }
    }

    private PPTX2PNG() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean parseCommandLine(java.lang.String[] r10) {
        /*
            r9 = this;
            int r0 = r10.length
            r1 = 0
            r2 = 0
            if (r0 != 0) goto L_0x0009
            usage(r1)
            return r2
        L_0x0009:
            r0 = r2
        L_0x000a:
            int r3 = r10.length
            r4 = 1
            if (r0 >= r3) goto L_0x01aa
            int r3 = r0 + 1
            int r5 = r10.length
            if (r3 >= r5) goto L_0x0016
            r5 = r10[r3]
            goto L_0x0017
        L_0x0016:
            r5 = r1
        L_0x0017:
            r6 = r10[r0]
            java.util.Locale r7 = java.util.Locale.ROOT
            java.lang.String r6 = r6.toLowerCase(r7)
            r6.hashCode()
            int r7 = r6.hashCode()
            r8 = -1
            switch(r7) {
                case -1227488812: goto L_0x0105;
                case -1148328386: goto L_0x00fa;
                case -1022087042: goto L_0x00ef;
                case -558344333: goto L_0x00e4;
                case 10629373: goto L_0x00d9;
                case 14180964: goto L_0x00ce;
                case 44653473: goto L_0x00c3;
                case 273562143: goto L_0x00b8;
                case 277435628: goto L_0x00aa;
                case 277446914: goto L_0x009c;
                case 436126993: goto L_0x008e;
                case 436135392: goto L_0x0080;
                case 436142698: goto L_0x0072;
                case 1396259367: goto L_0x0064;
                case 1397562685: goto L_0x0056;
                case 1397838244: goto L_0x0048;
                case 1454756983: goto L_0x003a;
                case 1856128127: goto L_0x002c;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x010f
        L_0x002c:
            java.lang.String r7 = "-charset"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0036
            goto L_0x010f
        L_0x0036:
            r8 = 17
            goto L_0x010f
        L_0x003a:
            java.lang.String r7 = "-inputtype"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0044
            goto L_0x010f
        L_0x0044:
            r8 = 16
            goto L_0x010f
        L_0x0048:
            java.lang.String r7 = "-slide"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0052
            goto L_0x010f
        L_0x0052:
            r8 = 15
            goto L_0x010f
        L_0x0056:
            java.lang.String r7 = "-scale"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0060
            goto L_0x010f
        L_0x0060:
            r8 = 14
            goto L_0x010f
        L_0x0064:
            java.lang.String r7 = "-quiet"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x006e
            goto L_0x010f
        L_0x006e:
            r8 = 13
            goto L_0x010f
        L_0x0072:
            java.lang.String r7 = "-fontttf"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x007c
            goto L_0x010f
        L_0x007c:
            r8 = 12
            goto L_0x010f
        L_0x0080:
            java.lang.String r7 = "-fontmap"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x008a
            goto L_0x010f
        L_0x008a:
            r8 = 11
            goto L_0x010f
        L_0x008e:
            java.lang.String r7 = "-fontdir"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0098
            goto L_0x010f
        L_0x0098:
            r8 = 10
            goto L_0x010f
        L_0x009c:
            java.lang.String r7 = "-outpat"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00a6
            goto L_0x010f
        L_0x00a6:
            r8 = 9
            goto L_0x010f
        L_0x00aa:
            java.lang.String r7 = "-outdir"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00b4
            goto L_0x010f
        L_0x00b4:
            r8 = 8
            goto L_0x010f
        L_0x00b8:
            java.lang.String r7 = "-fixside"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00c1
            goto L_0x010f
        L_0x00c1:
            r8 = 7
            goto L_0x010f
        L_0x00c3:
            java.lang.String r7 = "-dump"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00cc
            goto L_0x010f
        L_0x00cc:
            r8 = 6
            goto L_0x010f
        L_0x00ce:
            java.lang.String r7 = "-format"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00d7
            goto L_0x010f
        L_0x00d7:
            r8 = 5
            goto L_0x010f
        L_0x00d9:
            java.lang.String r7 = "-outfile"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00e2
            goto L_0x010f
        L_0x00e2:
            r8 = 4
            goto L_0x010f
        L_0x00e4:
            java.lang.String r7 = "-emfheaderbounds"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00ed
            goto L_0x010f
        L_0x00ed:
            r8 = 3
            goto L_0x010f
        L_0x00ef:
            java.lang.String r7 = "-textasshapes"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00f8
            goto L_0x010f
        L_0x00f8:
            r8 = 2
            goto L_0x010f
        L_0x00fa:
            java.lang.String r7 = "-extractembedded"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x0103
            goto L_0x010f
        L_0x0103:
            r8 = r4
            goto L_0x010f
        L_0x0105:
            java.lang.String r7 = "-ignoreparse"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x010e
            goto L_0x010f
        L_0x010e:
            r8 = r2
        L_0x010f:
            switch(r8) {
                case 0: goto L_0x01a5;
                case 1: goto L_0x01a2;
                case 2: goto L_0x019f;
                case 3: goto L_0x019c;
                case 4: goto L_0x0199;
                case 5: goto L_0x0196;
                case 6: goto L_0x0182;
                case 7: goto L_0x0172;
                case 8: goto L_0x0168;
                case 9: goto L_0x0165;
                case 10: goto L_0x015d;
                case 11: goto L_0x0155;
                case 12: goto L_0x014d;
                case 13: goto L_0x0149;
                case 14: goto L_0x0140;
                case 15: goto L_0x013b;
                case 16: goto L_0x012c;
                case 17: goto L_0x011d;
                default: goto L_0x0112;
            }
        L_0x0112:
            java.io.File r3 = new java.io.File
            r5 = r10[r0]
            r3.<init>(r5)
            r9.file = r3
            goto L_0x01a7
        L_0x011d:
            if (r5 == 0) goto L_0x0126
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r5)
            r9.charset = r0
            goto L_0x013d
        L_0x0126:
            java.nio.charset.Charset r3 = org.apache.poi.util.LocaleUtil.CHARSET_1252
            r9.charset = r3
            goto L_0x01a7
        L_0x012c:
            if (r5 == 0) goto L_0x0135
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.lang.String) r5)
            r9.defaultFileType = r0
            goto L_0x013d
        L_0x0135:
            org.apache.poi.poifs.filesystem.FileMagic r3 = org.apache.poi.poifs.filesystem.FileMagic.OLE2
            r9.defaultFileType = r3
            goto L_0x01a7
        L_0x013b:
            r9.slidenumStr = r5
        L_0x013d:
            r0 = r3
            goto L_0x01a7
        L_0x0140:
            if (r5 == 0) goto L_0x01a7
            float r0 = java.lang.Float.parseFloat(r5)
            r9.scale = r0
            goto L_0x013d
        L_0x0149:
            r9.quiet = r4
            goto L_0x01a7
        L_0x014d:
            if (r5 == 0) goto L_0x0152
            r9.fontTtf = r5
            goto L_0x013d
        L_0x0152:
            r9.fontTtf = r1
            goto L_0x01a7
        L_0x0155:
            if (r5 == 0) goto L_0x015a
            r9.fontMap = r5
            goto L_0x013d
        L_0x015a:
            r9.fontMap = r1
            goto L_0x01a7
        L_0x015d:
            if (r5 == 0) goto L_0x0162
            r9.fontDir = r5
            goto L_0x013d
        L_0x0162:
            r9.fontDir = r1
            goto L_0x01a7
        L_0x0165:
            r9.outPattern = r5
            goto L_0x013d
        L_0x0168:
            if (r5 == 0) goto L_0x01a7
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            r9.outdir = r0
            goto L_0x013d
        L_0x0172:
            if (r5 == 0) goto L_0x017d
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r5.toLowerCase(r0)
            r9.fixSide = r0
            goto L_0x013d
        L_0x017d:
            java.lang.String r3 = "long"
            r9.fixSide = r3
            goto L_0x01a7
        L_0x0182:
            if (r5 == 0) goto L_0x018c
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            r9.dumpfile = r0
            goto L_0x013d
        L_0x018c:
            java.io.File r3 = new java.io.File
            java.lang.String r5 = "pptx2png.dump"
            r3.<init>(r5)
            r9.dumpfile = r3
            goto L_0x01a7
        L_0x0196:
            r9.format = r5
            goto L_0x013d
        L_0x0199:
            r9.outfile = r5
            goto L_0x013d
        L_0x019c:
            r9.emfHeaderBounds = r4
            goto L_0x01a7
        L_0x019f:
            r9.textAsShapes = r4
            goto L_0x01a7
        L_0x01a2:
            r9.extractEmbedded = r4
            goto L_0x01a7
        L_0x01a5:
            r9.ignoreParse = r4
        L_0x01a7:
            int r0 = r0 + r4
            goto L_0x000a
        L_0x01aa:
            java.io.File r10 = r9.file
            if (r10 == 0) goto L_0x01bc
            java.lang.String r0 = "stdin"
            java.lang.String r10 = r10.getName()
            boolean r10 = r0.equalsIgnoreCase(r10)
            if (r10 == 0) goto L_0x01bc
            r10 = r4
            goto L_0x01bd
        L_0x01bc:
            r10 = r2
        L_0x01bd:
            if (r10 != 0) goto L_0x01cf
            java.io.File r0 = r9.file
            if (r0 == 0) goto L_0x01c9
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x01cf
        L_0x01c9:
            java.lang.String r9 = "File not specified or it doesn't exist"
            usage(r9)
            return r2
        L_0x01cf:
            java.lang.String r0 = r9.format
            if (r0 == 0) goto L_0x0242
            java.lang.String r1 = "^(png|gif|jpg|null|svg|pdf|log)$"
            boolean r0 = r0.matches(r1)
            if (r0 != 0) goto L_0x01dc
            goto L_0x0242
        L_0x01dc:
            java.io.File r0 = r9.outdir
            if (r0 != 0) goto L_0x01f4
            if (r10 == 0) goto L_0x01e8
            java.lang.String r9 = "When reading from STDIN, you need to specify an outdir."
            usage(r9)
            return r2
        L_0x01e8:
            java.io.File r10 = r9.file
            java.io.File r10 = r10.getAbsoluteFile()
            java.io.File r10 = r10.getParentFile()
            r9.outdir = r10
        L_0x01f4:
            java.io.File r10 = r9.outdir
            boolean r10 = r10.exists()
            if (r10 != 0) goto L_0x0202
            java.lang.String r9 = "Outdir doesn't exist"
            usage(r9)
            return r2
        L_0x0202:
            java.lang.String r10 = "null"
            java.lang.String r0 = r9.format
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0224
            java.io.File r10 = r9.outdir
            if (r10 == 0) goto L_0x021e
            boolean r10 = r10.exists()
            if (r10 == 0) goto L_0x021e
            java.io.File r10 = r9.outdir
            boolean r10 = r10.isDirectory()
            if (r10 != 0) goto L_0x0224
        L_0x021e:
            java.lang.String r9 = "Output directory doesn't exist"
            usage(r9)
            return r2
        L_0x0224:
            float r10 = r9.scale
            r0 = 0
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 >= 0) goto L_0x0231
            java.lang.String r9 = "Invalid scale given"
            usage(r9)
            return r2
        L_0x0231:
            java.lang.String r10 = "long,short,width,height,scale"
            java.lang.String r9 = r9.fixSide
            boolean r9 = r10.contains(r9)
            if (r9 != 0) goto L_0x0241
            java.lang.String r9 = "<fixside> must be one of long / short / width / height / scale"
            usage(r9)
            return r2
        L_0x0241:
            return r4
        L_0x0242:
            java.lang.String r9 = "Invalid format given"
            usage(r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.parseCommandLine(java.lang.String[]):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0196, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0197, code lost:
        if (r6 != null) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x019d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01a1, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01a4, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01a5, code lost:
        if (r1 != null) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01af, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processFile() throws java.io.IOException {
        /*
            r15 = this;
            java.lang.String r0 = "slidenum must be either -1 (for all) or within range: [1.."
            boolean r1 = r15.quiet
            if (r1 != 0) goto L_0x001c
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Processing "
            r2.<init>(r3)
            java.io.File r3 = r15.file
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
        L_0x001c:
            java.io.File r1 = r15.file     // Catch:{ NoScratchpadException -> 0x01b0 }
            org.apache.poi.xslf.util.MFProxy r1 = r15.initProxy(r1)     // Catch:{ NoScratchpadException -> 0x01b0 }
            java.lang.String r2 = r15.slidenumStr     // Catch:{ all -> 0x01a2 }
            java.util.Set r2 = r1.slideIndexes(r2)     // Catch:{ all -> 0x01a2 }
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x01a2 }
            if (r3 == 0) goto L_0x0054
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a2 }
            r2.<init>(r0)     // Catch:{ all -> 0x01a2 }
            int r0 = r1.getSlideCount()     // Catch:{ all -> 0x01a2 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x01a2 }
            java.lang.String r2 = "] for "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a2 }
            java.io.File r2 = r15.file     // Catch:{ all -> 0x01a2 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01a2 }
            usage(r0)     // Catch:{ all -> 0x01a2 }
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ NoScratchpadException -> 0x01b0 }
        L_0x0053:
            return
        L_0x0054:
            org.apache.poi.util.Dimension2DDouble r0 = new org.apache.poi.util.Dimension2DDouble     // Catch:{ all -> 0x01a2 }
            r0.<init>()     // Catch:{ all -> 0x01a2 }
            double r3 = r15.getDimensions(r1, r0)     // Catch:{ all -> 0x01a2 }
            double r5 = r0.getWidth()     // Catch:{ all -> 0x01a2 }
            double r5 = java.lang.Math.rint(r5)     // Catch:{ all -> 0x01a2 }
            int r5 = (int) r5     // Catch:{ all -> 0x01a2 }
            r6 = 1
            int r5 = java.lang.Math.max(r5, r6)     // Catch:{ all -> 0x01a2 }
            double r7 = r0.getHeight()     // Catch:{ all -> 0x01a2 }
            double r7 = java.lang.Math.rint(r7)     // Catch:{ all -> 0x01a2 }
            int r0 = (int) r7     // Catch:{ all -> 0x01a2 }
            int r0 = java.lang.Math.max(r0, r6)     // Catch:{ all -> 0x01a2 }
            org.apache.poi.xslf.util.OutputFormat r6 = r15.getOutput()     // Catch:{ all -> 0x01a2 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0194 }
        L_0x0080:
            boolean r7 = r2.hasNext()     // Catch:{ all -> 0x0194 }
            r8 = 0
            if (r7 == 0) goto L_0x0170
            java.lang.Object r7 = r2.next()     // Catch:{ all -> 0x0194 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x0194 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x0194 }
            r1.setSlideNo(r7)     // Catch:{ all -> 0x0194 }
            boolean r9 = r15.quiet     // Catch:{ all -> 0x0194 }
            if (r9 != 0) goto L_0x00d4
            java.lang.String r9 = r1.getTitle()     // Catch:{ all -> 0x0194 }
            java.io.PrintStream r10 = java.lang.System.out     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0194 }
            r11.<init>()     // Catch:{ all -> 0x0194 }
            java.lang.String r12 = "Rendering slide "
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r11 = r11.append(r7)     // Catch:{ all -> 0x0194 }
            if (r9 != 0) goto L_0x00b2
            java.lang.String r9 = ""
            goto L_0x00c9
        L_0x00b2:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0194 }
            r12.<init>()     // Catch:{ all -> 0x0194 }
            java.lang.String r13 = ": "
            java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ all -> 0x0194 }
            java.lang.String r9 = r9.trim()     // Catch:{ all -> 0x0194 }
            java.lang.StringBuilder r9 = r12.append(r9)     // Catch:{ all -> 0x0194 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0194 }
        L_0x00c9:
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch:{ all -> 0x0194 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0194 }
            r10.println(r9)     // Catch:{ all -> 0x0194 }
        L_0x00d4:
            r15.dumpRecords(r1)     // Catch:{ all -> 0x0194 }
            r15.extractEmbedded(r1, r7)     // Catch:{ all -> 0x0194 }
            double r9 = (double) r5     // Catch:{ all -> 0x0194 }
            double r11 = (double) r0     // Catch:{ all -> 0x0194 }
            java.awt.Graphics2D r9 = r6.addSlide(r9, r11)     // Catch:{ all -> 0x0194 }
            java.awt.RenderingHints$Key r10 = java.awt.RenderingHints.KEY_ANTIALIASING     // Catch:{ all -> 0x0194 }
            java.lang.Object r11 = java.awt.RenderingHints.VALUE_ANTIALIAS_ON     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            java.awt.RenderingHints$Key r10 = java.awt.RenderingHints.KEY_RENDERING     // Catch:{ all -> 0x0194 }
            java.lang.Object r11 = java.awt.RenderingHints.VALUE_RENDER_QUALITY     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            java.awt.RenderingHints$Key r10 = java.awt.RenderingHints.KEY_COLOR_RENDERING     // Catch:{ all -> 0x0194 }
            java.lang.Object r11 = java.awt.RenderingHints.VALUE_COLOR_RENDER_QUALITY     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            java.awt.RenderingHints$Key r10 = java.awt.RenderingHints.KEY_INTERPOLATION     // Catch:{ all -> 0x0194 }
            java.lang.Object r11 = java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            java.awt.RenderingHints$Key r10 = java.awt.RenderingHints.KEY_FRACTIONALMETRICS     // Catch:{ all -> 0x0194 }
            java.lang.Object r11 = java.awt.RenderingHints.VALUE_FRACTIONALMETRICS_ON     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            org.apache.poi.sl.draw.Drawable$DrawableHint r10 = org.apache.poi.sl.draw.Drawable.DEFAULT_CHARSET     // Catch:{ all -> 0x0194 }
            java.nio.charset.Charset r11 = r15.getDefaultCharset()     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            org.apache.poi.sl.draw.Drawable$DrawableHint r10 = org.apache.poi.sl.draw.Drawable.EMF_FORCE_HEADER_BOUNDS     // Catch:{ all -> 0x0194 }
            boolean r11 = r15.emfHeaderBounds     // Catch:{ all -> 0x0194 }
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r11)     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r10, r11)     // Catch:{ all -> 0x0194 }
            java.lang.String r10 = r15.fontMap     // Catch:{ all -> 0x0194 }
            if (r10 == 0) goto L_0x0147
            java.lang.String r11 = ";"
            java.lang.String[] r10 = r10.split(r11)     // Catch:{ all -> 0x0194 }
            java.util.stream.Stream r10 = java.util.Arrays.stream(r10)     // Catch:{ all -> 0x0194 }
            org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda2 r11 = new org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda2     // Catch:{ all -> 0x0194 }
            r11.<init>()     // Catch:{ all -> 0x0194 }
            java.util.stream.Stream r10 = r10.map(r11)     // Catch:{ all -> 0x0194 }
            org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda3 r11 = new org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda3     // Catch:{ all -> 0x0194 }
            r11.<init>()     // Catch:{ all -> 0x0194 }
            org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda4 r12 = new org.apache.poi.xslf.util.PPTX2PNG$$ExternalSyntheticLambda4     // Catch:{ all -> 0x0194 }
            r12.<init>()     // Catch:{ all -> 0x0194 }
            java.util.stream.Collector r11 = java.util.stream.Collectors.toMap(r11, r12)     // Catch:{ all -> 0x0194 }
            java.lang.Object r10 = r10.collect(r11)     // Catch:{ all -> 0x0194 }
            java.util.Map r10 = (java.util.Map) r10     // Catch:{ all -> 0x0194 }
            org.apache.poi.sl.draw.Drawable$DrawableHint r11 = org.apache.poi.sl.draw.Drawable.FONT_MAP     // Catch:{ all -> 0x0194 }
            r9.setRenderingHint(r11, r10)     // Catch:{ all -> 0x0194 }
        L_0x0147:
            float r10 = r15.scale     // Catch:{ all -> 0x0194 }
            double r11 = (double) r10     // Catch:{ all -> 0x0194 }
            double r11 = r11 / r3
            double r13 = (double) r10     // Catch:{ all -> 0x0194 }
            double r13 = r13 / r3
            r9.scale(r11, r13)     // Catch:{ all -> 0x0194 }
            java.awt.AlphaComposite r10 = java.awt.AlphaComposite.Clear     // Catch:{ all -> 0x0194 }
            r9.setComposite(r10)     // Catch:{ all -> 0x0194 }
            r9.fillRect(r8, r8, r5, r0)     // Catch:{ all -> 0x0194 }
            java.awt.AlphaComposite r8 = java.awt.AlphaComposite.SrcOver     // Catch:{ all -> 0x0194 }
            r9.setComposite(r8)     // Catch:{ all -> 0x0194 }
            r1.draw(r9)     // Catch:{ all -> 0x0194 }
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x0194 }
            java.io.File r9 = r15.outdir     // Catch:{ all -> 0x0194 }
            java.lang.String r7 = r15.calcOutFile(r1, r7)     // Catch:{ all -> 0x0194 }
            r8.<init>(r9, r7)     // Catch:{ all -> 0x0194 }
            r6.writeSlide(r1, r8)     // Catch:{ all -> 0x0194 }
            goto L_0x0080
        L_0x0170:
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0194 }
            java.io.File r2 = r15.outdir     // Catch:{ all -> 0x0194 }
            java.lang.String r3 = r15.calcOutFile(r1, r8)     // Catch:{ all -> 0x0194 }
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0194 }
            r6.writeDocument(r1, r0)     // Catch:{ all -> 0x0194 }
            if (r6 == 0) goto L_0x0183
            r6.close()     // Catch:{ all -> 0x01a2 }
        L_0x0183:
            if (r1 == 0) goto L_0x0188
            r1.close()     // Catch:{ NoScratchpadException -> 0x01b0 }
        L_0x0188:
            boolean r15 = r15.quiet
            if (r15 != 0) goto L_0x0193
            java.io.PrintStream r15 = java.lang.System.out
            java.lang.String r0 = "Done"
            r15.println(r0)
        L_0x0193:
            return
        L_0x0194:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0196 }
        L_0x0196:
            r2 = move-exception
            if (r6 == 0) goto L_0x01a1
            r6.close()     // Catch:{ all -> 0x019d }
            goto L_0x01a1
        L_0x019d:
            r3 = move-exception
            r0.addSuppressed(r3)     // Catch:{ all -> 0x01a2 }
        L_0x01a1:
            throw r2     // Catch:{ all -> 0x01a2 }
        L_0x01a2:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x01a4 }
        L_0x01a4:
            r2 = move-exception
            if (r1 == 0) goto L_0x01af
            r1.close()     // Catch:{ all -> 0x01ab }
            goto L_0x01af
        L_0x01ab:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch:{ NoScratchpadException -> 0x01b0 }
        L_0x01af:
            throw r2     // Catch:{ NoScratchpadException -> 0x01b0 }
        L_0x01b0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "'"
            r0.<init>(r1)
            java.io.File r15 = r15.file
            java.lang.String r15 = r15.getName()
            java.lang.StringBuilder r15 = r0.append(r15)
            java.lang.String r0 = "': Format not supported - try to include poi-scratchpad.jar into the CLASSPATH."
            java.lang.StringBuilder r15 = r15.append(r0)
            java.lang.String r15 = r15.toString()
            usage(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.processFile():void");
    }

    static /* synthetic */ String lambda$processFile$1(String[] strArr) {
        return strArr[0];
    }

    static /* synthetic */ String lambda$processFile$2(String[] strArr) {
        return strArr[1];
    }

    private OutputFormat getOutput() {
        String str = this.format;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 107332:
                if (str.equals("log")) {
                    c = 0;
                    break;
                }
                break;
            case 110834:
                if (str.equals("pdf")) {
                    c = 1;
                    break;
                }
                break;
            case 114276:
                if (str.equals("svg")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new DummyFormat();
            case 1:
                return new PDFFormat(this.textAsShapes, this.fontDir, this.fontTtf);
            case 2:
                try {
                    return new SVGFormat(this.textAsShapes);
                } catch (Exception | NoClassDefFoundError e) {
                    LOG.atError().withThrowable(e).log("Batik is not not added to/working on the module-path. Use classpath mode instead of JPMS. Fallback to PNG.");
                    return new BitmapFormat(ContentTypes.EXTENSION_PNG);
                }
            default:
                return new BitmapFormat(this.format);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double getDimensions(org.apache.poi.xslf.util.MFProxy r7, java.awt.geom.Dimension2D r8) {
        /*
            r6 = this;
            java.awt.geom.Dimension2D r7 = r7.getSize()
            java.lang.String r0 = r6.fixSide
            int r1 = r0.hashCode()
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            switch(r1) {
                case -1221029593: goto L_0x003a;
                case 3327612: goto L_0x0030;
                case 109250890: goto L_0x0026;
                case 109413500: goto L_0x001c;
                case 113126854: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0044
        L_0x0012:
            java.lang.String r1 = "width"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r0 = r3
            goto L_0x0045
        L_0x001c:
            java.lang.String r1 = "short"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r0 = r4
            goto L_0x0045
        L_0x0026:
            java.lang.String r1 = "scale"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r0 = 1
            goto L_0x0045
        L_0x0030:
            java.lang.String r1 = "long"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r0 = r5
            goto L_0x0045
        L_0x003a:
            java.lang.String r1 = "height"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0044
            r0 = r2
            goto L_0x0045
        L_0x0044:
            r0 = -1
        L_0x0045:
            if (r0 == r5) goto L_0x0067
            if (r0 == r4) goto L_0x005a
            if (r0 == r3) goto L_0x0055
            if (r0 == r2) goto L_0x0050
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0073
        L_0x0050:
            double r0 = r7.getHeight()
            goto L_0x0073
        L_0x0055:
            double r0 = r7.getWidth()
            goto L_0x0073
        L_0x005a:
            double r0 = r7.getWidth()
            double r2 = r7.getHeight()
            double r0 = java.lang.Math.min(r0, r2)
            goto L_0x0073
        L_0x0067:
            double r0 = r7.getWidth()
            double r2 = r7.getHeight()
            double r0 = java.lang.Math.max(r0, r2)
        L_0x0073:
            double r2 = r7.getWidth()
            float r4 = r6.scale
            double r4 = (double) r4
            double r2 = r2 * r4
            double r2 = r2 / r0
            double r4 = r7.getHeight()
            float r6 = r6.scale
            double r6 = (double) r6
            double r4 = r4 * r6
            double r4 = r4 / r0
            r8.setSize(r2, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.getDimensions(org.apache.poi.xslf.util.MFProxy, java.awt.geom.Dimension2D):double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void dumpRecords(org.apache.poi.xslf.util.MFProxy r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.File r0 = r2.dumpfile
            if (r0 == 0) goto L_0x004e
            java.lang.String r1 = "null"
            java.lang.String r0 = r0.getPath()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0011
            goto L_0x004e
        L_0x0011:
            org.apache.poi.common.usermodel.GenericRecord r3 = r3.getRoot()
            org.apache.poi.xslf.util.PPTX2PNG$1 r0 = new org.apache.poi.xslf.util.PPTX2PNG$1
            java.io.File r1 = r2.dumpfile
            r0.<init>(r1)
            if (r3 != 0) goto L_0x003b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r3.<init>()     // Catch:{ all -> 0x0042 }
            java.io.File r2 = r2.file     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = r2.getName()     // Catch:{ all -> 0x0042 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = " doesn't support GenericRecord interface and can't be dumped to a file."
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0042 }
            r0.writeError(r2)     // Catch:{ all -> 0x0042 }
            goto L_0x003e
        L_0x003b:
            r0.write(r3)     // Catch:{ all -> 0x0042 }
        L_0x003e:
            r0.close()
            return
        L_0x0042:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x004d:
            throw r3
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.dumpRecords(org.apache.poi.xslf.util.MFProxy):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        r7.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void extractEmbedded(org.apache.poi.xslf.util.MFProxy r8, int r9) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.extractEmbedded
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Iterable r0 = r8.getEmbeddings(r9)
            java.util.Iterator r0 = r0.iterator()
        L_0x000d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0076
            java.lang.Object r1 = r0.next()
            org.apache.poi.sl.draw.EmbeddedExtractor$EmbeddedPart r1 = (org.apache.poi.sl.draw.EmbeddedExtractor.EmbeddedPart) r1
            java.lang.String r2 = r1.getName()
            java.io.File r3 = new java.io.File
            if (r2 != 0) goto L_0x0023
            java.lang.String r2 = "dummy.dat"
        L_0x0023:
            r3.<init>(r2)
            java.lang.String r2 = r3.getName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r7.calcOutFile(r8, r9)
            java.lang.String r5 = "\\.\\w+$"
            java.lang.String r6 = ""
            java.lang.String r4 = r4.replaceFirst(r5, r6)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = "_"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            java.io.FileOutputStream r3 = new java.io.FileOutputStream
            java.io.File r4 = new java.io.File
            java.io.File r5 = r7.outdir
            r4.<init>(r5, r2)
            r3.<init>(r4)
            java.util.function.Supplier r1 = r1.getData()     // Catch:{ all -> 0x006a }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x006a }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x006a }
            r3.write(r1)     // Catch:{ all -> 0x006a }
            r3.close()
            goto L_0x000d
        L_0x006a:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x006c }
        L_0x006c:
            r8 = move-exception
            r3.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r9 = move-exception
            r7.addSuppressed(r9)
        L_0x0075:
            throw r8
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.extractEmbedded(org.apache.poi.xslf.util.MFProxy, int):void");
    }

    private MFProxy initProxy(File file2) throws IOException {
        ProxyConsumer proxyConsumer;
        FileMagic fileMagic;
        MFProxy mFProxy;
        if ("stdin".equals(file2.getName().toLowerCase(Locale.ROOT))) {
            InputStream prepareToCheckMagic = FileMagic.prepareToCheckMagic(System.in);
            fileMagic = FileMagic.valueOf(prepareToCheckMagic);
            proxyConsumer = new PPTX2PNG$$ExternalSyntheticLambda0(prepareToCheckMagic);
        } else {
            fileMagic = FileMagic.valueOf(file2);
            proxyConsumer = new PPTX2PNG$$ExternalSyntheticLambda1(file2);
        }
        if (fileMagic == FileMagic.UNKNOWN) {
            fileMagic = this.defaultFileType;
        }
        int i = AnonymousClass2.$SwitchMap$org$apache$poi$poifs$filesystem$FileMagic[fileMagic.ordinal()];
        if (i == 1) {
            mFProxy = new EMFHandler();
        } else if (i != 2) {
            mFProxy = new PPTHandler();
        } else {
            mFProxy = new WMFHandler();
        }
        mFProxy.setIgnoreParse(this.ignoreParse);
        mFProxy.setQuiet(this.quiet);
        proxyConsumer.parse(mFProxy);
        mFProxy.setDefaultCharset(this.charset);
        return mFProxy;
    }

    /* renamed from: org.apache.poi.xslf.util.PPTX2PNG$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.poifs.filesystem.FileMagic[] r0 = org.apache.poi.poifs.filesystem.FileMagic.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic = r0
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.EMF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$poifs$filesystem$FileMagic     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.WMF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.PPTX2PNG.AnonymousClass2.<clinit>():void");
        }
    }

    private String calcOutFile(MFProxy mFProxy, int i) {
        String str = this.outfile;
        if (str != null) {
            return str;
        }
        String name = this.file.getName();
        if ("stdin".equals(name)) {
            name = name + ".ext";
        }
        return INPUT_PATTERN.matcher(String.format(Locale.ROOT, "%04d|%s|%s", new Object[]{Integer.valueOf(i), this.format, name})).replaceAll((mFProxy.getSlideCount() <= 1 || i <= 0) ? this.outPattern.replaceAll("-?\\$\\{slideno}", "") : this.outPattern);
    }

    private Charset getDefaultCharset() {
        return this.charset;
    }

    static class NoScratchpadException extends IOException {
        NoScratchpadException() {
        }

        NoScratchpadException(Throwable th) {
            super(th);
        }
    }
}
