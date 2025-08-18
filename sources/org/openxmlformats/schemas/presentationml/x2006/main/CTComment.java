package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;

public interface CTComment extends XmlObject {
    public static final DocumentFactory<CTComment> Factory;
    public static final SchemaType type;

    CTExtensionListModify addNewExtLst();

    CTPoint2D addNewPos();

    long getAuthorId();

    Calendar getDt();

    CTExtensionListModify getExtLst();

    long getIdx();

    CTPoint2D getPos();

    String getText();

    boolean isSetDt();

    boolean isSetExtLst();

    void setAuthorId(long j);

    void setDt(Calendar calendar);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setIdx(long j);

    void setPos(CTPoint2D cTPoint2D);

    void setText(String str);

    void unsetDt();

    void unsetExtLst();

    XmlUnsignedInt xgetAuthorId();

    XmlDateTime xgetDt();

    STIndex xgetIdx();

    XmlString xgetText();

    void xsetAuthorId(XmlUnsignedInt xmlUnsignedInt);

    void xsetDt(XmlDateTime xmlDateTime);

    void xsetIdx(STIndex sTIndex);

    void xsetText(XmlString xmlString);

    static {
        DocumentFactory<CTComment> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcomment2d10type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
