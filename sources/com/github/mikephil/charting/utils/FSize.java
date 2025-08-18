package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;

public final class FSize extends ObjectPool.Poolable {
    private static ObjectPool<FSize> pool;
    public float height;
    public float width;

    static {
        ObjectPool<FSize> create = ObjectPool.create(256, new FSize(0.0f, 0.0f));
        pool = create;
        create.setReplenishPercentage(0.5f);
    }

    /* access modifiers changed from: protected */
    public ObjectPool.Poolable instantiate() {
        return new FSize(0.0f, 0.0f);
    }

    public static FSize getInstance(float f, float f2) {
        FSize fSize = pool.get();
        fSize.width = f;
        fSize.height = f2;
        return fSize;
    }

    public static void recycleInstance(FSize fSize) {
        pool.recycle(fSize);
    }

    public static void recycleInstances(List<FSize> list) {
        pool.recycle(list);
    }

    public FSize() {
    }

    public FSize(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FSize)) {
            return false;
        }
        FSize fSize = (FSize) obj;
        if (this.width == fSize.width && this.height == fSize.height) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.height) ^ Float.floatToIntBits(this.width);
    }
}
