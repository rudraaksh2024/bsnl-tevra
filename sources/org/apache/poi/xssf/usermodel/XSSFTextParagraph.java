package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.model.ParagraphPropertyFetcher;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public class XSSFTextParagraph implements Iterable<XSSFTextRun> {
    private final CTTextParagraph _p;
    private final List<XSSFTextRun> _runs = new ArrayList();
    private final CTShape _shape;

    XSSFTextParagraph(CTTextParagraph cTTextParagraph, CTShape cTShape) {
        this._p = cTTextParagraph;
        this._shape = cTShape;
        for (XmlObject xmlObject : cTTextParagraph.selectPath("*")) {
            if (xmlObject instanceof CTRegularTextRun) {
                this._runs.add(new XSSFTextRun((CTRegularTextRun) xmlObject, this));
            } else if (xmlObject instanceof CTTextLineBreak) {
                CTRegularTextRun newInstance = CTRegularTextRun.Factory.newInstance();
                newInstance.setRPr(((CTTextLineBreak) xmlObject).getRPr());
                newInstance.setT("\n");
                this._runs.add(new XSSFTextRun(newInstance, this));
            } else if (xmlObject instanceof CTTextField) {
                CTTextField cTTextField = (CTTextField) xmlObject;
                CTRegularTextRun newInstance2 = CTRegularTextRun.Factory.newInstance();
                newInstance2.setRPr(cTTextField.getRPr());
                newInstance2.setT(cTTextField.getT());
                this._runs.add(new XSSFTextRun(newInstance2, this));
            }
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (XSSFTextRun text : this._runs) {
            sb.append(text.getText());
        }
        return sb.toString();
    }

    @Internal
    public CTTextParagraph getXmlObject() {
        return this._p;
    }

    @Internal
    public CTShape getParentShape() {
        return this._shape;
    }

    public List<XSSFTextRun> getTextRuns() {
        return this._runs;
    }

    public Iterator<XSSFTextRun> iterator() {
        return this._runs.iterator();
    }

    public XSSFTextRun addNewTextRun() {
        CTRegularTextRun addNewR = this._p.addNewR();
        addNewR.addNewRPr().setLang("en-US");
        XSSFTextRun xSSFTextRun = new XSSFTextRun(addNewR, this);
        this._runs.add(xSSFTextRun);
        return xSSFTextRun;
    }

    public XSSFTextRun addLineBreak() {
        CTTextCharacterProperties addNewRPr = this._p.addNewBr().addNewRPr();
        if (!this._runs.isEmpty()) {
            List<XSSFTextRun> list = this._runs;
            addNewRPr.set(list.get(list.size() - 1).getRPr());
        }
        CTRegularTextRun newInstance = CTRegularTextRun.Factory.newInstance();
        newInstance.setRPr(addNewRPr);
        newInstance.setT("\n");
        XSSFLineBreak xSSFLineBreak = new XSSFLineBreak(newInstance, this, addNewRPr);
        this._runs.add(xSSFLineBreak);
        return xSSFLineBreak;
    }

    public TextAlign getTextAlign() {
        AnonymousClass1 r0 = new ParagraphPropertyFetcher<TextAlign>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetAlgn()) {
                    return false;
                }
                setValue(TextAlign.values()[cTTextParagraphProperties.getAlgn().intValue() - 1]);
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return r0.getValue() == null ? TextAlign.LEFT : (TextAlign) r0.getValue();
    }

    public void setTextAlign(TextAlign textAlign) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (textAlign != null) {
            pPr.setAlgn(STTextAlignType.Enum.forInt(textAlign.ordinal() + 1));
        } else if (pPr.isSetAlgn()) {
            pPr.unsetAlgn();
        }
    }

    public TextFontAlign getTextFontAlign() {
        AnonymousClass2 r0 = new ParagraphPropertyFetcher<TextFontAlign>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetFontAlgn()) {
                    return false;
                }
                setValue(TextFontAlign.values()[cTTextParagraphProperties.getFontAlgn().intValue() - 1]);
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return r0.getValue() == null ? TextFontAlign.BASELINE : (TextFontAlign) r0.getValue();
    }

    public void setTextFontAlign(TextFontAlign textFontAlign) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (textFontAlign != null) {
            pPr.setFontAlgn(STTextFontAlignType.Enum.forInt(textFontAlign.ordinal() + 1));
        } else if (pPr.isSetFontAlgn()) {
            pPr.unsetFontAlgn();
        }
    }

    public String getBulletFont() {
        AnonymousClass3 r0 = new ParagraphPropertyFetcher<String>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuFont()) {
                    return false;
                }
                setValue(cTTextParagraphProperties.getBuFont().getTypeface());
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return (String) r0.getValue();
    }

    public void setBulletFont(String str) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetBuFont() ? pPr.getBuFont() : pPr.addNewBuFont()).setTypeface(str);
    }

    public String getBulletCharacter() {
        AnonymousClass4 r0 = new ParagraphPropertyFetcher<String>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuChar()) {
                    return false;
                }
                setValue(cTTextParagraphProperties.getBuChar().getChar());
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return (String) r0.getValue();
    }

    public void setBulletCharacter(String str) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetBuChar() ? pPr.getBuChar() : pPr.addNewBuChar()).setChar(str);
    }

    public Color getBulletFontColor() {
        AnonymousClass5 r0 = new ParagraphPropertyFetcher<Color>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuClr() || !cTTextParagraphProperties.getBuClr().isSetSrgbClr()) {
                    return false;
                }
                byte[] val = cTTextParagraphProperties.getBuClr().getSrgbClr().getVal();
                setValue(new Color(val[0] & UByte.MAX_VALUE, val[1] & UByte.MAX_VALUE, val[2] & UByte.MAX_VALUE));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return (Color) r0.getValue();
    }

    public void setBulletFontColor(Color color) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        CTColor buClr = pPr.isSetBuClr() ? pPr.getBuClr() : pPr.addNewBuClr();
        (buClr.isSetSrgbClr() ? buClr.getSrgbClr() : buClr.addNewSrgbClr()).setVal(new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()});
    }

    public double getBulletFontSize() {
        AnonymousClass6 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (cTTextParagraphProperties.isSetBuSzPct()) {
                    setValue(Double.valueOf(((double) POIXMLUnits.parsePercent(cTTextParagraphProperties.getBuSzPct().xgetVal())) * 0.001d));
                    return true;
                } else if (!cTTextParagraphProperties.isSetBuSzPts()) {
                    return false;
                } else {
                    setValue(Double.valueOf(((double) (-cTTextParagraphProperties.getBuSzPts().getVal())) * 0.01d));
                    return true;
                }
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 100.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setBulletFontSize(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (d >= 0.0d) {
            (pPr.isSetBuSzPct() ? pPr.getBuSzPct() : pPr.addNewBuSzPct()).setVal(Integer.toString((int) (d * 1000.0d)));
            if (pPr.isSetBuSzPts()) {
                pPr.unsetBuSzPts();
                return;
            }
            return;
        }
        (pPr.isSetBuSzPts() ? pPr.getBuSzPts() : pPr.addNewBuSzPts()).setVal((int) ((-d) * 100.0d));
        if (pPr.isSetBuSzPct()) {
            pPr.unsetBuSzPct();
        }
    }

    public void setIndent(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (d != -1.0d) {
            pPr.setIndent(Units.toEMU(d));
        } else if (pPr.isSetIndent()) {
            pPr.unsetIndent();
        }
    }

    public double getIndent() {
        AnonymousClass7 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetIndent()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getIndent())));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setLeftMargin(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (d != -1.0d) {
            pPr.setMarL(Units.toEMU(d));
        } else if (pPr.isSetMarL()) {
            pPr.unsetMarL();
        }
    }

    public double getLeftMargin() {
        AnonymousClass8 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetMarL()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getMarL())));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setRightMargin(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (d != -1.0d) {
            pPr.setMarR(Units.toEMU(d));
        } else if (pPr.isSetMarR()) {
            pPr.unsetMarR();
        }
    }

    public double getRightMargin() {
        AnonymousClass9 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetMarR()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getMarR())));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public double getDefaultTabSize() {
        AnonymousClass10 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetDefTabSz()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextParagraphProperties.xgetDefTabSz()))));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public double getTabStop(final int i) {
        AnonymousClass11 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetTabLst()) {
                    return false;
                }
                CTTextTabStopList tabLst = cTTextParagraphProperties.getTabLst();
                if (i >= tabLst.sizeOfTabArray()) {
                    return false;
                }
                setValue(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(tabLst.getTabArray(i).xgetPos()))));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void addTabStop(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetTabLst() ? pPr.getTabLst() : pPr.addNewTabLst()).addNewTab().setPos(Integer.valueOf(Units.toEMU(d)));
    }

    public void setLineSpacing(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        CTTextSpacing newInstance = CTTextSpacing.Factory.newInstance();
        if (d >= 0.0d) {
            newInstance.addNewSpcPct().setVal(Integer.valueOf((int) (d * 1000.0d)));
        } else {
            newInstance.addNewSpcPts().setVal((int) ((-d) * 100.0d));
        }
        pPr.setLnSpc(newInstance);
    }

    public double getLineSpacing() {
        CTTextNormalAutofit normAutofit;
        AnonymousClass12 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetLnSpc()) {
                    return false;
                }
                CTTextSpacing lnSpc = cTTextParagraphProperties.getLnSpc();
                if (lnSpc.isSetSpcPct()) {
                    setValue(Double.valueOf(((double) POIXMLUnits.parsePercent(lnSpc.getSpcPct().xgetVal())) * 0.001d));
                    return true;
                } else if (!lnSpc.isSetSpcPts()) {
                    return true;
                } else {
                    setValue(Double.valueOf(((double) (-lnSpc.getSpcPts().getVal())) * 0.01d));
                    return true;
                }
            }
        };
        fetchParagraphProperty(r0);
        double doubleValue = r0.getValue() == null ? 100.0d : ((Double) r0.getValue()).doubleValue();
        return (doubleValue <= 0.0d || (normAutofit = this._shape.getTxBody().getBodyPr().getNormAutofit()) == null) ? doubleValue : doubleValue * (1.0d - (((Double) normAutofit.getLnSpcReduction()).doubleValue() / 100000.0d));
    }

    public void setSpaceBefore(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        CTTextSpacing newInstance = CTTextSpacing.Factory.newInstance();
        if (d >= 0.0d) {
            newInstance.addNewSpcPct().setVal(Integer.valueOf((int) (d * 1000.0d)));
        } else {
            newInstance.addNewSpcPts().setVal((int) ((-d) * 100.0d));
        }
        pPr.setSpcBef(newInstance);
    }

    public double getSpaceBefore() {
        AnonymousClass13 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetSpcBef()) {
                    return false;
                }
                CTTextSpacing spcBef = cTTextParagraphProperties.getSpcBef();
                if (spcBef.isSetSpcPct()) {
                    setValue(Double.valueOf(((double) POIXMLUnits.parsePercent(spcBef.getSpcPct().xgetVal())) * 0.001d));
                    return true;
                } else if (!spcBef.isSetSpcPts()) {
                    return true;
                } else {
                    setValue(Double.valueOf(((double) (-spcBef.getSpcPts().getVal())) * 0.01d));
                    return true;
                }
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setSpaceAfter(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        CTTextSpacing newInstance = CTTextSpacing.Factory.newInstance();
        if (d >= 0.0d) {
            newInstance.addNewSpcPct().setVal(Integer.valueOf((int) (d * 1000.0d)));
        } else {
            newInstance.addNewSpcPts().setVal((int) ((-d) * 100.0d));
        }
        pPr.setSpcAft(newInstance);
    }

    public double getSpaceAfter() {
        AnonymousClass14 r0 = new ParagraphPropertyFetcher<Double>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetSpcAft()) {
                    return false;
                }
                CTTextSpacing spcAft = cTTextParagraphProperties.getSpcAft();
                if (spcAft.isSetSpcPct()) {
                    setValue(Double.valueOf(((double) POIXMLUnits.parsePercent(spcAft.getSpcPct().xgetVal())) * 0.001d));
                    return true;
                } else if (!spcAft.isSetSpcPts()) {
                    return true;
                } else {
                    setValue(Double.valueOf(((double) (-spcAft.getSpcPts().getVal())) * 0.01d));
                    return true;
                }
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0.0d;
        }
        return ((Double) r0.getValue()).doubleValue();
    }

    public void setLevel(int i) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        (isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr()).setLvl(i);
    }

    public int getLevel() {
        CTTextParagraphProperties pPr = this._p.getPPr();
        if (pPr == null) {
            return 0;
        }
        return pPr.getLvl();
    }

    public boolean isBullet() {
        AnonymousClass15 r0 = new ParagraphPropertyFetcher<Boolean>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (cTTextParagraphProperties.isSetBuNone()) {
                    setValue(false);
                    return true;
                } else if (!cTTextParagraphProperties.isSetBuFont() || (!cTTextParagraphProperties.isSetBuChar() && !cTTextParagraphProperties.isSetBuAutoNum())) {
                    return false;
                } else {
                    setValue(true);
                    return true;
                }
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return false;
        }
        return ((Boolean) r0.getValue()).booleanValue();
    }

    public void setBullet(boolean z) {
        if (isBullet() != z) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            if (!z) {
                pPr.addNewBuNone();
                if (pPr.isSetBuAutoNum()) {
                    pPr.unsetBuAutoNum();
                }
                if (pPr.isSetBuBlip()) {
                    pPr.unsetBuBlip();
                }
                if (pPr.isSetBuChar()) {
                    pPr.unsetBuChar();
                }
                if (pPr.isSetBuClr()) {
                    pPr.unsetBuClr();
                }
                if (pPr.isSetBuClrTx()) {
                    pPr.unsetBuClrTx();
                }
                if (pPr.isSetBuFont()) {
                    pPr.unsetBuFont();
                }
                if (pPr.isSetBuFontTx()) {
                    pPr.unsetBuFontTx();
                }
                if (pPr.isSetBuSzPct()) {
                    pPr.unsetBuSzPct();
                }
                if (pPr.isSetBuSzPts()) {
                    pPr.unsetBuSzPts();
                }
                if (pPr.isSetBuSzTx()) {
                    pPr.unsetBuSzTx();
                    return;
                }
                return;
            }
            if (pPr.isSetBuNone()) {
                pPr.unsetBuNone();
            }
            if (!pPr.isSetBuFont()) {
                pPr.addNewBuFont().setTypeface(HSSFFont.FONT_ARIAL);
            }
            if (!pPr.isSetBuAutoNum()) {
                pPr.addNewBuChar().setChar("•");
            }
        }
    }

    public void setBullet(ListAutoNumber listAutoNumber, int i) {
        if (i >= 1) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            CTTextAutonumberBullet buAutoNum = pPr.isSetBuAutoNum() ? pPr.getBuAutoNum() : pPr.addNewBuAutoNum();
            buAutoNum.setType(STTextAutonumberScheme.Enum.forInt(listAutoNumber.ordinal() + 1));
            buAutoNum.setStartAt(i);
            if (!pPr.isSetBuFont()) {
                pPr.addNewBuFont().setTypeface(HSSFFont.FONT_ARIAL);
            }
            if (pPr.isSetBuNone()) {
                pPr.unsetBuNone();
            }
            if (pPr.isSetBuBlip()) {
                pPr.unsetBuBlip();
            }
            if (pPr.isSetBuChar()) {
                pPr.unsetBuChar();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Start Number must be greater or equal that 1");
    }

    public void setBullet(ListAutoNumber listAutoNumber) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetBuAutoNum() ? pPr.getBuAutoNum() : pPr.addNewBuAutoNum()).setType(STTextAutonumberScheme.Enum.forInt(listAutoNumber.ordinal() + 1));
        if (!pPr.isSetBuFont()) {
            pPr.addNewBuFont().setTypeface(HSSFFont.FONT_ARIAL);
        }
        if (pPr.isSetBuNone()) {
            pPr.unsetBuNone();
        }
        if (pPr.isSetBuBlip()) {
            pPr.unsetBuBlip();
        }
        if (pPr.isSetBuChar()) {
            pPr.unsetBuChar();
        }
    }

    public boolean isBulletAutoNumber() {
        AnonymousClass16 r0 = new ParagraphPropertyFetcher<Boolean>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuAutoNum()) {
                    return false;
                }
                setValue(true);
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return false;
        }
        return ((Boolean) r0.getValue()).booleanValue();
    }

    public int getBulletAutoNumberStart() {
        AnonymousClass17 r0 = new ParagraphPropertyFetcher<Integer>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuAutoNum() || !cTTextParagraphProperties.getBuAutoNum().isSetStartAt()) {
                    return false;
                }
                setValue(Integer.valueOf(cTTextParagraphProperties.getBuAutoNum().getStartAt()));
                return true;
            }
        };
        fetchParagraphProperty(r0);
        if (r0.getValue() == null) {
            return 0;
        }
        return ((Integer) r0.getValue()).intValue();
    }

    public ListAutoNumber getBulletAutoNumberScheme() {
        AnonymousClass18 r0 = new ParagraphPropertyFetcher<ListAutoNumber>(getLevel()) {
            public boolean fetch(CTTextParagraphProperties cTTextParagraphProperties) {
                if (!cTTextParagraphProperties.isSetBuAutoNum()) {
                    return false;
                }
                setValue(ListAutoNumber.values()[cTTextParagraphProperties.getBuAutoNum().getType().intValue() - 1]);
                return true;
            }
        };
        fetchParagraphProperty(r0);
        return r0.getValue() == null ? ListAutoNumber.ARABIC_PLAIN : (ListAutoNumber) r0.getValue();
    }

    private boolean fetchParagraphProperty(ParagraphPropertyFetcher paragraphPropertyFetcher) {
        boolean fetch = this._p.isSetPPr() ? paragraphPropertyFetcher.fetch(this._p.getPPr()) : false;
        return !fetch ? paragraphPropertyFetcher.fetch(this._shape) : fetch;
    }

    public String toString() {
        return "[" + getClass() + "]" + getText();
    }
}
