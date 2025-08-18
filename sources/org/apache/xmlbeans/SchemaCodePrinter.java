package org.apache.xmlbeans;

import java.io.IOException;
import java.io.Writer;
import org.apache.xmlbeans.impl.repackage.Repackager;

public interface SchemaCodePrinter {
    void printHolder(Writer writer, SchemaTypeSystem schemaTypeSystem, XmlOptions xmlOptions, Repackager repackager) throws IOException;

    @Deprecated
    void printTypeImpl(Writer writer, SchemaType schemaType) throws IOException {
        printTypeImpl(writer, schemaType, (XmlOptions) null);
    }

    void printTypeImpl(Writer writer, SchemaType schemaType, XmlOptions xmlOptions) throws IOException {
        printTypeImpl(writer, schemaType);
    }

    @Deprecated
    void printType(Writer writer, SchemaType schemaType) throws IOException {
        printType(writer, schemaType, (XmlOptions) null);
    }

    void printType(Writer writer, SchemaType schemaType, XmlOptions xmlOptions) throws IOException {
        printType(writer, schemaType);
    }
}
