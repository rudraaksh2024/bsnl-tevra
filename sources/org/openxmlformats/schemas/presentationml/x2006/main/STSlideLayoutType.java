package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSlideLayoutType extends XmlToken {
    public static final Enum BLANK = Enum.forString("blank");
    public static final Enum CHART = Enum.forString("chart");
    public static final Enum CHART_AND_TX = Enum.forString("chartAndTx");
    public static final Enum CLIP_ART_AND_TX = Enum.forString("clipArtAndTx");
    public static final Enum CLIP_ART_AND_VERT_TX = Enum.forString("clipArtAndVertTx");
    public static final Enum CUST = Enum.forString("cust");
    public static final Enum DGM = Enum.forString("dgm");
    public static final Enum FOUR_OBJ = Enum.forString("fourObj");
    public static final SimpleTypeFactory<STSlideLayoutType> Factory;
    public static final int INT_BLANK = 12;
    public static final int INT_CHART = 8;
    public static final int INT_CHART_AND_TX = 6;
    public static final int INT_CLIP_ART_AND_TX = 10;
    public static final int INT_CLIP_ART_AND_VERT_TX = 26;
    public static final int INT_CUST = 32;
    public static final int INT_DGM = 7;
    public static final int INT_FOUR_OBJ = 24;
    public static final int INT_MEDIA_AND_TX = 18;
    public static final int INT_OBJ = 16;
    public static final int INT_OBJ_AND_TWO_OBJ = 30;
    public static final int INT_OBJ_AND_TX = 14;
    public static final int INT_OBJ_ONLY = 15;
    public static final int INT_OBJ_OVER_TX = 19;
    public static final int INT_OBJ_TX = 35;
    public static final int INT_PIC_TX = 36;
    public static final int INT_SEC_HEAD = 33;
    public static final int INT_TBL = 4;
    public static final int INT_TITLE = 1;
    public static final int INT_TITLE_ONLY = 11;
    public static final int INT_TWO_COL_TX = 3;
    public static final int INT_TWO_OBJ = 29;
    public static final int INT_TWO_OBJ_AND_OBJ = 31;
    public static final int INT_TWO_OBJ_AND_TX = 22;
    public static final int INT_TWO_OBJ_OVER_TX = 23;
    public static final int INT_TWO_TX_TWO_OBJ = 34;
    public static final int INT_TX = 2;
    public static final int INT_TX_AND_CHART = 5;
    public static final int INT_TX_AND_CLIP_ART = 9;
    public static final int INT_TX_AND_MEDIA = 17;
    public static final int INT_TX_AND_OBJ = 13;
    public static final int INT_TX_AND_TWO_OBJ = 21;
    public static final int INT_TX_OVER_OBJ = 20;
    public static final int INT_VERT_TITLE_AND_TX = 27;
    public static final int INT_VERT_TITLE_AND_TX_OVER_CHART = 28;
    public static final int INT_VERT_TX = 25;
    public static final Enum MEDIA_AND_TX = Enum.forString("mediaAndTx");
    public static final Enum OBJ = Enum.forString("obj");
    public static final Enum OBJ_AND_TWO_OBJ = Enum.forString("objAndTwoObj");
    public static final Enum OBJ_AND_TX = Enum.forString("objAndTx");
    public static final Enum OBJ_ONLY = Enum.forString("objOnly");
    public static final Enum OBJ_OVER_TX = Enum.forString("objOverTx");
    public static final Enum OBJ_TX = Enum.forString("objTx");
    public static final Enum PIC_TX = Enum.forString("picTx");
    public static final Enum SEC_HEAD = Enum.forString("secHead");
    public static final Enum TBL = Enum.forString("tbl");
    public static final Enum TITLE = Enum.forString("title");
    public static final Enum TITLE_ONLY = Enum.forString("titleOnly");
    public static final Enum TWO_COL_TX = Enum.forString("twoColTx");
    public static final Enum TWO_OBJ = Enum.forString("twoObj");
    public static final Enum TWO_OBJ_AND_OBJ = Enum.forString("twoObjAndObj");
    public static final Enum TWO_OBJ_AND_TX = Enum.forString("twoObjAndTx");
    public static final Enum TWO_OBJ_OVER_TX = Enum.forString("twoObjOverTx");
    public static final Enum TWO_TX_TWO_OBJ = Enum.forString("twoTxTwoObj");
    public static final Enum TX = Enum.forString("tx");
    public static final Enum TX_AND_CHART = Enum.forString("txAndChart");
    public static final Enum TX_AND_CLIP_ART = Enum.forString("txAndClipArt");
    public static final Enum TX_AND_MEDIA = Enum.forString("txAndMedia");
    public static final Enum TX_AND_OBJ = Enum.forString("txAndObj");
    public static final Enum TX_AND_TWO_OBJ = Enum.forString("txAndTwoObj");
    public static final Enum TX_OVER_OBJ = Enum.forString("txOverObj");
    public static final Enum VERT_TITLE_AND_TX = Enum.forString("vertTitleAndTx");
    public static final Enum VERT_TITLE_AND_TX_OVER_CHART = Enum.forString("vertTitleAndTxOverChart");
    public static final Enum VERT_TX = Enum.forString("vertTx");
    public static final SchemaType type;

    StringEnumAbstractBase getEnumValue();

    void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase);

    static {
        SimpleTypeFactory<STSlideLayoutType> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stslidelayouttype3aaatype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }

    public static final class Enum extends StringEnumAbstractBase {
        static final int INT_BLANK = 12;
        static final int INT_CHART = 8;
        static final int INT_CHART_AND_TX = 6;
        static final int INT_CLIP_ART_AND_TX = 10;
        static final int INT_CLIP_ART_AND_VERT_TX = 26;
        static final int INT_CUST = 32;
        static final int INT_DGM = 7;
        static final int INT_FOUR_OBJ = 24;
        static final int INT_MEDIA_AND_TX = 18;
        static final int INT_OBJ = 16;
        static final int INT_OBJ_AND_TWO_OBJ = 30;
        static final int INT_OBJ_AND_TX = 14;
        static final int INT_OBJ_ONLY = 15;
        static final int INT_OBJ_OVER_TX = 19;
        static final int INT_OBJ_TX = 35;
        static final int INT_PIC_TX = 36;
        static final int INT_SEC_HEAD = 33;
        static final int INT_TBL = 4;
        static final int INT_TITLE = 1;
        static final int INT_TITLE_ONLY = 11;
        static final int INT_TWO_COL_TX = 3;
        static final int INT_TWO_OBJ = 29;
        static final int INT_TWO_OBJ_AND_OBJ = 31;
        static final int INT_TWO_OBJ_AND_TX = 22;
        static final int INT_TWO_OBJ_OVER_TX = 23;
        static final int INT_TWO_TX_TWO_OBJ = 34;
        static final int INT_TX = 2;
        static final int INT_TX_AND_CHART = 5;
        static final int INT_TX_AND_CLIP_ART = 9;
        static final int INT_TX_AND_MEDIA = 17;
        static final int INT_TX_AND_OBJ = 13;
        static final int INT_TX_AND_TWO_OBJ = 21;
        static final int INT_TX_OVER_OBJ = 20;
        static final int INT_VERT_TITLE_AND_TX = 27;
        static final int INT_VERT_TITLE_AND_TX_OVER_CHART = 28;
        static final int INT_VERT_TX = 25;
        private static final long serialVersionUID = 1;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("title", 1), new Enum("tx", 2), new Enum("twoColTx", 3), new Enum("tbl", 4), new Enum("txAndChart", 5), new Enum("chartAndTx", 6), new Enum("dgm", 7), new Enum("chart", 8), new Enum("txAndClipArt", 9), new Enum("clipArtAndTx", 10), new Enum("titleOnly", 11), new Enum("blank", 12), new Enum("txAndObj", 13), new Enum("objAndTx", 14), new Enum("objOnly", 15), new Enum("obj", 16), new Enum("txAndMedia", 17), new Enum("mediaAndTx", 18), new Enum("objOverTx", 19), new Enum("txOverObj", 20), new Enum("txAndTwoObj", 21), new Enum("twoObjAndTx", 22), new Enum("twoObjOverTx", 23), new Enum("fourObj", 24), new Enum("vertTx", 25), new Enum("clipArtAndVertTx", 26), new Enum("vertTitleAndTx", 27), new Enum("vertTitleAndTxOverChart", 28), new Enum("twoObj", 29), new Enum("objAndTwoObj", 30), new Enum("twoObjAndObj", 31), new Enum("cust", 32), new Enum("secHead", 33), new Enum("twoTxTwoObj", 34), new Enum("objTx", 35), new Enum("picTx", 36)});

        public static Enum forString(String str) {
            return (Enum) table.forString(str);
        }

        public static Enum forInt(int i) {
            return (Enum) table.forInt(i);
        }

        private Enum(String str, int i) {
            super(str, i);
        }

        private Object readResolve() {
            return forInt(intValue());
        }
    }
}
