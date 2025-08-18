package org.apache.poi.poifs.macros;

public interface Module {

    public enum ModuleType {
        Document,
        Module,
        Class
    }

    ModuleType geModuleType();

    String getContent();
}
