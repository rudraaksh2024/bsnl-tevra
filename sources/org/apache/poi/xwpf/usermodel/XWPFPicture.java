package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;

public class XWPFPicture {
    private final CTPicture ctPic;
    private final String description;
    private final XWPFRun run;

    public XWPFPicture(CTPicture cTPicture, XWPFRun xWPFRun) {
        this.run = xWPFRun;
        this.ctPic = cTPicture;
        this.description = cTPicture.getNvPicPr().getCNvPr().getDescr();
    }

    public void setPictureReference(PackageRelationship packageRelationship) {
        this.ctPic.getBlipFill().getBlip().setEmbed(packageRelationship.getId());
    }

    public CTPicture getCTPicture() {
        return this.ctPic;
    }

    public XWPFPictureData getPictureData() {
        CTBlipFillProperties blipFill = this.ctPic.getBlipFill();
        if (blipFill != null && blipFill.isSetBlip()) {
            String embed = blipFill.getBlip().getEmbed();
            POIXMLDocumentPart part = this.run.getParent().getPart();
            if (part != null) {
                POIXMLDocumentPart relationById = part.getRelationById(embed);
                if (relationById instanceof XWPFPictureData) {
                    return (XWPFPictureData) relationById;
                }
            }
        }
        return null;
    }

    public double getWidth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCx());
    }

    public double getDepth() {
        return Units.toPoints(this.ctPic.getSpPr().getXfrm().getExt().getCy());
    }

    public String getDescription() {
        return this.description;
    }
}
