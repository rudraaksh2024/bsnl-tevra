package org.apache.commons.compress.harmony.unpack200;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

public class ClassBands extends BandSet {
    private final AttributeLayoutMap attrMap;
    private long[] classAccessFlags;
    private ArrayList[] classAttributes;
    private final int classCount = this.header.getClassCount();
    private int[] classFieldCount;
    private long[] classFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private List[] codeAttributes;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private int[] codeHandlerCount;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerStartP;
    private boolean[] codeHasAttributes;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private final CpBands cpBands;
    private long[][] fieldAccessFlags;
    private ArrayList[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private IcTuple[][] icLocal;
    private long[][] methodAccessFlags;
    private int[] methodAttrCalls;
    private ArrayList[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private final SegmentOptions options = this.header.getOptions();

    public void unpack() {
    }

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        int classCount2 = this.header.getClassCount();
        int[] decodeBandInt = decodeBandInt("class_this", inputStream, Codec.DELTA5, classCount2);
        this.classThisInts = decodeBandInt;
        this.classThis = getReferences(decodeBandInt, this.cpBands.getCpClass());
        this.classSuperInts = decodeBandInt("class_super", inputStream, Codec.DELTA5, classCount2);
        this.classInterfacesInts = decodeBandInt("class_interface", inputStream, Codec.DELTA5, decodeBandInt("class_interface_count", inputStream, Codec.DELTA5, classCount2));
        this.classFieldCount = decodeBandInt("class_field_count", inputStream, Codec.DELTA5, classCount2);
        this.classMethodCount = decodeBandInt("class_method_count", inputStream, Codec.DELTA5, classCount2);
        parseFieldBands(inputStream);
        parseMethodBands(inputStream);
        parseClassAttrBands(inputStream);
        parseCodeBands(inputStream);
    }

    private void parseFieldBands(InputStream inputStream) throws IOException, Pack200Exception {
        int[][] decodeBandInt = decodeBandInt("field_descr", inputStream, Codec.DELTA5, this.classFieldCount);
        this.fieldDescrInts = decodeBandInt;
        this.fieldDescr = getReferences(decodeBandInt, this.cpBands.getCpDescriptor());
        parseFieldAttrBands(inputStream);
    }

    private void parseFieldAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        AttributeLayout attributeLayout;
        int[] iArr;
        AttributeLayout attributeLayout2;
        InputStream inputStream2 = inputStream;
        long[][] parseFlags = parseFlags("field_flags", inputStream, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        this.fieldFlags = parseFlags;
        int[] decodeBandInt = decodeBandInt("field_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("field_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("field_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), this.fieldFlags, 1));
        this.fieldAttributes = new ArrayList[this.classCount][];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.fieldAttributes[i2] = new ArrayList[this.fieldFlags[i2].length];
            for (int i3 = 0; i3 < this.fieldFlags[i2].length; i3++) {
                this.fieldAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1);
        int[] decodeBandInt2 = decodeBandInt("field_ConstantValue_KQ", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 1);
        int[] decodeBandInt3 = decodeBandInt("field_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout4));
        AttributeLayout attributeLayout5 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.classCount) {
            int i7 = 0;
            while (true) {
                long[] jArr = this.fieldFlags[i4];
                if (i7 >= jArr.length) {
                    break;
                }
                long j = jArr[i7];
                if (attributeLayout5.matches(j)) {
                    this.fieldAttributes[i4][i7].add(new DeprecatedAttribute());
                }
                if (attributeLayout3.matches(j)) {
                    AttributeLayout attributeLayout6 = attributeLayout3;
                    long j2 = (long) decodeBandInt2[i5];
                    String str = this.fieldDescr[i4][i7];
                    iArr = decodeBandInt2;
                    attributeLayout = attributeLayout5;
                    String substring = str.substring(str.indexOf(58) + 1);
                    if (substring.equals("B") || substring.equals(ExifInterface.LATITUDE_SOUTH) || substring.equals("C") || substring.equals("Z")) {
                        substring = "I";
                    }
                    attributeLayout2 = attributeLayout6;
                    this.fieldAttributes[i4][i7].add(new ConstantValueAttribute(attributeLayout2.getValue(j2, substring, this.cpBands.getConstantPool())));
                    i5++;
                } else {
                    iArr = decodeBandInt2;
                    attributeLayout = attributeLayout5;
                    attributeLayout2 = attributeLayout3;
                }
                if (attributeLayout4.matches(j)) {
                    String str2 = this.fieldDescr[i4][i7];
                    this.fieldAttributes[i4][i7].add(new SignatureAttribute((CPUTF8) attributeLayout4.getValue((long) decodeBandInt3[i6], str2.substring(str2.indexOf(58) + 1), this.cpBands.getConstantPool())));
                    i6++;
                }
                i7++;
                attributeLayout3 = attributeLayout2;
                decodeBandInt2 = iArr;
                attributeLayout5 = attributeLayout;
            }
            int[] iArr2 = decodeBandInt2;
            AttributeLayout attributeLayout7 = attributeLayout5;
            AttributeLayout attributeLayout8 = attributeLayout3;
            i4++;
            attributeLayout5 = attributeLayout7;
        }
        int parseFieldMetadataBands = parseFieldMetadataBands(inputStream2, decodeBandInt);
        int i8 = this.options.hasFieldFlagsHi() ? 62 : 31;
        int i9 = i8 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i9];
        int[] iArr3 = new int[i9];
        List[] listArr = new List[i9];
        for (int i10 = 0; i10 < i8; i10++) {
            AttributeLayout attributeLayout9 = this.attrMap.getAttributeLayout(i10, 1);
            if (attributeLayout9 != null && !attributeLayout9.isDefaultLayout()) {
                attributeLayoutArr[i10] = attributeLayout9;
                iArr3[i10] = SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout9);
            }
        }
        for (int i11 = 0; i11 < i9; i11++) {
            if (iArr3[i11] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i11]);
                listArr[i11] = attributeBands.parseAttributes(inputStream2, iArr3[i11]);
                int numBackwardsCallables = attributeLayoutArr[i11].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr4 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt, parseFieldMetadataBands, iArr4, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr4);
                    parseFieldMetadataBands += numBackwardsCallables;
                }
            }
        }
        for (int i12 = 0; i12 < this.classCount; i12++) {
            int i13 = 0;
            while (true) {
                long[] jArr2 = this.fieldFlags[i12];
                if (i13 >= jArr2.length) {
                    break;
                }
                long j3 = jArr2[i13];
                int i14 = 0;
                for (int i15 = 0; i15 < i9; i15++) {
                    AttributeLayout attributeLayout10 = attributeLayoutArr[i15];
                    if (attributeLayout10 != null && attributeLayout10.matches(j3)) {
                        if (attributeLayoutArr[i15].getIndex() < 15) {
                            i = 0;
                            this.fieldAttributes[i12][i13].add(i14, listArr[i15].get(0));
                            i14++;
                        } else {
                            i = 0;
                            this.fieldAttributes[i12][i13].add(listArr[i15].get(0));
                        }
                        listArr[i15].remove(i);
                    }
                }
                i13++;
            }
        }
    }

    private void parseMethodBands(InputStream inputStream) throws IOException, Pack200Exception {
        int[][] decodeBandInt = decodeBandInt("method_descr", inputStream, Codec.MDELTA5, this.classMethodCount);
        this.methodDescrInts = decodeBandInt;
        this.methodDescr = getReferences(decodeBandInt, this.cpBands.getCpDescriptor());
        parseMethodAttrBands(inputStream);
    }

    private void parseMethodAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        int[] iArr;
        AttributeLayout attributeLayout;
        InputStream inputStream2 = inputStream;
        long[][] parseFlags = parseFlags("method_flags", inputStream, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        this.methodFlags = parseFlags;
        this.methodAttrCalls = decodeBandInt("method_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("method_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("method_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), this.methodFlags, 2));
        this.methodAttributes = new ArrayList[this.classCount][];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.methodAttributes[i2] = new ArrayList[this.methodFlags[i2].length];
            for (int i3 = 0; i3 < this.methodFlags[i2].length; i3++) {
                this.methodAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2);
        int[] decodeBandInt = decodeBandInt("method_Exceptions_n", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout2));
        int[][] decodeBandInt2 = decodeBandInt("method_Exceptions_RC", inputStream2, Codec.UNSIGNED5, decodeBandInt);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 2);
        int[] decodeBandInt3 = decodeBandInt("method_signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2);
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.methodAttributes.length; i6++) {
            int i7 = 0;
            while (i7 < this.methodAttributes[i6].length) {
                long j = this.methodFlags[i6][i7];
                if (attributeLayout2.matches(j)) {
                    int i8 = decodeBandInt[i4];
                    int[] iArr2 = decodeBandInt2[i4];
                    CPClass[] cPClassArr = new CPClass[i8];
                    attributeLayout = attributeLayout2;
                    int i9 = 0;
                    while (i9 < i8) {
                        cPClassArr[i9] = this.cpBands.cpClassValue(iArr2[i9]);
                        i9++;
                        i8 = i8;
                        decodeBandInt = decodeBandInt;
                    }
                    iArr = decodeBandInt;
                    this.methodAttributes[i6][i7].add(new ExceptionsAttribute(cPClassArr));
                    i4++;
                } else {
                    attributeLayout = attributeLayout2;
                    iArr = decodeBandInt;
                }
                if (attributeLayout3.matches(j)) {
                    long j2 = (long) decodeBandInt3[i5];
                    String str = this.methodDescr[i6][i7];
                    String substring = str.substring(str.indexOf(58) + 1);
                    if (substring.equals("B") || substring.equals("H")) {
                        substring = "I";
                    }
                    this.methodAttributes[i6][i7].add(new SignatureAttribute((CPUTF8) attributeLayout3.getValue(j2, substring, this.cpBands.getConstantPool())));
                    i5++;
                }
                if (attributeLayout4.matches(j)) {
                    this.methodAttributes[i6][i7].add(new DeprecatedAttribute());
                }
                i7++;
                attributeLayout2 = attributeLayout;
                decodeBandInt = iArr;
            }
            AttributeLayout attributeLayout5 = attributeLayout2;
            int[] iArr3 = decodeBandInt;
        }
        int parseMethodMetadataBands = parseMethodMetadataBands(inputStream2, this.methodAttrCalls);
        int i10 = this.options.hasMethodFlagsHi() ? 62 : 31;
        int i11 = i10 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i11];
        int[] iArr4 = new int[i11];
        List[] listArr = new List[i11];
        for (int i12 = 0; i12 < i10; i12++) {
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i12, 2);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i12] = attributeLayout6;
                iArr4[i12] = SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout6);
            }
        }
        for (int i13 = 0; i13 < i11; i13++) {
            if (iArr4[i13] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i13]);
                listArr[i13] = attributeBands.parseAttributes(inputStream2, iArr4[i13]);
                int numBackwardsCallables = attributeLayoutArr[i13].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr5 = new int[numBackwardsCallables];
                    System.arraycopy(this.methodAttrCalls, parseMethodMetadataBands, iArr5, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr5);
                    parseMethodMetadataBands += numBackwardsCallables;
                }
            }
        }
        for (int i14 = 0; i14 < this.methodAttributes.length; i14++) {
            for (int i15 = 0; i15 < this.methodAttributes[i14].length; i15++) {
                long j3 = this.methodFlags[i14][i15];
                int i16 = 0;
                for (int i17 = 0; i17 < i11; i17++) {
                    AttributeLayout attributeLayout7 = attributeLayoutArr[i17];
                    if (attributeLayout7 != null && attributeLayout7.matches(j3)) {
                        if (attributeLayoutArr[i17].getIndex() < 15) {
                            i = 0;
                            this.methodAttributes[i14][i15].add(i16, listArr[i17].get(0));
                            i16++;
                        } else {
                            i = 0;
                            this.methodAttributes[i14][i15].add(listArr[i17].get(0));
                        }
                        listArr[i17].remove(i);
                    }
                }
            }
        }
    }

    private int getCallCount(int[][] iArr, long[][] jArr, int i) throws Pack200Exception {
        int i2 = 0;
        for (int[] iArr2 : iArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= iArr2.length) {
                    break;
                }
                i2 += this.attrMap.getAttributeLayout(iArr2[i3], i).numBackwardsCallables();
                i3++;
            }
        }
        int i4 = 0;
        for (long[] jArr2 : jArr) {
            int i5 = 0;
            while (true) {
                if (i5 >= jArr2.length) {
                    break;
                }
                i4 = (int) (((long) i4) | jArr2[i5]);
                i5++;
            }
        }
        for (int i6 = 0; i6 < 26; i6++) {
            if (((1 << i6) & i4) != 0) {
                i2 += this.attrMap.getAttributeLayout(i6, i).numBackwardsCallables();
            }
        }
        return i2;
    }

    private void parseClassAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        List[] listArr;
        AttributeLayout attributeLayout;
        int[] iArr;
        AttributeLayout attributeLayout2;
        int i2;
        AttributeLayout attributeLayout3;
        int i3;
        int i4;
        int i5;
        AttributeLayout attributeLayout4;
        AttributeLayout attributeLayout5;
        int i6;
        AttributeLayout attributeLayout6;
        int i7;
        int i8;
        int i9;
        int i10;
        String str;
        String str2;
        int i11;
        int i12;
        int[] iArr2;
        int i13;
        int[][] iArr3;
        InputStream inputStream2 = inputStream;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        int i14 = 0;
        while (true) {
            i = this.classCount;
            if (i14 >= i) {
                break;
            }
            this.classAttributes[i14] = new ArrayList();
            i14++;
        }
        long[] parseFlags = parseFlags("class_flags", inputStream, i, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        this.classFlags = parseFlags;
        int[] decodeBandInt = decodeBandInt("class_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("class_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("class_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), new long[][]{this.classFlags}, 0));
        AttributeLayout attributeLayout7 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0);
        AttributeLayout attributeLayout8 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0);
        int[] decodeBandInt2 = decodeBandInt("class_SourceFile_RUN", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout8));
        AttributeLayout attributeLayout9 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0);
        int countMatches = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout9);
        int[] decodeBandInt3 = decodeBandInt("class_EnclosingMethod_RC", inputStream2, Codec.UNSIGNED5, countMatches);
        int[] decodeBandInt4 = decodeBandInt("class_EnclosingMethod_RDN", inputStream2, Codec.UNSIGNED5, countMatches);
        AttributeLayout attributeLayout10 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 0);
        int[] decodeBandInt5 = decodeBandInt("class_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout10));
        int parseClassMetadataBands = parseClassMetadataBands(inputStream2, decodeBandInt);
        AttributeLayout attributeLayout11 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0);
        String[] strArr = cpUTF8;
        int[] decodeBandInt6 = decodeBandInt("class_InnerClasses_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout11));
        int[][] decodeBandInt7 = decodeBandInt("class_InnerClasses_RC", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        String[] strArr2 = cpClass;
        int[][] decodeBandInt8 = decodeBandInt("class_InnerClasses_F", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[] iArr4 = decodeBandInt6;
        int[][] iArr5 = decodeBandInt7;
        int i15 = 0;
        int i16 = 0;
        while (i16 < decodeBandInt8.length) {
            AttributeLayout attributeLayout12 = attributeLayout11;
            int i17 = 0;
            while (true) {
                int[] iArr6 = decodeBandInt8[i16];
                iArr3 = decodeBandInt8;
                if (i17 >= iArr6.length) {
                    break;
                }
                if (iArr6[i17] != 0) {
                    i15++;
                }
                i17++;
                decodeBandInt8 = iArr3;
            }
            i16++;
            attributeLayout11 = attributeLayout12;
            decodeBandInt8 = iArr3;
        }
        int[][] iArr7 = decodeBandInt8;
        AttributeLayout attributeLayout13 = attributeLayout11;
        int[] decodeBandInt9 = decodeBandInt("class_InnerClasses_outer_RCN", inputStream2, Codec.UNSIGNED5, i15);
        int[] decodeBandInt10 = decodeBandInt("class_InnerClasses_name_RUN", inputStream2, Codec.UNSIGNED5, i15);
        AttributeLayout attributeLayout14 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0);
        int countMatches2 = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout14);
        AttributeLayout attributeLayout15 = attributeLayout14;
        int[] decodeBandInt11 = decodeBandInt("class_file_version_minor_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        int[] decodeBandInt12 = decodeBandInt("class_file_version_major_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        if (countMatches2 > 0) {
            int i18 = this.classCount;
            this.classVersionMajor = new int[i18];
            this.classVersionMinor = new int[i18];
        }
        int defaultClassMajorVersion = this.header.getDefaultClassMajorVersion();
        int defaultClassMinorVersion = this.header.getDefaultClassMinorVersion();
        int i19 = this.options.hasClassFlagsHi() ? 62 : 31;
        int i20 = defaultClassMajorVersion;
        int i21 = i19 + 1;
        int[] iArr8 = decodeBandInt12;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i21];
        int[] iArr9 = decodeBandInt10;
        int[] iArr10 = new int[i21];
        int[] iArr11 = decodeBandInt9;
        List[] listArr2 = new List[i21];
        int[] iArr12 = decodeBandInt5;
        int i22 = 0;
        while (i22 < i19) {
            int i23 = i19;
            AttributeLayout attributeLayout16 = attributeLayout10;
            AttributeLayout attributeLayout17 = this.attrMap.getAttributeLayout(i22, 0);
            if (attributeLayout17 != null && !attributeLayout17.isDefaultLayout()) {
                attributeLayoutArr[i22] = attributeLayout17;
                iArr10[i22] = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout17);
            }
            i22++;
            i19 = i23;
            attributeLayout10 = attributeLayout16;
        }
        AttributeLayout attributeLayout18 = attributeLayout10;
        int i24 = parseClassMetadataBands;
        int i25 = 0;
        while (i25 < i21) {
            if (iArr10[i25] > 0) {
                i13 = i21;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i25]);
                listArr2[i25] = attributeBands.parseAttributes(inputStream2, iArr10[i25]);
                int numBackwardsCallables = attributeLayoutArr[i25].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr13 = new int[numBackwardsCallables];
                    iArr2 = iArr10;
                    System.arraycopy(decodeBandInt, i24, iArr13, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr13);
                    i24 += numBackwardsCallables;
                } else {
                    iArr2 = iArr10;
                }
            } else {
                iArr2 = iArr10;
                i13 = i21;
            }
            i25++;
            inputStream2 = inputStream;
            i21 = i13;
            iArr10 = iArr2;
        }
        int i26 = i21;
        this.icLocal = new IcTuple[this.classCount][];
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        int i33 = 0;
        while (i33 < this.classCount) {
            int i34 = i32;
            long j = this.classFlags[i33];
            if (attributeLayout7.matches(j)) {
                attributeLayout = attributeLayout7;
                listArr = listArr2;
                this.classAttributes[i33].add(new DeprecatedAttribute());
            } else {
                attributeLayout = attributeLayout7;
                listArr = listArr2;
            }
            if (attributeLayout8.matches(j)) {
                i2 = i31;
                Object value = attributeLayout8.getValue((long) decodeBandInt2[i28], this.cpBands.getConstantPool());
                if (value == null) {
                    String str3 = this.classThis[i33];
                    String substring = str3.substring(str3.lastIndexOf(47) + 1);
                    String substring2 = substring.substring(substring.lastIndexOf(46) + 1);
                    char[] charArray = substring2.toCharArray();
                    attributeLayout2 = attributeLayout8;
                    int i35 = 0;
                    while (true) {
                        if (i35 >= charArray.length) {
                            iArr = decodeBandInt2;
                            i12 = -1;
                            i35 = -1;
                            break;
                        }
                        iArr = decodeBandInt2;
                        if (charArray[i35] <= '-') {
                            i12 = -1;
                            break;
                        } else {
                            i35++;
                            decodeBandInt2 = iArr;
                        }
                    }
                    if (i35 > i12) {
                        substring2 = substring2.substring(0, i35);
                    }
                    value = this.cpBands.cpUTF8Value(substring2 + ".java", true);
                } else {
                    attributeLayout2 = attributeLayout8;
                    iArr = decodeBandInt2;
                }
                this.classAttributes[i33].add(new SourceFileAttribute((CPUTF8) value));
                i28++;
            } else {
                attributeLayout2 = attributeLayout8;
                iArr = decodeBandInt2;
                i2 = i31;
            }
            if (attributeLayout9.matches(j)) {
                CPClass cpClassValue = this.cpBands.cpClassValue(decodeBandInt3[i29]);
                int i36 = decodeBandInt4[i29];
                this.classAttributes[i33].add(new EnclosingMethodAttribute(cpClassValue, i36 != 0 ? this.cpBands.cpNameAndTypeValue(i36 - 1) : null));
                i29++;
            }
            AttributeLayout attributeLayout19 = attributeLayout18;
            if (attributeLayout19.matches(j)) {
                this.classAttributes[i33].add(new SignatureAttribute((CPUTF8) attributeLayout19.getValue((long) iArr12[i30], this.cpBands.getConstantPool())));
                i30++;
            }
            AttributeLayout attributeLayout20 = attributeLayout13;
            if (attributeLayout20.matches(j)) {
                this.icLocal[i33] = new IcTuple[iArr4[i2]];
                i5 = i27;
                int i37 = 0;
                while (i37 < this.icLocal[i33].length) {
                    int i38 = iArr5[i2][i37];
                    String str4 = strArr2[i38];
                    int i39 = iArr7[i2][i37];
                    if (i39 != 0) {
                        int i40 = iArr11[i5];
                        int i41 = iArr9[i5];
                        String str5 = strArr2[i40];
                        i5++;
                        i8 = i28;
                        i7 = i29;
                        attributeLayout6 = attributeLayout19;
                        i10 = i40;
                        i9 = i41;
                        str = strArr[i41];
                        str2 = str5;
                        i11 = i39;
                    } else {
                        i8 = i28;
                        IcTuple[] icTuples = this.segment.getIcBands().getIcTuples();
                        i7 = i29;
                        attributeLayout6 = attributeLayout19;
                        int i42 = 0;
                        while (true) {
                            if (i42 >= icTuples.length) {
                                i11 = i39;
                                str2 = null;
                                str = null;
                                break;
                            } else if (icTuples[i42].getC().equals(str4)) {
                                int f = icTuples[i42].getF();
                                String c2 = icTuples[i42].getC2();
                                str = icTuples[i42].getN();
                                i11 = f;
                                str2 = c2;
                                break;
                            } else {
                                i42++;
                            }
                        }
                        i10 = -1;
                        i9 = -1;
                    }
                    this.icLocal[i33][i37] = new IcTuple(str4, i11, str2, str, i38, i10, i9, i37);
                    i37++;
                    i28 = i8;
                    i29 = i7;
                    attributeLayout19 = attributeLayout6;
                }
                i4 = i28;
                i3 = i29;
                attributeLayout3 = attributeLayout19;
                i31 = i2 + 1;
                attributeLayout4 = attributeLayout15;
            } else {
                i4 = i28;
                i3 = i29;
                attributeLayout3 = attributeLayout19;
                i5 = i27;
                attributeLayout4 = attributeLayout15;
                i31 = i2;
            }
            if (attributeLayout4.matches(j)) {
                this.classVersionMajor[i33] = iArr8[i34];
                this.classVersionMinor[i33] = decodeBandInt11[i34];
                i34++;
            } else {
                int[] iArr14 = this.classVersionMajor;
                if (iArr14 != null) {
                    iArr14[i33] = i20;
                    this.classVersionMinor[i33] = defaultClassMinorVersion;
                }
            }
            int i43 = i26;
            int i44 = 0;
            while (i44 < i43) {
                AttributeLayout attributeLayout21 = attributeLayoutArr[i44];
                if (attributeLayout21 == null || !attributeLayout21.matches(j)) {
                    attributeLayout5 = attributeLayout4;
                    i6 = i43;
                } else {
                    attributeLayout5 = attributeLayout4;
                    i6 = i43;
                    this.classAttributes[i33].add(listArr[i44].get(0));
                    listArr[i44].remove(0);
                }
                i44++;
                i43 = i6;
                attributeLayout4 = attributeLayout5;
            }
            attributeLayout15 = attributeLayout4;
            i26 = i43;
            i33++;
            attributeLayout13 = attributeLayout20;
            i27 = i5;
            i32 = i34;
            attributeLayout7 = attributeLayout;
            listArr2 = listArr;
            attributeLayout8 = attributeLayout2;
            decodeBandInt2 = iArr;
            i28 = i4;
            i29 = i3;
            attributeLayout18 = attributeLayout3;
        }
    }

    private void parseCodeBands(InputStream inputStream) throws Pack200Exception, IOException {
        char c;
        InputStream inputStream2 = inputStream;
        char c2 = 2;
        int countMatches = SegmentUtils.countMatches(this.methodFlags, (IMatcher) this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2));
        int[] decodeBandInt = decodeBandInt("code_headers", inputStream2, Codec.BYTE1, countMatches);
        boolean hasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!hasAllCodeFlags) {
            this.codeHasAttributes = new boolean[countMatches];
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < countMatches; i3++) {
            if (decodeBandInt[i3] == 0) {
                i2++;
                if (!hasAllCodeFlags) {
                    this.codeHasAttributes[i3] = true;
                }
            }
        }
        int[] decodeBandInt2 = decodeBandInt("code_max_stack", inputStream2, Codec.UNSIGNED5, i2);
        int[] decodeBandInt3 = decodeBandInt("code_max_na_locals", inputStream2, Codec.UNSIGNED5, i2);
        int[] decodeBandInt4 = decodeBandInt("code_handler_count", inputStream2, Codec.UNSIGNED5, i2);
        this.codeMaxStack = new int[countMatches];
        this.codeMaxNALocals = new int[countMatches];
        this.codeHandlerCount = new int[countMatches];
        int i4 = 0;
        int i5 = 0;
        while (i4 < countMatches) {
            int i6 = decodeBandInt[i4] & 255;
            if (i6 >= 0) {
                if (i6 == 0) {
                    this.codeMaxStack[i4] = decodeBandInt2[i5];
                    this.codeMaxNALocals[i4] = decodeBandInt3[i5];
                    this.codeHandlerCount[i4] = decodeBandInt4[i5];
                    i5++;
                    c = c2;
                } else {
                    if (i6 <= 144) {
                        int i7 = i6 - 1;
                        this.codeMaxStack[i4] = i7 % 12;
                        this.codeMaxNALocals[i4] = i7 / 12;
                        this.codeHandlerCount[i4] = 0;
                    } else if (i6 <= 208) {
                        int i8 = i6 - 145;
                        this.codeMaxStack[i4] = i8 % 8;
                        this.codeMaxNALocals[i4] = i8 / 8;
                        this.codeHandlerCount[i4] = 1;
                    } else if (i6 <= 255) {
                        int i9 = i6 - 209;
                        this.codeMaxStack[i4] = i9 % 7;
                        this.codeMaxNALocals[i4] = i9 / 7;
                        c = 2;
                        this.codeHandlerCount[i4] = 2;
                    } else {
                        throw new IllegalStateException("Shouldn't get here either");
                    }
                    c = 2;
                }
                i4++;
                c2 = c;
            } else {
                throw new IllegalStateException("Shouldn't get here");
            }
        }
        this.codeHandlerStartP = decodeBandInt("code_handler_start_P", inputStream2, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = decodeBandInt("code_handler_end_PO", inputStream2, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", inputStream2, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", inputStream2, Codec.UNSIGNED5, this.codeHandlerCount);
        if (!hasAllCodeFlags) {
            countMatches = i2;
        }
        this.codeAttributes = new List[countMatches];
        while (true) {
            List[] listArr = this.codeAttributes;
            if (i < listArr.length) {
                listArr[i] = new ArrayList();
                i++;
            } else {
                parseCodeAttrBands(inputStream2, countMatches);
                return;
            }
        }
    }

    private void parseCodeAttrBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
        AttributeLayout attributeLayout;
        int[] iArr;
        int i2;
        InputStream inputStream2 = inputStream;
        long[] parseFlags = parseFlags("code_flags", inputStream, i, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int[][] decodeBandInt = decodeBandInt("code_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("code_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags)));
        int i3 = 0;
        for (int[] iArr2 : decodeBandInt) {
            int i4 = 0;
            while (true) {
                if (i4 >= iArr2.length) {
                    break;
                }
                i3 += this.attrMap.getAttributeLayout(iArr2[i4], 3).numBackwardsCallables();
                i4++;
            }
        }
        int[] decodeBandInt2 = decodeBandInt("code_attr_calls", inputStream2, Codec.UNSIGNED5, i3);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3);
        int[] decodeBandInt3 = decodeBandInt("code_LineNumberTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout2));
        int[][] decodeBandInt4 = decodeBandInt("code_LineNumberTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt3);
        int[][] decodeBandInt5 = decodeBandInt("code_LineNumberTable_line", inputStream2, Codec.UNSIGNED5, decodeBandInt3);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3);
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3);
        int[] decodeBandInt6 = decodeBandInt("code_LocalVariableTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout3));
        int[][] decodeBandInt7 = decodeBandInt("code_LocalVariableTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt6);
        int[][] decodeBandInt8 = decodeBandInt("code_LocalVariableTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt6);
        CPUTF8[][] parseCPUTF8References = parseCPUTF8References("code_LocalVariableTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        CPUTF8[][] parseCPSignatureReferences = parseCPSignatureReferences("code_LocalVariableTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        AttributeLayout attributeLayout5 = attributeLayout4;
        int[][] decodeBandInt9 = decodeBandInt("code_LocalVariableTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[] decodeBandInt10 = decodeBandInt("code_LocalVariableTypeTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout4));
        int[][] decodeBandInt11 = decodeBandInt("code_LocalVariableTypeTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt10);
        int[][] decodeBandInt12 = decodeBandInt("code_LocalVariableTypeTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt10);
        CPUTF8[][] parseCPUTF8References2 = parseCPUTF8References("code_LocalVariableTypeTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        CPUTF8[][] parseCPSignatureReferences2 = parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int[][] decodeBandInt13 = decodeBandInt("code_LocalVariableTypeTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int i5 = this.options.hasCodeFlagsHi() ? 62 : 31;
        int[][] iArr3 = decodeBandInt13;
        int i6 = i5 + 1;
        int[] iArr4 = decodeBandInt10;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i6];
        CPUTF8[][] cputf8Arr = parseCPUTF8References;
        int[] iArr5 = new int[i6];
        int[][] iArr6 = decodeBandInt8;
        List[] listArr = new List[i6];
        int[][] iArr7 = decodeBandInt7;
        int i7 = 0;
        while (i7 < i5) {
            int i8 = i5;
            int[] iArr8 = decodeBandInt6;
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i7, 3);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i7] = attributeLayout6;
                iArr5[i7] = SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout6);
            }
            i7++;
            i5 = i8;
            decodeBandInt6 = iArr8;
        }
        int[] iArr9 = decodeBandInt6;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i6) {
            if (iArr5[i9] > 0) {
                i2 = i6;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i9]);
                listArr[i9] = attributeBands.parseAttributes(inputStream2, iArr5[i9]);
                int numBackwardsCallables = attributeLayoutArr[i9].numBackwardsCallables();
                iArr = iArr5;
                if (numBackwardsCallables > 0) {
                    int[] iArr10 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt2, i10, iArr10, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr10);
                    i10 += numBackwardsCallables;
                }
            } else {
                iArr = iArr5;
                i2 = i6;
            }
            i9++;
            inputStream2 = inputStream;
            i6 = i2;
            iArr5 = iArr;
        }
        int i11 = i6;
        int i12 = i;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i12) {
            if (attributeLayout2.matches(parseFlags[i15])) {
                attributeLayout = attributeLayout2;
                LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute(decodeBandInt3[i13], decodeBandInt4[i13], decodeBandInt5[i13]);
                i13++;
                this.codeAttributes[i15].add(lineNumberTableAttribute);
            } else {
                attributeLayout = attributeLayout2;
            }
            if (attributeLayout3.matches(parseFlags[i15])) {
                LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(iArr9[i14], iArr7[i14], iArr6[i14], cputf8Arr[i14], parseCPSignatureReferences[i14], decodeBandInt9[i14]);
                i14++;
                this.codeAttributes[i15].add(localVariableTableAttribute);
            }
            AttributeLayout attributeLayout7 = attributeLayout5;
            if (attributeLayout7.matches(parseFlags[i15])) {
                LocalVariableTypeTableAttribute localVariableTypeTableAttribute = new LocalVariableTypeTableAttribute(iArr4[i16], decodeBandInt11[i16], decodeBandInt12[i16], parseCPUTF8References2[i16], parseCPSignatureReferences2[i16], iArr3[i16]);
                i16++;
                this.codeAttributes[i15].add(localVariableTypeTableAttribute);
            }
            int i17 = i11;
            int i18 = 0;
            while (i18 < i17) {
                AttributeLayout attributeLayout8 = attributeLayoutArr[i18];
                int i19 = i13;
                int i20 = i14;
                if (attributeLayout8 != null && attributeLayout8.matches(parseFlags[i15])) {
                    this.codeAttributes[i15].add(listArr[i18].get(0));
                    listArr[i18].remove(0);
                }
                i18++;
                i13 = i19;
                i14 = i20;
            }
            int i21 = i13;
            int i22 = i14;
            i15++;
            i12 = i;
            attributeLayout5 = attributeLayout7;
            attributeLayout2 = attributeLayout;
            i11 = i17;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseFieldMetadataBands(java.io.InputStream r12, int[] r13) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r11 = this;
            java.lang.String r0 = "RVA"
            java.lang.String r1 = "RIA"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r0 = r11.attrMap
            java.lang.String r1 = "RuntimeVisibleAnnotations"
            r8 = 1
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r0 = r0.getAttributeLayout((java.lang.String) r1, (int) r8)
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r1 = r11.attrMap
            java.lang.String r2 = "RuntimeInvisibleAnnotations"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r1 = r1.getAttributeLayout((java.lang.String) r2, (int) r8)
            long[][] r2 = r11.fieldFlags
            int r2 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[][]) r2, (org.apache.commons.compress.harmony.unpack200.IMatcher) r0)
            long[][] r3 = r11.fieldFlags
            int r3 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[][]) r3, (org.apache.commons.compress.harmony.unpack200.IMatcher) r1)
            r5 = 2
            int[] r6 = new int[r5]
            r9 = 0
            r6[r9] = r2
            r6[r8] = r3
            int[] r7 = new int[r5]
            r7 = {0, 0} // fill-array
            if (r2 <= 0) goto L_0x0042
            r2 = r13[r9]
            r7[r9] = r2
            if (r3 <= 0) goto L_0x0040
            r13 = r13[r8]
            r7[r8] = r13
            r13 = r5
            goto L_0x004a
        L_0x0040:
            r13 = r8
            goto L_0x004a
        L_0x0042:
            if (r3 <= 0) goto L_0x0049
            r13 = r13[r9]
            r7[r8] = r13
            goto L_0x0040
        L_0x0049:
            r13 = r9
        L_0x004a:
            java.lang.String r10 = "field"
            r2 = r11
            r3 = r12
            r5 = r6
            r6 = r7
            r7 = r10
            org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[] r12 = r2.parseMetadata(r3, r4, r5, r6, r7)
            r2 = r12[r9]
            java.util.List r2 = r2.getAttributes()
            r12 = r12[r8]
            java.util.List r12 = r12.getAttributes()
            r3 = r9
            r4 = r3
            r5 = r4
        L_0x0064:
            long[][] r6 = r11.fieldFlags
            int r6 = r6.length
            if (r3 >= r6) goto L_0x00ab
            r6 = r9
        L_0x006a:
            long[][] r7 = r11.fieldFlags
            r7 = r7[r3]
            int r8 = r7.length
            if (r6 >= r8) goto L_0x00a8
            r7 = r7[r6]
            boolean r7 = r0.matches(r7)
            if (r7 == 0) goto L_0x0089
            java.util.ArrayList[][] r7 = r11.fieldAttributes
            r7 = r7[r3]
            r7 = r7[r6]
            int r8 = r4 + 1
            java.lang.Object r4 = r2.get(r4)
            r7.add(r4)
            r4 = r8
        L_0x0089:
            long[][] r7 = r11.fieldFlags
            r7 = r7[r3]
            r7 = r7[r6]
            boolean r7 = r1.matches(r7)
            if (r7 == 0) goto L_0x00a5
            java.util.ArrayList[][] r7 = r11.fieldAttributes
            r7 = r7[r3]
            r7 = r7[r6]
            int r8 = r5 + 1
            java.lang.Object r5 = r12.get(r5)
            r7.add(r5)
            r5 = r8
        L_0x00a5:
            int r6 = r6 + 1
            goto L_0x006a
        L_0x00a8:
            int r3 = r3 + 1
            goto L_0x0064
        L_0x00ab:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseFieldMetadataBands(java.io.InputStream, int[]):int");
    }

    private MetadataBandGroup[] parseMetadata(InputStream inputStream, String[] strArr, int[] iArr, int[] iArr2, String str) throws IOException, Pack200Exception {
        int i;
        ClassBands classBands = this;
        InputStream inputStream2 = inputStream;
        String[] strArr2 = strArr;
        String str2 = str;
        MetadataBandGroup[] metadataBandGroupArr = new MetadataBandGroup[strArr2.length];
        int i2 = 0;
        while (i2 < strArr2.length) {
            metadataBandGroupArr[i2] = new MetadataBandGroup(strArr2[i2], classBands.cpBands);
            String str3 = strArr2[i2];
            if (str3.indexOf(80) >= 0) {
                metadataBandGroupArr[i2].param_NB = classBands.decodeBandInt(str2 + "_" + str3 + "_param_NB", inputStream2, Codec.BYTE1, iArr[i2]);
            }
            if (!str3.equals("AD")) {
                metadataBandGroupArr[i2].anno_N = classBands.decodeBandInt(str2 + "_" + str3 + "_anno_N", inputStream2, Codec.UNSIGNED5, iArr[i2]);
                metadataBandGroupArr[i2].type_RS = classBands.parseCPSignatureReferences(str2 + "_" + str3 + "_type_RS", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                metadataBandGroupArr[i2].pair_N = classBands.decodeBandInt(str2 + "_" + str3 + "_pair_N", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                i = 0;
                for (int i3 = 0; i3 < metadataBandGroupArr[i2].pair_N.length; i3++) {
                    for (int i4 : metadataBandGroupArr[i2].pair_N[i3]) {
                        i += i4;
                    }
                }
                metadataBandGroupArr[i2].name_RU = classBands.parseCPUTF8References(str2 + "_" + str3 + "_name_RU", inputStream2, Codec.UNSIGNED5, i);
            } else {
                i = iArr[i2];
            }
            metadataBandGroupArr[i2].T = classBands.decodeBandInt(str2 + "_" + str3 + "_T", inputStream2, Codec.BYTE1, i + iArr2[i2]);
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i5 < metadataBandGroupArr[i2].T.length) {
                char c = (char) metadataBandGroupArr[i2].T[i5];
                String str4 = str3;
                if (c == '@') {
                    i11++;
                } else if (c != 'F') {
                    if (c != 'S') {
                        if (c == 'c') {
                            i10++;
                        } else if (c == 'e') {
                            i14++;
                        } else if (c == 's') {
                            i13++;
                        } else if (c != 'I') {
                            if (c == 'J') {
                                i9++;
                            } else if (c != 'Z') {
                                if (c != '[') {
                                    switch (c) {
                                        case 'B':
                                        case 'C':
                                            break;
                                        case 'D':
                                            i7++;
                                            break;
                                    }
                                } else {
                                    i12++;
                                }
                            }
                        }
                    }
                    i6++;
                } else {
                    i8++;
                }
                i5++;
                str3 = str4;
            }
            String str5 = str3;
            int i15 = i12;
            int i16 = i13;
            InputStream inputStream3 = inputStream;
            metadataBandGroupArr[i2].caseI_KI = parseCPIntReferences(str2 + "_" + str3 + "_caseI_KI", inputStream3, Codec.UNSIGNED5, i6);
            metadataBandGroupArr[i2].caseD_KD = parseCPDoubleReferences(str2 + "_" + str3 + "_caseD_KD", inputStream3, Codec.UNSIGNED5, i7);
            metadataBandGroupArr[i2].caseF_KF = parseCPFloatReferences(str2 + "_" + str3 + "_caseF_KF", inputStream3, Codec.UNSIGNED5, i8);
            metadataBandGroupArr[i2].caseJ_KJ = parseCPLongReferences(str2 + "_" + str3 + "_caseJ_KJ", inputStream3, Codec.UNSIGNED5, i9);
            metadataBandGroupArr[i2].casec_RS = parseCPSignatureReferences(str2 + "_" + str3 + "_casec_RS", inputStream3, Codec.UNSIGNED5, i10);
            InputStream inputStream4 = inputStream;
            int i17 = i14;
            metadataBandGroupArr[i2].caseet_RS = parseReferences(str2 + "_" + str3 + "_caseet_RS", inputStream4, Codec.UNSIGNED5, i17, this.cpBands.getCpSignature());
            metadataBandGroupArr[i2].caseec_RU = parseReferences(str2 + "_" + str3 + "_caseec_RU", inputStream4, Codec.UNSIGNED5, i17, this.cpBands.getCpUTF8());
            metadataBandGroupArr[i2].cases_RU = parseCPUTF8References(str2 + "_" + str3 + "_cases_RU", inputStream3, Codec.UNSIGNED5, i16);
            metadataBandGroupArr[i2].casearray_N = decodeBandInt(str2 + "_" + str3 + "_casearray_N", inputStream3, Codec.UNSIGNED5, i15);
            int i18 = i11;
            metadataBandGroupArr[i2].nesttype_RS = parseCPUTF8References(str2 + "_" + str3 + "_nesttype_RS", inputStream3, Codec.UNSIGNED5, i18);
            metadataBandGroupArr[i2].nestpair_N = decodeBandInt(str2 + "_" + str3 + "_nestpair_N", inputStream3, Codec.UNSIGNED5, i18);
            int i19 = 0;
            for (int i20 : metadataBandGroupArr[i2].nestpair_N) {
                i19 += i20;
            }
            metadataBandGroupArr[i2].nestname_RU = parseCPUTF8References(str2 + "_" + str3 + "_nestname_RU", inputStream3, Codec.UNSIGNED5, i19);
            i2++;
            classBands = this;
            inputStream2 = inputStream3;
            strArr2 = strArr;
        }
        return metadataBandGroupArr;
    }

    private int parseMethodMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        String[] strArr = {"RVA", "RIA", "RVPA", "RIPA", "AD"};
        int[] iArr2 = {0, 0, 0, 0, 0};
        AttributeLayout[] attributeLayoutArr = {this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT, 2)};
        for (int i = 0; i < 5; i++) {
            iArr2[i] = SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayoutArr[i]);
        }
        int[] iArr3 = new int[5];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iArr2[i4] > 0) {
                i2++;
                iArr3[i4] = iArr[i3];
                i3++;
            } else {
                iArr3[i4] = 0;
            }
        }
        MetadataBandGroup[] parseMetadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "method");
        List[] listArr = new List[5];
        int[] iArr4 = new int[5];
        for (int i5 = 0; i5 < parseMetadata.length; i5++) {
            listArr[i5] = parseMetadata[i5].getAttributes();
            iArr4[i5] = 0;
        }
        for (int i6 = 0; i6 < this.methodFlags.length; i6++) {
            for (int i7 = 0; i7 < this.methodFlags[i6].length; i7++) {
                for (int i8 = 0; i8 < 5; i8++) {
                    if (attributeLayoutArr[i8].matches(this.methodFlags[i6][i7])) {
                        ArrayList arrayList = this.methodAttributes[i6][i7];
                        List list = listArr[i8];
                        int i9 = iArr4[i8];
                        iArr4[i8] = i9 + 1;
                        arrayList.add(list.get(i9));
                    }
                }
            }
        }
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0099 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseClassMetadataBands(java.io.InputStream r12, int[] r13) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r11 = this;
            java.lang.String r0 = "RVA"
            java.lang.String r1 = "RIA"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r0 = r11.attrMap
            java.lang.String r1 = "RuntimeVisibleAnnotations"
            r8 = 0
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r0 = r0.getAttributeLayout((java.lang.String) r1, (int) r8)
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r1 = r11.attrMap
            java.lang.String r2 = "RuntimeInvisibleAnnotations"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r1 = r1.getAttributeLayout((java.lang.String) r2, (int) r8)
            long[] r2 = r11.classFlags
            int r2 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[]) r2, (org.apache.commons.compress.harmony.unpack200.IMatcher) r0)
            long[] r3 = r11.classFlags
            int r3 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[]) r3, (org.apache.commons.compress.harmony.unpack200.IMatcher) r1)
            r5 = 2
            int[] r6 = new int[r5]
            r6[r8] = r2
            r9 = 1
            r6[r9] = r3
            int[] r7 = new int[r5]
            r7 = {0, 0} // fill-array
            if (r2 <= 0) goto L_0x0042
            r2 = r13[r8]
            r7[r8] = r2
            if (r3 <= 0) goto L_0x0040
            r13 = r13[r9]
            r7[r9] = r13
            r13 = r5
            goto L_0x004a
        L_0x0040:
            r13 = r9
            goto L_0x004a
        L_0x0042:
            if (r3 <= 0) goto L_0x0049
            r13 = r13[r8]
            r7[r9] = r13
            goto L_0x0040
        L_0x0049:
            r13 = r8
        L_0x004a:
            java.lang.String r10 = "class"
            r2 = r11
            r3 = r12
            r5 = r6
            r6 = r7
            r7 = r10
            org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[] r12 = r2.parseMetadata(r3, r4, r5, r6, r7)
            r2 = r12[r8]
            java.util.List r2 = r2.getAttributes()
            r12 = r12[r9]
            java.util.List r12 = r12.getAttributes()
            r3 = r8
            r4 = r3
        L_0x0063:
            long[] r5 = r11.classFlags
            int r6 = r5.length
            if (r8 >= r6) goto L_0x0099
            r5 = r5[r8]
            boolean r5 = r0.matches(r5)
            if (r5 == 0) goto L_0x007e
            java.util.ArrayList[] r5 = r11.classAttributes
            r5 = r5[r8]
            int r6 = r3 + 1
            java.lang.Object r3 = r2.get(r3)
            r5.add(r3)
            r3 = r6
        L_0x007e:
            long[] r5 = r11.classFlags
            r5 = r5[r8]
            boolean r5 = r1.matches(r5)
            if (r5 == 0) goto L_0x0096
            java.util.ArrayList[] r5 = r11.classAttributes
            r5 = r5[r8]
            int r6 = r4 + 1
            java.lang.Object r4 = r12.get(r4)
            r5.add(r4)
            r4 = r6
        L_0x0096:
            int r8 = r8 + 1
            goto L_0x0063
        L_0x0099:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseClassMetadataBands(java.io.InputStream, int[]):int");
    }

    public ArrayList[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    public long[] getClassFlags() throws Pack200Exception {
        if (this.classAccessFlags == null) {
            int i = 0;
            long j = 32767;
            for (int i2 = 0; i2 < 16; i2++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i2, 0);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i2));
                }
            }
            this.classAccessFlags = new long[this.classFlags.length];
            while (true) {
                long[] jArr = this.classFlags;
                if (i >= jArr.length) {
                    break;
                }
                this.classAccessFlags[i] = jArr[i] & j;
                i++;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getFieldFlags() throws Pack200Exception {
        if (this.fieldAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 1);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.fieldAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.fieldFlags[i2];
                    if (i3 >= jArr2.length) {
                        break;
                    }
                    this.fieldAccessFlags[i2][i3] = jArr2[i3] & j;
                    i3++;
                }
                i2++;
            }
        }
        return this.fieldAccessFlags;
    }

    public ArrayList getOrderedCodeAttributes() {
        ArrayList arrayList = new ArrayList(this.codeAttributes.length);
        for (int i = 0; i < this.codeAttributes.length; i++) {
            ArrayList arrayList2 = new ArrayList(this.codeAttributes[i].size());
            for (int i2 = 0; i2 < this.codeAttributes[i].size(); i2++) {
                arrayList2.add((Attribute) this.codeAttributes[i].get(i2));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public ArrayList[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public long[][] getMethodFlags() throws Pack200Exception {
        if (this.methodAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 2);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.methodAccessFlags = new long[this.methodFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.methodFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.methodAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.methodFlags[i2];
                    if (i3 >= jArr2.length) {
                        break;
                    }
                    this.methodAccessFlags[i2][i3] = jArr2[i3] & j;
                    i3++;
                }
                i2++;
            }
        }
        return this.methodAccessFlags;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }
}
