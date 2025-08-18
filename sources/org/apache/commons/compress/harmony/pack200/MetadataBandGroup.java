package org.apache.commons.compress.harmony.pack200;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MetadataBandGroup extends BandSet {
    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;
    public List T = new ArrayList();
    public IntList anno_N = new IntList();
    public List caseD_KD = new ArrayList();
    public List caseF_KF = new ArrayList();
    public List caseI_KI = new ArrayList();
    public List caseJ_KJ = new ArrayList();
    public IntList casearray_N = new IntList();
    public List casec_RS = new ArrayList();
    public List caseec_RU = new ArrayList();
    public List caseet_RS = new ArrayList();
    public List cases_RU = new ArrayList();
    private final int context;
    private final CpBands cpBands;
    public List name_RU = new ArrayList();
    public List nestname_RU = new ArrayList();
    public IntList nestpair_N = new IntList();
    public List nesttype_RS = new ArrayList();
    private int numBackwardsCalls = 0;
    public IntList pair_N = new IntList();
    public IntList param_NB = new IntList();
    private final String type;
    public List type_RS = new ArrayList();

    public MetadataBandGroup(String str, int i, CpBands cpBands2, SegmentHeader segmentHeader, int i2) {
        super(i2, segmentHeader);
        this.type = str;
        this.cpBands = cpBands2;
        this.context = i;
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing metadata band group...");
        if (hasContent()) {
            int i = this.context;
            String str = i == 0 ? "Class" : i == 1 ? "Field" : "Method";
            if (!this.type.equals("AD")) {
                if (this.type.indexOf(80) != -1) {
                    byte[] encodeBandInt = encodeBandInt(str + "_" + this.type + " param_NB", this.param_NB.toArray(), Codec.BYTE1);
                    outputStream.write(encodeBandInt);
                    PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.param_NB.size() + "]");
                }
                byte[] encodeBandInt2 = encodeBandInt(str + "_" + this.type + " anno_N", this.anno_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt2);
                PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.anno_N.size() + "]");
                byte[] encodeBandInt3 = encodeBandInt(str + "_" + this.type + " type_RS", cpEntryListToArray(this.type_RS), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt3);
                PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from " + str + "_" + this.type + " type_RS[" + this.type_RS.size() + "]");
                byte[] encodeBandInt4 = encodeBandInt(str + "_" + this.type + " pair_N", this.pair_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt4);
                PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from " + str + "_" + this.type + " pair_N[" + this.pair_N.size() + "]");
                byte[] encodeBandInt5 = encodeBandInt(str + "_" + this.type + " name_RU", cpEntryListToArray(this.name_RU), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt5);
                PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from " + str + "_" + this.type + " name_RU[" + this.name_RU.size() + "]");
            }
            byte[] encodeBandInt6 = encodeBandInt(str + "_" + this.type + " T", tagListToArray(this.T), Codec.BYTE1);
            outputStream.write(encodeBandInt6);
            PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from " + str + "_" + this.type + " T[" + this.T.size() + "]");
            byte[] encodeBandInt7 = encodeBandInt(str + "_" + this.type + " caseI_KI", cpEntryListToArray(this.caseI_KI), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt7);
            PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from " + str + "_" + this.type + " caseI_KI[" + this.caseI_KI.size() + "]");
            byte[] encodeBandInt8 = encodeBandInt(str + "_" + this.type + " caseD_KD", cpEntryListToArray(this.caseD_KD), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt8);
            PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from " + str + "_" + this.type + " caseD_KD[" + this.caseD_KD.size() + "]");
            byte[] encodeBandInt9 = encodeBandInt(str + "_" + this.type + " caseF_KF", cpEntryListToArray(this.caseF_KF), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt9);
            PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from " + str + "_" + this.type + " caseF_KF[" + this.caseF_KF.size() + "]");
            byte[] encodeBandInt10 = encodeBandInt(str + "_" + this.type + " caseJ_KJ", cpEntryListToArray(this.caseJ_KJ), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt10);
            PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from " + str + "_" + this.type + " caseJ_KJ[" + this.caseJ_KJ.size() + "]");
            byte[] encodeBandInt11 = encodeBandInt(str + "_" + this.type + " casec_RS", cpEntryListToArray(this.casec_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt11);
            PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from " + str + "_" + this.type + " casec_RS[" + this.casec_RS.size() + "]");
            byte[] encodeBandInt12 = encodeBandInt(str + "_" + this.type + " caseet_RS", cpEntryListToArray(this.caseet_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt12);
            PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from " + str + "_" + this.type + " caseet_RS[" + this.caseet_RS.size() + "]");
            byte[] encodeBandInt13 = encodeBandInt(str + "_" + this.type + " caseec_RU", cpEntryListToArray(this.caseec_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt13);
            PackingUtils.log("Wrote " + encodeBandInt13.length + " bytes from " + str + "_" + this.type + " caseec_RU[" + this.caseec_RU.size() + "]");
            byte[] encodeBandInt14 = encodeBandInt(str + "_" + this.type + " cases_RU", cpEntryListToArray(this.cases_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt14);
            PackingUtils.log("Wrote " + encodeBandInt14.length + " bytes from " + str + "_" + this.type + " cases_RU[" + this.cases_RU.size() + "]");
            byte[] encodeBandInt15 = encodeBandInt(str + "_" + this.type + " casearray_N", this.casearray_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt15);
            PackingUtils.log("Wrote " + encodeBandInt15.length + " bytes from " + str + "_" + this.type + " casearray_N[" + this.casearray_N.size() + "]");
            byte[] encodeBandInt16 = encodeBandInt(str + "_" + this.type + " nesttype_RS", cpEntryListToArray(this.nesttype_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt16);
            PackingUtils.log("Wrote " + encodeBandInt16.length + " bytes from " + str + "_" + this.type + " nesttype_RS[" + this.nesttype_RS.size() + "]");
            byte[] encodeBandInt17 = encodeBandInt(str + "_" + this.type + " nestpair_N", this.nestpair_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt17);
            PackingUtils.log("Wrote " + encodeBandInt17.length + " bytes from " + str + "_" + this.type + " nestpair_N[" + this.nestpair_N.size() + "]");
            byte[] encodeBandInt18 = encodeBandInt(str + "_" + this.type + " nestname_RU", cpEntryListToArray(this.nestname_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt18);
            PackingUtils.log("Wrote " + encodeBandInt18.length + " bytes from " + str + "_" + this.type + " nestname_RU[" + this.nestname_RU.size() + "]");
        }
    }

    private int[] tagListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((String) list.get(i)).charAt(0);
        }
        return iArr;
    }

    public void addParameterAnnotation(int i, int[] iArr, IntList intList, List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8) {
        this.param_NB.add(i);
        for (int add : iArr) {
            this.anno_N.add(add);
        }
        this.pair_N.addAll(intList);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.type_RS.add(this.cpBands.getCPSignature((String) it.next()));
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
        }
        Iterator it3 = list4.iterator();
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            String str = (String) it4.next();
            this.T.add(str);
            if (str.equals("B") || str.equals("C") || str.equals("I") || str.equals(ExifInterface.LATITUDE_SOUTH) || str.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it3.next()));
            } else if (str.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it3.next()));
            } else if (str.equals("F")) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it3.next()));
            } else if (str.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it3.next()));
            } else if (str.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it3.next()));
            } else if (str.equals("e")) {
                this.caseet_RS.add(this.cpBands.getCPSignature((String) it3.next()));
                this.caseec_RU.add(this.cpBands.getCPUtf8((String) it3.next()));
            } else if (str.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it3.next()));
            }
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            int intValue = ((Integer) it5.next()).intValue();
            this.casearray_N.add(intValue);
            this.numBackwardsCalls += intValue;
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it7.next()));
        }
        Iterator it8 = list8.iterator();
        while (it8.hasNext()) {
            Integer num = (Integer) it8.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public void addAnnotation(String str, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        this.type_RS.add(this.cpBands.getCPSignature(str));
        this.pair_N.add(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it.next()));
        }
        Iterator it2 = list3.iterator();
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str2 = (String) it3.next();
            this.T.add(str2);
            if (str2.equals("B") || str2.equals("C") || str2.equals("I") || str2.equals(ExifInterface.LATITUDE_SOUTH) || str2.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it2.next()));
            } else if (str2.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it2.next()));
            } else if (str2.equals("F")) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it2.next()));
            } else if (str2.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it2.next()));
            } else if (str2.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it2.next()));
            } else if (str2.equals("e")) {
                this.caseet_RS.add(this.cpBands.getCPSignature((String) it2.next()));
                this.caseec_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
            } else if (str2.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
            }
        }
        Iterator it4 = list4.iterator();
        while (it4.hasNext()) {
            int intValue = ((Integer) it4.next()).intValue();
            this.casearray_N.add(intValue);
            this.numBackwardsCalls += intValue;
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it5.next()));
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            Integer num = (Integer) it7.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public boolean hasContent() {
        return this.type_RS.size() > 0;
    }

    public int numBackwardsCalls() {
        return this.numBackwardsCalls;
    }

    public void incrementAnnoN() {
        IntList intList = this.anno_N;
        intList.increment(intList.size() - 1);
    }

    public void newEntryInAnnoN() {
        this.anno_N.add(1);
    }

    public void removeLatest() {
        IntList intList = this.anno_N;
        int remove = intList.remove(intList.size() - 1);
        for (int i = 0; i < remove; i++) {
            List list = this.type_RS;
            list.remove(list.size() - 1);
            IntList intList2 = this.pair_N;
            int remove2 = intList2.remove(intList2.size() - 1);
            for (int i2 = 0; i2 < remove2; i2++) {
                removeOnePair();
            }
        }
    }

    private void removeOnePair() {
        List list = this.T;
        String str = (String) list.remove(list.size() - 1);
        if (str.equals("B") || str.equals("C") || str.equals("I") || str.equals(ExifInterface.LATITUDE_SOUTH) || str.equals("Z")) {
            List list2 = this.caseI_KI;
            list2.remove(list2.size() - 1);
        } else if (str.equals("D")) {
            List list3 = this.caseD_KD;
            list3.remove(list3.size() - 1);
        } else if (str.equals("F")) {
            List list4 = this.caseF_KF;
            list4.remove(list4.size() - 1);
        } else if (str.equals("J")) {
            List list5 = this.caseJ_KJ;
            list5.remove(list5.size() - 1);
        } else if (str.equals("C")) {
            List list6 = this.casec_RS;
            list6.remove(list6.size() - 1);
        } else if (str.equals("e")) {
            List list7 = this.caseet_RS;
            list7.remove(list7.size() - 1);
            this.caseec_RU.remove(this.caseet_RS.size() - 1);
        } else if (str.equals("s")) {
            List list8 = this.cases_RU;
            list8.remove(list8.size() - 1);
        } else {
            int i = 0;
            if (str.equals("[")) {
                IntList intList = this.casearray_N;
                int remove = intList.remove(intList.size() - 1);
                this.numBackwardsCalls -= remove;
                while (i < remove) {
                    removeOnePair();
                    i++;
                }
            } else if (str.equals("@")) {
                List list9 = this.nesttype_RS;
                list9.remove(list9.size() - 1);
                IntList intList2 = this.nestpair_N;
                int remove2 = intList2.remove(intList2.size() - 1);
                this.numBackwardsCalls -= remove2;
                while (i < remove2) {
                    removeOnePair();
                    i++;
                }
            }
        }
    }
}
