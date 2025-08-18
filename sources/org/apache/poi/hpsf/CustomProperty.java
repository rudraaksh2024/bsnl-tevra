package org.apache.poi.hpsf;

import java.util.Objects;

public class CustomProperty extends Property {
    private String name;

    public CustomProperty() {
        this.name = null;
    }

    public CustomProperty(Property property) {
        this(property, (String) null);
    }

    public CustomProperty(Property property, String str) {
        super(property);
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean equalsContents(Object obj) {
        boolean z;
        CustomProperty customProperty = (CustomProperty) obj;
        String name2 = customProperty.getName();
        String name3 = getName();
        if (name2 == null) {
            z = name3 == null;
        } else {
            z = name2.equals(name3);
        }
        if (!z || customProperty.getID() != getID() || customProperty.getType() != getType() || !customProperty.getValue().equals(getValue())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, Long.valueOf(getID())});
    }

    public boolean equals(Object obj) {
        return (obj instanceof CustomProperty) && equalsContents(obj);
    }
}
