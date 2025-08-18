package org.apache.poi.util;

import java.util.Iterator;

@Internal
public class CodepointsUtil {
    public static Iterator<String> iteratorFor(String str) {
        return str.codePoints().mapToObj(new CodepointsUtil$$ExternalSyntheticLambda0()).iterator();
    }
}
