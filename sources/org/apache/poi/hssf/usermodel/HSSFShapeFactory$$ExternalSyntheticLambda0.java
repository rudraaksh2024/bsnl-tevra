package org.apache.poi.hssf.usermodel;

import java.util.function.Consumer;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.poifs.filesystem.DirectoryNode;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HSSFShapeFactory$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ EscherAggregate f$0;
    public final /* synthetic */ HSSFShapeGroup f$1;
    public final /* synthetic */ DirectoryNode f$2;

    public /* synthetic */ HSSFShapeFactory$$ExternalSyntheticLambda0(EscherAggregate escherAggregate, HSSFShapeGroup hSSFShapeGroup, DirectoryNode directoryNode) {
        this.f$0 = escherAggregate;
        this.f$1 = hSSFShapeGroup;
        this.f$2 = directoryNode;
    }

    public final void accept(Object obj) {
        HSSFShapeFactory.createShapeTree((EscherContainerRecord) obj, this.f$0, this.f$1, this.f$2);
    }
}
