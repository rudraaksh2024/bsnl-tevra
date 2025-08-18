package org.apache.poi.ss.formula.udf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.poi.ss.formula.atp.AnalysisToolPak;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

public class AggregatingUDFFinder implements UDFFinder {
    public static final UDFFinder DEFAULT = new AggregatingUDFFinder(AnalysisToolPak.instance);
    private final Collection<UDFFinder> _usedToolPacks;

    public AggregatingUDFFinder(UDFFinder... uDFFinderArr) {
        ArrayList arrayList = new ArrayList(uDFFinderArr.length);
        this._usedToolPacks = arrayList;
        arrayList.addAll(Arrays.asList(uDFFinderArr));
    }

    public FreeRefFunction findFunction(String str) {
        for (UDFFinder findFunction : this._usedToolPacks) {
            FreeRefFunction findFunction2 = findFunction.findFunction(str);
            if (findFunction2 != null) {
                return findFunction2;
            }
        }
        return null;
    }

    public void add(UDFFinder uDFFinder) {
        this._usedToolPacks.add(uDFFinder);
    }
}
