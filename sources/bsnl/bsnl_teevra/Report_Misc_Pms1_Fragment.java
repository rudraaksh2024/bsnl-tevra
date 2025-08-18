package bsnl.bsnl_teevra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Report_Misc_Pms1_Fragment extends Fragment {
    private PieChart piechart;
    private TextView txt_header;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.report_misc_pms1_fragment, viewGroup, false);
        String string = getArguments().getString("response");
        String string2 = getArguments().getString("circle");
        String string3 = getArguments().getString("ssa");
        this.piechart = (PieChart) inflate.findViewById(R.id.piechart);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_header);
        this.txt_header = textView;
        textView.setText("CIRCLE : " + string2 + " | SSA : " + string3);
        try {
            JSONObject jSONObject = new JSONObject(string);
            String string4 = jSONObject.getString("ONLINE");
            String string5 = jSONObject.getString("OFFLINE");
            float parseFloat = (Float.parseFloat(string4) / Float.parseFloat(jSONObject.getString("TOTAL"))) * 100.0f;
            DecimalFormat decimalFormat = new DecimalFormat("###.#");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PieEntry(Float.parseFloat(string4), "ENABLED"));
            arrayList.add(new PieEntry(Float.parseFloat(string5), "DISABLED"));
            ArrayList arrayList2 = new ArrayList();
            for (int valueOf : ColorTemplate.MATERIAL_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf));
            }
            for (int valueOf2 : ColorTemplate.VORDIPLOM_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf2));
            }
            Collections.shuffle(arrayList2);
            PieDataSet pieDataSet = new PieDataSet(arrayList, "PMS");
            pieDataSet.setValueFormatter(new ValueFormatter() {
                public String getFormattedValue(float f) {
                    return String.valueOf((int) Math.floor((double) f));
                }
            });
            pieDataSet.setColors((List<Integer>) arrayList2);
            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(true);
            pieData.setValueFormatter(new PercentFormatter(this.piechart));
            pieData.setValueTextSize(12.0f);
            pieData.setValueTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.piechart.setData(pieData);
            this.piechart.invalidate();
            this.piechart.animateY(1400, Easing.EaseInOutQuad);
            this.piechart.setRotationEnabled(false);
            this.piechart.setDrawHoleEnabled(true);
            this.piechart.setEntryLabelTextSize(12.0f);
            this.piechart.setEntryLabelColor(ViewCompat.MEASURED_STATE_MASK);
            this.piechart.setCenterText("TIP PMS INDEX\n" + decimalFormat.format((double) parseFloat) + "%");
            this.piechart.setCenterTextSize(20.0f);
            this.piechart.getDescription().setEnabled(false);
            Legend legend = this.piechart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setDrawInside(false);
            legend.setEnabled(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return inflate;
    }
}
