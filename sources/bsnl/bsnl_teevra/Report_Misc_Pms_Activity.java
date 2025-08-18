package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
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

public class Report_Misc_Pms_Activity extends AppCompatActivity implements View.OnClickListener {
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
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView imageView;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public ViewPager viewpager_pms;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_misc_pms_activity);
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
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay2);
        this.lay2 = linearLayout;
        linearLayout.setVisibility(8);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_pms);
        this.viewpager_pms = viewPager;
        viewPager.setOffscreenPageLimit(2);
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Report_Misc_Pms_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Report_Misc_Pms_Activity report_Misc_Pms_Activity = Report_Misc_Pms_Activity.this;
                Report_Misc_Pms_Activity report_Misc_Pms_Activity2 = Report_Misc_Pms_Activity.this;
                ArrayAdapter unused = report_Misc_Pms_Activity.adapter_circle = new ArrayAdapter(report_Misc_Pms_Activity2, R.layout.spinner_item, report_Misc_Pms_Activity2.circle_element);
                Report_Misc_Pms_Activity.this.sp_circle.setAdapter(Report_Misc_Pms_Activity.this.adapter_circle);
                Report_Misc_Pms_Activity.this.sp_circle.setSelection(Report_Misc_Pms_Activity.this.adapter_circle.getPosition(Report_Misc_Pms_Activity.this.Pref_Circle));
                if (Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("SSA") || Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("BA") || Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Misc_Pms_Activity.this.sp_circle.setEnabled(false);
                    Report_Misc_Pms_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                Report_Misc_Pms_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Misc_Pms_Activity.this.getApplicationContext()));
                Report_Misc_Pms_Activity.this.error_dialog.show();
                Report_Misc_Pms_Activity report_Misc_Pms_Activity = Report_Misc_Pms_Activity.this;
                Report_Misc_Pms_Activity report_Misc_Pms_Activity2 = Report_Misc_Pms_Activity.this;
                ArrayAdapter unused = report_Misc_Pms_Activity.adapter_circle = new ArrayAdapter(report_Misc_Pms_Activity2, R.layout.spinner_item, report_Misc_Pms_Activity2.circle_element);
                Report_Misc_Pms_Activity.this.sp_circle.setAdapter(Report_Misc_Pms_Activity.this.adapter_circle);
                if (Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("SSA") || Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("BA") || Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Misc_Pms_Activity.this.sp_circle.setEnabled(false);
                    Report_Misc_Pms_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Report_Misc_Pms_Activity.this.Pref_Circle);
                hashMap.put("access", Report_Misc_Pms_Activity.this.Pref_Access_Level);
                hashMap.put("username", Report_Misc_Pms_Activity.this.Pref_Username);
                hashMap.put("random_key", Report_Misc_Pms_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Report_Misc_Pms_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Report_Misc_Pms_Activity.this.ssa_element = new ArrayList();
                Report_Misc_Pms_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Report_Misc_Pms_Activity.this.sp_circle.getSelectedItem().toString();
                Report_Misc_Pms_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Report_Misc_Pms_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Report_Misc_Pms_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Report_Misc_Pms_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter unused = Report_Misc_Pms_Activity.this.adapter_ssa = new ArrayAdapter(Report_Misc_Pms_Activity.this, R.layout.spinner_item, Report_Misc_Pms_Activity.this.ssa_element);
                        Report_Misc_Pms_Activity.this.sp_ssa.setAdapter(Report_Misc_Pms_Activity.this.adapter_ssa);
                        if (obj.equals(Report_Misc_Pms_Activity.this.Pref_Circle)) {
                            Report_Misc_Pms_Activity.this.sp_ssa.setSelection(Report_Misc_Pms_Activity.this.adapter_ssa.getPosition(Report_Misc_Pms_Activity.this.Pref_SSA));
                        }
                        if (Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Misc_Pms_Activity.this.sp_ssa.setEnabled(false);
                            Report_Misc_Pms_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Report_Misc_Pms_Activity.this.getApplicationContext());
                        Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                        Report_Misc_Pms_Activity.this.txt_alert.setText(onErrorResponse);
                        Report_Misc_Pms_Activity.this.error_dialog.show();
                        ArrayAdapter unused = Report_Misc_Pms_Activity.this.adapter_ssa = new ArrayAdapter(Report_Misc_Pms_Activity.this, R.layout.spinner_item, Report_Misc_Pms_Activity.this.ssa_element);
                        Report_Misc_Pms_Activity.this.sp_ssa.setAdapter(Report_Misc_Pms_Activity.this.adapter_ssa);
                        if (Report_Misc_Pms_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Misc_Pms_Activity.this.sp_ssa.setEnabled(false);
                            Report_Misc_Pms_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Report_Misc_Pms_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Report_Misc_Pms_Activity.this.Pref_SSA);
                        hashMap.put("username", Report_Misc_Pms_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Misc_Pms_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Misc_Pms_Activity.this.androidId);
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

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_submit) {
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT CIRCLE", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT SSA", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.lay2.setVisibility(8);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass7 r0 = new StringRequest(1, getString(R.string.serverip) + "report_misc_pms_index.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                            Bundle bundle = new Bundle();
                            bundle.putString("response", jSONObject.toString());
                            bundle.putString("circle", Report_Misc_Pms_Activity.this.Circle_name);
                            bundle.putString("ssa", Report_Misc_Pms_Activity.this.Ssa_name);
                            Report_Misc_Pms_Activity report_Misc_Pms_Activity = Report_Misc_Pms_Activity.this;
                            report_Misc_Pms_Activity.setupViewPager(report_Misc_Pms_Activity.viewpager_pms, bundle);
                            Report_Misc_Pms_Activity.this.lay2.setVisibility(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Report_Misc_Pms_Activity.this.progress_dialog.cancel();
                        Report_Misc_Pms_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Misc_Pms_Activity.this.getApplicationContext()));
                        Report_Misc_Pms_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Report_Misc_Pms_Activity.this.Circle_name);
                        hashMap.put("ssa", Report_Misc_Pms_Activity.this.Ssa_name);
                        hashMap.put("username", Report_Misc_Pms_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Misc_Pms_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Misc_Pms_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setupViewPager(ViewPager viewPager, Bundle bundle) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        Report_Misc_Pms1_Fragment report_Misc_Pms1_Fragment = new Report_Misc_Pms1_Fragment();
        Report_Misc_Pms2_Fragment report_Misc_Pms2_Fragment = new Report_Misc_Pms2_Fragment();
        report_Misc_Pms1_Fragment.setArguments(bundle);
        report_Misc_Pms2_Fragment.setArguments(bundle);
        sectionPageAdapter.addFragments(report_Misc_Pms1_Fragment, "CHART");
        sectionPageAdapter.addFragments(report_Misc_Pms2_Fragment, "DETAIL");
        viewPager.setAdapter(sectionPageAdapter);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Report_Misc_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
