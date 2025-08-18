package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPPr extends CTPPrBase {
    public static final DocumentFactory<CTPPr> Factory;
    public static final SchemaType type;

    CTPPrChange addNewPPrChange();

    CTParaRPr addNewRPr();

    CTSectPr addNewSectPr();

    CTPPrChange getPPrChange();

    CTParaRPr getRPr();

    CTSectPr getSectPr();

    boolean isSetPPrChange();

    boolean isSetRPr();

    boolean isSetSectPr();

    void setPPrChange(CTPPrChange cTPPrChange);

    void setRPr(CTParaRPr cTParaRPr);

    void setSectPr(CTSectPr cTSectPr);

    void unsetPPrChange();

    void unsetRPr();

    void unsetSectPr();

    static {
        DocumentFactory<CTPPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctppr01c0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
