package org.apache.logging.log4j.spi;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;
import org.apache.logging.log4j.util.Strings;

public class DefaultThreadContextStack implements ThreadContextStack, StringBuilderFormattable {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final ThreadLocal<MutableThreadContextStack> STACK = new ThreadLocal<>();
    private static final long serialVersionUID = 5050501;
    private final boolean useStack;

    public DefaultThreadContextStack(boolean z) {
        this.useStack = z;
    }

    private MutableThreadContextStack getNonNullStackCopy() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) (mutableThreadContextStack == null ? new MutableThreadContextStack() : mutableThreadContextStack.copy());
        MutableThreadContextStack mutableThreadContextStack3 = mutableThreadContextStack2;
        return mutableThreadContextStack2;
    }

    public boolean add(String str) {
        if (!this.useStack) {
            return false;
        }
        MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
        nonNullStackCopy.add(str);
        nonNullStackCopy.freeze();
        STACK.set(nonNullStackCopy);
        return true;
    }

    public boolean addAll(Collection<? extends String> collection) {
        if (!this.useStack || collection.isEmpty()) {
            return false;
        }
        MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
        nonNullStackCopy.addAll(collection);
        nonNullStackCopy.freeze();
        STACK.set(nonNullStackCopy);
        return true;
    }

    public List<String> asList() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList();
        }
        return mutableThreadContextStack.asList();
    }

    public void clear() {
        STACK.remove();
    }

    public boolean contains(Object obj) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack != null && mutableThreadContextStack.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return true;
        }
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null || !mutableThreadContextStack.containsAll(collection)) {
            return false;
        }
        return true;
    }

    public ThreadContextStack copy() {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || (mutableThreadContextStack = STACK.get()) == null) {
            return new MutableThreadContextStack();
        }
        return mutableThreadContextStack.copy();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (((obj instanceof DefaultThreadContextStack) && this.useStack != ((DefaultThreadContextStack) obj).useStack) || !(obj instanceof ThreadContextStack)) {
            return false;
        }
        ThreadContextStack threadContextStack = (ThreadContextStack) obj;
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return false;
        }
        return mutableThreadContextStack.equals(threadContextStack);
    }

    public int getDepth() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.getDepth();
    }

    public int hashCode() {
        int i;
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            i = 0;
        } else {
            i = mutableThreadContextStack.hashCode();
        }
        return 31 + i;
    }

    public boolean isEmpty() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack == null || mutableThreadContextStack.isEmpty();
    }

    public Iterator<String> iterator() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList().iterator();
        }
        return mutableThreadContextStack.iterator();
    }

    public String peek() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return (mutableThreadContextStack == null || mutableThreadContextStack.isEmpty()) ? "" : mutableThreadContextStack.peek();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r3 = STACK;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String pop() {
        /*
            r3 = this;
            boolean r3 = r3.useStack
            java.lang.String r0 = ""
            if (r3 != 0) goto L_0x0007
            return r0
        L_0x0007:
            java.lang.ThreadLocal<org.apache.logging.log4j.spi.MutableThreadContextStack> r3 = STACK
            java.lang.Object r1 = r3.get()
            org.apache.logging.log4j.spi.MutableThreadContextStack r1 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r1
            if (r1 == 0) goto L_0x0029
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0018
            goto L_0x0029
        L_0x0018:
            org.apache.logging.log4j.spi.ThreadContextStack r0 = r1.copy()
            org.apache.logging.log4j.spi.MutableThreadContextStack r0 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r0
            java.lang.String r1 = r0.pop()
            r0.freeze()
            r3.set(r0)
            return r1
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.spi.DefaultThreadContextStack.pop():java.lang.String");
    }

    public void push(String str) {
        if (this.useStack) {
            add(str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r3 = STACK;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean remove(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r3 = r3.useStack
            r0 = 0
            if (r3 != 0) goto L_0x0006
            return r0
        L_0x0006:
            java.lang.ThreadLocal<org.apache.logging.log4j.spi.MutableThreadContextStack> r3 = STACK
            java.lang.Object r1 = r3.get()
            org.apache.logging.log4j.spi.MutableThreadContextStack r1 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r1
            if (r1 == 0) goto L_0x0028
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0017
            goto L_0x0028
        L_0x0017:
            org.apache.logging.log4j.spi.ThreadContextStack r0 = r1.copy()
            org.apache.logging.log4j.spi.MutableThreadContextStack r0 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r0
            boolean r4 = r0.remove(r4)
            r0.freeze()
            r3.set(r0)
            return r4
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.spi.DefaultThreadContextStack.remove(java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r3 = STACK;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean removeAll(java.util.Collection<?> r4) {
        /*
            r3 = this;
            boolean r3 = r3.useStack
            r0 = 0
            if (r3 == 0) goto L_0x002e
            boolean r3 = r4.isEmpty()
            if (r3 == 0) goto L_0x000c
            goto L_0x002e
        L_0x000c:
            java.lang.ThreadLocal<org.apache.logging.log4j.spi.MutableThreadContextStack> r3 = STACK
            java.lang.Object r1 = r3.get()
            org.apache.logging.log4j.spi.MutableThreadContextStack r1 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r1
            if (r1 == 0) goto L_0x002e
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x001d
            goto L_0x002e
        L_0x001d:
            org.apache.logging.log4j.spi.ThreadContextStack r0 = r1.copy()
            org.apache.logging.log4j.spi.MutableThreadContextStack r0 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r0
            boolean r4 = r0.removeAll(r4)
            r0.freeze()
            r3.set(r0)
            return r4
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.spi.DefaultThreadContextStack.removeAll(java.util.Collection):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r3 = STACK;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean retainAll(java.util.Collection<?> r4) {
        /*
            r3 = this;
            boolean r3 = r3.useStack
            r0 = 0
            if (r3 == 0) goto L_0x002e
            boolean r3 = r4.isEmpty()
            if (r3 == 0) goto L_0x000c
            goto L_0x002e
        L_0x000c:
            java.lang.ThreadLocal<org.apache.logging.log4j.spi.MutableThreadContextStack> r3 = STACK
            java.lang.Object r1 = r3.get()
            org.apache.logging.log4j.spi.MutableThreadContextStack r1 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r1
            if (r1 == 0) goto L_0x002e
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x001d
            goto L_0x002e
        L_0x001d:
            org.apache.logging.log4j.spi.ThreadContextStack r0 = r1.copy()
            org.apache.logging.log4j.spi.MutableThreadContextStack r0 = (org.apache.logging.log4j.spi.MutableThreadContextStack) r0
            boolean r4 = r0.retainAll(r4)
            r0.freeze()
            r3.set(r0)
            return r4
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.spi.DefaultThreadContextStack.retainAll(java.util.Collection):boolean");
    }

    public int size() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.size();
    }

    public Object[] toArray() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return Strings.EMPTY_ARRAY;
        }
        return mutableThreadContextStack.toArray(EMPTY_OBJECT_ARRAY);
    }

    public <T> T[] toArray(T[] tArr) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack != null) {
            return mutableThreadContextStack.toArray(tArr);
        }
        if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    public String toString() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return "[]";
        }
        return mutableThreadContextStack.toString();
    }

    public void formatTo(StringBuilder sb) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            sb.append("[]");
        } else {
            StringBuilders.appendValue(sb, mutableThreadContextStack);
        }
    }

    public void trim(int i) {
        if (i >= 0) {
            ThreadLocal<MutableThreadContextStack> threadLocal = STACK;
            MutableThreadContextStack mutableThreadContextStack = threadLocal.get();
            if (mutableThreadContextStack != null) {
                MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.copy();
                mutableThreadContextStack2.trim(i);
                mutableThreadContextStack2.freeze();
                threadLocal.set(mutableThreadContextStack2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Maximum stack depth cannot be negative");
    }

    public ThreadContext.ContextStack getImmutableStackOrNull() {
        return STACK.get();
    }
}
