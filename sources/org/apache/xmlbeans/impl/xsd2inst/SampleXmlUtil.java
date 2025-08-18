package org.apache.xmlbeans.impl.xsd2inst;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationBuilder;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDuration;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.schema.SoapEncSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

public class SampleXmlUtil {
    private static final String[] DNS1 = {"corp", "your", "my", "sample", "company", "test", Languages.ANY};
    private static final String[] DNS2 = {"com", "org", "com", "gov", "org", "com", "org", "com", "edu"};
    private static final QName ENC_ARRAYTYPE = new QName("http://schemas.xmlsoap.org/soap/encoding/", SoapEncSchemaTypeSystem.ARRAY_TYPE);
    private static final QName ENC_OFFSET;
    private static final QName HREF;
    private static final QName ID;
    private static final int MAX_ELEMENTS = 1000;
    private static final Set<QName> SKIPPED_SOAP_ATTRS;
    private static final String[] WORDS = {"ipsa", "iovis", "rapidum", "iaculata", "e", "nubibus", "ignem", "disiecitque", "rates", "evertitque", "aequora", "ventis", "illum", "exspirantem", "transfixo", "pectore", "flammas", "turbine", "corripuit", "scopuloque", "infixit", "acuto", "ast", "ego", "quae", "divum", "incedo", "regina", "iovisque", "et", "soror", "et", "coniunx", "una", "cum", "gente", "tot", "annos", "bella", "gero", "et", "quisquam", "numen", "iunonis", "adorat", "praeterea", "aut", "supplex", "aris", "imponet", "honorem", "talia", "flammato", "secum", "dea", "corde", "volutans", "nimborum", "in", "patriam", "loca", "feta", "furentibus", "austris", "aeoliam", "venit", "hic", "vasto", "rex", "aeolus", "antro", "luctantis", "ventos", "tempestatesque", "sonoras", "imperio", "premit", "ac", "vinclis", "et", "carcere", "frenat", "illi", "indignantes", "magno", "cum", "murmure", "montis", "circum", "claustra", "fremunt", "celsa", "sedet", "aeolus", "arce", "sceptra", "tenens", "mollitque", "animos", "et", "temperat", "iras", "ni", "faciat", "maria", "ac", "terras", "caelumque", "profundum", "quippe", "ferant", "rapidi", "secum", "verrantque", "per", "auras", "sed", "pater", "omnipotens", "speluncis", "abdidit", "atris", "hoc", "metuens", "molemque", "et", "montis", "insuper", "altos", "imposuit", "regemque", "dedit", "qui", "foedere", "certo", "et", "premere", "et", "laxas", "sciret", "dare", "iussus", "habenas"};
    private static final QName XSI_TYPE = new QName("http://www.w3.org/2001/XMLSchema-instance", "type");
    private int _nElements;
    Random _picker = new Random(1);
    private final boolean _soapEnc;
    private final ArrayList<SchemaType> _typeStack = new ArrayList<>();

    private SampleXmlUtil(boolean z) {
        this._soapEnc = z;
    }

    public static String createSampleForType(SchemaType schemaType) {
        XmlObject newInstance = XmlObject.Factory.newInstance();
        XmlCursor newCursor = newInstance.newCursor();
        newCursor.toNextToken();
        new SampleXmlUtil(false).createSampleForType(schemaType, newCursor);
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(2);
        xmlOptions.setSaveAggressiveNamespaces();
        return newInstance.xmlText(xmlOptions);
    }

    public static String createSampleForType(SchemaField schemaField) {
        SchemaType type = schemaField.getType();
        XmlObject newInstance = XmlObject.Factory.newInstance();
        XmlCursor newCursor = newInstance.newCursor();
        newCursor.toNextToken();
        new SampleXmlUtil(false).createSampleForType(type, newCursor);
        XmlOptions xmlOptions = new XmlOptions();
        xmlOptions.setSavePrettyPrint();
        xmlOptions.setSavePrettyPrintIndent(2);
        xmlOptions.setSaveAggressiveNamespaces();
        xmlOptions.setSaveSyntheticDocumentElement(schemaField.getName());
        return newInstance.xmlText(xmlOptions);
    }

    private void createSampleForType(SchemaType schemaType, XmlCursor xmlCursor) {
        if (!this._typeStack.contains(schemaType)) {
            this._typeStack.add(schemaType);
            try {
                if (!schemaType.isSimpleType()) {
                    if (!schemaType.isURType()) {
                        processAttributes(schemaType, xmlCursor);
                        int contentType = schemaType.getContentType();
                        if (contentType == 2) {
                            processSimpleType(schemaType, xmlCursor);
                        } else if (contentType != 3) {
                            if (contentType == 4) {
                                StringBuilder sb = new StringBuilder();
                                String[] strArr = WORDS;
                                xmlCursor.insertChars(sb.append(pick(strArr)).append(" ").toString());
                                if (schemaType.getContentModel() != null) {
                                    processParticle(schemaType.getContentModel(), xmlCursor, true);
                                }
                                xmlCursor.insertChars(pick(strArr));
                            }
                        } else if (schemaType.getContentModel() != null) {
                            processParticle(schemaType.getContentModel(), xmlCursor, false);
                        }
                        ArrayList<SchemaType> arrayList = this._typeStack;
                        arrayList.remove(arrayList.size() - 1);
                        return;
                    }
                }
                processSimpleType(schemaType, xmlCursor);
            } finally {
                ArrayList<SchemaType> arrayList2 = this._typeStack;
                arrayList2.remove(arrayList2.size() - 1);
            }
        }
    }

    private void processSimpleType(SchemaType schemaType, XmlCursor xmlCursor) {
        xmlCursor.insertChars(sampleDataForSimpleType(schemaType));
    }

    private String sampleDataForSimpleType(SchemaType schemaType) {
        if (XmlObject.type.equals(schemaType)) {
            return "anyType";
        }
        if (XmlAnySimpleType.type.equals(schemaType)) {
            return "anySimpleType";
        }
        if (schemaType.getSimpleVariety() == 3) {
            SchemaType listItemType = schemaType.getListItemType();
            StringBuilder sb = new StringBuilder();
            int pickLength = pickLength(schemaType);
            if (pickLength > 0) {
                sb.append(sampleDataForSimpleType(listItemType));
            }
            for (int i = 1; i < pickLength; i++) {
                sb.append(Chars.SPACE);
                sb.append(sampleDataForSimpleType(listItemType));
            }
            return sb.toString();
        } else if (schemaType.getSimpleVariety() == 2) {
            SchemaType[] unionConstituentTypes = schemaType.getUnionConstituentTypes();
            if (unionConstituentTypes.length == 0) {
                return "";
            }
            return sampleDataForSimpleType(unionConstituentTypes[pick(unionConstituentTypes.length)]);
        } else {
            XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
            if (enumerationValues != null && enumerationValues.length > 0) {
                return enumerationValues[pick(enumerationValues.length)].getStringValue();
            }
            switch (schemaType.getPrimitiveType().getBuiltinTypeCode()) {
                case 1:
                case 2:
                    return "anything";
                case 3:
                    return pick(2) == 0 ? "true" : "false";
                case 4:
                    return Base64.getEncoder().encodeToString(formatToLength(pick(WORDS), schemaType).getBytes(StandardCharsets.UTF_8));
                case 5:
                    return HexBin.encode(formatToLength(pick(WORDS), schemaType));
                case 6:
                    StringBuilder append = new StringBuilder("http://www.").append(pick(DNS1)).append(".").append(pick(DNS2)).append(PackagingURIHelper.FORWARD_SLASH_STRING);
                    String[] strArr = WORDS;
                    return formatToLength(append.append(pick(strArr)).append(PackagingURIHelper.FORWARD_SLASH_STRING).append(pick(strArr)).toString(), schemaType);
                case 7:
                    return formatToLength("qname", schemaType);
                case 8:
                    return formatToLength("notation", schemaType);
                case 9:
                    return "1.5E2";
                case 10:
                    return "1.051732E7";
                case 11:
                    switch (closestBuiltin(schemaType).getBuiltinTypeCode()) {
                        case 22:
                            return formatDecimal("100", schemaType);
                        case 23:
                            return formatDecimal("10", schemaType);
                        case 24:
                            return formatDecimal(ExifInterface.GPS_MEASUREMENT_3D, schemaType);
                        case 25:
                            return formatDecimal("1", schemaType);
                        case 26:
                            return formatDecimal(ExifInterface.GPS_MEASUREMENT_2D, schemaType);
                        case 27:
                            return formatDecimal("-200", schemaType);
                        case 28:
                            return formatDecimal("-201", schemaType);
                        case 29:
                            return formatDecimal("200", schemaType);
                        case 30:
                            return formatDecimal("201", schemaType);
                        case 31:
                            return formatDecimal("11", schemaType);
                        case 32:
                            return formatDecimal("7", schemaType);
                        case 33:
                            return formatDecimal("5", schemaType);
                        case 34:
                            return formatDecimal("6", schemaType);
                        default:
                            return formatDecimal("1000.00", schemaType);
                    }
                case 12:
                    return formatToLength(closestBuiltin(schemaType).getBuiltinTypeCode() == 36 ? "token" : TypedValues.Custom.S_STRING, schemaType);
                case 13:
                    return formatDuration(schemaType);
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    return formatDate(schemaType);
                default:
                    return "";
            }
        }
    }

    static {
        QName qName = new QName("href");
        HREF = qName;
        QName qName2 = new QName("id");
        ID = qName2;
        QName qName3 = new QName("http://schemas.xmlsoap.org/soap/encoding/", TypedValues.CycleType.S_WAVE_OFFSET);
        ENC_OFFSET = qName3;
        SKIPPED_SOAP_ATTRS = new HashSet(Arrays.asList(new QName[]{qName, qName2, qName3}));
    }

    private int pick(int i) {
        return this._picker.nextInt(i);
    }

    private String pick(String[] strArr) {
        return strArr[pick(strArr.length)];
    }

    private int pickLength(SchemaType schemaType) {
        int i;
        int i2 = 0;
        XmlInteger xmlInteger = (XmlInteger) schemaType.getFacet(0);
        if (xmlInteger != null) {
            return xmlInteger.getBigIntegerValue().intValue();
        }
        int i3 = 1;
        XmlInteger xmlInteger2 = (XmlInteger) schemaType.getFacet(1);
        XmlInteger xmlInteger3 = (XmlInteger) schemaType.getFacet(2);
        if (xmlInteger2 != null) {
            i2 = xmlInteger2.getBigIntegerValue().intValue();
        }
        if (xmlInteger3 == null) {
            i = Integer.MAX_VALUE;
        } else {
            i = xmlInteger3.getBigIntegerValue().intValue();
        }
        if (i2 != 0 || i < 1) {
            i3 = i2;
        }
        int i4 = i3 + 2;
        if (i > i4) {
            i = i4;
        }
        if (i < i3) {
            i = i3;
        }
        return i3 + pick(i - i3);
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        r4 = r0.getIntValue();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String formatToLength(java.lang.String r3, org.apache.xmlbeans.SchemaType r4) {
        /*
            r2 = this;
            r2 = 0
            org.apache.xmlbeans.XmlAnySimpleType r0 = r4.getFacet(r2)     // Catch:{ Exception -> 0x004e }
            org.apache.xmlbeans.SimpleValue r0 = (org.apache.xmlbeans.SimpleValue) r0     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0010
            r0 = 1
            org.apache.xmlbeans.XmlAnySimpleType r0 = r4.getFacet(r0)     // Catch:{ Exception -> 0x004e }
            org.apache.xmlbeans.SimpleValue r0 = (org.apache.xmlbeans.SimpleValue) r0     // Catch:{ Exception -> 0x004e }
        L_0x0010:
            if (r0 == 0) goto L_0x002e
            int r0 = r0.getIntValue()     // Catch:{ Exception -> 0x004e }
        L_0x0016:
            int r1 = r3.length()     // Catch:{ Exception -> 0x004e }
            if (r1 >= r0) goto L_0x002e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e }
            r1.<init>()     // Catch:{ Exception -> 0x004e }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x004e }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ Exception -> 0x004e }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x004e }
            goto L_0x0016
        L_0x002e:
            org.apache.xmlbeans.XmlAnySimpleType r0 = r4.getFacet(r2)     // Catch:{ Exception -> 0x004e }
            org.apache.xmlbeans.SimpleValue r0 = (org.apache.xmlbeans.SimpleValue) r0     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x003e
            r0 = 2
            org.apache.xmlbeans.XmlAnySimpleType r4 = r4.getFacet(r0)     // Catch:{ Exception -> 0x004e }
            r0 = r4
            org.apache.xmlbeans.SimpleValue r0 = (org.apache.xmlbeans.SimpleValue) r0     // Catch:{ Exception -> 0x004e }
        L_0x003e:
            if (r0 == 0) goto L_0x004e
            int r4 = r0.getIntValue()     // Catch:{ Exception -> 0x004e }
            int r0 = r3.length()     // Catch:{ Exception -> 0x004e }
            if (r0 <= r4) goto L_0x004e
            java.lang.String r3 = r3.substring(r2, r4)     // Catch:{ Exception -> 0x004e }
        L_0x004e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil.formatToLength(java.lang.String, org.apache.xmlbeans.SchemaType):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ad A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0104 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x012e A[LOOP:2: B:78:0x0124->B:80:0x012e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0148  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String formatDecimal(java.lang.String r12, org.apache.xmlbeans.SchemaType r13) {
        /*
            r11 = this;
            java.math.BigDecimal r11 = new java.math.BigDecimal
            r11.<init>(r12)
            r12 = 4
            org.apache.xmlbeans.XmlAnySimpleType r12 = r13.getFacet(r12)
            org.apache.xmlbeans.XmlDecimal r12 = (org.apache.xmlbeans.XmlDecimal) r12
            r0 = 0
            if (r12 == 0) goto L_0x0014
            java.math.BigDecimal r12 = r12.getBigDecimalValue()
            goto L_0x0015
        L_0x0014:
            r12 = r0
        L_0x0015:
            r1 = 5
            org.apache.xmlbeans.XmlAnySimpleType r1 = r13.getFacet(r1)
            org.apache.xmlbeans.XmlDecimal r1 = (org.apache.xmlbeans.XmlDecimal) r1
            if (r1 == 0) goto L_0x0022
            java.math.BigDecimal r0 = r1.getBigDecimalValue()
        L_0x0022:
            r1 = 3
            org.apache.xmlbeans.XmlAnySimpleType r1 = r13.getFacet(r1)
            org.apache.xmlbeans.XmlDecimal r1 = (org.apache.xmlbeans.XmlDecimal) r1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x003c
            java.math.BigDecimal r1 = r1.getBigDecimalValue()
            if (r12 == 0) goto L_0x0039
            int r4 = r12.compareTo(r1)
            if (r4 >= 0) goto L_0x003c
        L_0x0039:
            r12 = r1
            r1 = r2
            goto L_0x003d
        L_0x003c:
            r1 = r3
        L_0x003d:
            r4 = 6
            org.apache.xmlbeans.XmlAnySimpleType r4 = r13.getFacet(r4)
            org.apache.xmlbeans.XmlDecimal r4 = (org.apache.xmlbeans.XmlDecimal) r4
            if (r4 == 0) goto L_0x0055
            java.math.BigDecimal r4 = r4.getBigDecimalValue()
            if (r0 == 0) goto L_0x0052
            int r5 = r0.compareTo(r4)
            if (r5 <= 0) goto L_0x0055
        L_0x0052:
            r0 = r4
            r4 = r2
            goto L_0x0056
        L_0x0055:
            r4 = r3
        L_0x0056:
            r5 = 7
            org.apache.xmlbeans.XmlAnySimpleType r5 = r13.getFacet(r5)
            org.apache.xmlbeans.XmlDecimal r5 = (org.apache.xmlbeans.XmlDecimal) r5
            r6 = -1
            if (r5 == 0) goto L_0x009a
            java.math.BigDecimal r5 = r5.getBigDecimalValue()
            int r5 = r5.intValue()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            r8 = r2
        L_0x006e:
            if (r8 >= r5) goto L_0x0078
            r9 = 57
            r7.append(r9)
            int r8 = r8 + 1
            goto L_0x006e
        L_0x0078:
            java.math.BigDecimal r8 = new java.math.BigDecimal
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            if (r0 == 0) goto L_0x008b
            int r7 = r0.compareTo(r8)
            if (r7 <= 0) goto L_0x008b
            r4 = r3
            r0 = r8
        L_0x008b:
            java.math.BigDecimal r7 = r8.negate()
            if (r12 == 0) goto L_0x009b
            int r8 = r12.compareTo(r7)
            if (r8 >= 0) goto L_0x009b
            r1 = r3
            r12 = r7
            goto L_0x009b
        L_0x009a:
            r5 = r6
        L_0x009b:
            if (r12 != 0) goto L_0x009f
            r7 = r3
            goto L_0x00a3
        L_0x009f:
            int r7 = r11.compareTo(r12)
        L_0x00a3:
            if (r0 != 0) goto L_0x00a7
            r8 = r6
            goto L_0x00ab
        L_0x00a7:
            int r8 = r11.compareTo(r0)
        L_0x00ab:
            if (r7 > 0) goto L_0x00b4
            if (r7 != 0) goto L_0x00b2
            if (r1 == 0) goto L_0x00b2
            goto L_0x00b4
        L_0x00b2:
            r7 = r2
            goto L_0x00b5
        L_0x00b4:
            r7 = r3
        L_0x00b5:
            if (r8 < 0) goto L_0x00be
            if (r8 != 0) goto L_0x00bc
            if (r4 == 0) goto L_0x00bc
            goto L_0x00be
        L_0x00bc:
            r8 = r2
            goto L_0x00bf
        L_0x00be:
            r8 = r3
        L_0x00bf:
            r9 = 8
            org.apache.xmlbeans.XmlAnySimpleType r13 = r13.getFacet(r9)
            org.apache.xmlbeans.XmlDecimal r13 = (org.apache.xmlbeans.XmlDecimal) r13
            if (r13 != 0) goto L_0x00cf
            java.math.BigDecimal r13 = new java.math.BigDecimal
            r13.<init>(r3)
            goto L_0x00fd
        L_0x00cf:
            java.math.BigDecimal r13 = r13.getBigDecimalValue()
            int r6 = r13.intValue()
            if (r6 <= 0) goto L_0x00fb
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r9 = "0."
            r13.<init>(r9)
            r9 = r3
        L_0x00e1:
            if (r9 >= r6) goto L_0x00eb
            r10 = 48
            r13.append(r10)
            int r9 = r9 + 1
            goto L_0x00e1
        L_0x00eb:
            r9 = 49
            r13.append(r9)
            java.math.BigDecimal r9 = new java.math.BigDecimal
            java.lang.String r13 = r13.toString()
            r9.<init>(r13)
            r13 = r9
            goto L_0x00fd
        L_0x00fb:
            java.math.BigDecimal r13 = java.math.BigDecimal.ONE
        L_0x00fd:
            if (r7 == 0) goto L_0x0102
            if (r8 == 0) goto L_0x0102
            goto L_0x011b
        L_0x0102:
            if (r7 == 0) goto L_0x010f
            if (r8 != 0) goto L_0x010f
            if (r4 == 0) goto L_0x010a
            r11 = r0
            goto L_0x011b
        L_0x010a:
            java.math.BigDecimal r11 = r0.subtract(r13)
            goto L_0x011b
        L_0x010f:
            if (r7 != 0) goto L_0x011b
            if (r8 == 0) goto L_0x011b
            if (r1 == 0) goto L_0x0117
            r11 = r12
            goto L_0x011b
        L_0x0117:
            java.math.BigDecimal r11 = r12.add(r13)
        L_0x011b:
            java.math.BigDecimal r12 = new java.math.BigDecimal
            java.math.BigInteger r13 = java.math.BigInteger.ONE
            r12.<init>(r13)
            r13 = r11
            r0 = r2
        L_0x0124:
            java.math.BigDecimal r1 = r13.abs()
            int r1 = r1.compareTo(r12)
            if (r1 < 0) goto L_0x0135
            java.math.BigDecimal r13 = r13.movePointLeft(r3)
            int r0 = r0 + 1
            goto L_0x0124
        L_0x0135:
            if (r6 <= 0) goto L_0x0148
            if (r5 < 0) goto L_0x0143
            int r5 = r5 - r0
            int r12 = java.lang.Math.max(r6, r5)
            java.math.BigDecimal r11 = r11.setScale(r12)
            goto L_0x014e
        L_0x0143:
            java.math.BigDecimal r11 = r11.setScale(r6)
            goto L_0x014e
        L_0x0148:
            if (r6 != 0) goto L_0x014e
            java.math.BigDecimal r11 = r11.setScale(r2)
        L_0x014e:
            java.lang.String r11 = r11.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil.formatDecimal(java.lang.String, org.apache.xmlbeans.SchemaType):java.lang.String");
    }

    private String formatDuration(SchemaType schemaType) {
        XmlDuration xmlDuration = (XmlDuration) schemaType.getFacet(4);
        GDuration gDuration = null;
        GDuration gDurationValue = xmlDuration != null ? xmlDuration.getGDurationValue() : null;
        XmlDuration xmlDuration2 = (XmlDuration) schemaType.getFacet(5);
        GDuration gDurationValue2 = xmlDuration2 != null ? xmlDuration2.getGDurationValue() : null;
        XmlDuration xmlDuration3 = (XmlDuration) schemaType.getFacet(3);
        GDuration gDurationValue3 = xmlDuration3 != null ? xmlDuration3.getGDurationValue() : null;
        XmlDuration xmlDuration4 = (XmlDuration) schemaType.getFacet(6);
        if (xmlDuration4 != null) {
            gDuration = xmlDuration4.getGDurationValue();
        }
        GDurationBuilder gDurationBuilder = new GDurationBuilder();
        gDurationBuilder.setSecond(pick(800000));
        gDurationBuilder.setMonth(pick(20));
        if (gDurationValue != null) {
            if (gDurationBuilder.getYear() < gDurationValue.getYear()) {
                gDurationBuilder.setYear(gDurationValue.getYear());
            }
            if (gDurationBuilder.getMonth() < gDurationValue.getMonth()) {
                gDurationBuilder.setMonth(gDurationValue.getMonth());
            }
            if (gDurationBuilder.getDay() < gDurationValue.getDay()) {
                gDurationBuilder.setDay(gDurationValue.getDay());
            }
            if (gDurationBuilder.getHour() < gDurationValue.getHour()) {
                gDurationBuilder.setHour(gDurationValue.getHour());
            }
            if (gDurationBuilder.getMinute() < gDurationValue.getMinute()) {
                gDurationBuilder.setMinute(gDurationValue.getMinute());
            }
            if (gDurationBuilder.getSecond() < gDurationValue.getSecond()) {
                gDurationBuilder.setSecond(gDurationValue.getSecond());
            }
            if (gDurationBuilder.getFraction().compareTo(gDurationValue.getFraction()) < 0) {
                gDurationBuilder.setFraction(gDurationValue.getFraction());
            }
        }
        if (gDurationValue2 != null) {
            if (gDurationBuilder.getYear() > gDurationValue2.getYear()) {
                gDurationBuilder.setYear(gDurationValue2.getYear());
            }
            if (gDurationBuilder.getMonth() > gDurationValue2.getMonth()) {
                gDurationBuilder.setMonth(gDurationValue2.getMonth());
            }
            if (gDurationBuilder.getDay() > gDurationValue2.getDay()) {
                gDurationBuilder.setDay(gDurationValue2.getDay());
            }
            if (gDurationBuilder.getHour() > gDurationValue2.getHour()) {
                gDurationBuilder.setHour(gDurationValue2.getHour());
            }
            if (gDurationBuilder.getMinute() > gDurationValue2.getMinute()) {
                gDurationBuilder.setMinute(gDurationValue2.getMinute());
            }
            if (gDurationBuilder.getSecond() > gDurationValue2.getSecond()) {
                gDurationBuilder.setSecond(gDurationValue2.getSecond());
            }
            if (gDurationBuilder.getFraction().compareTo(gDurationValue2.getFraction()) > 0) {
                gDurationBuilder.setFraction(gDurationValue2.getFraction());
            }
        }
        if (gDurationValue3 != null) {
            if (gDurationBuilder.getYear() <= gDurationValue3.getYear()) {
                gDurationBuilder.setYear(gDurationValue3.getYear() + 1);
            }
            if (gDurationBuilder.getMonth() <= gDurationValue3.getMonth()) {
                gDurationBuilder.setMonth(gDurationValue3.getMonth() + 1);
            }
            if (gDurationBuilder.getDay() <= gDurationValue3.getDay()) {
                gDurationBuilder.setDay(gDurationValue3.getDay() + 1);
            }
            if (gDurationBuilder.getHour() <= gDurationValue3.getHour()) {
                gDurationBuilder.setHour(gDurationValue3.getHour() + 1);
            }
            if (gDurationBuilder.getMinute() <= gDurationValue3.getMinute()) {
                gDurationBuilder.setMinute(gDurationValue3.getMinute() + 1);
            }
            if (gDurationBuilder.getSecond() <= gDurationValue3.getSecond()) {
                gDurationBuilder.setSecond(gDurationValue3.getSecond() + 1);
            }
            if (gDurationBuilder.getFraction().compareTo(gDurationValue3.getFraction()) <= 0) {
                gDurationBuilder.setFraction(gDurationValue3.getFraction().add(new BigDecimal("0.001")));
            }
        }
        if (gDuration != null) {
            if (gDurationBuilder.getYear() > gDuration.getYear()) {
                gDurationBuilder.setYear(gDuration.getYear());
            }
            if (gDurationBuilder.getMonth() > gDuration.getMonth()) {
                gDurationBuilder.setMonth(gDuration.getMonth());
            }
            if (gDurationBuilder.getDay() > gDuration.getDay()) {
                gDurationBuilder.setDay(gDuration.getDay());
            }
            if (gDurationBuilder.getHour() > gDuration.getHour()) {
                gDurationBuilder.setHour(gDuration.getHour());
            }
            if (gDurationBuilder.getMinute() > gDuration.getMinute()) {
                gDurationBuilder.setMinute(gDuration.getMinute());
            }
            if (gDurationBuilder.getSecond() > gDuration.getSecond()) {
                gDurationBuilder.setSecond(gDuration.getSecond());
            }
            if (gDurationBuilder.getFraction().compareTo(gDuration.getFraction()) > 0) {
                gDurationBuilder.setFraction(gDuration.getFraction());
            }
        }
        gDurationBuilder.normalize();
        return gDurationBuilder.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:186:0x0343  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String formatDate(org.apache.xmlbeans.SchemaType r12) {
        /*
            r11 = this;
            org.apache.xmlbeans.GDateBuilder r0 = new org.apache.xmlbeans.GDateBuilder
            java.util.Date r1 = new java.util.Date
            r2 = 31536000(0x1e13380, float:8.2725845E-38)
            int r2 = r11.pick((int) r2)
            long r2 = (long) r2
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            r6 = 20
            int r6 = r11.pick((int) r6)
            long r6 = (long) r6
            r8 = 30
            long r6 = r6 + r8
            r8 = 365(0x16d, double:1.803E-321)
            long r6 = r6 * r8
            r8 = 24
            long r6 = r6 * r8
            r8 = 60
            long r6 = r6 * r8
            long r6 = r6 * r8
            long r6 = r6 * r4
            long r2 = r2 + r6
            r1.<init>(r2)
            r0.<init>((java.util.Date) r1)
            org.apache.xmlbeans.SchemaType r1 = r12.getPrimitiveType()
            int r1 = r1.getBuiltinTypeCode()
            r2 = 6
            r3 = 5
            r4 = 3
            r5 = 4
            r6 = 0
            switch(r1) {
                case 14: goto L_0x0251;
                case 15: goto L_0x0206;
                case 16: goto L_0x01ba;
                case 17: goto L_0x016e;
                case 18: goto L_0x0122;
                case 19: goto L_0x00d6;
                case 20: goto L_0x008a;
                case 21: goto L_0x003e;
                default: goto L_0x003b;
            }
        L_0x003b:
            r1 = r6
            goto L_0x029e
        L_0x003e:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlGMonth r1 = (org.apache.xmlbeans.XmlGMonth) r1
            if (r1 == 0) goto L_0x004b
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x004c
        L_0x004b:
            r1 = r6
        L_0x004c:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlGMonth r4 = (org.apache.xmlbeans.XmlGMonth) r4
            if (r4 == 0) goto L_0x0064
            if (r1 == 0) goto L_0x0060
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x0064
        L_0x0060:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x0064:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlGMonth r3 = (org.apache.xmlbeans.XmlGMonth) r3
            if (r3 == 0) goto L_0x0070
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x0070:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlGMonth r2 = (org.apache.xmlbeans.XmlGMonth) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x0084
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x0084:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x008a:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlGDay r1 = (org.apache.xmlbeans.XmlGDay) r1
            if (r1 == 0) goto L_0x0097
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x0098
        L_0x0097:
            r1 = r6
        L_0x0098:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlGDay r4 = (org.apache.xmlbeans.XmlGDay) r4
            if (r4 == 0) goto L_0x00b0
            if (r1 == 0) goto L_0x00ac
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x00b0
        L_0x00ac:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x00b0:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlGDay r3 = (org.apache.xmlbeans.XmlGDay) r3
            if (r3 == 0) goto L_0x00bc
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x00bc:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlGDay r2 = (org.apache.xmlbeans.XmlGDay) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x00d0
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x00d0:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x00d6:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlGMonthDay r1 = (org.apache.xmlbeans.XmlGMonthDay) r1
            if (r1 == 0) goto L_0x00e3
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x00e4
        L_0x00e3:
            r1 = r6
        L_0x00e4:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlGMonthDay r4 = (org.apache.xmlbeans.XmlGMonthDay) r4
            if (r4 == 0) goto L_0x00fc
            if (r1 == 0) goto L_0x00f8
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x00fc
        L_0x00f8:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x00fc:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlGMonthDay r3 = (org.apache.xmlbeans.XmlGMonthDay) r3
            if (r3 == 0) goto L_0x0108
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x0108:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlGMonthDay r2 = (org.apache.xmlbeans.XmlGMonthDay) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x011c
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x011c:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x0122:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlGYear r1 = (org.apache.xmlbeans.XmlGYear) r1
            if (r1 == 0) goto L_0x012f
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x0130
        L_0x012f:
            r1 = r6
        L_0x0130:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlGYear r4 = (org.apache.xmlbeans.XmlGYear) r4
            if (r4 == 0) goto L_0x0148
            if (r1 == 0) goto L_0x0144
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x0148
        L_0x0144:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x0148:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlGYear r3 = (org.apache.xmlbeans.XmlGYear) r3
            if (r3 == 0) goto L_0x0154
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x0154:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlGYear r2 = (org.apache.xmlbeans.XmlGYear) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x0168
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x0168:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x016e:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlGYearMonth r1 = (org.apache.xmlbeans.XmlGYearMonth) r1
            if (r1 == 0) goto L_0x017b
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x017c
        L_0x017b:
            r1 = r6
        L_0x017c:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlGYearMonth r4 = (org.apache.xmlbeans.XmlGYearMonth) r4
            if (r4 == 0) goto L_0x0194
            if (r1 == 0) goto L_0x0190
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x0194
        L_0x0190:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x0194:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlGYearMonth r3 = (org.apache.xmlbeans.XmlGYearMonth) r3
            if (r3 == 0) goto L_0x01a0
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x01a0:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlGYearMonth r2 = (org.apache.xmlbeans.XmlGYearMonth) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x01b4
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x01b4:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x01ba:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlDate r1 = (org.apache.xmlbeans.XmlDate) r1
            if (r1 == 0) goto L_0x01c7
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x01c8
        L_0x01c7:
            r1 = r6
        L_0x01c8:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlDate r4 = (org.apache.xmlbeans.XmlDate) r4
            if (r4 == 0) goto L_0x01e0
            if (r1 == 0) goto L_0x01dc
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x01e0
        L_0x01dc:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x01e0:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlDate r3 = (org.apache.xmlbeans.XmlDate) r3
            if (r3 == 0) goto L_0x01ec
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x01ec:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlDate r2 = (org.apache.xmlbeans.XmlDate) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x0200
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x0200:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x0206:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlTime r1 = (org.apache.xmlbeans.XmlTime) r1
            if (r1 == 0) goto L_0x0213
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x0214
        L_0x0213:
            r1 = r6
        L_0x0214:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlTime r4 = (org.apache.xmlbeans.XmlTime) r4
            if (r4 == 0) goto L_0x022c
            if (r1 == 0) goto L_0x0228
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x022c
        L_0x0228:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x022c:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlTime r3 = (org.apache.xmlbeans.XmlTime) r3
            if (r3 == 0) goto L_0x0238
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x0238:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlTime r2 = (org.apache.xmlbeans.XmlTime) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x024c
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x024c:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
            goto L_0x029b
        L_0x0251:
            org.apache.xmlbeans.XmlAnySimpleType r1 = r12.getFacet(r5)
            org.apache.xmlbeans.XmlDateTime r1 = (org.apache.xmlbeans.XmlDateTime) r1
            if (r1 == 0) goto L_0x025e
            org.apache.xmlbeans.GDate r1 = r1.getGDateValue()
            goto L_0x025f
        L_0x025e:
            r1 = r6
        L_0x025f:
            org.apache.xmlbeans.XmlAnySimpleType r4 = r12.getFacet(r4)
            org.apache.xmlbeans.XmlDateTime r4 = (org.apache.xmlbeans.XmlDateTime) r4
            if (r4 == 0) goto L_0x0277
            if (r1 == 0) goto L_0x0273
            org.apache.xmlbeans.GDate r5 = r4.getGDateValue()
            int r5 = r1.compareToGDate(r5)
            if (r5 > 0) goto L_0x0277
        L_0x0273:
            org.apache.xmlbeans.GDate r1 = r4.getGDateValue()
        L_0x0277:
            org.apache.xmlbeans.XmlAnySimpleType r3 = r12.getFacet(r3)
            org.apache.xmlbeans.XmlDateTime r3 = (org.apache.xmlbeans.XmlDateTime) r3
            if (r3 == 0) goto L_0x0283
            org.apache.xmlbeans.GDate r6 = r3.getGDateValue()
        L_0x0283:
            org.apache.xmlbeans.XmlAnySimpleType r2 = r12.getFacet(r2)
            org.apache.xmlbeans.XmlDateTime r2 = (org.apache.xmlbeans.XmlDateTime) r2
            if (r2 == 0) goto L_0x029b
            if (r6 == 0) goto L_0x0297
            org.apache.xmlbeans.GDate r3 = r2.getGDateValue()
            int r3 = r6.compareToGDate(r3)
            if (r3 < 0) goto L_0x029b
        L_0x0297:
            org.apache.xmlbeans.GDate r6 = r2.getGDateValue()
        L_0x029b:
            r10 = r6
            r6 = r1
            r1 = r10
        L_0x029e:
            r2 = 8
            r3 = 11
            if (r6 == 0) goto L_0x02bf
            if (r1 != 0) goto L_0x02bf
            int r1 = r6.compareToGDate(r0)
            if (r1 < 0) goto L_0x0331
            org.apache.xmlbeans.XmlCalendar r0 = r0.getCalendar()
            int r1 = r11.pick((int) r2)
            r0.add(r3, r1)
            org.apache.xmlbeans.GDateBuilder r1 = new org.apache.xmlbeans.GDateBuilder
            r1.<init>((java.util.Calendar) r0)
        L_0x02bc:
            r0 = r1
            goto L_0x0331
        L_0x02bf:
            if (r6 != 0) goto L_0x02db
            if (r1 == 0) goto L_0x02db
            int r1 = r1.compareToGDate(r0)
            if (r1 > 0) goto L_0x0331
            org.apache.xmlbeans.XmlCalendar r0 = r0.getCalendar()
            int r1 = r11.pick((int) r2)
            int r1 = -r1
            r0.add(r3, r1)
            org.apache.xmlbeans.GDateBuilder r1 = new org.apache.xmlbeans.GDateBuilder
            r1.<init>((java.util.Calendar) r0)
            goto L_0x02bc
        L_0x02db:
            if (r6 == 0) goto L_0x0331
            if (r1 == 0) goto L_0x0331
            int r2 = r6.compareToGDate(r0)
            if (r2 >= 0) goto L_0x02eb
            int r2 = r1.compareToGDate(r0)
            if (r2 > 0) goto L_0x0331
        L_0x02eb:
            org.apache.xmlbeans.XmlCalendar r0 = r6.getCalendar()
            org.apache.xmlbeans.XmlCalendar r1 = r1.getCalendar()
            r2 = 1
            r0.add(r3, r2)
            boolean r4 = r0.after(r1)
            if (r4 == 0) goto L_0x032b
            r4 = -1
            r0.add(r3, r4)
            r3 = 12
            r0.add(r3, r2)
            boolean r5 = r0.after(r1)
            if (r5 == 0) goto L_0x032b
            r0.add(r3, r4)
            r3 = 13
            r0.add(r3, r2)
            boolean r5 = r0.after(r1)
            if (r5 == 0) goto L_0x032b
            r0.add(r3, r4)
            r3 = 14
            r0.add(r3, r2)
            boolean r1 = r0.after(r1)
            if (r1 == 0) goto L_0x032b
            r0.add(r3, r4)
        L_0x032b:
            org.apache.xmlbeans.GDateBuilder r1 = new org.apache.xmlbeans.GDateBuilder
            r1.<init>((java.util.Calendar) r0)
            goto L_0x02bc
        L_0x0331:
            org.apache.xmlbeans.SchemaType r12 = r12.getPrimitiveType()
            int r12 = r12.getBuiltinTypeCode()
            r0.setBuiltinTypeCode(r12)
            r12 = 2
            int r11 = r11.pick((int) r12)
            if (r11 != 0) goto L_0x0346
            r0.clearTimeZone()
        L_0x0346:
            java.lang.String r11 = r0.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil.formatDate(org.apache.xmlbeans.SchemaType):java.lang.String");
    }

    private SchemaType closestBuiltin(SchemaType schemaType) {
        while (!schemaType.isBuiltinType()) {
            schemaType = schemaType.getBaseType();
        }
        return schemaType;
    }

    public static QName crackQName(String str) {
        String str2;
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf >= 0) {
            str2 = str.substring(0, lastIndexOf);
            str = str.substring(lastIndexOf + 1);
        } else {
            str2 = "";
        }
        return new QName(str2, str);
    }

    private void processParticle(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        int determineMinMaxForSample = determineMinMaxForSample(schemaParticle, xmlCursor);
        while (true) {
            int i = determineMinMaxForSample - 1;
            if (determineMinMaxForSample > 0) {
                int particleType = schemaParticle.getParticleType();
                if (particleType == 1) {
                    processAll(schemaParticle, xmlCursor, z);
                } else if (particleType == 2) {
                    processChoice(schemaParticle, xmlCursor, z);
                } else if (particleType == 3) {
                    processSequence(schemaParticle, xmlCursor, z);
                } else if (particleType == 4) {
                    processElement(schemaParticle, xmlCursor, z);
                } else if (particleType == 5) {
                    processWildCard(schemaParticle, xmlCursor, z);
                }
                determineMinMaxForSample = i;
            } else {
                return;
            }
        }
    }

    private int determineMinMaxForSample(SchemaParticle schemaParticle, XmlCursor xmlCursor) {
        int intMinOccurs = schemaParticle.getIntMinOccurs();
        if (intMinOccurs == schemaParticle.getIntMaxOccurs()) {
            return intMinOccurs;
        }
        int i = (intMinOccurs != 0 || this._nElements >= 1000) ? intMinOccurs : 1;
        if (schemaParticle.getParticleType() != 4) {
            return i;
        }
        if (schemaParticle.getMaxOccurs() == null) {
            if (intMinOccurs == 0) {
                xmlCursor.insertComment("Zero or more repetitions:");
            } else {
                xmlCursor.insertComment(intMinOccurs + " or more repetitions:");
            }
        } else if (schemaParticle.getIntMaxOccurs() > 1) {
            xmlCursor.insertComment(intMinOccurs + " to " + schemaParticle.getMaxOccurs() + " repetitions:");
        } else {
            xmlCursor.insertComment("Optional:");
        }
        return i;
    }

    private void processElement(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        SchemaLocalElement schemaLocalElement = (SchemaLocalElement) schemaParticle;
        if (this._soapEnc) {
            xmlCursor.insertElement(schemaLocalElement.getName().getLocalPart());
        } else {
            xmlCursor.insertElement(schemaLocalElement.getName().getLocalPart(), schemaLocalElement.getName().getNamespaceURI());
        }
        this._nElements++;
        xmlCursor.toPrevToken();
        createSampleForType(schemaLocalElement.getType(), xmlCursor);
        xmlCursor.toNextToken();
    }

    private static String formatQName(XmlCursor xmlCursor, QName qName) {
        XmlCursor newCursor = xmlCursor.newCursor();
        newCursor.toParent();
        String prefixForNamespace = newCursor.prefixForNamespace(qName.getNamespaceURI());
        newCursor.dispose();
        if (prefixForNamespace == null || prefixForNamespace.length() == 0) {
            return qName.getLocalPart();
        }
        return prefixForNamespace + ParameterizedMessage.ERROR_MSG_SEPARATOR + qName.getLocalPart();
    }

    private void processAttributes(SchemaType schemaType, XmlCursor xmlCursor) {
        QName name;
        if (this._soapEnc && (name = schemaType.getName()) != null) {
            xmlCursor.insertAttributeWithValue(XSI_TYPE, formatQName(xmlCursor, name));
        }
        for (SchemaProperty schemaProperty : schemaType.getAttributeProperties()) {
            if (this._soapEnc) {
                if (!SKIPPED_SOAP_ATTRS.contains(schemaProperty.getName())) {
                    if (ENC_ARRAYTYPE.equals(schemaProperty.getName())) {
                        SOAPArrayType wSDLArrayType = ((SchemaWSDLArrayType) schemaType.getAttributeModel().getAttribute(schemaProperty.getName())).getWSDLArrayType();
                        if (wSDLArrayType != null) {
                            xmlCursor.insertAttributeWithValue(schemaProperty.getName(), formatQName(xmlCursor, wSDLArrayType.getQName()) + wSDLArrayType.soap11DimensionString());
                        }
                    }
                }
            }
            String defaultText = schemaProperty.getDefaultText();
            QName name2 = schemaProperty.getName();
            if (defaultText == null) {
                defaultText = sampleDataForSimpleType(schemaProperty.getType());
            }
            xmlCursor.insertAttributeWithValue(name2, defaultText);
        }
    }

    private void processSequence(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        SchemaParticle[] particleChildren = schemaParticle.getParticleChildren();
        for (int i = 0; i < particleChildren.length; i++) {
            processParticle(particleChildren[i], xmlCursor, z);
            if (z && i < particleChildren.length - 1) {
                xmlCursor.insertChars(pick(WORDS));
            }
        }
    }

    private void processChoice(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        SchemaParticle[] particleChildren = schemaParticle.getParticleChildren();
        xmlCursor.insertComment("You have a CHOICE of the next " + particleChildren.length + " items at this level");
        for (SchemaParticle processParticle : particleChildren) {
            processParticle(processParticle, xmlCursor, z);
        }
    }

    private void processAll(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        SchemaParticle[] particleChildren = schemaParticle.getParticleChildren();
        for (int i = 0; i < particleChildren.length; i++) {
            processParticle(particleChildren[i], xmlCursor, z);
            if (z && i < particleChildren.length - 1) {
                xmlCursor.insertChars(pick(WORDS));
            }
        }
    }

    private void processWildCard(SchemaParticle schemaParticle, XmlCursor xmlCursor, boolean z) {
        xmlCursor.insertComment("You may enter ANY elements at this point");
        xmlCursor.insertElement("AnyElement");
    }
}
