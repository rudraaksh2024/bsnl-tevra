package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STHorizontalAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignment;

public interface CTCellAlignment extends XmlObject {
    public static final DocumentFactory<CTCellAlignment> Factory;
    public static final SchemaType type;

    STHorizontalAlignment.Enum getHorizontal();

    long getIndent();

    boolean getJustifyLastLine();

    long getReadingOrder();

    int getRelativeIndent();

    boolean getShrinkToFit();

    BigInteger getTextRotation();

    STVerticalAlignment.Enum getVertical();

    boolean getWrapText();

    boolean isSetHorizontal();

    boolean isSetIndent();

    boolean isSetJustifyLastLine();

    boolean isSetReadingOrder();

    boolean isSetRelativeIndent();

    boolean isSetShrinkToFit();

    boolean isSetTextRotation();

    boolean isSetVertical();

    boolean isSetWrapText();

    void setHorizontal(STHorizontalAlignment.Enum enumR);

    void setIndent(long j);

    void setJustifyLastLine(boolean z);

    void setReadingOrder(long j);

    void setRelativeIndent(int i);

    void setShrinkToFit(boolean z);

    void setTextRotation(BigInteger bigInteger);

    void setVertical(STVerticalAlignment.Enum enumR);

    void setWrapText(boolean z);

    void unsetHorizontal();

    void unsetIndent();

    void unsetJustifyLastLine();

    void unsetReadingOrder();

    void unsetRelativeIndent();

    void unsetShrinkToFit();

    void unsetTextRotation();

    void unsetVertical();

    void unsetWrapText();

    STHorizontalAlignment xgetHorizontal();

    XmlUnsignedInt xgetIndent();

    XmlBoolean xgetJustifyLastLine();

    XmlUnsignedInt xgetReadingOrder();

    XmlInt xgetRelativeIndent();

    XmlBoolean xgetShrinkToFit();

    STTextRotation xgetTextRotation();

    STVerticalAlignment xgetVertical();

    XmlBoolean xgetWrapText();

    void xsetHorizontal(STHorizontalAlignment sTHorizontalAlignment);

    void xsetIndent(XmlUnsignedInt xmlUnsignedInt);

    void xsetJustifyLastLine(XmlBoolean xmlBoolean);

    void xsetReadingOrder(XmlUnsignedInt xmlUnsignedInt);

    void xsetRelativeIndent(XmlInt xmlInt);

    void xsetShrinkToFit(XmlBoolean xmlBoolean);

    void xsetTextRotation(STTextRotation sTTextRotation);

    void xsetVertical(STVerticalAlignment sTVerticalAlignment);

    void xsetWrapText(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTCellAlignment> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellalignmentb580type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
