package org.apache.poi.sl.usermodel;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public enum PresetColor {
    ActiveBorder(-4934476, 1, "activeBorder"),
    ActiveCaption(-6703919, 2, "activeCaption"),
    ActiveCaptionText(r2, 3, "captionText"),
    AppWorkspace(-5526613, 4, "appWorkspace"),
    Control(-986896, 5, "btnFace"),
    ControlDark(-9868951, 6, "btnShadow"),
    ControlDarkDark(r10, 7, "3dDkShadow"),
    ControlLight(-1842205, 8, "btnHighlight"),
    ControlLightLight(-1842205, 9, "3dLight"),
    ControlText(r10, 10, "btnText"),
    Desktop(r10, 11, "background"),
    GrayText(-9605779, 12, "grayText"),
    Highlight(-13395457, 13, "highlight"),
    HighlightText(-1, 14, "highlightText"),
    HotTrack(-16750900, 15, "hotLight"),
    InactiveBorder(-722948, 16, "inactiveBorder"),
    InactiveCaption(-4207141, 17, "inactiveCaption"),
    InactiveCaptionText(r10, 18, "inactiveCaptionText"),
    Info(-31, 19, "infoBk"),
    InfoText(r10, 20, "infoText"),
    Menu(-986896, 21, "menu"),
    MenuText(r2, 22, "menuText"),
    ScrollBar(-3618616, 23, "scrollBar"),
    Window(-1, 24, "window"),
    WindowFrame(-10197916, 25, "windowFrame"),
    WindowText(r2, 26, "windowText"),
    Transparent(Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK), 27, (Integer) null),
    AliceBlue(-984833, 28, "aliceBlue"),
    AntiqueWhite(-332841, 29, "antiqueWhite"),
    Aqua(-16711681, 30, "aqua"),
    Aquamarine(-8388652, 31, "aquamarine"),
    Azure(-983041, 32, "azure"),
    Beige(-657956, 33, "beige"),
    Bisque(-6972, 34, "bisque"),
    Black(r2, 35, "black"),
    BlanchedAlmond(-5171, 36, "blanchedAlmond"),
    Blue(-16776961, 37, "blue"),
    BlueViolet(-7722014, 38, "blueViolet"),
    Brown(-5952982, 39, "brown"),
    BurlyWood(-2180985, 40, "burlyWood"),
    CadetBlue(-10510688, 41, "cadetBlue"),
    Chartreuse(-8388864, 42, "chartreuse"),
    Chocolate(-2987746, 43, "chocolate"),
    Coral(-32944, 44, "coral"),
    CornflowerBlue(-10185235, 45, "cornflowerBlue"),
    Cornsilk(-1828, 46, "cornsilk"),
    Crimson(-2354116, 47, "crimson"),
    Cyan(-16711681, 48, "cyan"),
    DarkBlue(-16777077, 49, "dkBlue"),
    DarkCyan(-16741493, 50, "dkCyan"),
    DarkGoldenrod(-4684277, 51, "dkGoldenrod"),
    DarkGray(-5658199, 52, "dkGray"),
    DarkGreen(-16751616, 53, "dkGreen"),
    DarkKhaki(-4343957, 54, "dkKhaki"),
    DarkMagenta(-7667573, 55, "dkMagenta"),
    DarkOliveGreen(-11179217, 56, "dkOliveGreen"),
    DarkOrange(-29696, 57, "dkOrange"),
    DarkOrchid(-6737204, 58, "dkOrchid"),
    DarkRed(-7667712, 59, "dkRed"),
    DarkSalmon(-1468806, 60, "dkSalmon"),
    DarkSeaGreen(-7357301, 61, "dkSeaGreen"),
    DarkSlateBlue(-12042869, 62, "dkSlateBlue"),
    DarkSlateGray(-13676721, 63, "dkSlateGray"),
    DarkTurquoise(-16724271, 64, "dkTurquoise"),
    DarkViolet(-7077677, 65, "dkViolet"),
    DeepPink(-60269, 66, "deepPink"),
    DeepSkyBlue(-16728065, 67, "deepSkyBlue"),
    DimGray(-9868951, 68, "dimGray"),
    DodgerBlue(-14774017, 69, "dodgerBlue"),
    Firebrick(-5103070, 70, "firebrick"),
    FloralWhite(-1296, 71, "floralWhite"),
    ForestGreen(-14513374, 72, "forestGreen"),
    Fuchsia(-65281, 73, "fuchsia"),
    Gainsboro(-2302756, 74, "gainsboro"),
    GhostWhite(-460545, 75, "ghostWhite"),
    Gold(-10496, 76, "gold"),
    Goldenrod(-2448096, 77, "goldenrod"),
    Gray(-8355712, 78, "gray"),
    Green(-16744448, 79, "green"),
    GreenYellow(-5374161, 80, "greenYellow"),
    Honeydew(-983056, 81, "honeydew"),
    HotPink(-38476, 82, "hotPink"),
    IndianRed(-3318692, 83, "indianRed"),
    Indigo(-11861886, 84, "indigo"),
    Ivory(-16, 85, "ivory"),
    Khaki(-989556, 86, "khaki"),
    Lavender(-1644806, 87, "lavender"),
    LavenderBlush(-3851, 88, "lavenderBlush"),
    LawnGreen(-8586240, 89, "lawnGreen"),
    LemonChiffon(-1331, 90, "lemonChiffon"),
    LightBlue(-5383962, 91, "ltBlue"),
    LightCoral(-1015680, 92, "ltCoral"),
    LightCyan(-2031617, 93, "ltCyan"),
    LightGoldenrodYellow(-329096, 94, "ltGoldenrodYellow"),
    LightGray(-2894893, 95, "ltGray"),
    LightGreen(-7278960, 96, "ltGreen"),
    LightPink(-18751, 97, "ltPink"),
    LightSalmon(-24454, 98, "ltSalmon"),
    LightSeaGreen(-14634326, 99, "ltSeaGreen"),
    LightSkyBlue(-7876870, 100, "ltSkyBlue"),
    LightSlateGray(-8943463, 101, "ltSlateGray"),
    LightSteelBlue(-5192482, 102, "ltSteelBlue"),
    LightYellow(-32, 103, "ltYellow"),
    Lime(-16711936, 104, "lime"),
    LimeGreen(-13447886, 105, "limeGreen"),
    Linen(-331546, 106, "linen"),
    Magenta(-65281, 107, "magenta"),
    Maroon(-8388608, 108, "maroon"),
    MediumAquamarine(-10039894, 109, "medAquamarine"),
    MediumBlue(-16777011, 110, "medBlue"),
    MediumOrchid(-4565549, 111, "medOrchid"),
    MediumPurple(-7114533, 112, "medPurple"),
    MediumSeaGreen(-12799119, 113, "medSeaGreen"),
    MediumSlateBlue(-8689426, 114, "medSlateBlue"),
    MediumSpringGreen(-16713062, 115, "medSpringGreen"),
    MediumTurquoise(-12004916, 116, "medTurquoise"),
    MediumVioletRed(-3730043, 117, "medVioletRed"),
    MidnightBlue(-15132304, 118, "midnightBlue"),
    MintCream(-655366, 119, "mintCream"),
    MistyRose(-6943, 120, "mistyRose"),
    Moccasin(-6987, 121, "moccasin"),
    NavajoWhite(-8531, 122, "navajoWhite"),
    Navy(-16777088, 123, "navy"),
    OldLace(-133658, 124, "oldLace"),
    Olive(-8355840, 125, "olive"),
    OliveDrab(-9728477, 126, "oliveDrab"),
    Orange(-23296, 127, "orange"),
    OrangeRed(-47872, 128, "orangeRed"),
    Orchid(-2461482, 129, "orchid"),
    PaleGoldenrod(-1120086, 130, "paleGoldenrod"),
    PaleGreen(-6751336, 131, "paleGreen"),
    PaleTurquoise(-5247250, 132, "paleTurquoise"),
    PaleVioletRed(-2396013, 133, "paleVioletRed"),
    PapayaWhip(-4139, 134, "papayaWhip"),
    PeachPuff(-9543, 135, "peachPuff"),
    Peru(-3308225, 136, "peru"),
    Pink(-16181, 137, "pink"),
    Plum(-2252579, 138, "plum"),
    PowderBlue(-5185306, 139, "powderBlue"),
    Purple(-8388480, 140, "purple"),
    Red(Integer.valueOf(SupportMenu.CATEGORY_MASK), 141, "red"),
    RosyBrown(-4419697, 142, "rosyBrown"),
    RoyalBlue(-12490271, 143, "royalBlue"),
    SaddleBrown(-7650029, 144, "saddleBrown"),
    Salmon(-360334, 145, "salmon"),
    SandyBrown(-744352, 146, "sandyBrown"),
    SeaGreen(-13726889, 147, "seaGreen"),
    SeaShell(-2578, 148, "seaShell"),
    Sienna(-6270419, 149, "sienna"),
    Silver(-4144960, 150, "silver"),
    SkyBlue(-7876885, 151, "skyBlue"),
    SlateBlue(-9807155, 152, "slateBlue"),
    SlateGray(-9404272, 153, "slateGray"),
    Snow(-1286, 154, "snow"),
    SpringGreen(-16711809, 155, "springGreen"),
    SteelBlue(-12156236, 156, "steelBlue"),
    Tan(-2968436, 157, "tan"),
    Teal(-16744320, 158, "teal"),
    Thistle(-2572328, 159, "thistle"),
    Tomato(-40121, 160, "tomato"),
    Turquoise(-12525360, 161, "turquoise"),
    Violet(-1146130, 162, "violet"),
    Wheat(-663885, 163, "wheat"),
    White(-1, 164, "white"),
    WhiteSmoke(-657931, 165, "whiteSmoke"),
    Yellow(Integer.valueOf(InputDeviceCompat.SOURCE_ANY), 166, "yellow"),
    YellowGreen(-6632142, 167, "yellowGreen"),
    ButtonFace(-986896, 168, (Integer) null),
    ButtonHighlight(-1, 169, (Integer) null),
    ButtonShadow(-6250336, 170, (Integer) null),
    GradientActiveCaption(-4599318, 171, "gradientActiveCaption"),
    GradientInactiveCaption(-2628366, 172, "gradientInactiveCaption"),
    MenuBar(-986896, 173, "menuBar"),
    MenuHighlight(-13395457, 174, "menuHighlight");
    
    private static final Map<String, PresetColor> lookupOoxmlId = null;
    public final Color color;
    public final int nativeId;
    public final String ooxmlId;

    static {
        int i;
        lookupOoxmlId = new HashMap();
        for (PresetColor presetColor : values()) {
            String str = presetColor.ooxmlId;
            if (str != null) {
                lookupOoxmlId.put(str, presetColor);
            }
        }
    }

    private PresetColor(Integer num, int i, String str) {
        Color color2;
        if (num == null) {
            color2 = null;
        } else {
            color2 = new Color(num.intValue(), true);
        }
        this.color = color2;
        this.nativeId = i;
        this.ooxmlId = str;
    }

    public static PresetColor valueOfOoxmlId(String str) {
        return lookupOoxmlId.get(str);
    }

    public static PresetColor valueOfNativeId(int i) {
        PresetColor[] values = values();
        if (i <= 0 || i > values.length) {
            return null;
        }
        return values[i - 1];
    }
}
