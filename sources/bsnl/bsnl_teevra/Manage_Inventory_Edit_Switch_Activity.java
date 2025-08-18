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
import android.widget.Button;
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

public class Manage_Inventory_Edit_Switch_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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
    public ArrayAdapter bngip_adapter;
    /* access modifiers changed from: private */
    public ArrayList<String> bngiplist;
    /* access modifiers changed from: private */
    public ArrayAdapter bngname_adapter;
    /* access modifiers changed from: private */
    public ArrayList<String> bngnamelist;
    private Button btn_sw_inventory_submit;
    private ArrayAdapter element_adapter;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_new_inventory_make;
    private EditText et_sw_inventory_exgcode;
    private EditText et_sw_inventory_loc;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public Intent intent;
    /* access modifiers changed from: private */
    public ArrayList<String> inventory_make_list;
    /* access modifiers changed from: private */
    public String make;
    /* access modifiers changed from: private */
    public ArrayAdapter make_adapter;
    private ArrayList<String> manageable;
    private ArrayAdapter manageable_adapter;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_bngip;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_bngname;
    /* access modifiers changed from: private */
    public Spinner sp_sw_inventory_make;
    private Spinner sp_sw_manageable;
    private Spinner sp_swt_type;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_sw_inventory_circle;
    private TextView txt_sw_inventory_ip;
    private TextView txt_sw_inventory_ssa;
    private TextView txt_sw_inventory_vendor;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_edit_switch_activity);
        this.intent = getIntent();
        this.imageView = (ImageView) findViewById(R.id.img_header);
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
        TextView textView = (TextView) findViewById(R.id.txt_sw_inventory_vendor);
        this.txt_sw_inventory_vendor = textView;
        textView.setText(this.intent.getStringExtra("VENDOR").trim());
        TextView textView2 = (TextView) findViewById(R.id.txt_sw_inventory_circle);
        this.txt_sw_inventory_circle = textView2;
        textView2.setText(this.intent.getStringExtra("CIRCLE").trim());
        TextView textView3 = (TextView) findViewById(R.id.txt_sw_inventory_ssa);
        this.txt_sw_inventory_ssa = textView3;
        textView3.setText(this.intent.getStringExtra("SSA").trim());
        EditText editText = (EditText) findViewById(R.id.et_sw_inventory_exgcode);
        this.et_sw_inventory_exgcode = editText;
        editText.setText(this.intent.getStringExtra("EXGCODE").trim());
        EditText editText2 = (EditText) findViewById(R.id.et_sw_inventory_loc);
        this.et_sw_inventory_loc = editText2;
        editText2.setText(this.intent.getStringExtra(CodePackage.LOCATION).trim());
        TextView textView4 = (TextView) findViewById(R.id.txt_sw_inventory_ip);
        this.txt_sw_inventory_ip = textView4;
        textView4.setText(this.intent.getStringExtra("IP").trim());
        this.et_new_inventory_make = (EditText) findViewById(R.id.et_new_inventory_make);
        this.sp_swt_type = (Spinner) findViewById(R.id.sp_swt_type);
        this.sp_sw_inventory_make = (Spinner) findViewById(R.id.sp_sw_inventory_make);
        this.sp_sw_manageable = (Spinner) findViewById(R.id.sp_sw_manageable);
        this.sp_sw_inventory_bngip = (Spinner) findViewById(R.id.sp_sw_inventory_bngip);
        this.sp_sw_inventory_bngname = (Spinner) findViewById(R.id.sp_sw_inventory_bngname);
        this.btn_sw_inventory_submit = (Button) findViewById(R.id.btn_sw_inventory_submit);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, new String[]{"-- SWITCH-TYPE --", "RPR-T1", "RPR-T2", "OCLAN", "MNG-PAN-COAU", "MNG-PAN-SWITCH", "MNG-OCPAN"});
        this.element_adapter = arrayAdapter;
        this.sp_swt_type.setAdapter(arrayAdapter);
        this.sp_swt_type.setSelection(this.element_adapter.getPosition(this.intent.getStringExtra("N/W_ELEMENT").trim()), false);
        load_inventory_make();
        this.sp_sw_inventory_make.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_make.getSelectedItem().toString().equals("-- ADD NEW --")) {
                    Manage_Inventory_Edit_Switch_Activity.this.et_new_inventory_make.setVisibility(0);
                    return;
                }
                Manage_Inventory_Edit_Switch_Activity.this.et_new_inventory_make.setVisibility(8);
                Manage_Inventory_Edit_Switch_Activity.this.et_new_inventory_make.setText("");
            }
        });
        load_bng_populate();
        this.sp_sw_inventory_bngip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngname.setSelection(Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngip.getSelectedItemPosition());
            }
        });
        this.sp_sw_inventory_bngname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngip.setSelection(Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngname.getSelectedItemPosition());
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        this.manageable = arrayList;
        arrayList.add("MANAGED");
        this.manageable.add("UNMANAGED");
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.spinner_item, this.manageable);
        this.manageable_adapter = arrayAdapter2;
        this.sp_sw_manageable.setAdapter(arrayAdapter2);
        this.sp_sw_manageable.setOnItemSelectedListener(this);
        this.sp_sw_manageable.setSelection(this.manageable_adapter.getPosition(this.intent.getStringExtra("MANAGEABLE").trim()), false);
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
            final String trim = this.txt_sw_inventory_ip.getText().toString().trim();
            if (this.sp_sw_inventory_make.getSelectedItem().toString().trim().equals("-- ADD NEW --")) {
                this.make = this.et_new_inventory_make.getText().toString().trim().toUpperCase();
            } else {
                this.make = this.sp_sw_inventory_make.getSelectedItem().toString().trim().toUpperCase();
            }
            final String obj = this.sp_sw_manageable.getSelectedItem().toString();
            final String obj2 = this.sp_sw_inventory_bngip.getSelectedItem().toString();
            final String obj3 = this.sp_sw_inventory_bngname.getSelectedItem().toString();
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
            } else if (obj2.equals("-- BNG-IP --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-IP!!", getApplicationContext());
            } else if (obj3.equals("-- BNG-NAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID BNG-NAME!!", getApplicationContext());
            } else {
                this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass6 r18 = r0;
                AnonymousClass6 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory_swt_edit_new.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                                new AlertHelperclass().ptoast("AGGR-SWITCH INVENTORY IS ADDED SUCCESSFULLY !!", Manage_Inventory_Edit_Switch_Activity.this.getApplicationContext());
                                Manage_Inventory_Edit_Switch_Activity.this.startActivity(new Intent(Manage_Inventory_Edit_Switch_Activity.this, Manage_Inventory_Activity.class));
                                Manage_Inventory_Edit_Switch_Activity.this.finish();
                                return;
                            }
                            Manage_Inventory_Edit_Switch_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Manage_Inventory_Edit_Switch_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Switch_Activity.this.getApplicationContext());
                        Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Edit_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_Inventory_Edit_Switch_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", Manage_Inventory_Edit_Switch_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Inventory_Edit_Switch_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Inventory_Edit_Switch_Activity.this.androidId);
                        hashMap.put("vendor", upperCase);
                        hashMap.put("circle", upperCase2);
                        hashMap.put("ssa", upperCase3);
                        hashMap.put("exgcode", upperCase4);
                        hashMap.put("location", upperCase5);
                        hashMap.put("sw_type", upperCase6);
                        hashMap.put("ip", trim);
                        hashMap.put("make", Manage_Inventory_Edit_Switch_Activity.this.make);
                        hashMap.put("manageable", obj);
                        hashMap.put("bngip", obj2);
                        hashMap.put("bngname", obj3);
                        return hashMap;
                    }
                };
                AnonymousClass6 r1 = r18;
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
                Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Switch_Activity.this.bngiplist.add("-- BNG-IP --");
                Manage_Inventory_Edit_Switch_Activity.this.bngnamelist.add("-- BNG-NAME --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Manage_Inventory_Edit_Switch_Activity.this.bngiplist.add(jSONObject.getString("BNG_IP"));
                        Manage_Inventory_Edit_Switch_Activity.this.bngnamelist.add(jSONObject.getString("BNG_NAME"));
                    }
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity = Manage_Inventory_Edit_Switch_Activity.this;
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity2 = Manage_Inventory_Edit_Switch_Activity.this;
                    ArrayAdapter unused = manage_Inventory_Edit_Switch_Activity.bngip_adapter = new ArrayAdapter(manage_Inventory_Edit_Switch_Activity2, R.layout.spinner_item, manage_Inventory_Edit_Switch_Activity2.bngiplist);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngip.setAdapter(Manage_Inventory_Edit_Switch_Activity.this.bngip_adapter);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngip.setSelection(Manage_Inventory_Edit_Switch_Activity.this.bngip_adapter.getPosition(Manage_Inventory_Edit_Switch_Activity.this.intent.getStringExtra("BNGIP").trim()), false);
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity3 = Manage_Inventory_Edit_Switch_Activity.this;
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity4 = Manage_Inventory_Edit_Switch_Activity.this;
                    ArrayAdapter unused2 = manage_Inventory_Edit_Switch_Activity3.bngname_adapter = new ArrayAdapter(manage_Inventory_Edit_Switch_Activity4, R.layout.spinner_item, manage_Inventory_Edit_Switch_Activity4.bngnamelist);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngname.setAdapter(Manage_Inventory_Edit_Switch_Activity.this.bngname_adapter);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_bngname.setSelection(Manage_Inventory_Edit_Switch_Activity.this.bngname_adapter.getPosition(Manage_Inventory_Edit_Switch_Activity.this.intent.getStringExtra("BNGNAME").trim()), false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Switch_Activity.this.getApplicationContext());
                Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Edit_Switch_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Manage_Inventory_Edit_Switch_Activity.this.Pref_Circle);
                hashMap.put("ssa", Manage_Inventory_Edit_Switch_Activity.this.Pref_SSA);
                hashMap.put("username", Manage_Inventory_Edit_Switch_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Edit_Switch_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Edit_Switch_Activity.this.androidId);
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
                Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Switch_Activity.this.inventory_make_list.add("-- MAKE --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_Inventory_Edit_Switch_Activity.this.inventory_make_list.add(jSONArray.getJSONObject(i).getString("MAKE"));
                    }
                    Manage_Inventory_Edit_Switch_Activity.this.inventory_make_list.add("-- ADD NEW --");
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity = Manage_Inventory_Edit_Switch_Activity.this;
                    Manage_Inventory_Edit_Switch_Activity manage_Inventory_Edit_Switch_Activity2 = Manage_Inventory_Edit_Switch_Activity.this;
                    ArrayAdapter unused = manage_Inventory_Edit_Switch_Activity.make_adapter = new ArrayAdapter(manage_Inventory_Edit_Switch_Activity2, R.layout.spinner_item, manage_Inventory_Edit_Switch_Activity2.inventory_make_list);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_make.setAdapter(Manage_Inventory_Edit_Switch_Activity.this.make_adapter);
                    Manage_Inventory_Edit_Switch_Activity.this.sp_sw_inventory_make.setSelection(Manage_Inventory_Edit_Switch_Activity.this.make_adapter.getPosition(Manage_Inventory_Edit_Switch_Activity.this.intent.getStringExtra("MAKE").trim()), false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Edit_Switch_Activity.this.getApplicationContext());
                Manage_Inventory_Edit_Switch_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Edit_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Edit_Switch_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("username", Manage_Inventory_Edit_Switch_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_Inventory_Edit_Switch_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_Inventory_Edit_Switch_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    public void onBackPressed() {
        Intent intent2 = new Intent(this, Manage_Inventory_Activity.class);
        intent2.setFlags(67108864);
        startActivity(intent2);
        finish();
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView.getId() != R.id.sp_sw_manageable) {
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
