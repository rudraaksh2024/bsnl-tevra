package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

public class SchemaAnnotationImpl implements SchemaAnnotation {
    private AppinfoDocument.Appinfo[] _appInfo;
    private String[] _appInfoAsXml;
    private SchemaAnnotation.Attribute[] _attributes;
    private SchemaContainer _container;
    private DocumentationDocument.Documentation[] _documentation;
    private String[] _documentationAsXml;
    private String _filename;

    public SchemaComponent.Ref getComponentRef() {
        return null;
    }

    public int getComponentType() {
        return 8;
    }

    public QName getName() {
        return null;
    }

    public void setFilename(String str) {
        this._filename = str;
    }

    public String getSourceName() {
        return this._filename;
    }

    public XmlObject[] getApplicationInformation() {
        if (this._appInfo == null) {
            int length = this._appInfoAsXml.length;
            this._appInfo = new AppinfoDocument.Appinfo[length];
            for (int i = 0; i < length; i++) {
                try {
                    this._appInfo[i] = AppinfoDocument.Factory.parse(this._appInfoAsXml[i]).getAppinfo();
                } catch (XmlException unused) {
                    this._appInfo[i] = AppinfoDocument.Factory.newInstance().getAppinfo();
                }
            }
        }
        return this._appInfo;
    }

    public XmlObject[] getUserInformation() {
        if (this._documentation == null) {
            int length = this._documentationAsXml.length;
            this._documentation = new DocumentationDocument.Documentation[length];
            for (int i = 0; i < length; i++) {
                try {
                    this._documentation[i] = DocumentationDocument.Factory.parse(this._documentationAsXml[i]).getDocumentation();
                } catch (XmlException unused) {
                    this._documentation[i] = DocumentationDocument.Factory.newInstance().getDocumentation();
                }
            }
        }
        return this._documentation;
    }

    public SchemaAnnotation.Attribute[] getAttributes() {
        return this._attributes;
    }

    public SchemaTypeSystem getTypeSystem() {
        SchemaContainer schemaContainer = this._container;
        if (schemaContainer != null) {
            return schemaContainer.getTypeSystem();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public SchemaContainer getContainer() {
        return this._container;
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer schemaContainer, Annotated annotated) {
        return getAnnotation(schemaContainer, annotated, annotated.getAnnotation());
    }

    public static SchemaAnnotationImpl getAnnotation(SchemaContainer schemaContainer, XmlObject xmlObject, AnnotationDocument.Annotation annotation) {
        if (StscState.get().noAnn()) {
            return null;
        }
        SchemaAnnotationImpl schemaAnnotationImpl = new SchemaAnnotationImpl(schemaContainer);
        ArrayList arrayList = new ArrayList(2);
        addNoSchemaAttributes(xmlObject, arrayList);
        if (annotation != null) {
            schemaAnnotationImpl._appInfo = annotation.getAppinfoArray();
            schemaAnnotationImpl._documentation = annotation.getDocumentationArray();
            addNoSchemaAttributes(annotation, arrayList);
        } else if (arrayList.size() == 0) {
            return null;
        } else {
            schemaAnnotationImpl._appInfo = new AppinfoDocument.Appinfo[0];
            schemaAnnotationImpl._documentation = new DocumentationDocument.Documentation[0];
        }
        schemaAnnotationImpl._attributes = (AttributeImpl[]) arrayList.toArray(new AttributeImpl[arrayList.size()]);
        return schemaAnnotationImpl;
    }

    private static void addNoSchemaAttributes(XmlObject xmlObject, List list) {
        XmlCursor newCursor = xmlObject.newCursor();
        for (boolean firstAttribute = newCursor.toFirstAttribute(); firstAttribute; firstAttribute = newCursor.toNextAttribute()) {
            QName name = newCursor.getName();
            String namespaceURI = name.getNamespaceURI();
            String str = "";
            if (!str.equals(namespaceURI) && !"http://www.w3.org/2001/XMLSchema".equals(namespaceURI)) {
                String textValue = newCursor.getTextValue();
                if (textValue.indexOf(58) > 0) {
                    str = textValue.substring(0, textValue.indexOf(58));
                }
                newCursor.push();
                newCursor.toParent();
                String namespaceForPrefix = newCursor.namespaceForPrefix(str);
                newCursor.pop();
                list.add(new AttributeImpl(name, textValue, namespaceForPrefix));
            }
        }
        newCursor.dispose();
    }

    private SchemaAnnotationImpl(SchemaContainer schemaContainer) {
        this._container = schemaContainer;
    }

    SchemaAnnotationImpl(SchemaContainer schemaContainer, String[] strArr, String[] strArr2, SchemaAnnotation.Attribute[] attributeArr) {
        this._container = schemaContainer;
        this._appInfoAsXml = strArr;
        this._documentationAsXml = strArr2;
        this._attributes = attributeArr;
    }

    static class AttributeImpl implements SchemaAnnotation.Attribute {
        private QName _name;
        private String _value;
        private String _valueUri;

        AttributeImpl(QName qName, String str, String str2) {
            this._name = qName;
            this._value = str;
            this._valueUri = str2;
        }

        public QName getName() {
            return this._name;
        }

        public String getValue() {
            return this._value;
        }

        public String getValueUri() {
            return this._valueUri;
        }
    }
}
