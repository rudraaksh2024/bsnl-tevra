package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Network_Status_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Circle_name;
    private String Grand_Bsnl;
    private String Grand_Fra;
    private String Grand_Total;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String Ssa_name;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private JSONArray bbnl_olt_down_array;
    private JSONArray bng_down_array;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    private JSONArray dslam_down_array;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public ImageView img_share;
    /* access modifiers changed from: private */
    public LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    /* access modifiers changed from: private */
    public LinearLayout lay3;
    /* access modifiers changed from: private */
    public LinearLayout lay4;
    /* access modifiers changed from: private */
    public LinearLayout lay5;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_baf;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_bbnlolt;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_bng;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_bsnlolt;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_dslam;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_lmg;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_mng;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_oclan;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_rpr;
    /* access modifiers changed from: private */
    public LinearLayout lay_network_status_tipolt;
    private LinearLayout lay_nms;
    /* access modifiers changed from: private */
    public LinearLayout lay_nms_brief1;
    /* access modifiers changed from: private */
    public LinearLayout lay_nms_brief2;
    private JSONArray lmg_down_array;
    private JSONArray oclan_down_array;
    private JSONArray olt_down_array;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private JSONArray rpr_down_array;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    private Spannable span_b;
    private Spannable span_baf;
    private Spannable span_bbnlolt;
    private Spannable span_bng;
    private Spannable span_bsnlolt;
    private Spannable span_dslam;
    private Spannable span_lmg;
    private Spannable span_mng;
    private Spannable span_oclan;
    private Spannable span_rpr;
    private Spannable span_tip;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    private JSONArray tip_baf_down_array;
    private JSONArray tip_down_array;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_baf;
    /* access modifiers changed from: private */
    public TextView txt_baf_offline;
    /* access modifiers changed from: private */
    public TextView txt_baf_online;
    /* access modifiers changed from: private */
    public TextView txt_baf_total;
    /* access modifiers changed from: private */
    public TextView txt_baf_unmanaged;
    private TextView txt_bbnlolt;
    /* access modifiers changed from: private */
    public TextView txt_bbnlolt_offline;
    /* access modifiers changed from: private */
    public TextView txt_bbnlolt_online;
    /* access modifiers changed from: private */
    public TextView txt_bbnlolt_total;
    /* access modifiers changed from: private */
    public TextView txt_bbnlolt_unmanaged;
    private TextView txt_bng;
    /* access modifiers changed from: private */
    public TextView txt_bng_offline;
    /* access modifiers changed from: private */
    public TextView txt_bng_online;
    /* access modifiers changed from: private */
    public TextView txt_bng_total;
    /* access modifiers changed from: private */
    public TextView txt_bng_unmanaged;
    private TextView txt_bsnlolt;
    /* access modifiers changed from: private */
    public TextView txt_bsnlolt_offline;
    /* access modifiers changed from: private */
    public TextView txt_bsnlolt_online;
    /* access modifiers changed from: private */
    public TextView txt_bsnlolt_total;
    /* access modifiers changed from: private */
    public TextView txt_bsnlolt_unmanaged;
    private TextView txt_dslam;
    /* access modifiers changed from: private */
    public TextView txt_dslam_offline;
    /* access modifiers changed from: private */
    public TextView txt_dslam_online;
    /* access modifiers changed from: private */
    public TextView txt_dslam_total;
    /* access modifiers changed from: private */
    public TextView txt_dslam_unmanaged;
    private TextView txt_header;
    private TextView txt_lmg;
    /* access modifiers changed from: private */
    public TextView txt_lmg_offline;
    /* access modifiers changed from: private */
    public TextView txt_lmg_online;
    /* access modifiers changed from: private */
    public TextView txt_lmg_total;
    /* access modifiers changed from: private */
    public TextView txt_lmg_unmanaged;
    private TextView txt_mng;
    /* access modifiers changed from: private */
    public TextView txt_mng_offline;
    /* access modifiers changed from: private */
    public TextView txt_mng_online;
    /* access modifiers changed from: private */
    public TextView txt_mng_total;
    /* access modifiers changed from: private */
    public TextView txt_mng_unmanaged;
    private TextView txt_oclan;
    /* access modifiers changed from: private */
    public TextView txt_oclan_offline;
    /* access modifiers changed from: private */
    public TextView txt_oclan_online;
    /* access modifiers changed from: private */
    public TextView txt_oclan_total;
    /* access modifiers changed from: private */
    public TextView txt_oclan_unmanaged;
    private TextView txt_rpr;
    /* access modifiers changed from: private */
    public TextView txt_rpr_offline;
    /* access modifiers changed from: private */
    public TextView txt_rpr_online;
    /* access modifiers changed from: private */
    public TextView txt_rpr_total;
    /* access modifiers changed from: private */
    public TextView txt_rpr_unmanaged;
    private TextView txt_tip;
    /* access modifiers changed from: private */
    public TextView txt_tip_offline;
    /* access modifiers changed from: private */
    public TextView txt_tip_online;
    /* access modifiers changed from: private */
    public TextView txt_tip_total;
    /* access modifiers changed from: private */
    public TextView txt_tip_unmanaged;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.network_status_activity);
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
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.txt_bng_offline = (TextView) findViewById(R.id.txt_bng_offline);
        this.txt_bng_online = (TextView) findViewById(R.id.txt_bng_online);
        this.txt_bng_unmanaged = (TextView) findViewById(R.id.txt_bng_unmanaged);
        this.txt_bng_total = (TextView) findViewById(R.id.txt_bng_total);
        this.txt_bng = (TextView) findViewById(R.id.txt_bng);
        this.txt_rpr_offline = (TextView) findViewById(R.id.txt_rpr_offline);
        this.txt_rpr_online = (TextView) findViewById(R.id.txt_rpr_online);
        this.txt_rpr_unmanaged = (TextView) findViewById(R.id.txt_rpr_unmanaged);
        this.txt_rpr_total = (TextView) findViewById(R.id.txt_rpr_total);
        this.txt_rpr = (TextView) findViewById(R.id.txt_rpr);
        this.txt_mng_offline = (TextView) findViewById(R.id.txt_mng_offline);
        this.txt_mng_online = (TextView) findViewById(R.id.txt_mng_online);
        this.txt_mng_unmanaged = (TextView) findViewById(R.id.txt_mng_unmanaged);
        this.txt_mng_total = (TextView) findViewById(R.id.txt_mng_total);
        this.txt_mng = (TextView) findViewById(R.id.txt_mng);
        this.txt_oclan_offline = (TextView) findViewById(R.id.txt_oclan_offline);
        this.txt_oclan_online = (TextView) findViewById(R.id.txt_oclan_online);
        this.txt_oclan_unmanaged = (TextView) findViewById(R.id.txt_oclan_unmanaged);
        this.txt_oclan_total = (TextView) findViewById(R.id.txt_oclan_total);
        this.txt_oclan = (TextView) findViewById(R.id.txt_oclan);
        this.txt_dslam_offline = (TextView) findViewById(R.id.txt_dslam_offline);
        this.txt_dslam_online = (TextView) findViewById(R.id.txt_dslam_online);
        this.txt_dslam_unmanaged = (TextView) findViewById(R.id.txt_dslam_unmanaged);
        this.txt_dslam_total = (TextView) findViewById(R.id.txt_dslam_total);
        this.txt_dslam = (TextView) findViewById(R.id.txt_dslam);
        this.txt_bsnlolt_offline = (TextView) findViewById(R.id.txt_bsnlolt_offline);
        this.txt_bsnlolt_online = (TextView) findViewById(R.id.txt_bsnlolt_online);
        this.txt_bsnlolt_unmanaged = (TextView) findViewById(R.id.txt_bsnlolt_unmanaged);
        this.txt_bsnlolt_total = (TextView) findViewById(R.id.txt_bsnlolt_total);
        this.txt_bsnlolt = (TextView) findViewById(R.id.txt_bsnlolt);
        this.txt_bbnlolt_offline = (TextView) findViewById(R.id.txt_bbnlolt_offline);
        this.txt_bbnlolt_online = (TextView) findViewById(R.id.txt_bbnlolt_online);
        this.txt_bbnlolt_unmanaged = (TextView) findViewById(R.id.txt_bbnlolt_unmanaged);
        this.txt_bbnlolt_total = (TextView) findViewById(R.id.txt_bbnlolt_total);
        this.txt_bbnlolt = (TextView) findViewById(R.id.txt_bbnlolt);
        this.txt_tip_offline = (TextView) findViewById(R.id.txt_tip_offline);
        this.txt_tip_online = (TextView) findViewById(R.id.txt_tip_online);
        this.txt_tip_unmanaged = (TextView) findViewById(R.id.txt_tip_unmanaged);
        this.txt_tip_total = (TextView) findViewById(R.id.txt_tip_total);
        this.txt_tip = (TextView) findViewById(R.id.txt_tip);
        this.txt_lmg_offline = (TextView) findViewById(R.id.txt_lmg_offline);
        this.txt_lmg_online = (TextView) findViewById(R.id.txt_lmg_online);
        this.txt_lmg_unmanaged = (TextView) findViewById(R.id.txt_lmg_unmanaged);
        this.txt_lmg_total = (TextView) findViewById(R.id.txt_lmg_total);
        this.txt_lmg = (TextView) findViewById(R.id.txt_lmg);
        this.txt_baf_offline = (TextView) findViewById(R.id.txt_baf_offline);
        this.txt_baf_online = (TextView) findViewById(R.id.txt_baf_online);
        this.txt_baf_unmanaged = (TextView) findViewById(R.id.txt_baf_unmanaged);
        this.txt_baf_total = (TextView) findViewById(R.id.txt_baf_total);
        this.txt_baf = (TextView) findViewById(R.id.txt_baf);
        SpannableString spannableString = new SpannableString(this.txt_bng.getText());
        this.span_bng = spannableString;
        spannableString.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_bng.setText(this.span_bng);
        SpannableString spannableString2 = new SpannableString(this.txt_rpr.getText());
        this.span_rpr = spannableString2;
        spannableString2.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_rpr.setText(this.span_rpr);
        SpannableString spannableString3 = new SpannableString(this.txt_mng.getText());
        this.span_mng = spannableString3;
        spannableString3.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_mng.setText(this.span_mng);
        SpannableString spannableString4 = new SpannableString(this.txt_oclan.getText());
        this.span_oclan = spannableString4;
        spannableString4.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_oclan.setText(this.span_oclan);
        SpannableString spannableString5 = new SpannableString(this.txt_dslam.getText());
        this.span_dslam = spannableString5;
        spannableString5.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_dslam.setText(this.span_dslam);
        SpannableString spannableString6 = new SpannableString(this.txt_bsnlolt.getText());
        this.span_bsnlolt = spannableString6;
        spannableString6.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_bsnlolt.setText(this.span_bsnlolt);
        SpannableString spannableString7 = new SpannableString(this.txt_bbnlolt.getText());
        this.span_bbnlolt = spannableString7;
        spannableString7.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_bbnlolt.setText(this.span_bbnlolt);
        SpannableString spannableString8 = new SpannableString(this.txt_tip.getText());
        this.span_tip = spannableString8;
        spannableString8.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_tip.setText(this.span_tip);
        SpannableString spannableString9 = new SpannableString(this.txt_lmg.getText());
        this.span_lmg = spannableString9;
        spannableString9.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_lmg.setText(this.span_lmg);
        SpannableString spannableString10 = new SpannableString(this.txt_baf.getText());
        this.span_baf = spannableString10;
        spannableString10.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_baf.setText(this.span_baf);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_share);
        this.img_share = imageView2;
        imageView2.setVisibility(8);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.lay_nms_brief1 = (LinearLayout) findViewById(R.id.lay_nms_brief1);
        this.lay_nms_brief2 = (LinearLayout) findViewById(R.id.lay_nms_brief2);
        this.lay_nms = (LinearLayout) findViewById(R.id.lay_nms);
        this.lay_nms_brief2.setVisibility(8);
        this.lay_network_status_bng = (LinearLayout) findViewById(R.id.lay_network_status_bng);
        this.lay_network_status_rpr = (LinearLayout) findViewById(R.id.lay_network_status_rpr);
        this.lay_network_status_mng = (LinearLayout) findViewById(R.id.lay_network_status_mng);
        this.lay_network_status_oclan = (LinearLayout) findViewById(R.id.lay_network_status_oclan);
        this.lay_network_status_dslam = (LinearLayout) findViewById(R.id.lay_network_status_dslam);
        this.lay_network_status_bsnlolt = (LinearLayout) findViewById(R.id.lay_network_status_bsnlolt);
        this.lay_network_status_bbnlolt = (LinearLayout) findViewById(R.id.lay_network_status_bbnlolt);
        this.lay_network_status_tipolt = (LinearLayout) findViewById(R.id.lay_network_status_tipolt);
        this.lay_network_status_lmg = (LinearLayout) findViewById(R.id.lay_network_status_lmg);
        this.lay_network_status_baf = (LinearLayout) findViewById(R.id.lay_network_status_baf);
        this.lay_nms_brief1.post(new Runnable() {
            public void run() {
                int measuredWidth = Network_Status_Activity.this.lay_nms_brief1.getMeasuredWidth();
                Network_Status_Activity network_Status_Activity = Network_Status_Activity.this;
                LinearLayout unused = network_Status_Activity.lay1 = (LinearLayout) network_Status_Activity.findViewById(R.id.lay1);
                Network_Status_Activity network_Status_Activity2 = Network_Status_Activity.this;
                LinearLayout unused2 = network_Status_Activity2.lay2 = (LinearLayout) network_Status_Activity2.findViewById(R.id.lay2);
                Network_Status_Activity network_Status_Activity3 = Network_Status_Activity.this;
                LinearLayout unused3 = network_Status_Activity3.lay3 = (LinearLayout) network_Status_Activity3.findViewById(R.id.lay3);
                Network_Status_Activity network_Status_Activity4 = Network_Status_Activity.this;
                LinearLayout unused4 = network_Status_Activity4.lay4 = (LinearLayout) network_Status_Activity4.findViewById(R.id.lay4);
                Network_Status_Activity network_Status_Activity5 = Network_Status_Activity.this;
                LinearLayout unused5 = network_Status_Activity5.lay5 = (LinearLayout) network_Status_Activity5.findViewById(R.id.lay5);
                int i = (int) (((float) measuredWidth) / 2.5f);
                Network_Status_Activity.this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
                Network_Status_Activity.this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
                Network_Status_Activity.this.lay3.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
                Network_Status_Activity.this.lay4.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
                Network_Status_Activity.this.lay5.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass4 r4 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Network_Status_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Network_Status_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network_Status_Activity network_Status_Activity = Network_Status_Activity.this;
                Network_Status_Activity network_Status_Activity2 = Network_Status_Activity.this;
                ArrayAdapter unused = network_Status_Activity.adapter_circle = new ArrayAdapter(network_Status_Activity2, R.layout.spinner_item, network_Status_Activity2.circle_element);
                Network_Status_Activity.this.sp_circle.setAdapter(Network_Status_Activity.this.adapter_circle);
                Network_Status_Activity.this.sp_circle.setSelection(Network_Status_Activity.this.adapter_circle.getPosition(Network_Status_Activity.this.Pref_Circle));
                if (Network_Status_Activity.this.Pref_Access_Level.equals("SSA") || Network_Status_Activity.this.Pref_Access_Level.equals("BA") || Network_Status_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Network_Status_Activity.this.sp_circle.setEnabled(false);
                    Network_Status_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Network_Status_Activity.this.progress_dialog.cancel();
                Network_Status_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Network_Status_Activity.this.getApplicationContext()));
                Network_Status_Activity.this.error_dialog.show();
                Network_Status_Activity network_Status_Activity = Network_Status_Activity.this;
                Network_Status_Activity network_Status_Activity2 = Network_Status_Activity.this;
                ArrayAdapter unused = network_Status_Activity.adapter_circle = new ArrayAdapter(network_Status_Activity2, R.layout.spinner_item, network_Status_Activity2.circle_element);
                Network_Status_Activity.this.sp_circle.setAdapter(Network_Status_Activity.this.adapter_circle);
                if (Network_Status_Activity.this.Pref_Access_Level.equals("SSA") || Network_Status_Activity.this.Pref_Access_Level.equals("BA") || Network_Status_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Network_Status_Activity.this.sp_circle.setEnabled(false);
                    Network_Status_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Network_Status_Activity.this.Pref_Circle);
                hashMap.put("access", Network_Status_Activity.this.Pref_Access_Level);
                hashMap.put("username", Network_Status_Activity.this.Pref_Username);
                hashMap.put("random_key", Network_Status_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Network_Status_Activity.this.androidId);
                return hashMap;
            }
        };
        r4.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r4);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Network_Status_Activity.this.lay_nms_brief2.setVisibility(8);
                ArrayList unused = Network_Status_Activity.this.ssa_element = new ArrayList();
                Network_Status_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Network_Status_Activity.this.sp_circle.getSelectedItem().toString();
                Network_Status_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Network_Status_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Network_Status_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Network_Status_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Network_Status_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter unused = Network_Status_Activity.this.adapter_ssa = new ArrayAdapter(Network_Status_Activity.this, R.layout.spinner_item, Network_Status_Activity.this.ssa_element);
                        Network_Status_Activity.this.sp_ssa.setAdapter(Network_Status_Activity.this.adapter_ssa);
                        if (obj.equals(Network_Status_Activity.this.Pref_Circle)) {
                            Network_Status_Activity.this.sp_ssa.setSelection(Network_Status_Activity.this.adapter_ssa.getPosition(Network_Status_Activity.this.Pref_SSA));
                        }
                        if (Network_Status_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Network_Status_Activity.this.sp_ssa.setEnabled(false);
                            Network_Status_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Network_Status_Activity.this.progress_dialog.cancel();
                        Network_Status_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Network_Status_Activity.this.getApplicationContext()));
                        Network_Status_Activity.this.error_dialog.show();
                        ArrayAdapter unused = Network_Status_Activity.this.adapter_ssa = new ArrayAdapter(Network_Status_Activity.this, R.layout.spinner_item, Network_Status_Activity.this.ssa_element);
                        Network_Status_Activity.this.sp_ssa.setAdapter(Network_Status_Activity.this.adapter_ssa);
                        if (obj.equals(Network_Status_Activity.this.Pref_Circle)) {
                            Network_Status_Activity.this.sp_ssa.setSelection(Network_Status_Activity.this.adapter_ssa.getPosition(Network_Status_Activity.this.Pref_SSA));
                        }
                        if (Network_Status_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Network_Status_Activity.this.sp_ssa.setEnabled(false);
                            Network_Status_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Network_Status_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Network_Status_Activity.this.Pref_SSA);
                        hashMap.put("username", Network_Status_Activity.this.Pref_Username);
                        hashMap.put("random_key", Network_Status_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Network_Status_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.imageView.setOnClickListener(this);
        this.img_share.setOnClickListener(this);
        this.btn_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.img_share) {
            Bitmap createBitmap = Bitmap.createBitmap(this.lay_nms.getWidth(), this.lay_nms.getHeight(), Bitmap.Config.ARGB_8888);
            this.lay_nms.draw(new Canvas(createBitmap));
            new ShareImage().share(createBitmap, new File(getExternalCacheDir(), "network_glance.jpg"), this);
        } else if (view.getId() == R.id.btn_submit) {
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            this.img_share.setVisibility(8);
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT CIRCLE", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT SSA", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.lay_nms_brief2.setVisibility(8);
                this.txt_header.setText("CIRCLE : " + this.Circle_name + " | SSA : " + this.Ssa_name);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass8 r0 = new StringRequest(1, getString(R.string.serverip) + "network_glance.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        JSONObject jSONObject;
                        Network_Status_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject2 = new JSONObject(str);
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("BNG");
                            JSONObject jSONObject4 = jSONObject2.getJSONObject("RPR");
                            JSONObject jSONObject5 = jSONObject2.getJSONObject("MNG");
                            JSONObject jSONObject6 = jSONObject2.getJSONObject("OCLAN");
                            JSONObject jSONObject7 = jSONObject2.getJSONObject("DSLAM");
                            JSONObject jSONObject8 = jSONObject2.getJSONObject("LMG");
                            JSONObject jSONObject9 = jSONObject2.getJSONObject("TIP");
                            JSONObject jSONObject10 = jSONObject2.getJSONObject("TIP_BAF");
                            JSONObject jSONObject11 = jSONObject2.getJSONObject("BSNL_OLT");
                            JSONObject jSONObject12 = jSONObject2.getJSONObject("BBNL_OLT");
                            if (jSONObject3.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_bng.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "BNG");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject4.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_rpr.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "RPR");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject5.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_mng.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "MNG");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject6.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_oclan.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "OCLAN");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject7.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_dslam.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "DSLAM");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject8.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_lmg.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "LMG");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject9.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_tipolt.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "TIP");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject10.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_baf.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "BAF");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject11.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_bsnlolt.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "BSNL_OLT");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject12.getInt("TOTAL") > 0) {
                                Network_Status_Activity.this.lay_network_status_bbnlolt.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Network_Status_Activity.this, Network_Status_Display_Activity.class);
                                        intent.putExtra("ELEMENT", "BBNL_OLT");
                                        intent.putExtra("CIRCLE", Network_Status_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Network_Status_Activity.this.Ssa_name);
                                        Network_Status_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            JSONObject jSONObject13 = jSONObject10;
                            JSONObject jSONObject14 = jSONObject8;
                            if (jSONObject3.getInt("TOTAL") > 0) {
                                if (jSONObject3.getInt("OFFLINE") > 0) {
                                    jSONObject = jSONObject9;
                                    Network_Status_Activity.this.txt_bng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    jSONObject = jSONObject9;
                                    Network_Status_Activity.this.txt_bng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject3.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_bng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_bng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject3.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_bng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_bng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                jSONObject = jSONObject9;
                                Network_Status_Activity.this.txt_bng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_bng_offline.setText(jSONObject3.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_bng_unmanaged.setText(jSONObject3.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_bng_online.setText(jSONObject3.getString("ONLINE"));
                            Network_Status_Activity.this.txt_bng_total.setText(jSONObject3.getString("TOTAL"));
                            if (jSONObject4.getInt("TOTAL") > 0) {
                                if (jSONObject4.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_rpr_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_rpr_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject4.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_rpr_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_rpr_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject4.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_rpr_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_rpr_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_rpr_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_rpr_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_rpr_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_rpr_offline.setText(jSONObject4.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_rpr_unmanaged.setText(jSONObject4.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_rpr_online.setText(jSONObject4.getString("ONLINE"));
                            Network_Status_Activity.this.txt_rpr_total.setText(jSONObject4.getString("TOTAL"));
                            if (jSONObject5.getInt("TOTAL") > 0) {
                                if (jSONObject5.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_mng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_mng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject5.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_mng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_mng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject5.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_mng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_mng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_mng_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_mng_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_mng_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_mng_offline.setText(jSONObject5.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_mng_unmanaged.setText(jSONObject5.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_mng_online.setText(jSONObject5.getString("ONLINE"));
                            Network_Status_Activity.this.txt_mng_total.setText(jSONObject5.getString("TOTAL"));
                            if (jSONObject6.getInt("TOTAL") > 0) {
                                if (jSONObject6.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_oclan_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_oclan_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject6.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_oclan_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_oclan_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject6.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_oclan_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_oclan_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_oclan_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_oclan_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_oclan_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_oclan_offline.setText(jSONObject6.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_oclan_unmanaged.setText(jSONObject6.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_oclan_online.setText(jSONObject6.getString("ONLINE"));
                            Network_Status_Activity.this.txt_oclan_total.setText(jSONObject6.getString("TOTAL"));
                            if (jSONObject7.getInt("TOTAL") > 0) {
                                if (jSONObject7.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_dslam_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_dslam_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject7.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_dslam_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_dslam_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject7.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_dslam_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_dslam_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_dslam_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_dslam_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_dslam_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_dslam_offline.setText(jSONObject7.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_dslam_unmanaged.setText(jSONObject7.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_dslam_online.setText(jSONObject7.getString("ONLINE"));
                            Network_Status_Activity.this.txt_dslam_total.setText(jSONObject7.getString("TOTAL"));
                            if (jSONObject11.getInt("TOTAL") > 0) {
                                if (jSONObject11.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_bsnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_bsnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject11.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_bsnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_bsnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject11.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_bsnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_bsnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_bsnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bsnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bsnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_bsnlolt_offline.setText(jSONObject11.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_bsnlolt_unmanaged.setText(jSONObject11.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_bsnlolt_online.setText(jSONObject11.getString("ONLINE"));
                            Network_Status_Activity.this.txt_bsnlolt_total.setText(jSONObject11.getString("TOTAL"));
                            if (jSONObject12.getInt("TOTAL") > 0) {
                                if (jSONObject12.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_bbnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_bbnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject12.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_bbnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_bbnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject12.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_bbnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_bbnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_bbnlolt_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bbnlolt_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_bbnlolt_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_bbnlolt_offline.setText(jSONObject12.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_bbnlolt_unmanaged.setText(jSONObject12.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_bbnlolt_online.setText(jSONObject12.getString("ONLINE"));
                            Network_Status_Activity.this.txt_bbnlolt_total.setText(jSONObject12.getString("TOTAL"));
                            JSONObject jSONObject15 = jSONObject;
                            if (jSONObject15.getInt("TOTAL") > 0) {
                                if (jSONObject15.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_tip_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_tip_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject15.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_tip_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_tip_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject15.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_tip_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_tip_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_tip_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_tip_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_tip_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_tip_offline.setText(jSONObject15.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_tip_unmanaged.setText(jSONObject15.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_tip_online.setText(jSONObject15.getString("ONLINE"));
                            Network_Status_Activity.this.txt_tip_total.setText(jSONObject15.getString("TOTAL"));
                            JSONObject jSONObject16 = jSONObject14;
                            if (jSONObject16.getInt("TOTAL") > 0) {
                                if (jSONObject16.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_lmg_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_lmg_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject16.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_lmg_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_lmg_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject16.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_lmg_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_lmg_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_lmg_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_lmg_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_lmg_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_lmg_offline.setText(jSONObject16.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_lmg_unmanaged.setText(jSONObject16.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_lmg_online.setText(jSONObject16.getString("ONLINE"));
                            Network_Status_Activity.this.txt_lmg_total.setText(jSONObject16.getString("TOTAL"));
                            JSONObject jSONObject17 = jSONObject13;
                            if (jSONObject17.getInt("TOTAL") > 0) {
                                if (jSONObject17.getInt("OFFLINE") > 0) {
                                    Network_Status_Activity.this.txt_baf_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_baf_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject17.getInt("UNMANAGED") > 0) {
                                    Network_Status_Activity.this.txt_baf_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                } else {
                                    Network_Status_Activity.this.txt_baf_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                }
                                if (jSONObject17.getInt("ONLINE") > 0) {
                                    Network_Status_Activity.this.txt_baf_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                } else {
                                    Network_Status_Activity.this.txt_baf_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                }
                            } else {
                                Network_Status_Activity.this.txt_baf_offline.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_baf_unmanaged.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                                Network_Status_Activity.this.txt_baf_online.setTextColor(Network_Status_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Network_Status_Activity.this.txt_baf_offline.setText(jSONObject17.getString("OFFLINE"));
                            Network_Status_Activity.this.txt_baf_unmanaged.setText(jSONObject17.getString("UNMANAGED"));
                            Network_Status_Activity.this.txt_baf_online.setText(jSONObject17.getString("ONLINE"));
                            Network_Status_Activity.this.txt_baf_total.setText(jSONObject17.getString("TOTAL"));
                            Network_Status_Activity.this.lay_nms_brief2.setVisibility(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Network_Status_Activity.this.img_share.setVisibility(0);
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Network_Status_Activity.this.progress_dialog.cancel();
                        Network_Status_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Network_Status_Activity.this.getApplicationContext()));
                        Network_Status_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Network_Status_Activity.this.Circle_name);
                        hashMap.put("ssa", Network_Status_Activity.this.Ssa_name);
                        hashMap.put("username", Network_Status_Activity.this.Pref_Username);
                        hashMap.put("random_key", Network_Status_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Network_Status_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    private void share(Bitmap bitmap) {
        File file = new File(getApplicationContext().getExternalCacheDir(), File.separator + "Network_Glance.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            file.setReadable(true, false);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getApplicationContext(), "bsnl.bsnl_teevra.provider", file));
            intent.setFlags(268435456);
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard_New.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
