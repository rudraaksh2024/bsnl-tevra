package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_BBC_Activity extends AppCompatActivity {
    public static final int LOCATION_PERMISSION_REQ = 1001;
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public AlertDialog geo_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private Intent intent;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay_bbnl;
    /* access modifiers changed from: private */
    public LinearLayout lay_bsnl;
    private LinearLayout lay_detail;
    private LinearLayout lay_geotag;
    /* access modifiers changed from: private */
    public LinearLayout lay_tip;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private Spannable span_bbnl;
    private Spannable span_bsnl;
    private Spannable span_tip;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_bbnl;
    /* access modifiers changed from: private */
    public TextView txt_bbnl_tag;
    /* access modifiers changed from: private */
    public TextView txt_bbnl_total;
    /* access modifiers changed from: private */
    public TextView txt_bbnl_untag;
    private TextView txt_bsnl;
    /* access modifiers changed from: private */
    public TextView txt_bsnl_tag;
    /* access modifiers changed from: private */
    public TextView txt_bsnl_total;
    /* access modifiers changed from: private */
    public TextView txt_bsnl_untag;
    /* access modifiers changed from: private */
    public TextView txt_header;
    private TextView txt_tip;
    /* access modifiers changed from: private */
    public TextView txt_tip_tag;
    /* access modifiers changed from: private */
    public TextView txt_tip_total;
    /* access modifiers changed from: private */
    public TextView txt_tip_untag;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.intent = getIntent();
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.progress_dialog = create;
        create.show();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay1);
        this.lay1 = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) i) / 2.5d)));
        this.lay_bsnl = (LinearLayout) findViewById(R.id.lay_bsnl);
        this.lay_bbnl = (LinearLayout) findViewById(R.id.lay_bbnl);
        this.lay_tip = (LinearLayout) findViewById(R.id.lay_tip);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.lay_detail);
        this.lay_detail = linearLayout2;
        linearLayout2.setVisibility(8);
        this.lay_geotag = (LinearLayout) findViewById(R.id.lay_geotag);
        this.txt_bsnl = (TextView) findViewById(R.id.txt_bsnl);
        this.txt_bbnl = (TextView) findViewById(R.id.txt_bbnl);
        this.txt_tip = (TextView) findViewById(R.id.txt_tip);
        SpannableString spannableString = new SpannableString(this.txt_bsnl.getText());
        this.span_bsnl = spannableString;
        spannableString.setSpan(new RelativeSizeSpan(2.0f), 0, 1, 33);
        this.txt_bsnl.setText(this.span_bsnl);
        SpannableString spannableString2 = new SpannableString(this.txt_bbnl.getText());
        this.span_bbnl = spannableString2;
        spannableString2.setSpan(new RelativeSizeSpan(2.0f), 0, 1, 33);
        this.txt_bbnl.setText(this.span_bbnl);
        SpannableString spannableString3 = new SpannableString(this.txt_tip.getText());
        this.span_tip = spannableString3;
        spannableString3.setSpan(new RelativeSizeSpan(2.0f), 0, 1, 33);
        this.txt_tip.setText(this.span_tip);
        this.txt_bsnl_untag = (TextView) findViewById(R.id.txt_bsnl_untag);
        this.txt_bsnl_tag = (TextView) findViewById(R.id.txt_bsnl_tag);
        this.txt_bsnl_total = (TextView) findViewById(R.id.txt_bsnl_total);
        this.txt_bbnl_untag = (TextView) findViewById(R.id.txt_bbnl_untag);
        this.txt_bbnl_tag = (TextView) findViewById(R.id.txt_bbnl_tag);
        this.txt_bbnl_total = (TextView) findViewById(R.id.txt_bbnl_total);
        this.txt_tip_untag = (TextView) findViewById(R.id.txt_tip_untag);
        this.txt_tip_tag = (TextView) findViewById(R.id.txt_tip_tag);
        this.txt_tip_total = (TextView) findViewById(R.id.txt_tip_total);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r4 = new StringRequest(1, getString(R.string.serverip) + "fms/get_bbc_data.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_BBC_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("SUCCESS")).booleanValue()) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("BSNL");
                        JSONObject jSONObject3 = jSONObject.getJSONObject("BBNL");
                        JSONObject jSONObject4 = jSONObject.getJSONObject("TIP");
                        String string = jSONObject2.getString("UNTAG");
                        String string2 = jSONObject2.getString("TAG");
                        final String string3 = jSONObject2.getString("TOTAL");
                        final JSONArray jSONArray = jSONObject2.getJSONArray("ROWSET");
                        String string4 = jSONObject3.getString("UNTAG");
                        String string5 = jSONObject3.getString("TAG");
                        final String string6 = jSONObject3.getString("TOTAL");
                        JSONArray jSONArray2 = jSONObject3.getJSONArray("ROWSET");
                        String string7 = jSONObject4.getString("UNTAG");
                        String string8 = jSONObject4.getString("TAG");
                        final String string9 = jSONObject4.getString("TOTAL");
                        JSONArray jSONArray3 = jSONObject4.getJSONArray("ROWSET");
                        FMS_BBC_Activity.this.txt_bsnl_untag.setText(string);
                        FMS_BBC_Activity.this.txt_bsnl_tag.setText(string2);
                        FMS_BBC_Activity.this.txt_bsnl_tag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        FMS_BBC_Activity.this.txt_bsnl_total.setText(string3);
                        FMS_BBC_Activity.this.txt_bbnl_untag.setText(string4);
                        FMS_BBC_Activity.this.txt_bbnl_tag.setText(string5);
                        FMS_BBC_Activity.this.txt_bbnl_tag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        FMS_BBC_Activity.this.txt_bbnl_total.setText(string6);
                        FMS_BBC_Activity.this.txt_tip_untag.setText(string7);
                        FMS_BBC_Activity.this.txt_tip_tag.setText(string8);
                        FMS_BBC_Activity.this.txt_tip_tag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        FMS_BBC_Activity.this.txt_tip_total.setText(string9);
                        if (Integer.parseInt(string3) <= 0) {
                            FMS_BBC_Activity.this.txt_bsnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_bsnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        } else if (Integer.parseInt(jSONObject2.getString("UNTAG")) > 0) {
                            FMS_BBC_Activity.this.txt_bsnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                            FMS_BBC_Activity.this.txt_bsnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                        } else {
                            FMS_BBC_Activity.this.txt_bsnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_bsnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        }
                        if (Integer.parseInt(string6) <= 0) {
                            FMS_BBC_Activity.this.txt_bbnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_bbnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        } else if (Integer.parseInt(jSONObject3.getString("UNTAG")) > 0) {
                            FMS_BBC_Activity.this.txt_bbnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                            FMS_BBC_Activity.this.txt_bbnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                        } else {
                            FMS_BBC_Activity.this.txt_bbnl_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_bbnl_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        }
                        if (Integer.parseInt(string9) <= 0) {
                            FMS_BBC_Activity.this.txt_tip_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_tip_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        } else if (Integer.parseInt(jSONObject4.getString("UNTAG")) > 0) {
                            FMS_BBC_Activity.this.txt_tip_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                            FMS_BBC_Activity.this.txt_tip_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorRed));
                        } else {
                            FMS_BBC_Activity.this.txt_tip_untag.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                            FMS_BBC_Activity.this.txt_tip_total.setTextColor(FMS_BBC_Activity.this.getColor(R.color.colorGreen));
                        }
                        FMS_BBC_Activity.this.lay_bsnl.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (Integer.parseInt(string3) > 0) {
                                    FMS_BBC_Activity.this.txt_header.setText("MAPPED BSNL OLTS");
                                    FMS_BBC_Activity.this.Display_olts(jSONArray);
                                    return;
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                                View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                                TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                                textView.setText("OK");
                                builder.setCancelable(false);
                                builder.setView(inflate);
                                AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                                FMS_BBC_Activity.this.info_dialog.show();
                                ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                                ((TextView) inflate.findViewById(R.id.txt_error)).setText("No BSNL OLTs are Mapped In Your FMS Account\nPlease contact your Franchisee Manager");
                                textView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        FMS_BBC_Activity.this.info_dialog.cancel();
                                    }
                                });
                            }
                        });
                        final JSONArray jSONArray4 = jSONArray2;
                        FMS_BBC_Activity.this.lay_bbnl.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (Integer.parseInt(string6) > 0) {
                                    FMS_BBC_Activity.this.txt_header.setText("MAPPED BBNL OLTS");
                                    FMS_BBC_Activity.this.Display_olts(jSONArray4);
                                    return;
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                                View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                                TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                                textView.setText("OK");
                                builder.setCancelable(false);
                                builder.setView(inflate);
                                AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                                FMS_BBC_Activity.this.info_dialog.show();
                                ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                                ((TextView) inflate.findViewById(R.id.txt_error)).setText("No BBNL OLTs are Mapped In Your FMS Account\nPlease contact your Franchisee Manager");
                                textView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        FMS_BBC_Activity.this.info_dialog.cancel();
                                    }
                                });
                            }
                        });
                        final JSONArray jSONArray5 = jSONArray3;
                        FMS_BBC_Activity.this.lay_tip.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (Integer.parseInt(string9) > 0) {
                                    FMS_BBC_Activity.this.txt_header.setText("MAPPED TIP OLTS");
                                    FMS_BBC_Activity.this.Display_olts(jSONArray5);
                                    return;
                                }
                                AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                                View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                                TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                                textView.setText("OK");
                                builder.setCancelable(false);
                                builder.setView(inflate);
                                AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                                FMS_BBC_Activity.this.info_dialog.show();
                                ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                                ((TextView) inflate.findViewById(R.id.txt_error)).setText("No TIP OLTs/BAFs are Mapped In Your FMS Account\nPlease contact your Franchisee Manager");
                                textView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        FMS_BBC_Activity.this.info_dialog.cancel();
                                    }
                                });
                            }
                        });
                        return;
                    }
                    String string10 = jSONObject.getString("ERROR");
                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                    View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                    TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                    textView.setText("OK");
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                    FMS_BBC_Activity.this.info_dialog.show();
                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText(string10);
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_BBC_Activity.this.info_dialog.cancel();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_BBC_Activity.this.progress_dialog.cancel();
                FMS_BBC_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Activity.this.getApplicationContext()));
                FMS_BBC_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_username", FMS_BBC_Activity.this.Pref_Fms_Username);
                hashMap.put("circle", FMS_BBC_Activity.this.Pref_Circle);
                hashMap.put("ssa", FMS_BBC_Activity.this.Pref_SSA);
                hashMap.put("username", FMS_BBC_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_BBC_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_BBC_Activity.this.androidId);
                return hashMap;
            }
        };
        r4.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r4);
    }

    /* access modifiers changed from: private */
    public void Display_olts(JSONArray jSONArray) {
        String str = "OLT_VLAN";
        String str2 = "OLT_IP";
        this.lay_detail.setVisibility(8);
        this.lay_geotag.removeAllViews();
        int i = 0;
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String trim = jSONObject.getString(str2).trim();
                String trim2 = jSONObject.getString(str).trim();
                LinearLayout linearLayout = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(i, 10, i, 10);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setOrientation(1);
                linearLayout.setPadding(10, 10, 10, 10);
                linearLayout.setBackgroundResource(R.drawable.new_style);
                LinearLayout linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout2.setOrientation(0);
                linearLayout2.setGravity(17);
                linearLayout2.setPadding(10, 10, 10, 10);
                linearLayout2.setBackgroundResource(R.drawable.new_style10);
                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                int i3 = i2 + 1;
                textView.setText(Integer.toString(i3));
                textView.setTextColor(-1);
                textView.setGravity(17);
                textView.setPadding(5, 5, 5, 5);
                textView.setTypeface((Typeface) null, 1);
                textView.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView.setBackgroundResource(R.drawable.ic_new_circle_red);
                TextView textView2 = new TextView(this);
                int i4 = i3;
                LinearLayout linearLayout3 = linearLayout;
                textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView2.setText(jSONObject.getString("TEEVRA_FIRM_NAME").toUpperCase());
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setGravity(17);
                textView2.setTypeface((Typeface) null, 1);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                linearLayout2.addView(textView);
                linearLayout2.addView(textView2);
                LinearLayout linearLayout4 = new LinearLayout(this);
                linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout4.setOrientation(0);
                linearLayout4.setGravity(17);
                linearLayout4.setPadding(5, 10, 5, 0);
                TextView textView3 = new TextView(this);
                LinearLayout linearLayout5 = linearLayout2;
                textView3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView3.setText("OLT-VLAN");
                textView3.setPaintFlags(8);
                textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView3.setGravity(17);
                textView3.setTypeface((Typeface) null, 1);
                textView3.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView4 = new TextView(this);
                String str3 = trim;
                textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView4.setText("OLT-IP");
                textView4.setPaintFlags(8);
                textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView4.setGravity(17);
                textView4.setTypeface((Typeface) null, 1);
                textView4.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView5 = new TextView(this);
                String str4 = trim2;
                textView5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView5.setText("OLT-MAKE");
                textView5.setPaintFlags(8);
                textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView5.setGravity(17);
                textView5.setTypeface((Typeface) null, 1);
                textView5.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout4.addView(textView3);
                linearLayout4.addView(textView4);
                linearLayout4.addView(textView5);
                LinearLayout linearLayout6 = new LinearLayout(this);
                linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout6.setOrientation(0);
                linearLayout6.setGravity(17);
                linearLayout6.setPadding(5, 5, 5, 10);
                TextView textView6 = new TextView(this);
                textView6.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView6.setText(jSONObject.getString(str).toUpperCase());
                textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView6.setGravity(17);
                textView6.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView7 = new TextView(this);
                textView7.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView7.setText(jSONObject.getString(str2).toUpperCase());
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView8 = new TextView(this);
                String str5 = str;
                textView8.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView8.setText(jSONObject.getString("OLT_MAKE").toUpperCase());
                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView8.setGravity(17);
                textView8.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout6.addView(textView6);
                linearLayout6.addView(textView7);
                linearLayout6.addView(textView8);
                LinearLayout linearLayout7 = new LinearLayout(this);
                linearLayout7.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout7.setOrientation(0);
                linearLayout7.setGravity(17);
                linearLayout7.setPadding(5, 10, 5, 0);
                TextView textView9 = new TextView(this);
                textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView9.setText("LATITUDE");
                textView9.setPaintFlags(8);
                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView9.setGravity(17);
                textView9.setTypeface((Typeface) null, 1);
                textView9.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView10 = new TextView(this);
                textView10.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView10.setText("LONGITUDE");
                textView10.setPaintFlags(8);
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setTypeface((Typeface) null, 1);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView11 = new TextView(this);
                String str6 = str2;
                textView11.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView11.setText("GEO-TAGGED");
                textView11.setPaintFlags(8);
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setTypeface((Typeface) null, 1);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout7.addView(textView9);
                linearLayout7.addView(textView10);
                linearLayout7.addView(textView11);
                LinearLayout linearLayout8 = new LinearLayout(this);
                linearLayout8.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout8.setOrientation(0);
                linearLayout8.setGravity(17);
                linearLayout8.setPadding(5, 5, 5, 10);
                TextView textView12 = new TextView(this);
                textView12.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView12.setText(jSONObject.getString("OLT_LAT").toUpperCase());
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView13 = new TextView(this);
                textView13.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView13.setText(jSONObject.getString("OLT_LONG").toUpperCase());
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView14 = new TextView(this);
                LinearLayout linearLayout9 = linearLayout7;
                textView14.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView14.setText(jSONObject.getString("GEO_TAGGED").toUpperCase());
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setTypeface((Typeface) null, 1);
                textView14.setGravity(17);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout8.addView(textView12);
                linearLayout8.addView(textView13);
                linearLayout8.addView(textView14);
                LinearLayout linearLayout10 = new LinearLayout(this);
                linearLayout10.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout10.setOrientation(0);
                linearLayout10.setPadding(5, 10, 5, 5);
                TextView textView15 = new TextView(this);
                LinearLayout linearLayout11 = linearLayout8;
                LinearLayout linearLayout12 = linearLayout6;
                textView15.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView15.setText("GEO LOCATION : ");
                textView15.setPaintFlags(8);
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(1);
                textView15.setTypeface((Typeface) null, 1);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView16 = new TextView(this);
                LinearLayout linearLayout13 = linearLayout4;
                textView16.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                textView16.setText(jSONObject.getString("GEO_LOC").toUpperCase());
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(3);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout10.addView(textView15);
                linearLayout10.addView(textView16);
                LinearLayout linearLayout14 = new LinearLayout(this);
                linearLayout14.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout14.setOrientation(0);
                linearLayout14.setGravity(5);
                linearLayout14.setPadding(5, 10, 5, 0);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams2.setMargins(0, 0, 50, 20);
                TextView textView17 = new TextView(this);
                textView17.setLayoutParams(layoutParams2);
                textView17.setText("UPDATE");
                textView17.setTextColor(-1);
                textView17.setGravity(5);
                textView17.setTypeface((Typeface) null, 1);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView17.setPadding(40, 20, 40, 20);
                textView17.setBackgroundResource(R.drawable.button);
                linearLayout14.addView(textView17);
                if (!jSONObject.getBoolean("GEO_TAGGED")) {
                    textView12.setText("N/A");
                    textView12.setTextColor(getColor(R.color.colorRed));
                    textView12.setTypeface((Typeface) null, 1);
                    textView13.setText("N/A");
                    textView13.setTextColor(getColor(R.color.colorRed));
                    textView13.setTypeface((Typeface) null, 1);
                    textView14.setText("NOT GEO TAGGED");
                    textView14.setTextColor(getColor(R.color.colorRed));
                    textView16.setText("N/A");
                    textView16.setTextColor(getColor(R.color.colorRed));
                    textView16.setTypeface((Typeface) null, 1);
                    textView17.setText("ADD");
                    final String str7 = str3;
                    final String str8 = str4;
                    textView17.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_BBC_Activity.this.getGeoLocation(str7, str8);
                        }
                    });
                } else {
                    textView14.setText("GEO TAGGED");
                    textView14.setTextColor(getColor(R.color.colorGreen));
                    textView17.setText("UPDATE");
                    textView17.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                            View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(8);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("Ok");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                            FMS_BBC_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText("OLT Is GEO-Tagged And Freezed.\n\nKindly Ask Ur FM To Raise A Ticket To DSCM/FMS Development Team");
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    FMS_BBC_Activity.this.info_dialog.cancel();
                                }
                            });
                        }
                    });
                }
                LinearLayout linearLayout15 = linearLayout3;
                linearLayout15.addView(linearLayout5);
                linearLayout15.addView(linearLayout13);
                linearLayout15.addView(linearLayout12);
                linearLayout15.addView(linearLayout9);
                linearLayout15.addView(linearLayout11);
                linearLayout15.addView(linearLayout10);
                linearLayout15.addView(linearLayout14);
                this.lay_geotag.addView(linearLayout15);
                this.lay_detail.setVisibility(0);
                i = 0;
                i2 = i4;
                str = str5;
                str2 = str6;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void getGeoLocation(final String str, final String str2) {
        final HashMap hashMap = new HashMap();
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(100);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE"}, 1001);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("SETTINGS");
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_error)).setText("Action Needs Access Of Device GPS");
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + FMS_BBC_Activity.this.getPackageName()));
                    intent.addFlags(268435456);
                    intent.addFlags(BasicMeasure.EXACTLY);
                    intent.addFlags(8388608);
                    FMS_BBC_Activity.this.startActivity(intent);
                    FMS_BBC_Activity.this.info_dialog.cancel();
                }
            });
        } else if (((LocationManager) getSystemService("location")).isProviderEnabled("gps")) {
            this.progress_dialog.show();
            LocationServices.getFusedLocationProviderClient((Activity) this).requestLocationUpdates(locationRequest, (LocationCallback) new LocationCallback() {
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LocationServices.getFusedLocationProviderClient((Activity) FMS_BBC_Activity.this).removeLocationUpdates((LocationCallback) this);
                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                        int size = locationResult.getLocations().size() - 1;
                        double latitude = locationResult.getLocations().get(size).getLatitude();
                        double longitude = locationResult.getLocations().get(size).getLongitude();
                        hashMap.put("latitude", Double.toString(latitude));
                        hashMap.put("longitude", Double.toString(longitude));
                        hashMap.put("address", FMS_BBC_Activity.this.getCompleteAddressString(latitude, longitude));
                        FMS_BBC_Activity.this.display_geo_info(hashMap, str, str2);
                        FMS_BBC_Activity.this.progress_dialog.cancel();
                    }
                }
            }, Looper.getMainLooper());
        } else {
            this.txt_alert.setText("GPS is disabled\nPlease Turn On GPS to Proceed Further");
            this.error_dialog.show();
        }
    }

    /* access modifiers changed from: private */
    public void display_geo_info(Map<String, String> map, String str, String str2) {
        final String str3 = map.get("latitude");
        final String str4 = map.get("longitude");
        final String str5 = map.get("address");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_geotag, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.txt_info)).setText("OLT-IP : " + str + " | OLT-VLAN : " + str2);
        ((TextView) inflate.findViewById(R.id.txt_lat)).setText(str3);
        ((TextView) inflate.findViewById(R.id.txt_long)).setText(str4);
        ((TextView) inflate.findViewById(R.id.txt_address)).setText(str5);
        builder.setCancelable(false);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.geo_dialog = create;
        create.show();
        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FMS_BBC_Activity.this.geo_dialog.cancel();
            }
        });
        final String str6 = str;
        ((TextView) inflate.findViewById(R.id.txt_update)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FMS_BBC_Activity.this.geo_dialog.cancel();
                FMS_BBC_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(FMS_BBC_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, FMS_BBC_Activity.this.getString(R.string.serverip) + "fms/bbc_geoloc_update1.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        FMS_BBC_Activity.this.progress_dialog.cancel();
                        new AlertHelperclass().ptoast("GEO-LOC IS UPDATED SUCCESSFULLY !!", FMS_BBC_Activity.this.getApplicationContext());
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Activity.this);
                        View inflate = FMS_BBC_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                        ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                        TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                        textView.setText("OK");
                        builder.setCancelable(false);
                        builder.setView(inflate);
                        AlertDialog unused = FMS_BBC_Activity.this.info_dialog = builder.create();
                        FMS_BBC_Activity.this.info_dialog.show();
                        ((TextView) inflate.findViewById(R.id.txt_alert)).setText("Congratulations");
                        ((TextView) inflate.findViewById(R.id.txt_error)).setText("Olt Geo Location Is Successfuly Updated");
                        textView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                FMS_BBC_Activity.this.info_dialog.cancel();
                                Intent intent = new Intent(FMS_BBC_Activity.this, FMS_BBC_Activity.class);
                                intent.setFlags(67108864);
                                FMS_BBC_Activity.this.startActivity(intent);
                                FMS_BBC_Activity.this.finish();
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Activity.this.getApplicationContext());
                        FMS_BBC_Activity.this.progress_dialog.cancel();
                        FMS_BBC_Activity.this.txt_alert.setText(onErrorResponse);
                        FMS_BBC_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", FMS_BBC_Activity.this.Pref_Username);
                        hashMap.put("random_key", FMS_BBC_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", FMS_BBC_Activity.this.androidId);
                        hashMap.put("circle", FMS_BBC_Activity.this.Pref_Circle);
                        hashMap.put("ssa", FMS_BBC_Activity.this.Pref_SSA);
                        hashMap.put("olt_ip", str6);
                        hashMap.put("olt_lat", str3);
                        hashMap.put("olt_long", str4);
                        hashMap.put("geo_loc", str5);
                        hashMap.put("geo_updated_by", FMS_BBC_Activity.this.Pref_Fms_Username);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
    }

    /* access modifiers changed from: private */
    public String getCompleteAddressString(double d, double d2) {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(d, d2, 1);
            if (fromLocation == null) {
                return "";
            }
            Address address = fromLocation.get(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                sb.append(address.getAddressLine(i));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intent intent2 = getIntent();
        this.intent = intent2;
        intent2.getStringExtra("fms_username");
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent2 = new Intent(this, DashBoard_New.class);
            intent2.setFlags(67108864);
            startActivity(intent2);
            finish();
        } else if (itemId == R.id.fmslogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.logout_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Activity fMS_BBC_Activity = FMS_BBC_Activity.this;
                    fMS_BBC_Activity.editor = fMS_BBC_Activity.sharedPreferences.edit();
                    FMS_BBC_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_Activity.this.startActivity(intent);
                    FMS_BBC_Activity.this.finish();
                    FMS_BBC_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
