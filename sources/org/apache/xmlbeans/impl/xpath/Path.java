package org.apache.xmlbeans.impl.xpath;

import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;

public interface Path {
    XPathEngine execute(Cur cur, XmlOptions xmlOptions);
}
