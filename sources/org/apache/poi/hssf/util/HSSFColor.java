package org.apache.poi.hssf.util;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.util.Removal;

public class HSSFColor implements Color {
    private static Map<HSSFColorPredefined, HSSFColor> enumList;
    private static Map<Integer, HSSFColor> indexHash;
    /* access modifiers changed from: private */
    public final java.awt.Color color;
    private final int index;
    private final int index2;

    public enum HSSFColorPredefined {
        BLACK(8, -1, 0),
        BROWN(60, -1, 10040064),
        OLIVE_GREEN(59, -1, 3355392),
        DARK_GREEN(58, -1, 13056),
        DARK_TEAL(56, -1, 13158),
        DARK_BLUE(18, 32, 128),
        INDIGO(62, -1, 3355545),
        GREY_80_PERCENT(63, -1, 3355443),
        ORANGE(53, -1, 16737792),
        DARK_YELLOW(19, -1, 8421376),
        GREEN(17, -1, 32768),
        TEAL(21, 38, 32896),
        BLUE(12, 39, 255),
        BLUE_GREY(54, -1, 6710937),
        GREY_50_PERCENT(23, -1, 8421504),
        RED(10, -1, 16711680),
        LIGHT_ORANGE(52, -1, 16750848),
        LIME(50, -1, 10079232),
        SEA_GREEN(57, -1, 3381606),
        AQUA(49, -1, 3394764),
        LIGHT_BLUE(48, -1, 3368703),
        VIOLET(20, 36, 8388736),
        GREY_40_PERCENT(55, -1, 9868950),
        PINK(14, 33, 16711935),
        GOLD(51, -1, 16763904),
        YELLOW(13, 34, 16776960),
        BRIGHT_GREEN(11, -1, MotionEventCompat.ACTION_POINTER_INDEX_MASK),
        TURQUOISE(15, 35, 65535),
        DARK_RED(16, 37, 8388608),
        SKY_BLUE(40, -1, 52479),
        PLUM(61, 25, 10040166),
        GREY_25_PERCENT(22, -1, 12632256),
        ROSE(45, -1, 16751052),
        LIGHT_YELLOW(43, -1, 16777113),
        LIGHT_GREEN(42, -1, 13434828),
        LIGHT_TURQUOISE(41, 27, 13434879),
        PALE_BLUE(44, -1, 10079487),
        LAVENDER(46, -1, 13408767),
        WHITE(9, -1, ViewCompat.MEASURED_SIZE_MASK),
        CORNFLOWER_BLUE(24, -1, 10066431),
        LEMON_CHIFFON(26, -1, 16777164),
        MAROON(25, -1, 8323072),
        ORCHID(28, -1, 6684774),
        CORAL(29, -1, 16744576),
        ROYAL_BLUE(30, -1, 26316),
        LIGHT_CORNFLOWER_BLUE(31, -1, 13421823),
        TAN(47, -1, 16764057),
        AUTOMATIC(64, -1, 0);
        
        private final HSSFColor color;

        private HSSFColorPredefined(int i, int i2, int i3) {
            this.color = new HSSFColor(i, i2, new java.awt.Color(i3));
        }

        public short getIndex() {
            return this.color.getIndex();
        }

        public short getIndex2() {
            return this.color.getIndex2();
        }

        public short[] getTriplet() {
            return this.color.getTriplet();
        }

        public String getHexString() {
            return this.color.getHexString();
        }

        public HSSFColor getColor() {
            return new HSSFColor(getIndex(), getIndex2(), this.color.color);
        }
    }

    public HSSFColor() {
        this(64, -1, java.awt.Color.BLACK);
    }

    public HSSFColor(int i, int i2, java.awt.Color color2) {
        this.index = i;
        this.index2 = i2;
        this.color = color2;
    }

    public static synchronized Map<Integer, HSSFColor> getIndexHash() {
        Map<Integer, HSSFColor> map;
        synchronized (HSSFColor.class) {
            if (indexHash == null) {
                indexHash = Collections.unmodifiableMap(createColorsByIndexMap());
            }
            map = indexHash;
        }
        return map;
    }

    public static Map<Integer, HSSFColor> getMutableIndexHash() {
        return createColorsByIndexMap();
    }

    private static Map<Integer, HSSFColor> createColorsByIndexMap() {
        Map<HSSFColorPredefined, HSSFColor> mapEnumToColorClass = mapEnumToColorClass();
        HashMap hashMap = new HashMap((mapEnumToColorClass.size() * 3) / 2);
        for (Map.Entry next : mapEnumToColorClass.entrySet()) {
            Integer valueOf = Integer.valueOf(((HSSFColorPredefined) next.getKey()).getIndex());
            if (!hashMap.containsKey(valueOf)) {
                hashMap.put(valueOf, next.getValue());
            }
            Integer valueOf2 = Integer.valueOf(((HSSFColorPredefined) next.getKey()).getIndex2());
            if (valueOf2.intValue() != -1 && !hashMap.containsKey(valueOf2)) {
                hashMap.put(valueOf2, next.getValue());
            }
        }
        return hashMap;
    }

    public static Map<String, HSSFColor> getTripletHash() {
        return createColorsByHexStringMap();
    }

    private static Map<String, HSSFColor> createColorsByHexStringMap() {
        Map<HSSFColorPredefined, HSSFColor> mapEnumToColorClass = mapEnumToColorClass();
        HashMap hashMap = new HashMap(mapEnumToColorClass.size());
        for (Map.Entry next : mapEnumToColorClass.entrySet()) {
            String hexString = ((HSSFColorPredefined) next.getKey()).getHexString();
            if (!hashMap.containsKey(hexString)) {
                hashMap.put(hexString, next.getValue());
            }
        }
        return hashMap;
    }

    @Deprecated
    @Removal(version = "3.18")
    private static synchronized Map<HSSFColorPredefined, HSSFColor> mapEnumToColorClass() {
        Map<HSSFColorPredefined, HSSFColor> map;
        synchronized (HSSFColor.class) {
            if (enumList == null) {
                enumList = new EnumMap(HSSFColorPredefined.class);
                addHSSFColorPredefined(HSSFColorPredefined.BLACK);
                addHSSFColorPredefined(HSSFColorPredefined.BROWN);
                addHSSFColorPredefined(HSSFColorPredefined.OLIVE_GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.DARK_GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.DARK_TEAL);
                addHSSFColorPredefined(HSSFColorPredefined.DARK_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.INDIGO);
                addHSSFColorPredefined(HSSFColorPredefined.GREY_80_PERCENT);
                addHSSFColorPredefined(HSSFColorPredefined.ORANGE);
                addHSSFColorPredefined(HSSFColorPredefined.DARK_YELLOW);
                addHSSFColorPredefined(HSSFColorPredefined.GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.TEAL);
                addHSSFColorPredefined(HSSFColorPredefined.BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.BLUE_GREY);
                addHSSFColorPredefined(HSSFColorPredefined.GREY_50_PERCENT);
                addHSSFColorPredefined(HSSFColorPredefined.RED);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_ORANGE);
                addHSSFColorPredefined(HSSFColorPredefined.LIME);
                addHSSFColorPredefined(HSSFColorPredefined.SEA_GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.AQUA);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.VIOLET);
                addHSSFColorPredefined(HSSFColorPredefined.GREY_40_PERCENT);
                addHSSFColorPredefined(HSSFColorPredefined.PINK);
                addHSSFColorPredefined(HSSFColorPredefined.GOLD);
                addHSSFColorPredefined(HSSFColorPredefined.YELLOW);
                addHSSFColorPredefined(HSSFColorPredefined.BRIGHT_GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.TURQUOISE);
                addHSSFColorPredefined(HSSFColorPredefined.DARK_RED);
                addHSSFColorPredefined(HSSFColorPredefined.SKY_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.PLUM);
                addHSSFColorPredefined(HSSFColorPredefined.GREY_25_PERCENT);
                addHSSFColorPredefined(HSSFColorPredefined.ROSE);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_YELLOW);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_GREEN);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_TURQUOISE);
                addHSSFColorPredefined(HSSFColorPredefined.PALE_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.LAVENDER);
                addHSSFColorPredefined(HSSFColorPredefined.WHITE);
                addHSSFColorPredefined(HSSFColorPredefined.CORNFLOWER_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.LEMON_CHIFFON);
                addHSSFColorPredefined(HSSFColorPredefined.MAROON);
                addHSSFColorPredefined(HSSFColorPredefined.ORCHID);
                addHSSFColorPredefined(HSSFColorPredefined.CORAL);
                addHSSFColorPredefined(HSSFColorPredefined.ROYAL_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE);
                addHSSFColorPredefined(HSSFColorPredefined.TAN);
            }
            map = enumList;
        }
        return map;
    }

    private static void addHSSFColorPredefined(HSSFColorPredefined hSSFColorPredefined) {
        enumList.put(hSSFColorPredefined, hSSFColorPredefined.getColor());
    }

    public short getIndex() {
        return (short) this.index;
    }

    public short getIndex2() {
        return (short) this.index2;
    }

    public short[] getTriplet() {
        return new short[]{(short) this.color.getRed(), (short) this.color.getGreen(), (short) this.color.getBlue()};
    }

    public String getHexString() {
        return (Integer.toHexString(this.color.getRed() * 257) + ParameterizedMessage.ERROR_MSG_SEPARATOR + Integer.toHexString(this.color.getGreen() * 257) + ParameterizedMessage.ERROR_MSG_SEPARATOR + Integer.toHexString(this.color.getBlue() * 257)).toUpperCase(Locale.ROOT);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HSSFColor hSSFColor = (HSSFColor) obj;
        if (this.index == hSSFColor.index && this.index2 == hSSFColor.index2) {
            return Objects.equals(this.color, hSSFColor.color);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.color, Integer.valueOf(this.index), Integer.valueOf(this.index2)});
    }

    public static HSSFColor toHSSFColor(Color color2) {
        if (color2 == null || (color2 instanceof HSSFColor)) {
            return (HSSFColor) color2;
        }
        throw new IllegalArgumentException("Only HSSFColor objects are supported, but had " + color2.getClass());
    }
}
