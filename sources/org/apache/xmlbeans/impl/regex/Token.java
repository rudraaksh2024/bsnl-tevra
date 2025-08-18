package org.apache.xmlbeans.impl.regex;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.text.Typography;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.logging.log4j.message.ParameterizedMessage;

class Token implements Serializable {
    static final int ANCHOR = 8;
    static final int BACKREFERENCE = 12;
    static final int CHAR = 0;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_SYMBOL = 37;
    static final int CLOSURE = 3;
    static final int CONCAT = 1;
    static final int CONDITION = 26;
    static final boolean COUNTTOKENS = true;
    static final int DOT = 11;
    static final int EMPTY = 7;
    static final int FC_ANY = 2;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIERGROUP = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    private static final int NONBMP_BLOCK_START = 84;
    static final int NONGREEDYCLOSURE = 9;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int RANGE = 4;
    static final int STRING = 10;
    static final int UNION = 2;
    static final int UTF16_MAX = 1114111;
    private static final String[] blockNames = {"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
    static final String blockRanges = "\u0000ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ԰֏֐׿؀ۿ܀ݏހ޿ऀॿঀ৿਀੿઀૿଀୿஀௿ఀ౿ಀ೿ഀൿ඀෿฀๿຀໿ༀ࿿က႟Ⴀჿᄀᇿሀ፿Ꭰ᏿᐀ᙿ ᚟ᚠ᛿ក៿᠀᢯Ḁỿἀ῿ ⁯⁰₟₠⃏⃐⃿℀⅏⅐↏←⇿∀⋿⌀⏿␀␿⑀⑟①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀⻿⼀⿟⿰⿿　〿぀ゟ゠ヿ㄀ㄯ㄰㆏㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ꒏꒐꓏가힣豈﫿ﬀﭏﭐ﷿︠︯︰﹏﹐﹯ﹰ﻾﻿﻿＀￯";
    private static final Hashtable categories = new Hashtable();
    private static final Hashtable categories2 = new Hashtable();
    private static final String[] categoryNames = {"Cn", "Lu", "Ll", "Lt", "Lm", "Lo", "Mn", "Me", "Mc", "Nd", "Nl", "No", "Zs", "Zl", "Zp", "Cc", "Cf", null, "Co", "Cs", "Pd", "Ps", "Pe", "Pc", "Po", "Sm", "Sc", "Sk", "So", "Pi", "Pf", "L", "M", "N", "Z", "C", "P", ExifInterface.LATITUDE_SOUTH};
    static final int[] nonBMPBlockRanges = {66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, 131072, 173782, 194560, 195103, 917504, 917631};
    static Hashtable nonxs = null;
    static Token token_0to9 = null;
    private static Token token_ccs = null;
    static Token token_dot = new Token(11);
    static Token token_empty = new Token(7);
    private static Token token_grapheme = null;
    static Token token_linebeginning = createAnchor(94);
    static Token token_linebeginning2 = createAnchor(64);
    static Token token_lineend = createAnchor(36);
    static Token token_not_0to9 = complementRanges(token_0to9);
    static Token token_not_spaces = complementRanges(token_spaces);
    static Token token_not_wordchars = complementRanges(token_wordchars);
    static Token token_not_wordedge = createAnchor(66);
    static Token token_spaces = null;
    static Token token_stringbeginning = createAnchor(65);
    static Token token_stringend = createAnchor(122);
    static Token token_stringend2 = createAnchor(90);
    static Token token_wordbeginning = createAnchor(60);
    static Token token_wordchars = null;
    static Token token_wordedge = createAnchor(98);
    static Token token_wordend = createAnchor(62);
    static int tokens = 0;
    static final String viramaString = "्্੍્୍்్್്ฺ྄";
    int type;

    private static final boolean isSet(int i, int i2) {
        return (i & i2) == i2;
    }

    /* access modifiers changed from: package-private */
    public int getChar() {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Token getChild(int i) {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getMax() {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getMin() {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getParenNumber() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getReferenceNumber() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public String getString() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setMax(int i) {
    }

    /* access modifiers changed from: package-private */
    public void setMin(int i) {
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return 0;
    }

    static {
        RangeToken createRange = createRange();
        token_0to9 = createRange;
        createRange.addRange(48, 57);
        RangeToken createRange2 = createRange();
        token_wordchars = createRange2;
        createRange2.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        RangeToken createRange3 = createRange();
        token_spaces = createRange3;
        createRange3.addRange(9, 9);
        token_spaces.addRange(10, 10);
        token_spaces.addRange(12, 12);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(32, 32);
    }

    static ParenToken createLook(int i, Token token) {
        tokens++;
        return new ParenToken(i, token, 0);
    }

    static ParenToken createParen(Token token, int i) {
        tokens++;
        return new ParenToken(6, token, i);
    }

    static ClosureToken createClosure(Token token) {
        tokens++;
        return new ClosureToken(3, token);
    }

    static ClosureToken createNGClosure(Token token) {
        tokens++;
        return new ClosureToken(9, token);
    }

    static ConcatToken createConcat(Token token, Token token2) {
        tokens++;
        return new ConcatToken(token, token2);
    }

    static UnionToken createConcat() {
        tokens++;
        return new UnionToken(1);
    }

    static UnionToken createUnion() {
        tokens++;
        return new UnionToken(2);
    }

    static Token createEmpty() {
        return token_empty;
    }

    static RangeToken createRange() {
        tokens++;
        return new RangeToken(4);
    }

    static RangeToken createNRange() {
        tokens++;
        return new RangeToken(5);
    }

    static CharToken createChar(int i) {
        tokens++;
        return new CharToken(0, i);
    }

    private static CharToken createAnchor(int i) {
        tokens++;
        return new CharToken(8, i);
    }

    static StringToken createBackReference(int i) {
        tokens++;
        return new StringToken(12, (String) null, i);
    }

    static StringToken createString(String str) {
        tokens++;
        return new StringToken(10, str, 0);
    }

    static ModifierToken createModifierGroup(Token token, int i, int i2) {
        tokens++;
        return new ModifierToken(token, i, i2);
    }

    static ConditionToken createCondition(int i, Token token, Token token2, Token token3) {
        tokens++;
        return new ConditionToken(i, token, token2, token3);
    }

    protected Token(int i) {
        this.type = i;
    }

    /* access modifiers changed from: package-private */
    public void addChild(Token token) {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void addRange(int i, int i2) {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void mergeRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void subtractRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    /* access modifiers changed from: protected */
    public void intersectRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    static Token complementRanges(Token token) {
        return RangeToken.complementRanges(token);
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        return this.type == 11 ? "." : "";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        return getChild(0).getMinLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        if (size() != 0) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        r0 = getChild(0).getMinLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        if (r1 >= size()) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        r2 = getChild(r1).getMinLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        if (r2 >= r0) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0021, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getMinLength() {
        /*
            r3 = this;
            int r0 = r3.type
            r1 = 1
            r2 = 0
            switch(r0) {
                case 0: goto L_0x0082;
                case 1: goto L_0x006e;
                case 2: goto L_0x004a;
                case 3: goto L_0x0035;
                case 4: goto L_0x0082;
                case 5: goto L_0x0082;
                case 6: goto L_0x002c;
                case 7: goto L_0x002b;
                case 8: goto L_0x002b;
                case 9: goto L_0x0035;
                case 10: goto L_0x0022;
                case 11: goto L_0x0082;
                case 12: goto L_0x0021;
                default: goto L_0x0007;
            }
        L_0x0007:
            switch(r0) {
                case 20: goto L_0x0021;
                case 21: goto L_0x0021;
                case 22: goto L_0x0021;
                case 23: goto L_0x0021;
                case 24: goto L_0x002c;
                case 25: goto L_0x002c;
                case 26: goto L_0x004a;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Token#getMinLength(): Invalid Type: "
            r1.<init>(r2)
            int r3 = r3.type
            java.lang.StringBuilder r3 = r1.append(r3)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        L_0x0021:
            return r2
        L_0x0022:
            java.lang.String r3 = r3.getString()
            int r3 = r3.length()
            return r3
        L_0x002b:
            return r2
        L_0x002c:
            org.apache.xmlbeans.impl.regex.Token r3 = r3.getChild(r2)
            int r3 = r3.getMinLength()
            return r3
        L_0x0035:
            int r0 = r3.getMin()
            if (r0 < 0) goto L_0x0049
            int r0 = r3.getMin()
            org.apache.xmlbeans.impl.regex.Token r3 = r3.getChild(r2)
            int r3 = r3.getMinLength()
            int r0 = r0 * r3
            return r0
        L_0x0049:
            return r2
        L_0x004a:
            int r0 = r3.size()
            if (r0 != 0) goto L_0x0051
            return r2
        L_0x0051:
            org.apache.xmlbeans.impl.regex.Token r0 = r3.getChild(r2)
            int r0 = r0.getMinLength()
        L_0x0059:
            int r2 = r3.size()
            if (r1 >= r2) goto L_0x006d
            org.apache.xmlbeans.impl.regex.Token r2 = r3.getChild(r1)
            int r2 = r2.getMinLength()
            if (r2 >= r0) goto L_0x006a
            r0 = r2
        L_0x006a:
            int r1 = r1 + 1
            goto L_0x0059
        L_0x006d:
            return r0
        L_0x006e:
            r0 = r2
        L_0x006f:
            int r1 = r3.size()
            if (r2 >= r1) goto L_0x0081
            org.apache.xmlbeans.impl.regex.Token r1 = r3.getChild(r2)
            int r1 = r1.getMinLength()
            int r0 = r0 + r1
            int r2 = r2 + 1
            goto L_0x006f
        L_0x0081:
            return r0
        L_0x0082:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.Token.getMinLength():int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        return getChild(0).getMaxLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (size() != 0) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
        r0 = getChild(0).getMaxLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        if (r0 < 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0063, code lost:
        if (r2 >= size()) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        r3 = getChild(r2).getMaxLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        if (r3 >= 0) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0070, code lost:
        if (r3 <= r0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0072, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getMaxLength() {
        /*
            r4 = this;
            int r0 = r4.type
            r1 = -1
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x008f;
                case 1: goto L_0x0078;
                case 2: goto L_0x004e;
                case 3: goto L_0x0039;
                case 4: goto L_0x0037;
                case 5: goto L_0x0037;
                case 6: goto L_0x002e;
                case 7: goto L_0x002d;
                case 8: goto L_0x002d;
                case 9: goto L_0x0039;
                case 10: goto L_0x0024;
                case 11: goto L_0x0037;
                case 12: goto L_0x0023;
                default: goto L_0x0008;
            }
        L_0x0008:
            switch(r0) {
                case 20: goto L_0x0022;
                case 21: goto L_0x0022;
                case 22: goto L_0x0022;
                case 23: goto L_0x0022;
                case 24: goto L_0x002e;
                case 25: goto L_0x002e;
                case 26: goto L_0x004e;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Token#getMaxLength(): Invalid Type: "
            r1.<init>(r2)
            int r4 = r4.type
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            throw r0
        L_0x0022:
            return r3
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.String r4 = r4.getString()
            int r4 = r4.length()
            return r4
        L_0x002d:
            return r3
        L_0x002e:
            org.apache.xmlbeans.impl.regex.Token r4 = r4.getChild(r3)
            int r4 = r4.getMaxLength()
            return r4
        L_0x0037:
            r4 = 2
            return r4
        L_0x0039:
            int r0 = r4.getMax()
            if (r0 < 0) goto L_0x004d
            int r0 = r4.getMax()
            org.apache.xmlbeans.impl.regex.Token r4 = r4.getChild(r3)
            int r4 = r4.getMaxLength()
            int r0 = r0 * r4
            return r0
        L_0x004d:
            return r1
        L_0x004e:
            int r0 = r4.size()
            if (r0 != 0) goto L_0x0055
            return r3
        L_0x0055:
            org.apache.xmlbeans.impl.regex.Token r0 = r4.getChild(r3)
            int r0 = r0.getMaxLength()
        L_0x005d:
            if (r0 < 0) goto L_0x0076
            int r3 = r4.size()
            if (r2 >= r3) goto L_0x0076
            org.apache.xmlbeans.impl.regex.Token r3 = r4.getChild(r2)
            int r3 = r3.getMaxLength()
            if (r3 >= 0) goto L_0x0070
            goto L_0x0077
        L_0x0070:
            if (r3 <= r0) goto L_0x0073
            r0 = r3
        L_0x0073:
            int r2 = r2 + 1
            goto L_0x005d
        L_0x0076:
            r1 = r0
        L_0x0077:
            return r1
        L_0x0078:
            r0 = r3
        L_0x0079:
            int r2 = r4.size()
            if (r3 >= r2) goto L_0x008e
            org.apache.xmlbeans.impl.regex.Token r2 = r4.getChild(r3)
            int r2 = r2.getMaxLength()
            if (r2 >= 0) goto L_0x008a
            return r1
        L_0x008a:
            int r0 = r0 + r2
            int r3 = r3 + 1
            goto L_0x0079
        L_0x008e:
            return r0
        L_0x008f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.Token.getMaxLength():int");
    }

    /* access modifiers changed from: package-private */
    public final int analyzeFirstCharacter(RangeToken rangeToken, int i) {
        int i2 = this.type;
        int i3 = 0;
        switch (i2) {
            case 0:
                int i4 = getChar();
                rangeToken.addRange(i4, i4);
                if (i4 < 65536 && isSet(i, 2)) {
                    char upperCase = Character.toUpperCase((char) i4);
                    rangeToken.addRange(upperCase, upperCase);
                    char lowerCase = Character.toLowerCase((char) upperCase);
                    rangeToken.addRange(lowerCase, lowerCase);
                }
                return 1;
            case 1:
                int i5 = 0;
                while (i3 < size() && (i5 = getChild(i3).analyzeFirstCharacter(rangeToken, i)) == 0) {
                    i3++;
                }
                return i5;
            case 2:
                if (size() == 0) {
                    return 0;
                }
                int i6 = 0;
                int i7 = 0;
                boolean z = false;
                while (i6 < size() && (i7 = getChild(i6).analyzeFirstCharacter(rangeToken, i)) != 2) {
                    if (i7 == 0) {
                        z = true;
                    }
                    i6++;
                }
                if (z) {
                    return 0;
                }
                return i7;
            case 3:
            case 9:
                getChild(0).analyzeFirstCharacter(rangeToken, i);
                return 0;
            case 4:
                if (isSet(i, 2)) {
                    rangeToken.mergeRanges(((RangeToken) this).getCaseInsensitiveToken());
                } else {
                    rangeToken.mergeRanges(this);
                }
                return 1;
            case 5:
                if (isSet(i, 2)) {
                    rangeToken.mergeRanges(complementRanges(((RangeToken) this).getCaseInsensitiveToken()));
                } else {
                    rangeToken.mergeRanges(complementRanges(this));
                }
                return 1;
            case 6:
                break;
            case 7:
            case 8:
                return 0;
            case 10:
                int charAt = getString().charAt(0);
                if (REUtil.isHighSurrogate(charAt) && getString().length() >= 2) {
                    char charAt2 = getString().charAt(1);
                    if (REUtil.isLowSurrogate(charAt2)) {
                        charAt = REUtil.composeFromSurrogates(charAt, charAt2);
                    }
                }
                rangeToken.addRange(charAt, charAt);
                if (charAt < 65536 && isSet(i, 2)) {
                    char upperCase2 = Character.toUpperCase((char) charAt);
                    rangeToken.addRange(upperCase2, upperCase2);
                    char lowerCase2 = Character.toLowerCase((char) upperCase2);
                    rangeToken.addRange(lowerCase2, lowerCase2);
                }
                return 1;
            case 11:
                isSet(i, 4);
                return 0;
            case 12:
                rangeToken.addRange(0, UTF16_MAX);
                return 2;
            default:
                switch (i2) {
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        return 0;
                    case 24:
                        break;
                    case 25:
                        ModifierToken modifierToken = (ModifierToken) this;
                        return getChild(0).analyzeFirstCharacter(rangeToken, (i | modifierToken.getOptions()) & (~modifierToken.getOptionsMask()));
                    case 26:
                        int analyzeFirstCharacter = getChild(0).analyzeFirstCharacter(rangeToken, i);
                        if (size() == 1) {
                            return 0;
                        }
                        if (analyzeFirstCharacter == 2) {
                            return analyzeFirstCharacter;
                        }
                        int analyzeFirstCharacter2 = getChild(1).analyzeFirstCharacter(rangeToken, i);
                        if (analyzeFirstCharacter2 == 2) {
                            return analyzeFirstCharacter2;
                        }
                        if (analyzeFirstCharacter == 0 || analyzeFirstCharacter2 == 0) {
                            return 0;
                        }
                        return 1;
                    default:
                        throw new RuntimeException("Token#analyzeHeadCharacter(): Invalid Type: " + this.type);
                }
        }
        return getChild(0).analyzeFirstCharacter(rangeToken, i);
    }

    private final boolean isShorterThan(Token token) {
        if (token == null) {
            return false;
        }
        if (this.type == 10) {
            int length = getString().length();
            if (token.type != 10) {
                throw new RuntimeException("Internal Error: Illegal type: " + token.type);
            } else if (length < token.getString().length()) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException("Internal Error: Illegal type: " + this.type);
        }
    }

    static class FixedStringContainer {
        int options = 0;
        Token token = null;

        FixedStringContainer() {
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r6.token = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003c, code lost:
        getChild(0).findFixedString(r6, r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void findFixedString(org.apache.xmlbeans.impl.regex.Token.FixedStringContainer r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.type
            r1 = 0
            r2 = 0
            switch(r0) {
                case 0: goto L_0x006e;
                case 1: goto L_0x0047;
                case 2: goto L_0x0044;
                case 3: goto L_0x0044;
                case 4: goto L_0x0044;
                case 5: goto L_0x0044;
                case 6: goto L_0x003c;
                case 7: goto L_0x0044;
                case 8: goto L_0x0044;
                case 9: goto L_0x0044;
                case 10: goto L_0x0037;
                case 11: goto L_0x0044;
                case 12: goto L_0x0044;
                default: goto L_0x0007;
            }
        L_0x0007:
            switch(r0) {
                case 20: goto L_0x0044;
                case 21: goto L_0x0044;
                case 22: goto L_0x0044;
                case 23: goto L_0x0044;
                case 24: goto L_0x003c;
                case 25: goto L_0x0021;
                case 26: goto L_0x0044;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Token#findFixedString(): Invalid Type: "
            r7.<init>(r0)
            int r5 = r5.type
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            throw r6
        L_0x0021:
            r0 = r5
            org.apache.xmlbeans.impl.regex.Token$ModifierToken r0 = (org.apache.xmlbeans.impl.regex.Token.ModifierToken) r0
            int r1 = r0.getOptions()
            r7 = r7 | r1
            int r0 = r0.getOptionsMask()
            int r0 = ~r0
            r7 = r7 & r0
            org.apache.xmlbeans.impl.regex.Token r5 = r5.getChild(r2)
            r5.findFixedString(r6, r7)
            return
        L_0x0037:
            r6.token = r5
            r6.options = r7
            return
        L_0x003c:
            org.apache.xmlbeans.impl.regex.Token r5 = r5.getChild(r2)
            r5.findFixedString(r6, r7)
            return
        L_0x0044:
            r6.token = r1
            return
        L_0x0047:
            r0 = r2
        L_0x0048:
            int r3 = r5.size()
            if (r2 >= r3) goto L_0x0069
            org.apache.xmlbeans.impl.regex.Token r3 = r5.getChild(r2)
            r3.findFixedString(r6, r7)
            if (r1 == 0) goto L_0x005f
            org.apache.xmlbeans.impl.regex.Token r3 = r6.token
            boolean r3 = r1.isShorterThan(r3)
            if (r3 == 0) goto L_0x0066
        L_0x005f:
            org.apache.xmlbeans.impl.regex.Token r0 = r6.token
            int r1 = r6.options
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0066:
            int r2 = r2 + 1
            goto L_0x0048
        L_0x0069:
            r6.token = r1
            r6.options = r0
            return
        L_0x006e:
            r6.token = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.Token.findFixedString(org.apache.xmlbeans.impl.regex.Token$FixedStringContainer, int):void");
    }

    /* access modifiers changed from: package-private */
    public boolean match(int i) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    protected static RangeToken getRange(String str, boolean z) {
        String str2 = str;
        Hashtable hashtable = categories;
        if (hashtable.size() == 0) {
            synchronized (hashtable) {
                int length = categoryNames.length;
                Token[] tokenArr = new Token[length];
                for (int i = 0; i < length; i++) {
                    tokenArr[i] = createRange();
                }
                int i2 = 0;
                while (true) {
                    char c = '\"';
                    if (i2 < 65536) {
                        int type2 = Character.getType((char) i2);
                        if (type2 == 21 || type2 == 22) {
                            if (i2 == 171 || i2 == 8216 || i2 == 8219 || i2 == 8220 || i2 == 8223 || i2 == 8249) {
                                type2 = 29;
                            }
                            if (i2 == 187 || i2 == 8217 || i2 == 8221 || i2 == 8250) {
                                type2 = 30;
                            }
                        }
                        tokenArr[type2].addRange(i2, i2);
                        switch (type2) {
                            case 0:
                            case 15:
                            case 16:
                            case 18:
                            case 19:
                                c = '#';
                                break;
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                c = 31;
                                break;
                            case 6:
                            case 7:
                            case 8:
                                c = ' ';
                                break;
                            case 9:
                            case 10:
                            case 11:
                                c = '!';
                                break;
                            case 12:
                            case 13:
                            case 14:
                                break;
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 29:
                            case 30:
                                c = Typography.dollar;
                                break;
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                                c = '%';
                                break;
                            default:
                                throw new RuntimeException("org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type2);
                        }
                        tokenArr[c].addRange(i2, i2);
                        i2++;
                    } else {
                        tokenArr[0].addRange(65536, UTF16_MAX);
                        for (int i3 = 0; i3 < length; i3++) {
                            String[] strArr = categoryNames;
                            if (strArr[i3] != null) {
                                if (i3 == 0) {
                                    tokenArr[i3].addRange(65536, UTF16_MAX);
                                }
                                categories.put(strArr[i3], tokenArr[i3]);
                                categories2.put(strArr[i3], complementRanges(tokenArr[i3]));
                            }
                        }
                        StringBuilder sb = new StringBuilder(50);
                        int i4 = 0;
                        while (true) {
                            String[] strArr2 = blockNames;
                            if (i4 < strArr2.length) {
                                RangeToken createRange = createRange();
                                if (i4 < 84) {
                                    int i5 = i4 * 2;
                                    createRange.addRange(blockRanges.charAt(i5), blockRanges.charAt(i5 + 1));
                                } else {
                                    int i6 = (i4 - 84) * 2;
                                    int[] iArr = nonBMPBlockRanges;
                                    createRange.addRange(iArr[i6], iArr[i6 + 1]);
                                }
                                String str3 = strArr2[i4];
                                if (str3.equals("Specials")) {
                                    createRange.addRange(65520, 65533);
                                }
                                if (str3.equals("Private Use")) {
                                    createRange.addRange(983040, 1048573);
                                    createRange.addRange(1048576, 1114109);
                                }
                                categories.put(str3, createRange);
                                categories2.put(str3, complementRanges(createRange));
                                sb.setLength(0);
                                sb.append("Is");
                                if (str3.indexOf(32) >= 0) {
                                    for (int i7 = 0; i7 < str3.length(); i7++) {
                                        if (str3.charAt(i7) != ' ') {
                                            sb.append(str3.charAt(i7));
                                        }
                                    }
                                } else {
                                    sb.append(str3);
                                }
                                setAlias(sb.toString(), str3, true);
                                i4++;
                            } else {
                                setAlias("ASSIGNED", "Cn", false);
                                setAlias("UNASSIGNED", "Cn", true);
                                RangeToken createRange2 = createRange();
                                createRange2.addRange(0, UTF16_MAX);
                                Hashtable hashtable2 = categories;
                                hashtable2.put(Rule.ALL, createRange2);
                                Hashtable hashtable3 = categories2;
                                hashtable3.put(Rule.ALL, complementRanges(createRange2));
                                registerNonXS("ASSIGNED");
                                registerNonXS("UNASSIGNED");
                                registerNonXS(Rule.ALL);
                                RangeToken createRange3 = createRange();
                                createRange3.mergeRanges(tokenArr[1]);
                                createRange3.mergeRanges(tokenArr[2]);
                                createRange3.mergeRanges(tokenArr[5]);
                                hashtable2.put("IsAlpha", createRange3);
                                hashtable3.put("IsAlpha", complementRanges(createRange3));
                                registerNonXS("IsAlpha");
                                RangeToken createRange4 = createRange();
                                createRange4.mergeRanges(createRange3);
                                createRange4.mergeRanges(tokenArr[9]);
                                hashtable2.put("IsAlnum", createRange4);
                                hashtable3.put("IsAlnum", complementRanges(createRange4));
                                registerNonXS("IsAlnum");
                                RangeToken createRange5 = createRange();
                                createRange5.mergeRanges(token_spaces);
                                createRange5.mergeRanges(tokenArr[34]);
                                hashtable2.put("IsSpace", createRange5);
                                hashtable3.put("IsSpace", complementRanges(createRange5));
                                registerNonXS("IsSpace");
                                RangeToken createRange6 = createRange();
                                createRange6.mergeRanges(createRange4);
                                createRange6.addRange(95, 95);
                                hashtable2.put("IsWord", createRange6);
                                hashtable3.put("IsWord", complementRanges(createRange6));
                                registerNonXS("IsWord");
                                RangeToken createRange7 = createRange();
                                createRange7.addRange(0, 127);
                                hashtable2.put("IsASCII", createRange7);
                                hashtable3.put("IsASCII", complementRanges(createRange7));
                                registerNonXS("IsASCII");
                                RangeToken createRange8 = createRange();
                                createRange8.mergeRanges(tokenArr[35]);
                                createRange8.addRange(32, 32);
                                hashtable2.put("IsGraph", complementRanges(createRange8));
                                hashtable3.put("IsGraph", createRange8);
                                registerNonXS("IsGraph");
                                RangeToken createRange9 = createRange();
                                createRange9.addRange(48, 57);
                                createRange9.addRange(65, 70);
                                createRange9.addRange(97, 102);
                                hashtable2.put("IsXDigit", complementRanges(createRange9));
                                hashtable3.put("IsXDigit", createRange9);
                                registerNonXS("IsXDigit");
                                setAlias("IsDigit", "Nd", true);
                                setAlias("IsUpper", "Lu", true);
                                setAlias("IsLower", "Ll", true);
                                setAlias("IsCntrl", "C", true);
                                setAlias("IsPrint", "C", false);
                                setAlias("IsPunct", "P", true);
                                registerNonXS("IsDigit");
                                registerNonXS("IsUpper");
                                registerNonXS("IsLower");
                                registerNonXS("IsCntrl");
                                registerNonXS("IsPrint");
                                registerNonXS("IsPunct");
                                setAlias("alpha", "IsAlpha", true);
                                setAlias("alnum", "IsAlnum", true);
                                setAlias("ascii", "IsASCII", true);
                                setAlias("cntrl", "IsCntrl", true);
                                setAlias("digit", "IsDigit", true);
                                setAlias("graph", "IsGraph", true);
                                setAlias("lower", "IsLower", true);
                                setAlias("print", "IsPrint", true);
                                setAlias("punct", "IsPunct", true);
                                setAlias("space", "IsSpace", true);
                                setAlias("upper", "IsUpper", true);
                                setAlias("word", "IsWord", true);
                                setAlias("xdigit", "IsXDigit", true);
                                registerNonXS("alpha");
                                registerNonXS("alnum");
                                registerNonXS("ascii");
                                registerNonXS("cntrl");
                                registerNonXS("digit");
                                registerNonXS("graph");
                                registerNonXS("lower");
                                registerNonXS("print");
                                registerNonXS("punct");
                                registerNonXS("space");
                                registerNonXS("upper");
                                registerNonXS("word");
                                registerNonXS("xdigit");
                            }
                        }
                    }
                }
            }
        }
        if (z) {
            return (RangeToken) categories.get(str2);
        }
        return (RangeToken) categories2.get(str2);
    }

    protected static RangeToken getRange(String str, boolean z, boolean z2) {
        RangeToken range = getRange(str, z);
        if (!z2 || range == null || !isRegisterNonXS(str)) {
            return range;
        }
        return null;
    }

    protected static void registerNonXS(String str) {
        if (nonxs == null) {
            nonxs = new Hashtable();
        }
        nonxs.put(str, str);
    }

    protected static boolean isRegisterNonXS(String str) {
        Hashtable hashtable = nonxs;
        if (hashtable == null) {
            return false;
        }
        return hashtable.containsKey(str);
    }

    private static void setAlias(String str, String str2, boolean z) {
        Hashtable hashtable = categories;
        Token token = (Token) hashtable.get(str2);
        Hashtable hashtable2 = categories2;
        Token token2 = (Token) hashtable2.get(str2);
        if (z) {
            hashtable.put(str, token);
            hashtable2.put(str, token2);
            return;
        }
        hashtable2.put(str, token);
        hashtable.put(str, token2);
    }

    static synchronized Token getGraphemePattern() {
        synchronized (Token.class) {
            Token token = token_grapheme;
            if (token != null) {
                return token;
            }
            RangeToken createRange = createRange();
            createRange.mergeRanges(getRange("ASSIGNED", true));
            createRange.subtractRanges(getRange("M", true));
            createRange.subtractRanges(getRange("C", true));
            RangeToken createRange2 = createRange();
            for (int i = 0; i < 11; i++) {
                viramaString.charAt(i);
                createRange2.addRange(i, i);
            }
            RangeToken createRange3 = createRange();
            createRange3.mergeRanges(getRange("M", true));
            createRange3.addRange(4448, 4607);
            createRange3.addRange(65438, 65439);
            UnionToken createUnion = createUnion();
            createUnion.addChild(createRange);
            createUnion.addChild(token_empty);
            UnionToken createUnion2 = createUnion();
            createUnion2.addChild(createConcat(createRange2, getRange("L", true)));
            createUnion2.addChild(createRange3);
            ConcatToken createConcat = createConcat(createUnion, createClosure(createUnion2));
            token_grapheme = createConcat;
            return createConcat;
        }
    }

    static synchronized Token getCombiningCharacterSequence() {
        synchronized (Token.class) {
            Token token = token_ccs;
            if (token != null) {
                return token;
            }
            ConcatToken createConcat = createConcat(getRange("M", false), createClosure(getRange("M", true)));
            token_ccs = createConcat;
            return createConcat;
        }
    }

    static class StringToken extends Token implements Serializable {
        int refNumber;
        String string;

        StringToken(int i, String str, int i2) {
            super(i);
            this.string = str;
            this.refNumber = i2;
        }

        /* access modifiers changed from: package-private */
        public int getReferenceNumber() {
            return this.refNumber;
        }

        /* access modifiers changed from: package-private */
        public String getString() {
            return this.string;
        }

        public String toString(int i) {
            if (this.type == 12) {
                return "\\" + this.refNumber;
            }
            return REUtil.quoteMeta(this.string);
        }
    }

    static class ConcatToken extends Token implements Serializable {
        Token child;
        Token child2;

        /* access modifiers changed from: package-private */
        public int size() {
            return 2;
        }

        ConcatToken(Token token, Token token2) {
            super(1);
            this.child = token;
            this.child2 = token2;
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            return i == 0 ? this.child : this.child2;
        }

        public String toString(int i) {
            if (this.child2.type == 3 && this.child2.getChild(0) == this.child) {
                return this.child.toString(i) + "+";
            }
            if (this.child2.type == 9 && this.child2.getChild(0) == this.child) {
                return this.child.toString(i) + "+?";
            }
            return this.child.toString(i) + this.child2.toString(i);
        }
    }

    static class CharToken extends Token implements Serializable {
        int chardata;

        CharToken(int i, int i2) {
            super(i);
            this.chardata = i2;
        }

        /* access modifiers changed from: package-private */
        public int getChar() {
            return this.chardata;
        }

        public String toString(int i) {
            int i2 = this.type;
            if (i2 == 0) {
                int i3 = this.chardata;
                if (i3 == 9) {
                    return "\\t";
                }
                if (i3 == 10) {
                    return "\\n";
                }
                if (i3 == 12) {
                    return "\\f";
                }
                if (i3 == 13) {
                    return "\\r";
                }
                if (i3 == 27) {
                    return "\\e";
                }
                if (!(i3 == 46 || i3 == 63 || i3 == 91 || i3 == 92 || i3 == 123 || i3 == 124)) {
                    switch (i3) {
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                            break;
                        default:
                            if (i3 < 65536) {
                                return "" + ((char) this.chardata);
                            }
                            String str = "0" + Integer.toHexString(this.chardata);
                            return "\\v" + str.substring(str.length() - 6, str.length());
                    }
                }
                return "\\" + ((char) this.chardata);
            } else if (i2 != 8) {
                return null;
            } else {
                if (this == Token.token_linebeginning || this == Token.token_lineend) {
                    return "" + ((char) this.chardata);
                }
                return "\\" + ((char) this.chardata);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean match(int i) {
            if (this.type == 0) {
                return i == this.chardata;
            }
            throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
        }
    }

    static class ClosureToken extends Token implements Serializable {
        Token child;
        int max;
        int min;

        /* access modifiers changed from: package-private */
        public int size() {
            return 1;
        }

        ClosureToken(int i, Token token) {
            super(i);
            this.child = token;
            setMin(-1);
            setMax(-1);
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            return this.child;
        }

        /* access modifiers changed from: package-private */
        public final void setMin(int i) {
            this.min = i;
        }

        /* access modifiers changed from: package-private */
        public final void setMax(int i) {
            this.max = i;
        }

        /* access modifiers changed from: package-private */
        public final int getMin() {
            return this.min;
        }

        /* access modifiers changed from: package-private */
        public final int getMax() {
            return this.max;
        }

        public String toString(int i) {
            if (this.type == 3) {
                if (getMin() < 0 && getMax() < 0) {
                    return this.child.toString(i) + "*";
                }
                if (getMin() == getMax()) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + VectorFormat.DEFAULT_SUFFIX;
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + "," + getMax() + VectorFormat.DEFAULT_SUFFIX;
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + ",}";
                }
                throw new RuntimeException("Token#toString(): CLOSURE " + getMin() + ", " + getMax());
            } else if (getMin() < 0 && getMax() < 0) {
                return this.child.toString(i) + "*?";
            } else {
                if (getMin() == getMax()) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + "}?";
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + "," + getMax() + "}?";
                }
                if (getMin() >= 0 && getMax() < 0) {
                    return this.child.toString(i) + VectorFormat.DEFAULT_PREFIX + getMin() + ",}?";
                }
                throw new RuntimeException("Token#toString(): NONGREEDYCLOSURE " + getMin() + ", " + getMax());
            }
        }
    }

    static class ParenToken extends Token implements Serializable {
        Token child;
        int parennumber;

        /* access modifiers changed from: package-private */
        public int size() {
            return 1;
        }

        ParenToken(int i, Token token, int i2) {
            super(i);
            this.child = token;
            this.parennumber = i2;
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            return this.child;
        }

        /* access modifiers changed from: package-private */
        public int getParenNumber() {
            return this.parennumber;
        }

        public String toString(int i) {
            int i2 = this.type;
            if (i2 != 6) {
                switch (i2) {
                    case 20:
                        return "(?=" + this.child.toString(i) + ")";
                    case 21:
                        return "(?!" + this.child.toString(i) + ")";
                    case 22:
                        return "(?<=" + this.child.toString(i) + ")";
                    case 23:
                        return "(?<!" + this.child.toString(i) + ")";
                    case 24:
                        return "(?>" + this.child.toString(i) + ")";
                    default:
                        return null;
                }
            } else if (this.parennumber == 0) {
                return "(?:" + this.child.toString(i) + ")";
            } else {
                return "(" + this.child.toString(i) + ")";
            }
        }
    }

    static class ConditionToken extends Token implements Serializable {
        Token condition;
        Token no;
        int refNumber;
        Token yes;

        ConditionToken(int i, Token token, Token token2, Token token3) {
            super(26);
            this.refNumber = i;
            this.condition = token;
            this.yes = token2;
            this.no = token3;
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.no == null ? 1 : 2;
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            if (i == 0) {
                return this.yes;
            }
            if (i == 1) {
                return this.no;
            }
            throw new RuntimeException("Internal Error: " + i);
        }

        public String toString(int i) {
            String str;
            if (this.refNumber > 0) {
                str = "(?(" + this.refNumber + ")";
            } else if (this.condition.type == 8) {
                str = "(?(" + this.condition + ")";
            } else {
                str = "(?" + this.condition;
            }
            if (this.no == null) {
                return str + this.yes + ")";
            }
            return str + this.yes + "|" + this.no + ")";
        }
    }

    static class ModifierToken extends Token implements Serializable {
        int add;
        Token child;
        int mask;

        /* access modifiers changed from: package-private */
        public int size() {
            return 1;
        }

        ModifierToken(Token token, int i, int i2) {
            super(25);
            this.child = token;
            this.add = i;
            this.mask = i2;
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            return this.child;
        }

        /* access modifiers changed from: package-private */
        public int getOptions() {
            return this.add;
        }

        /* access modifiers changed from: package-private */
        public int getOptionsMask() {
            return this.mask;
        }

        public String toString(int i) {
            String str;
            StringBuilder sb = new StringBuilder("(?");
            int i2 = this.add;
            String str2 = "";
            if (i2 == 0) {
                str = str2;
            } else {
                str = REUtil.createOptionString(i2);
            }
            StringBuilder append = sb.append(str);
            int i3 = this.mask;
            if (i3 != 0) {
                str2 = REUtil.createOptionString(i3);
            }
            return append.append(str2).append(ParameterizedMessage.ERROR_MSG_SEPARATOR).append(this.child.toString(i)).append(")").toString();
        }
    }

    static class UnionToken extends Token implements Serializable {
        Vector children;

        UnionToken(int i) {
            super(i);
        }

        /* access modifiers changed from: package-private */
        public void addChild(Token token) {
            StringBuilder sb;
            if (token != null) {
                if (this.children == null) {
                    this.children = new Vector();
                }
                if (this.type == 2) {
                    this.children.addElement(token);
                } else if (token.type == 1) {
                    for (int i = 0; i < token.size(); i++) {
                        addChild(token.getChild(i));
                    }
                } else {
                    int size = this.children.size();
                    if (size == 0) {
                        this.children.addElement(token);
                        return;
                    }
                    int i2 = size - 1;
                    Token token2 = (Token) this.children.elementAt(i2);
                    if ((token2.type == 0 || token2.type == 10) && (token.type == 0 || token.type == 10)) {
                        int length = token.type == 0 ? 2 : token.getString().length();
                        if (token2.type == 0) {
                            sb = new StringBuilder(length + 2);
                            int i3 = token2.getChar();
                            if (i3 >= 65536) {
                                sb.append(REUtil.decomposeToSurrogates(i3));
                            } else {
                                sb.append((char) i3);
                            }
                            token2 = Token.createString((String) null);
                            this.children.setElementAt(token2, i2);
                        } else {
                            sb = new StringBuilder(token2.getString().length() + length);
                            sb.append(token2.getString());
                        }
                        if (token.type == 0) {
                            int i4 = token.getChar();
                            if (i4 >= 65536) {
                                sb.append(REUtil.decomposeToSurrogates(i4));
                            } else {
                                sb.append((char) i4);
                            }
                        } else {
                            sb.append(token.getString());
                        }
                        ((StringToken) token2).string = new String(sb);
                        return;
                    }
                    this.children.addElement(token);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public int size() {
            Vector vector = this.children;
            if (vector == null) {
                return 0;
            }
            return vector.size();
        }

        /* access modifiers changed from: package-private */
        public Token getChild(int i) {
            return (Token) this.children.elementAt(i);
        }

        public String toString(int i) {
            if (this.type == 1) {
                if (this.children.size() == 2) {
                    Token child = getChild(0);
                    Token child2 = getChild(1);
                    if (child2.type == 3 && child2.getChild(0) == child) {
                        return child.toString(i) + "+";
                    }
                    if (child2.type == 9 && child2.getChild(0) == child) {
                        return child.toString(i) + "+?";
                    }
                    return child.toString(i) + child2.toString(i);
                }
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < this.children.size(); i2++) {
                    sb.append(((Token) this.children.elementAt(i2)).toString(i));
                }
                return new String(sb);
            } else if (this.children.size() == 2 && getChild(1).type == 7) {
                return getChild(0).toString(i) + "?";
            } else {
                if (this.children.size() == 2 && getChild(0).type == 7) {
                    return getChild(1).toString(i) + "??";
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(((Token) this.children.elementAt(0)).toString(i));
                for (int i3 = 1; i3 < this.children.size(); i3++) {
                    sb2.append('|');
                    sb2.append(((Token) this.children.elementAt(i3)).toString(i));
                }
                return new String(sb2);
            }
        }
    }
}
