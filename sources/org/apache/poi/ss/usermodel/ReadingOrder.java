package org.apache.poi.ss.usermodel;

public enum ReadingOrder {
    CONTEXT,
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    public short getCode() {
        return (short) ordinal();
    }

    public static ReadingOrder forLong(long j) {
        if (j >= 0 && j < ((long) values().length)) {
            return values()[(int) j];
        }
        throw new IllegalArgumentException("Invalid ReadingOrder code: " + j);
    }
}
