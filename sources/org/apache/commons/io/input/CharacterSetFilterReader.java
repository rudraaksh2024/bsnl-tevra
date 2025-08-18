package org.apache.commons.io.input;

import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntPredicate;

public class CharacterSetFilterReader extends AbstractCharacterFilterReader {
    private static IntPredicate toIntPredicate(Set<Integer> set) {
        if (set == null) {
            return SKIP_NONE;
        }
        return new CharacterSetFilterReader$$ExternalSyntheticLambda0(Collections.unmodifiableSet(set));
    }

    public CharacterSetFilterReader(Reader reader, Integer... numArr) {
        this(reader, (Set<Integer>) new HashSet(Arrays.asList(numArr)));
    }

    public CharacterSetFilterReader(Reader reader, Set<Integer> set) {
        super(reader, toIntPredicate(set));
    }
}
