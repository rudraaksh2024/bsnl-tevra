package org.apache.poi.poifs.property;

import java.io.IOException;
import java.util.Iterator;

public interface Parent extends Child, Iterable<Property> {
    void addChild(Property property) throws IOException;

    Iterator<Property> getChildren();

    void setNextChild(Child child);

    void setPreviousChild(Child child);
}
