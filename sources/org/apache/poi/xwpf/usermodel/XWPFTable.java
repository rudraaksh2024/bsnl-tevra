package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class XWPFTable implements IBodyElement, ISDTContents {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_PERCENTAGE_WIDTH = "100%";
    public static final String REGEX_PERCENTAGE = "[0-9]+(\\.[0-9]+)?%";
    public static final String REGEX_WIDTH_VALUE = "auto|[0-9]+|[0-9]+(\\.[0-9]+)?%";
    private static final HashMap<Integer, XWPFBorderType> stBorderTypeMap;
    private static final EnumMap<XWPFBorderType, STBorder.Enum> xwpfBorderTypeMap;
    private final CTTbl ctTbl;
    protected IBody part;
    protected final List<XWPFTableRow> tableRows;
    protected StringBuilder text;

    private enum Border {
        INSIDE_V,
        INSIDE_H,
        LEFT,
        TOP,
        BOTTOM,
        RIGHT
    }

    public enum XWPFBorderType {
        NIL,
        NONE,
        SINGLE,
        THICK,
        DOUBLE,
        DOTTED,
        DASHED,
        DOT_DASH,
        DOT_DOT_DASH,
        TRIPLE,
        THIN_THICK_SMALL_GAP,
        THICK_THIN_SMALL_GAP,
        THIN_THICK_THIN_SMALL_GAP,
        THIN_THICK_MEDIUM_GAP,
        THICK_THIN_MEDIUM_GAP,
        THIN_THICK_THIN_MEDIUM_GAP,
        THIN_THICK_LARGE_GAP,
        THICK_THIN_LARGE_GAP,
        THIN_THICK_THIN_LARGE_GAP,
        WAVE,
        DOUBLE_WAVE,
        DASH_SMALL_GAP,
        DASH_DOT_STROKED,
        THREE_D_EMBOSS,
        THREE_D_ENGRAVE,
        OUTSET,
        INSET
    }

    static {
        EnumMap<XWPFBorderType, STBorder.Enum> enumMap = new EnumMap<>(XWPFBorderType.class);
        xwpfBorderTypeMap = enumMap;
        enumMap.put(XWPFBorderType.NIL, STBorder.Enum.forInt(1));
        enumMap.put(XWPFBorderType.NONE, STBorder.Enum.forInt(2));
        enumMap.put(XWPFBorderType.SINGLE, STBorder.Enum.forInt(3));
        enumMap.put(XWPFBorderType.THICK, STBorder.Enum.forInt(4));
        enumMap.put(XWPFBorderType.DOUBLE, STBorder.Enum.forInt(5));
        enumMap.put(XWPFBorderType.DOTTED, STBorder.Enum.forInt(6));
        enumMap.put(XWPFBorderType.DASHED, STBorder.Enum.forInt(7));
        enumMap.put(XWPFBorderType.DOT_DASH, STBorder.Enum.forInt(8));
        enumMap.put(XWPFBorderType.DOT_DOT_DASH, STBorder.Enum.forInt(9));
        enumMap.put(XWPFBorderType.TRIPLE, STBorder.Enum.forInt(10));
        enumMap.put(XWPFBorderType.THIN_THICK_SMALL_GAP, STBorder.Enum.forInt(11));
        enumMap.put(XWPFBorderType.THICK_THIN_SMALL_GAP, STBorder.Enum.forInt(12));
        enumMap.put(XWPFBorderType.THIN_THICK_THIN_SMALL_GAP, STBorder.Enum.forInt(13));
        enumMap.put(XWPFBorderType.THIN_THICK_MEDIUM_GAP, STBorder.Enum.forInt(14));
        enumMap.put(XWPFBorderType.THICK_THIN_MEDIUM_GAP, STBorder.Enum.forInt(15));
        enumMap.put(XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP, STBorder.Enum.forInt(16));
        enumMap.put(XWPFBorderType.THIN_THICK_LARGE_GAP, STBorder.Enum.forInt(17));
        enumMap.put(XWPFBorderType.THICK_THIN_LARGE_GAP, STBorder.Enum.forInt(18));
        enumMap.put(XWPFBorderType.THIN_THICK_THIN_LARGE_GAP, STBorder.Enum.forInt(19));
        enumMap.put(XWPFBorderType.WAVE, STBorder.Enum.forInt(20));
        enumMap.put(XWPFBorderType.DOUBLE_WAVE, STBorder.Enum.forInt(21));
        enumMap.put(XWPFBorderType.DASH_SMALL_GAP, STBorder.Enum.forInt(22));
        enumMap.put(XWPFBorderType.DASH_DOT_STROKED, STBorder.Enum.forInt(23));
        enumMap.put(XWPFBorderType.THREE_D_EMBOSS, STBorder.Enum.forInt(24));
        enumMap.put(XWPFBorderType.THREE_D_ENGRAVE, STBorder.Enum.forInt(25));
        enumMap.put(XWPFBorderType.OUTSET, STBorder.Enum.forInt(26));
        enumMap.put(XWPFBorderType.INSET, STBorder.Enum.forInt(27));
        HashMap<Integer, XWPFBorderType> hashMap = new HashMap<>();
        stBorderTypeMap = hashMap;
        hashMap.put(1, XWPFBorderType.NIL);
        hashMap.put(2, XWPFBorderType.NONE);
        hashMap.put(3, XWPFBorderType.SINGLE);
        hashMap.put(4, XWPFBorderType.THICK);
        hashMap.put(5, XWPFBorderType.DOUBLE);
        hashMap.put(6, XWPFBorderType.DOTTED);
        hashMap.put(7, XWPFBorderType.DASHED);
        hashMap.put(8, XWPFBorderType.DOT_DASH);
        hashMap.put(9, XWPFBorderType.DOT_DOT_DASH);
        hashMap.put(10, XWPFBorderType.TRIPLE);
        hashMap.put(11, XWPFBorderType.THIN_THICK_SMALL_GAP);
        hashMap.put(12, XWPFBorderType.THICK_THIN_SMALL_GAP);
        hashMap.put(13, XWPFBorderType.THIN_THICK_THIN_SMALL_GAP);
        hashMap.put(14, XWPFBorderType.THIN_THICK_MEDIUM_GAP);
        hashMap.put(15, XWPFBorderType.THICK_THIN_MEDIUM_GAP);
        hashMap.put(16, XWPFBorderType.THIN_THICK_THIN_MEDIUM_GAP);
        hashMap.put(17, XWPFBorderType.THIN_THICK_LARGE_GAP);
        hashMap.put(18, XWPFBorderType.THICK_THIN_LARGE_GAP);
        hashMap.put(19, XWPFBorderType.THIN_THICK_THIN_LARGE_GAP);
        hashMap.put(20, XWPFBorderType.WAVE);
        hashMap.put(21, XWPFBorderType.DOUBLE_WAVE);
        hashMap.put(22, XWPFBorderType.DASH_SMALL_GAP);
        hashMap.put(23, XWPFBorderType.DASH_DOT_STROKED);
        hashMap.put(24, XWPFBorderType.THREE_D_EMBOSS);
        hashMap.put(25, XWPFBorderType.THREE_D_ENGRAVE);
        hashMap.put(26, XWPFBorderType.OUTSET);
        hashMap.put(27, XWPFBorderType.INSET);
    }

    public XWPFTable(CTTbl cTTbl, IBody iBody, int i, int i2) {
        this(cTTbl, iBody);
        for (int i3 = 0; i3 < i; i3++) {
            XWPFTableRow createRow = getRow(i3) == null ? createRow() : getRow(i3);
            for (int i4 = 0; i4 < i2; i4++) {
                if (createRow.getCell(i4) == null) {
                    createRow.createCell();
                }
            }
        }
    }

    public XWPFTable(CTTbl cTTbl, IBody iBody) {
        this.text = new StringBuilder(64);
        this.tableRows = new ArrayList();
        this.part = iBody;
        this.ctTbl = cTTbl;
        if (cTTbl.sizeOfTrArray() == 0) {
            createEmptyTable(cTTbl);
        }
        for (CTRow next : cTTbl.getTrList()) {
            StringBuilder sb = new StringBuilder();
            this.tableRows.add(new XWPFTableRow(next, this));
            for (CTTc pList : next.getTcList()) {
                for (CTP xWPFParagraph : pList.getPList()) {
                    XWPFParagraph xWPFParagraph2 = new XWPFParagraph(xWPFParagraph, iBody);
                    if (sb.length() > 0) {
                        sb.append(9);
                    }
                    sb.append(xWPFParagraph2.getText());
                }
            }
            if (sb.length() > 0) {
                this.text.append(sb);
                this.text.append(10);
            }
        }
    }

    private void createEmptyTable(CTTbl cTTbl) {
        cTTbl.addNewTr().addNewTc().addNewP();
        CTTblPr addNewTblPr = cTTbl.addNewTblPr();
        addNewTblPr.addNewTblW().setW(BigInteger.valueOf(0));
        addNewTblPr.getTblW().setType(STTblWidth.AUTO);
        CTTblBorders addNewTblBorders = addNewTblPr.addNewTblBorders();
        addNewTblBorders.addNewBottom().setVal(STBorder.SINGLE);
        addNewTblBorders.addNewInsideH().setVal(STBorder.SINGLE);
        addNewTblBorders.addNewInsideV().setVal(STBorder.SINGLE);
        addNewTblBorders.addNewLeft().setVal(STBorder.SINGLE);
        addNewTblBorders.addNewRight().setVal(STBorder.SINGLE);
        addNewTblBorders.addNewTop().setVal(STBorder.SINGLE);
    }

    @Internal
    public CTTbl getCTTbl() {
        return this.ctTbl;
    }

    public String getText() {
        return this.text.toString();
    }

    public void addNewCol() {
        if (this.tableRows.isEmpty()) {
            createRow();
        }
        for (XWPFTableRow createCell : this.tableRows) {
            createCell.createCell();
        }
    }

    public XWPFTableRow createRow() {
        int i = 0;
        if (this.ctTbl.sizeOfTrArray() > 0) {
            i = this.ctTbl.getTrArray(0).sizeOfTcArray();
        }
        XWPFTableRow xWPFTableRow = new XWPFTableRow(this.ctTbl.addNewTr(), this);
        addColumn(xWPFTableRow, i);
        this.tableRows.add(xWPFTableRow);
        return xWPFTableRow;
    }

    public XWPFTableRow getRow(int i) {
        if (i < 0 || i >= this.ctTbl.sizeOfTrArray()) {
            return null;
        }
        return getRows().get(i);
    }

    public int getWidth() {
        CTTblPr tblPr = getTblPr();
        if (tblPr.isSetTblW()) {
            return (int) Units.toDXA(POIXMLUnits.parseLength(tblPr.getTblW().xgetW()));
        }
        return -1;
    }

    public void setWidth(int i) {
        CTTblPr tblPr = getTblPr();
        CTTblWidth tblW = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        tblW.setW(new BigInteger(Integer.toString(i)));
        tblW.setType(STTblWidth.DXA);
    }

    public int getNumberOfRows() {
        return this.ctTbl.sizeOfTrArray();
    }

    private CTTblPr getTblPr() {
        return getTblPr(true);
    }

    private CTTblPr getTblPr(boolean z) {
        if (this.ctTbl.getTblPr() != null) {
            return this.ctTbl.getTblPr();
        }
        if (z) {
            return this.ctTbl.addNewTblPr();
        }
        return null;
    }

    private CTTblBorders getTblBorders(boolean z) {
        CTTblPr tblPr = getTblPr(z);
        if (tblPr == null) {
            return null;
        }
        if (tblPr.isSetTblBorders()) {
            return tblPr.getTblBorders();
        }
        if (z) {
            return tblPr.addNewTblBorders();
        }
        return null;
    }

    /* renamed from: org.apache.poi.xwpf.usermodel.XWPFTable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.xwpf.usermodel.XWPFTable$Border[] r0 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border = r0
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.INSIDE_V     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.INSIDE_H     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.LEFT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.TOP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.RIGHT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.xwpf.usermodel.XWPFTable$Border r1 = org.apache.poi.xwpf.usermodel.XWPFTable.Border.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFTable.AnonymousClass1.<clinit>():void");
        }
    }

    private CTBorder getTblBorder(boolean z, Border border) {
        Function function;
        Function function2;
        Function function3;
        switch (AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border[border.ordinal()]) {
            case 1:
                new XWPFTable$$ExternalSyntheticLambda36
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x005f: CONSTRUCTOR  (r5v7 ? I:org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36) =  call: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36.<init>():void type: CONSTRUCTOR in method: org.apache.poi.xwpf.usermodel.XWPFTable.getTblBorder(boolean, org.apache.poi.xwpf.usermodel.XWPFTable$Border):org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:298)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:64)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                    	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r5v7 ?
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 34 more
                    */
                /*
                    this = this;
                    int[] r0 = org.apache.poi.xwpf.usermodel.XWPFTable.AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border
                    int r5 = r5.ordinal()
                    r5 = r0[r5]
                    r0 = 0
                    switch(r5) {
                        case 1: goto L_0x005d;
                        case 2: goto L_0x004d;
                        case 3: goto L_0x003d;
                        case 4: goto L_0x002d;
                        case 5: goto L_0x001d;
                        case 6: goto L_0x000d;
                        default: goto L_0x000c;
                    }
                L_0x000c:
                    return r0
                L_0x000d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda33 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda33
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda9 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda9
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda10 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda10
                    r2.<init>()
                    goto L_0x006c
                L_0x001d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda3 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda3
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda7 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda7
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda8 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda8
                    r2.<init>()
                    goto L_0x006c
                L_0x002d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda1 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda1
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda5 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda5
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda6 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda6
                    r2.<init>()
                    goto L_0x006c
                L_0x003d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda38 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda38
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda16 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda16
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda17 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda17
                    r2.<init>()
                    goto L_0x006c
                L_0x004d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda22 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda22
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda14 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda14
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda15 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda15
                    r2.<init>()
                    goto L_0x006c
                L_0x005d:
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36 r5 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36
                    r5.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda12 r1 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda12
                    r1.<init>()
                    org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda13 r2 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda13
                    r2.<init>()
                L_0x006c:
                    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders r3 = r3.getTblBorders(r4)
                    if (r3 != 0) goto L_0x0073
                    goto L_0x0090
                L_0x0073:
                    java.lang.Object r5 = r5.apply(r3)
                    java.lang.Boolean r5 = (java.lang.Boolean) r5
                    boolean r5 = r5.booleanValue()
                    if (r5 == 0) goto L_0x0087
                    java.lang.Object r3 = r1.apply(r3)
                    r0 = r3
                    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder r0 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) r0
                    goto L_0x0090
                L_0x0087:
                    if (r4 == 0) goto L_0x0090
                    java.lang.Object r3 = r2.apply(r3)
                    r0 = r3
                    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder r0 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) r0
                L_0x0090:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFTable.getTblBorder(boolean, org.apache.poi.xwpf.usermodel.XWPFTable$Border):org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder");
            }

            public TableRowAlign getTableAlignment() {
                CTTblPr tblPr = getTblPr(false);
                if (tblPr != null && tblPr.isSetJc()) {
                    return TableRowAlign.valueOf(tblPr.getJc().getVal().intValue());
                }
                return null;
            }

            public void setTableAlignment(TableRowAlign tableRowAlign) {
                CTTblPr tblPr = getTblPr(true);
                (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc()).setVal(STJcTable.Enum.forInt(tableRowAlign.getValue()));
            }

            public void removeTableAlignment() {
                CTTblPr tblPr = getTblPr(false);
                if (tblPr != null && tblPr.isSetJc()) {
                    tblPr.unsetJc();
                }
            }

            private void addColumn(XWPFTableRow xWPFTableRow, int i) {
                if (i > 0) {
                    for (int i2 = 0; i2 < i; i2++) {
                        xWPFTableRow.createCell();
                    }
                }
            }

            public String getStyleID() {
                CTString tblStyle;
                CTTblPr tblPr = this.ctTbl.getTblPr();
                if (tblPr == null || (tblStyle = tblPr.getTblStyle()) == null) {
                    return null;
                }
                return tblStyle.getVal();
            }

            public void setStyleID(String str) {
                CTTblPr tblPr = getTblPr();
                CTString tblStyle = tblPr.getTblStyle();
                if (tblStyle == null) {
                    tblStyle = tblPr.addNewTblStyle();
                }
                tblStyle.setVal(str);
            }

            public XWPFBorderType getInsideHBorderType() {
                return getBorderType(Border.INSIDE_H);
            }

            public int getInsideHBorderSize() {
                return getBorderSize(Border.INSIDE_H);
            }

            public int getInsideHBorderSpace() {
                return getBorderSpace(Border.INSIDE_H);
            }

            public String getInsideHBorderColor() {
                return getBorderColor(Border.INSIDE_H);
            }

            public XWPFBorderType getInsideVBorderType() {
                return getBorderType(Border.INSIDE_V);
            }

            public int getInsideVBorderSize() {
                return getBorderSize(Border.INSIDE_V);
            }

            public int getInsideVBorderSpace() {
                return getBorderSpace(Border.INSIDE_V);
            }

            public String getInsideVBorderColor() {
                return getBorderColor(Border.INSIDE_V);
            }

            public XWPFBorderType getTopBorderType() {
                return getBorderType(Border.TOP);
            }

            public int getTopBorderSize() {
                return getBorderSize(Border.TOP);
            }

            public int getTopBorderSpace() {
                return getBorderSpace(Border.TOP);
            }

            public String getTopBorderColor() {
                return getBorderColor(Border.TOP);
            }

            public XWPFBorderType getBottomBorderType() {
                return getBorderType(Border.BOTTOM);
            }

            public int getBottomBorderSize() {
                return getBorderSize(Border.BOTTOM);
            }

            public int getBottomBorderSpace() {
                return getBorderSpace(Border.BOTTOM);
            }

            public String getBottomBorderColor() {
                return getBorderColor(Border.BOTTOM);
            }

            public XWPFBorderType getLeftBorderType() {
                return getBorderType(Border.LEFT);
            }

            public int getLeftBorderSize() {
                return getBorderSize(Border.LEFT);
            }

            public int getLeftBorderSpace() {
                return getBorderSpace(Border.LEFT);
            }

            public String getLeftBorderColor() {
                return getBorderColor(Border.LEFT);
            }

            public XWPFBorderType getRightBorderType() {
                return getBorderType(Border.RIGHT);
            }

            public int getRightBorderSize() {
                return getBorderSize(Border.RIGHT);
            }

            public int getRightBorderSpace() {
                return getBorderSpace(Border.RIGHT);
            }

            public String getRightBorderColor() {
                return getBorderColor(Border.RIGHT);
            }

            private XWPFBorderType getBorderType(Border border) {
                CTBorder tblBorder = getTblBorder(false, border);
                if (tblBorder != null) {
                    return stBorderTypeMap.get(Integer.valueOf(tblBorder.getVal().intValue()));
                }
                return null;
            }

            private int getBorderSize(Border border) {
                CTBorder tblBorder = getTblBorder(false, border);
                if (tblBorder == null || !tblBorder.isSetSz()) {
                    return -1;
                }
                return tblBorder.getSz().intValue();
            }

            private int getBorderSpace(Border border) {
                CTBorder tblBorder = getTblBorder(false, border);
                if (tblBorder == null || !tblBorder.isSetSpace()) {
                    return -1;
                }
                return tblBorder.getSpace().intValue();
            }

            private String getBorderColor(Border border) {
                CTBorder tblBorder = getTblBorder(false, border);
                if (tblBorder == null || !tblBorder.isSetColor()) {
                    return null;
                }
                return tblBorder.xgetColor().getStringValue();
            }

            public int getRowBandSize() {
                CTTblPr tblPr = getTblPr();
                if (tblPr.isSetTblStyleRowBandSize()) {
                    return tblPr.getTblStyleRowBandSize().getVal().intValue();
                }
                return 0;
            }

            public void setRowBandSize(int i) {
                CTTblPr tblPr = getTblPr();
                (tblPr.isSetTblStyleRowBandSize() ? tblPr.getTblStyleRowBandSize() : tblPr.addNewTblStyleRowBandSize()).setVal(BigInteger.valueOf((long) i));
            }

            public int getColBandSize() {
                CTTblPr tblPr = getTblPr();
                if (tblPr.isSetTblStyleColBandSize()) {
                    return tblPr.getTblStyleColBandSize().getVal().intValue();
                }
                return 0;
            }

            public void setColBandSize(int i) {
                CTTblPr tblPr = getTblPr();
                (tblPr.isSetTblStyleColBandSize() ? tblPr.getTblStyleColBandSize() : tblPr.addNewTblStyleColBandSize()).setVal(BigInteger.valueOf((long) i));
            }

            public void setInsideHBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.INSIDE_H, xWPFBorderType, i, i2, str);
            }

            public void setInsideVBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.INSIDE_V, xWPFBorderType, i, i2, str);
            }

            public void setTopBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.TOP, xWPFBorderType, i, i2, str);
            }

            public void setBottomBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.BOTTOM, xWPFBorderType, i, i2, str);
            }

            public void setLeftBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.LEFT, xWPFBorderType, i, i2, str);
            }

            public void setRightBorder(XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                setBorder(Border.RIGHT, xWPFBorderType, i, i2, str);
            }

            private void setBorder(Border border, XWPFBorderType xWPFBorderType, int i, int i2, String str) {
                CTBorder tblBorder = getTblBorder(true, border);
                tblBorder.setVal(xwpfBorderTypeMap.get(xWPFBorderType));
                tblBorder.setSz(BigInteger.valueOf((long) i));
                tblBorder.setSpace(BigInteger.valueOf((long) i2));
                tblBorder.setColor(str);
            }

            public void removeInsideHBorder() {
                removeBorder(Border.INSIDE_H);
            }

            public void removeInsideVBorder() {
                removeBorder(Border.INSIDE_V);
            }

            public void removeTopBorder() {
                removeBorder(Border.TOP);
            }

            public void removeBottomBorder() {
                removeBorder(Border.BOTTOM);
            }

            public void removeLeftBorder() {
                removeBorder(Border.LEFT);
            }

            public void removeRightBorder() {
                removeBorder(Border.RIGHT);
            }

            public void removeBorders() {
                CTTblPr tblPr = getTblPr(false);
                if (tblPr != null && tblPr.isSetTblBorders()) {
                    tblPr.unsetTblBorders();
                }
            }

            private void removeBorder(Border border) {
                Function function;
                Consumer consumer;
                switch (AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border[border.ordinal()]) {
                    case 1:
                        new XWPFTable$$ExternalSyntheticLambda36
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0045: CONSTRUCTOR  (r3v7 ? I:org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36) =  call: org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36.<init>():void type: CONSTRUCTOR in method: org.apache.poi.xwpf.usermodel.XWPFTable.removeBorder(org.apache.poi.xwpf.usermodel.XWPFTable$Border):void, dex: classes3.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:298)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:64)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.util.ArrayList.forEach(ArrayList.java:1259)
                            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                            	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                            	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                            	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                            	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                            	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v7 ?
                            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                            	... 34 more
                            */
                        /*
                            this = this;
                            int[] r0 = org.apache.poi.xwpf.usermodel.XWPFTable.AnonymousClass1.$SwitchMap$org$apache$poi$xwpf$usermodel$XWPFTable$Border
                            int r3 = r3.ordinal()
                            r3 = r0[r3]
                            switch(r3) {
                                case 1: goto L_0x0043;
                                case 2: goto L_0x0038;
                                case 3: goto L_0x002d;
                                case 4: goto L_0x0022;
                                case 5: goto L_0x0017;
                                case 6: goto L_0x000c;
                                default: goto L_0x000b;
                            }
                        L_0x000b:
                            return
                        L_0x000c:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda33 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda33
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda34 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda34
                            r0.<init>()
                            goto L_0x004d
                        L_0x0017:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda3 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda3
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda4 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda4
                            r0.<init>()
                            goto L_0x004d
                        L_0x0022:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda1 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda1
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda2 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda2
                            r0.<init>()
                            goto L_0x004d
                        L_0x002d:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda38 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda38
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda39 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda39
                            r0.<init>()
                            goto L_0x004d
                        L_0x0038:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda22 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda22
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda35 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda35
                            r0.<init>()
                            goto L_0x004d
                        L_0x0043:
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36 r3 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda36
                            r3.<init>()
                            org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda37 r0 = new org.apache.poi.xwpf.usermodel.XWPFTable$$ExternalSyntheticLambda37
                            r0.<init>()
                        L_0x004d:
                            r1 = 0
                            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders r1 = r2.getTblBorders(r1)
                            if (r1 == 0) goto L_0x0066
                            java.lang.Object r3 = r3.apply(r1)
                            java.lang.Boolean r3 = (java.lang.Boolean) r3
                            boolean r3 = r3.booleanValue()
                            if (r3 == 0) goto L_0x0066
                            r0.accept(r1)
                            r2.cleanupTblBorders()
                        L_0x0066:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFTable.removeBorder(org.apache.poi.xwpf.usermodel.XWPFTable$Border):void");
                    }

                    private void cleanupTblBorders() {
                        CTTblPr tblPr = getTblPr(false);
                        if (tblPr != null && tblPr.isSetTblBorders()) {
                            CTTblBorders tblBorders = tblPr.getTblBorders();
                            if (!tblBorders.isSetInsideH() && !tblBorders.isSetInsideV() && !tblBorders.isSetTop() && !tblBorders.isSetBottom() && !tblBorders.isSetLeft() && !tblBorders.isSetRight()) {
                                tblPr.unsetTblBorders();
                            }
                        }
                    }

                    public int getCellMarginTop() {
                        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda26());
                    }

                    public int getCellMarginLeft() {
                        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda11());
                    }

                    public int getCellMarginBottom() {
                        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda0());
                    }

                    public int getCellMarginRight() {
                        return getCellMargin(new XWPFTable$$ExternalSyntheticLambda23());
                    }

                    private int getCellMargin(Function<CTTblCellMar, CTTblWidth> function) {
                        CTTblWidth apply;
                        CTTblCellMar tblCellMar = getTblPr().getTblCellMar();
                        if (tblCellMar == null || (apply = function.apply(tblCellMar)) == null) {
                            return 0;
                        }
                        return (int) Units.toDXA(POIXMLUnits.parseLength(apply.xgetW()));
                    }

                    public void setCellMargins(int i, int i2, int i3, int i4) {
                        CTTblPr tblPr = getTblPr();
                        CTTblCellMar tblCellMar = tblPr.isSetTblCellMar() ? tblPr.getTblCellMar() : tblPr.addNewTblCellMar();
                        setCellMargin(tblCellMar, new XWPFTable$$ExternalSyntheticLambda18(), new XWPFTable$$ExternalSyntheticLambda26(), new XWPFTable$$ExternalSyntheticLambda27(), new XWPFTable$$ExternalSyntheticLambda28(), i);
                        setCellMargin(tblCellMar, new XWPFTable$$ExternalSyntheticLambda29(), new XWPFTable$$ExternalSyntheticLambda11(), new XWPFTable$$ExternalSyntheticLambda30(), new XWPFTable$$ExternalSyntheticLambda31(), i2);
                        setCellMargin(tblCellMar, new XWPFTable$$ExternalSyntheticLambda32(), new XWPFTable$$ExternalSyntheticLambda0(), new XWPFTable$$ExternalSyntheticLambda19(), new XWPFTable$$ExternalSyntheticLambda20(), i3);
                        setCellMargin(tblCellMar, new XWPFTable$$ExternalSyntheticLambda21(), new XWPFTable$$ExternalSyntheticLambda23(), new XWPFTable$$ExternalSyntheticLambda24(), new XWPFTable$$ExternalSyntheticLambda25(), i4);
                    }

                    private void setCellMargin(CTTblCellMar cTTblCellMar, Function<CTTblCellMar, Boolean> function, Function<CTTblCellMar, CTTblWidth> function2, Function<CTTblCellMar, CTTblWidth> function3, Consumer<CTTblCellMar> consumer, int i) {
                        if (i != 0) {
                            if (!function.apply(cTTblCellMar).booleanValue()) {
                                function2 = function3;
                            }
                            CTTblWidth apply = function2.apply(cTTblCellMar);
                            apply.setType(STTblWidth.DXA);
                            apply.setW(BigInteger.valueOf((long) i));
                        } else if (function.apply(cTTblCellMar).booleanValue()) {
                            consumer.accept(cTTblCellMar);
                        }
                    }

                    public void addRow(XWPFTableRow xWPFTableRow) {
                        this.ctTbl.addNewTr();
                        this.ctTbl.setTrArray(getNumberOfRows() - 1, xWPFTableRow.getCtRow());
                        this.tableRows.add(xWPFTableRow);
                    }

                    public boolean addRow(XWPFTableRow xWPFTableRow, int i) {
                        if (i < 0 || i > this.tableRows.size()) {
                            return false;
                        }
                        this.ctTbl.insertNewTr(i);
                        this.ctTbl.setTrArray(i, xWPFTableRow.getCtRow());
                        this.tableRows.add(i, xWPFTableRow);
                        return true;
                    }

                    public XWPFTableRow insertNewTableRow(int i) {
                        if (i < 0 || i > this.tableRows.size()) {
                            return null;
                        }
                        XWPFTableRow xWPFTableRow = new XWPFTableRow(this.ctTbl.insertNewTr(i), this);
                        this.tableRows.add(i, xWPFTableRow);
                        return xWPFTableRow;
                    }

                    public boolean removeRow(int i) throws IndexOutOfBoundsException {
                        if (i < 0 || i >= this.tableRows.size()) {
                            return false;
                        }
                        if (this.ctTbl.sizeOfTrArray() > 0) {
                            this.ctTbl.removeTr(i);
                        }
                        this.tableRows.remove(i);
                        return true;
                    }

                    public List<XWPFTableRow> getRows() {
                        return Collections.unmodifiableList(this.tableRows);
                    }

                    public BodyElementType getElementType() {
                        return BodyElementType.TABLE;
                    }

                    public IBody getBody() {
                        return this.part;
                    }

                    public POIXMLDocumentPart getPart() {
                        IBody iBody = this.part;
                        if (iBody != null) {
                            return iBody.getPart();
                        }
                        return null;
                    }

                    public BodyType getPartType() {
                        return this.part.getPartType();
                    }

                    public XWPFTableRow getRow(CTRow cTRow) {
                        for (int i = 0; i < getRows().size(); i++) {
                            if (getRows().get(i).getCtRow() == cTRow) {
                                return getRow(i);
                            }
                        }
                        return null;
                    }

                    public double getWidthDecimal() {
                        return getWidthDecimal(getTblPr().getTblW());
                    }

                    protected static double getWidthDecimal(CTTblWidth cTTblWidth) {
                        STTblWidth.Enum type = cTTblWidth.getType();
                        if (type == STTblWidth.DXA || type == STTblWidth.AUTO || type == STTblWidth.NIL) {
                            return 0.0d + Units.toDXA(POIXMLUnits.parseLength(cTTblWidth.xgetW()));
                        }
                        if (type == STTblWidth.PCT) {
                            return Units.toDXA(POIXMLUnits.parseLength(cTTblWidth.xgetW())) / 50.0d;
                        }
                        return 0.0d;
                    }

                    public TableWidthType getWidthType() {
                        return getWidthType(getTblPr().getTblW());
                    }

                    protected static TableWidthType getWidthType(CTTblWidth cTTblWidth) {
                        STTblWidth.Enum type = cTTblWidth.getType();
                        if (type == null) {
                            type = STTblWidth.NIL;
                            cTTblWidth.setType(type);
                        }
                        int intValue = type.intValue();
                        if (intValue == 1) {
                            return TableWidthType.NIL;
                        }
                        if (intValue == 2) {
                            return TableWidthType.PCT;
                        }
                        if (intValue != 3) {
                            return TableWidthType.AUTO;
                        }
                        return TableWidthType.DXA;
                    }

                    public void setWidth(String str) {
                        setWidthValue(str, getTblPr().getTblW());
                    }

                    protected static void setWidthValue(String str, CTTblWidth cTTblWidth) {
                        if (!str.matches(REGEX_WIDTH_VALUE)) {
                            throw new RuntimeException("Table width value \"" + str + "\" must match regular expression \"auto|[0-9]+|[0-9]+(\\.[0-9]+)?%\".");
                        } else if (str.matches(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                            cTTblWidth.setType(STTblWidth.AUTO);
                            cTTblWidth.setW(BigInteger.ZERO);
                        } else if (str.matches(REGEX_PERCENTAGE)) {
                            setWidthPercentage(cTTblWidth, str);
                        } else {
                            cTTblWidth.setW(new BigInteger(str));
                            cTTblWidth.setType(STTblWidth.DXA);
                        }
                    }

                    protected static void setWidthPercentage(CTTblWidth cTTblWidth, String str) {
                        cTTblWidth.setType(STTblWidth.PCT);
                        if (str.matches(REGEX_PERCENTAGE)) {
                            cTTblWidth.setW(BigInteger.valueOf(Math.round(Double.parseDouble(str.substring(0, str.length() - 1)) * 50.0d)));
                        } else if (str.matches("[0-9]+")) {
                            cTTblWidth.setW(new BigInteger(str));
                        } else {
                            throw new RuntimeException("setWidthPercentage(): Width value must be a percentage (\"33.3%\" or an integer, was \"" + str + "\"");
                        }
                    }

                    public void setWidthType(TableWidthType tableWidthType) {
                        setWidthType(tableWidthType, getTblPr().getTblW());
                    }

                    protected static void setWidthType(TableWidthType tableWidthType, CTTblWidth cTTblWidth) {
                        if (!getWidthType(cTTblWidth).equals(tableWidthType)) {
                            STTblWidth.Enum stWidthType = tableWidthType.getStWidthType();
                            cTTblWidth.setType(stWidthType);
                            if (stWidthType.intValue() == 2) {
                                setWidthPercentage(cTTblWidth, DEFAULT_PERCENTAGE_WIDTH);
                            } else {
                                cTTblWidth.setW(BigInteger.ZERO);
                            }
                        }
                    }
                }
