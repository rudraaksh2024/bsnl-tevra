package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AddPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.ArrayPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.apache.poi.ss.formula.ptg.DividePtg;
import org.apache.poi.ss.formula.ptg.EqualPtg;
import org.apache.poi.ss.formula.ptg.ErrPtg;
import org.apache.poi.ss.formula.ptg.FuncPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.apache.poi.ss.formula.ptg.LessThanPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MultiplyPtg;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.NameXPxg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.PowerPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.SubtractPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;

@Internal
public final class FormulaParser {
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("(\\$?[A-Za-z]+)?(\\$?[0-9]+)?");
    private static final char CR = '\r';
    private static final char LF = '\n';
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) FormulaParser.class);
    private static final char TAB = '\t';
    private static final String specAll = "All";
    private static final String specData = "Data";
    private static final String specHeaders = "Headers";
    private static final String specThisRow = "This Row";
    private static final String specTotals = "Totals";
    private final FormulaParsingWorkbook _book;
    private final int _formulaLength;
    private final String _formulaString;
    private boolean _inIntersection;
    private int _pointer = 0;
    private ParseNode _rootNode;
    private final int _rowIndex;
    private final int _sheetIndex;
    private final SpreadsheetVersion _ssVersion;
    private int look;

    private static boolean IsWhite(int i) {
        return i == 32 || i == 9 || i == 13 || i == 10;
    }

    private static boolean isArgumentDelimiter(int i) {
        return i == 44 || i == 41;
    }

    private FormulaParser(String str, FormulaParsingWorkbook formulaParsingWorkbook, int i, int i2) {
        this._formulaString = str;
        this._book = formulaParsingWorkbook;
        this._ssVersion = formulaParsingWorkbook == null ? SpreadsheetVersion.EXCEL97 : formulaParsingWorkbook.getSpreadsheetVersion();
        this._formulaLength = str.length();
        this._sheetIndex = i;
        this._rowIndex = i2;
    }

    public static Ptg[] parse(String str, FormulaParsingWorkbook formulaParsingWorkbook, FormulaType formulaType, int i, int i2) {
        FormulaParser formulaParser = new FormulaParser(str, formulaParsingWorkbook, i, i2);
        formulaParser.parse();
        return formulaParser.getRPNPtg(formulaType);
    }

    public static Ptg[] parse(String str, FormulaParsingWorkbook formulaParsingWorkbook, FormulaType formulaType, int i) {
        return parse(str, formulaParsingWorkbook, formulaType, i, -1);
    }

    public static Area3DPxg parseStructuredReference(String str, FormulaParsingWorkbook formulaParsingWorkbook, int i) {
        Ptg[] parse = parse(str, formulaParsingWorkbook, FormulaType.CELL, -1, i);
        if (parse.length == 1) {
            Ptg ptg = parse[0];
            if (ptg instanceof Area3DPxg) {
                return (Area3DPxg) ptg;
            }
        }
        throw new IllegalStateException("Illegal structured reference, had length: " + parse.length);
    }

    private void GetChar() {
        if (!IsWhite(this.look)) {
            this._inIntersection = false;
        } else if (this.look == 32) {
            this._inIntersection = true;
        }
        int i = this._pointer;
        int i2 = this._formulaLength;
        if (i <= i2) {
            if (i < i2) {
                this.look = this._formulaString.codePointAt(i);
            } else {
                this.look = 0;
                this._inIntersection = false;
            }
            this._pointer += Character.charCount(this.look);
            return;
        }
        throw new RuntimeException("Parsed past the end of the formula, pos: " + this._pointer + ", length: " + this._formulaLength + ", formula: " + this._formulaString);
    }

    private void resetPointer(int i) {
        this._pointer = i;
        if (i <= this._formulaLength) {
            this.look = this._formulaString.codePointAt(i - Character.charCount(this.look));
        } else {
            this.look = 0;
        }
    }

    private RuntimeException expected(String str) {
        String str2;
        if (this.look != 61 || this._formulaString.substring(0, this._pointer - 1).trim().length() >= 1) {
            str2 = new StringBuilder("Parse error near char ").append(this._pointer - 1).append(" '").appendCodePoint(this.look).append("'").append(" in specified formula '").append(this._formulaString).append("'. Expected ").append(str).toString();
        } else {
            str2 = "The specified formula '" + this._formulaString + "' starts with an equals sign which is not allowed.";
        }
        return new FormulaParseException(str2);
    }

    private static boolean IsAlpha(int i) {
        return Character.isLetter(i) || i == 36 || i == 95;
    }

    private static boolean IsDigit(int i) {
        return Character.isDigit(i);
    }

    private void SkipWhite() {
        while (IsWhite(this.look)) {
            GetChar();
        }
    }

    private void Match(int i) {
        if (this.look == i) {
            GetChar();
            return;
        }
        throw expected(new StringBuilder().append("'").appendCodePoint(i).append("'").toString());
    }

    private String GetNum() {
        StringBuilder sb = new StringBuilder();
        while (IsDigit(this.look)) {
            sb.appendCodePoint(this.look);
            GetChar();
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    private ParseNode parseRangeExpression() {
        ParseNode parseRangeable = parseRangeable();
        boolean z = false;
        while (this.look == 58) {
            int i = this._pointer;
            GetChar();
            ParseNode parseRangeable2 = parseRangeable();
            checkValidRangeOperand("LHS", i, parseRangeable);
            checkValidRangeOperand("RHS", i, parseRangeable2);
            z = true;
            parseRangeable = new ParseNode((Ptg) RangePtg.instance, new ParseNode[]{parseRangeable, parseRangeable2});
        }
        return z ? augmentWithMemPtg(parseRangeable) : parseRangeable;
    }

    private static ParseNode augmentWithMemPtg(ParseNode parseNode) {
        Ptg ptg;
        if (needsMemFunc(parseNode)) {
            ptg = new MemFuncPtg(parseNode.getEncodedSize());
        } else {
            ptg = new MemAreaPtg(parseNode.getEncodedSize());
        }
        return new ParseNode(ptg, parseNode);
    }

    private static boolean needsMemFunc(ParseNode parseNode) {
        Ptg token = parseNode.getToken();
        if ((token instanceof AbstractFunctionPtg) || (token instanceof ExternSheetReferenceToken) || (token instanceof NamePtg) || (token instanceof NameXPtg)) {
            return true;
        }
        if (!(token instanceof OperationPtg) && !(token instanceof ParenthesisPtg)) {
            return false;
        }
        for (ParseNode needsMemFunc : parseNode.getChildren()) {
            if (needsMemFunc(needsMemFunc)) {
                return true;
            }
        }
        return false;
    }

    private static void checkValidRangeOperand(String str, int i, ParseNode parseNode) {
        if (!isValidRangeOperand(parseNode)) {
            throw new FormulaParseException("The " + str + " of the range operator ':' at position " + i + " is not a proper reference.");
        }
    }

    private static boolean isValidRangeOperand(ParseNode parseNode) {
        Ptg token = parseNode.getToken();
        if (token instanceof OperandPtg) {
            return true;
        }
        if (token instanceof AbstractFunctionPtg) {
            byte defaultOperandClass = ((AbstractFunctionPtg) token).getDefaultOperandClass();
            if (defaultOperandClass == 0 || 32 == defaultOperandClass) {
                return true;
            }
            return false;
        } else if (token instanceof ValueOperatorPtg) {
            return false;
        } else {
            if (token instanceof OperationPtg) {
                return true;
            }
            if (token instanceof ParenthesisPtg) {
                return isValidRangeOperand(parseNode.getChildren()[0]);
            }
            if (token == ErrPtg.REF_INVALID) {
                return true;
            }
            return false;
        }
    }

    private ParseNode parseRangeable() {
        int i;
        SkipWhite();
        int i2 = this._pointer;
        SheetIdentifier parseSheetName = parseSheetName();
        if (parseSheetName == null) {
            resetPointer(i2);
        } else {
            SkipWhite();
            i2 = this._pointer;
        }
        SimpleRangePart parseSimpleRangePart = parseSimpleRangePart();
        if (parseSimpleRangePart != null) {
            boolean IsWhite = IsWhite(this.look);
            if (IsWhite) {
                SkipWhite();
            }
            int i3 = this.look;
            SimpleRangePart simpleRangePart = null;
            if (i3 == 58) {
                int i4 = this._pointer;
                GetChar();
                SkipWhite();
                SimpleRangePart parseSimpleRangePart2 = parseSimpleRangePart();
                if (parseSimpleRangePart2 == null || parseSimpleRangePart.isCompatibleForArea(parseSimpleRangePart2)) {
                    simpleRangePart = parseSimpleRangePart2;
                }
                if (simpleRangePart == null) {
                    resetPointer(i4);
                    if (!parseSimpleRangePart.isCell()) {
                        throw new FormulaParseException((parseSheetName != null ? "'" + parseSheetName.getSheetIdentifier().getName() + '!' : "") + parseSimpleRangePart.getRep() + "' is not a proper reference.");
                    }
                }
                return createAreaRefParseNode(parseSheetName, parseSimpleRangePart, simpleRangePart);
            } else if (i3 == 46) {
                GetChar();
                int i5 = 1;
                while (true) {
                    i = this.look;
                    if (i != 46) {
                        break;
                    }
                    i5++;
                    GetChar();
                }
                boolean IsWhite2 = IsWhite(i);
                SkipWhite();
                SimpleRangePart parseSimpleRangePart3 = parseSimpleRangePart();
                String substring = this._formulaString.substring(i2 - 1, this._pointer - 1);
                if (parseSimpleRangePart3 == null) {
                    if (parseSheetName == null) {
                        return parseNonRange(i2);
                    }
                    throw new FormulaParseException("Complete area reference expected after sheet name at index " + this._pointer + ".");
                } else if (IsWhite || IsWhite2) {
                    if (!parseSimpleRangePart.isRowOrColumn() && !parseSimpleRangePart3.isRowOrColumn()) {
                        return createAreaRefParseNode(parseSheetName, parseSimpleRangePart, parseSimpleRangePart3);
                    }
                    throw new FormulaParseException("Dotted range (full row or column) expression '" + substring + "' must not contain whitespace.");
                } else if (i5 == 1 && parseSimpleRangePart.isRow() && parseSimpleRangePart3.isRow()) {
                    return parseNonRange(i2);
                } else {
                    if ((!parseSimpleRangePart.isRowOrColumn() && !parseSimpleRangePart3.isRowOrColumn()) || i5 == 2) {
                        return createAreaRefParseNode(parseSheetName, parseSimpleRangePart, parseSimpleRangePart3);
                    }
                    throw new FormulaParseException("Dotted range (full row or column) expression '" + substring + "' must have exactly 2 dots.");
                }
            } else if (parseSimpleRangePart.isCell() && isValidCellReference(parseSimpleRangePart.getRep())) {
                return createAreaRefParseNode(parseSheetName, parseSimpleRangePart, (SimpleRangePart) null);
            } else {
                if (parseSheetName == null) {
                    return parseNonRange(i2);
                }
                throw new FormulaParseException("Second part of cell reference expected after sheet name at index " + this._pointer + ".");
            }
        } else if (parseSheetName == null) {
            return parseNonRange(i2);
        } else {
            if (this.look == 35) {
                return new ParseNode(ErrPtg.valueOf(parseErrorLiteral()));
            }
            String parseAsName = parseAsName();
            if (parseAsName.length() != 0) {
                Ptg nameXPtg = this._book.getNameXPtg(parseAsName, parseSheetName);
                if (nameXPtg != null) {
                    return new ParseNode(nameXPtg);
                }
                throw new FormulaParseException("Specified name '" + parseAsName + "' for sheet " + parseSheetName.asFormulaString() + " not found");
            }
            throw new FormulaParseException("Cell reference or Named Range expected after sheet name at index " + this._pointer + ".");
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0160, code lost:
        if (r7.equals(specThisRow) == false) goto L_0x0159;
     */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.ParseNode parseStructuredReference(java.lang.String r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            org.apache.poi.ss.SpreadsheetVersion r2 = r0._ssVersion
            org.apache.poi.ss.SpreadsheetVersion r3 = org.apache.poi.ss.SpreadsheetVersion.EXCEL2007
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0359
            org.apache.poi.ss.formula.FormulaParsingWorkbook r2 = r0._book
            org.apache.poi.ss.usermodel.Table r2 = r2.getTable(r1)
            if (r2 == 0) goto L_0x033e
            java.lang.String r1 = r2.getSheetName()
            int r3 = r2.getStartColIndex()
            int r4 = r2.getEndColIndex()
            int r5 = r2.getStartRowIndex()
            int r6 = r2.getEndRowIndex()
            int r7 = r0._pointer
            r29.GetChar()
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0035:
            int r15 = r0._pointer
            java.lang.String r8 = r29.parseAsSpecialQuantifier()
            r16 = r4
            java.lang.String r4 = "Unknown special quantifier "
            r18 = 4
            r19 = r10
            java.lang.String r10 = "This Row"
            r20 = 3
            r21 = r11
            java.lang.String r11 = "Data"
            r22 = r12
            java.lang.String r12 = "All"
            r23 = r13
            java.lang.String r13 = "Totals"
            r24 = r14
            java.lang.String r14 = "Headers"
            r25 = r1
            if (r8 != 0) goto L_0x0060
            r0.resetPointer(r15)
            goto L_0x00d9
        L_0x0060:
            r8.hashCode()
            int r15 = r8.hashCode()
            switch(r15) {
                case -1835006106: goto L_0x0092;
                case -1784055345: goto L_0x0089;
                case 65921: goto L_0x0080;
                case 2122698: goto L_0x0076;
                case 1291583832: goto L_0x006c;
                default: goto L_0x006a;
            }
        L_0x006a:
            r15 = -1
            goto L_0x009a
        L_0x006c:
            boolean r15 = r8.equals(r10)
            if (r15 != 0) goto L_0x0073
            goto L_0x006a
        L_0x0073:
            r15 = r18
            goto L_0x009a
        L_0x0076:
            boolean r15 = r8.equals(r11)
            if (r15 != 0) goto L_0x007d
            goto L_0x006a
        L_0x007d:
            r15 = r20
            goto L_0x009a
        L_0x0080:
            boolean r15 = r8.equals(r12)
            if (r15 != 0) goto L_0x0087
            goto L_0x006a
        L_0x0087:
            r15 = 2
            goto L_0x009a
        L_0x0089:
            boolean r15 = r8.equals(r13)
            if (r15 != 0) goto L_0x0090
            goto L_0x006a
        L_0x0090:
            r15 = 1
            goto L_0x009a
        L_0x0092:
            boolean r15 = r8.equals(r14)
            if (r15 != 0) goto L_0x0099
            goto L_0x006a
        L_0x0099:
            r15 = 0
        L_0x009a:
            switch(r15) {
                case 0: goto L_0x00bc;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00b6;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00b0;
                default: goto L_0x009d;
            }
        L_0x009d:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00b0:
            r21 = 1
            goto L_0x00be
        L_0x00b3:
            r22 = 1
            goto L_0x00be
        L_0x00b6:
            r24 = 1
            goto L_0x00be
        L_0x00b9:
            r19 = 1
            goto L_0x00be
        L_0x00bc:
            r23 = 1
        L_0x00be:
            int r9 = r9 + 1
            int r8 = r0.look
            r15 = 44
            if (r8 != r15) goto L_0x00d9
            r29.GetChar()
            r4 = r16
            r10 = r19
            r11 = r21
            r12 = r22
            r13 = r23
            r14 = r24
            r1 = r25
            goto L_0x0035
        L_0x00d9:
            r29.SkipWhite()
            int r8 = r0.look
            r15 = 64
            if (r8 != r15) goto L_0x00e7
            r29.GetChar()
            r8 = 1
            goto L_0x00e8
        L_0x00e7:
            r8 = 0
        L_0x00e8:
            int r15 = r0._pointer
            java.lang.String r26 = r29.parseAsColumnQuantifier()
            java.lang.String r1 = "The formula "
            if (r26 != 0) goto L_0x00fb
            r0.resetPointer(r15)
            r27 = r3
            r15 = 0
        L_0x00f8:
            r17 = 0
            goto L_0x0131
        L_0x00fb:
            int r15 = r0.look
            r27 = r3
            r3 = 44
            if (r15 == r3) goto L_0x0323
            r3 = 58
            if (r15 != r3) goto L_0x012f
            r29.GetChar()
            java.lang.String r3 = r29.parseAsColumnQuantifier()
            if (r3 == 0) goto L_0x0114
            r17 = r3
            r15 = 2
            goto L_0x0131
        L_0x0114:
            org.apache.poi.ss.formula.FormulaParseException r2 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.String r0 = r0._formulaString
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r1 = " is illegal: the string after ':' must be column quantifier"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x012f:
            r15 = 1
            goto L_0x00f8
        L_0x0131:
            java.lang.String r3 = " is illegal"
            if (r15 != 0) goto L_0x01ca
            if (r9 != 0) goto L_0x01ca
            r0.resetPointer(r7)
            int r7 = r0._pointer
            java.lang.String r26 = r29.parseAsColumnQuantifier()
            if (r26 == 0) goto L_0x0146
            int r15 = r15 + 1
            goto L_0x01cf
        L_0x0146:
            r0.resetPointer(r7)
            java.lang.String r7 = r29.parseAsSpecialQuantifier()
            if (r7 == 0) goto L_0x01b1
            r7.hashCode()
            int r28 = r7.hashCode()
            switch(r28) {
                case -1835006106: goto L_0x0181;
                case -1784055345: goto L_0x0177;
                case 65921: goto L_0x016d;
                case 2122698: goto L_0x0163;
                case 1291583832: goto L_0x015c;
                default: goto L_0x0159;
            }
        L_0x0159:
            r18 = -1
            goto L_0x018a
        L_0x015c:
            boolean r10 = r7.equals(r10)
            if (r10 != 0) goto L_0x018a
            goto L_0x0159
        L_0x0163:
            boolean r10 = r7.equals(r11)
            if (r10 != 0) goto L_0x016a
            goto L_0x0159
        L_0x016a:
            r18 = r20
            goto L_0x018a
        L_0x016d:
            boolean r10 = r7.equals(r12)
            if (r10 != 0) goto L_0x0174
            goto L_0x0159
        L_0x0174:
            r18 = 2
            goto L_0x018a
        L_0x0177:
            boolean r10 = r7.equals(r13)
            if (r10 != 0) goto L_0x017e
            goto L_0x0159
        L_0x017e:
            r18 = 1
            goto L_0x018a
        L_0x0181:
            boolean r10 = r7.equals(r14)
            if (r10 != 0) goto L_0x0188
            goto L_0x0159
        L_0x0188:
            r18 = 0
        L_0x018a:
            switch(r18) {
                case 0: goto L_0x01ac;
                case 1: goto L_0x01a9;
                case 2: goto L_0x01a6;
                case 3: goto L_0x01a3;
                case 4: goto L_0x01a0;
                default: goto L_0x018d;
            }
        L_0x018d:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01a0:
            r21 = 1
            goto L_0x01ae
        L_0x01a3:
            r22 = 1
            goto L_0x01ae
        L_0x01a6:
            r24 = 1
            goto L_0x01ae
        L_0x01a9:
            r19 = 1
            goto L_0x01ae
        L_0x01ac:
            r23 = 1
        L_0x01ae:
            int r9 = r9 + 1
            goto L_0x01cf
        L_0x01b1:
            org.apache.poi.ss.formula.FormulaParseException r2 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r0 = r0._formulaString
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x01ca:
            r4 = 93
            r0.Match(r4)
        L_0x01cf:
            r4 = r26
            if (r19 == 0) goto L_0x01e1
            int r7 = r2.getTotalsRowCount()
            if (r7 != 0) goto L_0x01e1
            org.apache.poi.ss.formula.ParseNode r0 = new org.apache.poi.ss.formula.ParseNode
            org.apache.poi.ss.formula.ptg.ErrPtg r1 = org.apache.poi.ss.formula.ptg.ErrPtg.REF_INVALID
            r0.<init>(r1)
            return r0
        L_0x01e1:
            if (r8 != 0) goto L_0x01e5
            if (r21 == 0) goto L_0x01ed
        L_0x01e5:
            int r7 = r0._rowIndex
            if (r7 < r5) goto L_0x0311
            if (r6 >= r7) goto L_0x01ed
            goto L_0x0311
        L_0x01ed:
            if (r9 <= 0) goto L_0x0248
            r7 = 1
            if (r9 != r7) goto L_0x01f6
            if (r24 == 0) goto L_0x01f6
            goto L_0x0259
        L_0x01f6:
            if (r22 == 0) goto L_0x0203
            if (r23 == 0) goto L_0x0203
            int r1 = r2.getTotalsRowCount()
            if (r1 <= 0) goto L_0x0259
            int r6 = r6 + -1
            goto L_0x0259
        L_0x0203:
            if (r22 == 0) goto L_0x020a
            if (r19 == 0) goto L_0x020a
            int r5 = r5 + 1
            goto L_0x0259
        L_0x020a:
            r7 = 1
            if (r9 != r7) goto L_0x021b
            if (r22 == 0) goto L_0x021a
            int r5 = r5 + 1
            int r1 = r2.getTotalsRowCount()
            if (r1 <= 0) goto L_0x0259
            int r1 = r6 + -1
            goto L_0x0258
        L_0x021a:
            r7 = 1
        L_0x021b:
            if (r9 != r7) goto L_0x0220
            if (r23 == 0) goto L_0x0220
            goto L_0x024c
        L_0x0220:
            if (r9 != r7) goto L_0x0226
            if (r19 == 0) goto L_0x0226
            r5 = r6
            goto L_0x0259
        L_0x0226:
            if (r9 != r7) goto L_0x022a
            if (r21 != 0) goto L_0x022c
        L_0x022a:
            if (r8 == 0) goto L_0x022f
        L_0x022c:
            int r5 = r0._rowIndex
            goto L_0x024c
        L_0x022f:
            org.apache.poi.ss.formula.FormulaParseException r2 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            java.lang.String r0 = r0._formulaString
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x0248:
            if (r8 == 0) goto L_0x024e
            int r5 = r0._rowIndex
        L_0x024c:
            r6 = r5
            goto L_0x0259
        L_0x024e:
            int r5 = r5 + 1
            int r1 = r2.getTotalsRowCount()
            if (r1 <= 0) goto L_0x0259
            int r1 = r6 + -1
        L_0x0258:
            r6 = r1
        L_0x0259:
            java.lang.String r1 = " doesn't exist in table "
            java.lang.String r3 = "Fatal error"
            r7 = 2
            if (r15 != r7) goto L_0x02a9
            if (r4 == 0) goto L_0x02a3
            if (r17 == 0) goto L_0x02a3
            int r3 = r2.findColumnIndex(r4)
            r7 = r17
            int r8 = r2.findColumnIndex(r7)
            r9 = -1
            if (r3 == r9) goto L_0x0278
            if (r8 == r9) goto L_0x0278
            int r3 = r27 + r3
            int r4 = r27 + r8
            goto L_0x02e6
        L_0x0278:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "One of the columns "
            r3.<init>(r5)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ", "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r2 = r2.getName()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02a3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x02a9:
            r7 = 1
            if (r15 != r7) goto L_0x02e2
            if (r8 != 0) goto L_0x02e2
            if (r4 == 0) goto L_0x02dc
            int r3 = r2.findColumnIndex(r4)
            r7 = -1
            if (r3 == r7) goto L_0x02bb
            int r3 = r27 + r3
            r4 = r3
            goto L_0x02e6
        L_0x02bb:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "The column "
            r3.<init>(r5)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r2 = r2.getName()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02dc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x02e2:
            r4 = r16
            r3 = r27
        L_0x02e6:
            org.apache.poi.ss.util.CellReference r1 = new org.apache.poi.ss.util.CellReference
            r1.<init>((int) r5, (int) r3)
            org.apache.poi.ss.util.CellReference r2 = new org.apache.poi.ss.util.CellReference
            r2.<init>((int) r6, (int) r4)
            org.apache.poi.ss.formula.SheetIdentifier r3 = new org.apache.poi.ss.formula.SheetIdentifier
            org.apache.poi.ss.formula.NameIdentifier r4 = new org.apache.poi.ss.formula.NameIdentifier
            r5 = r25
            r6 = 1
            r4.<init>(r5, r6)
            r5 = 0
            r3.<init>(r5, r4)
            org.apache.poi.ss.formula.FormulaParsingWorkbook r4 = r0._book
            org.apache.poi.ss.util.AreaReference r5 = new org.apache.poi.ss.util.AreaReference
            org.apache.poi.ss.SpreadsheetVersion r0 = r0._ssVersion
            r5.<init>(r1, r2, r0)
            org.apache.poi.ss.formula.ptg.Ptg r0 = r4.get3DReferencePtg((org.apache.poi.ss.util.AreaReference) r5, (org.apache.poi.ss.formula.SheetIdentifier) r3)
            org.apache.poi.ss.formula.ParseNode r1 = new org.apache.poi.ss.formula.ParseNode
            r1.<init>(r0)
            return r1
        L_0x0311:
            if (r7 < 0) goto L_0x031b
            org.apache.poi.ss.formula.ParseNode r0 = new org.apache.poi.ss.formula.ParseNode
            org.apache.poi.ss.formula.ptg.ErrPtg r1 = org.apache.poi.ss.formula.ptg.ErrPtg.VALUE_INVALID
            r0.<init>(r1)
            return r0
        L_0x031b:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.String r1 = "Formula contained [#This Row] or [@] structured reference but this row < 0. Row index must be specified for row-referencing structured references."
            r0.<init>(r1)
            throw r0
        L_0x0323:
            org.apache.poi.ss.formula.FormulaParseException r2 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.String r0 = r0._formulaString
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r1 = " is illegal: you should not use ',' with column quantifiers"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x033e:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Illegal table name: '"
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = "'"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0359:
            org.apache.poi.ss.formula.FormulaParseException r0 = new org.apache.poi.ss.formula.FormulaParseException
            java.lang.String r1 = "Structured references work only on XSSF (Excel 2007+)!"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.parseStructuredReference(java.lang.String):org.apache.poi.ss.formula.ParseNode");
    }

    private String parseAsColumnQuantifier() {
        if (this.look != 91) {
            return null;
        }
        GetChar();
        int i = this.look;
        if (i == 35) {
            return null;
        }
        if (i == 64) {
            GetChar();
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = this.look;
            if (i2 != 93) {
                sb.appendCodePoint(i2);
                GetChar();
            } else {
                Match(93);
                return sb.toString();
            }
        }
    }

    private String parseAsSpecialQuantifier() {
        if (this.look != 91) {
            return null;
        }
        GetChar();
        if (this.look != 35) {
            return null;
        }
        GetChar();
        String parseAsName = parseAsName();
        if (parseAsName.equals("This")) {
            parseAsName = parseAsName + Chars.SPACE + parseAsName();
        }
        Match(93);
        return parseAsName;
    }

    private ParseNode parseNonRange(int i) {
        resetPointer(i);
        if (Character.isDigit(this.look)) {
            return new ParseNode(parseNumber());
        }
        if (this.look == 34) {
            return new ParseNode(new StringPtg(parseStringLiteral()));
        }
        String parseAsName = parseAsName();
        int i2 = this.look;
        if (i2 == 40) {
            return function(parseAsName);
        }
        if (i2 == 91) {
            return parseStructuredReference(parseAsName);
        }
        if (parseAsName.equalsIgnoreCase("TRUE") || parseAsName.equalsIgnoreCase("FALSE")) {
            return new ParseNode(BoolPtg.valueOf(parseAsName.equalsIgnoreCase("TRUE")));
        }
        FormulaParsingWorkbook formulaParsingWorkbook = this._book;
        if (formulaParsingWorkbook != null) {
            EvaluationName name = formulaParsingWorkbook.getName(parseAsName, this._sheetIndex);
            if (name == null) {
                throw new FormulaParseException("Specified named range '" + parseAsName + "' does not exist in the current workbook.");
            } else if (name.isRange()) {
                return new ParseNode(name.createPtg());
            } else {
                throw new FormulaParseException("Specified name '" + parseAsName + "' is not a range as expected.");
            }
        } else {
            throw new IllegalStateException("Need book to evaluate name '" + parseAsName + "'");
        }
    }

    private String parseAsName() {
        int i;
        StringBuilder sb = new StringBuilder();
        if (Character.isLetter(this.look) || (i = this.look) == 95 || i == 92) {
            while (isValidDefinedNameChar(this.look)) {
                sb.appendCodePoint(this.look);
                GetChar();
            }
            SkipWhite();
            return sb.toString();
        }
        throw expected("number, string, defined name, or data table");
    }

    private static boolean isValidDefinedNameChar(int i) {
        return Character.isLetterOrDigit(i) || i > 128 || i == 46 || i == 63 || i == 92 || i == 95;
    }

    private ParseNode createAreaRefParseNode(SheetIdentifier sheetIdentifier, SimpleRangePart simpleRangePart, SimpleRangePart simpleRangePart2) throws FormulaParseException {
        Ptg ptg;
        if (simpleRangePart2 == null) {
            CellReference cellReference = simpleRangePart.getCellReference();
            if (sheetIdentifier == null) {
                ptg = new RefPtg(cellReference);
            } else {
                ptg = this._book.get3DReferencePtg(cellReference, sheetIdentifier);
            }
        } else {
            AreaReference createAreaRef = createAreaRef(simpleRangePart, simpleRangePart2);
            if (sheetIdentifier == null) {
                ptg = new AreaPtg(createAreaRef);
            } else {
                ptg = this._book.get3DReferencePtg(createAreaRef, sheetIdentifier);
            }
        }
        return new ParseNode(ptg);
    }

    private AreaReference createAreaRef(SimpleRangePart simpleRangePart, SimpleRangePart simpleRangePart2) {
        if (!simpleRangePart.isCompatibleForArea(simpleRangePart2)) {
            throw new FormulaParseException("has incompatible parts: '" + simpleRangePart.getRep() + "' and '" + simpleRangePart2.getRep() + "'.");
        } else if (simpleRangePart.isRow()) {
            return AreaReference.getWholeRow(this._ssVersion, simpleRangePart.getRep(), simpleRangePart2.getRep());
        } else {
            if (simpleRangePart.isColumn()) {
                return AreaReference.getWholeColumn(this._ssVersion, simpleRangePart.getRep(), simpleRangePart2.getRep());
            }
            return new AreaReference(simpleRangePart.getCellReference(), simpleRangePart2.getCellReference(), this._ssVersion);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0077, code lost:
        if (r5 <= r8._ssVersion.getMaxRows()) goto L_0x007a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.FormulaParser.SimpleRangePart parseSimpleRangePart() {
        /*
            r8 = this;
            int r0 = r8._pointer
            r1 = 1
            int r0 = r0 - r1
            r2 = 0
            r3 = r2
        L_0x0006:
            int r4 = r8._formulaLength
            if (r0 >= r4) goto L_0x002b
            java.lang.String r4 = r8._formulaString
            char r4 = r4.charAt(r0)
            boolean r5 = java.lang.Character.isDigit(r4)
            if (r5 == 0) goto L_0x0018
            r2 = r1
            goto L_0x0028
        L_0x0018:
            boolean r5 = java.lang.Character.isLetter(r4)
            if (r5 == 0) goto L_0x0020
            r3 = r1
            goto L_0x0028
        L_0x0020:
            r5 = 36
            if (r4 == r5) goto L_0x0028
            r5 = 95
            if (r4 != r5) goto L_0x002b
        L_0x0028:
            int r0 = r0 + 1
            goto L_0x0006
        L_0x002b:
            int r4 = r8._pointer
            int r5 = r4 + -1
            r6 = 0
            if (r0 > r5) goto L_0x0033
            return r6
        L_0x0033:
            java.lang.String r5 = r8._formulaString
            int r4 = r4 - r1
            java.lang.String r4 = r5.substring(r4, r0)
            java.util.regex.Pattern r5 = CELL_REF_PATTERN
            java.util.regex.Matcher r5 = r5.matcher(r4)
            boolean r5 = r5.matches()
            if (r5 != 0) goto L_0x0047
            return r6
        L_0x0047:
            if (r3 == 0) goto L_0x0052
            if (r2 == 0) goto L_0x0052
            boolean r5 = r8.isValidCellReference(r4)
            if (r5 != 0) goto L_0x007a
            return r6
        L_0x0052:
            java.lang.String r5 = ""
            java.lang.String r7 = "$"
            if (r3 == 0) goto L_0x0065
            java.lang.String r5 = r4.replace(r7, r5)
            org.apache.poi.ss.SpreadsheetVersion r7 = r8._ssVersion
            boolean r5 = org.apache.poi.ss.util.CellReference.isColumnWithinRange(r5, r7)
            if (r5 != 0) goto L_0x007a
            return r6
        L_0x0065:
            if (r2 == 0) goto L_0x0084
            java.lang.String r5 = r4.replace(r7, r5)     // Catch:{ NumberFormatException -> 0x0084 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x0084 }
            if (r5 < r1) goto L_0x0084
            org.apache.poi.ss.SpreadsheetVersion r7 = r8._ssVersion
            int r7 = r7.getMaxRows()
            if (r5 <= r7) goto L_0x007a
            goto L_0x0084
        L_0x007a:
            int r0 = r0 + r1
            r8.resetPointer(r0)
            org.apache.poi.ss.formula.FormulaParser$SimpleRangePart r8 = new org.apache.poi.ss.formula.FormulaParser$SimpleRangePart
            r8.<init>(r4, r3, r2)
            return r8
        L_0x0084:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.parseSimpleRangePart():org.apache.poi.ss.formula.FormulaParser$SimpleRangePart");
    }

    private static final class SimpleRangePart {
        private final String _rep;
        private final Type _type;

        private enum Type {
            CELL,
            ROW,
            COLUMN;

            public static Type get(boolean z, boolean z2) {
                if (z) {
                    return z2 ? CELL : COLUMN;
                }
                if (z2) {
                    return ROW;
                }
                throw new IllegalArgumentException("must have either letters or numbers");
            }
        }

        public SimpleRangePart(String str, boolean z, boolean z2) {
            this._rep = str;
            this._type = Type.get(z, z2);
        }

        public boolean isCell() {
            return this._type == Type.CELL;
        }

        public boolean isRowOrColumn() {
            return this._type != Type.CELL;
        }

        public CellReference getCellReference() {
            if (this._type == Type.CELL) {
                return new CellReference(this._rep);
            }
            throw new IllegalStateException("Not applicable to this type");
        }

        public boolean isColumn() {
            return this._type == Type.COLUMN;
        }

        public boolean isRow() {
            return this._type == Type.ROW;
        }

        public String getRep() {
            return this._rep;
        }

        public boolean isCompatibleForArea(SimpleRangePart simpleRangePart) {
            return this._type == simpleRangePart._type;
        }

        public String toString() {
            return getClass().getName() + " [" + this._rep + "]";
        }
    }

    private String getBookName() {
        StringBuilder sb = new StringBuilder();
        GetChar();
        while (true) {
            int i = this.look;
            if (i != 93) {
                sb.appendCodePoint(i);
                GetChar();
            } else {
                GetChar();
                return sb.toString();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r9.look == 39) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r4 != false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r2.appendCodePoint(r9.look);
        GetChar();
        r3 = r9.look;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (r3 == 39) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r3 == 58) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        GetChar();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r9.look != 39) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        GetChar();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        r3 = new org.apache.poi.ss.formula.NameIdentifier(r2.toString(), true);
        SkipWhite();
        r2 = r9.look;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        if (r2 != 33) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        GetChar();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        return new org.apache.poi.ss.formula.SheetIdentifier(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (r2 != 58) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        return parseSheetRange(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.SheetIdentifier parseSheetName() {
        /*
            r9 = this;
            int r0 = r9.look
            r1 = 0
            r2 = 91
            if (r0 != r2) goto L_0x000c
            java.lang.String r0 = r9.getBookName()
            goto L_0x000d
        L_0x000c:
            r0 = r1
        L_0x000d:
            int r3 = r9.look
            r4 = 0
            r5 = 58
            r6 = 33
            r7 = 39
            if (r3 != r7) goto L_0x006b
            r9.Match(r7)
            int r3 = r9.look
            if (r3 != r2) goto L_0x0023
            java.lang.String r0 = r9.getBookName()
        L_0x0023:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            int r3 = r9.look
            r8 = 1
            if (r3 != r7) goto L_0x002e
        L_0x002d:
            r4 = r8
        L_0x002e:
            if (r4 != 0) goto L_0x004a
            int r3 = r9.look
            r2.appendCodePoint(r3)
            r9.GetChar()
            int r3 = r9.look
            if (r3 == r7) goto L_0x003f
            if (r3 == r5) goto L_0x002d
            goto L_0x002e
        L_0x003f:
            r9.GetChar()
            int r3 = r9.look
            if (r3 != r7) goto L_0x002d
            r9.GetChar()
            goto L_0x002e
        L_0x004a:
            org.apache.poi.ss.formula.NameIdentifier r3 = new org.apache.poi.ss.formula.NameIdentifier
            java.lang.String r2 = r2.toString()
            r3.<init>(r2, r8)
            r9.SkipWhite()
            int r2 = r9.look
            if (r2 != r6) goto L_0x0063
            r9.GetChar()
            org.apache.poi.ss.formula.SheetIdentifier r9 = new org.apache.poi.ss.formula.SheetIdentifier
            r9.<init>(r0, r3)
            return r9
        L_0x0063:
            if (r2 != r5) goto L_0x006a
            org.apache.poi.ss.formula.SheetIdentifier r9 = r9.parseSheetRange(r0, r3)
            return r9
        L_0x006a:
            return r1
        L_0x006b:
            r2 = 95
            if (r3 == r2) goto L_0x0086
            boolean r2 = java.lang.Character.isLetter(r3)
            if (r2 == 0) goto L_0x0076
            goto L_0x0086
        L_0x0076:
            int r2 = r9.look
            if (r2 != r6) goto L_0x0085
            if (r0 == 0) goto L_0x0085
            r9.GetChar()
            org.apache.poi.ss.formula.SheetIdentifier r9 = new org.apache.poi.ss.formula.SheetIdentifier
            r9.<init>(r0, r1)
            return r9
        L_0x0085:
            return r1
        L_0x0086:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L_0x008b:
            int r3 = r9.look
            boolean r3 = isUnquotedSheetNameChar(r3)
            if (r3 == 0) goto L_0x009c
            int r3 = r9.look
            r2.appendCodePoint(r3)
            r9.GetChar()
            goto L_0x008b
        L_0x009c:
            int r3 = r9.look
            if (r3 != r7) goto L_0x00a3
            r9.GetChar()
        L_0x00a3:
            org.apache.poi.ss.formula.NameIdentifier r3 = new org.apache.poi.ss.formula.NameIdentifier
            java.lang.String r2 = r2.toString()
            r3.<init>(r2, r4)
            r9.SkipWhite()
            int r2 = r9.look
            if (r2 != r6) goto L_0x00bc
            r9.GetChar()
            org.apache.poi.ss.formula.SheetIdentifier r9 = new org.apache.poi.ss.formula.SheetIdentifier
            r9.<init>(r0, r3)
            return r9
        L_0x00bc:
            if (r2 != r5) goto L_0x00c3
            org.apache.poi.ss.formula.SheetIdentifier r9 = r9.parseSheetRange(r0, r3)
            return r9
        L_0x00c3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.parseSheetName():org.apache.poi.ss.formula.SheetIdentifier");
    }

    private SheetIdentifier parseSheetRange(String str, NameIdentifier nameIdentifier) {
        GetChar();
        SheetIdentifier parseSheetName = parseSheetName();
        if (parseSheetName != null) {
            return new SheetRangeIdentifier(str, nameIdentifier, parseSheetName.getSheetIdentifier());
        }
        return null;
    }

    private static boolean isUnquotedSheetNameChar(int i) {
        return Character.isLetterOrDigit(i) || i > 128 || i == 32 || i == 46 || i == 95;
    }

    private boolean isValidCellReference(String str) {
        boolean z = true;
        boolean z2 = CellReference.classifyCellReference(str, this._ssVersion) == CellReference.NameType.CELL;
        if (!z2) {
            return z2;
        }
        if (!(FunctionMetadataRegistry.getFunctionByName(str.toUpperCase(Locale.ROOT)) != null)) {
            return z2;
        }
        int i = this._pointer;
        resetPointer(str.length() + i);
        SkipWhite();
        if (this.look == 40) {
            z = false;
        }
        resetPointer(i);
        return z;
    }

    private ParseNode function(String str) {
        Ptg ptg = null;
        if (!AbstractFunctionPtg.isBuiltInFunctionName(str)) {
            FormulaParsingWorkbook formulaParsingWorkbook = this._book;
            if (formulaParsingWorkbook != null) {
                EvaluationName name = formulaParsingWorkbook.getName(str, this._sheetIndex);
                if (name == null) {
                    ptg = this._book.getNameXPtg(str, (SheetIdentifier) null);
                    if (ptg == null) {
                        LOGGER.atWarn().log("FormulaParser.function: Name '{}' is completely unknown in the current workbook.", (Object) str);
                        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$SpreadsheetVersion[this._book.getSpreadsheetVersion().ordinal()];
                        if (i == 1) {
                            addName(str);
                            ptg = this._book.getName(str, this._sheetIndex).createPtg();
                        } else if (i == 2) {
                            ptg = new NameXPxg(str);
                        } else {
                            throw new IllegalStateException("Unexpected spreadsheet version: " + this._book.getSpreadsheetVersion().name());
                        }
                    }
                } else if (name.isFunctionName()) {
                    ptg = name.createPtg();
                } else {
                    throw new FormulaParseException("Attempt to use name '" + str + "' as a function, but defined name in workbook does not refer to a function");
                }
            } else {
                throw new IllegalStateException("Need book to evaluate name '" + str + "'");
            }
        }
        Match(40);
        ParseNode[] Arguments = Arguments();
        Match(41);
        return getFunction(str, ptg, Arguments);
    }

    /* renamed from: org.apache.poi.ss.formula.FormulaParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$SpreadsheetVersion;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.poi.ss.SpreadsheetVersion[] r0 = org.apache.poi.ss.SpreadsheetVersion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$SpreadsheetVersion = r0
                org.apache.poi.ss.SpreadsheetVersion r1 = org.apache.poi.ss.SpreadsheetVersion.EXCEL97     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$SpreadsheetVersion     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.SpreadsheetVersion r1 = org.apache.poi.ss.SpreadsheetVersion.EXCEL2007     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.AnonymousClass1.<clinit>():void");
        }
    }

    private void addName(String str) {
        Name createName = this._book.createName();
        createName.setFunction(true);
        createName.setNameName(str);
        createName.setSheetIndex(this._sheetIndex);
    }

    private ParseNode getFunction(String str, Ptg ptg, ParseNode[] parseNodeArr) {
        Ptg ptg2;
        FunctionMetadata functionByName = FunctionMetadataRegistry.getFunctionByName(str.toUpperCase(Locale.ROOT));
        int length = parseNodeArr.length;
        if (functionByName == null) {
            if (ptg != null) {
                int i = length + 1;
                ParseNode[] parseNodeArr2 = new ParseNode[i];
                parseNodeArr2[0] = new ParseNode(ptg);
                System.arraycopy(parseNodeArr, 0, parseNodeArr2, 1, length);
                return new ParseNode((Ptg) FuncVarPtg.create(str, i), parseNodeArr2);
            }
            throw new IllegalStateException("NamePtg must be supplied for external functions");
        } else if (ptg == null) {
            boolean z = !functionByName.hasFixedArgsLength();
            int index = functionByName.getIndex();
            if (index == 4 && parseNodeArr.length == 1) {
                return new ParseNode((Ptg) AttrPtg.getSumSingle(), parseNodeArr);
            }
            validateNumArgs(parseNodeArr.length, functionByName);
            if (z) {
                ptg2 = FuncVarPtg.create(str, length);
            } else {
                ptg2 = FuncPtg.create(index);
            }
            return new ParseNode(ptg2, parseNodeArr);
        } else {
            throw new IllegalStateException("NamePtg no applicable to internal functions");
        }
    }

    private void validateNumArgs(int i, FunctionMetadata functionMetadata) {
        int i2;
        String str;
        String str2;
        if (i < functionMetadata.getMinParams()) {
            String str3 = "Too few arguments to function '" + functionMetadata.getName() + "'. ";
            if (functionMetadata.hasFixedArgsLength()) {
                str2 = str3 + "Expected " + functionMetadata.getMinParams();
            } else {
                str2 = str3 + "At least " + functionMetadata.getMinParams() + " were expected";
            }
            throw new FormulaParseException(str2 + " but got " + i + ".");
        }
        if (functionMetadata.hasUnlimitedVarags()) {
            FormulaParsingWorkbook formulaParsingWorkbook = this._book;
            if (formulaParsingWorkbook != null) {
                i2 = formulaParsingWorkbook.getSpreadsheetVersion().getMaxFunctionArgs();
            } else {
                i2 = functionMetadata.getMaxParams();
            }
        } else {
            i2 = functionMetadata.getMaxParams();
        }
        if (i > i2) {
            String str4 = "Too many arguments to function '" + functionMetadata.getName() + "'. ";
            if (functionMetadata.hasFixedArgsLength()) {
                str = str4 + "Expected " + i2;
            } else {
                str = str4 + "At most " + i2 + " were expected";
            }
            throw new FormulaParseException(str + " but got " + i + ".");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r5.look != 41) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r5 = new org.apache.poi.ss.formula.ParseNode[r0.size()];
        r0.toArray(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r3 == false) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r0.add(new org.apache.poi.ss.formula.ParseNode(org.apache.poi.ss.formula.ptg.MissingArgPtg.instance));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.ParseNode[] Arguments() {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 2
            r0.<init>(r1)
            r5.SkipWhite()
            int r1 = r5.look
            r2 = 41
            if (r1 != r2) goto L_0x0012
            org.apache.poi.ss.formula.ParseNode[] r5 = org.apache.poi.ss.formula.ParseNode.EMPTY_ARRAY
            return r5
        L_0x0012:
            r1 = 1
        L_0x0013:
            r3 = r1
        L_0x0014:
            r5.SkipWhite()
            int r4 = r5.look
            boolean r4 = isArgumentDelimiter(r4)
            if (r4 == 0) goto L_0x003f
            if (r3 == 0) goto L_0x002b
            org.apache.poi.ss.formula.ParseNode r3 = new org.apache.poi.ss.formula.ParseNode
            org.apache.poi.ss.formula.ptg.Ptg r4 = org.apache.poi.ss.formula.ptg.MissingArgPtg.instance
            r3.<init>(r4)
            r0.add(r3)
        L_0x002b:
            int r3 = r5.look
            if (r3 != r2) goto L_0x0039
            int r5 = r0.size()
            org.apache.poi.ss.formula.ParseNode[] r5 = new org.apache.poi.ss.formula.ParseNode[r5]
            r0.toArray(r5)
            return r5
        L_0x0039:
            r3 = 44
            r5.Match(r3)
            goto L_0x0013
        L_0x003f:
            org.apache.poi.ss.formula.ParseNode r3 = r5.intersectionExpression()
            r0.add(r3)
            r5.SkipWhite()
            int r3 = r5.look
            boolean r3 = isArgumentDelimiter(r3)
            if (r3 == 0) goto L_0x0053
            r3 = 0
            goto L_0x0014
        L_0x0053:
            java.lang.String r0 = "',' or ')'"
            java.lang.RuntimeException r5 = r5.expected(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.Arguments():org.apache.poi.ss.formula.ParseNode[]");
    }

    private ParseNode powerFactor() {
        ParseNode percentFactor = percentFactor();
        while (true) {
            SkipWhite();
            if (this.look != 94) {
                return percentFactor;
            }
            Match(94);
            percentFactor = new ParseNode(PowerPtg.instance, percentFactor, percentFactor());
        }
    }

    private ParseNode percentFactor() {
        ParseNode parseSimpleFactor = parseSimpleFactor();
        while (true) {
            SkipWhite();
            if (this.look != 37) {
                return parseSimpleFactor;
            }
            Match(37);
            parseSimpleFactor = new ParseNode((Ptg) PercentPtg.instance, parseSimpleFactor);
        }
    }

    private ParseNode parseSimpleFactor() {
        int i;
        SkipWhite();
        int i2 = this.look;
        if (i2 == 34) {
            return new ParseNode(new StringPtg(parseStringLiteral()));
        }
        if (i2 == 35) {
            return new ParseNode(ErrPtg.valueOf(parseErrorLiteral()));
        }
        if (i2 == 40) {
            Match(40);
            ParseNode unionExpression = unionExpression();
            Match(41);
            return new ParseNode((Ptg) ParenthesisPtg.instance, unionExpression);
        } else if (i2 == 43) {
            Match(43);
            return parseUnary(true);
        } else if (i2 == 45) {
            Match(45);
            return parseUnary(false);
        } else if (i2 == 123) {
            Match(123);
            ParseNode parseArray = parseArray();
            Match(125);
            return parseArray;
        } else if (IsAlpha(i2) || Character.isDigit(this.look) || (i = this.look) == 39 || i == 91 || i == 95 || i == 92) {
            return parseRangeExpression();
        } else {
            if (i == 46) {
                return new ParseNode(parseNumber());
            }
            throw expected("cell ref or constant literal");
        }
    }

    private ParseNode parseUnary(boolean z) {
        boolean z2 = IsDigit(this.look) || this.look == 46;
        ParseNode powerFactor = powerFactor();
        if (z2) {
            Ptg token = powerFactor.getToken();
            if (token instanceof NumberPtg) {
                if (z) {
                    return powerFactor;
                }
                return new ParseNode(new NumberPtg(-((NumberPtg) token).getValue()));
            } else if (token instanceof IntPtg) {
                if (z) {
                    return powerFactor;
                }
                return new ParseNode(new NumberPtg((double) (-((IntPtg) token).getValue())));
            }
        }
        return new ParseNode(z ? UnaryPlusPtg.instance : UnaryMinusPtg.instance, powerFactor);
    }

    private ParseNode parseArray() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(parseArrayRow());
            int i = this.look;
            if (i == 125) {
                Object[][] objArr = new Object[arrayList.size()][];
                arrayList.toArray(objArr);
                checkRowLengths(objArr, objArr[0].length);
                return new ParseNode(new ArrayPtg(objArr));
            } else if (i == 59) {
                Match(59);
            } else {
                throw expected("'}' or ';'");
            }
        }
    }

    private void checkRowLengths(Object[][] objArr, int i) {
        int i2 = 0;
        while (i2 < objArr.length) {
            int length = objArr[i2].length;
            if (length == i) {
                i2++;
            } else {
                throw new FormulaParseException("Array row " + i2 + " has length " + length + " but row 0 has length " + i);
            }
        }
    }

    private Object[] parseArrayRow() {
        int i;
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(parseArrayItem());
            SkipWhite();
            i = this.look;
            if (i != 44) {
                break;
            }
            Match(44);
        }
        if (i == 59 || i == 125) {
            Object[] objArr = new Object[arrayList.size()];
            arrayList.toArray(objArr);
            return objArr;
        }
        throw expected("'}' or ','");
    }

    private Object parseArrayItem() {
        SkipWhite();
        int i = this.look;
        if (i == 34) {
            return parseStringLiteral();
        }
        if (i == 35) {
            return ErrorConstant.valueOf(parseErrorLiteral());
        }
        if (i != 45) {
            return (i == 70 || i == 84 || i == 102 || i == 116) ? parseBooleanLiteral() : convertArrayNumber(parseNumber(), true);
        }
        Match(45);
        SkipWhite();
        return convertArrayNumber(parseNumber(), false);
    }

    private Boolean parseBooleanLiteral() {
        String parseUnquotedIdentifier = parseUnquotedIdentifier();
        if ("TRUE".equalsIgnoreCase(parseUnquotedIdentifier)) {
            return Boolean.TRUE;
        }
        if ("FALSE".equalsIgnoreCase(parseUnquotedIdentifier)) {
            return Boolean.FALSE;
        }
        throw expected("'TRUE' or 'FALSE'");
    }

    private static Double convertArrayNumber(Ptg ptg, boolean z) {
        double d;
        if (ptg instanceof IntPtg) {
            d = (double) ((IntPtg) ptg).getValue();
        } else if (ptg instanceof NumberPtg) {
            d = ((NumberPtg) ptg).getValue();
        } else {
            throw new RuntimeException("Unexpected ptg (" + ptg.getClass().getName() + ")");
        }
        if (!z) {
            d = -d;
        }
        return Double.valueOf(d);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.poi.ss.formula.ptg.Ptg parseNumber() {
        /*
            r6 = this;
            java.lang.String r0 = r6.GetNum()
            int r1 = r6.look
            r2 = 46
            r3 = 0
            if (r1 != r2) goto L_0x0013
            r6.GetChar()
            java.lang.String r1 = r6.GetNum()
            goto L_0x0014
        L_0x0013:
            r1 = r3
        L_0x0014:
            int r2 = r6.look
            r4 = 69
            java.lang.String r5 = "Integer"
            if (r2 != r4) goto L_0x0052
            r6.GetChar()
            int r2 = r6.look
            r3 = 43
            if (r2 != r3) goto L_0x0029
            r6.GetChar()
            goto L_0x0033
        L_0x0029:
            r3 = 45
            if (r2 != r3) goto L_0x0033
            r6.GetChar()
            java.lang.String r2 = "-"
            goto L_0x0035
        L_0x0033:
            java.lang.String r2 = ""
        L_0x0035:
            java.lang.String r3 = r6.GetNum()
            if (r3 == 0) goto L_0x004d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r2.toString()
            goto L_0x0052
        L_0x004d:
            java.lang.RuntimeException r6 = r6.expected(r5)
            throw r6
        L_0x0052:
            if (r0 != 0) goto L_0x005c
            if (r1 == 0) goto L_0x0057
            goto L_0x005c
        L_0x0057:
            java.lang.RuntimeException r6 = r6.expected(r5)
            throw r6
        L_0x005c:
            org.apache.poi.ss.formula.ptg.Ptg r6 = getNumberPtgFromString(r0, r1, r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.FormulaParser.parseNumber():org.apache.poi.ss.formula.ptg.Ptg");
    }

    private int parseErrorLiteral() {
        Match(35);
        String parseUnquotedIdentifier = parseUnquotedIdentifier();
        if (parseUnquotedIdentifier != null) {
            String upperCase = parseUnquotedIdentifier.toUpperCase(Locale.ROOT);
            char charAt = upperCase.charAt(0);
            if (charAt == 'D') {
                FormulaError formulaError = FormulaError.DIV0;
                if (upperCase.equals("DIV")) {
                    Match(47);
                    Match(48);
                    Match(33);
                    return formulaError.getCode();
                }
                throw expected(formulaError.getString());
            } else if (charAt == 'N') {
                FormulaError formulaError2 = FormulaError.NAME;
                if (upperCase.equals(formulaError2.name())) {
                    Match(63);
                    return formulaError2.getCode();
                }
                FormulaError formulaError3 = FormulaError.NUM;
                if (upperCase.equals(formulaError3.name())) {
                    Match(33);
                    return formulaError3.getCode();
                }
                FormulaError formulaError4 = FormulaError.NULL;
                if (upperCase.equals(formulaError4.name())) {
                    Match(33);
                    return formulaError4.getCode();
                }
                FormulaError formulaError5 = FormulaError.NA;
                if (upperCase.equals("N")) {
                    Match(47);
                    int i = this.look;
                    if (i == 65 || i == 97) {
                        Match(i);
                        return formulaError5.getCode();
                    }
                    throw expected(formulaError5.getString());
                }
                throw expected("#NAME?, #NUM!, #NULL! or #N/A");
            } else if (charAt == 'R') {
                FormulaError formulaError6 = FormulaError.REF;
                if (upperCase.equals(formulaError6.name())) {
                    Match(33);
                    return formulaError6.getCode();
                }
                throw expected(formulaError6.getString());
            } else if (charAt == 'V') {
                FormulaError formulaError7 = FormulaError.VALUE;
                if (upperCase.equals(formulaError7.name())) {
                    Match(33);
                    return formulaError7.getCode();
                }
                throw expected(formulaError7.getString());
            } else {
                throw expected("#VALUE!, #REF!, #DIV/0!, #NAME?, #NUM!, #NULL! or #N/A");
            }
        } else {
            throw expected("remainder of error constant literal");
        }
    }

    private String parseUnquotedIdentifier() {
        if (this.look != 39) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                if (!Character.isLetterOrDigit(this.look) && this.look != 46) {
                    break;
                }
                sb.appendCodePoint(this.look);
                GetChar();
            }
            if (sb.length() < 1) {
                return null;
            }
            return sb.toString();
        }
        throw expected("unquoted identifier");
    }

    private static Ptg getNumberPtgFromString(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            sb.append(str);
            if (str3 != null) {
                sb.append('E');
                sb.append(str3);
            }
            String sb2 = sb.toString();
            try {
                int parseInt = Integer.parseInt(sb2);
                if (IntPtg.isInRange(parseInt)) {
                    return new IntPtg(parseInt);
                }
                return new NumberPtg(sb2);
            } catch (NumberFormatException unused) {
                return new NumberPtg(sb2);
            }
        } else {
            if (str != null) {
                sb.append(str);
            }
            sb.append('.');
            sb.append(str2);
            if (str3 != null) {
                sb.append('E');
                sb.append(str3);
            }
            return new NumberPtg(sb.toString());
        }
    }

    private String parseStringLiteral() {
        Match(34);
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (this.look == 34) {
                GetChar();
                if (this.look != 34) {
                    return sb.toString();
                }
            }
            sb.appendCodePoint(this.look);
            GetChar();
        }
    }

    private ParseNode Term() {
        Ptg ptg;
        ParseNode powerFactor = powerFactor();
        while (true) {
            SkipWhite();
            int i = this.look;
            if (i == 42) {
                Match(42);
                ptg = MultiplyPtg.instance;
            } else if (i != 47) {
                return powerFactor;
            } else {
                Match(47);
                ptg = DividePtg.instance;
            }
            powerFactor = new ParseNode(ptg, powerFactor, powerFactor());
        }
    }

    private ParseNode unionExpression() {
        ParseNode intersectionExpression = intersectionExpression();
        boolean z = false;
        while (true) {
            SkipWhite();
            if (this.look != 44) {
                break;
            }
            GetChar();
            z = true;
            intersectionExpression = new ParseNode(UnionPtg.instance, intersectionExpression, intersectionExpression());
        }
        return z ? augmentWithMemPtg(intersectionExpression) : intersectionExpression;
    }

    private ParseNode intersectionExpression() {
        ParseNode comparisonExpression = comparisonExpression();
        boolean z = false;
        while (true) {
            SkipWhite();
            if (!this._inIntersection) {
                break;
            }
            try {
                z = true;
                comparisonExpression = new ParseNode(IntersectionPtg.instance, comparisonExpression, comparisonExpression());
            } catch (FormulaParseException unused) {
                resetPointer(this._pointer);
            }
        }
        return z ? augmentWithMemPtg(comparisonExpression) : comparisonExpression;
    }

    private ParseNode comparisonExpression() {
        ParseNode concatExpression = concatExpression();
        while (true) {
            SkipWhite();
            switch (this.look) {
                case 60:
                case 61:
                case 62:
                    concatExpression = new ParseNode(getComparisonToken(), concatExpression, concatExpression());
                default:
                    return concatExpression;
            }
        }
    }

    private Ptg getComparisonToken() {
        int i = this.look;
        if (i == 61) {
            Match(i);
            return EqualPtg.instance;
        }
        boolean z = i == 62;
        Match(i);
        if (!z) {
            int i2 = this.look;
            if (i2 == 61) {
                Match(61);
                return LessEqualPtg.instance;
            } else if (i2 != 62) {
                return LessThanPtg.instance;
            } else {
                Match(62);
                return NotEqualPtg.instance;
            }
        } else if (this.look != 61) {
            return GreaterThanPtg.instance;
        } else {
            Match(61);
            return GreaterEqualPtg.instance;
        }
    }

    private ParseNode concatExpression() {
        ParseNode additiveExpression = additiveExpression();
        while (true) {
            SkipWhite();
            if (this.look != 38) {
                return additiveExpression;
            }
            Match(38);
            additiveExpression = new ParseNode(ConcatPtg.instance, additiveExpression, additiveExpression());
        }
    }

    private ParseNode additiveExpression() {
        Ptg ptg;
        ParseNode Term = Term();
        while (true) {
            SkipWhite();
            int i = this.look;
            if (i == 43) {
                Match(43);
                ptg = AddPtg.instance;
            } else if (i != 45) {
                return Term;
            } else {
                Match(45);
                ptg = SubtractPtg.instance;
            }
            Term = new ParseNode(ptg, Term, Term());
        }
    }

    private void parse() {
        this._pointer = 0;
        GetChar();
        this._rootNode = unionExpression();
        if (this._pointer <= this._formulaLength) {
            throw new FormulaParseException("Unused input [" + this._formulaString.substring(this._pointer - 1) + "] after attempting to parse the formula [" + this._formulaString + "]");
        }
    }

    private Ptg[] getRPNPtg(FormulaType formulaType) {
        new OperandClassTransformer(formulaType).transformFormula(this._rootNode);
        return ParseNode.toTokenArray(this._rootNode);
    }
}
