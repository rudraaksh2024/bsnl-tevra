package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;

public class StscSimpleTypeResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final RegularExpression[] EMPTY_REGEX_ARRAY = new RegularExpression[0];
    private static final Map<QName, Integer> facetCodeMap = buildFacetCodeMap();
    private static final CodeForNameEntry[] facetCodes = {new CodeForNameEntry(QNameHelper.forLNS("length", "http://www.w3.org/2001/XMLSchema"), 0), new CodeForNameEntry(QNameHelper.forLNS("minLength", "http://www.w3.org/2001/XMLSchema"), 1), new CodeForNameEntry(QNameHelper.forLNS("maxLength", "http://www.w3.org/2001/XMLSchema"), 2), new CodeForNameEntry(QNameHelper.forLNS("pattern", "http://www.w3.org/2001/XMLSchema"), 10), new CodeForNameEntry(QNameHelper.forLNS("enumeration", "http://www.w3.org/2001/XMLSchema"), 11), new CodeForNameEntry(QNameHelper.forLNS("whiteSpace", "http://www.w3.org/2001/XMLSchema"), 9), new CodeForNameEntry(QNameHelper.forLNS("maxInclusive", "http://www.w3.org/2001/XMLSchema"), 5), new CodeForNameEntry(QNameHelper.forLNS("maxExclusive", "http://www.w3.org/2001/XMLSchema"), 6), new CodeForNameEntry(QNameHelper.forLNS("minInclusive", "http://www.w3.org/2001/XMLSchema"), 4), new CodeForNameEntry(QNameHelper.forLNS("minExclusive", "http://www.w3.org/2001/XMLSchema"), 3), new CodeForNameEntry(QNameHelper.forLNS("totalDigits", "http://www.w3.org/2001/XMLSchema"), 7), new CodeForNameEntry(QNameHelper.forLNS("fractionDigits", "http://www.w3.org/2001/XMLSchema"), 8)};

    static boolean isMultipleFacet(int i) {
        return i == 11 || i == 10;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void resolveSimpleType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r7) {
        /*
            org.apache.xmlbeans.XmlObject r0 = r7.getParseObject()
            org.apache.xmlbeans.impl.xb.xsdschema.SimpleType r0 = (org.apache.xmlbeans.impl.xb.xsdschema.SimpleType) r0
            org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument$Schema r1 = org.apache.xmlbeans.impl.schema.StscComplexTypeResolver.getSchema(r0)
            boolean r2 = r0.isSetList()
            boolean r3 = r0.isSetUnion()
            int r2 = r2 + r3
            boolean r3 = r0.isSetRestriction()
            int r2 = r2 + r3
            r3 = 52
            r4 = 1
            if (r2 <= r4) goto L_0x0027
            org.apache.xmlbeans.impl.schema.StscState r2 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.String r5 = "A simple type must define either a list, a union, or a restriction: more than one found."
            r2.error((java.lang.String) r5, (int) r3, (org.apache.xmlbeans.XmlObject) r0)
            goto L_0x0036
        L_0x0027:
            if (r2 >= r4) goto L_0x0036
            org.apache.xmlbeans.impl.schema.StscState r1 = org.apache.xmlbeans.impl.schema.StscState.get()
            java.lang.String r2 = "A simple type must define either a list, a union, or a restriction: none was found."
            r1.error((java.lang.String) r2, (int) r3, (org.apache.xmlbeans.XmlObject) r0)
            resolveErrorSimpleType(r7)
            return
        L_0x0036:
            boolean r2 = r0.isSetFinal()
            if (r2 == 0) goto L_0x0041
            java.lang.Object r1 = r0.getFinal()
            goto L_0x004f
        L_0x0041:
            if (r1 == 0) goto L_0x004e
            boolean r2 = r1.isSetFinalDefault()
            if (r2 == 0) goto L_0x004e
            java.lang.Object r1 = r1.getFinalDefault()
            goto L_0x004f
        L_0x004e:
            r1 = 0
        L_0x004f:
            r2 = 0
            if (r1 == 0) goto L_0x007e
            boolean r3 = r1 instanceof java.lang.String
            if (r3 == 0) goto L_0x0061
            java.lang.String r3 = "#all"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x007e
            r2 = r4
            r5 = r2
            goto L_0x0080
        L_0x0061:
            boolean r3 = r1 instanceof java.util.List
            if (r3 == 0) goto L_0x007e
            java.util.List r1 = (java.util.List) r1
            java.lang.String r3 = "restriction"
            boolean r3 = r1.contains(r3)
            java.lang.String r5 = "list"
            boolean r5 = r1.contains(r5)
            java.lang.String r6 = "union"
            boolean r1 = r1.contains(r6)
            if (r1 == 0) goto L_0x007c
            r2 = r4
        L_0x007c:
            r4 = r3
            goto L_0x0080
        L_0x007e:
            r4 = r2
            r5 = r4
        L_0x0080:
            r7.setSimpleFinal(r4, r5, r2)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.apache.xmlbeans.impl.xb.xsdschema.ListDocument$List r2 = r0.getList()
            if (r2 == 0) goto L_0x0096
            org.apache.xmlbeans.impl.xb.xsdschema.ListDocument$List r0 = r0.getList()
            resolveListType(r7, r0, r1)
            goto L_0x00b1
        L_0x0096:
            org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument$Union r2 = r0.getUnion()
            if (r2 == 0) goto L_0x00a4
            org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument$Union r0 = r0.getUnion()
            resolveUnionType(r7, r0, r1)
            goto L_0x00b1
        L_0x00a4:
            org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument$Restriction r2 = r0.getRestriction()
            if (r2 == 0) goto L_0x00b1
            org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument$Restriction r0 = r0.getRestriction()
            resolveSimpleRestrictionType(r7, r0, r1)
        L_0x00b1:
            org.apache.xmlbeans.SchemaType$Ref[] r0 = makeRefArray(r1)
            r7.setAnonymousTypeRefs(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver.resolveSimpleType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl):void");
    }

    static /* synthetic */ SchemaType.Ref[] lambda$makeRefArray$0(int i) {
        return new SchemaType.Ref[i];
    }

    private static SchemaType.Ref[] makeRefArray(List<? extends SchemaType> list) {
        return (SchemaType.Ref[]) list.stream().map(new StscComplexTypeResolver$$ExternalSyntheticLambda7()).toArray(new StscSimpleTypeResolver$$ExternalSyntheticLambda0());
    }

    static void resolveErrorSimpleType(SchemaTypeImpl schemaTypeImpl) {
        schemaTypeImpl.setSimpleTypeVariety(1);
        schemaTypeImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        schemaTypeImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        schemaTypeImpl.setPrimitiveTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
    }

    /* JADX WARNING: type inference failed for: r0v16, types: [org.apache.xmlbeans.impl.xb.xsdschema.SimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void resolveListType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r14, org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List r15, java.util.List<org.apache.xmlbeans.SchemaType> r16) {
        /*
            r7 = r14
            r8 = r15
            org.apache.xmlbeans.impl.schema.StscState r9 = org.apache.xmlbeans.impl.schema.StscState.get()
            r10 = 3
            r14.setSimpleTypeVariety(r10)
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r0 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_SIMPLE
            org.apache.xmlbeans.SchemaType$Ref r0 = r0.getRef()
            r14.setBaseTypeRef(r0)
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r0 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_SIMPLE
            int r0 = r0.getBaseDepth()
            r11 = 1
            int r0 = r0 + r11
            r14.setBaseDepth(r0)
            r14.setDerivationType(r11)
            boolean r0 = r14.isRedefinition()
            r1 = 0
            if (r0 == 0) goto L_0x0033
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r2 = "list"
            r0[r1] = r2
            java.lang.String r2 = "src-redefine.5a"
            r9.error((java.lang.String) r2, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r15)
        L_0x0033:
            javax.xml.namespace.QName r0 = r15.getItemType()
            org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType r2 = r15.getSimpleType()
            r12 = 0
            if (r0 == 0) goto L_0x0047
            if (r2 == 0) goto L_0x0047
            java.lang.String r2 = "src-simple-type.3a"
            r9.error((java.lang.String) r2, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r15)
            r13 = r12
            goto L_0x0048
        L_0x0047:
            r13 = r2
        L_0x0048:
            if (r0 == 0) goto L_0x0066
            java.lang.String r2 = r14.getChameleonNamespace()
            java.lang.String r3 = r14.getTargetNamespace()
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = r9.findGlobalType(r0, r2, r3)
            org.apache.xmlbeans.XmlQName r13 = r15.xgetItemType()
            if (r2 != 0) goto L_0x0089
            org.apache.xmlbeans.XmlQName r2 = r15.xgetItemType()
            r9.notFoundError(r0, r1, r2, r11)
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_SIMPLE
            goto L_0x0089
        L_0x0066:
            if (r13 == 0) goto L_0x00f5
            java.lang.String r2 = r14.getTargetNamespace()
            java.lang.String r0 = r14.getChameleonNamespace()
            if (r0 == 0) goto L_0x0074
            r3 = r11
            goto L_0x0075
        L_0x0074:
            r3 = r1
        L_0x0075:
            java.lang.String r4 = r14.getElemFormDefault()
            java.lang.String r5 = r14.getAttFormDefault()
            r0 = r13
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r16
            r6 = r14
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = org.apache.xmlbeans.impl.schema.StscTranslator.translateAnonymousSimpleType(r0, r1, r2, r3, r4, r5, r6)
        L_0x0089:
            boolean r0 = r2.finalList()
            if (r0 == 0) goto L_0x0094
            java.lang.String r0 = "st-props-correct.4.2.1"
            r9.error((java.lang.String) r0, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r15)
        L_0x0094:
            org.apache.xmlbeans.impl.schema.StscResolver.resolveType(r2)
            boolean r0 = r2.isSimpleType()
            if (r0 != 0) goto L_0x00a5
            java.lang.String r0 = "cos-st-restricts.2.1a"
            r9.error((java.lang.String) r0, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r13)
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r0 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_SIMPLE
            goto L_0x00a6
        L_0x00a5:
            r0 = r7
        L_0x00a6:
            int r1 = r2.getSimpleVariety()
            if (r1 == r11) goto L_0x00d3
            r3 = 2
            if (r1 == r3) goto L_0x00c4
            if (r1 == r10) goto L_0x00bb
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r1 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_SIMPLE
            org.apache.xmlbeans.SchemaType$Ref r1 = r1.getRef()
            r0.setListItemTypeRef(r1)
            goto L_0x00e7
        L_0x00bb:
            java.lang.String r1 = "cos-st-restricts.2.1b"
            r9.error((java.lang.String) r1, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r13)
            resolveErrorSimpleType(r0)
            return
        L_0x00c4:
            boolean r1 = r2.isUnionOfLists()
            if (r1 == 0) goto L_0x00d3
            java.lang.String r1 = "cos-st-restricts.2.1c"
            r9.error((java.lang.String) r1, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r13)
            resolveErrorSimpleType(r0)
            return
        L_0x00d3:
            org.apache.xmlbeans.SchemaType$Ref r1 = r2.getRef()
            r0.setListItemTypeRef(r1)
            int r1 = r0.getBuiltinTypeCode()
            r2 = 8
            if (r1 != r2) goto L_0x00e7
            java.lang.String r1 = "enumeration-required-notation"
            r9.recover(r1, r12, r13)
        L_0x00e7:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r1 = org.apache.xmlbeans.impl.schema.StscState.FACETS_LIST
            boolean[] r2 = org.apache.xmlbeans.impl.schema.StscState.FIXED_FACETS_LIST
            r0.setBasicFacets(r1, r2)
            r0.setWhiteSpaceRule(r10)
            resolveFundamentalFacets(r0)
            return
        L_0x00f5:
            java.lang.String r0 = "src-simple-type.3b"
            r9.error((java.lang.String) r0, (java.lang.Object[]) r12, (org.apache.xmlbeans.XmlObject) r15)
            resolveErrorSimpleType(r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver.resolveListType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl, org.apache.xmlbeans.impl.xb.xsdschema.ListDocument$List, java.util.List):void");
    }

    static void resolveUnionType(SchemaTypeImpl schemaTypeImpl, UnionDocument.Union union, List<SchemaType> list) {
        String str;
        XmlObject xmlObject;
        String str2;
        XmlObject xmlObject2;
        SchemaTypeImpl schemaTypeImpl2 = schemaTypeImpl;
        UnionDocument.Union union2 = union;
        schemaTypeImpl2.setSimpleTypeVariety(2);
        schemaTypeImpl2.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        schemaTypeImpl2.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        schemaTypeImpl2.setDerivationType(1);
        StscState stscState = StscState.get();
        if (schemaTypeImpl.isRedefinition()) {
            stscState.error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{XmlErrorCodes.UNION}, (XmlObject) union2);
        }
        List<QName> memberTypes = union.getMemberTypes();
        LocalSimpleType[] simpleTypeArray = union.getSimpleTypeArray();
        ArrayList<SchemaTypeImpl> arrayList = new ArrayList<>();
        if (simpleTypeArray.length == 0 && (memberTypes == null || memberTypes.size() == 0)) {
            stscState.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$UNION_HAS_MEMBER_TYPES_OR_SIMPLE_TYPES, (Object[]) null, (XmlObject) union2);
        }
        if (memberTypes != null) {
            for (QName qName : memberTypes) {
                SchemaTypeImpl findGlobalType = stscState.findGlobalType(qName, schemaTypeImpl.getChameleonNamespace(), schemaTypeImpl.getTargetNamespace());
                if (findGlobalType == null) {
                    stscState.notFoundError(qName, 0, union.xgetMemberTypes(), true);
                } else {
                    arrayList.add(findGlobalType);
                }
            }
        }
        int i = 0;
        while (i < simpleTypeArray.length) {
            SchemaTypeImpl translateAnonymousSimpleType = StscTranslator.translateAnonymousSimpleType(simpleTypeArray[i], schemaTypeImpl.getTargetNamespace(), schemaTypeImpl.getChameleonNamespace() != null, schemaTypeImpl.getElemFormDefault(), schemaTypeImpl.getAttFormDefault(), list, schemaTypeImpl);
            arrayList.add(translateAnonymousSimpleType);
            i++;
            translateAnonymousSimpleType.setAnonymousUnionMemberOrdinal(i);
        }
        Iterator it = arrayList.iterator();
        while (true) {
            str = "";
            if (!it.hasNext()) {
                break;
            }
            SchemaTypeImpl schemaTypeImpl3 = (SchemaTypeImpl) it.next();
            if (!StscResolver.resolveType(schemaTypeImpl3)) {
                if (Objects.equals(schemaTypeImpl3.getOuterType(), schemaTypeImpl2)) {
                    xmlObject2 = schemaTypeImpl3.getParseObject();
                } else {
                    str = QNameHelper.pretty(schemaTypeImpl3.getName()) + " ";
                    xmlObject2 = union.xgetMemberTypes();
                }
                stscState.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$CYCLIC_UNION, new Object[]{str}, xmlObject2);
                it.remove();
            }
        }
        Iterator it2 = arrayList.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            SchemaTypeImpl schemaTypeImpl4 = (SchemaTypeImpl) it2.next();
            if (!schemaTypeImpl4.isSimpleType()) {
                if (schemaTypeImpl4.getOuterType() == null || !schemaTypeImpl4.getOuterType().equals(schemaTypeImpl2)) {
                    str2 = QNameHelper.pretty(schemaTypeImpl4.getName()) + " ";
                    xmlObject = union.xgetMemberTypes();
                } else {
                    xmlObject = schemaTypeImpl4.getParseObject();
                    str2 = str;
                }
                stscState.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$UNION_MEMBER_NOT_SIMPLE, new Object[]{str2}, xmlObject);
                it2.remove();
            } else if (schemaTypeImpl4.getSimpleVariety() == 3 || (schemaTypeImpl4.getSimpleVariety() == 2 && schemaTypeImpl4.isUnionOfLists())) {
                z = true;
            }
        }
        for (SchemaTypeImpl finalUnion : arrayList) {
            if (finalUnion.finalUnion()) {
                stscState.error(XmlErrorCodes.SIMPLE_TYPE_PROPERTIES$UNION_FINAL, (Object[]) null, (XmlObject) union2);
            }
        }
        schemaTypeImpl2.setUnionOfLists(z);
        schemaTypeImpl2.setUnionMemberTypeRefs(makeRefArray(arrayList));
        schemaTypeImpl2.setBasicFacets(StscState.FACETS_UNION, StscState.FIXED_FACETS_UNION);
        resolveFundamentalFacets(schemaTypeImpl);
    }

    static void resolveSimpleRestrictionType(SchemaTypeImpl schemaTypeImpl, RestrictionDocument.Restriction restriction, List<SchemaType> list) {
        LocalSimpleType localSimpleType;
        SchemaTypeImpl schemaTypeImpl2;
        QName base = restriction.getBase();
        LocalSimpleType simpleType = restriction.getSimpleType();
        StscState stscState = StscState.get();
        if (base == null || simpleType == null) {
            localSimpleType = simpleType;
        } else {
            stscState.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$RESTRICTION_HAS_BOTH_BASE_OR_SIMPLE_TYPE, (Object[]) null, (XmlObject) restriction);
            localSimpleType = null;
        }
        boolean z = false;
        if (base != null) {
            if (schemaTypeImpl.isRedefinition()) {
                schemaTypeImpl2 = stscState.findRedefinedGlobalType(restriction.getBase(), schemaTypeImpl.getChameleonNamespace(), schemaTypeImpl);
                if (schemaTypeImpl2 != null && !schemaTypeImpl2.getName().equals(schemaTypeImpl.getName())) {
                    stscState.error(XmlErrorCodes.SCHEMA_REDEFINE$SAME_TYPE, new Object[]{"<simpleType>", QNameHelper.pretty(base), QNameHelper.pretty(schemaTypeImpl.getName())}, (XmlObject) restriction);
                }
            } else {
                schemaTypeImpl2 = stscState.findGlobalType(base, schemaTypeImpl.getChameleonNamespace(), schemaTypeImpl.getTargetNamespace());
            }
            if (schemaTypeImpl2 == null) {
                stscState.notFoundError(base, 0, restriction.xgetBase(), true);
                schemaTypeImpl2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        } else if (localSimpleType != null) {
            if (schemaTypeImpl.isRedefinition()) {
                StscState.get().error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{"<simpleType>"}, (XmlObject) localSimpleType);
            }
            String targetNamespace = schemaTypeImpl.getTargetNamespace();
            if (schemaTypeImpl.getChameleonNamespace() != null) {
                z = true;
            }
            schemaTypeImpl2 = StscTranslator.translateAnonymousSimpleType(localSimpleType, targetNamespace, z, schemaTypeImpl.getElemFormDefault(), schemaTypeImpl.getAttFormDefault(), list, schemaTypeImpl);
        } else {
            stscState.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$RESTRICTION_HAS_NEITHER_BASE_OR_SIMPLE_TYPE, (Object[]) null, (XmlObject) restriction);
            schemaTypeImpl2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!StscResolver.resolveType(schemaTypeImpl2)) {
            schemaTypeImpl2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (schemaTypeImpl2.finalRestriction()) {
            stscState.error(XmlErrorCodes.SIMPLE_TYPE_PROPERTIES$RESTRICTION_FINAL, (Object[]) null, (XmlObject) restriction);
        }
        schemaTypeImpl.setBaseTypeRef(schemaTypeImpl2.getRef());
        schemaTypeImpl.setBaseDepth(schemaTypeImpl2.getBaseDepth() + 1);
        schemaTypeImpl.setDerivationType(1);
        if (!schemaTypeImpl2.isSimpleType()) {
            stscState.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$ATOMIC_NOT_SIMPLE, (Object[]) null, (XmlObject) restriction.xgetBase());
            resolveErrorSimpleType(schemaTypeImpl);
            return;
        }
        schemaTypeImpl.setSimpleTypeVariety(schemaTypeImpl2.getSimpleVariety());
        int simpleVariety = schemaTypeImpl2.getSimpleVariety();
        if (simpleVariety == 1) {
            schemaTypeImpl.setPrimitiveTypeRef(schemaTypeImpl2.getPrimitiveType().getRef());
        } else if (simpleVariety == 2) {
            schemaTypeImpl.setUnionOfLists(schemaTypeImpl2.isUnionOfLists());
            schemaTypeImpl.setUnionMemberTypeRefs(makeRefArray(Arrays.asList(schemaTypeImpl2.getUnionMemberTypes())));
        } else if (simpleVariety == 3) {
            schemaTypeImpl.setListItemTypeRef(schemaTypeImpl2.getListItemType().getRef());
        }
        resolveFacets(schemaTypeImpl, restriction, schemaTypeImpl2);
        resolveFundamentalFacets(schemaTypeImpl);
    }

    static int translateWhitespaceCode(XmlAnySimpleType xmlAnySimpleType) {
        String stringValue = xmlAnySimpleType.getStringValue();
        if (stringValue.equals("collapse")) {
            return 3;
        }
        if (stringValue.equals("preserve")) {
            return 1;
        }
        if (stringValue.equals("replace")) {
            return 2;
        }
        StscState.get().error("Unrecognized whitespace value \"" + stringValue + "\"", 20, (XmlObject) xmlAnySimpleType);
        return 0;
    }

    static boolean facetAppliesToType(int i, SchemaTypeImpl schemaTypeImpl) {
        int simpleVariety = schemaTypeImpl.getSimpleVariety();
        if (simpleVariety == 2) {
            return i == 10 || i == 11;
        }
        if (simpleVariety != 3) {
            switch (schemaTypeImpl.getPrimitiveType().getBuiltinTypeCode()) {
                case 3:
                    return i == 9 || i == 10;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 12:
                    if (!(i == 0 || i == 1 || i == 2)) {
                        switch (i) {
                            case 9:
                            case 10:
                            case 11:
                                break;
                            default:
                                return false;
                        }
                    }
                    return true;
                case 9:
                case 10:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    switch (i) {
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 9:
                        case 10:
                        case 11:
                            return true;
                        default:
                            return false;
                    }
                case 11:
                    switch (i) {
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            return true;
                        default:
                            return false;
                    }
                default:
                    return false;
            }
        } else {
            if (!(i == 0 || i == 1 || i == 2)) {
                switch (i) {
                    case 9:
                    case 10:
                    case 11:
                        break;
                    default:
                        return false;
                }
            }
            return true;
        }
    }

    private static int other_similar_limit(int i) {
        if (i == 3) {
            return 4;
        }
        if (i == 4) {
            return 3;
        }
        if (i == 5) {
            return 6;
        }
        if (i == 6) {
            return 5;
        }
        throw new IllegalStateException();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0247, code lost:
        if (r9 == r13) goto L_0x0249;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0077, code lost:
        if (r11 == 2) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x03a7, code lost:
        r13 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x03ae, code lost:
        if (r8.getFixed() == false) goto L_0x03b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x03b0, code lost:
        r7[r11] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x03f9, code lost:
        if (r22.getBaseType() != r0) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c4, code lost:
        r19 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void resolveFacets(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r22, org.apache.xmlbeans.XmlObject r23, org.apache.xmlbeans.impl.schema.SchemaTypeImpl r24) {
        /*
            r1 = r22
            r2 = r23
            r3 = r24
            org.apache.xmlbeans.impl.schema.StscState r4 = org.apache.xmlbeans.impl.schema.StscState.get()
            r0 = 12
            boolean[] r5 = new boolean[r0]
            org.apache.xmlbeans.XmlAnySimpleType[] r6 = r24.getBasicFacets()
            boolean[] r7 = r24.getFixedFacets()
            r10 = 0
            if (r2 == 0) goto L_0x03bf
            org.apache.xmlbeans.XmlCursor r12 = r23.newCursor()
            boolean r0 = r12.toFirstChild()
            r13 = r10
            r14 = 0
            r15 = 0
        L_0x0024:
            if (r0 == 0) goto L_0x03bb
            javax.xml.namespace.QName r0 = r12.getName()
            java.lang.String r16 = r0.getLocalPart()
            int r11 = translateFacetCode(r0)
            r0 = -1
            if (r11 != r0) goto L_0x003b
        L_0x0035:
            r19 = r5
            r20 = r13
            goto L_0x03a1
        L_0x003b:
            org.apache.xmlbeans.XmlObject r17 = r12.getObject()
            r8 = r17
            org.apache.xmlbeans.impl.xb.xsdschema.Facet r8 = (org.apache.xmlbeans.impl.xb.xsdschema.Facet) r8
            boolean r17 = facetAppliesToType(r11, r3)
            r9 = 2
            if (r17 != 0) goto L_0x005f
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r0[r10] = r16
            javax.xml.namespace.QName r9 = r24.getName()
            java.lang.String r9 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r9)
            r11 = 1
            r0[r11] = r9
            java.lang.String r9 = "cos-applicable-facets"
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x0035
        L_0x005f:
            r10 = 1
            int r0 = r24.getSimpleVariety()
            if (r0 != r10) goto L_0x008f
            org.apache.xmlbeans.SchemaType r0 = r24.getPrimitiveType()
            int r0 = r0.getBuiltinTypeCode()
            r9 = 8
            if (r0 != r9) goto L_0x008f
            if (r11 == 0) goto L_0x007a
            if (r11 == r10) goto L_0x007a
            r9 = 2
            if (r11 != r9) goto L_0x008f
            goto L_0x007b
        L_0x007a:
            r9 = 2
        L_0x007b:
            java.lang.Object[] r0 = new java.lang.Object[r9]
            r9 = 0
            r0[r9] = r16
            javax.xml.namespace.QName r9 = r24.getName()
            java.lang.String r9 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r9)
            r0[r10] = r9
            java.lang.String r9 = "notation-facets"
            r4.warning((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
        L_0x008f:
            boolean r0 = r5[r11]
            if (r0 == 0) goto L_0x00a0
            boolean r0 = isMultipleFacet(r11)
            if (r0 != 0) goto L_0x00a0
            java.lang.String r0 = "src-single-facet-value"
            r9 = 0
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x0035
        L_0x00a0:
            r9 = 1
            r5[r11] = r9
            java.lang.String r10 = "length-minLength-maxLength"
            java.lang.String r0 = "Must be a nonnegative integer"
            java.lang.String r9 = "facet-fixed"
            r20 = r13
            r13 = 20
            switch(r11) {
                case 0: goto L_0x033e;
                case 1: goto L_0x02bf;
                case 2: goto L_0x02bf;
                case 3: goto L_0x01ce;
                case 4: goto L_0x01ce;
                case 5: goto L_0x01ce;
                case 6: goto L_0x01ce;
                case 7: goto L_0x0192;
                case 8: goto L_0x0144;
                case 9: goto L_0x011c;
                case 10: goto L_0x00e5;
                case 11: goto L_0x00b1;
                default: goto L_0x00b0;
            }
        L_0x00b0:
            goto L_0x00c4
        L_0x00b1:
            org.apache.xmlbeans.XmlAnySimpleType r0 = r8.getValue()     // Catch:{ XmlValueOutOfRangeException -> 0x00c8 }
            r9 = 1
            org.apache.xmlbeans.XmlAnySimpleType r0 = r3.newValue(r0, r9)     // Catch:{ XmlValueOutOfRangeException -> 0x00c8 }
            if (r14 != 0) goto L_0x00c1
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
        L_0x00c1:
            r14.add(r0)
        L_0x00c4:
            r19 = r5
            goto L_0x03a7
        L_0x00c8:
            r0 = move-exception
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            org.apache.xmlbeans.XmlAnySimpleType r10 = r8.getValue()
            java.lang.String r10 = r10.getStringValue()
            r11 = 0
            r9[r11] = r10
            java.lang.String r0 = r0.getMessage()
            r10 = 1
            r9[r10] = r0
            java.lang.String r0 = "enumeration-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x00e5:
            org.apache.xmlbeans.impl.regex.RegularExpression r0 = new org.apache.xmlbeans.impl.regex.RegularExpression     // Catch:{ ParseException -> 0x00ff }
            org.apache.xmlbeans.XmlAnySimpleType r9 = r8.getValue()     // Catch:{ ParseException -> 0x00ff }
            java.lang.String r9 = r9.getStringValue()     // Catch:{ ParseException -> 0x00ff }
            java.lang.String r10 = "X"
            r0.<init>(r9, r10)     // Catch:{ ParseException -> 0x00ff }
            if (r15 != 0) goto L_0x00fb
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
        L_0x00fb:
            r15.add(r0)
            goto L_0x00c4
        L_0x00ff:
            r0 = move-exception
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            org.apache.xmlbeans.XmlAnySimpleType r10 = r8.getValue()
            java.lang.String r10 = r10.getStringValue()
            r11 = 0
            r9[r11] = r10
            java.lang.String r0 = r0.getMessage()
            r10 = 1
            r9[r10] = r0
            java.lang.String r0 = "pattern-regex"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x011c:
            org.apache.xmlbeans.XmlAnySimpleType r0 = r8.getValue()
            int r13 = translateWhitespaceCode(r0)
            int r0 = r24.getWhiteSpaceRule()
            if (r0 <= r13) goto L_0x0136
            java.lang.String r0 = "whiteSpace-valid-restriction"
            r9 = 0
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
            r19 = r5
            r5 = 1
            r13 = 0
            goto L_0x03b2
        L_0x0136:
            org.apache.xmlbeans.impl.schema.XmlValueRef r0 = org.apache.xmlbeans.impl.schema.StscState.build_wsstring(r13)
            org.apache.xmlbeans.XmlAnySimpleType r0 = r0.get()
            r6[r11] = r0
            r19 = r5
            goto L_0x03a9
        L_0x0144:
            org.apache.xmlbeans.XmlAnySimpleType r10 = r8.getValue()
            org.apache.xmlbeans.XmlNonNegativeInteger r10 = org.apache.xmlbeans.impl.schema.StscTranslator.buildNnInteger(r10)
            if (r10 != 0) goto L_0x0153
            r4.error((java.lang.String) r0, (int) r13, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x00b0
        L_0x0153:
            boolean r0 = r7[r11]
            if (r0 == 0) goto L_0x016a
            r0 = r6[r11]
            boolean r0 = r0.valueEquals(r10)
            if (r0 != 0) goto L_0x016a
            r13 = 1
            java.lang.Object[] r0 = new java.lang.Object[r13]
            r10 = 0
            r0[r10] = r16
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x016a:
            r9 = 8
            r0 = r6[r9]
            if (r0 == 0) goto L_0x017d
            int r0 = r10.compareValue(r0)
            if (r0 <= 0) goto L_0x017d
            java.lang.String r0 = "fractionDigits-valid-restriction"
            r9 = 0
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x017e
        L_0x017d:
            r9 = 0
        L_0x017e:
            r0 = 7
            r0 = r6[r0]
            if (r0 == 0) goto L_0x018e
            int r0 = r10.compareValue(r0)
            if (r0 <= 0) goto L_0x018e
            java.lang.String r0 = "fractionDigits-totalDigits"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r9, (org.apache.xmlbeans.XmlObject) r8)
        L_0x018e:
            r6[r11] = r10
            goto L_0x00b0
        L_0x0192:
            org.apache.xmlbeans.XmlAnySimpleType r0 = r8.getValue()
            org.apache.xmlbeans.XmlPositiveInteger r0 = org.apache.xmlbeans.impl.schema.StscTranslator.buildPosInteger(r0)
            if (r0 != 0) goto L_0x01a3
            java.lang.String r0 = "Must be a positive integer"
            r4.error((java.lang.String) r0, (int) r13, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x00b0
        L_0x01a3:
            boolean r10 = r7[r11]
            if (r10 == 0) goto L_0x01b9
            r10 = r6[r11]
            boolean r10 = r10.valueEquals(r0)
            if (r10 != 0) goto L_0x01b9
            r10 = 1
            java.lang.Object[] r0 = new java.lang.Object[r10]
            r10 = 0
            r0[r10] = r16
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x01b9:
            r9 = 7
            r9 = r6[r9]
            if (r9 == 0) goto L_0x01ca
            int r9 = r0.compareValue(r9)
            if (r9 <= 0) goto L_0x01ca
            java.lang.String r9 = "totalDigits-valid-restriction"
            r10 = 0
            r4.error((java.lang.String) r9, (java.lang.Object[]) r10, (org.apache.xmlbeans.XmlObject) r8)
        L_0x01ca:
            r6[r11] = r0
            goto L_0x00b0
        L_0x01ce:
            int r0 = other_similar_limit(r11)
            boolean r0 = r5[r0]
            if (r0 == 0) goto L_0x01e1
            java.lang.String r0 = "Cannot define both inclusive and exclusive limit in the same restriciton"
            r9 = 19
            r4.error((java.lang.String) r0, (int) r9, (org.apache.xmlbeans.XmlObject) r8)
        L_0x01dd:
            r19 = r5
            goto L_0x03a1
        L_0x01e1:
            r10 = 4
            r13 = 3
            if (r11 == r13) goto L_0x01ea
            if (r11 != r10) goto L_0x01e8
            goto L_0x01ea
        L_0x01e8:
            r0 = 0
            goto L_0x01eb
        L_0x01ea:
            r0 = 1
        L_0x01eb:
            r10 = 6
            if (r11 == r13) goto L_0x01f4
            if (r11 != r10) goto L_0x01f1
            goto L_0x01f4
        L_0x01f1:
            r21 = 0
            goto L_0x01f6
        L_0x01f4:
            r21 = 1
        L_0x01f6:
            org.apache.xmlbeans.XmlAnySimpleType r10 = r8.getValue()     // Catch:{ XmlValueOutOfRangeException -> 0x026c }
            r13 = 1
            org.apache.xmlbeans.XmlAnySimpleType r10 = r3.newValue(r10, r13)     // Catch:{ XmlValueOutOfRangeException -> 0x026c }
            boolean r18 = r7[r11]
            if (r18 == 0) goto L_0x0215
            r13 = r6[r11]
            boolean r13 = r13.valueEquals(r10)
            if (r13 != 0) goto L_0x0215
            r13 = 1
            java.lang.Object[] r0 = new java.lang.Object[r13]
            r10 = 0
            r0[r10] = r16
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x0215:
            r9 = r6[r11]
            if (r9 == 0) goto L_0x0261
            org.apache.xmlbeans.SchemaType r9 = r10.schemaType()
            if (r9 == 0) goto L_0x0239
            boolean r13 = r9.isSimpleType()
            if (r13 != 0) goto L_0x0239
            int r9 = r9.getContentType()
            r13 = 2
            if (r9 != r13) goto L_0x023a
            org.apache.xmlbeans.SchemaType r9 = r24.getContentBasedOnType()
            org.apache.xmlbeans.XmlAnySimpleType r10 = r8.getValue()
            org.apache.xmlbeans.XmlAnySimpleType r10 = r9.newValue(r10)
            goto L_0x023a
        L_0x0239:
            r13 = 2
        L_0x023a:
            r9 = r6[r11]
            int r9 = r10.compareValue(r9)
            if (r9 == r13) goto L_0x0249
            if (r0 == 0) goto L_0x0246
            r13 = -1
            goto L_0x0247
        L_0x0246:
            r13 = 1
        L_0x0247:
            if (r9 != r13) goto L_0x0261
        L_0x0249:
            if (r0 == 0) goto L_0x0253
            if (r21 == 0) goto L_0x0250
            java.lang.String r0 = "Must be greater than or equal to previous minExclusive"
            goto L_0x025a
        L_0x0250:
            java.lang.String r0 = "Must be greater than or equal to previous minInclusive"
            goto L_0x025a
        L_0x0253:
            if (r21 == 0) goto L_0x0258
            java.lang.String r0 = "Must be less than or equal to previous maxExclusive"
            goto L_0x025a
        L_0x0258:
            java.lang.String r0 = "Must be less than or equal to previous maxInclusive"
        L_0x025a:
            r9 = 20
            r4.error((java.lang.String) r0, (int) r9, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x0261:
            r6[r11] = r10
            int r0 = other_similar_limit(r11)
            r9 = 0
            r6[r0] = r9
            goto L_0x00b0
        L_0x026c:
            r0 = move-exception
            r9 = 3
            if (r11 == r9) goto L_0x02ae
            r9 = 4
            if (r11 == r9) goto L_0x029d
            r9 = 5
            if (r11 == r9) goto L_0x028c
            r9 = 6
            if (r11 == r9) goto L_0x027b
            goto L_0x01dd
        L_0x027b:
            r9 = 1
            java.lang.Object[] r10 = new java.lang.Object[r9]
            java.lang.String r0 = r0.getMessage()
            r11 = 0
            r10[r11] = r0
            java.lang.String r0 = "maxExclusive-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r10, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x028c:
            r9 = 1
            r11 = 0
            java.lang.Object[] r10 = new java.lang.Object[r9]
            java.lang.String r0 = r0.getMessage()
            r10[r11] = r0
            java.lang.String r0 = "maxInclusive-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r10, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x029d:
            r9 = 1
            r11 = 0
            java.lang.Object[] r10 = new java.lang.Object[r9]
            java.lang.String r0 = r0.getMessage()
            r10[r11] = r0
            java.lang.String r0 = "minInclusive-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r10, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x02ae:
            r9 = 1
            r11 = 0
            java.lang.Object[] r10 = new java.lang.Object[r9]
            java.lang.String r0 = r0.getMessage()
            r10[r11] = r0
            java.lang.String r0 = "minExclusive-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r10, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x01dd
        L_0x02bf:
            org.apache.xmlbeans.XmlAnySimpleType r13 = r8.getValue()
            org.apache.xmlbeans.XmlNonNegativeInteger r13 = org.apache.xmlbeans.impl.schema.StscTranslator.buildNnInteger(r13)
            if (r13 != 0) goto L_0x02d2
            r19 = r5
            r5 = 20
            r4.error((java.lang.String) r0, (int) r5, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x02d2:
            r19 = r5
            boolean r0 = r7[r11]
            if (r0 == 0) goto L_0x02eb
            r0 = r6[r11]
            boolean r0 = r0.valueEquals(r13)
            if (r0 != 0) goto L_0x02eb
            r5 = 1
            java.lang.Object[] r0 = new java.lang.Object[r5]
            r5 = 0
            r0[r5] = r16
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x02eb:
            r5 = 0
            r0 = r6[r5]
            if (r0 == 0) goto L_0x0316
            org.apache.xmlbeans.XmlAnySimpleType r0 = r3.getFacet(r11)
            if (r0 == 0) goto L_0x0310
            boolean r9 = r0.valueEquals(r13)
            if (r9 == 0) goto L_0x0310
            r9 = 1
            if (r11 != r9) goto L_0x0308
            r9 = r6[r5]
            int r0 = r0.compareTo(r9)
            if (r0 > 0) goto L_0x0310
            goto L_0x0316
        L_0x0308:
            r9 = r6[r5]
            int r0 = r0.compareTo(r9)
            if (r0 >= 0) goto L_0x0316
        L_0x0310:
            r5 = 0
            r4.error((java.lang.String) r10, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x0316:
            r5 = 0
            r9 = 2
            r0 = r6[r9]
            if (r0 == 0) goto L_0x0329
            int r0 = r13.compareValue(r0)
            if (r0 <= 0) goto L_0x0329
            java.lang.String r0 = "maxLength-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x0329:
            r9 = 1
            r0 = r6[r9]
            if (r0 == 0) goto L_0x033a
            int r0 = r13.compareValue(r0)
            if (r0 >= 0) goto L_0x033a
            java.lang.String r0 = "minLength-valid-restriction"
            r4.error((java.lang.String) r0, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x033a:
            r6[r11] = r13
            goto L_0x03a7
        L_0x033e:
            r19 = r5
            org.apache.xmlbeans.XmlAnySimpleType r5 = r8.getValue()
            org.apache.xmlbeans.XmlNonNegativeInteger r5 = org.apache.xmlbeans.impl.schema.StscTranslator.buildNnInteger(r5)
            if (r5 != 0) goto L_0x0350
            r13 = 20
            r4.error((java.lang.String) r0, (int) r13, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x0350:
            boolean r0 = r7[r11]
            if (r0 == 0) goto L_0x0366
            r0 = r6[r11]
            boolean r0 = r0.valueEquals(r5)
            if (r0 != 0) goto L_0x0366
            r13 = 1
            java.lang.Object[] r0 = new java.lang.Object[r13]
            r5 = 0
            r0[r5] = r16
            r4.error((java.lang.String) r9, (java.lang.Object[]) r0, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x0366:
            r13 = 1
            r0 = r6[r13]
            if (r0 == 0) goto L_0x0384
            org.apache.xmlbeans.XmlAnySimpleType r0 = r3.getFacet(r13)
            if (r0 == 0) goto L_0x037f
            r9 = r6[r13]
            boolean r9 = r0.valueEquals(r9)
            if (r9 == 0) goto L_0x037f
            int r0 = r0.compareValue(r5)
            if (r0 <= 0) goto L_0x0384
        L_0x037f:
            r5 = 0
            r4.error((java.lang.String) r10, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r8)
            goto L_0x03a1
        L_0x0384:
            r9 = 2
            r0 = r6[r9]
            if (r0 == 0) goto L_0x03a5
            org.apache.xmlbeans.XmlAnySimpleType r0 = r3.getFacet(r9)
            if (r0 == 0) goto L_0x039d
            r9 = r6[r9]
            boolean r9 = r0.valueEquals(r9)
            if (r9 == 0) goto L_0x039d
            int r0 = r0.compareValue(r5)
            if (r0 >= 0) goto L_0x03a5
        L_0x039d:
            r5 = 0
            r4.error((java.lang.String) r10, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r8)
        L_0x03a1:
            r13 = r20
            r5 = 1
            goto L_0x03b2
        L_0x03a5:
            r6[r11] = r5
        L_0x03a7:
            r13 = r20
        L_0x03a9:
            boolean r0 = r8.getFixed()
            r5 = 1
            if (r0 == 0) goto L_0x03b2
            r7[r11] = r5
        L_0x03b2:
            boolean r0 = r12.toNextSibling()
            r5 = r19
            r10 = 0
            goto L_0x0024
        L_0x03bb:
            r20 = r13
            r5 = 1
            goto L_0x03c3
        L_0x03bf:
            r5 = 1
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x03c3:
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = makeValueRefArray(r6)
            r1.setBasicFacets(r0, r7)
            if (r13 != 0) goto L_0x03d0
            int r13 = r24.getWhiteSpaceRule()
        L_0x03d0:
            r1.setWhiteSpaceRule(r13)
            if (r14 == 0) goto L_0x0418
            r6 = 0
            org.apache.xmlbeans.XmlAnySimpleType[] r0 = new org.apache.xmlbeans.XmlAnySimpleType[r6]
            java.lang.Object[] r0 = r14.toArray(r0)
            org.apache.xmlbeans.XmlAnySimpleType[] r0 = (org.apache.xmlbeans.XmlAnySimpleType[]) r0
            org.apache.xmlbeans.impl.schema.XmlValueRef[] r0 = makeValueRefArray(r0)
            r1.setEnumerationValues(r0)
            boolean r0 = r22.isRedefinition()
            if (r0 == 0) goto L_0x03fc
            org.apache.xmlbeans.SchemaType r0 = r22.getBaseType()
            org.apache.xmlbeans.SchemaType r0 = r0.getBaseEnumType()
            if (r0 == 0) goto L_0x040f
            org.apache.xmlbeans.SchemaType r7 = r22.getBaseType()
            if (r7 != r0) goto L_0x0410
            goto L_0x040f
        L_0x03fc:
            org.apache.xmlbeans.SchemaType r0 = r22.getBaseType()
            org.apache.xmlbeans.SchemaType r0 = r0.getBaseEnumType()
            if (r0 == 0) goto L_0x040f
            org.apache.xmlbeans.SchemaType r0 = r22.getBaseType()
            org.apache.xmlbeans.SchemaType r0 = r0.getBaseEnumType()
            goto L_0x0410
        L_0x040f:
            r0 = r1
        L_0x0410:
            org.apache.xmlbeans.SchemaType$Ref r0 = r0.getRef()
            r1.setBaseEnumTypeRef(r0)
            goto L_0x041c
        L_0x0418:
            r6 = 0
            r1.copyEnumerationValues(r3)
        L_0x041c:
            org.apache.xmlbeans.impl.regex.RegularExpression[] r0 = EMPTY_REGEX_ARRAY
            if (r15 == 0) goto L_0x0426
            java.lang.Object[] r0 = r15.toArray(r0)
            org.apache.xmlbeans.impl.regex.RegularExpression[] r0 = (org.apache.xmlbeans.impl.regex.RegularExpression[]) r0
        L_0x0426:
            int r7 = r0.length
            if (r7 > 0) goto L_0x0432
            boolean r7 = r24.hasPatternFacet()
            if (r7 == 0) goto L_0x0430
            goto L_0x0432
        L_0x0430:
            r9 = r6
            goto L_0x0433
        L_0x0432:
            r9 = r5
        L_0x0433:
            r1.setPatternFacet(r9)
            r1.setPatterns(r0)
            int r0 = r24.getBuiltinTypeCode()
            r3 = 8
            if (r0 != r3) goto L_0x044d
            org.apache.xmlbeans.XmlAnySimpleType[] r0 = r22.getEnumerationValues()
            if (r0 != 0) goto L_0x044d
            java.lang.String r0 = "enumeration-required-notation"
            r1 = 0
            r4.recover(r0, r1, r2)
        L_0x044d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver.resolveFacets(org.apache.xmlbeans.impl.schema.SchemaTypeImpl, org.apache.xmlbeans.XmlObject, org.apache.xmlbeans.impl.schema.SchemaTypeImpl):void");
    }

    private static XmlValueRef[] makeValueRefArray(XmlAnySimpleType[] xmlAnySimpleTypeArr) {
        int length = xmlAnySimpleTypeArr.length;
        XmlValueRef[] xmlValueRefArr = new XmlValueRef[length];
        for (int i = 0; i < length; i++) {
            xmlValueRefArr[i] = xmlAnySimpleTypeArr[i] == null ? null : new XmlValueRef(xmlAnySimpleTypeArr[i]);
        }
        return xmlValueRefArr;
    }

    private static boolean isDiscreteType(SchemaTypeImpl schemaTypeImpl) {
        int builtinTypeCode;
        if (schemaTypeImpl.getFacet(8) == null && (builtinTypeCode = schemaTypeImpl.getPrimitiveType().getBuiltinTypeCode()) != 3) {
            switch (builtinTypeCode) {
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    private static boolean isNumericPrimitive(SchemaType schemaType) {
        switch (schemaType.getBuiltinTypeCode()) {
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    private static int decimalSizeOfType(SchemaTypeImpl schemaTypeImpl) {
        int mathematicalSizeOfType = mathematicalSizeOfType(schemaTypeImpl);
        if (mathematicalSizeOfType == 8 && !XmlByte.type.isAssignableFrom(schemaTypeImpl)) {
            mathematicalSizeOfType = 16;
        }
        if (mathematicalSizeOfType != 16 || XmlShort.type.isAssignableFrom(schemaTypeImpl) || XmlUnsignedByte.type.isAssignableFrom(schemaTypeImpl)) {
            return mathematicalSizeOfType;
        }
        return 32;
    }

    private static int mathematicalSizeOfType(SchemaTypeImpl schemaTypeImpl) {
        BigInteger bigInteger;
        if (schemaTypeImpl.getPrimitiveType().getBuiltinTypeCode() != 11) {
            return 0;
        }
        if (schemaTypeImpl.getFacet(8) == null || ((SimpleValue) schemaTypeImpl.getFacet(8)).getBigIntegerValue().signum() != 0) {
            return SchemaType.SIZE_BIG_DECIMAL;
        }
        BigInteger bigInteger2 = null;
        BigInteger bigIntegerValue = schemaTypeImpl.getFacet(3) != null ? ((SimpleValue) schemaTypeImpl.getFacet(3)).getBigIntegerValue() : null;
        if (schemaTypeImpl.getFacet(4) != null) {
            bigIntegerValue = ((SimpleValue) schemaTypeImpl.getFacet(4)).getBigIntegerValue();
        }
        BigInteger bigIntegerValue2 = schemaTypeImpl.getFacet(5) != null ? ((SimpleValue) schemaTypeImpl.getFacet(5)).getBigIntegerValue() : null;
        if (schemaTypeImpl.getFacet(6) != null) {
            bigIntegerValue2 = ((SimpleValue) schemaTypeImpl.getFacet(6)).getBigIntegerValue();
        }
        if (schemaTypeImpl.getFacet(7) != null) {
            try {
                switch (((SimpleValue) schemaTypeImpl.getFacet(7)).getBigIntegerValue().intValue()) {
                    case 0:
                    case 1:
                    case 2:
                        bigInteger = BigInteger.valueOf(99);
                        break;
                    case 3:
                    case 4:
                        bigInteger = BigInteger.valueOf(9999);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        bigInteger = BigInteger.valueOf(999999999);
                        break;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        bigInteger = BigInteger.valueOf(999999999999999999L);
                        break;
                }
                bigInteger2 = bigInteger;
            } catch (XmlValueOutOfRangeException unused) {
            }
            if (bigInteger2 != null) {
                BigInteger negate = bigInteger2.negate();
                if (bigIntegerValue != null) {
                    negate = bigIntegerValue.max(negate);
                }
                bigIntegerValue = negate;
                if (bigIntegerValue2 == null) {
                    bigIntegerValue2 = bigInteger2;
                } else {
                    bigIntegerValue2 = bigIntegerValue2.min(bigInteger2);
                }
            }
        }
        if (bigIntegerValue == null || bigIntegerValue2 == null) {
            return 1000000;
        }
        if (bigIntegerValue.signum() < 0) {
            bigIntegerValue = bigIntegerValue.negate().subtract(BigInteger.ONE);
        }
        if (bigIntegerValue2.signum() < 0) {
            bigIntegerValue2 = bigIntegerValue2.negate().subtract(BigInteger.ONE);
        }
        BigInteger max = bigIntegerValue2.max(bigIntegerValue);
        if (max.compareTo(BigInteger.valueOf(127)) <= 0) {
            return 8;
        }
        if (max.compareTo(BigInteger.valueOf(32767)) <= 0) {
            return 16;
        }
        if (max.compareTo(BigInteger.valueOf(2147483647L)) <= 0) {
            return 32;
        }
        return max.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0 ? 64 : 1000000;
    }

    static void resolveFundamentalFacets(SchemaTypeImpl schemaTypeImpl) {
        int simpleVariety = schemaTypeImpl.getSimpleVariety();
        boolean z = true;
        if (simpleVariety == 1) {
            SchemaTypeImpl schemaTypeImpl2 = (SchemaTypeImpl) schemaTypeImpl.getBaseType();
            schemaTypeImpl.setOrdered(schemaTypeImpl2.ordered());
            schemaTypeImpl.setBounded(((schemaTypeImpl.getFacet(3) == null && schemaTypeImpl.getFacet(4) == null) || (schemaTypeImpl.getFacet(5) == null && schemaTypeImpl.getFacet(6) == null)) ? false : true);
            schemaTypeImpl.setFinite(schemaTypeImpl2.isFinite() || (schemaTypeImpl.isBounded() && isDiscreteType(schemaTypeImpl)));
            if (!schemaTypeImpl2.isNumeric() && !isNumericPrimitive(schemaTypeImpl.getPrimitiveType())) {
                z = false;
            }
            schemaTypeImpl.setNumeric(z);
            schemaTypeImpl.setDecimalSize(decimalSizeOfType(schemaTypeImpl));
        } else if (simpleVariety == 2) {
            boolean z2 = true;
            boolean z3 = true;
            boolean z4 = true;
            int i = 0;
            for (SchemaType schemaType : schemaTypeImpl.getUnionMemberTypes()) {
                if (schemaType.ordered() != 0) {
                    i = 1;
                }
                if (!schemaType.isBounded()) {
                    z2 = false;
                }
                if (!schemaType.isFinite()) {
                    z3 = false;
                }
                if (!schemaType.isNumeric()) {
                    z4 = false;
                }
            }
            schemaTypeImpl.setOrdered(i);
            schemaTypeImpl.setBounded(z2);
            schemaTypeImpl.setFinite(z3);
            schemaTypeImpl.setNumeric(z4);
            schemaTypeImpl.setDecimalSize(0);
        } else if (simpleVariety == 3) {
            schemaTypeImpl.setOrdered(0);
            schemaTypeImpl.setBounded((schemaTypeImpl.getFacet(0) == null && schemaTypeImpl.getFacet(2) == null) ? false : true);
            if (!schemaTypeImpl.getListItemType().isFinite() || !schemaTypeImpl.isBounded()) {
                z = false;
            }
            schemaTypeImpl.setFinite(z);
            schemaTypeImpl.setNumeric(false);
            schemaTypeImpl.setDecimalSize(0);
        }
    }

    private static class CodeForNameEntry {
        public int code;
        public QName name;

        CodeForNameEntry(QName qName, int i) {
            this.name = qName;
            this.code = i;
        }
    }

    private static Map<QName, Integer> buildFacetCodeMap() {
        HashMap hashMap = new HashMap();
        for (CodeForNameEntry codeForNameEntry : facetCodes) {
            hashMap.put(codeForNameEntry.name, Integer.valueOf(codeForNameEntry.code));
        }
        return hashMap;
    }

    private static int translateFacetCode(QName qName) {
        return facetCodeMap.getOrDefault(qName, -1).intValue();
    }
}
