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

public class Manage_Inventory_Add_Activity extends AppCompatActivity implements View.OnClickListener {
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
    public String bngip;
    /* access modifiers changed from: private */
    public ArrayList<String> bngiplist;
    /* access modifiers changed from: private */
    public String bngname;
    /* access modifiers changed from: private */
    public ArrayList<String> bngnamelist;
    private TextView btn_inventory_submit;
    /* access modifiers changed from: private */
    public String circle;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_inventory_exgcode;
    private EditText et_inventory_ip_detail;
    private EditText et_inventory_l_innervlan;
    private EditText et_inventory_loc;
    private EditText et_inventory_make_detail;
    private EditText et_inventory_outervlan;
    private EditText et_inventory_s_innervlan;
    /* access modifiers changed from: private */
    public EditText et_new_inventory_make;
    /* access modifiers changed from: private */
    public String exgcode;
    /* access modifiers changed from: private */
    public ArrayList<String> fr_firm_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fr_fmsusername_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fr_frcode_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fr_frname_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fr_service_list;
    /* access modifiers changed from: private */
    public String fra_firm_name;
    /* access modifiers changed from: private */
    public String fra_fms_username;
    /* access modifiers changed from: private */
    public String fra_franchisee_code;
    /* access modifiers changed from: private */
    public String fra_franchisee_name;
    /* access modifiers changed from: private */
    public String fra_network_element;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public ArrayList<String> inventory_make_list;
    /* access modifiers changed from: private */
    public String ip;
    /* access modifiers changed from: private */
    public String last_innervlan;
    /* access modifiers changed from: private */
    public String location;
    /* access modifiers changed from: private */
    public String make;
    /* access modifiers changed from: private */
    public String nasip;
    /* access modifiers changed from: private */
    public ArrayList<String> nasiplist;
    /* access modifiers changed from: private */
    public String network_element;
    /* access modifiers changed from: private */
    public String outervlan;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_inventory_bngip;
    /* access modifiers changed from: private */
    public Spinner sp_inventory_bngname;
    /* access modifiers changed from: private */
    public Spinner sp_inventory_make_detail;
    /* access modifiers changed from: private */
    public Spinner sp_inventory_nasip;
    /* access modifiers changed from: private */
    public Spinner sp_inventory_vendor;
    /* access modifiers changed from: private */
    public String ssa;
    /* access modifiers changed from: private */
    public String start_innervlan;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_fms_username;
    private TextView txt_header;
    private TextView txt_inventory_circle;
    /* access modifiers changed from: private */
    public TextView txt_inventory_element;
    private TextView txt_inventory_ip;
    private TextView txt_inventory_make;
    private TextView txt_inventory_outer_vlan;
    private TextView txt_inventory_ssa;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> vendor_adapter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_add_activity);
        this.network_element = getIntent().getStringExtra("network_element");
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.txt_header.setText("(" + this.network_element + ")");
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
        this.txt_inventory_ip = (TextView) findViewById(R.id.txt_inventory_ip);
        this.txt_inventory_make = (TextView) findViewById(R.id.txt_inventory_make);
        this.txt_inventory_outer_vlan = (TextView) findViewById(R.id.txt_inventory_outer_vlan);
        this.btn_inventory_submit = (TextView) findViewById(R.id.btn_inventory_submit);
        this.sp_inventory_vendor = (Spinner) findViewById(R.id.sp_inventory_vendor);
        this.txt_inventory_element = (TextView) findViewById(R.id.txt_inventory_element);
        this.txt_fms_username = (TextView) findViewById(R.id.txt_fms_username);
        this.txt_inventory_circle = (TextView) findViewById(R.id.txt_inventory_circle);
        this.txt_inventory_ssa = (TextView) findViewById(R.id.txt_inventory_ssa);
        this.et_inventory_exgcode = (EditText) findViewById(R.id.txt_inventory_exgcode);
        this.et_inventory_loc = (EditText) findViewById(R.id.txt_inventory_loc);
        this.et_inventory_ip_detail = (EditText) findViewById(R.id.txt_inventory_ip_detail);
        this.sp_inventory_make_detail = (Spinner) findViewById(R.id.sp_inventory_make_detail);
        this.et_new_inventory_make = (EditText) findViewById(R.id.et_new_inventory_make);
        this.et_inventory_outervlan = (EditText) findViewById(R.id.et_inventory_outervlan);
        this.et_inventory_s_innervlan = (EditText) findViewById(R.id.et_inventory_s_innervlan);
        this.et_inventory_l_innervlan = (EditText) findViewById(R.id.et_inventory_l_innervlan);
        this.sp_inventory_nasip = (Spinner) findViewById(R.id.sp_inventory_nasip);
        this.sp_inventory_bngname = (Spinner) findViewById(R.id.sp_inventory_bngname);
        this.sp_inventory_bngip = (Spinner) findViewById(R.id.sp_inventory_bngip);
        this.txt_inventory_circle.setText(this.Pref_Circle);
        this.txt_inventory_circle.setEnabled(false);
        this.txt_inventory_ssa.setText(this.Pref_SSA);
        this.txt_inventory_ssa.setEnabled(false);
        this.txt_inventory_ip.setText(this.network_element + "-IP");
        this.txt_inventory_make.setText(this.network_element + "-MAKE");
        this.txt_inventory_outer_vlan.setText(this.network_element + "-OUTERVLAN");
        this.btn_inventory_submit.setText("ADD " + this.network_element);
        this.fr_firm_list = new ArrayList<>();
        this.fr_frcode_list = new ArrayList<>();
        this.fr_fmsusername_list = new ArrayList<>();
        this.fr_frname_list = new ArrayList<>();
        this.fr_service_list = new ArrayList<>();
        if (this.network_element.equals("DSLAM")) {
            this.fr_firm_list.add("-- VENDOR --");
            this.fr_firm_list.add("BSNL");
            this.fr_frcode_list.add("--");
            this.fr_frcode_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_service_list.add("--");
            this.fr_service_list.add("DSLAM");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, this.fr_firm_list);
            this.vendor_adapter = arrayAdapter;
            this.sp_inventory_vendor.setAdapter(arrayAdapter);
        } else if (this.network_element.equals("LMG")) {
            this.fr_firm_list.add("-- VENDOR --");
            this.fr_firm_list.add("BSNL");
            this.fr_frcode_list.add("--");
            this.fr_frcode_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_service_list.add("--");
            this.fr_service_list.add("LMG");
            ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, this.fr_firm_list);
            this.vendor_adapter = arrayAdapter2;
            this.sp_inventory_vendor.setAdapter(arrayAdapter2);
        } else if (this.network_element.equals("OLT")) {
            this.fr_firm_list.add("-- VENDOR --");
            this.fr_firm_list.add("BSNL");
            this.fr_firm_list.add("BBNL");
            this.fr_frcode_list.add("--");
            this.fr_frcode_list.add("--");
            this.fr_frcode_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_fmsusername_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_frname_list.add("--");
            this.fr_service_list.add("--");
            this.fr_service_list.add("OLT");
            this.fr_service_list.add("OLT");
            ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, R.layout.spinner_item, this.fr_firm_list);
            this.vendor_adapter = arrayAdapter3;
            this.sp_inventory_vendor.setAdapter(arrayAdapter3);
        } else if (this.network_element.equals("TIP-OLT")) {
            this.progress_dialog.show();
            this.fr_firm_list = new ArrayList<>();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_cativ_franchisees.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                    Manage_Inventory_Add_Activity.this.fr_firm_list.add("-- VENDOR --");
                    Manage_Inventory_Add_Activity.this.fr_frcode_list.add("--");
                    Manage_Inventory_Add_Activity.this.fr_fmsusername_list.add("--");
                    Manage_Inventory_Add_Activity.this.fr_frname_list.add("--");
                    Manage_Inventory_Add_Activity.this.fr_service_list.add("--");
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (Boolean.valueOf(jSONObject.getBoolean("STATUS")).booleanValue()) {
                            JSONArray jSONArray = jSONObject.getJSONArray("ROWSET");
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                Manage_Inventory_Add_Activity.this.fr_firm_list.add(jSONObject2.getString("FIRM_NAME").toUpperCase().trim());
                                Manage_Inventory_Add_Activity.this.fr_frcode_list.add(jSONObject2.getString("FRANCHISEE_CODES").trim());
                                Manage_Inventory_Add_Activity.this.fr_fmsusername_list.add(jSONObject2.getString("USERNAME").trim());
                                Manage_Inventory_Add_Activity.this.fr_frname_list.add(jSONObject2.getString("NAME").toUpperCase().trim());
                                Manage_Inventory_Add_Activity.this.fr_service_list.add(jSONObject2.getString("SERVICE_TYPE").toUpperCase().trim());
                            }
                            Manage_Inventory_Add_Activity manage_Inventory_Add_Activity = Manage_Inventory_Add_Activity.this;
                            Manage_Inventory_Add_Activity manage_Inventory_Add_Activity2 = Manage_Inventory_Add_Activity.this;
                            ArrayAdapter unused = manage_Inventory_Add_Activity.vendor_adapter = new ArrayAdapter(manage_Inventory_Add_Activity2, R.layout.spinner_item, manage_Inventory_Add_Activity2.fr_firm_list);
                        } else {
                            Manage_Inventory_Add_Activity.this.txt_alert.setText("NO TIP FRANCHISEE FOUND");
                            Manage_Inventory_Add_Activity.this.error_dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Manage_Inventory_Add_Activity.this.sp_inventory_vendor.setAdapter(Manage_Inventory_Add_Activity.this.vendor_adapter);
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Activity.this.getApplicationContext());
                    Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                    Manage_Inventory_Add_Activity.this.txt_alert.setText(onErrorResponse);
                    Manage_Inventory_Add_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("circle", Manage_Inventory_Add_Activity.this.Pref_Circle);
                    hashMap.put("ssa", Manage_Inventory_Add_Activity.this.Pref_SSA);
                    hashMap.put("username", Manage_Inventory_Add_Activity.this.Pref_Username);
                    hashMap.put("random_key", Manage_Inventory_Add_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", Manage_Inventory_Add_Activity.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
        this.sp_inventory_vendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Add_Activity.this.load_inventory_make();
                Manage_Inventory_Add_Activity.this.load_bng_populate();
                int selectedItemPosition = Manage_Inventory_Add_Activity.this.sp_inventory_vendor.getSelectedItemPosition();
                Manage_Inventory_Add_Activity manage_Inventory_Add_Activity = Manage_Inventory_Add_Activity.this;
                String unused = manage_Inventory_Add_Activity.fra_firm_name = (String) manage_Inventory_Add_Activity.fr_firm_list.get(selectedItemPosition);
                Manage_Inventory_Add_Activity manage_Inventory_Add_Activity2 = Manage_Inventory_Add_Activity.this;
                String unused2 = manage_Inventory_Add_Activity2.fra_franchisee_code = (String) manage_Inventory_Add_Activity2.fr_frcode_list.get(selectedItemPosition);
                Manage_Inventory_Add_Activity manage_Inventory_Add_Activity3 = Manage_Inventory_Add_Activity.this;
                String unused3 = manage_Inventory_Add_Activity3.fra_franchisee_name = (String) manage_Inventory_Add_Activity3.fr_frname_list.get(selectedItemPosition);
                Manage_Inventory_Add_Activity manage_Inventory_Add_Activity4 = Manage_Inventory_Add_Activity.this;
                String unused4 = manage_Inventory_Add_Activity4.fra_fms_username = (String) manage_Inventory_Add_Activity4.fr_fmsusername_list.get(selectedItemPosition);
                Manage_Inventory_Add_Activity manage_Inventory_Add_Activity5 = Manage_Inventory_Add_Activity.this;
                String unused5 = manage_Inventory_Add_Activity5.fra_network_element = (String) manage_Inventory_Add_Activity5.fr_service_list.get(selectedItemPosition);
                Manage_Inventory_Add_Activity.this.txt_inventory_element.setText(Manage_Inventory_Add_Activity.this.fra_network_element);
                Manage_Inventory_Add_Activity.this.txt_fms_username.setText(Manage_Inventory_Add_Activity.this.fra_fms_username);
            }
        });
        this.sp_inventory_make_detail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (Manage_Inventory_Add_Activity.this.sp_inventory_make_detail.getSelectedItem().toString().equals("-- ADD NEW --")) {
                    Manage_Inventory_Add_Activity.this.et_new_inventory_make.setVisibility(0);
                    return;
                }
                Manage_Inventory_Add_Activity.this.et_new_inventory_make.setVisibility(8);
                Manage_Inventory_Add_Activity.this.et_new_inventory_make.setText("");
            }
        });
        this.sp_inventory_nasip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Add_Activity.this.sp_inventory_nasip.getSelectedItemPosition();
                Manage_Inventory_Add_Activity.this.sp_inventory_bngip.setSelection(selectedItemPosition);
                Manage_Inventory_Add_Activity.this.sp_inventory_bngname.setSelection(selectedItemPosition);
            }
        });
        this.sp_inventory_bngip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Add_Activity.this.sp_inventory_bngip.getSelectedItemPosition();
                Manage_Inventory_Add_Activity.this.sp_inventory_nasip.setSelection(selectedItemPosition);
                Manage_Inventory_Add_Activity.this.sp_inventory_bngname.setSelection(selectedItemPosition);
            }
        });
        this.sp_inventory_bngname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Add_Activity.this.sp_inventory_bngname.getSelectedItemPosition();
                Manage_Inventory_Add_Activity.this.sp_inventory_nasip.setSelection(selectedItemPosition);
                Manage_Inventory_Add_Activity.this.sp_inventory_bngip.setSelection(selectedItemPosition);
            }
        });
        this.imageView.setOnClickListener(this);
        this.btn_inventory_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_inventory_submit) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            this.circle = this.txt_inventory_circle.getText().toString().trim().toUpperCase();
            this.ssa = this.txt_inventory_ssa.getText().toString().trim().toUpperCase();
            this.exgcode = this.et_inventory_exgcode.getText().toString().trim().toUpperCase();
            this.location = this.et_inventory_loc.getText().toString().trim().toUpperCase();
            this.ip = this.et_inventory_ip_detail.getText().toString().trim();
            if (this.sp_inventory_make_detail.getSelectedItem().toString().trim().equals("-- ADD NEW --")) {
                this.make = this.et_new_inventory_make.getText().toString().trim().toUpperCase();
            } else {
                this.make = this.sp_inventory_make_detail.getSelectedItem().toString().trim().toUpperCase();
            }
            this.outervlan = this.et_inventory_outervlan.getText().toString().trim();
            this.start_innervlan = this.et_inventory_s_innervlan.getText().toString().trim();
            this.last_innervlan = this.et_inventory_l_innervlan.getText().toString().trim();
            this.nasip = this.sp_inventory_nasip.getSelectedItem().toString();
            this.bngip = this.sp_inventory_bngip.getSelectedItem().toString();
            this.bngname = this.sp_inventory_bngname.getSelectedItem().toString();
            if (this.fra_firm_name.equals("-- VENDOR --")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID VENDOR !!", getApplicationContext());
            } else if (this.fra_network_element.equals("--")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID N/W ELEMENT TYPE", getApplicationContext());
            } else if (this.circle.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID CIRCLE!!", getApplicationContext());
            } else if (this.ssa.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID SSA!!", getApplicationContext());
            } else if (this.exgcode.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID EXGCODE!!", getApplicationContext());
            } else if (this.location.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID LOCATION!!", getApplicationContext());
            } else if (this.ip.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID IP!!", getApplicationContext());
            } else if (this.make.equals("-- MAKE --") || this.make.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID " + this.network_element + " MAKE", getApplicationContext());
            } else if (this.outervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID OUTER-VLAN!!", getApplicationContext());
            } else if (this.start_innervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID START INNER VLAN!!", getApplicationContext());
            } else if (this.last_innervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID LAST INNER VLAN!!", getApplicationContext());
            } else if (this.nasip.equals("-- NAS-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID NAS-IP!!", getApplicationContext());
            } else if (this.bngip.equals("-- BNG-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-IP!!", getApplicationContext());
            } else if (this.bngname.equals("-- BNG-NAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-NAME!!", getApplicationContext());
            } else {
                this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass11 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory_add.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                                new AlertHelperclass().ptoast(Manage_Inventory_Add_Activity.this.network_element + " INVENTORY IS ADDED SUCCESSFULLY !!", Manage_Inventory_Add_Activity.this.getApplicationContext());
                                Manage_Inventory_Add_Activity.this.startActivity(new Intent(Manage_Inventory_Add_Activity.this, Manage_Inventory_Activity.class));
                                Manage_Inventory_Add_Activity.this.finish();
                                return;
                            }
                            Manage_Inventory_Add_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Manage_Inventory_Add_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Activity.this.getApplicationContext());
                        Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Add_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_Inventory_Add_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", Manage_Inventory_Add_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Inventory_Add_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Inventory_Add_Activity.this.androidId);
                        hashMap.put("network_element", Manage_Inventory_Add_Activity.this.network_element);
                        hashMap.put("vendor", Manage_Inventory_Add_Activity.this.fra_firm_name);
                        hashMap.put("fr_code", Manage_Inventory_Add_Activity.this.fra_franchisee_code);
                        hashMap.put("fr_username", Manage_Inventory_Add_Activity.this.fra_fms_username);
                        hashMap.put("fr_name", Manage_Inventory_Add_Activity.this.fra_franchisee_name);
                        hashMap.put("element", Manage_Inventory_Add_Activity.this.fra_network_element);
                        hashMap.put("circle", Manage_Inventory_Add_Activity.this.circle);
                        hashMap.put("ssa", Manage_Inventory_Add_Activity.this.ssa);
                        hashMap.put("exgcode", Manage_Inventory_Add_Activity.this.exgcode);
                        hashMap.put("location", Manage_Inventory_Add_Activity.this.location);
                        hashMap.put("ip", Manage_Inventory_Add_Activity.this.ip);
                        hashMap.put("make", Manage_Inventory_Add_Activity.this.make);
                        hashMap.put("outervlan", Manage_Inventory_Add_Activity.this.outervlan);
                        hashMap.put("s_innervlan", Manage_Inventory_Add_Activity.this.start_innervlan);
                        hashMap.put("l_innervlan", Manage_Inventory_Add_Activity.this.last_innervlan);
                        hashMap.put("nasip", Manage_Inventory_Add_Activity.this.nasip);
                        hashMap.put("bngip", Manage_Inventory_Add_Activity.this.bngip);
                        hashMap.put("bngname", Manage_Inventory_Add_Activity.this.bngname);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void load_inventory_make() {
        this.inventory_make_list = new ArrayList<>();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass14 r1 = new StringRequest(1, getString(R.string.serverip) + "inventory_make.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Activity.this.inventory_make_list.add("-- MAKE --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_Inventory_Add_Activity.this.inventory_make_list.add(jSONArray.getJSONObject(i).getString("MAKE"));
                    }
                    Manage_Inventory_Add_Activity.this.inventory_make_list.add("-- ADD NEW --");
                    Spinner access$2400 = Manage_Inventory_Add_Activity.this.sp_inventory_make_detail;
                    Manage_Inventory_Add_Activity manage_Inventory_Add_Activity = Manage_Inventory_Add_Activity.this;
                    access$2400.setAdapter(new ArrayAdapter(manage_Inventory_Add_Activity, R.layout.spinner_item, manage_Inventory_Add_Activity.inventory_make_list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Activity.this.getApplicationContext());
                Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Add_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Add_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Add_Activity.this.Pref_SSA);
                hashMap.put("network", Manage_Inventory_Add_Activity.this.network_element);
                hashMap.put("username", Manage_Inventory_Add_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Add_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Add_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    /* access modifiers changed from: private */
    public void load_bng_populate() {
        this.progress_dialog.show();
        this.nasiplist = new ArrayList<>();
        this.bngiplist = new ArrayList<>();
        this.bngnamelist = new ArrayList<>();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass17 r1 = new StringRequest(1, getString(R.string.serverip) + "bng_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Activity.this.nasiplist.add("-- NAS-IP --");
                Manage_Inventory_Add_Activity.this.bngiplist.add("-- BNG-IP --");
                Manage_Inventory_Add_Activity.this.bngnamelist.add("-- BNG-NAME --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Manage_Inventory_Add_Activity.this.nasiplist.add(jSONObject.getString("NAS_IP"));
                        Manage_Inventory_Add_Activity.this.bngiplist.add(jSONObject.getString("BNG_IP"));
                        Manage_Inventory_Add_Activity.this.bngnamelist.add(jSONObject.getString("BNG_NAME"));
                    }
                    Spinner access$2600 = Manage_Inventory_Add_Activity.this.sp_inventory_nasip;
                    Manage_Inventory_Add_Activity manage_Inventory_Add_Activity = Manage_Inventory_Add_Activity.this;
                    access$2600.setAdapter(new ArrayAdapter(manage_Inventory_Add_Activity, R.layout.spinner_item, manage_Inventory_Add_Activity.nasiplist));
                    Spinner access$2700 = Manage_Inventory_Add_Activity.this.sp_inventory_bngip;
                    Manage_Inventory_Add_Activity manage_Inventory_Add_Activity2 = Manage_Inventory_Add_Activity.this;
                    access$2700.setAdapter(new ArrayAdapter(manage_Inventory_Add_Activity2, R.layout.spinner_item, manage_Inventory_Add_Activity2.bngiplist));
                    Spinner access$2800 = Manage_Inventory_Add_Activity.this.sp_inventory_bngname;
                    Manage_Inventory_Add_Activity manage_Inventory_Add_Activity3 = Manage_Inventory_Add_Activity.this;
                    access$2800.setAdapter(new ArrayAdapter(manage_Inventory_Add_Activity3, R.layout.spinner_item, manage_Inventory_Add_Activity3.bngnamelist));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Add_Activity.this.getApplicationContext());
                Manage_Inventory_Add_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Add_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Add_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Add_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Add_Activity.this.Pref_SSA);
                hashMap.put("username", Manage_Inventory_Add_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Add_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Add_Activity.this.androidId);
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
