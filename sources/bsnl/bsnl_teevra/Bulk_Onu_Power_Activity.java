package bsnl.bsnl_teevra;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bulk_Onu_Power_Activity extends AppCompatActivity implements TextWatcher, View.OnClickListener {
    public static final int CAMERA_REQUEST_CODE = 200;
    public static final int STORAGE_PERMISSION_REQ = 101;
    public static final int STORAGE_REQUEST_CODE = 201;
    public int NUM_ITEMS_PAGE = 50;
    private String Pref_Circle;
    private String Pref_Designation;
    private String Pref_Randkey;
    private String Pref_SSA;
    private String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    private JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private String androidId;
    private LinearLayout btnLay;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public int countt = 0;
    private AlertDialog error_dialog;
    private EditText et_search;
    private AlertDialog geo_dialog;
    private ImageView img_download;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_btn;
    private AlertDialog logout_dialog;
    private TextView next;
    private TextView prev;
    private AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public int search_count = 0;
    private JSONArray searched_array;
    private SharedPreferences sharedPreferences;
    private TableLayout tbl_bulk;
    private TableLayout tbl_bulk_header;
    private TextView title;
    private TextView txt_alert;
    private TextView txt_element;
    private AlertDialog uplink_dialog;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.bulk_onu_power_activity);
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
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        this.txt_element = (TextView) findViewById(R.id.txt_element);
        this.img_download = (ImageView) findViewById(R.id.img_download);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.tbl_bulk_header = (TableLayout) findViewById(R.id.tbl_bulk_header);
        this.tbl_bulk = (TableLayout) findViewById(R.id.tbl_bulk);
        this.et_search.addTextChangedListener(this);
        this.txt_element.setText(" (" + getIntent().getStringExtra("IP") + ")");
        try {
            JSONArray jSONArray = new JSONObject(getIntent().getStringExtra("RESPONSE")).getJSONArray("ROWSET");
            this.Row_Array = jSONArray;
            this.TOTAL_LIST_ITEMS = jSONArray.length();
            Btnfooter();
            loadList(0);
            CheckBtnBackGroud(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.img_download.setOnClickListener(this);
    }

    private void Btnfooter() {
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
                    if (Bulk_Onu_Power_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "Page " + (Bulk_Onu_Power_Activity.this.countt + 2) + " of " + i3, 1).show();
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity.loadList(bulk_Onu_Power_Activity.countt + 1);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity2 = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity2.CheckBtnBackGroud(bulk_Onu_Power_Activity2.countt + 1);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity3 = Bulk_Onu_Power_Activity.this;
                    int unused = bulk_Onu_Power_Activity3.countt = bulk_Onu_Power_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Bulk_Onu_Power_Activity.this.countt == 0) {
                        Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "Page " + Bulk_Onu_Power_Activity.this.countt + " of " + i3, 1).show();
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity.loadList(bulk_Onu_Power_Activity.countt - 1);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity2 = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity2.CheckBtnBackGroud(bulk_Onu_Power_Activity2.countt - 1);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity3 = Bulk_Onu_Power_Activity.this;
                    int unused = bulk_Onu_Power_Activity3.countt = bulk_Onu_Power_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "" + (i4 + 1), 1).show();
                    Bulk_Onu_Power_Activity.this.loadList(i4);
                    Bulk_Onu_Power_Activity.this.CheckBtnBackGroud(i4);
                    int unused = Bulk_Onu_Power_Activity.this.countt = i4;
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
                    if (Bulk_Onu_Power_Activity.this.search_count + 1 >= length) {
                        Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity.loadList1(bulk_Onu_Power_Activity.search_count + 1, jSONArray);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity2 = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity2.CheckBtnBackGroud1(bulk_Onu_Power_Activity2.search_count + 1, jSONArray);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity3 = Bulk_Onu_Power_Activity.this;
                    int unused = bulk_Onu_Power_Activity3.search_count = bulk_Onu_Power_Activity3.search_count + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (Bulk_Onu_Power_Activity.this.search_count == 0) {
                        Toast.makeText(Bulk_Onu_Power_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity.loadList1(bulk_Onu_Power_Activity.search_count - 1, jSONArray);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity2 = Bulk_Onu_Power_Activity.this;
                    bulk_Onu_Power_Activity2.CheckBtnBackGroud1(bulk_Onu_Power_Activity2.search_count - 1, jSONArray);
                    Bulk_Onu_Power_Activity bulk_Onu_Power_Activity3 = Bulk_Onu_Power_Activity.this;
                    int unused = bulk_Onu_Power_Activity3.search_count = bulk_Onu_Power_Activity3.search_count - 1;
                }
            });
            this.btns[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bulk_Onu_Power_Activity.this.loadList1(i, jSONArray);
                    Bulk_Onu_Power_Activity.this.CheckBtnBackGroud1(i, jSONArray);
                    int unused = Bulk_Onu_Power_Activity.this.search_count = i;
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
        this.tbl_bulk.removeAllViews();
        this.searched_array = new JSONArray();
        this.search_count = 0;
        int i = 0;
        while (i < this.Row_Array.length()) {
            try {
                JSONObject jSONObject = this.Row_Array.getJSONObject(i);
                if (jSONObject.getString("telephone").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("username").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("vlan").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("ont_mac").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("port").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("tx_power").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("rx_power").toUpperCase().contains(obj.toUpperCase()) || jSONObject.getString("distance").toUpperCase().contains(obj.toUpperCase())) {
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

    private void Populate_table(JSONArray jSONArray, int i) {
        this.tbl_bulk_header.removeAllViews();
        this.tbl_bulk.removeAllViews();
        TableRow tableRow = new TableRow(this);
        int i2 = -1;
        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
        TableRow tableRow2 = new TableRow(this);
        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
        TextView textView = new TextView(this);
        textView.setText("SR");
        textView.setTextColor(-1);
        int i3 = 17;
        textView.setGravity(17);
        textView.setPadding(20, 15, 20, 15);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView.setBackgroundResource(R.drawable.new_style1);
        TextView textView2 = new TextView(this);
        textView2.setText("TELEPHONE");
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setPadding(20, 15, 20, 15);
        textView2.setTypeface((Typeface) null, 1);
        textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView2.setBackgroundResource(R.drawable.new_style1);
        TextView textView3 = new TextView(this);
        textView3.setText("STATUS");
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        textView3.setPadding(20, 15, 20, 15);
        textView3.setTypeface((Typeface) null, 1);
        textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView3.setBackgroundResource(R.drawable.new_style1);
        TextView textView4 = new TextView(this);
        textView4.setText("VLAN");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(this);
        textView5.setText("ONT-MAC");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(this);
        textView6.setText("PORT");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(this);
        textView7.setText("TX-POWER");
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(this);
        textView8.setText("RX-POWER");
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(this);
        textView9.setText("DISTANCE");
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        tableRow.addView(textView);
        tableRow.addView(textView2);
        tableRow2.addView(textView3);
        tableRow2.addView(textView4);
        tableRow2.addView(textView5);
        tableRow2.addView(textView6);
        tableRow2.addView(textView7);
        tableRow2.addView(textView8);
        tableRow2.addView(textView9);
        this.tbl_bulk_header.addView(tableRow);
        this.tbl_bulk.addView(tableRow2);
        int i4 = 0;
        while (i4 < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i4);
                TableRow tableRow3 = new TableRow(this);
                tableRow3.setLayoutParams(new TableRow.LayoutParams(i2, -2));
                TableRow tableRow4 = new TableRow(this);
                tableRow4.setLayoutParams(new TableRow.LayoutParams(i2, -2));
                TextView textView10 = new TextView(this);
                textView10.setText(Integer.toString(i + i4 + 1));
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(i3);
                textView10.setTypeface((Typeface) null, 1);
                textView10.setPadding(20, 15, 20, 15);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView10.setBackgroundResource(R.drawable.style17);
                TextView textView11 = new TextView(this);
                textView11.setText(jSONObject.getString("telephone"));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(i3);
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(this);
                textView12.setText(Html.fromHtml(jSONObject.getString(NotificationCompat.CATEGORY_STATUS).toUpperCase()));
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(i3);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                TextView textView13 = new TextView(this);
                textView13.setText(Html.fromHtml(jSONObject.getString("vlan").toUpperCase()));
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(i3);
                textView13.setPadding(20, 15, 20, 15);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView13.setBackgroundResource(R.drawable.style17);
                TextView textView14 = new TextView(this);
                textView14.setText(Html.fromHtml(jSONObject.getString("ont_mac")));
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(i3);
                textView14.setPadding(20, 15, 20, 15);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView14.setBackgroundResource(R.drawable.style17);
                TextView textView15 = new TextView(this);
                textView15.setText(Html.fromHtml(jSONObject.getString("port")));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(i3);
                textView15.setPadding(20, 15, 20, 15);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView15.setBackgroundResource(R.drawable.style17);
                TextView textView16 = new TextView(this);
                textView16.setText(Html.fromHtml(jSONObject.getString("tx_power")));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                textView16.setPadding(20, 15, 20, 15);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView16.setBackgroundResource(R.drawable.style17);
                TextView textView17 = new TextView(this);
                textView17.setText(Html.fromHtml(jSONObject.getString("rx_power")));
                textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView17.setGravity(17);
                textView17.setPadding(20, 15, 20, 15);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView17.setBackgroundResource(R.drawable.style17);
                TextView textView18 = new TextView(this);
                textView18.setText(Html.fromHtml(jSONObject.getString("distance")));
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                textView18.setPadding(20, 15, 20, 15);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView18.setBackgroundResource(R.drawable.style17);
                tableRow3.addView(textView10);
                tableRow3.addView(textView11);
                tableRow4.addView(textView12);
                tableRow4.addView(textView13);
                tableRow4.addView(textView14);
                tableRow4.addView(textView15);
                tableRow4.addView(textView16);
                tableRow4.addView(textView17);
                tableRow4.addView(textView18);
                this.tbl_bulk_header.addView(tableRow3);
                this.tbl_bulk.addView(tableRow4);
                i4++;
                i2 = -1;
                i3 = 17;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_download) {
            save_file();
        }
    }

    private void check_for_permission() {
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
                    Bulk_Onu_Power_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + Bulk_Onu_Power_Activity.this.getPackageName()));
                    intent.addFlags(268435456);
                    intent.addFlags(BasicMeasure.EXACTLY);
                    intent.addFlags(8388608);
                    Bulk_Onu_Power_Activity.this.startActivity(intent);
                    Bulk_Onu_Power_Activity.this.info_dialog.cancel();
                }
            });
        }
    }

    private void save_file() {
        Bulk_Onu_Power_Activity bulk_Onu_Power_Activity = this;
        bulk_Onu_Power_Activity.progress_dialog.show();
        final File file = new File(bulk_Onu_Power_Activity.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Bulk_Onu_Power_" + getIntent().getStringExtra("IP") + " (" + new SimpleDateFormat("dd MMM yy").format(new Date()) + ").xlsx");
        if (file.exists()) {
            file.delete();
        }
        XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
        XSSFSheet createSheet = xSSFWorkbook.createSheet(bulk_Onu_Power_Activity.txt_element.getText().toString());
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
        XSSFCell createCell9 = createRow.createCell(8);
        createCell.setCellValue("SR");
        createCell2.setCellValue("TELEPHONE");
        createCell3.setCellValue("STATUS");
        createCell4.setCellValue("VLAN");
        createCell5.setCellValue("ONT-MAC");
        createCell6.setCellValue("PORT");
        createCell7.setCellValue("TX-POWER");
        createCell8.setCellValue("RX-POWER");
        createCell9.setCellValue("DISTANCE");
        createCell.setCellStyle(createCellStyle);
        createCell2.setCellStyle(createCellStyle);
        createCell3.setCellStyle(createCellStyle);
        createCell4.setCellStyle(createCellStyle);
        createCell5.setCellStyle(createCellStyle);
        createCell6.setCellStyle(createCellStyle);
        createCell7.setCellStyle(createCellStyle);
        createCell8.setCellStyle(createCellStyle);
        createCell9.setCellStyle(createCellStyle);
        int i = 0;
        while (i < bulk_Onu_Power_Activity.Row_Array.length()) {
            try {
                JSONObject jSONObject = bulk_Onu_Power_Activity.Row_Array.getJSONObject(i);
                i++;
                XSSFRow createRow2 = createSheet.createRow(i);
                XSSFCell createCell10 = createRow2.createCell(0);
                XSSFCell createCell11 = createRow2.createCell(1);
                XSSFCell createCell12 = createRow2.createCell(2);
                XSSFCell createCell13 = createRow2.createCell(3);
                XSSFCell createCell14 = createRow2.createCell(4);
                XSSFCell createCell15 = createRow2.createCell(5);
                XSSFCell createCell16 = createRow2.createCell(6);
                XSSFCell createCell17 = createRow2.createCell(7);
                XSSFCell createCell18 = createRow2.createCell(8);
                createCell10.setCellValue(Integer.toString(i));
                createCell11.setCellValue(Html.fromHtml(jSONObject.getString("telephone")).toString());
                createCell12.setCellValue(Html.fromHtml(jSONObject.getString(NotificationCompat.CATEGORY_STATUS)).toString());
                createCell13.setCellValue(Html.fromHtml(jSONObject.getString("vlan")).toString());
                createCell14.setCellValue(Html.fromHtml(jSONObject.getString("ont_mac")).toString());
                createCell15.setCellValue(Html.fromHtml(jSONObject.getString("port")).toString());
                createCell16.setCellValue(Html.fromHtml(jSONObject.getString("tx_power")).toString());
                createCell17.setCellValue(Html.fromHtml(jSONObject.getString("rx_power")).toString());
                createCell18.setCellValue(Html.fromHtml(jSONObject.getString("distance")).toString());
                createCell10.setCellStyle(createCellStyle2);
                createCell11.setCellStyle(createCellStyle2);
                createCell12.setCellStyle(createCellStyle2);
                createCell13.setCellStyle(createCellStyle2);
                createCell14.setCellStyle(createCellStyle2);
                createCell15.setCellStyle(createCellStyle2);
                createCell16.setCellStyle(createCellStyle2);
                createCell17.setCellStyle(createCellStyle2);
                createCell18.setCellStyle(createCellStyle2);
                bulk_Onu_Power_Activity = this;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        createSheet.setColumnWidth(0, 2048);
        createSheet.setColumnWidth(1, 3840);
        createSheet.setColumnWidth(2, 2560);
        createSheet.setColumnWidth(3, 1792);
        createSheet.setColumnWidth(4, 5632);
        createSheet.setColumnWidth(5, 3840);
        createSheet.setColumnWidth(6, 3840);
        createSheet.setColumnWidth(7, 3840);
        createSheet.setColumnWidth(8, 3840);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xSSFWorkbook.write(fileOutputStream);
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
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("The File Is Downloaded and Saved at <br><b>" + file + "</b><br><br>Do You Want To Open The File "));
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bulk_Onu_Power_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Bulk_Onu_Power_Activity.this.info_dialog.cancel();
                    Uri uriForFile = FileProvider.getUriForFile(Bulk_Onu_Power_Activity.this.getApplicationContext(), Bulk_Onu_Power_Activity.this.getApplicationContext().getPackageName() + ".provider", file);
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(uriForFile, "application/vnd.ms-excel");
                    intent.setFlags(1);
                    Bulk_Onu_Power_Activity.this.startActivity(intent);
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
