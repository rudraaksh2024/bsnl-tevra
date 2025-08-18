package org.apache.xmlbeans.impl.values;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Map;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.xml.stream.StartElement;

public class NamespaceContext implements PrefixResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAP = 3;
    private static final int RESOLVER = 5;
    private static final int START_ELEMENT = 4;
    private static final int TYPE_STORE = 1;
    private static final int XML_OBJECT = 2;
    private static ThreadLocal tl_namespaceContextStack = new ThreadLocal();
    private int _code = 3;
    private Object _obj;

    public NamespaceContext(Map map) {
        this._obj = map;
    }

    public NamespaceContext(TypeStore typeStore) {
        this._obj = typeStore;
    }

    public NamespaceContext(XmlObject xmlObject) {
        this._obj = xmlObject;
    }

    public NamespaceContext(StartElement startElement) {
        this._obj = startElement;
    }

    public NamespaceContext(PrefixResolver prefixResolver) {
        this._obj = prefixResolver;
    }

    private static final class NamespaceContextStack {
        NamespaceContext current;
        ArrayList stack;

        private NamespaceContextStack() {
            this.stack = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public final void push(NamespaceContext namespaceContext) {
            this.stack.add(this.current);
            this.current = namespaceContext;
        }

        /* access modifiers changed from: package-private */
        public final void pop() {
            ArrayList arrayList = this.stack;
            this.current = (NamespaceContext) arrayList.get(arrayList.size() - 1);
            ArrayList arrayList2 = this.stack;
            arrayList2.remove(arrayList2.size() - 1);
        }
    }

    public static void clearThreadLocals() {
        tl_namespaceContextStack.remove();
    }

    private static NamespaceContextStack getNamespaceContextStack() {
        NamespaceContextStack namespaceContextStack = (NamespaceContextStack) tl_namespaceContextStack.get();
        if (namespaceContextStack != null) {
            return namespaceContextStack;
        }
        NamespaceContextStack namespaceContextStack2 = new NamespaceContextStack();
        tl_namespaceContextStack.set(namespaceContextStack2);
        return namespaceContextStack2;
    }

    public static void push(NamespaceContext namespaceContext) {
        getNamespaceContextStack().push(namespaceContext);
    }

    public static void pop() {
        NamespaceContextStack namespaceContextStack = getNamespaceContextStack();
        namespaceContextStack.pop();
        if (namespaceContextStack.stack.size() == 0) {
            tl_namespaceContextStack.set((Object) null);
        }
    }

    public static PrefixResolver getCurrent() {
        return getNamespaceContextStack().current;
    }

    public String getNamespaceForPrefix(String str) {
        if (str != null && str.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        int i = this._code;
        if (i == 1) {
            return ((TypeStore) this._obj).getNamespaceForPrefix(str);
        }
        if (i == 2) {
            Object obj = this._obj;
            if (Proxy.isProxyClass(obj.getClass())) {
                obj = Proxy.getInvocationHandler(obj);
            }
            if (obj instanceof TypeStoreUser) {
                return ((TypeStoreUser) obj).get_store().getNamespaceForPrefix(str);
            }
            XmlCursor newCursor = ((XmlObject) this._obj).newCursor();
            if (newCursor != null) {
                if (newCursor.currentTokenType() == XmlCursor.TokenType.ATTR) {
                    newCursor.toParent();
                }
                try {
                    return newCursor.namespaceForPrefix(str);
                } finally {
                    newCursor.dispose();
                }
            }
        } else if (i != 3) {
            if (i == 4) {
                return ((StartElement) this._obj).getNamespaceUri(str);
            }
            if (i != 5) {
                return null;
            }
            return ((PrefixResolver) this._obj).getNamespaceForPrefix(str);
        }
        return (String) ((Map) this._obj).get(str);
    }
}
