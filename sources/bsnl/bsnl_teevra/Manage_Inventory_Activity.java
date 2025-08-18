package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class Manage_Inventory_Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    /* access modifiers changed from: private */
    public String Circle_name;
    public int NUM_ITEMS_PAGE = 100;
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Password;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private Boolean Pref_setting_Key;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    /* access modifiers changed from: private */
    public String Ssa_name;
    public int TOTAL_LIST_ITEMS = 0;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView btn_inventory_populate;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public int countt = 0;
    /* access modifiers changed from: private */
    public String element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private JSONArray jArray;
    private LinearLayout lay_btn;
    private LinearLayout lay_inventory;
    private LinearLayout lay_inventory_display;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_inventory;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    private TableLayout tbl_inventory;
    private TableLayout tbl_inventory_header;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header1;
    private TextView txt_manageinventory;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_activity);
        this.imageView = (ImageView) findViewById(R.id.img_header);
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
        this.Pref_Access_Level = sharedPreferences2.getString("access_level_Key", (String) null);
        this.Pref_Role = this.sharedPreferences.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_setting_Key = Boolean.valueOf(this.sharedPreferences.getBoolean("setting_Key", false));
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.btn_inventory_populate = (TextView) findViewById(R.id.btn_inventory_populate);
        this.tbl_inventory_header = (TableLayout) findViewById(R.id.tbl_inventory_header);
        this.tbl_inventory = (TableLayout) findViewById(R.id.tbl_inventory);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.sp_inventory = (Spinner) findViewById(R.id.sp_inventory);
        this.lay_inventory_display = (LinearLayout) findViewById(R.id.lay_inventory_diaplay);
        this.lay_inventory = (LinearLayout) findViewById(R.id.lay_inventory);
        this.txt_manageinventory = (TextView) findViewById(R.id.txt_manageinventory);
        this.et_search = (EditText) findViewById(R.id.et_search_keyword);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.lay_inventory_display.setVisibility(4);
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "register_circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_Inventory_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_Inventory_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                    Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                    Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                    ArrayAdapter unused = manage_Inventory_Activity.adapter_circle = new ArrayAdapter(manage_Inventory_Activity2, R.layout.spinner_item, manage_Inventory_Activity2.circle_element);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Manage_Inventory_Activity.this.sp_circle.setAdapter(Manage_Inventory_Activity.this.adapter_circle);
                Manage_Inventory_Activity.this.sp_circle.setSelection(Manage_Inventory_Activity.this.adapter_circle.getPosition(Manage_Inventory_Activity.this.Pref_Circle));
                if (Manage_Inventory_Activity.this.Pref_Role.equals("NODAL") || Manage_Inventory_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                    Manage_Inventory_Activity.this.sp_circle.setEnabled(false);
                    Manage_Inventory_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                ArrayAdapter unused = manage_Inventory_Activity.adapter_circle = new ArrayAdapter(manage_Inventory_Activity2, R.layout.spinner_item, manage_Inventory_Activity2.circle_element);
                Manage_Inventory_Activity.this.sp_circle.setAdapter(Manage_Inventory_Activity.this.adapter_circle);
                Manage_Inventory_Activity.this.sp_circle.setSelection(Manage_Inventory_Activity.this.adapter_circle.getPosition(Manage_Inventory_Activity.this.Pref_Circle));
                if (Manage_Inventory_Activity.this.Pref_Role.equals("NODAL") || Manage_Inventory_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                    Manage_Inventory_Activity.this.sp_circle.setEnabled(false);
                    Manage_Inventory_Activity.this.sp_circle.setAlpha(0.5f);
                }
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Activity.this.getApplicationContext());
                Manage_Inventory_Activity.this.progress_dialog.cancel();
                Manage_Inventory_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_Inventory_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                return new HashMap();
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Manage_Inventory_Activity.this.ssa_element = new ArrayList();
                Manage_Inventory_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Manage_Inventory_Activity.this.sp_circle.getSelectedItem().toString();
                Manage_Inventory_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Manage_Inventory_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Manage_Inventory_Activity.this.getString(R.string.serverip) + "register_ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Manage_Inventory_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                            ArrayAdapter unused = Manage_Inventory_Activity.this.adapter_ssa = new ArrayAdapter(Manage_Inventory_Activity.this, R.layout.spinner_item, Manage_Inventory_Activity.this.ssa_element);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Manage_Inventory_Activity.this.sp_ssa.setAdapter(Manage_Inventory_Activity.this.adapter_ssa);
                        if (obj.equals(Manage_Inventory_Activity.this.Pref_Circle)) {
                            Manage_Inventory_Activity.this.sp_ssa.setSelection(Manage_Inventory_Activity.this.adapter_ssa.getPosition(Manage_Inventory_Activity.this.Pref_SSA));
                        }
                        if (Manage_Inventory_Activity.this.Pref_Role.equals("NODAL")) {
                            Manage_Inventory_Activity.this.sp_ssa.setEnabled(false);
                            Manage_Inventory_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ArrayAdapter unused = Manage_Inventory_Activity.this.adapter_ssa = new ArrayAdapter(Manage_Inventory_Activity.this, R.layout.spinner_item, Manage_Inventory_Activity.this.ssa_element);
                        Manage_Inventory_Activity.this.sp_ssa.setAdapter(Manage_Inventory_Activity.this.adapter_ssa);
                        if (obj.equals(Manage_Inventory_Activity.this.Pref_Circle)) {
                            Manage_Inventory_Activity.this.sp_ssa.setSelection(Manage_Inventory_Activity.this.adapter_ssa.getPosition(Manage_Inventory_Activity.this.Pref_SSA));
                        }
                        if (Manage_Inventory_Activity.this.Pref_Role.equals("NODAL")) {
                            Manage_Inventory_Activity.this.sp_ssa.setEnabled(false);
                            Manage_Inventory_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Activity.this.getApplicationContext());
                        Manage_Inventory_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_Inventory_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.sp_ssa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Manage_Inventory_Activity.this.sp_inventory.setAdapter(new ArrayAdapter(Manage_Inventory_Activity.this, R.layout.spinner_item, new String[]{"DSLAM", "LMG", "OLT", "TIP-OLT", "AGGR-SWITCH"}));
            }
        });
        this.btn_inventory_populate.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
        this.et_search.addTextChangedListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_inventory_populate) {
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            this.element = this.sp_inventory.getSelectedItem().toString();
            this.countt = 0;
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT CIRCLE", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT SSA", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.lay_inventory_display.setVisibility(8);
                this.et_search.getText().clear();
                this.tbl_inventory.removeAllViews();
                this.tbl_inventory_header.removeAllViews();
                this.txt_manageinventory.setText("CIRCLE: " + this.Circle_name + " | SSA: " + this.Ssa_name + " (" + this.element + ")");
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass8 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Inventory_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray unused = Manage_Inventory_Activity.this.Row_Array = new JSONArray(str);
                            if (Manage_Inventory_Activity.this.Row_Array.length() > 0) {
                                Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                                manage_Inventory_Activity.TOTAL_LIST_ITEMS = manage_Inventory_Activity.Row_Array.length();
                                Manage_Inventory_Activity.this.Btnfooter();
                                Manage_Inventory_Activity.this.loadList(0);
                                Manage_Inventory_Activity.this.CheckBtnBackGroud(0);
                                return;
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(Manage_Inventory_Activity.this);
                            View inflate = Manage_Inventory_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("ADD");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused2 = Manage_Inventory_Activity.this.info_dialog = builder.create();
                            Manage_Inventory_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText(Html.fromHtml("<b>INFO</b>"));
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("<b>" + Manage_Inventory_Activity.this.element + "</b> Inventory For <b>" + Manage_Inventory_Activity.this.Ssa_name + "</b> Is Not Present<br><br>Do You Want To Add Inventory ?"));
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Manage_Inventory_Activity.this.info_dialog.cancel();
                                }
                            });
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Manage_Inventory_Activity.this.info_dialog.cancel();
                                    if (!Manage_Inventory_Activity.this.Pref_SSA.equals(Manage_Inventory_Activity.this.Ssa_name) || !Manage_Inventory_Activity.this.Pref_Circle.equals(Manage_Inventory_Activity.this.Circle_name) || (!Manage_Inventory_Activity.this.Pref_Role.equals("NODAL") && !Manage_Inventory_Activity.this.Pref_Role.equals("SUPER"))) {
                                        Manage_Inventory_Activity.this.txt_alert.setText("Only Concerned SSA Nodal Can Add Inventory");
                                        Manage_Inventory_Activity.this.error_dialog.show();
                                    } else if (Manage_Inventory_Activity.this.element.equals("AGGR-SWITCH")) {
                                        Manage_Inventory_Activity.this.startActivity(new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Add_Switch_Activity.class));
                                    } else {
                                        Intent intent = new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Add_Activity.class);
                                        intent.putExtra("network_element", Manage_Inventory_Activity.this.element);
                                        Manage_Inventory_Activity.this.startActivity(intent);
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Activity.this.getApplicationContext());
                        Manage_Inventory_Activity.this.progress_dialog.cancel();
                        Manage_Inventory_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_Inventory_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Manage_Inventory_Activity.this.Circle_name);
                        hashMap.put("ssa", Manage_Inventory_Activity.this.Ssa_name);
                        hashMap.put("network", Manage_Inventory_Activity.this.element);
                        hashMap.put("username", Manage_Inventory_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Inventory_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Inventory_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    private void Populate_table(JSONArray jSONArray, int i) {
        TextView textView;
        Manage_Inventory_Activity manage_Inventory_Activity = this;
        manage_Inventory_Activity.tbl_inventory.removeAllViews();
        manage_Inventory_Activity.tbl_inventory_header.removeAllViews();
        TableRow tableRow = new TableRow(manage_Inventory_Activity);
        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
        TableRow tableRow2 = new TableRow(manage_Inventory_Activity);
        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
        TextView textView2 = new TextView(manage_Inventory_Activity);
        textView2.setText("MANAGE " + manage_Inventory_Activity.element);
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setPadding(20, 15, 20, 15);
        textView2.setTypeface((Typeface) null, 1);
        textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView2.setBackgroundResource(R.drawable.new_style1);
        TextView textView3 = new TextView(manage_Inventory_Activity);
        textView3.setText("SR NO");
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        textView3.setPadding(20, 15, 20, 15);
        textView3.setTypeface((Typeface) null, 1);
        textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView3.setBackgroundResource(R.drawable.new_style1);
        TextView textView4 = new TextView(manage_Inventory_Activity);
        textView4.setText("VENDOR");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(manage_Inventory_Activity);
        textView5.setText("CIRCLE");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(manage_Inventory_Activity);
        textView6.setText("SSA");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(manage_Inventory_Activity);
        textView7.setText("EXGCODE");
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(manage_Inventory_Activity);
        textView8.setText(CodePackage.LOCATION);
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        String str = CodePackage.LOCATION;
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(manage_Inventory_Activity);
        String str2 = "EXGCODE";
        textView9.setText(manage_Inventory_Activity.element + "-IP");
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        TextView textView10 = new TextView(manage_Inventory_Activity);
        textView10.setText("N/W-ELEMENT");
        textView10.setTextColor(-1);
        textView10.setGravity(17);
        String str3 = "SSA";
        textView10.setPadding(20, 15, 20, 15);
        textView10.setTypeface((Typeface) null, 1);
        textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView10.setBackgroundResource(R.drawable.new_style1);
        TextView textView11 = new TextView(manage_Inventory_Activity);
        textView11.setText("MAKE");
        String str4 = "MAKE";
        textView11.setTextColor(-1);
        textView11.setGravity(17);
        String str5 = "CIRCLE";
        textView11.setPadding(20, 15, 20, 15);
        textView11.setTypeface((Typeface) null, 1);
        textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView11.setBackgroundResource(R.drawable.new_style1);
        TextView textView12 = new TextView(manage_Inventory_Activity);
        textView12.setText("OUTER-VLAN");
        textView12.setTextColor(-1);
        textView12.setGravity(17);
        String str6 = "VENDOR";
        textView12.setPadding(20, 15, 20, 15);
        textView12.setTypeface((Typeface) null, 1);
        textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView12.setBackgroundResource(R.drawable.new_style1);
        TextView textView13 = new TextView(manage_Inventory_Activity);
        textView13.setText("START INNER-VLAN");
        textView13.setTextColor(-1);
        textView13.setGravity(17);
        TextView textView14 = textView11;
        textView13.setPadding(20, 15, 20, 15);
        textView13.setTypeface((Typeface) null, 1);
        textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView13.setBackgroundResource(R.drawable.new_style1);
        TextView textView15 = new TextView(manage_Inventory_Activity);
        textView15.setText("LAST INNER-VLAN");
        textView15.setTextColor(-1);
        textView15.setGravity(17);
        TextView textView16 = textView13;
        textView15.setPadding(20, 15, 20, 15);
        textView15.setTypeface((Typeface) null, 1);
        textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView15.setBackgroundResource(R.drawable.new_style1);
        TextView textView17 = new TextView(manage_Inventory_Activity);
        textView17.setText("NAS-IP");
        textView17.setTextColor(-1);
        textView17.setGravity(17);
        TextView textView18 = textView15;
        textView17.setPadding(20, 15, 20, 15);
        textView17.setTypeface((Typeface) null, 1);
        textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView17.setBackgroundResource(R.drawable.new_style1);
        TextView textView19 = new TextView(manage_Inventory_Activity);
        textView19.setText("BNG-IP");
        textView19.setTextColor(-1);
        textView19.setGravity(17);
        TextView textView20 = textView17;
        textView19.setPadding(20, 15, 20, 15);
        textView19.setTypeface((Typeface) null, 1);
        textView19.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView19.setBackgroundResource(R.drawable.new_style1);
        TextView textView21 = new TextView(manage_Inventory_Activity);
        textView21.setText("BNG-NAME");
        textView21.setTextColor(-1);
        textView21.setGravity(17);
        TextView textView22 = textView19;
        textView21.setPadding(20, 15, 20, 15);
        textView21.setTypeface((Typeface) null, 1);
        textView21.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView21.setBackgroundResource(R.drawable.new_style1);
        TextView textView23 = new TextView(manage_Inventory_Activity);
        textView23.setText("OPERATIONAL");
        textView23.setTextColor(-1);
        textView23.setGravity(17);
        TextView textView24 = textView21;
        textView23.setPadding(20, 15, 20, 15);
        textView23.setTypeface((Typeface) null, 1);
        textView23.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView23.setBackgroundResource(R.drawable.new_style1);
        tableRow2.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView5);
        tableRow.addView(textView6);
        tableRow.addView(textView7);
        tableRow.addView(textView8);
        String str7 = "AGGR-SWITCH";
        if (!manage_Inventory_Activity.element.equals(str7)) {
            tableRow.addView(textView12);
        }
        tableRow.addView(textView9);
        tableRow.addView(textView10);
        tableRow.addView(textView14);
        if (!manage_Inventory_Activity.element.equals(str7)) {
            tableRow.addView(textView16);
            tableRow.addView(textView18);
        }
        tableRow.addView(textView23);
        if (!manage_Inventory_Activity.element.equals(str7)) {
            tableRow.addView(textView20);
        }
        tableRow.addView(textView22);
        tableRow.addView(textView24);
        manage_Inventory_Activity.tbl_inventory_header.addView(tableRow2);
        manage_Inventory_Activity.tbl_inventory.addView(tableRow);
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String str8 = str6;
                String string = jSONObject.getString(str8);
                String str9 = str5;
                String string2 = jSONObject.getString(str9);
                String str10 = str3;
                String string3 = jSONObject.getString(str10);
                String str11 = str2;
                String string4 = jSONObject.getString(str11);
                String str12 = str;
                String string5 = jSONObject.getString(str12);
                String str13 = str12;
                String string6 = jSONObject.getString("IP");
                String str14 = str9;
                String string7 = jSONObject.getString("N/W_ELEMENT");
                int i3 = i2;
                String str15 = str4;
                String string8 = jSONObject.getString(str15);
                String str16 = str11;
                String string9 = jSONObject.getString("OUTER_VLAN");
                String str17 = str8;
                String string10 = jSONObject.getString("START_INNER_VLAN");
                String str18 = str10;
                String string11 = jSONObject.getString("LAST_INNER_VLAN");
                String str19 = str15;
                String string12 = jSONObject.getString("MANAGEABLE");
                String str20 = str7;
                String string13 = jSONObject.getString("NAS_IP");
                String str21 = string12;
                String string14 = jSONObject.getString("BNG_IP");
                String string15 = jSONObject.getString("BNG_NAME");
                TableRow tableRow3 = new TableRow(manage_Inventory_Activity);
                String str22 = string15;
                String str23 = string13;
                tableRow3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow3.setGravity(17);
                TableRow tableRow4 = new TableRow(manage_Inventory_Activity);
                TableRow tableRow5 = tableRow3;
                tableRow4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow4.setGravity(17);
                String str24 = string10;
                String str25 = string11;
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, (int) (((double) getResources().getDimension(R.dimen.mediumtext)) * 2.4d));
                LinearLayout linearLayout = new LinearLayout(manage_Inventory_Activity);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(17);
                linearLayout.setOrientation(0);
                linearLayout.setPadding(10, 10, 10, 10);
                linearLayout.setBackgroundResource(R.drawable.style17);
                String str26 = str24;
                String str27 = string9;
                String str28 = string8;
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 1.0f);
                ImageView imageView2 = new ImageView(manage_Inventory_Activity);
                String str29 = string7;
                layoutParams2.setMargins(10, 0, 5, 0);
                imageView2.setLayoutParams(layoutParams2);
                imageView2.setPadding(5, 5, 5, 5);
                imageView2.setClickable(true);
                imageView2.setImageResource(R.drawable.ic_action_add);
                imageView2.setBackgroundResource(R.drawable.button01);
                ImageView imageView3 = new ImageView(manage_Inventory_Activity);
                layoutParams2.setMargins(5, 0, 5, 0);
                imageView3.setLayoutParams(layoutParams2);
                imageView3.setPadding(5, 5, 5, 5);
                imageView3.setClickable(true);
                imageView3.setImageResource(R.drawable.ic_action_edit);
                imageView3.setBackgroundResource(R.drawable.button01);
                ImageView imageView4 = new ImageView(manage_Inventory_Activity);
                String str30 = string5;
                String str31 = string6;
                layoutParams2.setMargins(5, 0, 10, 0);
                imageView4.setLayoutParams(layoutParams2);
                imageView4.setPadding(5, 5, 5, 5);
                imageView4.setClickable(true);
                imageView4.setImageResource(R.drawable.ic_action_delete);
                imageView4.setBackgroundResource(R.drawable.button01);
                linearLayout.addView(imageView2);
                linearLayout.addView(imageView3);
                linearLayout.addView(imageView4);
                tableRow4.addView(linearLayout);
                TextView textView25 = new TextView(manage_Inventory_Activity);
                textView25.setLayoutParams(layoutParams);
                textView25.setText(Integer.toString(i + i2 + 1));
                textView25.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView25.setGravity(17);
                textView25.setPadding(20, 15, 20, 15);
                textView25.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView25.setBackgroundResource(R.drawable.style17);
                TextView textView26 = new TextView(manage_Inventory_Activity);
                textView26.setLayoutParams(layoutParams);
                textView26.setText(string);
                textView26.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView26.setGravity(17);
                textView26.setPadding(20, 15, 20, 15);
                textView26.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView26.setBackgroundResource(R.drawable.style17);
                TextView textView27 = new TextView(manage_Inventory_Activity);
                textView27.setLayoutParams(layoutParams);
                textView27.setText(string2);
                textView27.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView27.setGravity(17);
                textView27.setPadding(20, 15, 20, 15);
                textView27.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView27.setBackgroundResource(R.drawable.style17);
                TextView textView28 = new TextView(manage_Inventory_Activity);
                textView28.setLayoutParams(layoutParams);
                textView28.setText(string3);
                textView28.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView28.setGravity(17);
                textView28.setPadding(20, 15, 20, 15);
                textView28.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView28.setBackgroundResource(R.drawable.style17);
                TextView textView29 = new TextView(manage_Inventory_Activity);
                textView29.setLayoutParams(layoutParams);
                textView29.setText(string4);
                textView29.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView29.setGravity(17);
                ImageView imageView5 = imageView4;
                textView29.setPadding(20, 15, 20, 15);
                textView29.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView29.setBackgroundResource(R.drawable.style17);
                TextView textView30 = new TextView(manage_Inventory_Activity);
                textView30.setLayoutParams(layoutParams);
                String str32 = str30;
                textView30.setText(str32);
                String str33 = string4;
                textView30.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView30.setGravity(17);
                String str34 = str32;
                textView30.setPadding(20, 15, 20, 15);
                textView30.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView30.setBackgroundResource(R.drawable.style17);
                TextView textView31 = new TextView(manage_Inventory_Activity);
                textView31.setLayoutParams(layoutParams);
                String str35 = str31;
                textView31.setText(str35);
                String str36 = str35;
                textView31.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView31.setGravity(17);
                ImageView imageView6 = imageView3;
                textView31.setPadding(20, 15, 20, 15);
                textView31.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView31.setBackgroundResource(R.drawable.style17);
                TextView textView32 = new TextView(manage_Inventory_Activity);
                textView32.setLayoutParams(layoutParams);
                String str37 = str29;
                textView32.setText(str37);
                String str38 = str37;
                textView32.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView32.setGravity(17);
                String str39 = string3;
                textView32.setPadding(20, 15, 20, 15);
                textView32.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView32.setBackgroundResource(R.drawable.style17);
                TextView textView33 = new TextView(manage_Inventory_Activity);
                textView33.setLayoutParams(layoutParams);
                String str40 = str28;
                textView33.setText(str40);
                String str41 = str40;
                textView33.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView33.setGravity(17);
                String str42 = string2;
                textView33.setPadding(20, 15, 20, 15);
                textView33.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView33.setBackgroundResource(R.drawable.style17);
                TextView textView34 = new TextView(manage_Inventory_Activity);
                textView34.setLayoutParams(layoutParams);
                String str43 = str27;
                textView34.setText(str43);
                String str44 = str43;
                textView34.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView34.setGravity(17);
                String str45 = string;
                textView34.setPadding(20, 15, 20, 15);
                textView34.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView34.setBackgroundResource(R.drawable.style17);
                TextView textView35 = new TextView(manage_Inventory_Activity);
                textView35.setLayoutParams(layoutParams);
                String str46 = str26;
                textView35.setText(str46);
                String str47 = str46;
                textView35.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView35.setGravity(17);
                ImageView imageView7 = imageView2;
                textView35.setPadding(20, 15, 20, 15);
                textView35.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView35.setBackgroundResource(R.drawable.style17);
                TextView textView36 = new TextView(manage_Inventory_Activity);
                textView36.setLayoutParams(layoutParams);
                String str48 = str25;
                textView36.setText(str48);
                String str49 = str48;
                textView36.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView36.setGravity(17);
                TableRow tableRow6 = tableRow4;
                textView36.setPadding(20, 15, 20, 15);
                textView36.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView36.setBackgroundResource(R.drawable.style17);
                TextView textView37 = new TextView(manage_Inventory_Activity);
                textView37.setLayoutParams(layoutParams);
                String str50 = str23;
                textView37.setText(str50);
                String str51 = str50;
                textView37.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView37.setGravity(17);
                TextView textView38 = textView36;
                textView37.setPadding(20, 15, 20, 15);
                textView37.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView37.setBackgroundResource(R.drawable.style17);
                TextView textView39 = new TextView(manage_Inventory_Activity);
                textView39.setLayoutParams(layoutParams);
                String str52 = string14;
                textView39.setText(str52);
                String str53 = str52;
                textView39.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView39.setGravity(17);
                TextView textView40 = textView37;
                textView39.setPadding(20, 15, 20, 15);
                textView39.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView39.setBackgroundResource(R.drawable.style17);
                TextView textView41 = new TextView(manage_Inventory_Activity);
                textView41.setLayoutParams(layoutParams);
                String str54 = str22;
                textView41.setText(str54);
                String str55 = str54;
                textView41.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView41.setGravity(17);
                TextView textView42 = textView39;
                textView41.setPadding(20, 15, 20, 15);
                textView41.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView41.setBackgroundResource(R.drawable.style17);
                TextView textView43 = new TextView(manage_Inventory_Activity);
                textView43.setLayoutParams(layoutParams);
                String str56 = str21;
                textView43.setText(str56);
                if (str56.equals("MANAGED")) {
                    textView = textView41;
                    textView43.setTextColor(getResources().getColor(R.color.colorGreen));
                } else {
                    textView = textView41;
                    if (str56.equals("UNMANAGED")) {
                        textView43.setTextColor(getResources().getColor(R.color.colorRed));
                    } else {
                        textView43.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                }
                textView43.setGravity(17);
                textView43.setPadding(20, 15, 20, 15);
                textView43.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView43.setBackgroundResource(R.drawable.style17);
                TableRow tableRow7 = tableRow5;
                tableRow7.addView(textView25);
                tableRow7.addView(textView26);
                tableRow7.addView(textView27);
                tableRow7.addView(textView28);
                tableRow7.addView(textView29);
                tableRow7.addView(textView30);
                String str57 = str20;
                if (!manage_Inventory_Activity.element.equals(str57)) {
                    tableRow7.addView(textView34);
                }
                tableRow7.addView(textView31);
                tableRow7.addView(textView32);
                tableRow7.addView(textView33);
                if (!manage_Inventory_Activity.element.equals(str57)) {
                    tableRow7.addView(textView35);
                    tableRow7.addView(textView38);
                }
                tableRow7.addView(textView43);
                if (!manage_Inventory_Activity.element.equals(str57)) {
                    tableRow7.addView(textView40);
                }
                tableRow7.addView(textView42);
                tableRow7.addView(textView);
                manage_Inventory_Activity.tbl_inventory.addView(tableRow7);
                manage_Inventory_Activity.tbl_inventory_header.addView(tableRow6);
                manage_Inventory_Activity.lay_inventory_display.setVisibility(0);
                imageView7.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!Manage_Inventory_Activity.this.Pref_SSA.equals(Manage_Inventory_Activity.this.Ssa_name) || !Manage_Inventory_Activity.this.Pref_Circle.equals(Manage_Inventory_Activity.this.Circle_name) || (!Manage_Inventory_Activity.this.Pref_Role.equals("NODAL") && !Manage_Inventory_Activity.this.Pref_Role.equals("SUPER"))) {
                            Manage_Inventory_Activity.this.txt_alert.setText("Only Concerned SSA Nodal Can Add Inventory");
                            Manage_Inventory_Activity.this.error_dialog.show();
                        } else if (Manage_Inventory_Activity.this.element.equals("AGGR-SWITCH")) {
                            Manage_Inventory_Activity.this.startActivity(new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Add_Switch_Activity.class));
                        } else {
                            Intent intent = new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Add_Activity.class);
                            intent.putExtra("network_element", Manage_Inventory_Activity.this.element);
                            Manage_Inventory_Activity.this.startActivity(intent);
                        }
                    }
                });
                String str58 = str34;
                final String str59 = str45;
                String str60 = str13;
                String str61 = str36;
                final String str62 = str42;
                String str63 = str14;
                String str64 = str38;
                final String str65 = str39;
                String str66 = str33;
                final String str67 = str66;
                String str68 = str16;
                String str69 = str44;
                final String str70 = str58;
                String str71 = str47;
                String str72 = str17;
                final String str73 = str61;
                String str74 = str49;
                String str75 = str18;
                final String str76 = str64;
                final String str77 = str41;
                final String str78 = str56;
                final String str79 = str53;
                AnonymousClass10 r56 = r1;
                final String str80 = str55;
                String str81 = str57;
                int i4 = i3;
                String str82 = str19;
                final String str83 = str69;
                ImageView imageView8 = imageView5;
                String str84 = str82;
                String str85 = str81;
                String str86 = str60;
                final String str87 = str71;
                final String str88 = str74;
                final String str89 = str51;
                AnonymousClass10 r1 = new View.OnClickListener(this) {
                    final /* synthetic */ Manage_Inventory_Activity this$0;

                    {
                        this.this$0 = r3;
                    }

                    public void onClick(View view) {
                        if (!this.this$0.Pref_SSA.equals(this.this$0.Ssa_name) || !this.this$0.Pref_Circle.equals(this.this$0.Circle_name) || (!this.this$0.Pref_Role.equals("NODAL") && !this.this$0.Pref_Role.equals("SUPER"))) {
                            this.this$0.txt_alert.setText("Only Concerned SSA Nodal Can Edit Inventory");
                            this.this$0.error_dialog.show();
                        } else if (this.this$0.element.equals("AGGR-SWITCH")) {
                            Intent intent = new Intent(this.this$0, Manage_Inventory_Edit_Switch_Activity.class);
                            intent.putExtra("VENDOR", str59);
                            intent.putExtra("CIRCLE", str62);
                            intent.putExtra("SSA", str65);
                            intent.putExtra("EXGCODE", str67);
                            intent.putExtra(CodePackage.LOCATION, str70);
                            intent.putExtra("IP", str73);
                            intent.putExtra("N/W_ELEMENT", str76);
                            intent.putExtra("MAKE", str77);
                            intent.putExtra("MANAGEABLE", str78);
                            intent.putExtra("BNGIP", str79);
                            intent.putExtra("BNGNAME", str80);
                            this.this$0.startActivity(intent);
                        } else {
                            Intent intent2 = new Intent(this.this$0, Manage_Inventory_Edit_Activity.class);
                            intent2.putExtra("network_element", this.this$0.element);
                            intent2.putExtra("VENDOR", str59);
                            intent2.putExtra("CIRCLE", str62);
                            intent2.putExtra("SSA", str65);
                            intent2.putExtra("EXGCODE", str67);
                            intent2.putExtra(CodePackage.LOCATION, str70);
                            intent2.putExtra("IP", str73);
                            intent2.putExtra("N/W_ELEMENT", str76);
                            intent2.putExtra("MAKE", str77);
                            intent2.putExtra("OUTERVLAN", str83);
                            intent2.putExtra("S_INNERVLAN", str87);
                            intent2.putExtra("L_INNERVLAN", str88);
                            intent2.putExtra("MANAGEABLE", str78);
                            intent2.putExtra("NASIP", str89);
                            intent2.putExtra("BNGIP", str79);
                            intent2.putExtra("BNGNAME", str80);
                            this.this$0.startActivity(intent2);
                        }
                    }
                };
                imageView6.setOnClickListener(r56);
                final String str90 = str45;
                final String str91 = str42;
                final String str92 = str39;
                final String str93 = str66;
                final String str94 = str58;
                final String str95 = str61;
                final String str96 = str64;
                final String str97 = str41;
                final String str98 = str53;
                final String str99 = str55;
                final String str100 = str69;
                final String str101 = str71;
                final String str102 = str74;
                final String str103 = str51;
                imageView8.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!Manage_Inventory_Activity.this.Pref_SSA.equals(Manage_Inventory_Activity.this.Ssa_name) || !Manage_Inventory_Activity.this.Pref_Circle.equals(Manage_Inventory_Activity.this.Circle_name) || (!Manage_Inventory_Activity.this.Pref_Role.equals("NODAL") && !Manage_Inventory_Activity.this.Pref_Role.equals("SUPER"))) {
                            Manage_Inventory_Activity.this.txt_alert.setText("Only Concerned SSA Nodal Can Delete Inventory");
                            Manage_Inventory_Activity.this.error_dialog.show();
                        } else if (Manage_Inventory_Activity.this.element.equals("AGGR-SWITCH")) {
                            Intent intent = new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Delete_Switch_Activity.class);
                            intent.putExtra("VENDOR", str90);
                            intent.putExtra("CIRCLE", str91);
                            intent.putExtra("SSA", str92);
                            intent.putExtra("EXGCODE", str93);
                            intent.putExtra(CodePackage.LOCATION, str94);
                            intent.putExtra("IP", str95);
                            intent.putExtra("N/W_ELEMENT", str96);
                            intent.putExtra("MAKE", str97);
                            intent.putExtra("BNGIP", str98);
                            intent.putExtra("BNGNAME", str99);
                            Manage_Inventory_Activity.this.startActivity(intent);
                        } else {
                            Intent intent2 = new Intent(Manage_Inventory_Activity.this, Manage_Inventory_Delete_Activity.class);
                            intent2.putExtra("network_element", Manage_Inventory_Activity.this.element);
                            intent2.putExtra("VENDOR", str90);
                            intent2.putExtra("CIRCLE", str91);
                            intent2.putExtra("SSA", str92);
                            intent2.putExtra("EXGCODE", str93);
                            intent2.putExtra(CodePackage.LOCATION, str94);
                            intent2.putExtra("IP", str95);
                            intent2.putExtra("N/W_ELEMENT", str96);
                            intent2.putExtra("MAKE", str97);
                            intent2.putExtra("OUTERVLAN", str100);
                            intent2.putExtra("S_INNERVLAN", str101);
                            intent2.putExtra("L_INNERVLAN", str102);
                            intent2.putExtra("NASIP", str103);
                            intent2.putExtra("BNGIP", str98);
                            intent2.putExtra("BNGNAME", str99);
                            Manage_Inventory_Activity.this.startActivity(intent2);
                        }
                    }
                });
                i2 = i4 + 1;
                manage_Inventory_Activity = this;
                str5 = str63;
                str3 = str75;
                str2 = str68;
                str6 = str72;
                str = str86;
                str4 = str84;
                str7 = str85;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void Btnfooter() {
        int i = this.TOTAL_LIST_ITEMS;
        int i2 = this.NUM_ITEMS_PAGE;
        final int i3 = (i / i2) + (i % i2 == 0 ? 0 : 1);
        this.lay_btn.removeAllViews();
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
                    if (Manage_Inventory_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Manage_Inventory_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity.loadList(manage_Inventory_Activity.countt + 1);
                    Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity2.CheckBtnBackGroud(manage_Inventory_Activity2.countt + 1);
                    Manage_Inventory_Activity manage_Inventory_Activity3 = Manage_Inventory_Activity.this;
                    int unused = manage_Inventory_Activity3.countt = manage_Inventory_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Manage_Inventory_Activity.this.countt == 0) {
                        Toast.makeText(Manage_Inventory_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity.loadList(manage_Inventory_Activity.countt - 1);
                    Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity2.CheckBtnBackGroud(manage_Inventory_Activity2.countt - 1);
                    Manage_Inventory_Activity manage_Inventory_Activity3 = Manage_Inventory_Activity.this;
                    int unused = manage_Inventory_Activity3.countt = manage_Inventory_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Manage_Inventory_Activity.this.loadList(i4);
                    Manage_Inventory_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Manage_Inventory_Activity.this.countt = i4;
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
        Populate_table(jSONArray, i2);
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
                    if (Manage_Inventory_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Manage_Inventory_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity.loadList1(manage_Inventory_Activity.search_count + 1, jSONArray);
                    Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity2.CheckBtnBackGroud1(manage_Inventory_Activity2.search_count + 1, jSONArray);
                    Manage_Inventory_Activity manage_Inventory_Activity3 = Manage_Inventory_Activity.this;
                    int unused = manage_Inventory_Activity3.search_count = manage_Inventory_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Manage_Inventory_Activity.this.search_count == 0) {
                        Toast.makeText(Manage_Inventory_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Manage_Inventory_Activity manage_Inventory_Activity = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity.loadList1(manage_Inventory_Activity.search_count - 1, jSONArray);
                    Manage_Inventory_Activity manage_Inventory_Activity2 = Manage_Inventory_Activity.this;
                    manage_Inventory_Activity2.CheckBtnBackGroud1(manage_Inventory_Activity2.search_count - 1, jSONArray);
                    Manage_Inventory_Activity manage_Inventory_Activity3 = Manage_Inventory_Activity.this;
                    int unused = manage_Inventory_Activity3.search_count = manage_Inventory_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Manage_Inventory_Activity.this.loadList1(i, jSONArray);
                    Manage_Inventory_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Manage_Inventory_Activity.this.search_count = i;
                }
            });
            i = i2;
        }
    }

    /* access modifiers changed from: private */
    public void loadList1(int i, JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
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
        Populate_table(jSONArray2, i2);
    }

    /* access modifiers changed from: private */
    public void CheckBtnBackGroud1(int i, JSONArray jSONArray) {
        int length = (jSONArray.length() / this.NUM_ITEMS_PAGE) + (jSONArray.length() % this.NUM_ITEMS_PAGE == 0 ? 0 : 1);
        this.title.setText("Page " + (i + 1) + " of " + length + " Pages");
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 == i) {
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorwhite));
            } else {
                this.btns[i2].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i2].setTextColor(getResources().getColor(R.color.colorblack));
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        String obj = this.et_search.getText().toString();
        this.tbl_inventory.removeAllViews();
        this.tbl_inventory_header.removeAllViews();
        this.searched_array = new JSONArray();
        this.search_count = 0;
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                if (jSONObject.getString("VENDOR").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("EXGCODE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("N/W_ELEMENT").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("MAKE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OUTER_VLAN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("MANAGEABLE").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("NAS_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("BNG_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("BNG_NAME").toUpperCase().contains(obj.toUpperCase())) {
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
        Intent intent = new Intent(this, Setting_New_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
