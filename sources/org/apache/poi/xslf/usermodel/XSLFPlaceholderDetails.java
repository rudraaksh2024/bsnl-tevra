package org.apache.poi.xslf.usermodel;

import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType;

public class XSLFPlaceholderDetails implements PlaceholderDetails {
    private static final QName[] NV_CONTAINER = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvCxnSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGrpSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPicPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGraphicFramePr")};
    private static final QName[] NV_PROPS = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr")};
    private CTPlaceholder _ph;
    private final XSLFShape shape;

    public String getText() {
        return null;
    }

    public void setText(String str) {
    }

    XSLFPlaceholderDetails(XSLFShape xSLFShape) {
        this.shape = xSLFShape;
    }

    public Placeholder getPlaceholder() {
        CTPlaceholder cTPlaceholder = getCTPlaceholder(false);
        if (cTPlaceholder == null) {
            return null;
        }
        if (cTPlaceholder.isSetType() || cTPlaceholder.isSetIdx()) {
            return Placeholder.lookupOoxml(cTPlaceholder.getType().intValue());
        }
        return null;
    }

    public XSLFSimpleShape getPlaceholderShape() {
        CTPlaceholder cTPlaceholder = getCTPlaceholder(false);
        if (cTPlaceholder == null) {
            return null;
        }
        return ((XSLFSheet) this.shape.getSheet().getMasterSheet()).getPlaceholder(cTPlaceholder);
    }

    public void setPlaceholder(Placeholder placeholder) {
        CTPlaceholder cTPlaceholder = getCTPlaceholder(placeholder != null);
        if (cTPlaceholder == null) {
            return;
        }
        if (placeholder != null) {
            cTPlaceholder.setType(STPlaceholderType.Enum.forInt(placeholder.ooxmlId));
            return;
        }
        CTApplicationNonVisualDrawingProps nvProps = getNvProps();
        if (nvProps != null) {
            nvProps.unsetPh();
        }
    }

    public boolean isVisible() {
        CTPlaceholder cTPlaceholder = getCTPlaceholder(false);
        if (cTPlaceholder == null || !cTPlaceholder.isSetType()) {
            return true;
        }
        CTHeaderFooter headerFooter = getHeaderFooter(false);
        if (headerFooter == null) {
            return false;
        }
        Placeholder lookupOoxml = Placeholder.lookupOoxml(cTPlaceholder.getType().intValue());
        if (lookupOoxml == null) {
            return true;
        }
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder[lookupOoxml.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return true;
                    }
                    if (!headerFooter.isSetSldNum() || headerFooter.getSldNum()) {
                        return true;
                    }
                    return false;
                } else if (!headerFooter.isSetHdr() || headerFooter.getHdr()) {
                    return true;
                } else {
                    return false;
                }
            } else if (!headerFooter.isSetFtr() || headerFooter.getFtr()) {
                return true;
            } else {
                return false;
            }
        } else if (!headerFooter.isSetDt() || headerFooter.getDt()) {
            return true;
        } else {
            return false;
        }
    }

    public void setVisible(boolean z) {
        Function function;
        Placeholder placeholder = getPlaceholder();
        if (placeholder != null) {
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder[placeholder.ordinal()];
            if (i == 1) {
                new XSLFPlaceholderDetails$$ExternalSyntheticLambda2
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0030: CONSTRUCTOR  (r0v4 ? I:org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda2) =  call: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda2.<init>():void type: CONSTRUCTOR in method: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails.setVisible(boolean):void, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v4 ?
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 41 more
                    */
                /*
                    this = this;
                    org.apache.poi.sl.usermodel.Placeholder r0 = r3.getPlaceholder()
                    if (r0 != 0) goto L_0x0007
                    return
                L_0x0007:
                    int[] r1 = org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails.AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder
                    int r0 = r0.ordinal()
                    r0 = r1[r0]
                    r1 = 1
                    if (r0 == r1) goto L_0x002e
                    r2 = 2
                    if (r0 == r2) goto L_0x0028
                    r2 = 3
                    if (r0 == r2) goto L_0x0022
                    r2 = 4
                    if (r0 == r2) goto L_0x001c
                    return
                L_0x001c:
                    org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda5 r0 = new org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda5
                    r0.<init>()
                    goto L_0x0033
                L_0x0022:
                    org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda4 r0 = new org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda4
                    r0.<init>()
                    goto L_0x0033
                L_0x0028:
                    org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda3 r0 = new org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda3
                    r0.<init>()
                    goto L_0x0033
                L_0x002e:
                    org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda2 r0 = new org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$$ExternalSyntheticLambda2
                    r0.<init>()
                L_0x0033:
                    org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter r3 = r3.getHeaderFooter(r1)
                    if (r3 != 0) goto L_0x003a
                    return
                L_0x003a:
                    java.lang.Object r3 = r0.apply(r3)
                    java.util.function.Consumer r3 = (java.util.function.Consumer) r3
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                    r3.accept(r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails.setVisible(boolean):void");
            }

            public PlaceholderDetails.PlaceholderSize getSize() {
                CTPlaceholder cTPlaceholder = getCTPlaceholder(false);
                if (cTPlaceholder == null || !cTPlaceholder.isSetSz()) {
                    return null;
                }
                int intValue = cTPlaceholder.getSz().intValue();
                if (intValue == 1) {
                    return PlaceholderDetails.PlaceholderSize.full;
                }
                if (intValue == 2) {
                    return PlaceholderDetails.PlaceholderSize.half;
                }
                if (intValue != 3) {
                    return null;
                }
                return PlaceholderDetails.PlaceholderSize.quarter;
            }

            public void setSize(PlaceholderDetails.PlaceholderSize placeholderSize) {
                CTPlaceholder cTPlaceholder = getCTPlaceholder(false);
                if (cTPlaceholder != null) {
                    if (placeholderSize == null) {
                        cTPlaceholder.unsetSz();
                        return;
                    }
                    int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$PlaceholderDetails$PlaceholderSize[placeholderSize.ordinal()];
                    if (i == 1) {
                        cTPlaceholder.setSz(STPlaceholderSize.FULL);
                    } else if (i == 2) {
                        cTPlaceholder.setSz(STPlaceholderSize.HALF);
                    } else if (i == 3) {
                        cTPlaceholder.setSz(STPlaceholderSize.QUARTER);
                    }
                }
            }

            /* renamed from: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails$1  reason: invalid class name */
            static /* synthetic */ class AnonymousClass1 {
                static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$Placeholder;
                static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PlaceholderDetails$PlaceholderSize;

                /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
                /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
                static {
                    /*
                        org.apache.poi.sl.usermodel.PlaceholderDetails$PlaceholderSize[] r0 = org.apache.poi.sl.usermodel.PlaceholderDetails.PlaceholderSize.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        $SwitchMap$org$apache$poi$sl$usermodel$PlaceholderDetails$PlaceholderSize = r0
                        r1 = 1
                        org.apache.poi.sl.usermodel.PlaceholderDetails$PlaceholderSize r2 = org.apache.poi.sl.usermodel.PlaceholderDetails.PlaceholderSize.full     // Catch:{ NoSuchFieldError -> 0x0012 }
                        int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                        r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
                    L_0x0012:
                        r0 = 2
                        int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$PlaceholderDetails$PlaceholderSize     // Catch:{ NoSuchFieldError -> 0x001d }
                        org.apache.poi.sl.usermodel.PlaceholderDetails$PlaceholderSize r3 = org.apache.poi.sl.usermodel.PlaceholderDetails.PlaceholderSize.half     // Catch:{ NoSuchFieldError -> 0x001d }
                        int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                        r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
                    L_0x001d:
                        r2 = 3
                        int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$PlaceholderDetails$PlaceholderSize     // Catch:{ NoSuchFieldError -> 0x0028 }
                        org.apache.poi.sl.usermodel.PlaceholderDetails$PlaceholderSize r4 = org.apache.poi.sl.usermodel.PlaceholderDetails.PlaceholderSize.quarter     // Catch:{ NoSuchFieldError -> 0x0028 }
                        int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                        r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
                    L_0x0028:
                        org.apache.poi.sl.usermodel.Placeholder[] r3 = org.apache.poi.sl.usermodel.Placeholder.values()
                        int r3 = r3.length
                        int[] r3 = new int[r3]
                        $SwitchMap$org$apache$poi$sl$usermodel$Placeholder = r3
                        org.apache.poi.sl.usermodel.Placeholder r4 = org.apache.poi.sl.usermodel.Placeholder.DATETIME     // Catch:{ NoSuchFieldError -> 0x0039 }
                        int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                        r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
                    L_0x0039:
                        int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0043 }
                        org.apache.poi.sl.usermodel.Placeholder r3 = org.apache.poi.sl.usermodel.Placeholder.FOOTER     // Catch:{ NoSuchFieldError -> 0x0043 }
                        int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                        r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
                    L_0x0043:
                        int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x004d }
                        org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.HEADER     // Catch:{ NoSuchFieldError -> 0x004d }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
                    L_0x004d:
                        int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0058 }
                        org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.SLIDE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0058 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
                    L_0x0058:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails.AnonymousClass1.<clinit>():void");
                }
            }

            /* access modifiers changed from: package-private */
            public CTPlaceholder getCTPlaceholder(boolean z) {
                CTPlaceholder cTPlaceholder = this._ph;
                if (cTPlaceholder != null) {
                    return cTPlaceholder;
                }
                CTApplicationNonVisualDrawingProps nvProps = getNvProps();
                if (nvProps == null) {
                    return null;
                }
                CTPlaceholder ph = (nvProps.isSetPh() || !z) ? nvProps.getPh() : nvProps.addNewPh();
                this._ph = ph;
                return ph;
            }

            private CTApplicationNonVisualDrawingProps getNvProps() {
                try {
                    return (CTApplicationNonVisualDrawingProps) XPathHelper.selectProperty(this.shape.getXmlObject(), CTApplicationNonVisualDrawingProps.class, (XSLFShape.ReparseFactory) null, NV_CONTAINER, NV_PROPS);
                } catch (XmlException unused) {
                    return null;
                }
            }

            private CTHeaderFooter getHeaderFooter(boolean z) {
                XSLFSheet sheet = this.shape.getSheet();
                if (!(sheet instanceof MasterSheet) || (sheet instanceof XSLFSlideLayout)) {
                    sheet = (XSLFSheet) sheet.getMasterSheet();
                }
                if (sheet instanceof XSLFSlideMaster) {
                    CTSlideMaster xmlObject = ((XSLFSlideMaster) sheet).getXmlObject();
                    return (xmlObject.isSetHf() || !z) ? xmlObject.getHf() : xmlObject.addNewHf();
                } else if (!(sheet instanceof XSLFNotesMaster)) {
                    return null;
                } else {
                    CTNotesMaster xmlObject2 = ((XSLFNotesMaster) sheet).getXmlObject();
                    return (xmlObject2.isSetHf() || !z) ? xmlObject2.getHf() : xmlObject2.addNewHf();
                }
            }
        }
