package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;

public class NameIdentifier {
    private final boolean _isQuoted;
    private final String _name;

    public NameIdentifier(String str, boolean z) {
        this._name = str;
        this._isQuoted = z;
    }

    public String getName() {
        return this._name;
    }

    public boolean isQuoted() {
        return this._isQuoted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append(" [");
        if (this._isQuoted) {
            sb.append(Chars.QUOTE).append(this._name).append("'");
        } else {
            sb.append(this._name);
        }
        sb.append(']');
        return sb.toString();
    }
}
