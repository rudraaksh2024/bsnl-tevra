package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ReducedDerivationControl extends DerivationControl {
    public static final DerivationControl.Enum EXTENSION = DerivationControl.EXTENSION;
    public static final SimpleTypeFactory<ReducedDerivationControl> Factory;
    public static final int INT_EXTENSION = 2;
    public static final int INT_RESTRICTION = 3;
    public static final DerivationControl.Enum RESTRICTION = DerivationControl.RESTRICTION;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<ReducedDerivationControl> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "reducedderivationcontrole1cbtype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
