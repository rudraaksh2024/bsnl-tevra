package org.apache.poi.ss.formula.eval;

import java.util.ArrayList;
import java.util.List;

public class RefListEval implements ValueEval {
    private final List<ValueEval> list = new ArrayList();

    public RefListEval(ValueEval valueEval, ValueEval valueEval2) {
        add(valueEval);
        add(valueEval2);
    }

    private void add(ValueEval valueEval) {
        if (valueEval instanceof RefListEval) {
            this.list.addAll(((RefListEval) valueEval).list);
        } else {
            this.list.add(valueEval);
        }
    }

    public List<ValueEval> getList() {
        return this.list;
    }
}
