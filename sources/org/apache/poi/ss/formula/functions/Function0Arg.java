package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version = "6.0.0")
public interface Function0Arg extends Function {
    ValueEval evaluate(int i, int i2);
}
