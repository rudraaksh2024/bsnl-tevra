package org.apache.poi.xslf.usermodel;

import androidx.core.net.MailTo;
import java.net.URI;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;

public class XSLFHyperlink implements Hyperlink<XSLFShape, XSLFTextParagraph> {
    private final CTHyperlink _link;
    private final XSLFSheet _sheet;

    XSLFHyperlink(CTHyperlink cTHyperlink, XSLFSheet xSLFSheet) {
        this._sheet = xSLFSheet;
        this._link = cTHyperlink;
    }

    @Internal
    public CTHyperlink getXmlObject() {
        return this._link;
    }

    public void setAddress(String str) {
        linkToUrl(str);
    }

    public String getAddress() {
        URI targetURI;
        String id = this._link.getId();
        if (id == null || id.isEmpty()) {
            return this._link.getAction();
        }
        PackageRelationship relationship = this._sheet.getPackagePart().getRelationship(id);
        if (relationship == null || (targetURI = relationship.getTargetURI()) == null) {
            return null;
        }
        return targetURI.toASCIIString();
    }

    public String getLabel() {
        return this._link.getTooltip();
    }

    public void setLabel(String str) {
        this._link.setTooltip(str);
    }

    public HyperlinkType getType() {
        String action = this._link.getAction();
        String str = "";
        if (action == null) {
            action = str;
        }
        if (action.equals("ppaction://hlinksldjump") || action.startsWith("ppaction://hlinkshowjump")) {
            return HyperlinkType.DOCUMENT;
        }
        String address = getAddress();
        if (address != null) {
            str = address;
        }
        if (str.startsWith(MailTo.MAILTO_SCHEME)) {
            return HyperlinkType.EMAIL;
        }
        return HyperlinkType.URL;
    }

    public void linkToEmail(String str) {
        linkToExternal(MailTo.MAILTO_SCHEME + str);
        setLabel(str);
    }

    public void linkToUrl(String str) {
        linkToExternal(str);
        setLabel(str);
    }

    private void linkToExternal(String str) {
        PackagePart packagePart = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            packagePart.removeRelationship(this._link.getId());
        }
        this._link.setId(packagePart.addExternalRelationship(str, XSLFRelation.HYPERLINK.getRelation()).getId());
        if (this._link.isSetAction()) {
            this._link.unsetAction();
        }
    }

    public void linkToSlide(Slide<XSLFShape, XSLFTextParagraph> slide) {
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            this._sheet.getPackagePart().removeRelationship(this._link.getId());
        }
        this._link.setId(this._sheet.addRelation((String) null, XSLFRelation.SLIDE, (XSLFSheet) slide).getRelationship().getId());
        this._link.setAction("ppaction://hlinksldjump");
    }

    public void linkToNextSlide() {
        linkToRelativeSlide("nextslide");
    }

    public void linkToPreviousSlide() {
        linkToRelativeSlide("previousslide");
    }

    public void linkToFirstSlide() {
        linkToRelativeSlide("firstslide");
    }

    public void linkToLastSlide() {
        linkToRelativeSlide("lastslide");
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFHyperlink$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.common.usermodel.HyperlinkType[] r0 = org.apache.poi.common.usermodel.HyperlinkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType = r0
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.EMAIL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.URL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.FILE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$HyperlinkType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.common.usermodel.HyperlinkType r1 = org.apache.poi.common.usermodel.HyperlinkType.NONE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFHyperlink.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFHyperlink xSLFHyperlink) {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$HyperlinkType[xSLFHyperlink.getType().ordinal()];
        if (i == 1 || i == 2) {
            linkToExternal(xSLFHyperlink.getAddress());
        } else if (i == 3) {
            String id = xSLFHyperlink._link.getId();
            if (id == null || id.isEmpty()) {
                linkToRelativeSlide(xSLFHyperlink.getAddress());
            } else {
                POIXMLDocumentPart relationById = xSLFHyperlink._sheet.getRelationById(id);
                if (relationById != null) {
                    this._link.setId(this._sheet.addRelation((String) null, XSLFRelation.SLIDE, relationById).getRelationship().getId());
                    this._link.setAction(xSLFHyperlink._link.getAction());
                }
            }
        } else {
            return;
        }
        setLabel(xSLFHyperlink.getLabel());
    }

    private void linkToRelativeSlide(String str) {
        PackagePart packagePart = this._sheet.getPackagePart();
        if (this._link.isSetId() && !this._link.getId().isEmpty()) {
            packagePart.removeRelationship(this._link.getId());
        }
        String str2 = "";
        this._link.setId(str2);
        CTHyperlink cTHyperlink = this._link;
        StringBuilder sb = new StringBuilder();
        if (!str.startsWith("ppaction")) {
            str2 = "ppaction://hlinkshowjump?jump=";
        }
        cTHyperlink.setAction(sb.append(str2).append(str).toString());
    }
}
