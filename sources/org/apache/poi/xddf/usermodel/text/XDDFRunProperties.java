package org.apache.poi.xddf.usermodel.text;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFEffectContainer;
import org.apache.poi.xddf.usermodel.XDDFEffectList;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGradientFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGroupFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFNoFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPatternFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPictureFillProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

public class XDDFRunProperties {
    private CTTextCharacterProperties props;

    public XDDFRunProperties() {
        this(CTTextCharacterProperties.Factory.newInstance());
    }

    @Internal
    public XDDFRunProperties(CTTextCharacterProperties cTTextCharacterProperties) {
        this.props = cTTextCharacterProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextCharacterProperties getXmlObject() {
        return this.props;
    }

    public void setBaseline(Integer num) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda52 xDDFRunProperties$$ExternalSyntheticLambda52 = new XDDFRunProperties$$ExternalSyntheticLambda52(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda53 xDDFRunProperties$$ExternalSyntheticLambda53 = new XDDFRunProperties$$ExternalSyntheticLambda53(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda52, xDDFRunProperties$$ExternalSyntheticLambda53, new XDDFRunProperties$$ExternalSyntheticLambda54(cTTextCharacterProperties3), num);
    }

    public void setDirty(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda26 xDDFRunProperties$$ExternalSyntheticLambda26 = new XDDFRunProperties$$ExternalSyntheticLambda26(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda27 xDDFRunProperties$$ExternalSyntheticLambda27 = new XDDFRunProperties$$ExternalSyntheticLambda27(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda26, xDDFRunProperties$$ExternalSyntheticLambda27, new XDDFRunProperties$$ExternalSyntheticLambda28(cTTextCharacterProperties3), bool);
    }

    public void setSpellError(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda62 xDDFRunProperties$$ExternalSyntheticLambda62 = new XDDFRunProperties$$ExternalSyntheticLambda62(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda63 xDDFRunProperties$$ExternalSyntheticLambda63 = new XDDFRunProperties$$ExternalSyntheticLambda63(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda62, xDDFRunProperties$$ExternalSyntheticLambda63, new XDDFRunProperties$$ExternalSyntheticLambda64(cTTextCharacterProperties3), bool);
    }

    public void setNoProof(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda39 xDDFRunProperties$$ExternalSyntheticLambda39 = new XDDFRunProperties$$ExternalSyntheticLambda39(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda40 xDDFRunProperties$$ExternalSyntheticLambda40 = new XDDFRunProperties$$ExternalSyntheticLambda40(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda39, xDDFRunProperties$$ExternalSyntheticLambda40, new XDDFRunProperties$$ExternalSyntheticLambda41(cTTextCharacterProperties3), bool);
    }

    public void setNormalizeHeights(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda46 xDDFRunProperties$$ExternalSyntheticLambda46 = new XDDFRunProperties$$ExternalSyntheticLambda46(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda47 xDDFRunProperties$$ExternalSyntheticLambda47 = new XDDFRunProperties$$ExternalSyntheticLambda47(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda46, xDDFRunProperties$$ExternalSyntheticLambda47, new XDDFRunProperties$$ExternalSyntheticLambda48(cTTextCharacterProperties3), bool);
    }

    public void setKumimoji(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda32 xDDFRunProperties$$ExternalSyntheticLambda32 = new XDDFRunProperties$$ExternalSyntheticLambda32(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda34 xDDFRunProperties$$ExternalSyntheticLambda34 = new XDDFRunProperties$$ExternalSyntheticLambda34(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda32, xDDFRunProperties$$ExternalSyntheticLambda34, new XDDFRunProperties$$ExternalSyntheticLambda35(cTTextCharacterProperties3), bool);
    }

    public void setBold(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda19 xDDFRunProperties$$ExternalSyntheticLambda19 = new XDDFRunProperties$$ExternalSyntheticLambda19(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda20 xDDFRunProperties$$ExternalSyntheticLambda20 = new XDDFRunProperties$$ExternalSyntheticLambda20(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda19, xDDFRunProperties$$ExternalSyntheticLambda20, new XDDFRunProperties$$ExternalSyntheticLambda21(cTTextCharacterProperties3), bool);
    }

    public void setItalic(Boolean bool) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda3 xDDFRunProperties$$ExternalSyntheticLambda3 = new XDDFRunProperties$$ExternalSyntheticLambda3(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda4 xDDFRunProperties$$ExternalSyntheticLambda4 = new XDDFRunProperties$$ExternalSyntheticLambda4(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda3, xDDFRunProperties$$ExternalSyntheticLambda4, new XDDFRunProperties$$ExternalSyntheticLambda5(cTTextCharacterProperties3), bool);
    }

    public void setFontSize(Double d) {
        if (d == null || (d.doubleValue() >= 1.0d && 400.0d >= d.doubleValue())) {
            CTTextCharacterProperties cTTextCharacterProperties = this.props;
            cTTextCharacterProperties.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda49 xDDFRunProperties$$ExternalSyntheticLambda49 = new XDDFRunProperties$$ExternalSyntheticLambda49(cTTextCharacterProperties);
            CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
            cTTextCharacterProperties2.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda50 xDDFRunProperties$$ExternalSyntheticLambda50 = new XDDFRunProperties$$ExternalSyntheticLambda50(cTTextCharacterProperties2);
            CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
            cTTextCharacterProperties3.getClass();
            update(xDDFRunProperties$$ExternalSyntheticLambda49, xDDFRunProperties$$ExternalSyntheticLambda50, new XDDFRunProperties$$ExternalSyntheticLambda51(cTTextCharacterProperties3), d == null ? null : Integer.valueOf((int) (d.doubleValue() * 100.0d)));
            return;
        }
        throw new IllegalArgumentException("Minimum inclusive = 1. Maximum inclusive = 400.");
    }

    public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
        if (this.props.isSetBlipFill()) {
            this.props.unsetBlipFill();
        }
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetGrpFill()) {
            this.props.unsetGrpFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (xDDFFillProperties != null) {
            if (xDDFFillProperties instanceof XDDFGradientFillProperties) {
                this.props.setGradFill(((XDDFGradientFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFGroupFillProperties) {
                this.props.setGrpFill(((XDDFGroupFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFNoFillProperties) {
                this.props.setNoFill(((XDDFNoFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFPatternFillProperties) {
                this.props.setPattFill(((XDDFPatternFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFPictureFillProperties) {
                this.props.setBlipFill(((XDDFPictureFillProperties) xDDFFillProperties).getXmlObject());
            } else if (xDDFFillProperties instanceof XDDFSolidFillProperties) {
                this.props.setSolidFill(((XDDFSolidFillProperties) xDDFFillProperties).getXmlObject());
            }
        }
    }

    public void setCharacterKerning(Double d) {
        if (d == null || (d.doubleValue() >= 0.0d && 4000.0d >= d.doubleValue())) {
            CTTextCharacterProperties cTTextCharacterProperties = this.props;
            cTTextCharacterProperties.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda83 xDDFRunProperties$$ExternalSyntheticLambda83 = new XDDFRunProperties$$ExternalSyntheticLambda83(cTTextCharacterProperties);
            CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
            cTTextCharacterProperties2.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda1 xDDFRunProperties$$ExternalSyntheticLambda1 = new XDDFRunProperties$$ExternalSyntheticLambda1(cTTextCharacterProperties2);
            CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
            cTTextCharacterProperties3.getClass();
            update(xDDFRunProperties$$ExternalSyntheticLambda83, xDDFRunProperties$$ExternalSyntheticLambda1, new XDDFRunProperties$$ExternalSyntheticLambda2(cTTextCharacterProperties3), d == null ? null : Integer.valueOf((int) (d.doubleValue() * 100.0d)));
            return;
        }
        throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4000.");
    }

    public void setCharacterSpacing(Double d) {
        if (d == null || (d.doubleValue() >= -4000.0d && 4000.0d >= d.doubleValue())) {
            CTTextCharacterProperties cTTextCharacterProperties = this.props;
            cTTextCharacterProperties.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda65 xDDFRunProperties$$ExternalSyntheticLambda65 = new XDDFRunProperties$$ExternalSyntheticLambda65(cTTextCharacterProperties);
            CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
            cTTextCharacterProperties2.getClass();
            XDDFRunProperties$$ExternalSyntheticLambda67 xDDFRunProperties$$ExternalSyntheticLambda67 = new XDDFRunProperties$$ExternalSyntheticLambda67(cTTextCharacterProperties2);
            CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
            cTTextCharacterProperties3.getClass();
            update(xDDFRunProperties$$ExternalSyntheticLambda65, xDDFRunProperties$$ExternalSyntheticLambda67, new XDDFRunProperties$$ExternalSyntheticLambda68(cTTextCharacterProperties3), d == null ? null : Integer.valueOf((int) (d.doubleValue() * 100.0d)));
            return;
        }
        throw new IllegalArgumentException("Minimum inclusive = -4000. Maximum inclusive = 4000.");
    }

    public void setFonts(XDDFFont[] xDDFFontArr) {
        for (XDDFFont xDDFFont : xDDFFontArr) {
            CTTextFont xmlObject = xDDFFont.getXmlObject();
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup[xDDFFont.getGroup().ordinal()];
            if (i == 1) {
                CTTextCharacterProperties cTTextCharacterProperties = this.props;
                cTTextCharacterProperties.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda69 xDDFRunProperties$$ExternalSyntheticLambda69 = new XDDFRunProperties$$ExternalSyntheticLambda69(cTTextCharacterProperties);
                CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
                cTTextCharacterProperties2.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda72 xDDFRunProperties$$ExternalSyntheticLambda72 = new XDDFRunProperties$$ExternalSyntheticLambda72(cTTextCharacterProperties2);
                CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
                cTTextCharacterProperties3.getClass();
                update(xDDFRunProperties$$ExternalSyntheticLambda69, xDDFRunProperties$$ExternalSyntheticLambda72, new XDDFRunProperties$$ExternalSyntheticLambda73(cTTextCharacterProperties3), xmlObject);
            } else if (i == 2) {
                CTTextCharacterProperties cTTextCharacterProperties4 = this.props;
                cTTextCharacterProperties4.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda74 xDDFRunProperties$$ExternalSyntheticLambda74 = new XDDFRunProperties$$ExternalSyntheticLambda74(cTTextCharacterProperties4);
                CTTextCharacterProperties cTTextCharacterProperties5 = this.props;
                cTTextCharacterProperties5.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda75 xDDFRunProperties$$ExternalSyntheticLambda75 = new XDDFRunProperties$$ExternalSyntheticLambda75(cTTextCharacterProperties5);
                CTTextCharacterProperties cTTextCharacterProperties6 = this.props;
                cTTextCharacterProperties6.getClass();
                update(xDDFRunProperties$$ExternalSyntheticLambda74, xDDFRunProperties$$ExternalSyntheticLambda75, new XDDFRunProperties$$ExternalSyntheticLambda76(cTTextCharacterProperties6), xmlObject);
            } else if (i == 3) {
                CTTextCharacterProperties cTTextCharacterProperties7 = this.props;
                cTTextCharacterProperties7.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda78 xDDFRunProperties$$ExternalSyntheticLambda78 = new XDDFRunProperties$$ExternalSyntheticLambda78(cTTextCharacterProperties7);
                CTTextCharacterProperties cTTextCharacterProperties8 = this.props;
                cTTextCharacterProperties8.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda79 xDDFRunProperties$$ExternalSyntheticLambda79 = new XDDFRunProperties$$ExternalSyntheticLambda79(cTTextCharacterProperties8);
                CTTextCharacterProperties cTTextCharacterProperties9 = this.props;
                cTTextCharacterProperties9.getClass();
                update(xDDFRunProperties$$ExternalSyntheticLambda78, xDDFRunProperties$$ExternalSyntheticLambda79, new XDDFRunProperties$$ExternalSyntheticLambda80(cTTextCharacterProperties9), xmlObject);
            } else if (i == 4) {
                CTTextCharacterProperties cTTextCharacterProperties10 = this.props;
                cTTextCharacterProperties10.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda81 xDDFRunProperties$$ExternalSyntheticLambda81 = new XDDFRunProperties$$ExternalSyntheticLambda81(cTTextCharacterProperties10);
                CTTextCharacterProperties cTTextCharacterProperties11 = this.props;
                cTTextCharacterProperties11.getClass();
                XDDFRunProperties$$ExternalSyntheticLambda70 xDDFRunProperties$$ExternalSyntheticLambda70 = new XDDFRunProperties$$ExternalSyntheticLambda70(cTTextCharacterProperties11);
                CTTextCharacterProperties cTTextCharacterProperties12 = this.props;
                cTTextCharacterProperties12.getClass();
                update(xDDFRunProperties$$ExternalSyntheticLambda81, xDDFRunProperties$$ExternalSyntheticLambda70, new XDDFRunProperties$$ExternalSyntheticLambda71(cTTextCharacterProperties12), xmlObject);
            }
        }
    }

    /* renamed from: org.apache.poi.xddf.usermodel.text.XDDFRunProperties$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.common.usermodel.fonts.FontGroup[] r0 = org.apache.poi.common.usermodel.fonts.FontGroup.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup = r0
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.COMPLEX_SCRIPT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.EAST_ASIAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.LATIN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.SYMBOL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xddf.usermodel.text.XDDFRunProperties.AnonymousClass1.<clinit>():void");
        }
    }

    public void setUnderline(UnderlineType underlineType) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda36 xDDFRunProperties$$ExternalSyntheticLambda36 = new XDDFRunProperties$$ExternalSyntheticLambda36(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda37 xDDFRunProperties$$ExternalSyntheticLambda37 = new XDDFRunProperties$$ExternalSyntheticLambda37(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda36, xDDFRunProperties$$ExternalSyntheticLambda37, new XDDFRunProperties$$ExternalSyntheticLambda38(cTTextCharacterProperties3), underlineType == null ? null : underlineType.underlying);
    }

    public void setStrikeThrough(StrikeType strikeType) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda9 xDDFRunProperties$$ExternalSyntheticLambda9 = new XDDFRunProperties$$ExternalSyntheticLambda9(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda10 xDDFRunProperties$$ExternalSyntheticLambda10 = new XDDFRunProperties$$ExternalSyntheticLambda10(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda9, xDDFRunProperties$$ExternalSyntheticLambda10, new XDDFRunProperties$$ExternalSyntheticLambda12(cTTextCharacterProperties3), strikeType == null ? null : strikeType.underlying);
    }

    public void setCapitals(CapsType capsType) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda42 xDDFRunProperties$$ExternalSyntheticLambda42 = new XDDFRunProperties$$ExternalSyntheticLambda42(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda43 xDDFRunProperties$$ExternalSyntheticLambda43 = new XDDFRunProperties$$ExternalSyntheticLambda43(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda42, xDDFRunProperties$$ExternalSyntheticLambda43, new XDDFRunProperties$$ExternalSyntheticLambda45(cTTextCharacterProperties3), capsType == null ? null : capsType.underlying);
    }

    public void setHyperlink(XDDFHyperlink xDDFHyperlink) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda6 xDDFRunProperties$$ExternalSyntheticLambda6 = new XDDFRunProperties$$ExternalSyntheticLambda6(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda7 xDDFRunProperties$$ExternalSyntheticLambda7 = new XDDFRunProperties$$ExternalSyntheticLambda7(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda6, xDDFRunProperties$$ExternalSyntheticLambda7, new XDDFRunProperties$$ExternalSyntheticLambda8(cTTextCharacterProperties3), xDDFHyperlink == null ? null : xDDFHyperlink.getXmlObject());
    }

    public void setMouseOver(XDDFHyperlink xDDFHyperlink) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda56 xDDFRunProperties$$ExternalSyntheticLambda56 = new XDDFRunProperties$$ExternalSyntheticLambda56(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda57 xDDFRunProperties$$ExternalSyntheticLambda57 = new XDDFRunProperties$$ExternalSyntheticLambda57(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda56, xDDFRunProperties$$ExternalSyntheticLambda57, new XDDFRunProperties$$ExternalSyntheticLambda58(cTTextCharacterProperties3), xDDFHyperlink == null ? null : xDDFHyperlink.getXmlObject());
    }

    public void setLanguage(Locale locale) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda33 xDDFRunProperties$$ExternalSyntheticLambda33 = new XDDFRunProperties$$ExternalSyntheticLambda33(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda44 xDDFRunProperties$$ExternalSyntheticLambda44 = new XDDFRunProperties$$ExternalSyntheticLambda44(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda33, xDDFRunProperties$$ExternalSyntheticLambda44, new XDDFRunProperties$$ExternalSyntheticLambda55(cTTextCharacterProperties3), locale == null ? null : locale.toLanguageTag());
    }

    public void setAlternativeLanguage(Locale locale) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda66 xDDFRunProperties$$ExternalSyntheticLambda66 = new XDDFRunProperties$$ExternalSyntheticLambda66(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda77 xDDFRunProperties$$ExternalSyntheticLambda77 = new XDDFRunProperties$$ExternalSyntheticLambda77(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda66, xDDFRunProperties$$ExternalSyntheticLambda77, new XDDFRunProperties$$ExternalSyntheticLambda82(cTTextCharacterProperties3), locale == null ? null : locale.toLanguageTag());
    }

    public void setHighlight(XDDFColor xDDFColor) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda0 xDDFRunProperties$$ExternalSyntheticLambda0 = new XDDFRunProperties$$ExternalSyntheticLambda0(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda11 xDDFRunProperties$$ExternalSyntheticLambda11 = new XDDFRunProperties$$ExternalSyntheticLambda11(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda0, xDDFRunProperties$$ExternalSyntheticLambda11, new XDDFRunProperties$$ExternalSyntheticLambda22(cTTextCharacterProperties3), xDDFColor == null ? null : xDDFColor.getColorContainer());
    }

    public void setLineProperties(XDDFLineProperties xDDFLineProperties) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda16 xDDFRunProperties$$ExternalSyntheticLambda16 = new XDDFRunProperties$$ExternalSyntheticLambda16(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda17 xDDFRunProperties$$ExternalSyntheticLambda17 = new XDDFRunProperties$$ExternalSyntheticLambda17(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda16, xDDFRunProperties$$ExternalSyntheticLambda17, new XDDFRunProperties$$ExternalSyntheticLambda18(cTTextCharacterProperties3), xDDFLineProperties == null ? null : xDDFLineProperties.getXmlObject());
    }

    public void setBookmark(String str) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda13 xDDFRunProperties$$ExternalSyntheticLambda13 = new XDDFRunProperties$$ExternalSyntheticLambda13(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda14 xDDFRunProperties$$ExternalSyntheticLambda14 = new XDDFRunProperties$$ExternalSyntheticLambda14(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda13, xDDFRunProperties$$ExternalSyntheticLambda14, new XDDFRunProperties$$ExternalSyntheticLambda15(cTTextCharacterProperties3), str);
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList xDDFExtensionList) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda29 xDDFRunProperties$$ExternalSyntheticLambda29 = new XDDFRunProperties$$ExternalSyntheticLambda29(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda30 xDDFRunProperties$$ExternalSyntheticLambda30 = new XDDFRunProperties$$ExternalSyntheticLambda30(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda29, xDDFRunProperties$$ExternalSyntheticLambda30, new XDDFRunProperties$$ExternalSyntheticLambda31(cTTextCharacterProperties3), xDDFExtensionList == null ? null : xDDFExtensionList.getXmlObject());
    }

    public XDDFEffectContainer getEffectContainer() {
        if (this.props.isSetEffectDag()) {
            return new XDDFEffectContainer(this.props.getEffectDag());
        }
        return null;
    }

    public void setEffectContainer(XDDFEffectContainer xDDFEffectContainer) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda23 xDDFRunProperties$$ExternalSyntheticLambda23 = new XDDFRunProperties$$ExternalSyntheticLambda23(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda24 xDDFRunProperties$$ExternalSyntheticLambda24 = new XDDFRunProperties$$ExternalSyntheticLambda24(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda23, xDDFRunProperties$$ExternalSyntheticLambda24, new XDDFRunProperties$$ExternalSyntheticLambda25(cTTextCharacterProperties3), xDDFEffectContainer == null ? null : xDDFEffectContainer.getXmlObject());
    }

    public XDDFEffectList getEffectList() {
        if (this.props.isSetEffectLst()) {
            return new XDDFEffectList(this.props.getEffectLst());
        }
        return null;
    }

    public void setEffectList(XDDFEffectList xDDFEffectList) {
        CTTextCharacterProperties cTTextCharacterProperties = this.props;
        cTTextCharacterProperties.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda59 xDDFRunProperties$$ExternalSyntheticLambda59 = new XDDFRunProperties$$ExternalSyntheticLambda59(cTTextCharacterProperties);
        CTTextCharacterProperties cTTextCharacterProperties2 = this.props;
        cTTextCharacterProperties2.getClass();
        XDDFRunProperties$$ExternalSyntheticLambda60 xDDFRunProperties$$ExternalSyntheticLambda60 = new XDDFRunProperties$$ExternalSyntheticLambda60(cTTextCharacterProperties2);
        CTTextCharacterProperties cTTextCharacterProperties3 = this.props;
        cTTextCharacterProperties3.getClass();
        update(xDDFRunProperties$$ExternalSyntheticLambda59, xDDFRunProperties$$ExternalSyntheticLambda60, new XDDFRunProperties$$ExternalSyntheticLambda61(cTTextCharacterProperties3), xDDFEffectList == null ? null : xDDFEffectList.getXmlObject());
    }

    private static <T> void update(Supplier<Boolean> supplier, Runnable runnable, Consumer<T> consumer, T t) {
        if (t != null) {
            consumer.accept(t);
        } else if (supplier.get().booleanValue()) {
            runnable.run();
        }
    }
}
