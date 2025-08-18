package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.OutputStream;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;

public final class DefaultMarshaller implements PartMarshaller {
    public boolean marshall(PackagePart packagePart, OutputStream outputStream) throws OpenXML4JException {
        return packagePart.save(outputStream);
    }
}
