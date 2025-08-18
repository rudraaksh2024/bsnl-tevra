package com.graphbuilder.math;

public abstract class TermNode extends Expression {
    protected String name = null;
    protected boolean negate = false;

    public TermNode(String str, boolean z) {
        setName(str);
        setNegate(z);
    }

    public boolean getNegate() {
        return this.negate;
    }

    public void setNegate(boolean z) {
        this.negate = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null");
        } else if (isValidName(str)) {
            this.name = str;
        } else {
            throw new IllegalArgumentException("invalid name: " + str);
        }
    }

    private static boolean isValidName(String str) {
        if (str.length() == 0) {
            return false;
        }
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '.' || charAt == ',' || charAt == '(' || charAt == ')' || charAt == '^' || charAt == '*' || charAt == '/' || charAt == '+' || charAt == '-' || charAt == ' ' || charAt == 9 || charAt == 10) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char charAt2 = str.charAt(i);
            if (charAt2 == ',' || charAt2 == '(' || charAt2 == ')' || charAt2 == '^' || charAt2 == '*' || charAt2 == '/' || charAt2 == '+' || charAt2 == '-' || charAt2 == ' ' || charAt2 == 9 || charAt2 == 10) {
                return false;
            }
        }
        return true;
    }
}
