package org.apache.poi.ss.format;

import java.util.Locale;

public enum CellFormatType {
    GENERAL {
        /* access modifiers changed from: package-private */
        public boolean isSpecial(char c) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(String str) {
            return new CellGeneralFormatter();
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(Locale locale, String str) {
            return new CellGeneralFormatter(locale);
        }
    },
    NUMBER {
        /* access modifiers changed from: package-private */
        public boolean isSpecial(char c) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(String str) {
            return new CellNumberFormatter(str);
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(Locale locale, String str) {
            return new CellNumberFormatter(locale, str);
        }
    },
    DATE {
        /* access modifiers changed from: package-private */
        public boolean isSpecial(char c) {
            return c == '\'' || (c <= 127 && Character.isLetter(c));
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(String str) {
            return new CellDateFormatter(str);
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(Locale locale, String str) {
            return new CellDateFormatter(locale, str);
        }
    },
    ELAPSED {
        /* access modifiers changed from: package-private */
        public boolean isSpecial(char c) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(String str) {
            return new CellElapsedFormatter(str);
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(Locale locale, String str) {
            return new CellElapsedFormatter(str);
        }
    },
    TEXT {
        /* access modifiers changed from: package-private */
        public boolean isSpecial(char c) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(String str) {
            return new CellTextFormatter(str);
        }

        /* access modifiers changed from: package-private */
        public CellFormatter formatter(Locale locale, String str) {
            return new CellTextFormatter(str);
        }
    };

    /* access modifiers changed from: package-private */
    public abstract CellFormatter formatter(String str);

    /* access modifiers changed from: package-private */
    public abstract CellFormatter formatter(Locale locale, String str);

    /* access modifiers changed from: package-private */
    public abstract boolean isSpecial(char c);
}
