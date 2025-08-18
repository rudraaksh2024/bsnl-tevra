package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Manage_Inventory_Add_Switch_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public ArrayList<String> bngiplist;
    /* access modifiers changed from: private */
    public ArrayList<String> bngnamelist;
    private TextView btn_sw_inventory_submit;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_new_inventory_make;
    private EditText et_sw_inventory_exgcode;
    private EditText et_sw_inventory_ip;
    private EditText et_sw_inventory_loc;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public ArrayList<String> inventory_make_list;
    /* access modifiers changed from: private */
    public String make;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_bngip;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_bngname;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_make;
    private Spinner sp_swt_type;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_sw_inventory_circle;
    private TextView txt_sw_inventory_ssa;
    private TextView txt_sw_inventory_vendor;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_add_switch_activity_);
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
        this.imageView = (ImageView) findViewById(R.id.img_header);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.txt_sw_inventory_vendor = (TextView) findViewById(R.id.txt_sw_inventory_vendor);
        this.txt_sw_inventory_circle = (TextView) findViewById(R.id.txt_sw_inventory_circle);
        this.txt_sw_inventory_ssa = (TextView) findViewById(R.id.txt_sw_inventory_ssa);
        this.et_sw_inventory_exgcode = (EditText) findViewById(R.id.et_sw_inventory_exgcode);
        this.et_sw_inventory_loc = (EditText) findViewById(R.id.et_sw_inventory_loc);
        this.et_sw_inventory_ip = (EditText) findViewById(R.id.txt_sw_inventory_ip);
        this.et_new_inventory_make = (EditText) findViewById(R.id.et_new_inventory_make);
        this.sp_swt_type = (Spinner) findViewById(R.id.sp_swt_type);
        this.sp_sw_inventory_make = (Spinner) findViewById(R.id.sp_sw_inventory_make);
        this.sp_sw_inventory_bngip = (Spinner) findViewById(R.id.sp_sw_inventory_bngip);
        this.sp_sw_inventory_bngname = (Spinner) findViewById(R.id.sp_sw_inventory_bngname);
        this.btn_sw_inventory_submit = (TextView) findViewById(R.id.btn_sw_inventory_submit);
        this.txt_sw_inventory_circle.setText(this.Pref_Circle);
        this.txt_sw_inventory_ssa.setText(this.Pref_SSA);
        this.sp_swt_type.setAdapter(new ArrayAdapter(this, R.layout.spinner_item, new String[]{"-- SWITCH-TYPE --", "RPR-T1", "RPR-T2", "OCLAN", "MNG-PAN-COAU", "MNG-PAN-SWITCH", "MNG-OCPAN"}));
        load_bng_populate();
        load_inventory_make();
        this.sp_sw_inventory_make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_make.getSelectedItem().toString().equals("-- ADD NEW --")) {
                    Manage_Inventory_Add_Switch_Activity.this.et_new_inventory_make.setVisibility(0);
                    return;
                }
                Manage_Inventory_Add_Switch_Activity.this.et_new_inventory_make.setVisibility(8);
                Manage_Inventory_Add_Switch_Activity.this.et_new_inventory_make.setText("");
            }
        });
        this.sp_sw_inventory_bngip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngname.setSelection(Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngip.getSelectedItemPosition());
            }
        });
        this.sp_sw_inventory_bngname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngip.setSelection(Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngname.getSelectedItemPosition());
            }
        });
        this.imageView.setOnClickListener(this);
        this.btn_sw_inventory_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_sw_inventory_submit) {
            final String upperCase = this.txt_sw_inventory_vendor.getText().toString().trim().toUpperCase();
            final String upperCase2 = this.txt_sw_inventory_circle.getText().toString().trim().toUpperCase();
            final String upperCase3 = this.txt_sw_inventory_ssa.getText().toString().trim().toUpperCase();
            final String upperCase4 = this.et_sw_inventory_exgcode.getText().toString().trim().toUpperCase();
            final String upperCase5 = this.et_sw_inventory_loc.getText().toString().trim().toUpperCase();
            final String upperCase6 = this.sp_swt_type.getSelectedItem().toString().trim().toUpperCase();
            final String trim = this.et_sw_inventory_ip.getText().toString().trim();
            if (this.sp_sw_inventory_make.getSelectedItem().toString().trim().equals("-- ADD NEW --")) {
                this.make = this.et_new_inventory_make.getText().toString().trim().toUpperCase();
            } else {
                this.make = this.sp_sw_inventory_make.getSelectedItem().toString().trim().toUpperCase();
            }
            final String obj = this.sp_sw_inventory_bngip.getSelectedItem().toString();
            final String obj2 = this.sp_sw_inventory_bngname.getSelectedItem().toString();
            if (upperCase2.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID CIRCLE!!", getApplicationContext());
            } else if (upperCase3.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID SSA!!", getApplicationContext());
            } else if (upperCase4.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID EXGCODE!!", getApplicationContext());
            } else if (upperCase5.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID LOCATION!!", getApplicationContext());
            } else if (trim.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID IP!!", getApplicationContext());
            } else if (this.make.equals("-- MAKE --") || this.make.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID SWITCH MAKE", getApplicationContext());
            } else if (obj.equals("-- BNG-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-IP!!", getApplicationContext());
            } else if (obj2.equals("-- BNG-NAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-NAME!!", getApplicationContext());
            } else {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass6 r17 = r0;
                AnonymousClass6 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory_swt_add.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                                new AlertHelperclass().ptoast("AGGR-SWITCH INVENTORY IS ADDED SUCCESSFULLY !!", Manage_Inventory_Add_Switch_Activity.this.getApplicationContext());
                                Manage_Inventory_Add_Switch_Activity.this.startActivity(new Intent(Manage_Inventory_Add_Switch_Activity.this, Manage_Inventory_Activity.class));
                                Manage_Inventory_Add_Switch_Activity.this.finish();
                                return;
                            }
                            Manage_Inventory_Add_Switch_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Manage_Inventory_Add_Switch_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Add_Switch_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Switch_Activity.this.getApplicationContext()));
                        Manage_Inventory_Add_Switch_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", Manage_Inventory_Add_Switch_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Inventory_Add_Switch_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Inventory_Add_Switch_Activity.this.androidId);
                        hashMap.put("vendor", upperCase);
                        hashMap.put("circle", upperCase2);
                        hashMap.put("ssa", upperCase3);
                        hashMap.put("exgcode", upperCase4);
                        hashMap.put("location", upperCase5);
                        hashMap.put("sw_type", upperCase6);
                        hashMap.put("ip", trim);
                        hashMap.put("make", Manage_Inventory_Add_Switch_Activity.this.make);
                        hashMap.put("bngip", obj);
                        hashMap.put("bngname", obj2);
                        return hashMap;
                    }
                };
                AnonymousClass6 r1 = r17;
                r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r1);
            }
        }
    }

    private void load_bng_populate() {
        this.bngiplist = new ArrayList<>();
        this.bngnamelist = new ArrayList<>();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass9 r1 = new StringRequest(1, getString(R.string.serverip) + "bng_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Switch_Activity.this.bngiplist.add("-- BNG-IP --");
                Manage_Inventory_Add_Switch_Activity.this.bngnamelist.add("-- BNG-NAME --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Manage_Inventory_Add_Switch_Activity.this.bngiplist.add(jSONObject.getString("BNG_IP"));
                        Manage_Inventory_Add_Switch_Activity.this.bngnamelist.add(jSONObject.getString("BNG_NAME"));
                    }
                    Spinner access$200 = Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngip;
                    Manage_Inventory_Add_Switch_Activity manage_Inventory_Add_Switch_Activity = Manage_Inventory_Add_Switch_Activity.this;
                    access$200.setAdapter(new ArrayAdapter(manage_Inventory_Add_Switch_Activity, R.layout.spinner_item, manage_Inventory_Add_Switch_Activity.bngiplist));
                    Spinner access$300 = Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_bngname;
                    Manage_Inventory_Add_Switch_Activity manage_Inventory_Add_Switch_Activity2 = Manage_Inventory_Add_Switch_Activity.this;
                    access$300.setAdapter(new ArrayAdapter(manage_Inventory_Add_Switch_Activity2, R.layout.spinner_item, manage_Inventory_Add_Switch_Activity2.bngnamelist));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Switch_Activity.this.getApplicationContext());
                Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Add_Switch_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Add_Switch_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Add_Switch_Activity.this.Pref_SSA);
                hashMap.put("username", Manage_Inventory_Add_Switch_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Add_Switch_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Add_Switch_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    private void load_inventory_make() {
        this.inventory_make_list = new ArrayList<>();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass12 r1 = new StringRequest(1, getString(R.string.serverip) + "inventory_make_switch.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Switch_Activity.this.inventory_make_list.add("-- MAKE --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_Inventory_Add_Switch_Activity.this.inventory_make_list.add(jSONArray.getJSONObject(i).getString("MAKE"));
                    }
                    Manage_Inventory_Add_Switch_Activity.this.inventory_make_list.add("-- ADD NEW --");
                    Spinner access$000 = Manage_Inventory_Add_Switch_Activity.this.sp_sw_inventory_make;
                    Manage_Inventory_Add_Switch_Activity manage_Inventory_Add_Switch_Activity = Manage_Inventory_Add_Switch_Activity.this;
                    access$000.setAdapter(new ArrayAdapter(manage_Inventory_Add_Switch_Activity, R.layout.spinner_item, manage_Inventory_Add_Switch_Activity.inventory_make_list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Switch_Activity.this.getApplicationContext());
                Manage_Inventory_Add_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Add_Switch_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("username", Manage_Inventory_Add_Switch_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Add_Switch_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Add_Switch_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Manage_Inventory_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
