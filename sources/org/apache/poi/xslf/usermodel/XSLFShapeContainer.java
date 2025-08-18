package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeContainer;

public interface XSLFShapeContainer extends ShapeContainer<XSLFShape, XSLFTextParagraph> {
    void clear();

    XSLFAutoShape createAutoShape();

    XSLFConnectorShape createConnector();

    XSLFFreeformShape createFreeform();

    XSLFGroupShape createGroup();

    XSLFPictureShape createPicture(PictureData pictureData);

    XSLFTextBox createTextBox();
}
