package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.util.GenericRecordXmlWriter;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class EscherAggregate extends AbstractEscherHolderRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = 100000000;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTBORDERCALLOUT1 = 50;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTBORDERCALLOUT2 = 51;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTBORDERCALLOUT3 = 52;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTBORDERCALLOUT90 = 181;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTCALLOUT1 = 44;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTCALLOUT2 = 45;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTCALLOUT3 = 46;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACCENTCALLOUT90 = 179;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONBACKPREVIOUS = 194;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONBEGINNING = 196;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONBLANK = 189;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONDOCUMENT = 198;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONEND = 195;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONFORWARDNEXT = 193;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONHELP = 191;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONHOME = 190;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONINFORMATION = 192;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONMOVIE = 200;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONRETURN = 197;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ACTIONBUTTONSOUND = 199;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ARC = 19;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ARROW = 13;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BALLOON = 17;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTARROW = 91;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTCONNECTOR2 = 33;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTCONNECTOR3 = 34;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTCONNECTOR4 = 35;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTCONNECTOR5 = 36;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BENTUPARROW = 90;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BEVEL = 84;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BLOCKARC = 95;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BORDERCALLOUT1 = 47;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BORDERCALLOUT2 = 48;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BORDERCALLOUT3 = 49;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BORDERCALLOUT90 = 180;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BRACEPAIR = 186;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_BRACKETPAIR = 185;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CALLOUT1 = 41;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CALLOUT2 = 42;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CALLOUT3 = 43;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CALLOUT90 = 178;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CAN = 22;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CHEVRON = 55;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CIRCULARARROW = 99;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CLOUDCALLOUT = 106;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CUBE = 16;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDCONNECTOR2 = 37;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDCONNECTOR3 = 38;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDCONNECTOR4 = 39;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDCONNECTOR5 = 40;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDDOWNARROW = 105;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDLEFTARROW = 103;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDRIGHTARROW = 102;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_CURVEDUPARROW = 104;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_DIAMOND = 4;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_DONUT = 23;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_DOUBLEWAVE = 188;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_DOWNARROW = 67;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_DOWNARROWCALLOUT = 80;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ELLIPSE = 3;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ELLIPSERIBBON = 107;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ELLIPSERIBBON2 = 108;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTALTERNATEPROCESS = 176;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTCOLLATE = 125;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTCONNECTOR = 120;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTDECISION = 110;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTDELAY = 135;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTDISPLAY = 134;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTDOCUMENT = 114;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTEXTRACT = 127;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTINPUTOUTPUT = 111;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTINTERNALSTORAGE = 113;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMAGNETICDISK = 132;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMAGNETICDRUM = 133;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMAGNETICTAPE = 131;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMANUALINPUT = 118;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMANUALOPERATION = 119;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMERGE = 128;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTMULTIDOCUMENT = 115;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTOFFLINESTORAGE = 129;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTOFFPAGECONNECTOR = 177;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTONLINESTORAGE = 130;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTOR = 124;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTPREDEFINEDPROCESS = 112;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTPREPARATION = 117;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTPROCESS = 109;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTPUNCHEDCARD = 121;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTPUNCHEDTAPE = 122;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTSORT = 126;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTSUMMINGJUNCTION = 123;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FLOWCHARTTERMINATOR = 116;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_FOLDEDCORNER = 65;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_HEART = 74;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_HEXAGON = 9;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_HOMEPLATE = 15;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_HORIZONTALSCROLL = 98;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_HOSTCONTROL = 201;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_IRREGULARSEAL1 = 71;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_IRREGULARSEAL2 = 72;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ISOCELESTRIANGLE = 5;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTARROW = 66;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTARROWCALLOUT = 77;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTBRACE = 87;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTBRACKET = 85;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTRIGHTARROW = 69;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTRIGHTARROWCALLOUT = 81;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTRIGHTUPARROW = 182;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LEFTUPARROW = 89;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LIGHTNINGBOLT = 73;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_LINE = 20;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_MIN = 0;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_MOON = 184;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_NIL = 4095;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_NOSMOKING = 57;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_NOTCHEDCIRCULARARROW = 100;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_NOTCHEDRIGHTARROW = 94;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_NOT_PRIMATIVE = 0;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_OCTAGON = 10;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_PARALLELOGRAM = 7;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_PENTAGON = 56;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_PICTUREFRAME = 75;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_PLAQUE = 21;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_PLUS = 11;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_QUADARROW = 76;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_QUADARROWCALLOUT = 83;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RECTANGLE = 1;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIBBON = 53;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIBBON2 = 54;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIGHTARROWCALLOUT = 78;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIGHTBRACE = 88;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIGHTBRACKET = 86;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_RIGHTTRIANGLE = 6;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_ROUNDRECTANGLE = 2;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL = 18;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL16 = 59;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL24 = 92;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL32 = 60;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL4 = 187;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SEAL8 = 58;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SMILEYFACE = 96;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_STAR = 12;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_STRAIGHTCONNECTOR1 = 32;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_STRIPEDRIGHTARROW = 93;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_SUN = 183;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTARCHDOWNCURVE = 145;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTARCHDOWNPOUR = 149;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTARCHUPCURVE = 144;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTARCHUPPOUR = 148;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTBOX = 202;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTBUTTONCURVE = 147;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTBUTTONPOUR = 151;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCANDOWN = 175;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCANUP = 174;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCASCADEDOWN = 155;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCASCADEUP = 154;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCHEVRON = 140;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCHEVRONINVERTED = 141;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCIRCLECURVE = 146;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCIRCLEPOUR = 150;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCURVE = 27;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCURVEDOWN = 153;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTCURVEUP = 152;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTDEFLATE = 161;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTDEFLATEBOTTOM = 163;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTDEFLATEINFLATE = 166;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTDEFLATEINFLATEDEFLATE = 167;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTDEFLATETOP = 165;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTFADEDOWN = 171;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTFADELEFT = 169;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTFADERIGHT = 168;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTFADEUP = 170;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTHEXAGON = 26;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTINFLATE = 160;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTINFLATEBOTTOM = 162;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTINFLATETOP = 164;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTOCTAGON = 25;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTONCURVE = 30;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTONRING = 31;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTPLAINTEXT = 136;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTRING = 29;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTRINGINSIDE = 142;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTRINGOUTSIDE = 143;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTSIMPLE = 24;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTSLANTDOWN = 173;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTSLANTUP = 172;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTSTOP = 137;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTTRIANGLE = 138;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTTRIANGLEINVERTED = 139;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTWAVE = 28;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTWAVE1 = 156;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTWAVE2 = 157;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTWAVE3 = 158;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TEXTWAVE4 = 159;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_THICKARROW = 14;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_TRAPEZOID = 8;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_UPARROW = 68;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_UPARROWCALLOUT = 79;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_UPDOWNARROW = 70;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_UPDOWNARROWCALLOUT = 82;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_UTURNARROW = 101;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_VERTICALSCROLL = 97;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_WAVE = 64;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_WEDGEELLIPSECALLOUT = 63;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_WEDGERECTCALLOUT = 61;
    @Deprecated
    @Removal(version = "5.3")
    public static final short ST_WEDGERRECTCALLOUT = 62;
    public static final short sid = 9876;
    private final Map<EscherRecord, Record> shapeToObj;
    private final Map<Integer, NoteRecord> tailRec;

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "ESCHERAGGREGATE";
    }

    public short getSid() {
        return sid;
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherAggregate(boolean z) {
        this.shapeToObj = new HashMap();
        this.tailRec = new LinkedHashMap();
        if (z) {
            buildBaseTree();
        }
    }

    public EscherAggregate(EscherAggregate escherAggregate) {
        super((AbstractEscherHolderRecord) escherAggregate);
        HashMap hashMap = new HashMap();
        this.shapeToObj = hashMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.tailRec = linkedHashMap;
        hashMap.putAll(escherAggregate.shapeToObj);
        linkedHashMap.putAll(escherAggregate.tailRec);
    }

    public String toXml(String str) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public static EscherAggregate createAggregate(List<RecordBase> list, int i) {
        EscherAggregate escherAggregate = new EscherAggregate(false);
        ShapeCollector shapeCollector = new ShapeCollector();
        ArrayList arrayList = new ArrayList();
        Iterator<RecordBase> it = list.subList(i, list.size()).iterator();
        int i2 = i;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RecordBase next = it.next();
            i2++;
            short sid2 = sid(next);
            if (sid2 == 28) {
                NoteRecord noteRecord = (NoteRecord) next;
                escherAggregate.tailRec.put(Integer.valueOf(noteRecord.getShapeId()), noteRecord);
            } else if (sid2 != 60) {
                if (sid2 != 93) {
                    if (sid2 != 236) {
                        if (sid2 != 438) {
                            i2--;
                            break;
                        }
                    } else {
                        shapeCollector.addBytes(((DrawingRecord) next).getRecordData());
                    }
                }
                arrayList.add((Record) next);
            } else {
                shapeCollector.addBytes(((ContinueRecord) next).getData());
            }
        }
        list.set(i, escherAggregate);
        int i3 = i + 1;
        if (i3 <= i2) {
            list.subList(i3, i2).clear();
        }
        arrayList.forEach(new EscherAggregate$$ExternalSyntheticLambda0(escherAggregate, shapeCollector.parse(escherAggregate).iterator()));
        return escherAggregate;
    }

    private static class ShapeCollector extends DefaultEscherRecordFactory {
        final UnsynchronizedByteArrayOutputStream buffer;
        final List<EscherRecord> objShapes;

        private ShapeCollector() {
            this.objShapes = new ArrayList();
            this.buffer = new UnsynchronizedByteArrayOutputStream();
        }

        /* access modifiers changed from: package-private */
        public void addBytes(byte[] bArr) {
            try {
                this.buffer.write(bArr);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't get data from drawing/continue records", e);
            }
        }

        public EscherRecord createRecord(byte[] bArr, int i) {
            EscherRecord createRecord = super.createRecord(bArr, i);
            short recordId = createRecord.getRecordId();
            if (recordId == EscherClientDataRecord.RECORD_ID || recordId == EscherTextboxRecord.RECORD_ID) {
                this.objShapes.add(createRecord);
            }
            return createRecord;
        }

        /* access modifiers changed from: package-private */
        public List<EscherRecord> parse(EscherAggregate escherAggregate) {
            byte[] byteArray = this.buffer.toByteArray();
            int i = 0;
            while (i < byteArray.length) {
                EscherRecord createRecord = createRecord(byteArray, i);
                i += createRecord.fillFields(byteArray, i, this);
                escherAggregate.addEscherRecord(createRecord);
            }
            return this.objShapes;
        }
    }

    public int serialize(int i, byte[] bArr) {
        byte[] bArr2 = bArr;
        List<EscherRecord> escherRecords = getEscherRecords();
        int escherRecordSize = getEscherRecordSize(escherRecords);
        byte[] bArr3 = new byte[escherRecordSize];
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        for (EscherRecord serialize : escherRecords) {
            i2 += serialize.serialize(i2, bArr3, new EscherSerializationListener() {
                public void beforeRecordSerialize(int i, short s, EscherRecord escherRecord) {
                }

                public void afterRecordSerialize(int i, short s, int i2, EscherRecord escherRecord) {
                    if (s == EscherClientDataRecord.RECORD_ID || s == EscherTextboxRecord.RECORD_ID) {
                        arrayList.add(Integer.valueOf(i));
                        arrayList2.add(escherRecord);
                    }
                }
            });
        }
        arrayList2.add(0, (Object) null);
        arrayList.add(0, 0);
        int i3 = i;
        boolean z = true;
        int i4 = 1;
        int i5 = 0;
        int i6 = 0;
        while (i4 < arrayList2.size()) {
            int intValue = ((Integer) arrayList.get(i4)).intValue();
            byte[] copyOfRange = Arrays.copyOfRange(bArr3, i5, intValue);
            int writeDataIntoDrawingRecord = i3 + writeDataIntoDrawingRecord(copyOfRange, i6, i3, bArr, z);
            i6 += copyOfRange.length;
            i3 = writeDataIntoDrawingRecord + this.shapeToObj.get(arrayList2.get(i4)).serialize(writeDataIntoDrawingRecord, bArr2);
            i4++;
            i5 = intValue;
            z = false;
        }
        if (i5 < escherRecordSize - 1) {
            i3 += writeDataIntoDrawingRecord(Arrays.copyOfRange(bArr3, i5, escherRecordSize), i6, i3, bArr, z);
        }
        for (NoteRecord serialize2 : this.tailRec.values()) {
            i3 += serialize2.serialize(i3, bArr2);
        }
        int i7 = i3 - i;
        if (i7 == getRecordSize()) {
            return i7;
        }
        throw new RecordFormatException(i7 + " bytes written but getRecordSize() reports " + getRecordSize());
    }

    private int writeDataIntoDrawingRecord(byte[] bArr, int i, int i2, byte[] bArr2, boolean z) {
        boolean z2 = z || i + bArr.length <= 8224;
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            int i5 = i3 + 8224;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i3, Math.min(i5, bArr.length));
            i4 += (z2 ? new DrawingRecord(copyOfRange) : new ContinueRecord(copyOfRange)).serialize(i2 + i4, bArr2);
            z2 = false;
            i3 = i5;
        }
        return i4;
    }

    private int getEscherRecordSize(List<EscherRecord> list) {
        int i = 0;
        for (EscherRecord recordSize : list) {
            i += recordSize.getRecordSize();
        }
        return i;
    }

    public int getRecordSize() {
        List<EscherRecord> escherRecords = getEscherRecords();
        int escherRecordSize = getEscherRecordSize(escherRecords);
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) escherRecordSize, MAX_RECORD_LENGTH);
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (EscherRecord serialize : escherRecords) {
            i2 += serialize.serialize(i2, safelyAllocate, new EscherSerializationListener() {
                public void beforeRecordSerialize(int i, short s, EscherRecord escherRecord) {
                }

                public void afterRecordSerialize(int i, short s, int i2, EscherRecord escherRecord) {
                    if (s == EscherClientDataRecord.RECORD_ID || s == EscherTextboxRecord.RECORD_ID) {
                        arrayList.add(Integer.valueOf(i));
                    }
                }
            });
        }
        arrayList.add(0, 0);
        int i3 = 0;
        for (int i4 = 1; i4 < arrayList.size(); i4++) {
            if (i4 == arrayList.size() - 1 && ((Integer) arrayList.get(i4)).intValue() < i2) {
                i3 += 4;
            }
            int i5 = i4 - 1;
            if (((Integer) arrayList.get(i4)).intValue() - ((Integer) arrayList.get(i5)).intValue() > 8224) {
                i3 += ((((Integer) arrayList.get(i4)).intValue() - ((Integer) arrayList.get(i5)).intValue()) / 8224) * 4;
            }
        }
        int size = (this.shapeToObj.size() * 4) + escherRecordSize;
        if (escherRecordSize != 0 && arrayList.size() == 1) {
            i3 += 4;
        }
        int i6 = 0;
        for (Record recordSize : this.shapeToObj.values()) {
            i6 += recordSize.getRecordSize();
        }
        for (NoteRecord recordSize2 : this.tailRec.values()) {
            i += recordSize2.getRecordSize();
        }
        return size + i6 + i + i3;
    }

    public void associateShapeToObjRecord(EscherRecord escherRecord, Record record) {
        this.shapeToObj.put(escherRecord, record);
    }

    public void removeShapeToObjRecord(EscherRecord escherRecord) {
        this.shapeToObj.remove(escherRecord);
    }

    private void buildBaseTree() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        EscherContainerRecord escherContainerRecord2 = new EscherContainerRecord();
        EscherContainerRecord escherContainerRecord3 = new EscherContainerRecord();
        EscherSpgrRecord escherSpgrRecord = new EscherSpgrRecord();
        EscherSpRecord escherSpRecord = new EscherSpRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.DG_CONTAINER);
        escherContainerRecord.setOptions(15);
        EscherDgRecord escherDgRecord = new EscherDgRecord();
        escherDgRecord.setRecordId(EscherDgRecord.RECORD_ID);
        escherDgRecord.setOptions((short) 16);
        escherDgRecord.setNumShapes(0);
        escherDgRecord.setLastMSOSPID(1024);
        escherContainerRecord2.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        escherContainerRecord2.setOptions(15);
        escherContainerRecord3.setRecordId(EscherContainerRecord.SP_CONTAINER);
        escherContainerRecord3.setOptions(15);
        escherSpgrRecord.setRecordId(EscherSpgrRecord.RECORD_ID);
        escherSpgrRecord.setOptions(1);
        escherSpgrRecord.setRectX1(0);
        escherSpgrRecord.setRectY1(0);
        escherSpgrRecord.setRectX2(IEEEDouble.EXPONENT_BIAS);
        escherSpgrRecord.setRectY2(255);
        escherSpRecord.setRecordId(EscherSpRecord.RECORD_ID);
        escherSpRecord.setOptions(2);
        escherSpRecord.setVersion(2);
        escherSpRecord.setShapeId(-1);
        escherSpRecord.setFlags(5);
        escherContainerRecord.addChildRecord(escherDgRecord);
        escherContainerRecord.addChildRecord(escherContainerRecord2);
        escherContainerRecord2.addChildRecord(escherContainerRecord3);
        escherContainerRecord3.addChildRecord(escherSpgrRecord);
        escherContainerRecord3.addChildRecord(escherSpRecord);
        addEscherRecord(escherContainerRecord);
    }

    public void setDgId(short s) {
        EscherDgRecord escherDgRecord = (EscherDgRecord) getEscherContainer().getChildById(EscherDgRecord.RECORD_ID);
        if (escherDgRecord != null) {
            escherDgRecord.setOptions((short) (s << 4));
        }
    }

    public void setMainSpRecordId(int i) {
        EscherSpRecord escherSpRecord;
        EscherContainerRecord escherContainerRecord = (EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SPGR_CONTAINER);
        if (escherContainerRecord != null && (escherSpRecord = (EscherSpRecord) ((EscherContainerRecord) escherContainerRecord.getChild(0)).getChildById(EscherSpRecord.RECORD_ID)) != null) {
            escherSpRecord.setShapeId(i);
        }
    }

    private static short sid(RecordBase recordBase) {
        if (recordBase instanceof Record) {
            return ((Record) recordBase).getSid();
        }
        return -1;
    }

    public Map<EscherRecord, Record> getShapeToObjMapping() {
        return Collections.unmodifiableMap(this.shapeToObj);
    }

    public Map<Integer, NoteRecord> getTailRecords() {
        return Collections.unmodifiableMap(this.tailRec);
    }

    public NoteRecord getNoteRecordByObj(ObjRecord objRecord) {
        return this.tailRec.get(Integer.valueOf(((CommonObjectDataSubRecord) objRecord.getSubRecords().get(0)).getObjectId()));
    }

    public void addTailRecord(NoteRecord noteRecord) {
        this.tailRec.put(Integer.valueOf(noteRecord.getShapeId()), noteRecord);
    }

    public void removeTailRecord(NoteRecord noteRecord) {
        this.tailRec.remove(Integer.valueOf(noteRecord.getShapeId()));
    }

    public EscherAggregate copy() {
        return new EscherAggregate(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ESCHER_AGGREGATE;
    }
}
