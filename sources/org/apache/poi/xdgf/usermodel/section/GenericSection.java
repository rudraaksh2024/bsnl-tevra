package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import org.apache.poi.xdgf.usermodel.XDGFSheet;

public class GenericSection extends XDGFSection {
    public void setupMaster(XDGFSection xDGFSection) {
    }

    public GenericSection(SectionType sectionType, XDGFSheet xDGFSheet) {
        super(sectionType, xDGFSheet);
    }
}
