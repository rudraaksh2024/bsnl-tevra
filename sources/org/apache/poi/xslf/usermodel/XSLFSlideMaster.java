package org.apache.poi.xslf.usermodel;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;

public class XSLFSlideMaster extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private Map<String, XSLFSlideLayout> _layouts;
    private CTSlideMaster _slide;

    public XSLFSlideMaster getMasterSheet() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRootElementName() {
        return "sldMaster";
    }

    /* access modifiers changed from: package-private */
    public boolean isSupportTheme() {
        return true;
    }

    public /* bridge */ /* synthetic */ SimpleShape getPlaceholder(Placeholder placeholder) {
        return super.getPlaceholder(placeholder);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r3 != null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected XSLFSlideMaster(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster r0 = r0.getSldMaster()     // Catch:{ all -> 0x0021 }
            r2._slide = r0     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x0020
            r3.close()
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r0 = move-exception
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideMaster.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public CTSlideMaster getXmlObject() {
        return this._slide;
    }

    private Map<String, XSLFSlideLayout> getLayouts() {
        if (this._layouts == null) {
            this._layouts = new HashMap();
            for (POIXMLDocumentPart next : getRelations()) {
                if (next instanceof XSLFSlideLayout) {
                    XSLFSlideLayout xSLFSlideLayout = (XSLFSlideLayout) next;
                    this._layouts.put(xSLFSlideLayout.getName().toLowerCase(Locale.ROOT), xSLFSlideLayout);
                }
            }
        }
        return this._layouts;
    }

    public XSLFSlideLayout[] getSlideLayouts() {
        return (XSLFSlideLayout[]) getLayouts().values().toArray(new XSLFSlideLayout[this._layouts.size()]);
    }

    public XSLFSlideLayout getLayout(SlideLayout slideLayout) {
        for (XSLFSlideLayout next : getLayouts().values()) {
            if (next.getType() == slideLayout) {
                return next;
            }
        }
        return null;
    }

    public XSLFSlideLayout getLayout(String str) {
        return getLayouts().get(str.toLowerCase(Locale.ROOT));
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFSlideMaster$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$Placeholder;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.sl.usermodel.Placeholder[] r0 = org.apache.poi.sl.usermodel.Placeholder.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$Placeholder = r0
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.TITLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.CENTERED_TITLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.SUBTITLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.BODY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideMaster.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public CTTextListStyle getTextProperties(Placeholder placeholder) {
        CTSlideMasterTextStyles txStyles = getXmlObject().getTxStyles();
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder[placeholder.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return txStyles.getTitleStyle();
        }
        if (i != 4) {
            return txStyles.getOtherStyle();
        }
        return txStyles.getBodyStyle();
    }

    public XSLFBackground getBackground() {
        CTBackground bg = this._slide.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, this);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        String mapSchemeColor = mapSchemeColor(this._slide.getClrMap(), str);
        return mapSchemeColor == null ? str : mapSchemeColor;
    }
}
