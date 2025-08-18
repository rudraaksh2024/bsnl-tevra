package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException;

public class XWPFLatentStyles {
    private CTLatentStyles latentStyles;
    protected XWPFStyles styles;

    protected XWPFLatentStyles() {
    }

    protected XWPFLatentStyles(CTLatentStyles cTLatentStyles) {
        this(cTLatentStyles, (XWPFStyles) null);
    }

    protected XWPFLatentStyles(CTLatentStyles cTLatentStyles, XWPFStyles xWPFStyles) {
        this.latentStyles = cTLatentStyles;
        this.styles = xWPFStyles;
    }

    public int getNumberOfStyles() {
        return this.latentStyles.sizeOfLsdExceptionArray();
    }

    public boolean isLatentStyle(String str) {
        for (CTLsdException name : this.latentStyles.getLsdExceptionArray()) {
            if (name.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
