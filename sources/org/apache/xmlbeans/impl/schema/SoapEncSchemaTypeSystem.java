package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.soap.SOAPArrayType;

public class SoapEncSchemaTypeSystem extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    public static final String ARRAY_TYPE = "arrayType";
    private static final String ATTR_HREF = "href";
    private static final String ATTR_ID = "id";
    private static final String ATTR_OFFSET = "offset";
    private static final SchemaAnnotation[] EMPTY_SCHEMAANNOTATION_ARRAY = new SchemaAnnotation[0];
    private static final SchemaAttributeGroup[] EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaGlobalElement[] EMPTY_SCHEMAELEMENT_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaModelGroup[] EMPTY_SCHEMAMODELGROUP_ARRAY = new SchemaModelGroup[0];
    private static final SchemaType[] EMPTY_SCHEMATYPE_ARRAY = new SchemaType[0];
    public static final String SOAPENC = "http://schemas.xmlsoap.org/soap/encoding/";
    public static final String SOAP_ARRAY = "Array";
    private static final SoapEncSchemaTypeSystem _global = new SoapEncSchemaTypeSystem();
    private final Map<String, SchemaComponent> _handlesToObjects;
    private final SchemaGlobalAttributeImpl arrayType;
    private final SchemaTypeImpl soapArray;
    private final String soapArrayHandle;

    public SchemaAttributeGroup findAttributeGroup(QName qName) {
        return null;
    }

    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName qName) {
        return null;
    }

    public SchemaType findAttributeType(QName qName) {
        return null;
    }

    public SchemaType.Ref findAttributeTypeRef(QName qName) {
        return null;
    }

    public SchemaType findDocumentType(QName qName) {
        return null;
    }

    public SchemaType.Ref findDocumentTypeRef(QName qName) {
        return null;
    }

    public SchemaGlobalElement findElement(QName qName) {
        return null;
    }

    public SchemaGlobalElement.Ref findElementRef(QName qName) {
        return null;
    }

    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName qName) {
        return null;
    }

    public SchemaModelGroup findModelGroup(QName qName) {
        return null;
    }

    public SchemaModelGroup.Ref findModelGroupRef(QName qName) {
        return null;
    }

    public String getName() {
        return "schema.typesystem.soapenc.builtin";
    }

    public InputStream getSourceAsStream(String str) {
        return null;
    }

    public void resolve() {
    }

    public SchemaType typeForClassname(String str) {
        return null;
    }

    public static SchemaTypeSystem get() {
        return _global;
    }

    private SoapEncSchemaTypeSystem() {
        HashMap hashMap = new HashMap();
        this._handlesToObjects = hashMap;
        SchemaContainer schemaContainer = new SchemaContainer("http://schemas.xmlsoap.org/soap/encoding/");
        schemaContainer.setTypeSystem(this);
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(schemaContainer, true);
        this.soapArray = schemaTypeImpl;
        schemaContainer.addGlobalType(schemaTypeImpl.getRef());
        schemaTypeImpl.setName(new QName("http://schemas.xmlsoap.org/soap/encoding/", SOAP_ARRAY));
        String str = SOAP_ARRAY.toLowerCase(Locale.ROOT) + "type";
        this.soapArrayHandle = str;
        schemaTypeImpl.setComplexTypeVariety(3);
        schemaTypeImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        schemaTypeImpl.setBaseDepth(1);
        schemaTypeImpl.setDerivationType(2);
        schemaTypeImpl.setSimpleTypeVariety(0);
        SchemaParticleImpl schemaParticleImpl = new SchemaParticleImpl();
        schemaParticleImpl.setParticleType(3);
        schemaParticleImpl.setMinOccurs(BigInteger.ZERO);
        schemaParticleImpl.setMaxOccurs(BigInteger.ONE);
        schemaParticleImpl.setTransitionRules(QNameSet.ALL, true);
        SchemaParticleImpl[] schemaParticleImplArr = new SchemaParticleImpl[1];
        schemaParticleImpl.setParticleChildren(schemaParticleImplArr);
        SchemaParticleImpl schemaParticleImpl2 = new SchemaParticleImpl();
        schemaParticleImpl2.setParticleType(5);
        schemaParticleImpl2.setWildcardSet(QNameSet.ALL);
        schemaParticleImpl2.setWildcardProcess(2);
        schemaParticleImpl2.setMinOccurs(BigInteger.ZERO);
        schemaParticleImpl2.setMaxOccurs((BigInteger) null);
        schemaParticleImpl2.setTransitionRules(QNameSet.ALL, true);
        schemaParticleImplArr[0] = schemaParticleImpl2;
        SchemaAttributeModelImpl schemaAttributeModelImpl = new SchemaAttributeModelImpl();
        schemaAttributeModelImpl.setWildcardProcess(2);
        HashSet hashSet = new HashSet();
        hashSet.add("http://schemas.xmlsoap.org/soap/encoding/");
        schemaAttributeModelImpl.setWildcardSet(QNameSet.forSets(hashSet, (Set<String>) null, Collections.emptySet(), Collections.emptySet()));
        SchemaLocalAttributeImpl schemaLocalAttributeImpl = new SchemaLocalAttributeImpl();
        schemaLocalAttributeImpl.init(new QName("", ATTR_ID), BuiltinSchemaTypeSystem.ST_ID.getRef(), 2, (String) null, (XmlObject) null, (XmlValueRef) null, false, (SOAPArrayType) null, (SchemaAnnotation) null, (Object) null);
        schemaAttributeModelImpl.addAttribute(schemaLocalAttributeImpl);
        SchemaLocalAttributeImpl schemaLocalAttributeImpl2 = new SchemaLocalAttributeImpl();
        schemaLocalAttributeImpl2.init(new QName("", ATTR_HREF), BuiltinSchemaTypeSystem.ST_ANY_URI.getRef(), 2, (String) null, (XmlObject) null, (XmlValueRef) null, false, (SOAPArrayType) null, (SchemaAnnotation) null, (Object) null);
        schemaAttributeModelImpl.addAttribute(schemaLocalAttributeImpl2);
        SchemaLocalAttributeImpl schemaLocalAttributeImpl3 = new SchemaLocalAttributeImpl();
        schemaLocalAttributeImpl3.init(new QName("http://schemas.xmlsoap.org/soap/encoding/", ARRAY_TYPE), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, (String) null, (XmlObject) null, (XmlValueRef) null, false, (SOAPArrayType) null, (SchemaAnnotation) null, (Object) null);
        schemaAttributeModelImpl.addAttribute(schemaLocalAttributeImpl3);
        SchemaLocalAttributeImpl schemaLocalAttributeImpl4 = new SchemaLocalAttributeImpl();
        schemaLocalAttributeImpl4.init(new QName("http://schemas.xmlsoap.org/soap/encoding/", "offset"), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, (String) null, (XmlObject) null, (XmlValueRef) null, false, (SOAPArrayType) null, (SchemaAnnotation) null, (Object) null);
        schemaAttributeModelImpl.addAttribute(schemaLocalAttributeImpl4);
        Map emptyMap = Collections.emptyMap();
        String str2 = ARRAY_TYPE;
        schemaTypeImpl.setContentModel(schemaParticleImpl, schemaAttributeModelImpl, emptyMap, Collections.emptyMap(), false);
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl = new SchemaGlobalAttributeImpl(schemaContainer);
        this.arrayType = schemaGlobalAttributeImpl;
        schemaContainer.addGlobalAttribute(schemaGlobalAttributeImpl.getRef());
        schemaGlobalAttributeImpl.init(new QName("http://schemas.xmlsoap.org/soap/encoding/", str2), BuiltinSchemaTypeSystem.ST_STRING.getRef(), 2, (String) null, (XmlObject) null, (XmlValueRef) null, false, (SOAPArrayType) null, (SchemaAnnotation) null, (Object) null);
        hashMap.put(str, schemaTypeImpl);
        hashMap.put(str2.toLowerCase(Locale.ROOT) + "attribute", schemaGlobalAttributeImpl);
        schemaContainer.setImmutable();
    }

    public SchemaType findType(QName qName) {
        if (!"http://schemas.xmlsoap.org/soap/encoding/".equals(qName.getNamespaceURI()) || !SOAP_ARRAY.equals(qName.getLocalPart())) {
            return null;
        }
        return this.soapArray;
    }

    public SchemaGlobalAttribute findAttribute(QName qName) {
        if (!"http://schemas.xmlsoap.org/soap/encoding/".equals(qName.getNamespaceURI()) || !ARRAY_TYPE.equals(qName.getLocalPart())) {
            return null;
        }
        return this.arrayType;
    }

    public boolean isNamespaceDefined(String str) {
        return "http://schemas.xmlsoap.org/soap/encoding/".equals(str);
    }

    public SchemaType.Ref findTypeRef(QName qName) {
        SchemaType findType = findType(qName);
        if (findType == null) {
            return null;
        }
        return findType.getRef();
    }

    public SchemaGlobalAttribute.Ref findAttributeRef(QName qName) {
        SchemaGlobalAttribute findAttribute = findAttribute(qName);
        if (findAttribute == null) {
            return null;
        }
        return findAttribute.getRef();
    }

    public ClassLoader getClassLoader() {
        return SoapEncSchemaTypeSystem.class.getClassLoader();
    }

    public SchemaType[] globalTypes() {
        return new SchemaType[]{this.soapArray};
    }

    public SchemaType[] documentTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    public SchemaType[] attributeTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    public SchemaGlobalElement[] globalElements() {
        return EMPTY_SCHEMAELEMENT_ARRAY;
    }

    public SchemaGlobalAttribute[] globalAttributes() {
        return new SchemaGlobalAttribute[]{this.arrayType};
    }

    public SchemaModelGroup[] modelGroups() {
        return EMPTY_SCHEMAMODELGROUP_ARRAY;
    }

    public SchemaAttributeGroup[] attributeGroups() {
        return EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY;
    }

    public SchemaAnnotation[] annotations() {
        return EMPTY_SCHEMAANNOTATION_ARRAY;
    }

    public String handleForType(SchemaType schemaType) {
        if (this.soapArray.equals(schemaType)) {
            return this.soapArrayHandle;
        }
        return null;
    }

    public SchemaComponent resolveHandle(String str) {
        return this._handlesToObjects.get(str);
    }

    public SchemaType typeForHandle(String str) {
        return (SchemaType) this._handlesToObjects.get(str);
    }

    public void saveToDirectory(File file) {
        throw new UnsupportedOperationException("The builtin soap encoding schema type system cannot be saved.");
    }

    public void save(Filer filer) {
        throw new UnsupportedOperationException("The builtin soap encoding schema type system cannot be saved.");
    }
}
