package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

public interface CTDataValidation extends XmlObject {
    public static final DocumentFactory<CTDataValidation> Factory;
    public static final SchemaType type;

    boolean getAllowBlank();

    String getError();

    STDataValidationErrorStyle.Enum getErrorStyle();

    String getErrorTitle();

    String getFormula1();

    String getFormula2();

    STDataValidationImeMode$Enum getImeMode();

    STDataValidationOperator.Enum getOperator();

    String getPrompt();

    String getPromptTitle();

    boolean getShowDropDown();

    boolean getShowErrorMessage();

    boolean getShowInputMessage();

    List getSqref();

    STDataValidationType.Enum getType();

    boolean isSetAllowBlank();

    boolean isSetError();

    boolean isSetErrorStyle();

    boolean isSetErrorTitle();

    boolean isSetFormula1();

    boolean isSetFormula2();

    boolean isSetImeMode();

    boolean isSetOperator();

    boolean isSetPrompt();

    boolean isSetPromptTitle();

    boolean isSetShowDropDown();

    boolean isSetShowErrorMessage();

    boolean isSetShowInputMessage();

    boolean isSetType();

    void setAllowBlank(boolean z);

    void setError(String str);

    void setErrorStyle(STDataValidationErrorStyle.Enum enumR);

    void setErrorTitle(String str);

    void setFormula1(String str);

    void setFormula2(String str);

    void setImeMode(STDataValidationImeMode$Enum sTDataValidationImeMode$Enum);

    void setOperator(STDataValidationOperator.Enum enumR);

    void setPrompt(String str);

    void setPromptTitle(String str);

    void setShowDropDown(boolean z);

    void setShowErrorMessage(boolean z);

    void setShowInputMessage(boolean z);

    void setSqref(List list);

    void setType(STDataValidationType.Enum enumR);

    void unsetAllowBlank();

    void unsetError();

    void unsetErrorStyle();

    void unsetErrorTitle();

    void unsetFormula1();

    void unsetFormula2();

    void unsetImeMode();

    void unsetOperator();

    void unsetPrompt();

    void unsetPromptTitle();

    void unsetShowDropDown();

    void unsetShowErrorMessage();

    void unsetShowInputMessage();

    void unsetType();

    XmlBoolean xgetAllowBlank();

    STXstring xgetError();

    STDataValidationErrorStyle xgetErrorStyle();

    STXstring xgetErrorTitle();

    STFormula xgetFormula1();

    STFormula xgetFormula2();

    STDataValidationImeMode xgetImeMode();

    STDataValidationOperator xgetOperator();

    STXstring xgetPrompt();

    STXstring xgetPromptTitle();

    XmlBoolean xgetShowDropDown();

    XmlBoolean xgetShowErrorMessage();

    XmlBoolean xgetShowInputMessage();

    STSqref xgetSqref();

    STDataValidationType xgetType();

    void xsetAllowBlank(XmlBoolean xmlBoolean);

    void xsetError(STXstring sTXstring);

    void xsetErrorStyle(STDataValidationErrorStyle sTDataValidationErrorStyle);

    void xsetErrorTitle(STXstring sTXstring);

    void xsetFormula1(STFormula sTFormula);

    void xsetFormula2(STFormula sTFormula);

    void xsetImeMode(STDataValidationImeMode sTDataValidationImeMode);

    void xsetOperator(STDataValidationOperator sTDataValidationOperator);

    void xsetPrompt(STXstring sTXstring);

    void xsetPromptTitle(STXstring sTXstring);

    void xsetShowDropDown(XmlBoolean xmlBoolean);

    void xsetShowErrorMessage(XmlBoolean xmlBoolean);

    void xsetShowInputMessage(XmlBoolean xmlBoolean);

    void xsetSqref(STSqref sTSqref);

    void xsetType(STDataValidationType sTDataValidationType);

    static {
        DocumentFactory<CTDataValidation> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdatavalidation9d0ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
