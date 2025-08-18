package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTRst extends XmlObject {
    public static final DocumentFactory<CTRst> Factory;
    public static final SchemaType type;

    CTPhoneticPr addNewPhoneticPr();

    CTRElt addNewR();

    CTPhoneticRun addNewRPh();

    CTPhoneticPr getPhoneticPr();

    CTRElt getRArray(int i);

    CTRElt[] getRArray();

    List<CTRElt> getRList();

    CTPhoneticRun getRPhArray(int i);

    CTPhoneticRun[] getRPhArray();

    List<CTPhoneticRun> getRPhList();

    String getT();

    CTRElt insertNewR(int i);

    CTPhoneticRun insertNewRPh(int i);

    boolean isSetPhoneticPr();

    boolean isSetT();

    void removeR(int i);

    void removeRPh(int i);

    void setPhoneticPr(CTPhoneticPr cTPhoneticPr);

    void setRArray(int i, CTRElt cTRElt);

    void setRArray(CTRElt[] cTREltArr);

    void setRPhArray(int i, CTPhoneticRun cTPhoneticRun);

    void setRPhArray(CTPhoneticRun[] cTPhoneticRunArr);

    void setT(String str);

    int sizeOfRArray();

    int sizeOfRPhArray();

    void unsetPhoneticPr();

    void unsetT();

    STXstring xgetT();

    void xsetT(STXstring sTXstring);

    static {
        DocumentFactory<CTRst> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrsta472type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
