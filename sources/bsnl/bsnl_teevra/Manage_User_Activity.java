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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
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

public class Manage_User_Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    /* access modifiers changed from: private */
    public String Circle_name;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
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
    private TextView btn_user_populate;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search_keyword;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public JSONArray jArray;
    /* access modifiers changed from: private */
    public LinearLayout lay_user_display;
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
    public TableLayout tbl_user;
    /* access modifiers changed from: private */
    public TableLayout tbl_user_header;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_manageuser;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_user_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_user_populate = (TextView) findViewById(R.id.btn_user_populate);
        this.lay_user_display = (LinearLayout) findViewById(R.id.lay_user_display);
        this.txt_manageuser = (TextView) findViewById(R.id.txt_manageuser);
        this.tbl_user = (TableLayout) findViewById(R.id.tbl_user);
        this.tbl_user_header = (TableLayout) findViewById(R.id.tbl_user_header);
        this.et_search_keyword = (EditText) findViewById(R.id.et_search_keyword);
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
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "register_circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_User_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Manage_User_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                    Manage_User_Activity manage_User_Activity = Manage_User_Activity.this;
                    Manage_User_Activity manage_User_Activity2 = Manage_User_Activity.this;
                    ArrayAdapter unused = manage_User_Activity.adapter_circle = new ArrayAdapter(manage_User_Activity2, R.layout.spinner_item, manage_User_Activity2.circle_element);
                    Manage_User_Activity.this.sp_circle.setAdapter(Manage_User_Activity.this.adapter_circle);
                    Manage_User_Activity.this.sp_circle.setSelection(Manage_User_Activity.this.adapter_circle.getPosition(Manage_User_Activity.this.Pref_Circle));
                    if (Manage_User_Activity.this.Pref_Role.equals("NODAL") || Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                        Manage_User_Activity.this.sp_circle.setEnabled(false);
                        Manage_User_Activity.this.sp_circle.setAlpha(0.5f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Manage_User_Activity manage_User_Activity = Manage_User_Activity.this;
                Manage_User_Activity manage_User_Activity2 = Manage_User_Activity.this;
                ArrayAdapter unused = manage_User_Activity.adapter_circle = new ArrayAdapter(manage_User_Activity2, R.layout.spinner_item, manage_User_Activity2.circle_element);
                Manage_User_Activity.this.sp_circle.setAdapter(Manage_User_Activity.this.adapter_circle);
                Manage_User_Activity.this.sp_circle.setSelection(Manage_User_Activity.this.adapter_circle.getPosition(Manage_User_Activity.this.Pref_Circle));
                if (Manage_User_Activity.this.Pref_Role.equals("NODAL") || Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                    Manage_User_Activity.this.sp_circle.setEnabled(false);
                    Manage_User_Activity.this.sp_circle.setAlpha(0.5f);
                }
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Activity.this.getApplicationContext());
                Manage_User_Activity.this.progress_dialog.cancel();
                Manage_User_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_User_Activity.this.error_dialog.show();
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
                ArrayList unused = Manage_User_Activity.this.ssa_element = new ArrayList();
                Manage_User_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Manage_User_Activity.this.sp_circle.getSelectedItem().toString();
                if (!obj.equals("-- CIRCLE --")) {
                    Manage_User_Activity.this.progress_dialog.show();
                    RequestQueue newRequestQueue = Volley.newRequestQueue(Manage_User_Activity.this);
                    AnonymousClass3 r0 = new StringRequest(1, Manage_User_Activity.this.getString(R.string.serverip) + "register_ssa_populate.php", new Response.Listener<String>() {
                        public void onResponse(String str) {
                            Manage_User_Activity.this.progress_dialog.cancel();
                            try {
                                JSONArray jSONArray = new JSONArray(str);
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    Manage_User_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                                }
                                ArrayAdapter unused = Manage_User_Activity.this.adapter_ssa = new ArrayAdapter(Manage_User_Activity.this, R.layout.spinner_item, Manage_User_Activity.this.ssa_element);
                                Manage_User_Activity.this.sp_ssa.setAdapter(Manage_User_Activity.this.adapter_ssa);
                                if (obj.equals(Manage_User_Activity.this.Pref_Circle)) {
                                    Manage_User_Activity.this.sp_ssa.setSelection(Manage_User_Activity.this.adapter_ssa.getPosition(Manage_User_Activity.this.Pref_SSA));
                                }
                                if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                                    Manage_User_Activity.this.sp_ssa.setEnabled(false);
                                    Manage_User_Activity.this.sp_ssa.setAlpha(0.5f);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            ArrayAdapter unused = Manage_User_Activity.this.adapter_ssa = new ArrayAdapter(Manage_User_Activity.this, R.layout.spinner_item, Manage_User_Activity.this.ssa_element);
                            Manage_User_Activity.this.sp_ssa.setAdapter(Manage_User_Activity.this.adapter_ssa);
                            if (obj.equals(Manage_User_Activity.this.Pref_Circle)) {
                                Manage_User_Activity.this.sp_ssa.setSelection(Manage_User_Activity.this.adapter_ssa.getPosition(Manage_User_Activity.this.Pref_SSA));
                            }
                            if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                                Manage_User_Activity.this.sp_ssa.setEnabled(false);
                                Manage_User_Activity.this.sp_ssa.setAlpha(0.5f);
                            }
                            String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Activity.this.getApplicationContext());
                            Manage_User_Activity.this.progress_dialog.cancel();
                            Manage_User_Activity.this.txt_alert.setText(onErrorResponse);
                            Manage_User_Activity.this.error_dialog.show();
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
                    return;
                }
                Manage_User_Activity manage_User_Activity = Manage_User_Activity.this;
                Manage_User_Activity manage_User_Activity2 = Manage_User_Activity.this;
                ArrayAdapter unused2 = manage_User_Activity.adapter_ssa = new ArrayAdapter(manage_User_Activity2, R.layout.spinner_item, manage_User_Activity2.ssa_element);
                Manage_User_Activity.this.sp_ssa.setAdapter(Manage_User_Activity.this.adapter_ssa);
            }
        });
        this.btn_user_populate.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
        this.et_search_keyword.addTextChangedListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_user_populate) {
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT CIRCLE", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT SSA", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.et_search_keyword.getText().clear();
                this.lay_user_display.setVisibility(8);
                this.tbl_user.removeAllViews();
                this.tbl_user_header.removeAllViews();
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass7 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_user.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_User_Activity.this.txt_manageuser.setText("CIRCLE : " + Manage_User_Activity.this.Circle_name + " | SSA : " + Manage_User_Activity.this.Ssa_name);
                        TableRow tableRow = new TableRow(Manage_User_Activity.this);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                        TableRow tableRow2 = new TableRow(Manage_User_Activity.this);
                        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                        TextView textView = new TextView(Manage_User_Activity.this);
                        textView.setText("MANAGE USER");
                        textView.setTextColor(-1);
                        textView.setGravity(17);
                        textView.setPadding(20, 15, 20, 15);
                        textView.setTypeface((Typeface) null, 1);
                        textView.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView.setBackgroundResource(R.drawable.new_style1);
                        TextView textView2 = new TextView(Manage_User_Activity.this);
                        textView2.setText("SR No");
                        textView2.setTextColor(-1);
                        textView2.setGravity(17);
                        textView2.setPadding(20, 15, 20, 15);
                        textView2.setTypeface((Typeface) null, 1);
                        textView2.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView2.setBackgroundResource(R.drawable.new_style1);
                        TextView textView3 = new TextView(Manage_User_Activity.this);
                        String str2 = "USERNAME";
                        textView3.setText(str2);
                        textView3.setTextColor(-1);
                        textView3.setGravity(17);
                        textView3.setPadding(20, 15, 20, 15);
                        textView3.setTypeface((Typeface) null, 1);
                        textView3.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView3.setBackgroundResource(R.drawable.new_style1);
                        TextView textView4 = new TextView(Manage_User_Activity.this);
                        textView4.setText("NAME");
                        textView4.setTextColor(-1);
                        textView4.setGravity(17);
                        textView4.setPadding(20, 15, 20, 15);
                        textView4.setTypeface((Typeface) null, 1);
                        textView4.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView4.setBackgroundResource(R.drawable.new_style1);
                        TextView textView5 = new TextView(Manage_User_Activity.this);
                        textView5.setText("ROLE");
                        textView5.setTextColor(-1);
                        textView5.setGravity(17);
                        textView5.setPadding(20, 15, 20, 15);
                        textView5.setTypeface((Typeface) null, 1);
                        textView5.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView5.setBackgroundResource(R.drawable.new_style1);
                        TextView textView6 = new TextView(Manage_User_Activity.this);
                        textView6.setText("DESIGNATION");
                        textView6.setTextColor(-1);
                        textView6.setGravity(17);
                        textView6.setPadding(20, 15, 20, 15);
                        textView6.setTypeface((Typeface) null, 1);
                        textView6.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView6.setBackgroundResource(R.drawable.new_style1);
                        TextView textView7 = new TextView(Manage_User_Activity.this);
                        textView7.setText("MOBILE");
                        textView7.setTextColor(-1);
                        textView7.setGravity(17);
                        String str3 = "MOBILE";
                        textView7.setPadding(20, 15, 20, 15);
                        textView7.setTypeface((Typeface) null, 1);
                        textView7.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView7.setBackgroundResource(R.drawable.new_style1);
                        TextView textView8 = new TextView(Manage_User_Activity.this);
                        textView8.setText("ACCOUNT-STATUS");
                        textView8.setTextColor(-1);
                        textView8.setGravity(17);
                        String str4 = "DESIGNATION";
                        textView8.setPadding(20, 15, 20, 15);
                        textView8.setTypeface((Typeface) null, 1);
                        textView8.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView8.setBackgroundResource(R.drawable.new_style1);
                        TextView textView9 = new TextView(Manage_User_Activity.this);
                        textView9.setText("CREATED ON");
                        textView9.setTextColor(-1);
                        textView9.setGravity(17);
                        String str5 = "ROLE";
                        textView9.setPadding(20, 15, 20, 15);
                        textView9.setTypeface((Typeface) null, 1);
                        textView9.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView9.setBackgroundResource(R.drawable.new_style1);
                        TextView textView10 = new TextView(Manage_User_Activity.this);
                        textView10.setText("LAST LOGIN");
                        textView10.setTextColor(-1);
                        textView10.setGravity(17);
                        String str6 = "NAME";
                        textView10.setPadding(20, 15, 20, 15);
                        textView10.setTypeface((Typeface) null, 1);
                        textView10.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView10.setBackgroundResource(R.drawable.new_style1);
                        tableRow2.addView(textView);
                        tableRow.addView(textView2);
                        tableRow.addView(textView3);
                        tableRow.addView(textView4);
                        tableRow.addView(textView7);
                        tableRow.addView(textView5);
                        tableRow.addView(textView6);
                        tableRow.addView(textView8);
                        tableRow.addView(textView10);
                        tableRow.addView(textView9);
                        Manage_User_Activity.this.tbl_user_header.addView(tableRow2);
                        Manage_User_Activity.this.tbl_user.addView(tableRow);
                        try {
                            JSONArray unused = Manage_User_Activity.this.jArray = new JSONArray(str);
                            if (Manage_User_Activity.this.jArray.length() > 0) {
                                int i = 0;
                                while (i < Manage_User_Activity.this.jArray.length()) {
                                    int i2 = i + 1;
                                    JSONObject jSONObject = Manage_User_Activity.this.jArray.getJSONObject(i);
                                    TableRow tableRow3 = new TableRow(Manage_User_Activity.this);
                                    tableRow3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                                    tableRow3.setGravity(17);
                                    TableRow tableRow4 = new TableRow(Manage_User_Activity.this);
                                    tableRow4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                                    tableRow4.setGravity(17);
                                    TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, (int) (((double) Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext)) * 2.4d));
                                    LinearLayout linearLayout = new LinearLayout(Manage_User_Activity.this);
                                    linearLayout.setLayoutParams(layoutParams);
                                    linearLayout.setGravity(17);
                                    linearLayout.setOrientation(0);
                                    linearLayout.setPadding(10, 10, 10, 10);
                                    linearLayout.setBackgroundResource(R.drawable.style17);
                                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                                    ImageView imageView = new ImageView(Manage_User_Activity.this);
                                    layoutParams2.setMargins(5, 0, 5, 0);
                                    imageView.setLayoutParams(layoutParams2);
                                    imageView.setPadding(5, 5, 5, 5);
                                    imageView.setClickable(true);
                                    imageView.setImageResource(R.drawable.ic_action_edit);
                                    imageView.setBackgroundResource(R.drawable.button01);
                                    ImageView imageView2 = new ImageView(Manage_User_Activity.this);
                                    layoutParams2.setMargins(5, 0, 10, 0);
                                    imageView2.setLayoutParams(layoutParams2);
                                    imageView2.setPadding(5, 5, 5, 5);
                                    imageView2.setClickable(true);
                                    imageView2.setImageResource(R.drawable.ic_action_delete);
                                    imageView2.setBackgroundResource(R.drawable.button01);
                                    linearLayout.addView(imageView);
                                    linearLayout.addView(imageView2);
                                    tableRow4.addView(linearLayout);
                                    TextView textView11 = new TextView(Manage_User_Activity.this);
                                    textView11.setLayoutParams(layoutParams);
                                    textView11.setText(Integer.toString(i2));
                                    textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView11.setGravity(17);
                                    textView11.setPadding(20, 15, 20, 15);
                                    textView11.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView11.setBackgroundResource(R.drawable.style17);
                                    TextView textView12 = new TextView(Manage_User_Activity.this);
                                    textView12.setLayoutParams(layoutParams);
                                    textView12.setText(jSONObject.getString(str2));
                                    textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView12.setGravity(17);
                                    textView12.setTypeface((Typeface) null, 1);
                                    textView12.setPadding(20, 15, 20, 15);
                                    textView12.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView12.setBackgroundResource(R.drawable.style17);
                                    TextView textView13 = new TextView(Manage_User_Activity.this);
                                    textView13.setLayoutParams(layoutParams);
                                    String str7 = str6;
                                    textView13.setText(jSONObject.getString(str7));
                                    textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView13.setGravity(17);
                                    textView13.setPadding(20, 15, 20, 15);
                                    textView13.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView13.setBackgroundResource(R.drawable.style17);
                                    TextView textView14 = new TextView(Manage_User_Activity.this);
                                    textView14.setLayoutParams(layoutParams);
                                    String str8 = str5;
                                    textView14.setText(jSONObject.getString(str8));
                                    textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView14.setGravity(17);
                                    int i3 = i2;
                                    textView14.setPadding(20, 15, 20, 15);
                                    textView14.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView14.setBackgroundResource(R.drawable.style17);
                                    TextView textView15 = new TextView(Manage_User_Activity.this);
                                    textView15.setLayoutParams(layoutParams);
                                    str6 = str7;
                                    String str9 = str4;
                                    textView15.setText(jSONObject.getString(str9));
                                    textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView15.setGravity(17);
                                    str4 = str9;
                                    textView15.setPadding(20, 15, 20, 15);
                                    textView15.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView15.setBackgroundResource(R.drawable.style17);
                                    TextView textView16 = new TextView(Manage_User_Activity.this);
                                    textView16.setLayoutParams(layoutParams);
                                    String str10 = str3;
                                    String str11 = str8;
                                    textView16.setText(jSONObject.getString(str10));
                                    textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView16.setGravity(17);
                                    String str12 = str10;
                                    textView16.setPadding(20, 15, 20, 15);
                                    textView16.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView16.setBackgroundResource(R.drawable.style17);
                                    TextView textView17 = new TextView(Manage_User_Activity.this);
                                    textView17.setLayoutParams(layoutParams);
                                    textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView17.setGravity(17);
                                    String str13 = str2;
                                    textView17.setTypeface((Typeface) null, 1);
                                    textView17.setPadding(20, 15, 20, 15);
                                    textView17.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView17.setBackgroundResource(R.drawable.style17);
                                    TextView textView18 = new TextView(Manage_User_Activity.this);
                                    textView18.setLayoutParams(layoutParams);
                                    textView18.setText(jSONObject.getString("CREATED_ON"));
                                    textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView18.setGravity(17);
                                    ImageView imageView3 = imageView2;
                                    textView18.setPadding(20, 15, 20, 15);
                                    textView18.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView18.setBackgroundResource(R.drawable.style17);
                                    TextView textView19 = new TextView(Manage_User_Activity.this);
                                    textView19.setLayoutParams(layoutParams);
                                    textView19.setText(jSONObject.getString("LAST_LOGIN"));
                                    textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                    textView19.setGravity(17);
                                    textView19.setPadding(20, 15, 20, 15);
                                    textView19.setTextSize(0, Manage_User_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                    textView19.setBackgroundResource(R.drawable.style17);
                                    if (jSONObject.getBoolean("ACCOUNT_STATUS")) {
                                        textView17.setText("APPROVED");
                                    } else {
                                        textView17.setText("PENDING");
                                        textView11.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView12.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView13.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView14.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView15.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView16.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView17.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView18.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                        textView19.setTextColor(Manage_User_Activity.this.getResources().getColor(R.color.colorRed));
                                    }
                                    tableRow3.addView(textView11);
                                    tableRow3.addView(textView12);
                                    tableRow3.addView(textView13);
                                    tableRow3.addView(textView16);
                                    tableRow3.addView(textView14);
                                    tableRow3.addView(textView15);
                                    tableRow3.addView(textView17);
                                    tableRow3.addView(textView19);
                                    tableRow3.addView(textView18);
                                    Manage_User_Activity.this.tbl_user.addView(tableRow3);
                                    Manage_User_Activity.this.tbl_user_header.addView(tableRow4);
                                    final String trim = textView14.getText().toString().trim();
                                    final String trim2 = textView12.getText().toString().trim();
                                    imageView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            if (Manage_User_Activity.this.Pref_Username.equals(trim2)) {
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Set Privileges To Yourself");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                                                if (trim.equals("USER")) {
                                                    Intent intent = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                                    intent.putExtra("username", trim2);
                                                    intent.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                                                if (trim.equals("USER") || trim.equals("NODAL")) {
                                                    Intent intent2 = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                                    intent2.putExtra("username", trim2);
                                                    intent2.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent2.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent2);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (!Manage_User_Activity.this.Pref_Role.equals("SUPER")) {
                                            } else {
                                                if (trim.equals("USER") || trim.equals("NODAL") || trim.equals("ADMINISTRATOR")) {
                                                    Intent intent3 = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                                    intent3.putExtra("username", trim2);
                                                    intent3.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent3.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent3);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            }
                                        }
                                    });
                                    imageView3.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            if (Manage_User_Activity.this.Pref_Username.equals(trim2)) {
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete Your Account By Yourself");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                                                if (trim.equals("USER")) {
                                                    Intent intent = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                                    intent.putExtra("username", trim2);
                                                    intent.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                                                if (trim.equals("USER") || trim.equals("NODAL")) {
                                                    Intent intent2 = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                                    intent2.putExtra("username", trim2);
                                                    intent2.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent2.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent2);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            } else if (Manage_User_Activity.this.Pref_Role.equals("SUPER")) {
                                                new AlertHelperclass().ptoast(Manage_User_Activity.this.Circle_name, Manage_User_Activity.this.getApplicationContext());
                                                if (trim.equals("USER") || trim.equals("NODAL") || trim.equals("ADMINISTRATOR")) {
                                                    Intent intent3 = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                                    intent3.putExtra("username", trim2);
                                                    intent3.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                                    intent3.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                                    Manage_User_Activity.this.startActivity(intent3);
                                                    return;
                                                }
                                                Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                                                Manage_User_Activity.this.error_dialog.show();
                                            }
                                        }
                                    });
                                    str2 = str13;
                                    i = i3;
                                    str5 = str11;
                                    str3 = str12;
                                }
                            } else {
                                Manage_User_Activity.this.lay_user_display.setVisibility(0);
                                Manage_User_Activity.this.tbl_user.removeAllViews();
                                Manage_User_Activity.this.tbl_user_header.removeAllViews();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Manage_User_Activity.this.lay_user_display.setVisibility(0);
                        Manage_User_Activity.this.progress_dialog.cancel();
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Activity.this.getApplicationContext());
                        Manage_User_Activity.this.progress_dialog.cancel();
                        Manage_User_Activity.this.txt_alert.setText(onErrorResponse);
                        Manage_User_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Manage_User_Activity.this.Circle_name);
                        hashMap.put("ssa", Manage_User_Activity.this.Ssa_name);
                        hashMap.put("username", Manage_User_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_User_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_User_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        String str;
        String str2;
        String str3;
        int i;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String obj = this.et_search_keyword.getText().toString();
        this.tbl_user.removeAllViews();
        this.tbl_user_header.removeAllViews();
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
        TableRow tableRow2 = new TableRow(this);
        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
        TextView textView = new TextView(this);
        textView.setText("MANAGE USER");
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setPadding(20, 15, 20, 15);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView.setBackgroundResource(R.drawable.new_style1);
        TextView textView2 = new TextView(this);
        textView2.setText("SR No");
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setPadding(20, 15, 20, 15);
        textView2.setTypeface((Typeface) null, 1);
        textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView2.setBackgroundResource(R.drawable.new_style1);
        TextView textView3 = new TextView(this);
        String str10 = "USERNAME";
        textView3.setText(str10);
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        textView3.setPadding(20, 15, 20, 15);
        textView3.setTypeface((Typeface) null, 1);
        textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView3.setBackgroundResource(R.drawable.new_style1);
        TextView textView4 = new TextView(this);
        textView4.setText("NAME");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(this);
        textView5.setText("ROLE");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(this);
        textView6.setText("DESIGNATION");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(this);
        textView7.setText("MOBILE");
        String str11 = "DESIGNATION";
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        String str12 = "ROLE";
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(this);
        textView8.setText("ACCOUNT-STATUS");
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        String str13 = "MOBILE";
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(this);
        textView9.setText("CREATED ON");
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        String str14 = "NAME";
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        TextView textView10 = new TextView(this);
        textView10.setText("LAST LOGIN");
        textView10.setTextColor(-1);
        textView10.setGravity(17);
        String str15 = obj;
        textView10.setPadding(20, 15, 20, 15);
        textView10.setTypeface((Typeface) null, 1);
        textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView10.setBackgroundResource(R.drawable.new_style1);
        tableRow2.addView(textView);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView7);
        tableRow.addView(textView5);
        tableRow.addView(textView6);
        tableRow.addView(textView8);
        tableRow.addView(textView10);
        tableRow.addView(textView9);
        this.tbl_user_header.addView(tableRow2);
        this.tbl_user.addView(tableRow);
        int i2 = 1;
        int i3 = 0;
        while (i3 < this.jArray.length()) {
            try {
                JSONObject jSONObject = this.jArray.getJSONObject(i3);
                if (!jSONObject.getString(str10).toUpperCase().contains(str15.toUpperCase())) {
                    str9 = str14;
                    if (!jSONObject.getString(str9).toUpperCase().contains(str15.toUpperCase())) {
                        str8 = str13;
                        if (!jSONObject.getString(str8).toUpperCase().contains(str15.toUpperCase())) {
                            str7 = str12;
                            if (!jSONObject.getString(str7).toUpperCase().contains(str15.toUpperCase())) {
                                str6 = str11;
                                if (!jSONObject.getString(str6).toUpperCase().contains(str15.toUpperCase())) {
                                    if (!jSONObject.getString("ACCOUNT_STATUS").toUpperCase().contains(str15.toUpperCase())) {
                                        str3 = str9;
                                        str = str8;
                                        str4 = str7;
                                        str2 = str6;
                                        str5 = str10;
                                        i = i3;
                                        i3 = i + 1;
                                        str10 = str5;
                                        str12 = str4;
                                        str14 = str3;
                                        str11 = str2;
                                        str13 = str;
                                    }
                                }
                            } else {
                                str6 = str11;
                            }
                        } else {
                            str6 = str11;
                            str7 = str12;
                        }
                    } else {
                        str6 = str11;
                        str7 = str12;
                        str8 = str13;
                    }
                } else {
                    str6 = str11;
                    str7 = str12;
                    str8 = str13;
                    str9 = str14;
                }
                TableRow tableRow3 = new TableRow(this);
                tableRow3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow3.setGravity(17);
                TableRow tableRow4 = new TableRow(this);
                tableRow4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow4.setGravity(17);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, (int) (((double) getResources().getDimension(R.dimen.mediumtext)) * 2.4d));
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(17);
                linearLayout.setOrientation(0);
                linearLayout.setPadding(10, 10, 10, 10);
                linearLayout.setBackgroundResource(R.drawable.style17);
                i = i3;
                TableRow tableRow5 = tableRow3;
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                ImageView imageView2 = new ImageView(this);
                layoutParams2.setMargins(5, 0, 5, 0);
                imageView2.setLayoutParams(layoutParams2);
                imageView2.setPadding(5, 5, 5, 5);
                imageView2.setClickable(true);
                imageView2.setImageResource(R.drawable.ic_action_edit);
                imageView2.setBackgroundResource(R.drawable.button01);
                ImageView imageView3 = new ImageView(this);
                String str16 = str8;
                layoutParams2.setMargins(5, 0, 10, 0);
                imageView3.setLayoutParams(layoutParams2);
                imageView3.setPadding(5, 5, 5, 5);
                imageView3.setClickable(true);
                imageView3.setImageResource(R.drawable.ic_action_delete);
                imageView3.setBackgroundResource(R.drawable.button01);
                linearLayout.addView(imageView2);
                linearLayout.addView(imageView3);
                tableRow4.addView(linearLayout);
                TextView textView11 = new TextView(this);
                textView11.setLayoutParams(layoutParams);
                int i4 = i2 + 1;
                textView11.setText(Integer.toString(i2));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(this);
                textView12.setLayoutParams(layoutParams);
                textView12.setText(jSONObject.getString(str10));
                textView12.setTypeface((Typeface) null, 1);
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                TextView textView13 = new TextView(this);
                textView13.setLayoutParams(layoutParams);
                textView13.setText(jSONObject.getString(str9));
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setPadding(20, 15, 20, 15);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView13.setBackgroundResource(R.drawable.style17);
                TextView textView14 = new TextView(this);
                textView14.setLayoutParams(layoutParams);
                textView14.setText(jSONObject.getString(str7));
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                str3 = str9;
                textView14.setPadding(20, 15, 20, 15);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView14.setBackgroundResource(R.drawable.style17);
                TextView textView15 = new TextView(this);
                textView15.setLayoutParams(layoutParams);
                textView15.setText(jSONObject.getString(str6));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                int i5 = i4;
                textView15.setPadding(20, 15, 20, 15);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView15.setBackgroundResource(R.drawable.style17);
                TextView textView16 = new TextView(this);
                textView16.setLayoutParams(layoutParams);
                String str17 = str16;
                str4 = str7;
                textView16.setText(jSONObject.getString(str17));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                str2 = str6;
                textView16.setPadding(20, 15, 20, 15);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView16.setBackgroundResource(R.drawable.style17);
                TextView textView17 = new TextView(this);
                textView17.setLayoutParams(layoutParams);
                textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView17.setGravity(17);
                str5 = str10;
                textView17.setTypeface((Typeface) null, 1);
                textView17.setPadding(20, 15, 20, 15);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView17.setBackgroundResource(R.drawable.style17);
                TextView textView18 = new TextView(this);
                textView18.setLayoutParams(layoutParams);
                textView18.setText(jSONObject.getString("CREATED_ON"));
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                str = str17;
                textView18.setPadding(20, 15, 20, 15);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView18.setBackgroundResource(R.drawable.style17);
                TextView textView19 = new TextView(this);
                textView19.setLayoutParams(layoutParams);
                textView19.setText(jSONObject.getString("LAST_LOGIN"));
                textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView19.setGravity(17);
                textView19.setPadding(20, 15, 20, 15);
                textView19.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView19.setBackgroundResource(R.drawable.style17);
                if (jSONObject.getBoolean("ACCOUNT_STATUS")) {
                    textView17.setText("APPROVED");
                } else {
                    textView17.setText("PENDING");
                    textView11.setTextColor(getResources().getColor(R.color.colorRed));
                    textView12.setTextColor(getResources().getColor(R.color.colorRed));
                    textView13.setTextColor(getResources().getColor(R.color.colorRed));
                    textView14.setTextColor(getResources().getColor(R.color.colorRed));
                    textView15.setTextColor(getResources().getColor(R.color.colorRed));
                    textView16.setTextColor(getResources().getColor(R.color.colorRed));
                    textView17.setTextColor(getResources().getColor(R.color.colorRed));
                    textView18.setTextColor(getResources().getColor(R.color.colorRed));
                    textView19.setTextColor(getResources().getColor(R.color.colorRed));
                }
                TableRow tableRow6 = tableRow5;
                tableRow6.addView(textView11);
                tableRow6.addView(textView12);
                tableRow6.addView(textView13);
                tableRow6.addView(textView16);
                tableRow6.addView(textView14);
                tableRow6.addView(textView15);
                tableRow6.addView(textView17);
                tableRow6.addView(textView19);
                tableRow6.addView(textView18);
                this.tbl_user.addView(tableRow6);
                this.tbl_user_header.addView(tableRow4);
                final String trim = textView14.getText().toString().trim();
                final String trim2 = textView12.getText().toString().trim();
                imageView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (Manage_User_Activity.this.Pref_Username.equals(trim2)) {
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Set Privileges To Yourself");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                            if (trim.equals("USER")) {
                                Intent intent = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                intent.putExtra("username", trim2);
                                intent.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                            if (trim.equals("USER") || trim.equals("NODAL")) {
                                Intent intent2 = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                intent2.putExtra("username", trim2);
                                intent2.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent2.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent2);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (!Manage_User_Activity.this.Pref_Role.equals("SUPER")) {
                        } else {
                            if (trim.equals("USER") || trim.equals("NODAL") || trim.equals("ADMINISTRATOR")) {
                                Intent intent3 = new Intent(Manage_User_Activity.this, Manage_User_Privilege_Activity.class);
                                intent3.putExtra("username", trim2);
                                intent3.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent3.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent3);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Edit Privilege Of " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        }
                    }
                });
                imageView3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (Manage_User_Activity.this.Pref_Username.equals(trim2)) {
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete Your Account By Yourself");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (Manage_User_Activity.this.Pref_Role.equals("NODAL")) {
                            if (trim.equals("USER")) {
                                Intent intent = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                intent.putExtra("username", trim2);
                                intent.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (Manage_User_Activity.this.Pref_Role.equals("ADMINISTRATOR")) {
                            if (trim.equals("USER") || trim.equals("NODAL")) {
                                Intent intent2 = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                intent2.putExtra("username", trim2);
                                intent2.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent2.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent2);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        } else if (!Manage_User_Activity.this.Pref_Role.equals("SUPER")) {
                        } else {
                            if (trim.equals("USER") || trim.equals("NODAL") || trim.equals("ADMINISTRATOR")) {
                                Intent intent3 = new Intent(Manage_User_Activity.this, Manage_User_Delete_Activity.class);
                                intent3.putExtra("username", trim2);
                                intent3.putExtra("user_circle", Manage_User_Activity.this.Circle_name);
                                intent3.putExtra("user_ssa", Manage_User_Activity.this.Ssa_name);
                                Manage_User_Activity.this.startActivity(intent3);
                                return;
                            }
                            Manage_User_Activity.this.txt_alert.setText("You Are Not Authorized To Delete A " + trim + " User");
                            Manage_User_Activity.this.error_dialog.show();
                        }
                    }
                });
                i2 = i5;
                i3 = i + 1;
                str10 = str5;
                str12 = str4;
                str14 = str3;
                str11 = str2;
                str13 = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Setting_New_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
