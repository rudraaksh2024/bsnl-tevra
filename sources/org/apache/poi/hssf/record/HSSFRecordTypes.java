package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.hssf.record.chart.AreaFormatRecord;
import org.apache.poi.hssf.record.chart.AreaRecord;
import org.apache.poi.hssf.record.chart.AxisLineFormatRecord;
import org.apache.poi.hssf.record.chart.AxisOptionsRecord;
import org.apache.poi.hssf.record.chart.AxisParentRecord;
import org.apache.poi.hssf.record.chart.AxisRecord;
import org.apache.poi.hssf.record.chart.AxisUsedRecord;
import org.apache.poi.hssf.record.chart.BarRecord;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.record.chart.CatLabRecord;
import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.apache.poi.hssf.record.chart.ChartEndBlockRecord;
import org.apache.poi.hssf.record.chart.ChartEndObjectRecord;
import org.apache.poi.hssf.record.chart.ChartFRTInfoRecord;
import org.apache.poi.hssf.record.chart.ChartFormatRecord;
import org.apache.poi.hssf.record.chart.ChartRecord;
import org.apache.poi.hssf.record.chart.ChartStartBlockRecord;
import org.apache.poi.hssf.record.chart.ChartStartObjectRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.hssf.record.chart.DatRecord;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.record.chart.DataLabelExtensionRecord;
import org.apache.poi.hssf.record.chart.DefaultDataLabelTextPropertiesRecord;
import org.apache.poi.hssf.record.chart.EndRecord;
import org.apache.poi.hssf.record.chart.FontBasisRecord;
import org.apache.poi.hssf.record.chart.FontIndexRecord;
import org.apache.poi.hssf.record.chart.FrameRecord;
import org.apache.poi.hssf.record.chart.LegendRecord;
import org.apache.poi.hssf.record.chart.LineFormatRecord;
import org.apache.poi.hssf.record.chart.LinkedDataRecord;
import org.apache.poi.hssf.record.chart.NumberFormatIndexRecord;
import org.apache.poi.hssf.record.chart.ObjectLinkRecord;
import org.apache.poi.hssf.record.chart.PlotAreaRecord;
import org.apache.poi.hssf.record.chart.PlotGrowthRecord;
import org.apache.poi.hssf.record.chart.SeriesChartGroupIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesIndexRecord;
import org.apache.poi.hssf.record.chart.SeriesLabelsRecord;
import org.apache.poi.hssf.record.chart.SeriesListRecord;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.apache.poi.hssf.record.chart.SeriesTextRecord;
import org.apache.poi.hssf.record.chart.SheetPropertiesRecord;
import org.apache.poi.hssf.record.chart.TextRecord;
import org.apache.poi.hssf.record.chart.TickRecord;
import org.apache.poi.hssf.record.chart.UnitsRecord;
import org.apache.poi.hssf.record.chart.ValueRangeRecord;
import org.apache.poi.hssf.record.pivottable.DataItemRecord;
import org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;
import org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord;
import org.apache.poi.hssf.record.pivottable.ViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.ViewSourceRecord;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;

public enum HSSFRecordTypes {
    UNKNOWN(-1, UnknownRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda0(), false),
    FORMULA(6, FormulaRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda149()),
    EOF(10, EOFRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda161()),
    CALC_COUNT(12, CalcCountRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda4()),
    CALC_MODE(13, CalcModeRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda16()),
    PRECISION(14, PrecisionRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda28()),
    REF_MODE(15, RefModeRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda40()),
    DELTA(16, DeltaRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda52()),
    ITERATION(17, IterationRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda64()),
    PROTECT(18, ProtectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda76()),
    PASSWORD(19, PasswordRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda22()),
    HEADER(20, HeaderRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda84()),
    FOOTER(21, FooterRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda96()),
    EXTERN_SHEET(23, ExternSheetRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda108()),
    NAME(24, NameRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda120()),
    WINDOW_PROTECT(25, WindowProtectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda132()),
    VERTICAL_PAGE_BREAK(26, VerticalPageBreakRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda144()),
    HORIZONTAL_PAGE_BREAK(27, HorizontalPageBreakRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda145()),
    NOTE(28, NoteRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda146()),
    SELECTION(29, SelectionRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda148()),
    DATE_WINDOW_1904(34, DateWindow1904Record.class, new HSSFRecordTypes$$ExternalSyntheticLambda150()),
    EXTERNAL_NAME(35, ExternalNameRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda151()),
    LEFT_MARGIN(38, LeftMarginRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda152()),
    RIGHT_MARGIN(39, RightMarginRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda153()),
    TOP_MARGIN(40, TopMarginRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda154()),
    BOTTOM_MARGIN(41, BottomMarginRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda155()),
    PRINT_HEADERS(42, PrintHeadersRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda156()),
    PRINT_GRIDLINES(43, PrintGridlinesRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda157()),
    FILE_PASS(47, FilePassRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda159()),
    FONT(49, FontRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda160()),
    CONTINUE(60, ContinueRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda162()),
    WINDOW_ONE(61, WindowOneRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda163()),
    BACKUP(64, BackupRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda164()),
    PANE(65, PaneRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda165()),
    CODEPAGE(66, CodepageRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda166()),
    DCON_REF(81, DConRefRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda167()),
    DEFAULT_COL_WIDTH(85, DefaultColWidthRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda168()),
    CRN_COUNT(89, CRNCountRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda1()),
    CRN(90, CRNRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda2()),
    WRITE_ACCESS(92, WriteAccessRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda3()),
    FILE_SHARING(91, FileSharingRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda5()),
    OBJ(93, ObjRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda6()),
    UNCALCED(94, UncalcedRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda7()),
    SAVE_RECALC(95, SaveRecalcRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda8()),
    OBJECT_PROTECT(99, ObjectProtectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda9()),
    COLUMN_INFO(125, ColumnInfoRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda10()),
    GUTS(128, GutsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda12()),
    WS_BOOL(129, WSBoolRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda13()),
    GRIDSET(130, GridsetRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda14()),
    H_CENTER(131, HCenterRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda15()),
    V_CENTER(132, VCenterRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda17()),
    BOUND_SHEET(133, BoundSheetRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda18()),
    WRITE_PROTECT(134, WriteProtectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda19()),
    COUNTRY(140, CountryRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda20()),
    HIDE_OBJ(141, HideObjRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda21()),
    PALETTE(146, PaletteRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda23()),
    FN_GROUP_COUNT(156, FnGroupCountRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda24()),
    AUTO_FILTER_INFO(157, AutoFilterInfoRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda25()),
    SCL(160, SCLRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda26(), false),
    PRINT_SETUP(161, PrintSetupRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda27()),
    VIEW_DEFINITION(176, ViewDefinitionRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda29()),
    VIEW_FIELDS(177, ViewFieldsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda30()),
    PAGE_ITEM(182, PageItemRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda31()),
    MUL_BLANK(190, MulBlankRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda32()),
    MUL_RK(189, MulRKRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda34()),
    MMS(193, MMSRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda35()),
    DATA_ITEM(HSSFShapeTypes.ActionButtonReturn, DataItemRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda36()),
    STREAM_ID(213, StreamIDRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda37()),
    DB_CELL(215, DBCellRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda38()),
    BOOK_BOOL(218, BookBoolRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda39()),
    SCENARIO_PROTECT(221, ScenarioProtectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda41()),
    EXTENDED_FORMAT(224, ExtendedFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda42()),
    INTERFACE_HDR(225, InterfaceHdrRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda43()),
    INTERFACE_END(226, InterfaceEndRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda45()),
    VIEW_SOURCE(227, ViewSourceRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda46()),
    MERGE_CELLS(229, MergeCellsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda47()),
    DRAWING_GROUP(235, DrawingGroupRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda48()),
    DRAWING(236, DrawingRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda49()),
    DRAWING_SELECTION(237, DrawingSelectionRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda50()),
    SST(252, SSTRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda51()),
    LABEL_SST(253, LabelSSTRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda53()),
    EXT_SST(255, ExtSSTRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda54()),
    EXTENDED_PIVOT_TABLE_VIEW_FIELDS(256, ExtendedPivotTableViewFieldsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda56()),
    TAB_ID(TypedValues.AttributesType.TYPE_EASING, TabIdRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda57()),
    USE_SEL_FS(352, UseSelFSRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda58()),
    DSF(353, DSFRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda59()),
    USER_SVIEW_BEGIN(426, UserSViewBegin.class, new HSSFRecordTypes$$ExternalSyntheticLambda60()),
    USER_SVIEW_END(427, UserSViewEnd.class, new HSSFRecordTypes$$ExternalSyntheticLambda61()),
    SUP_BOOK(430, SupBookRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda62()),
    PROTECTION_REV_4(431, ProtectionRev4Record.class, new HSSFRecordTypes$$ExternalSyntheticLambda63()),
    CF_HEADER(432, CFHeaderRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda65()),
    CF_RULE(433, CFRuleRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda67()),
    DVAL(434, DVALRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda68()),
    TEXT_OBJECT(438, TextObjectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda69()),
    REFRESH_ALL(439, RefreshAllRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda70()),
    HYPERLINK(440, HyperlinkRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda71()),
    PASSWORD_REV_4(444, PasswordRev4Record.class, new HSSFRecordTypes$$ExternalSyntheticLambda72()),
    DV(446, DVRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda73()),
    RECALC_ID(449, RecalcIdRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda74()),
    DIMENSIONS(512, DimensionsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda75()),
    BLANK(InputDeviceCompat.SOURCE_DPAD, BlankRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda81()),
    NUMBER(515, NumberRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda92()),
    LABEL(516, LabelRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda103()),
    BOOL_ERR(517, BoolErrRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda114()),
    STRING(519, StringRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda125()),
    ROW(520, RowRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda136()),
    INDEX(523, IndexRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda147()),
    ARRAY(545, ArrayRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda158()),
    DEFAULT_ROW_HEIGHT(549, DefaultRowHeightRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda169()),
    TABLE(566, TableRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda11()),
    WINDOW_TWO(574, WindowTwoRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda33()),
    RK(638, RKRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda44()),
    STYLE(659, StyleRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda55()),
    FORMAT(1054, FormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda66()),
    SHARED_FORMULA(1212, SharedFormulaRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda77()),
    BOF(2057, BOFRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda78()),
    CHART_FRT_INFO(2128, ChartFRTInfoRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda79()),
    CHART_START_BLOCK(2130, ChartStartBlockRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda80()),
    CHART_END_BLOCK(2131, ChartEndBlockRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda82()),
    CHART_START_OBJECT(2132, ChartStartObjectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda83()),
    CHART_END_OBJECT(2133, ChartEndObjectRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda85()),
    CAT_LAB(2134, CatLabRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda86()),
    FEAT_HDR(UnknownRecord.SHEETPROTECTION_0867, FeatHdrRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda87()),
    FEAT(2152, FeatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda88()),
    DATA_LABEL_EXTENSION(2154, DataLabelExtensionRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda89(), false),
    CF_HEADER_12(2169, CFHeader12Record.class, new HSSFRecordTypes$$ExternalSyntheticLambda90()),
    CF_RULE_12(2170, CFRule12Record.class, new HSSFRecordTypes$$ExternalSyntheticLambda91()),
    TABLE_STYLES(2190, TableStylesRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda93()),
    NAME_COMMENT(2196, NameCommentRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda94()),
    HEADER_FOOTER(UnknownRecord.HEADER_FOOTER_089C, HeaderFooterRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda95()),
    UNITS(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, UnitsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda97(), false),
    CHART(InputDeviceCompat.SOURCE_TOUCHSCREEN, ChartRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda98()),
    SERIES(FragmentTransaction.TRANSIT_FRAGMENT_FADE, SeriesRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda99()),
    DATA_FORMAT(4102, DataFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda100()),
    LINE_FORMAT(4103, LineFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda101(), false),
    AREA_FORMAT(4106, AreaFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda102(), false),
    SERIES_LABELS(4108, SeriesLabelsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda104(), false),
    SERIES_TEXT(4109, SeriesTextRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda105()),
    CHART_FORMAT(4116, ChartFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda106(), false),
    LEGEND(4117, LegendRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda107()),
    SERIES_LIST(4118, SeriesListRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda109(), false),
    BAR(4119, BarRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda110(), false),
    AREA(4122, AreaRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda111()),
    AXIS(4125, AxisRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda112(), false),
    TICK(4126, TickRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda113(), false),
    VALUE_RANGE(4127, ValueRangeRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda115()),
    CATEGORY_SERIES_AXIS(4128, CategorySeriesAxisRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda116(), false),
    AXIS_LINE_FORMAT(4129, AxisLineFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda117(), false),
    DEFAULT_DATA_LABEL_TEXT_PROPERTIES(4132, DefaultDataLabelTextPropertiesRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda118(), false),
    TEXT(4133, TextRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda119(), false),
    FONT_INDEX(4134, FontIndexRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda121(), false),
    OBJECT_LINK(4135, ObjectLinkRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda122(), false),
    FRAME(4146, FrameRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda123(), false),
    BEGIN(4147, BeginRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda124()),
    END(4148, EndRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda126()),
    PLOT_AREA(4149, PlotAreaRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda127(), false),
    AXIS_PARENT(4161, AxisParentRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda128(), false),
    SHEET_PROPERTIES(4164, SheetPropertiesRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda129(), false),
    SERIES_CHART_GROUP_INDEX(4165, SeriesChartGroupIndexRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda130()),
    AXIS_USED(4166, AxisUsedRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda131(), false),
    NUMBER_FORMAT_INDEX(4174, NumberFormatIndexRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda133(), false),
    CHART_TITLE_FORMAT(4176, ChartTitleFormatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda134()),
    LINKED_DATA(4177, LinkedDataRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda135()),
    FONT_BASIS(4192, FontBasisRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda137(), false),
    AXIS_OPTIONS(4194, AxisOptionsRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda138(), false),
    DAT(4195, DatRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda139(), false),
    PLOT_GROWTH(4196, PlotGrowthRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda140(), false),
    SERIES_INDEX(4197, SeriesIndexRecord.class, new HSSFRecordTypes$$ExternalSyntheticLambda141(), false),
    ESCHER_AGGREGATE(9876, EscherAggregate.class, new HSSFRecordTypes$$ExternalSyntheticLambda142());
    
    private static final Map<Short, HSSFRecordTypes> LOOKUP = null;
    public final Class<? extends Record> clazz;
    public final boolean parse;
    public final RecordConstructor<? extends Record> recordConstructor;
    public final short sid;

    @FunctionalInterface
    public interface RecordConstructor<T extends Record> {
        T apply(RecordInputStream recordInputStream);
    }

    static {
        LOOKUP = (Map) Arrays.stream(values()).collect(Collectors.toMap(new HSSFRecordTypes$$ExternalSyntheticLambda143(), Function.identity()));
    }

    private HSSFRecordTypes(int i, Class<? extends Record> cls, RecordConstructor<? extends Record> recordConstructor2) {
        this(r8, r9, i, cls, recordConstructor2, true);
    }

    private HSSFRecordTypes(int i, Class<? extends Record> cls, RecordConstructor<? extends Record> recordConstructor2, boolean z) {
        this.sid = (short) i;
        this.clazz = cls;
        this.recordConstructor = recordConstructor2;
        this.parse = z;
    }

    public static HSSFRecordTypes forSID(int i) {
        return LOOKUP.getOrDefault(Short.valueOf((short) i), UNKNOWN);
    }

    public short getSid() {
        return this.sid;
    }

    public Class<? extends Record> getClazz() {
        return this.clazz;
    }

    public RecordConstructor<? extends Record> getRecordConstructor() {
        return this.recordConstructor;
    }

    public boolean isParseable() {
        return this.parse;
    }
}
