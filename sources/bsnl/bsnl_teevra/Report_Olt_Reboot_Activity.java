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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Report_Olt_Reboot_Activity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    public static final int CAMERA_REQUEST_CODE = 200;
    public static final int STORAGE_PERMISSION_REQ = 101;
    public static final int STORAGE_REQUEST_CODE = 201;
    public int NUM_ITEMS_PAGE = 50;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    private String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Fms_role;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private CustomBaseAdapter5 adapter;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView btn_submit;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public int countt = 0;
    private CustomBaseAdapter5 customBaseAdapter;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    private ImageView imageView;
    private ImageView img_cal1;
    private ImageView img_cal2;
    /* access modifiers changed from: private */
    public ImageView img_download;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    private LinearLayout lay_btn;
    private ListView listview;
    private AlertDialog logout_dialog;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    private TextView title;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_end_date;
    private TextView txt_header;
    private TextView txt_header1;
    private TextView txt_header2;
    /* access modifiers changed from: private */
    public TextView txt_start_date;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_olt_reboot_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        SharedPreferences sharedPreferences3 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences3;
        this.Pref_Fms_Username = sharedPreferences3.getString("fms_username_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Fms_UserId = this.sharedPreferences.getString("fms_userid_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Zone = this.sharedPreferences.getString("fms_user_zone_Key", (String) null);
        this.Pref_Fms_TeamId = this.sharedPreferences.getString("fms_teamid_key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
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
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.txt_start_date = (TextView) findViewById(R.id.txt_start_date);
        this.img_cal1 = (ImageView) findViewById(R.id.img_cal1);
        this.txt_end_date = (TextView) findViewById(R.id.txt_end_date);
        this.img_cal2 = (ImageView) findViewById(R.id.img_cal2);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay2);
        this.lay2 = linearLayout;
        linearLayout.setVisibility(8);
        this.txt_header1 = (TextView) findViewById(R.id.txt_header1);
        this.txt_header2 = (TextView) findViewById(R.id.txt_header2);
        ImageView imageView2 = (ImageView) findViewById(R.id.img_download);
        this.img_download = imageView2;
        imageView2.setEnabled(false);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.listview = (ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Report_Olt_Reboot_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                ArrayAdapter unused = report_Olt_Reboot_Activity.adapter_circle = new ArrayAdapter(report_Olt_Reboot_Activity2, R.layout.spinner_item, report_Olt_Reboot_Activity2.circle_element);
                Report_Olt_Reboot_Activity.this.sp_circle.setAdapter(Report_Olt_Reboot_Activity.this.adapter_circle);
                Report_Olt_Reboot_Activity.this.sp_circle.setSelection(Report_Olt_Reboot_Activity.this.adapter_circle.getPosition(Report_Olt_Reboot_Activity.this.Pref_Circle));
                if (Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("SSA") || Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("BA") || Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Olt_Reboot_Activity.this.sp_circle.setEnabled(false);
                    Report_Olt_Reboot_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                Report_Olt_Reboot_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Olt_Reboot_Activity.this.getApplicationContext()));
                Report_Olt_Reboot_Activity.this.error_dialog.show();
                Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                ArrayAdapter unused = report_Olt_Reboot_Activity.adapter_circle = new ArrayAdapter(report_Olt_Reboot_Activity2, R.layout.spinner_item, report_Olt_Reboot_Activity2.circle_element);
                Report_Olt_Reboot_Activity.this.sp_circle.setAdapter(Report_Olt_Reboot_Activity.this.adapter_circle);
                if (Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("SSA") || Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("BA") || Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("CIRCLE")) {
                    Report_Olt_Reboot_Activity.this.sp_circle.setEnabled(false);
                    Report_Olt_Reboot_Activity.this.sp_circle.setAlpha(0.5f);
                }
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", Report_Olt_Reboot_Activity.this.Pref_Circle);
                hashMap.put("access", Report_Olt_Reboot_Activity.this.Pref_Access_Level);
                hashMap.put("username", Report_Olt_Reboot_Activity.this.Pref_Username);
                hashMap.put("random_key", Report_Olt_Reboot_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Report_Olt_Reboot_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = Report_Olt_Reboot_Activity.this.ssa_element = new ArrayList();
                Report_Olt_Reboot_Activity.this.ssa_element.add("-- SSA --");
                final String obj = Report_Olt_Reboot_Activity.this.sp_circle.getSelectedItem().toString();
                Report_Olt_Reboot_Activity.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(Report_Olt_Reboot_Activity.this);
                AnonymousClass3 r0 = new StringRequest(1, Report_Olt_Reboot_Activity.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                Report_Olt_Reboot_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter unused = Report_Olt_Reboot_Activity.this.adapter_ssa = new ArrayAdapter(Report_Olt_Reboot_Activity.this, R.layout.spinner_item, Report_Olt_Reboot_Activity.this.ssa_element);
                        Report_Olt_Reboot_Activity.this.sp_ssa.setAdapter(Report_Olt_Reboot_Activity.this.adapter_ssa);
                        if (obj.equals(Report_Olt_Reboot_Activity.this.Pref_Circle)) {
                            Report_Olt_Reboot_Activity.this.sp_ssa.setSelection(Report_Olt_Reboot_Activity.this.adapter_ssa.getPosition(Report_Olt_Reboot_Activity.this.Pref_SSA));
                        }
                        if (Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Olt_Reboot_Activity.this.sp_ssa.setEnabled(false);
                            Report_Olt_Reboot_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Report_Olt_Reboot_Activity.this.getApplicationContext());
                        Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                        Report_Olt_Reboot_Activity.this.txt_alert.setText(onErrorResponse);
                        Report_Olt_Reboot_Activity.this.error_dialog.show();
                        ArrayAdapter unused = Report_Olt_Reboot_Activity.this.adapter_ssa = new ArrayAdapter(Report_Olt_Reboot_Activity.this, R.layout.spinner_item, Report_Olt_Reboot_Activity.this.ssa_element);
                        Report_Olt_Reboot_Activity.this.sp_ssa.setAdapter(Report_Olt_Reboot_Activity.this.adapter_ssa);
                        if (Report_Olt_Reboot_Activity.this.Pref_Access_Level.equals("SSA")) {
                            Report_Olt_Reboot_Activity.this.sp_ssa.setEnabled(false);
                            Report_Olt_Reboot_Activity.this.sp_ssa.setAlpha(0.5f);
                        }
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", Report_Olt_Reboot_Activity.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", Report_Olt_Reboot_Activity.this.Pref_SSA);
                        hashMap.put("username", Report_Olt_Reboot_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Olt_Reboot_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Olt_Reboot_Activity.this.androidId);
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
        this.img_cal1.setOnClickListener(this);
        this.img_cal2.setOnClickListener(this);
        this.txt_start_date.setOnClickListener(this);
        this.txt_end_date.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.img_cal1 || view.getId() == R.id.txt_start_date) {
            Calendar instance = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    Report_Olt_Reboot_Activity.this.txt_start_date.setText(String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2 + 1), Integer.valueOf(i3)}));
                }
            }, instance.get(1), instance.get(2), instance.get(5));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            instance.add(5, -30);
            datePickerDialog.getDatePicker().setMinDate(instance.getTimeInMillis());
            datePickerDialog.show();
        } else if (view.getId() == R.id.img_cal2 || view.getId() == R.id.txt_end_date) {
            Calendar instance2 = Calendar.getInstance();
            DatePickerDialog datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    Report_Olt_Reboot_Activity.this.txt_end_date.setText(String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2 + 1), Integer.valueOf(i3)}));
                }
            }, instance2.get(1), instance2.get(2), instance2.get(5));
            datePickerDialog2.getDatePicker().setMaxDate(System.currentTimeMillis());
            instance2.add(5, -30);
            datePickerDialog2.getDatePicker().setMinDate(instance2.getTimeInMillis());
            datePickerDialog2.show();
        } else if (view.getId() == R.id.btn_submit) {
            final String trim = this.sp_circle.getSelectedItem().toString().trim();
            final String trim2 = this.sp_ssa.getSelectedItem().toString().trim();
            final String trim3 = this.txt_start_date.getText().toString().trim();
            final String trim4 = this.txt_end_date.getText().toString().trim();
            if (trim.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT  A VALID CIRCLE NAME", getApplicationContext());
            } else if (trim2.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT A VALID SSA NAME", getApplicationContext());
            } else if (trim3.equals("-- SELECT START DATE --")) {
                new AlertHelperclass().ntoast("Please Select A Valid Start Date", getApplicationContext());
            } else if (trim4.equals("-- SELECT END DATE --")) {
                new AlertHelperclass().ntoast("Please Select A Valid End Date", getApplicationContext());
            } else {
                this.lay2.setVisibility(8);
                this.img_download.setEnabled(false);
                this.progress_dialog.show();
                this.listview.setAdapter((ListAdapter) null);
                this.txt_header1.setText("CIRCLE : " + trim + " | SSA : " + trim2);
                this.txt_header2.setText("(START DATE : " + trim3 + " | END DATE : " + trim4 + ")");
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass9 r0 = new StringRequest(1, getString(R.string.serverip) + "report/olt_reboot_all.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.getBoolean("SUCCESS")) {
                                JSONArray unused = Report_Olt_Reboot_Activity.this.Row_Array = jSONObject.getJSONArray("ROWSET");
                                Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                                report_Olt_Reboot_Activity.TOTAL_LIST_ITEMS = report_Olt_Reboot_Activity.Row_Array.length();
                                Report_Olt_Reboot_Activity.this.Btnfooter();
                                Report_Olt_Reboot_Activity.this.loadList(0);
                                Report_Olt_Reboot_Activity.this.CheckBtnBackGroud(0);
                                Report_Olt_Reboot_Activity.this.img_download.setEnabled(true);
                                Report_Olt_Reboot_Activity.this.img_download.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Report_Olt_Reboot_Activity.this.check_for_permission();
                                    }
                                });
                                Report_Olt_Reboot_Activity.this.lay2.setVisibility(0);
                                return;
                            }
                            String string = jSONObject.getString("ERROR");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Report_Olt_Reboot_Activity.this);
                            View inflate = Report_Olt_Reboot_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setBackgroundResource(0);
                            textView.setText("OK");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused2 = Report_Olt_Reboot_Activity.this.info_dialog = builder.create();
                            Report_Olt_Reboot_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml(string));
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Report_Olt_Reboot_Activity.this.info_dialog.cancel();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Report_Olt_Reboot_Activity.this.progress_dialog.cancel();
                        Report_Olt_Reboot_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Report_Olt_Reboot_Activity.this.getApplicationContext()));
                        Report_Olt_Reboot_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", trim);
                        hashMap.put("ssa", trim2);
                        hashMap.put("start_date", trim3);
                        hashMap.put("end_date", trim4);
                        hashMap.put("username", Report_Olt_Reboot_Activity.this.Pref_Username);
                        hashMap.put("random_key", Report_Olt_Reboot_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Report_Olt_Reboot_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
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
            this.btns[i4].setBackgroundResource(R.drawable.button);
            this.lay_btn.addView(this.btns[i4], new LinearLayout.LayoutParams(-2, -1));
            this.next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Olt_Reboot_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Report_Olt_Reboot_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity.loadList(report_Olt_Reboot_Activity.countt + 1);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity2.CheckBtnBackGroud(report_Olt_Reboot_Activity2.countt + 1);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity3 = Report_Olt_Reboot_Activity.this;
                    int unused = report_Olt_Reboot_Activity3.countt = report_Olt_Reboot_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Olt_Reboot_Activity.this.countt == 0) {
                        Toast.makeText(Report_Olt_Reboot_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity.loadList(report_Olt_Reboot_Activity.countt - 1);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity2.CheckBtnBackGroud(report_Olt_Reboot_Activity2.countt - 1);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity3 = Report_Olt_Reboot_Activity.this;
                    int unused = report_Olt_Reboot_Activity3.countt = report_Olt_Reboot_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Olt_Reboot_Activity.this.loadList(i4);
                    Report_Olt_Reboot_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Report_Olt_Reboot_Activity.this.countt = i4;
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
        CustomBaseAdapter5 customBaseAdapter5 = new CustomBaseAdapter5(getApplicationContext(), jSONArray, i2, this);
        this.adapter = customBaseAdapter5;
        this.listview.setAdapter(customBaseAdapter5);
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
            this.btns[i].setBackgroundResource(R.drawable.button);
            this.lay_btn.addView(this.btns[i], new LinearLayout.LayoutParams(-2, -1));
            this.next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Olt_Reboot_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Report_Olt_Reboot_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity.loadList1(report_Olt_Reboot_Activity.search_count + 1, jSONArray);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity2.CheckBtnBackGroud1(report_Olt_Reboot_Activity2.search_count + 1, jSONArray);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity3 = Report_Olt_Reboot_Activity.this;
                    int unused = report_Olt_Reboot_Activity3.search_count = report_Olt_Reboot_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Report_Olt_Reboot_Activity.this.search_count == 0) {
                        Toast.makeText(Report_Olt_Reboot_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity.loadList1(report_Olt_Reboot_Activity.search_count - 1, jSONArray);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity2 = Report_Olt_Reboot_Activity.this;
                    report_Olt_Reboot_Activity2.CheckBtnBackGroud1(report_Olt_Reboot_Activity2.search_count - 1, jSONArray);
                    Report_Olt_Reboot_Activity report_Olt_Reboot_Activity3 = Report_Olt_Reboot_Activity.this;
                    int unused = report_Olt_Reboot_Activity3.search_count = report_Olt_Reboot_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Olt_Reboot_Activity.this.loadList1(i, jSONArray);
                    Report_Olt_Reboot_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Report_Olt_Reboot_Activity.this.search_count = i;
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
        CustomBaseAdapter5 customBaseAdapter5 = new CustomBaseAdapter5(getApplicationContext(), jSONArray2, i2, this);
        this.adapter = customBaseAdapter5;
        this.listview.setAdapter(customBaseAdapter5);
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
        this.listview.setAdapter((ListAdapter) null);
        this.searched_array = new JSONArray();
        this.search_count = 0;
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                if (jSONObject.getString("FMS_FIRM").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_IP").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("OLT_VLAN").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString(CodePackage.LOCATION).toUpperCase().contains(obj.toUpperCase())) {
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
    public void check_for_permission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            save_file();
        } else if (shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 101);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("SETTINGS");
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_error)).setText("Action Needs Access Of Device STORAGE");
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Olt_Reboot_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + Report_Olt_Reboot_Activity.this.getPackageName()));
                    intent.addFlags(268435456);
                    intent.addFlags(BasicMeasure.EXACTLY);
                    intent.addFlags(8388608);
                    Report_Olt_Reboot_Activity.this.startActivity(intent);
                    Report_Olt_Reboot_Activity.this.info_dialog.cancel();
                }
            });
        }
    }

    private void save_file() {
        Report_Olt_Reboot_Activity report_Olt_Reboot_Activity = this;
        report_Olt_Reboot_Activity.progress_dialog.show();
        File file = new File(Environment.getExternalStorageDirectory(), "BSNL-TEEVRA");
        if (!file.exists()) {
            file.mkdirs();
        }
        String format = new SimpleDateFormat("dd MMM yy").format(new Date());
        getIntent().getStringExtra("IP");
        File file2 = new File(Environment.getExternalStorageDirectory() + "/BSNL-TEEVRA/Olt_Reboot_Report(" + format + ").xlsx");
        if (file2.exists()) {
            file2.delete();
        }
        XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
        XSSFSheet createSheet = xSSFWorkbook.createSheet("Olt Reboot");
        XSSFCellStyle createCellStyle = xSSFWorkbook.createCellStyle();
        createCellStyle.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
        createCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        createCellStyle.setAlignment(HorizontalAlignment.CENTER);
        createCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFCellStyle createCellStyle2 = xSSFWorkbook.createCellStyle();
        createCellStyle2.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
        createCellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        createCellStyle2.setAlignment(HorizontalAlignment.CENTER);
        createCellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        createCellStyle2.setBorderTop(BorderStyle.THIN);
        createCellStyle2.setBorderBottom(BorderStyle.THIN);
        createCellStyle2.setBorderLeft(BorderStyle.THIN);
        createCellStyle2.setBorderRight(BorderStyle.THIN);
        createCellStyle2.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle2.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle2.setRightBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle2.setTopBorderColor(IndexedColors.WHITE.getIndex());
        createCellStyle2.setTopBorderColor(IndexedColors.WHITE.getIndex());
        XSSFFont createFont = xSSFWorkbook.createFont();
        createFont.setColor(IndexedColors.WHITE.getIndex());
        createFont.setBold(true);
        createFont.setFontName(HSSFFont.FONT_ARIAL);
        createCellStyle2.setFont(createFont);
        createCellStyle.setFont(createFont);
        XSSFCellStyle createCellStyle3 = xSSFWorkbook.createCellStyle();
        createCellStyle3.setAlignment(HorizontalAlignment.CENTER);
        createCellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
        createCellStyle3.setBorderTop(BorderStyle.THIN);
        createCellStyle3.setBorderBottom(BorderStyle.THIN);
        createCellStyle3.setBorderLeft(BorderStyle.THIN);
        createCellStyle3.setBorderRight(BorderStyle.THIN);
        XSSFFont createFont2 = xSSFWorkbook.createFont();
        createFont2.setColor(IndexedColors.BLACK.getIndex());
        createFont2.setFontName(HSSFFont.FONT_ARIAL);
        createCellStyle3.setFont(createFont2);
        XSSFRow createRow = createSheet.createRow(0);
        createRow.setHeight((short) ((int) (((double) createRow.getHeight()) * 1.5d)));
        XSSFCell createCell = createRow.createCell(0);
        createCell.setCellStyle(createCellStyle);
        createCell.setCellValue(report_Olt_Reboot_Activity.txt_header1.getText().toString());
        createSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        XSSFRow createRow2 = createSheet.createRow(1);
        createRow2.setHeight((short) ((int) (((double) createRow2.getHeight()) * 1.5d)));
        XSSFCell createCell2 = createRow2.createCell(0);
        createCell2.setCellValue(report_Olt_Reboot_Activity.txt_header2.getText().toString());
        createCell2.setCellStyle(createCellStyle);
        createSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
        XSSFRow createRow3 = createSheet.createRow(2);
        createRow3.setHeight((short) ((int) (((double) createRow3.getHeight()) * 1.5d)));
        XSSFCell createCell3 = createRow3.createCell(0);
        XSSFCell createCell4 = createRow3.createCell(1);
        XSSFCell createCell5 = createRow3.createCell(2);
        XSSFCell createCell6 = createRow3.createCell(3);
        XSSFCell createCell7 = createRow3.createCell(4);
        XSSFCell createCell8 = createRow3.createCell(5);
        XSSFCell createCell9 = createRow3.createCell(6);
        XSSFCell createCell10 = createRow3.createCell(7);
        createRow3.createCell(8);
        createRow3.createCell(9);
        createRow3.createCell(10);
        createCell3.setCellValue("SR");
        createCell4.setCellValue("FRANCHISEE");
        String str = CodePackage.LOCATION;
        createCell5.setCellValue(str);
        createCell6.setCellValue("VLAN");
        String str2 = "BBC";
        createCell7.setCellValue(str2);
        XSSFWorkbook xSSFWorkbook2 = xSSFWorkbook;
        String str3 = "MOBILE";
        createCell8.setCellValue(str3);
        File file3 = file2;
        createCell9.setCellValue("ALARM DESCRIPTION");
        createCell10.setCellValue("ALARM RAISED AT");
        createCell3.setCellStyle(createCellStyle2);
        createCell4.setCellStyle(createCellStyle2);
        createCell5.setCellStyle(createCellStyle2);
        createCell6.setCellStyle(createCellStyle2);
        createCell7.setCellStyle(createCellStyle2);
        createCell8.setCellStyle(createCellStyle2);
        createCell9.setCellStyle(createCellStyle2);
        createCell10.setCellStyle(createCellStyle2);
        createSheet.setColumnWidth(0, 2048);
        createSheet.setColumnWidth(1, TarConstants.DEFAULT_BLKSIZE);
        createSheet.setColumnWidth(2, 6400);
        createSheet.setColumnWidth(3, 2048);
        createSheet.setColumnWidth(4, 7680);
        createSheet.setColumnWidth(5, 3072);
        createSheet.setColumnWidth(6, TarConstants.DEFAULT_BLKSIZE);
        createSheet.setColumnWidth(7, 5120);
        int i = 0;
        int i2 = 0;
        while (i2 < report_Olt_Reboot_Activity.Row_Array.length()) {
            try {
                JSONObject jSONObject = report_Olt_Reboot_Activity.Row_Array.getJSONObject(i2);
                String trim = jSONObject.getString("FMS_FIRM").trim();
                String trim2 = jSONObject.getString(str).trim();
                String trim3 = jSONObject.getString("OLT_VLAN").trim();
                String trim4 = jSONObject.getString(str2).trim();
                String trim5 = jSONObject.getString(str3).trim();
                JSONArray jSONArray = jSONObject.getJSONArray("ALARM");
                int i3 = 0;
                while (i3 < jSONArray.length()) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    String str4 = str3;
                    String trim6 = jSONObject2.getString("ALARM_DESCRIPTION").trim();
                    JSONArray jSONArray2 = jSONArray;
                    String trim7 = jSONObject2.getString("TIMESTAMP").trim();
                    XSSFRow createRow4 = createSheet.createRow(i + 3);
                    XSSFSheet xSSFSheet = createSheet;
                    String str5 = str;
                    XSSFCell createCell11 = createRow4.createCell(0);
                    String str6 = str2;
                    XSSFCell createCell12 = createRow4.createCell(1);
                    XSSFCell createCell13 = createRow4.createCell(2);
                    int i4 = i2;
                    XSSFCell createCell14 = createRow4.createCell(3);
                    int i5 = i3;
                    XSSFCell createCell15 = createRow4.createCell(4);
                    XSSFCellStyle xSSFCellStyle = createCellStyle3;
                    XSSFCell createCell16 = createRow4.createCell(5);
                    String str7 = trim7;
                    XSSFCell createCell17 = createRow4.createCell(6);
                    XSSFCell createCell18 = createRow4.createCell(7);
                    i++;
                    createCell11.setCellValue(Integer.toString(i));
                    createCell12.setCellValue(trim);
                    createCell13.setCellValue(trim2);
                    createCell14.setCellValue(trim3);
                    createCell15.setCellValue(trim4);
                    createCell16.setCellValue(trim5);
                    createCell17.setCellValue(trim6);
                    createCell18.setCellValue(str7);
                    XSSFCellStyle xSSFCellStyle2 = xSSFCellStyle;
                    createCell11.setCellStyle(xSSFCellStyle2);
                    createCell12.setCellStyle(xSSFCellStyle2);
                    createCell13.setCellStyle(xSSFCellStyle2);
                    createCell14.setCellStyle(xSSFCellStyle2);
                    createCell15.setCellStyle(xSSFCellStyle2);
                    createCell16.setCellStyle(xSSFCellStyle2);
                    createCell17.setCellStyle(xSSFCellStyle2);
                    createCell18.setCellStyle(xSSFCellStyle2);
                    i3 = i5 + 1;
                    createCellStyle3 = xSSFCellStyle2;
                    i2 = i4;
                    str = str5;
                    str3 = str4;
                    jSONArray = jSONArray2;
                    createSheet = xSSFSheet;
                    str2 = str6;
                }
                XSSFSheet xSSFSheet2 = createSheet;
                String str8 = str3;
                XSSFCellStyle xSSFCellStyle3 = createCellStyle3;
                String str9 = str;
                String str10 = str2;
                i2++;
                report_Olt_Reboot_Activity = this;
                str3 = str8;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            if (!file3.exists()) {
                file3.createNewFile();
            }
            final File file4 = file3;
            FileOutputStream fileOutputStream = new FileOutputStream(file4);
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
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("The File Is Downloaded and Saved at <br><b>" + file4 + "</b><br><br>Do You Want To Open The File "));
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Olt_Reboot_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Olt_Reboot_Activity.this.info_dialog.cancel();
                    Uri uriForFile = FileProvider.getUriForFile(Report_Olt_Reboot_Activity.this.getApplicationContext(), Report_Olt_Reboot_Activity.this.getApplicationContext().getPackageName() + ".provider", file4);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(uriForFile, "application/vnd.ms-excel");
                    intent.setFlags(1);
                    Report_Olt_Reboot_Activity.this.startActivity(intent);
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

    public void onBackPressed() {
        Intent intent = new Intent(this, Report_Traffic_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
