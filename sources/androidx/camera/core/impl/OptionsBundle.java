package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OptionsBundle implements Config {
    private static final OptionsBundle EMPTY_BUNDLE;
    protected static final Comparator<Config.Option<?>> ID_COMPARE;
    protected final TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> mOptions;

    static {
        OptionsBundle$$ExternalSyntheticLambda0 optionsBundle$$ExternalSyntheticLambda0 = new OptionsBundle$$ExternalSyntheticLambda0();
        ID_COMPARE = optionsBundle$$ExternalSyntheticLambda0;
        EMPTY_BUNDLE = new OptionsBundle(new TreeMap(optionsBundle$$ExternalSyntheticLambda0));
    }

    OptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> treeMap) {
        this.mOptions = treeMap;
    }

    public static OptionsBundle from(Config config) {
        if (OptionsBundle.class.equals(config.getClass())) {
            return (OptionsBundle) config;
        }
        TreeMap treeMap = new TreeMap(ID_COMPARE);
        for (Config.Option next : config.listOptions()) {
            Set<Config.OptionPriority> priorities = config.getPriorities(next);
            ArrayMap arrayMap = new ArrayMap();
            for (Config.OptionPriority next2 : priorities) {
                arrayMap.put(next2, config.retrieveOptionWithPriority(next, next2));
            }
            treeMap.put(next, arrayMap);
        }
        return new OptionsBundle(treeMap);
    }

    public static OptionsBundle emptyBundle() {
        return EMPTY_BUNDLE;
    }

    public Set<Config.Option<?>> listOptions() {
        return Collections.unmodifiableSet(this.mOptions.keySet());
    }

    public boolean containsOption(Config.Option<?> option) {
        return this.mOptions.containsKey(option);
    }

    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> option) {
        Map map = this.mOptions.get(option);
        if (map != null) {
            return map.get((Config.OptionPriority) Collections.min(map.keySet()));
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> option, ValueT valuet) {
        try {
            return retrieveOption(option);
        } catch (IllegalArgumentException unused) {
            return valuet;
        }
    }

    public <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> option, Config.OptionPriority optionPriority) {
        Map map = this.mOptions.get(option);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + option);
        } else if (map.containsKey(optionPriority)) {
            return map.get(optionPriority);
        } else {
            throw new IllegalArgumentException("Option does not exist: " + option + " with priority=" + optionPriority);
        }
    }

    public Config.OptionPriority getOptionPriority(Config.Option<?> option) {
        Map map = this.mOptions.get(option);
        if (map != null) {
            return (Config.OptionPriority) Collections.min(map.keySet());
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findOptions(java.lang.String r3, androidx.camera.core.impl.Config.OptionMatcher r4) {
        /*
            r2 = this;
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            androidx.camera.core.impl.Config$Option r0 = androidx.camera.core.impl.Config.Option.create(r3, r0)
            java.util.TreeMap<androidx.camera.core.impl.Config$Option<?>, java.util.Map<androidx.camera.core.impl.Config$OptionPriority, java.lang.Object>> r2 = r2.mOptions
            java.util.SortedMap r2 = r2.tailMap(r0)
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0014:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x003d
            java.lang.Object r0 = r2.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            androidx.camera.core.impl.Config$Option r1 = (androidx.camera.core.impl.Config.Option) r1
            java.lang.String r1 = r1.getId()
            boolean r1 = r1.startsWith(r3)
            if (r1 != 0) goto L_0x0031
            goto L_0x003d
        L_0x0031:
            java.lang.Object r0 = r0.getKey()
            androidx.camera.core.impl.Config$Option r0 = (androidx.camera.core.impl.Config.Option) r0
            boolean r0 = r4.onOptionMatched(r0)
            if (r0 != 0) goto L_0x0014
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.OptionsBundle.findOptions(java.lang.String, androidx.camera.core.impl.Config$OptionMatcher):void");
    }

    public Set<Config.OptionPriority> getPriorities(Config.Option<?> option) {
        Map map = this.mOptions.get(option);
        if (map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }
}
