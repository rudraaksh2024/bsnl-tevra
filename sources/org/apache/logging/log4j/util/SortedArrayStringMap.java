package org.apache.logging.log4j.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.status.StatusLogger;

public class SortedArrayStringMap implements IndexedStringMap {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    private static final String[] EMPTY = Strings.EMPTY_ARRAY;
    private static final String FROZEN = "Frozen collection cannot be modified";
    private static final int HASHVAL = 31;
    private static final TriConsumer<String, Object, StringMap> PUT_ALL = new SortedArrayStringMap$$ExternalSyntheticLambda0();
    private static final Method getObjectInputFilter;
    private static final Method newObjectInputFilter;
    private static final long serialVersionUID = -5748905872274478116L;
    private static final Method setObjectInputFilter;
    private boolean immutable;
    private transient boolean iterating;
    private transient String[] keys;
    private transient int size;
    private int threshold;
    private transient Object[] values;

    static {
        Method method = null;
        int i = 0;
        Method method2 = null;
        Method method3 = null;
        for (Method method4 : ObjectInputStream.class.getMethods()) {
            if (method4.getName().equals("setObjectInputFilter")) {
                method2 = method4;
            } else if (method4.getName().equals("getObjectInputFilter")) {
                method3 = method4;
            }
        }
        if (method2 != null) {
            try {
                Method[] methods = Class.forName("org.apache.logging.log4j.util.internal.DefaultObjectInputFilter").getMethods();
                int length = methods.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method5 = methods[i];
                    if (method5.getName().equals("newInstance") && Modifier.isStatic(method5.getModifiers())) {
                        method = method5;
                        break;
                    }
                    i++;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        newObjectInputFilter = method;
        setObjectInputFilter = method2;
        getObjectInputFilter = method3;
    }

    public SortedArrayStringMap() {
        this(4);
    }

    public SortedArrayStringMap(int i) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        if (i >= 0) {
            this.threshold = ceilingNextPowerOfTwo(i == 0 ? 1 : i);
            return;
        }
        throw new IllegalArgumentException("Initial capacity must be at least zero but was " + i);
    }

    public SortedArrayStringMap(ReadOnlyStringMap readOnlyStringMap) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        if (readOnlyStringMap instanceof SortedArrayStringMap) {
            initFrom0((SortedArrayStringMap) readOnlyStringMap);
        } else if (readOnlyStringMap != null) {
            resize(ceilingNextPowerOfTwo(readOnlyStringMap.size()));
            readOnlyStringMap.forEach(PUT_ALL, this);
        }
    }

    public SortedArrayStringMap(Map<String, ?> map) {
        String[] strArr = EMPTY;
        this.keys = strArr;
        this.values = strArr;
        resize(ceilingNextPowerOfTwo(map.size()));
        for (Map.Entry next : map.entrySet()) {
            putValue(Objects.toString(next.getKey(), (String) null), next.getValue());
        }
    }

    private void assertNotFrozen() {
        if (this.immutable) {
            throw new UnsupportedOperationException(FROZEN);
        }
    }

    private void assertNoConcurrentModification() {
        if (this.iterating) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        if (this.keys != EMPTY) {
            assertNotFrozen();
            assertNoConcurrentModification();
            Arrays.fill(this.keys, 0, this.size, (Object) null);
            Arrays.fill(this.values, 0, this.size, (Object) null);
            this.size = 0;
        }
    }

    public boolean containsKey(String str) {
        return indexOfKey(str) >= 0;
    }

    public Map<String, String> toMap() {
        HashMap hashMap = new HashMap(size());
        for (int i = 0; i < size(); i++) {
            Object valueAt = getValueAt(i);
            hashMap.put(getKeyAt(i), valueAt == null ? null : String.valueOf(valueAt));
        }
        return hashMap;
    }

    public void freeze() {
        this.immutable = true;
    }

    public boolean isFrozen() {
        return this.immutable;
    }

    public <V> V getValue(String str) {
        int indexOfKey = indexOfKey(str);
        if (indexOfKey < 0) {
            return null;
        }
        return this.values[indexOfKey];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int indexOfKey(String str) {
        String[] strArr = this.keys;
        if (strArr == EMPTY) {
            return -1;
        }
        if (str == null) {
            return nullKeyIndex();
        }
        int i = this.size;
        int i2 = 0;
        if (i > 0 && strArr[0] == null) {
            i2 = 1;
        }
        return Arrays.binarySearch(strArr, i2, i, str);
    }

    private int nullKeyIndex() {
        return (this.size <= 0 || this.keys[0] != null) ? -1 : 0;
    }

    public void putValue(String str, Object obj) {
        assertNotFrozen();
        assertNoConcurrentModification();
        if (this.keys == EMPTY) {
            inflateTable(this.threshold);
        }
        int indexOfKey = indexOfKey(str);
        if (indexOfKey >= 0) {
            this.keys[indexOfKey] = str;
            this.values[indexOfKey] = obj;
            return;
        }
        insertAt(~indexOfKey, str, obj);
    }

    private void insertAt(int i, String str, Object obj) {
        ensureCapacity();
        String[] strArr = this.keys;
        int i2 = i + 1;
        System.arraycopy(strArr, i, strArr, i2, this.size - i);
        Object[] objArr = this.values;
        System.arraycopy(objArr, i, objArr, i2, this.size - i);
        this.keys[i] = str;
        this.values[i] = obj;
        this.size++;
    }

    public void putAll(ReadOnlyStringMap readOnlyStringMap) {
        if (readOnlyStringMap != this && readOnlyStringMap != null && !readOnlyStringMap.isEmpty()) {
            assertNotFrozen();
            assertNoConcurrentModification();
            if (readOnlyStringMap instanceof SortedArrayStringMap) {
                if (this.size == 0) {
                    initFrom0((SortedArrayStringMap) readOnlyStringMap);
                } else {
                    merge((SortedArrayStringMap) readOnlyStringMap);
                }
            } else if (readOnlyStringMap != null) {
                readOnlyStringMap.forEach(PUT_ALL, this);
            }
        }
    }

    private void initFrom0(SortedArrayStringMap sortedArrayStringMap) {
        int length = this.keys.length;
        int i = sortedArrayStringMap.size;
        if (length < i) {
            int i2 = sortedArrayStringMap.threshold;
            this.keys = new String[i2];
            this.values = new Object[i2];
        }
        System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, 0, i);
        System.arraycopy(sortedArrayStringMap.values, 0, this.values, 0, sortedArrayStringMap.size);
        this.size = sortedArrayStringMap.size;
        this.threshold = sortedArrayStringMap.threshold;
    }

    private void merge(SortedArrayStringMap sortedArrayStringMap) {
        String[] strArr = this.keys;
        Object[] objArr = this.values;
        int i = sortedArrayStringMap.size + this.size;
        int ceilingNextPowerOfTwo = ceilingNextPowerOfTwo(i);
        this.threshold = ceilingNextPowerOfTwo;
        if (this.keys.length < ceilingNextPowerOfTwo) {
            this.keys = new String[ceilingNextPowerOfTwo];
            this.values = new Object[ceilingNextPowerOfTwo];
        }
        boolean z = false;
        if (sortedArrayStringMap.size() > size()) {
            System.arraycopy(strArr, 0, this.keys, sortedArrayStringMap.size, this.size);
            System.arraycopy(objArr, 0, this.values, sortedArrayStringMap.size, this.size);
            System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, 0, sortedArrayStringMap.size);
            System.arraycopy(sortedArrayStringMap.values, 0, this.values, 0, sortedArrayStringMap.size);
            this.size = sortedArrayStringMap.size;
        } else {
            System.arraycopy(strArr, 0, this.keys, 0, this.size);
            System.arraycopy(objArr, 0, this.values, 0, this.size);
            System.arraycopy(sortedArrayStringMap.keys, 0, this.keys, this.size, sortedArrayStringMap.size);
            System.arraycopy(sortedArrayStringMap.values, 0, this.values, this.size, sortedArrayStringMap.size);
            z = true;
        }
        for (int i2 = this.size; i2 < i; i2++) {
            int indexOfKey = indexOfKey(this.keys[i2]);
            if (indexOfKey < 0) {
                insertAt(~indexOfKey, this.keys[i2], this.values[i2]);
            } else if (z) {
                String[] strArr2 = this.keys;
                strArr2[indexOfKey] = strArr2[i2];
                Object[] objArr2 = this.values;
                objArr2[indexOfKey] = objArr2[i2];
            }
        }
        Arrays.fill(this.keys, this.size, i, (Object) null);
        Arrays.fill(this.values, this.size, i, (Object) null);
    }

    private void ensureCapacity() {
        int i = this.size;
        int i2 = this.threshold;
        if (i >= i2) {
            resize(i2 * 2);
        }
    }

    private void resize(int i) {
        String[] strArr = this.keys;
        Object[] objArr = this.values;
        String[] strArr2 = new String[i];
        this.keys = strArr2;
        this.values = new Object[i];
        System.arraycopy(strArr, 0, strArr2, 0, this.size);
        System.arraycopy(objArr, 0, this.values, 0, this.size);
        this.threshold = i;
    }

    private void inflateTable(int i) {
        this.threshold = i;
        this.keys = new String[i];
        this.values = new Object[i];
    }

    public void remove(String str) {
        int indexOfKey;
        if (this.keys != EMPTY && (indexOfKey = indexOfKey(str)) >= 0) {
            assertNotFrozen();
            assertNoConcurrentModification();
            String[] strArr = this.keys;
            int i = indexOfKey + 1;
            System.arraycopy(strArr, i, strArr, indexOfKey, (this.size - 1) - indexOfKey);
            Object[] objArr = this.values;
            System.arraycopy(objArr, i, objArr, indexOfKey, (this.size - 1) - indexOfKey);
            String[] strArr2 = this.keys;
            int i2 = this.size;
            strArr2[i2 - 1] = null;
            this.values[i2 - 1] = null;
            this.size = i2 - 1;
        }
    }

    public String getKeyAt(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return this.keys[i];
    }

    public <V> V getValueAt(int i) {
        if (i < 0 || i >= this.size) {
            return null;
        }
        return this.values[i];
    }

    public int size() {
        return this.size;
    }

    public <V> void forEach(BiConsumer<String, ? super V> biConsumer) {
        this.iterating = true;
        int i = 0;
        while (i < this.size) {
            try {
                biConsumer.accept(this.keys[i], this.values[i]);
                i++;
            } finally {
                this.iterating = false;
            }
        }
    }

    public <V, T> void forEach(TriConsumer<String, ? super V, T> triConsumer, T t) {
        this.iterating = true;
        int i = 0;
        while (i < this.size) {
            try {
                triConsumer.accept(this.keys[i], this.values[i], t);
                i++;
            } finally {
                this.iterating = false;
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SortedArrayStringMap)) {
            return false;
        }
        SortedArrayStringMap sortedArrayStringMap = (SortedArrayStringMap) obj;
        if (size() != sortedArrayStringMap.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(this.keys[i], sortedArrayStringMap.keys[i]) || !Objects.equals(this.values[i], sortedArrayStringMap.values[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.size;
        return ((((1147 + i) * 31) + hashCode(this.keys, i)) * 31) + hashCode(this.values, this.size);
    }

    private static int hashCode(Object[] objArr, int i) {
        int i2 = 1;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i2 * 31;
            Object obj = objArr[i3];
            i2 = i4 + (obj == null ? 0 : obj.hashCode());
        }
        return i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append('{');
        for (int i = 0; i < this.size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.keys[i]).append(Chars.EQ);
            Object obj = this.values[i];
            if (obj == this) {
                obj = "(this map)";
            }
            sb.append(obj);
        }
        sb.append('}');
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        String[] strArr = this.keys;
        if (strArr == EMPTY) {
            objectOutputStream.writeInt(ceilingNextPowerOfTwo(this.threshold));
        } else {
            objectOutputStream.writeInt(strArr.length);
        }
        objectOutputStream.writeInt(this.size);
        if (this.size > 0) {
            for (int i = 0; i < this.size; i++) {
                objectOutputStream.writeObject(this.keys[i]);
                try {
                    objectOutputStream.writeObject(marshall(this.values[i]));
                } catch (Exception e) {
                    handleSerializationException(e, i, this.keys[i]);
                    objectOutputStream.writeObject((Object) null);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] marshall(java.lang.Object r2) throws java.io.IOException {
        /*
            if (r2 != 0) goto L_0x0004
            r2 = 0
            return r2
        L_0x0004:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream
            r1.<init>(r0)
            r1.writeObject(r2)     // Catch:{ all -> 0x001c }
            r1.flush()     // Catch:{ all -> 0x001c }
            byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x001c }
            r1.close()
            return r2
        L_0x001c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001e }
        L_0x001e:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0027:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.SortedArrayStringMap.marshall(java.lang.Object):byte[]");
    }

    private static Object unmarshall(byte[] bArr, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        if (objectInputStream instanceof FilteredObjectInputStream) {
            objectInputStream2 = new FilteredObjectInputStream(byteArrayInputStream, ((FilteredObjectInputStream) objectInputStream).getAllowedClasses());
        } else {
            try {
                Object invoke = getObjectInputFilter.invoke(objectInputStream, new Object[0]);
                Object invoke2 = newObjectInputFilter.invoke((Object) null, new Object[]{invoke});
                objectInputStream2 = new ObjectInputStream(byteArrayInputStream);
                setObjectInputFilter.invoke(objectInputStream2, new Object[]{invoke2});
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new StreamCorruptedException("Unable to set ObjectInputFilter on stream");
            }
        }
        try {
            return objectInputStream2.readObject();
        } finally {
            objectInputStream2.close();
        }
    }

    private static int ceilingNextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if ((objectInputStream instanceof FilteredObjectInputStream) || setObjectInputFilter != null) {
            objectInputStream.defaultReadObject();
            String[] strArr = EMPTY;
            this.keys = strArr;
            this.values = strArr;
            int readInt = objectInputStream.readInt();
            if (readInt >= 0) {
                int readInt2 = objectInputStream.readInt();
                if (readInt2 >= 0) {
                    if (readInt2 > 0) {
                        inflateTable(readInt);
                    } else {
                        this.threshold = readInt;
                    }
                    for (int i = 0; i < readInt2; i++) {
                        this.keys[i] = (String) objectInputStream.readObject();
                        try {
                            byte[] bArr = (byte[]) objectInputStream.readObject();
                            this.values[i] = bArr == null ? null : unmarshall(bArr, objectInputStream);
                        } catch (Exception | LinkageError e) {
                            handleSerializationException(e, i, this.keys[i]);
                            this.values[i] = null;
                        }
                    }
                    this.size = readInt2;
                    return;
                }
                throw new InvalidObjectException("Illegal mappings count: " + readInt2);
            }
            throw new InvalidObjectException("Illegal capacity: " + readInt);
        }
        throw new IllegalArgumentException("readObject requires a FilteredObjectInputStream or an ObjectInputStream that accepts an ObjectInputFilter");
    }

    private void handleSerializationException(Throwable th, int i, String str) {
        StatusLogger.getLogger().warn("Ignoring {} for key[{}] ('{}')", (Object) String.valueOf(th), (Object) Integer.valueOf(i), (Object) this.keys[i]);
    }
}
