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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
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

public class Pms_Setting_Detail_Activity extends AppCompatActivity implements TextWatcher {
    public int NUM_ITEMS_PAGE = 100;
    private String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private CustomBaseAdapter adapter;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public int countt = 0;
    private CustomBaseAdapter customBaseAdapter;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    private LinearLayout lay_btn;
    private ListView listview;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    private SharedPreferences sharedPreferences;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_element;
    /* access modifiers changed from: private */
    public AlertDialog uplink_dialog;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_setting_detail_activity);
        final String stringExtra = getIntent().getStringExtra("ELEMENT");
        final String stringExtra2 = getIntent().getStringExtra("SSA");
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
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
        TextView textView = (TextView) findViewById(R.id.txt_element);
        this.txt_element = textView;
        textView.setText(" (" + stringExtra + ")");
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.listview = (ListView) findViewById(R.id.listView);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r1 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_inventory_tip_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray unused = Pms_Setting_Detail_Activity.this.Row_Array = new JSONObject(str).getJSONArray("ROWSET");
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity.TOTAL_LIST_ITEMS = pms_Setting_Detail_Activity.Row_Array.length();
                    Pms_Setting_Detail_Activity.this.Btnfooter();
                    Pms_Setting_Detail_Activity.this.loadList(0);
                    Pms_Setting_Detail_Activity.this.CheckBtnBackGroud(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                Pms_Setting_Detail_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Pms_Setting_Detail_Activity.this.getApplicationContext()));
                Pms_Setting_Detail_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("element", stringExtra);
                hashMap.put("ssa", stringExtra2);
                hashMap.put("username", Pms_Setting_Detail_Activity.this.Pref_Username);
                hashMap.put("random_key", Pms_Setting_Detail_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Pms_Setting_Detail_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
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
                    if (Pms_Setting_Detail_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Pms_Setting_Detail_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity.loadList(pms_Setting_Detail_Activity.countt + 1);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity2 = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity2.CheckBtnBackGroud(pms_Setting_Detail_Activity2.countt + 1);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity3 = Pms_Setting_Detail_Activity.this;
                    int unused = pms_Setting_Detail_Activity3.countt = pms_Setting_Detail_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Pms_Setting_Detail_Activity.this.countt == 0) {
                        Toast.makeText(Pms_Setting_Detail_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity.loadList(pms_Setting_Detail_Activity.countt - 1);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity2 = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity2.CheckBtnBackGroud(pms_Setting_Detail_Activity2.countt - 1);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity3 = Pms_Setting_Detail_Activity.this;
                    int unused = pms_Setting_Detail_Activity3.countt = pms_Setting_Detail_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Pms_Setting_Detail_Activity.this.loadList(i4);
                    Pms_Setting_Detail_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Pms_Setting_Detail_Activity.this.countt = i4;
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
        CustomBaseAdapter customBaseAdapter2 = new CustomBaseAdapter(getApplicationContext(), jSONArray, i2);
        this.adapter = customBaseAdapter2;
        this.listview.setAdapter(customBaseAdapter2);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    jSONArray.getJSONObject(i).getString("FMS_FIRM_NAME").trim();
                    String trim = jSONArray.getJSONObject(i).getString("OLT_IP").trim();
                    String trim2 = jSONArray.getJSONObject(i).getString("OLT_VLAN").trim();
                    String trim3 = jSONArray.getJSONObject(i).getString("NAS_IP").trim();
                    jSONArray.getJSONObject(i).getString("OLT_MAKE").trim();
                    jSONArray.getJSONObject(i).getString(CodePackage.LOCATION).trim();
                    jSONArray.getJSONObject(i).getString("SNMP_ENABLED");
                    String trim4 = jSONArray.getJSONObject(i).getString("UPLINK").trim();
                    String trim5 = jSONArray.getJSONObject(i).getString("OLT_STATUS").trim();
                    if (jSONArray.getJSONObject(i).getString("MANAGEABLE").trim().equals("UNMANAGED")) {
                        Pms_Setting_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is Unmanaged\nKindly Manage The Olt Before Performing PMS Settins");
                        Pms_Setting_Detail_Activity.this.error_dialog.show();
                    } else if (trim5.equals("OFFLINE")) {
                        Pms_Setting_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is OFFLINE\nKindly Try After Sometime");
                        Pms_Setting_Detail_Activity.this.error_dialog.show();
                    } else {
                        Pms_Setting_Detail_Activity.this.pms_get_uplink(trim, trim4, trim2, trim3);
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
                    if (Pms_Setting_Detail_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Pms_Setting_Detail_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity.loadList1(pms_Setting_Detail_Activity.search_count + 1, jSONArray);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity2 = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity2.CheckBtnBackGroud1(pms_Setting_Detail_Activity2.search_count + 1, jSONArray);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity3 = Pms_Setting_Detail_Activity.this;
                    int unused = pms_Setting_Detail_Activity3.search_count = pms_Setting_Detail_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Pms_Setting_Detail_Activity.this.search_count == 0) {
                        Toast.makeText(Pms_Setting_Detail_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity.loadList1(pms_Setting_Detail_Activity.search_count - 1, jSONArray);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity2 = Pms_Setting_Detail_Activity.this;
                    pms_Setting_Detail_Activity2.CheckBtnBackGroud1(pms_Setting_Detail_Activity2.search_count - 1, jSONArray);
                    Pms_Setting_Detail_Activity pms_Setting_Detail_Activity3 = Pms_Setting_Detail_Activity.this;
                    int unused = pms_Setting_Detail_Activity3.search_count = pms_Setting_Detail_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Pms_Setting_Detail_Activity.this.loadList1(i, jSONArray);
                    Pms_Setting_Detail_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Pms_Setting_Detail_Activity.this.search_count = i;
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
        CustomBaseAdapter customBaseAdapter2 = new CustomBaseAdapter(getApplicationContext(), jSONArray2, i2);
        this.adapter = customBaseAdapter2;
        this.listview.setAdapter(customBaseAdapter2);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                try {
                    jSONArray2.getJSONObject(i).getString("FMS_FIRM_NAME").trim();
                    String trim = jSONArray2.getJSONObject(i).getString("OLT_IP").trim();
                    String trim2 = jSONArray2.getJSONObject(i).getString("OLT_VLAN").trim();
                    String trim3 = jSONArray2.getJSONObject(i).getString("NAS_IP").trim();
                    jSONArray2.getJSONObject(i).getString("OLT_MAKE").trim();
                    jSONArray2.getJSONObject(i).getString(CodePackage.LOCATION).trim();
                    jSONArray2.getJSONObject(i).getString("SNMP_ENABLED");
                    String trim4 = jSONArray2.getJSONObject(i).getString("UPLINK").trim();
                    String trim5 = jSONArray2.getJSONObject(i).getString("OLT_STATUS").trim();
                    if (jSONArray2.getJSONObject(i).getString("MANAGEABLE").trim().equals("UNMANAGED")) {
                        Pms_Setting_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is Unmanaged\nKindly Manage The Olt Before Performing PMS Settins");
                        Pms_Setting_Detail_Activity.this.error_dialog.show();
                    } else if (trim5.equals("OFFLINE")) {
                        Pms_Setting_Detail_Activity.this.txt_alert.setText("The Olt (" + trim + ") Is OFFLINE\nKindly Try After Sometime");
                        Pms_Setting_Detail_Activity.this.error_dialog.show();
                    } else {
                        Pms_Setting_Detail_Activity.this.pms_get_uplink(trim, trim4, trim2, trim3);
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

    public void afterTextChanged(Editable editable) {
        String obj = this.et_search.getText().toString();
        this.listview.setAdapter((ListAdapter) null);
        this.searched_array = new JSONArray();
        this.search_count = 0;
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                if (jSONObject.getString("FMS_FIRM_NAME").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_VLAN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_MAKE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("SNMP_ENABLED").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("UPLINK").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_STATUS").toUpperCase().contains(obj.toUpperCase())) {
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

    /* access modifiers changed from: private */
    public void pms_get_uplink(String str, String str2, String str3, String str4) {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str5 = str2;
        final String str6 = str;
        final String str7 = str3;
        final String str8 = str4;
        final String str9 = str;
        AnonymousClass14 r0 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_tip_uplink_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("SUCCESS")).booleanValue()) {
                        JSONArray jSONArray = jSONObject.getJSONArray("UPLINK");
                        ArrayList arrayList = new ArrayList();
                        arrayList.add("UPLINK");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add(jSONArray.getJSONObject(i).getString("DESC"));
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(Pms_Setting_Detail_Activity.this);
                        View inflate = Pms_Setting_Detail_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_setting_tip2, (ViewGroup) null);
                        builder.setCancelable(false);
                        builder.setView(inflate);
                        AlertDialog unused = Pms_Setting_Detail_Activity.this.uplink_dialog = builder.create();
                        final Spinner spinner = (Spinner) inflate.findViewById(R.id.sp_pms_tip);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(Pms_Setting_Detail_Activity.this, R.layout.spinner_item, arrayList);
                        spinner.setAdapter(arrayAdapter);
                        spinner.setSelection(arrayAdapter.getPosition(str5));
                        Pms_Setting_Detail_Activity.this.uplink_dialog.show();
                        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Pms_Setting_Detail_Activity.this.uplink_dialog.cancel();
                            }
                        });
                        ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                String trim = spinner.getSelectedItem().toString().trim();
                                if (trim.equals("UPLINK")) {
                                    new AlertHelperclass().ntoast("Please Select a Valid OLT Uplink", Pms_Setting_Detail_Activity.this.getApplicationContext());
                                } else {
                                    Pms_Setting_Detail_Activity.this.pms_get_oid(str6, trim, str7, str8);
                                }
                            }
                        });
                        return;
                    }
                    Pms_Setting_Detail_Activity.this.txt_alert.setText(jSONObject.getString("ERROR"));
                    Pms_Setting_Detail_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                Pms_Setting_Detail_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Pms_Setting_Detail_Activity.this.getApplicationContext()));
                Pms_Setting_Detail_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("username", Pms_Setting_Detail_Activity.this.Pref_Username);
                hashMap.put("random_key", Pms_Setting_Detail_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Pms_Setting_Detail_Activity.this.androidId);
                hashMap.put("olt_ip", str9);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void pms_get_oid(String str, String str2, String str3, String str4) {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str5 = str;
        final String str6 = str3;
        final String str7 = str4;
        final String str8 = str2;
        AnonymousClass17 r0 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_tip_oid_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                Pms_Setting_Detail_Activity.this.uplink_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                        new AlertHelperclass().ptoast("Congratulations!!\nPms is Successfully Configured", Pms_Setting_Detail_Activity.this.getApplicationContext());
                        new Intent(Pms_Setting_Detail_Activity.this, Pms_Setting_Detail_Activity.class);
                        Pms_Setting_Detail_Activity.this.finish();
                        Pms_Setting_Detail_Activity.this.overridePendingTransition(0, 0);
                        Pms_Setting_Detail_Activity pms_Setting_Detail_Activity = Pms_Setting_Detail_Activity.this;
                        pms_Setting_Detail_Activity.startActivity(pms_Setting_Detail_Activity.getIntent());
                        Pms_Setting_Detail_Activity.this.overridePendingTransition(0, 0);
                        return;
                    }
                    Pms_Setting_Detail_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                    Pms_Setting_Detail_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Pms_Setting_Detail_Activity.this.progress_dialog.cancel();
                Pms_Setting_Detail_Activity.this.uplink_dialog.cancel();
                Pms_Setting_Detail_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Pms_Setting_Detail_Activity.this.getApplicationContext()));
                Pms_Setting_Detail_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("username", Pms_Setting_Detail_Activity.this.Pref_Username);
                hashMap.put("random_key", Pms_Setting_Detail_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Pms_Setting_Detail_Activity.this.androidId);
                hashMap.put("olt_ip", str5);
                hashMap.put("olt_vlan", str6);
                hashMap.put("nas_ip", str7);
                hashMap.put("olt_uplink", str8);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }
}
