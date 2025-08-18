package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;

class SchemaContainer {
    private final List<SchemaAnnotation> _annotations = new ArrayList();
    private final List<SchemaAttributeGroup.Ref> _attributeGroups = new ArrayList();
    private final List<SchemaType.Ref> _attributeTypes = new ArrayList();
    private final List<SchemaType.Ref> _documentTypes = new ArrayList();
    private final List<SchemaGlobalAttribute.Ref> _globalAttributes = new ArrayList();
    private final List<SchemaGlobalElement.Ref> _globalElements = new ArrayList();
    private final List<SchemaType.Ref> _globalTypes = new ArrayList();
    private final List<SchemaIdentityConstraint.Ref> _identityConstraints = new ArrayList();
    boolean _immutable;
    private final List<SchemaModelGroup.Ref> _modelGroups = new ArrayList();
    private final String _namespace;
    private final List<SchemaAttributeGroup.Ref> _redefinedAttributeGroups = new ArrayList();
    private final List<SchemaType.Ref> _redefinedGlobalTypes = new ArrayList();
    private final List<SchemaModelGroup.Ref> _redefinedModelGroups = new ArrayList();
    private SchemaTypeSystem _typeSystem;

    SchemaContainer(String str) {
        this._namespace = str;
    }

    /* access modifiers changed from: package-private */
    public String getNamespace() {
        return this._namespace;
    }

    /* access modifiers changed from: package-private */
    public synchronized SchemaTypeSystem getTypeSystem() {
        return this._typeSystem;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setTypeSystem(SchemaTypeSystem schemaTypeSystem) {
        this._typeSystem = schemaTypeSystem;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setImmutable() {
        this._immutable = true;
    }

    /* access modifiers changed from: package-private */
    public synchronized void unsetImmutable() {
        this._immutable = false;
    }

    private void check_immutable() {
        if (this._immutable) {
            throw new IllegalStateException("Cannot add components to immutable SchemaContainer");
        }
    }

    /* access modifiers changed from: package-private */
    public void addGlobalElement(SchemaGlobalElement.Ref ref) {
        check_immutable();
        this._globalElements.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaGlobalElement> globalElements() {
        return (List) this._globalElements.stream().map(new SchemaContainer$$ExternalSyntheticLambda5()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addGlobalAttribute(SchemaGlobalAttribute.Ref ref) {
        check_immutable();
        this._globalAttributes.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaGlobalAttribute> globalAttributes() {
        return (List) this._globalAttributes.stream().map(new SchemaContainer$$ExternalSyntheticLambda4()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addModelGroup(SchemaModelGroup.Ref ref) {
        check_immutable();
        this._modelGroups.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaModelGroup> modelGroups() {
        return (List) this._modelGroups.stream().map(new SchemaContainer$$ExternalSyntheticLambda0()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addRedefinedModelGroup(SchemaModelGroup.Ref ref) {
        check_immutable();
        this._redefinedModelGroups.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaModelGroup> redefinedModelGroups() {
        return (List) this._redefinedModelGroups.stream().map(new SchemaContainer$$ExternalSyntheticLambda0()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addAttributeGroup(SchemaAttributeGroup.Ref ref) {
        check_immutable();
        this._attributeGroups.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaAttributeGroup> attributeGroups() {
        return (List) this._attributeGroups.stream().map(new SchemaContainer$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addRedefinedAttributeGroup(SchemaAttributeGroup.Ref ref) {
        check_immutable();
        this._redefinedAttributeGroups.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaAttributeGroup> redefinedAttributeGroups() {
        return (List) this._redefinedAttributeGroups.stream().map(new SchemaContainer$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addGlobalType(SchemaType.Ref ref) {
        check_immutable();
        this._globalTypes.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaType> globalTypes() {
        return (List) this._globalTypes.stream().map(new SchemaContainer$$ExternalSyntheticLambda3()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addRedefinedType(SchemaType.Ref ref) {
        check_immutable();
        this._redefinedGlobalTypes.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaType> redefinedGlobalTypes() {
        return (List) this._redefinedGlobalTypes.stream().map(new SchemaContainer$$ExternalSyntheticLambda3()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addDocumentType(SchemaType.Ref ref) {
        check_immutable();
        this._documentTypes.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaType> documentTypes() {
        return (List) this._documentTypes.stream().map(new SchemaContainer$$ExternalSyntheticLambda3()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addAttributeType(SchemaType.Ref ref) {
        check_immutable();
        this._attributeTypes.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaType> attributeTypes() {
        return (List) this._attributeTypes.stream().map(new SchemaContainer$$ExternalSyntheticLambda3()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addIdentityConstraint(SchemaIdentityConstraint.Ref ref) {
        check_immutable();
        this._identityConstraints.add(ref);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaIdentityConstraint> identityConstraints() {
        return (List) this._identityConstraints.stream().map(new SchemaContainer$$ExternalSyntheticLambda2()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public void addAnnotation(SchemaAnnotation schemaAnnotation) {
        check_immutable();
        this._annotations.add(schemaAnnotation);
    }

    /* access modifiers changed from: package-private */
    public List<SchemaAnnotation> annotations() {
        return Collections.unmodifiableList(this._annotations);
    }
}
