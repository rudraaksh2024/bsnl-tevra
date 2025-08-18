package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.stats.CodePackage;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_BBC_Bulk_Onu_Detail_Activity extends AppCompatActivity implements TextWatcher {
    public int NUM_ITEMS_PAGE = 100;
    private String Pref_Access_Level;
    private String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private CustomBaseAdapter3 adapter;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public int countt = 0;
    private CustomBaseAdapter3 customBaseAdapter;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    private LinearLayout lay_btn;
    private ListView listview;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_element;
    private AlertDialog uplink_dialog;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_bulk_onu_detail_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        SharedPreferences sharedPreferences3 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences3;
        this.Pref_Role = sharedPreferences3.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Fms_Username = this.sharedPreferences.getString("fms_username_Key", (String) null);
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
        this.txt_element = (TextView) findViewById(R.id.txt_element);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.listview = (ListView) findViewById(R.id.listView);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "BULK/bulk_onu_power_detail_bbc.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_BBC_Bulk_Onu_Detail_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray unused = FMS_BBC_Bulk_Onu_Detail_Activity.this.Row_Array = new JSONObject(str).getJSONArray("ROWSET");
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity.TOTAL_LIST_ITEMS = fMS_BBC_Bulk_Onu_Detail_Activity.Row_Array.length();
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.Btnfooter();
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.loadList(0);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.CheckBtnBackGroud(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_BBC_Bulk_Onu_Detail_Activity.this.progress_dialog.cancel();
                FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext()));
                FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("bbc_username", FMS_BBC_Bulk_Onu_Detail_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_BBC_Bulk_Onu_Detail_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_BBC_Bulk_Onu_Detail_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_BBC_Bulk_Onu_Detail_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.et_search.addTextChangedListener(this);
    }

    /* access modifiers changed from: private */
    public void Btnfooter() {
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
                    if (FMS_BBC_Bulk_Onu_Detail_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity.loadList(fMS_BBC_Bulk_Onu_Detail_Activity.countt + 1);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity2 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity2.CheckBtnBackGroud(fMS_BBC_Bulk_Onu_Detail_Activity2.countt + 1);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity3 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    int unused = fMS_BBC_Bulk_Onu_Detail_Activity3.countt = fMS_BBC_Bulk_Onu_Detail_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FMS_BBC_Bulk_Onu_Detail_Activity.this.countt == 0) {
                        Toast.makeText(FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity.loadList(fMS_BBC_Bulk_Onu_Detail_Activity.countt - 1);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity2 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity2.CheckBtnBackGroud(fMS_BBC_Bulk_Onu_Detail_Activity2.countt - 1);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity3 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    int unused = fMS_BBC_Bulk_Onu_Detail_Activity3.countt = fMS_BBC_Bulk_Onu_Detail_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.loadList(i4);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.CheckBtnBackGroud(i4);
                    int unused = FMS_BBC_Bulk_Onu_Detail_Activity.this.countt = i4;
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
        CustomBaseAdapter3 customBaseAdapter3 = new CustomBaseAdapter3(getApplicationContext(), jSONArray, i2);
        this.adapter = customBaseAdapter3;
        this.listview.setAdapter(customBaseAdapter3);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    jSONArray.getJSONObject(i).getString("FMS_FIRM_NAME").trim();
                    jSONArray.getJSONObject(i).getString(CodePackage.LOCATION).trim();
                    String trim = jSONArray.getJSONObject(i).getString("OLT_IP").trim();
                    String trim2 = jSONArray.getJSONObject(i).getString("OLT_VLAN").trim();
                    String trim3 = jSONArray.getJSONObject(i).getString("NAS_IP").trim();
                    jSONArray.getJSONObject(i).getString("OLT_MAKE").trim();
                    String trim4 = jSONArray.getJSONObject(i).getString("OLT_STATUS").trim();
                    String trim5 = jSONArray.getJSONObject(i).getString("MANAGEABLE").trim();
                    String trim6 = jSONArray.getJSONObject(i).getString("LOGIN").trim();
                    if (trim5.equals("UNMANAGED")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is Unmanaged\nKindly Manage The Olt Before Fetching Bulk Onu POwer Levels");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else if (trim4.equals("OFFLINE")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is OFFLINE\nKindly Try After Sometime");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else if (trim6.equals("PENDING")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("Olt Login Credentials is Not Updated\nKindly Ask Your Franchisee To Update OLT login Credentials");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.Get_Bulk_Power(trim, trim2, trim3);
                    }
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
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button02));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorblack));
            } else {
                this.btns[i5].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorwhite));
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
                    if (FMS_BBC_Bulk_Onu_Detail_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity.loadList1(fMS_BBC_Bulk_Onu_Detail_Activity.search_count + 1, jSONArray);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity2 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity2.CheckBtnBackGroud1(fMS_BBC_Bulk_Onu_Detail_Activity2.search_count + 1, jSONArray);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity3 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    int unused = fMS_BBC_Bulk_Onu_Detail_Activity3.search_count = fMS_BBC_Bulk_Onu_Detail_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FMS_BBC_Bulk_Onu_Detail_Activity.this.search_count == 0) {
                        Toast.makeText(FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity.loadList1(fMS_BBC_Bulk_Onu_Detail_Activity.search_count - 1, jSONArray);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity2 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    fMS_BBC_Bulk_Onu_Detail_Activity2.CheckBtnBackGroud1(fMS_BBC_Bulk_Onu_Detail_Activity2.search_count - 1, jSONArray);
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity3 = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    int unused = fMS_BBC_Bulk_Onu_Detail_Activity3.search_count = fMS_BBC_Bulk_Onu_Detail_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.loadList1(i, jSONArray);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = FMS_BBC_Bulk_Onu_Detail_Activity.this.search_count = i;
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
        CustomBaseAdapter3 customBaseAdapter3 = new CustomBaseAdapter3(getApplicationContext(), jSONArray2, i2);
        this.adapter = customBaseAdapter3;
        this.listview.setAdapter(customBaseAdapter3);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    jSONArray2.getJSONObject(i).getString("FMS_FIRM_NAME").trim();
                    jSONArray2.getJSONObject(i).getString(CodePackage.LOCATION).trim();
                    String trim = jSONArray2.getJSONObject(i).getString("OLT_IP").trim();
                    String trim2 = jSONArray2.getJSONObject(i).getString("OLT_VLAN").trim();
                    String trim3 = jSONArray2.getJSONObject(i).getString("NAS_IP").trim();
                    jSONArray2.getJSONObject(i).getString("OLT_MAKE").trim();
                    String trim4 = jSONArray2.getJSONObject(i).getString("OLT_STATUS").trim();
                    String trim5 = jSONArray2.getJSONObject(i).getString("MANAGEABLE").trim();
                    String trim6 = jSONArray2.getJSONObject(i).getString("LOGIN").trim();
                    if (trim5.equals("UNMANAGED")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is Unmanaged\nKindly Manage The Olt Before Fetching Bulk Onu POwer Levels");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else if (trim4.equals("OFFLINE")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is OFFLINE\nKindly Try After Sometime");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else if (trim6.equals("PENDING")) {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText("Olt Login Credentials is Not Updated\nKindly Ask Your Franchisee To Update OLT login Credentials");
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                    } else {
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.Get_Bulk_Power(trim, trim2, trim3);
                    }
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
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button02));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorblack));
            } else {
                this.btns[i2].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorwhite));
            }
        }
    }

    /* access modifiers changed from: private */
    public void Get_Bulk_Power(final String str, String str2, String str3) {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        AnonymousClass14 r1 = new StringRequest(1, getString(R.string.serverip) + "BULK/bulk_onu_power.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_BBC_Bulk_Onu_Detail_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("SUCCESS"));
                    String string = jSONObject.getString("ERROR");
                    if (valueOf.booleanValue()) {
                        Intent intent = new Intent(FMS_BBC_Bulk_Onu_Detail_Activity.this, Bulk_Onu_Power_Activity.class);
                        intent.putExtra("RESPONSE", jSONObject.toString());
                        intent.putExtra("IP", str);
                        FMS_BBC_Bulk_Onu_Detail_Activity.this.startActivity(intent);
                        return;
                    }
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText(string);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_BBC_Bulk_Onu_Detail_Activity.this.progress_dialog.cancel();
                FMS_BBC_Bulk_Onu_Detail_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Bulk_Onu_Detail_Activity.this.getApplicationContext()));
                FMS_BBC_Bulk_Onu_Detail_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("olt_ip", str4);
                hashMap.put("olt_vlan", str5);
                hashMap.put("nas_ip", str6);
                hashMap.put("username", FMS_BBC_Bulk_Onu_Detail_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_BBC_Bulk_Onu_Detail_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_BBC_Bulk_Onu_Detail_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.fmslogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.logout_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Bulk_Onu_Detail_Activity fMS_BBC_Bulk_Onu_Detail_Activity = FMS_BBC_Bulk_Onu_Detail_Activity.this;
                    SharedPreferences.Editor unused = fMS_BBC_Bulk_Onu_Detail_Activity.editor = fMS_BBC_Bulk_Onu_Detail_Activity.sharedPreferences.edit();
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_Bulk_Onu_Detail_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.startActivity(intent);
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.finish();
                    FMS_BBC_Bulk_Onu_Detail_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
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
                if (jSONObject.getString("FMS_FIRM_NAME").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_VLAN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_MAKE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("LOGIN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("MANAGEABLE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_STATUS").toUpperCase().contains(obj.toUpperCase())) {
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

    public void onBackPressed() {
        Intent intent = new Intent(this, FMS_BBC_Dashboard_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
