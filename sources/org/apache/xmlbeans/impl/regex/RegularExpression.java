package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import java.text.CharacterIterator;
import java.util.Locale;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.impl.regex.Op;
import org.apache.xmlbeans.impl.regex.Token;

public class RegularExpression implements Serializable {
    static final int CARRIAGE_RETURN = 13;
    static final boolean DEBUG = false;
    static final int EXTENDED_COMMENT = 16;
    static final int IGNORE_CASE = 2;
    static final int LINE_FEED = 10;
    static final int LINE_SEPARATOR = 8232;
    static final int MULTIPLE_LINES = 8;
    static final int PARAGRAPH_SEPARATOR = 8233;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int SINGLE_LINE = 4;
    static final int SPECIAL_COMMA = 1024;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int USE_UNICODE_CATEGORY = 32;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int XMLSCHEMA_MODE = 512;
    private static final long serialVersionUID = 6242499334195006401L;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient boolean fixedStringOnly;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    boolean hasBackReferences;
    transient int minlength;
    int nofparen;
    transient int numberOfClosures;
    transient Op operations;
    int options;
    String regex;
    Token tokentree;

    private static final boolean isEOLChar(int i) {
        return i == 10 || i == 13 || i == LINE_SEPARATOR || i == PARAGRAPH_SEPARATOR;
    }

    private static final boolean isSet(int i, int i2) {
        return (i & i2) == i2;
    }

    private static final boolean isWordChar(int i) {
        if (i == 95) {
            return true;
        }
        if (i < 48 || i > 122) {
            return false;
        }
        if (i <= 57) {
            return true;
        }
        if (i < 65) {
            return false;
        }
        return i <= 90 || i >= 97;
    }

    private synchronized void compile(Token token) {
        if (this.operations == null) {
            this.numberOfClosures = 0;
            this.operations = compile(token, (Op) null, false);
        }
    }

    private Op compile(Token token, Op op, boolean z) {
        Op op2;
        Op op3;
        Op.ChildOp childOp;
        int i = token.type;
        int i2 = 0;
        switch (i) {
            case 0:
                op2 = Op.createChar(token.getChar());
                op2.next = op;
                break;
            case 1:
                if (!z) {
                    for (int size = token.size() - 1; size >= 0; size--) {
                        op = compile(token.getChild(size), op, false);
                    }
                    return op;
                }
                while (i2 < token.size()) {
                    op = compile(token.getChild(i2), op, true);
                    i2++;
                }
                return op;
            case 2:
                Op.UnionOp createUnion = Op.createUnion(token.size());
                while (i2 < token.size()) {
                    createUnion.addElement(compile(token.getChild(i2), op, z));
                    i2++;
                }
                return createUnion;
            case 3:
            case 9:
                Token child = token.getChild(0);
                int min = token.getMin();
                int max = token.getMax();
                if (min < 0 || min != max) {
                    if (min > 0 && max > 0) {
                        max -= min;
                    }
                    if (max > 0) {
                        Op.ChildOp childOp2 = op;
                        int i3 = 0;
                        while (i3 < max) {
                            Op.ChildOp createQuestion = Op.createQuestion(token.type == 9);
                            createQuestion.next = op;
                            createQuestion.setChild(compile(child, childOp2, z));
                            i3++;
                            childOp2 = createQuestion;
                        }
                        op3 = childOp2;
                    } else {
                        if (token.type == 9) {
                            childOp = Op.createNonGreedyClosure();
                        } else {
                            int i4 = this.numberOfClosures;
                            this.numberOfClosures = i4 + 1;
                            childOp = Op.createClosure(i4);
                        }
                        childOp.next = op;
                        childOp.setChild(compile(child, childOp, z));
                        op3 = childOp;
                    }
                    if (min <= 0) {
                        return op3;
                    }
                    while (i2 < min) {
                        op3 = compile(child, op3, z);
                        i2++;
                    }
                    return op3;
                }
                while (i2 < min) {
                    op = compile(child, op, z);
                    i2++;
                }
                return op;
            case 4:
            case 5:
                op2 = Op.createRange(token);
                op2.next = op;
                break;
            case 6:
                if (token.getParenNumber() == 0) {
                    return compile(token.getChild(0), op, z);
                }
                if (z) {
                    return Op.createCapture(-token.getParenNumber(), compile(token.getChild(0), Op.createCapture(token.getParenNumber(), op), z));
                }
                return Op.createCapture(token.getParenNumber(), compile(token.getChild(0), Op.createCapture(-token.getParenNumber(), op), z));
            case 7:
                return op;
            case 8:
                op2 = Op.createAnchor(token.getChar());
                op2.next = op;
                break;
            case 10:
                op2 = Op.createString(token.getString());
                op2.next = op;
                break;
            case 11:
                op2 = Op.createDot();
                op2.next = op;
                break;
            case 12:
                op2 = Op.createBackReference(token.getReferenceNumber());
                op2.next = op;
                break;
            default:
                Op op4 = null;
                switch (i) {
                    case 20:
                        return Op.createLook(20, op, compile(token.getChild(0), (Op) null, false));
                    case 21:
                        return Op.createLook(21, op, compile(token.getChild(0), (Op) null, false));
                    case 22:
                        return Op.createLook(22, op, compile(token.getChild(0), (Op) null, true));
                    case 23:
                        return Op.createLook(23, op, compile(token.getChild(0), (Op) null, true));
                    case 24:
                        return Op.createIndependent(op, compile(token.getChild(0), (Op) null, z));
                    case 25:
                        Token.ModifierToken modifierToken = (Token.ModifierToken) token;
                        return Op.createModifier(op, compile(token.getChild(0), (Op) null, z), modifierToken.getOptions(), modifierToken.getOptionsMask());
                    case 26:
                        Token.ConditionToken conditionToken = (Token.ConditionToken) token;
                        int i5 = conditionToken.refNumber;
                        Op compile = conditionToken.condition == null ? null : compile(conditionToken.condition, (Op) null, z);
                        Op compile2 = compile(conditionToken.yes, op, z);
                        if (conditionToken.no != null) {
                            op4 = compile(conditionToken.no, op, z);
                        }
                        return Op.createCondition(op, i5, compile, compile2, op4);
                    default:
                        throw new RuntimeException("Unknown token type: " + token.type);
                }
        }
        return op2;
    }

    public boolean matches(char[] cArr) {
        Match match = null;
        return matches(cArr, 0, cArr.length, (Match) null);
    }

    public boolean matches(char[] cArr, int i, int i2) {
        Match match = null;
        return matches(cArr, i, i2, (Match) null);
    }

    public boolean matches(char[] cArr, Match match) {
        return matches(cArr, 0, cArr.length, match);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean matches(char[] r10, int r11, int r12, org.apache.xmlbeans.impl.regex.Match r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            org.apache.xmlbeans.impl.regex.Op r0 = r9.operations     // Catch:{ all -> 0x017e }
            if (r0 != 0) goto L_0x0008
            r9.prepare()     // Catch:{ all -> 0x017e }
        L_0x0008:
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r0 = r9.context     // Catch:{ all -> 0x017e }
            if (r0 != 0) goto L_0x0013
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r0 = new org.apache.xmlbeans.impl.regex.RegularExpression$Context     // Catch:{ all -> 0x017e }
            r0.<init>()     // Catch:{ all -> 0x017e }
            r9.context = r0     // Catch:{ all -> 0x017e }
        L_0x0013:
            monitor-exit(r9)     // Catch:{ all -> 0x017e }
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r0 = r9.context
            monitor-enter(r0)
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r1 = r9.context     // Catch:{ all -> 0x017b }
            boolean r1 = r1.inuse     // Catch:{ all -> 0x017b }
            if (r1 == 0) goto L_0x0023
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r1 = new org.apache.xmlbeans.impl.regex.RegularExpression$Context     // Catch:{ all -> 0x017b }
            r1.<init>()     // Catch:{ all -> 0x017b }
            goto L_0x0025
        L_0x0023:
            org.apache.xmlbeans.impl.regex.RegularExpression$Context r1 = r9.context     // Catch:{ all -> 0x017b }
        L_0x0025:
            int r2 = r9.numberOfClosures     // Catch:{ all -> 0x017b }
            r1.reset((char[]) r10, (int) r11, (int) r12, (int) r2)     // Catch:{ all -> 0x017b }
            monitor-exit(r0)     // Catch:{ all -> 0x017b }
            if (r13 == 0) goto L_0x0036
            int r11 = r9.nofparen
            r13.setNumberOfGroups(r11)
            r13.setSource((char[]) r10)
            goto L_0x0044
        L_0x0036:
            boolean r11 = r9.hasBackReferences
            if (r11 == 0) goto L_0x0044
            org.apache.xmlbeans.impl.regex.Match r13 = new org.apache.xmlbeans.impl.regex.Match
            r13.<init>()
            int r11 = r9.nofparen
            r13.setNumberOfGroups(r11)
        L_0x0044:
            r1.match = r13
            int r11 = r9.options
            r12 = 512(0x200, float:7.175E-43)
            boolean r11 = isSet(r11, r12)
            r12 = 1
            r13 = 0
            if (r11 == 0) goto L_0x0078
            org.apache.xmlbeans.impl.regex.Op r4 = r9.operations
            int r5 = r1.start
            r6 = 1
            int r7 = r9.options
            r2 = r9
            r3 = r1
            int r9 = r2.match(r3, r4, r5, r6, r7)
            int r10 = r1.limit
            if (r9 != r10) goto L_0x0077
            org.apache.xmlbeans.impl.regex.Match r10 = r1.match
            if (r10 == 0) goto L_0x0073
            org.apache.xmlbeans.impl.regex.Match r10 = r1.match
            int r11 = r1.start
            r10.setBeginning(r13, r11)
            org.apache.xmlbeans.impl.regex.Match r10 = r1.match
            r10.setEnd(r13, r9)
        L_0x0073:
            r1.setInUse(r13)
            return r12
        L_0x0077:
            return r13
        L_0x0078:
            boolean r11 = r9.fixedStringOnly
            if (r11 == 0) goto L_0x00a5
            org.apache.xmlbeans.impl.regex.BMPattern r11 = r9.fixedStringTable
            int r0 = r1.start
            int r2 = r1.limit
            int r10 = r11.matches((char[]) r10, (int) r0, (int) r2)
            if (r10 < 0) goto L_0x00a1
            org.apache.xmlbeans.impl.regex.Match r11 = r1.match
            if (r11 == 0) goto L_0x009d
            org.apache.xmlbeans.impl.regex.Match r11 = r1.match
            r11.setBeginning(r13, r10)
            org.apache.xmlbeans.impl.regex.Match r11 = r1.match
            java.lang.String r9 = r9.fixedString
            int r9 = r9.length()
            int r10 = r10 + r9
            r11.setEnd(r13, r10)
        L_0x009d:
            r1.setInUse(r13)
            return r12
        L_0x00a1:
            r1.setInUse(r13)
            return r13
        L_0x00a5:
            java.lang.String r11 = r9.fixedString
            if (r11 == 0) goto L_0x00b9
            org.apache.xmlbeans.impl.regex.BMPattern r11 = r9.fixedStringTable
            int r0 = r1.start
            int r2 = r1.limit
            int r11 = r11.matches((char[]) r10, (int) r0, (int) r2)
            if (r11 >= 0) goto L_0x00b9
            r1.setInUse(r13)
            return r13
        L_0x00b9:
            int r11 = r1.limit
            int r0 = r9.minlength
            int r11 = r11 - r0
            org.apache.xmlbeans.impl.regex.Op r0 = r9.operations
            r2 = -1
            if (r0 == 0) goto L_0x0113
            int r0 = r0.type
            r3 = 7
            if (r0 != r3) goto L_0x0113
            org.apache.xmlbeans.impl.regex.Op r0 = r9.operations
            org.apache.xmlbeans.impl.regex.Op r0 = r0.getChild()
            int r0 = r0.type
            if (r0 != 0) goto L_0x0113
            int r0 = r9.options
            r3 = 4
            boolean r0 = isSet(r0, r3)
            if (r0 == 0) goto L_0x00ec
            int r10 = r1.start
            org.apache.xmlbeans.impl.regex.Op r4 = r9.operations
            int r5 = r1.start
            r6 = 1
            int r7 = r9.options
            r2 = r9
            r3 = r1
            int r9 = r2.match(r3, r4, r5, r6, r7)
            goto L_0x0163
        L_0x00ec:
            int r0 = r1.start
            r3 = r12
        L_0x00ef:
            if (r0 > r11) goto L_0x0110
            char r4 = r10[r0]
            boolean r4 = isEOLChar(r4)
            if (r4 == 0) goto L_0x00fb
            r3 = r12
            goto L_0x010d
        L_0x00fb:
            if (r3 == 0) goto L_0x010c
            org.apache.xmlbeans.impl.regex.Op r4 = r9.operations
            r6 = 1
            int r7 = r9.options
            r2 = r9
            r3 = r1
            r5 = r0
            int r2 = r2.match(r3, r4, r5, r6, r7)
            if (r2 < 0) goto L_0x010c
            goto L_0x0110
        L_0x010c:
            r3 = r13
        L_0x010d:
            int r0 = r0 + 1
            goto L_0x00ef
        L_0x0110:
            r9 = r2
            r10 = r0
            goto L_0x0163
        L_0x0113:
            org.apache.xmlbeans.impl.regex.RangeToken r0 = r9.firstChar
            if (r0 == 0) goto L_0x014c
            int r3 = r1.start
            r8 = r3
        L_0x011a:
            if (r8 > r11) goto L_0x0149
            char r3 = r10[r8]
            boolean r4 = org.apache.xmlbeans.impl.regex.REUtil.isHighSurrogate(r3)
            if (r4 == 0) goto L_0x0130
            int r4 = r8 + 1
            int r5 = r1.limit
            if (r4 >= r5) goto L_0x0130
            char r4 = r10[r4]
            int r3 = org.apache.xmlbeans.impl.regex.REUtil.composeFromSurrogates(r3, r4)
        L_0x0130:
            boolean r3 = r0.match(r3)
            if (r3 != 0) goto L_0x0137
            goto L_0x0146
        L_0x0137:
            org.apache.xmlbeans.impl.regex.Op r4 = r9.operations
            r6 = 1
            int r7 = r9.options
            r2 = r9
            r3 = r1
            r5 = r8
            int r2 = r2.match(r3, r4, r5, r6, r7)
            if (r2 < 0) goto L_0x0146
            goto L_0x0149
        L_0x0146:
            int r8 = r8 + 1
            goto L_0x011a
        L_0x0149:
            r9 = r2
            r10 = r8
            goto L_0x0163
        L_0x014c:
            int r10 = r1.start
        L_0x014e:
            if (r10 > r11) goto L_0x0162
            org.apache.xmlbeans.impl.regex.Op r4 = r9.operations
            r6 = 1
            int r7 = r9.options
            r2 = r9
            r3 = r1
            r5 = r10
            int r2 = r2.match(r3, r4, r5, r6, r7)
            if (r2 < 0) goto L_0x015f
            goto L_0x0162
        L_0x015f:
            int r10 = r10 + 1
            goto L_0x014e
        L_0x0162:
            r9 = r2
        L_0x0163:
            if (r9 < 0) goto L_0x0177
            org.apache.xmlbeans.impl.regex.Match r11 = r1.match
            if (r11 == 0) goto L_0x0173
            org.apache.xmlbeans.impl.regex.Match r11 = r1.match
            r11.setBeginning(r13, r10)
            org.apache.xmlbeans.impl.regex.Match r10 = r1.match
            r10.setEnd(r13, r9)
        L_0x0173:
            r1.setInUse(r13)
            return r12
        L_0x0177:
            r1.setInUse(r13)
            return r13
        L_0x017b:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x017b }
            throw r9
        L_0x017e:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x017e }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matches(char[], int, int, org.apache.xmlbeans.impl.regex.Match):boolean");
    }

    public boolean matches(String str) {
        Match match = null;
        return matches(str, 0, str.length(), (Match) null);
    }

    public boolean matches(String str, int i, int i2) {
        Match match = null;
        return matches(str, i, i2, (Match) null);
    }

    public boolean matches(String str, Match match) {
        return matches(str, 0, str.length(), match);
    }

    public boolean matches(String str, int i, int i2, Match match) {
        Context context2;
        int i3;
        int i4;
        int i5;
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            context2 = this.context.inuse ? new Context() : this.context;
            context2.reset(str, i, i2, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(str);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        context2.match = match;
        if (isSet(this.options, 512)) {
            int match2 = match(context2, this.operations, context2.start, 1, this.options);
            if (match2 != context2.limit) {
                return false;
            }
            if (context2.match != null) {
                context2.match.setBeginning(0, context2.start);
                context2.match.setEnd(0, match2);
            }
            context2.setInUse(false);
            return true;
        } else if (this.fixedStringOnly) {
            int matches = this.fixedStringTable.matches(str, context2.start, context2.limit);
            if (matches >= 0) {
                if (context2.match != null) {
                    context2.match.setBeginning(0, matches);
                    context2.match.setEnd(0, matches + this.fixedString.length());
                }
                context2.setInUse(false);
                return true;
            }
            context2.setInUse(false);
            return false;
        } else if (this.fixedString == null || this.fixedStringTable.matches(str, context2.start, context2.limit) >= 0) {
            int i6 = context2.limit - this.minlength;
            Op op = this.operations;
            int i7 = -1;
            if (op == null || op.type != 7 || this.operations.getChild().type != 0) {
                RangeToken rangeToken = this.firstChar;
                if (rangeToken != null) {
                    int i8 = context2.start;
                    while (i8 <= i6) {
                        int charAt = str.charAt(i8);
                        if (REUtil.isHighSurrogate(charAt) && (i5 = i8 + 1) < context2.limit) {
                            charAt = REUtil.composeFromSurrogates(charAt, str.charAt(i5));
                        }
                        if (rangeToken.match(charAt) && (i7 = match(context2, this.operations, i8, 1, this.options)) >= 0) {
                            break;
                        }
                        i8++;
                    }
                    i4 = i7;
                    i3 = i8;
                } else {
                    i3 = context2.start;
                    while (i3 <= i6) {
                        i7 = match(context2, this.operations, i3, 1, this.options);
                        if (i7 >= 0) {
                            break;
                        }
                        i3++;
                    }
                    i4 = i7;
                }
            } else if (isSet(this.options, 4)) {
                i3 = context2.start;
                i4 = match(context2, this.operations, context2.start, 1, this.options);
            } else {
                int i9 = context2.start;
                boolean z = true;
                while (i9 <= i6) {
                    if (!isEOLChar(str.charAt(i9))) {
                        if (z && (i7 = match(context2, this.operations, i9, 1, this.options)) >= 0) {
                            break;
                        }
                        z = false;
                    } else {
                        z = true;
                    }
                    i9++;
                }
                i4 = i7;
                i3 = i9;
            }
            if (i4 >= 0) {
                if (context2.match != null) {
                    context2.match.setBeginning(0, i3);
                    context2.match.setEnd(0, i4);
                }
                context2.setInUse(false);
                return true;
            }
            context2.setInUse(false);
            return false;
        } else {
            context2.setInUse(false);
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: org.apache.xmlbeans.impl.regex.Op} */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03be A[SYNTHETIC] */
    private int match(org.apache.xmlbeans.impl.regex.RegularExpression.Context r24, org.apache.xmlbeans.impl.regex.Op r25, int r26, int r27, int r28) {
        /*
            r23 = this;
            r6 = r23
            r7 = r24
            org.apache.xmlbeans.impl.regex.RegularExpression$ExpressionTarget r8 = r7.target
            java.util.Stack r9 = new java.util.Stack
            r9.<init>()
            org.apache.xmlbeans.impl.regex.IntStack r10 = new org.apache.xmlbeans.impl.regex.IntStack
            r10.<init>()
            r0 = 2
            r1 = r28
            boolean r11 = isSet(r1, r0)
            r12 = 0
            r13 = r25
            r14 = r26
            r15 = r27
            r5 = r1
            r16 = r12
        L_0x0021:
            r4 = 15
            r17 = -1
            r3 = 1
            if (r13 == 0) goto L_0x030e
            int r0 = r7.limit
            if (r14 > r0) goto L_0x030e
            int r0 = r7.start
            if (r14 >= r0) goto L_0x0032
            goto L_0x030e
        L_0x0032:
            int r0 = r13.type
            if (r0 == 0) goto L_0x02ad
            if (r0 == r3) goto L_0x0287
            if (r0 == r4) goto L_0x0250
            r1 = 16
            java.lang.String r2 = "Internal Error: Reference number must be more than zero: "
            if (r0 == r1) goto L_0x01e3
            switch(r0) {
                case 3: goto L_0x019f;
                case 4: goto L_0x019f;
                case 5: goto L_0x0181;
                case 6: goto L_0x013e;
                case 7: goto L_0x0112;
                case 8: goto L_0x0109;
                case 9: goto L_0x012c;
                case 10: goto L_0x0109;
                case 11: goto L_0x00f4;
                default: goto L_0x0043;
            }
        L_0x0043:
            switch(r0) {
                case 20: goto L_0x00d4;
                case 21: goto L_0x00d4;
                case 22: goto L_0x00d4;
                case 23: goto L_0x00d4;
                case 24: goto L_0x00c9;
                case 25: goto L_0x00ad;
                case 26: goto L_0x005d;
                default: goto L_0x0046;
            }
        L_0x0046:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown operation type: "
            r1.<init>(r2)
            int r2 = r13.type
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x005d:
            r0 = r13
            org.apache.xmlbeans.impl.regex.Op$ConditionOp r0 = (org.apache.xmlbeans.impl.regex.Op.ConditionOp) r0
            int r1 = r0.refNumber
            if (r1 <= 0) goto L_0x00a3
            int r1 = r0.refNumber
            int r13 = r6.nofparen
            if (r1 >= r13) goto L_0x008e
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r2 = r0.refNumber
            int r1 = r1.getBeginning(r2)
            if (r1 < 0) goto L_0x0082
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r2 = r0.refNumber
            int r1 = r1.getEnd(r2)
            if (r1 < 0) goto L_0x0082
            org.apache.xmlbeans.impl.regex.Op r0 = r0.yes
            goto L_0x0136
        L_0x0082:
            org.apache.xmlbeans.impl.regex.Op r1 = r0.no
            if (r1 == 0) goto L_0x008a
            org.apache.xmlbeans.impl.regex.Op r0 = r0.no
            goto L_0x0136
        L_0x008a:
            org.apache.xmlbeans.impl.regex.Op r0 = r0.next
            goto L_0x0136
        L_0x008e:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            int r0 = r0.refNumber
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x00a3:
            r9.push(r13)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r0 = r0.condition
            goto L_0x0136
        L_0x00ad:
            int r0 = r13.getData()
            r0 = r0 | r5
            int r1 = r13.getData2()
            int r1 = ~r1
            r0 = r0 & r1
            r9.push(r13)
            r10.push(r5)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r1 = r13.getChild()
            r5 = r0
        L_0x00c6:
            r13 = r1
            goto L_0x0137
        L_0x00c9:
            r9.push(r13)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r0 = r13.getChild()
            goto L_0x0136
        L_0x00d4:
            r9.push(r13)
            r10.push(r15)
            r10.push(r14)
            int r0 = r13.type
            r1 = 20
            if (r0 == r1) goto L_0x00ed
            int r0 = r13.type
            r1 = 21
            if (r0 != r1) goto L_0x00ea
            goto L_0x00ed
        L_0x00ea:
            r0 = r17
            goto L_0x00ee
        L_0x00ed:
            r0 = r3
        L_0x00ee:
            org.apache.xmlbeans.impl.regex.Op r1 = r13.getChild()
            r15 = r0
            goto L_0x00c6
        L_0x00f4:
            int r0 = r13.size()
            if (r0 != 0) goto L_0x00fb
            goto L_0x0120
        L_0x00fb:
            r9.push(r13)
            r10.push(r12)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r0 = r13.elementAt(r12)
            goto L_0x0136
        L_0x0109:
            r9.push(r13)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x0136
        L_0x0112:
            int r0 = r13.getData()
            org.apache.xmlbeans.impl.regex.RegularExpression$ClosureContext[] r1 = r7.closureContexts
            r1 = r1[r0]
            boolean r1 = r1.contains(r14)
            if (r1 == 0) goto L_0x0125
        L_0x0120:
            r16 = r3
            r21 = r16
            goto L_0x0139
        L_0x0125:
            org.apache.xmlbeans.impl.regex.RegularExpression$ClosureContext[] r1 = r7.closureContexts
            r0 = r1[r0]
            r0.addOffset(r14)
        L_0x012c:
            r9.push(r13)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r0 = r13.getChild()
        L_0x0136:
            r13 = r0
        L_0x0137:
            r21 = r3
        L_0x0139:
            r12 = r4
        L_0x013a:
            r0 = r17
            goto L_0x0328
        L_0x013e:
            java.lang.String r18 = r13.getString()
            int r19 = r18.length()
            if (r15 <= 0) goto L_0x0164
            int r2 = r7.limit
            r0 = r8
            r1 = r11
            r20 = r2
            r2 = r14
            r21 = r3
            r3 = r20
            r12 = r4
            r4 = r18
            r25 = r5
            r5 = r19
            boolean r0 = r0.regionMatches((boolean) r1, (int) r2, (int) r3, (java.lang.String) r4, (int) r5)
            if (r0 != 0) goto L_0x0161
            goto L_0x0193
        L_0x0161:
            int r14 = r14 + r19
            goto L_0x017e
        L_0x0164:
            r21 = r3
            r12 = r4
            r25 = r5
            int r22 = r14 - r19
            int r3 = r7.limit
            r0 = r8
            r1 = r11
            r2 = r22
            r4 = r18
            r5 = r19
            boolean r0 = r0.regionMatches((boolean) r1, (int) r2, (int) r3, (java.lang.String) r4, (int) r5)
            if (r0 != 0) goto L_0x017c
            goto L_0x0193
        L_0x017c:
            r14 = r22
        L_0x017e:
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x019b
        L_0x0181:
            r21 = r3
            r12 = r4
            r25 = r5
            r0 = r23
            r1 = r8
            r2 = r13
            r3 = r24
            r4 = r14
            boolean r0 = r0.matchAnchor(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0199
        L_0x0193:
            r5 = r25
        L_0x0195:
            r0 = r17
            goto L_0x0326
        L_0x0199:
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
        L_0x019b:
            r5 = r25
            r13 = r0
            goto L_0x013a
        L_0x019f:
            r21 = r3
            r12 = r4
            r25 = r5
            if (r15 <= 0) goto L_0x01a8
            r0 = r14
            goto L_0x01aa
        L_0x01a8:
            int r0 = r14 + -1
        L_0x01aa:
            int r1 = r7.limit
            if (r0 >= r1) goto L_0x0193
            if (r0 >= 0) goto L_0x01b1
            goto L_0x0193
        L_0x01b1:
            char r1 = r8.charAt(r14)
            boolean r2 = org.apache.xmlbeans.impl.regex.REUtil.isHighSurrogate(r1)
            if (r2 == 0) goto L_0x01cc
            int r2 = r0 + r15
            int r3 = r7.limit
            if (r2 >= r3) goto L_0x01cc
            if (r2 < 0) goto L_0x01cc
            char r0 = r8.charAt(r2)
            int r1 = org.apache.xmlbeans.impl.regex.REUtil.composeFromSurrogates(r1, r0)
            r0 = r2
        L_0x01cc:
            org.apache.xmlbeans.impl.regex.RangeToken r2 = r13.getToken()
            boolean r1 = r2.match(r1)
            if (r1 != 0) goto L_0x01d7
            goto L_0x0193
        L_0x01d7:
            if (r15 <= 0) goto L_0x01db
            int r0 = r0 + 1
        L_0x01db:
            org.apache.xmlbeans.impl.regex.Op r1 = r13.next
            r5 = r25
            r14 = r0
            r13 = r1
            goto L_0x013a
        L_0x01e3:
            r21 = r3
            r12 = r4
            r25 = r5
            int r0 = r13.getData()
            if (r0 <= 0) goto L_0x023d
            int r1 = r6.nofparen
            if (r0 >= r1) goto L_0x023d
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r1 = r1.getBeginning(r0)
            if (r1 < 0) goto L_0x0193
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r1 = r1.getEnd(r0)
            if (r1 >= 0) goto L_0x0203
            goto L_0x0193
        L_0x0203:
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r4 = r1.getBeginning(r0)
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r0 = r1.getEnd(r0)
            int r18 = r0 - r4
            if (r15 <= 0) goto L_0x0225
            int r3 = r7.limit
            r0 = r8
            r1 = r11
            r2 = r14
            r5 = r18
            boolean r0 = r0.regionMatches((boolean) r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 != 0) goto L_0x0222
            goto L_0x0193
        L_0x0222:
            int r14 = r14 + r18
            goto L_0x0239
        L_0x0225:
            int r19 = r14 - r18
            int r3 = r7.limit
            r0 = r8
            r1 = r11
            r2 = r19
            r5 = r18
            boolean r0 = r0.regionMatches((boolean) r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 != 0) goto L_0x0237
            goto L_0x0193
        L_0x0237:
            r14 = r19
        L_0x0239:
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x019b
        L_0x023d:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0250:
            r21 = r3
            r12 = r4
            r25 = r5
            int r0 = r13.getData()
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            if (r1 == 0) goto L_0x0283
            if (r0 <= 0) goto L_0x026e
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r1 = r1.getBeginning(r0)
            r10.push(r1)
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            r1.setBeginning(r0, r14)
            goto L_0x027d
        L_0x026e:
            int r0 = -r0
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            int r1 = r1.getEnd(r0)
            r10.push(r1)
            org.apache.xmlbeans.impl.regex.Match r1 = r7.match
            r1.setEnd(r0, r14)
        L_0x027d:
            r9.push(r13)
            r10.push(r14)
        L_0x0283:
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x019b
        L_0x0287:
            r21 = r3
            r12 = r4
            r25 = r5
            if (r15 <= 0) goto L_0x0290
            r0 = r14
            goto L_0x0292
        L_0x0290:
            int r0 = r14 + -1
        L_0x0292:
            int r1 = r7.limit
            if (r0 >= r1) goto L_0x0193
            if (r0 < 0) goto L_0x0193
            int r1 = r13.getData()
            char r0 = r8.charAt(r0)
            boolean r0 = r6.matchChar(r1, r0, r11)
            if (r0 != 0) goto L_0x02a8
            goto L_0x0193
        L_0x02a8:
            int r14 = r14 + r15
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x019b
        L_0x02ad:
            r21 = r3
            r12 = r4
            r25 = r5
            if (r15 <= 0) goto L_0x02b6
            r0 = r14
            goto L_0x02b8
        L_0x02b6:
            int r0 = r14 + -1
        L_0x02b8:
            int r1 = r7.limit
            if (r0 >= r1) goto L_0x0309
            if (r0 >= 0) goto L_0x02bf
            goto L_0x0309
        L_0x02bf:
            r1 = 4
            r2 = r25
            boolean r1 = isSet(r2, r1)
            if (r1 == 0) goto L_0x02dc
            char r1 = r8.charAt(r0)
            boolean r1 = org.apache.xmlbeans.impl.regex.REUtil.isHighSurrogate(r1)
            if (r1 == 0) goto L_0x02fe
            int r1 = r0 + r15
            if (r1 < 0) goto L_0x02fe
            int r3 = r7.limit
            if (r1 >= r3) goto L_0x02fe
            r0 = r1
            goto L_0x02fe
        L_0x02dc:
            char r1 = r8.charAt(r0)
            boolean r3 = org.apache.xmlbeans.impl.regex.REUtil.isHighSurrogate(r1)
            if (r3 == 0) goto L_0x02f7
            int r3 = r0 + r15
            if (r3 < 0) goto L_0x02f7
            int r4 = r7.limit
            if (r3 >= r4) goto L_0x02f7
            char r0 = r8.charAt(r3)
            int r1 = org.apache.xmlbeans.impl.regex.REUtil.composeFromSurrogates(r1, r0)
            r0 = r3
        L_0x02f7:
            boolean r1 = isEOLChar(r1)
            if (r1 == 0) goto L_0x02fe
            goto L_0x030b
        L_0x02fe:
            if (r15 <= 0) goto L_0x0302
            int r0 = r0 + 1
        L_0x0302:
            org.apache.xmlbeans.impl.regex.Op r1 = r13.next
            r14 = r0
            r13 = r1
            r5 = r2
            goto L_0x013a
        L_0x0309:
            r2 = r25
        L_0x030b:
            r5 = r2
            goto L_0x0195
        L_0x030e:
            r21 = r3
            r12 = r4
            r2 = r5
            if (r13 != 0) goto L_0x0323
            r0 = 512(0x200, float:7.175E-43)
            boolean r0 = isSet(r2, r0)
            if (r0 == 0) goto L_0x0321
            int r0 = r7.limit
            if (r14 == r0) goto L_0x0321
            goto L_0x0323
        L_0x0321:
            r0 = r14
            goto L_0x0325
        L_0x0323:
            r0 = r17
        L_0x0325:
            r5 = r2
        L_0x0326:
            r16 = r21
        L_0x0328:
            if (r16 == 0) goto L_0x03be
            boolean r1 = r9.isEmpty()
            if (r1 == 0) goto L_0x0331
            return r0
        L_0x0331:
            java.lang.Object r1 = r9.pop()
            r13 = r1
            org.apache.xmlbeans.impl.regex.Op r13 = (org.apache.xmlbeans.impl.regex.Op) r13
            int r14 = r10.pop()
            int r1 = r13.type
            if (r1 == r12) goto L_0x03a3
            switch(r1) {
                case 7: goto L_0x039e;
                case 8: goto L_0x0397;
                case 9: goto L_0x039e;
                case 10: goto L_0x0397;
                case 11: goto L_0x037a;
                default: goto L_0x0343;
            }
        L_0x0343:
            switch(r1) {
                case 20: goto L_0x0371;
                case 21: goto L_0x0364;
                case 22: goto L_0x0371;
                case 23: goto L_0x0364;
                case 24: goto L_0x035c;
                case 25: goto L_0x0358;
                case 26: goto L_0x0347;
                default: goto L_0x0346;
            }
        L_0x0346:
            goto L_0x0328
        L_0x0347:
            org.apache.xmlbeans.impl.regex.Op$ConditionOp r13 = (org.apache.xmlbeans.impl.regex.Op.ConditionOp) r13
            if (r0 < 0) goto L_0x034e
            org.apache.xmlbeans.impl.regex.Op r1 = r13.yes
            goto L_0x0395
        L_0x034e:
            org.apache.xmlbeans.impl.regex.Op r1 = r13.no
            if (r1 == 0) goto L_0x0355
            org.apache.xmlbeans.impl.regex.Op r1 = r13.no
            goto L_0x0395
        L_0x0355:
            org.apache.xmlbeans.impl.regex.Op r1 = r13.next
            goto L_0x0395
        L_0x0358:
            int r5 = r10.pop()
        L_0x035c:
            if (r0 < 0) goto L_0x0328
            org.apache.xmlbeans.impl.regex.Op r13 = r13.next
            r14 = r0
        L_0x0361:
            r16 = 0
            goto L_0x0328
        L_0x0364:
            int r15 = r10.pop()
            if (r0 >= 0) goto L_0x013a
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
        L_0x036c:
            r13 = r0
            r16 = 0
            goto L_0x013a
        L_0x0371:
            int r15 = r10.pop()
            if (r0 < 0) goto L_0x013a
            org.apache.xmlbeans.impl.regex.Op r0 = r13.next
            goto L_0x036c
        L_0x037a:
            int r1 = r10.pop()
            if (r0 >= 0) goto L_0x0328
            int r1 = r1 + 1
            int r2 = r13.size()
            if (r1 >= r2) goto L_0x013a
            r9.push(r13)
            r10.push(r1)
            r10.push(r14)
            org.apache.xmlbeans.impl.regex.Op r1 = r13.elementAt(r1)
        L_0x0395:
            r13 = r1
            goto L_0x0361
        L_0x0397:
            if (r0 >= 0) goto L_0x0328
            org.apache.xmlbeans.impl.regex.Op r13 = r13.getChild()
            goto L_0x0361
        L_0x039e:
            if (r0 >= 0) goto L_0x0328
            org.apache.xmlbeans.impl.regex.Op r13 = r13.next
            goto L_0x0361
        L_0x03a3:
            int r1 = r13.getData()
            int r2 = r10.pop()
            if (r0 >= 0) goto L_0x0328
            if (r1 <= 0) goto L_0x03b6
            org.apache.xmlbeans.impl.regex.Match r3 = r7.match
            r3.setBeginning(r1, r2)
            goto L_0x0328
        L_0x03b6:
            org.apache.xmlbeans.impl.regex.Match r3 = r7.match
            int r1 = -r1
            r3.setEnd(r1, r2)
            goto L_0x0328
        L_0x03be:
            r12 = 0
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.match(org.apache.xmlbeans.impl.regex.RegularExpression$Context, org.apache.xmlbeans.impl.regex.Op, int, int, int):int");
    }

    private boolean matchChar(int i, int i2, boolean z) {
        if (z) {
            return matchIgnoreCase(i, i2);
        }
        return i == i2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x014c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean matchAnchor(org.apache.xmlbeans.impl.regex.RegularExpression.ExpressionTarget r7, org.apache.xmlbeans.impl.regex.Op r8, org.apache.xmlbeans.impl.regex.RegularExpression.Context r9, int r10, int r11) {
        /*
            r6 = this;
            int r6 = r8.getData()
            r8 = 36
            r0 = 10
            r1 = 13
            r2 = 8
            r3 = 1
            r4 = 0
            if (r6 == r8) goto L_0x010c
            r8 = 60
            r5 = 2
            if (r6 == r8) goto L_0x00ee
            r8 = 62
            if (r6 == r8) goto L_0x00d0
            r8 = 90
            if (r6 == r8) goto L_0x00a9
            r8 = 94
            if (r6 == r8) goto L_0x0086
            r8 = 98
            if (r6 == r8) goto L_0x006b
            r8 = 122(0x7a, float:1.71E-43)
            if (r6 == r8) goto L_0x0066
            switch(r6) {
                case 64: goto L_0x0052;
                case 65: goto L_0x004d;
                case 66: goto L_0x002e;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x014c
        L_0x002e:
            int r6 = r9.length
            if (r6 != 0) goto L_0x0034
        L_0x0032:
            r6 = r3
            goto L_0x004a
        L_0x0034:
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getWordType(r7, r6, r8, r10, r11)
            if (r6 == 0) goto L_0x0032
            int r8 = r9.start
            int r9 = r9.limit
            int r7 = getPreviousWordType(r7, r8, r9, r10, r11)
            if (r6 != r7) goto L_0x0049
            goto L_0x0032
        L_0x0049:
            r6 = r4
        L_0x004a:
            if (r6 != 0) goto L_0x014c
            return r4
        L_0x004d:
            int r6 = r9.start
            if (r10 == r6) goto L_0x014c
            return r4
        L_0x0052:
            int r6 = r9.start
            if (r10 == r6) goto L_0x014c
            int r6 = r9.start
            if (r10 <= r6) goto L_0x0065
            int r10 = r10 - r3
            char r6 = r7.charAt(r10)
            boolean r6 = isEOLChar(r6)
            if (r6 != 0) goto L_0x014c
        L_0x0065:
            return r4
        L_0x0066:
            int r6 = r9.limit
            if (r10 == r6) goto L_0x014c
            return r4
        L_0x006b:
            int r6 = r9.length
            if (r6 != 0) goto L_0x0070
            return r4
        L_0x0070:
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getWordType(r7, r6, r8, r10, r11)
            if (r6 != 0) goto L_0x007b
            return r4
        L_0x007b:
            int r8 = r9.start
            int r9 = r9.limit
            int r7 = getPreviousWordType(r7, r8, r9, r10, r11)
            if (r6 != r7) goto L_0x014c
            return r4
        L_0x0086:
            boolean r6 = isSet(r11, r2)
            if (r6 == 0) goto L_0x00a4
            int r6 = r9.start
            if (r10 == r6) goto L_0x014c
            int r6 = r9.start
            if (r10 <= r6) goto L_0x00a3
            int r6 = r9.limit
            if (r10 >= r6) goto L_0x00a3
            int r10 = r10 - r3
            char r6 = r7.charAt(r10)
            boolean r6 = isEOLChar(r6)
            if (r6 != 0) goto L_0x014c
        L_0x00a3:
            return r4
        L_0x00a4:
            int r6 = r9.start
            if (r10 == r6) goto L_0x014c
            return r4
        L_0x00a9:
            int r6 = r9.limit
            if (r10 == r6) goto L_0x014c
            int r6 = r10 + 1
            int r8 = r9.limit
            if (r6 != r8) goto L_0x00bd
            char r8 = r7.charAt(r10)
            boolean r8 = isEOLChar(r8)
            if (r8 != 0) goto L_0x014c
        L_0x00bd:
            int r8 = r10 + 2
            int r9 = r9.limit
            if (r8 != r9) goto L_0x00cf
            char r8 = r7.charAt(r10)
            if (r8 != r1) goto L_0x00cf
            char r6 = r7.charAt(r6)
            if (r6 == r0) goto L_0x014c
        L_0x00cf:
            return r4
        L_0x00d0:
            int r6 = r9.length
            if (r6 == 0) goto L_0x00ed
            int r6 = r9.start
            if (r10 != r6) goto L_0x00d9
            goto L_0x00ed
        L_0x00d9:
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getWordType(r7, r6, r8, r10, r11)
            if (r6 != r5) goto L_0x00ed
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getPreviousWordType(r7, r6, r8, r10, r11)
            if (r6 == r3) goto L_0x014c
        L_0x00ed:
            return r4
        L_0x00ee:
            int r6 = r9.length
            if (r6 == 0) goto L_0x010b
            int r6 = r9.limit
            if (r10 != r6) goto L_0x00f7
            goto L_0x010b
        L_0x00f7:
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getWordType(r7, r6, r8, r10, r11)
            if (r6 != r3) goto L_0x010b
            int r6 = r9.start
            int r8 = r9.limit
            int r6 = getPreviousWordType(r7, r6, r8, r10, r11)
            if (r6 == r5) goto L_0x014c
        L_0x010b:
            return r4
        L_0x010c:
            boolean r6 = isSet(r11, r2)
            if (r6 == 0) goto L_0x0125
            int r6 = r9.limit
            if (r10 == r6) goto L_0x014c
            int r6 = r9.limit
            if (r10 >= r6) goto L_0x0124
            char r6 = r7.charAt(r10)
            boolean r6 = isEOLChar(r6)
            if (r6 != 0) goto L_0x014c
        L_0x0124:
            return r4
        L_0x0125:
            int r6 = r9.limit
            if (r10 == r6) goto L_0x014c
            int r6 = r10 + 1
            int r8 = r9.limit
            if (r6 != r8) goto L_0x0139
            char r8 = r7.charAt(r10)
            boolean r8 = isEOLChar(r8)
            if (r8 != 0) goto L_0x014c
        L_0x0139:
            int r8 = r10 + 2
            int r9 = r9.limit
            if (r8 != r9) goto L_0x014b
            char r8 = r7.charAt(r10)
            if (r8 != r1) goto L_0x014b
            char r6 = r7.charAt(r6)
            if (r6 == r0) goto L_0x014c
        L_0x014b:
            return r4
        L_0x014c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.RegularExpression.matchAnchor(org.apache.xmlbeans.impl.regex.RegularExpression$ExpressionTarget, org.apache.xmlbeans.impl.regex.Op, org.apache.xmlbeans.impl.regex.RegularExpression$Context, int, int):boolean");
    }

    private static final int getPreviousWordType(ExpressionTarget expressionTarget, int i, int i2, int i3, int i4) {
        int i5 = i3 - 1;
        int wordType = getWordType(expressionTarget, i, i2, i5, i4);
        while (wordType == 0) {
            i5--;
            wordType = getWordType(expressionTarget, i, i2, i5, i4);
        }
        return wordType;
    }

    private static final int getWordType(ExpressionTarget expressionTarget, int i, int i2, int i3, int i4) {
        if (i3 < i || i3 >= i2) {
            return 2;
        }
        return getWordType0(expressionTarget.charAt(i3), i4);
    }

    public boolean matches(CharacterIterator characterIterator) {
        Match match = null;
        return matches(characterIterator, (Match) null);
    }

    public boolean matches(CharacterIterator characterIterator, Match match) {
        Context context2;
        int i;
        int i2;
        int i3;
        int beginIndex = characterIterator.getBeginIndex();
        int endIndex = characterIterator.getEndIndex();
        synchronized (this) {
            if (this.operations == null) {
                prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        synchronized (this.context) {
            context2 = this.context.inuse ? new Context() : this.context;
            context2.reset(characterIterator, beginIndex, endIndex, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(characterIterator);
        } else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        context2.match = match;
        if (isSet(this.options, 512)) {
            int match2 = match(context2, this.operations, context2.start, 1, this.options);
            if (match2 != context2.limit) {
                return false;
            }
            if (context2.match != null) {
                context2.match.setBeginning(0, context2.start);
                context2.match.setEnd(0, match2);
            }
            context2.setInUse(false);
            return true;
        } else if (this.fixedStringOnly) {
            int matches = this.fixedStringTable.matches(characterIterator, context2.start, context2.limit);
            if (matches >= 0) {
                if (context2.match != null) {
                    context2.match.setBeginning(0, matches);
                    context2.match.setEnd(0, matches + this.fixedString.length());
                }
                context2.setInUse(false);
                return true;
            }
            context2.setInUse(false);
            return false;
        } else if (this.fixedString == null || this.fixedStringTable.matches(characterIterator, context2.start, context2.limit) >= 0) {
            int i4 = context2.limit - this.minlength;
            Op op = this.operations;
            int i5 = -1;
            if (op == null || op.type != 7 || this.operations.getChild().type != 0) {
                RangeToken rangeToken = this.firstChar;
                if (rangeToken != null) {
                    int i6 = context2.start;
                    while (i6 <= i4) {
                        int index = characterIterator.setIndex(i6);
                        if (REUtil.isHighSurrogate(index) && (i3 = i6 + 1) < context2.limit) {
                            index = REUtil.composeFromSurrogates(index, characterIterator.setIndex(i3));
                        }
                        if (rangeToken.match(index) && (i5 = match(context2, this.operations, i6, 1, this.options)) >= 0) {
                            break;
                        }
                        i6++;
                    }
                    i2 = i5;
                    i = i6;
                } else {
                    i = context2.start;
                    while (i <= i4) {
                        i5 = match(context2, this.operations, i, 1, this.options);
                        if (i5 >= 0) {
                            break;
                        }
                        i++;
                    }
                    i2 = i5;
                }
            } else if (isSet(this.options, 4)) {
                i = context2.start;
                i2 = match(context2, this.operations, context2.start, 1, this.options);
            } else {
                int i7 = context2.start;
                boolean z = true;
                while (i7 <= i4) {
                    if (!isEOLChar(characterIterator.setIndex(i7))) {
                        if (z && (i5 = match(context2, this.operations, i7, 1, this.options)) >= 0) {
                            break;
                        }
                        z = false;
                    } else {
                        z = true;
                    }
                    i7++;
                }
                i2 = i5;
                i = i7;
            }
            if (i2 >= 0) {
                if (context2.match != null) {
                    context2.match.setBeginning(0, i);
                    context2.match.setEnd(0, i2);
                }
                context2.setInUse(false);
                return true;
            }
            context2.setInUse(false);
            return false;
        } else {
            context2.setInUse(false);
            return false;
        }
    }

    static abstract class ExpressionTarget {
        /* access modifiers changed from: package-private */
        public abstract char charAt(int i);

        /* access modifiers changed from: package-private */
        public abstract boolean regionMatches(boolean z, int i, int i2, int i3, int i4);

        /* access modifiers changed from: package-private */
        public abstract boolean regionMatches(boolean z, int i, int i2, String str, int i3);

        ExpressionTarget() {
        }
    }

    static final class StringTarget extends ExpressionTarget {
        private String target;

        StringTarget(String str) {
            this.target = str;
        }

        /* access modifiers changed from: package-private */
        public final void resetTarget(String str) {
            this.target = str;
        }

        /* access modifiers changed from: package-private */
        public final char charAt(int i) {
            return this.target.charAt(i);
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i2 - i < i3) {
                return false;
            }
            return z ? this.target.regionMatches(true, i, str, 0, i3) : this.target.regionMatches(i, str, 0, i3);
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i2 - i < i4) {
                return false;
            }
            if (z) {
                String str = this.target;
                return str.regionMatches(true, i, str, i3, i4);
            }
            String str2 = this.target;
            return str2.regionMatches(i, str2, i3, i4);
        }
    }

    static final class CharArrayTarget extends ExpressionTarget {
        char[] target;

        CharArrayTarget(char[] cArr) {
            this.target = cArr;
        }

        /* access modifiers changed from: package-private */
        public final void resetTarget(char[] cArr) {
            this.target = cArr;
        }

        /* access modifiers changed from: package-private */
        public char charAt(int i) {
            return this.target[i];
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i < 0 || i2 - i < i3) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, str, i3);
            }
            return regionMatches(i, i2, str, i3);
        }

        private final boolean regionMatches(int i, int i2, String str, int i3) {
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i4 + 1;
                if (this.target[i] != str.charAt(i4)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, String str, int i3) {
            char upperCase;
            char upperCase2;
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char c = this.target[i];
                int i7 = i4 + 1;
                char charAt = str.charAt(i4);
                if (c != charAt && (upperCase = Character.toUpperCase(c)) != (upperCase2 = Character.toUpperCase(charAt)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i < 0 || i2 - i < i4) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, i3, i4);
            }
            return regionMatches(i, i2, i3, i4);
        }

        private final boolean regionMatches(int i, int i2, int i3, int i4) {
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                char[] cArr = this.target;
                int i6 = i + 1;
                int i7 = i3 + 1;
                if (cArr[i] != cArr[i3]) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, int i3, int i4) {
            char upperCase;
            char upperCase2;
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                char[] cArr = this.target;
                int i6 = i + 1;
                char c = cArr[i];
                int i7 = i3 + 1;
                char c2 = cArr[i3];
                if (c != c2 && (upperCase = Character.toUpperCase(c)) != (upperCase2 = Character.toUpperCase(c2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }
    }

    static final class CharacterIteratorTarget extends ExpressionTarget {
        CharacterIterator target;

        CharacterIteratorTarget(CharacterIterator characterIterator) {
            this.target = characterIterator;
        }

        /* access modifiers changed from: package-private */
        public final void resetTarget(CharacterIterator characterIterator) {
            this.target = characterIterator;
        }

        /* access modifiers changed from: package-private */
        public final char charAt(int i) {
            return this.target.setIndex(i);
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i < 0 || i2 - i < i3) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, str, i3);
            }
            return regionMatches(i, i2, str, i3);
        }

        private final boolean regionMatches(int i, int i2, String str, int i3) {
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i4 + 1;
                if (this.target.setIndex(i) != str.charAt(i4)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, String str, int i3) {
            char upperCase;
            char upperCase2;
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char index = this.target.setIndex(i);
                int i7 = i4 + 1;
                char charAt = str.charAt(i4);
                if (index != charAt && (upperCase = Character.toUpperCase(index)) != (upperCase2 = Character.toUpperCase(charAt)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i < 0 || i2 - i < i4) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, i3, i4);
            }
            return regionMatches(i, i2, i3, i4);
        }

        private final boolean regionMatches(int i, int i2, int i3, int i4) {
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i3 + 1;
                if (this.target.setIndex(i) != this.target.setIndex(i3)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, int i3, int i4) {
            char upperCase;
            char upperCase2;
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char index = this.target.setIndex(i);
                int i7 = i3 + 1;
                char index2 = this.target.setIndex(i3);
                if (index != index2 && (upperCase = Character.toUpperCase(index)) != (upperCase2 = Character.toUpperCase(index2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }
    }

    static final class ClosureContext {
        int currentIndex = 0;
        int[] offsets = new int[4];

        ClosureContext() {
        }

        /* access modifiers changed from: package-private */
        public boolean contains(int i) {
            for (int i2 = 0; i2 < this.currentIndex; i2++) {
                if (this.offsets[i2] == i) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.currentIndex = 0;
        }

        /* access modifiers changed from: package-private */
        public void addOffset(int i) {
            if (this.currentIndex == this.offsets.length) {
                this.offsets = expandOffsets();
            }
            int[] iArr = this.offsets;
            int i2 = this.currentIndex;
            this.currentIndex = i2 + 1;
            iArr[i2] = i;
        }

        private int[] expandOffsets() {
            int[] iArr = this.offsets;
            int[] iArr2 = new int[(iArr.length << 1)];
            System.arraycopy(iArr, 0, iArr2, 0, this.currentIndex);
            return iArr2;
        }
    }

    static final class Context {
        private CharArrayTarget charArrayTarget;
        private CharacterIteratorTarget characterIteratorTarget;
        ClosureContext[] closureContexts;
        boolean inuse = false;
        int length;
        int limit;
        Match match;
        int start;
        private StringTarget stringTarget;
        ExpressionTarget target;

        Context() {
        }

        private void resetCommon(int i) {
            this.length = this.limit - this.start;
            setInUse(true);
            this.match = null;
            ClosureContext[] closureContextArr = this.closureContexts;
            if (closureContextArr == null || closureContextArr.length != i) {
                this.closureContexts = new ClosureContext[i];
            }
            for (int i2 = 0; i2 < i; i2++) {
                ClosureContext[] closureContextArr2 = this.closureContexts;
                ClosureContext closureContext = closureContextArr2[i2];
                if (closureContext == null) {
                    closureContextArr2[i2] = new ClosureContext();
                } else {
                    closureContext.reset();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void reset(CharacterIterator characterIterator, int i, int i2, int i3) {
            CharacterIteratorTarget characterIteratorTarget2 = this.characterIteratorTarget;
            if (characterIteratorTarget2 == null) {
                this.characterIteratorTarget = new CharacterIteratorTarget(characterIterator);
            } else {
                characterIteratorTarget2.resetTarget(characterIterator);
            }
            this.target = this.characterIteratorTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }

        /* access modifiers changed from: package-private */
        public void reset(String str, int i, int i2, int i3) {
            StringTarget stringTarget2 = this.stringTarget;
            if (stringTarget2 == null) {
                this.stringTarget = new StringTarget(str);
            } else {
                stringTarget2.resetTarget(str);
            }
            this.target = this.stringTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }

        /* access modifiers changed from: package-private */
        public void reset(char[] cArr, int i, int i2, int i3) {
            CharArrayTarget charArrayTarget2 = this.charArrayTarget;
            if (charArrayTarget2 == null) {
                this.charArrayTarget = new CharArrayTarget(cArr);
            } else {
                charArrayTarget2.resetTarget(cArr);
            }
            this.target = this.charArrayTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setInUse(boolean z) {
            this.inuse = z;
        }
    }

    /* access modifiers changed from: package-private */
    public void prepare() {
        compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!isSet(this.options, 128) && !isSet(this.options, 512)) {
            RangeToken createRange = Token.createRange();
            if (this.tokentree.analyzeFirstCharacter(createRange, this.options) == 1) {
                createRange.compactRanges();
                this.firstChar = createRange;
            }
        }
        Op op = this.operations;
        if (op != null && ((op.type == 6 || this.operations.type == 1) && this.operations.next == null)) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            } else if (this.operations.getData() >= 65536) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            } else {
                this.fixedString = new String(new char[]{(char) this.operations.getData()});
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
        } else if (!isSet(this.options, 256) && !isSet(this.options, 512)) {
            Token.FixedStringContainer fixedStringContainer = new Token.FixedStringContainer();
            this.tokentree.findFixedString(fixedStringContainer, this.options);
            this.fixedString = fixedStringContainer.token == null ? null : fixedStringContainer.token.getString();
            this.fixedStringOptions = fixedStringContainer.options;
            String str = this.fixedString;
            if (str != null && str.length() < 2) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
            }
        }
    }

    public RegularExpression(String str) throws ParseException {
        this(str, (String) null);
    }

    public RegularExpression(String str, String str2) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(str, str2);
    }

    public RegularExpression(String str, String str2, Locale locale) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(str, str2, locale);
    }

    RegularExpression(String str, Token token, int i, boolean z, int i2) {
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.regex = str;
        this.tokentree = token;
        this.nofparen = i;
        this.options = i2;
        this.hasBackReferences = z;
    }

    public void setPattern(String str) throws ParseException {
        setPattern(str, Locale.getDefault());
    }

    public void setPattern(String str, Locale locale) throws ParseException {
        setPattern(str, this.options, locale);
    }

    private void setPattern(String str, int i, Locale locale) throws ParseException {
        this.regex = str;
        this.options = i;
        RegexParser parserForXMLSchema = isSet(i, 512) ? new ParserForXMLSchema(locale) : new RegexParser(locale);
        this.tokentree = parserForXMLSchema.parse(this.regex, this.options);
        this.nofparen = parserForXMLSchema.parennumber;
        this.hasBackReferences = parserForXMLSchema.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public void setPattern(String str, String str2) throws ParseException {
        setPattern(str, str2, Locale.getDefault());
    }

    public void setPattern(String str, String str2, Locale locale) throws ParseException {
        setPattern(str, REUtil.parseOptions(str2), locale);
    }

    public String getPattern() {
        return this.regex;
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RegularExpression)) {
            return false;
        }
        RegularExpression regularExpression = (RegularExpression) obj;
        if (!this.regex.equals(regularExpression.regex) || this.options != regularExpression.options) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean equals(String str, int i) {
        return this.regex.equals(str) && this.options == i;
    }

    public int hashCode() {
        return (this.regex + PackagingURIHelper.FORWARD_SLASH_STRING + getOptions()).hashCode();
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    private static final int getWordType0(char c, int i) {
        if (isSet(i, 64)) {
            int type = Character.getType(c);
            if (type != 15) {
                if (type != 16) {
                    switch (type) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            return 1;
                        case 6:
                        case 7:
                            break;
                        default:
                            return 2;
                    }
                }
                return 0;
            }
            switch (c) {
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    return 2;
                default:
                    return 0;
            }
        } else if (isSet(i, 32)) {
            if (Token.getRange("IsWord", true).match(c)) {
                return 1;
            }
            return 2;
        } else if (isWordChar(c)) {
            return 1;
        } else {
            return 2;
        }
    }

    private static final boolean matchIgnoreCase(int i, int i2) {
        if (i == i2) {
            return true;
        }
        if (i > 65535 || i2 > 65535) {
            return false;
        }
        char upperCase = Character.toUpperCase((char) i);
        char upperCase2 = Character.toUpperCase((char) i2);
        if (upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)) {
            return true;
        }
        return false;
    }
}
