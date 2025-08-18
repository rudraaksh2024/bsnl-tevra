package bsnl.bsnl_teevra;

import android.app.Activity;
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

public class CustomBaseAdapter4 extends BaseAdapter {
    Context context;
    LayoutInflater inflator;
    JSONArray jsonArray;
    LayoutInflater layoutInflater;
    Activity parent;
    Activity parentview;
    int startvalue;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CustomBaseAdapter4(Context context2, JSONArray jSONArray, int i, Activity activity) {
        this.context = context2;
        this.jsonArray = jSONArray;
        this.layoutInflater = LayoutInflater.from(context2);
        this.inflator = LayoutInflater.from(context2);
        this.startvalue = i;
        this.parent = activity;
        this.parentview = activity;
    }

    public int getCount() {
        return this.jsonArray.length();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = i;
        View inflate = this.layoutInflater.inflate(R.layout.custom_peak_traffic, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 10);
        inflate.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_srno);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_firm);
        TextView textView3 = (TextView) inflate.findViewById(R.id.txt_location);
        TextView textView4 = (TextView) inflate.findViewById(R.id.txt_ip);
        TextView textView5 = (TextView) inflate.findViewById(R.id.txt_vlan);
        TextView textView6 = (TextView) inflate.findViewById(R.id.txt_make);
        TextView textView7 = (TextView) inflate.findViewById(R.id.txt_conn);
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("FMS_FIRM_NAME").trim();
            String trim2 = jSONObject.getString(CodePackage.LOCATION).trim();
            String trim3 = jSONObject.getString("OLT_IP").trim();
            String trim4 = jSONObject.getString("OLT_VLAN").trim();
            View view2 = inflate;
            jSONObject.getString("NAS_IP").trim();
            String trim5 = jSONObject.getString("OLT_MAKE").trim();
            String string = jSONObject.getString("CONNECTION");
            jSONObject.getString("STATUS").trim();
            textView.setText(String.valueOf(this.startvalue + i2 + 1));
            textView2.setText(trim);
            textView3.setText("(" + trim2 + ")");
            textView4.setText(trim3);
            textView5.setText(trim4);
            textView6.setText(trim5);
            TextView textView8 = textView7;
            textView8.setText(string);
            textView8.setPaintFlags(8);
            return view2;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
