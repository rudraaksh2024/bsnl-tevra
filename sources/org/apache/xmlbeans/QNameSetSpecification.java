package org.apache.xmlbeans;

import java.util.Set;
import javax.xml.namespace.QName;

public interface QNameSetSpecification {
    boolean contains(QName qName);

    boolean containsAll(QNameSetSpecification qNameSetSpecification);

    Set<QName> excludedQNamesInIncludedURIs();

    Set<String> excludedURIs();

    Set<QName> includedQNamesInExcludedURIs();

    Set<String> includedURIs();

    QNameSet intersect(QNameSetSpecification qNameSetSpecification);

    QNameSet inverse();

    boolean isAll();

    boolean isDisjoint(QNameSetSpecification qNameSetSpecification);

    boolean isEmpty();

    QNameSet union(QNameSetSpecification qNameSetSpecification);
}
