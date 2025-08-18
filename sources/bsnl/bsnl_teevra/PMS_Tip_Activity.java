package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.stats.CodePackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PMS_Tip_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Designation;
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
    public ArrayAdapter<String> adapter_fms_username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_vendor;
    /* access modifiers changed from: private */
    public AlertDialog alarm_dialog;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_pms_tip;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_firmaname;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_username;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_detail;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_tip_vendor;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_tip_activity);
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
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_tip_vendor = (Spinner) findViewById(R.id.sp_tip_vendor);
        this.btn_pms_tip = (TextView) findViewById(R.id.btn_pms_tip);
        this.lay_pms_detail = (LinearLayout) findViewById(R.id.lay_pms_detail);
        this.fms_firmaname = new ArrayList<>();
        this.fms_username = new ArrayList<>();
        this.fms_firmaname.add("-- VENDOR --");
        this.fms_username.add("-- FMS-USERNAME --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "tip_vendor_populate_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                PMS_Tip_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        PMS_Tip_Activity.this.fms_firmaname.add(jSONObject.getString("FMS_FIRM_NAME"));
                        PMS_Tip_Activity.this.fms_username.add(jSONObject.getString("FMS_USERNAME"));
                    }
                    PMS_Tip_Activity pMS_Tip_Activity = PMS_Tip_Activity.this;
                    PMS_Tip_Activity pMS_Tip_Activity2 = PMS_Tip_Activity.this;
                    ArrayAdapter unused = pMS_Tip_Activity.adapter_vendor = new ArrayAdapter(pMS_Tip_Activity2, R.layout.spinner_item, pMS_Tip_Activity2.fms_firmaname);
                    PMS_Tip_Activity pMS_Tip_Activity3 = PMS_Tip_Activity.this;
                    PMS_Tip_Activity pMS_Tip_Activity4 = PMS_Tip_Activity.this;
                    ArrayAdapter unused2 = pMS_Tip_Activity3.adapter_fms_username = new ArrayAdapter(pMS_Tip_Activity4, R.layout.spinner_item, pMS_Tip_Activity4.fms_username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PMS_Tip_Activity.this.sp_tip_vendor.setAdapter(PMS_Tip_Activity.this.adapter_vendor);
                if (PMS_Tip_Activity.this.Pref_Designation.contains("FRANCHISEE")) {
                    PMS_Tip_Activity.this.sp_tip_vendor.setSelection(PMS_Tip_Activity.this.adapter_fms_username.getPosition(PMS_Tip_Activity.this.Pref_fms_username));
                    PMS_Tip_Activity.this.sp_tip_vendor.setEnabled(false);
                    PMS_Tip_Activity.this.sp_tip_vendor.setAlpha(0.6f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                PMS_Tip_Activity.this.progress_dialog.cancel();
                PMS_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Tip_Activity.this.getApplicationContext()));
                PMS_Tip_Activity.this.error_dialog.show();
                PMS_Tip_Activity pMS_Tip_Activity = PMS_Tip_Activity.this;
                PMS_Tip_Activity pMS_Tip_Activity2 = PMS_Tip_Activity.this;
                ArrayAdapter unused = pMS_Tip_Activity.adapter_vendor = new ArrayAdapter(pMS_Tip_Activity2, R.layout.spinner_item, pMS_Tip_Activity2.fms_firmaname);
                PMS_Tip_Activity pMS_Tip_Activity3 = PMS_Tip_Activity.this;
                PMS_Tip_Activity pMS_Tip_Activity4 = PMS_Tip_Activity.this;
                ArrayAdapter unused2 = pMS_Tip_Activity3.adapter_fms_username = new ArrayAdapter(pMS_Tip_Activity4, R.layout.spinner_item, pMS_Tip_Activity4.fms_username);
                PMS_Tip_Activity.this.sp_tip_vendor.setAdapter(PMS_Tip_Activity.this.adapter_vendor);
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", PMS_Tip_Activity.this.Pref_Circle);
                hashMap.put("ssa", PMS_Tip_Activity.this.Pref_SSA);
                hashMap.put("username", PMS_Tip_Activity.this.Pref_Username);
                hashMap.put("random_key", PMS_Tip_Activity.this.Pref_Randkey);
                hashMap.put("device_id", PMS_Tip_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.btn_pms_tip.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.btn_pms_tip) {
            String str = getString(R.string.serverip) + "PMS-TIP/pms_olt_detail_new.php";
            this.lay_pms_detail.removeAllViewsInLayout();
            final String str2 = this.adapter_fms_username.getItem(this.sp_tip_vendor.getSelectedItemPosition()).toString();
            if (str2.equals("-- FMS-USERNAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT VENDOR CODE !!", this);
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass6 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                public void onResponse(String str) {
                    PMS_Tip_Activity.this.progress_dialog.cancel();
                    int i = 0;
                    try {
                        JSONArray jSONArray = new JSONArray(str);
                        if (jSONArray.length() == 0) {
                            new AlertHelperclass().ntoast("No Olt Is Mapped\nKindly Contact Nodal Officer", PMS_Tip_Activity.this);
                        } else {
                            int i2 = 0;
                            while (i2 < jSONArray.length()) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                LinearLayout linearLayout = new LinearLayout(PMS_Tip_Activity.this);
                                int i3 = -2;
                                int i4 = -1;
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                                int i5 = 10;
                                layoutParams.setMargins(i, 10, i, 10);
                                linearLayout.setLayoutParams(layoutParams);
                                linearLayout.setOrientation(1);
                                linearLayout.setGravity(17);
                                linearLayout.setBackgroundResource(R.drawable.new_style);
                                TextView textView = new TextView(PMS_Tip_Activity.this);
                                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                                layoutParams2.setMargins(i, i, i, 10);
                                textView.setLayoutParams(layoutParams2);
                                textView.setText(jSONObject.getString("FMS_FIRM_NAME"));
                                textView.setTextColor(-1);
                                textView.setGravity(17);
                                int i6 = 15;
                                textView.setPadding(15, 15, 15, 15);
                                ViewGroup viewGroup = null;
                                textView.setTypeface((Typeface) null, 1);
                                textView.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView.setBackgroundResource(R.drawable.new_style1_1);
                                linearLayout.addView(textView);
                                JSONArray jSONArray2 = jSONObject.getJSONArray("ROWSET");
                                int i7 = i;
                                while (i7 < jSONArray2.length()) {
                                    final JSONObject jSONObject2 = jSONArray2.getJSONObject(i7);
                                    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(i4, i3);
                                    layoutParams3.setMargins(i6, 5, i6, i5);
                                    View inflate = PMS_Tip_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_tip, viewGroup);
                                    inflate.setLayoutParams(layoutParams3);
                                    LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.lay_alarm);
                                    TextView textView2 = (TextView) inflate.findViewById(R.id.txt_alarm_major);
                                    TextView textView3 = (TextView) inflate.findViewById(R.id.txt_alarm_minor);
                                    TextView textView4 = (TextView) inflate.findViewById(R.id.txt_alarm_normal);
                                    JSONArray jSONArray3 = jSONArray;
                                    JSONArray jSONArray4 = jSONArray2;
                                    TextView textView5 = (TextView) inflate.findViewById(R.id.txt_error);
                                    i7++;
                                    int i8 = i2;
                                    ((TextView) inflate.findViewById(R.id.txt_srno)).setText(String.valueOf(i7));
                                    ((TextView) inflate.findViewById(R.id.txt_description)).setText("OLT-IP : " + jSONObject2.getString("OLT_IP") + " | OLT-VLAN : " + jSONObject2.getString("OLT_VLAN"));
                                    ((TextView) inflate.findViewById(R.id.txt_location)).setText("(" + jSONObject2.getString(CodePackage.LOCATION) + ")");
                                    if (Boolean.valueOf(jSONObject2.getBoolean("SUCCESS")).booleanValue()) {
                                        textView5.setVisibility(8);
                                        linearLayout2.setVisibility(0);
                                        JSONObject jSONObject3 = jSONObject2.getJSONObject("ALARM");
                                        final JSONArray jSONArray5 = jSONObject3.getJSONArray("MAJOR");
                                        final JSONArray jSONArray6 = jSONObject3.getJSONArray("MINOR");
                                        final JSONArray jSONArray7 = jSONObject3.getJSONArray("NORMAL");
                                        textView2.setText("" + jSONArray5.length());
                                        textView3.setText("" + jSONArray6.length());
                                        textView4.setText("" + jSONArray7.length());
                                        textView2.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (jSONArray5.length() > 0) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(PMS_Tip_Activity.this);
                                                    View inflate = PMS_Tip_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_alarm, (ViewGroup) null);
                                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alarm1);
                                                    TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.tbl_alarm);
                                                    int i = 0;
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton("OK", (DialogInterface.OnClickListener) null);
                                                    builder.setView(inflate);
                                                    AlertDialog unused = PMS_Tip_Activity.this.alarm_dialog = builder.create();
                                                    try {
                                                        textView.setText("IP : " + jSONObject2.getString("OLT_IP") + " | VLAN : " + jSONObject2.getString("OLT_VLAN") + " | ALARM : MAJOR");
                                                        textView.setBackgroundResource(R.drawable.new_style1_3);
                                                        int i2 = 0;
                                                        while (i2 < jSONArray5.length()) {
                                                            JSONObject jSONObject = jSONArray5.getJSONObject(i2);
                                                            TableRow tableRow = new TableRow(PMS_Tip_Activity.this);
                                                            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                                                            TextView textView2 = new TextView(PMS_Tip_Activity.this);
                                                            textView2.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            i2++;
                                                            textView2.setText(Integer.toString(i2));
                                                            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView2.setGravity(17);
                                                            textView2.setPadding(15, 15, 15, 15);
                                                            textView2.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView2.setTypeface(Typeface.DEFAULT_BOLD);
                                                            textView2.setBackgroundResource(R.drawable.style17);
                                                            TextView textView3 = new TextView(PMS_Tip_Activity.this);
                                                            textView3.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView3.setText(jSONObject.getString("SENSOR"));
                                                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView3.setGravity(3);
                                                            textView3.setPadding(15, 15, 15, 15);
                                                            textView3.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView3.setBackgroundResource(R.drawable.style17);
                                                            TextView textView4 = new TextView(PMS_Tip_Activity.this);
                                                            textView4.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView4.setText(jSONObject.getString("LASTVALUE"));
                                                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView4.setGravity(17);
                                                            textView4.setPadding(15, 15, 15, 15);
                                                            textView4.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView4.setBackgroundResource(R.drawable.style17);
                                                            TextView textView5 = new TextView(PMS_Tip_Activity.this);
                                                            textView5.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView5.setText(jSONObject.getString("MESSAGE"));
                                                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView5.setGravity(17);
                                                            textView5.setPadding(15, 15, 15, 15);
                                                            textView5.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView5.setBackgroundResource(R.drawable.style17);
                                                            TextView textView6 = new TextView(PMS_Tip_Activity.this);
                                                            textView6.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView6.setText(jSONObject.getString("TIMESTAMP"));
                                                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView6.setGravity(17);
                                                            textView6.setPadding(15, 15, 15, 15);
                                                            textView6.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView6.setBackgroundResource(R.drawable.style17);
                                                            tableRow.addView(textView2);
                                                            tableRow.addView(textView3);
                                                            tableRow.addView(textView4);
                                                            tableRow.addView(textView5);
                                                            tableRow.addView(textView6);
                                                            tableLayout.addView(tableRow);
                                                            i = 0;
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    PMS_Tip_Activity.this.alarm_dialog.show();
                                                    return;
                                                }
                                                new AlertHelperclass().ptoast("No Active Major Alarm", PMS_Tip_Activity.this);
                                            }
                                        });
                                        textView3.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (jSONArray6.length() > 0) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(PMS_Tip_Activity.this);
                                                    View inflate = PMS_Tip_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_alarm, (ViewGroup) null);
                                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alarm1);
                                                    TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.tbl_alarm);
                                                    int i = 0;
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton("OK", (DialogInterface.OnClickListener) null);
                                                    builder.setView(inflate);
                                                    AlertDialog unused = PMS_Tip_Activity.this.alarm_dialog = builder.create();
                                                    try {
                                                        textView.setText("IP : " + jSONObject2.getString("OLT_IP") + " | VLAN : " + jSONObject2.getString("OLT_VLAN") + " | ALARM : MINOR");
                                                        textView.setBackgroundResource(R.drawable.new_style1_5);
                                                        int i2 = 0;
                                                        while (i2 < jSONArray7.length()) {
                                                            JSONObject jSONObject = jSONArray6.getJSONObject(i2);
                                                            TableRow tableRow = new TableRow(PMS_Tip_Activity.this);
                                                            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                                                            TextView textView2 = new TextView(PMS_Tip_Activity.this);
                                                            textView2.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            i2++;
                                                            textView2.setText(Integer.toString(i2));
                                                            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView2.setGravity(17);
                                                            textView2.setPadding(15, 15, 15, 15);
                                                            textView2.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView2.setTypeface(Typeface.DEFAULT_BOLD);
                                                            textView2.setBackgroundResource(R.drawable.style17);
                                                            TextView textView3 = new TextView(PMS_Tip_Activity.this);
                                                            textView3.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView3.setText(jSONObject.getString("SENSOR"));
                                                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView3.setGravity(3);
                                                            textView3.setPadding(15, 15, 15, 15);
                                                            textView3.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView3.setBackgroundResource(R.drawable.style17);
                                                            TextView textView4 = new TextView(PMS_Tip_Activity.this);
                                                            textView4.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView4.setText(jSONObject.getString("LASTVALUE"));
                                                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView4.setGravity(17);
                                                            textView4.setPadding(15, 15, 15, 15);
                                                            textView4.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView4.setBackgroundResource(R.drawable.style17);
                                                            TextView textView5 = new TextView(PMS_Tip_Activity.this);
                                                            textView5.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView5.setText(jSONObject.getString("MESSAGE"));
                                                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView5.setGravity(17);
                                                            textView5.setPadding(15, 15, 15, 15);
                                                            textView5.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView5.setBackgroundResource(R.drawable.style17);
                                                            TextView textView6 = new TextView(PMS_Tip_Activity.this);
                                                            textView6.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView6.setText(jSONObject.getString("TIMESTAMP"));
                                                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView6.setGravity(17);
                                                            textView6.setPadding(15, 15, 15, 15);
                                                            textView6.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView6.setBackgroundResource(R.drawable.style17);
                                                            tableRow.addView(textView2);
                                                            tableRow.addView(textView3);
                                                            tableRow.addView(textView4);
                                                            tableRow.addView(textView5);
                                                            tableRow.addView(textView6);
                                                            tableLayout.addView(tableRow);
                                                            i = 0;
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    PMS_Tip_Activity.this.alarm_dialog.show();
                                                    return;
                                                }
                                                new AlertHelperclass().ptoast("No Active Minor Alarm", PMS_Tip_Activity.this);
                                            }
                                        });
                                        textView4.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (jSONArray7.length() > 0) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(PMS_Tip_Activity.this);
                                                    View inflate = PMS_Tip_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_alarm, (ViewGroup) null);
                                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alarm1);
                                                    TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.tbl_alarm);
                                                    int i = 0;
                                                    builder.setCancelable(false);
                                                    builder.setNegativeButton("OK", (DialogInterface.OnClickListener) null);
                                                    builder.setView(inflate);
                                                    AlertDialog unused = PMS_Tip_Activity.this.alarm_dialog = builder.create();
                                                    try {
                                                        textView.setText("IP : " + jSONObject2.getString("OLT_IP") + " | VLAN : " + jSONObject2.getString("OLT_VLAN") + " | ALARM : NORMAL");
                                                        textView.setBackgroundResource(R.drawable.new_style1_4);
                                                        int i2 = 0;
                                                        while (i2 < jSONArray7.length()) {
                                                            JSONObject jSONObject = jSONArray7.getJSONObject(i2);
                                                            TableRow tableRow = new TableRow(PMS_Tip_Activity.this);
                                                            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                                                            TextView textView2 = new TextView(PMS_Tip_Activity.this);
                                                            textView2.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            i2++;
                                                            textView2.setText(Integer.toString(i2));
                                                            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView2.setGravity(17);
                                                            textView2.setPadding(15, 15, 15, 15);
                                                            textView2.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView2.setTypeface(Typeface.DEFAULT_BOLD);
                                                            textView2.setBackgroundResource(R.drawable.style17);
                                                            TextView textView3 = new TextView(PMS_Tip_Activity.this);
                                                            textView3.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView3.setText(jSONObject.getString("SENSOR"));
                                                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView3.setGravity(3);
                                                            textView3.setPadding(15, 15, 15, 15);
                                                            textView3.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView3.setBackgroundResource(R.drawable.style17);
                                                            TextView textView4 = new TextView(PMS_Tip_Activity.this);
                                                            textView4.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView4.setText(jSONObject.getString("LASTVALUE"));
                                                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView4.setGravity(17);
                                                            textView4.setPadding(15, 15, 15, 15);
                                                            textView4.setTextSize(i, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView4.setBackgroundResource(R.drawable.style17);
                                                            TextView textView5 = new TextView(PMS_Tip_Activity.this);
                                                            textView5.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView5.setText(jSONObject.getString("MESSAGE"));
                                                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView5.setGravity(17);
                                                            textView5.setPadding(15, 15, 15, 15);
                                                            textView5.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView5.setBackgroundResource(R.drawable.style17);
                                                            TextView textView6 = new TextView(PMS_Tip_Activity.this);
                                                            textView6.setLayoutParams(new TableRow.LayoutParams(-2, -1));
                                                            textView6.setText(jSONObject.getString("TIMESTAMP"));
                                                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                                            textView6.setGravity(17);
                                                            textView6.setPadding(15, 15, 15, 15);
                                                            textView6.setTextSize(0, PMS_Tip_Activity.this.getResources().getDimension(R.dimen.smalltext));
                                                            textView6.setBackgroundResource(R.drawable.style17);
                                                            tableRow.addView(textView2);
                                                            tableRow.addView(textView3);
                                                            tableRow.addView(textView4);
                                                            tableRow.addView(textView5);
                                                            tableRow.addView(textView6);
                                                            tableLayout.addView(tableRow);
                                                            i = 0;
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    PMS_Tip_Activity.this.alarm_dialog.show();
                                                    return;
                                                }
                                                new AlertHelperclass().ntoast("No Active Normal Alarm", PMS_Tip_Activity.this);
                                            }
                                        });
                                    } else {
                                        textView5.setVisibility(0);
                                        linearLayout2.setVisibility(8);
                                        textView2.setText("NA");
                                        textView3.setText("NA");
                                        textView4.setText("NA");
                                    }
                                    linearLayout.addView(inflate);
                                    jSONArray = jSONArray3;
                                    jSONArray2 = jSONArray4;
                                    i2 = i8;
                                    i3 = -2;
                                    i4 = -1;
                                    i5 = 10;
                                    i6 = 15;
                                    viewGroup = null;
                                }
                                JSONArray jSONArray8 = jSONArray;
                                int i9 = i2;
                                PMS_Tip_Activity.this.lay_pms_detail.addView(linearLayout);
                                i2 = i9 + 1;
                                jSONArray = jSONArray8;
                                i = 0;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    PMS_Tip_Activity.this.progress_dialog.cancel();
                    PMS_Tip_Activity.this.lay_pms_detail.setVisibility(0);
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    PMS_Tip_Activity.this.progress_dialog.cancel();
                    PMS_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Tip_Activity.this.getApplicationContext()));
                    PMS_Tip_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("vendor", str2);
                    hashMap.put("circle", PMS_Tip_Activity.this.Pref_Circle);
                    hashMap.put("ssa", PMS_Tip_Activity.this.Pref_SSA);
                    hashMap.put("username", PMS_Tip_Activity.this.Pref_Username);
                    hashMap.put("random_key", PMS_Tip_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", PMS_Tip_Activity.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, PMS_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
