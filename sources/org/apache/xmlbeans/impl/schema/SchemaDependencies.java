package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SchemaDependencies {
    private final Map<String, List<String>> _contributions = new HashMap();
    private final Map<String, Set<String>> _dependencies = new HashMap();

    static /* synthetic */ Set lambda$registerDependency$0(String str) {
        return new HashSet();
    }

    /* access modifiers changed from: package-private */
    public void registerDependency(String str, String str2) {
        this._dependencies.computeIfAbsent(str2, new SchemaDependencies$$ExternalSyntheticLambda0()).add(str);
    }

    /* access modifiers changed from: package-private */
    public Set<String> computeTransitiveClosure(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        HashSet hashSet = new HashSet(list);
        for (int i = 0; i < arrayList.size(); i++) {
            Set<String> set = this._dependencies.get(arrayList.get(i));
            if (set != null) {
                for (String str : set) {
                    if (!hashSet.contains(str)) {
                        arrayList.add(str);
                        hashSet.add(str);
                    }
                }
            }
        }
        return hashSet;
    }

    SchemaDependencies() {
    }

    SchemaDependencies(SchemaDependencies schemaDependencies, Set<String> set) {
        for (String next : schemaDependencies._dependencies.keySet()) {
            if (!set.contains(next)) {
                HashSet hashSet = new HashSet();
                this._dependencies.put(next, hashSet);
                for (String str : schemaDependencies._dependencies.get(next)) {
                    if (!set.contains(str)) {
                        hashSet.add(str);
                    }
                }
            }
        }
        for (String next2 : schemaDependencies._contributions.keySet()) {
            if (!set.contains(next2)) {
                ArrayList arrayList = new ArrayList();
                this._contributions.put(next2, arrayList);
                arrayList.addAll(schemaDependencies._contributions.get(next2));
            }
        }
    }

    static /* synthetic */ List lambda$registerContribution$1(String str) {
        return new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public void registerContribution(String str, String str2) {
        this._contributions.computeIfAbsent(str, new SchemaDependencies$$ExternalSyntheticLambda3()).add(str2);
    }

    /* access modifiers changed from: package-private */
    public boolean isFileRepresented(String str) {
        return this._contributions.values().stream().anyMatch(new SchemaDependencies$$ExternalSyntheticLambda5(str));
    }

    /* access modifiers changed from: package-private */
    public List<String> getFilesTouched(Set<String> set) {
        Stream stream = set.stream();
        Map<String, List<String>> map = this._contributions;
        map.getClass();
        return (List) stream.map(new SchemaDependencies$$ExternalSyntheticLambda6(map)).filter(new SchemaDependencies$$ExternalSyntheticLambda7()).flatMap(new SchemaDependencies$$ExternalSyntheticLambda8()).collect(Collectors.toList());
    }

    /* access modifiers changed from: package-private */
    public List<String> getNamespacesTouched(Set<String> set) {
        return (List) this._contributions.entrySet().stream().filter(new SchemaDependencies$$ExternalSyntheticLambda1(set)).map(new SchemaDependencies$$ExternalSyntheticLambda2()).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$getNamespacesTouched$3(Set set, Map.Entry entry) {
        Stream stream = ((List) entry.getValue()).stream();
        set.getClass();
        return stream.anyMatch(new SchemaDependencies$$ExternalSyntheticLambda4(set));
    }
}
