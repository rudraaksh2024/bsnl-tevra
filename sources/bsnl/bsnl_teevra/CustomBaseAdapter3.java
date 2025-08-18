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

public class CustomBaseAdapter3 extends BaseAdapter {
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

    public CustomBaseAdapter3(Context context2, JSONArray jSONArray, int i) {
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
        View inflate = this.layoutInflater.inflate(R.layout.custom_bulk_power, (ViewGroup) null);
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
        TextView textView8 = (TextView) inflate.findViewById(R.id.txt_manageable);
        TextView textView9 = (TextView) inflate.findViewById(R.id.txt_login);
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("FMS_FIRM_NAME").trim();
            String trim2 = jSONObject.getString(CodePackage.LOCATION).trim();
            view2 = inflate;
            try {
                String trim3 = jSONObject.getString("OLT_IP").trim();
                TextView textView10 = textView9;
                String trim4 = jSONObject.getString("OLT_VLAN").trim();
                TextView textView11 = textView8;
                jSONObject.getString("NAS_IP").trim();
                String trim5 = jSONObject.getString("OLT_MAKE").trim();
                TextView textView12 = textView7;
                String trim6 = jSONObject.getString("OLT_STATUS").trim();
                String trim7 = jSONObject.getString("MANAGEABLE").trim();
                String trim8 = jSONObject.getString("LOGIN").trim();
                textView.setText(String.valueOf(this.startvalue + i2 + 1));
                textView2.setText(trim);
                textView3.setText("(" + trim2 + ")");
                textView4.setText(trim3);
                textView5.setText(trim4);
                textView6.setText(trim5);
                TextView textView13 = textView12;
                String str = trim6;
                textView13.setText(str);
                if (str.equals("ONLINE")) {
                    textView13.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView13.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                TextView textView14 = textView11;
                String str2 = trim7;
                textView14.setText(str2);
                if (str2.equals("MANAGED")) {
                    textView14.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
                } else {
                    textView14.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                }
                TextView textView15 = textView10;
                textView15.setText(trim8.toUpperCase());
                if (trim8.equals("PENDING")) {
                    textView15.setTextColor(this.context.getResources().getColor(R.color.colorRed));
                } else {
                    textView15.setTextColor(this.context.getResources().getColor(R.color.colorGreen));
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
