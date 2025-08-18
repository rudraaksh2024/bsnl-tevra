package org.apache.xmlbeans.impl.regex;

import java.util.Hashtable;
import java.util.Locale;
import org.apache.xmlbeans.impl.regex.Token;

class ParserForXMLSchema extends RegexParser {
    private static final String DIGITS = "09٠٩۰۹०९০৯੦੯૦૯୦୯௧௯౦౯೦೯൦൯๐๙໐໙༠༩";
    private static final String LETTERS = "AZazÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣";
    private static final String NAMECHARS = "-.0:AZ__az··ÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁːˑ̀͠͡ͅΆΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁ҃҆ҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆֹֻֽֿֿׁׂ֑֣֡ׄׄאתװײءغـْ٠٩ٰڷںھۀێېۓە۪ۭۨ۰۹ँःअह़्॑॔क़ॣ०९ঁঃঅঌএঐওনপরললশহ়়াৄেৈো্ৗৗড়ঢ়য়ৣ০ৱਂਂਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹ਼਼ਾੂੇੈੋ੍ਖ਼ੜਫ਼ਫ਼੦ੴઁઃઅઋઍઍએઑઓનપરલળવહ઼ૅેૉો્ૠૠ૦૯ଁଃଅଌଏଐଓନପରଲଳଶହ଼ୃେୈୋ୍ୖୗଡ଼ଢ଼ୟୡ୦୯ஂஃஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹாூெைொ்ௗௗ௧௯ఁఃఅఌఎఐఒనపళవహాౄెైొ్ౕౖౠౡ౦౯ಂಃಅಌಎಐಒನಪಳವಹಾೄೆೈೊ್ೕೖೞೞೠೡ೦೯ംഃഅഌഎഐഒനപഹാൃെൈൊ്ൗൗൠൡ൦൯กฮะฺเ๎๐๙ກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະູົຽເໄໆໆ່ໍ໐໙༘༙༠༩༹༹༵༵༷༷༾ཇཉཀྵ྄ཱ྆ྋྐྕྗྗྙྭྱྷྐྵྐྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼ⃐⃜⃡⃡ΩΩKÅ℮℮ↀↂ々々〇〇〡〯〱〵ぁゔ゙゚ゝゞァヺーヾㄅㄬ一龥가힣";
    private static final String SPACES = "\t\n\r\r  ";
    private static Hashtable ranges;
    private static Hashtable ranges2;

    /* access modifiers changed from: package-private */
    public boolean checkQuestion(int i) {
        return false;
    }

    public ParserForXMLSchema() {
    }

    public ParserForXMLSchema(Locale locale) {
    }

    /* access modifiers changed from: package-private */
    public Token processCaret() throws ParseException {
        next();
        return Token.createChar(94);
    }

    /* access modifiers changed from: package-private */
    public Token processDollar() throws ParseException {
        next();
        return Token.createChar(36);
    }

    /* access modifiers changed from: package-private */
    public Token processLookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processNegativelookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processLookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processNegativelookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_A() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_Z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_b() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_B() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_lt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_gt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processStar(Token token) throws ParseException {
        next();
        return Token.createClosure(token);
    }

    /* access modifiers changed from: package-private */
    public Token processPlus(Token token) throws ParseException {
        next();
        return Token.createConcat(token, Token.createClosure(token));
    }

    /* access modifiers changed from: package-private */
    public Token processQuestion(Token token) throws ParseException {
        next();
        Token.UnionToken createUnion = Token.createUnion();
        createUnion.addChild(token);
        createUnion.addChild(Token.createEmpty());
        return createUnion;
    }

    /* access modifiers changed from: package-private */
    public Token processParen() throws ParseException {
        next();
        Token.ParenToken createParen = Token.createParen(parseRegex(), 0);
        if (read() == 7) {
            next();
            return createParen;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processParen2() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processCondition() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processModifiers() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processIndependent() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_c() throws ParseException {
        next();
        return getTokenForShorthand(99);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_C() throws ParseException {
        next();
        return getTokenForShorthand(67);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_i() throws ParseException {
        next();
        return getTokenForShorthand(105);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_I() throws ParseException {
        next();
        return getTokenForShorthand(73);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_g() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_X() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    /* access modifiers changed from: package-private */
    public Token processBackreference() throws ParseException {
        throw ex("parser.process.1", this.offset - 4);
    }

    /* access modifiers changed from: package-private */
    public int processCIinCharacterClass(RangeToken rangeToken, int i) {
        rangeToken.mergeRanges(getTokenForShorthand(i));
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0190, code lost:
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a6, code lost:
        if (r10 < 0) goto L_0x00a0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x018c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.impl.regex.RangeToken parseCharacterClass(boolean r17) throws org.apache.xmlbeans.impl.regex.ParseException {
        /*
            r16 = this;
            r0 = r16
            r1 = 1
            r0.setContext(r1)
            r16.next()
            int r2 = r16.read()
            r3 = 0
            if (r2 != 0) goto L_0x0029
            int r2 = r0.chardata
            r4 = 94
            if (r2 != r4) goto L_0x0029
            r16.next()
            org.apache.xmlbeans.impl.regex.RangeToken r2 = org.apache.xmlbeans.impl.regex.Token.createRange()
            r4 = 1114111(0x10ffff, float:1.561202E-39)
            r2.addRange(r3, r4)
            org.apache.xmlbeans.impl.regex.RangeToken r4 = org.apache.xmlbeans.impl.regex.Token.createRange()
            r5 = r1
            goto L_0x002f
        L_0x0029:
            org.apache.xmlbeans.impl.regex.RangeToken r4 = org.apache.xmlbeans.impl.regex.Token.createRange()
            r2 = 0
            r5 = r3
        L_0x002f:
            r6 = r1
        L_0x0030:
            int r7 = r16.read()
            java.lang.String r8 = "parser.cc.2"
            if (r7 == r1) goto L_0x0190
            r9 = 93
            if (r7 != 0) goto L_0x0049
            int r10 = r0.chardata
            if (r10 != r9) goto L_0x0049
            if (r6 != 0) goto L_0x0049
            if (r5 == 0) goto L_0x0190
            r2.subtractRanges(r4)
            goto L_0x0191
        L_0x0049:
            int r10 = r0.chardata
            r11 = 24
            r12 = 10
            if (r7 != r12) goto L_0x00a9
            r13 = 67
            if (r10 == r13) goto L_0x00a2
            r13 = 68
            if (r10 == r13) goto L_0x0099
            r13 = 73
            if (r10 == r13) goto L_0x00a2
            r13 = 80
            if (r10 == r13) goto L_0x0086
            r13 = 83
            if (r10 == r13) goto L_0x0099
            r13 = 87
            if (r10 == r13) goto L_0x0099
            r13 = 105(0x69, float:1.47E-43)
            if (r10 == r13) goto L_0x00a2
            r13 = 112(0x70, float:1.57E-43)
            if (r10 == r13) goto L_0x0086
            r13 = 115(0x73, float:1.61E-43)
            if (r10 == r13) goto L_0x0099
            r13 = 119(0x77, float:1.67E-43)
            if (r10 == r13) goto L_0x0099
            r13 = 99
            if (r10 == r13) goto L_0x00a2
            r13 = 100
            if (r10 == r13) goto L_0x0099
            int r10 = r16.decodeEscaped()
            goto L_0x00d0
        L_0x0086:
            int r13 = r0.offset
            org.apache.xmlbeans.impl.regex.RangeToken r14 = r0.processBacksolidus_pP(r10)
            if (r14 == 0) goto L_0x0092
            r4.mergeRanges(r14)
            goto L_0x00a0
        L_0x0092:
            java.lang.String r1 = "parser.atom.5"
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r1, r13)
            throw r0
        L_0x0099:
            org.apache.xmlbeans.impl.regex.Token r13 = r0.getTokenForShorthand(r10)
            r4.mergeRanges(r13)
        L_0x00a0:
            r13 = r1
            goto L_0x00d1
        L_0x00a2:
            int r10 = r0.processCIinCharacterClass(r4, r10)
            if (r10 >= 0) goto L_0x00d0
            goto L_0x00a0
        L_0x00a9:
            if (r7 != r11) goto L_0x00d0
            if (r6 != 0) goto L_0x00d0
            if (r5 == 0) goto L_0x00b3
            r2.subtractRanges(r4)
            goto L_0x00b4
        L_0x00b3:
            r2 = r4
        L_0x00b4:
            org.apache.xmlbeans.impl.regex.RangeToken r4 = r0.parseCharacterClass(r3)
            r2.subtractRanges(r4)
            int r4 = r16.read()
            if (r4 != 0) goto L_0x00c7
            int r4 = r0.chardata
            if (r4 != r9) goto L_0x00c7
            goto L_0x0191
        L_0x00c7:
            java.lang.String r1 = "parser.cc.5"
            int r2 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r1, r2)
            throw r0
        L_0x00d0:
            r13 = r3
        L_0x00d1:
            r16.next()
            if (r13 != 0) goto L_0x018c
            java.lang.String r13 = "parser.cc.7"
            java.lang.String r14 = "parser.cc.6"
            r15 = 91
            java.lang.String r3 = "parser.cc.8"
            r12 = 45
            if (r7 != 0) goto L_0x010a
            if (r10 == r15) goto L_0x0101
            if (r10 == r9) goto L_0x00f8
            if (r10 != r12) goto L_0x010a
            if (r6 != 0) goto L_0x010a
            int r6 = r0.chardata
            if (r6 != r9) goto L_0x00ef
            goto L_0x010a
        L_0x00ef:
            int r1 = r0.offset
            int r1 = r1 + -2
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r3, r1)
            throw r0
        L_0x00f8:
            int r1 = r0.offset
            int r1 = r1 + -2
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r13, r1)
            throw r0
        L_0x0101:
            int r1 = r0.offset
            int r1 = r1 + -2
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r14, r1)
            throw r0
        L_0x010a:
            int r6 = r16.read()
            if (r6 != 0) goto L_0x0189
            int r6 = r0.chardata
            if (r6 == r12) goto L_0x0116
            goto L_0x0189
        L_0x0116:
            r16.next()
            int r6 = r16.read()
            if (r6 == r1) goto L_0x0182
            if (r6 == r11) goto L_0x017a
            if (r6 != 0) goto L_0x012e
            int r7 = r0.chardata
            if (r7 != r9) goto L_0x012e
            r4.addRange(r10, r10)
            r4.addRange(r12, r12)
            goto L_0x018c
        L_0x012e:
            int r7 = r0.chardata
            if (r6 != 0) goto L_0x0159
            if (r7 == r15) goto L_0x0151
            if (r7 == r9) goto L_0x0149
            if (r7 != r12) goto L_0x0161
            r16.next()
            int r6 = r0.chardata
            if (r6 != r9) goto L_0x0140
            goto L_0x0161
        L_0x0140:
            int r1 = r0.offset
            int r1 = r1 + -2
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r3, r1)
            throw r0
        L_0x0149:
            int r2 = r0.offset
            int r2 = r2 - r1
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r13, r2)
            throw r0
        L_0x0151:
            int r2 = r0.offset
            int r2 = r2 - r1
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r14, r2)
            throw r0
        L_0x0159:
            r3 = 10
            if (r6 != r3) goto L_0x0161
            int r7 = r16.decodeEscaped()
        L_0x0161:
            if (r7 != r12) goto L_0x0167
            int r3 = r0.chardata
            if (r3 == r9) goto L_0x016a
        L_0x0167:
            r16.next()
        L_0x016a:
            if (r10 > r7) goto L_0x0170
            r4.addRange(r10, r7)
            goto L_0x018c
        L_0x0170:
            int r2 = r0.offset
            int r2 = r2 - r1
            java.lang.String r1 = "parser.ope.3"
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r1, r2)
            throw r0
        L_0x017a:
            int r2 = r0.offset
            int r2 = r2 - r1
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r3, r2)
            throw r0
        L_0x0182:
            int r1 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r8, r1)
            throw r0
        L_0x0189:
            r4.addRange(r10, r10)
        L_0x018c:
            r3 = 0
            r6 = 0
            goto L_0x0030
        L_0x0190:
            r2 = r4
        L_0x0191:
            int r3 = r16.read()
            if (r3 == r1) goto L_0x01a5
            r2.sortRanges()
            r2.compactRanges()
            r1 = 0
            r0.setContext(r1)
            r16.next()
            return r2
        L_0x01a5:
            int r1 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r8, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.ParserForXMLSchema.parseCharacterClass(boolean):org.apache.xmlbeans.impl.regex.RangeToken");
    }

    /* access modifiers changed from: protected */
    public RangeToken parseSetOperations() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token getTokenForShorthand(int i) {
        if (i == 67) {
            return getRange("xml:isNameChar", false);
        }
        if (i == 68) {
            return getRange("xml:isDigit", false);
        }
        if (i == 73) {
            return getRange("xml:isInitialNameChar", false);
        }
        if (i == 83) {
            return getRange("xml:isSpace", false);
        }
        if (i == 87) {
            return getRange("xml:isWord", false);
        }
        if (i == 105) {
            return getRange("xml:isInitialNameChar", true);
        }
        if (i == 115) {
            return getRange("xml:isSpace", true);
        }
        if (i == 119) {
            return getRange("xml:isWord", true);
        }
        if (i == 99) {
            return getRange("xml:isNameChar", true);
        }
        if (i == 100) {
            return getRange("xml:isDigit", true);
        }
        throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(i, 16));
    }

    /* access modifiers changed from: package-private */
    public int decodeEscaped() throws ParseException {
        if (read() == 10) {
            int i = this.chardata;
            if (!(i == 45 || i == 46 || i == 63)) {
                if (i == 110) {
                    return 10;
                }
                if (i == 114) {
                    return 13;
                }
                if (i == 116) {
                    return 9;
                }
                switch (i) {
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                        break;
                    default:
                        switch (i) {
                            case 91:
                            case 92:
                            case 93:
                            case 94:
                                break;
                            default:
                                switch (i) {
                                    case 123:
                                    case 124:
                                    case 125:
                                        break;
                                    default:
                                        throw ex("parser.process.1", this.offset - 2);
                                }
                        }
                }
            }
            return i;
        }
        throw ex("parser.next.1", this.offset - 1);
    }

    protected static synchronized RangeToken getRange(String str, boolean z) {
        RangeToken rangeToken;
        synchronized (ParserForXMLSchema.class) {
            if (ranges == null) {
                ranges = new Hashtable();
                ranges2 = new Hashtable();
                RangeToken createRange = Token.createRange();
                setupRange(createRange, SPACES);
                ranges.put("xml:isSpace", createRange);
                ranges2.put("xml:isSpace", Token.complementRanges(createRange));
                RangeToken createRange2 = Token.createRange();
                setupRange(createRange2, DIGITS);
                ranges.put("xml:isDigit", createRange2);
                ranges2.put("xml:isDigit", Token.complementRanges(createRange2));
                RangeToken createRange3 = Token.createRange();
                setupRange(createRange3, DIGITS);
                ranges.put("xml:isDigit", createRange3);
                ranges2.put("xml:isDigit", Token.complementRanges(createRange3));
                RangeToken createRange4 = Token.createRange();
                setupRange(createRange4, LETTERS);
                createRange4.mergeRanges((Token) ranges.get("xml:isDigit"));
                ranges.put("xml:isWord", createRange4);
                ranges2.put("xml:isWord", Token.complementRanges(createRange4));
                RangeToken createRange5 = Token.createRange();
                setupRange(createRange5, NAMECHARS);
                ranges.put("xml:isNameChar", createRange5);
                ranges2.put("xml:isNameChar", Token.complementRanges(createRange5));
                RangeToken createRange6 = Token.createRange();
                setupRange(createRange6, LETTERS);
                createRange6.addRange(95, 95);
                createRange6.addRange(58, 58);
                ranges.put("xml:isInitialNameChar", createRange6);
                ranges2.put("xml:isInitialNameChar", Token.complementRanges(createRange6));
            }
            if (z) {
                rangeToken = (RangeToken) ranges.get(str);
            } else {
                rangeToken = (RangeToken) ranges2.get(str);
            }
        }
        return rangeToken;
    }

    static void setupRange(Token token, String str) {
        int length = str.length();
        for (int i = 0; i < length; i += 2) {
            token.addRange(str.charAt(i), str.charAt(i + 1));
        }
    }
}
