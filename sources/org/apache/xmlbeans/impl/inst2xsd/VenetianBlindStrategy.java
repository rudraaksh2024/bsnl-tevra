package org.apache.xmlbeans.impl.inst2xsd;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.Type;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;

public class VenetianBlindStrategy extends RussianDollStrategy implements XsdGenStrategy {
    private boolean compatibleTypes(Type type, Type type2) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void checkIfReferenceToGlobalTypeIsNeeded(Element element, TypeSystemHolder typeSystemHolder, Inst2XsdOptions inst2XsdOptions) {
        Type type = element.getType();
        QName name = element.getName();
        if (!type.isGlobal() && type.isComplexType()) {
            int i = 0;
            while (true) {
                String namespaceURI = name.getNamespaceURI();
                StringBuilder append = new StringBuilder().append(name.getLocalPart()).append(PackageRelationship.TYPE_ATTRIBUTE_NAME);
                String str = "";
                if (i != 0) {
                    str = str + i;
                }
                type.setName(new QName(namespaceURI, append.append(str).toString()));
                Type globalType = typeSystemHolder.getGlobalType(type.getName());
                if (globalType == null) {
                    type.setGlobal(true);
                    typeSystemHolder.addGlobalType(type);
                    return;
                } else if (compatibleTypes(globalType, type)) {
                    combineTypes(globalType, type, inst2XsdOptions);
                    element.setType(globalType);
                    return;
                } else {
                    i++;
                }
            }
        }
    }
}
