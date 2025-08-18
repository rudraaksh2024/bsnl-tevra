package org.apache.xmlbeans.impl.xb.ltgfmt;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

public interface FileDesc extends XmlObject {
    public static final DocumentFactory<FileDesc> Factory;
    public static final SchemaType type;

    Code addNewCode();

    Code getCode();

    String getFileName();

    String getFolder();

    Role.Enum getRole();

    String getTsDir();

    boolean getValidity();

    boolean isSetCode();

    boolean isSetFileName();

    boolean isSetFolder();

    boolean isSetRole();

    boolean isSetTsDir();

    boolean isSetValidity();

    void setCode(Code code);

    void setFileName(String str);

    void setFolder(String str);

    void setRole(Role.Enum enumR);

    void setTsDir(String str);

    void setValidity(boolean z);

    void unsetCode();

    void unsetFileName();

    void unsetFolder();

    void unsetRole();

    void unsetTsDir();

    void unsetValidity();

    XmlToken xgetFileName();

    XmlToken xgetFolder();

    Role xgetRole();

    XmlToken xgetTsDir();

    XmlBoolean xgetValidity();

    void xsetFileName(XmlToken xmlToken);

    void xsetFolder(XmlToken xmlToken);

    void xsetRole(Role role);

    void xsetTsDir(XmlToken xmlToken);

    void xsetValidity(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<FileDesc> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "filedesc9392type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Role extends XmlToken {
        public static final ElementFactory<Role> Factory;
        public static final Enum INSTANCE = Enum.forString("instance");
        public static final int INT_INSTANCE = 2;
        public static final int INT_RESOURCE = 3;
        public static final int INT_SCHEMA = 1;
        public static final Enum RESOURCE = Enum.forString("resource");
        public static final Enum SCHEMA = Enum.forString("schema");
        public static final SchemaType type;

        StringEnumAbstractBase getEnumValue();

        void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

        static {
            ElementFactory<Role> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "role21a8attrtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }

        public static final class Enum extends StringEnumAbstractBase {
            static final int INT_INSTANCE = 2;
            static final int INT_RESOURCE = 3;
            static final int INT_SCHEMA = 1;
            private static final long serialVersionUID = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("schema", 1), new Enum("instance", 2), new Enum("resource", 3)});

            public static Enum forString(String str) {
                return (Enum) table.forString(str);
            }

            public static Enum forInt(int i) {
                return (Enum) table.forInt(i);
            }

            private Enum(String str, int i) {
                super(str, i);
            }

            private Object readResolve() {
                return forInt(intValue());
            }
        }
    }
}
