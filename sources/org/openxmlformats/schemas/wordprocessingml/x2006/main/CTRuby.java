package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRuby extends XmlObject {
    public static final DocumentFactory<CTRuby> Factory;
    public static final SchemaType type;

    CTRubyContent addNewRt();

    CTRubyContent addNewRubyBase();

    CTRubyPr addNewRubyPr();

    CTRubyContent getRt();

    CTRubyContent getRubyBase();

    CTRubyPr getRubyPr();

    void setRt(CTRubyContent cTRubyContent);

    void setRubyBase(CTRubyContent cTRubyContent);

    void setRubyPr(CTRubyPr cTRubyPr);

    static {
        DocumentFactory<CTRuby> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctruby9dcetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
