package bsnl.bsnl_teevra;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.stats.CodePackage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomBaseAdapter2 extends BaseAdapter {
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

    public CustomBaseAdapter2(Context context2, JSONArray jSONArray, int i) {
        this.context = context2;
        this.jsonArray = jSONArray;
        this.layoutInflater = LayoutInflater.from(context2);
        this.startvalue = i;
    }

    public int getCount() {
        return this.jsonArray.length();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        int i2 = i;
        View inflate = this.layoutInflater.inflate(R.layout.custom_pms_notification, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        inflate.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_srno);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_firm);
        TextView textView3 = (TextView) inflate.findViewById(R.id.txt_location);
        TextView textView4 = (TextView) inflate.findViewById(R.id.txt_ip);
        TextView textView5 = (TextView) inflate.findViewById(R.id.txt_interface);
        TextView textView6 = (TextView) inflate.findViewById(R.id.txt_sensor);
        TextView textView7 = (TextView) inflate.findViewById(R.id.txt_alarm_desc);
        TextView textView8 = (TextView) inflate.findViewById(R.id.txt_alarm_raised_at);
        TextView textView9 = (TextView) inflate.findViewById(R.id.txt_alarm_cleared_at);
        TextView textView10 = (TextView) inflate.findViewById(R.id.txt_alarm_status);
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("FMS_FIRM").trim();
            view2 = inflate;
            try {
                String trim2 = jSONObject.getString(CodePackage.LOCATION).trim();
                TextView textView11 = textView10;
                String string = jSONObject.getString("ALARM_DESCRIPTION");
                TextView textView12 = textView9;
                String trim3 = jSONObject.getString("OLT_IP").trim();
                TextView textView13 = textView8;
                String upperCase = jSONObject.getString("INTERFACE").trim().toUpperCase();
                TextView textView14 = textView6;
                String trim4 = jSONObject.getString("SENSOR").trim();
                String trim5 = jSONObject.getString("DURATION").trim();
                String trim6 = jSONObject.getString("ALARM_RAISED_AT").trim();
                String trim7 = jSONObject.getString("ALARM_CLEARED_AT").trim();
                String trim8 = jSONObject.getString("ALARM_STATUS").trim();
                textView.setText(String.valueOf(this.startvalue + i2 + 1));
                textView2.setText(trim);
                textView3.setText("(" + trim2 + ")");
                textView7.setText(Html.fromHtml(string));
                textView4.setText(trim3);
                textView5.setText(upperCase);
                String str = trim4;
                textView14.setText(str);
                textView13.setText(trim6);
                textView12.setText(trim7);
                TextView textView15 = textView11;
                textView15.setText(trim8);
                if (trim8.equals("ACTIVE")) {
                    textView15.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                } else {
                    textView15.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                    if (str.equals("REACHABILITY") || str.equals("OP_STATUS")) {
                        textView7.setText(Html.fromHtml(string + " <br><b>Total DownTime :</b> " + trim5));
                        textView7.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                    }
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
}
