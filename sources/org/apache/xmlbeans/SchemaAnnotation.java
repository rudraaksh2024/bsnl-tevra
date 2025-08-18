package org.apache.xmlbeans;

import javax.xml.namespace.QName;

public interface SchemaAnnotation extends SchemaComponent {

    public interface Attribute {
        QName getName();

        String getValue();

        String getValueUri();
    }

    XmlObject[] getApplicationInformation();

    Attribute[] getAttributes();

    XmlObject[] getUserInformation();
}
