package org.apache.poi.xssf.binary;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;

@Internal
public enum XSSFBRecordType {
    BrtCellBlank(1),
    BrtCellRk(2),
    BrtCellError(3),
    BrtCellBool(4),
    BrtCellReal(5),
    BrtCellSt(6),
    BrtCellIsst(7),
    BrtFmlaString(8),
    BrtFmlaNum(9),
    BrtFmlaBool(10),
    BrtFmlaError(11),
    BrtRowHdr(0),
    BrtCellRString(62),
    BrtBeginSheet(129),
    BrtWsProp(147),
    BrtWsDim(148),
    BrtColInfo(60),
    BrtBeginSheetData(145),
    BrtEndSheetData(146),
    BrtHLink(494),
    BrtBeginHeaderFooter(479),
    BrtBeginCommentAuthors(630),
    BrtEndCommentAuthors(631),
    BrtCommentAuthor(632),
    BrtBeginComment(Units.EMU_PER_DXA),
    BrtCommentText(637),
    BrtEndComment(636),
    BrtXf(47),
    BrtFmt(44),
    BrtBeginFmts(615),
    BrtEndFmts(616),
    BrtBeginCellXFs(617),
    BrtEndCellXFs(618),
    BrtBeginCellStyleXFS(626),
    BrtEndCellStyleXFS(627),
    BrtSstItem(19),
    BrtBeginSst(159),
    BrtEndSst(160),
    BrtBundleSh(156),
    BrtAbsPath15(2071),
    Unimplemented(-1);
    
    private static final Map<Integer, XSSFBRecordType> TYPE_MAP = null;
    private final int id;

    static {
        TYPE_MAP = new HashMap();
        for (XSSFBRecordType xSSFBRecordType : values()) {
            TYPE_MAP.put(Integer.valueOf(xSSFBRecordType.getId()), xSSFBRecordType);
        }
    }

    private XSSFBRecordType(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public static XSSFBRecordType lookup(int i) {
        XSSFBRecordType xSSFBRecordType = TYPE_MAP.get(Integer.valueOf(i));
        return xSSFBRecordType == null ? Unimplemented : xSSFBRecordType;
    }
}
