package org.apache.poi.xddf.usermodel.text;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public class XDDFTextRun {
    private final XDDFTextParagraph _parent;
    private XDDFRunProperties _properties;
    private CTRegularTextRun _rtr;
    private CTTextField _tf;
    private CTTextLineBreak _tlb;

    @Internal
    protected XDDFTextRun(CTTextLineBreak cTTextLineBreak, XDDFTextParagraph xDDFTextParagraph) {
        this._tlb = cTTextLineBreak;
        this._parent = xDDFTextParagraph;
    }

    @Internal
    protected XDDFTextRun(CTTextField cTTextField, XDDFTextParagraph xDDFTextParagraph) {
        this._tf = cTTextField;
        this._parent = xDDFTextParagraph;
    }

    @Internal
    protected XDDFTextRun(CTRegularTextRun cTRegularTextRun, XDDFTextParagraph xDDFTextParagraph) {
        this._rtr = cTRegularTextRun;
        this._parent = xDDFTextParagraph;
    }

    public XDDFTextParagraph getParentParagraph() {
        return this._parent;
    }

    public boolean isLineBreak() {
        return this._tlb != null;
    }

    public boolean isField() {
        return this._tf != null;
    }

    public boolean isRegularRun() {
        return this._rtr != null;
    }

    public String getText() {
        if (isLineBreak()) {
            return "\n";
        }
        if (isField()) {
            return this._tf.getT();
        }
        return this._rtr.getT();
    }

    public void setText(String str) {
        if (isField()) {
            this._tf.setT(str);
        } else if (isRegularRun()) {
            this._rtr.setT(str);
        }
    }

    public void setDirty(Boolean bool) {
        getOrCreateProperties().setDirty(bool);
    }

    public Boolean getDirty() {
        return (Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda49(), new XDDFTextRun$$ExternalSyntheticLambda50()).orElse((Object) null);
    }

    public void setSpellError(Boolean bool) {
        getOrCreateProperties().setSpellError(bool);
    }

    public Boolean getSpellError() {
        return (Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda33(), new XDDFTextRun$$ExternalSyntheticLambda44()).orElse((Object) null);
    }

    public void setNoProof(Boolean bool) {
        getOrCreateProperties().setNoProof(bool);
    }

    public Boolean getNoProof() {
        return (Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda7(), new XDDFTextRun$$ExternalSyntheticLambda8()).orElse((Object) null);
    }

    public void setNormalizeHeights(Boolean bool) {
        getOrCreateProperties().setNormalizeHeights(bool);
    }

    public Boolean getNormalizeHeights() {
        return (Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda51(), new XDDFTextRun$$ExternalSyntheticLambda52()).orElse((Object) null);
    }

    public void setKumimoji(Boolean bool) {
        getOrCreateProperties().setKumimoji(bool);
    }

    public boolean isKumimoji() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda45(), new XDDFTextRun$$ExternalSyntheticLambda46()).orElse(false)).booleanValue();
    }

    public void setBold(Boolean bool) {
        getOrCreateProperties().setBold(bool);
    }

    public boolean isBold() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda54(), new XDDFTextRun$$ExternalSyntheticLambda56()).orElse(false)).booleanValue();
    }

    public void setItalic(Boolean bool) {
        getOrCreateProperties().setItalic(bool);
    }

    public boolean isItalic() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda23(), new XDDFTextRun$$ExternalSyntheticLambda24()).orElse(false)).booleanValue();
    }

    public void setStrikeThrough(StrikeType strikeType) {
        getOrCreateProperties().setStrikeThrough(strikeType);
    }

    public boolean isStrikeThrough() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda55(), new XDDFTextRun$$ExternalSyntheticLambda66()).map(new XDDFTextRun$$ExternalSyntheticLambda25()).orElse(false)).booleanValue();
    }

    static /* synthetic */ Boolean lambda$isStrikeThrough$0(STTextStrikeType.Enum enumR) {
        return Boolean.valueOf(enumR != STTextStrikeType.NO_STRIKE);
    }

    public StrikeType getStrikeThrough() {
        return (StrikeType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda55(), new XDDFTextRun$$ExternalSyntheticLambda66()).map(new XDDFTextRun$$ExternalSyntheticLambda71()).orElse((Object) null);
    }

    public void setUnderline(UnderlineType underlineType) {
        getOrCreateProperties().setUnderline(underlineType);
    }

    public boolean isUnderline() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda19(), new XDDFTextRun$$ExternalSyntheticLambda20()).map(new XDDFTextRun$$ExternalSyntheticLambda40()).orElse(false)).booleanValue();
    }

    static /* synthetic */ Boolean lambda$isUnderline$1(STTextUnderlineType.Enum enumR) {
        return Boolean.valueOf(enumR != STTextUnderlineType.NONE);
    }

    public UnderlineType getUnderline() {
        return (UnderlineType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda19(), new XDDFTextRun$$ExternalSyntheticLambda20()).map(new XDDFTextRun$$ExternalSyntheticLambda21()).orElse((Object) null);
    }

    public void setCapitals(CapsType capsType) {
        getOrCreateProperties().setCapitals(capsType);
    }

    public boolean isCapitals() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda4(), new XDDFTextRun$$ExternalSyntheticLambda5()).map(new XDDFTextRun$$ExternalSyntheticLambda53()).orElse(false)).booleanValue();
    }

    static /* synthetic */ Boolean lambda$isCapitals$2(STTextCapsType.Enum enumR) {
        return Boolean.valueOf(enumR != STTextCapsType.NONE);
    }

    public CapsType getCapitals() {
        return (CapsType) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda4(), new XDDFTextRun$$ExternalSyntheticLambda5()).map(new XDDFTextRun$$ExternalSyntheticLambda6()).orElse((Object) null);
    }

    public boolean isSubscript() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda15(), new XDDFTextRun$$ExternalSyntheticLambda16()).map(new XDDFTextRun$$ExternalSyntheticLambda17()).map(new XDDFTextRun$$ExternalSyntheticLambda65()).orElse(false)).booleanValue();
    }

    static /* synthetic */ Boolean lambda$isSubscript$3(Integer num) {
        return Boolean.valueOf(num.intValue() < 0);
    }

    public boolean isSuperscript() {
        return ((Boolean) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda15(), new XDDFTextRun$$ExternalSyntheticLambda16()).map(new XDDFTextRun$$ExternalSyntheticLambda17()).map(new XDDFTextRun$$ExternalSyntheticLambda18()).orElse(false)).booleanValue();
    }

    static /* synthetic */ Boolean lambda$isSuperscript$4(Integer num) {
        return Boolean.valueOf(num.intValue() > 0);
    }

    public void setBaseline(Double d) {
        if (d == null) {
            getOrCreateProperties().setBaseline((Integer) null);
        } else {
            getOrCreateProperties().setBaseline(Integer.valueOf((int) (d.doubleValue() * 1000.0d)));
        }
    }

    public void setSuperscript(Double d) {
        setBaseline(d == null ? null : Double.valueOf(Math.abs(d.doubleValue())));
    }

    public void setSubscript(Double d) {
        setBaseline(d == null ? null : Double.valueOf(-Math.abs(d.doubleValue())));
    }

    public void setFillProperties(XDDFFillProperties xDDFFillProperties) {
        getOrCreateProperties().setFillProperties(xDDFFillProperties);
    }

    public void setFontColor(XDDFColor xDDFColor) {
        XDDFSolidFillProperties xDDFSolidFillProperties = new XDDFSolidFillProperties();
        xDDFSolidFillProperties.setColor(xDDFColor);
        setFillProperties(xDDFSolidFillProperties);
    }

    public XDDFColor getFontColor() {
        return ((XDDFSolidFillProperties) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda9(), new XDDFTextRun$$ExternalSyntheticLambda10()).map(new XDDFTextRun$$ExternalSyntheticLambda12()).orElse(new XDDFSolidFillProperties())).getColor();
    }

    public void setFonts(XDDFFont[] xDDFFontArr) {
        getOrCreateProperties().setFonts(xDDFFontArr);
    }

    public XDDFFont[] getFonts() {
        LinkedList linkedList = new LinkedList();
        findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda26(), new XDDFTextRun$$ExternalSyntheticLambda32()).map(new XDDFTextRun$$ExternalSyntheticLambda34()).ifPresent(new XDDFTextRun$$ExternalSyntheticLambda28(linkedList));
        findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda35(), new XDDFTextRun$$ExternalSyntheticLambda36()).map(new XDDFTextRun$$ExternalSyntheticLambda37()).ifPresent(new XDDFTextRun$$ExternalSyntheticLambda28(linkedList));
        findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda38(), new XDDFTextRun$$ExternalSyntheticLambda39()).map(new XDDFTextRun$$ExternalSyntheticLambda27()).ifPresent(new XDDFTextRun$$ExternalSyntheticLambda28(linkedList));
        findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda29(), new XDDFTextRun$$ExternalSyntheticLambda30()).map(new XDDFTextRun$$ExternalSyntheticLambda31()).ifPresent(new XDDFTextRun$$ExternalSyntheticLambda28(linkedList));
        return (XDDFFont[]) linkedList.toArray(new XDDFFont[0]);
    }

    static /* synthetic */ XDDFFont lambda$getFonts$5(CTTextFont cTTextFont) {
        return new XDDFFont(FontGroup.COMPLEX_SCRIPT, cTTextFont);
    }

    static /* synthetic */ XDDFFont lambda$getFonts$6(CTTextFont cTTextFont) {
        return new XDDFFont(FontGroup.EAST_ASIAN, cTTextFont);
    }

    static /* synthetic */ XDDFFont lambda$getFonts$7(CTTextFont cTTextFont) {
        return new XDDFFont(FontGroup.LATIN, cTTextFont);
    }

    static /* synthetic */ XDDFFont lambda$getFonts$8(CTTextFont cTTextFont) {
        return new XDDFFont(FontGroup.SYMBOL, cTTextFont);
    }

    public void setFontSize(Double d) {
        getOrCreateProperties().setFontSize(d);
    }

    public Double getFontSize() {
        return Double.valueOf(((double) ((Integer) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda2(), new XDDFTextRun$$ExternalSyntheticLambda3()).orElse(1100)).intValue()) * (((double) this._parent.getParentBody().getBodyProperties().getAutoFit().getFontScale()) / 1.0E7d));
    }

    public void setCharacterKerning(Double d) {
        getOrCreateProperties().setCharacterKerning(d);
    }

    public Double getCharacterKerning() {
        return (Double) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda62(), new XDDFTextRun$$ExternalSyntheticLambda63()).map(new XDDFTextRun$$ExternalSyntheticLambda64()).orElse((Object) null);
    }

    public void setCharacterSpacing(Double d) {
        getOrCreateProperties().setCharacterSpacing(d);
    }

    public Double getCharacterSpacing() {
        return (Double) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda67(), new XDDFTextRun$$ExternalSyntheticLambda68()).map(new XDDFTextRun$$ExternalSyntheticLambda69()).map(new XDDFTextRun$$ExternalSyntheticLambda70()).orElse((Object) null);
    }

    public void setBookmark(String str) {
        getOrCreateProperties().setBookmark(str);
    }

    public String getBookmark() {
        return (String) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda60(), new XDDFTextRun$$ExternalSyntheticLambda61()).orElse((Object) null);
    }

    public XDDFHyperlink linkToExternal(String str, PackagePart packagePart, POIXMLRelation pOIXMLRelation) {
        XDDFHyperlink xDDFHyperlink = new XDDFHyperlink(packagePart.addExternalRelationship(str, pOIXMLRelation.getRelation()).getId());
        getOrCreateProperties().setHyperlink(xDDFHyperlink);
        return xDDFHyperlink;
    }

    public XDDFHyperlink linkToAction(String str) {
        XDDFHyperlink xDDFHyperlink = new XDDFHyperlink("", str);
        getOrCreateProperties().setHyperlink(xDDFHyperlink);
        return xDDFHyperlink;
    }

    public XDDFHyperlink linkToInternal(String str, PackagePart packagePart, POIXMLRelation pOIXMLRelation, PackagePartName packagePartName) {
        XDDFHyperlink xDDFHyperlink = new XDDFHyperlink(packagePart.addRelationship(packagePartName, TargetMode.INTERNAL, pOIXMLRelation.getRelation()).getId(), str);
        getOrCreateProperties().setHyperlink(xDDFHyperlink);
        return xDDFHyperlink;
    }

    public XDDFHyperlink getHyperlink() {
        return (XDDFHyperlink) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda72(), new XDDFTextRun$$ExternalSyntheticLambda73()).map(new XDDFTextRun$$ExternalSyntheticLambda1()).orElse((Object) null);
    }

    public XDDFHyperlink createMouseOver(String str) {
        XDDFHyperlink xDDFHyperlink = new XDDFHyperlink("", str);
        getOrCreateProperties().setMouseOver(xDDFHyperlink);
        return xDDFHyperlink;
    }

    public XDDFHyperlink getMouseOver() {
        return (XDDFHyperlink) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda13(), new XDDFTextRun$$ExternalSyntheticLambda14()).map(new XDDFTextRun$$ExternalSyntheticLambda1()).orElse((Object) null);
    }

    public void setLanguage(Locale locale) {
        getOrCreateProperties().setLanguage(locale);
    }

    public Locale getLanguage() {
        return (Locale) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda47(), new XDDFTextRun$$ExternalSyntheticLambda48()).map(new XDDFTextRun$$ExternalSyntheticLambda43()).orElse((Object) null);
    }

    public void setAlternativeLanguage(Locale locale) {
        getOrCreateProperties().setAlternativeLanguage(locale);
    }

    public Locale getAlternativeLanguage() {
        return (Locale) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda41(), new XDDFTextRun$$ExternalSyntheticLambda42()).map(new XDDFTextRun$$ExternalSyntheticLambda43()).orElse((Object) null);
    }

    public void setHighlight(XDDFColor xDDFColor) {
        getOrCreateProperties().setHighlight(xDDFColor);
    }

    public XDDFColor getHighlight() {
        return (XDDFColor) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda0(), new XDDFTextRun$$ExternalSyntheticLambda11()).map(new XDDFTextRun$$ExternalSyntheticLambda22()).orElse((Object) null);
    }

    public void setLineProperties(XDDFLineProperties xDDFLineProperties) {
        getOrCreateProperties().setLineProperties(xDDFLineProperties);
    }

    public XDDFLineProperties getLineProperties() {
        return (XDDFLineProperties) findDefinedProperty(new XDDFTextRun$$ExternalSyntheticLambda57(), new XDDFTextRun$$ExternalSyntheticLambda58()).map(new XDDFTextRun$$ExternalSyntheticLambda59()).orElse((Object) null);
    }

    private <R> Optional<R> findDefinedProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        CTTextCharacterProperties properties = getProperties();
        if (properties == null || !predicate.test(properties)) {
            return this._parent.findDefinedRunProperty(predicate, function);
        }
        return Optional.ofNullable(function.apply(properties));
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTTextCharacterProperties getProperties() {
        if (isLineBreak() && this._tlb.isSetRPr()) {
            return this._tlb.getRPr();
        }
        if (isField() && this._tf.isSetRPr()) {
            return this._tf.getRPr();
        }
        if (isRegularRun() && this._rtr.isSetRPr()) {
            return this._rtr.getRPr();
        }
        XDDFRunProperties defaultRunProperties = this._parent.getDefaultRunProperties();
        if (defaultRunProperties == null) {
            return null;
        }
        return defaultRunProperties.getXmlObject();
    }

    private XDDFRunProperties getOrCreateProperties() {
        if (this._properties == null) {
            if (isLineBreak()) {
                this._properties = new XDDFRunProperties(this._tlb.isSetRPr() ? this._tlb.getRPr() : this._tlb.addNewRPr());
            } else if (isField()) {
                this._properties = new XDDFRunProperties(this._tf.isSetRPr() ? this._tf.getRPr() : this._tf.addNewRPr());
            } else if (isRegularRun()) {
                this._properties = new XDDFRunProperties(this._rtr.isSetRPr() ? this._rtr.getRPr() : this._rtr.addNewRPr());
            }
        }
        return this._properties;
    }
}
