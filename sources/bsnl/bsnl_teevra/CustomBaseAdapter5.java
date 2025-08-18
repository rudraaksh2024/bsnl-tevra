package bsnl.bsnl_teevra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.gms.stats.CodePackage;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomBaseAdapter5 extends BaseAdapter {
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

    public CustomBaseAdapter5(Context context2, JSONArray jSONArray, int i, Activity activity) {
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
        View inflate = this.layoutInflater.inflate(R.layout.custom_olt_reboot, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(10, 10, 10, 0);
        inflate.setLayoutParams(layoutParams);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_srno);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_firm);
        TextView textView3 = (TextView) inflate.findViewById(R.id.txt_location);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_share);
        TextView textView4 = (TextView) inflate.findViewById(R.id.txt_ip);
        TextView textView5 = (TextView) inflate.findViewById(R.id.txt_vlan);
        TextView textView6 = (TextView) inflate.findViewById(R.id.txt_count);
        textView6.setPaintFlags(8);
        TextView textView7 = (TextView) inflate.findViewById(R.id.txt_bbc);
        textView7.setPaintFlags(8);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_reboot_detail);
        TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.table_reboot_detail);
        linearLayout.setVisibility(8);
        textView6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (linearLayout.getVisibility() == 0) {
                    linearLayout.setVisibility(8);
                } else {
                    linearLayout.setVisibility(0);
                }
            }
        });
        try {
            JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
            String trim = jSONObject.getString("FMS_FIRM").trim();
            TableLayout tableLayout2 = tableLayout;
            final String trim2 = jSONObject.getString("OLT_IP").trim();
            ImageView imageView2 = imageView;
            String trim3 = jSONObject.getString("OLT_VLAN").trim();
            View view2 = inflate;
            String trim4 = jSONObject.getString(CodePackage.LOCATION).trim();
            TextView textView8 = textView7;
            String trim5 = jSONObject.getString("REBOOT").trim();
            String trim6 = jSONObject.getString("BBC").trim();
            String trim7 = jSONObject.getString("MOBILE").trim();
            JSONArray jSONArray = jSONObject.getJSONArray("ALARM");
            textView.setText(String.valueOf(this.startvalue + i2 + 1));
            textView2.setText(trim);
            textView3.setText("(" + trim4 + ")");
            textView4.setText(trim2);
            textView5.setText(trim3);
            textView6.setText(trim5);
            TextView textView9 = textView8;
            textView9.setText("BBM DETAIL : " + trim6.toUpperCase());
            final View view3 = view2;
            final String str = trim7;
            textView9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!str.isEmpty()) {
                        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
                        intent.setFlags(268435456);
                        view3.getContext().startActivity(intent);
                    }
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bitmap createBitmap = Bitmap.createBitmap(view3.getWidth(), view3.getHeight(), Bitmap.Config.ARGB_8888);
                    view3.draw(new Canvas(createBitmap));
                    new ShareImage().share(createBitmap, new File(view3.getContext().getExternalCacheDir(), File.separator + "Olt_Reboot(" + trim2 + ").jpg"), CustomBaseAdapter5.this.parentview);
                }
            });
            int i3 = 0;
            while (i3 < jSONArray.length()) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                TableRow tableRow = new TableRow(view3.getContext());
                tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                i3++;
                TextView textView10 = new TextView(view3.getContext());
                textView10.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.0f));
                textView10.setText(Integer.toString(i3));
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setPadding(10, 15, 10, 15);
                textView10.setTextSize(0, view3.getResources().getDimension(R.dimen.smalltext));
                textView10.setBackgroundResource(R.drawable.style17);
                TextView textView11 = new TextView(view3.getContext());
                textView11.setLayoutParams(new TableRow.LayoutParams(0, -1, 7.5f));
                textView11.setText(jSONObject2.getString("ALARM_DESCRIPTION").toUpperCase());
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setPadding(5, 15, 5, 15);
                textView11.setTextSize(0, view3.getResources().getDimension(R.dimen.smalltext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(view3.getContext());
                textView12.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.5f));
                textView12.setText(jSONObject2.getString("TIMESTAMP").toUpperCase());
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setPadding(5, 15, 5, 15);
                textView12.setTextSize(0, view3.getResources().getDimension(R.dimen.smalltext));
                textView12.setBackgroundResource(R.drawable.style17);
                tableRow.addView(textView10);
                tableRow.addView(textView11);
                tableRow.addView(textView12);
                TableLayout tableLayout3 = tableLayout2;
                tableLayout3.addView(tableRow);
                tableLayout2 = tableLayout3;
            }
            return view3;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
