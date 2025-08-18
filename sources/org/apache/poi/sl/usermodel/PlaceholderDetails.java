package org.apache.poi.sl.usermodel;

import java.time.format.DateTimeFormatter;

public interface PlaceholderDetails {

    public enum PlaceholderSize {
        quarter,
        half,
        full
    }

    Placeholder getPlaceholder();

    PlaceholderSize getSize();

    String getText();

    String getUserDate() {
        return null;
    }

    boolean isVisible();

    void setPlaceholder(Placeholder placeholder);

    void setSize(PlaceholderSize placeholderSize);

    void setText(String str);

    void setVisible(boolean z);

    DateTimeFormatter getDateFormat() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
