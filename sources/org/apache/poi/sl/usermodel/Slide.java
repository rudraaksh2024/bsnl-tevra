package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.util.Removal;

public interface Slide<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Sheet<S, P> {
    List<? extends Comment> getComments();

    @Deprecated
    @Removal(version = "6.0.0")
    boolean getDisplayPlaceholder(Placeholder placeholder) {
        return false;
    }

    boolean getDisplayPlaceholder(SimpleShape<?, ?> simpleShape) {
        return false;
    }

    boolean getFollowMasterBackground();

    boolean getFollowMasterColourScheme();

    boolean getFollowMasterObjects();

    Notes<S, P> getNotes();

    MasterSheet<S, P> getSlideLayout();

    String getSlideName();

    int getSlideNumber();

    String getTitle();

    boolean isHidden();

    void setFollowMasterBackground(boolean z);

    void setFollowMasterColourScheme(boolean z);

    void setFollowMasterObjects(boolean z);

    void setHidden(boolean z);

    void setNotes(Notes<S, P> notes);
}
