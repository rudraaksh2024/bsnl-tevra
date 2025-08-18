package bsnl.bsnl_teevra.wizards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.DashBoard_New;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.VolleyErrorHelper;
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

public class Ont_Configure_Olt_Activity extends AppCompatActivity implements View.OnClickListener {
    public int NUM_ITEMS_PAGE = 100;
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    /* access modifiers changed from: private */
    public String Pref_fms_username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private CustomBaseAdapter adapter;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_fms_username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_vendor;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView btn_submit;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public int countt = 0;
    private CustomBaseAdapter customBaseAdapter;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_firmaname;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_username;
    private ImageView img_header;
    private LinearLayout lay_btn;
    private ListView listview;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private int search_count = 0;
    private JSONArray searched_array;
    private SharedPreferences sharedPreferences;
    private Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_nms_vendor;
    private Spinner sp_ssa;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;
    private TextView txt_header1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ont_configure_olt_activity);
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
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.img_header = (ImageView) findViewById(R.id.img_header);
        this.sp_nms_vendor = (Spinner) findViewById(R.id.sp_nms_vendor);
        TextView textView = (TextView) findViewById(R.id.btn_submit);
        this.btn_submit = textView;
        textView.setOnClickListener(this);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.listview = (ListView) findViewById(R.id.listView);
        this.progress_dialog.show();
        this.fms_firmaname = new ArrayList<>();
        this.fms_username = new ArrayList<>();
        this.fms_firmaname.add("-- VENDOR --");
        this.fms_username.add("-- FMS-USERNAME --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "tip_vendor_populate_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Ont_Configure_Olt_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        Ont_Configure_Olt_Activity.this.fms_firmaname.add(jSONObject.getString("FMS_FIRM_NAME"));
                        Ont_Configure_Olt_Activity.this.fms_username.add(jSONObject.getString("FMS_USERNAME"));
                    }
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity = Ont_Configure_Olt_Activity.this;
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity2 = Ont_Configure_Olt_Activity.this;
                    ArrayAdapter unused = ont_Configure_Olt_Activity.adapter_vendor = new ArrayAdapter(ont_Configure_Olt_Activity2, R.layout.spinner_item, ont_Configure_Olt_Activity2.fms_firmaname);
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity3 = Ont_Configure_Olt_Activity.this;
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity4 = Ont_Configure_Olt_Activity.this;
                    ArrayAdapter unused2 = ont_Configure_Olt_Activity3.adapter_fms_username = new ArrayAdapter(ont_Configure_Olt_Activity4, R.layout.spinner_item, ont_Configure_Olt_Activity4.fms_username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Ont_Configure_Olt_Activity.this.sp_nms_vendor.setAdapter(Ont_Configure_Olt_Activity.this.adapter_vendor);
                if (Ont_Configure_Olt_Activity.this.Pref_Designation.contains("FRANCHISEE")) {
                    Ont_Configure_Olt_Activity.this.sp_nms_vendor.setSelection(Ont_Configure_Olt_Activity.this.adapter_fms_username.getPosition(Ont_Configure_Olt_Activity.this.Pref_fms_username));
                    Ont_Configure_Olt_Activity.this.sp_nms_vendor.setEnabled(false);
                    Ont_Configure_Olt_Activity.this.sp_nms_vendor.setAlpha(0.6f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Ont_Configure_Olt_Activity ont_Configure_Olt_Activity = Ont_Configure_Olt_Activity.this;
                Ont_Configure_Olt_Activity ont_Configure_Olt_Activity2 = Ont_Configure_Olt_Activity.this;
                ArrayAdapter unused = ont_Configure_Olt_Activity.adapter_vendor = new ArrayAdapter(ont_Configure_Olt_Activity2, R.layout.spinner_item, ont_Configure_Olt_Activity2.fms_firmaname);
                Ont_Configure_Olt_Activity ont_Configure_Olt_Activity3 = Ont_Configure_Olt_Activity.this;
                Ont_Configure_Olt_Activity ont_Configure_Olt_Activity4 = Ont_Configure_Olt_Activity.this;
                ArrayAdapter unused2 = ont_Configure_Olt_Activity3.adapter_fms_username = new ArrayAdapter(ont_Configure_Olt_Activity4, R.layout.spinner_item, ont_Configure_Olt_Activity4.fms_username);
                Ont_Configure_Olt_Activity.this.sp_nms_vendor.setAdapter(Ont_Configure_Olt_Activity.this.adapter_vendor);
                Ont_Configure_Olt_Activity.this.progress_dialog.cancel();
                Ont_Configure_Olt_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Ont_Configure_Olt_Activity.this.getApplicationContext()));
                Ont_Configure_Olt_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Ont_Configure_Olt_Activity.this.Pref_Circle);
                hashMap.put("ssa", Ont_Configure_Olt_Activity.this.Pref_SSA);
                hashMap.put("username", Ont_Configure_Olt_Activity.this.Pref_Username);
                hashMap.put("random_key", Ont_Configure_Olt_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Ont_Configure_Olt_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_submit) {
            String obj = this.sp_nms_vendor.getSelectedItem().toString();
            if (obj.equals("-- VENDOR --")) {
                new AlertHelperclass().ntoast("Please Select A Valid Franchisee", this);
                return;
            }
            final String item = this.adapter_fms_username.getItem(this.adapter_vendor.getPosition(obj));
            this.txt_header.setText(obj);
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass6 r1 = new StringRequest(1, getString(R.string.serverip) + "ont/olt_populate.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Ont_Configure_Olt_Activity.this.progress_dialog.cancel();
                    try {
                        JSONArray unused = Ont_Configure_Olt_Activity.this.Row_Array = new JSONObject(str).getJSONArray("ROWSET");
                        Ont_Configure_Olt_Activity ont_Configure_Olt_Activity = Ont_Configure_Olt_Activity.this;
                        ont_Configure_Olt_Activity.TOTAL_LIST_ITEMS = ont_Configure_Olt_Activity.Row_Array.length();
                        Ont_Configure_Olt_Activity.this.Btnfooter();
                        Ont_Configure_Olt_Activity.this.loadList(0);
                        Ont_Configure_Olt_Activity.this.CheckBtnBackGroud(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Ont_Configure_Olt_Activity.this.progress_dialog.cancel();
                    Ont_Configure_Olt_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Ont_Configure_Olt_Activity.this.getApplicationContext()));
                    Ont_Configure_Olt_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("fms_username", item);
                    hashMap.put("username", Ont_Configure_Olt_Activity.this.Pref_Username);
                    hashMap.put("random_key", Ont_Configure_Olt_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", Ont_Configure_Olt_Activity.this.androidId);
                    return hashMap;
                }
            };
            r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r1);
        }
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
                    if (Ont_Configure_Olt_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Ont_Configure_Olt_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity = Ont_Configure_Olt_Activity.this;
                    ont_Configure_Olt_Activity.loadList(ont_Configure_Olt_Activity.countt + 1);
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity2 = Ont_Configure_Olt_Activity.this;
                    ont_Configure_Olt_Activity2.CheckBtnBackGroud(ont_Configure_Olt_Activity2.countt + 1);
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity3 = Ont_Configure_Olt_Activity.this;
                    int unused = ont_Configure_Olt_Activity3.countt = ont_Configure_Olt_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Ont_Configure_Olt_Activity.this.countt == 0) {
                        Toast.makeText(Ont_Configure_Olt_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity = Ont_Configure_Olt_Activity.this;
                    ont_Configure_Olt_Activity.loadList(ont_Configure_Olt_Activity.countt - 1);
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity2 = Ont_Configure_Olt_Activity.this;
                    ont_Configure_Olt_Activity2.CheckBtnBackGroud(ont_Configure_Olt_Activity2.countt - 1);
                    Ont_Configure_Olt_Activity ont_Configure_Olt_Activity3 = Ont_Configure_Olt_Activity.this;
                    int unused = ont_Configure_Olt_Activity3.countt = ont_Configure_Olt_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Ont_Configure_Olt_Activity.this.loadList(i4);
                    Ont_Configure_Olt_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Ont_Configure_Olt_Activity.this.countt = i4;
                }
            });
            i4 = i5;
        }
    }

    /* access modifiers changed from: private */
    public void loadList(int i) {
        JSONArray jSONArray = new JSONArray();
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
        CustomBaseAdapter customBaseAdapter2 = new CustomBaseAdapter(getApplicationContext(), jSONArray, i2);
        this.adapter = customBaseAdapter2;
        this.listview.setAdapter(customBaseAdapter2);
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
}
