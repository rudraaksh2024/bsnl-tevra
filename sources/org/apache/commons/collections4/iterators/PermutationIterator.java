package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PermutationIterator<E> implements Iterator<List<E>> {
    private final boolean[] direction;
    private final int[] keys;
    private List<E> nextPermutation;
    private final Map<Integer, E> objectMap;

    public PermutationIterator(Collection<? extends E> collection) {
        if (collection != null) {
            this.keys = new int[collection.size()];
            boolean[] zArr = new boolean[collection.size()];
            this.direction = zArr;
            Arrays.fill(zArr, false);
            this.objectMap = new HashMap();
            int i = 1;
            for (Object put : collection) {
                this.objectMap.put(Integer.valueOf(i), put);
                this.keys[i - 1] = i;
                i++;
            }
            this.nextPermutation = new ArrayList(collection);
            return;
        }
        throw new NullPointerException("The collection must not be null");
    }

    public boolean hasNext() {
        return this.nextPermutation != null;
    }

    public List<E> next() {
        int[] iArr;
        int i;
        if (hasNext()) {
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            int i5 = -1;
            int i6 = -1;
            while (true) {
                iArr = this.keys;
                if (i4 >= iArr.length) {
                    break;
                }
                boolean z = this.direction[i4];
                if (((z && i4 < iArr.length - 1 && iArr[i4] > iArr[i4 + 1]) || (!z && i4 > 0 && iArr[i4] > iArr[i4 - 1])) && (i = iArr[i4]) > i5) {
                    i6 = i4;
                    i5 = i;
                }
                i4++;
            }
            if (i5 == -1) {
                List<E> list = this.nextPermutation;
                this.nextPermutation = null;
                return list;
            }
            boolean[] zArr = this.direction;
            boolean z2 = zArr[i6];
            if (z2) {
                i3 = 1;
            }
            int i7 = iArr[i6];
            int i8 = i3 + i6;
            iArr[i6] = iArr[i8];
            iArr[i8] = i7;
            zArr[i6] = zArr[i8];
            zArr[i8] = z2;
            ArrayList arrayList = new ArrayList();
            while (true) {
                int[] iArr2 = this.keys;
                if (i2 < iArr2.length) {
                    int i9 = iArr2[i2];
                    if (i9 > i5) {
                        boolean[] zArr2 = this.direction;
                        zArr2[i2] = !zArr2[i2];
                    }
                    arrayList.add(this.objectMap.get(Integer.valueOf(i9)));
                    i2++;
                } else {
                    List<E> list2 = this.nextPermutation;
                    this.nextPermutation = arrayList;
                    return list2;
                }
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
