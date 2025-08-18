package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.util.ProcessIdUtil;

public final class QNameSet implements QNameSetSpecification, Serializable {
    public static final QNameSet ALL = new QNameSet(Collections.emptySet(), (Set<String>) null, Collections.emptySet(), Collections.emptySet());
    public static final QNameSet EMPTY = new QNameSet((Set<String>) null, Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
    public static final QNameSet LOCAL = new QNameSet((Set<String>) null, Collections.singleton(""), Collections.emptySet(), Collections.emptySet());
    public static final QNameSet NONLOCAL = new QNameSet(Collections.singleton(""), (Set<String>) null, Collections.emptySet(), Collections.emptySet());
    private static final long serialVersionUID = 1;
    private final Set<QName> _excludedQNames;
    private final Set<QName> _includedQNames;
    private final Set<String> _includedURIs;
    private final boolean _inverted;

    private static <T> Set<T> minSetCopy(Set<T> set) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return Collections.emptySet();
        }
        if (set.size() == 1) {
            return Collections.singleton(set.iterator().next());
        }
        return new HashSet(set);
    }

    public static QNameSet forSets(Set<String> set, Set<String> set2, Set<QName> set3, Set<QName> set4) {
        boolean z = false;
        boolean z2 = set != null;
        if (set2 != null) {
            z = true;
        }
        if (z2 == z) {
            throw new IllegalArgumentException("Exactly one of excludedURIs and includedURIs must be null");
        } else if (set == null && set2.isEmpty() && set4.isEmpty()) {
            return EMPTY;
        } else {
            if (set2 == null && set.isEmpty() && set3.isEmpty()) {
                return ALL;
            }
            if (set == null && set2.size() == 1 && set2.contains("") && set4.isEmpty() && set3.isEmpty()) {
                return LOCAL;
            }
            if (set2 != null || set.size() != 1 || !set.contains("") || !set3.isEmpty() || !set4.isEmpty()) {
                return new QNameSet(minSetCopy(set), minSetCopy(set2), minSetCopy(set3), minSetCopy(set4));
            }
            return NONLOCAL;
        }
    }

    public static QNameSet forArray(QName[] qNameArr) {
        if (qNameArr != null) {
            return new QNameSet((Set<String>) null, Collections.emptySet(), Collections.emptySet(), new HashSet(Arrays.asList(qNameArr)));
        }
        throw new IllegalArgumentException("includedQNames cannot be null");
    }

    public static QNameSet forSpecification(QNameSetSpecification qNameSetSpecification) {
        if (qNameSetSpecification instanceof QNameSet) {
            return (QNameSet) qNameSetSpecification;
        }
        return forSets(qNameSetSpecification.excludedURIs(), qNameSetSpecification.includedURIs(), qNameSetSpecification.excludedQNamesInIncludedURIs(), qNameSetSpecification.includedQNamesInExcludedURIs());
    }

    public static QNameSet forWildcardNamespaceString(String str, String str2) {
        return forSpecification(new QNameSetBuilder(str, str2));
    }

    public static QNameSet singleton(QName qName) {
        return new QNameSet((Set<String>) null, Collections.emptySet(), Collections.emptySet(), Collections.singleton(qName));
    }

    private QNameSet(Set<String> set, Set<String> set2, Set<QName> set3, Set<QName> set4) {
        if (set2 != null && set == null) {
            this._inverted = false;
            this._includedURIs = set2;
            this._excludedQNames = set3;
            this._includedQNames = set4;
        } else if (set == null || set2 != null) {
            throw new IllegalArgumentException("Exactly one of excludedURIs and includedURIs must be null");
        } else {
            this._inverted = true;
            this._includedURIs = set;
            this._excludedQNames = set4;
            this._includedQNames = set3;
        }
    }

    private static String nsFromName(QName qName) {
        String namespaceURI = qName.getNamespaceURI();
        return namespaceURI == null ? "" : namespaceURI;
    }

    public boolean contains(QName qName) {
        boolean z;
        if (this._includedURIs.contains(nsFromName(qName))) {
            z = !this._excludedQNames.contains(qName);
        } else {
            z = this._includedQNames.contains(qName);
        }
        return this._inverted ^ z;
    }

    public boolean isAll() {
        return this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    public boolean isEmpty() {
        return !this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    public QNameSet intersect(QNameSetSpecification qNameSetSpecification) {
        QNameSetBuilder qNameSetBuilder = new QNameSetBuilder(this);
        qNameSetBuilder.restrict(qNameSetSpecification);
        return qNameSetBuilder.toQNameSet();
    }

    public QNameSet union(QNameSetSpecification qNameSetSpecification) {
        QNameSetBuilder qNameSetBuilder = new QNameSetBuilder(this);
        qNameSetBuilder.addAll(qNameSetSpecification);
        return qNameSetBuilder.toQNameSet();
    }

    public QNameSet inverse() {
        QNameSet qNameSet = EMPTY;
        if (this == qNameSet) {
            return ALL;
        }
        if (this == ALL) {
            return qNameSet;
        }
        QNameSet qNameSet2 = LOCAL;
        if (this == qNameSet2) {
            return NONLOCAL;
        }
        if (this == NONLOCAL) {
            return qNameSet2;
        }
        return new QNameSet(includedURIs(), excludedURIs(), includedQNamesInExcludedURIs(), excludedQNamesInIncludedURIs());
    }

    public boolean containsAll(QNameSetSpecification qNameSetSpecification) {
        if (this._inverted || qNameSetSpecification.excludedURIs() == null) {
            return inverse().isDisjoint(qNameSetSpecification);
        }
        return false;
    }

    public boolean isDisjoint(QNameSetSpecification qNameSetSpecification) {
        if (this._inverted && qNameSetSpecification.excludedURIs() != null) {
            return false;
        }
        if (this._inverted) {
            return isDisjointImpl(qNameSetSpecification, this);
        }
        return isDisjointImpl(this, qNameSetSpecification);
    }

    private boolean isDisjointImpl(QNameSetSpecification qNameSetSpecification, QNameSetSpecification qNameSetSpecification2) {
        Set<String> includedURIs = qNameSetSpecification.includedURIs();
        Set<String> includedURIs2 = qNameSetSpecification2.includedURIs();
        if (includedURIs2 != null) {
            if (!Collections.disjoint(includedURIs, includedURIs2)) {
                return false;
            }
        } else if (!qNameSetSpecification2.excludedURIs().containsAll(includedURIs)) {
            return false;
        }
        Stream stream = qNameSetSpecification.includedQNamesInExcludedURIs().stream();
        qNameSetSpecification2.getClass();
        if (stream.anyMatch(new QNameSet$$ExternalSyntheticLambda0(qNameSetSpecification2))) {
            return false;
        }
        Stream stream2 = qNameSetSpecification2.includedQNamesInExcludedURIs().stream();
        qNameSetSpecification.getClass();
        if (stream2.anyMatch(new QNameSet$$ExternalSyntheticLambda0(qNameSetSpecification))) {
            return false;
        }
        return true;
    }

    public Set<String> excludedURIs() {
        if (this._inverted) {
            return Collections.unmodifiableSet(this._includedURIs);
        }
        return null;
    }

    public Set<String> includedURIs() {
        if (!this._inverted) {
            return this._includedURIs;
        }
        return null;
    }

    public Set<QName> excludedQNamesInIncludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._includedQNames : this._excludedQNames);
    }

    public Set<QName> includedQNamesInExcludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._excludedQNames : this._includedQNames);
    }

    private String prettyQName(QName qName) {
        if (qName.getNamespaceURI() == null) {
            return qName.getLocalPart();
        }
        return qName.getLocalPart() + "@" + qName.getNamespaceURI();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QNameSet");
        sb.append(this._inverted ? "-(" : "+(");
        for (String append : this._includedURIs) {
            sb.append("+*@");
            sb.append(append);
            sb.append(", ");
        }
        for (QName prettyQName : this._excludedQNames) {
            sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            sb.append(prettyQName(prettyQName));
            sb.append(", ");
        }
        for (QName prettyQName2 : this._includedQNames) {
            sb.append("+");
            sb.append(prettyQName(prettyQName2));
            sb.append(", ");
        }
        int lastIndexOf = sb.lastIndexOf(", ");
        if (lastIndexOf > 0) {
            sb.setLength(lastIndexOf);
        }
        sb.append(')');
        return sb.toString();
    }
}
