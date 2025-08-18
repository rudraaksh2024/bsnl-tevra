package bsnl.bsnl_teevra.fms;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import bsnl.bsnl_teevra.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseAdapter_TR069 extends BaseAdapter {
    Context context;
    JSONArray jsonArray;
    LayoutInflater layoutInflater;
    int startvalue;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public BaseAdapter_TR069(Context context2, JSONArray jSONArray, int i) {
        this.context = context2;
        this.jsonArray = jSONArray;
        this.layoutInflater = LayoutInflater.from(context2);
        this.startvalue = i;
    }

    public int getCount() {
        return this.jsonArray.length();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = i;
        View inflate = this.layoutInflater.inflate(R.layout.custom_bulk_tr069, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        inflate.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_pon);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_table);
        TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.table);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.lay_remark);
        ((TextView) inflate.findViewById(R.id.txt_srno)).setText(String.valueOf(this.startvalue + i2 + 1));
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("PON_PORT").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("WAN");
            JSONArray jSONArray2 = jSONObject.getJSONArray("REMARK");
            textView.setText(trim);
            if (jSONArray.length() == 0) {
                linearLayout.setVisibility(8);
            } else {
                tableLayout.removeAllViews();
                TableRow tableRow = new TableRow(this.context);
                tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                TextView textView2 = new TextView(this.context);
                textView2.setText("SR");
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setGravity(17);
                textView2.setPadding(20, 10, 20, 10);
                textView2.setTypeface((Typeface) null, 1);
                textView2.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                TextView textView3 = new TextView(this.context);
                textView3.setText("VLAN");
                textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView3.setGravity(17);
                textView3.setPadding(20, 10, 20, 10);
                textView3.setTypeface((Typeface) null, 1);
                textView3.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                TextView textView4 = new TextView(this.context);
                textView4.setText("INTERFACE");
                textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView4.setGravity(17);
                textView4.setPadding(20, 10, 20, 10);
                textView4.setTypeface((Typeface) null, 1);
                textView4.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                TextView textView5 = new TextView(this.context);
                textView5.setText("SERVICES");
                textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView5.setGravity(17);
                textView5.setPadding(20, 10, 20, 10);
                textView5.setTypeface((Typeface) null, 1);
                textView5.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                TextView textView6 = new TextView(this.context);
                textView6.setText("MAC-ADDRESS");
                textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView6.setGravity(17);
                textView6.setPadding(20, 10, 20, 10);
                textView6.setTypeface((Typeface) null, 1);
                textView6.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                TextView textView7 = new TextView(this.context);
                textView7.setText("STATUS");
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setPadding(20, 10, 20, 10);
                textView7.setTypeface((Typeface) null, 1);
                textView7.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                tableRow.addView(textView2);
                tableRow.addView(textView3);
                tableRow.addView(textView4);
                tableRow.addView(textView5);
                tableRow.addView(textView6);
                tableRow.addView(textView7);
                tableLayout.addView(tableRow);
                int i3 = 0;
                while (i3 < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    TableRow tableRow2 = new TableRow(this.context);
                    tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                    TextView textView8 = new TextView(this.context);
                    int i4 = i3 + 1;
                    textView8.setText(Integer.toString(i4));
                    textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView8.setGravity(17);
                    textView8.setPadding(20, 10, 20, 10);
                    textView8.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    TextView textView9 = new TextView(this.context);
                    textView9.setText(jSONObject2.getString("vlan"));
                    textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView9.setGravity(17);
                    textView9.setPadding(20, 10, 20, 10);
                    textView9.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    TextView textView10 = new TextView(this.context);
                    textView10.setText(jSONObject2.getString("wan_name").toUpperCase());
                    textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView10.setGravity(17);
                    textView10.setPadding(20, 10, 20, 10);
                    textView10.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    TextView textView11 = new TextView(this.context);
                    textView11.setText(jSONObject2.getString("wan_mode").toUpperCase());
                    textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView11.setGravity(17);
                    textView11.setPadding(20, 10, 20, 10);
                    textView11.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    TextView textView12 = new TextView(this.context);
                    textView12.setText(jSONObject2.getString("mac").toUpperCase());
                    textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView12.setGravity(17);
                    int i5 = i4;
                    textView12.setPadding(20, 10, 20, 10);
                    textView12.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    TextView textView13 = new TextView(this.context);
                    textView13.setText(jSONObject2.getString(NotificationCompat.CATEGORY_STATUS).toUpperCase());
                    if (jSONObject2.getString(NotificationCompat.CATEGORY_STATUS).toLowerCase().equals("connected")) {
                        textView13.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                    } else {
                        textView13.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                    }
                    textView13.setGravity(17);
                    textView13.setTypeface((Typeface) null, 1);
                    textView13.setPadding(20, 10, 20, 10);
                    textView13.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    tableRow2.addView(textView8);
                    tableRow2.addView(textView9);
                    tableRow2.addView(textView10);
                    tableRow2.addView(textView11);
                    tableRow2.addView(textView12);
                    tableRow2.addView(textView13);
                    tableLayout.addView(tableRow2);
                    i3 = i5;
                }
            }
            linearLayout2.removeAllViews();
            for (int i6 = 0; i6 < jSONArray2.length(); i6++) {
                TextView textView14 = new TextView(this.context);
                textView14.setText(Html.fromHtml(jSONArray2.getString(i6), 63));
                textView14.setTypeface((Typeface) null, 1);
                textView14.setPadding(15, 0, 15, 0);
                textView14.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                linearLayout2.addView(textView14);
            }
            return inflate;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
