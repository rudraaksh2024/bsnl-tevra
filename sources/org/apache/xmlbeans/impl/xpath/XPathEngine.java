package org.apache.xmlbeans.impl.xpath;

import org.apache.xmlbeans.impl.store.Cur;

public interface XPathEngine {
    boolean next(Cur cur);

    void release();
}
