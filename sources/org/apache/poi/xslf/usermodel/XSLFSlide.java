package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.NotImplemented;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;

public final class XSLFSlide extends XSLFSheet implements Slide<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private XSLFCommentAuthors _commentAuthors;
    private XSLFComments _comments;
    private XSLFSlideLayout _layout;
    private XSLFNotes _notes;
    private final CTSlide _slide;

    public boolean getFollowMasterBackground() {
        return false;
    }

    public boolean getFollowMasterColourScheme() {
        return false;
    }

    /* access modifiers changed from: protected */
    public String getRootElementName() {
        return "sld";
    }

    @NotImplemented
    public void setNotes(Notes<XSLFShape, XSLFTextParagraph> notes) {
    }

    XSLFSlide() {
        this._slide = prototype();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        if (r3 != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    XSLFSlide(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>(r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()     // Catch:{ SAXException -> 0x0033 }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ SAXException -> 0x0033 }
            org.w3c.dom.Document r0 = org.apache.poi.ooxml.util.DocumentHelper.readDocument((java.io.InputStream) r3)     // Catch:{ all -> 0x0025 }
            if (r3 == 0) goto L_0x0014
            r3.close()     // Catch:{ SAXException -> 0x0033 }
        L_0x0014:
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.presentationml.x2006.main.SldDocument> r3 = org.openxmlformats.schemas.presentationml.x2006.main.SldDocument.Factory
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            java.lang.Object r3 = r3.parse((org.w3c.dom.Node) r0, (org.apache.xmlbeans.XmlOptions) r1)
            org.openxmlformats.schemas.presentationml.x2006.main.SldDocument r3 = (org.openxmlformats.schemas.presentationml.x2006.main.SldDocument) r3
            org.openxmlformats.schemas.presentationml.x2006.main.CTSlide r3 = r3.getSld()
            r2._slide = r3
            return
        L_0x0025:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r0 = move-exception
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ SAXException -> 0x0033 }
        L_0x0032:
            throw r0     // Catch:{ SAXException -> 0x0033 }
        L_0x0033:
            r2 = move-exception
            java.io.IOException r3 = new java.io.IOException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlide.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    private static CTSlide prototype() {
        CTSlide newInstance = CTSlide.Factory.newInstance();
        CTGroupShape addNewSpTree = newInstance.addNewCSld().addNewSpTree();
        CTGroupShapeNonVisual addNewNvGrpSpPr = addNewSpTree.addNewNvGrpSpPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvGrpSpPr.addNewCNvPr();
        addNewCNvPr.setId(1);
        addNewCNvPr.setName("");
        addNewNvGrpSpPr.addNewCNvGrpSpPr();
        addNewNvGrpSpPr.addNewNvPr();
        CTGroupTransform2D addNewXfrm = addNewSpTree.addNewGrpSpPr().addNewXfrm();
        CTPoint2D addNewOff = addNewXfrm.addNewOff();
        addNewOff.setX(0);
        addNewOff.setY(0);
        CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
        addNewExt.setCx(0);
        addNewExt.setCy(0);
        CTPoint2D addNewChOff = addNewXfrm.addNewChOff();
        addNewChOff.setX(0);
        addNewChOff.setY(0);
        CTPositiveSize2D addNewChExt = addNewXfrm.addNewChExt();
        addNewChExt.setCx(0);
        addNewChExt.setCy(0);
        newInstance.addNewClrMapOvr().addNewMasterClrMapping();
        return newInstance;
    }

    public CTSlide getXmlObject() {
        return this._slide;
    }

    /* access modifiers changed from: protected */
    public void removeChartRelation(XSLFChart xSLFChart) {
        removeRelation((POIXMLDocumentPart) xSLFChart);
    }

    /* access modifiers changed from: protected */
    public void removeLayoutRelation(XSLFSlideLayout xSLFSlideLayout) {
        removeRelation((POIXMLDocumentPart) xSLFSlideLayout, false);
    }

    public XSLFSlideLayout getMasterSheet() {
        return getSlideLayout();
    }

    public XSLFSlideLayout getSlideLayout() {
        if (this._layout == null) {
            for (POIXMLDocumentPart next : getRelations()) {
                if (next instanceof XSLFSlideLayout) {
                    this._layout = (XSLFSlideLayout) next;
                }
            }
        }
        XSLFSlideLayout xSLFSlideLayout = this._layout;
        if (xSLFSlideLayout != null) {
            return xSLFSlideLayout;
        }
        throw new IllegalArgumentException("SlideLayout was not found for " + this);
    }

    public XSLFSlideMaster getSlideMaster() {
        return getSlideLayout().getSlideMaster();
    }

    public XSLFComments getCommentsPart() {
        if (this._comments == null) {
            Iterator<POIXMLDocumentPart> it = getRelations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                POIXMLDocumentPart next = it.next();
                if (next instanceof XSLFComments) {
                    this._comments = (XSLFComments) next;
                    break;
                }
            }
        }
        return this._comments;
    }

    public XSLFCommentAuthors getCommentAuthorsPart() {
        if (this._commentAuthors != null) {
            return null;
        }
        for (POIXMLDocumentPart next : getRelations()) {
            if (next instanceof XSLFCommentAuthors) {
                XSLFCommentAuthors xSLFCommentAuthors = (XSLFCommentAuthors) next;
                this._commentAuthors = xSLFCommentAuthors;
                return xSLFCommentAuthors;
            }
        }
        for (POIXMLDocumentPart next2 : getSlideShow().getRelations()) {
            if (next2 instanceof XSLFCommentAuthors) {
                XSLFCommentAuthors xSLFCommentAuthors2 = (XSLFCommentAuthors) next2;
                this._commentAuthors = xSLFCommentAuthors2;
                return xSLFCommentAuthors2;
            }
        }
        return null;
    }

    public List<XSLFComment> getComments() {
        ArrayList arrayList = new ArrayList();
        XSLFComments commentsPart = getCommentsPart();
        XSLFCommentAuthors commentAuthorsPart = getCommentAuthorsPart();
        if (commentsPart != null) {
            for (CTComment xSLFComment : commentsPart.getCTCommentsList().getCmArray()) {
                arrayList.add(new XSLFComment(xSLFComment, commentAuthorsPart));
            }
        }
        return arrayList;
    }

    public XSLFNotes getNotes() {
        if (this._notes == null) {
            for (POIXMLDocumentPart next : getRelations()) {
                if (next instanceof XSLFNotes) {
                    this._notes = (XSLFNotes) next;
                }
            }
        }
        XSLFNotes xSLFNotes = this._notes;
        if (xSLFNotes == null) {
            return null;
        }
        return xSLFNotes;
    }

    public String getTitle() {
        XSLFTextShape textShapeByType = getTextShapeByType(Placeholder.TITLE);
        if (textShapeByType == null) {
            return null;
        }
        return textShapeByType.getText();
    }

    public XSLFTheme getTheme() {
        return getSlideLayout().getSlideMaster().getTheme();
    }

    public XSLFBackground getBackground() {
        CTBackground bg = this._slide.getCSld().getBg();
        if (bg != null) {
            return new XSLFBackground(bg, this);
        }
        return getMasterSheet().getBackground();
    }

    public boolean getFollowMasterGraphics() {
        return this._slide.getShowMasterSp();
    }

    public void setFollowMasterGraphics(boolean z) {
        this._slide.setShowMasterSp(z);
    }

    public boolean getFollowMasterObjects() {
        return getFollowMasterGraphics();
    }

    public void setFollowMasterObjects(boolean z) {
        setFollowMasterGraphics(z);
    }

    public XSLFSlide importContent(XSLFSheet xSLFSheet) {
        super.importContent(xSLFSheet);
        if (!(xSLFSheet instanceof XSLFSlide)) {
            return this;
        }
        XSLFSlide xSLFSlide = (XSLFSlide) xSLFSheet;
        XSLFNotes notes = xSLFSlide.getNotes();
        if (notes != null) {
            getSlideShow().getNotesSlide(this).importContent(notes);
        }
        CTBackground bg = xSLFSlide._slide.getCSld().getBg();
        if (bg == null) {
            return this;
        }
        CTBackground bg2 = this._slide.getCSld().getBg();
        if (bg2 != null) {
            if (bg2.isSetBgPr() && bg2.getBgPr().isSetBlipFill()) {
                removeRelation(bg2.getBgPr().getBlipFill().getBlip().getEmbed());
            }
            this._slide.getCSld().unsetBg();
        }
        CTBackground cTBackground = (CTBackground) this._slide.getCSld().addNewBg().set(bg);
        if (bg.isSetBgPr() && bg.getBgPr().isSetBlipFill()) {
            cTBackground.getBgPr().getBlipFill().getBlip().setEmbed(importBlip(bg.getBgPr().getBlipFill().getBlip().getEmbed(), xSLFSheet));
        }
        return this;
    }

    @NotImplemented
    public void setFollowMasterBackground(boolean z) {
        throw new UnsupportedOperationException();
    }

    @NotImplemented
    public void setFollowMasterColourScheme(boolean z) {
        throw new UnsupportedOperationException();
    }

    public int getSlideNumber() {
        int indexOf = getSlideShow().getSlides().indexOf(this);
        return indexOf == -1 ? indexOf : indexOf + 1;
    }

    public void draw(Graphics2D graphics2D) {
        DrawFactory.getInstance(graphics2D).getDrawable((Slide<?, ?>) this).draw(graphics2D);
    }

    public void setHidden(boolean z) {
        CTSlide xmlObject = getXmlObject();
        if (z) {
            xmlObject.setShow(false);
        } else if (xmlObject.isSetShow()) {
            xmlObject.unsetShow();
        }
    }

    public boolean isHidden() {
        CTSlide xmlObject = getXmlObject();
        return xmlObject.isSetShow() && !xmlObject.getShow();
    }

    public String getSlideName() {
        CTCommonSlideData cSld = getXmlObject().getCSld();
        return cSld.isSetName() ? cSld.getName() : "Slide" + getSlideNumber();
    }

    /* access modifiers changed from: package-private */
    public String mapSchemeColor(String str) {
        return mapSchemeColor(this._slide.getClrMapOvr(), str);
    }
}
