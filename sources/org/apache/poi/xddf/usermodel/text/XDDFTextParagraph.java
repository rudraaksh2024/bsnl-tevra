package org.apache.poi.xddf.usermodel.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

public class XDDFTextParagraph implements Iterable<XDDFTextRun> {
    private final CTTextParagraph _p;
    private XDDFTextBody _parent;
    private XDDFParagraphProperties _properties;
    private final ArrayList<XDDFTextRun> _runs;

    @Internal
    protected XDDFTextParagraph(CTTextParagraph cTTextParagraph, XDDFTextBody xDDFTextBody) {
        this._p = cTTextParagraph;
        this._parent = xDDFTextBody;
        this._runs = new ArrayList<>(cTTextParagraph.sizeOfBrArray() + cTTextParagraph.sizeOfFldArray() + cTTextParagraph.sizeOfRArray());
        for (XmlObject xmlObject : cTTextParagraph.selectChildren(QNameSet.ALL)) {
            if (xmlObject instanceof CTTextLineBreak) {
                this._runs.add(new XDDFTextRun((CTTextLineBreak) xmlObject, this));
            } else if (xmlObject instanceof CTTextField) {
                this._runs.add(new XDDFTextRun((CTTextField) xmlObject, this));
            } else if (xmlObject instanceof CTRegularTextRun) {
                this._runs.add(new XDDFTextRun((CTRegularTextRun) xmlObject, this));
            }
        }
        addDefaultRunProperties();
        addAfterLastRunProperties();
    }

    public void setText(String str) {
        XmlObject xmlObject;
        if (!this._runs.isEmpty()) {
            ArrayList<XDDFTextRun> arrayList = this._runs;
            xmlObject = arrayList.get(arrayList.size() - 1).getProperties().copy();
        } else {
            xmlObject = null;
        }
        for (int sizeOfBrArray = this._p.sizeOfBrArray() - 1; sizeOfBrArray >= 0; sizeOfBrArray--) {
            this._p.removeBr(sizeOfBrArray);
        }
        for (int sizeOfFldArray = this._p.sizeOfFldArray() - 1; sizeOfFldArray >= 0; sizeOfFldArray--) {
            this._p.removeFld(sizeOfFldArray);
        }
        for (int sizeOfRArray = this._p.sizeOfRArray() - 1; sizeOfRArray >= 0; sizeOfRArray--) {
            this._p.removeR(sizeOfRArray);
        }
        this._runs.clear();
        XDDFTextRun appendRegularRun = appendRegularRun(str);
        if (xmlObject != null) {
            appendRegularRun.getProperties().set(xmlObject);
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        Iterator<XDDFTextRun> it = this._runs.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getText());
        }
        return sb.toString();
    }

    public XDDFTextBody getParentBody() {
        return this._parent;
    }

    public List<XDDFTextRun> getTextRuns() {
        return this._runs;
    }

    public Iterator<XDDFTextRun> iterator() {
        return this._runs.iterator();
    }

    public Spliterator<XDDFTextRun> spliterator() {
        return this._runs.spliterator();
    }

    public XDDFTextRun appendLineBreak() {
        CTTextLineBreak addNewBr = this._p.addNewBr();
        Iterator it = new IteratorIterable(new ReverseListIterator(this._runs)).iterator();
        while (true) {
            if (it.hasNext()) {
                CTTextCharacterProperties properties = ((XDDFTextRun) it.next()).getProperties();
                if (properties != null) {
                    addNewBr.setRPr((CTTextCharacterProperties) properties.copy());
                    break;
                }
            } else {
                break;
            }
        }
        XDDFTextRun xDDFTextRun = new XDDFTextRun(addNewBr, this);
        this._runs.add(xDDFTextRun);
        return xDDFTextRun;
    }

    public XDDFTextRun appendField(String str, String str2, String str3) {
        CTTextField addNewFld = this._p.addNewFld();
        addNewFld.setId(str);
        addNewFld.setType(str2);
        addNewFld.setT(str3);
        addNewFld.addNewRPr().setLang(LocaleUtil.getUserLocale().toLanguageTag());
        XDDFTextRun xDDFTextRun = new XDDFTextRun(addNewFld, this);
        this._runs.add(xDDFTextRun);
        return xDDFTextRun;
    }

    public XDDFTextRun appendRegularRun(String str) {
        CTRegularTextRun addNewR = this._p.addNewR();
        addNewR.setT(str);
        addNewR.addNewRPr().setLang(LocaleUtil.getUserLocale().toLanguageTag());
        XDDFTextRun xDDFTextRun = new XDDFTextRun(addNewR, this);
        this._runs.add(xDDFTextRun);
        return xDDFTextRun;
    }

    public int getIndentationLevel() {
        if (this._p.isSetPPr()) {
            return getProperties().getLevel();
        }
        return 0;
    }

    public void setIndentationLevel(Integer num) {
        if (this._p.isSetPPr()) {
            getProperties().setLevel(num);
        }
    }

    public TextAlignment getTextAlignment() {
        return (TextAlignment) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda21(), new XDDFTextParagraph$$ExternalSyntheticLambda23()).map(new XDDFTextParagraph$$ExternalSyntheticLambda24()).orElse((Object) null);
    }

    public void setTextAlignment(TextAlignment textAlignment) {
        if (textAlignment != null || this._p.isSetPPr()) {
            getOrCreateProperties().setTextAlignment(textAlignment);
        }
    }

    public FontAlignment getFontAlignment() {
        return (FontAlignment) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda7(), new XDDFTextParagraph$$ExternalSyntheticLambda8()).map(new XDDFTextParagraph$$ExternalSyntheticLambda9()).orElse((Object) null);
    }

    public void setFontAlignment(FontAlignment fontAlignment) {
        if (fontAlignment != null || this._p.isSetPPr()) {
            getOrCreateProperties().setFontAlignment(fontAlignment);
        }
    }

    public Double getIndentation() {
        return (Double) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda27(), new XDDFTextParagraph$$ExternalSyntheticLambda28()).map(new XDDFTextParagraph$$ExternalSyntheticLambda6()).orElse((Object) null);
    }

    public void setIndentation(Double d) {
        if (d != null || this._p.isSetPPr()) {
            getOrCreateProperties().setIndentation(d);
        }
    }

    public Double getMarginLeft() {
        return (Double) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda10(), new XDDFTextParagraph$$ExternalSyntheticLambda12()).map(new XDDFTextParagraph$$ExternalSyntheticLambda6()).orElse((Object) null);
    }

    public void setMarginLeft(Double d) {
        if (d != null || this._p.isSetPPr()) {
            getOrCreateProperties().setMarginLeft(d);
        }
    }

    public Double getMarginRight() {
        return (Double) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda4(), new XDDFTextParagraph$$ExternalSyntheticLambda5()).map(new XDDFTextParagraph$$ExternalSyntheticLambda6()).orElse((Object) null);
    }

    public void setMarginRight(Double d) {
        if (d != null || this._p.isSetPPr()) {
            getOrCreateProperties().setMarginRight(d);
        }
    }

    public Double getDefaultTabSize() {
        return (Double) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda13(), new XDDFTextParagraph$$ExternalSyntheticLambda14()).map(new XDDFTextParagraph$$ExternalSyntheticLambda15()).map(new XDDFTextParagraph$$ExternalSyntheticLambda16()).orElse((Object) null);
    }

    public void setDefaultTabSize(Double d) {
        if (d != null || this._p.isSetPPr()) {
            getOrCreateProperties().setDefaultTabSize(d);
        }
    }

    public XDDFSpacing getLineSpacing() {
        return (XDDFSpacing) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda29(), new XDDFTextParagraph$$ExternalSyntheticLambda30()).map(new XDDFTextParagraph$$ExternalSyntheticLambda22(this)).orElse((Object) null);
    }

    public void setLineSpacing(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null || this._p.isSetPPr()) {
            getOrCreateProperties().setLineSpacing(xDDFSpacing);
        }
    }

    public XDDFSpacing getSpaceBefore() {
        return (XDDFSpacing) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda25(), new XDDFTextParagraph$$ExternalSyntheticLambda26()).map(new XDDFTextParagraph$$ExternalSyntheticLambda22(this)).orElse((Object) null);
    }

    public void setSpaceBefore(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null || this._p.isSetPPr()) {
            getOrCreateProperties().setSpaceBefore(xDDFSpacing);
        }
    }

    public XDDFSpacing getSpaceAfter() {
        return (XDDFSpacing) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda0(), new XDDFTextParagraph$$ExternalSyntheticLambda11()).map(new XDDFTextParagraph$$ExternalSyntheticLambda22(this)).orElse((Object) null);
    }

    public void setSpaceAfter(XDDFSpacing xDDFSpacing) {
        if (xDDFSpacing != null || this._p.isSetPPr()) {
            getOrCreateProperties().setSpaceAfter(xDDFSpacing);
        }
    }

    static /* synthetic */ boolean lambda$getBulletColor$0(CTTextParagraphProperties cTTextParagraphProperties) {
        return cTTextParagraphProperties.isSetBuClr() || cTTextParagraphProperties.isSetBuClrTx();
    }

    public XDDFColor getBulletColor() {
        return (XDDFColor) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda37(), new XDDFTextParagraph$$ExternalSyntheticLambda38()).orElse((Object) null);
    }

    public void setBulletColor(XDDFColor xDDFColor) {
        if (xDDFColor != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletColor(xDDFColor);
        }
    }

    public void setBulletColorFollowText() {
        getOrCreateBulletProperties().setBulletColorFollowText();
    }

    static /* synthetic */ boolean lambda$getBulletFont$2(CTTextParagraphProperties cTTextParagraphProperties) {
        return cTTextParagraphProperties.isSetBuFont() || cTTextParagraphProperties.isSetBuFontTx();
    }

    public XDDFFont getBulletFont() {
        return (XDDFFont) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda35(), new XDDFTextParagraph$$ExternalSyntheticLambda36()).orElse((Object) null);
    }

    public void setBulletFont(XDDFFont xDDFFont) {
        if (xDDFFont != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletFont(xDDFFont);
        }
    }

    public void setBulletFontFollowText() {
        getOrCreateBulletProperties().setBulletFontFollowText();
    }

    public XDDFBulletSize getBulletSize() {
        return (XDDFBulletSize) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda17(), new XDDFTextParagraph$$ExternalSyntheticLambda18()).orElse((Object) null);
    }

    static /* synthetic */ boolean lambda$getBulletSize$4(CTTextParagraphProperties cTTextParagraphProperties) {
        return cTTextParagraphProperties.isSetBuSzPct() || cTTextParagraphProperties.isSetBuSzPts() || cTTextParagraphProperties.isSetBuSzTx();
    }

    public void setBulletSize(XDDFBulletSize xDDFBulletSize) {
        if (xDDFBulletSize != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletSize(xDDFBulletSize);
        }
    }

    public XDDFBulletStyle getBulletStyle() {
        return (XDDFBulletStyle) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda19(), new XDDFTextParagraph$$ExternalSyntheticLambda20()).orElse((Object) null);
    }

    static /* synthetic */ boolean lambda$getBulletStyle$6(CTTextParagraphProperties cTTextParagraphProperties) {
        return cTTextParagraphProperties.isSetBuAutoNum() || cTTextParagraphProperties.isSetBuBlip() || cTTextParagraphProperties.isSetBuChar() || cTTextParagraphProperties.isSetBuNone();
    }

    public void setBulletStyle(XDDFBulletStyle xDDFBulletStyle) {
        if (xDDFBulletStyle != null || this._p.isSetPPr()) {
            getOrCreateBulletProperties().setBulletStyle(xDDFBulletStyle);
        }
    }

    public boolean hasEastAsianLineBreak() {
        return ((Boolean) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda2(), new XDDFTextParagraph$$ExternalSyntheticLambda3()).orElse(false)).booleanValue();
    }

    public void setEastAsianLineBreak(Boolean bool) {
        if (bool != null || this._p.isSetPPr()) {
            getOrCreateProperties().setEastAsianLineBreak(bool);
        }
    }

    public boolean hasLatinLineBreak() {
        return ((Boolean) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda39(), new XDDFTextParagraph$$ExternalSyntheticLambda1()).orElse(false)).booleanValue();
    }

    public void setLatinLineBreak(Boolean bool) {
        if (bool != null || this._p.isSetPPr()) {
            getOrCreateProperties().setLatinLineBreak(bool);
        }
    }

    public boolean hasHangingPunctuation() {
        return ((Boolean) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda31(), new XDDFTextParagraph$$ExternalSyntheticLambda32()).orElse(false)).booleanValue();
    }

    public void setHangingPunctuation(Boolean bool) {
        if (bool != null || this._p.isSetPPr()) {
            getOrCreateProperties().setHangingPunctuation(bool);
        }
    }

    public boolean isRightToLeft() {
        return ((Boolean) findDefinedParagraphProperty(new XDDFTextParagraph$$ExternalSyntheticLambda33(), new XDDFTextParagraph$$ExternalSyntheticLambda34()).orElse(false)).booleanValue();
    }

    public void setRightToLeft(Boolean bool) {
        if (bool != null || this._p.isSetPPr()) {
            getOrCreateProperties().setRightToLeft(bool);
        }
    }

    public XDDFTabStop addTabStop() {
        return getOrCreateProperties().addTabStop();
    }

    public XDDFTabStop insertTabStop(int i) {
        return getOrCreateProperties().insertTabStop(i);
    }

    public void removeTabStop(int i) {
        if (this._p.isSetPPr()) {
            getProperties().removeTabStop(i);
        }
    }

    public XDDFTabStop getTabStop(int i) {
        if (this._p.isSetPPr()) {
            return getProperties().getTabStop(i);
        }
        return null;
    }

    public List<XDDFTabStop> getTabStops() {
        if (this._p.isSetPPr()) {
            return getProperties().getTabStops();
        }
        return Collections.emptyList();
    }

    public int countTabStops() {
        if (this._p.isSetPPr()) {
            return getProperties().countTabStops();
        }
        return 0;
    }

    public XDDFParagraphBulletProperties getOrCreateBulletProperties() {
        return getOrCreateProperties().getBulletProperties();
    }

    public XDDFParagraphBulletProperties getBulletProperties() {
        if (this._p.isSetPPr()) {
            return getProperties().getBulletProperties();
        }
        return null;
    }

    public XDDFRunProperties addDefaultRunProperties() {
        return getOrCreateProperties().addDefaultRunProperties();
    }

    public XDDFRunProperties getDefaultRunProperties() {
        if (this._p.isSetPPr()) {
            return getProperties().getDefaultRunProperties();
        }
        return null;
    }

    public void setDefaultRunProperties(XDDFRunProperties xDDFRunProperties) {
        if (xDDFRunProperties != null || this._p.isSetPPr()) {
            getOrCreateProperties().setDefaultRunProperties(xDDFRunProperties);
        }
    }

    public XDDFRunProperties addAfterLastRunProperties() {
        if (!this._p.isSetEndParaRPr()) {
            this._p.addNewEndParaRPr();
        }
        return getAfterLastRunProperties();
    }

    public XDDFRunProperties getAfterLastRunProperties() {
        if (this._p.isSetEndParaRPr()) {
            return new XDDFRunProperties(this._p.getEndParaRPr());
        }
        return null;
    }

    public void setAfterLastRunProperties(XDDFRunProperties xDDFRunProperties) {
        if (xDDFRunProperties != null) {
            this._p.setEndParaRPr(xDDFRunProperties.getXmlObject());
        } else if (this._p.isSetEndParaRPr()) {
            this._p.unsetEndParaRPr();
        }
    }

    /* access modifiers changed from: private */
    public XDDFSpacing extractSpacing(CTTextSpacing cTTextSpacing) {
        if (cTTextSpacing.isSetSpcPct()) {
            return new XDDFSpacingPercent(cTTextSpacing, cTTextSpacing.getSpcPct(), Double.valueOf(1.0d - (((double) this._parent.getBodyProperties().getAutoFit().getLineSpaceReduction()) / 100000.0d)));
        } else if (cTTextSpacing.isSetSpcPts()) {
            return new XDDFSpacingPoints(cTTextSpacing, cTTextSpacing.getSpcPts());
        } else {
            return null;
        }
    }

    private XDDFParagraphProperties getProperties() {
        if (this._properties == null) {
            this._properties = new XDDFParagraphProperties(this._p.getPPr());
        }
        return this._properties;
    }

    private XDDFParagraphProperties getOrCreateProperties() {
        if (!this._p.isSetPPr()) {
            this._properties = new XDDFParagraphProperties(this._p.addNewPPr());
        }
        return getProperties();
    }

    /* access modifiers changed from: protected */
    public <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function) {
        int i = 0;
        if (!this._p.isSetPPr()) {
            return this._parent.findDefinedParagraphProperty(predicate, function, 0);
        }
        if (this._p.getPPr().isSetLvl()) {
            i = this._p.getPPr().getLvl() + 1;
        }
        return findDefinedParagraphProperty(predicate, function, i);
    }

    private <R> Optional<R> findDefinedParagraphProperty(Predicate<CTTextParagraphProperties> predicate, Function<CTTextParagraphProperties, R> function, int i) {
        CTTextParagraphProperties pPr = this._p.getPPr();
        if (pPr == null || !predicate.test(pPr)) {
            return this._parent.findDefinedParagraphProperty(predicate, function, i);
        }
        return Optional.ofNullable(function.apply(pPr));
    }

    /* access modifiers changed from: protected */
    public <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function) {
        int i = 0;
        if (!this._p.isSetPPr()) {
            return this._parent.findDefinedRunProperty(predicate, function, 0);
        }
        if (this._p.getPPr().isSetLvl()) {
            i = this._p.getPPr().getLvl() + 1;
        }
        return findDefinedRunProperty(predicate, function, i);
    }

    private <R> Optional<R> findDefinedRunProperty(Predicate<CTTextCharacterProperties> predicate, Function<CTTextCharacterProperties, R> function, int i) {
        CTTextCharacterProperties defRPr = this._p.getPPr().isSetDefRPr() ? this._p.getPPr().getDefRPr() : null;
        if (defRPr == null || !predicate.test(defRPr)) {
            return this._parent.findDefinedRunProperty(predicate, function, i);
        }
        return Optional.ofNullable(function.apply(defRPr));
    }
}
