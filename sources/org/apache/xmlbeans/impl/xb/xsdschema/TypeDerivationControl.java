package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface TypeDerivationControl extends DerivationControl {
    public static final DerivationControl.Enum EXTENSION = DerivationControl.EXTENSION;
    public static final SimpleTypeFactory<TypeDerivationControl> Factory;
    public static final int INT_EXTENSION = 2;
    public static final int INT_LIST = 4;
    public static final int INT_RESTRICTION = 3;
    public static final int INT_UNION = 5;
    public static final DerivationControl.Enum LIST = DerivationControl.LIST;
    public static final DerivationControl.Enum RESTRICTION = DerivationControl.RESTRICTION;
    public static final DerivationControl.Enum UNION = DerivationControl.UNION;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<TypeDerivationControl> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "typederivationcontrol3239type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
