package org.apache.xmlbeans.impl.tool;

import java.util.ArrayList;
import java.util.List;

public class Extension {
    private Class<?> className;
    private final List<Param> params = new ArrayList();

    public Class<?> getClassName() {
        return this.className;
    }

    public void setClassName(Class<?> cls) {
        this.className = cls;
    }

    public void setClassName(String str) throws ClassNotFoundException {
        this.className = Class.forName(str);
    }

    public List<Param> getParams() {
        return this.params;
    }

    public Param createParam() {
        Param param = new Param();
        this.params.add(param);
        return param;
    }

    public static class Param {
        private String name;
        private String value;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }
}
