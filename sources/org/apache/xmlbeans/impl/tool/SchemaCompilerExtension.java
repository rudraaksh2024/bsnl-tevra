package org.apache.xmlbeans.impl.tool;

import java.util.Map;
import org.apache.xmlbeans.SchemaTypeSystem;

public interface SchemaCompilerExtension {
    String getExtensionName();

    void schemaCompilerExtension(SchemaTypeSystem schemaTypeSystem, Map map);
}
