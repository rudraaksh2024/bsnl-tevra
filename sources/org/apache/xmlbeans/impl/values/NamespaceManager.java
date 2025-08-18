package org.apache.xmlbeans.impl.values;

import org.apache.xmlbeans.impl.common.PrefixResolver;

public interface NamespaceManager extends PrefixResolver {
    String find_prefix_for_nsuri(String str, String str2);
}
