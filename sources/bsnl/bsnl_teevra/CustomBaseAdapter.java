package bsnl.bsnl_teevra;

import android.content.Context;
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

public class CustomBaseAdapter extends BaseAdapter {
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

    public CustomBaseAdapter(Context context2, JSONArray jSONArray, int i) {
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
        View inflate = this.layoutInflater.inflate(R.layout.custom_pms_setting_tip1, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        inflate.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_srno);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_firm);
        TextView textView3 = (TextView) inflate.findViewById(R.id.txt_location);
        TextView textView4 = (TextView) inflate.findViewById(R.id.txt_ip);
        TextView textView5 = (TextView) inflate.findViewById(R.id.txt_vlan);
        TextView textView6 = (TextView) inflate.findViewById(R.id.txt_make);
        TextView textView7 = (TextView) inflate.findViewById(R.id.txt_status);
        TextView textView8 = (TextView) inflate.findViewById(R.id.txt_pms);
        TextView textView9 = (TextView) inflate.findViewById(R.id.txt_uplink);
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("FMS_FIRM_NAME").trim();
            String trim2 = jSONObject.getString("OLT_IP").trim();
            view2 = inflate;
            try {
                String trim3 = jSONObject.getString("OLT_VLAN").trim();
                TextView textView10 = textView9;
                String trim4 = jSONObject.getString("OLT_MAKE").trim();
                TextView textView11 = textView8;
                String trim5 = jSONObject.getString(CodePackage.LOCATION).trim();
                String string = jSONObject.getString("SNMP_ENABLED");
                String trim6 = jSONObject.getString("UPLINK").trim();
                String trim7 = jSONObject.getString("OLT_STATUS").trim();
                jSONObject.getString("MANAGEABLE").trim();
                textView.setText(String.valueOf(this.startvalue + i2 + 1));
                textView2.setText(trim);
                textView3.setText("(" + trim5 + ")");
                textView4.setText(trim2);
                textView5.setText(trim3);
                textView6.setText(trim4);
                TextView textView12 = textView7;
                String str = trim7;
                textView12.setText(str);
                if (str.equals("ONLINE")) {
                    textView12.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView12.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                TextView textView13 = textView11;
                String str2 = string;
                textView13.setText(str2);
                if (str2.equals("CONFIGURED")) {
                    textView13.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView13.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                TextView textView14 = textView10;
                textView14.setText(trim6.toUpperCase());
                if (trim6.equals("N/A")) {
                    textView14.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                } else {
                    textView14.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
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
