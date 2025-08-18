package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.stats.CodePackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Manage_Inventory_Edit_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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
    public ArrayAdapter bngip_adapter;
    /* access modifiers changed from: private */
    public ArrayList<String> bngiplist;
    /* access modifiers changed from: private */
    public String bngname;
    /* access modifiers changed from: private */
    public ArrayAdapter bngname_adapter;
    /* access modifiers changed from: private */
    public ArrayList<String> bngnamelist;
    private TextView btn_inventory_submit;
    /* access modifiers changed from: private */
    public String circle;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_inventory_circle;
    private EditText et_inventory_exgcode;
    private EditText et_inventory_ip_detail;
    private EditText et_inventory_l_innervlan;
    private EditText et_inventory_location;
    private EditText et_inventory_make_detail;
    private EditText et_inventory_outervlan;
    private EditText et_inventory_s_innervlan;
    private EditText et_inventory_ssa;
    /* access modifiers changed from: private */
    public EditText et_new_inventory_make;
    /* access modifiers changed from: private */
    public String exgcode;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public Intent intent;
    /* access modifiers changed from: private */
    public ArrayAdapter inventory_adapter;
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
    private ArrayList<String> manageable;
    private ArrayAdapter manageable_adapter;
    /* access modifiers changed from: private */
    public String nasip;
    /* access modifiers changed from: private */
    public ArrayAdapter nasip_adapter;
    /* access modifiers changed from: private */
    public ArrayList<String> nasiplist;
    /* access modifiers changed from: private */
    public String network_element;
    /* access modifiers changed from: private */
    public String oper_status;
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
    private Spinner sp_manageable;
    /* access modifiers changed from: private */
    public String ssa;
    /* access modifiers changed from: private */
    public String start_innervlan;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;
    private TextView txt_inventory_element;
    private TextView txt_inventory_ip;
    private TextView txt_inventory_make;
    private TextView txt_inventory_outer_vlan;
    private TextView txt_inventory_vendor;
    /* access modifiers changed from: private */
    public String vendor;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_edit_activity);
        Intent intent2 = getIntent();
        this.intent = intent2;
        this.network_element = intent2.getStringExtra("network_element");
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.txt_header.setText("(" + this.network_element + ")");
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
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
        this.txt_inventory_ip = (TextView) findViewById(R.id.txt_inventory_ip);
        this.txt_inventory_make = (TextView) findViewById(R.id.txt_inventory_make);
        this.txt_inventory_outer_vlan = (TextView) findViewById(R.id.txt_inventory_outer_vlan);
        this.btn_inventory_submit = (TextView) findViewById(R.id.btn_inventory_submit);
        TextView textView = (TextView) findViewById(R.id.txt_inventory_vendor);
        this.txt_inventory_vendor = textView;
        textView.setText(this.intent.getStringExtra("VENDOR").trim());
        TextView textView2 = (TextView) findViewById(R.id.txt_inventory_element);
        this.txt_inventory_element = textView2;
        textView2.setText(this.intent.getStringExtra("N/W_ELEMENT").trim());
        EditText editText = (EditText) findViewById(R.id.txt_inventory_circle);
        this.et_inventory_circle = editText;
        editText.setText(this.intent.getStringExtra("CIRCLE").trim());
        EditText editText2 = (EditText) findViewById(R.id.txt_inventory_ssa);
        this.et_inventory_ssa = editText2;
        editText2.setText(this.intent.getStringExtra("SSA").trim());
        EditText editText3 = (EditText) findViewById(R.id.txt_inventory_exgcode);
        this.et_inventory_exgcode = editText3;
        editText3.setText(this.intent.getStringExtra("EXGCODE").trim());
        EditText editText4 = (EditText) findViewById(R.id.et_inventory_location);
        this.et_inventory_location = editText4;
        editText4.setText(this.intent.getStringExtra(CodePackage.LOCATION).trim());
        EditText editText5 = (EditText) findViewById(R.id.txt_inventory_ip_detail);
        this.et_inventory_ip_detail = editText5;
        editText5.setText(this.intent.getStringExtra("IP").trim());
        this.et_inventory_ip_detail.setEnabled(false);
        this.sp_inventory_make_detail = (Spinner) findViewById(R.id.sp_inventory_make_detail);
        this.et_new_inventory_make = (EditText) findViewById(R.id.et_new_inventory_make);
        EditText editText6 = (EditText) findViewById(R.id.et_inventory_outervlan);
        this.et_inventory_outervlan = editText6;
        editText6.setText(this.intent.getStringExtra("OUTERVLAN").trim());
        this.et_inventory_outervlan.setEnabled(false);
        EditText editText7 = (EditText) findViewById(R.id.et_inventory_s_innervlan);
        this.et_inventory_s_innervlan = editText7;
        editText7.setText(this.intent.getStringExtra("S_INNERVLAN").trim());
        EditText editText8 = (EditText) findViewById(R.id.et_inventory_l_innervlan);
        this.et_inventory_l_innervlan = editText8;
        editText8.setText(this.intent.getStringExtra("L_INNERVLAN").trim());
        this.sp_manageable = (Spinner) findViewById(R.id.sp_manageable);
        this.sp_inventory_nasip = (Spinner) findViewById(R.id.sp_inventory_nasip);
        this.sp_inventory_bngname = (Spinner) findViewById(R.id.sp_inventory_bngname);
        this.sp_inventory_bngip = (Spinner) findViewById(R.id.sp_inventory_bngip);
        this.et_inventory_circle.setEnabled(false);
        this.et_inventory_ssa.setEnabled(false);
        this.txt_inventory_ip.setText(this.network_element + "-IP");
        this.txt_inventory_make.setText(this.network_element + "-MAKE");
        this.txt_inventory_outer_vlan.setText(this.network_element + "-OUTERVLAN");
        this.btn_inventory_submit.setText("EDIT " + this.network_element);
        ArrayList<String> arrayList = new ArrayList<>();
        this.inventory_make_list = arrayList;
        arrayList.add("-- MAKE --");
        this.nasiplist = new ArrayList<>();
        this.bngiplist = new ArrayList<>();
        this.bngnamelist = new ArrayList<>();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r2 = new StringRequest(1, getString(R.string.serverip) + "inventory_make.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_Inventory_Edit_Activity.this.inventory_make_list.add(jSONArray.getJSONObject(i).getString("MAKE"));
                    }
                    Manage_Inventory_Edit_Activity.this.inventory_make_list.add("-- ADD NEW --");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity = Manage_Inventory_Edit_Activity.this;
                Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity2 = Manage_Inventory_Edit_Activity.this;
                ArrayAdapter unused = manage_Inventory_Edit_Activity.inventory_adapter = new ArrayAdapter(manage_Inventory_Edit_Activity2, R.layout.spinner_item, manage_Inventory_Edit_Activity2.inventory_make_list);
                Manage_Inventory_Edit_Activity.this.sp_inventory_make_detail.setAdapter(Manage_Inventory_Edit_Activity.this.inventory_adapter);
                Manage_Inventory_Edit_Activity.this.sp_inventory_make_detail.setSelection(Manage_Inventory_Edit_Activity.this.inventory_adapter.getPosition(Manage_Inventory_Edit_Activity.this.intent.getStringExtra("MAKE").trim()), false);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity = Manage_Inventory_Edit_Activity.this;
                Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity2 = Manage_Inventory_Edit_Activity.this;
                ArrayAdapter unused = manage_Inventory_Edit_Activity.inventory_adapter = new ArrayAdapter(manage_Inventory_Edit_Activity2, R.layout.spinner_item, manage_Inventory_Edit_Activity2.inventory_make_list);
                Manage_Inventory_Edit_Activity.this.sp_inventory_make_detail.setAdapter(Manage_Inventory_Edit_Activity.this.inventory_adapter);
                Manage_Inventory_Edit_Activity.this.sp_inventory_make_detail.setSelection(Manage_Inventory_Edit_Activity.this.inventory_adapter.getPosition(Manage_Inventory_Edit_Activity.this.intent.getStringExtra("MAKE").trim()), false);
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Activity.this.getApplicationContext());
                Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Edit_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Edit_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Edit_Activity.this.Pref_SSA);
                hashMap.put("network", Manage_Inventory_Edit_Activity.this.network_element);
                hashMap.put("username", Manage_Inventory_Edit_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Edit_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Edit_Activity.this.androidId);
                return hashMap;
            }
        };
        r2.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r2);
        this.progress_dialog.show();
        AnonymousClass6 r6 = new StringRequest(1, getString(R.string.serverip) + "bng_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Activity.this.nasiplist.add("-- NAS-IP --");
                Manage_Inventory_Edit_Activity.this.bngiplist.add("-- BNG-IP --");
                Manage_Inventory_Edit_Activity.this.bngnamelist.add("-- BNG-NAME --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Manage_Inventory_Edit_Activity.this.nasiplist.add(jSONObject.getString("NAS_IP"));
                        Manage_Inventory_Edit_Activity.this.bngiplist.add(jSONObject.getString("BNG_IP"));
                        Manage_Inventory_Edit_Activity.this.bngnamelist.add(jSONObject.getString("BNG_NAME"));
                    }
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity = Manage_Inventory_Edit_Activity.this;
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity2 = Manage_Inventory_Edit_Activity.this;
                    ArrayAdapter unused = manage_Inventory_Edit_Activity.nasip_adapter = new ArrayAdapter(manage_Inventory_Edit_Activity2, R.layout.spinner_item, manage_Inventory_Edit_Activity2.nasiplist);
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity3 = Manage_Inventory_Edit_Activity.this;
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity4 = Manage_Inventory_Edit_Activity.this;
                    ArrayAdapter unused2 = manage_Inventory_Edit_Activity3.bngip_adapter = new ArrayAdapter(manage_Inventory_Edit_Activity4, R.layout.spinner_item, manage_Inventory_Edit_Activity4.bngiplist);
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity5 = Manage_Inventory_Edit_Activity.this;
                    Manage_Inventory_Edit_Activity manage_Inventory_Edit_Activity6 = Manage_Inventory_Edit_Activity.this;
                    ArrayAdapter unused3 = manage_Inventory_Edit_Activity5.bngname_adapter = new ArrayAdapter(manage_Inventory_Edit_Activity6, R.layout.spinner_item, manage_Inventory_Edit_Activity6.bngnamelist);
                    Manage_Inventory_Edit_Activity.this.sp_inventory_nasip.setAdapter(Manage_Inventory_Edit_Activity.this.nasip_adapter);
                    if (Manage_Inventory_Edit_Activity.this.nasip_adapter.getPosition(Manage_Inventory_Edit_Activity.this.intent.getStringExtra("NASIP").trim()) >= 0 && Manage_Inventory_Edit_Activity.this.nasip_adapter.getPosition(Manage_Inventory_Edit_Activity.this.intent.getStringExtra("NASIP").trim()) < Manage_Inventory_Edit_Activity.this.nasip_adapter.getCount()) {
                        Manage_Inventory_Edit_Activity.this.sp_inventory_nasip.setSelection(Manage_Inventory_Edit_Activity.this.nasip_adapter.getPosition(Manage_Inventory_Edit_Activity.this.intent.getStringExtra("NASIP").trim()), false);
                    }
                    Manage_Inventory_Edit_Activity.this.sp_inventory_bngip.setAdapter(Manage_Inventory_Edit_Activity.this.bngip_adapter);
                    Manage_Inventory_Edit_Activity.this.sp_inventory_bngname.setAdapter(Manage_Inventory_Edit_Activity.this.bngname_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Activity.this.getApplicationContext());
                Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Edit_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Edit_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Edit_Activity.this.Pref_SSA);
                hashMap.put("username", Manage_Inventory_Edit_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Edit_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Edit_Activity.this.androidId);
                return hashMap;
            }
        };
        r6.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r6);
        this.sp_inventory_make_detail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (Manage_Inventory_Edit_Activity.this.sp_inventory_make_detail.getSelectedItem().toString().equals("-- ADD NEW --")) {
                    Manage_Inventory_Edit_Activity.this.et_new_inventory_make.setVisibility(0);
                    return;
                }
                Manage_Inventory_Edit_Activity.this.et_new_inventory_make.setVisibility(8);
                Manage_Inventory_Edit_Activity.this.et_new_inventory_make.setText("");
            }
        });
        this.sp_inventory_nasip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Edit_Activity.this.sp_inventory_nasip.getSelectedItemPosition();
                Manage_Inventory_Edit_Activity.this.sp_inventory_bngip.setSelection(selectedItemPosition);
                Manage_Inventory_Edit_Activity.this.sp_inventory_bngname.setSelection(selectedItemPosition);
            }
        });
        this.sp_inventory_bngip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Edit_Activity.this.sp_inventory_bngip.getSelectedItemPosition();
                Manage_Inventory_Edit_Activity.this.sp_inventory_nasip.setSelection(selectedItemPosition);
                Manage_Inventory_Edit_Activity.this.sp_inventory_bngname.setSelection(selectedItemPosition);
            }
        });
        this.sp_inventory_bngname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedItemPosition = Manage_Inventory_Edit_Activity.this.sp_inventory_bngname.getSelectedItemPosition();
                Manage_Inventory_Edit_Activity.this.sp_inventory_bngip.setSelection(selectedItemPosition);
                Manage_Inventory_Edit_Activity.this.sp_inventory_nasip.setSelection(selectedItemPosition);
            }
        });
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.manageable = arrayList2;
        arrayList2.add("MANAGED");
        this.manageable.add("UNMANAGED");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.manageable);
        this.manageable_adapter = arrayAdapter;
        this.sp_manageable.setAdapter(arrayAdapter);
        this.sp_manageable.setOnItemSelectedListener(this);
        this.sp_manageable.setSelection(this.manageable_adapter.getPosition(this.intent.getStringExtra("MANAGEABLE").trim()), false);
        this.imageView.setOnClickListener(this);
        this.btn_inventory_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_inventory_submit) {
            this.vendor = this.txt_inventory_vendor.getText().toString().trim().toUpperCase();
            this.circle = this.et_inventory_circle.getText().toString().trim().toUpperCase();
            this.ssa = this.et_inventory_ssa.getText().toString().trim().toUpperCase();
            this.exgcode = this.et_inventory_exgcode.getText().toString().trim().toUpperCase();
            this.location = this.et_inventory_location.getText().toString().trim().toUpperCase();
            this.ip = this.et_inventory_ip_detail.getText().toString().trim();
            if (this.sp_inventory_make_detail.getSelectedItem().toString().trim().equals("-- ADD NEW --")) {
                this.make = this.et_new_inventory_make.getText().toString().trim().toUpperCase();
            } else {
                this.make = this.sp_inventory_make_detail.getSelectedItem().toString().trim().toUpperCase();
            }
            this.outervlan = this.et_inventory_outervlan.getText().toString().trim();
            this.start_innervlan = this.et_inventory_s_innervlan.getText().toString().trim();
            this.last_innervlan = this.et_inventory_l_innervlan.getText().toString().trim();
            this.oper_status = this.sp_manageable.getSelectedItem().toString();
            this.nasip = this.sp_inventory_nasip.getSelectedItem().toString();
            this.bngip = this.sp_inventory_bngip.getSelectedItem().toString();
            this.bngname = this.sp_inventory_bngname.getSelectedItem().toString();
            if (this.vendor.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID VENDOR CODE !!", getApplicationContext());
            } else if (this.circle.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID CIRCLE !!", getApplicationContext());
            } else if (this.ssa.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID SSA !!", getApplicationContext());
            } else if (this.exgcode.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID EXGCODE !!", getApplicationContext());
            } else if (this.location.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID LOCATION !!", getApplicationContext());
            } else if (this.ip.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID IP !!", getApplicationContext());
            } else if (this.make.equals("-- MAKE --") || this.make.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID " + this.network_element + " MAKE", getApplicationContext());
            } else if (this.outervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID OUTER-VLAN !!", getApplicationContext());
            } else if (this.start_innervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID START INNER VLAN !!", getApplicationContext());
            } else if (this.last_innervlan.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID LAST INNER VLAN !!", getApplicationContext());
            } else if (this.nasip.equals("-- NAS-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID NAS-IP !!", getApplicationContext());
            } else if (this.bngip.equals("-- BNG-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-IP !!", getApplicationContext());
            } else if (this.bngname.equals("-- BNG-NAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-NAME !!", getApplicationContext());
            } else {
                this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass13 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory_edit_new.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                                new AlertHelperclass().ptoast(Manage_Inventory_Edit_Activity.this.network_element + " INVENTORY IS UPDATED SUCCESSFULLY !!", Manage_Inventory_Edit_Activity.this.getApplicationContext());
                                Manage_Inventory_Edit_Activity.this.startActivity(new Intent(Manage_Inventory_Edit_Activity.this, Manage_Inventory_Activity.class));
                                Manage_Inventory_Edit_Activity.this.finish();
                                return;
                            }
                            Manage_Inventory_Edit_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Manage_Inventory_Edit_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Activity.this.getApplicationContext());
                        Manage_Inventory_Edit_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Edit_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_Inventory_Edit_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", Manage_Inventory_Edit_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Inventory_Edit_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Inventory_Edit_Activity.this.androidId);
                        hashMap.put("network_element", Manage_Inventory_Edit_Activity.this.network_element);
                        hashMap.put("vendor", Manage_Inventory_Edit_Activity.this.vendor);
                        hashMap.put("circle", Manage_Inventory_Edit_Activity.this.circle);
                        hashMap.put("ssa", Manage_Inventory_Edit_Activity.this.ssa);
                        hashMap.put("exgcode", Manage_Inventory_Edit_Activity.this.exgcode);
                        hashMap.put("location", Manage_Inventory_Edit_Activity.this.location);
                        hashMap.put("ip", Manage_Inventory_Edit_Activity.this.ip);
                        hashMap.put("make", Manage_Inventory_Edit_Activity.this.make);
                        hashMap.put("outervlan", Manage_Inventory_Edit_Activity.this.outervlan);
                        hashMap.put("s_innervlan", Manage_Inventory_Edit_Activity.this.start_innervlan);
                        hashMap.put("l_innervlan", Manage_Inventory_Edit_Activity.this.last_innervlan);
                        hashMap.put("manageable", Manage_Inventory_Edit_Activity.this.oper_status);
                        hashMap.put("nasip", Manage_Inventory_Edit_Activity.this.nasip);
                        hashMap.put("bngip", Manage_Inventory_Edit_Activity.this.bngip);
                        hashMap.put("bngname", Manage_Inventory_Edit_Activity.this.bngname);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    public void onBackPressed() {
        Intent intent2 = new Intent(this, Manage_Inventory_Activity.class);
        intent2.setFlags(67108864);
        startActivity(intent2);
        finish();
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView.getId() != R.id.sp_manageable) {
            return;
        }
        if (((TextView) adapterView.getChildAt(0)).getText().toString().equals("MANAGED")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorGreen));
            ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
            return;
        }
        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorRed));
        ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
    }
}
