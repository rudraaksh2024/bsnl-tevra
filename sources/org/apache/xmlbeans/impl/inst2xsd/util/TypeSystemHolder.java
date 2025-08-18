package org.apache.xmlbeans.impl.inst2xsd.util;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;

public class TypeSystemHolder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Map _globalAttributes = new LinkedHashMap();
    Map _globalElements = new LinkedHashMap();
    Map _globalTypes = new LinkedHashMap();

    public void addGlobalElement(Element element) {
        this._globalElements.put(element.getName(), element);
    }

    public Element getGlobalElement(QName qName) {
        return (Element) this._globalElements.get(qName);
    }

    public Element[] getGlobalElements() {
        Collection values = this._globalElements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public void addGlobalAttribute(Attribute attribute) {
        this._globalAttributes.put(attribute.getName(), attribute);
    }

    public Attribute getGlobalAttribute(QName qName) {
        return (Attribute) this._globalAttributes.get(qName);
    }

    public Attribute[] getGlobalAttributes() {
        Collection values = this._globalAttributes.values();
        return (Attribute[]) values.toArray(new Attribute[values.size()]);
    }

    public void addGlobalType(Type type) {
        this._globalTypes.put(type.getName(), type);
    }

    public Type getGlobalType(QName qName) {
        return (Type) this._globalTypes.get(qName);
    }

    public Type[] getGlobalTypes() {
        Collection values = this._globalTypes.values();
        return (Type[]) values.toArray(new Type[values.size()]);
    }

    public SchemaDocument[] getSchemaDocuments() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (QName qName : this._globalElements.keySet()) {
            String namespaceURI = qName.getNamespaceURI();
            fillUpGlobalElement((Element) this._globalElements.get(qName), getSchemaDocumentForTNS(linkedHashMap, namespaceURI), namespaceURI);
        }
        for (QName qName2 : this._globalAttributes.keySet()) {
            String namespaceURI2 = qName2.getNamespaceURI();
            fillUpGlobalAttribute((Attribute) this._globalAttributes.get(qName2), getSchemaDocumentForTNS(linkedHashMap, namespaceURI2), namespaceURI2);
        }
        for (QName qName3 : this._globalTypes.keySet()) {
            String namespaceURI3 = qName3.getNamespaceURI();
            fillUpGlobalType((Type) this._globalTypes.get(qName3), getSchemaDocumentForTNS(linkedHashMap, namespaceURI3), namespaceURI3);
        }
        Collection values = linkedHashMap.values();
        return (SchemaDocument[]) values.toArray(new SchemaDocument[values.size()]);
    }

    private static SchemaDocument getSchemaDocumentForTNS(Map map, String str) {
        SchemaDocument schemaDocument = (SchemaDocument) map.get(str);
        if (schemaDocument != null) {
            return schemaDocument;
        }
        SchemaDocument newInstance = SchemaDocument.Factory.newInstance();
        map.put(str, newInstance);
        return newInstance;
    }

    private static SchemaDocument.Schema getTopLevelSchemaElement(SchemaDocument schemaDocument, String str) {
        SchemaDocument.Schema schema = schemaDocument.getSchema();
        if (schema == null) {
            schema = schemaDocument.addNewSchema();
            schema.setAttributeFormDefault(FormChoice.Enum.forString("unqualified"));
            schema.setElementFormDefault(FormChoice.Enum.forString("qualified"));
            if (!str.equals("")) {
                schema.setTargetNamespace(str);
            }
        }
        return schema;
    }

    private void fillUpGlobalElement(Element element, SchemaDocument schemaDocument, String str) {
        TopLevelElement addNewElement = getTopLevelSchemaElement(schemaDocument, str).addNewElement();
        addNewElement.setName(element.getName().getLocalPart());
        if (element.isNillable()) {
            addNewElement.setNillable(element.isNillable());
        }
        fillUpElementDocumentation(addNewElement, element.getComment());
        fillUpTypeOnElement(element.getType(), addNewElement, str);
    }

    /* access modifiers changed from: protected */
    public void fillUpLocalElement(Element element, LocalElement localElement, String str) {
        fillUpElementDocumentation(localElement, element.getComment());
        if (!element.isRef()) {
            fillUpTypeOnElement(element.getType(), localElement, str);
            localElement.setName(element.getName().getLocalPart());
        } else {
            localElement.setRef(element.getName());
        }
        if (element.getMaxOccurs() == -1) {
            localElement.setMaxOccurs("unbounded");
        }
        if (element.getMinOccurs() != 1) {
            localElement.setMinOccurs(new BigInteger("" + element.getMinOccurs()));
        }
        if (element.isNillable()) {
            localElement.setNillable(element.isNillable());
        }
    }

    private void fillUpTypeOnElement(Type type, Element element, String str) {
        if (type.isGlobal()) {
            element.setType(type.getName());
        } else if (type.getContentType() != 1) {
            fillUpContentForComplexType(type, element.addNewComplexType(), str);
        } else if (type.isEnumeration()) {
            fillUpEnumeration(type, element);
        } else {
            element.setType(type.getName());
        }
    }

    private void fillUpEnumeration(Type type, Element element) {
        RestrictionDocument.Restriction addNewRestriction = element.addNewSimpleType().addNewRestriction();
        addNewRestriction.setBase(type.getName());
        int i = 0;
        if (type.isQNameEnumeration()) {
            while (i < type.getEnumerationQNames().size()) {
                QName qName = type.getEnumerationQNames().get(i);
                XmlQName newValue = XmlQName.Factory.newValue(qName);
                NoFixedFacet addNewEnumeration = addNewRestriction.addNewEnumeration();
                XmlCursor newCursor = addNewEnumeration.newCursor();
                String prefixForNamespace = newCursor.prefixForNamespace(qName.getNamespaceURI());
                newCursor.dispose();
                addNewEnumeration.setValue(XmlQName.Factory.newValue(new QName(qName.getNamespaceURI(), qName.getLocalPart(), prefixForNamespace)));
                i++;
            }
            return;
        }
        while (i < type.getEnumerationValues().size()) {
            addNewRestriction.addNewEnumeration().setValue(XmlString.Factory.newValue(type.getEnumerationValues().get(i)));
            i++;
        }
    }

    private void fillUpAttributesInComplexTypesSimpleContent(Type type, SimpleExtensionType simpleExtensionType, String str) {
        for (int i = 0; i < type.getAttributes().size(); i++) {
            fillUpLocalAttribute(type.getAttributes().get(i), simpleExtensionType.addNewAttribute(), str);
        }
    }

    private void fillUpAttributesInComplexTypesComplexContent(Type type, ComplexType complexType, String str) {
        for (int i = 0; i < type.getAttributes().size(); i++) {
            fillUpLocalAttribute(type.getAttributes().get(i), complexType.addNewAttribute(), str);
        }
    }

    /* access modifiers changed from: protected */
    public void fillUpLocalAttribute(Attribute attribute, Attribute attribute2, String str) {
        if (attribute.isRef()) {
            attribute2.setRef(attribute.getRef().getName());
            return;
        }
        attribute2.setType(attribute.getType().getName());
        attribute2.setName(attribute.getName().getLocalPart());
        if (attribute.isOptional()) {
            attribute2.setUse(Attribute.Use.OPTIONAL);
        }
    }

    /* access modifiers changed from: protected */
    public void fillUpContentForComplexType(Type type, ComplexType complexType, String str) {
        ExplicitGroup explicitGroup;
        if (type.getContentType() == 2) {
            SimpleExtensionType addNewExtension = complexType.addNewSimpleContent().addNewExtension();
            addNewExtension.setBase(type.getExtensionType().getName());
            fillUpAttributesInComplexTypesSimpleContent(type, addNewExtension, str);
            return;
        }
        if (type.getContentType() == 4) {
            complexType.setMixed(true);
        }
        if (type.getContentType() == 5) {
            explicitGroup = null;
        } else if (type.getTopParticleForComplexOrMixedContent() == 1) {
            explicitGroup = complexType.addNewSequence();
        } else if (type.getTopParticleForComplexOrMixedContent() == 2) {
            explicitGroup = complexType.addNewChoice();
            explicitGroup.setMaxOccurs("unbounded");
            explicitGroup.setMinOccurs(new BigInteger("0"));
        } else {
            throw new IllegalStateException("Unknown particle type in complex and mixed content");
        }
        for (int i = 0; i < type.getElements().size(); i++) {
            fillUpLocalElement(type.getElements().get(i), explicitGroup.addNewElement(), str);
        }
        fillUpAttributesInComplexTypesComplexContent(type, complexType, str);
    }

    private void fillUpGlobalAttribute(Attribute attribute, SchemaDocument schemaDocument, String str) {
        TopLevelAttribute addNewAttribute = getTopLevelSchemaElement(schemaDocument, str).addNewAttribute();
        addNewAttribute.setName(attribute.getName().getLocalPart());
        Type type = attribute.getType();
        if (type.getContentType() == 1) {
            addNewAttribute.setType(type.getName());
            return;
        }
        throw new IllegalStateException();
    }

    private static void fillUpElementDocumentation(Element element, String str) {
        if (str != null && str.length() > 0) {
            element.addNewAnnotation().addNewDocumentation().set(XmlString.Factory.newValue(str));
        }
    }

    private void fillUpGlobalType(Type type, SchemaDocument schemaDocument, String str) {
        TopLevelComplexType addNewComplexType = getTopLevelSchemaElement(schemaDocument, str).addNewComplexType();
        addNewComplexType.setName(type.getName().getLocalPart());
        fillUpContentForComplexType(type, addNewComplexType, str);
    }

    public String toString() {
        return "TypeSystemHolder{\n\n_globalElements=" + this._globalElements + "\n\n_globalAttributes=" + this._globalAttributes + "\n\n_globalTypes=" + this._globalTypes + "\n}";
    }
}
