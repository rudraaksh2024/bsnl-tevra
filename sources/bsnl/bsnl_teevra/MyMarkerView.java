package bsnl.bsnl_teevra;

import android.content.Context;
import android.widget.TextView;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyMarkerView extends MarkerView {
    private String marker;
    private ArrayList<String> timearray;
    private final TextView tvContent = ((TextView) findViewById(R.id.tvContent));

    public MyMarkerView(Context context, int i, String str, ArrayList<String> arrayList) {
        super(context, i);
        this.marker = str;
        this.timearray = arrayList;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
        String str;
        String str2;
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.##");
        if (entry instanceof CandleEntry) {
            str2 = decimalFormat.format((double) ((CandleEntry) entry).getHigh()) + " " + this.marker;
            str = this.timearray.get((int) entry.getX());
        } else {
            str2 = decimalFormat.format((double) entry.getY()) + " " + this.marker;
            str = this.timearray.get((int) entry.getX());
        }
        this.tvContent.setText(str2 + " On " + str);
        super.refreshContent(entry, highlight);
    }

    public MPPointF getOffset() {
        return new MPPointF((float) (-(getWidth() / 2)), (float) (-getHeight()));
    }
}
