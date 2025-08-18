package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;

public class XDDFLineEndProperties {
    private CTLineEndProperties props;

    protected XDDFLineEndProperties(CTLineEndProperties cTLineEndProperties) {
        this.props = cTLineEndProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLineEndProperties getXmlObject() {
        return this.props;
    }

    public LineEndLength getLength() {
        return LineEndLength.valueOf(this.props.getLen());
    }

    public void setLength(LineEndLength lineEndLength) {
        this.props.setLen(lineEndLength.underlying);
    }

    public LineEndType getType() {
        return LineEndType.valueOf(this.props.getType());
    }

    public void setType(LineEndType lineEndType) {
        this.props.setType(lineEndType.underlying);
    }

    public LineEndWidth getWidth() {
        return LineEndWidth.valueOf(this.props.getW());
    }

    public void setWidth(LineEndWidth lineEndWidth) {
        this.props.setW(lineEndWidth.underlying);
    }
}
