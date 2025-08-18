package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextParagraph extends XmlObject {
    public static final DocumentFactory<CTTextParagraph> Factory;
    public static final SchemaType type;

    CTTextLineBreak addNewBr();

    CTTextCharacterProperties addNewEndParaRPr();

    CTTextField addNewFld();

    CTTextParagraphProperties addNewPPr();

    CTRegularTextRun addNewR();

    CTTextLineBreak getBrArray(int i);

    CTTextLineBreak[] getBrArray();

    List<CTTextLineBreak> getBrList();

    CTTextCharacterProperties getEndParaRPr();

    CTTextField getFldArray(int i);

    CTTextField[] getFldArray();

    List<CTTextField> getFldList();

    CTTextParagraphProperties getPPr();

    CTRegularTextRun getRArray(int i);

    CTRegularTextRun[] getRArray();

    List<CTRegularTextRun> getRList();

    CTTextLineBreak insertNewBr(int i);

    CTTextField insertNewFld(int i);

    CTRegularTextRun insertNewR(int i);

    boolean isSetEndParaRPr();

    boolean isSetPPr();

    void removeBr(int i);

    void removeFld(int i);

    void removeR(int i);

    void setBrArray(int i, CTTextLineBreak cTTextLineBreak);

    void setBrArray(CTTextLineBreak[] cTTextLineBreakArr);

    void setEndParaRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void setFldArray(int i, CTTextField cTTextField);

    void setFldArray(CTTextField[] cTTextFieldArr);

    void setPPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setRArray(int i, CTRegularTextRun cTRegularTextRun);

    void setRArray(CTRegularTextRun[] cTRegularTextRunArr);

    int sizeOfBrArray();

    int sizeOfFldArray();

    int sizeOfRArray();

    void unsetEndParaRPr();

    void unsetPPr();

    static {
        DocumentFactory<CTTextParagraph> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextparagraphcaf2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
