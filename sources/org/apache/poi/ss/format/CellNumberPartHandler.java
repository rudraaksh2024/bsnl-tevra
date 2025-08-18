package org.apache.poi.ss.format;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.util.Internal;

@Internal
public class CellNumberPartHandler implements CellFormatPart.PartHandler {
    private CellNumberFormatter.Special decimalPoint;
    private CellNumberFormatter.Special exponent;
    private boolean improperFraction;
    private char insertSignForExponent;
    private CellNumberFormatter.Special numerator;
    private double scale = 1.0d;
    private CellNumberFormatter.Special slash;
    private final List<CellNumberFormatter.Special> specials = new LinkedList();

    public String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        int i = 0;
        char charAt = str.charAt(0);
        if (charAt != '#') {
            if (charAt == '%') {
                this.scale *= 100.0d;
            } else if (charAt != '?') {
                if (charAt != 'E' && charAt != 'e') {
                    switch (charAt) {
                        case '.':
                            if (this.decimalPoint == null && !this.specials.isEmpty()) {
                                CellNumberFormatter.Special special = new CellNumberFormatter.Special('.', length);
                                this.decimalPoint = special;
                                this.specials.add(special);
                                break;
                            }
                        case '/':
                            if (this.slash == null && !this.specials.isEmpty()) {
                                CellNumberFormatter.Special previousNumber = previousNumber();
                                this.numerator = previousNumber;
                                boolean z = this.improperFraction;
                                if (previousNumber == firstDigit(this.specials)) {
                                    i = 1;
                                }
                                this.improperFraction = i | z;
                                CellNumberFormatter.Special special2 = new CellNumberFormatter.Special('.', length);
                                this.slash = special2;
                                this.specials.add(special2);
                                break;
                            }
                        case '0':
                            break;
                        default:
                            return null;
                    }
                } else if (this.exponent == null && !this.specials.isEmpty()) {
                    CellNumberFormatter.Special special3 = new CellNumberFormatter.Special('.', length);
                    this.exponent = special3;
                    this.specials.add(special3);
                    this.insertSignForExponent = str.charAt(1);
                    return str.substring(0, 1);
                }
            }
            return str;
        }
        if (this.insertSignForExponent != 0) {
            this.specials.add(new CellNumberFormatter.Special(this.insertSignForExponent, length));
            stringBuffer.append(this.insertSignForExponent);
            this.insertSignForExponent = 0;
            length++;
        }
        while (i < str.length()) {
            this.specials.add(new CellNumberFormatter.Special(str.charAt(i), length + i));
            i++;
        }
        return str;
    }

    public double getScale() {
        return this.scale;
    }

    public CellNumberFormatter.Special getDecimalPoint() {
        return this.decimalPoint;
    }

    public CellNumberFormatter.Special getSlash() {
        return this.slash;
    }

    public CellNumberFormatter.Special getExponent() {
        return this.exponent;
    }

    public CellNumberFormatter.Special getNumerator() {
        return this.numerator;
    }

    public List<CellNumberFormatter.Special> getSpecials() {
        return this.specials;
    }

    public boolean isImproperFraction() {
        return this.improperFraction;
    }

    private CellNumberFormatter.Special previousNumber() {
        List<CellNumberFormatter.Special> list = this.specials;
        ListIterator<CellNumberFormatter.Special> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            CellNumberFormatter.Special previous = listIterator.previous();
            if (isDigitFmt(previous)) {
                while (listIterator.hasPrevious()) {
                    CellNumberFormatter.Special previous2 = listIterator.previous();
                    if (previous.pos - previous2.pos > 1 || !isDigitFmt(previous2)) {
                        break;
                    }
                    previous = previous2;
                }
                return previous;
            }
        }
        return null;
    }

    private static boolean isDigitFmt(CellNumberFormatter.Special special) {
        return special.ch == '0' || special.ch == '?' || special.ch == '#';
    }

    private static CellNumberFormatter.Special firstDigit(List<CellNumberFormatter.Special> list) {
        for (CellNumberFormatter.Special next : list) {
            if (isDigitFmt(next)) {
                return next;
            }
        }
        return null;
    }
}
