package org.apache.poi.ss.formula;

import org.apache.poi.util.Internal;

@Internal
enum OperatorEnum {
    NO_COMPARISON(new OperatorEnum$$ExternalSyntheticLambda0(), false),
    BETWEEN(new OperatorEnum$$ExternalSyntheticLambda1(), false),
    NOT_BETWEEN(new OperatorEnum$$ExternalSyntheticLambda2(), true),
    EQUAL(new OperatorEnum$$ExternalSyntheticLambda3(), false),
    NOT_EQUAL(new OperatorEnum$$ExternalSyntheticLambda4(), true),
    GREATER_THAN(new OperatorEnum$$ExternalSyntheticLambda5(), false),
    LESS_THAN(new OperatorEnum$$ExternalSyntheticLambda6(), false),
    GREATER_OR_EQUAL(new OperatorEnum$$ExternalSyntheticLambda7(), false),
    LESS_OR_EQUAL(new OperatorEnum$$ExternalSyntheticLambda8(), false);
    
    private final CompareOp compareOp;
    private final boolean validForIncompatibleTypes;

    private interface CompareOp {
        <C extends Comparable<C>> boolean isValid(C c, C c2, C c3);
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean noComp(C c, C c2, C c3) {
        return false;
    }

    private OperatorEnum(CompareOp compareOp2, boolean z) {
        this.compareOp = compareOp2;
        this.validForIncompatibleTypes = z;
    }

    /* access modifiers changed from: package-private */
    public <C extends Comparable<C>> boolean isValid(C c, C c2, C c3) {
        return this.compareOp.isValid(c, c2, c3);
    }

    /* access modifiers changed from: package-private */
    public boolean isValidForIncompatibleTypes() {
        return this.validForIncompatibleTypes;
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean between(C c, C c2, C c3) {
        String str;
        double d;
        if (c2 == null) {
            if (c instanceof Number) {
                if (c3 == null) {
                    d = 0.0d;
                } else {
                    d = ((Number) c3).doubleValue();
                }
                Number number = (Number) c;
                if (Double.compare(number.doubleValue(), 0.0d) < 0 || Double.compare(number.doubleValue(), d) > 0) {
                    return false;
                }
                return true;
            } else if (c instanceof String) {
                if (c3 == null) {
                    str = "";
                } else {
                    str = (String) c3;
                }
                String str2 = (String) c;
                if (str2.compareToIgnoreCase("") < 0 || str2.compareToIgnoreCase(str) > 0) {
                    return false;
                }
                return true;
            } else {
                boolean z = c instanceof Boolean;
                return false;
            }
        } else if (c.compareTo(c2) < 0 || c.compareTo(c3) > 0) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean notBetween(C c, C c2, C c3) {
        String str;
        double d;
        if (c2 == null) {
            if (c instanceof Number) {
                if (c3 == null) {
                    d = 0.0d;
                } else {
                    d = ((Number) c3).doubleValue();
                }
                Number number = (Number) c;
                if (Double.compare(number.doubleValue(), 0.0d) < 0 || Double.compare(number.doubleValue(), d) > 0) {
                    return true;
                }
                return false;
            } else if (!(c instanceof String)) {
                return c instanceof Boolean;
            } else {
                if (c3 == null) {
                    str = "";
                } else {
                    str = (String) c3;
                }
                String str2 = (String) c;
                if (str2.compareToIgnoreCase("") < 0 || str2.compareToIgnoreCase(str) > 0) {
                    return true;
                }
                return false;
            }
        } else if (c.compareTo(c2) < 0 || c.compareTo(c3) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean equalCheck(C c, C c2, C c3) {
        if (c2 == null) {
            if (c instanceof Number) {
                if (Double.compare(((Number) c).doubleValue(), 0.0d) == 0) {
                    return true;
                }
                return false;
            } else if (c instanceof String) {
                return false;
            } else {
                boolean z = c instanceof Boolean;
                return false;
            }
        } else if (c instanceof String) {
            if (c.toString().compareToIgnoreCase(c2.toString()) == 0) {
                return true;
            }
            return false;
        } else if (c.compareTo(c2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean notEqual(C c, C c2, C c3) {
        if (c2 == null) {
            return true;
        }
        if (c instanceof String) {
            if (c.toString().compareToIgnoreCase(c2.toString()) == 0) {
                return true;
            }
            return false;
        } else if (c.compareTo(c2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean greaterThan(C c, C c2, C c3) {
        if (c2 == null) {
            if (c instanceof Number) {
                if (Double.compare(((Number) c).doubleValue(), 0.0d) > 0) {
                    return true;
                }
                return false;
            } else if (c instanceof String) {
                return true;
            } else {
                return c instanceof Boolean;
            }
        } else if (c.compareTo(c2) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean lessThan(C c, C c2, C c3) {
        if (c2 == null) {
            if (c instanceof Number) {
                if (Double.compare(((Number) c).doubleValue(), 0.0d) < 0) {
                    return true;
                }
                return false;
            } else if (c instanceof String) {
                return false;
            } else {
                boolean z = c instanceof Boolean;
                return false;
            }
        } else if (c.compareTo(c2) < 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean greaterOrEqual(C c, C c2, C c3) {
        if (c2 == null) {
            if (c instanceof Number) {
                if (Double.compare(((Number) c).doubleValue(), 0.0d) >= 0) {
                    return true;
                }
                return false;
            } else if (c instanceof String) {
                return true;
            } else {
                return c instanceof Boolean;
            }
        } else if (c.compareTo(c2) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static <C extends Comparable<C>> boolean lessOrEqual(C c, C c2, C c3) {
        if (c2 == null) {
            if (c instanceof Number) {
                if (Double.compare(((Number) c).doubleValue(), 0.0d) <= 0) {
                    return true;
                }
                return false;
            } else if (c instanceof String) {
                return false;
            } else {
                boolean z = c instanceof Boolean;
                return false;
            }
        } else if (c.compareTo(c2) <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
