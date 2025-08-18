package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.stats.CodePackage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Report_Traffic_Tip_Cum_Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    public int NUM_ITEMS_PAGE = 100;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    private String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Fms_role;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private CustomBaseAdapter4 adapter;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView btn_submit;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public int countt = 0;
    private CustomBaseAdapter4 customBaseAdapter;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_search;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    private LinearLayout lay_btn;
    private ListView listview;
    private AlertDialog logout_dialog;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    private TextView title;
    /* access modifiers changed from: private */
    public AlertDialog traffic_dialog;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header1;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_traffic_tip_cum_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        SharedPreferences sharedPreferences3 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences3;
        this.Pref_Fms_Username = sharedPreferences3.getString("fms_username_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Fms_UserId = this.sharedPreferences.getString("fms_userid_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Zone = this.sharedPreferences.getString("fms_user_zone_Key", (String) null);
        this.Pref_Fms_TeamId = this.sharedPreferences.getString("fms_teamid_key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
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
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay2);
        this.lay2 = linearLayout;
        linearLayout.setVisibility(8);
        this.txt_header1 = (TextView) findViewById(R.id.txt_header1);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.listview = (ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Report_Traffic_Tip_Cum_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                ArrayAdapter unused = report_Traffic_Tip_Cum_Activity.adapter_circle = new ArrayAdapter(report_Traffic_Tip_Cum_Activity2, R.layout.spinner_item, report_Traffic_Tip_Cum_Activity2.circle_element);
                Report_Traffic_Tip_Cum_Activity.this.sp_circle.setAdapter(Report_Traffic_Tip_Cum_Activity.this.adapter_circle);
                Report_Traffic_Tip_Cum_Activity.this.sp_circle.setSelection(Report_Traffic_Tip_Cum_Activity.this.adapter_circle.getPosition(Report_Traffic_Tip_Cum_Activity.this.Pref_Circle));
                if (Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("SSA") || Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("BA") || Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Traffic_Tip_Cum_Activity.this.sp_circle.setEnabled(false);
                    Report_Traffic_Tip_Cum_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Cum_Activity.this.getApplicationContext()));
                Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
                Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                ArrayAdapter unused = report_Traffic_Tip_Cum_Activity.adapter_circle = new ArrayAdapter(report_Traffic_Tip_Cum_Activity2, R.layout.spinner_item, report_Traffic_Tip_Cum_Activity2.circle_element);
                Report_Traffic_Tip_Cum_Activity.this.sp_circle.setAdapter(Report_Traffic_Tip_Cum_Activity.this.adapter_circle);
                if (Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("SSA") || Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("BA") || Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Traffic_Tip_Cum_Activity.this.sp_circle.setEnabled(false);
                    Report_Traffic_Tip_Cum_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Report_Traffic_Tip_Cum_Activity.this.Pref_Circle);
                hashMap.put("access", Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level);
                hashMap.put("username", Report_Traffic_Tip_Cum_Activity.this.Pref_Username);
                hashMap.put("random_key", Report_Traffic_Tip_Cum_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Report_Traffic_Tip_Cum_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Report_Traffic_Tip_Cum_Activity.this.ssa_element = new ArrayList();
                Report_Traffic_Tip_Cum_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Report_Traffic_Tip_Cum_Activity.this.sp_circle.getSelectedItem().toString();
                Report_Traffic_Tip_Cum_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Report_Traffic_Tip_Cum_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Report_Traffic_Tip_Cum_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Report_Traffic_Tip_Cum_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter unused = Report_Traffic_Tip_Cum_Activity.this.adapter_ssa = new ArrayAdapter(Report_Traffic_Tip_Cum_Activity.this, R.layout.spinner_item, Report_Traffic_Tip_Cum_Activity.this.ssa_element);
                        Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setAdapter(Report_Traffic_Tip_Cum_Activity.this.adapter_ssa);
                        if (obj.equals(Report_Traffic_Tip_Cum_Activity.this.Pref_Circle)) {
                            Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setSelection(Report_Traffic_Tip_Cum_Activity.this.adapter_ssa.getPosition(Report_Traffic_Tip_Cum_Activity.this.Pref_SSA));
                        }
                        if (Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setEnabled(false);
                            Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Cum_Activity.this.getApplicationContext());
                        Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                        Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText(onErrorResponse);
                        Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
                        ArrayAdapter unused = Report_Traffic_Tip_Cum_Activity.this.adapter_ssa = new ArrayAdapter(Report_Traffic_Tip_Cum_Activity.this, R.layout.spinner_item, Report_Traffic_Tip_Cum_Activity.this.ssa_element);
                        Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setAdapter(Report_Traffic_Tip_Cum_Activity.this.adapter_ssa);
                        if (Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setEnabled(false);
                            Report_Traffic_Tip_Cum_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Report_Traffic_Tip_Cum_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Report_Traffic_Tip_Cum_Activity.this.Pref_SSA);
                        hashMap.put("username", Report_Traffic_Tip_Cum_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Traffic_Tip_Cum_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Traffic_Tip_Cum_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.btn_submit.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Report_Traffic_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_submit) {
            final String trim = this.sp_circle.getSelectedItem().toString().trim();
            final String trim2 = this.sp_ssa.getSelectedItem().toString().trim();
            if (trim.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT  A VALID CIRCLE NAME", getApplicationContext());
            } else if (trim2.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID SSA NAME", getApplicationContext());
            } else {
                this.lay2.setVisibility(8);
                this.progress_dialog.show();
                this.listview.setAdapter((ListAdapter) null);
                this.txt_header1.setText("CIRCLE : " + trim + " | SSA : " + trim2);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass7 r0 = new StringRequest(1, getString(R.string.serverip) + "report/peak_traffic1.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.getBoolean("SUCCESS")) {
                                JSONArray unused = Report_Traffic_Tip_Cum_Activity.this.Row_Array = jSONObject.getJSONArray("ROWSET");
                                Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                                report_Traffic_Tip_Cum_Activity.TOTAL_LIST_ITEMS = report_Traffic_Tip_Cum_Activity.Row_Array.length();
                                Report_Traffic_Tip_Cum_Activity.this.Btnfooter();
                                Report_Traffic_Tip_Cum_Activity.this.loadList(0);
                                Report_Traffic_Tip_Cum_Activity.this.CheckBtnBackGroud(0);
                                Report_Traffic_Tip_Cum_Activity.this.et_search.addTextChangedListener(Report_Traffic_Tip_Cum_Activity.this);
                                Report_Traffic_Tip_Cum_Activity.this.lay2.setVisibility(0);
                                return;
                            }
                            String string = jSONObject.getString("ERROR");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Report_Traffic_Tip_Cum_Activity.this);
                            View inflate = Report_Traffic_Tip_Cum_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setBackgroundResource(0);
                            textView.setText("OK");
                            textView.setGravity(5);
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused2 = Report_Traffic_Tip_Cum_Activity.this.info_dialog = builder.create();
                            Report_Traffic_Tip_Cum_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(string);
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Report_Traffic_Tip_Cum_Activity.this.info_dialog.cancel();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                        Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Cum_Activity.this.getApplicationContext()));
                        Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", trim);
                        hashMap.put("ssa", trim2);
                        hashMap.put("username", Report_Traffic_Tip_Cum_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Traffic_Tip_Cum_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Traffic_Tip_Cum_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        String obj = this.et_search.getText().toString();
        this.listview.setAdapter((ListAdapter) null);
        this.searched_array = new JSONArray();
        this.search_count = 0;
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                if (jSONObject.getString("FMS_FIRM_NAME").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_VLAN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_MAKE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase())) {
                    this.searched_array.put(this.Row_Array.getJSONObject(i));
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        Btnfooter1(this.searched_array);
        loadList1(0, this.searched_array);
        CheckBtnBackGroud1(0, this.searched_array);
    }

    /* access modifiers changed from: private */
    public void get_traffic1(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Utils.init((Context) this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        final int i2 = displayMetrics.widthPixels;
        this.progress_dialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View inflate = getLayoutInflater().inflate(R.layout.custom_peak_traffic1, (ViewGroup) null);
        builder.setCancelable(true);
        builder.setView(inflate);
        this.traffic_dialog = builder.create();
        TextView textView = (TextView) inflate.findViewById(R.id.txt_conn);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.img_share);
        imageView2.setVisibility(8);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_detail);
        linearLayout.setVisibility(8);
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (imageView2.getVisibility() == 0) {
                    Bitmap createBitmap = Bitmap.createBitmap(inflate.getWidth(), inflate.getHeight(), Bitmap.Config.ARGB_8888);
                    inflate.draw(new Canvas(createBitmap));
                    new ShareImage().share(createBitmap, new File(Report_Traffic_Tip_Cum_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "peak_traffic.jpg"), Report_Traffic_Tip_Cum_Activity.this);
                }
            }
        });
        ((TextView) inflate.findViewById(R.id.txt_srno)).setText(str);
        ((TextView) inflate.findViewById(R.id.txt_firm)).setText(str2);
        ((TextView) inflate.findViewById(R.id.txt_location)).setText("(" + str3 + ")");
        ((TextView) inflate.findViewById(R.id.txt_ip)).setText(str4);
        ((TextView) inflate.findViewById(R.id.txt_vlan)).setText(str5);
        ((TextView) inflate.findViewById(R.id.txt_make)).setText(str7);
        textView.setText(str8);
        textView.setPaintFlags(8);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str9 = str4;
        final String str10 = str5;
        final String str11 = str6;
        AnonymousClass11 r0 = new StringRequest(1, getString(R.string.serverip) + "report/peak_traffic2.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        jSONObject.getString("DESC");
                        JSONArray jSONArray2 = jSONObject.getJSONArray("ROWSET");
                        LinearLayout linearLayout = new LinearLayout(Report_Traffic_Tip_Cum_Activity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                        layoutParams.setMargins(0, 10, 0, 0);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setOrientation(1);
                        TextView textView = new TextView(Report_Traffic_Tip_Cum_Activity.this);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        textView.setText("UPLINK INTERFACE : " + jSONObject.getString("DESC").toUpperCase());
                        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView.setPaintFlags(8);
                        textView.setGravity(17);
                        textView.setPadding(15, 10, 15, 5);
                        textView.setTypeface((Typeface) null, 1);
                        textView.setTextSize(0, Report_Traffic_Tip_Cum_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        LinearLayout linearLayout2 = new LinearLayout(Report_Traffic_Tip_Cum_Activity.this);
                        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        linearLayout2.setOrientation(0);
                        linearLayout2.setGravity(5);
                        linearLayout2.setPadding(0, 5, 15, 5);
                        TextView textView2 = new TextView(Report_Traffic_Tip_Cum_Activity.this);
                        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        textView2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_blue, 0, 0, 0);
                        textView2.setText("IN-TRAFFIC (Kbps)");
                        textView2.setGravity(17);
                        textView2.setTextColor(-16776961);
                        textView2.setPaintFlags(8);
                        textView2.setTypeface((Typeface) null, 1);
                        textView2.setTextSize(0, Report_Traffic_Tip_Cum_Activity.this.getResources().getDimension(R.dimen.smalltext));
                        TextView textView3 = new TextView(Report_Traffic_Tip_Cum_Activity.this);
                        textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        textView3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_red, 0, 0, 0);
                        textView3.setText("OUT-TRAFFIC (Kbps)");
                        textView3.setGravity(17);
                        textView3.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView3.setPaintFlags(8);
                        textView3.setTypeface((Typeface) null, 1);
                        textView3.setTextSize(0, Report_Traffic_Tip_Cum_Activity.this.getResources().getDimension(R.dimen.smalltext));
                        linearLayout2.addView(textView2);
                        linearLayout2.addView(textView3);
                        linearLayout.addView(textView);
                        linearLayout.addView(linearLayout2);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                            int i3 = jSONObject2.getInt("MAX_INTRAFFIC");
                            int i4 = jSONObject2.getInt("MAX_OUTTRAFFIC");
                            arrayList3.add(jSONObject2.getString("DATE"));
                            float f = (float) i2;
                            arrayList.add(new Entry(f, (float) i3));
                            arrayList2.add(new Entry(f, (float) i4));
                        }
                        LineDataSet lineDataSet = new LineDataSet(arrayList, "IN-TRAFFIC");
                        lineDataSet.setColor(-16776961);
                        lineDataSet.setDrawFilled(true);
                        lineDataSet.setFillColor(Color.parseColor("#cce6ff"));
                        lineDataSet.setDrawValues(false);
                        lineDataSet.setDrawCircles(false);
                        LineDataSet lineDataSet2 = new LineDataSet(arrayList2, "OUT-TRAFFIC");
                        lineDataSet2.setColor(SupportMenu.CATEGORY_MASK);
                        lineDataSet2.setDrawFilled(true);
                        lineDataSet2.setFillColor(Color.parseColor("#e6ccff"));
                        lineDataSet2.setDrawValues(false);
                        lineDataSet2.setDrawCircles(false);
                        LineData lineData = new LineData(lineDataSet, lineDataSet2);
                        LineChart lineChart = new LineChart(Report_Traffic_Tip_Cum_Activity.this);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, i2);
                        layoutParams2.setMargins(5, 5, 5, 10);
                        lineChart.setLayoutParams(layoutParams2);
                        MyMarkerView myMarkerView = new MyMarkerView(Report_Traffic_Tip_Cum_Activity.this, R.layout.custom_marker_view, "Kbps", arrayList3);
                        myMarkerView.setChartView(lineChart);
                        lineChart.setMarker(myMarkerView);
                        lineChart.getAxisRight().setDrawLabels(false);
                        lineChart.getDescription().setEnabled(false);
                        lineChart.getLegend().setEnabled(false);
                        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        lineChart.getXAxis().setDrawGridLines(false);
                        lineChart.getXAxis().setAxisMinimum(0.0f);
                        lineChart.getXAxis().setLabelCount(arrayList3.size(), false);
                        XAxis xAxis = lineChart.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter((Collection<String>) arrayList3));
                        xAxis.setLabelRotationAngle(-90.0f);
                        lineChart.getAxisLeft().setAxisMinimum(0.0f);
                        lineChart.getAxisLeft().setGranularityEnabled(true);
                        lineChart.getAxisLeft().setGranularity(10000.0f);
                        lineChart.getAxisLeft().setDrawGridLines(false);
                        lineChart.getAxisLeft().setLabelCount(arrayList3.size(), false);
                        lineChart.getAxisRight().setDrawGridLines(false);
                        lineChart.animateX(1000);
                        lineChart.setData(lineData);
                        lineChart.invalidate();
                        linearLayout.addView(lineChart);
                        linearLayout.addView(linearLayout);
                    }
                    imageView2.setVisibility(0);
                    linearLayout.setVisibility(0);
                    Report_Traffic_Tip_Cum_Activity.this.traffic_dialog.show();
                    Report_Traffic_Tip_Cum_Activity.this.traffic_dialog.getWindow().setBackgroundDrawableResource(R.drawable.new_style);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Report_Traffic_Tip_Cum_Activity.this.progress_dialog.cancel();
                Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Cum_Activity.this.getApplicationContext()));
                Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("olt_ip", str9);
                hashMap.put("olt_vlan", str10);
                hashMap.put("nas_ip", str11);
                hashMap.put("username", Report_Traffic_Tip_Cum_Activity.this.Pref_Username);
                hashMap.put("random_key", Report_Traffic_Tip_Cum_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Report_Traffic_Tip_Cum_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void Btnfooter() {
        this.lay_btn.removeAllViews();
        int i = this.TOTAL_LIST_ITEMS;
        int i2 = this.NUM_ITEMS_PAGE;
        final int i3 = (i / i2) + (i % i2 == 0 ? 0 : 1);
        this.prev.setPadding(20, 15, 20, 15);
        this.next.setPadding(20, 15, 20, 15);
        this.btns = new TextView[i3];
        final int i4 = 0;
        while (i4 < i3) {
            this.btns[i4] = new TextView(this);
            this.btns[i4].setBackgroundColor(getResources().getColor(17170445));
            int i5 = i4 + 1;
            this.btns[i4].setText("" + i5);
            this.btns[i4].setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.btns[i4].setTypeface(Typeface.DEFAULT_BOLD);
            this.btns[i4].setGravity(17);
            this.btns[i4].setPadding(20, 15, 20, 15);
            this.btns[i4].setTextSize(0, getResources().getDimension(R.dimen.smalltext));
            this.btns[i4].setBackgroundResource(R.drawable.button01);
            this.lay_btn.addView(this.btns[i4], new LinearLayout.LayoutParams(-2, -1));
            this.next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Cum_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Report_Traffic_Tip_Cum_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity.loadList(report_Traffic_Tip_Cum_Activity.countt + 1);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity2.CheckBtnBackGroud(report_Traffic_Tip_Cum_Activity2.countt + 1);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity3 = Report_Traffic_Tip_Cum_Activity.this;
                    int unused = report_Traffic_Tip_Cum_Activity3.countt = report_Traffic_Tip_Cum_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Cum_Activity.this.countt == 0) {
                        Toast.makeText(Report_Traffic_Tip_Cum_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity.loadList(report_Traffic_Tip_Cum_Activity.countt - 1);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity2.CheckBtnBackGroud(report_Traffic_Tip_Cum_Activity2.countt - 1);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity3 = Report_Traffic_Tip_Cum_Activity.this;
                    int unused = report_Traffic_Tip_Cum_Activity3.countt = report_Traffic_Tip_Cum_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Cum_Activity.this.loadList(i4);
                    Report_Traffic_Tip_Cum_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Report_Traffic_Tip_Cum_Activity.this.countt = i4;
                }
            });
            i4 = i5;
        }
    }

    /* access modifiers changed from: private */
    public void loadList(int i) {
        final JSONArray jSONArray = new JSONArray();
        int i2 = i * this.NUM_ITEMS_PAGE;
        int i3 = i2;
        while (i3 < this.NUM_ITEMS_PAGE + i2 && i3 < this.Row_Array.length()) {
            try {
                jSONArray.put(this.Row_Array.getJSONObject(i3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i3++;
        }
        CustomBaseAdapter4 customBaseAdapter4 = new CustomBaseAdapter4(getApplicationContext(), jSONArray, i2, this);
        this.adapter = customBaseAdapter4;
        this.listview.setAdapter(customBaseAdapter4);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String charSequence = ((TextView) view.findViewById(R.id.txt_srno)).getText().toString();
                try {
                    String string = jSONArray.getJSONObject(i).getString("FMS_FIRM_NAME");
                    String string2 = jSONArray.getJSONObject(i).getString(CodePackage.LOCATION);
                    String string3 = jSONArray.getJSONObject(i).getString("OLT_IP");
                    String string4 = jSONArray.getJSONObject(i).getString("OLT_VLAN");
                    String string5 = jSONArray.getJSONObject(i).getString("NAS_IP");
                    String string6 = jSONArray.getJSONObject(i).getString("OLT_MAKE");
                    String string7 = jSONArray.getJSONObject(i).getString("CONNECTION");
                    if (jSONArray.getJSONObject(i).getString("STATUS").equals("TRUE")) {
                        Report_Traffic_Tip_Cum_Activity.this.get_traffic1(charSequence, string, string2, string3, string4, string5, string6, string7);
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText("PMS ERROR\n\nOlt Is Not PMS Configured\nPlease Ask Your Nodal Officer To Configure PMS\n\nPath : ADMIN -> PMS SETTING");
                    Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void CheckBtnBackGroud(int i) {
        int i2 = this.TOTAL_LIST_ITEMS;
        int i3 = this.NUM_ITEMS_PAGE;
        int i4 = (i2 / i3) + (i2 % i3 == 0 ? 0 : 1);
        this.title.setText("Page " + (i + 1) + " of " + i4 + " Pages");
        for (int i5 = 0; i5 < i4; i5++) {
            if (i5 == i) {
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorwhite));
            } else {
                this.btns[i5].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorblack));
            }
        }
    }

    private void Btnfooter1(final JSONArray jSONArray) {
        final int length = (jSONArray.length() / this.NUM_ITEMS_PAGE) + (jSONArray.length() % this.NUM_ITEMS_PAGE == 0 ? 0 : 1);
        this.prev.setPadding(20, 15, 20, 15);
        this.next.setPadding(20, 15, 20, 15);
        this.lay_btn.removeAllViews();
        this.btns = new TextView[length];
        final int i = 0;
        while (i < length) {
            this.btns[i] = new TextView(this);
            this.btns[i].setBackgroundColor(getResources().getColor(17170445));
            int i2 = i + 1;
            this.btns[i].setText("" + i2);
            this.btns[i].setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.btns[i].setTypeface(Typeface.DEFAULT_BOLD);
            this.btns[i].setGravity(17);
            this.btns[i].setPadding(20, 15, 20, 15);
            this.btns[i].setTextSize(0, getResources().getDimension(R.dimen.smalltext));
            this.btns[i].setBackgroundResource(R.drawable.button01);
            this.lay_btn.addView(this.btns[i], new LinearLayout.LayoutParams(-2, -1));
            this.next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Cum_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Report_Traffic_Tip_Cum_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity.loadList1(report_Traffic_Tip_Cum_Activity.search_count + 1, jSONArray);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity2.CheckBtnBackGroud1(report_Traffic_Tip_Cum_Activity2.search_count + 1, jSONArray);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity3 = Report_Traffic_Tip_Cum_Activity.this;
                    int unused = report_Traffic_Tip_Cum_Activity3.search_count = report_Traffic_Tip_Cum_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Cum_Activity.this.search_count == 0) {
                        Toast.makeText(Report_Traffic_Tip_Cum_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity.loadList1(report_Traffic_Tip_Cum_Activity.search_count - 1, jSONArray);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity2 = Report_Traffic_Tip_Cum_Activity.this;
                    report_Traffic_Tip_Cum_Activity2.CheckBtnBackGroud1(report_Traffic_Tip_Cum_Activity2.search_count - 1, jSONArray);
                    Report_Traffic_Tip_Cum_Activity report_Traffic_Tip_Cum_Activity3 = Report_Traffic_Tip_Cum_Activity.this;
                    int unused = report_Traffic_Tip_Cum_Activity3.search_count = report_Traffic_Tip_Cum_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Cum_Activity.this.loadList1(i, jSONArray);
                    Report_Traffic_Tip_Cum_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Report_Traffic_Tip_Cum_Activity.this.search_count = i;
                }
            });
            i = i2;
        }
    }

    /* access modifiers changed from: private */
    public void loadList1(int i, JSONArray jSONArray) {
        final JSONArray jSONArray2 = new JSONArray();
        int i2 = i * this.NUM_ITEMS_PAGE;
        int i3 = i2;
        while (i3 < this.NUM_ITEMS_PAGE + i2 && i3 < jSONArray.length()) {
            try {
                jSONArray2.put(jSONArray.getJSONObject(i3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i3++;
        }
        CustomBaseAdapter4 customBaseAdapter4 = new CustomBaseAdapter4(getApplicationContext(), jSONArray2, i2, this);
        this.adapter = customBaseAdapter4;
        this.listview.setAdapter(customBaseAdapter4);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                String charSequence = ((TextView) view.findViewById(R.id.txt_srno)).getText().toString();
                try {
                    String string = jSONArray2.getJSONObject(i).getString("FMS_FIRM_NAME");
                    String string2 = jSONArray2.getJSONObject(i).getString(CodePackage.LOCATION);
                    String string3 = jSONArray2.getJSONObject(i).getString("OLT_IP");
                    String string4 = jSONArray2.getJSONObject(i).getString("OLT_VLAN");
                    String string5 = jSONArray2.getJSONObject(i).getString("NAS_IP");
                    String string6 = jSONArray2.getJSONObject(i).getString("OLT_MAKE");
                    String string7 = jSONArray2.getJSONObject(i).getString("CONNECTION");
                    if (jSONArray2.getJSONObject(i).getString("STATUS").equals("TRUE")) {
                        Report_Traffic_Tip_Cum_Activity.this.get_traffic1(charSequence, string, string2, string3, string4, string5, string6, string7);
                        return;
                    }
                    Report_Traffic_Tip_Cum_Activity.this.txt_alert.setText("PMS ERROR\n\nOlt Is Not PMS Configured\nPlease Ask Your Nodal Officer To Configure PMS\n\nPath : ADMIN -> PMS SETTING");
                    Report_Traffic_Tip_Cum_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void CheckBtnBackGroud1(int i, JSONArray jSONArray) {
        int length = (jSONArray.length() / this.NUM_ITEMS_PAGE) + (jSONArray.length() % this.NUM_ITEMS_PAGE == 0 ? 0 : 1);
        this.title.setText("Page " + (i + 1) + " of " + length + " Pages");
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == i) {
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorwhite));
            } else {
                this.btns[i2].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorblack));
            }
        }
    }
}
