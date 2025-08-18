package org.apache.xmlbeans.impl.common;

public interface ValidationContext {
    void invalid(String str);

    void invalid(String str, Object[] objArr);
}
