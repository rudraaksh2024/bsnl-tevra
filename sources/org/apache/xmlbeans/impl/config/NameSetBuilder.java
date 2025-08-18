package org.apache.xmlbeans.impl.config;

import java.util.HashSet;
import java.util.Set;

public class NameSetBuilder {
    private final Set<String> _finiteSet = new HashSet();
    private boolean _isFinite = true;

    public void invert() {
        this._isFinite = !this._isFinite;
    }

    public void add(String str) {
        if (this._isFinite) {
            this._finiteSet.add(str);
        } else {
            this._finiteSet.remove(str);
        }
    }

    public NameSet toNameSet() {
        if (this._finiteSet.size() != 0) {
            return NameSet.newInstance(this._isFinite, this._finiteSet);
        }
        if (this._isFinite) {
            return NameSet.EMPTY;
        }
        return NameSet.EVERYTHING;
    }
}
