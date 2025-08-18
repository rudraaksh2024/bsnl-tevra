package org.apache.xmlbeans.impl.xpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;

public class XPathExecutionContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ATTRS = 4;
    public static final int DESCEND = 2;
    public static final int HIT = 1;
    private PathContext[] _paths;
    /* access modifiers changed from: private */
    public final ArrayList<QName> _stack = new ArrayList<>();
    private XPath _xpath;

    public final void init(XPath xPath) {
        if (this._xpath != xPath) {
            this._xpath = xPath;
            PathContext[] pathContextArr = new PathContext[xPath._selector._paths.length];
            this._paths = pathContextArr;
            Arrays.setAll(pathContextArr, new XPathExecutionContext$$ExternalSyntheticLambda0(this));
        }
        this._stack.clear();
        int i = 0;
        while (true) {
            PathContext[] pathContextArr2 = this._paths;
            if (i < pathContextArr2.length) {
                pathContextArr2[i].init(xPath._selector._paths[i]);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$init$0$org-apache-xmlbeans-impl-xpath-XPathExecutionContext  reason: not valid java name */
    public /* synthetic */ PathContext m2409lambda$init$0$orgapachexmlbeansimplxpathXPathExecutionContext(int i) {
        return new PathContext();
    }

    public final int start() {
        int i = 0;
        for (PathContext start : this._paths) {
            i |= start.start();
        }
        return i;
    }

    public final int element(QName qName) {
        this._stack.add(qName);
        int i = 0;
        for (PathContext element : this._paths) {
            i |= element.element(qName);
        }
        return i;
    }

    public final boolean attr(QName qName) {
        boolean z = false;
        for (PathContext attr : this._paths) {
            z |= attr.attr(qName);
        }
        return z;
    }

    public final void end() {
        ArrayList<QName> arrayList = this._stack;
        arrayList.remove(arrayList.size() - 1);
        for (PathContext end : this._paths) {
            end.end();
        }
    }

    private final class PathContext {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private XPathStep _curr;
        private final List<XPathStep> _prev;

        static {
            Class<XPathExecutionContext> cls = XPathExecutionContext.class;
        }

        private PathContext() {
            this._prev = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public void init(XPathStep xPathStep) {
            this._curr = xPathStep;
            this._prev.clear();
        }

        private QName top(int i) {
            return (QName) XPathExecutionContext.this._stack.get((XPathExecutionContext.this._stack.size() - 1) - i);
        }

        private void backtrack() {
            if (this._curr._hasBacktrack) {
                this._curr = this._curr._backtrack;
                return;
            }
            this._curr = this._curr._prev;
            while (!this._curr._deep) {
                XPathStep xPathStep = this._curr;
                int i = 0;
                while (!xPathStep._deep) {
                    int i2 = i + 1;
                    if (!xPathStep.match(top(i))) {
                        this._curr = this._curr._prev;
                    } else {
                        xPathStep = xPathStep._prev;
                        i = i2;
                    }
                }
                return;
            }
        }

        /* access modifiers changed from: package-private */
        public int start() {
            if (this._curr._name != null) {
                return this._curr._flags;
            }
            this._curr = null;
            return 1;
        }

        /* access modifiers changed from: package-private */
        public int element(QName qName) {
            this._prev.add(this._curr);
            XPathStep xPathStep = this._curr;
            if (xPathStep == null) {
                return 0;
            }
            if (xPathStep._attr || !this._curr.match(qName)) {
                while (true) {
                    backtrack();
                    XPathStep xPathStep2 = this._curr;
                    if (xPathStep2 != null) {
                        if (!xPathStep2.match(qName)) {
                            if (this._curr._deep) {
                                break;
                            }
                        } else {
                            this._curr = this._curr._next;
                            break;
                        }
                    } else {
                        return 0;
                    }
                }
                return this._curr._flags;
            }
            XPathStep xPathStep3 = this._curr._next;
            this._curr = xPathStep3;
            if (xPathStep3._name != null) {
                return this._curr._flags;
            }
            backtrack();
            XPathStep xPathStep4 = this._curr;
            if (xPathStep4 == null) {
                return 1;
            }
            return 1 | xPathStep4._flags;
        }

        /* access modifiers changed from: package-private */
        public boolean attr(QName qName) {
            XPathStep xPathStep = this._curr;
            return xPathStep != null && xPathStep._attr && this._curr.match(qName);
        }

        /* access modifiers changed from: package-private */
        public void end() {
            List<XPathStep> list = this._prev;
            this._curr = list.remove(list.size() - 1);
        }
    }
}
