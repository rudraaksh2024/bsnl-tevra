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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fms_Inventory_NewActivity extends AppCompatActivity implements TextWatcher {
    private String Pref_Fms_Firm;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_olt_genre;
    private ArrayAdapter<String> adapter_olt_make;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public AlertDialog inventory_dialog;
    private ArrayList<String> inventory_make_list;
    /* access modifiers changed from: private */
    public JSONArray jsonArray;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> olt_genre;
    private ArrayList<String> olt_uplink;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TableLayout tbl_detail;
    /* access modifiers changed from: private */
    public TableLayout tbl_header;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_header;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_inventory_newactivity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_Firm = this.sharedPreferences.getString("fms_firm_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
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
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.tbl_header = (TableLayout) findViewById(R.id.tbl_header);
        this.tbl_detail = (TableLayout) findViewById(R.id.tbl_detail);
        this.et_search.addTextChangedListener(this);
        this.tbl_header.removeAllViews();
        this.tbl_detail.removeAllViews();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_manage_inventory.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Fms_Inventory_NewActivity.this.txt_header.setText(jSONObject.getString("FIRM_NAME").toUpperCase());
                    Fms_Inventory_NewActivity.this.txt_header.setPaintFlags(8);
                    JSONArray unused = Fms_Inventory_NewActivity.this.jsonArray = jSONObject.getJSONArray("ROWSET");
                    if (Fms_Inventory_NewActivity.this.jsonArray.length() > 0) {
                        Fms_Inventory_NewActivity.this.tbl_detail.removeAllViews();
                        TableRow tableRow = new TableRow(Fms_Inventory_NewActivity.this);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                        TableRow tableRow2 = new TableRow(Fms_Inventory_NewActivity.this);
                        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                        TextView textView = new TextView(Fms_Inventory_NewActivity.this);
                        textView.setText("MANAGE");
                        textView.setTextColor(-1);
                        textView.setGravity(17);
                        textView.setPadding(20, 15, 20, 15);
                        textView.setTypeface((Typeface) null, 1);
                        textView.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView.setBackgroundResource(R.drawable.new_style1);
                        TextView textView2 = new TextView(Fms_Inventory_NewActivity.this);
                        textView2.setText("SR");
                        textView2.setTextColor(-1);
                        textView2.setGravity(17);
                        textView2.setPadding(20, 15, 20, 15);
                        textView2.setTypeface((Typeface) null, 1);
                        textView2.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView2.setBackgroundResource(R.drawable.new_style1);
                        TextView textView3 = new TextView(Fms_Inventory_NewActivity.this);
                        textView3.setText(CodePackage.LOCATION);
                        textView3.setTextColor(-1);
                        textView3.setGravity(17);
                        textView3.setPadding(20, 15, 20, 15);
                        textView3.setTypeface((Typeface) null, 1);
                        textView3.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView3.setBackgroundResource(R.drawable.new_style1);
                        TextView textView4 = new TextView(Fms_Inventory_NewActivity.this);
                        textView4.setText("IP");
                        textView4.setTextColor(-1);
                        textView4.setGravity(17);
                        CharSequence charSequence = "********";
                        textView4.setPadding(20, 15, 20, 15);
                        textView4.setTypeface((Typeface) null, 1);
                        textView4.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView4.setBackgroundResource(R.drawable.new_style1);
                        TextView textView5 = new TextView(Fms_Inventory_NewActivity.this);
                        textView5.setText("VLAN");
                        textView5.setTextColor(-1);
                        textView5.setGravity(17);
                        String str2 = "BNG_NAME";
                        textView5.setPadding(20, 15, 20, 15);
                        textView5.setTypeface((Typeface) null, 1);
                        textView5.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView5.setBackgroundResource(R.drawable.new_style1);
                        TextView textView6 = new TextView(Fms_Inventory_NewActivity.this);
                        textView6.setText("ELEMENT");
                        textView6.setTextColor(-1);
                        textView6.setGravity(17);
                        String str3 = "OLT_CHIPSET";
                        textView6.setPadding(20, 15, 20, 15);
                        textView6.setTypeface((Typeface) null, 1);
                        textView6.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView6.setBackgroundResource(R.drawable.new_style1);
                        TextView textView7 = new TextView(Fms_Inventory_NewActivity.this);
                        textView7.setText("MAKE");
                        textView7.setTextColor(-1);
                        textView7.setGravity(17);
                        String str4 = "OUTER_VLAN";
                        textView7.setPadding(20, 15, 20, 15);
                        textView7.setTypeface((Typeface) null, 1);
                        textView7.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView7.setBackgroundResource(R.drawable.new_style1);
                        TextView textView8 = new TextView(Fms_Inventory_NewActivity.this);
                        textView8.setText("USERNAME");
                        textView8.setTextColor(-1);
                        textView8.setGravity(17);
                        String str5 = "MAKE";
                        textView8.setPadding(20, 15, 20, 15);
                        textView8.setTypeface((Typeface) null, 1);
                        textView8.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView8.setBackgroundResource(R.drawable.new_style1);
                        TextView textView9 = new TextView(Fms_Inventory_NewActivity.this);
                        textView9.setText("PASSWORD");
                        textView9.setTextColor(-1);
                        textView9.setGravity(17);
                        String str6 = "N/W_ELEMENT";
                        textView9.setPadding(20, 15, 20, 15);
                        textView9.setTypeface((Typeface) null, 1);
                        textView9.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView9.setBackgroundResource(R.drawable.new_style1);
                        TextView textView10 = new TextView(Fms_Inventory_NewActivity.this);
                        textView10.setText("ENABLE PASSWORD");
                        textView10.setTextColor(-1);
                        textView10.setGravity(17);
                        String str7 = "IP";
                        textView10.setPadding(20, 15, 20, 15);
                        textView10.setTypeface((Typeface) null, 1);
                        textView10.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView10.setBackgroundResource(R.drawable.new_style1);
                        TextView textView11 = new TextView(Fms_Inventory_NewActivity.this);
                        textView11.setText("CHIPSET");
                        textView11.setTextColor(-1);
                        textView11.setGravity(17);
                        String str8 = CodePackage.LOCATION;
                        textView11.setPadding(20, 15, 20, 15);
                        textView11.setTypeface((Typeface) null, 1);
                        textView11.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView11.setBackgroundResource(R.drawable.new_style1);
                        TextView textView12 = new TextView(Fms_Inventory_NewActivity.this);
                        textView12.setText("BNG");
                        textView12.setTextColor(-1);
                        textView12.setGravity(17);
                        textView12.setPadding(20, 15, 20, 15);
                        textView12.setTypeface((Typeface) null, 1);
                        textView12.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView12.setBackgroundResource(R.drawable.new_style1);
                        tableRow.addView(textView);
                        tableRow2.addView(textView2);
                        tableRow2.addView(textView3);
                        tableRow2.addView(textView4);
                        tableRow2.addView(textView5);
                        tableRow2.addView(textView6);
                        tableRow2.addView(textView7);
                        tableRow2.addView(textView8);
                        tableRow2.addView(textView9);
                        tableRow2.addView(textView10);
                        tableRow2.addView(textView11);
                        tableRow2.addView(textView12);
                        Fms_Inventory_NewActivity.this.tbl_header.addView(tableRow);
                        Fms_Inventory_NewActivity.this.tbl_detail.addView(tableRow2);
                        int i = 0;
                        while (i < Fms_Inventory_NewActivity.this.jsonArray.length()) {
                            JSONObject jSONObject2 = Fms_Inventory_NewActivity.this.jsonArray.getJSONObject(i);
                            jSONObject2.getString("CIRCLE");
                            jSONObject2.getString("SSA");
                            String str9 = str8;
                            String string = jSONObject2.getString(str9);
                            String str10 = str7;
                            String string2 = jSONObject2.getString(str10);
                            String str11 = str6;
                            jSONObject2.getString(str11);
                            String str12 = str5;
                            String string3 = jSONObject2.getString(str12);
                            String str13 = str4;
                            String string4 = jSONObject2.getString(str13);
                            String string5 = jSONObject2.getString("OLT_UID");
                            String string6 = jSONObject2.getString("OLT_PWD");
                            String string7 = jSONObject2.getString("OLT_EN_PWD");
                            String str14 = str3;
                            String string8 = jSONObject2.getString(str14);
                            String str15 = string3;
                            String str16 = str2;
                            jSONObject2.getString(str16);
                            String str17 = string7;
                            String str18 = string6;
                            String str19 = string5;
                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, (int) (((double) Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext)) * 2.4d));
                            TableRow tableRow3 = new TableRow(Fms_Inventory_NewActivity.this);
                            String str20 = string;
                            tableRow3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                            tableRow3.setGravity(17);
                            TableRow tableRow4 = new TableRow(Fms_Inventory_NewActivity.this);
                            String str21 = string4;
                            tableRow4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                            tableRow4.setGravity(17);
                            LinearLayout linearLayout = new LinearLayout(Fms_Inventory_NewActivity.this);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.setGravity(17);
                            linearLayout.setOrientation(0);
                            String str22 = string2;
                            linearLayout.setPadding(20, 10, 20, 10);
                            linearLayout.setBackgroundResource(R.drawable.style17);
                            ImageView imageView = new ImageView(Fms_Inventory_NewActivity.this);
                            imageView.setPadding(5, 5, 5, 5);
                            imageView.setClickable(true);
                            imageView.setImageResource(R.drawable.ic_action_edit);
                            imageView.setBackgroundResource(R.drawable.button01);
                            linearLayout.addView(imageView);
                            tableRow3.addView(linearLayout);
                            TextView textView13 = new TextView(Fms_Inventory_NewActivity.this);
                            textView13.setLayoutParams(layoutParams);
                            int i2 = i + 1;
                            textView13.setText(Integer.toString(i2));
                            textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView13.setGravity(17);
                            textView13.setPadding(20, 15, 20, 15);
                            textView13.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView13.setBackgroundResource(R.drawable.style17);
                            TextView textView14 = new TextView(Fms_Inventory_NewActivity.this);
                            textView14.setLayoutParams(layoutParams);
                            textView14.setText(jSONObject2.getString(str9).toUpperCase());
                            textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView14.setGravity(17);
                            String str23 = str9;
                            textView14.setPadding(20, 15, 20, 15);
                            textView14.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView14.setBackgroundResource(R.drawable.style17);
                            TextView textView15 = new TextView(Fms_Inventory_NewActivity.this);
                            textView15.setLayoutParams(layoutParams);
                            textView15.setText(jSONObject2.getString(str10));
                            textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView15.setGravity(17);
                            String str24 = str10;
                            textView15.setPadding(20, 15, 20, 15);
                            textView15.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView15.setBackgroundResource(R.drawable.style17);
                            TextView textView16 = new TextView(Fms_Inventory_NewActivity.this);
                            textView16.setLayoutParams(layoutParams);
                            textView16.setText(jSONObject2.getString(str13));
                            textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView16.setGravity(17);
                            String str25 = str13;
                            textView16.setPadding(20, 15, 20, 15);
                            textView16.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView16.setBackgroundResource(R.drawable.style17);
                            TextView textView17 = new TextView(Fms_Inventory_NewActivity.this);
                            textView17.setLayoutParams(layoutParams);
                            textView17.setText(jSONObject2.getString(str11));
                            textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView17.setGravity(17);
                            String str26 = str11;
                            textView17.setPadding(20, 15, 20, 15);
                            textView17.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView17.setBackgroundResource(R.drawable.style17);
                            TextView textView18 = new TextView(Fms_Inventory_NewActivity.this);
                            textView18.setLayoutParams(layoutParams);
                            textView18.setText(jSONObject2.getString(str12));
                            textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView18.setGravity(17);
                            String str27 = str12;
                            textView18.setPadding(20, 15, 20, 15);
                            textView18.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView18.setBackgroundResource(R.drawable.style17);
                            TextView textView19 = new TextView(Fms_Inventory_NewActivity.this);
                            textView19.setLayoutParams(layoutParams);
                            CharSequence charSequence2 = charSequence;
                            textView19.setText(charSequence2);
                            ImageView imageView2 = imageView;
                            textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView19.setGravity(17);
                            TableRow tableRow5 = tableRow3;
                            textView19.setPadding(20, 15, 20, 15);
                            textView19.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView19.setBackgroundResource(R.drawable.style17);
                            TextView textView20 = new TextView(Fms_Inventory_NewActivity.this);
                            textView20.setLayoutParams(layoutParams);
                            textView20.setText(charSequence2);
                            textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView20.setGravity(17);
                            TextView textView21 = textView19;
                            textView20.setPadding(20, 15, 20, 15);
                            textView20.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView20.setBackgroundResource(R.drawable.style17);
                            TextView textView22 = new TextView(Fms_Inventory_NewActivity.this);
                            textView22.setLayoutParams(layoutParams);
                            textView22.setText(charSequence2);
                            textView22.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView22.setGravity(17);
                            CharSequence charSequence3 = charSequence2;
                            textView22.setPadding(20, 15, 20, 15);
                            textView22.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView22.setBackgroundResource(R.drawable.style17);
                            TextView textView23 = new TextView(Fms_Inventory_NewActivity.this);
                            textView23.setLayoutParams(layoutParams);
                            textView23.setText(jSONObject2.getString(str14));
                            textView23.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView23.setGravity(17);
                            textView23.setPadding(20, 15, 20, 15);
                            textView23.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView23.setBackgroundResource(R.drawable.style17);
                            TextView textView24 = new TextView(Fms_Inventory_NewActivity.this);
                            textView24.setLayoutParams(layoutParams);
                            textView24.setText(jSONObject2.getString(str16).toUpperCase());
                            textView24.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView24.setGravity(17);
                            textView24.setPadding(20, 15, 20, 15);
                            textView24.setTextSize(0, Fms_Inventory_NewActivity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView24.setBackgroundResource(R.drawable.style17);
                            tableRow4.addView(textView13);
                            tableRow4.addView(textView14);
                            tableRow4.addView(textView15);
                            tableRow4.addView(textView16);
                            tableRow4.addView(textView17);
                            tableRow4.addView(textView18);
                            tableRow4.addView(textView21);
                            tableRow4.addView(textView20);
                            tableRow4.addView(textView22);
                            tableRow4.addView(textView23);
                            tableRow4.addView(textView24);
                            Fms_Inventory_NewActivity.this.tbl_header.addView(tableRow5);
                            Fms_Inventory_NewActivity.this.tbl_detail.addView(tableRow4);
                            final String str28 = str22;
                            final String str29 = str21;
                            final String str30 = str20;
                            final String str31 = str19;
                            final String str32 = str18;
                            final String str33 = str17;
                            final String str34 = str15;
                            final String str35 = string8;
                            imageView2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Fms_Inventory_NewActivity.this);
                                    View inflate = Fms_Inventory_NewActivity.this.getLayoutInflater().inflate(R.layout.custom_fms_inventory, (ViewGroup) null);
                                    final EditText editText = (EditText) inflate.findViewById(R.id.et_olt_uid);
                                    final EditText editText2 = (EditText) inflate.findViewById(R.id.et_olt_pwd);
                                    final EditText editText3 = (EditText) inflate.findViewById(R.id.et_olt_en_pwd);
                                    final Spinner spinner = (Spinner) inflate.findViewById(R.id.sp_olt_genere);
                                    ((TextView) inflate.findViewById(R.id.txt_header)).setText("OLT-IP : " + str28 + " | OLT VLAN : " + str29);
                                    ((TextView) inflate.findViewById(R.id.txt_location)).setText("(" + str30 + ")");
                                    editText.setText(str31);
                                    editText2.setText(str32);
                                    editText3.setText(str33);
                                    ((EditText) inflate.findViewById(R.id.et_olt_make)).setText(str34);
                                    ArrayList unused = Fms_Inventory_NewActivity.this.olt_genre = new ArrayList();
                                    Fms_Inventory_NewActivity.this.olt_genre.add("OLT-GENRE");
                                    Fms_Inventory_NewActivity.this.olt_genre.add("EPON");
                                    Fms_Inventory_NewActivity.this.olt_genre.add("GPON");
                                    Fms_Inventory_NewActivity.this.olt_genre.add("GEPON");
                                    ArrayAdapter unused2 = Fms_Inventory_NewActivity.this.adapter_olt_genre = new ArrayAdapter(Fms_Inventory_NewActivity.this, R.layout.spinner_item, Fms_Inventory_NewActivity.this.olt_genre);
                                    spinner.setAdapter(Fms_Inventory_NewActivity.this.adapter_olt_genre);
                                    spinner.setSelection(Fms_Inventory_NewActivity.this.adapter_olt_genre.getPosition(str35));
                                    builder.setCancelable(true);
                                    builder.setView(inflate);
                                    AlertDialog unused3 = Fms_Inventory_NewActivity.this.inventory_dialog = builder.create();
                                    Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                                    Fms_Inventory_NewActivity.this.inventory_dialog.show();
                                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Fms_Inventory_NewActivity.this.inventory_dialog.cancel();
                                        }
                                    });
                                    ((TextView) inflate.findViewById(R.id.txt_update)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            final String trim = editText.getText().toString().trim();
                                            final String trim2 = editText2.getText().toString().trim();
                                            final String trim3 = editText3.getText().toString().trim();
                                            final String trim4 = spinner.getSelectedItem().toString().trim();
                                            if (trim4.equals("OLT-GENRE")) {
                                                new AlertHelperclass().ntoast("PLEASE ENTER A VALID OLT-GENRE\nFOR EXAMPLE :- EPON or GPON or GEPON", Fms_Inventory_NewActivity.this.getApplicationContext());
                                                return;
                                            }
                                            Fms_Inventory_NewActivity.this.progress_dialog.show();
                                            RequestQueue newRequestQueue = Volley.newRequestQueue(Fms_Inventory_NewActivity.this);
                                            AnonymousClass3 r0 = new StringRequest(1, Fms_Inventory_NewActivity.this.getString(R.string.serverip) + "fms/fms_inventory_update.php", new Response.Listener<String>() {
                                                public void onResponse(String str) {
                                                    Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                                                    Fms_Inventory_NewActivity.this.inventory_dialog.cancel();
                                                    try {
                                                        if (Boolean.valueOf(new JSONObject(str).getBoolean("success")).booleanValue()) {
                                                            new AlertHelperclass().ptoast("OLT Login Credentials Are Successfully Updated", Fms_Inventory_NewActivity.this.getApplicationContext());
                                                            Fms_Inventory_NewActivity.this.finish();
                                                            Fms_Inventory_NewActivity.this.overridePendingTransition(0, 0);
                                                            Fms_Inventory_NewActivity.this.startActivity(Fms_Inventory_NewActivity.this.getIntent());
                                                            Fms_Inventory_NewActivity.this.overridePendingTransition(0, 0);
                                                            return;
                                                        }
                                                        Fms_Inventory_NewActivity.this.txt_alert.setText("FAILED\nOlt Login Credential Updation Failed");
                                                        Fms_Inventory_NewActivity.this.error_dialog.show();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                                public void onErrorResponse(VolleyError volleyError) {
                                                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Fms_Inventory_NewActivity.this.getApplicationContext());
                                                    Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                                                    Fms_Inventory_NewActivity.this.inventory_dialog.cancel();
                                                    Fms_Inventory_NewActivity.this.txt_alert.setText(onErrorResponse);
                                                    Fms_Inventory_NewActivity.this.error_dialog.show();
                                                }
                                            }) {
                                                /* access modifiers changed from: protected */
                                                public Map<String, String> getParams() throws AuthFailureError {
                                                    HashMap hashMap = new HashMap();
                                                    hashMap.put("username", Fms_Inventory_NewActivity.this.Pref_Username);
                                                    hashMap.put("random_key", Fms_Inventory_NewActivity.this.Pref_Randkey);
                                                    hashMap.put("device_id", Fms_Inventory_NewActivity.this.androidId);
                                                    hashMap.put("olt_ip", str28);
                                                    hashMap.put("olt_loginid", trim);
                                                    hashMap.put("olt_pwd", trim2);
                                                    hashMap.put("olt_en_pwd", trim3);
                                                    hashMap.put("olt_chipset", trim4);
                                                    return hashMap;
                                                }
                                            };
                                            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                            newRequestQueue.add(r0);
                                        }
                                    });
                                }
                            });
                            str2 = str16;
                            str5 = str27;
                            str8 = str23;
                            str7 = str24;
                            i = i2;
                            str4 = str25;
                            str6 = str26;
                            charSequence = charSequence3;
                            str3 = str14;
                        }
                        Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                        return;
                    }
                    Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Fms_Inventory_NewActivity.this);
                    View inflate = Fms_Inventory_NewActivity.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                    TextView textView25 = (TextView) inflate.findViewById(R.id.txt_submit);
                    ((TextView) inflate.findViewById(R.id.txt_info)).setText("WARNING");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("No TIP Olt/BAF Inventory Is Available\nPlease Contact Your SSA Nodal Officer");
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                    textView25.setText("OK");
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog unused2 = Fms_Inventory_NewActivity.this.logout_dialog = builder.create();
                    Fms_Inventory_NewActivity.this.logout_dialog.show();
                    textView25.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Fms_Inventory_NewActivity.this.logout_dialog.cancel();
                            Intent intent = new Intent(Fms_Inventory_NewActivity.this, FMS_DashBoard_Activity.class);
                            intent.setFlags(67108864);
                            Fms_Inventory_NewActivity.this.startActivity(intent);
                            Fms_Inventory_NewActivity.this.finish();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Fms_Inventory_NewActivity.this.progress_dialog.cancel();
                Fms_Inventory_NewActivity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Fms_Inventory_NewActivity.this.getApplicationContext()));
                Fms_Inventory_NewActivity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_username", Fms_Inventory_NewActivity.this.Pref_Fms_Username);
                hashMap.put("username", Fms_Inventory_NewActivity.this.Pref_Username);
                hashMap.put("random_key", Fms_Inventory_NewActivity.this.Pref_Randkey);
                hashMap.put("device_id", Fms_Inventory_NewActivity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
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
                    Fms_Inventory_NewActivity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Fms_Inventory_NewActivity fms_Inventory_NewActivity = Fms_Inventory_NewActivity.this;
                    fms_Inventory_NewActivity.editor = fms_Inventory_NewActivity.sharedPreferences.edit();
                    Fms_Inventory_NewActivity.this.editor.remove("isfmsloggedin_key");
                    Fms_Inventory_NewActivity.this.editor.commit();
                    Intent intent = new Intent(Fms_Inventory_NewActivity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    Fms_Inventory_NewActivity.this.startActivity(intent);
                    Fms_Inventory_NewActivity.this.finish();
                    Fms_Inventory_NewActivity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void afterTextChanged(Editable editable) {
        String str;
        CharSequence charSequence;
        String str2;
        String str3;
        String obj = this.et_search.getText().toString();
        this.tbl_detail.removeAllViews();
        this.tbl_header.removeAllViews();
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(-2, -2));
        TableRow tableRow2 = new TableRow(this);
        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
        TextView textView = new TextView(this);
        textView.setText("MANAGE");
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setPadding(20, 15, 20, 15);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView.setBackgroundResource(R.drawable.new_style1);
        TextView textView2 = new TextView(this);
        textView2.setText("SR");
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
        textView4.setText("IP");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        CharSequence charSequence2 = "********";
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(this);
        textView5.setText("VLAN");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        String str4 = obj;
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(this);
        textView6.setText("ELEMENT");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        String str5 = "BNG_NAME";
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(this);
        textView7.setText("MAKE");
        String str6 = "OLT_CHIPSET";
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        String str7 = "OUTER_VLAN";
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(this);
        textView8.setText("USERNAME");
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        String str8 = "MAKE";
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(this);
        textView9.setText("PASSWORD");
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        String str9 = "N/W_ELEMENT";
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        TextView textView10 = new TextView(this);
        textView10.setText("ENABLE PASSWORD");
        textView10.setTextColor(-1);
        textView10.setGravity(17);
        String str10 = "IP";
        textView10.setPadding(20, 15, 20, 15);
        textView10.setTypeface((Typeface) null, 1);
        textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView10.setBackgroundResource(R.drawable.new_style1);
        TextView textView11 = new TextView(this);
        textView11.setText("CHIPSET");
        textView11.setTextColor(-1);
        textView11.setGravity(17);
        String str11 = CodePackage.LOCATION;
        textView11.setPadding(20, 15, 20, 15);
        textView11.setTypeface((Typeface) null, 1);
        textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView11.setBackgroundResource(R.drawable.new_style1);
        TextView textView12 = new TextView(this);
        textView12.setText("BNG");
        textView12.setTextColor(-1);
        textView12.setGravity(17);
        textView12.setPadding(20, 15, 20, 15);
        textView12.setTypeface((Typeface) null, 1);
        textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView12.setBackgroundResource(R.drawable.new_style1);
        tableRow.addView(textView);
        tableRow2.addView(textView2);
        tableRow2.addView(textView3);
        tableRow2.addView(textView4);
        tableRow2.addView(textView5);
        tableRow2.addView(textView6);
        tableRow2.addView(textView7);
        tableRow2.addView(textView8);
        tableRow2.addView(textView9);
        tableRow2.addView(textView10);
        tableRow2.addView(textView11);
        tableRow2.addView(textView12);
        this.tbl_header.addView(tableRow);
        this.tbl_detail.addView(tableRow2);
        int i = 1;
        int i2 = 0;
        while (i2 < this.jsonArray.length()) {
            try {
                JSONObject jSONObject = this.jsonArray.getJSONObject(i2);
                String string = jSONObject.getString("CIRCLE");
                String string2 = jSONObject.getString("SSA");
                String str12 = str11;
                String string3 = jSONObject.getString(str12);
                String str13 = str10;
                String string4 = jSONObject.getString(str13);
                String str14 = str9;
                String string5 = jSONObject.getString(str14);
                String str15 = str8;
                String string6 = jSONObject.getString(str15);
                String str16 = str7;
                String string7 = jSONObject.getString(str16);
                int i3 = i2;
                jSONObject.getString("OLT_UID");
                jSONObject.getString("OLT_PWD");
                jSONObject.getString("OLT_EN_PWD");
                String str17 = str6;
                String string8 = jSONObject.getString(str17);
                String str18 = str17;
                String str19 = str5;
                String string9 = jSONObject.getString(str19);
                String str20 = str19;
                if (!string.toUpperCase().contains(str4.toUpperCase()) && !string2.toUpperCase().contains(str4.toUpperCase()) && !string3.toUpperCase().contains(str4.toUpperCase()) && !string4.toUpperCase().contains(str4.toUpperCase()) && !string5.toUpperCase().contains(str4.toUpperCase()) && !string6.toUpperCase().contains(str4.toUpperCase()) && !string7.toUpperCase().contains(str4.toUpperCase()) && !string9.toUpperCase().contains(str4.toUpperCase())) {
                    if (!string8.toUpperCase().contains(str4.toUpperCase())) {
                        str11 = str12;
                        str10 = str13;
                        str9 = str14;
                        str8 = str15;
                        charSequence = charSequence2;
                        str = str18;
                        str3 = str20;
                        str2 = str16;
                        i2 = i3 + 1;
                        str5 = str3;
                        str7 = str2;
                        charSequence2 = charSequence;
                        str6 = str;
                    }
                }
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(-1, (int) (((double) getResources().getDimension(R.dimen.mediumtext)) * 2.4d));
                TableRow tableRow3 = new TableRow(this);
                tableRow3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow3.setGravity(17);
                TableRow tableRow4 = new TableRow(this);
                tableRow4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
                tableRow4.setGravity(17);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setGravity(17);
                linearLayout.setOrientation(0);
                linearLayout.setPadding(20, 10, 20, 10);
                linearLayout.setBackgroundResource(R.drawable.style17);
                ImageView imageView = new ImageView(this);
                imageView.setPadding(5, 5, 5, 5);
                imageView.setClickable(true);
                imageView.setImageResource(R.drawable.ic_action_edit);
                imageView.setBackgroundResource(R.drawable.button01);
                linearLayout.addView(imageView);
                tableRow3.addView(linearLayout);
                TextView textView13 = new TextView(this);
                textView13.setLayoutParams(layoutParams);
                int i4 = i + 1;
                textView13.setText(Integer.toString(i));
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setPadding(20, 15, 20, 15);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView13.setBackgroundResource(R.drawable.style17);
                TextView textView14 = new TextView(this);
                textView14.setLayoutParams(layoutParams);
                textView14.setText(jSONObject.getString(str12).toUpperCase());
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                textView14.setPadding(20, 15, 20, 15);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView14.setBackgroundResource(R.drawable.style17);
                TextView textView15 = new TextView(this);
                textView15.setLayoutParams(layoutParams);
                textView15.setText(jSONObject.getString(str13));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                textView15.setPadding(20, 15, 20, 15);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView15.setBackgroundResource(R.drawable.style17);
                TextView textView16 = new TextView(this);
                textView16.setLayoutParams(layoutParams);
                textView16.setText(jSONObject.getString(str16));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                str11 = str12;
                textView16.setPadding(20, 15, 20, 15);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView16.setBackgroundResource(R.drawable.style17);
                TextView textView17 = new TextView(this);
                textView17.setLayoutParams(layoutParams);
                textView17.setText(jSONObject.getString(str14));
                textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView17.setGravity(17);
                str10 = str13;
                textView17.setPadding(20, 15, 20, 15);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView17.setBackgroundResource(R.drawable.style17);
                TextView textView18 = new TextView(this);
                textView18.setLayoutParams(layoutParams);
                textView18.setText(jSONObject.getString(str15));
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                str9 = str14;
                textView18.setPadding(20, 15, 20, 15);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView18.setBackgroundResource(R.drawable.style17);
                TextView textView19 = new TextView(this);
                textView19.setLayoutParams(layoutParams);
                CharSequence charSequence3 = charSequence2;
                textView19.setText(charSequence3);
                str8 = str15;
                textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView19.setGravity(17);
                str2 = str16;
                textView19.setPadding(20, 15, 20, 15);
                textView19.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView19.setBackgroundResource(R.drawable.style17);
                TextView textView20 = new TextView(this);
                textView20.setLayoutParams(layoutParams);
                textView20.setText(charSequence3);
                textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView20.setGravity(17);
                int i5 = i4;
                textView20.setPadding(20, 15, 20, 15);
                textView20.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView20.setBackgroundResource(R.drawable.style17);
                TextView textView21 = new TextView(this);
                textView21.setLayoutParams(layoutParams);
                textView21.setText(charSequence3);
                textView21.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView21.setGravity(17);
                charSequence = charSequence3;
                textView21.setPadding(20, 15, 20, 15);
                textView21.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView21.setBackgroundResource(R.drawable.style17);
                TextView textView22 = new TextView(this);
                textView22.setLayoutParams(layoutParams);
                String str21 = str18;
                TableRow tableRow5 = tableRow3;
                textView22.setText(jSONObject.getString(str21));
                textView22.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView22.setGravity(17);
                str = str21;
                textView22.setPadding(20, 15, 20, 15);
                textView22.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView22.setBackgroundResource(R.drawable.style17);
                TextView textView23 = new TextView(this);
                textView23.setLayoutParams(layoutParams);
                str3 = str20;
                textView23.setText(jSONObject.getString(str3).toUpperCase());
                textView23.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView23.setGravity(17);
                textView23.setPadding(20, 15, 20, 15);
                textView23.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView23.setBackgroundResource(R.drawable.style17);
                tableRow4.addView(textView13);
                tableRow4.addView(textView14);
                tableRow4.addView(textView15);
                tableRow4.addView(textView16);
                tableRow4.addView(textView17);
                tableRow4.addView(textView18);
                tableRow4.addView(textView19);
                tableRow4.addView(textView20);
                tableRow4.addView(textView21);
                tableRow4.addView(textView22);
                tableRow4.addView(textView23);
                this.tbl_header.addView(tableRow5);
                this.tbl_detail.addView(tableRow4);
                i = i5;
                i2 = i3 + 1;
                str5 = str3;
                str7 = str2;
                charSequence2 = charSequence;
                str6 = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
