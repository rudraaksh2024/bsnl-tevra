package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XBeanDebug;

public class StscChecker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void checkAll() {
        StscState stscState = StscState.get();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(stscState.documentTypes()));
        arrayList.addAll(Arrays.asList(stscState.attributeTypes()));
        arrayList.addAll(Arrays.asList(stscState.redefinedGlobalTypes()));
        arrayList.addAll(Arrays.asList(stscState.globalTypes()));
        for (int i = 0; i < arrayList.size(); i++) {
            SchemaType schemaType = (SchemaType) arrayList.get(i);
            if (!stscState.noPvr() && !schemaType.isDocumentType()) {
                checkRestriction((SchemaTypeImpl) schemaType);
            }
            checkFields((SchemaTypeImpl) schemaType);
            arrayList.addAll(Arrays.asList(schemaType.getAnonymousTypes()));
        }
        checkSubstitutionGroups(stscState.globalElements());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ed, code lost:
        if (r8.getName().getNamespaceURI().length() > 0) goto L_0x00ef;
     */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void checkFields(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r18) {
        /*
            r0 = r18
            boolean r1 = r18.isSimpleType()
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            org.apache.xmlbeans.XmlObject r1 = r18.getParseObject()
            org.apache.xmlbeans.SchemaAttributeModel r2 = r18.getAttributeModel()
            if (r2 == 0) goto L_0x01bd
            org.apache.xmlbeans.SchemaLocalAttribute[] r2 = r2.getAttributes()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r7 = r4
            r6 = r5
        L_0x001c:
            if (r6 >= r3) goto L_0x01bd
            r8 = r2[r6]
            r9 = r8
            org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl r9 = (org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl) r9
            org.apache.xmlbeans.XmlObject r9 = r9._parseObject
            org.apache.xmlbeans.SchemaType r10 = org.apache.xmlbeans.XmlID.type
            org.apache.xmlbeans.SchemaType r11 = r8.getType()
            boolean r10 = r10.isAssignableFrom(r11)
            r11 = 2
            r12 = 1
            if (r10 == 0) goto L_0x006c
            if (r7 != 0) goto L_0x003a
            javax.xml.namespace.QName r7 = r8.getName()
            goto L_0x0056
        L_0x003a:
            org.apache.xmlbeans.impl.schema.StscState r10 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.Object[] r11 = new java.lang.Object[r11]
            java.lang.String r13 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r7)
            r11[r5] = r13
            javax.xml.namespace.QName r13 = r8.getName()
            r11[r12] = r13
            if (r9 == 0) goto L_0x0050
            r12 = r9
            goto L_0x0051
        L_0x0050:
            r12 = r1
        L_0x0051:
            java.lang.String r13 = "ag-props-correct.3"
            r10.error((java.lang.String) r13, (java.lang.Object[]) r11, (org.apache.xmlbeans.XmlObject) r12)
        L_0x0056:
            java.lang.String r8 = r8.getDefaultText()
            if (r8 == 0) goto L_0x0069
            org.apache.xmlbeans.impl.schema.StscState r8 = org.apache.xmlbeans.impl.schema.StscState.get()
            if (r9 == 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r9 = r1
        L_0x0064:
            java.lang.String r10 = "a-props-correct.3"
            r8.error((java.lang.String) r10, (java.lang.Object[]) r4, (org.apache.xmlbeans.XmlObject) r9)
        L_0x0069:
            r15 = r5
            goto L_0x01b7
        L_0x006c:
            org.apache.xmlbeans.SchemaType r10 = org.apache.xmlbeans.XmlNOTATION.type
            org.apache.xmlbeans.SchemaType r13 = r8.getType()
            boolean r10 = r10.isAssignableFrom(r13)
            if (r10 == 0) goto L_0x0141
            org.apache.xmlbeans.SchemaType r10 = r8.getType()
            int r10 = r10.getBuiltinTypeCode()
            java.lang.String r13 = "enumeration-required-notation-attr"
            r14 = 8
            if (r10 != r14) goto L_0x009e
            org.apache.xmlbeans.impl.schema.StscState r10 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.Object[] r11 = new java.lang.Object[r12]
            javax.xml.namespace.QName r8 = r8.getName()
            java.lang.String r8 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r8)
            r11[r5] = r8
            if (r9 == 0) goto L_0x0099
            goto L_0x009a
        L_0x0099:
            r9 = r1
        L_0x009a:
            r10.recover(r13, r11, r9)
            goto L_0x0069
        L_0x009e:
            org.apache.xmlbeans.SchemaType r10 = r8.getType()
            int r10 = r10.getSimpleVariety()
            if (r10 != r11) goto L_0x00db
            org.apache.xmlbeans.SchemaType r10 = r8.getType()
            org.apache.xmlbeans.SchemaType[] r10 = r10.getUnionConstituentTypes()
            int r11 = r10.length
            r15 = r5
        L_0x00b2:
            if (r15 >= r11) goto L_0x00db
            r16 = r10[r15]
            int r4 = r16.getBuiltinTypeCode()
            if (r4 != r14) goto L_0x00d4
            org.apache.xmlbeans.impl.schema.StscState r4 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.Object[] r14 = new java.lang.Object[r12]
            javax.xml.namespace.QName r17 = r8.getName()
            java.lang.String r17 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r17)
            r14[r5] = r17
            if (r9 == 0) goto L_0x00d0
            r5 = r9
            goto L_0x00d1
        L_0x00d0:
            r5 = r1
        L_0x00d1:
            r4.recover(r13, r14, r5)
        L_0x00d4:
            int r15 = r15 + 1
            r4 = 0
            r5 = 0
            r14 = 8
            goto L_0x00b2
        L_0x00db:
            boolean r4 = r18.isAttributeType()
            if (r4 == 0) goto L_0x00f3
            javax.xml.namespace.QName r4 = r8.getName()
            java.lang.String r4 = r4.getNamespaceURI()
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x00f1
        L_0x00ef:
            r4 = r12
            goto L_0x0123
        L_0x00f1:
            r4 = 0
            goto L_0x0123
        L_0x00f3:
            r4 = r0
        L_0x00f4:
            org.apache.xmlbeans.SchemaType r5 = r4.getOuterType()
            if (r5 == 0) goto L_0x00ff
            org.apache.xmlbeans.SchemaType r4 = r4.getOuterType()
            goto L_0x00f4
        L_0x00ff:
            boolean r5 = r4.isDocumentType()
            if (r5 == 0) goto L_0x0114
            javax.xml.namespace.QName r4 = r4.getDocumentElementName()
            java.lang.String r4 = r4.getNamespaceURI()
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x00f1
            goto L_0x00ef
        L_0x0114:
            javax.xml.namespace.QName r4 = r4.getName()
            java.lang.String r4 = r4.getNamespaceURI()
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x00f1
            goto L_0x00ef
        L_0x0123:
            if (r4 == 0) goto L_0x01b6
            org.apache.xmlbeans.impl.schema.StscState r4 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.Object[] r5 = new java.lang.Object[r12]
            javax.xml.namespace.QName r8 = r8.getName()
            java.lang.String r8 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r8)
            r10 = 0
            r5[r10] = r8
            if (r9 == 0) goto L_0x0139
            goto L_0x013a
        L_0x0139:
            r9 = r1
        L_0x013a:
            java.lang.String r8 = "notation-targetns-attr"
            r4.warning((java.lang.String) r8, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r9)
            goto L_0x01b6
        L_0x0141:
            java.lang.String r4 = r8.getDefaultText()
            if (r4 == 0) goto L_0x01b6
            org.apache.xmlbeans.XmlAnySimpleType r5 = r8.getDefaultValue()     // Catch:{ Exception -> 0x0172 }
            boolean r10 = r5.validate()     // Catch:{ Exception -> 0x0172 }
            if (r10 == 0) goto L_0x016c
            javax.xml.namespace.QName r10 = r8.getName()     // Catch:{ Exception -> 0x0172 }
            org.apache.xmlbeans.SchemaProperty r10 = r0.getAttributeProperty(r10)     // Catch:{ Exception -> 0x0172 }
            org.apache.xmlbeans.impl.schema.SchemaPropertyImpl r10 = (org.apache.xmlbeans.impl.schema.SchemaPropertyImpl) r10     // Catch:{ Exception -> 0x0172 }
            if (r10 == 0) goto L_0x01b6
            java.lang.String r13 = r10.getDefaultText()     // Catch:{ Exception -> 0x0172 }
            if (r13 == 0) goto L_0x01b6
            org.apache.xmlbeans.impl.schema.XmlValueRef r13 = new org.apache.xmlbeans.impl.schema.XmlValueRef     // Catch:{ Exception -> 0x0172 }
            r13.<init>(r5)     // Catch:{ Exception -> 0x0172 }
            r10.setDefaultValue(r13)     // Catch:{ Exception -> 0x0172 }
            goto L_0x01b6
        L_0x016c:
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Exception -> 0x0172 }
            r5.<init>()     // Catch:{ Exception -> 0x0172 }
            throw r5     // Catch:{ Exception -> 0x0172 }
        L_0x0172:
            boolean r5 = r8.isFixed()
            if (r5 == 0) goto L_0x017b
            java.lang.String r5 = "fixed"
            goto L_0x017d
        L_0x017b:
            java.lang.String r5 = "default"
        L_0x017d:
            if (r9 == 0) goto L_0x018a
            java.lang.String r10 = ""
            org.apache.xmlbeans.XmlObject r10 = r9.selectAttribute(r10, r5)
            if (r10 != 0) goto L_0x0188
            goto L_0x018b
        L_0x0188:
            r9 = r10
            goto L_0x018b
        L_0x018a:
            r9 = r1
        L_0x018b:
            org.apache.xmlbeans.impl.schema.StscState r10 = org.apache.xmlbeans.impl.schema.StscState.get()
            r13 = 4
            java.lang.Object[] r13 = new java.lang.Object[r13]
            javax.xml.namespace.QName r14 = r8.getName()
            java.lang.String r14 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r14)
            r15 = 0
            r13[r15] = r14
            r13[r12] = r5
            r13[r11] = r4
            org.apache.xmlbeans.SchemaType r4 = r8.getType()
            javax.xml.namespace.QName r4 = r4.getName()
            java.lang.String r4 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r4)
            r5 = 3
            r13[r5] = r4
            java.lang.String r4 = "a-props-correct.2"
            r10.error((java.lang.String) r4, (java.lang.Object[]) r13, (org.apache.xmlbeans.XmlObject) r9)
            goto L_0x01b7
        L_0x01b6:
            r15 = 0
        L_0x01b7:
            int r6 = r6 + 1
            r5 = r15
            r4 = 0
            goto L_0x001c
        L_0x01bd:
            org.apache.xmlbeans.SchemaParticle r2 = r18.getContentModel()
            checkElementDefaults(r2, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.checkFields(org.apache.xmlbeans.impl.schema.SchemaTypeImpl):void");
    }

    private static void checkElementDefaults(SchemaParticle schemaParticle, XmlObject xmlObject, SchemaType schemaType) {
        String str;
        String str2;
        XmlObject xmlObject2;
        XmlObject xmlObject3;
        XmlObject xmlObject4 = xmlObject;
        SchemaType schemaType2 = schemaType;
        if (schemaParticle != null) {
            int particleType = schemaParticle.getParticleType();
            if (particleType == 1 || particleType == 2 || particleType == 3) {
                for (SchemaParticle checkElementDefaults : schemaParticle.getParticleChildren()) {
                    checkElementDefaults(checkElementDefaults, xmlObject4, schemaType2);
                }
            } else if (particleType == 4) {
                String defaultText = schemaParticle.getDefaultText();
                if (defaultText != null) {
                    String str3 = "fixed";
                    if (schemaParticle.getType().isSimpleType() || schemaParticle.getType().getContentType() == 2) {
                        try {
                            XmlAnySimpleType defaultValue = schemaParticle.getDefaultValue();
                            XmlOptions xmlOptions = new XmlOptions();
                            xmlOptions.setValidateTextOnly();
                            if (defaultValue.validate(xmlOptions)) {
                                SchemaPropertyImpl schemaPropertyImpl = (SchemaPropertyImpl) schemaType2.getElementProperty(schemaParticle.getName());
                                if (!(schemaPropertyImpl == null || schemaPropertyImpl.getDefaultText() == null)) {
                                    schemaPropertyImpl.setDefaultValue(new XmlValueRef(defaultValue));
                                }
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception unused) {
                            if (!schemaParticle.isFixed()) {
                                str3 = "default";
                            }
                            XmlObject selectAttribute = xmlObject4.selectAttribute("", str3);
                            StscState stscState = StscState.get();
                            Object[] objArr = {QNameHelper.pretty(schemaParticle.getName()), str3, defaultText, QNameHelper.pretty(schemaParticle.getType().getName())};
                            if (selectAttribute == null) {
                                selectAttribute = xmlObject4;
                            }
                            stscState.error(XmlErrorCodes.ELEM_PROPERTIES$CONSTRAINT_VALID, objArr, selectAttribute);
                        }
                    } else if (schemaParticle.getType().getContentType() == 4) {
                        if (!schemaParticle.getType().getContentModel().isSkippable()) {
                            if (!schemaParticle.isFixed()) {
                                str3 = "default";
                            }
                            XmlObject selectAttribute2 = xmlObject4.selectAttribute("", str3);
                            StscState stscState2 = StscState.get();
                            Object[] objArr2 = {QNameHelper.pretty(schemaParticle.getName()), str3, defaultText};
                            if (selectAttribute2 == null) {
                                selectAttribute2 = xmlObject4;
                            }
                            stscState2.error(XmlErrorCodes.ELEM_DEFAULT_VALID$MIXED_AND_EMPTIABLE, objArr2, selectAttribute2);
                        } else {
                            SchemaPropertyImpl schemaPropertyImpl2 = (SchemaPropertyImpl) schemaType2.getElementProperty(schemaParticle.getName());
                            if (!(schemaPropertyImpl2 == null || schemaPropertyImpl2.getDefaultText() == null)) {
                                schemaPropertyImpl2.setDefaultValue(new XmlValueRef(XmlString.type.newValue(defaultText)));
                            }
                        }
                    } else if (schemaParticle.getType().getContentType() == 3) {
                        XmlObject selectAttribute3 = xmlObject4.selectAttribute("", "default");
                        StscState stscState3 = StscState.get();
                        Object[] objArr3 = {QNameHelper.pretty(schemaParticle.getName()), defaultText, "element"};
                        if (selectAttribute3 == null) {
                            selectAttribute3 = xmlObject4;
                        }
                        stscState3.error(XmlErrorCodes.ELEM_DEFAULT_VALID$SIMPLE_TYPE_OR_MIXED, objArr3, selectAttribute3);
                    } else if (schemaParticle.getType().getContentType() == 1) {
                        XmlObject selectAttribute4 = xmlObject4.selectAttribute("", "default");
                        StscState stscState4 = StscState.get();
                        Object[] objArr4 = {QNameHelper.pretty(schemaParticle.getName()), defaultText, "empty"};
                        if (selectAttribute4 == null) {
                            selectAttribute4 = xmlObject4;
                        }
                        stscState4.error(XmlErrorCodes.ELEM_DEFAULT_VALID$SIMPLE_TYPE_OR_MIXED, objArr4, selectAttribute4);
                    }
                }
                if (BuiltinSchemaTypeSystem.ST_ID.isAssignableFrom(schemaParticle.getType())) {
                    str = BuiltinSchemaTypeSystem.ST_ID.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREF.isAssignableFrom(schemaParticle.getType())) {
                    str = BuiltinSchemaTypeSystem.ST_IDREF.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREFS.isAssignableFrom(schemaParticle.getType())) {
                    str = BuiltinSchemaTypeSystem.ST_IDREFS.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITY.isAssignableFrom(schemaParticle.getType())) {
                    str = BuiltinSchemaTypeSystem.ST_ENTITY.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITIES.isAssignableFrom(schemaParticle.getType())) {
                    str = BuiltinSchemaTypeSystem.ST_ENTITIES.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_NOTATION.isAssignableFrom(schemaParticle.getType())) {
                    if (schemaParticle.getType().getBuiltinTypeCode() == 8) {
                        StscState stscState5 = StscState.get();
                        Object[] objArr5 = {QNameHelper.pretty(schemaParticle.getName())};
                        SchemaLocalElementImpl schemaLocalElementImpl = (SchemaLocalElementImpl) schemaParticle;
                        if (schemaLocalElementImpl._parseObject == null) {
                            xmlObject3 = xmlObject4;
                        } else {
                            xmlObject3 = schemaLocalElementImpl._parseObject.selectAttribute("", "type");
                        }
                        stscState5.recover(XmlErrorCodes.ELEM_NOTATION_TYPE_FORBIDDEN, objArr5, xmlObject3);
                        str2 = null;
                    } else {
                        if (schemaParticle.getType().getSimpleVariety() == 2) {
                            for (SchemaType builtinTypeCode : schemaParticle.getType().getUnionConstituentTypes()) {
                                if (builtinTypeCode.getBuiltinTypeCode() == 8) {
                                    StscState stscState6 = StscState.get();
                                    Object[] objArr6 = {QNameHelper.pretty(schemaParticle.getName())};
                                    SchemaLocalElementImpl schemaLocalElementImpl2 = (SchemaLocalElementImpl) schemaParticle;
                                    if (schemaLocalElementImpl2._parseObject == null) {
                                        xmlObject2 = xmlObject4;
                                    } else {
                                        xmlObject2 = schemaLocalElementImpl2._parseObject.selectAttribute("", "type");
                                    }
                                    stscState6.recover(XmlErrorCodes.ELEM_NOTATION_TYPE_FORBIDDEN, objArr6, xmlObject2);
                                }
                            }
                        }
                        str2 = BuiltinSchemaTypeSystem.ST_NOTATION.getName().getLocalPart();
                    }
                    while (schemaType2.getOuterType() != null) {
                        schemaType2 = schemaType2.getOuterType();
                    }
                    if (!schemaType2.isDocumentType() ? schemaType2.getName().getNamespaceURI().length() > 0 : schemaType2.getDocumentElementName().getNamespaceURI().length() > 0) {
                        SchemaLocalElementImpl schemaLocalElementImpl3 = (SchemaLocalElementImpl) schemaParticle;
                        StscState.get().warning(XmlErrorCodes.ELEM_COMPATIBILITY_TARGETNS, new Object[]{QNameHelper.pretty(schemaParticle.getName())}, schemaLocalElementImpl3._parseObject == null ? xmlObject4 : schemaLocalElementImpl3._parseObject);
                    }
                    str = str2;
                } else {
                    str = null;
                }
                if (str != null) {
                    StscState stscState7 = StscState.get();
                    Object[] objArr7 = {QNameHelper.pretty(schemaParticle.getName()), str};
                    SchemaLocalElementImpl schemaLocalElementImpl4 = (SchemaLocalElementImpl) schemaParticle;
                    if (schemaLocalElementImpl4._parseObject != null) {
                        xmlObject4 = schemaLocalElementImpl4._parseObject.selectAttribute("", "type");
                    }
                    stscState7.warning(XmlErrorCodes.ELEM_COMPATIBILITY_TYPE, objArr7, xmlObject4);
                }
            }
        }
    }

    public static boolean checkRestriction(SchemaTypeImpl schemaTypeImpl) {
        if (schemaTypeImpl.getDerivationType() == 1 && !schemaTypeImpl.isSimpleType()) {
            StscState stscState = StscState.get();
            XmlObject parseObject = schemaTypeImpl.getParseObject();
            SchemaType baseType = schemaTypeImpl.getBaseType();
            if (baseType.isSimpleType()) {
                stscState.error(XmlErrorCodes.SCHEMA_COMPLEX_TYPE$COMPLEX_CONTENT, new Object[]{QNameHelper.pretty(baseType.getName())}, parseObject);
                return false;
            }
            int contentType = schemaTypeImpl.getContentType();
            if (contentType == 1) {
                int contentType2 = baseType.getContentType();
                if (contentType2 != 1) {
                    if (contentType2 != 3 && contentType2 != 4) {
                        stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$EMPTY_AND_NOT_SIMPLE, (Object[]) null, parseObject);
                        return false;
                    } else if (baseType.getContentModel() != null && !baseType.getContentModel().isSkippable()) {
                        stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$EMPTY_AND_ELEMENT_OR_MIXED_EMPTIABLE, (Object[]) null, parseObject);
                        return false;
                    }
                }
            } else if (contentType != 2) {
                if (contentType != 3) {
                    if (contentType == 4) {
                        if (baseType.getContentType() != 4) {
                            stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_MIXED, (Object[]) null, parseObject);
                            return false;
                        }
                    }
                }
                if (baseType.getContentType() == 1) {
                    stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_EMPTY, (Object[]) null, parseObject);
                    return false;
                } else if (baseType.getContentType() == 2) {
                    stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_SIMPLE, (Object[]) null, parseObject);
                    return false;
                } else {
                    SchemaParticle contentModel = baseType.getContentModel();
                    SchemaParticle contentModel2 = schemaTypeImpl.getContentModel();
                    if (contentModel2 == null && schemaTypeImpl.getDerivationType() == 1) {
                        return true;
                    }
                    if (contentModel == null || contentModel2 == null) {
                        XBeanDebug.LOG.atTrace().withThrowable(new Exception("Stacktrace")).log("Null models that weren't caught by EMPTY_CONTENT: {} ({}), {} ({})", baseType, contentModel, schemaTypeImpl, contentModel2);
                        stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_VALID, (Object[]) null, parseObject);
                        return false;
                    }
                    ArrayList arrayList = new ArrayList();
                    if (!isParticleValidRestriction(contentModel, contentModel2, arrayList, parseObject)) {
                        if (arrayList.size() == 0) {
                            stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_VALID, (Object[]) null, parseObject);
                        } else {
                            stscState.getErrorListener().add(arrayList.get(arrayList.size() - 1));
                        }
                        return false;
                    }
                }
            } else {
                int contentType3 = baseType.getContentType();
                if (contentType3 == 2) {
                    SchemaType contentBasedOnType = schemaTypeImpl.getContentBasedOnType();
                    if (contentBasedOnType != baseType) {
                        while (baseType != null && !baseType.isSimpleType()) {
                            baseType = baseType.getContentBasedOnType();
                        }
                        if (baseType != null && !baseType.isAssignableFrom(contentBasedOnType)) {
                            stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_NOT_DERIVED, (Object[]) null, parseObject);
                            return false;
                        }
                    }
                } else if (contentType3 != 4) {
                    stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_AND_SIMPLE_TYPE_OR_MIXED, (Object[]) null, parseObject);
                    return false;
                } else if (baseType.getContentModel() != null && !baseType.getContentModel().isSkippable()) {
                    stscState.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_AND_MIXED_EMPTIABLE, (Object[]) null, parseObject);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isParticleValidRestriction(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (schemaParticle.equals(schemaParticle2)) {
            return true;
        }
        int particleType = schemaParticle.getParticleType();
        if (particleType == 1) {
            int particleType2 = schemaParticle2.getParticleType();
            if (particleType2 == 1) {
                return recurse(schemaParticle, schemaParticle2, collection, xmlObject);
            }
            if (particleType2 != 2) {
                if (particleType2 == 3) {
                    return recurseUnordered(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType2 == 4) {
                    return recurseAsIfGroup(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType2 != 5) {
                    XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                }
            }
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle)}, xmlObject));
        } else if (particleType == 2) {
            int particleType3 = schemaParticle2.getParticleType();
            if (particleType3 != 1) {
                if (particleType3 == 2) {
                    return recurseLax(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType3 == 3) {
                    return mapAndSum(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType3 == 4) {
                    return recurseAsIfGroup(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType3 != 5) {
                    XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                }
            }
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle)}, xmlObject));
        } else if (particleType == 3) {
            int particleType4 = schemaParticle2.getParticleType();
            if (!(particleType4 == 1 || particleType4 == 2)) {
                if (particleType4 == 3) {
                    return recurse(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType4 == 4) {
                    return recurseAsIfGroup(schemaParticle, schemaParticle2, collection, xmlObject);
                }
                if (particleType4 != 5) {
                    XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                }
            }
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle)}, xmlObject));
        } else if (particleType == 4) {
            int particleType5 = schemaParticle2.getParticleType();
            if (!(particleType5 == 1 || particleType5 == 2 || particleType5 == 3)) {
                if (particleType5 == 4) {
                    return nameAndTypeOK((SchemaLocalElement) schemaParticle, (SchemaLocalElement) schemaParticle2, collection, xmlObject);
                }
                if (particleType5 != 5) {
                    XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                }
            }
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle)}, xmlObject));
        } else if (particleType != 5) {
            XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Base Type");
        } else {
            int particleType6 = schemaParticle2.getParticleType();
            if (particleType6 == 1 || particleType6 == 2 || particleType6 == 3) {
                return nsRecurseCheckCardinality(schemaParticle, schemaParticle2, collection, xmlObject);
            }
            if (particleType6 == 4) {
                return nsCompat(schemaParticle, (SchemaLocalElement) schemaParticle2, collection, xmlObject);
            }
            if (particleType6 == 5) {
                return nsSubset(schemaParticle, schemaParticle2, collection, xmlObject);
            }
            XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
        }
        return false;
    }

    private static boolean mapAndSum(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        BigInteger bigInteger;
        String str;
        boolean z;
        SchemaParticle[] particleChildren = schemaParticle2.getParticleChildren();
        SchemaParticle[] particleChildren2 = schemaParticle.getParticleChildren();
        for (SchemaParticle schemaParticle3 : particleChildren) {
            int length = particleChildren2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                } else if (isParticleValidRestriction(particleChildren2[i], schemaParticle3, collection, xmlObject)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$MAP, new Object[]{printParticle(schemaParticle3)}, xmlObject));
                return false;
            }
        }
        BigInteger multiply = schemaParticle2.getMinOccurs().multiply(BigInteger.valueOf((long) schemaParticle2.getParticleChildren().length));
        if (schemaParticle2.getMaxOccurs() == null) {
            bigInteger = null;
        } else {
            bigInteger = schemaParticle2.getMaxOccurs().multiply(BigInteger.valueOf((long) schemaParticle2.getParticleChildren().length));
        }
        if (multiply.compareTo(schemaParticle.getMinOccurs()) < 0) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$SUM_MIN_OCCURS_GTE_MIN_OCCURS, new Object[]{multiply.toString(), schemaParticle.getMinOccurs().toString()}, xmlObject));
            return false;
        } else if (schemaParticle.getMaxOccurs() == null || (bigInteger != null && bigInteger.compareTo(schemaParticle.getMaxOccurs()) <= 0)) {
            return true;
        } else {
            Object[] objArr = new Object[2];
            if (bigInteger == null) {
                str = "unbounded";
            } else {
                str = bigInteger.toString();
            }
            objArr[0] = str;
            objArr[1] = schemaParticle.getMaxOccurs().toString();
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$SUM_MAX_OCCURS_LTE_MAX_OCCURS, objArr, xmlObject));
            return false;
        }
    }

    private static boolean recurseAsIfGroup(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        SchemaParticleImpl schemaParticleImpl = new SchemaParticleImpl();
        schemaParticleImpl.setParticleType(schemaParticle.getParticleType());
        schemaParticleImpl.setMinOccurs(BigInteger.ONE);
        schemaParticleImpl.setMaxOccurs(BigInteger.ONE);
        schemaParticleImpl.setParticleChildren(new SchemaParticle[]{schemaParticle2});
        return isParticleValidRestriction(schemaParticle, schemaParticleImpl, collection, xmlObject);
    }

    private static boolean recurseLax(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (!occurrenceRangeOK(schemaParticle, schemaParticle2, collection, xmlObject)) {
            return false;
        }
        SchemaParticle[] particleChildren = schemaParticle2.getParticleChildren();
        SchemaParticle[] particleChildren2 = schemaParticle.getParticleChildren();
        int i = 0;
        int i2 = 0;
        while (i < particleChildren.length && i2 < particleChildren2.length) {
            if (isParticleValidRestriction(particleChildren2[i2], particleChildren[i], collection, xmlObject)) {
                i++;
            }
            i2++;
        }
        if (i >= particleChildren.length) {
            return true;
        }
        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE_LAX$MAP, new Object[]{printParticles(particleChildren2, i)}, xmlObject));
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004e, code lost:
        r9 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean recurseUnordered(org.apache.xmlbeans.SchemaParticle r9, org.apache.xmlbeans.SchemaParticle r10, java.util.Collection<org.apache.xmlbeans.XmlError> r11, org.apache.xmlbeans.XmlObject r12) {
        /*
            boolean r0 = occurrenceRangeOK(r9, r10, r11, r12)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            org.apache.xmlbeans.SchemaParticle[] r9 = r9.getParticleChildren()
            java.util.HashMap r0 = new java.util.HashMap
            r2 = 10
            r0.<init>(r2)
            java.lang.Object r2 = new java.lang.Object
            r2.<init>()
            int r3 = r9.length
            r4 = r1
        L_0x001a:
            if (r4 >= r3) goto L_0x0028
            r5 = r9[r4]
            javax.xml.namespace.QName r6 = r5.getName()
            r0.put(r6, r5)
            int r4 = r4 + 1
            goto L_0x001a
        L_0x0028:
            org.apache.xmlbeans.SchemaParticle[] r9 = r10.getParticleChildren()
            int r10 = r9.length
            r3 = r1
        L_0x002e:
            r4 = 1
            if (r3 >= r10) goto L_0x00a7
            r5 = r9[r3]
            javax.xml.namespace.QName r6 = r5.getName()
            java.lang.Object r6 = r0.get(r6)
            if (r6 != 0) goto L_0x0050
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r10 = printParticle(r5)
            r9[r1] = r10
            java.lang.String r10 = "rcase-RecurseUnordered.2"
            org.apache.xmlbeans.XmlError r9 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r10, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r12)
            r11.add(r9)
        L_0x004e:
            r9 = r1
            goto L_0x00a8
        L_0x0050:
            if (r6 != r2) goto L_0x0064
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r10 = printParticle(r5)
            r9[r1] = r10
            java.lang.String r10 = "rcase-RecurseUnordered.2.1"
            org.apache.xmlbeans.XmlError r9 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r10, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r12)
            r11.add(r9)
            goto L_0x004e
        L_0x0064:
            org.apache.xmlbeans.SchemaParticle r6 = (org.apache.xmlbeans.SchemaParticle) r6
            java.math.BigInteger r7 = r5.getMaxOccurs()
            if (r7 == 0) goto L_0x008a
            java.math.BigInteger r7 = r5.getMaxOccurs()
            java.math.BigInteger r8 = java.math.BigInteger.ONE
            int r7 = r7.compareTo(r8)
            if (r7 <= 0) goto L_0x0079
            goto L_0x008a
        L_0x0079:
            boolean r6 = isParticleValidRestriction(r6, r5, r11, r12)
            if (r6 != 0) goto L_0x0080
            goto L_0x004e
        L_0x0080:
            javax.xml.namespace.QName r4 = r5.getName()
            r0.put(r4, r2)
            int r3 = r3 + 1
            goto L_0x002e
        L_0x008a:
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.String r10 = printParticle(r5)
            r9[r1] = r10
            java.math.BigInteger r10 = r5.getMinOccurs()
            java.lang.String r10 = printMaxOccurs(r10)
            r9[r4] = r10
            java.lang.String r10 = "rcase-RecurseUnordered.2.2a"
            org.apache.xmlbeans.XmlError r9 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r10, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r12)
            r11.add(r9)
            goto L_0x004e
        L_0x00a7:
            r9 = r4
        L_0x00a8:
            if (r9 == 0) goto L_0x00e9
            java.util.Set r10 = r0.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x00b2:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto L_0x00e9
            java.lang.Object r3 = r10.next()
            javax.xml.namespace.QName r3 = (javax.xml.namespace.QName) r3
            java.lang.Object r5 = r0.get(r3)
            if (r5 == r2) goto L_0x00b2
            java.lang.Object r5 = r0.get(r3)
            org.apache.xmlbeans.SchemaParticle r5 = (org.apache.xmlbeans.SchemaParticle) r5
            boolean r5 = r5.isSkippable()
            if (r5 != 0) goto L_0x00b2
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.Object r3 = r0.get(r3)
            org.apache.xmlbeans.SchemaParticle r3 = (org.apache.xmlbeans.SchemaParticle) r3
            java.lang.String r3 = printParticle(r3)
            r9[r1] = r3
            java.lang.String r3 = "rcase-RecurseUnordered.2.3"
            org.apache.xmlbeans.XmlError r9 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r3, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r12)
            r11.add(r9)
            r9 = r1
            goto L_0x00b2
        L_0x00e9:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.recurseUnordered(org.apache.xmlbeans.SchemaParticle, org.apache.xmlbeans.SchemaParticle, java.util.Collection, org.apache.xmlbeans.XmlObject):boolean");
    }

    private static boolean recurse(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        boolean z;
        if (!occurrenceRangeOK(schemaParticle, schemaParticle2, collection, xmlObject)) {
            return false;
        }
        SchemaParticle[] particleChildren = schemaParticle2.getParticleChildren();
        SchemaParticle[] particleChildren2 = schemaParticle.getParticleChildren();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= particleChildren.length || i2 >= particleChildren2.length) {
                z = true;
            } else {
                SchemaParticle schemaParticle3 = particleChildren[i];
                SchemaParticle schemaParticle4 = particleChildren2[i2];
                if (!isParticleValidRestriction(schemaParticle4, schemaParticle3, collection, xmlObject)) {
                    if (!schemaParticle4.isSkippable()) {
                        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$MAP_VALID, new Object[]{printParticle(schemaParticle3), printParticle(schemaParticle2), printParticle(schemaParticle4), printParticle(schemaParticle)}, xmlObject));
                        z = false;
                        break;
                    }
                } else {
                    i++;
                }
                i2++;
            }
        }
        if (i < particleChildren.length) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$MAP, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle), printParticles(particleChildren, i)}, xmlObject));
            return false;
        }
        if (i2 < particleChildren2.length) {
            ArrayList arrayList = new ArrayList(particleChildren2.length);
            while (i2 < particleChildren2.length) {
                if (!particleChildren2[i2].isSkippable()) {
                    arrayList.add(particleChildren2[i2]);
                }
                i2++;
            }
            if (arrayList.size() > 0) {
                collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$UNMAPPED_ARE_EMPTIABLE, new Object[]{printParticle(schemaParticle), printParticle(schemaParticle2), printParticles((List<SchemaParticle>) arrayList)}, xmlObject));
                return false;
            }
        }
        return z;
    }

    private static boolean nsRecurseCheckCardinality(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        SchemaParticleImpl schemaParticleImpl = new SchemaParticleImpl();
        schemaParticleImpl.setParticleType(schemaParticle.getParticleType());
        schemaParticleImpl.setWildcardProcess(schemaParticle.getWildcardProcess());
        schemaParticleImpl.setWildcardSet(schemaParticle.getWildcardSet());
        schemaParticleImpl.setMinOccurs(BigInteger.ZERO);
        schemaParticleImpl.setMaxOccurs((BigInteger) null);
        schemaParticleImpl.setTransitionRules(schemaParticle.getWildcardSet(), true);
        schemaParticleImpl.setTransitionNotes(schemaParticle.getWildcardSet(), true);
        boolean z = true;
        for (SchemaParticle schemaParticle3 : schemaParticle2.getParticleChildren()) {
            int particleType = schemaParticle3.getParticleType();
            if (particleType == 1 || particleType == 2 || particleType == 3) {
                z = nsRecurseCheckCardinality(schemaParticleImpl, schemaParticle3, collection, xmlObject);
            } else if (particleType == 4) {
                z = nsCompat(schemaParticleImpl, (SchemaLocalElement) schemaParticle3, collection, xmlObject);
            } else if (particleType == 5) {
                z = nsSubset(schemaParticleImpl, schemaParticle3, collection, xmlObject);
            }
            if (!z) {
                break;
            }
        }
        return z ? checkGroupOccurrenceOK(schemaParticle, schemaParticle2, collection, xmlObject) : z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r2 != 3) goto L_0x0023;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean checkGroupOccurrenceOK(org.apache.xmlbeans.SchemaParticle r7, org.apache.xmlbeans.SchemaParticle r8, java.util.Collection<org.apache.xmlbeans.XmlError> r9, org.apache.xmlbeans.XmlObject r10) {
        /*
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            int r2 = r8.getParticleType()
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x001b
            if (r2 == r3) goto L_0x0012
            r5 = 3
            if (r2 == r5) goto L_0x001b
            goto L_0x0023
        L_0x0012:
            java.math.BigInteger r0 = getEffectiveMinRangeChoice(r8)
            java.math.BigInteger r1 = getEffectiveMaxRangeChoice(r8)
            goto L_0x0023
        L_0x001b:
            java.math.BigInteger r0 = getEffectiveMinRangeAllSeq(r8)
            java.math.BigInteger r1 = getEffectiveMaxRangeAllSeq(r8)
        L_0x0023:
            java.math.BigInteger r2 = r7.getMinOccurs()
            int r0 = r0.compareTo(r2)
            r2 = 0
            if (r0 >= 0) goto L_0x0047
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r5 = printParticle(r8)
            r0[r2] = r5
            java.lang.String r5 = printParticle(r7)
            r0[r4] = r5
            java.lang.String r5 = "range-ok.1"
            org.apache.xmlbeans.XmlError r0 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r5, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r10)
            r9.add(r0)
            r0 = r2
            goto L_0x0048
        L_0x0047:
            r0 = r4
        L_0x0048:
            java.math.BigInteger r5 = r7.getMaxOccurs()
            if (r5 == 0) goto L_0x0088
            java.lang.String r5 = "range-ok.2"
            if (r1 != 0) goto L_0x0068
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r8 = printParticle(r8)
            r0[r2] = r8
            java.lang.String r7 = printParticle(r7)
            r0[r4] = r7
            org.apache.xmlbeans.XmlError r7 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r5, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r10)
            r9.add(r7)
            goto L_0x0089
        L_0x0068:
            java.math.BigInteger r6 = r7.getMaxOccurs()
            int r1 = r1.compareTo(r6)
            if (r1 <= 0) goto L_0x0088
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r8 = printParticle(r8)
            r0[r2] = r8
            java.lang.String r7 = printParticle(r7)
            r0[r4] = r7
            org.apache.xmlbeans.XmlError r7 = org.apache.xmlbeans.XmlError.forObject((java.lang.String) r5, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r10)
            r9.add(r7)
            goto L_0x0089
        L_0x0088:
            r2 = r0
        L_0x0089:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.checkGroupOccurrenceOK(org.apache.xmlbeans.SchemaParticle, org.apache.xmlbeans.SchemaParticle, java.util.Collection, org.apache.xmlbeans.XmlObject):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
        if (r0.compareTo(r2) > 0) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005b, code lost:
        if (r0.compareTo(r2) > 0) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005d, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0061 A[LOOP:0: B:1:0x000d->B:32:0x0061, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0064 A[EDGE_INSN: B:40:0x0064->B:33:0x0064 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.math.BigInteger getEffectiveMaxRangeChoice(org.apache.xmlbeans.SchemaParticle r12) {
        /*
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            org.apache.xmlbeans.SchemaParticle[] r3 = r12.getParticleChildren()
            int r4 = r3.length
            r5 = 0
            r6 = r5
        L_0x000d:
            r7 = 0
            if (r5 >= r4) goto L_0x0064
            r8 = r3[r5]
            int r9 = r8.getParticleType()
            r10 = 1
            if (r9 == r10) goto L_0x0051
            r11 = 2
            if (r9 == r11) goto L_0x0044
            r11 = 3
            if (r9 == r11) goto L_0x0051
            r11 = 4
            if (r9 == r11) goto L_0x0026
            r11 = 5
            if (r9 == r11) goto L_0x0026
            goto L_0x005e
        L_0x0026:
            java.math.BigInteger r9 = r8.getMaxOccurs()
            if (r9 != 0) goto L_0x002e
            r0 = r7
            goto L_0x005e
        L_0x002e:
            int r9 = r8.getIntMaxOccurs()
            if (r9 <= 0) goto L_0x005e
            java.math.BigInteger r6 = r8.getMaxOccurs()
            int r6 = r6.compareTo(r1)
            if (r6 <= 0) goto L_0x0042
            java.math.BigInteger r1 = r8.getMaxOccurs()
        L_0x0042:
            r6 = r10
            goto L_0x005e
        L_0x0044:
            java.math.BigInteger r0 = getEffectiveMaxRangeChoice(r8)
            if (r0 == 0) goto L_0x005e
            int r8 = r0.compareTo(r2)
            if (r8 <= 0) goto L_0x005e
            goto L_0x005d
        L_0x0051:
            java.math.BigInteger r0 = getEffectiveMaxRangeAllSeq(r8)
            if (r0 == 0) goto L_0x005e
            int r8 = r0.compareTo(r2)
            if (r8 <= 0) goto L_0x005e
        L_0x005d:
            r2 = r0
        L_0x005e:
            if (r0 != 0) goto L_0x0061
            goto L_0x0064
        L_0x0061:
            int r5 = r5 + 1
            goto L_0x000d
        L_0x0064:
            if (r0 == 0) goto L_0x007c
            if (r6 == 0) goto L_0x006f
            java.math.BigInteger r0 = r12.getMaxOccurs()
            if (r0 != 0) goto L_0x006f
            goto L_0x007d
        L_0x006f:
            java.math.BigInteger r12 = r12.getMaxOccurs()
            java.math.BigInteger r0 = r1.add(r2)
            java.math.BigInteger r7 = r12.multiply(r0)
            goto L_0x007d
        L_0x007c:
            r7 = r0
        L_0x007d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.getEffectiveMaxRangeChoice(org.apache.xmlbeans.SchemaParticle):java.math.BigInteger");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r0.compareTo(r2) > 0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r0.compareTo(r2) > 0) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0057, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005b A[LOOP:0: B:1:0x000d->B:29:0x005b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005e A[EDGE_INSN: B:37:0x005e->B:30:0x005e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.math.BigInteger getEffectiveMaxRangeAllSeq(org.apache.xmlbeans.SchemaParticle r12) {
        /*
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            org.apache.xmlbeans.SchemaParticle[] r3 = r12.getParticleChildren()
            int r4 = r3.length
            r5 = 0
            r6 = r5
        L_0x000d:
            r7 = 0
            if (r5 >= r4) goto L_0x005e
            r8 = r3[r5]
            int r9 = r8.getParticleType()
            r10 = 1
            if (r9 == r10) goto L_0x004b
            r11 = 2
            if (r9 == r11) goto L_0x003e
            r11 = 3
            if (r9 == r11) goto L_0x004b
            r11 = 4
            if (r9 == r11) goto L_0x0026
            r11 = 5
            if (r9 == r11) goto L_0x0026
            goto L_0x0058
        L_0x0026:
            java.math.BigInteger r9 = r8.getMaxOccurs()
            if (r9 != 0) goto L_0x002e
            r0 = r7
            goto L_0x0058
        L_0x002e:
            int r9 = r8.getIntMaxOccurs()
            if (r9 <= 0) goto L_0x0058
            java.math.BigInteger r6 = r8.getMaxOccurs()
            java.math.BigInteger r1 = r1.add(r6)
            r6 = r10
            goto L_0x0058
        L_0x003e:
            java.math.BigInteger r0 = getEffectiveMaxRangeChoice(r8)
            if (r0 == 0) goto L_0x0058
            int r8 = r0.compareTo(r2)
            if (r8 <= 0) goto L_0x0058
            goto L_0x0057
        L_0x004b:
            java.math.BigInteger r0 = getEffectiveMaxRangeAllSeq(r8)
            if (r0 == 0) goto L_0x0058
            int r8 = r0.compareTo(r2)
            if (r8 <= 0) goto L_0x0058
        L_0x0057:
            r2 = r0
        L_0x0058:
            if (r0 != 0) goto L_0x005b
            goto L_0x005e
        L_0x005b:
            int r5 = r5 + 1
            goto L_0x000d
        L_0x005e:
            if (r0 == 0) goto L_0x0076
            if (r6 == 0) goto L_0x0069
            java.math.BigInteger r0 = r12.getMaxOccurs()
            if (r0 != 0) goto L_0x0069
            goto L_0x0077
        L_0x0069:
            java.math.BigInteger r12 = r12.getMaxOccurs()
            java.math.BigInteger r0 = r1.add(r2)
            java.math.BigInteger r7 = r12.multiply(r0)
            goto L_0x0077
        L_0x0076:
            r7 = r0
        L_0x0077:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.getEffectiveMaxRangeAllSeq(org.apache.xmlbeans.SchemaParticle):java.math.BigInteger");
    }

    private static BigInteger getEffectiveMinRangeChoice(SchemaParticle schemaParticle) {
        BigInteger bigInteger;
        SchemaParticle[] particleChildren = schemaParticle.getParticleChildren();
        if (particleChildren.length == 0) {
            return BigInteger.ZERO;
        }
        BigInteger bigInteger2 = null;
        for (SchemaParticle schemaParticle2 : particleChildren) {
            int particleType = schemaParticle2.getParticleType();
            if (particleType != 1) {
                if (particleType == 2) {
                    bigInteger = getEffectiveMinRangeChoice(schemaParticle2);
                    if (bigInteger2 != null && bigInteger2.compareTo(bigInteger) <= 0) {
                    }
                    bigInteger2 = bigInteger;
                } else if (particleType != 3) {
                    if ((particleType == 4 || particleType == 5) && (bigInteger2 == null || bigInteger2.compareTo(schemaParticle2.getMinOccurs()) > 0)) {
                        bigInteger2 = schemaParticle2.getMinOccurs();
                    }
                }
            }
            bigInteger = getEffectiveMinRangeAllSeq(schemaParticle2);
            if (bigInteger2 != null && bigInteger2.compareTo(bigInteger) <= 0) {
            }
            bigInteger2 = bigInteger;
        }
        if (bigInteger2 == null) {
            bigInteger2 = BigInteger.ZERO;
        }
        return schemaParticle.getMinOccurs().multiply(bigInteger2);
    }

    private static BigInteger getEffectiveMinRangeAllSeq(SchemaParticle schemaParticle) {
        SchemaParticle[] particleChildren = schemaParticle.getParticleChildren();
        BigInteger bigInteger = BigInteger.ZERO;
        for (SchemaParticle schemaParticle2 : particleChildren) {
            int particleType = schemaParticle2.getParticleType();
            if (particleType != 1) {
                if (particleType == 2) {
                    bigInteger = bigInteger.add(getEffectiveMinRangeChoice(schemaParticle2));
                } else if (particleType != 3) {
                    if (particleType == 4 || particleType == 5) {
                        bigInteger = bigInteger.add(schemaParticle2.getMinOccurs());
                    }
                }
            }
            bigInteger = bigInteger.add(getEffectiveMinRangeAllSeq(schemaParticle2));
        }
        return schemaParticle.getMinOccurs().multiply(bigInteger);
    }

    private static boolean nsSubset(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (!occurrenceRangeOK(schemaParticle, schemaParticle2, collection, xmlObject)) {
            return false;
        }
        if (schemaParticle.getWildcardSet().inverse().isDisjoint(schemaParticle2.getWildcardSet())) {
            return true;
        }
        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_NS_SUBST$WILDCARD_SUBSET, new Object[]{printParticle(schemaParticle2), printParticle(schemaParticle)}, xmlObject));
        return false;
    }

    private static boolean nsCompat(SchemaParticle schemaParticle, SchemaLocalElement schemaLocalElement, Collection<XmlError> collection, XmlObject xmlObject) {
        if (schemaParticle.getWildcardSet().contains(schemaLocalElement.getName())) {
            return occurrenceRangeOK(schemaParticle, (SchemaParticle) schemaLocalElement, collection, xmlObject);
        }
        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_NS_COMPAT$WILDCARD_VALID, new Object[]{printParticle((SchemaParticle) schemaLocalElement), printParticle(schemaParticle)}, xmlObject));
        return false;
    }

    private static boolean nameAndTypeOK(SchemaLocalElement schemaLocalElement, SchemaLocalElement schemaLocalElement2, Collection<XmlError> collection, XmlObject xmlObject) {
        SchemaParticle schemaParticle = (SchemaParticle) schemaLocalElement;
        if (!schemaParticle.canStartWithElement(schemaLocalElement2.getName())) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$NAME, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), printParticle(schemaParticle)}, xmlObject));
            return false;
        } else if (!schemaLocalElement.isNillable() && schemaLocalElement2.isNillable()) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$NILLABLE, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), printParticle(schemaParticle)}, xmlObject));
            return false;
        } else if (occurrenceRangeOK(schemaParticle, (SchemaParticle) schemaLocalElement2, collection, xmlObject) && checkFixed(schemaLocalElement, schemaLocalElement2, collection, xmlObject) && checkIdentityConstraints(schemaLocalElement, schemaLocalElement2, collection, xmlObject) && typeDerivationOK(schemaLocalElement.getType(), schemaLocalElement2.getType(), collection, xmlObject)) {
            return blockSetOK(schemaLocalElement, schemaLocalElement2, collection, xmlObject);
        } else {
            return false;
        }
    }

    private static boolean blockSetOK(SchemaLocalElement schemaLocalElement, SchemaLocalElement schemaLocalElement2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (schemaLocalElement.blockRestriction() && !schemaLocalElement2.blockRestriction()) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), "restriction", printParticle((SchemaParticle) schemaLocalElement)}, xmlObject));
            return false;
        } else if (schemaLocalElement.blockExtension() && !schemaLocalElement2.blockExtension()) {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), "extension", printParticle((SchemaParticle) schemaLocalElement)}, xmlObject));
            return false;
        } else if (!schemaLocalElement.blockSubstitution() || schemaLocalElement2.blockSubstitution()) {
            return true;
        } else {
            collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), "substitution", printParticle((SchemaParticle) schemaLocalElement)}, xmlObject));
            return false;
        }
    }

    private static boolean typeDerivationOK(SchemaType schemaType, SchemaType schemaType2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (schemaType.isAssignableFrom(schemaType2)) {
            return checkAllDerivationsForRestriction(schemaType, schemaType2, collection, xmlObject);
        }
        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$TYPE_VALID, new Object[]{printType(schemaType2), printType(schemaType)}, xmlObject));
        return false;
    }

    private static boolean checkAllDerivationsForRestriction(SchemaType schemaType, SchemaType schemaType2, Collection<XmlError> collection, XmlObject xmlObject) {
        HashSet hashSet = schemaType.getSimpleVariety() == 2 ? new HashSet(Arrays.asList(schemaType.getUnionConstituentTypes())) : null;
        SchemaType schemaType3 = schemaType2;
        while (!schemaType.equals(schemaType3) && hashSet != null && !hashSet.contains(schemaType3)) {
            if (schemaType3.getDerivationType() == 1) {
                schemaType3 = schemaType3.getBaseType();
            } else {
                collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$TYPE_RESTRICTED, new Object[]{printType(schemaType2), printType(schemaType), printType(schemaType3)}, xmlObject));
                return false;
            }
        }
        return true;
    }

    private static boolean checkIdentityConstraints(SchemaLocalElement schemaLocalElement, SchemaLocalElement schemaLocalElement2, Collection<XmlError> collection, XmlObject xmlObject) {
        SchemaIdentityConstraint[] identityConstraints = schemaLocalElement.getIdentityConstraints();
        for (SchemaIdentityConstraint checkForIdentityConstraintExistence : schemaLocalElement2.getIdentityConstraints()) {
            if (checkForIdentityConstraintExistence(identityConstraints, checkForIdentityConstraintExistence)) {
                collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$IDENTITY_CONSTRAINTS, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), printParticle((SchemaParticle) schemaLocalElement)}, xmlObject));
                return false;
            }
        }
        return true;
    }

    private static boolean checkForIdentityConstraintExistence(SchemaIdentityConstraint[] schemaIdentityConstraintArr, SchemaIdentityConstraint schemaIdentityConstraint) {
        for (SchemaIdentityConstraint name : schemaIdentityConstraintArr) {
            if (name.getName().equals(schemaIdentityConstraint.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkFixed(SchemaLocalElement schemaLocalElement, SchemaLocalElement schemaLocalElement2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (!schemaLocalElement.isFixed() || schemaLocalElement.getDefaultText().equals(schemaLocalElement2.getDefaultText())) {
            return true;
        }
        collection.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$FIXED, new Object[]{printParticle((SchemaParticle) schemaLocalElement2), schemaLocalElement2.getDefaultText(), printParticle((SchemaParticle) schemaLocalElement), schemaLocalElement.getDefaultText()}, xmlObject));
        return false;
    }

    private static boolean occurrenceRangeOK(SchemaParticle schemaParticle, SchemaParticle schemaParticle2, Collection<XmlError> collection, XmlObject xmlObject) {
        if (schemaParticle2.getMinOccurs().compareTo(schemaParticle.getMinOccurs()) < 0) {
            collection.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MIN_GTE_MIN, new Object[]{printParticle(schemaParticle2), schemaParticle2.getMinOccurs().toString(), printParticle(schemaParticle), schemaParticle.getMinOccurs().toString()}, xmlObject));
        } else if (schemaParticle.getMaxOccurs() == null) {
            return true;
        } else {
            if (schemaParticle2.getMaxOccurs() != null && schemaParticle.getMaxOccurs() != null && schemaParticle2.getMaxOccurs().compareTo(schemaParticle.getMaxOccurs()) <= 0) {
                return true;
            }
            collection.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MAX_LTE_MAX, new Object[]{printParticle(schemaParticle2), printMaxOccurs(schemaParticle2.getMaxOccurs()), printParticle(schemaParticle), printMaxOccurs(schemaParticle.getMaxOccurs())}, xmlObject));
        }
        return false;
    }

    private static String printParticles(List<SchemaParticle> list) {
        return printParticles((SchemaParticle[]) list.toArray(new SchemaParticle[0]));
    }

    private static String printParticles(SchemaParticle[] schemaParticleArr) {
        return printParticles(schemaParticleArr, 0, schemaParticleArr.length);
    }

    private static String printParticles(SchemaParticle[] schemaParticleArr, int i) {
        return printParticles(schemaParticleArr, i, schemaParticleArr.length);
    }

    private static String printParticles(SchemaParticle[] schemaParticleArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(schemaParticleArr.length * 30);
        while (i < i2) {
            sb.append(printParticle(schemaParticleArr[i]));
            i++;
            if (i != i2) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private static String printParticle(SchemaParticle schemaParticle) {
        int particleType = schemaParticle.getParticleType();
        if (particleType == 1) {
            return "<all>";
        }
        if (particleType == 2) {
            return "<choice>";
        }
        if (particleType == 3) {
            return "<sequence>";
        }
        if (particleType != 4) {
            return particleType != 5 ? "??" : "<any>";
        }
        return "<element name=\"" + QNameHelper.pretty(schemaParticle.getName()) + "\">";
    }

    private static String printMaxOccurs(BigInteger bigInteger) {
        return bigInteger == null ? "unbounded" : bigInteger.toString();
    }

    private static String printType(SchemaType schemaType) {
        if (schemaType.getName() != null) {
            return QNameHelper.pretty(schemaType.getName());
        }
        return schemaType.toString();
    }

    private static void checkSubstitutionGroups(SchemaGlobalElement[] schemaGlobalElementArr) {
        StscState stscState = StscState.get();
        for (SchemaGlobalElementImpl schemaGlobalElementImpl : schemaGlobalElementArr) {
            SchemaGlobalElement substitutionGroup = schemaGlobalElementImpl.substitutionGroup();
            if (substitutionGroup != null) {
                SchemaType type = substitutionGroup.getType();
                SchemaType type2 = schemaGlobalElementImpl.getType();
                XmlObject xmlObject = schemaGlobalElementImpl._parseObject;
                if (!type.isAssignableFrom(type2)) {
                    stscState.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_VALID, new Object[]{QNameHelper.pretty(schemaGlobalElementImpl.getName()), QNameHelper.pretty(substitutionGroup.getName())}, xmlObject);
                } else if (substitutionGroup.finalExtension() && substitutionGroup.finalRestriction()) {
                    stscState.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(schemaGlobalElementImpl.getName()), QNameHelper.pretty(substitutionGroup.getName()), "#all"}, xmlObject);
                } else if (!type.equals(type2)) {
                    if (substitutionGroup.finalExtension() && type2.getDerivationType() == 2) {
                        stscState.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(schemaGlobalElementImpl.getName()), QNameHelper.pretty(substitutionGroup.getName()), "extension"}, xmlObject);
                    } else if (substitutionGroup.finalRestriction() && type2.getDerivationType() == 1) {
                        stscState.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(schemaGlobalElementImpl.getName()), QNameHelper.pretty(substitutionGroup.getName()), "restriction"}, xmlObject);
                    }
                }
            }
        }
    }
}
