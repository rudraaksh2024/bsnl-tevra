package org.apache.xmlbeans.impl.regex;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import org.apache.xmlbeans.impl.regex.Token;

class RegexParser {
    private static final String BUNDLE_PKG = "org.apache.xmlbeans.impl.regex.message";
    protected static final int S_INBRACKETS = 1;
    protected static final int S_INXBRACKETS = 2;
    protected static final int S_NORMAL = 0;
    static final int T_BACKSOLIDUS = 10;
    static final int T_CARET = 11;
    static final int T_CHAR = 0;
    static final int T_COMMENT = 21;
    static final int T_CONDITION = 23;
    static final int T_DOLLAR = 12;
    static final int T_DOT = 8;
    static final int T_EOF = 1;
    static final int T_INDEPENDENT = 18;
    static final int T_LBRACKET = 9;
    static final int T_LOOKAHEAD = 14;
    static final int T_LOOKBEHIND = 16;
    static final int T_LPAREN = 6;
    static final int T_LPAREN2 = 13;
    static final int T_MODIFIERS = 22;
    static final int T_NEGATIVELOOKAHEAD = 15;
    static final int T_NEGATIVELOOKBEHIND = 17;
    static final int T_OR = 2;
    static final int T_PLUS = 4;
    static final int T_POSIX_CHARCLASS_START = 20;
    static final int T_QUESTION = 5;
    static final int T_RPAREN = 7;
    static final int T_SET_OPERATIONS = 19;
    static final int T_STAR = 3;
    static final int T_XMLSCHEMA_CC_SUBTRACTION = 24;
    int chardata;
    int context = 0;
    boolean hasBackReferences;
    int nexttoken;
    int offset;
    int options;
    int parennumber = 1;
    Vector references = null;
    String regex;
    int regexlen;
    ResourceBundle resources;

    private static final int hexChar(int i) {
        if (i < 48 || i > 102) {
            return -1;
        }
        if (i <= 57) {
            return i - 48;
        }
        int i2 = 65;
        if (i < 65) {
            return -1;
        }
        if (i > 70) {
            i2 = 97;
            if (i < 97) {
                return -1;
            }
        }
        return (i - i2) + 10;
    }

    static class ReferencePosition {
        int position;
        int refNumber;

        ReferencePosition(int i, int i2) {
            this.refNumber = i;
            this.position = i2;
        }
    }

    public RegexParser() {
        setLocale(Locale.getDefault());
    }

    public RegexParser(Locale locale) {
        setLocale(locale);
    }

    public void setLocale(Locale locale) {
        this.resources = ResourceBundle.getBundle(BUNDLE_PKG, locale, RegexParser.class.getClassLoader());
    }

    /* access modifiers changed from: package-private */
    public final ParseException ex(String str, int i) {
        return new ParseException(this.resources.getString(str), i);
    }

    private boolean isSet(int i) {
        return (this.options & i) == i;
    }

    /* access modifiers changed from: package-private */
    public synchronized Token parse(String str, int i) throws ParseException {
        Token parseRegex;
        this.options = i;
        int i2 = 0;
        this.offset = 0;
        setContext(0);
        this.parennumber = 1;
        this.hasBackReferences = false;
        this.regex = str;
        if (isSet(16)) {
            this.regex = REUtil.stripExtendedComment(this.regex);
        }
        this.regexlen = this.regex.length();
        next();
        parseRegex = parseRegex();
        int i3 = this.offset;
        if (i3 != this.regexlen) {
            throw ex("parser.parse.1", i3);
        } else if (this.references != null) {
            while (i2 < this.references.size()) {
                ReferencePosition referencePosition = (ReferencePosition) this.references.elementAt(i2);
                if (this.parennumber > referencePosition.refNumber) {
                    i2++;
                } else {
                    throw ex("parser.parse.2", referencePosition.position);
                }
            }
            this.references.removeAllElements();
        }
        return parseRegex;
    }

    /* access modifiers changed from: protected */
    public final void setContext(int i) {
        this.context = i;
    }

    /* access modifiers changed from: package-private */
    public final int read() {
        return this.nexttoken;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0188  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void next() {
        /*
            r12 = this;
            int r0 = r12.offset
            int r1 = r12.regexlen
            r2 = 1
            if (r0 < r1) goto L_0x000d
            r0 = -1
            r12.chardata = r0
            r12.nexttoken = r2
            return
        L_0x000d:
            java.lang.String r1 = r12.regex
            int r3 = r0 + 1
            r12.offset = r3
            char r0 = r1.charAt(r0)
            r12.chardata = r0
            int r1 = r12.context
            r3 = 58
            java.lang.String r4 = "parser.next.1"
            r5 = 10
            r6 = 45
            r7 = 92
            r8 = 0
            r9 = 91
            if (r1 != r2) goto L_0x00ab
            r1 = 512(0x200, float:7.175E-43)
            if (r0 == r6) goto L_0x008d
            if (r0 == r9) goto L_0x004c
            if (r0 == r7) goto L_0x0033
            goto L_0x0068
        L_0x0033:
            int r0 = r12.offset
            int r1 = r12.regexlen
            if (r0 >= r1) goto L_0x0046
            java.lang.String r1 = r12.regex
            int r2 = r0 + 1
            r12.offset = r2
            char r0 = r1.charAt(r0)
            r12.chardata = r0
            goto L_0x00a8
        L_0x0046:
            int r0 = r0 - r2
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r4, r0)
            throw r12
        L_0x004c:
            boolean r1 = r12.isSet(r1)
            if (r1 != 0) goto L_0x0068
            int r1 = r12.offset
            int r4 = r12.regexlen
            if (r1 >= r4) goto L_0x0068
            java.lang.String r4 = r12.regex
            char r1 = r4.charAt(r1)
            if (r1 != r3) goto L_0x0068
            int r0 = r12.offset
            int r0 = r0 + r2
            r12.offset = r0
            r5 = 20
            goto L_0x00a8
        L_0x0068:
            boolean r1 = org.apache.xmlbeans.impl.regex.REUtil.isHighSurrogate(r0)
            if (r1 == 0) goto L_0x008b
            int r1 = r12.offset
            int r3 = r12.regexlen
            if (r1 >= r3) goto L_0x008b
            java.lang.String r3 = r12.regex
            char r1 = r3.charAt(r1)
            boolean r3 = org.apache.xmlbeans.impl.regex.REUtil.isLowSurrogate(r1)
            if (r3 == 0) goto L_0x008b
            int r0 = org.apache.xmlbeans.impl.regex.REUtil.composeFromSurrogates(r0, r1)
            r12.chardata = r0
            int r0 = r12.offset
            int r0 = r0 + r2
            r12.offset = r0
        L_0x008b:
            r5 = r8
            goto L_0x00a8
        L_0x008d:
            boolean r0 = r12.isSet(r1)
            if (r0 == 0) goto L_0x008b
            int r0 = r12.offset
            int r1 = r12.regexlen
            if (r0 >= r1) goto L_0x008b
            java.lang.String r1 = r12.regex
            char r0 = r1.charAt(r0)
            if (r0 != r9) goto L_0x008b
            int r0 = r12.offset
            int r0 = r0 + r2
            r12.offset = r0
            r5 = 24
        L_0x00a8:
            r12.nexttoken = r5
            return
        L_0x00ab:
            r1 = 36
            if (r0 == r1) goto L_0x01c1
            r1 = 46
            if (r0 == r1) goto L_0x01be
            r1 = 63
            if (r0 == r1) goto L_0x01bc
            r10 = 94
            if (r0 == r10) goto L_0x01b9
            r10 = 124(0x7c, float:1.74E-43)
            r11 = 2
            if (r0 == r10) goto L_0x01b7
            if (r0 == r9) goto L_0x01b4
            if (r0 == r7) goto L_0x019b
            r5 = 3
            switch(r0) {
                case 40: goto L_0x00d1;
                case 41: goto L_0x00ce;
                case 42: goto L_0x01c3;
                case 43: goto L_0x00cb;
                default: goto L_0x00c8;
            }
        L_0x00c8:
            r5 = r8
            goto L_0x01c3
        L_0x00cb:
            r5 = 4
            goto L_0x01c3
        L_0x00ce:
            r5 = 7
            goto L_0x01c3
        L_0x00d1:
            int r0 = r12.offset
            int r4 = r12.regexlen
            if (r0 < r4) goto L_0x00d8
            goto L_0x00e0
        L_0x00d8:
            java.lang.String r4 = r12.regex
            char r0 = r4.charAt(r0)
            if (r0 == r1) goto L_0x00e3
        L_0x00e0:
            r5 = 6
            goto L_0x01c3
        L_0x00e3:
            int r0 = r12.offset
            int r0 = r0 + r2
            r12.offset = r0
            int r1 = r12.regexlen
            java.lang.String r4 = "parser.next.2"
            if (r0 >= r1) goto L_0x0195
            java.lang.String r1 = r12.regex
            int r7 = r0 + 1
            r12.offset = r7
            char r0 = r1.charAt(r0)
            r1 = 33
            if (r0 == r1) goto L_0x0192
            r7 = 35
            if (r0 == r7) goto L_0x016f
            if (r0 == r3) goto L_0x016c
            if (r0 == r9) goto L_0x0169
            switch(r0) {
                case 60: goto L_0x013b;
                case 61: goto L_0x0137;
                case 62: goto L_0x0133;
                default: goto L_0x0107;
            }
        L_0x0107:
            if (r0 == r6) goto L_0x012a
            r1 = 97
            if (r1 > r0) goto L_0x0111
            r1 = 122(0x7a, float:1.71E-43)
            if (r0 <= r1) goto L_0x012a
        L_0x0111:
            r1 = 65
            if (r1 > r0) goto L_0x011a
            r1 = 90
            if (r0 > r1) goto L_0x011a
            goto L_0x012a
        L_0x011a:
            r1 = 40
            if (r0 != r1) goto L_0x0122
            r5 = 23
            goto L_0x01c3
        L_0x0122:
            int r0 = r12.offset
            int r0 = r0 - r11
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r4, r0)
            throw r12
        L_0x012a:
            int r0 = r12.offset
            int r0 = r0 - r2
            r12.offset = r0
            r5 = 22
            goto L_0x01c3
        L_0x0133:
            r5 = 18
            goto L_0x01c3
        L_0x0137:
            r5 = 14
            goto L_0x01c3
        L_0x013b:
            int r0 = r12.offset
            int r2 = r12.regexlen
            if (r0 >= r2) goto L_0x0163
            java.lang.String r2 = r12.regex
            int r3 = r0 + 1
            r12.offset = r3
            char r0 = r2.charAt(r0)
            r2 = 61
            if (r0 != r2) goto L_0x0153
            r5 = 16
            goto L_0x01c3
        L_0x0153:
            if (r0 != r1) goto L_0x0159
            r5 = 17
            goto L_0x01c3
        L_0x0159:
            int r0 = r12.offset
            int r0 = r0 - r5
            java.lang.String r1 = "parser.next.3"
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r1, r0)
            throw r12
        L_0x0163:
            int r0 = r0 - r5
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r4, r0)
            throw r12
        L_0x0169:
            r5 = 19
            goto L_0x01c3
        L_0x016c:
            r5 = 13
            goto L_0x01c3
        L_0x016f:
            int r1 = r12.offset
            int r3 = r12.regexlen
            r4 = 41
            if (r1 >= r3) goto L_0x0183
            java.lang.String r0 = r12.regex
            int r3 = r1 + 1
            r12.offset = r3
            char r0 = r0.charAt(r1)
            if (r0 != r4) goto L_0x016f
        L_0x0183:
            if (r0 != r4) goto L_0x0188
            r5 = 21
            goto L_0x01c3
        L_0x0188:
            int r0 = r12.offset
            int r0 = r0 - r2
            java.lang.String r1 = "parser.next.4"
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r1, r0)
            throw r12
        L_0x0192:
            r5 = 15
            goto L_0x01c3
        L_0x0195:
            int r0 = r0 - r2
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r4, r0)
            throw r12
        L_0x019b:
            int r0 = r12.offset
            int r1 = r12.regexlen
            if (r0 >= r1) goto L_0x01ae
            java.lang.String r1 = r12.regex
            int r2 = r0 + 1
            r12.offset = r2
            char r0 = r1.charAt(r0)
            r12.chardata = r0
            goto L_0x01c3
        L_0x01ae:
            int r0 = r0 - r2
            org.apache.xmlbeans.impl.regex.ParseException r12 = r12.ex(r4, r0)
            throw r12
        L_0x01b4:
            r5 = 9
            goto L_0x01c3
        L_0x01b7:
            r5 = r11
            goto L_0x01c3
        L_0x01b9:
            r5 = 11
            goto L_0x01c3
        L_0x01bc:
            r5 = 5
            goto L_0x01c3
        L_0x01be:
            r5 = 8
            goto L_0x01c3
        L_0x01c1:
            r5 = 12
        L_0x01c3:
            r12.nexttoken = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.next():void");
    }

    /* access modifiers changed from: package-private */
    public Token parseRegex() throws ParseException {
        Token parseTerm = parseTerm();
        Token.UnionToken unionToken = null;
        while (read() == 2) {
            next();
            if (unionToken == null) {
                unionToken = Token.createUnion();
                unionToken.addChild(parseTerm);
                parseTerm = unionToken;
            }
            parseTerm.addChild(parseTerm());
        }
        return parseTerm;
    }

    /* access modifiers changed from: package-private */
    public Token parseTerm() throws ParseException {
        int read = read();
        if (read == 2 || read == 7 || read == 1) {
            return Token.createEmpty();
        }
        Token parseFactor = parseFactor();
        Token.UnionToken unionToken = null;
        while (true) {
            int read2 = read();
            if (read2 == 2 || read2 == 7 || read2 == 1) {
                return parseFactor;
            }
            if (unionToken == null) {
                unionToken = Token.createConcat();
                unionToken.addChild(parseFactor);
                parseFactor = unionToken;
            }
            unionToken.addChild(parseFactor());
        }
        return parseFactor;
    }

    /* access modifiers changed from: package-private */
    public Token processCaret() throws ParseException {
        next();
        return Token.token_linebeginning;
    }

    /* access modifiers changed from: package-private */
    public Token processDollar() throws ParseException {
        next();
        return Token.token_lineend;
    }

    /* access modifiers changed from: package-private */
    public Token processLookahead() throws ParseException {
        next();
        Token.ParenToken createLook = Token.createLook(20, parseRegex());
        if (read() == 7) {
            next();
            return createLook;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processNegativelookahead() throws ParseException {
        next();
        Token.ParenToken createLook = Token.createLook(21, parseRegex());
        if (read() == 7) {
            next();
            return createLook;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processLookbehind() throws ParseException {
        next();
        Token.ParenToken createLook = Token.createLook(22, parseRegex());
        if (read() == 7) {
            next();
            return createLook;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processNegativelookbehind() throws ParseException {
        next();
        Token.ParenToken createLook = Token.createLook(23, parseRegex());
        if (read() == 7) {
            next();
            return createLook;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_A() throws ParseException {
        next();
        return Token.token_stringbeginning;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_Z() throws ParseException {
        next();
        return Token.token_stringend2;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_z() throws ParseException {
        next();
        return Token.token_stringend;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_b() throws ParseException {
        next();
        return Token.token_wordedge;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_B() throws ParseException {
        next();
        return Token.token_not_wordedge;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_lt() throws ParseException {
        next();
        return Token.token_wordbeginning;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_gt() throws ParseException {
        next();
        return Token.token_wordend;
    }

    /* access modifiers changed from: package-private */
    public Token processStar(Token token) throws ParseException {
        next();
        if (read() != 5) {
            return Token.createClosure(token);
        }
        next();
        return Token.createNGClosure(token);
    }

    /* access modifiers changed from: package-private */
    public Token processPlus(Token token) throws ParseException {
        next();
        if (read() != 5) {
            return Token.createConcat(token, Token.createClosure(token));
        }
        next();
        return Token.createConcat(token, Token.createNGClosure(token));
    }

    /* access modifiers changed from: package-private */
    public Token processQuestion(Token token) throws ParseException {
        next();
        Token.UnionToken createUnion = Token.createUnion();
        if (read() == 5) {
            next();
            createUnion.addChild(Token.createEmpty());
            createUnion.addChild(token);
        } else {
            createUnion.addChild(token);
            createUnion.addChild(Token.createEmpty());
        }
        return createUnion;
    }

    /* access modifiers changed from: package-private */
    public boolean checkQuestion(int i) {
        return i < this.regexlen && this.regex.charAt(i) == '?';
    }

    /* access modifiers changed from: package-private */
    public Token processParen() throws ParseException {
        next();
        int i = this.parennumber;
        this.parennumber = i + 1;
        Token.ParenToken createParen = Token.createParen(parseRegex(), i);
        if (read() == 7) {
            next();
            return createParen;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processParen2() throws ParseException {
        next();
        Token.ParenToken createParen = Token.createParen(parseRegex(), 0);
        if (read() == 7) {
            next();
            return createParen;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processCondition() throws ParseException {
        Token token;
        int i;
        int i2 = this.offset;
        if (i2 + 1 < this.regexlen) {
            char charAt = this.regex.charAt(i2);
            Token token2 = null;
            if ('1' > charAt || charAt > '9') {
                if (charAt == '?') {
                    this.offset--;
                }
                next();
                Token parseFactor = parseFactor();
                int i3 = parseFactor.type;
                if (i3 != 8) {
                    switch (i3) {
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                            break;
                        default:
                            throw ex("parser.factor.5", this.offset);
                    }
                } else if (read() != 7) {
                    throw ex("parser.factor.1", this.offset - 1);
                }
                token = parseFactor;
                i = -1;
            } else {
                i = charAt - '0';
                this.hasBackReferences = true;
                if (this.references == null) {
                    this.references = new Vector();
                }
                this.references.addElement(new ReferencePosition(i, this.offset));
                int i4 = this.offset + 1;
                this.offset = i4;
                if (this.regex.charAt(i4) == ')') {
                    this.offset++;
                    token = null;
                } else {
                    throw ex("parser.factor.1", this.offset);
                }
            }
            next();
            Token parseRegex = parseRegex();
            if (parseRegex.type == 2) {
                if (parseRegex.size() == 2) {
                    token2 = parseRegex.getChild(1);
                    parseRegex = parseRegex.getChild(0);
                } else {
                    throw ex("parser.factor.6", this.offset);
                }
            }
            if (read() == 7) {
                next();
                return Token.createCondition(i, token, parseRegex, token2);
            }
            throw ex("parser.factor.1", this.offset - 1);
        }
        throw ex("parser.factor.4", i2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = r6.regex.charAt(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r1 = r6.regex.charAt(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.impl.regex.Token processModifiers() throws org.apache.xmlbeans.impl.regex.ParseException {
        /*
            r6 = this;
            r0 = 0
            r1 = -1
            r2 = r0
        L_0x0003:
            int r3 = r6.offset
            int r4 = r6.regexlen
            if (r3 >= r4) goto L_0x001e
            java.lang.String r1 = r6.regex
            char r1 = r1.charAt(r3)
            int r3 = org.apache.xmlbeans.impl.regex.REUtil.getOptionValue(r1)
            if (r3 != 0) goto L_0x0016
            goto L_0x001e
        L_0x0016:
            r2 = r2 | r3
            int r3 = r6.offset
            int r3 = r3 + 1
            r6.offset = r3
            goto L_0x0003
        L_0x001e:
            int r3 = r6.offset
            int r4 = r6.regexlen
            java.lang.String r5 = "parser.factor.2"
            if (r3 >= r4) goto L_0x00a1
            r4 = 45
            if (r1 != r4) goto L_0x0057
            int r3 = r3 + 1
            r6.offset = r3
        L_0x002e:
            int r3 = r6.offset
            int r4 = r6.regexlen
            if (r3 >= r4) goto L_0x0049
            java.lang.String r1 = r6.regex
            char r1 = r1.charAt(r3)
            int r3 = org.apache.xmlbeans.impl.regex.REUtil.getOptionValue(r1)
            if (r3 != 0) goto L_0x0041
            goto L_0x0049
        L_0x0041:
            r0 = r0 | r3
            int r3 = r6.offset
            int r3 = r3 + 1
            r6.offset = r3
            goto L_0x002e
        L_0x0049:
            int r3 = r6.offset
            int r4 = r6.regexlen
            if (r3 >= r4) goto L_0x0050
            goto L_0x0057
        L_0x0050:
            int r3 = r3 + -1
            org.apache.xmlbeans.impl.regex.ParseException r6 = r6.ex(r5, r3)
            throw r6
        L_0x0057:
            r3 = 58
            if (r1 != r3) goto L_0x0082
            int r1 = r6.offset
            int r1 = r1 + 1
            r6.offset = r1
            r6.next()
            org.apache.xmlbeans.impl.regex.Token r1 = r6.parseRegex()
            org.apache.xmlbeans.impl.regex.Token$ModifierToken r0 = org.apache.xmlbeans.impl.regex.Token.createModifierGroup(r1, r2, r0)
            int r1 = r6.read()
            r2 = 7
            if (r1 != r2) goto L_0x0077
            r6.next()
            goto L_0x0097
        L_0x0077:
            int r0 = r6.offset
            int r0 = r0 + -1
            java.lang.String r1 = "parser.factor.1"
            org.apache.xmlbeans.impl.regex.ParseException r6 = r6.ex(r1, r0)
            throw r6
        L_0x0082:
            r3 = 41
            if (r1 != r3) goto L_0x0098
            int r1 = r6.offset
            int r1 = r1 + 1
            r6.offset = r1
            r6.next()
            org.apache.xmlbeans.impl.regex.Token r6 = r6.parseRegex()
            org.apache.xmlbeans.impl.regex.Token$ModifierToken r0 = org.apache.xmlbeans.impl.regex.Token.createModifierGroup(r6, r2, r0)
        L_0x0097:
            return r0
        L_0x0098:
            java.lang.String r0 = "parser.factor.3"
            int r1 = r6.offset
            org.apache.xmlbeans.impl.regex.ParseException r6 = r6.ex(r0, r1)
            throw r6
        L_0x00a1:
            int r3 = r3 + -1
            org.apache.xmlbeans.impl.regex.ParseException r6 = r6.ex(r5, r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.processModifiers():org.apache.xmlbeans.impl.regex.Token");
    }

    /* access modifiers changed from: package-private */
    public Token processIndependent() throws ParseException {
        next();
        Token.ParenToken createLook = Token.createLook(24, parseRegex());
        if (read() == 7) {
            next();
            return createLook;
        }
        throw ex("parser.factor.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_c() throws ParseException {
        int i = this.offset;
        if (i < this.regexlen) {
            String str = this.regex;
            this.offset = i + 1;
            char charAt = str.charAt(i);
            if ((65504 & charAt) == '@') {
                next();
                return Token.createChar(charAt - '@');
            }
        }
        throw ex("parser.atom.1", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_C() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_i() throws ParseException {
        Token.CharToken createChar = Token.createChar(105);
        next();
        return createChar;
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_I() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_g() throws ParseException {
        next();
        return Token.getGraphemePattern();
    }

    /* access modifiers changed from: package-private */
    public Token processBacksolidus_X() throws ParseException {
        next();
        return Token.getCombiningCharacterSequence();
    }

    /* access modifiers changed from: package-private */
    public Token processBackreference() throws ParseException {
        int i = this.chardata - 48;
        Token.StringToken createBackReference = Token.createBackReference(i);
        this.hasBackReferences = true;
        if (this.references == null) {
            this.references = new Vector();
        }
        this.references.addElement(new ReferencePosition(i, this.offset - 2));
        next();
        return createBackReference;
    }

    /* access modifiers changed from: package-private */
    public Token parseFactor() throws ParseException {
        int i;
        char c;
        int i2;
        int i3;
        switch (read()) {
            case 10:
                int i4 = this.chardata;
                if (i4 == 60) {
                    return processBacksolidus_lt();
                }
                if (i4 == 62) {
                    return processBacksolidus_gt();
                }
                if (i4 == 90) {
                    return processBacksolidus_Z();
                }
                if (i4 == 98) {
                    return processBacksolidus_b();
                }
                if (i4 == 122) {
                    return processBacksolidus_z();
                }
                if (i4 == 65) {
                    return processBacksolidus_A();
                }
                if (i4 == 66) {
                    return processBacksolidus_B();
                }
                break;
            case 11:
                return processCaret();
            case 12:
                return processDollar();
            case 14:
                return processLookahead();
            case 15:
                return processNegativelookahead();
            case 16:
                return processLookbehind();
            case 17:
                return processNegativelookbehind();
            case 21:
                next();
                return Token.createEmpty();
        }
        Token parseAtom = parseAtom();
        int read = read();
        if (read != 0) {
            if (read == 3) {
                return processStar(parseAtom);
            }
            if (read == 4) {
                return processPlus(parseAtom);
            }
            if (read == 5) {
                return processQuestion(parseAtom);
            }
        } else if (this.chardata == 123 && (i = this.offset) < this.regexlen) {
            int i5 = i + 1;
            char charAt = this.regex.charAt(i);
            if (charAt < '0' || charAt > '9') {
                throw ex("parser.quantifier.1", this.offset);
            }
            int i6 = charAt - '0';
            while (true) {
                if (i5 < this.regexlen) {
                    int i7 = i5 + 1;
                    charAt = this.regex.charAt(i5);
                    if (charAt < '0' || charAt > '9') {
                        i5 = i7;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 >= 0) {
                            i5 = i7;
                        } else {
                            throw ex("parser.quantifier.5", this.offset);
                        }
                    }
                }
            }
            if (c != ',') {
                i2 = i6;
            } else if (i5 < this.regexlen) {
                int i8 = i5 + 1;
                c = this.regex.charAt(i5);
                if (c < '0' || c > '9') {
                    i3 = -1;
                } else {
                    i3 = c - '0';
                    while (true) {
                        if (i8 < this.regexlen) {
                            int i9 = i8 + 1;
                            c = this.regex.charAt(i8);
                            if (c < '0' || c > '9') {
                                i8 = i9;
                            } else {
                                i3 = ((i3 * 10) + c) - 48;
                                if (i3 >= 0) {
                                    i8 = i9;
                                } else {
                                    throw ex("parser.quantifier.5", this.offset);
                                }
                            }
                        }
                    }
                    if (i6 > i3) {
                        throw ex("parser.quantifier.4", this.offset);
                    }
                }
                i2 = i3;
                i5 = i8;
            } else {
                throw ex("parser.quantifier.3", this.offset);
            }
            if (c == '}') {
                if (checkQuestion(i5)) {
                    parseAtom = Token.createNGClosure(parseAtom);
                    this.offset = i5 + 1;
                } else {
                    parseAtom = Token.createClosure(parseAtom);
                    this.offset = i5;
                }
                parseAtom.setMin(i6);
                parseAtom.setMax(i2);
                next();
            } else {
                throw ex("parser.quantifier.2", this.offset);
            }
        }
        return parseAtom;
    }

    /* access modifiers changed from: package-private */
    public Token parseAtom() throws ParseException {
        Token token;
        int read = read();
        if (read == 0) {
            int i = this.chardata;
            if (i == 93 || i == 123 || i == 125) {
                throw ex("parser.atom.4", this.offset - 1);
            }
            Token.CharToken createChar = Token.createChar(i);
            int i2 = this.chardata;
            next();
            if (!REUtil.isHighSurrogate(i2) || read() != 0 || !REUtil.isLowSurrogate(this.chardata)) {
                return createChar;
            }
            Token.ParenToken createParen = Token.createParen(Token.createString(new String(new char[]{(char) i2, (char) this.chardata})), 0);
            next();
            return createParen;
        } else if (read == 6) {
            return processParen();
        } else {
            if (read == 13) {
                return processParen2();
            }
            if (read == 18) {
                return processIndependent();
            }
            if (read == 19) {
                return parseSetOperations();
            }
            if (read == 22) {
                return processModifiers();
            }
            if (read == 23) {
                return processCondition();
            }
            switch (read) {
                case 8:
                    next();
                    return Token.token_dot;
                case 9:
                    return parseCharacterClass(true);
                case 10:
                    int i3 = this.chardata;
                    if (i3 == 67) {
                        return processBacksolidus_C();
                    }
                    if (i3 != 68) {
                        if (i3 == 73) {
                            return processBacksolidus_I();
                        }
                        if (i3 != 80) {
                            if (i3 != 83) {
                                if (i3 == 105) {
                                    return processBacksolidus_i();
                                }
                                if (i3 != 110) {
                                    if (i3 != 112) {
                                        if (i3 != 87) {
                                            if (i3 != 88) {
                                                switch (i3) {
                                                    case 49:
                                                    case 50:
                                                    case 51:
                                                    case 52:
                                                    case 53:
                                                    case 54:
                                                    case 55:
                                                    case 56:
                                                    case 57:
                                                        return processBackreference();
                                                    default:
                                                        switch (i3) {
                                                            case 99:
                                                                return processBacksolidus_c();
                                                            case 100:
                                                                break;
                                                            case 101:
                                                            case 102:
                                                                break;
                                                            case 103:
                                                                return processBacksolidus_g();
                                                            default:
                                                                switch (i3) {
                                                                    case 114:
                                                                    case 116:
                                                                    case 117:
                                                                    case 118:
                                                                    case 120:
                                                                        break;
                                                                    case 115:
                                                                    case 119:
                                                                        break;
                                                                    default:
                                                                        token = Token.createChar(i3);
                                                                        break;
                                                                }
                                                        }
                                                }
                                            } else {
                                                return processBacksolidus_X();
                                            }
                                        }
                                    }
                                }
                                int decodeEscaped = decodeEscaped();
                                if (decodeEscaped < 65536) {
                                    token = Token.createChar(decodeEscaped);
                                } else {
                                    token = Token.createString(REUtil.decomposeToSurrogates(decodeEscaped));
                                }
                                next();
                                return token;
                            }
                        }
                        int i4 = this.offset;
                        token = processBacksolidus_pP(i3);
                        if (token == null) {
                            throw ex("parser.atom.5", i4);
                        }
                        next();
                        return token;
                    }
                    Token tokenForShorthand = getTokenForShorthand(i3);
                    next();
                    return tokenForShorthand;
                default:
                    throw ex("parser.atom.4", this.offset - 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public RangeToken processBacksolidus_pP(int i) throws ParseException {
        next();
        if (read() == 0 && this.chardata == 123) {
            boolean z = i == 112;
            int i2 = this.offset;
            int indexOf = this.regex.indexOf(125, i2);
            if (indexOf >= 0) {
                String substring = this.regex.substring(i2, indexOf);
                this.offset = indexOf + 1;
                return Token.getRange(substring, z, isSet(512));
            }
            throw ex("parser.atom.3", this.offset);
        }
        throw ex("parser.atom.2", this.offset - 1);
    }

    /* access modifiers changed from: package-private */
    public int processCIinCharacterClass(RangeToken rangeToken, int i) {
        return decodeEscaped();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x016e, code lost:
        if (read() == 1) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0170, code lost:
        if (r17 != false) goto L_0x0178;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0172, code lost:
        if (r6 == false) goto L_0x0178;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0174, code lost:
        r5.subtractRanges(r2);
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0178, code lost:
        r2.sortRanges();
        r2.compactRanges();
        setContext(0);
        next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0184, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x018b, code lost:
        throw ex("parser.cc.2", r0.offset);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a8, code lost:
        if (r7 < 0) goto L_0x00a1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.impl.regex.RangeToken parseCharacterClass(boolean r17) throws org.apache.xmlbeans.impl.regex.ParseException {
        /*
            r16 = this;
            r0 = r16
            r1 = 1
            r0.setContext(r1)
            r16.next()
            int r2 = r16.read()
            r3 = 94
            r4 = 0
            r5 = 0
            if (r2 != 0) goto L_0x0031
            int r2 = r0.chardata
            if (r2 != r3) goto L_0x0031
            r16.next()
            if (r17 == 0) goto L_0x0021
            org.apache.xmlbeans.impl.regex.RangeToken r2 = org.apache.xmlbeans.impl.regex.Token.createNRange()
            goto L_0x002f
        L_0x0021:
            org.apache.xmlbeans.impl.regex.RangeToken r5 = org.apache.xmlbeans.impl.regex.Token.createRange()
            r2 = 1114111(0x10ffff, float:1.561202E-39)
            r5.addRange(r4, r2)
            org.apache.xmlbeans.impl.regex.RangeToken r2 = org.apache.xmlbeans.impl.regex.Token.createRange()
        L_0x002f:
            r6 = r1
            goto L_0x0036
        L_0x0031:
            org.apache.xmlbeans.impl.regex.RangeToken r2 = org.apache.xmlbeans.impl.regex.Token.createRange()
            r6 = r4
        L_0x0036:
            r7 = r1
        L_0x0037:
            int r8 = r16.read()
            java.lang.String r9 = "parser.cc.2"
            if (r8 == r1) goto L_0x016a
            r10 = 93
            if (r8 != 0) goto L_0x004b
            int r11 = r0.chardata
            if (r11 != r10) goto L_0x004b
            if (r7 != 0) goto L_0x004b
            goto L_0x016a
        L_0x004b:
            int r7 = r0.chardata
            r11 = 10
            if (r8 != r11) goto L_0x00ab
            r8 = 67
            if (r7 == r8) goto L_0x00a4
            r8 = 68
            if (r7 == r8) goto L_0x009a
            r8 = 73
            if (r7 == r8) goto L_0x00a4
            r8 = 80
            if (r7 == r8) goto L_0x0087
            r8 = 83
            if (r7 == r8) goto L_0x009a
            r8 = 87
            if (r7 == r8) goto L_0x009a
            r8 = 105(0x69, float:1.47E-43)
            if (r7 == r8) goto L_0x00a4
            r8 = 112(0x70, float:1.57E-43)
            if (r7 == r8) goto L_0x0087
            r8 = 115(0x73, float:1.61E-43)
            if (r7 == r8) goto L_0x009a
            r8 = 119(0x77, float:1.67E-43)
            if (r7 == r8) goto L_0x009a
            r8 = 99
            if (r7 == r8) goto L_0x00a4
            r8 = 100
            if (r7 == r8) goto L_0x009a
            int r7 = r16.decodeEscaped()
            goto L_0x010e
        L_0x0087:
            int r8 = r0.offset
            org.apache.xmlbeans.impl.regex.RangeToken r12 = r0.processBacksolidus_pP(r7)
            if (r12 == 0) goto L_0x0093
            r2.mergeRanges(r12)
            goto L_0x00a1
        L_0x0093:
            java.lang.String r1 = "parser.atom.5"
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r1, r8)
            throw r0
        L_0x009a:
            org.apache.xmlbeans.impl.regex.Token r8 = r0.getTokenForShorthand(r7)
            r2.mergeRanges(r8)
        L_0x00a1:
            r8 = r1
            goto L_0x010f
        L_0x00a4:
            int r7 = r0.processCIinCharacterClass(r2, r7)
            if (r7 >= 0) goto L_0x010e
            goto L_0x00a1
        L_0x00ab:
            r12 = 20
            if (r8 != r12) goto L_0x010e
            java.lang.String r8 = r0.regex
            r12 = 58
            int r13 = r0.offset
            int r8 = r8.indexOf(r12, r13)
            java.lang.String r12 = "parser.cc.1"
            if (r8 < 0) goto L_0x0107
            java.lang.String r13 = r0.regex
            int r14 = r0.offset
            char r13 = r13.charAt(r14)
            if (r13 != r3) goto L_0x00ce
            int r13 = r0.offset
            int r13 = r13 + r1
            r0.offset = r13
            r13 = r4
            goto L_0x00cf
        L_0x00ce:
            r13 = r1
        L_0x00cf:
            java.lang.String r14 = r0.regex
            int r15 = r0.offset
            java.lang.String r14 = r14.substring(r15, r8)
            r15 = 512(0x200, float:7.175E-43)
            boolean r15 = r0.isSet(r15)
            org.apache.xmlbeans.impl.regex.RangeToken r13 = org.apache.xmlbeans.impl.regex.Token.getRange(r14, r13, r15)
            if (r13 == 0) goto L_0x00fe
            r2.mergeRanges(r13)
            int r13 = r8 + 1
            int r14 = r0.regexlen
            if (r13 >= r14) goto L_0x00f9
            java.lang.String r14 = r0.regex
            char r13 = r14.charAt(r13)
            if (r13 != r10) goto L_0x00f9
            int r8 = r8 + 2
            r0.offset = r8
            goto L_0x00a1
        L_0x00f9:
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r12, r8)
            throw r0
        L_0x00fe:
            java.lang.String r1 = "parser.cc.3"
            int r2 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r1, r2)
            throw r0
        L_0x0107:
            int r1 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r12, r1)
            throw r0
        L_0x010e:
            r8 = r4
        L_0x010f:
            r16.next()
            if (r8 != 0) goto L_0x0150
            int r8 = r16.read()
            if (r8 != 0) goto L_0x014d
            int r8 = r0.chardata
            r12 = 45
            if (r8 == r12) goto L_0x0121
            goto L_0x014d
        L_0x0121:
            r16.next()
            int r8 = r16.read()
            if (r8 == r1) goto L_0x0146
            if (r8 != 0) goto L_0x0137
            int r9 = r0.chardata
            if (r9 != r10) goto L_0x0137
            r2.addRange(r7, r7)
            r2.addRange(r12, r12)
            goto L_0x0150
        L_0x0137:
            int r9 = r0.chardata
            if (r8 != r11) goto L_0x013f
            int r9 = r16.decodeEscaped()
        L_0x013f:
            r16.next()
            r2.addRange(r7, r9)
            goto L_0x0150
        L_0x0146:
            int r1 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r9, r1)
            throw r0
        L_0x014d:
            r2.addRange(r7, r7)
        L_0x0150:
            r7 = 1024(0x400, float:1.435E-42)
            boolean r7 = r0.isSet(r7)
            if (r7 == 0) goto L_0x0167
            int r7 = r16.read()
            if (r7 != 0) goto L_0x0167
            int r7 = r0.chardata
            r8 = 44
            if (r7 != r8) goto L_0x0167
            r16.next()
        L_0x0167:
            r7 = r4
            goto L_0x0037
        L_0x016a:
            int r3 = r16.read()
            if (r3 == r1) goto L_0x0185
            if (r17 != 0) goto L_0x0178
            if (r6 == 0) goto L_0x0178
            r5.subtractRanges(r2)
            r2 = r5
        L_0x0178:
            r2.sortRanges()
            r2.compactRanges()
            r0.setContext(r4)
            r16.next()
            return r2
        L_0x0185:
            int r1 = r0.offset
            org.apache.xmlbeans.impl.regex.ParseException r0 = r0.ex(r9, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegexParser.parseCharacterClass(boolean):org.apache.xmlbeans.impl.regex.RangeToken");
    }

    /* access modifiers changed from: protected */
    public RangeToken parseSetOperations() throws ParseException {
        RangeToken parseCharacterClass = parseCharacterClass(false);
        while (true) {
            int read = read();
            if (read != 7) {
                int i = this.chardata;
                if ((read == 0 && (i == 45 || i == 38)) || read == 4) {
                    next();
                    if (read() == 9) {
                        RangeToken parseCharacterClass2 = parseCharacterClass(false);
                        if (read == 4) {
                            parseCharacterClass.mergeRanges(parseCharacterClass2);
                        } else if (i == 45) {
                            parseCharacterClass.subtractRanges(parseCharacterClass2);
                        } else if (i == 38) {
                            parseCharacterClass.intersectRanges(parseCharacterClass2);
                        } else {
                            throw new RuntimeException("ASSERT");
                        }
                    } else {
                        throw ex("parser.ope.1", this.offset - 1);
                    }
                } else {
                    throw ex("parser.ope.2", this.offset - 1);
                }
            } else {
                next();
                return parseCharacterClass;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Token getTokenForShorthand(int i) {
        if (i == 68) {
            return isSet(32) ? Token.getRange("Nd", false) : Token.token_not_0to9;
        }
        if (i != 83) {
            if (i == 87) {
                return isSet(32) ? Token.getRange("IsWord", false) : Token.token_not_wordchars;
            }
            if (i != 100) {
                if (i != 115) {
                    if (i != 119) {
                        throw new RuntimeException("Internal Error: shorthands: \\u" + Integer.toString(i, 16));
                    } else if (isSet(32)) {
                        return Token.getRange("IsWord", true);
                    } else {
                        return Token.token_wordchars;
                    }
                } else if (isSet(32)) {
                    return Token.getRange("IsSpace", true);
                } else {
                    return Token.token_spaces;
                }
            } else if (isSet(32)) {
                return Token.getRange("Nd", true);
            } else {
                return Token.token_0to9;
            }
        } else if (isSet(32)) {
            return Token.getRange("IsSpace", false);
        } else {
            return Token.token_not_spaces;
        }
    }

    /* access modifiers changed from: package-private */
    public int decodeEscaped() throws ParseException {
        int i;
        int i2;
        int hexChar;
        int hexChar2;
        int hexChar3;
        int hexChar4;
        int hexChar5;
        int hexChar6;
        int hexChar7;
        int hexChar8;
        int hexChar9;
        if (read() == 10) {
            int i3 = this.chardata;
            if (!(i3 == 65 || i3 == 90)) {
                if (i3 == 110) {
                    return 10;
                }
                if (i3 == 114) {
                    return 13;
                }
                if (i3 == 120) {
                    next();
                    if (read() != 0) {
                        throw ex("parser.descape.1", this.offset - 1);
                    } else if (this.chardata == 123) {
                        int i4 = 0;
                        while (true) {
                            next();
                            if (read() == 0) {
                                int hexChar10 = hexChar(this.chardata);
                                if (hexChar10 >= 0) {
                                    int i5 = i4 * 16;
                                    if (i4 <= i5) {
                                        i4 = i5 + hexChar10;
                                    } else {
                                        throw ex("parser.descape.2", this.offset - 1);
                                    }
                                } else if (this.chardata != 125) {
                                    throw ex("parser.descape.3", this.offset - 1);
                                } else if (i4 <= 1114111) {
                                    return i4;
                                } else {
                                    throw ex("parser.descape.4", this.offset - 1);
                                }
                            } else {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                        }
                    } else if (read() != 0 || (i2 = hexChar(this.chardata)) < 0) {
                        throw ex("parser.descape.1", this.offset - 1);
                    } else {
                        next();
                        if (read() != 0 || (i = hexChar(this.chardata)) < 0) {
                            throw ex("parser.descape.1", this.offset - 1);
                        }
                    }
                } else if (i3 != 122) {
                    if (i3 == 101) {
                        return 27;
                    }
                    if (i3 == 102) {
                        return 12;
                    }
                    switch (i3) {
                        case 116:
                            return 9;
                        case 117:
                            next();
                            if (read() != 0 || (hexChar = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            next();
                            if (read() != 0 || (hexChar2 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i6 = (hexChar * 16) + hexChar2;
                            next();
                            if (read() != 0 || (hexChar3 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            i2 = (i6 * 16) + hexChar3;
                            next();
                            if (read() != 0 || (i = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            break;
                        case 118:
                            next();
                            if (read() != 0 || (hexChar4 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            next();
                            if (read() != 0 || (hexChar5 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i7 = (hexChar4 * 16) + hexChar5;
                            next();
                            if (read() != 0 || (hexChar6 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i8 = (i7 * 16) + hexChar6;
                            next();
                            if (read() != 0 || (hexChar7 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i9 = (i8 * 16) + hexChar7;
                            next();
                            if (read() != 0 || (hexChar8 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i10 = (i9 * 16) + hexChar8;
                            next();
                            if (read() != 0 || (hexChar9 = hexChar(this.chardata)) < 0) {
                                throw ex("parser.descape.1", this.offset - 1);
                            }
                            int i11 = hexChar9 + (i10 * 16);
                            if (i11 <= 1114111) {
                                return i11;
                            }
                            throw ex("parser.descappe.4", this.offset - 1);
                        default:
                            return i3;
                    }
                }
                return i + (i2 * 16);
            }
            throw ex("parser.descape.5", this.offset - 2);
        }
        throw ex("parser.next.1", this.offset - 1);
    }
}
