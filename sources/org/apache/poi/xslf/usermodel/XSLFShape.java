package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;

public abstract class XSLFShape implements Shape<XSLFShape, XSLFTextParagraph> {
    private static final QName[] CNV_PROPS = {new QName(PML_NS, "cNvPr")};
    static final String DML_NS = "http://schemas.openxmlformats.org/drawingml/2006/main";
    private static final QName[] NV_CONTAINER = {new QName(PML_NS, "nvSpPr"), new QName(PML_NS, "nvCxnSpPr"), new QName(PML_NS, "nvGrpSpPr"), new QName(PML_NS, "nvPicPr"), new QName(PML_NS, "nvGraphicFramePr")};
    static final String PML_NS = "http://schemas.openxmlformats.org/presentationml/2006/main";
    private CTNonVisualDrawingProps _nvPr;
    private XSLFShapeContainer _parent;
    private final XmlObject _shape;
    private final XSLFSheet _sheet;
    private CTShapeStyle _spStyle;

    @Internal
    public interface ReparseFactory<T extends XmlObject> {
        T parse(XMLStreamReader xMLStreamReader) throws XmlException;
    }

    protected XSLFShape(XmlObject xmlObject, XSLFSheet xSLFSheet) {
        this._shape = xmlObject;
        this._sheet = xSLFSheet;
    }

    public final XmlObject getXmlObject() {
        return this._shape;
    }

    public XSLFSheet getSheet() {
        return this._sheet;
    }

    public String getShapeName() {
        CTNonVisualDrawingProps cNvPr = getCNvPr();
        if (cNvPr == null) {
            return null;
        }
        return cNvPr.getName();
    }

    public int getShapeId() {
        CTNonVisualDrawingProps cNvPr = getCNvPr();
        if (cNvPr != null) {
            return Math.toIntExact(cNvPr.getId());
        }
        throw new IllegalStateException("no underlying shape exists");
    }

    /* access modifiers changed from: package-private */
    @Internal
    public void copy(XSLFShape xSLFShape) {
        if (!getClass().isInstance(xSLFShape)) {
            throw new IllegalArgumentException("Can't copy " + xSLFShape.getClass().getSimpleName() + " into " + getClass().getSimpleName());
        } else if (this instanceof PlaceableShape) {
            PlaceableShape placeableShape = (PlaceableShape) this;
            Rectangle2D anchor = xSLFShape.getAnchor();
            if (anchor != null) {
                placeableShape.setAnchor(anchor);
            }
        }
    }

    public void setParent(XSLFShapeContainer xSLFShapeContainer) {
        this._parent = xSLFShapeContainer;
    }

    public XSLFShapeContainer getParent() {
        return this._parent;
    }

    /* access modifiers changed from: protected */
    public PaintStyle getFillPaint() {
        final XSLFTheme theme = getSheet().getTheme();
        final boolean z = getPlaceholder() != null;
        AnonymousClass1 r2 = new PropertyFetcher<PaintStyle>() {
            public boolean fetch(XSLFShape xSLFShape) {
                PackagePart packagePart = xSLFShape.getSheet().getPackagePart();
                if (xSLFShape instanceof XSLFPictureShape) {
                    CTPicture cTPicture = (CTPicture) xSLFShape.getXmlObject();
                    if (cTPicture.getBlipFill() != null) {
                        setValue(XSLFShape.this.selectPaint(cTPicture.getBlipFill(), packagePart, (CTSchemeColor) null, theme));
                        return true;
                    }
                }
                XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(xSLFShape.getShapeProperties());
                if (fillDelegate == null) {
                    return false;
                }
                if (fillDelegate.isSetNoFill()) {
                    setValue(null);
                    return true;
                }
                PaintStyle selectPaint = XSLFShape.this.selectPaint(fillDelegate, (CTSchemeColor) null, packagePart, theme, z);
                if (selectPaint != null) {
                    setValue(selectPaint);
                    return true;
                }
                CTShapeStyle spStyle = xSLFShape.getSpStyle();
                if (spStyle != null) {
                    selectPaint = XSLFShape.this.selectPaint(XSLFPropertiesDelegate.getFillDelegate(spStyle.getFillRef()), (CTSchemeColor) null, packagePart, theme, z);
                }
                if (selectPaint == null) {
                    return false;
                }
                setValue(selectPaint);
                return true;
            }
        };
        fetchShapeProperty(r2);
        return (PaintStyle) r2.getValue();
    }

    /* access modifiers changed from: protected */
    public CTBackgroundProperties getBgPr() {
        return (CTBackgroundProperties) getChild(CTBackgroundProperties.class, PML_NS, "bgPr");
    }

    /* access modifiers changed from: protected */
    public CTStyleMatrixReference getBgRef() {
        return (CTStyleMatrixReference) getChild(CTStyleMatrixReference.class, PML_NS, "bgRef");
    }

    /* access modifiers changed from: protected */
    public CTGroupShapeProperties getGrpSpPr() {
        return (CTGroupShapeProperties) getChild(CTGroupShapeProperties.class, PML_NS, "grpSpPr");
    }

    /* access modifiers changed from: protected */
    public CTNonVisualDrawingProps getCNvPr() {
        try {
            if (this._nvPr == null) {
                this._nvPr = (CTNonVisualDrawingProps) XPathHelper.selectProperty(getXmlObject(), CTNonVisualDrawingProps.class, (ReparseFactory) null, NV_CONTAINER, CNV_PROPS);
            }
            return this._nvPr;
        } catch (XmlException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public CTShapeStyle getSpStyle() {
        if (this._spStyle == null) {
            this._spStyle = (CTShapeStyle) getChild(CTShapeStyle.class, PML_NS, "style");
        }
        return this._spStyle;
    }

    /* access modifiers changed from: protected */
    public <T extends XmlObject> T getChild(Class<T> cls, String str, String str2) {
        XmlCursor newCursor = getXmlObject().newCursor();
        try {
            T object = newCursor.toChild(str, str2) ? newCursor.getObject() : null;
            if (newCursor.toChild("http://schemas.openxmlformats.org/drawingml/2006/main", str2)) {
                object = newCursor.getObject();
            }
            return object;
        } finally {
            newCursor.dispose();
        }
    }

    public boolean isPlaceholder() {
        return getPlaceholderDetails().getCTPlaceholder(false) != null;
    }

    public Placeholder getPlaceholder() {
        return getPlaceholderDetails().getPlaceholder();
    }

    public void setPlaceholder(Placeholder placeholder) {
        getPlaceholderDetails().setPlaceholder(placeholder);
    }

    public XSLFPlaceholderDetails getPlaceholderDetails() {
        return new XSLFPlaceholderDetails(this);
    }

    /* access modifiers changed from: protected */
    public <T extends XmlObject> T selectProperty(Class<T> cls, String str) {
        T[] selectPath = getXmlObject().selectPath(str);
        if (selectPath.length != 0 && cls.isInstance(selectPath[0])) {
            return selectPath[0];
        }
        return null;
    }

    @Internal
    public boolean fetchShapeProperty(PropertyFetcher<?> propertyFetcher) {
        if (propertyFetcher.fetch(this)) {
            return true;
        }
        CTPlaceholder cTPlaceholder = getPlaceholderDetails().getCTPlaceholder(false);
        if (cTPlaceholder == null) {
            return false;
        }
        Object masterSheet = getSheet().getMasterSheet();
        if (masterSheet instanceof XSLFSlideLayout) {
            XSLFSlideLayout xSLFSlideLayout = (XSLFSlideLayout) masterSheet;
            XSLFSimpleShape placeholder = xSLFSlideLayout.getPlaceholder(cTPlaceholder);
            if (placeholder != null && propertyFetcher.fetch(placeholder)) {
                return true;
            }
            masterSheet = xSLFSlideLayout.getMasterSheet();
        }
        if (!(masterSheet instanceof XSLFSlideMaster)) {
            return false;
        }
        XSLFSimpleShape placeholderByType = ((XSLFSlideMaster) masterSheet).getPlaceholderByType(getPlaceholderType(cTPlaceholder));
        if (placeholderByType == null || !propertyFetcher.fetch(placeholderByType)) {
            return false;
        }
        return true;
    }

    private static int getPlaceholderType(CTPlaceholder cTPlaceholder) {
        if (!cTPlaceholder.isSetType()) {
            return 2;
        }
        int intValue = cTPlaceholder.getType().intValue();
        if (intValue == 1 || intValue == 3) {
            return 1;
        }
        if (intValue == 5 || intValue == 6 || intValue == 7) {
            return cTPlaceholder.getType().intValue();
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(XSLFPropertiesDelegate.XSLFFillProperties xSLFFillProperties, CTSchemeColor cTSchemeColor, PackagePart packagePart, XSLFTheme xSLFTheme, boolean z) {
        if (xSLFFillProperties != null && !xSLFFillProperties.isSetNoFill()) {
            if (xSLFFillProperties.isSetSolidFill()) {
                return selectPaint(xSLFFillProperties.getSolidFill(), cTSchemeColor, xSLFTheme);
            }
            if (xSLFFillProperties.isSetBlipFill()) {
                return selectPaint(xSLFFillProperties.getBlipFill(), packagePart, cTSchemeColor, xSLFTheme);
            }
            if (xSLFFillProperties.isSetGradFill()) {
                return selectPaint(xSLFFillProperties.getGradFill(), cTSchemeColor, xSLFTheme);
            }
            if (xSLFFillProperties.isSetMatrixStyle()) {
                return selectPaint(xSLFFillProperties.getMatrixStyle(), xSLFTheme, xSLFFillProperties.isLineStyle(), z);
            }
            if (cTSchemeColor != null) {
                return selectPaint(cTSchemeColor, xSLFTheme);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme) {
        return DrawPaint.createSolidPaint(new XSLFColor((XmlObject) null, xSLFTheme, cTSchemeColor, this._sheet).getColorStyle());
    }

    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(CTSolidColorFillProperties cTSolidColorFillProperties, CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme) {
        CTSchemeColor schemeClr = cTSolidColorFillProperties.getSchemeClr();
        if ((schemeClr == null || schemeClr.getVal() == null || STSchemeColorVal.PH_CLR.equals(schemeClr.getVal())) ? false : true) {
            cTSchemeColor = schemeClr;
        }
        return DrawPaint.createSolidPaint(new XSLFColor(cTSolidColorFillProperties, xSLFTheme, cTSchemeColor, this._sheet).getColorStyle());
    }

    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(CTBlipFillProperties cTBlipFillProperties, PackagePart packagePart, CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme) {
        return new XSLFTexturePaint(this, cTBlipFillProperties, packagePart, cTSchemeColor, xSLFTheme, this._sheet);
    }

    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(CTGradientFillProperties cTGradientFillProperties, CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme) {
        return new XSLFGradientPaint(cTGradientFillProperties, cTSchemeColor, xSLFTheme, this._sheet);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public PaintStyle selectPaint(CTStyleMatrixReference cTStyleMatrixReference, XSLFTheme xSLFTheme, boolean z, boolean z2) {
        XmlObject xmlObject;
        long j;
        XSLFPropertiesDelegate.XSLFFillProperties xSLFFillProperties = null;
        if (cTStyleMatrixReference == null) {
            return null;
        }
        long idx = cTStyleMatrixReference.getIdx();
        CTStyleMatrix fmtScheme = xSLFTheme.getXmlObject().getThemeElements().getFmtScheme();
        if (idx >= 1 && idx <= 999) {
            j = idx - 1;
            xmlObject = z ? fmtScheme.getLnStyleLst() : fmtScheme.getFillStyleLst();
        } else if (idx < 1001) {
            return null;
        } else {
            j = idx - 1001;
            xmlObject = fmtScheme.getBgFillStyleLst();
        }
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            if (newCursor.toChild(Math.toIntExact(j))) {
                xSLFFillProperties = XSLFPropertiesDelegate.getFillDelegate(newCursor.getObject());
            }
            newCursor.dispose();
            CTSchemeColor schemeClr = cTStyleMatrixReference.getSchemeClr();
            PaintStyle selectPaint = selectPaint(xSLFFillProperties, schemeClr, xSLFTheme.getPackagePart(), xSLFTheme, z2);
            return (selectPaint != null || z2) ? selectPaint : DrawPaint.createSolidPaint(new XSLFColor(cTStyleMatrixReference, xSLFTheme, schemeClr, this._sheet).getColorStyle());
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    public void draw(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        DrawFactory.getInstance(graphics2D).drawShape(graphics2D, this, rectangle2D);
    }

    /* access modifiers changed from: protected */
    public XmlObject getShapeProperties() {
        return getChild(CTShapeProperties.class, PML_NS, "spPr");
    }
}
