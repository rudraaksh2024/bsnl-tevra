package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.gms.stats.CodePackage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomBaseAdapter1 extends BaseAdapter {
    Context context;
    LayoutInflater inflator;
    JSONArray jsonArray;
    LayoutInflater layoutInflater;
    Activity parent;
    int startvalue;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CustomBaseAdapter1(Context context2, JSONArray jSONArray, int i, Activity activity) {
        this.context = context2;
        this.jsonArray = jSONArray;
        this.layoutInflater = LayoutInflater.from(context2);
        this.inflator = LayoutInflater.from(context2);
        this.startvalue = i;
        this.parent = activity;
    }

    public int getCount() {
        return this.jsonArray.length();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        String str;
        String str2;
        int i2 = i;
        View inflate = this.layoutInflater.inflate(R.layout.custom_pms_tip, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        inflate.setLayoutParams(layoutParams);
        TextView textView6 = (TextView) inflate.findViewById(R.id.txt_srno);
        TextView textView7 = (TextView) inflate.findViewById(R.id.txt_firm);
        TextView textView8 = (TextView) inflate.findViewById(R.id.txt_location);
        TextView textView9 = (TextView) inflate.findViewById(R.id.txt_ip);
        TextView textView10 = (TextView) inflate.findViewById(R.id.txt_vlan);
        TextView textView11 = (TextView) inflate.findViewById(R.id.txt_make);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_alarm);
        TextView textView12 = (TextView) inflate.findViewById(R.id.txt_alarm_major);
        TextView textView13 = (TextView) inflate.findViewById(R.id.txt_uplink);
        TextView textView14 = (TextView) inflate.findViewById(R.id.txt_alarm_minor);
        TextView textView15 = (TextView) inflate.findViewById(R.id.txt_pms);
        TextView textView16 = (TextView) inflate.findViewById(R.id.txt_alarm_normal);
        TextView textView17 = (TextView) inflate.findViewById(R.id.txt_status);
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            view2 = inflate;
            try {
                String trim = jSONObject.getString("FMS_FIRM_NAME").trim();
                TextView textView18 = textView11;
                String trim2 = jSONObject.getString("OLT_IP").trim();
                TextView textView19 = textView10;
                String trim3 = jSONObject.getString("OLT_VLAN").trim();
                TextView textView20 = textView9;
                String trim4 = jSONObject.getString("OLT_MAKE").trim();
                String trim5 = jSONObject.getString(CodePackage.LOCATION).trim();
                TextView textView21 = textView8;
                String string = jSONObject.getString("SNMP_ENABLED");
                String trim6 = jSONObject.getString("UPLINK").trim();
                String trim7 = jSONObject.getString("OLT_STATUS").trim();
                jSONObject.getString("MANAGEABLE").trim();
                if (jSONObject.get("ALARM") instanceof JSONObject) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("ALARM");
                    JSONArray jSONArray = jSONObject2.getJSONArray("MAJOR");
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("MINOR");
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("NORMAL");
                    str2 = trim5;
                    textView12.setText("" + jSONArray.length());
                    textView14.setText("" + jSONArray2.length());
                    textView16.setText("" + jSONArray3.length());
                    str = "(";
                    LinearLayout linearLayout2 = linearLayout;
                    textView2 = textView7;
                    TextView textView22 = textView12;
                    final JSONArray jSONArray4 = jSONArray3;
                    TextView textView23 = textView13;
                    textView = textView6;
                    TextView textView24 = textView14;
                    final String str3 = trim;
                    TextView textView25 = textView16;
                    textView5 = textView15;
                    final String str4 = trim2;
                    textView3 = textView23;
                    textView4 = textView17;
                    final String str5 = trim3;
                    textView25.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            CustomBaseAdapter1.this.alarm_normal(jSONArray4, str3, str4, str5, "NORMAL");
                        }
                    });
                    final JSONArray jSONArray5 = jSONArray;
                    final String str6 = trim;
                    final String str7 = trim2;
                    final String str8 = trim3;
                    textView22.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            CustomBaseAdapter1.this.alarm_normal(jSONArray5, str6, str7, str8, "MAJOR");
                        }
                    });
                    final JSONArray jSONArray6 = jSONArray2;
                    final String str9 = trim;
                    final String str10 = trim2;
                    final String str11 = trim3;
                    textView24.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            CustomBaseAdapter1.this.alarm_normal(jSONArray6, str9, str10, str11, "MINOR");
                        }
                    });
                    linearLayout2.setVisibility(0);
                } else {
                    textView3 = textView13;
                    str = "(";
                    textView = textView6;
                    str2 = trim5;
                    textView5 = textView15;
                    textView4 = textView17;
                    LinearLayout linearLayout3 = linearLayout;
                    textView2 = textView7;
                    linearLayout3.setVisibility(8);
                }
                textView.setText(String.valueOf(this.startvalue + i + 1));
                textView2.setText(trim);
                textView21.setText(str + str2 + ")");
                textView20.setText(trim2);
                textView19.setText(trim3);
                textView18.setText(trim4);
                String str12 = trim7;
                TextView textView26 = textView4;
                textView26.setText(str12);
                if (str12.equals("ONLINE")) {
                    textView26.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView26.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                String str13 = string;
                TextView textView27 = textView5;
                textView27.setText(str13);
                if (str13.equals("CONFIGURED")) {
                    textView27.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView27.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                TextView textView28 = textView3;
                textView28.setText(trim6.toUpperCase());
                if (trim6.equals("N/A")) {
                    textView28.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                } else {
                    textView28.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                }
            } catch (JSONException e) {
                e = e;
                e.printStackTrace();
                return view2;
            }
        } catch (JSONException e2) {
            e = e2;
            view2 = inflate;
            e.printStackTrace();
            return view2;
        }
        return view2;
    }

    /* access modifiers changed from: private */
    public void alarm_normal(JSONArray jSONArray, String str, String str2, String str3, String str4) {
        String str5 = str4;
        if (jSONArray.length() > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.parent);
            View inflate = this.inflator.inflate(R.layout.custom_pms_alarm, (ViewGroup) null);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_header);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_firm);
            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_alarm1);
            TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.tbl_alarm);
            int i = 0;
            builder.setCancelable(false);
            builder.setNegativeButton("OK", (DialogInterface.OnClickListener) null);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            try {
                if (str5.equals("MAJOR")) {
                    linearLayout.setBackgroundResource(R.drawable.new_style1_3);
                } else if (str5.equals("MINOR")) {
                    linearLayout.setBackgroundResource(R.drawable.new_style1_5);
                } else {
                    linearLayout.setBackgroundResource(R.drawable.new_style1_4);
                }
                textView.setText(str);
                textView2.setText("(OLT IP : " + str2 + " | OLT VLAN : " + str3 + ")");
                int i2 = 0;
                while (i2 < jSONArray.length()) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    TableRow tableRow = new TableRow(this.parent.getApplicationContext());
                    tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                    TextView textView3 = new TextView(this.parent.getApplicationContext());
                    textView3.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                    i2++;
                    textView3.setText(Integer.toString(i2));
                    textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView3.setGravity(17);
                    textView3.setPadding(15, 15, 15, 15);
                    textView3.setTextSize(i, this.context.getResources().getDimension(R.dimen.smalltext));
                    textView3.setTypeface(Typeface.DEFAULT_BOLD);
                    textView3.setBackgroundResource(R.drawable.style17);
                    TextView textView4 = new TextView(this.parent.getApplication());
                    textView4.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                    textView4.setText(jSONObject.getString("SENSOR"));
                    textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView4.setGravity(3);
                    textView4.setPadding(15, 15, 15, 15);
                    textView4.setTextSize(i, this.context.getResources().getDimension(R.dimen.smalltext));
                    textView4.setBackgroundResource(R.drawable.style17);
                    TextView textView5 = new TextView(this.parent.getApplication());
                    textView5.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                    textView5.setText(jSONObject.getString("LASTVALUE"));
                    textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView5.setGravity(17);
                    textView5.setPadding(15, 15, 15, 15);
                    textView5.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    textView5.setBackgroundResource(R.drawable.style17);
                    TextView textView6 = new TextView(this.parent.getApplication());
                    textView6.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                    textView6.setText(jSONObject.getString("MESSAGE"));
                    textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView6.setGravity(17);
                    textView6.setPadding(15, 15, 15, 15);
                    textView6.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    textView6.setBackgroundResource(R.drawable.style17);
                    TextView textView7 = new TextView(this.parent.getApplication());
                    textView7.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                    textView7.setText(jSONObject.getString("TIMESTAMP"));
                    textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView7.setGravity(17);
                    textView7.setPadding(15, 15, 15, 15);
                    textView7.setTextSize(0, this.context.getResources().getDimension(R.dimen.smalltext));
                    textView7.setBackgroundResource(R.drawable.style17);
                    tableRow.addView(textView3);
                    tableRow.addView(textView4);
                    tableRow.addView(textView5);
                    tableRow.addView(textView6);
                    tableRow.addView(textView7);
                    tableLayout.addView(tableRow);
                    i = 0;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            create.show();
            return;
        }
        new AlertHelperclass().ntoast("No Active Normal Alarm", this.context.getApplicationContext());
    }
}
