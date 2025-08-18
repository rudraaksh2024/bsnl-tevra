package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.stats.CodePackage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlErrorCodes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Report_Traffic_Tip_Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    public static final int CAMERA_REQUEST_CODE = 200;
    public static final int STORAGE_PERMISSION_REQ = 101;
    public static final int STORAGE_REQUEST_CODE = 201;
    /* access modifiers changed from: private */
    public String Circle_name;
    private String Grand_Bsnl;
    private String Grand_Fra;
    private String Grand_Total;
    public int NUM_ITEMS_PAGE = 100;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
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
    private TextView btn_submit;
    private TextView[] btns;
    private String ccode;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public int countt = 0;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    private ImageView imageView;
    private ImageView img_cal;
    private ImageView img_download;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay1;
    private LinearLayout lay2;
    private LinearLayout lay_btn;
    private LinearLayout lay_result;
    private ListView listview;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private String scode;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    private TableLayout tbl_detail;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_date;
    private TextView txt_header;
    private TextView txt_header1;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_traffic_tip_activity);
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
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.img_download = (ImageView) findViewById(R.id.img_download);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.img_cal = (ImageView) findViewById(R.id.img_cal);
        this.txt_date = (TextView) findViewById(R.id.txt_date);
        this.lay_result = (LinearLayout) findViewById(R.id.lay_result);
        this.tbl_detail = (TableLayout) findViewById(R.id.tbl_detail);
        this.lay2.setVisibility(8);
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Report_Traffic_Tip_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                ArrayAdapter unused = report_Traffic_Tip_Activity.adapter_circle = new ArrayAdapter(report_Traffic_Tip_Activity2, R.layout.spinner_item, report_Traffic_Tip_Activity2.circle_element);
                Report_Traffic_Tip_Activity.this.sp_circle.setAdapter(Report_Traffic_Tip_Activity.this.adapter_circle);
                Report_Traffic_Tip_Activity.this.sp_circle.setSelection(Report_Traffic_Tip_Activity.this.adapter_circle.getPosition(Report_Traffic_Tip_Activity.this.Pref_Circle));
                if (Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("SSA") || Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("BA") || Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Traffic_Tip_Activity.this.sp_circle.setEnabled(false);
                    Report_Traffic_Tip_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                Report_Traffic_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Activity.this.getApplicationContext()));
                Report_Traffic_Tip_Activity.this.error_dialog.show();
                Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                ArrayAdapter unused = report_Traffic_Tip_Activity.adapter_circle = new ArrayAdapter(report_Traffic_Tip_Activity2, R.layout.spinner_item, report_Traffic_Tip_Activity2.circle_element);
                Report_Traffic_Tip_Activity.this.sp_circle.setAdapter(Report_Traffic_Tip_Activity.this.adapter_circle);
                if (Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("SSA") || Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("BA") || Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Traffic_Tip_Activity.this.sp_circle.setEnabled(false);
                    Report_Traffic_Tip_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Report_Traffic_Tip_Activity.this.Pref_Circle);
                hashMap.put("access", Report_Traffic_Tip_Activity.this.Pref_Access_Level);
                hashMap.put("username", Report_Traffic_Tip_Activity.this.Pref_Username);
                hashMap.put("random_key", Report_Traffic_Tip_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Report_Traffic_Tip_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Report_Traffic_Tip_Activity.this.ssa_element = new ArrayList();
                Report_Traffic_Tip_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Report_Traffic_Tip_Activity.this.sp_circle.getSelectedItem().toString();
                Report_Traffic_Tip_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Report_Traffic_Tip_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Report_Traffic_Tip_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Report_Traffic_Tip_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter unused = Report_Traffic_Tip_Activity.this.adapter_ssa = new ArrayAdapter(Report_Traffic_Tip_Activity.this, R.layout.spinner_item, Report_Traffic_Tip_Activity.this.ssa_element);
                        Report_Traffic_Tip_Activity.this.sp_ssa.setAdapter(Report_Traffic_Tip_Activity.this.adapter_ssa);
                        if (obj.equals(Report_Traffic_Tip_Activity.this.Pref_Circle)) {
                            Report_Traffic_Tip_Activity.this.sp_ssa.setSelection(Report_Traffic_Tip_Activity.this.adapter_ssa.getPosition(Report_Traffic_Tip_Activity.this.Pref_SSA));
                        }
                        if (Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Traffic_Tip_Activity.this.sp_ssa.setEnabled(false);
                            Report_Traffic_Tip_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Activity.this.getApplicationContext());
                        Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                        Report_Traffic_Tip_Activity.this.txt_alert.setText(onErrorResponse);
                        Report_Traffic_Tip_Activity.this.error_dialog.show();
                        ArrayAdapter unused = Report_Traffic_Tip_Activity.this.adapter_ssa = new ArrayAdapter(Report_Traffic_Tip_Activity.this, R.layout.spinner_item, Report_Traffic_Tip_Activity.this.ssa_element);
                        Report_Traffic_Tip_Activity.this.sp_ssa.setAdapter(Report_Traffic_Tip_Activity.this.adapter_ssa);
                        if (Report_Traffic_Tip_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Traffic_Tip_Activity.this.sp_ssa.setEnabled(false);
                            Report_Traffic_Tip_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Report_Traffic_Tip_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Report_Traffic_Tip_Activity.this.Pref_SSA);
                        hashMap.put("username", Report_Traffic_Tip_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Traffic_Tip_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Traffic_Tip_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.btn_submit.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
        this.et_search.addTextChangedListener(this);
        this.img_cal.setOnClickListener(this);
        this.txt_date.setOnClickListener(this);
        this.img_download.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.img_download) {
            save_file();
        } else if (view.getId() == R.id.img_cal || view.getId() == R.id.txt_date) {
            Calendar instance = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    Report_Traffic_Tip_Activity.this.txt_date.setText(String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2 + 1), Integer.valueOf(i3)}));
                }
            }, instance.get(1), instance.get(2), instance.get(5));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - DateUtil.DAY_MILLISECONDS);
            instance.add(5, -30);
            datePickerDialog.getDatePicker().setMinDate(instance.getTimeInMillis());
            datePickerDialog.show();
        } else if (view.getId() == R.id.btn_submit) {
            this.countt = 0;
            this.Circle_name = this.sp_circle.getSelectedItem().toString().trim();
            this.Ssa_name = this.sp_ssa.getSelectedItem().toString().trim();
            final String trim = this.txt_date.getText().toString().trim();
            if (this.Circle_name.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT  A VALID CIRCLE NAME", getApplicationContext());
            } else if (this.Ssa_name.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID SSA NAME", getApplicationContext());
            } else if (trim.equals("-- SELECT DATE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID DATE", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.lay2.setVisibility(8);
                this.et_search.getText().clear();
                this.tbl_detail.removeAllViews();
                this.txt_header.setText("CIRCLE : " + this.Circle_name + " | SSA : " + this.Ssa_name + "\nDATE : " + trim);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass8 r3 = new StringRequest(1, getString(R.string.serverip) + "report_traffic_tip.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray unused = Report_Traffic_Tip_Activity.this.Row_Array = new JSONArray(str);
                            Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                            report_Traffic_Tip_Activity.TOTAL_LIST_ITEMS = report_Traffic_Tip_Activity.Row_Array.length();
                            if (Report_Traffic_Tip_Activity.this.Row_Array.length() > 0) {
                                Report_Traffic_Tip_Activity.this.Btnfooter();
                                Report_Traffic_Tip_Activity.this.loadList(0);
                                Report_Traffic_Tip_Activity.this.CheckBtnBackGroud(0);
                                return;
                            }
                            Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                            Report_Traffic_Tip_Activity.this.txt_alert.setText("Sorry No Data Available!!\nPlease Try For Another Date");
                            Report_Traffic_Tip_Activity.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Report_Traffic_Tip_Activity.this.progress_dialog.cancel();
                        Report_Traffic_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Traffic_Tip_Activity.this.getApplicationContext()));
                        Report_Traffic_Tip_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", Report_Traffic_Tip_Activity.this.Circle_name);
                        hashMap.put("ssa", Report_Traffic_Tip_Activity.this.Ssa_name);
                        hashMap.put(XmlErrorCodes.DATE, trim);
                        hashMap.put("username", Report_Traffic_Tip_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Traffic_Tip_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Traffic_Tip_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r3.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r3);
            }
        }
    }

    private void Populate_table(JSONArray jSONArray, int i) {
        this.tbl_detail.removeAllViews();
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
        TextView textView = new TextView(this);
        textView.setText("SR");
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setPadding(20, 15, 20, 15);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView.setBackgroundResource(R.drawable.new_style1);
        TextView textView2 = new TextView(this);
        textView2.setText("FRANCHISEE");
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setPadding(20, 15, 20, 15);
        textView2.setTypeface((Typeface) null, 1);
        textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView2.setBackgroundResource(R.drawable.new_style1);
        TextView textView3 = new TextView(this);
        textView3.setText(CodePackage.LOCATION);
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        textView3.setPadding(20, 15, 20, 15);
        textView3.setTypeface((Typeface) null, 1);
        textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView3.setBackgroundResource(R.drawable.new_style1);
        TextView textView4 = new TextView(this);
        textView4.setText("OLT_IP");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(this);
        String str = "INTERFACE";
        textView5.setText(str);
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(this);
        String str2 = "MAX_INTRAFFIC";
        textView6.setText(str2);
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(this);
        textView7.setText("MAX_INTRAFFIC_TIME");
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(this);
        textView8.setText("MAX_OUTTRAFFIC");
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        String str3 = "MAX_OUTTRAFFIC";
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(this);
        textView9.setText("MAX_OUTTRAFFIC_TIME");
        String str4 = "MAX_OUTTRAFFIC_TIME";
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        String str5 = "MAX_INTRAFFIC_TIME";
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        tableRow.addView(textView);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView5);
        tableRow.addView(textView6);
        tableRow.addView(textView7);
        tableRow.addView(textView8);
        tableRow.addView(textView9);
        this.tbl_detail.addView(tableRow);
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                this.ccode = jSONObject.getString("CCODE").trim();
                this.scode = jSONObject.getString("SCODE").trim();
                TableRow tableRow2 = new TableRow(this);
                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                TextView textView10 = new TextView(this);
                textView10.setText(Integer.toString(i + i2 + 1));
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setPadding(20, 15, 20, 15);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView10.setBackgroundResource(R.drawable.style17);
                TextView textView11 = new TextView(this);
                textView11.setText(jSONObject.getString("FMS_FIRM_NAME"));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(this);
                textView12.setText(jSONObject.getString(CodePackage.LOCATION));
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                TextView textView13 = new TextView(this);
                textView13.setText(jSONObject.getString("IP"));
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setPadding(20, 15, 20, 15);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView13.setBackgroundResource(R.drawable.style17);
                TextView textView14 = new TextView(this);
                textView14.setText(jSONObject.getString(str).toUpperCase());
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                textView14.setPadding(20, 15, 20, 15);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView14.setBackgroundResource(R.drawable.style17);
                TextView textView15 = new TextView(this);
                textView15.setText(jSONObject.getString(str2));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                textView15.setPadding(20, 15, 20, 15);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView15.setBackgroundResource(R.drawable.style17);
                TextView textView16 = new TextView(this);
                String str6 = str5;
                textView16.setText(jSONObject.getString(str6));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                String str7 = str2;
                textView16.setPadding(20, 15, 20, 15);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView16.setBackgroundResource(R.drawable.style17);
                TextView textView17 = new TextView(this);
                String str8 = str3;
                String str9 = str;
                textView17.setText(jSONObject.getString(str8));
                textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView17.setGravity(17);
                String str10 = str8;
                textView17.setPadding(20, 15, 20, 15);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView17.setBackgroundResource(R.drawable.style17);
                TextView textView18 = new TextView(this);
                String str11 = str4;
                textView18.setText(jSONObject.getString(str11));
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                str4 = str11;
                textView18.setPadding(20, 15, 20, 15);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView18.setBackgroundResource(R.drawable.style17);
                tableRow2.addView(textView10);
                tableRow2.addView(textView11);
                tableRow2.addView(textView12);
                tableRow2.addView(textView13);
                tableRow2.addView(textView14);
                tableRow2.addView(textView15);
                tableRow2.addView(textView16);
                tableRow2.addView(textView17);
                tableRow2.addView(textView18);
                this.tbl_detail.addView(tableRow2);
                i2++;
                str = str9;
                str2 = str7;
                str3 = str10;
                str5 = str6;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        this.lay2.setVisibility(0);
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
                    if (Report_Traffic_Tip_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Report_Traffic_Tip_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity.loadList(report_Traffic_Tip_Activity.countt + 1);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity2.CheckBtnBackGroud(report_Traffic_Tip_Activity2.countt + 1);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity3 = Report_Traffic_Tip_Activity.this;
                    int unused = report_Traffic_Tip_Activity3.countt = report_Traffic_Tip_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Activity.this.countt == 0) {
                        Toast.makeText(Report_Traffic_Tip_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity.loadList(report_Traffic_Tip_Activity.countt - 1);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity2.CheckBtnBackGroud(report_Traffic_Tip_Activity2.countt - 1);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity3 = Report_Traffic_Tip_Activity.this;
                    int unused = report_Traffic_Tip_Activity3.countt = report_Traffic_Tip_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Activity.this.loadList(i4);
                    Report_Traffic_Tip_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Report_Traffic_Tip_Activity.this.countt = i4;
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
                    if (Report_Traffic_Tip_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Report_Traffic_Tip_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity.loadList1(report_Traffic_Tip_Activity.search_count + 1, jSONArray);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity2.CheckBtnBackGroud1(report_Traffic_Tip_Activity2.search_count + 1, jSONArray);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity3 = Report_Traffic_Tip_Activity.this;
                    int unused = report_Traffic_Tip_Activity3.search_count = report_Traffic_Tip_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Traffic_Tip_Activity.this.search_count == 0) {
                        Toast.makeText(Report_Traffic_Tip_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity.loadList1(report_Traffic_Tip_Activity.search_count - 1, jSONArray);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity2 = Report_Traffic_Tip_Activity.this;
                    report_Traffic_Tip_Activity2.CheckBtnBackGroud1(report_Traffic_Tip_Activity2.search_count - 1, jSONArray);
                    Report_Traffic_Tip_Activity report_Traffic_Tip_Activity3 = Report_Traffic_Tip_Activity.this;
                    int unused = report_Traffic_Tip_Activity3.search_count = report_Traffic_Tip_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Activity.this.loadList1(i, jSONArray);
                    Report_Traffic_Tip_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Report_Traffic_Tip_Activity.this.search_count = i;
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
        this.searched_array = new JSONArray();
        this.search_count = 0;
        this.tbl_detail.removeAllViews();
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                this.ccode = jSONObject.getString("CCODE").trim();
                this.scode = jSONObject.getString("SCODE").trim();
                if (jSONObject.getString("FMS_FIRM_NAME").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase())) {
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

    private void save_file() {
        this.progress_dialog.show();
        String format = new SimpleDateFormat("dd MMM yy").format(new Date());
        getIntent().getStringExtra("IP");
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Olt_Peak_Traffic(" + format + ").xlsx");
        if (file.exists()) {
            file.delete();
        }
        XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
        XSSFSheet createSheet = xSSFWorkbook.createSheet("Olt Peak Traffic");
        XSSFCellStyle createCellStyle = xSSFWorkbook.createCellStyle();
        createCellStyle.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
        createCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        createCellStyle.setAlignment(HorizontalAlignment.CENTER);
        createCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        createCellStyle.setBorderTop(BorderStyle.THIN);
        createCellStyle.setBorderBottom(BorderStyle.THIN);
        createCellStyle.setBorderLeft(BorderStyle.THIN);
        createCellStyle.setBorderRight(BorderStyle.THIN);
        createCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
        XSSFFont createFont = xSSFWorkbook.createFont();
        createFont.setColor(IndexedColors.WHITE.getIndex());
        createFont.setBold(true);
        createFont.setFontName(HSSFFont.FONT_ARIAL);
        createCellStyle.setFont(createFont);
        XSSFCellStyle createCellStyle2 = xSSFWorkbook.createCellStyle();
        createCellStyle2.setAlignment(HorizontalAlignment.CENTER);
        createCellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        createCellStyle2.setBorderTop(BorderStyle.THIN);
        createCellStyle2.setBorderBottom(BorderStyle.THIN);
        createCellStyle2.setBorderLeft(BorderStyle.THIN);
        createCellStyle2.setBorderRight(BorderStyle.THIN);
        XSSFFont createFont2 = xSSFWorkbook.createFont();
        createFont2.setColor(IndexedColors.BLACK.getIndex());
        createFont2.setFontName(HSSFFont.FONT_ARIAL);
        createCellStyle2.setFont(createFont2);
        XSSFRow createRow = createSheet.createRow(0);
        createRow.setHeight((short) ((int) (((double) createRow.getHeight()) * 1.5d)));
        XSSFCell createCell = createRow.createCell(0);
        XSSFCell createCell2 = createRow.createCell(1);
        XSSFCell createCell3 = createRow.createCell(2);
        XSSFCell createCell4 = createRow.createCell(3);
        XSSFCell createCell5 = createRow.createCell(4);
        XSSFCell createCell6 = createRow.createCell(5);
        XSSFCell createCell7 = createRow.createCell(6);
        XSSFCell createCell8 = createRow.createCell(7);
        XSSFWorkbook xSSFWorkbook2 = xSSFWorkbook;
        XSSFCell createCell9 = createRow.createCell(8);
        File file2 = file;
        XSSFCell createCell10 = createRow.createCell(9);
        XSSFCell createCell11 = createRow.createCell(10);
        createCell.setCellValue("SR");
        createCell2.setCellValue("CIRCLE");
        createCell3.setCellValue("SSA");
        createCell4.setCellValue("FRANCHISEE");
        createCell5.setCellValue(CodePackage.LOCATION);
        XSSFCellStyle xSSFCellStyle = createCellStyle2;
        createCell6.setCellValue("VLAN");
        String str = "VLAN";
        createCell7.setCellValue("INTERFACE");
        String str2 = "INTERFACE";
        createCell8.setCellValue("MAX-INTRAFFIC");
        createCell9.setCellValue("MAX-INTRAFFIC TIME");
        createCell10.setCellValue("MAX-OUTTRAFFIC");
        createCell11.setCellValue("MAX-OUTTRAFFIC TIME");
        String str3 = CodePackage.LOCATION;
        createSheet.setColumnWidth(0, 2048);
        createSheet.setColumnWidth(1, 2048);
        createSheet.setColumnWidth(2, 2048);
        createSheet.setColumnWidth(3, TarConstants.DEFAULT_BLKSIZE);
        createSheet.setColumnWidth(4, 5632);
        createSheet.setColumnWidth(5, 2048);
        createSheet.setColumnWidth(6, 5120);
        createSheet.setColumnWidth(7, 3840);
        createSheet.setColumnWidth(8, 6400);
        createSheet.setColumnWidth(9, 3840);
        createSheet.setColumnWidth(10, 6400);
        createCell.setCellStyle(createCellStyle);
        createCell2.setCellStyle(createCellStyle);
        createCell3.setCellStyle(createCellStyle);
        createCell4.setCellStyle(createCellStyle);
        createCell5.setCellStyle(createCellStyle);
        createCell6.setCellStyle(createCellStyle);
        createCell7.setCellStyle(createCellStyle);
        createCell8.setCellStyle(createCellStyle);
        createCell9.setCellStyle(createCellStyle);
        createCell10.setCellStyle(createCellStyle);
        createCell11.setCellStyle(createCellStyle);
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                int i2 = i + 1;
                XSSFRow createRow2 = createSheet.createRow(i2);
                XSSFCell createCell12 = createRow2.createCell(0);
                XSSFCell createCell13 = createRow2.createCell(1);
                XSSFCell createCell14 = createRow2.createCell(2);
                XSSFCell createCell15 = createRow2.createCell(3);
                XSSFCell createCell16 = createRow2.createCell(4);
                XSSFCell createCell17 = createRow2.createCell(5);
                XSSFCell createCell18 = createRow2.createCell(6);
                XSSFCell createCell19 = createRow2.createCell(7);
                XSSFCell createCell20 = createRow2.createCell(8);
                XSSFCell createCell21 = createRow2.createCell(9);
                XSSFCell createCell22 = createRow2.createCell(10);
                createCell12.setCellValue(Integer.toString(i2));
                createCell13.setCellValue(Html.fromHtml(jSONObject.getString("CCODE")).toString());
                createCell14.setCellValue(Html.fromHtml(jSONObject.getString("SCODE")).toString());
                createCell15.setCellValue(Html.fromHtml(jSONObject.getString("FMS_FIRM_NAME")).toString());
                String str4 = str3;
                int i3 = i2;
                createCell16.setCellValue(Html.fromHtml(jSONObject.getString(str4)).toString());
                String str5 = str;
                String str6 = str5;
                createCell17.setCellValue(Html.fromHtml(jSONObject.getString(str5)).toString());
                String str7 = str2;
                str2 = str7;
                createCell18.setCellValue(Html.fromHtml(jSONObject.getString(str7)).toString());
                createCell19.setCellValue(Html.fromHtml(jSONObject.getString("MAX_INTRAFFIC")).toString());
                createCell20.setCellValue(Html.fromHtml(jSONObject.getString("MAX_INTRAFFIC_TIME")).toString());
                createCell21.setCellValue(Html.fromHtml(jSONObject.getString("MAX_OUTTRAFFIC")).toString());
                createCell22.setCellValue(Html.fromHtml(jSONObject.getString("MAX_OUTTRAFFIC_TIME")).toString());
                XSSFCellStyle xSSFCellStyle2 = xSSFCellStyle;
                createCell12.setCellStyle(xSSFCellStyle2);
                createCell13.setCellStyle(xSSFCellStyle2);
                createCell14.setCellStyle(xSSFCellStyle2);
                createCell15.setCellStyle(xSSFCellStyle2);
                createCell16.setCellStyle(xSSFCellStyle2);
                createCell17.setCellStyle(xSSFCellStyle2);
                createCell18.setCellStyle(xSSFCellStyle2);
                createCell19.setCellStyle(xSSFCellStyle2);
                createCell20.setCellStyle(xSSFCellStyle2);
                createCell21.setCellStyle(xSSFCellStyle2);
                createCell22.setCellStyle(xSSFCellStyle2);
                xSSFCellStyle = xSSFCellStyle2;
                str = str6;
                i = i3;
                str3 = str4;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        createSheet.setColumnWidth(0, 2048);
        createSheet.setColumnWidth(1, 2048);
        createSheet.setColumnWidth(2, 2048);
        createSheet.setColumnWidth(3, TarConstants.DEFAULT_BLKSIZE);
        createSheet.setColumnWidth(4, 5632);
        createSheet.setColumnWidth(5, 2048);
        createSheet.setColumnWidth(6, 5120);
        createSheet.setColumnWidth(7, 3840);
        createSheet.setColumnWidth(8, 6400);
        createSheet.setColumnWidth(9, 3840);
        createSheet.setColumnWidth(10, 6400);
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            final File file3 = file2;
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            xSSFWorkbook2.write(fileOutputStream);
            this.progress_dialog.cancel();
            fileOutputStream.flush();
            fileOutputStream.close();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("OPEN");
            builder.setCancelable(true);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("The File Is Downloaded and Saved at <br><b>" + file3 + "</b><br><br>Do You Want To Open The File "));
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Traffic_Tip_Activity.this.info_dialog.cancel();
                    Uri uriForFile = FileProvider.getUriForFile(Report_Traffic_Tip_Activity.this.getApplicationContext(), Report_Traffic_Tip_Activity.this.getApplicationContext().getPackageName() + ".provider", file3);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(uriForFile, "application/vnd.ms-excel");
                    intent.setFlags(1);
                    Report_Traffic_Tip_Activity.this.startActivity(intent);
                }
            });
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 201 && i2 == -1) {
            save_file();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            Toast.makeText(getApplicationContext(), "access denied", 1).show();
        } else {
            save_file();
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
