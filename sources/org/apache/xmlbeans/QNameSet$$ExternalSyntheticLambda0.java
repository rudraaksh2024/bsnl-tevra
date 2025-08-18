package org.apache.xmlbeans;

import java.util.function.Predicate;
import javax.xml.namespace.QName;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class QNameSet$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ QNameSetSpecification f$0;

    public /* synthetic */ QNameSet$$ExternalSyntheticLambda0(QNameSetSpecification qNameSetSpecification) {
        this.f$0 = qNameSetSpecification;
    }

    public final boolean test(Object obj) {
        return this.f$0.contains((QName) obj);
    }
}
