package org.apache.poi.xssf.model;

import org.apache.poi.ss.usermodel.RichTextString;

public interface SharedStrings {
    int getCount();

    RichTextString getItemAt(int i);

    int getUniqueCount();
}
