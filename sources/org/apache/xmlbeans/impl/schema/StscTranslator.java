package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaBookmark;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlNonNegativeIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlPositiveIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.soap.SOAPArrayType;

public class StscTranslator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String FORM_QUALIFIED = "qualified";
    private static final QName WSDL_ARRAYTYPE_NAME = QNameHelper.forLNS(SoapEncSchemaTypeSystem.ARRAY_TYPE, "http://schemas.xmlsoap.org/wsdl/");
    public static final RegularExpression XPATH_REGEXP = new RegularExpression("(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*", "X");

    public static void addAllDefinitions(StscImporter.SchemaToProcess[] schemaToProcessArr) {
        boolean z;
        StscImporter.SchemaToProcess[] schemaToProcessArr2 = schemaToProcessArr;
        ArrayList<RedefinitionHolder> arrayList = new ArrayList<>();
        boolean z2 = false;
        for (StscImporter.SchemaToProcess schemaToProcess : schemaToProcessArr2) {
            List<StscImporter.SchemaToProcess> redefines = schemaToProcess.getRedefines();
            if (redefines != null) {
                Iterator<RedefineDocument.Redefine> it = schemaToProcess.getRedefineObjects().iterator();
                for (StscImporter.SchemaToProcess redefinitionHolder : redefines) {
                    arrayList.add(new RedefinitionHolder(redefinitionHolder, it.next()));
                }
            }
        }
        RedefinitionMaster redefinitionMaster = new RedefinitionMaster((RedefinitionHolder[]) arrayList.toArray(new RedefinitionHolder[0]));
        StscState stscState = StscState.get();
        int length = schemaToProcessArr2.length;
        int i = 0;
        while (i < length) {
            StscImporter.SchemaToProcess schemaToProcess2 = schemaToProcessArr2[i];
            SchemaDocument.Schema schema = schemaToProcess2.getSchema();
            String chameleonNamespace = schemaToProcess2.getChameleonNamespace();
            if (schema.sizeOfNotationArray() > 0) {
                stscState.warning("Schema <notation> is not yet supported for this release.", 51, (XmlObject) schema.getNotationArray(z2 ? 1 : 0));
            }
            String targetNamespace = schema.getTargetNamespace();
            if (chameleonNamespace == null || targetNamespace != null) {
                chameleonNamespace = targetNamespace;
                z = z2;
            } else {
                z = true;
            }
            if (chameleonNamespace == null) {
                chameleonNamespace = "";
            }
            if (chameleonNamespace.length() > 0 || !isEmptySchema(schema)) {
                stscState.registerContribution(chameleonNamespace, schema.documentProperties().getSourceName());
                stscState.addNewContainer(chameleonNamespace);
            }
            ArrayList arrayList2 = new ArrayList();
            TopLevelComplexType[] complexTypeArray = schema.getComplexTypeArray();
            int length2 = complexTypeArray.length;
            int i2 = z2;
            while (i2 < length2) {
                TopLevelComplexType topLevelComplexType = complexTypeArray[i2];
                RedefinitionHolder[] complexTypeRedefinitions = redefinitionMaster.getComplexTypeRedefinitions(topLevelComplexType.getName(), schemaToProcess2);
                int length3 = complexTypeRedefinitions.length;
                TopLevelComplexType topLevelComplexType2 = topLevelComplexType;
                int i3 = 0;
                while (i3 < length3) {
                    int i4 = length3;
                    RedefinitionHolder redefinitionHolder2 = complexTypeRedefinitions[i3];
                    int i5 = length;
                    if (redefinitionHolder2 != null) {
                        TopLevelComplexType redefineComplexType = redefinitionHolder2.redefineComplexType(topLevelComplexType2.getName());
                        arrayList2.add(topLevelComplexType2);
                        topLevelComplexType2 = redefineComplexType;
                    } else {
                        TopLevelComplexType topLevelComplexType3 = topLevelComplexType2;
                    }
                    i3++;
                    length3 = i4;
                    length = i5;
                }
                int i6 = length;
                SchemaTypeImpl translateGlobalComplexType = translateGlobalComplexType(topLevelComplexType2, chameleonNamespace, z, arrayList2.size() > 0);
                stscState.addGlobalType(translateGlobalComplexType, (SchemaTypeImpl) null);
                int size = arrayList2.size() - 1;
                while (size >= 0) {
                    SchemaTypeImpl translateGlobalComplexType2 = translateGlobalComplexType((TopLevelComplexType) arrayList2.remove(size), chameleonNamespace, z, size > 0);
                    stscState.addGlobalType(translateGlobalComplexType2, translateGlobalComplexType);
                    size--;
                    translateGlobalComplexType = translateGlobalComplexType2;
                }
                i2++;
                StscImporter.SchemaToProcess[] schemaToProcessArr3 = schemaToProcessArr;
                length = i6;
            }
            int i7 = length;
            TopLevelSimpleType[] simpleTypeArray = schema.getSimpleTypeArray();
            int length4 = simpleTypeArray.length;
            int i8 = 0;
            while (i8 < length4) {
                TopLevelSimpleType topLevelSimpleType = simpleTypeArray[i8];
                RedefinitionHolder[] simpleTypeRedefinitions = redefinitionMaster.getSimpleTypeRedefinitions(topLevelSimpleType.getName(), schemaToProcess2);
                int length5 = simpleTypeRedefinitions.length;
                int i9 = 0;
                while (i9 < length5) {
                    TopLevelSimpleType[] topLevelSimpleTypeArr = simpleTypeArray;
                    RedefinitionHolder redefinitionHolder3 = simpleTypeRedefinitions[i9];
                    int i10 = length4;
                    if (redefinitionHolder3 != null) {
                        TopLevelSimpleType redefineSimpleType = redefinitionHolder3.redefineSimpleType(topLevelSimpleType.getName());
                        arrayList2.add(topLevelSimpleType);
                        topLevelSimpleType = redefineSimpleType;
                    }
                    i9++;
                    simpleTypeArray = topLevelSimpleTypeArr;
                    length4 = i10;
                }
                TopLevelSimpleType[] topLevelSimpleTypeArr2 = simpleTypeArray;
                int i11 = length4;
                SchemaTypeImpl translateGlobalSimpleType = translateGlobalSimpleType(topLevelSimpleType, chameleonNamespace, z, arrayList2.size() > 0);
                stscState.addGlobalType(translateGlobalSimpleType, (SchemaTypeImpl) null);
                int size2 = arrayList2.size() - 1;
                while (size2 >= 0) {
                    SchemaTypeImpl translateGlobalSimpleType2 = translateGlobalSimpleType((TopLevelSimpleType) arrayList2.remove(size2), chameleonNamespace, z, size2 > 0);
                    stscState.addGlobalType(translateGlobalSimpleType2, translateGlobalSimpleType);
                    size2--;
                    translateGlobalSimpleType = translateGlobalSimpleType2;
                }
                i8++;
                simpleTypeArray = topLevelSimpleTypeArr2;
                length4 = i11;
            }
            for (TopLevelElement topLevelElement : schema.getElementArray()) {
                stscState.addDocumentType(translateDocumentType(topLevelElement, chameleonNamespace, z), QNameHelper.forLNS(topLevelElement.getName(), chameleonNamespace));
            }
            for (TopLevelAttribute topLevelAttribute : schema.getAttributeArray()) {
                stscState.addAttributeType(translateAttributeType(topLevelAttribute, chameleonNamespace, z), QNameHelper.forLNS(topLevelAttribute.getName(), chameleonNamespace));
            }
            NamedGroup[] groupArray = schema.getGroupArray();
            int length6 = groupArray.length;
            int i12 = 0;
            while (i12 < length6) {
                NamedGroup namedGroup = groupArray[i12];
                RedefinitionHolder[] modelGroupRedefinitions = redefinitionMaster.getModelGroupRedefinitions(namedGroup.getName(), schemaToProcess2);
                int length7 = modelGroupRedefinitions.length;
                int i13 = 0;
                while (i13 < length7) {
                    NamedGroup[] namedGroupArr = groupArray;
                    RedefinitionHolder redefinitionHolder4 = modelGroupRedefinitions[i13];
                    int i14 = length6;
                    if (redefinitionHolder4 != null) {
                        NamedGroup redefineModelGroup = redefinitionHolder4.redefineModelGroup(namedGroup.getName());
                        arrayList2.add(namedGroup);
                        namedGroup = redefineModelGroup;
                    }
                    i13++;
                    groupArray = namedGroupArr;
                    length6 = i14;
                }
                NamedGroup[] namedGroupArr2 = groupArray;
                int i15 = length6;
                SchemaModelGroupImpl translateModelGroup = translateModelGroup(namedGroup, chameleonNamespace, z, arrayList2.size() > 0);
                stscState.addModelGroup(translateModelGroup, (SchemaModelGroupImpl) null);
                int size3 = arrayList2.size() - 1;
                while (size3 >= 0) {
                    SchemaModelGroupImpl translateModelGroup2 = translateModelGroup((NamedGroup) arrayList2.remove(size3), chameleonNamespace, z, size3 > 0);
                    stscState.addModelGroup(translateModelGroup2, translateModelGroup);
                    size3--;
                    translateModelGroup = translateModelGroup2;
                }
                i12++;
                groupArray = namedGroupArr2;
                length6 = i15;
            }
            NamedAttributeGroup[] attributeGroupArray = schema.getAttributeGroupArray();
            int length8 = attributeGroupArray.length;
            int i16 = 0;
            while (i16 < length8) {
                NamedAttributeGroup namedAttributeGroup = attributeGroupArray[i16];
                RedefinitionHolder[] attributeGroupRedefinitions = redefinitionMaster.getAttributeGroupRedefinitions(namedAttributeGroup.getName(), schemaToProcess2);
                int length9 = attributeGroupRedefinitions.length;
                int i17 = 0;
                while (i17 < length9) {
                    NamedAttributeGroup[] namedAttributeGroupArr = attributeGroupArray;
                    RedefinitionHolder redefinitionHolder5 = attributeGroupRedefinitions[i17];
                    RedefinitionMaster redefinitionMaster2 = redefinitionMaster;
                    if (redefinitionHolder5 != null) {
                        NamedAttributeGroup redefineAttributeGroup = redefinitionHolder5.redefineAttributeGroup(namedAttributeGroup.getName());
                        arrayList2.add(namedAttributeGroup);
                        namedAttributeGroup = redefineAttributeGroup;
                    }
                    i17++;
                    attributeGroupArray = namedAttributeGroupArr;
                    redefinitionMaster = redefinitionMaster2;
                }
                NamedAttributeGroup[] namedAttributeGroupArr2 = attributeGroupArray;
                RedefinitionMaster redefinitionMaster3 = redefinitionMaster;
                SchemaAttributeGroupImpl translateAttributeGroup = translateAttributeGroup(namedAttributeGroup, chameleonNamespace, z, arrayList2.size() > 0);
                stscState.addAttributeGroup(translateAttributeGroup, (SchemaAttributeGroupImpl) null);
                int size4 = arrayList2.size() - 1;
                while (size4 >= 0) {
                    SchemaAttributeGroupImpl translateAttributeGroup2 = translateAttributeGroup((NamedAttributeGroup) arrayList2.remove(size4), chameleonNamespace, z, size4 > 0);
                    stscState.addAttributeGroup(translateAttributeGroup2, translateAttributeGroup);
                    size4--;
                    translateAttributeGroup = translateAttributeGroup2;
                }
                i16++;
                attributeGroupArray = namedAttributeGroupArr2;
                redefinitionMaster = redefinitionMaster3;
            }
            RedefinitionMaster redefinitionMaster4 = redefinitionMaster;
            for (AnnotationDocument.Annotation annotation : schema.getAnnotationArray()) {
                stscState.addAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(chameleonNamespace), schema, annotation), chameleonNamespace);
            }
            i++;
            schemaToProcessArr2 = schemaToProcessArr;
            redefinitionMaster = redefinitionMaster4;
            length = i7;
            z2 = false;
        }
        for (RedefinitionHolder complainAboutMissingDefinitions : arrayList) {
            complainAboutMissingDefinitions.complainAboutMissingDefinitions();
        }
    }

    private static class RedefinitionHolder {
        /* access modifiers changed from: private */
        public Map<String, NamedAttributeGroup> agRedefinitions = Collections.emptyMap();
        /* access modifiers changed from: private */
        public Map<String, TopLevelComplexType> ctRedefinitions = Collections.emptyMap();
        /* access modifiers changed from: private */
        public Map<String, NamedGroup> mgRedefinitions = Collections.emptyMap();
        /* access modifiers changed from: private */
        public String schemaLocation = "";
        /* access modifiers changed from: private */
        public final StscImporter.SchemaToProcess schemaRedefined;
        /* access modifiers changed from: private */
        public Map<String, TopLevelSimpleType> stRedefinitions = Collections.emptyMap();

        RedefinitionHolder(StscImporter.SchemaToProcess schemaToProcess, RedefineDocument.Redefine redefine) {
            this.schemaRedefined = schemaToProcess;
            if (redefine != null) {
                StscState stscState = StscState.get();
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                if (redefine.getSchemaLocation() != null) {
                    this.schemaLocation = redefine.getSchemaLocation();
                }
                for (TopLevelComplexType topLevelComplexType : redefine.getComplexTypeArray()) {
                    if (topLevelComplexType.getName() != null) {
                        if (this.ctRedefinitions.containsKey(topLevelComplexType.getName())) {
                            stscState.error("Duplicate type redefinition: " + topLevelComplexType.getName(), 49, (XmlObject) null);
                        } else {
                            this.ctRedefinitions.put(topLevelComplexType.getName(), topLevelComplexType);
                        }
                    }
                }
                for (TopLevelSimpleType topLevelSimpleType : redefine.getSimpleTypeArray()) {
                    if (topLevelSimpleType.getName() != null) {
                        if (this.stRedefinitions.containsKey(topLevelSimpleType.getName())) {
                            stscState.error("Duplicate type redefinition: " + topLevelSimpleType.getName(), 49, (XmlObject) null);
                        } else {
                            this.stRedefinitions.put(topLevelSimpleType.getName(), topLevelSimpleType);
                        }
                    }
                }
                for (NamedGroup namedGroup : redefine.getGroupArray()) {
                    if (namedGroup.getName() != null) {
                        if (this.mgRedefinitions.containsKey(namedGroup.getName())) {
                            stscState.error("Duplicate type redefinition: " + namedGroup.getName(), 49, (XmlObject) null);
                        } else {
                            this.mgRedefinitions.put(namedGroup.getName(), namedGroup);
                        }
                    }
                }
                for (NamedAttributeGroup namedAttributeGroup : redefine.getAttributeGroupArray()) {
                    if (namedAttributeGroup.getName() != null) {
                        if (this.agRedefinitions.containsKey(namedAttributeGroup.getName())) {
                            stscState.error("Duplicate type redefinition: " + namedAttributeGroup.getName(), 49, (XmlObject) null);
                        } else {
                            this.agRedefinitions.put(namedAttributeGroup.getName(), namedAttributeGroup);
                        }
                    }
                }
            }
        }

        public TopLevelSimpleType redefineSimpleType(String str) {
            if (str == null || !this.stRedefinitions.containsKey(str)) {
                return null;
            }
            return this.stRedefinitions.remove(str);
        }

        public TopLevelComplexType redefineComplexType(String str) {
            if (str == null || !this.ctRedefinitions.containsKey(str)) {
                return null;
            }
            return this.ctRedefinitions.remove(str);
        }

        public NamedGroup redefineModelGroup(String str) {
            if (str == null || !this.mgRedefinitions.containsKey(str)) {
                return null;
            }
            return this.mgRedefinitions.remove(str);
        }

        public NamedAttributeGroup redefineAttributeGroup(String str) {
            if (str == null || !this.agRedefinitions.containsKey(str)) {
                return null;
            }
            return this.agRedefinitions.remove(str);
        }

        public void complainAboutMissingDefinitions() {
            if (!this.stRedefinitions.isEmpty() || !this.ctRedefinitions.isEmpty() || !this.agRedefinitions.isEmpty() || !this.mgRedefinitions.isEmpty()) {
                StscState stscState = StscState.get();
                for (String next : this.stRedefinitions.keySet()) {
                    stscState.error("Redefined simple type " + next + " not found in " + this.schemaLocation, 60, (XmlObject) this.stRedefinitions.get(next));
                }
                for (String next2 : this.ctRedefinitions.keySet()) {
                    stscState.error("Redefined complex type " + next2 + " not found in " + this.schemaLocation, 60, (XmlObject) this.ctRedefinitions.get(next2));
                }
                for (String next3 : this.agRedefinitions.keySet()) {
                    stscState.error("Redefined attribute group " + next3 + " not found in " + this.schemaLocation, 60, (XmlObject) this.agRedefinitions.get(next3));
                }
                for (String next4 : this.mgRedefinitions.keySet()) {
                    stscState.error("Redefined model group " + next4 + " not found in " + this.schemaLocation, 60, (XmlObject) this.mgRedefinitions.get(next4));
                }
            }
        }
    }

    private static class RedefinitionMaster {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final short ATTRIBUTE_GROUP = 4;
        private static final short COMPLEX_TYPE = 2;
        private static final RedefinitionHolder[] EMPTY_REDEFINTION_HOLDER_ARRAY = new RedefinitionHolder[0];
        private static final short MODEL_GROUP = 3;
        private static final short SIMPLE_TYPE = 1;
        private Map<String, List<RedefinitionHolder>> agRedefinitions = Collections.emptyMap();
        private Map<String, List<RedefinitionHolder>> ctRedefinitions = Collections.emptyMap();
        private Map<String, List<RedefinitionHolder>> mgRedefinitions = Collections.emptyMap();
        private Map<String, List<RedefinitionHolder>> stRedefinitions = Collections.emptyMap();

        private String componentNameFromCode(short s) {
            return s != 1 ? s != 2 ? s != 3 ? s != 4 ? "" : "attribute group" : "model group" : "complex type" : "simple type";
        }

        static {
            Class<StscTranslator> cls = StscTranslator.class;
        }

        RedefinitionMaster(RedefinitionHolder[] redefinitionHolderArr) {
            if (redefinitionHolderArr.length > 0) {
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                for (RedefinitionHolder redefinitionHolder : redefinitionHolderArr) {
                    for (String computeIfAbsent : redefinitionHolder.stRedefinitions.keySet()) {
                        this.stRedefinitions.computeIfAbsent(computeIfAbsent, new StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda0()).add(redefinitionHolder);
                    }
                    for (String computeIfAbsent2 : redefinitionHolder.ctRedefinitions.keySet()) {
                        this.ctRedefinitions.computeIfAbsent(computeIfAbsent2, new StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda1()).add(redefinitionHolder);
                    }
                    for (String computeIfAbsent3 : redefinitionHolder.agRedefinitions.keySet()) {
                        this.agRedefinitions.computeIfAbsent(computeIfAbsent3, new StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda2()).add(redefinitionHolder);
                    }
                    for (String computeIfAbsent4 : redefinitionHolder.mgRedefinitions.keySet()) {
                        this.mgRedefinitions.computeIfAbsent(computeIfAbsent4, new StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda3()).add(redefinitionHolder);
                    }
                }
            }
        }

        static /* synthetic */ List lambda$new$0(String str) {
            return new ArrayList();
        }

        static /* synthetic */ List lambda$new$1(String str) {
            return new ArrayList();
        }

        static /* synthetic */ List lambda$new$2(String str) {
            return new ArrayList();
        }

        static /* synthetic */ List lambda$new$3(String str) {
            return new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public RedefinitionHolder[] getSimpleTypeRedefinitions(String str, StscImporter.SchemaToProcess schemaToProcess) {
            List list = this.stRedefinitions.get(str);
            if (list == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(list, schemaToProcess, str, 1);
        }

        /* access modifiers changed from: package-private */
        public RedefinitionHolder[] getComplexTypeRedefinitions(String str, StscImporter.SchemaToProcess schemaToProcess) {
            List list = this.ctRedefinitions.get(str);
            if (list == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(list, schemaToProcess, str, 2);
        }

        /* access modifiers changed from: package-private */
        public RedefinitionHolder[] getAttributeGroupRedefinitions(String str, StscImporter.SchemaToProcess schemaToProcess) {
            List list = this.agRedefinitions.get(str);
            if (list == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(list, schemaToProcess, str, 4);
        }

        /* access modifiers changed from: package-private */
        public RedefinitionHolder[] getModelGroupRedefinitions(String str, StscImporter.SchemaToProcess schemaToProcess) {
            List list = this.mgRedefinitions.get(str);
            if (list == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(list, schemaToProcess, str, 3);
        }

        private RedefinitionHolder[] doTopologicalSort(List<RedefinitionHolder> list, StscImporter.SchemaToProcess schemaToProcess, String str, short s) {
            StscImporter.SchemaToProcess schemaToProcess2 = schemaToProcess;
            String str2 = str;
            short s2 = s;
            RedefinitionHolder[] redefinitionHolderArr = new RedefinitionHolder[list.size()];
            int i = 0;
            for (RedefinitionHolder next : list) {
                if (next.schemaRedefined == schemaToProcess2 || next.schemaRedefined.indirectIncludes(schemaToProcess2)) {
                    redefinitionHolderArr[i] = next;
                    i++;
                }
            }
            RedefinitionHolder[] redefinitionHolderArr2 = new RedefinitionHolder[i];
            int[] iArr = new int[i];
            int i2 = 0;
            while (i2 < i - 1) {
                RedefinitionHolder redefinitionHolder = redefinitionHolderArr[i2];
                int i3 = i2 + 1;
                for (int i4 = i3; i4 < i; i4++) {
                    if (redefinitionHolder.schemaRedefined.indirectIncludes(redefinitionHolderArr[i4].schemaRedefined)) {
                        iArr[i2] = iArr[i2] + 1;
                    }
                    if (redefinitionHolderArr[i4].schemaRedefined.indirectIncludes(redefinitionHolder.schemaRedefined)) {
                        iArr[i4] = iArr[i4] + 1;
                    }
                }
                i2 = i3;
            }
            int i5 = 0;
            boolean z = false;
            while (true) {
                XmlObject xmlObject = null;
                if (i5 >= i) {
                    break;
                }
                int i6 = -1;
                for (int i7 = 0; i7 < i; i7++) {
                    if (iArr[i7] == 0 && i6 < 0) {
                        i6 = i7;
                    }
                }
                if (i6 < 0) {
                    if (!z) {
                        StringBuilder sb = new StringBuilder();
                        for (int i8 = 0; i8 < i; i8++) {
                            RedefinitionHolder redefinitionHolder2 = redefinitionHolderArr[i8];
                            if (redefinitionHolder2 != null) {
                                sb.append(redefinitionHolder2.schemaLocation).append(", ");
                                if (xmlObject == null) {
                                    xmlObject = locationFromRedefinitionAndCode(redefinitionHolderArr[i8], str2, s2);
                                }
                            }
                        }
                        StscState.get().error("Detected circular redefinition of " + componentNameFromCode(s2) + " \"" + str2 + "\"; Files involved: " + sb.toString(), 60, xmlObject);
                        z = true;
                    }
                    int i9 = i;
                    for (int i10 = 0; i10 < i; i10++) {
                        int i11 = iArr[i10];
                        if (i11 > 0 && i11 < i9) {
                            i6 = i10;
                            i9 = i11;
                        }
                    }
                    iArr[i6] = iArr[i6] - 1;
                } else {
                    int i12 = i5 + 1;
                    redefinitionHolderArr2[i5] = redefinitionHolderArr[i6];
                    for (int i13 = 0; i13 < i; i13++) {
                        RedefinitionHolder redefinitionHolder3 = redefinitionHolderArr[i13];
                        if (redefinitionHolder3 != null && redefinitionHolder3.schemaRedefined.indirectIncludes(redefinitionHolderArr[i6].schemaRedefined)) {
                            iArr[i13] = iArr[i13] - 1;
                        }
                    }
                    redefinitionHolderArr[i6] = null;
                    iArr[i6] = iArr[i6] - 1;
                    i5 = i12;
                }
            }
            for (int i14 = 1; i14 < i; i14++) {
                int i15 = i14 - 1;
                while (i15 >= 0 && redefinitionHolderArr2[i15] == null) {
                    i15--;
                }
                if (!redefinitionHolderArr2[i14].schemaRedefined.indirectIncludes(redefinitionHolderArr2[i15].schemaRedefined)) {
                    StscState.get().error("Detected multiple redefinitions of " + componentNameFromCode(s2) + " \"" + str2 + "\"; Files involved: " + redefinitionHolderArr2[i15].schemaRedefined.getSourceName() + ", " + redefinitionHolderArr2[i14].schemaRedefined.getSourceName(), 49, locationFromRedefinitionAndCode(redefinitionHolderArr2[i14], str2, s2));
                    if (s2 == 1) {
                        redefinitionHolderArr2[i14].redefineSimpleType(str2);
                    } else if (s2 == 2) {
                        redefinitionHolderArr2[i14].redefineComplexType(str2);
                    } else if (s2 == 3) {
                        redefinitionHolderArr2[i14].redefineModelGroup(str2);
                    } else if (s2 == 4) {
                        redefinitionHolderArr2[i14].redefineAttributeGroup(str2);
                    }
                    redefinitionHolderArr2[i14] = null;
                }
            }
            return redefinitionHolderArr2;
        }

        private XmlObject locationFromRedefinitionAndCode(RedefinitionHolder redefinitionHolder, String str, short s) {
            if (s == 1) {
                return (XmlObject) redefinitionHolder.stRedefinitions.get(str);
            }
            if (s == 2) {
                return (XmlObject) redefinitionHolder.ctRedefinitions.get(str);
            }
            if (s == 3) {
                return (XmlObject) redefinitionHolder.mgRedefinitions.get(str);
            }
            if (s != 4) {
                return null;
            }
            return (XmlObject) redefinitionHolder.agRedefinitions.get(str);
        }
    }

    private static String findFilename(XmlObject xmlObject) {
        return StscState.get().sourceNameForUri(xmlObject.documentProperties().getSourceName());
    }

    private static SchemaTypeImpl translateDocumentType(TopLevelElement topLevelElement, String str, boolean z) {
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(StscState.get().getContainer(str));
        schemaTypeImpl.setDocumentType(true);
        schemaTypeImpl.setParseContext(topLevelElement, str, z, (String) null, (String) null, false);
        schemaTypeImpl.setFilename(findFilename(topLevelElement));
        return schemaTypeImpl;
    }

    private static SchemaTypeImpl translateAttributeType(TopLevelAttribute topLevelAttribute, String str, boolean z) {
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(StscState.get().getContainer(str));
        schemaTypeImpl.setAttributeType(true);
        schemaTypeImpl.setParseContext(topLevelAttribute, str, z, (String) null, (String) null, false);
        schemaTypeImpl.setFilename(findFilename(topLevelAttribute));
        return schemaTypeImpl;
    }

    private static SchemaTypeImpl translateGlobalComplexType(TopLevelComplexType topLevelComplexType, String str, boolean z, boolean z2) {
        StscState stscState = StscState.get();
        String name = topLevelComplexType.getName();
        if (name == null) {
            stscState.error(XmlErrorCodes.MISSING_NAME, new Object[]{"global type"}, (XmlObject) topLevelComplexType);
            return null;
        }
        if (!XMLChar.isValidNCName(name)) {
            stscState.error(XmlErrorCodes.INVALID_VALUE, new Object[]{name, "name"}, (XmlObject) topLevelComplexType.xgetName());
        }
        QName forLNS = QNameHelper.forLNS(name, str);
        if (isReservedTypeName(forLNS)) {
            stscState.warning(XmlErrorCodes.RESERVED_TYPE_NAME, new Object[]{QNameHelper.pretty(forLNS)}, (XmlObject) topLevelComplexType);
            return null;
        }
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(stscState.getContainer(str));
        schemaTypeImpl.setParseContext(topLevelComplexType, str, z, (String) null, (String) null, z2);
        schemaTypeImpl.setFilename(findFilename(topLevelComplexType));
        schemaTypeImpl.setName(QNameHelper.forLNS(name, str));
        schemaTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str), topLevelComplexType));
        schemaTypeImpl.setUserData(getUserData(topLevelComplexType));
        return schemaTypeImpl;
    }

    private static SchemaTypeImpl translateGlobalSimpleType(TopLevelSimpleType topLevelSimpleType, String str, boolean z, boolean z2) {
        StscState stscState = StscState.get();
        String name = topLevelSimpleType.getName();
        if (name == null) {
            stscState.error(XmlErrorCodes.MISSING_NAME, new Object[]{"global type"}, (XmlObject) topLevelSimpleType);
            return null;
        }
        if (!XMLChar.isValidNCName(name)) {
            stscState.error(XmlErrorCodes.INVALID_VALUE, new Object[]{name, "name"}, (XmlObject) topLevelSimpleType.xgetName());
        }
        QName forLNS = QNameHelper.forLNS(name, str);
        if (isReservedTypeName(forLNS)) {
            stscState.warning(XmlErrorCodes.RESERVED_TYPE_NAME, new Object[]{QNameHelper.pretty(forLNS)}, (XmlObject) topLevelSimpleType);
            return null;
        }
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(stscState.getContainer(str));
        schemaTypeImpl.setSimpleType(true);
        schemaTypeImpl.setParseContext(topLevelSimpleType, str, z, (String) null, (String) null, z2);
        schemaTypeImpl.setFilename(findFilename(topLevelSimpleType));
        schemaTypeImpl.setName(forLNS);
        schemaTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str), topLevelSimpleType));
        schemaTypeImpl.setUserData(getUserData(topLevelSimpleType));
        return schemaTypeImpl;
    }

    static SchemaTypeImpl translateAnonymousSimpleType(SimpleType simpleType, String str, boolean z, String str2, String str3, List<SchemaType> list, SchemaType schemaType) {
        StscState stscState = StscState.get();
        SchemaTypeImpl schemaTypeImpl = new SchemaTypeImpl(stscState.getContainer(str));
        schemaTypeImpl.setSimpleType(true);
        schemaTypeImpl.setParseContext(simpleType, str, z, str2, str3, false);
        schemaTypeImpl.setOuterSchemaTypeRef(schemaType.getRef());
        schemaTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str), simpleType));
        schemaTypeImpl.setUserData(getUserData(simpleType));
        list.add(schemaTypeImpl);
        return schemaTypeImpl;
    }

    static FormChoice findElementFormDefault(XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        while (newCursor.getObject().schemaType() != SchemaDocument.Schema.type) {
            if (!newCursor.toParent()) {
                return null;
            }
        }
        return ((SchemaDocument.Schema) newCursor.getObject()).xgetElementFormDefault();
    }

    public static boolean uriMatch(String str, String str2) {
        if (str == null) {
            return str2 == null || str2.equals("");
        }
        if (str2 == null) {
            return str.equals("");
        }
        return str.equals(str2);
    }

    public static void copyGlobalElementToLocalElement(SchemaGlobalElement schemaGlobalElement, SchemaLocalElementImpl schemaLocalElementImpl) {
        schemaLocalElementImpl.setNameAndTypeRef(schemaGlobalElement.getName(), schemaGlobalElement.getType().getRef());
        schemaLocalElementImpl.setNillable(schemaGlobalElement.isNillable());
        schemaLocalElementImpl.setDefault(schemaGlobalElement.getDefaultText(), schemaGlobalElement.isFixed(), ((SchemaGlobalElementImpl) schemaGlobalElement).getParseObject());
        schemaLocalElementImpl.setIdentityConstraints(((SchemaLocalElementImpl) schemaGlobalElement).getIdentityConstraintRefs());
        schemaLocalElementImpl.setBlock(schemaGlobalElement.blockExtension(), schemaGlobalElement.blockRestriction(), schemaGlobalElement.blockSubstitution());
        schemaLocalElementImpl.setAbstract(schemaGlobalElement.isAbstract());
        SchemaParticle schemaParticle = (SchemaParticle) schemaGlobalElement;
        schemaLocalElementImpl.setTransitionRules(schemaParticle.acceptedStartNames(), schemaParticle.isSkippable());
        schemaLocalElementImpl.setAnnotation(schemaGlobalElement.getAnnotation());
    }

    public static void copyGlobalAttributeToLocalAttribute(SchemaGlobalAttributeImpl schemaGlobalAttributeImpl, SchemaLocalAttributeImpl schemaLocalAttributeImpl) {
        schemaLocalAttributeImpl.init(schemaGlobalAttributeImpl.getName(), schemaGlobalAttributeImpl.getTypeRef(), schemaGlobalAttributeImpl.getUse(), schemaGlobalAttributeImpl.getDefaultText(), schemaGlobalAttributeImpl.getParseObject(), schemaGlobalAttributeImpl._defaultValue, schemaGlobalAttributeImpl.isFixed(), schemaGlobalAttributeImpl.getWSDLArrayType(), schemaGlobalAttributeImpl.getAnnotation(), (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0320  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x032d  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x033e A[SYNTHETIC, Splitter:B:155:0x033e] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0356  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0367  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0387  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x03e7  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x043b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl translateElement(org.apache.xmlbeans.impl.xb.xsdschema.Element r23, java.lang.String r24, boolean r25, java.lang.String r26, java.lang.String r27, java.util.List<org.apache.xmlbeans.SchemaType> r28, org.apache.xmlbeans.SchemaType r29) {
        /*
            r0 = r23
            r8 = r24
            r9 = r25
            r5 = r26
            org.apache.xmlbeans.impl.schema.StscState r10 = org.apache.xmlbeans.impl.schema.StscState.get()
            boolean r1 = r23.isSetSubstitutionGroup()
            r11 = 0
            if (r1 == 0) goto L_0x002a
            javax.xml.namespace.QName r1 = r23.getSubstitutionGroup()
            r2 = r29
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r2 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r2
            java.lang.String r2 = r2.getChameleonNamespace()
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r1 = r10.findDocumentType(r1, r2, r8)
            if (r1 == 0) goto L_0x0028
            org.apache.xmlbeans.impl.schema.StscResolver.resolveType(r1)
        L_0x0028:
            r12 = r1
            goto L_0x002b
        L_0x002a:
            r12 = r11
        L_0x002b:
            java.lang.String r1 = r23.getName()
            javax.xml.namespace.QName r2 = r23.getRef()
            r13 = 0
            r14 = 1
            if (r2 == 0) goto L_0x0047
            if (r1 == 0) goto L_0x0047
            java.lang.Object[] r3 = new java.lang.Object[r14]
            r3[r13] = r1
            org.apache.xmlbeans.XmlQName r1 = r23.xgetRef()
            java.lang.String r4 = "src-element.2.1a"
            r10.error((java.lang.String) r4, (java.lang.Object[]) r3, (org.apache.xmlbeans.XmlObject) r1)
            r1 = r11
        L_0x0047:
            if (r2 != 0) goto L_0x0051
            if (r1 != 0) goto L_0x0051
            java.lang.String r1 = "src-element.2.1b"
            r10.error((java.lang.String) r1, (java.lang.Object[]) r11, (org.apache.xmlbeans.XmlObject) r0)
            return r11
        L_0x0051:
            r15 = 2
            if (r1 == 0) goto L_0x006b
            boolean r3 = org.apache.xmlbeans.impl.common.XMLChar.isValidNCName(r1)
            if (r3 != 0) goto L_0x006b
            java.lang.Object[] r3 = new java.lang.Object[r15]
            r3[r13] = r1
            java.lang.String r4 = "name"
            r3[r14] = r4
            org.apache.xmlbeans.XmlNCName r4 = r23.xgetName()
            java.lang.String r6 = "invalid-value"
            r10.error((java.lang.String) r6, (java.lang.Object[]) r3, (org.apache.xmlbeans.XmlObject) r4)
        L_0x006b:
            r7 = 4
            if (r2 == 0) goto L_0x015b
            javax.xml.namespace.QName r1 = r23.getType()
            java.lang.String r3 = "src-element.2.2"
            if (r1 == 0) goto L_0x0083
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "type"
            r1[r13] = r4
            org.apache.xmlbeans.XmlQName r4 = r23.xgetType()
            r10.error((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x0083:
            org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType r1 = r23.getSimpleType()
            if (r1 == 0) goto L_0x0096
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "<simpleType>"
            r1[r13] = r4
            org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType r4 = r23.getSimpleType()
            r10.error((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x0096:
            org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType r1 = r23.getComplexType()
            if (r1 == 0) goto L_0x00a9
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "<complexType>"
            r1[r13] = r4
            org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType r4 = r23.getComplexType()
            r10.error((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x00a9:
            org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum r1 = r23.getForm()
            if (r1 == 0) goto L_0x00bc
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "form"
            r1[r13] = r4
            org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r4 = r23.xgetForm()
            r10.error((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x00bc:
            int r1 = r23.sizeOfKeyArray()
            if (r1 <= 0) goto L_0x00cb
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "<key>"
            r1[r13] = r4
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r0)
        L_0x00cb:
            int r1 = r23.sizeOfKeyrefArray()
            if (r1 <= 0) goto L_0x00da
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "<keyref>"
            r1[r13] = r4
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r0)
        L_0x00da:
            int r1 = r23.sizeOfUniqueArray()
            if (r1 <= 0) goto L_0x00e9
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "<unique>"
            r1[r13] = r4
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r0)
        L_0x00e9:
            boolean r1 = r23.isSetDefault()
            if (r1 == 0) goto L_0x00fc
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "default"
            r1[r13] = r4
            org.apache.xmlbeans.XmlString r4 = r23.xgetDefault()
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x00fc:
            boolean r1 = r23.isSetFixed()
            if (r1 == 0) goto L_0x010f
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "fixed"
            r1[r13] = r4
            org.apache.xmlbeans.XmlString r4 = r23.xgetFixed()
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x010f:
            boolean r1 = r23.isSetBlock()
            if (r1 == 0) goto L_0x0122
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "block"
            r1[r13] = r4
            org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r4 = r23.xgetBlock()
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x0122:
            boolean r1 = r23.isSetNillable()
            if (r1 == 0) goto L_0x0135
            java.lang.Object[] r1 = new java.lang.Object[r14]
            java.lang.String r4 = "nillable"
            r1[r13] = r4
            org.apache.xmlbeans.XmlBoolean r4 = r23.xgetNillable()
            r10.warning((java.lang.String) r3, (java.lang.Object[]) r1, (org.apache.xmlbeans.XmlObject) r4)
        L_0x0135:
            if (r9 == 0) goto L_0x0139
            r1 = r8
            goto L_0x013a
        L_0x0139:
            r1 = r11
        L_0x013a:
            org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl r1 = r10.findGlobalElement(r2, r1, r8)
            if (r1 != 0) goto L_0x0148
            org.apache.xmlbeans.XmlQName r0 = r23.xgetRef()
            r10.notFoundError(r2, r14, r0, r14)
            return r11
        L_0x0148:
            org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl
            r2.<init>()
            r2.setParticleType(r7)
            java.lang.Object r0 = getUserData(r23)
            r2.setUserData(r0)
            copyGlobalElementToLocalElement(r1, r2)
            return r2
        L_0x015b:
            boolean r2 = r0 instanceof org.apache.xmlbeans.impl.xb.xsdschema.LocalElement
            java.lang.String r6 = "restriction"
            java.lang.String r4 = "extension"
            java.lang.String r3 = "#all"
            if (r2 == 0) goto L_0x01a3
            org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl
            r2.<init>()
            org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r16 = r23.xgetForm()
            java.lang.String r7 = "qualified"
            if (r16 == 0) goto L_0x017b
            java.lang.String r15 = r16.getStringValue()
            boolean r7 = r15.equals(r7)
            goto L_0x0195
        L_0x017b:
            if (r5 == 0) goto L_0x0182
            boolean r7 = r5.equals(r7)
            goto L_0x0195
        L_0x0182:
            org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r15 = findElementFormDefault(r23)
            if (r15 == 0) goto L_0x0194
            java.lang.String r15 = r15.getStringValue()
            boolean r7 = r15.equals(r7)
            if (r7 == 0) goto L_0x0194
            r7 = r14
            goto L_0x0195
        L_0x0194:
            r7 = r13
        L_0x0195:
            if (r7 == 0) goto L_0x019d
            javax.xml.namespace.QName r1 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r1, r8)
            goto L_0x0233
        L_0x019d:
            javax.xml.namespace.QName r1 = org.apache.xmlbeans.impl.common.QNameHelper.forLN(r1)
            goto L_0x0233
        L_0x01a3:
            org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl
            org.apache.xmlbeans.impl.schema.SchemaContainer r7 = r10.getContainer(r8)
            r2.<init>(r7)
            if (r12 == 0) goto L_0x01c4
            javax.xml.namespace.QName r7 = r23.getSubstitutionGroup()
            if (r9 == 0) goto L_0x01b6
            r15 = r8
            goto L_0x01b7
        L_0x01b6:
            r15 = r11
        L_0x01b7:
            org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl r7 = r10.findGlobalElement(r7, r15, r8)
            if (r7 == 0) goto L_0x01c4
            org.apache.xmlbeans.SchemaGlobalElement$Ref r7 = r7.getRef()
            r2.setSubstitutionGroup(r7)
        L_0x01c4:
            javax.xml.namespace.QName r1 = org.apache.xmlbeans.impl.common.QNameHelper.forLNS(r1, r8)
            r7 = r29
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r7 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r7
            javax.xml.namespace.QName[] r7 = r7.getSubstitutionGroupMembers()
            org.apache.xmlbeans.QNameSetBuilder r15 = new org.apache.xmlbeans.QNameSetBuilder
            r15.<init>()
            r15.add(r1)
            int r11 = r7.length
            r14 = r13
        L_0x01da:
            if (r14 >= r11) goto L_0x01e8
            r13 = r7[r14]
            r2.addSubstitutionGroupMember(r13)
            r15.add(r13)
            int r14 = r14 + 1
            r13 = 0
            goto L_0x01da
        L_0x01e8:
            org.apache.xmlbeans.QNameSet r7 = org.apache.xmlbeans.QNameSet.forSpecification(r15)
            r11 = 0
            r2.setTransitionRules(r7, r11)
            org.apache.xmlbeans.QNameSet r7 = org.apache.xmlbeans.QNameSet.EMPTY
            r11 = 1
            r2.setTransitionNotes(r7, r11)
            java.lang.Object r7 = r23.getFinal()
            if (r7 == 0) goto L_0x021d
            boolean r11 = r7 instanceof java.lang.String
            if (r11 == 0) goto L_0x0209
            boolean r11 = r7.equals(r3)
            if (r11 == 0) goto L_0x0209
            r7 = 1
            r11 = 1
            goto L_0x021f
        L_0x0209:
            boolean r11 = r7 instanceof java.util.List
            if (r11 == 0) goto L_0x021d
            java.util.List r7 = (java.util.List) r7
            boolean r11 = r7.contains(r4)
            boolean r7 = r7.contains(r6)
            if (r7 == 0) goto L_0x021b
            r7 = 1
            goto L_0x021f
        L_0x021b:
            r7 = 0
            goto L_0x021f
        L_0x021d:
            r7 = 0
            r11 = 0
        L_0x021f:
            r2.setFinal(r11, r7)
            boolean r7 = r23.getAbstract()
            r2.setAbstract(r7)
            java.lang.String r7 = findFilename(r23)
            r2.setFilename(r7)
            r2.setParseContext(r0, r8, r9)
        L_0x0233:
            r11 = r1
            r13 = r2
            org.apache.xmlbeans.impl.schema.SchemaContainer r1 = r10.getContainer(r8)
            org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl r1 = org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl.getAnnotation(r1, r0)
            r13.setAnnotation(r1)
            java.lang.Object r1 = getUserData(r23)
            r13.setUserData(r1)
            javax.xml.namespace.QName r1 = r23.getType()
            if (r1 == 0) goto L_0x026a
            javax.xml.namespace.QName r1 = r23.getType()
            if (r9 == 0) goto L_0x0255
            r2 = r8
            goto L_0x0256
        L_0x0255:
            r2 = 0
        L_0x0256:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r1 = r10.findGlobalType(r1, r2, r8)
            if (r1 != 0) goto L_0x026b
            javax.xml.namespace.QName r2 = r23.getType()
            org.apache.xmlbeans.XmlQName r7 = r23.xgetType()
            r14 = 0
            r15 = 1
            r10.notFoundError(r2, r14, r7, r15)
            goto L_0x026b
        L_0x026a:
            r1 = 0
        L_0x026b:
            org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType r2 = r23.getComplexType()
            if (r2 != 0) goto L_0x0277
            org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType r2 = r23.getSimpleType()
            r7 = 1
            goto L_0x0278
        L_0x0277:
            r7 = 0
        L_0x0278:
            if (r1 == 0) goto L_0x0284
            if (r2 == 0) goto L_0x0284
            java.lang.String r14 = "src-element.3"
            r15 = 0
            r10.error((java.lang.String) r14, (java.lang.Object[]) r15, (org.apache.xmlbeans.XmlObject) r2)
            r14 = 0
            goto L_0x0285
        L_0x0284:
            r14 = r2
        L_0x0285:
            if (r14 == 0) goto L_0x030b
            org.apache.xmlbeans.SchemaComponent[] r1 = r10.getCurrentProcessing()
            int r2 = r1.length
            javax.xml.namespace.QName[] r15 = new javax.xml.namespace.QName[r2]
            r17 = r3
            r3 = 0
        L_0x0291:
            if (r3 >= r2) goto L_0x02ac
            r18 = r2
            r2 = r1[r3]
            r19 = r1
            boolean r1 = r2 instanceof org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl
            if (r1 == 0) goto L_0x02a5
            org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl r2 = (org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl) r2
            javax.xml.namespace.QName r1 = r2.getName()
            r15[r3] = r1
        L_0x02a5:
            int r3 = r3 + 1
            r2 = r18
            r1 = r19
            goto L_0x0291
        L_0x02ac:
            r1 = r29
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r1 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r1
            org.apache.xmlbeans.SchemaType r1 = checkRecursiveGroupReference(r15, r11, r1)
            if (r1 == 0) goto L_0x02bd
            r21 = r4
            r22 = r6
            r20 = r17
            goto L_0x0311
        L_0x02bd:
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r3 = new org.apache.xmlbeans.impl.schema.SchemaTypeImpl
            org.apache.xmlbeans.impl.schema.SchemaContainer r1 = r10.getContainer(r8)
            r3.<init>(r1)
            r3.setContainerField(r13)
            if (r29 != 0) goto L_0x02cd
            r1 = 0
            goto L_0x02d1
        L_0x02cd:
            org.apache.xmlbeans.SchemaType$Ref r1 = r29.getRef()
        L_0x02d1:
            r3.setOuterSchemaTypeRef(r1)
            r3.setGroupReferenceContext(r15)
            r1 = r28
            r1.add(r3)
            r3.setSimpleType(r7)
            r7 = 0
            r1 = r3
            r2 = r14
            r15 = r3
            r20 = r17
            r3 = r24
            r21 = r4
            r4 = r25
            r5 = r26
            r22 = r6
            r6 = r27
            r17 = r11
            r11 = 4
            r1.setParseContext(r2, r3, r4, r5, r6, r7)
            org.apache.xmlbeans.impl.schema.SchemaContainer r1 = r10.getContainer(r8)
            org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl r1 = org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl.getAnnotation(r1, r14)
            r15.setAnnotation(r1)
            java.lang.Object r1 = getUserData(r14)
            r15.setUserData(r1)
            r1 = r15
            goto L_0x0314
        L_0x030b:
            r20 = r3
            r21 = r4
            r22 = r6
        L_0x0311:
            r17 = r11
            r11 = 4
        L_0x0314:
            if (r1 != 0) goto L_0x032b
            if (r12 == 0) goto L_0x032b
            javax.xml.namespace.QName r2 = r23.getSubstitutionGroup()
            if (r9 == 0) goto L_0x0320
            r15 = r8
            goto L_0x0321
        L_0x0320:
            r15 = 0
        L_0x0321:
            org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl r2 = r10.findGlobalElement(r2, r15, r8)
            if (r2 == 0) goto L_0x032b
            org.apache.xmlbeans.SchemaType r1 = r2.getType()
        L_0x032b:
            if (r1 != 0) goto L_0x032f
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r1 = org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem.ST_ANY_TYPE
        L_0x032f:
            org.apache.xmlbeans.XmlCursor r2 = r23.newCursor()
            javax.xml.namespace.QName r3 = WSDL_ARRAYTYPE_NAME
            java.lang.String r3 = r2.getAttributeText(r3)
            r2.dispose()
            if (r3 == 0) goto L_0x0356
            org.apache.xmlbeans.soap.SOAPArrayType r15 = new org.apache.xmlbeans.soap.SOAPArrayType     // Catch:{ XmlValueOutOfRangeException -> 0x034a }
            org.apache.xmlbeans.impl.values.NamespaceContext r2 = new org.apache.xmlbeans.impl.values.NamespaceContext     // Catch:{ XmlValueOutOfRangeException -> 0x034a }
            r2.<init>((org.apache.xmlbeans.XmlObject) r0)     // Catch:{ XmlValueOutOfRangeException -> 0x034a }
            r15.<init>((java.lang.String) r3, (org.apache.xmlbeans.impl.common.PrefixResolver) r2)     // Catch:{ XmlValueOutOfRangeException -> 0x034a }
            r2 = 0
            goto L_0x0358
        L_0x034a:
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r2 = 0
            r4[r2] = r3
            java.lang.String r3 = "soaparray"
            r10.error((java.lang.String) r3, (java.lang.Object[]) r4, (org.apache.xmlbeans.XmlObject) r0)
            goto L_0x0357
        L_0x0356:
            r2 = 0
        L_0x0357:
            r15 = 0
        L_0x0358:
            r13.setWsdlArrayType(r15)
            boolean r3 = r23.isSetFixed()
            boolean r4 = r23.isSetDefault()
            if (r4 == 0) goto L_0x0372
            if (r3 == 0) goto L_0x0372
            java.lang.String r3 = "src-element.1"
            org.apache.xmlbeans.XmlString r4 = r23.xgetFixed()
            r5 = 0
            r10.error((java.lang.String) r3, (java.lang.Object[]) r5, (org.apache.xmlbeans.XmlObject) r4)
            r3 = r2
        L_0x0372:
            r13.setParticleType(r11)
            org.apache.xmlbeans.SchemaType$Ref r1 = r1.getRef()
            r4 = r17
            r13.setNameAndTypeRef(r4, r1)
            boolean r1 = r23.getNillable()
            r13.setNillable(r1)
            if (r3 == 0) goto L_0x038c
            java.lang.String r1 = r23.getFixed()
            goto L_0x0390
        L_0x038c:
            java.lang.String r1 = r23.getDefault()
        L_0x0390:
            r13.setDefault(r1, r3, r0)
            java.lang.Object r1 = r23.getBlock()
            if (r1 == 0) goto L_0x03c7
            boolean r3 = r1 instanceof java.lang.String
            if (r3 == 0) goto L_0x03a9
            r3 = r20
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x03a9
            r1 = 1
            r3 = 1
            r11 = 1
            goto L_0x03ca
        L_0x03a9:
            boolean r3 = r1 instanceof java.util.List
            if (r3 == 0) goto L_0x03c7
            java.util.List r1 = (java.util.List) r1
            r3 = r21
            boolean r11 = r1.contains(r3)
            r3 = r22
            boolean r3 = r1.contains(r3)
            java.lang.String r4 = "substitution"
            boolean r1 = r1.contains(r4)
            if (r1 == 0) goto L_0x03c5
            r1 = 1
            goto L_0x03ca
        L_0x03c5:
            r1 = r2
            goto L_0x03ca
        L_0x03c7:
            r1 = r2
            r3 = r1
            r11 = r3
        L_0x03ca:
            r13.setBlock(r11, r3, r1)
            int r1 = r23.sizeOfKeyArray()
            int r3 = r23.sizeOfKeyrefArray()
            int r1 = r1 + r3
            int r3 = r23.sizeOfUniqueArray()
            int r1 = r1 + r3
            org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl[] r3 = new org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl[r1]
            org.apache.xmlbeans.impl.xb.xsdschema.Keybase[] r4 = r23.getKeyArray()
            r5 = r2
            r6 = r5
            r11 = r6
        L_0x03e4:
            int r7 = r4.length
            if (r11 >= r7) goto L_0x03fd
            r7 = r4[r11]
            org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl r7 = translateIdentityConstraint(r7, r8, r9)
            r3[r5] = r7
            if (r7 == 0) goto L_0x03f6
            r10 = 1
            r7.setConstraintCategory(r10)
            goto L_0x03f8
        L_0x03f6:
            r10 = 1
            r6 = r10
        L_0x03f8:
            int r11 = r11 + 1
            int r5 = r5 + 1
            goto L_0x03e4
        L_0x03fd:
            r10 = 1
            org.apache.xmlbeans.impl.xb.xsdschema.Keybase[] r4 = r23.getUniqueArray()
            r11 = r2
        L_0x0403:
            int r7 = r4.length
            if (r11 >= r7) goto L_0x041b
            r7 = r4[r11]
            org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl r7 = translateIdentityConstraint(r7, r8, r9)
            r3[r5] = r7
            if (r7 == 0) goto L_0x0415
            r12 = 3
            r7.setConstraintCategory(r12)
            goto L_0x0416
        L_0x0415:
            r6 = r10
        L_0x0416:
            int r11 = r11 + 1
            int r5 = r5 + 1
            goto L_0x0403
        L_0x041b:
            org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument$Keyref[] r0 = r23.getKeyrefArray()
            r11 = r2
        L_0x0420:
            int r4 = r0.length
            if (r11 >= r4) goto L_0x0439
            r4 = r0[r11]
            org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl r4 = translateIdentityConstraint(r4, r8, r9)
            r3[r5] = r4
            if (r4 == 0) goto L_0x0432
            r7 = 2
            r4.setConstraintCategory(r7)
            goto L_0x0434
        L_0x0432:
            r7 = 2
            r6 = r10
        L_0x0434:
            int r11 = r11 + 1
            int r5 = r5 + 1
            goto L_0x0420
        L_0x0439:
            if (r6 != 0) goto L_0x044d
            org.apache.xmlbeans.SchemaIdentityConstraint$Ref[] r0 = new org.apache.xmlbeans.SchemaIdentityConstraint.Ref[r1]
        L_0x043d:
            if (r2 >= r1) goto L_0x044a
            r4 = r3[r2]
            org.apache.xmlbeans.SchemaIdentityConstraint$Ref r4 = r4.getRef()
            r0[r2] = r4
            int r2 = r2 + 1
            goto L_0x043d
        L_0x044a:
            r13.setIdentityConstraints(r0)
        L_0x044d:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscTranslator.translateElement(org.apache.xmlbeans.impl.xb.xsdschema.Element, java.lang.String, boolean, java.lang.String, java.lang.String, java.util.List, org.apache.xmlbeans.SchemaType):org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl");
    }

    private static SchemaType checkRecursiveGroupReference(QName[] qNameArr, QName qName, SchemaTypeImpl schemaTypeImpl) {
        QName[] groupReferenceContext;
        if (qNameArr.length < 1) {
            return null;
        }
        while (schemaTypeImpl != null && schemaTypeImpl.getName() == null && !schemaTypeImpl.isDocumentType()) {
            if (qName.equals(schemaTypeImpl.getContainerField().getName()) && (groupReferenceContext = schemaTypeImpl.getGroupReferenceContext()) != null && groupReferenceContext.length == qNameArr.length) {
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= qNameArr.length) {
                        z = true;
                        break;
                    }
                    QName qName2 = qNameArr[i];
                    if ((qName2 != null || groupReferenceContext[i] != null) && (qName2 == null || !qName2.equals(groupReferenceContext[i]))) {
                        break;
                    }
                    i++;
                }
                if (z) {
                    return schemaTypeImpl;
                }
            }
            schemaTypeImpl = (SchemaTypeImpl) schemaTypeImpl.getOuterType();
        }
        return null;
    }

    private static String removeWhitespace(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!XMLChar.isSpace(charAt)) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private static boolean checkXPathSyntax(String str) {
        boolean matches;
        if (str == null) {
            return false;
        }
        String removeWhitespace = removeWhitespace(str);
        RegularExpression regularExpression = XPATH_REGEXP;
        synchronized (regularExpression) {
            matches = regularExpression.matches(removeWhitespace);
        }
        return matches;
    }

    private static SchemaIdentityConstraintImpl translateIdentityConstraint(Keybase keybase, String str, boolean z) {
        StscState stscState = StscState.get();
        String xpath = keybase.getSelector() == null ? null : keybase.getSelector().getXpath();
        if (!checkXPathSyntax(xpath)) {
            stscState.error(XmlErrorCodes.SELECTOR_XPATH, new Object[]{xpath}, (XmlObject) keybase.getSelector().xgetXpath());
            return null;
        }
        FieldDocument.Field[] fieldArray = keybase.getFieldArray();
        for (FieldDocument.Field field : fieldArray) {
            if (!checkXPathSyntax(field.getXpath())) {
                stscState.error(XmlErrorCodes.FIELDS_XPATH, new Object[]{field.getXpath()}, (XmlObject) field.xgetXpath());
                return null;
            }
        }
        SchemaIdentityConstraintImpl schemaIdentityConstraintImpl = new SchemaIdentityConstraintImpl(stscState.getContainer(str));
        schemaIdentityConstraintImpl.setName(QNameHelper.forLNS(keybase.getName(), str));
        schemaIdentityConstraintImpl.setSelector(keybase.getSelector().getXpath());
        schemaIdentityConstraintImpl.setParseContext(keybase, str, z);
        schemaIdentityConstraintImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str), keybase));
        schemaIdentityConstraintImpl.setUserData(getUserData(keybase));
        XmlCursor newCursor = keybase.newCursor();
        HashMap hashMap = new HashMap();
        newCursor.getAllNamespaces(hashMap);
        hashMap.remove("");
        schemaIdentityConstraintImpl.setNSMap(hashMap);
        newCursor.dispose();
        int length = fieldArray.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fieldArray[i].getXpath();
        }
        schemaIdentityConstraintImpl.setFields(strArr);
        try {
            schemaIdentityConstraintImpl.buildPaths();
            stscState.addIdConstraint(schemaIdentityConstraintImpl);
            schemaIdentityConstraintImpl.setFilename(findFilename(keybase));
            return stscState.findIdConstraint(schemaIdentityConstraintImpl.getName(), str, (String) null);
        } catch (XPath.XPathCompileException e) {
            stscState.error(XmlErrorCodes.INVALID_XPATH, new Object[]{e.getMessage()}, (XmlObject) keybase);
            return null;
        }
    }

    public static SchemaModelGroupImpl translateModelGroup(NamedGroup namedGroup, String str, boolean z, boolean z2) {
        String str2;
        String str3;
        String name = namedGroup.getName();
        if (name == null) {
            StscState.get().error(XmlErrorCodes.MISSING_NAME, new Object[]{"model group"}, (XmlObject) namedGroup);
            return null;
        }
        SchemaContainer container = StscState.get().getContainer(str);
        SchemaModelGroupImpl schemaModelGroupImpl = new SchemaModelGroupImpl(container);
        SchemaAnnotationImpl annotation = SchemaAnnotationImpl.getAnnotation(container, namedGroup);
        FormChoice findElementFormDefault = findElementFormDefault(namedGroup);
        FormChoice findAttributeFormDefault = findAttributeFormDefault(namedGroup);
        QName forLNS = QNameHelper.forLNS(name, str);
        if (findElementFormDefault == null) {
            str2 = null;
        } else {
            str2 = findElementFormDefault.getStringValue();
        }
        if (findAttributeFormDefault == null) {
            str3 = null;
        } else {
            str3 = findAttributeFormDefault.getStringValue();
        }
        schemaModelGroupImpl.init(forLNS, str, z, str2, str3, z2, namedGroup, annotation, getUserData(namedGroup));
        schemaModelGroupImpl.setFilename(findFilename(namedGroup));
        return schemaModelGroupImpl;
    }

    public static SchemaAttributeGroupImpl translateAttributeGroup(AttributeGroup attributeGroup, String str, boolean z, boolean z2) {
        String str2;
        String name = attributeGroup.getName();
        if (name == null) {
            StscState.get().error(XmlErrorCodes.MISSING_NAME, new Object[]{"attribute group"}, (XmlObject) attributeGroup);
            return null;
        }
        SchemaContainer container = StscState.get().getContainer(str);
        SchemaAttributeGroupImpl schemaAttributeGroupImpl = new SchemaAttributeGroupImpl(container);
        SchemaAnnotationImpl annotation = SchemaAnnotationImpl.getAnnotation(container, attributeGroup);
        FormChoice findAttributeFormDefault = findAttributeFormDefault(attributeGroup);
        QName forLNS = QNameHelper.forLNS(name, str);
        if (findAttributeFormDefault == null) {
            str2 = null;
        } else {
            str2 = findAttributeFormDefault.getStringValue();
        }
        schemaAttributeGroupImpl.init(forLNS, str, z, str2, z2, attributeGroup, annotation, getUserData(attributeGroup));
        schemaAttributeGroupImpl.setFilename(findFilename(attributeGroup));
        return schemaAttributeGroupImpl;
    }

    static FormChoice findAttributeFormDefault(XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        while (newCursor.getObject().schemaType() != SchemaDocument.Schema.type) {
            if (!newCursor.toParent()) {
                return null;
            }
        }
        return ((SchemaDocument.Schema) newCursor.getObject()).xgetAttributeFormDefault();
    }

    static SchemaLocalAttributeImpl translateAttribute(Attribute attribute, String str, String str2, boolean z, List<SchemaType> list, SchemaType schemaType, SchemaAttributeModel schemaAttributeModel, boolean z2) {
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl;
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl2;
        int i;
        int i2;
        String str3;
        String str4;
        SchemaType schemaType2;
        boolean z3;
        boolean z4;
        QName qName;
        SchemaTypeImpl schemaTypeImpl;
        LocalSimpleType localSimpleType;
        SchemaTypeImpl schemaTypeImpl2;
        SchemaType.Ref ref;
        boolean z5;
        Attribute attribute2 = attribute;
        String str5 = str;
        String str6 = str2;
        boolean z6 = z;
        SchemaAttributeModel schemaAttributeModel2 = schemaAttributeModel;
        StscState stscState = StscState.get();
        String name = attribute.getName();
        QName ref2 = attribute.getRef();
        SOAPArrayType sOAPArrayType = null;
        if (!(ref2 == null || name == null)) {
            if (!name.equals(ref2.getLocalPart()) || !uriMatch(str5, ref2.getNamespaceURI())) {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_BOTH, new Object[]{name}, (XmlObject) attribute.xgetRef());
            } else {
                stscState.warning(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_BOTH, new Object[]{name}, (XmlObject) attribute.xgetRef());
            }
            name = null;
        }
        if (ref2 == null && name == null) {
            stscState.error(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_NEITHER, (Object[]) null, (XmlObject) attribute2);
            return null;
        }
        if (name != null && !XMLChar.isValidNCName(name)) {
            stscState.error(XmlErrorCodes.INVALID_VALUE, new Object[]{name, "name"}, (XmlObject) attribute.xgetName());
        }
        if (z2) {
            schemaGlobalAttributeImpl = new SchemaLocalAttributeImpl();
        } else {
            SchemaGlobalAttributeImpl schemaGlobalAttributeImpl3 = new SchemaGlobalAttributeImpl(StscState.get().getContainer(str5));
            SchemaGlobalAttributeImpl schemaGlobalAttributeImpl4 = schemaGlobalAttributeImpl3;
            schemaGlobalAttributeImpl3.setParseContext(attribute2, str5, z6);
            schemaGlobalAttributeImpl = schemaGlobalAttributeImpl3;
        }
        SchemaGlobalAttributeImpl schemaGlobalAttributeImpl5 = schemaGlobalAttributeImpl;
        if (ref2 != null) {
            if (attribute.getType() != null) {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{"type"}, (XmlObject) attribute.xgetType());
            }
            if (attribute.getSimpleType() != null) {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{"<simpleType>"}, (XmlObject) attribute.getSimpleType());
            }
            if (attribute.getForm() != null) {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{"form"}, (XmlObject) attribute.xgetForm());
            }
            SchemaGlobalAttributeImpl findGlobalAttribute = stscState.findGlobalAttribute(ref2, z6 ? str5 : null, str5);
            if (findGlobalAttribute == null) {
                stscState.notFoundError(ref2, 3, attribute.xgetRef(), true);
                return null;
            }
            int use = findGlobalAttribute.getUse();
            schemaType2 = findGlobalAttribute.getType();
            str4 = findGlobalAttribute.getDefaultText();
            if (str4 != null) {
                z3 = findGlobalAttribute.isFixed();
                if (z3) {
                    str3 = str4;
                    schemaGlobalAttributeImpl2 = schemaGlobalAttributeImpl5;
                    i = 2;
                    i2 = use;
                }
            } else {
                z3 = false;
            }
            str3 = null;
            schemaGlobalAttributeImpl2 = schemaGlobalAttributeImpl5;
            i = 2;
            i2 = use;
        } else {
            if (z2) {
                FormChoice xgetForm = attribute.xgetForm();
                if (xgetForm != null) {
                    z5 = xgetForm.getStringValue().equals(FORM_QUALIFIED);
                } else if (str6 != null) {
                    z5 = str6.equals(FORM_QUALIFIED);
                } else {
                    FormChoice findAttributeFormDefault = findAttributeFormDefault(attribute);
                    z5 = findAttributeFormDefault != null && findAttributeFormDefault.getStringValue().equals(FORM_QUALIFIED);
                }
                qName = z5 ? QNameHelper.forLNS(name, str5) : QNameHelper.forLN(name);
            } else {
                qName = QNameHelper.forLNS(name, str5);
            }
            if (attribute.getType() != null) {
                schemaTypeImpl = stscState.findGlobalType(attribute.getType(), z6 ? str5 : null, str5);
                if (schemaTypeImpl == null) {
                    stscState.notFoundError(attribute.getType(), 0, attribute.xgetType(), true);
                }
            } else {
                schemaTypeImpl = null;
            }
            if (qName.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance")) {
                stscState.error(XmlErrorCodes.NO_XSI, new Object[]{"http://www.w3.org/2001/XMLSchema-instance"}, (XmlObject) attribute.xgetName());
            }
            if (qName.getNamespaceURI().length() == 0 && qName.getLocalPart().equals(Sax2Dom.XMLNS_PREFIX)) {
                stscState.error(XmlErrorCodes.NO_XMLNS, (Object[]) null, (XmlObject) attribute.xgetName());
            }
            LocalSimpleType simpleType = attribute.getSimpleType();
            if (schemaTypeImpl == null || simpleType == null) {
                localSimpleType = simpleType;
            } else {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$TYPE_ATTR_OR_NESTED_TYPE, (Object[]) null, (XmlObject) simpleType);
                localSimpleType = null;
            }
            if (localSimpleType != null) {
                SchemaTypeImpl schemaTypeImpl3 = new SchemaTypeImpl(stscState.getContainer(str5));
                schemaTypeImpl3.setContainerField(schemaGlobalAttributeImpl5);
                if (schemaType == null) {
                    ref = null;
                } else {
                    ref = schemaType.getRef();
                }
                schemaTypeImpl3.setOuterSchemaTypeRef(ref);
                list.add(schemaTypeImpl3);
                schemaTypeImpl3.setSimpleType(true);
                schemaTypeImpl2 = schemaTypeImpl3;
                LocalSimpleType localSimpleType2 = localSimpleType;
                schemaGlobalAttributeImpl2 = schemaGlobalAttributeImpl5;
                i = 2;
                schemaTypeImpl3.setParseContext(localSimpleType, str, z, (String) null, (String) null, false);
                schemaTypeImpl2.setAnnotation(SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str5), localSimpleType2));
                schemaTypeImpl2.setUserData(getUserData(localSimpleType2));
            } else {
                schemaGlobalAttributeImpl2 = schemaGlobalAttributeImpl5;
                i = 2;
                schemaTypeImpl2 = schemaTypeImpl;
            }
            if (schemaTypeImpl2 != null || schemaAttributeModel2 == null || schemaAttributeModel2.getAttribute(qName) == null) {
                ref2 = qName;
                i2 = i;
                schemaType2 = schemaTypeImpl2;
            } else {
                schemaType2 = schemaAttributeModel2.getAttribute(qName).getType();
                ref2 = qName;
                i2 = i;
            }
            str4 = null;
            str3 = null;
            z3 = false;
        }
        if (schemaType2 == null) {
            schemaType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!schemaType2.isSimpleType()) {
            stscState.error("Attributes must have a simple type (not complex).", 46, (XmlObject) attribute2);
            schemaType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (attribute.isSetUse()) {
            int translateUseCode = translateUseCode(attribute.xgetUse());
            if (translateUseCode == i || z3) {
                i2 = translateUseCode;
            } else {
                i2 = translateUseCode;
                str4 = null;
            }
        }
        if (attribute.isSetDefault() || attribute.isSetFixed()) {
            if (z3 && !attribute.isSetFixed()) {
                stscState.error("A use of a fixed attribute definition must also be fixed", 9, (XmlObject) attribute.xgetFixed());
            }
            boolean isSetFixed = attribute.isSetFixed();
            if (attribute.isSetDefault() && isSetFixed) {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$DEFAULT_OR_FIXED, (Object[]) null, (XmlObject) attribute.xgetFixed());
                isSetFixed = false;
            }
            String fixed = isSetFixed ? attribute.getFixed() : attribute.getDefault();
            if (str3 == null || str3.equals(fixed)) {
                z4 = isSetFixed;
                str3 = fixed;
            } else {
                stscState.error(XmlErrorCodes.SCHEMA_ATTR$FIXED_NOT_MATCH, (Object[]) null, (XmlObject) attribute.xgetFixed());
                z4 = isSetFixed;
            }
        } else {
            z4 = z3;
            str3 = str4;
        }
        if (!z2) {
            schemaGlobalAttributeImpl2.setFilename(findFilename(attribute));
        }
        XmlCursor newCursor = attribute.newCursor();
        String attributeText = newCursor.getAttributeText(WSDL_ARRAYTYPE_NAME);
        newCursor.dispose();
        if (attributeText != null) {
            try {
                sOAPArrayType = new SOAPArrayType(attributeText, (PrefixResolver) new NamespaceContext((XmlObject) attribute2));
            } catch (XmlValueOutOfRangeException unused) {
                stscState.error(XmlErrorCodes.SOAPARRAY, new Object[]{attributeText}, (XmlObject) attribute2);
            }
        }
        schemaGlobalAttributeImpl2.init(ref2, schemaType2.getRef(), i2, str3, attribute, (XmlValueRef) null, z4, sOAPArrayType, SchemaAnnotationImpl.getAnnotation(stscState.getContainer(str5), attribute2), getUserData(attribute));
        return schemaGlobalAttributeImpl2;
    }

    static int translateUseCode(Attribute.Use use) {
        if (use == null) {
            return 2;
        }
        String stringValue = use.getStringValue();
        if (stringValue.equals("optional")) {
            return 2;
        }
        if (stringValue.equals("required")) {
            return 3;
        }
        if (stringValue.equals("prohibited")) {
            return 1;
        }
        return 2;
    }

    static BigInteger buildBigInt(XmlAnySimpleType xmlAnySimpleType) {
        if (xmlAnySimpleType == null) {
            return null;
        }
        String stringValue = xmlAnySimpleType.getStringValue();
        try {
            BigInteger bigInteger = new BigInteger(stringValue);
            if (bigInteger.signum() >= 0) {
                return bigInteger;
            }
            StscState.get().error(XmlErrorCodes.INVALID_VALUE, new Object[]{stringValue, "nonNegativeInteger"}, (XmlObject) xmlAnySimpleType);
            return null;
        } catch (NumberFormatException e) {
            StscState.get().error(XmlErrorCodes.INVALID_VALUE_DETAIL, new Object[]{stringValue, "nonNegativeInteger", e.getMessage()}, (XmlObject) xmlAnySimpleType);
            return null;
        }
    }

    static XmlNonNegativeInteger buildNnInteger(XmlAnySimpleType xmlAnySimpleType) {
        BigInteger buildBigInt = buildBigInt(xmlAnySimpleType);
        try {
            XmlNonNegativeIntegerImpl xmlNonNegativeIntegerImpl = new XmlNonNegativeIntegerImpl();
            xmlNonNegativeIntegerImpl.setBigIntegerValue(buildBigInt);
            xmlNonNegativeIntegerImpl.setImmutable();
            return xmlNonNegativeIntegerImpl;
        } catch (XmlValueOutOfRangeException unused) {
            StscState.get().error("Internal error processing number", 21, (XmlObject) xmlAnySimpleType);
            return null;
        }
    }

    static XmlPositiveInteger buildPosInteger(XmlAnySimpleType xmlAnySimpleType) {
        BigInteger buildBigInt = buildBigInt(xmlAnySimpleType);
        try {
            XmlPositiveIntegerImpl xmlPositiveIntegerImpl = new XmlPositiveIntegerImpl();
            xmlPositiveIntegerImpl.setBigIntegerValue(buildBigInt);
            xmlPositiveIntegerImpl.setImmutable();
            return xmlPositiveIntegerImpl;
        } catch (XmlValueOutOfRangeException unused) {
            StscState.get().error("Internal error processing number", 21, (XmlObject) xmlAnySimpleType);
            return null;
        }
    }

    private static Object getUserData(XmlObject xmlObject) {
        XmlCursor.XmlBookmark bookmark = xmlObject.newCursor().getBookmark(SchemaBookmark.class);
        if (bookmark instanceof SchemaBookmark) {
            return ((SchemaBookmark) bookmark).getValue();
        }
        return null;
    }

    private static boolean isEmptySchema(SchemaDocument.Schema schema) {
        XmlCursor newCursor = schema.newCursor();
        boolean z = !newCursor.toFirstChild();
        newCursor.dispose();
        return z;
    }

    private static boolean isReservedTypeName(QName qName) {
        return BuiltinSchemaTypeSystem.get().findType(qName) != null;
    }
}
