package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaIntHolderEx;
import org.apache.xmlbeans.impl.values.JavaIntegerHolderEx;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTextRotation;

public class STTextRotationImpl extends XmlUnionImpl implements STTextRotation, STTextRotation.Member, STTextRotation.Member2 {
    private static final long serialVersionUID = 1;

    public STTextRotationImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STTextRotationImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }

    public static class MemberImpl extends JavaIntHolderEx implements STTextRotation.Member {
        private static final long serialVersionUID = 1;

        public MemberImpl(SchemaType schemaType) {
            super(schemaType, false);
        }

        protected MemberImpl(SchemaType schemaType, boolean z) {
            super(schemaType, z);
        }
    }

    public static class MemberImpl2 extends JavaIntegerHolderEx implements STTextRotation.Member2 {
        private static final long serialVersionUID = 1;

        public MemberImpl2(SchemaType schemaType) {
            super(schemaType, false);
        }

        protected MemberImpl2(SchemaType schemaType, boolean z) {
            super(schemaType, z);
        }
    }
}
