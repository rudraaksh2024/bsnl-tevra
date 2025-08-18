package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Manage_User_Privilege_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String[] access;
    /* access modifiers changed from: private */
    public String androidId;
    private String[] bol;
    private Button btn_update;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView img_header;
    /* access modifiers changed from: private */
    public Intent intent;
    /* access modifiers changed from: private */
    public LinearLayout lay_manage_privilege;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private String[] role;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_access_level;
    /* access modifiers changed from: private */
    public Spinner sp_account_status;
    /* access modifiers changed from: private */
    public Spinner sp_bbdetail;
    /* access modifiers changed from: private */
    public Spinner sp_ftthdetail;
    /* access modifiers changed from: private */
    public Spinner sp_network_glance;
    /* access modifiers changed from: private */
    public Spinner sp_nms_dslam;
    /* access modifiers changed from: private */
    public Spinner sp_nms_olt;
    /* access modifiers changed from: private */
    public Spinner sp_nms_tolt;
    /* access modifiers changed from: private */
    public Spinner sp_pms;
    /* access modifiers changed from: private */
    public Spinner sp_portverify;
    /* access modifiers changed from: private */
    public Spinner sp_report;
    /* access modifiers changed from: private */
    public Spinner sp_role;
    /* access modifiers changed from: private */
    public Spinner sp_tipdetail;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_designation;
    /* access modifiers changed from: private */
    public TextView txt_email;
    /* access modifiers changed from: private */
    public TextView txt_mobile;
    /* access modifiers changed from: private */
    public TextView txt_name;
    /* access modifiers changed from: private */
    public TextView txt_unit;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_user_privilege_activity);
        String str = getString(R.string.serverip) + "manage_user_getdetail.php";
        final String str2 = getString(R.string.serverip) + "manage_user_setprivilege1.php";
        Intent intent2 = getIntent();
        this.intent = intent2;
        final String stringExtra = intent2.getStringExtra("username");
        final String stringExtra2 = this.intent.getStringExtra("user_circle");
        final String stringExtra3 = this.intent.getStringExtra("user_ssa");
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
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.img_header = (ImageView) findViewById(R.id.img_header);
        this.lay_manage_privilege = (LinearLayout) findViewById(R.id.lay_manage_privilege);
        this.btn_update = (Button) findViewById(R.id.btn_update);
        this.txt_name = (TextView) findViewById(R.id.txt_name);
        this.txt_mobile = (TextView) findViewById(R.id.txt_mobile);
        this.txt_email = (TextView) findViewById(R.id.txt_email);
        this.txt_designation = (TextView) findViewById(R.id.txt_designation);
        this.txt_unit = (TextView) findViewById(R.id.txt_unit);
        this.sp_role = (Spinner) findViewById(R.id.spinner_role);
        this.sp_access_level = (Spinner) findViewById(R.id.spinner_access_level);
        this.sp_account_status = (Spinner) findViewById(R.id.spinner_account_status);
        this.sp_bbdetail = (Spinner) findViewById(R.id.spinner_bbdetail);
        this.sp_ftthdetail = (Spinner) findViewById(R.id.spinner_ftthdetail);
        this.sp_tipdetail = (Spinner) findViewById(R.id.spinner_tipdetail);
        this.sp_portverify = (Spinner) findViewById(R.id.spinner_portverify);
        this.sp_nms_dslam = (Spinner) findViewById(R.id.spinner_nms_dslam);
        this.sp_nms_olt = (Spinner) findViewById(R.id.spinner_nms_olt);
        this.sp_nms_tolt = (Spinner) findViewById(R.id.spinner_nms_tpartyolt);
        this.sp_pms = (Spinner) findViewById(R.id.spinner_pms);
        this.sp_network_glance = (Spinner) findViewById(R.id.spinner_network_glance);
        this.sp_report = (Spinner) findViewById(R.id.spinner_report);
        if (this.Pref_Role.equals("NODAL")) {
            this.role = new String[]{"USER"};
            this.access = new String[]{"SSA", "BA"};
        } else if (this.Pref_Role.equals("ADMINISTRATOR")) {
            this.role = new String[]{"USER", "NODAL"};
            this.access = new String[]{"SSA", "BA", "CIRCLE"};
        } else if (this.Pref_Role.equals("SUPER")) {
            this.role = new String[]{"USER", "NODAL", "ADMINISTRATOR"};
            this.access = new String[]{"SSA", "BA", "CIRCLE", "PAN-INDIA"};
        }
        this.bol = new String[]{"FALSE", "TRUE"};
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.bol);
        final ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.spinner_item, this.role);
        final ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, R.layout.spinner_item, this.access);
        this.sp_role.setAdapter(arrayAdapter2);
        this.sp_role.setOnItemSelectedListener(this);
        this.sp_access_level.setAdapter(arrayAdapter3);
        this.sp_access_level.setOnItemSelectedListener(this);
        this.sp_account_status.setAdapter(arrayAdapter);
        this.sp_account_status.setOnItemSelectedListener(this);
        this.sp_bbdetail.setAdapter(arrayAdapter);
        this.sp_bbdetail.setOnItemSelectedListener(this);
        this.sp_ftthdetail.setAdapter(arrayAdapter);
        this.sp_ftthdetail.setOnItemSelectedListener(this);
        this.sp_tipdetail.setAdapter(arrayAdapter);
        this.sp_tipdetail.setOnItemSelectedListener(this);
        this.sp_portverify.setAdapter(arrayAdapter);
        this.sp_portverify.setOnItemSelectedListener(this);
        this.sp_nms_dslam.setAdapter(arrayAdapter);
        this.sp_nms_dslam.setOnItemSelectedListener(this);
        this.sp_nms_olt.setAdapter(arrayAdapter);
        this.sp_nms_olt.setOnItemSelectedListener(this);
        this.sp_nms_tolt.setAdapter(arrayAdapter);
        this.sp_nms_tolt.setOnItemSelectedListener(this);
        this.sp_pms.setAdapter(arrayAdapter);
        this.sp_pms.setOnItemSelectedListener(this);
        this.sp_network_glance.setAdapter(arrayAdapter);
        this.sp_network_glance.setOnItemSelectedListener(this);
        this.sp_report.setAdapter(arrayAdapter);
        this.sp_report.setOnItemSelectedListener(this);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str3 = stringExtra;
        AnonymousClass3 r1 = new StringRequest(1, str, new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_User_Privilege_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                        Manage_User_Privilege_Activity.this.txt_name.setText(jSONObject.getString("NAME"));
                        Manage_User_Privilege_Activity.this.txt_mobile.setText(jSONObject.getString("MOBILE"));
                        Manage_User_Privilege_Activity.this.txt_email.setText(jSONObject.getString("EMAIL"));
                        Manage_User_Privilege_Activity.this.txt_designation.setText(jSONObject.getString("DESIGNATION"));
                        Manage_User_Privilege_Activity.this.txt_unit.setText(jSONObject.getString("UNIT") + ", " + jSONObject.getString("SSA") + ", " + jSONObject.getString("CIRCLE"));
                        Manage_User_Privilege_Activity.this.sp_role.setSelection(arrayAdapter2.getPosition(jSONObject.getString("ROLE")));
                        Manage_User_Privilege_Activity.this.sp_access_level.setSelection(arrayAdapter3.getPosition(jSONObject.getString("ACCESS_LEVEL")));
                        Manage_User_Privilege_Activity.this.sp_account_status.setSelection(arrayAdapter.getPosition(jSONObject.getString("ACCOUNT_STATUS")));
                        Manage_User_Privilege_Activity.this.sp_bbdetail.setSelection(arrayAdapter.getPosition(jSONObject.getString("DETAIL_BB")));
                        Manage_User_Privilege_Activity.this.sp_ftthdetail.setSelection(arrayAdapter.getPosition(jSONObject.getString("DETAIL_FTTH")));
                        Manage_User_Privilege_Activity.this.sp_tipdetail.setSelection(arrayAdapter.getPosition(jSONObject.getString("DETAIL_TIP")));
                        Manage_User_Privilege_Activity.this.sp_portverify.setSelection(arrayAdapter.getPosition(jSONObject.getString("PORT_VERIFY")));
                        Manage_User_Privilege_Activity.this.sp_nms_dslam.setSelection(arrayAdapter.getPosition(jSONObject.getString("NMS_DSLAM")));
                        Manage_User_Privilege_Activity.this.sp_nms_olt.setSelection(arrayAdapter.getPosition(jSONObject.getString("NMS_OLT")));
                        Manage_User_Privilege_Activity.this.sp_nms_tolt.setSelection(arrayAdapter.getPosition(jSONObject.getString("NMS_TPARTYOLT")));
                        Manage_User_Privilege_Activity.this.sp_pms.setSelection(arrayAdapter.getPosition(jSONObject.getString("PMS")));
                        Manage_User_Privilege_Activity.this.sp_network_glance.setSelection(arrayAdapter.getPosition(jSONObject.getString("NW_GLANCE")));
                        Manage_User_Privilege_Activity.this.sp_report.setSelection(arrayAdapter.getPosition(jSONObject.getString("REPORT")));
                        Manage_User_Privilege_Activity.this.lay_manage_privilege.setVisibility(0);
                        return;
                    }
                    Manage_User_Privilege_Activity.this.lay_manage_privilege.setVisibility(4);
                    Manage_User_Privilege_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                    Manage_User_Privilege_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Privilege_Activity.this.getApplicationContext());
                Manage_User_Privilege_Activity.this.progress_dialog.cancel();
                Manage_User_Privilege_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_User_Privilege_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("uid", str3);
                hashMap.put("Circle", stringExtra2);
                hashMap.put("SSA", stringExtra3);
                hashMap.put("username", Manage_User_Privilege_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_User_Privilege_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_User_Privilege_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
        this.img_header.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Manage_User_Privilege_Activity.this.startActivity(new Intent(Manage_User_Privilege_Activity.this, DashBoard_New.class));
                Manage_User_Privilege_Activity.this.finish();
            }
        });
        this.btn_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Manage_User_Privilege_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Manage_User_Privilege_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, str2, new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_User_Privilege_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.getBoolean("update")) {
                                new AlertHelperclass().ptoast("PRIVILEGE IS SET SUCCESSFULLY !!", Manage_User_Privilege_Activity.this.getApplicationContext());
                                Manage_User_Privilege_Activity.this.startActivity(new Intent(Manage_User_Privilege_Activity.this, Manage_User_Activity.class));
                                Manage_User_Privilege_Activity.this.finish();
                                return;
                            }
                            Manage_User_Privilege_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Manage_User_Privilege_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Privilege_Activity.this.getApplicationContext());
                        Manage_User_Privilege_Activity.this.progress_dialog.cancel();
                        Manage_User_Privilege_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_User_Privilege_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("uid", stringExtra);
                        hashMap.put("user_circle", Manage_User_Privilege_Activity.this.intent.getStringExtra("user_circle"));
                        hashMap.put("user_ssa", Manage_User_Privilege_Activity.this.intent.getStringExtra("user_ssa"));
                        hashMap.put("role", Manage_User_Privilege_Activity.this.sp_role.getSelectedItem().toString());
                        hashMap.put("access_level", Manage_User_Privilege_Activity.this.sp_access_level.getSelectedItem().toString());
                        hashMap.put("account_status", Manage_User_Privilege_Activity.this.sp_account_status.getSelectedItem().toString());
                        hashMap.put("detail_bb", Manage_User_Privilege_Activity.this.sp_bbdetail.getSelectedItem().toString());
                        hashMap.put("detail_ftth", Manage_User_Privilege_Activity.this.sp_ftthdetail.getSelectedItem().toString());
                        hashMap.put("detail_tip", Manage_User_Privilege_Activity.this.sp_tipdetail.getSelectedItem().toString());
                        hashMap.put("nms_dslam", Manage_User_Privilege_Activity.this.sp_nms_dslam.getSelectedItem().toString());
                        hashMap.put("nms_olt", Manage_User_Privilege_Activity.this.sp_nms_olt.getSelectedItem().toString());
                        hashMap.put("nms_tpartyolt", Manage_User_Privilege_Activity.this.sp_nms_tolt.getSelectedItem().toString());
                        hashMap.put("pms", Manage_User_Privilege_Activity.this.sp_pms.getSelectedItem().toString());
                        hashMap.put("portverify", Manage_User_Privilege_Activity.this.sp_portverify.getSelectedItem().toString());
                        hashMap.put("network_glance", Manage_User_Privilege_Activity.this.sp_network_glance.getSelectedItem().toString());
                        hashMap.put("report", Manage_User_Privilege_Activity.this.sp_report.getSelectedItem().toString());
                        hashMap.put("modified_by", Manage_User_Privilege_Activity.this.Pref_Username);
                        hashMap.put("username", Manage_User_Privilege_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_User_Privilege_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_User_Privilege_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView.getId() == R.id.spinner_access_level) {
            if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("SSA")) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorGreen));
            } else if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("CIRCLE")) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#cc0099"));
            } else {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#0000b3"));
            }
        } else if (adapterView.getId() == R.id.spinner_role) {
            if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("USER")) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorGreen));
            } else if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("NODAL")) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#cc0099"));
            } else {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#0000b3"));
            }
        } else if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("FALSE")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorRed));
        } else {
            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorGreen));
        }
    }
}
