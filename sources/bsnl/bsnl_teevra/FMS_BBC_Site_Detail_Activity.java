package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_BBC_Site_Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    public static final int LOCATION_PERMISSION_REQ = 1001;
    /* access modifiers changed from: private */
    public double Latitude;
    /* access modifiers changed from: private */
    public double Longitude;
    private String Pref_Access_Level;
    private String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_update;
    private CheckBox chk_bahead;
    private CheckBox chk_bbc;
    private CheckBox chk_oahead;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_comment;
    private EditText et_customerbase;
    private EditText et_olt_capacity;
    private EditText et_olt_humidity;
    private EditText et_olt_powerlevel;
    private EditText et_olt_temperature;
    private AlertDialog geo_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LocationRequest locationRequest;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private ArrayAdapter olt_backhaul_adapter;
    private ArrayList<String> olt_backhaul_list;
    private ArrayAdapter olt_make_adapter;
    private ArrayList<String> olt_make_list;
    private ArrayAdapter olt_port_adapter;
    private ArrayList<String> olt_port_list;
    private ArrayAdapter olt_type_adapter;
    private ArrayList<String> olt_type_list;
    private ArrayAdapter olt_uplink_adapter;
    private ArrayList<String> olt_uplink_list;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private RadioGroup radio_ac;
    private RadioButton radio_btn_ac;
    private RadioButton radio_btn_clean;
    private RadioButton radio_btn_install;
    private RadioButton radio_btn_marketing;
    private RadioButton radio_btn_olt_backup;
    private RadioButton radio_btn_pon_map;
    private RadioButton radio_btn_power;
    private RadioGroup radio_clean;
    private RadioGroup radio_install;
    private RadioGroup radio_marketing;
    private RadioGroup radio_olt_backup;
    private RadioGroup radio_pon_map;
    private RadioGroup radio_power;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private Spinner sp_olt_backhaul;
    private Spinner sp_olt_make;
    private Spinner sp_olt_port;
    private Spinner sp_olt_type;
    private Spinner sp_olt_uplink;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_comment_count;
    private TextView txt_header;
    /* access modifiers changed from: private */
    public TextView txt_lat;
    /* access modifiers changed from: private */
    public TextView txt_long;
    private TextView txt_olt_ip;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_site_detail_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Username = this.sharedPreferences.getString("fms_username_Key", (String) null);
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
        this.txt_lat = (TextView) findViewById(R.id.txt_lat);
        this.txt_long = (TextView) findViewById(R.id.txt_long);
        new HashMap();
        LocationRequest locationRequest2 = new LocationRequest();
        this.locationRequest = locationRequest2;
        locationRequest2.setInterval(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
        this.locationRequest.setFastestInterval(3000);
        this.locationRequest.setPriority(100);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.progress_dialog.show();
            LocationServices.getFusedLocationProviderClient((Activity) this).requestLocationUpdates(this.locationRequest, (LocationCallback) new LocationCallback() {
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LocationServices.getFusedLocationProviderClient((Activity) FMS_BBC_Site_Detail_Activity.this).removeLocationUpdates((LocationCallback) this);
                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                        int size = locationResult.getLocations().size() - 1;
                        double unused = FMS_BBC_Site_Detail_Activity.this.Latitude = locationResult.getLocations().get(size).getLatitude();
                        double unused2 = FMS_BBC_Site_Detail_Activity.this.Longitude = locationResult.getLocations().get(size).getLongitude();
                        FMS_BBC_Site_Detail_Activity.this.txt_lat.setText(Double.toString(locationResult.getLocations().get(size).getLatitude()));
                        FMS_BBC_Site_Detail_Activity.this.txt_long.setText(Double.toString(locationResult.getLocations().get(size).getLongitude()));
                        FMS_BBC_Site_Detail_Activity.this.progress_dialog.cancel();
                    }
                }
            }, Looper.getMainLooper());
        } else if (shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE"}, 1001);
        } else {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            View inflate3 = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate3.findViewById(R.id.txt_update);
            textView.setText("SETTINGS");
            builder3.setCancelable(false);
            builder3.setView(inflate3);
            AlertDialog create = builder3.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate3.findViewById(R.id.txt_error)).setText("Action Needs Access Of Device GPS");
            ((TextView) inflate3.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Site_Detail_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + FMS_BBC_Site_Detail_Activity.this.getPackageName()));
                    intent.addFlags(268435456);
                    intent.addFlags(BasicMeasure.EXACTLY);
                    intent.addFlags(8388608);
                    FMS_BBC_Site_Detail_Activity.this.startActivity(intent);
                    FMS_BBC_Site_Detail_Activity.this.info_dialog.cancel();
                }
            });
        }
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.txt_olt_ip = (TextView) findViewById(R.id.txt_olt_ip);
        this.sp_olt_make = (Spinner) findViewById(R.id.sp_olt_make);
        this.sp_olt_port = (Spinner) findViewById(R.id.sp_olt_port);
        this.sp_olt_type = (Spinner) findViewById(R.id.sp_olt_type);
        this.et_olt_capacity = (EditText) findViewById(R.id.et_olt_capacity);
        this.sp_olt_uplink = (Spinner) findViewById(R.id.sp_olt_uplink);
        this.sp_olt_backhaul = (Spinner) findViewById(R.id.sp_olt_backhaul);
        this.et_olt_powerlevel = (EditText) findViewById(R.id.et_olt_powerlevel);
        this.et_olt_humidity = (EditText) findViewById(R.id.et_olt_humidity);
        this.et_olt_temperature = (EditText) findViewById(R.id.et_olt_temperature);
        this.radio_ac = (RadioGroup) findViewById(R.id.radio_ac);
        this.radio_power = (RadioGroup) findViewById(R.id.radio_power);
        this.radio_clean = (RadioGroup) findViewById(R.id.radio_clean);
        this.radio_install = (RadioGroup) findViewById(R.id.radio_install);
        this.radio_olt_backup = (RadioGroup) findViewById(R.id.radio_olt_backup);
        this.radio_pon_map = (RadioGroup) findViewById(R.id.radio_pon_map);
        this.radio_marketing = (RadioGroup) findViewById(R.id.radio_marketing);
        this.et_customerbase = (EditText) findViewById(R.id.et_customerbase);
        this.txt_comment_count = (TextView) findViewById(R.id.txt_comment_count);
        EditText editText = (EditText) findViewById(R.id.et_comment);
        this.et_comment = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str = charSequence.length() + "/100";
                if (charSequence.length() < 90) {
                    FMS_BBC_Site_Detail_Activity.this.txt_comment_count.setTextColor(FMS_BBC_Site_Detail_Activity.this.getResources().getColor(R.color.colorGreen));
                } else {
                    FMS_BBC_Site_Detail_Activity.this.txt_comment_count.setTextColor(FMS_BBC_Site_Detail_Activity.this.getResources().getColor(R.color.colorRed));
                }
                FMS_BBC_Site_Detail_Activity.this.txt_comment_count.setText(str);
            }
        });
        this.chk_bbc = (CheckBox) findViewById(R.id.chk_bbc);
        this.chk_oahead = (CheckBox) findViewById(R.id.chk_oahead);
        this.chk_bahead = (CheckBox) findViewById(R.id.chk_bahead);
        TextView textView2 = (TextView) findViewById(R.id.btn_update);
        this.btn_update = textView2;
        textView2.setOnClickListener(this);
        ArrayList<String> arrayList = new ArrayList<>();
        this.olt_make_list = arrayList;
        arrayList.add("-- OLT-MAKE --");
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.olt_port_list = arrayList2;
        arrayList2.add("-- OLT-PORT --");
        ArrayList<String> arrayList3 = new ArrayList<>();
        this.olt_type_list = arrayList3;
        arrayList3.add("-- OLT-TYPE --");
        ArrayList<String> arrayList4 = new ArrayList<>();
        this.olt_uplink_list = arrayList4;
        arrayList4.add("-- OLT-UPLINK --");
        ArrayList<String> arrayList5 = new ArrayList<>();
        this.olt_backhaul_list = arrayList5;
        arrayList5.add("-- OLT-BACKHAUL --");
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("Response"));
            getIntent().getStringExtra("Olt_Latitude");
            getIntent().getStringExtra("Olt_Longitude");
            String stringExtra = getIntent().getStringExtra("Inspection_Period");
            String string = jSONObject.getString("OLT_IP");
            String string2 = jSONObject.getString("OLT_MAKE");
            String string3 = jSONObject.getString("OLT_PORT");
            String string4 = jSONObject.getString("OLT_TYPE");
            String string5 = jSONObject.getString("OLT_CAPACITY");
            String string6 = jSONObject.getString("OLT_UPLINK");
            String string7 = jSONObject.getString("OLT_BACKHAUL");
            String string8 = jSONObject.getString("OLT_POWER_LEVEL");
            String string9 = jSONObject.getString("HUMIDITY");
            String string10 = jSONObject.getString("TEMPERATURE");
            String string11 = jSONObject.getString("AC_UNIT");
            Object obj = "Y";
            String string12 = jSONObject.getString("POWER_BACKUP");
            String string13 = jSONObject.getString("CLEANLINESS");
            String string14 = jSONObject.getString("INSTALLED_AT");
            String string15 = jSONObject.getString("OLT_BACKUP");
            String string16 = jSONObject.getString("PON_ROUTEMAP");
            String string17 = jSONObject.getString("MARKETING");
            String string18 = jSONObject.getString("CUSTOMER_BASE");
            String string19 = jSONObject.getString("ONSERVATION");
            String string20 = jSONObject.getString("INSP_BBC");
            String string21 = jSONObject.getString("INSP_OAHEAD");
            String string22 = jSONObject.getString("INSP_BAHEAD");
            JSONArray jSONArray = jSONObject.getJSONArray("ARR_OLT_MAKE");
            String str = string11;
            JSONArray jSONArray2 = jSONObject.getJSONArray("ARR_OLT_PORT");
            String str2 = string10;
            JSONArray jSONArray3 = jSONObject.getJSONArray("ARR_OLT_TYPE");
            String str3 = string9;
            JSONArray jSONArray4 = jSONObject.getJSONArray("ARR_OLT_UPLINK");
            String str4 = string8;
            JSONArray jSONArray5 = jSONObject.getJSONArray("ARR_OLT_BACKHAUL");
            String str5 = string7;
            this.txt_header.setText("   (" + stringExtra + ")");
            this.txt_olt_ip.setText(string);
            for (int i = 0; i < jSONArray.length(); i++) {
                this.olt_make_list.add(jSONArray.getString(i));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.olt_make_list);
            this.olt_make_adapter = arrayAdapter;
            this.sp_olt_make.setAdapter(arrayAdapter);
            this.sp_olt_make.setSelection(this.olt_make_adapter.getPosition(string2), false);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                this.olt_port_list.add(jSONArray2.getString(i2));
            }
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_port_list);
            this.olt_port_adapter = arrayAdapter2;
            this.sp_olt_port.setAdapter(arrayAdapter2);
            this.sp_olt_port.setSelection(this.olt_port_adapter.getPosition(string3), false);
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                this.olt_type_list.add(jSONArray3.getString(i3));
            }
            ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_type_list);
            this.olt_type_adapter = arrayAdapter3;
            this.sp_olt_type.setAdapter(arrayAdapter3);
            this.sp_olt_type.setSelection(this.olt_type_adapter.getPosition(string4), false);
            this.et_olt_capacity.setText(string5);
            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                this.olt_uplink_list.add(jSONArray4.getString(i4));
            }
            ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_uplink_list);
            this.olt_uplink_adapter = arrayAdapter4;
            this.sp_olt_uplink.setAdapter(arrayAdapter4);
            this.sp_olt_uplink.setSelection(this.olt_uplink_adapter.getPosition(string6), false);
            for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                this.olt_backhaul_list.add(jSONArray5.getString(i5));
            }
            ArrayAdapter arrayAdapter5 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_backhaul_list);
            this.olt_backhaul_adapter = arrayAdapter5;
            this.sp_olt_backhaul.setAdapter(arrayAdapter5);
            this.sp_olt_backhaul.setSelection(this.olt_backhaul_adapter.getPosition(str5), false);
            this.et_olt_powerlevel.setText(str4);
            this.et_olt_humidity.setText(str3);
            this.et_olt_temperature.setText(str2);
            Object obj2 = obj;
            if (str.equals(obj2)) {
                this.radio_ac.check(R.id.radio_ac_avlb);
            } else {
                this.radio_ac.check(R.id.radio_ac_na);
            }
            if (string12.equals(obj2)) {
                this.radio_power.check(R.id.radio_power_avlb);
            } else {
                this.radio_power.check(R.id.radio_power_na);
            }
            if (string13.equals(obj2)) {
                this.radio_clean.check(R.id.radio_clean_avlb);
            } else {
                this.radio_clean.check(R.id.radio_clean_na);
            }
            String str6 = string14;
            if (str6.equals("BSNL Exg")) {
                this.radio_install.check(R.id.radio_install_bsnl);
            } else if (str6.equals("BTS")) {
                this.radio_install.check(R.id.radio_install_bts);
            } else if (str6.equals("TIP Premises")) {
                this.radio_install.check(R.id.radio_install_tip);
            }
            if (string15.equals(obj2)) {
                this.radio_olt_backup.check(R.id.radio_olt_backup_avl);
            } else {
                this.radio_olt_backup.check(R.id.radio_olt_backup_never);
            }
            if (string16.equals(obj2)) {
                this.radio_pon_map.check(R.id.radio_pon_map_avl);
            } else {
                this.radio_pon_map.check(R.id.radio_pon_map_na);
            }
            String str7 = string17;
            if (str7.equals("Daily")) {
                this.radio_marketing.check(R.id.radio_marketing_daily);
            } else if (str7.equals("Weekly")) {
                this.radio_marketing.check(R.id.radio_marketing_weekly);
            } else if (str7.equals("Biweekly")) {
                this.radio_marketing.check(R.id.radio_marketing_biweekly);
            }
            this.et_customerbase.setText(string18);
            this.et_comment.setText(string19);
            if (string20.equals(obj2)) {
                this.chk_bbc.setChecked(true);
                this.chk_bbc.setEnabled(false);
            } else {
                this.chk_bbc.setChecked(false);
            }
            if (string21.equals(obj2)) {
                this.chk_oahead.setChecked(true);
                this.chk_oahead.setEnabled(false);
            } else {
                this.chk_oahead.setChecked(false);
            }
            if (string22.equals(obj2)) {
                this.chk_bahead.setChecked(true);
                this.chk_bahead.setEnabled(false);
                return;
            }
            this.chk_bahead.setChecked(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            String stringExtra = getIntent().getStringExtra("Inspection_Period");
            String format = new SimpleDateFormat("MMM yyyy").format(Calendar.getInstance().getTime());
            Toast.makeText(getApplicationContext(), format, 1).show();
            if (format.equals(stringExtra)) {
                String stringExtra2 = getIntent().getStringExtra("Olt_Latitude");
                String stringExtra3 = getIntent().getStringExtra("Olt_Longitude");
                String trim = this.txt_lat.getText().toString().trim();
                String trim2 = this.txt_long.getText().toString().trim();
                Location location = new Location("locationA");
                location.setLatitude(Double.valueOf(stringExtra2).doubleValue());
                location.setLongitude(Double.valueOf(stringExtra3).doubleValue());
                Location location2 = new Location("locationB");
                location2.setLatitude(Double.valueOf(trim).doubleValue());
                location2.setLongitude(Double.valueOf(trim2).doubleValue());
                double round = ((double) (Math.round(location.distanceTo(location2) * 100.0f) / 1000)) / 100.0d;
                if (round > 0.1d) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                    TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                    textView.setText("OK");
                    textView.setBackgroundResource(0);
                    textView.setGravity(5);
                    textView.setPadding(0, 0, 80, 0);
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog create = builder.create();
                    this.info_dialog = create;
                    create.show();
                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("\nYou Are " + round + " Km Away From Olt Location\nKindly Submit The Site Inspection Report From Olt Location");
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_BBC_Site_Detail_Activity.this.info_dialog.cancel();
                        }
                    });
                    return;
                }
                String trim3 = this.txt_olt_ip.getText().toString().trim();
                String trim4 = this.sp_olt_make.getSelectedItem().toString().trim();
                String trim5 = this.sp_olt_port.getSelectedItem().toString().trim();
                String trim6 = this.sp_olt_type.getSelectedItem().toString().trim();
                String trim7 = this.et_olt_capacity.getText().toString().trim();
                String trim8 = this.sp_olt_uplink.getSelectedItem().toString().trim();
                String trim9 = this.sp_olt_backhaul.getSelectedItem().toString().trim();
                String trim10 = this.et_olt_powerlevel.getText().toString().trim();
                String trim11 = this.et_olt_humidity.getText().toString().trim();
                String trim12 = this.et_olt_temperature.getText().toString().trim();
                RadioButton radioButton = (RadioButton) findViewById(this.radio_ac.getCheckedRadioButtonId());
                this.radio_btn_ac = radioButton;
                String str = radioButton.getText().toString().trim().equals("Available") ? "Y" : "N";
                RadioButton radioButton2 = (RadioButton) findViewById(this.radio_power.getCheckedRadioButtonId());
                this.radio_btn_power = radioButton2;
                String str2 = radioButton2.getText().toString().trim().equals("Available") ? "Y" : "N";
                RadioButton radioButton3 = (RadioButton) findViewById(this.radio_clean.getCheckedRadioButtonId());
                this.radio_btn_clean = radioButton3;
                String str3 = radioButton3.getText().toString().trim().equals("Available") ? "Y" : "N";
                RadioButton radioButton4 = (RadioButton) findViewById(this.radio_install.getCheckedRadioButtonId());
                this.radio_btn_install = radioButton4;
                String trim13 = radioButton4.getText().toString().trim();
                RadioButton radioButton5 = (RadioButton) findViewById(this.radio_olt_backup.getCheckedRadioButtonId());
                this.radio_btn_olt_backup = radioButton5;
                String str4 = radioButton5.getText().toString().trim().equals("Periodic") ? "Y" : "N";
                RadioButton radioButton6 = (RadioButton) findViewById(this.radio_pon_map.getCheckedRadioButtonId());
                this.radio_btn_pon_map = radioButton6;
                String str5 = radioButton6.getText().toString().trim().equals("Available") ? "Y" : "N";
                RadioButton radioButton7 = (RadioButton) findViewById(this.radio_marketing.getCheckedRadioButtonId());
                this.radio_btn_marketing = radioButton7;
                String trim14 = radioButton7.getText().toString().trim();
                String trim15 = this.et_customerbase.getText().toString().trim();
                String trim16 = this.et_comment.getText().toString().trim();
                String str6 = this.chk_bbc.isChecked() ? "Y" : "N";
                String str7 = this.chk_oahead.isChecked() ? "Y" : "N";
                String str8 = this.chk_bahead.isChecked() ? "Y" : "N";
                if (trim4.equals("-- OLT-MAKE --")) {
                    new AlertHelperclass().ntoast("Please Select A Valid Olt Make", getApplicationContext());
                } else if (trim5.equals("-- OLT-PORT --")) {
                    new AlertHelperclass().ntoast("Please Select A Valid Olt Port", getApplicationContext());
                } else if (trim6.equals("-- OLT-TYPE --")) {
                    new AlertHelperclass().ntoast("Please Select A Valid Olt Type", getApplicationContext());
                } else if (trim7.isEmpty()) {
                    new AlertHelperclass().ntoast("Please Enter Olt Equipped Capacity", getApplicationContext());
                } else if (trim8.equals("-- OLT-UPLINK --")) {
                    new AlertHelperclass().ntoast("Please Select A Valid Olt Uplink", getApplicationContext());
                } else if (trim9.equals("-- OLT-BACKHAUL --")) {
                    new AlertHelperclass().ntoast("Please Select A Valid Olt BackHaul", getApplicationContext());
                } else if (trim10.isEmpty()) {
                    new AlertHelperclass().ntoast("Please Enter Olt Uplink Optical Power Level", getApplicationContext());
                } else if (trim11.isEmpty()) {
                    new AlertHelperclass().ntoast("Please Enter Humidity Of Olt Room", getApplicationContext());
                } else if (trim12.isEmpty()) {
                    new AlertHelperclass().ntoast("Please Enter Temperature Of Olt Room", getApplicationContext());
                } else if (trim15.isEmpty()) {
                    new AlertHelperclass().ntoast("Please Enter Customer Count As On Date", getApplicationContext());
                } else if (!str6.equals("N") || !str7.equals("N") || !str8.equals("N")) {
                    this.progress_dialog.show();
                    AnonymousClass9 r29 = r0;
                    String str9 = str7;
                    RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                    AnonymousClass7 r4 = r2;
                    AnonymousClass7 r2 = new Response.Listener<String>() {
                        public void onResponse(String str) {
                            FMS_BBC_Site_Detail_Activity.this.progress_dialog.cancel();
                            new AlertHelperclass().ptoast("Site Inspection Report Is Successfully Updated", FMS_BBC_Site_Detail_Activity.this.getApplicationContext());
                        }
                    };
                    String str10 = str6;
                    AnonymousClass8 r5 = r2;
                    AnonymousClass8 r22 = new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Site_Detail_Activity.this.getApplicationContext());
                            FMS_BBC_Site_Detail_Activity.this.progress_dialog.cancel();
                            FMS_BBC_Site_Detail_Activity.this.txt_alert.setText(onErrorResponse);
                            FMS_BBC_Site_Detail_Activity.this.error_dialog.show();
                        }
                    };
                    AnonymousClass9 r0 = new StringRequest(this, 1, getString(R.string.serverip) + "fms/bbc_insp_upload.php", r4, r5, trim3, stringExtra, trim4, trim5, trim6, trim7, trim8, trim9, trim10, trim11, trim12, str, str2, str3, trim13, str4, str5, trim14, trim15, trim16, str10, str9, str8) {
                        final /* synthetic */ FMS_BBC_Site_Detail_Activity this$0;
                        final /* synthetic */ String val$Customer_count;
                        final /* synthetic */ String val$Humidity;
                        final /* synthetic */ String val$Inspection_Period;
                        final /* synthetic */ String val$Observation;
                        final /* synthetic */ String val$Olt_backhaul;
                        final /* synthetic */ String val$Olt_capacity;
                        final /* synthetic */ String val$Olt_ip;
                        final /* synthetic */ String val$Olt_make;
                        final /* synthetic */ String val$Olt_port;
                        final /* synthetic */ String val$Olt_type;
                        final /* synthetic */ String val$Olt_uplink;
                        final /* synthetic */ String val$Power_level;
                        final /* synthetic */ String val$Radio_install;
                        final /* synthetic */ String val$Radio_marketing;
                        final /* synthetic */ String val$Temperature;
                        final /* synthetic */ String val$finalInsp_bahead;
                        final /* synthetic */ String val$finalInsp_bbc1;
                        final /* synthetic */ String val$finalInsp_oahead;
                        final /* synthetic */ String val$finalRadio_ac;
                        final /* synthetic */ String val$finalRadio_clean;
                        final /* synthetic */ String val$finalRadio_olt_backup;
                        final /* synthetic */ String val$finalRadio_pon_map;
                        final /* synthetic */ String val$finalRadio_power;

                        {
                            this.this$0 = r6;
                            this.val$Olt_ip = r11;
                            this.val$Inspection_Period = r12;
                            this.val$Olt_make = r13;
                            this.val$Olt_port = r14;
                            this.val$Olt_type = r15;
                            this.val$Olt_capacity = r16;
                            this.val$Olt_uplink = r17;
                            this.val$Olt_backhaul = r18;
                            this.val$Power_level = r19;
                            this.val$Humidity = r20;
                            this.val$Temperature = r21;
                            this.val$finalRadio_ac = r22;
                            this.val$finalRadio_power = r23;
                            this.val$finalRadio_clean = r24;
                            this.val$Radio_install = r25;
                            this.val$finalRadio_olt_backup = r26;
                            this.val$finalRadio_pon_map = r27;
                            this.val$Radio_marketing = r28;
                            this.val$Customer_count = r29;
                            this.val$Observation = r30;
                            this.val$finalInsp_bbc1 = r31;
                            this.val$finalInsp_oahead = r32;
                            this.val$finalInsp_bahead = r33;
                            int i = r7;
                            String str = r8;
                            Response.Listener listener = r9;
                            Response.ErrorListener errorListener = r10;
                        }

                        /* access modifiers changed from: protected */
                        public Map<String, String> getParams() throws AuthFailureError {
                            HashMap hashMap = new HashMap();
                            hashMap.put("username", this.this$0.Pref_Username);
                            hashMap.put("random_key", this.this$0.Pref_Randkey);
                            hashMap.put("device_id", this.this$0.androidId);
                            hashMap.put("fms_username", this.this$0.Pref_Fms_Username);
                            hashMap.put("olt_ip", this.val$Olt_ip);
                            hashMap.put("inspection_period", this.val$Inspection_Period);
                            hashMap.put("olt_make", this.val$Olt_make);
                            hashMap.put("olt_ports", this.val$Olt_port);
                            hashMap.put("olt_type", this.val$Olt_type);
                            hashMap.put("olt_capacity", this.val$Olt_capacity);
                            hashMap.put("olt_uplink", this.val$Olt_uplink);
                            hashMap.put("olt_backhaul", this.val$Olt_backhaul);
                            hashMap.put("olt_power_level", this.val$Power_level);
                            hashMap.put("olt_humidity", this.val$Humidity);
                            hashMap.put("olt_temperature", this.val$Temperature);
                            hashMap.put("ac_unit", this.val$finalRadio_ac);
                            hashMap.put("power_backup", this.val$finalRadio_power);
                            hashMap.put("cleanliness", this.val$finalRadio_clean);
                            hashMap.put("installed_at", this.val$Radio_install);
                            hashMap.put("olt_config_backup", this.val$finalRadio_olt_backup);
                            hashMap.put("pon_route_map", this.val$finalRadio_pon_map);
                            hashMap.put("marketing", this.val$Radio_marketing);
                            hashMap.put("customer_count", this.val$Customer_count);
                            hashMap.put("observation", this.val$Observation);
                            hashMap.put("insp_bbc", this.val$finalInsp_bbc1);
                            hashMap.put("insp_oahead", this.val$finalInsp_oahead);
                            hashMap.put("insp_bahead", this.val$finalInsp_bahead);
                            return hashMap;
                        }
                    };
                    AnonymousClass9 r1 = r29;
                    r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                    newRequestQueue.add(r1);
                } else {
                    new AlertHelperclass().ntoast("Please Select The Appropriate Inspected BY", getApplicationContext());
                }
            } else {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                View inflate2 = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.txt_update);
                textView2.setText("OK");
                textView2.setBackgroundResource(0);
                textView2.setGravity(5);
                textView2.setPadding(0, 0, 80, 0);
                builder2.setCancelable(false);
                builder2.setView(inflate2);
                AlertDialog create2 = builder2.create();
                this.info_dialog = create2;
                create2.show();
                ((TextView) inflate2.findViewById(R.id.txt_alert)).setText("INFO");
                ((TextView) inflate2.findViewById(R.id.txt_error)).setText("\nYou Can Only Submit SIte Inspection Report For Current Month (" + format + ")");
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_BBC_Site_Detail_Activity.this.info_dialog.cancel();
                    }
                });
            }
        }
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
                    FMS_BBC_Site_Detail_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Site_Detail_Activity fMS_BBC_Site_Detail_Activity = FMS_BBC_Site_Detail_Activity.this;
                    fMS_BBC_Site_Detail_Activity.editor = fMS_BBC_Site_Detail_Activity.sharedPreferences.edit();
                    FMS_BBC_Site_Detail_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_Site_Detail_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_Site_Detail_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_Site_Detail_Activity.this.startActivity(intent);
                    FMS_BBC_Site_Detail_Activity.this.finish();
                    FMS_BBC_Site_Detail_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            Intent intent = new Intent(this, FMS_BBC_Site_Insp_Activity.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else {
            LocationRequest locationRequest2 = new LocationRequest();
            this.locationRequest = locationRequest2;
            locationRequest2.setInterval(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
            this.locationRequest.setFastestInterval(3000);
            this.locationRequest.setPriority(100);
            this.progress_dialog.show();
            LocationServices.getFusedLocationProviderClient((Activity) this).requestLocationUpdates(this.locationRequest, (LocationCallback) new LocationCallback() {
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LocationServices.getFusedLocationProviderClient((Activity) FMS_BBC_Site_Detail_Activity.this).removeLocationUpdates((LocationCallback) this);
                    if (locationResult != null && locationResult.getLocations().size() > 0) {
                        int size = locationResult.getLocations().size() - 1;
                        double unused = FMS_BBC_Site_Detail_Activity.this.Latitude = locationResult.getLocations().get(size).getLatitude();
                        double unused2 = FMS_BBC_Site_Detail_Activity.this.Longitude = locationResult.getLocations().get(size).getLongitude();
                        FMS_BBC_Site_Detail_Activity.this.txt_lat.setText(Double.toString(locationResult.getLocations().get(size).getLatitude()));
                        FMS_BBC_Site_Detail_Activity.this.txt_long.setText(Double.toString(locationResult.getLocations().get(size).getLongitude()));
                        FMS_BBC_Site_Detail_Activity.this.progress_dialog.cancel();
                    }
                }
            }, Looper.getMainLooper());
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
