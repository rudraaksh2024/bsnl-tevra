package bsnl.bsnl_teevra;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;
import com.google.android.gms.stats.CodePackage;
import java.text.DecimalFormat;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class PMS_Tip_Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private LinearLayout lay_pms_display;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_tip_detail_activity);
        this.lay_pms_display = (LinearLayout) findViewById(R.id.lay_pms_display);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_header);
        this.imageView = imageView2;
        imageView2.setOnClickListener(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        String stringExtra = getIntent().getStringExtra("CIRCLE");
        String stringExtra2 = getIntent().getStringExtra("SSA");
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("RESPONSE"));
            String string = jSONObject.getString("FMS_FIRM_NAME");
            String string2 = jSONObject.getString("OLT_IP");
            String string3 = jSONObject.getString("OLT_VLAN");
            String string4 = jSONObject.getString("OLT_MAKE");
            String string5 = jSONObject.getString("NAS_IP");
            String string6 = jSONObject.getString("TIMESTAMP");
            JSONObject jSONObject2 = jSONObject.getJSONObject("TRAFFIC");
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            String str4 = "PON_TRAFFIC";
            layoutParams.setMargins(0, 10, 0, 5);
            View inflate = getLayoutInflater().inflate(R.layout.custom_pms_tip_detail, (ViewGroup) null);
            inflate.setLayoutParams(layoutParams);
            String str5 = string5;
            String str6 = stringExtra2;
            String str7 = stringExtra;
            int i2 = i;
            ((TextView) inflate.findViewById(R.id.txt_description)).setText(string);
            ((TextView) inflate.findViewById(R.id.txt_location)).setText("(" + jSONObject.getString(CodePackage.LOCATION) + ")");
            ((TextView) inflate.findViewById(R.id.txt_ip)).setText(string2);
            ((TextView) inflate.findViewById(R.id.txt_vlan)).setText(string3);
            ((TextView) inflate.findViewById(R.id.txt_make)).setText(string4);
            ((TextView) inflate.findViewById(R.id.txt_ping)).setText(jSONObject2.getString("PING"));
            ((TextView) inflate.findViewById(R.id.txt_systime)).setText(jSONObject2.getString("SYS_UPTIME"));
            ((TextView) inflate.findViewById(R.id.txt_sampledtime)).setText(string6);
            LinearLayout.LayoutParams layoutParams2 = layoutParams;
            layoutParams2.setMargins(0, 5, 0, 5);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.setMargins(0, 0, 0, 0);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(layoutParams2);
            linearLayout.setPadding(0, 0, 0, 20);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setBackgroundResource(R.drawable.new_style);
            TextView textView = new TextView(this);
            textView.setLayoutParams(layoutParams3);
            textView.setText("UPLINK PERFORMANCE");
            textView.setTextColor(-1);
            textView.setGravity(17);
            textView.setPadding(15, 15, 15, 15);
            textView.setTypeface((Typeface) null, 1);
            textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView.setBackgroundResource(R.drawable.new_style1_1);
            linearLayout.addView(textView);
            JSONObject jSONObject3 = jSONObject2.getJSONObject("UPLINK_TRAFFIC");
            Iterator<String> keys = jSONObject3.keys();
            while (keys.hasNext()) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject(keys.next());
                LinearLayout.LayoutParams layoutParams4 = layoutParams2;
                LinearLayout linearLayout2 = new LinearLayout(this);
                JSONObject jSONObject5 = jSONObject3;
                Iterator<String> it = keys;
                View view = inflate;
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, i2 / 2));
                linearLayout2.setOrientation(1);
                linearLayout2.setGravity(17);
                linearLayout2.setPadding(0, 10, 0, 0);
                TextView textView2 = new TextView(this);
                textView2.setLayoutParams(layoutParams3);
                if (jSONObject4.getString("OP_STATUS").equals("1")) {
                    textView2.setText(Html.fromHtml("UPLINK INTERFACE : " + jSONObject4.getString("DESC").toUpperCase() + "<font color='#00802b'> (Up)</font>"));
                } else {
                    textView2.setText(Html.fromHtml("UPLINK INTERFACE : " + jSONObject4.getString("DESC").toUpperCase() + "<font color='red'> (Down)</font>"));
                }
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setGravity(17);
                textView2.setPadding(0, 5, 0, 15);
                textView2.setTypeface((Typeface) null, 1);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                linearLayout2.addView(textView2);
                linearLayout2.addView(getgauge_big(jSONObject4.getString("IN_TRAFFIC"), jSONObject4.getString("OUT_TRAFFIC")));
                linearLayout.addView(linearLayout2);
                layoutParams2 = layoutParams4;
                jSONObject3 = jSONObject5;
                keys = it;
                inflate = view;
                boolean z = R.dimen.mediumtext;
            }
            LinearLayout.LayoutParams layoutParams5 = layoutParams2;
            AnonymousClass1 r0 = r1;
            String str8 = "DESC";
            String str9 = "<font color='#00802b'> (Up)</font>";
            final String str10 = str7;
            final String str11 = str6;
            String str12 = "<font color='red'> (Down)</font>";
            final String str13 = string2;
            String str14 = string2;
            final String str15 = string3;
            String str16 = "OP_STATUS";
            String str17 = string3;
            LinearLayout.LayoutParams layoutParams6 = layoutParams5;
            final String str18 = str5;
            AnonymousClass1 r1 = new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(PMS_Tip_Detail_Activity.this, PMS_Tip_Uplink_Activity.class);
                    intent.putExtra("circle", str10);
                    intent.putExtra("ssa", str11);
                    intent.putExtra("olt_ip", str13);
                    intent.putExtra("olt_vlan", str15);
                    intent.putExtra("nas_ip", str18);
                    PMS_Tip_Detail_Activity.this.startActivity(intent);
                }
            };
            linearLayout.setOnClickListener(r0);
            this.lay_pms_display.addView(inflate);
            this.lay_pms_display.addView(linearLayout);
            LinearLayout linearLayout3 = new LinearLayout(this);
            layoutParams6.setMargins(0, 5, 0, 10);
            linearLayout3.setLayoutParams(layoutParams6);
            linearLayout3.setPadding(0, 0, 0, 20);
            linearLayout3.setOrientation(1);
            int i3 = 17;
            linearLayout3.setGravity(17);
            linearLayout3.setBackgroundResource(R.drawable.new_style);
            TextView textView3 = new TextView(this);
            textView3.setLayoutParams(layoutParams3);
            textView3.setText("PON PERFORMANCE");
            int i4 = -1;
            textView3.setTextColor(-1);
            textView3.setGravity(17);
            textView3.setPadding(15, 15, 15, 15);
            textView3.setTypeface((Typeface) null, 1);
            textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView3.setBackgroundResource(R.drawable.new_style1_1);
            linearLayout3.addView(textView3);
            String str19 = str4;
            if (jSONObject2.get(str19) instanceof JSONObject) {
                JSONObject jSONObject6 = jSONObject2.getJSONObject(str19);
                Iterator<String> keys2 = jSONObject6.keys();
                while (keys2.hasNext()) {
                    JSONObject jSONObject7 = jSONObject6.getJSONObject(keys2.next());
                    LinearLayout linearLayout4 = new LinearLayout(this);
                    linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(i4, i2 / 2));
                    linearLayout4.setOrientation(1);
                    linearLayout4.setGravity(i3);
                    linearLayout4.setPadding(0, 10, 0, 0);
                    TextView textView4 = new TextView(this);
                    textView4.setLayoutParams(layoutParams3);
                    String str20 = str16;
                    if (jSONObject7.getString(str20).equals("1")) {
                        str = str8;
                        str2 = str9;
                        textView4.setText(Html.fromHtml("PON INTERFACE : " + jSONObject7.getString(str).toUpperCase() + str2));
                        str3 = str12;
                    } else {
                        str = str8;
                        str2 = str9;
                        str3 = str12;
                        textView4.setText(Html.fromHtml("PON INTERFACE : " + jSONObject7.getString(str).toUpperCase() + str3));
                    }
                    textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView4.setGravity(i3);
                    textView4.setPadding(15, 5, 15, 15);
                    textView4.setTypeface((Typeface) null, 1);
                    textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                    linearLayout4.addView(textView4);
                    linearLayout4.addView(getgauge_big(jSONObject7.getString("IN_TRAFFIC"), jSONObject7.getString("OUT_TRAFFIC")));
                    linearLayout3.addView(linearLayout4);
                    str12 = str3;
                    str9 = str2;
                    str8 = str;
                    str16 = str20;
                    i3 = 17;
                    i4 = -1;
                }
                final String str21 = str7;
                final String str22 = str6;
                final String str23 = str14;
                final String str24 = str17;
                final String str25 = str5;
                linearLayout3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(PMS_Tip_Detail_Activity.this, PMS_Tip_Pon_Activity.class);
                        intent.putExtra("circle", str21);
                        intent.putExtra("ssa", str22);
                        intent.putExtra("olt_ip", str23);
                        intent.putExtra("olt_vlan", str24);
                        intent.putExtra("nas_ip", str25);
                        PMS_Tip_Detail_Activity.this.startActivity(intent);
                    }
                });
                this.lay_pms_display.addView(linearLayout3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private View getgauge_big(String str, String str2) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###.##");
        int parseInt = Integer.parseInt(str.trim());
        int parseInt2 = Integer.parseInt(str2.trim());
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_gauge, (ViewGroup) null);
        Range range = new Range();
        range.setColor(ContextCompat.getColor(this, R.color.colorGreen));
        range.setFrom(0.0d);
        range.setTo(700000.0d);
        Range range2 = new Range();
        range2.setColor(ContextCompat.getColor(this, R.color.colorOrange));
        range2.setFrom(700000.0d);
        range2.setTo(850000.0d);
        Range range3 = new Range();
        range3.setColor(ContextCompat.getColor(this, R.color.colorRed));
        range3.setFrom(700000.0d);
        range3.setTo(850000.0d);
        ArcGauge arcGauge = (ArcGauge) inflate.findViewById(R.id.gauge_in);
        arcGauge.addRange(range);
        arcGauge.addRange(range2);
        arcGauge.addRange(range3);
        arcGauge.setMinValue(0.0d);
        arcGauge.setMaxValue(1000000.0d);
        arcGauge.setValue((double) parseInt);
        arcGauge.setUseRangeBGColor(true);
        arcGauge.setFormatter(new ValueFormatter() {
            public String getFormattedValue(double d) {
                return "";
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_in)).setText(decimalFormat.format((long) parseInt) + " Kbps");
        ArcGauge arcGauge2 = (ArcGauge) inflate.findViewById(R.id.gauge_out);
        arcGauge2.addRange(range);
        arcGauge2.addRange(range2);
        arcGauge2.addRange(range3);
        arcGauge2.setMinValue(0.0d);
        arcGauge2.setMaxValue(1000000.0d);
        arcGauge2.setValue((double) parseInt2);
        arcGauge2.setUseRangeBGColor(true);
        arcGauge2.setValueColor(ContextCompat.getColor(this, R.color.colorGreen));
        arcGauge2.setFormatter(new ValueFormatter() {
            public String getFormattedValue(double d) {
                return "";
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_out)).setText(decimalFormat.format((long) parseInt2) + " Kbps");
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        }
    }
}
