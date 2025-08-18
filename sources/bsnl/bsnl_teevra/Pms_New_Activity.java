package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pms_New_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Circle_name;
    /* access modifiers changed from: private */
    public JSONArray NOTIFY;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    /* access modifiers changed from: private */
    public String Pref_fms_username;
    /* access modifiers changed from: private */
    public String Ssa_name;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay1;
    /* access modifiers changed from: private */
    public RelativeLayout lay_alarm;
    private LinearLayout lay_pms;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_baf;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_brief1;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_brief2;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_tip;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    private Spannable span_baf;
    private Spannable span_tip;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    /* access modifiers changed from: private */
    public TextView txt_alarm;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;
    private TextView txt_pms_baf;
    /* access modifiers changed from: private */
    public TextView txt_pms_baf_disabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_baf_enabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_baf_total;
    private TextView txt_pms_tip;
    /* access modifiers changed from: private */
    public TextView txt_pms_tip_disabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_tip_enabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_tip_total;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_new_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay_alarm);
        this.lay_alarm = relativeLayout;
        relativeLayout.setVisibility(8);
        this.txt_alarm = (TextView) findViewById(R.id.txt_alarm);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.lay_pms_brief1 = (LinearLayout) findViewById(R.id.lay_pms_brief1);
        this.lay_pms_brief2 = (LinearLayout) findViewById(R.id.lay_pms_brief2);
        this.lay_pms = (LinearLayout) findViewById(R.id.lay_pms);
        this.lay_pms_tip = (LinearLayout) findViewById(R.id.lay_pms_tip);
        this.lay_pms_baf = (LinearLayout) findViewById(R.id.lay_pms_baf);
        this.lay_pms_brief2.setVisibility(8);
        this.txt_pms_tip = (TextView) findViewById(R.id.txt_pms_tip);
        this.txt_pms_tip_enabled = (TextView) findViewById(R.id.txt_pms_tip_enabled);
        this.txt_pms_tip_disabled = (TextView) findViewById(R.id.txt_pms_tip_disabled);
        this.txt_pms_tip_total = (TextView) findViewById(R.id.txt_pms_tip_total);
        this.txt_pms_baf = (TextView) findViewById(R.id.txt_pms_baf);
        this.txt_pms_baf_enabled = (TextView) findViewById(R.id.txt_pms_baf_enabled);
        this.txt_pms_baf_disabled = (TextView) findViewById(R.id.txt_pms_baf_disabled);
        this.txt_pms_baf_total = (TextView) findViewById(R.id.txt_pms_baf_total);
        SpannableString spannableString = new SpannableString(this.txt_pms_tip.getText());
        this.span_tip = spannableString;
        spannableString.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_pms_tip.setText(this.span_tip);
        SpannableString spannableString2 = new SpannableString(this.txt_pms_baf.getText());
        this.span_baf = spannableString2;
        spannableString2.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_pms_baf.setText(this.span_baf);
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
        this.lay_pms_brief1.post(new Runnable() {
            public void run() {
                int measuredWidth = Pms_New_Activity.this.lay_pms_brief1.getMeasuredWidth();
                Pms_New_Activity pms_New_Activity = Pms_New_Activity.this;
                LinearLayout unused = pms_New_Activity.lay1 = (LinearLayout) pms_New_Activity.findViewById(R.id.lay1);
                Pms_New_Activity.this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((float) measuredWidth) / 2.5f)));
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass4 r5 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Pms_New_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Pms_New_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                    Pms_New_Activity pms_New_Activity = Pms_New_Activity.this;
                    Pms_New_Activity pms_New_Activity2 = Pms_New_Activity.this;
                    ArrayAdapter unused = pms_New_Activity.adapter_circle = new ArrayAdapter(pms_New_Activity2, R.layout.spinner_item, pms_New_Activity2.circle_element);
                    Pms_New_Activity.this.sp_circle.setAdapter(Pms_New_Activity.this.adapter_circle);
                    Pms_New_Activity.this.sp_circle.setSelection(Pms_New_Activity.this.adapter_circle.getPosition(Pms_New_Activity.this.Pref_Circle));
                    if (Pms_New_Activity.this.Pref_Access_Level.equals("SSA") || Pms_New_Activity.this.Pref_Access_Level.equals("BA") || Pms_New_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                        Pms_New_Activity.this.sp_circle.setEnabled(false);
                        Pms_New_Activity.this.sp_circle.setAlpha(0.5f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Pms_New_Activity pms_New_Activity = Pms_New_Activity.this;
                Pms_New_Activity pms_New_Activity2 = Pms_New_Activity.this;
                ArrayAdapter unused = pms_New_Activity.adapter_circle = new ArrayAdapter(pms_New_Activity2, R.layout.spinner_item, pms_New_Activity2.circle_element);
                Pms_New_Activity.this.sp_circle.setAdapter(Pms_New_Activity.this.adapter_circle);
                Pms_New_Activity.this.sp_circle.setSelection(Pms_New_Activity.this.adapter_circle.getPosition(Pms_New_Activity.this.Pref_Circle));
                if (Pms_New_Activity.this.Pref_Access_Level.equals("SSA") || Pms_New_Activity.this.Pref_Access_Level.equals("BA") || Pms_New_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Pms_New_Activity.this.sp_circle.setEnabled(false);
                    Pms_New_Activity.this.sp_circle.setAlpha(0.5f);
                }
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Pms_New_Activity.this.getApplicationContext());
                Pms_New_Activity.this.progress_dialog.cancel();
                Pms_New_Activity.this.txt_alert.setText(onErrorResponse);
                Pms_New_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Pms_New_Activity.this.Pref_Circle);
                hashMap.put("access", Pms_New_Activity.this.Pref_Access_Level);
                hashMap.put("username", Pms_New_Activity.this.Pref_Username);
                hashMap.put("random_key", Pms_New_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Pms_New_Activity.this.androidId);
                return hashMap;
            }
        };
        r5.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r5);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Pms_New_Activity.this.ssa_element = new ArrayList();
                Pms_New_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Pms_New_Activity.this.sp_circle.getSelectedItem().toString();
                Pms_New_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Pms_New_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Pms_New_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Pms_New_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Pms_New_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                            ArrayAdapter unused = Pms_New_Activity.this.adapter_ssa = new ArrayAdapter(Pms_New_Activity.this, R.layout.spinner_item, Pms_New_Activity.this.ssa_element);
                            Pms_New_Activity.this.sp_ssa.setAdapter(Pms_New_Activity.this.adapter_ssa);
                            if (obj.equals(Pms_New_Activity.this.Pref_Circle)) {
                                Pms_New_Activity.this.sp_ssa.setSelection(Pms_New_Activity.this.adapter_ssa.getPosition(Pms_New_Activity.this.Pref_SSA));
                            }
                            if (Pms_New_Activity.this.Pref_Access_Level.equals("SSA")) {
                                Pms_New_Activity.this.sp_ssa.setEnabled(false);
                                Pms_New_Activity.this.sp_ssa.setAlpha(0.5f);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ArrayAdapter unused = Pms_New_Activity.this.adapter_ssa = new ArrayAdapter(Pms_New_Activity.this, R.layout.spinner_item, Pms_New_Activity.this.ssa_element);
                        Pms_New_Activity.this.sp_ssa.setAdapter(Pms_New_Activity.this.adapter_ssa);
                        if (obj.equals(Pms_New_Activity.this.Pref_Circle)) {
                            Pms_New_Activity.this.sp_ssa.setSelection(Pms_New_Activity.this.adapter_ssa.getPosition(Pms_New_Activity.this.Pref_SSA));
                        }
                        if (Pms_New_Activity.this.Pref_Role.equals("NODAL")) {
                            Pms_New_Activity.this.sp_ssa.setEnabled(false);
                            Pms_New_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Pms_New_Activity.this.getApplicationContext());
                        Pms_New_Activity.this.progress_dialog.cancel();
                        Pms_New_Activity.this.txt_alert.setText(onErrorResponse);
                        Pms_New_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Pms_New_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Pms_New_Activity.this.Pref_SSA);
                        hashMap.put("username", Pms_New_Activity.this.Pref_Username);
                        hashMap.put("random_key", Pms_New_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Pms_New_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.imageView.setOnClickListener(this);
        this.btn_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        String str;
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.btn_submit) {
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT CIRCLE", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT SSA", getApplicationContext());
            } else {
                this.lay_pms_brief2.setVisibility(8);
                this.lay_alarm.setVisibility(8);
                if (this.Pref_Designation.contains("FRANCHISEE")) {
                    this.txt_header.setText("CIRCLE : " + this.Circle_name + " | SSA : " + this.Ssa_name + "\n(" + this.Pref_fms_firmname + ")");
                    str = "TIP";
                } else {
                    this.txt_header.setText("CIRCLE : " + this.Circle_name + " | SSA : " + this.Ssa_name);
                    str = "BSNL";
                }
                final String str2 = str;
                this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass8 r0 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_count_new1.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Pms_New_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            JSONObject jSONObject2 = jSONObject.getJSONObject("TIP");
                            JSONObject jSONObject3 = jSONObject.getJSONObject("BAF");
                            JSONArray unused = Pms_New_Activity.this.NOTIFY = jSONObject.getJSONArray("NOTIFY");
                            Pms_New_Activity.this.txt_alarm.setText("" + Pms_New_Activity.this.NOTIFY.length());
                            String string = jSONObject2.getString("ONLINE");
                            String string2 = jSONObject2.getString("OFFLINE");
                            String string3 = jSONObject2.getString("TOTAL");
                            String string4 = jSONObject3.getString("ONLINE");
                            String string5 = jSONObject3.getString("OFFLINE");
                            String string6 = jSONObject3.getString("TOTAL");
                            if (jSONObject2.getInt("ONLINE") > 0) {
                                Pms_New_Activity.this.txt_pms_tip_enabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorGreen));
                            } else {
                                Pms_New_Activity.this.txt_pms_tip_enabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorRed));
                            }
                            if (jSONObject2.getInt("OFFLINE") > 0) {
                                Pms_New_Activity.this.txt_pms_tip_disabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorRed));
                            } else {
                                Pms_New_Activity.this.txt_pms_tip_disabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Pms_New_Activity.this.txt_pms_tip_enabled.setText(string);
                            Pms_New_Activity.this.txt_pms_tip_disabled.setText(string2);
                            Pms_New_Activity.this.txt_pms_tip_total.setText(string3);
                            if (jSONObject3.getInt("ONLINE") > 0) {
                                Pms_New_Activity.this.txt_pms_baf_enabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorGreen));
                            } else {
                                Pms_New_Activity.this.txt_pms_baf_enabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorRed));
                            }
                            if (jSONObject3.getInt("OFFLINE") > 0) {
                                Pms_New_Activity.this.txt_pms_baf_disabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorRed));
                            } else {
                                Pms_New_Activity.this.txt_pms_baf_disabled.setTextColor(Pms_New_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            Pms_New_Activity.this.txt_pms_baf_enabled.setText(string4);
                            Pms_New_Activity.this.txt_pms_baf_disabled.setText(string5);
                            Pms_New_Activity.this.txt_pms_baf_total.setText(string6);
                            Pms_New_Activity.this.lay_pms_brief2.setVisibility(0);
                            if (jSONObject2.getInt("TOTAL") > 0) {
                                Pms_New_Activity.this.lay_pms_tip.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Pms_New_Activity.this, PMS_Tip_New_Activity.class);
                                        intent.putExtra("ELEMENT", "TIP");
                                        intent.putExtra("CIRCLE", Pms_New_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Pms_New_Activity.this.Ssa_name);
                                        intent.putExtra("USER", str2);
                                        Pms_New_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            if (jSONObject3.getInt("TOTAL") > 0) {
                                Pms_New_Activity.this.lay_pms_baf.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Pms_New_Activity.this, PMS_Tip_New_Activity.class);
                                        intent.putExtra("ELEMENT", "BAF");
                                        intent.putExtra("CIRCLE", Pms_New_Activity.this.Circle_name);
                                        intent.putExtra("SSA", Pms_New_Activity.this.Ssa_name);
                                        intent.putExtra("USER", str2);
                                        Pms_New_Activity.this.startActivity(intent);
                                    }
                                });
                            }
                            Pms_New_Activity.this.lay_pms_brief2.setVisibility(0);
                            Pms_New_Activity.this.lay_alarm.setVisibility(0);
                            if (Pms_New_Activity.this.NOTIFY.length() > 0) {
                                Pms_New_Activity.this.lay_alarm.setOnClickListener(Pms_New_Activity.this);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Pms_New_Activity.this.progress_dialog.cancel();
                        Pms_New_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Pms_New_Activity.this.getApplicationContext()));
                        Pms_New_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Pms_New_Activity.this.Circle_name);
                        hashMap.put("ssa", Pms_New_Activity.this.Ssa_name);
                        hashMap.put("user", str2);
                        hashMap.put("fms_username", Pms_New_Activity.this.Pref_fms_username);
                        hashMap.put("username", Pms_New_Activity.this.Pref_Username);
                        hashMap.put("random_key", Pms_New_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Pms_New_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        } else if (view.getId() == R.id.lay_alarm) {
            Intent intent2 = new Intent(this, PMS_Notification_Activity.class);
            intent2.putExtra("RESPONSE", this.NOTIFY.toString());
            startActivity(intent2);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashBoard_New.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
