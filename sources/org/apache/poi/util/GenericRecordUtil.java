package org.apache.poi.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Internal
public final class GenericRecordUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private GenericRecordUtil() {
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier) {
        return Collections.singletonMap(str, supplier);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2) {
        return getGenericProperties(str, supplier, str2, supplier2, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, str4, supplier4, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4, String str5, Supplier<?> supplier5) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, str4, supplier4, str5, supplier5, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4, String str5, Supplier<?> supplier5, String str6, Supplier<?> supplier6) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, str4, supplier4, str5, supplier5, str6, supplier6, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4, String str5, Supplier<?> supplier5, String str6, Supplier<?> supplier6, String str7, Supplier<?> supplier7) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, str4, supplier4, str5, supplier5, str6, supplier6, str7, supplier7, (String) null, (Supplier<?>) null, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4, String str5, Supplier<?> supplier5, String str6, Supplier<?> supplier6, String str7, Supplier<?> supplier7, String str8, Supplier<?> supplier8) {
        return getGenericProperties(str, supplier, str2, supplier2, str3, supplier3, str4, supplier4, str5, supplier5, str6, supplier6, str7, supplier7, str8, supplier8, (String) null, (Supplier<?>) null);
    }

    public static Map<String, Supplier<?>> getGenericProperties(String str, Supplier<?> supplier, String str2, Supplier<?> supplier2, String str3, Supplier<?> supplier3, String str4, Supplier<?> supplier4, String str5, Supplier<?> supplier5, String str6, Supplier<?> supplier6, String str7, Supplier<?> supplier7, String str8, Supplier<?> supplier8, String str9, Supplier<?> supplier9) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] strArr = {str, str2, str3, str4, str5, str6, str7, str8, str9};
        Supplier[] supplierArr = {supplier, supplier2, supplier3, supplier4, supplier5, supplier6, supplier7, supplier8, supplier9};
        for (int i = 0; i < 9; i++) {
            String str10 = strArr[i];
            if (str10 == null) {
                break;
            }
            if ("base".equals(str10)) {
                linkedHashMap.putAll((Map) supplierArr[i].get());
            } else {
                linkedHashMap.put(strArr[i], supplierArr[i]);
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public static <T extends Enum<?>> Supplier<T> safeEnum(T[] tArr, Supplier<Number> supplier) {
        return safeEnum(tArr, supplier, (Enum) null);
    }

    public static <T extends Enum<?>> Supplier<T> safeEnum(T[] tArr, Supplier<Number> supplier, T t) {
        return new GenericRecordUtil$$ExternalSyntheticLambda2(supplier.get().intValue(), tArr, t);
    }

    static /* synthetic */ Enum lambda$safeEnum$0(int i, Enum[] enumArr, Enum enumR) {
        return (i < 0 || i >= enumArr.length) ? enumR : enumArr[i];
    }

    public static Supplier<AnnotatedFlag> getBitsAsString(Supplier<Number> supplier, BitField[] bitFieldArr, String[] strArr) {
        return new GenericRecordUtil$$ExternalSyntheticLambda1(supplier, Arrays.stream(bitFieldArr).mapToInt(new GenericRecordUtil$$ExternalSyntheticLambda0()).toArray(), strArr);
    }

    static /* synthetic */ AnnotatedFlag lambda$getBitsAsString$1(Supplier supplier, int[] iArr, String[] strArr) {
        return new AnnotatedFlag(supplier, iArr, strArr, false);
    }

    public static Supplier<AnnotatedFlag> getBitsAsString(Supplier<Number> supplier, int[] iArr, String[] strArr) {
        return new GenericRecordUtil$$ExternalSyntheticLambda4(supplier, iArr, strArr);
    }

    static /* synthetic */ AnnotatedFlag lambda$getBitsAsString$2(Supplier supplier, int[] iArr, String[] strArr) {
        return new AnnotatedFlag(supplier, iArr, strArr, false);
    }

    public static Supplier<AnnotatedFlag> getEnumBitsAsString(Supplier<Number> supplier, int[] iArr, String[] strArr) {
        return new GenericRecordUtil$$ExternalSyntheticLambda3(supplier, iArr, strArr);
    }

    static /* synthetic */ AnnotatedFlag lambda$getEnumBitsAsString$3(Supplier supplier, int[] iArr, String[] strArr) {
        return new AnnotatedFlag(supplier, iArr, strArr, true);
    }

    public static class AnnotatedFlag {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean exactMatch;
        private final Map<Integer, String> masks = new LinkedHashMap();
        private final Supplier<Number> value;

        static {
            Class<GenericRecordUtil> cls = GenericRecordUtil.class;
        }

        AnnotatedFlag(Supplier<Number> supplier, int[] iArr, String[] strArr, boolean z) {
            this.value = supplier;
            this.exactMatch = z;
            for (int i = 0; i < iArr.length; i++) {
                this.masks.put(Integer.valueOf(iArr[i]), strArr[i]);
            }
        }

        public Supplier<Number> getValue() {
            return this.value;
        }

        public String getDescription() {
            return (String) this.masks.entrySet().stream().filter(new GenericRecordUtil$AnnotatedFlag$$ExternalSyntheticLambda0(this, this.value.get().intValue())).map(new GenericRecordUtil$AnnotatedFlag$$ExternalSyntheticLambda1()).collect(Collectors.joining(" | "));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getDescription$0$org-apache-poi-util-GenericRecordUtil$AnnotatedFlag  reason: not valid java name */
        public /* synthetic */ boolean m2290lambda$getDescription$0$orgapachepoiutilGenericRecordUtil$AnnotatedFlag(int i, Map.Entry entry) {
            return match(i, ((Integer) entry.getKey()).intValue());
        }

        private boolean match(int i, int i2) {
            if (this.exactMatch) {
                if (i == i2) {
                    return true;
                }
            } else if ((i & i2) == i2) {
                return true;
            }
            return false;
        }
    }
}
