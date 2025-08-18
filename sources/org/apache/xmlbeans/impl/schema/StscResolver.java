package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;

public class StscResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void resolveAll() {
        StscState stscState = StscState.get();
        SchemaType[] documentTypes = stscState.documentTypes();
        for (SchemaType schemaType : documentTypes) {
            resolveSubstitutionGroup((SchemaTypeImpl) schemaType);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(stscState.documentTypes()));
        arrayList.addAll(Arrays.asList(stscState.attributeTypes()));
        arrayList.addAll(Arrays.asList(stscState.redefinedGlobalTypes()));
        arrayList.addAll(Arrays.asList(stscState.globalTypes()));
        for (int i = 0; i < arrayList.size(); i++) {
            SchemaType schemaType2 = (SchemaType) arrayList.get(i);
            resolveType((SchemaTypeImpl) schemaType2);
            arrayList.addAll(Arrays.asList(schemaType2.getAnonymousTypes()));
        }
        resolveIdentityConstraints();
    }

    public static boolean resolveType(SchemaTypeImpl schemaTypeImpl) {
        if (schemaTypeImpl.isResolved()) {
            return true;
        }
        if (schemaTypeImpl.isResolving()) {
            StscState.get().error("Cyclic dependency error", 13, schemaTypeImpl.getParseObject());
            return false;
        }
        schemaTypeImpl.startResolving();
        if (schemaTypeImpl.isDocumentType()) {
            resolveDocumentType(schemaTypeImpl);
        } else if (schemaTypeImpl.isAttributeType()) {
            resolveAttributeType(schemaTypeImpl);
        } else if (schemaTypeImpl.isSimpleType()) {
            StscSimpleTypeResolver.resolveSimpleType(schemaTypeImpl);
        } else {
            StscComplexTypeResolver.resolveComplexType(schemaTypeImpl);
        }
        schemaTypeImpl.finishResolving();
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0095 A[EDGE_INSN: B:23:0x0095->B:21:0x0095 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean resolveSubstitutionGroup(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r8) {
        /*
            boolean r0 = r8.isSGResolved()
            r1 = 1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r0 = r8.isSGResolving()
            if (r0 == 0) goto L_0x001f
            org.apache.xmlbeans.impl.schema.StscState r0 = org.apache.xmlbeans.impl.schema.StscState.get()
            r1 = 13
            org.apache.xmlbeans.XmlObject r8 = r8.getParseObject()
            java.lang.String r2 = "Cyclic dependency error"
            r0.error((java.lang.String) r2, (int) r1, (org.apache.xmlbeans.XmlObject) r8)
            r8 = 0
            return r8
        L_0x001f:
            r8.startResolvingSGs()
            org.apache.xmlbeans.XmlObject r0 = r8.getParseObject()
            org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement r0 = (org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement) r0
            javax.xml.namespace.QName r2 = new javax.xml.namespace.QName
            java.lang.String r3 = r8.getTargetNamespace()
            java.lang.String r4 = r0.getName()
            r2.<init>(r3, r4)
            boolean r3 = r0.isSetSubstitutionGroup()
            r4 = 0
            if (r3 == 0) goto L_0x0071
            org.apache.xmlbeans.impl.schema.StscState r3 = org.apache.xmlbeans.impl.schema.StscState.get()
            javax.xml.namespace.QName r5 = r0.getSubstitutionGroup()
            java.lang.String r6 = r8.getChameleonNamespace()
            java.lang.String r7 = r8.getTargetNamespace()
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = r3.findDocumentType(r5, r6, r7)
            if (r3 != 0) goto L_0x0062
            org.apache.xmlbeans.impl.schema.StscState r5 = org.apache.xmlbeans.impl.schema.StscState.get()
            javax.xml.namespace.QName r6 = r0.getSubstitutionGroup()
            org.apache.xmlbeans.XmlQName r0 = r0.xgetSubstitutionGroup()
            r5.notFoundError(r6, r1, r0, r1)
            goto L_0x0072
        L_0x0062:
            boolean r5 = resolveSubstitutionGroup(r3)
            if (r5 != 0) goto L_0x0069
            goto L_0x0071
        L_0x0069:
            javax.xml.namespace.QName r0 = r0.getSubstitutionGroup()
            r8.setSubstitutionGroup(r0)
            goto L_0x0072
        L_0x0071:
            r3 = r4
        L_0x0072:
            if (r3 == 0) goto L_0x0095
            r3.addSubstitutionGroupMember(r2)
            javax.xml.namespace.QName r0 = r3.getSubstitutionGroup()
            if (r0 != 0) goto L_0x007e
            goto L_0x0095
        L_0x007e:
            org.apache.xmlbeans.impl.schema.StscState r0 = org.apache.xmlbeans.impl.schema.StscState.get()
            javax.xml.namespace.QName r5 = r3.getSubstitutionGroup()
            java.lang.String r3 = r3.getChameleonNamespace()
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = r0.findDocumentType(r5, r3, r4)
            boolean r0 = resolveSubstitutionGroup(r3)
            if (r0 != 0) goto L_0x0072
            goto L_0x0071
        L_0x0095:
            r8.finishResolvingSGs()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscResolver.resolveSubstitutionGroup(org.apache.xmlbeans.impl.schema.SchemaTypeImpl):boolean");
    }

    public static void resolveDocumentType(SchemaTypeImpl schemaTypeImpl) {
        SchemaLocalElementImpl schemaLocalElementImpl;
        SchemaTypeImpl schemaTypeImpl2;
        ArrayList arrayList = new ArrayList();
        SchemaGlobalElementImpl schemaGlobalElementImpl = (SchemaGlobalElementImpl) StscTranslator.translateElement((Element) schemaTypeImpl.getParseObject(), schemaTypeImpl.getTargetNamespace(), schemaTypeImpl.isChameleon(), (String) null, (String) null, arrayList, schemaTypeImpl);
        if (schemaGlobalElementImpl != null) {
            StscState.get().addGlobalElement(schemaGlobalElementImpl);
            SchemaLocalElementImpl schemaLocalElementImpl2 = new SchemaLocalElementImpl();
            schemaLocalElementImpl2.setParticleType(4);
            StscTranslator.copyGlobalElementToLocalElement(schemaGlobalElementImpl, schemaLocalElementImpl2);
            schemaLocalElementImpl2.setMinOccurs(BigInteger.ONE);
            schemaLocalElementImpl2.setMaxOccurs(BigInteger.ONE);
            schemaLocalElementImpl2.setTransitionNotes(QNameSet.EMPTY, true);
            schemaLocalElementImpl = schemaLocalElementImpl2;
        } else {
            schemaLocalElementImpl = null;
        }
        Map<QName, SchemaProperty> buildContentPropertyModelByQName = StscComplexTypeResolver.buildContentPropertyModelByQName(schemaLocalElementImpl, schemaTypeImpl);
        if (schemaTypeImpl.getSubstitutionGroup() == null) {
            schemaTypeImpl2 = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
        } else {
            schemaTypeImpl2 = StscState.get().findDocumentType(schemaTypeImpl.getSubstitutionGroup(), schemaTypeImpl.isChameleon() ? schemaTypeImpl.getTargetNamespace() : null, (String) null);
        }
        schemaTypeImpl.setBaseTypeRef(schemaTypeImpl2.getRef());
        schemaTypeImpl.setBaseDepth(schemaTypeImpl2.getBaseDepth() + 1);
        schemaTypeImpl.setDerivationType(1);
        schemaTypeImpl.setComplexTypeVariety(3);
        schemaTypeImpl.setContentModel(schemaLocalElementImpl, new SchemaAttributeModelImpl(), buildContentPropertyModelByQName, Collections.EMPTY_MAP, false);
        schemaTypeImpl.setWildcardSummary(QNameSet.EMPTY, false, QNameSet.EMPTY, false);
        schemaTypeImpl.setAnonymousTypeRefs(makeRefArray(arrayList));
    }

    public static void resolveAttributeType(SchemaTypeImpl schemaTypeImpl) {
        ArrayList arrayList = new ArrayList();
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl = (SchemaGlobalAttributeImpl) StscTranslator.translateAttribute((Attribute) schemaTypeImpl.getParseObject(), schemaTypeImpl.getTargetNamespace(), (String) null, schemaTypeImpl.isChameleon(), arrayList, schemaTypeImpl, (SchemaAttributeModel) null, false);
        SchemaAttributeModelImpl schemaAttributeModelImpl = new SchemaAttributeModelImpl();
        if (schemaGlobalAttributeImpl != null) {
            StscState.get().addGlobalAttribute(schemaGlobalAttributeImpl);
            SchemaLocalAttributeImpl schemaLocalAttributeImpl = new SchemaLocalAttributeImpl();
            StscTranslator.copyGlobalAttributeToLocalAttribute(schemaGlobalAttributeImpl, schemaLocalAttributeImpl);
            schemaAttributeModelImpl.addAttribute(schemaLocalAttributeImpl);
        }
        schemaTypeImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        schemaTypeImpl.setBaseDepth(schemaTypeImpl.getBaseDepth() + 1);
        schemaTypeImpl.setDerivationType(1);
        schemaTypeImpl.setComplexTypeVariety(1);
        SchemaTypeImpl schemaTypeImpl2 = schemaTypeImpl;
        schemaTypeImpl2.setContentModel((SchemaParticle) null, schemaAttributeModelImpl, Collections.EMPTY_MAP, StscComplexTypeResolver.buildAttributePropertyModelByQName(schemaAttributeModelImpl, schemaTypeImpl), false);
        schemaTypeImpl.setWildcardSummary(QNameSet.EMPTY, false, QNameSet.EMPTY, false);
        schemaTypeImpl.setAnonymousTypeRefs(makeRefArray(arrayList));
    }

    static /* synthetic */ SchemaType.Ref[] lambda$makeRefArray$0(int i) {
        return new SchemaType.Ref[i];
    }

    private static SchemaType.Ref[] makeRefArray(Collection<SchemaType> collection) {
        return (SchemaType.Ref[]) collection.stream().map(new StscComplexTypeResolver$$ExternalSyntheticLambda7()).toArray(new StscResolver$$ExternalSyntheticLambda0());
    }

    public static void resolveIdentityConstraints() {
        StscState stscState = StscState.get();
        for (SchemaIdentityConstraintImpl schemaIdentityConstraintImpl : stscState.idConstraints()) {
            if (!schemaIdentityConstraintImpl.isResolved()) {
                KeyrefDocument.Keyref keyref = (KeyrefDocument.Keyref) schemaIdentityConstraintImpl.getParseObject();
                QName refer = keyref.getRefer();
                SchemaIdentityConstraintImpl findIdConstraint = stscState.findIdConstraint(refer, schemaIdentityConstraintImpl.getChameleonNamespace(), schemaIdentityConstraintImpl.getTargetNamespace());
                if (findIdConstraint == null) {
                    stscState.notFoundError(refer, 5, keyref, true);
                } else {
                    if (findIdConstraint.getConstraintCategory() == 2) {
                        stscState.error(XmlErrorCodes.IDENTITY_CONSTRAINT_PROPERTIES$KEYREF_REFERS_TO_KEYREF, (Object[]) null, schemaIdentityConstraintImpl.getParseObject());
                    }
                    if (findIdConstraint.getFields().length != schemaIdentityConstraintImpl.getFields().length) {
                        stscState.error(XmlErrorCodes.IDENTITY_CONSTRAINT_PROPERTIES$KEY_KEYREF_FIELD_COUNT_EQ, (Object[]) null, schemaIdentityConstraintImpl.getParseObject());
                    }
                    schemaIdentityConstraintImpl.setReferencedKey(findIdConstraint.getRef());
                }
            }
        }
    }
}
