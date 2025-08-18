package org.apache.xmlbeans.impl.xpath;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;

public interface XQuery {
    XmlCursor cursorExecute(Cur cur, XmlOptions xmlOptions);

    XmlObject[] objectExecute(Cur cur, XmlOptions xmlOptions);
}
