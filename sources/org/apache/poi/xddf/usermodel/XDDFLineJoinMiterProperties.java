package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;

public class XDDFLineJoinMiterProperties implements XDDFLineJoinProperties {
    private CTLineJoinMiterProperties join;

    public XDDFLineJoinMiterProperties() {
        this(CTLineJoinMiterProperties.Factory.newInstance());
    }

    protected XDDFLineJoinMiterProperties(CTLineJoinMiterProperties cTLineJoinMiterProperties) {
        this.join = cTLineJoinMiterProperties;
    }

    /* access modifiers changed from: protected */
    @Internal
    public CTLineJoinMiterProperties getXmlObject() {
        return this.join;
    }

    public Integer getLimit() {
        if (this.join.isSetLim()) {
            return Integer.valueOf(POIXMLUnits.parsePercent(this.join.xgetLim()));
        }
        return null;
    }

    public void setLimit(Integer num) {
        if (num != null) {
            this.join.setLim(num);
        } else if (this.join.isSetLim()) {
            this.join.unsetLim();
        }
    }
}
