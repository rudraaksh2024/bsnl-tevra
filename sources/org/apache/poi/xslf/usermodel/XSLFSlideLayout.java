package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;

public class XSLFSlideLayout extends XSLFSheet implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private final CTSlideLayout _layout;
    private XSLFSlideMaster _master;

    /* access modifiers changed from: protected */
    public String getRootElementName() {
        return "sldLayout";
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
    public XSLFSlideLayout(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument> r0 = org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument r0 = (org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout r0 = r0.getSldLayout()     // Catch:{ all -> 0x0021 }
            r2._layout = r0     // Catch:{ all -> 0x0021 }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideLayout.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public String getName() {
        return this._layout.getCSld().getName();
    }

    @Internal
    public CTSlideLayout getXmlObject() {
        return this._layout;
    }

    public XSLFSlideMaster getSlideMaster() {
        if (this._master == null) {
            for (POIXMLDocumentPart next : getRelations()) {
                if (next instanceof XSLFSlideMaster) {
                    this._master = (XSLFSlideMaster) next;
                }
            }
        }
        XSLFSlideMaster xSLFSlideMaster = this._master;
        if (xSLFSlideMaster != null) {
            return xSLFSlideMaster;
        }
        throw new IllegalStateException("SlideMaster was not found for " + this);
    }

    public XSLFSlideMaster getMasterSheet() {
        return getSlideMaster();
    }

    public XSLFTheme getTheme() {
        return getSlideMaster().getTheme();
    }

    public boolean getFollowMasterGraphics() {
        return this._layout.getShowMasterSp();
    }

    public XSLFBackground getBackground() {
        CTBackground bg = this._layout.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, this);
        }
        return getMasterSheet().getBackground();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        r0 = (org.apache.poi.xslf.usermodel.XSLFTextShape) r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void copyLayout(org.apache.poi.xslf.usermodel.XSLFSlide r4) {
        /*
            r3 = this;
            java.util.List r3 = r3.getShapes()
            java.util.Iterator r3 = r3.iterator()
        L_0x0008:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0046
            java.lang.Object r0 = r3.next()
            org.apache.poi.xslf.usermodel.XSLFShape r0 = (org.apache.poi.xslf.usermodel.XSLFShape) r0
            boolean r1 = r0 instanceof org.apache.poi.xslf.usermodel.XSLFTextShape
            if (r1 == 0) goto L_0x0008
            org.apache.poi.xslf.usermodel.XSLFTextShape r0 = (org.apache.poi.xslf.usermodel.XSLFTextShape) r0
            org.apache.poi.sl.usermodel.Placeholder r1 = r0.getTextType()
            if (r1 != 0) goto L_0x0021
            goto L_0x0008
        L_0x0021:
            int[] r2 = org.apache.poi.xslf.usermodel.XSLFSlideLayout.AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$Placeholder
            int r1 = r1.ordinal()
            r1 = r2[r1]
            r2 = 1
            if (r1 == r2) goto L_0x0008
            r2 = 2
            if (r1 == r2) goto L_0x0008
            r2 = 3
            if (r1 == r2) goto L_0x0008
            org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape r1 = r4.getSpTree()
            org.openxmlformats.schemas.presentationml.x2006.main.CTShape r1 = r1.addNewSp()
            org.apache.xmlbeans.XmlObject r0 = r0.getXmlObject()
            org.apache.xmlbeans.XmlObject r0 = r0.copy()
            r1.set(r0)
            goto L_0x0008
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideLayout.copyLayout(org.apache.poi.xslf.usermodel.XSLFSlide):void");
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFSlideLayout$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$Placeholder;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.sl.usermodel.Placeholder[] r0 = org.apache.poi.sl.usermodel.Placeholder.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$Placeholder = r0
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.DATETIME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.SLIDE_NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$Placeholder     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.Placeholder r1 = org.apache.poi.sl.usermodel.Placeholder.FOOTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideLayout.AnonymousClass1.<clinit>():void");
        }
    }

    public SlideLayout getType() {
        return SlideLayout.values()[this._layout.getType().intValue() - 1];
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        return mapSchemeColor(this._layout.getClrMapOvr(), str);
    }
}
