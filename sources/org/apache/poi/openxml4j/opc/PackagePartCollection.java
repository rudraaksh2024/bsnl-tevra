package org.apache.poi.openxml4j.opc;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

public final class PackagePartCollection implements Serializable {
    private static final long serialVersionUID = 2515031135957635517L;
    private final TreeMap<String, PackagePart> packagePartLookup = new TreeMap<>(new PackagePartCollection$$ExternalSyntheticLambda0());
    private final Set<String> registerPartNameStr = new HashSet();

    /* renamed from: $r8$lambda$QVaw9wiS_WvatqGjDAtHqqR-h-4  reason: not valid java name */
    public static /* synthetic */ SparseBitSet m2211$r8$lambda$QVaw9wiS_WvatqGjDAtHqqRh4() {
        return new SparseBitSet();
    }

    public PackagePart put(PackagePartName packagePartName, PackagePart packagePart) {
        String name = packagePartName.getName();
        StringBuilder sb = new StringBuilder();
        String[] split = name.split("(?=[/])");
        int length = split.length;
        int i = 0;
        while (i < length) {
            sb.append(split[i]);
            if (!this.registerPartNameStr.contains(sb.toString())) {
                i++;
            } else {
                throw new InvalidOperationException("You can't add a part with a part name derived from another part ! [M1.11]");
            }
        }
        this.registerPartNameStr.add(name);
        return this.packagePartLookup.put(name, packagePart);
    }

    public PackagePart remove(PackagePartName packagePartName) {
        if (packagePartName == null) {
            return null;
        }
        String name = packagePartName.getName();
        PackagePart remove = this.packagePartLookup.remove(name);
        if (remove != null) {
            this.registerPartNameStr.remove(name);
        }
        return remove;
    }

    public Collection<PackagePart> sortedValues() {
        return Collections.unmodifiableCollection(this.packagePartLookup.values());
    }

    public boolean containsKey(PackagePartName packagePartName) {
        return packagePartName != null && this.packagePartLookup.containsKey(packagePartName.getName());
    }

    public PackagePart get(PackagePartName packagePartName) {
        if (packagePartName == null) {
            return null;
        }
        return this.packagePartLookup.get(packagePartName.getName());
    }

    public int size() {
        return this.packagePartLookup.size();
    }

    public int getUnusedPartIndex(String str) throws InvalidFormatException {
        if (str == null || !str.contains("#")) {
            throw new InvalidFormatException("name template must not be null and contain an index char (#)");
        }
        return ((SparseBitSet) this.packagePartLookup.keySet().stream().mapToInt(new PackagePartCollection$$ExternalSyntheticLambda1(Pattern.compile(str.replace("#", "([0-9]+)")))).collect(new PackagePartCollection$$ExternalSyntheticLambda2(), new PackagePartCollection$$ExternalSyntheticLambda3(), new PackagePartCollection$$ExternalSyntheticLambda4())).nextClearBit(1);
    }

    static /* synthetic */ int lambda$getUnusedPartIndex$0(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
}
