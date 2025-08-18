package org.apache.poi.common.usermodel;

import org.apache.poi.util.Internal;

public enum HyperlinkType {
    NONE(-1),
    URL(1),
    DOCUMENT(2),
    EMAIL(3),
    FILE(4);
    
    @Internal(since = "3.15 beta 3")
    @Deprecated
    private final int code;

    @Internal(since = "3.15 beta 3")
    @Deprecated
    private HyperlinkType(int i) {
        this.code = i;
    }

    /* access modifiers changed from: package-private */
    @Internal(since = "3.15 beta 3")
    public int getCode() {
        return this.code;
    }
}
