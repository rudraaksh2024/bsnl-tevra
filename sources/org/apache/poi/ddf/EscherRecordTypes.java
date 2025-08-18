package org.apache.poi.ddf;

import androidx.core.os.EnvironmentCompat;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EscherRecordTypes {
    DGG_CONTAINER(61440, "DggContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    BSTORE_CONTAINER(61441, "BStoreContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    DG_CONTAINER(61442, "DgContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SPGR_CONTAINER(61443, "SpgrContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SP_CONTAINER(61444, "SpContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    SOLVER_CONTAINER(61445, "SolverContainer", (int) null, new DefaultEscherRecordFactory$$ExternalSyntheticLambda0()),
    DGG(61446, "Dgg", "MsofbtDgg", new EscherRecordTypes$$ExternalSyntheticLambda2()),
    BSE(61447, "BSE", "MsofbtBSE", new EscherRecordTypes$$ExternalSyntheticLambda3()),
    DG(61448, "Dg", "MsofbtDg", new EscherRecordTypes$$ExternalSyntheticLambda4()),
    SPGR(61449, "Spgr", "MsofbtSpgr", new EscherRecordTypes$$ExternalSyntheticLambda5()),
    SP(61450, "Sp", "MsofbtSp", new EscherRecordTypes$$ExternalSyntheticLambda6()),
    OPT(61451, "Opt", "msofbtOPT", new EscherRecordTypes$$ExternalSyntheticLambda7()),
    TEXTBOX(61452, (int) null, (int) null, new EscherRecordTypes$$ExternalSyntheticLambda8()),
    CLIENT_TEXTBOX(61453, "ClientTextbox", "msofbtClientTextbox", new EscherRecordTypes$$ExternalSyntheticLambda8()),
    ANCHOR(61454, (int) null, (int) null, (String) null),
    CHILD_ANCHOR(61455, "ChildAnchor", "MsofbtChildAnchor", new EscherRecordTypes$$ExternalSyntheticLambda9()),
    CLIENT_ANCHOR(61456, "ClientAnchor", "MsofbtClientAnchor", new EscherRecordTypes$$ExternalSyntheticLambda10()),
    CLIENT_DATA(61457, "ClientData", "MsofbtClientData", new EscherRecordTypes$$ExternalSyntheticLambda11()),
    CONNECTOR_RULE(61458, (int) null, (int) null, (String) null),
    ALIGN_RULE(61459, (int) null, (int) null, (String) null),
    ARC_RULE(61460, (int) null, (int) null, (String) null),
    CLIENT_RULE(61461, (int) null, (int) null, (String) null),
    CLSID(61462, (int) null, (int) null, (String) null),
    CALLOUT_RULE(61463, (int) null, (int) null, (String) null),
    BLIP_START(61464, "Blip", "msofbtBlip", (String) null),
    BLIP_EMF(61466, "BlipEmf", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda12()),
    BLIP_WMF(61467, "BlipWmf", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda12()),
    BLIP_PICT(61468, "BlipPict", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda12()),
    BLIP_JPEG(61469, "BlipJpeg", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda13()),
    BLIP_PNG(61470, "BlipPng", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda13()),
    BLIP_DIB(61471, "BlipDib", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda13()),
    BLIP_TIFF(61481, "BlipTiff", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda13()),
    BLIP_END(61719, "Blip", "msofbtBlip", (String) null),
    REGROUP_ITEMS(61720, (int) null, (int) null, (String) null),
    SELECTION(61721, (int) null, (int) null, (String) null),
    COLOR_MRU(61722, (int) null, (int) null, (String) null),
    DELETED_PSPL(61725, (int) null, (int) null, (String) null),
    SPLIT_MENU_COLORS(61726, "SplitMenuColors", "MsofbtSplitMenuColors", new EscherRecordTypes$$ExternalSyntheticLambda14()),
    OLE_OBJECT(61727, (int) null, (int) null, (String) null),
    COLOR_SCHEME(61728, (int) null, (int) null, (String) null),
    USER_DEFINED(61730, "TertiaryOpt", (int) null, new EscherRecordTypes$$ExternalSyntheticLambda0()),
    UNKNOWN(65535, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, new DefaultEscherRecordFactory$$ExternalSyntheticLambda2());
    
    private static final Map<Short, EscherRecordTypes> LOOKUP = null;
    public final Supplier<? extends EscherRecord> constructor;
    public final String description;
    public final String recordName;
    public final short typeID;

    static {
        LOOKUP = (Map) Stream.of(values()).collect(Collectors.toMap(new EscherRecordTypes$$ExternalSyntheticLambda1(), Function.identity()));
    }

    private EscherRecordTypes(int i, String str, String str2, Supplier<? extends EscherRecord> supplier) {
        this.typeID = (short) i;
        this.recordName = str;
        this.description = str2;
        this.constructor = supplier;
    }

    /* access modifiers changed from: private */
    public Short getTypeId() {
        return Short.valueOf(this.typeID);
    }

    public static EscherRecordTypes forTypeID(int i) {
        if (i == 61482) {
            return BLIP_JPEG;
        }
        EscherRecordTypes escherRecordTypes = LOOKUP.get(Short.valueOf((short) i));
        return escherRecordTypes != null ? escherRecordTypes : UNKNOWN;
    }
}
