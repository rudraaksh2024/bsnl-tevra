package bsnl.bsnl_teevra;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.ekn.gruzer.gaugelibrary.contract.ValueFormatter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tr069_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    /* access modifiers changed from: private */
    public String Pref_fms_username;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_telephone;
    /* access modifiers changed from: private */
    public ArcGauge gauge_mem;
    /* access modifiers changed from: private */
    public ArcGauge gauge_rxpower;
    private ImageView img_header;
    /* access modifiers changed from: private */
    public LinearLayout lay_detail;
    /* access modifiers changed from: private */
    public LinearLayout lay_deviceinfo;
    /* access modifiers changed from: private */
    public LinearLayout lay_waninfo;
    /* access modifiers changed from: private */
    public LinearLayout lay_wlaninfo;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_deviceinfo;
    private TextView txt_header;
    private TextView txt_hv1;
    /* access modifiers changed from: private */
    public TextView txt_hv2;
    private TextView txt_lastreport1;
    /* access modifiers changed from: private */
    public TextView txt_lastreport2;
    private TextView txt_mac1;
    /* access modifiers changed from: private */
    public TextView txt_mac2;
    private TextView txt_mem1;
    /* access modifiers changed from: private */
    public TextView txt_mem2;
    /* access modifiers changed from: private */
    public TextView txt_mem3;
    /* access modifiers changed from: private */
    public TextView txt_mem4;
    private TextView txt_mfr1;
    /* access modifiers changed from: private */
    public TextView txt_mfr2;
    private TextView txt_model1;
    /* access modifiers changed from: private */
    public TextView txt_model2;
    private TextView txt_nextreport1;
    /* access modifiers changed from: private */
    public TextView txt_nextreport2;
    private TextView txt_phn1;
    /* access modifiers changed from: private */
    public TextView txt_phn2;
    private TextView txt_rxpower1;
    /* access modifiers changed from: private */
    public TextView txt_rxpower2;
    private TextView txt_sno1;
    /* access modifiers changed from: private */
    public TextView txt_sno2;
    private TextView txt_sv1;
    /* access modifiers changed from: private */
    public TextView txt_sv2;
    /* access modifiers changed from: private */
    public TextView txt_waninfo;
    /* access modifiers changed from: private */
    public TextView txt_wlaninfo;
    private AlertDialog uplink_dialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_tr069);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate);
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        this.img_header = (ImageView) findViewById(R.id.img_header);
        this.et_telephone = (EditText) findViewById(R.id.et_telephone);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_detail);
        this.lay_detail = linearLayout;
        linearLayout.setVisibility(8);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        TextView textView = (TextView) findViewById(R.id.txt_phn1);
        this.txt_phn1 = textView;
        textView.setPaintFlags(8);
        this.txt_phn2 = (TextView) findViewById(R.id.txt_phn2);
        TextView textView2 = (TextView) findViewById(R.id.txt_mac1);
        this.txt_mac1 = textView2;
        textView2.setPaintFlags(8);
        this.txt_mac2 = (TextView) findViewById(R.id.txt_mac2);
        TextView textView3 = (TextView) findViewById(R.id.txt_lastreport1);
        this.txt_lastreport1 = textView3;
        textView3.setPaintFlags(8);
        this.txt_lastreport2 = (TextView) findViewById(R.id.txt_lastreport2);
        TextView textView4 = (TextView) findViewById(R.id.txt_nextreport1);
        this.txt_nextreport1 = textView4;
        textView4.setPaintFlags(8);
        this.txt_nextreport2 = (TextView) findViewById(R.id.txt_nextreport2);
        TextView textView5 = (TextView) findViewById(R.id.txt_mem1);
        this.txt_mem1 = textView5;
        textView5.setPaintFlags(8);
        this.txt_mem2 = (TextView) findViewById(R.id.txt_mem2);
        this.txt_mem3 = (TextView) findViewById(R.id.txt_mem3);
        this.txt_mem4 = (TextView) findViewById(R.id.txt_mem4);
        TextView textView6 = (TextView) findViewById(R.id.txt_rxpower1);
        this.txt_rxpower1 = textView6;
        textView6.setPaintFlags(8);
        this.txt_rxpower2 = (TextView) findViewById(R.id.txt_rxpower2);
        this.gauge_mem = (ArcGauge) findViewById(R.id.gauge_mem);
        this.gauge_rxpower = (ArcGauge) findViewById(R.id.gauge_rxpower);
        this.txt_waninfo = (TextView) findViewById(R.id.txt_waninfo);
        this.lay_waninfo = (LinearLayout) findViewById(R.id.lay_waninfo);
        this.txt_waninfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Tr069_Activity.this.lay_waninfo.getVisibility() == 0) {
                    Tr069_Activity tr069_Activity = Tr069_Activity.this;
                    tr069_Activity.layout_gone(tr069_Activity.lay_waninfo);
                    Tr069_Activity.this.txt_waninfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_more, 0);
                    return;
                }
                Tr069_Activity tr069_Activity2 = Tr069_Activity.this;
                tr069_Activity2.layout_visible(tr069_Activity2.lay_waninfo);
                Tr069_Activity.this.txt_waninfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_less, 0);
            }
        });
        this.txt_wlaninfo = (TextView) findViewById(R.id.txt_wlaninfo);
        this.lay_wlaninfo = (LinearLayout) findViewById(R.id.lay_wlaninfo);
        this.txt_wlaninfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Tr069_Activity.this.lay_wlaninfo.getVisibility() == 0) {
                    Tr069_Activity tr069_Activity = Tr069_Activity.this;
                    tr069_Activity.layout_gone(tr069_Activity.lay_wlaninfo);
                    Tr069_Activity.this.txt_wlaninfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_more, 0);
                    return;
                }
                Tr069_Activity tr069_Activity2 = Tr069_Activity.this;
                tr069_Activity2.layout_visible(tr069_Activity2.lay_wlaninfo);
                Tr069_Activity.this.txt_wlaninfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_less, 0);
            }
        });
        this.txt_deviceinfo = (TextView) findViewById(R.id.txt_deviceinfo);
        this.lay_deviceinfo = (LinearLayout) findViewById(R.id.lay_deviceinfo);
        TextView textView7 = (TextView) findViewById(R.id.txt_mfr1);
        this.txt_mfr1 = textView7;
        textView7.setPaintFlags(8);
        this.txt_mfr2 = (TextView) findViewById(R.id.txt_mfr2);
        TextView textView8 = (TextView) findViewById(R.id.txt_model1);
        this.txt_model1 = textView8;
        textView8.setPaintFlags(8);
        this.txt_model2 = (TextView) findViewById(R.id.txt_model2);
        TextView textView9 = (TextView) findViewById(R.id.txt_sno1);
        this.txt_sno1 = textView9;
        textView9.setPaintFlags(8);
        this.txt_sno2 = (TextView) findViewById(R.id.txt_sno2);
        TextView textView10 = (TextView) findViewById(R.id.txt_sv1);
        this.txt_sv1 = textView10;
        textView10.setPaintFlags(8);
        this.txt_sv2 = (TextView) findViewById(R.id.txt_sv2);
        TextView textView11 = (TextView) findViewById(R.id.txt_hv1);
        this.txt_hv1 = textView11;
        textView11.setPaintFlags(8);
        this.txt_hv2 = (TextView) findViewById(R.id.txt_hv2);
        this.txt_deviceinfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Tr069_Activity.this.lay_deviceinfo.getVisibility() == 0) {
                    Tr069_Activity tr069_Activity = Tr069_Activity.this;
                    tr069_Activity.layout_gone(tr069_Activity.lay_deviceinfo);
                    Tr069_Activity.this.txt_deviceinfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_more, 0);
                    return;
                }
                Tr069_Activity tr069_Activity2 = Tr069_Activity.this;
                tr069_Activity2.layout_visible(tr069_Activity2.lay_deviceinfo);
                Tr069_Activity.this.txt_deviceinfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_new_less, 0);
            }
        });
        this.btn_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.btn_submit) {
            layout_gone(this.lay_detail);
            final String trim = this.et_telephone.getText().toString().trim();
            if (trim.isEmpty()) {
                new AlertHelperclass().ntoast("Please Enter A Valid Telephone Number", getApplicationContext());
                return;
            }
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            this.lay_waninfo.removeAllViews();
            this.lay_wlaninfo.removeAllViews();
            this.progress_dialog.show();
            if (this.Pref_Designation.equals("FRANCHISEE OWNER") || this.Pref_Designation.equals("FRANCHISEE MEMBER")) {
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass6 r1 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_phone_validate_new.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        try {
                            if (new JSONObject(str).getString("STATUS").equals("TRUE")) {
                                Tr069_Activity.this.tr069_detail(trim);
                                return;
                            }
                            Tr069_Activity.this.progress_dialog.cancel();
                            Tr069_Activity.this.txt_alert.setText("Access Restricted !!!\nThe Telephone No '" + trim + "' Doesn't Belongs To Your Work Group");
                            Tr069_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Tr069_Activity.this.progress_dialog.cancel();
                        Tr069_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Tr069_Activity.this.getApplicationContext()));
                        Tr069_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone_no", trim);
                        hashMap.put("fms_userid", Tr069_Activity.this.Pref_fms_username);
                        hashMap.put("circle", Tr069_Activity.this.Pref_Circle);
                        hashMap.put("ssa", Tr069_Activity.this.Pref_SSA);
                        hashMap.put("username", Tr069_Activity.this.Pref_Username);
                        hashMap.put("random_key", Tr069_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Tr069_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r1);
                return;
            }
            tr069_detail(trim);
        }
    }

    /* access modifiers changed from: private */
    public void tr069_detail(String str) {
        this.txt_header.setText("BRIDLE TR069 ATTRIBUTES OF " + str);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str2 = str;
        AnonymousClass9 r1 = new StringRequest(1, getString(R.string.serverip) + "tr069.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                AnonymousClass7 r0 = this;
                Tr069_Activity.this.progress_dialog.cancel();
                Log.v("RESPONSE", str.toString());
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("SUCCESS")).booleanValue()) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("INFO");
                        JSONObject jSONObject3 = jSONObject.getJSONObject("DEVICE");
                        Tr069_Activity.this.txt_phn2.setText(jSONObject2.getString("PHN"));
                        Tr069_Activity.this.txt_mac2.setText(Html.fromHtml(jSONObject2.getString("MAC"), 63));
                        Tr069_Activity.this.txt_lastreport2.setText(jSONObject2.getString("REPORT"));
                        Tr069_Activity.this.txt_nextreport2.setText(jSONObject2.getString("N_REPORT"));
                        if (jSONObject2.getBoolean("SYNC")) {
                            Tr069_Activity.this.txt_nextreport2.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorGreen));
                            Tr069_Activity.this.txt_nextreport2.setTypeface(Typeface.DEFAULT_BOLD);
                        } else {
                            Tr069_Activity.this.txt_nextreport2.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorRed));
                            Tr069_Activity.this.txt_nextreport2.setTypeface(Typeface.DEFAULT_BOLD);
                        }
                        int i = jSONObject2.getInt("POWER");
                        Range range = new Range();
                        range.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorRed));
                        range.setFrom(-40.0d);
                        range.setTo(-28.0d);
                        Range range2 = new Range();
                        range2.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorOrange));
                        range2.setFrom(-28.0d);
                        range2.setTo(-25.0d);
                        Range range3 = new Range();
                        range3.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorGreen));
                        range3.setFrom(-25.0d);
                        range3.setTo(-8.0d);
                        Range range4 = new Range();
                        range4.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorRed));
                        range4.setFrom(-8.0d);
                        range4.setTo(10.0d);
                        Tr069_Activity.this.gauge_rxpower.addRange(range);
                        Tr069_Activity.this.gauge_rxpower.addRange(range2);
                        Tr069_Activity.this.gauge_rxpower.addRange(range3);
                        Tr069_Activity.this.gauge_rxpower.addRange(range4);
                        Tr069_Activity.this.gauge_rxpower.setMinValue(-40.0d);
                        Tr069_Activity.this.gauge_rxpower.setMaxValue(10.0d);
                        Tr069_Activity.this.gauge_rxpower.setValue((double) i);
                        Tr069_Activity.this.gauge_rxpower.setUseRangeBGColor(true);
                        Tr069_Activity.this.gauge_rxpower.setFormatter(new ValueFormatter() {
                            public String getFormattedValue(double d) {
                                return "";
                            }
                        });
                        Tr069_Activity.this.txt_rxpower2.setText(i + " dBm");
                        int i2 = jSONObject2.getInt("MEMORY");
                        Range range5 = new Range();
                        range5.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorGreen));
                        range5.setFrom(0.0d);
                        range5.setTo(60.0d);
                        Range range6 = new Range();
                        range6.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorOrange));
                        range6.setFrom(60.0d);
                        range6.setTo(80.0d);
                        Range range7 = new Range();
                        range7.setColor(ContextCompat.getColor(Tr069_Activity.this, R.color.colorRed));
                        range7.setFrom(80.0d);
                        range7.setTo(100.0d);
                        Tr069_Activity.this.gauge_mem.addRange(range5);
                        Tr069_Activity.this.gauge_mem.addRange(range6);
                        Tr069_Activity.this.gauge_mem.addRange(range7);
                        Tr069_Activity.this.gauge_mem.setMinValue(0.0d);
                        Tr069_Activity.this.gauge_mem.setMaxValue(100.0d);
                        Tr069_Activity.this.gauge_mem.setValue((double) i2);
                        Tr069_Activity.this.gauge_mem.setUseRangeBGColor(true);
                        Tr069_Activity.this.gauge_mem.setFormatter(new ValueFormatter() {
                            public String getFormattedValue(double d) {
                                return "";
                            }
                        });
                        Tr069_Activity.this.txt_mem2.setText(i2 + " %");
                        Tr069_Activity.this.txt_mem3.setText(jSONObject2.getString("F_MEMORY"));
                        Tr069_Activity.this.txt_mem4.setText(jSONObject2.getString("T_MEMORY"));
                        Tr069_Activity.this.txt_mfr2.setText(jSONObject3.getString("MFR") + " (" + jSONObject3.getString("MODEL") + ")");
                        Tr069_Activity.this.txt_model2.setText(jSONObject3.getString("MODEL"));
                        Tr069_Activity.this.txt_sno2.setText(jSONObject3.getString("SNO"));
                        Tr069_Activity.this.txt_sv2.setText(jSONObject3.getString("SV"));
                        Tr069_Activity.this.txt_hv2.setText(jSONObject3.getString("HV"));
                        JSONArray jSONArray = jSONObject.getJSONArray("WAN");
                        int i3 = 0;
                        int i4 = 0;
                        while (i4 < jSONArray.length()) {
                            JSONObject jSONObject4 = jSONArray.getJSONObject(i4);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.setMargins(15, 10, 15, i3);
                            View inflate = Tr069_Activity.this.getLayoutInflater().inflate(R.layout.custom_tr069_wan, (ViewGroup) null);
                            inflate.setLayoutParams(layoutParams);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_user1);
                            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_user2);
                            TextView textView3 = (TextView) inflate.findViewById(R.id.txt_vlan1);
                            TextView textView4 = (TextView) inflate.findViewById(R.id.txt_vlan2);
                            TextView textView5 = (TextView) inflate.findViewById(R.id.txt_ip1);
                            TextView textView6 = (TextView) inflate.findViewById(R.id.txt_ip2);
                            TextView textView7 = (TextView) inflate.findViewById(R.id.txt_ipstack1);
                            JSONArray jSONArray2 = jSONArray;
                            TextView textView8 = (TextView) inflate.findViewById(R.id.txt_ipstack2);
                            JSONObject jSONObject5 = jSONObject;
                            TextView textView9 = (TextView) inflate.findViewById(R.id.txt_status1);
                            TextView textView10 = (TextView) inflate.findViewById(R.id.txt_status2);
                            i4++;
                            View view = inflate;
                            ((TextView) inflate.findViewById(R.id.txt_srno)).setText(Integer.toString(i4));
                            textView.setPaintFlags(8);
                            if (jSONObject4.getString("SVR").toLowerCase().contains("voip")) {
                                textView.setText("TELEPHONE NO");
                            }
                            textView2.setText(jSONObject4.getString("USER"));
                            textView3.setPaintFlags(8);
                            textView4.setText(jSONObject4.getString("VLAN") + " (" + jSONObject4.getString("SVR") + ")");
                            textView5.setPaintFlags(8);
                            textView6.setText(Html.fromHtml(jSONObject4.getString("IP"), 63));
                            textView7.setPaintFlags(8);
                            textView8.setText(Html.fromHtml(jSONObject4.getString("IPSTACK"), 63));
                            textView9.setPaintFlags(8);
                            textView10.setText(Html.fromHtml(jSONObject4.getString("STATUS"), 63));
                            r0 = this;
                            Tr069_Activity.this.lay_waninfo.addView(view);
                            jSONArray = jSONArray2;
                            jSONObject = jSONObject5;
                            i3 = 0;
                        }
                        JSONArray jSONArray3 = jSONObject.getJSONArray("WLAN");
                        int i5 = 0;
                        while (i5 < jSONArray3.length()) {
                            JSONObject jSONObject6 = jSONArray3.getJSONObject(i5);
                            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams2.setMargins(15, 10, 15, 0);
                            View inflate2 = Tr069_Activity.this.getLayoutInflater().inflate(R.layout.custom_tr069_wlan, (ViewGroup) null);
                            inflate2.setLayoutParams(layoutParams2);
                            TextView textView11 = (TextView) inflate2.findViewById(R.id.txt_ssid_status2);
                            TextView textView12 = (TextView) inflate2.findViewById(R.id.txt_ssid_asso2);
                            i5++;
                            ((TextView) inflate2.findViewById(R.id.txt_srno)).setText(Integer.toString(i5));
                            ((TextView) inflate2.findViewById(R.id.txt_ssid_name)).setText(jSONObject6.getString("SSID"));
                            ((TextView) inflate2.findViewById(R.id.txt_ssid_status1)).setPaintFlags(8);
                            ((TextView) inflate2.findViewById(R.id.txt_ssid_asso1)).setPaintFlags(8);
                            textView11.setText(jSONObject6.getString("Status"));
                            textView12.setText(jSONObject6.getString("ActiveDeviceCount"));
                            if (jSONObject6.getString("Status").toLowerCase().equals("up")) {
                                textView11.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorGreen));
                            } else {
                                textView11.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorRed));
                            }
                            if (jSONObject6.getInt("ActiveDeviceCount") > 0) {
                                textView12.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorGreen));
                            } else {
                                textView12.setTextColor(Tr069_Activity.this.getResources().getColor(R.color.colorRed));
                            }
                            Tr069_Activity.this.lay_wlaninfo.addView(inflate2);
                        }
                        Tr069_Activity tr069_Activity = Tr069_Activity.this;
                        tr069_Activity.layout_visible(tr069_Activity.lay_detail);
                        return;
                    }
                    Tr069_Activity.this.txt_alert.setText(jSONObject.getString("ERROR"));
                    Tr069_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Tr069_Activity.this.progress_dialog.cancel();
                Tr069_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Tr069_Activity.this.getApplicationContext()));
                Tr069_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("tele", str2);
                hashMap.put("username", Tr069_Activity.this.Pref_Username);
                hashMap.put("random_key", Tr069_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Tr069_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    /* access modifiers changed from: private */
    public void layout_gone(final View view) {
        view.animate().alpha(0.0f).setDuration((long) getResources().getInteger(17694720)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public void layout_visible(View view) {
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration((long) getResources().getInteger(17694720)).setListener((Animator.AnimatorListener) null);
    }
}
