package org.apache.commons.io.input;

import java.io.Reader;
import java.util.function.IntPredicate;

public class CharacterFilterReader extends AbstractCharacterFilterReader {
    static /* synthetic */ boolean lambda$new$0(int i, int i2) {
        return i2 == i;
    }

    public CharacterFilterReader(Reader reader, int i) {
        super(reader, new CharacterFilterReader$$ExternalSyntheticLambda0(i));
    }

    public CharacterFilterReader(Reader reader, IntPredicate intPredicate) {
        super(reader, intPredicate);
    }
}
