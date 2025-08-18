package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;

public class XDDFGroupFillProperties implements XDDFFillProperties {
    private CTGroupFillProperties props;

    public XDDFGroupFillProperties() {
        this(CTGroupFillProperties.Factory.newInstance());
    }

    protected XDDFGroupFillProperties(CTGroupFillProperties cTGroupFillProperties) {
        this.props = cTGroupFillProperties;
    }

    @Internal
    public CTGroupFillProperties getXmlObject() {
        return this.props;
    }
}
