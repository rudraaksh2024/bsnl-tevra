package org.apache.poi.sl.usermodel;

import java.io.IOException;
import org.apache.poi.util.Internal;

@Internal
public interface MetroShapeProvider {
    Shape<?, ?> parseShape(byte[] bArr) throws IOException;
}
