package bsnl.bsnl_teevra;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import bsnl.bsnl_teevra.utils.CustomAnimationUtils;
import bsnl.bsnl_teevra.utils.PermissionManager;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.stats.CodePackage;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yalantis.ucrop.UCrop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_BBC_SiteDetail_Activity extends AppCompatActivity implements View.OnClickListener, PermissionManager.PermissionCallback {
    public static final int CAMERA_REQUEST_CODE = 2000;
    private static final long LOCATION_TIMEOUT_MS = 30000;
    private static final int REQUEST_CHECK_SETTINGS = 301;
    private static final int UCROP_REQUEST_CODE = 69;
    private String CurrentMonth;
    private String InspMonth;
    private double Latitude;
    private double Longitude;
    private String OLT_IP;
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_update;
    private CancellationTokenSource cancellationTokenSource;
    private CheckBox chk_bahead;
    private CheckBox chk_bbc;
    private CheckBox chk_oahead;
    private String currentDate;
    SharedPreferences.Editor editor;
    private AlertDialog.Builder error_builder;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_comment;
    private EditText et_customerbase;
    private EditText et_olt_capacity;
    private EditText et_olt_humidity;
    private EditText et_olt_powerlevel;
    private EditText et_olt_temperature;
    private File file_profile_pic;
    private File file_temp;
    private FusedLocationProviderClient fusedLocationClient;
    private AlertDialog geo_dialog;
    /* access modifiers changed from: private */
    public String img;
    private ImageView img_capture;
    private ImageView img_site;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_caption;
    private RelativeLayout lay_site;
    private RelativeLayout lay_site_main;
    private LocationCallback locationCallback;
    private LocationManager locationManager;
    private LocationRequest locationRequest;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    private Uri mUri;
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
    private PermissionManager permissionManager_camera;
    private PermissionManager permissionManager_location;
    private Uri photoURI;
    private AlertDialog.Builder progress_builder;
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
    private Handler timeoutHandler;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_comment_count;
    private TextView txt_firm_name;
    private TextView txt_geocordinates;
    private TextView txt_header;
    private TextView txt_inspBy;
    private TextView txt_lat;
    private TextView txt_long;
    private TextView txt_olt_ip;
    private TextView txt_time;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_sitedetail_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
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
        this.progress_builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        this.progress_builder.setCancelable(false);
        this.progress_builder.setView(inflate);
        this.progress_dialog = this.progress_builder.create();
        this.error_builder = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        this.error_builder.setCancelable(false);
        this.error_builder.setView(inflate2);
        this.error_dialog = this.error_builder.create();
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar instance = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(timeZone);
        this.CurrentMonth = simpleDateFormat.format(instance.getTime());
        this.txt_inspBy = (TextView) findViewById(R.id.txt_inspBy);
        this.txt_lat = (TextView) findViewById(R.id.txt_lat);
        this.txt_long = (TextView) findViewById(R.id.txt_long);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.lay_site_main = (RelativeLayout) findViewById(R.id.lay_site_main);
        this.lay_site = (RelativeLayout) findViewById(R.id.lay_site);
        this.img_site = (ImageView) findViewById(R.id.img_site);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_caption);
        this.lay_caption = linearLayout;
        linearLayout.setVisibility(8);
        this.img_capture = (ImageView) findViewById(R.id.img_capture);
        this.file_temp = new File(getExternalCacheDir(), "site_temp.jpg");
        this.txt_time = (TextView) findViewById(R.id.txt_time);
        this.txt_geocordinates = (TextView) findViewById(R.id.txt_geocordinates);
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
        this.chk_bbc = (CheckBox) findViewById(R.id.chk_bbc);
        this.chk_oahead = (CheckBox) findViewById(R.id.chk_oahead);
        this.chk_bahead = (CheckBox) findViewById(R.id.chk_bahead);
        this.btn_update = (TextView) findViewById(R.id.btn_update);
        this.txt_comment_count = (TextView) findViewById(R.id.txt_comment_count);
        this.et_comment = (EditText) findViewById(R.id.et_comment);
        this.lay_site_main.setLayoutParams(new LinearLayout.LayoutParams(-1, (i * 10) / 16));
        this.et_comment.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str = charSequence.length() + "/100";
                if (charSequence.length() < 90) {
                    FMS_BBC_SiteDetail_Activity.this.txt_comment_count.setTextColor(FMS_BBC_SiteDetail_Activity.this.getResources().getColor(R.color.colorGreen));
                } else {
                    FMS_BBC_SiteDetail_Activity.this.txt_comment_count.setTextColor(FMS_BBC_SiteDetail_Activity.this.getResources().getColor(R.color.colorRed));
                }
                FMS_BBC_SiteDetail_Activity.this.txt_comment_count.setText(str);
            }
        });
        this.btn_update.setOnClickListener(this);
        this.img_capture.setOnClickListener(this);
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
        this.permissionManager_location = new PermissionManager(this, 101, CodePackage.LOCATION, this);
        this.permissionManager_camera = new PermissionManager(this, 102, "CAMERA", this);
        this.permissionManager_location.setPermissions("android.permission.ACCESS_FINE_LOCATION");
        this.permissionManager_camera.setPermissions("android.permission.CAMERA");
        this.locationManager = (LocationManager) getSystemService("location");
        this.currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        this.timeoutHandler = new Handler(Looper.getMainLooper());
        try {
            JSONObject jSONObject = new JSONObject(getIntent().getStringExtra("Response"));
            getIntent().getStringExtra("Olt_Latitude");
            getIntent().getStringExtra("Olt_Longitude");
            String stringExtra = getIntent().getStringExtra("Inspection_Period");
            this.InspMonth = stringExtra;
            this.OLT_IP = jSONObject.getString("OLT_IP");
            String string = jSONObject.getString("OLT_MAKE");
            String string2 = jSONObject.getString("OLT_PORT");
            String string3 = jSONObject.getString("OLT_TYPE");
            String string4 = jSONObject.getString("OLT_CAPACITY");
            String string5 = jSONObject.getString("OLT_UPLINK");
            String string6 = jSONObject.getString("OLT_BACKHAUL");
            String string7 = jSONObject.getString("OLT_POWER_LEVEL");
            String string8 = jSONObject.getString("HUMIDITY");
            String string9 = jSONObject.getString("TEMPERATURE");
            String string10 = jSONObject.getString("AC_UNIT");
            Object obj = "Y";
            String string11 = jSONObject.getString("POWER_BACKUP");
            String string12 = jSONObject.getString("CLEANLINESS");
            String string13 = jSONObject.getString("INSTALLED_AT");
            String string14 = jSONObject.getString("OLT_BACKUP");
            String string15 = jSONObject.getString("PON_ROUTEMAP");
            String string16 = jSONObject.getString("MARKETING");
            String string17 = jSONObject.getString("CUSTOMER_BASE");
            String string18 = jSONObject.getString("ONSERVATION");
            String string19 = jSONObject.getString("INSP_BBC");
            String string20 = jSONObject.getString("INSP_OAHEAD");
            String string21 = jSONObject.getString("INSP_BAHEAD");
            String string22 = jSONObject.getString("INSP_BY");
            String str = string10;
            JSONArray jSONArray = jSONObject.getJSONArray("ARR_OLT_MAKE");
            String str2 = string9;
            JSONArray jSONArray2 = jSONObject.getJSONArray("ARR_OLT_PORT");
            String str3 = string8;
            JSONArray jSONArray3 = jSONObject.getJSONArray("ARR_OLT_TYPE");
            String str4 = string7;
            JSONArray jSONArray4 = jSONObject.getJSONArray("ARR_OLT_UPLINK");
            String str5 = string6;
            JSONArray jSONArray5 = jSONObject.getJSONArray("ARR_OLT_BACKHAUL");
            String string23 = jSONObject.getString("IMG");
            this.txt_header.setText("   (" + stringExtra + ")");
            this.txt_inspBy.setText(Html.fromHtml("Olt Inspected By : <b>" + string22 + "</b>", 0));
            this.txt_olt_ip.setText(this.OLT_IP);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.olt_make_list.add(jSONArray.getString(i2));
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item, this.olt_make_list);
            this.olt_make_adapter = arrayAdapter;
            this.sp_olt_make.setAdapter(arrayAdapter);
            this.sp_olt_make.setSelection(this.olt_make_adapter.getPosition(string), false);
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                this.olt_port_list.add(jSONArray2.getString(i3));
            }
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_port_list);
            this.olt_port_adapter = arrayAdapter2;
            this.sp_olt_port.setAdapter(arrayAdapter2);
            this.sp_olt_port.setSelection(this.olt_port_adapter.getPosition(string2), false);
            for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                this.olt_type_list.add(jSONArray3.getString(i4));
            }
            ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_type_list);
            this.olt_type_adapter = arrayAdapter3;
            this.sp_olt_type.setAdapter(arrayAdapter3);
            this.sp_olt_type.setSelection(this.olt_type_adapter.getPosition(string3), false);
            this.et_olt_capacity.setText(string4);
            for (int i5 = 0; i5 < jSONArray4.length(); i5++) {
                this.olt_uplink_list.add(jSONArray4.getString(i5));
            }
            ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, R.layout.spinner_item, this.olt_uplink_list);
            this.olt_uplink_adapter = arrayAdapter4;
            this.sp_olt_uplink.setAdapter(arrayAdapter4);
            this.sp_olt_uplink.setSelection(this.olt_uplink_adapter.getPosition(string5), false);
            int i6 = 0;
            while (i6 < jSONArray5.length()) {
                JSONArray jSONArray6 = jSONArray5;
                this.olt_backhaul_list.add(jSONArray6.getString(i6));
                i6++;
                jSONArray5 = jSONArray6;
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
            if (string11.equals(obj2)) {
                this.radio_power.check(R.id.radio_power_avlb);
            } else {
                this.radio_power.check(R.id.radio_power_na);
            }
            if (string12.equals(obj2)) {
                this.radio_clean.check(R.id.radio_clean_avlb);
            } else {
                this.radio_clean.check(R.id.radio_clean_na);
            }
            String str6 = string13;
            if (str6.equals("BSNL Exg")) {
                this.radio_install.check(R.id.radio_install_bsnl);
            } else if (str6.equals("BTS")) {
                this.radio_install.check(R.id.radio_install_bts);
            } else if (str6.equals("TIP Premises")) {
                this.radio_install.check(R.id.radio_install_tip);
            }
            if (string14.equals(obj2)) {
                this.radio_olt_backup.check(R.id.radio_olt_backup_avl);
            } else {
                this.radio_olt_backup.check(R.id.radio_olt_backup_never);
            }
            if (string15.equals(obj2)) {
                this.radio_pon_map.check(R.id.radio_pon_map_avl);
            } else {
                this.radio_pon_map.check(R.id.radio_pon_map_na);
            }
            String str7 = string16;
            if (str7.equals("Daily")) {
                this.radio_marketing.check(R.id.radio_marketing_daily);
            } else if (str7.equals("Weekly")) {
                this.radio_marketing.check(R.id.radio_marketing_weekly);
            } else if (str7.equals("Biweekly")) {
                this.radio_marketing.check(R.id.radio_marketing_biweekly);
            }
            this.et_customerbase.setText(string17);
            this.et_comment.setText(string18);
            if (string23 != null && !string23.isEmpty()) {
                String str8 = string23;
                if (!str8.equals("null")) {
                    String str9 = getString(R.string.serverip) + str8;
                    Picasso.get().invalidate(str9);
                    if (this.CurrentMonth.equals(this.InspMonth)) {
                        Picasso.get().invalidate(str9);
                        Picasso.get().load(str9).error((int) R.drawable.ic_new_site).placeholder((int) R.drawable.ic_new_site).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, new NetworkPolicy[0]).transform((Transformation) new RoundedTransformation((int) getResources().getDimension(R.dimen.smallpad), 0)).into(this.img_site);
                        this.img_site.setTag("db");
                    } else {
                        Picasso.get().invalidate(str9);
                        Picasso.get().load(str9).error((int) R.drawable.ic_new_site).placeholder((int) R.drawable.ic_new_site).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, new NetworkPolicy[0]).transform((Transformation) new RoundedTransformation((int) getResources().getDimension(R.dimen.smallpad), 0)).into(this.img_site);
                        this.img_site.setTag("db");
                    }
                }
            }
            if (string19.equals(obj2)) {
                this.chk_bbc.setChecked(true);
                this.chk_bbc.setEnabled(false);
            } else {
                this.chk_bbc.setChecked(false);
            }
            if (string20.equals(obj2)) {
                this.chk_oahead.setChecked(true);
                this.chk_oahead.setEnabled(false);
            } else {
                this.chk_oahead.setChecked(false);
            }
            if (string21.equals(obj2)) {
                this.chk_bahead.setChecked(true);
                this.chk_bahead.setEnabled(false);
            } else {
                this.chk_bahead.setChecked(false);
            }
            checkAndRequestLocationPermission();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            Toast.makeText(getApplicationContext(), this.CurrentMonth, 1).show();
            if (this.CurrentMonth.contains(this.InspMonth)) {
                String stringExtra = getIntent().getStringExtra("Olt_Latitude");
                String stringExtra2 = getIntent().getStringExtra("Olt_Longitude");
                String trim = this.txt_lat.getText().toString().trim();
                String trim2 = this.txt_long.getText().toString().trim();
                if (trim.equals("--") || trim2.equals("--")) {
                    checkAndRequestLocationPermission();
                    return;
                }
                Location location = new Location("locationA");
                location.setLatitude(Double.valueOf(stringExtra).doubleValue());
                location.setLongitude(Double.valueOf(stringExtra2).doubleValue());
                Location location2 = new Location("locationB");
                location2.setLatitude(Double.valueOf(trim).doubleValue());
                location2.setLongitude(Double.valueOf(trim2).doubleValue());
                double round = ((double) (Math.round(location.distanceTo(location2) * 100.0f) / 1000)) / 100.0d;
                if (round > 1.0d) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog create = builder.create();
                    this.info_dialog = create;
                    create.show();
                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("<br>You Are <b>" + round + "</b> Km Away From Actual Olt Location<br><br>Do You still Want To submit??"));
                    ((TextView) inflate.findViewById(R.id.txt_update)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_BBC_SiteDetail_Activity.this.info_dialog.cancel();
                            FMS_BBC_SiteDetail_Activity.this.site_inspection_upload();
                        }
                    });
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_BBC_SiteDetail_Activity.this.info_dialog.cancel();
                        }
                    });
                    return;
                }
                site_inspection_upload();
                return;
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            View inflate2 = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
            TextView textView = (TextView) inflate2.findViewById(R.id.txt_update);
            textView.setText("OK");
            textView.setBackgroundResource(0);
            textView.setGravity(5);
            textView.setPadding(0, 0, 80, 0);
            builder2.setCancelable(false);
            builder2.setView(inflate2);
            AlertDialog create2 = builder2.create();
            this.info_dialog = create2;
            create2.show();
            ((TextView) inflate2.findViewById(R.id.txt_alert)).setText("INFO");
            ((TextView) inflate2.findViewById(R.id.txt_error)).setText("\nYou Can Only Submit Site Inspection Report For Current Month (" + this.CurrentMonth + ")");
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_SiteDetail_Activity.this.info_dialog.cancel();
                }
            });
        } else if (view.getId() == R.id.img_capture && this.CurrentMonth.contains(this.InspMonth)) {
            checkAndRequestCameraPermission();
        }
    }

    /* access modifiers changed from: private */
    public void site_inspection_upload() {
        String stringExtra = getIntent().getStringExtra("Inspection_Period");
        String trim = this.txt_olt_ip.getText().toString().trim();
        String trim2 = this.sp_olt_make.getSelectedItem().toString().trim();
        String trim3 = this.sp_olt_port.getSelectedItem().toString().trim();
        String trim4 = this.sp_olt_type.getSelectedItem().toString().trim();
        String trim5 = this.et_olt_capacity.getText().toString().trim();
        String trim6 = this.sp_olt_uplink.getSelectedItem().toString().trim();
        String trim7 = this.sp_olt_backhaul.getSelectedItem().toString().trim();
        String trim8 = this.et_olt_powerlevel.getText().toString().trim();
        String trim9 = this.et_olt_humidity.getText().toString().trim();
        String trim10 = this.et_olt_temperature.getText().toString().trim();
        String trim11 = this.txt_lat.getText().toString().trim();
        String trim12 = this.txt_long.getText().toString().trim();
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
        if (trim2.equals("-- OLT-MAKE --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Olt Make", getApplicationContext());
        } else if (trim3.equals("-- OLT-PORT --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Olt Port", getApplicationContext());
        } else if (trim4.equals("-- OLT-TYPE --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Olt Type", getApplicationContext());
        } else if (trim5.isEmpty()) {
            new AlertHelperclass().ntoast("Please Enter Olt Equipped Capacity", getApplicationContext());
        } else if (trim6.equals("-- OLT-UPLINK --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Olt Uplink", getApplicationContext());
        } else if (trim7.equals("-- OLT-BACKHAUL --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Olt BackHaul", getApplicationContext());
        } else if (trim8.isEmpty()) {
            new AlertHelperclass().ntoast("Please Enter Olt Uplink Optical Power Level", getApplicationContext());
        } else if (trim9.isEmpty()) {
            new AlertHelperclass().ntoast("Please Enter Humidity Of Olt Room", getApplicationContext());
        } else if (trim10.isEmpty()) {
            new AlertHelperclass().ntoast("Please Enter Temperature Of Olt Room", getApplicationContext());
        } else if (trim15.isEmpty()) {
            new AlertHelperclass().ntoast("Please Enter Customer Count As On Date", getApplicationContext());
        } else if (str6.equals("N") && str7.equals("N") && str8.equals("N")) {
            new AlertHelperclass().ntoast("Please Select The Appropriate Inspected BY", getApplicationContext());
        } else if (this.img_site.getTag().toString().equals("plain")) {
            new AlertHelperclass().ntoast("Please Upoad The site Image", getApplicationContext());
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String str9 = str8;
            String str10 = str7;
            Bitmap createBitmap = Bitmap.createBitmap(this.lay_site.getWidth(), this.lay_site.getHeight(), Bitmap.Config.ARGB_8888);
            this.lay_site.draw(new Canvas(createBitmap));
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            this.img = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            this.progress_dialog.show();
            String str11 = str9;
            AnonymousClass7 r31 = r0;
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass5 r4 = r2;
            AnonymousClass5 r2 = new Response.Listener<String>() {
                public void onResponse(String str) {
                    FMS_BBC_SiteDetail_Activity.this.progress_dialog.cancel();
                    new AlertHelperclass().ptoast("Site Inspection Report Is Successfully Updated", FMS_BBC_SiteDetail_Activity.this.getApplicationContext());
                }
            };
            String str12 = str6;
            AnonymousClass6 r5 = r2;
            AnonymousClass6 r22 = new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_SiteDetail_Activity.this.getApplicationContext());
                    FMS_BBC_SiteDetail_Activity.this.progress_dialog.cancel();
                    FMS_BBC_SiteDetail_Activity.this.txt_alert.setText(onErrorResponse);
                    FMS_BBC_SiteDetail_Activity.this.error_dialog.show();
                }
            };
            AnonymousClass7 r0 = new StringRequest(this, 1, getString(R.string.serverip) + "fms/bbc_insp_upload1.php", r4, r5, trim, stringExtra, trim2, trim3, trim4, trim5, trim6, trim7, trim8, trim9, trim10, str, str2, str3, trim13, str4, str5, trim14, trim15, trim16, str12, str10, str11, trim11, trim12) {
                final /* synthetic */ FMS_BBC_SiteDetail_Activity this$0;
                final /* synthetic */ String val$Customer_count;
                final /* synthetic */ String val$Humidity;
                final /* synthetic */ String val$Inspection_Period;
                final /* synthetic */ String val$Observation;
                final /* synthetic */ String val$Olt_Latitude_new;
                final /* synthetic */ String val$Olt_Longitude_new;
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
                    this.val$Olt_Latitude_new = r34;
                    this.val$Olt_Longitude_new = r35;
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
                    hashMap.put("circle", this.this$0.Pref_Circle);
                    hashMap.put("ssa", this.this$0.Pref_SSA);
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
                    hashMap.put("olt_lat", this.val$Olt_Latitude_new);
                    hashMap.put("olt_long", this.val$Olt_Longitude_new);
                    hashMap.put("img", this.this$0.img);
                    return hashMap;
                }
            };
            AnonymousClass7 r1 = r31;
            r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r1);
        }
    }

    private void Open_Camera() {
        this.file_temp = new File(getExternalCacheDir(), "site_temp.jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri uriForFile = FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", this.file_temp);
        this.photoURI = uriForFile;
        intent.putExtra("output", uriForFile);
        startActivityForResult(intent, 2000);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 301) {
            if (i2 == -1) {
                getLatLong();
                return;
            }
            Toast.makeText(this, "Location settings not enabled", 0).show();
            this.progress_dialog.cancel();
        } else if (i == 2000) {
            if (i2 != -1) {
                Toast.makeText(this, "Camera capture canceled", 0).show();
            } else if (this.photoURI != null) {
                UCrop.of(this.photoURI, FileProvider.getUriForFile(this, "bsnl.bsnl_teevra.provider", new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Site_Insp(" + this.OLT_IP + ")@" + this.InspMonth + ").jpg"))).withAspectRatio(17.0f, 11.0f).withOptions(new UCrop.Options()).start((Activity) this, 69);
            } else {
                Toast.makeText(this, "Failed to get image", 0).show();
            }
        } else if (i2 != -1 || i != 69) {
        } else {
            if (i2 == -1 && intent != null) {
                Uri output = UCrop.getOutput(intent);
                if (output != null) {
                    Picasso.get().invalidate(output);
                    Picasso.get().load(output).transform((Transformation) new RoundedTransformation((int) getResources().getDimension(R.dimen.mediumpad), 0)).into(this.img_site);
                    this.img_site.setTag("camera");
                    this.lay_caption.setVisibility(0);
                    File file = this.file_temp;
                    if (file != null && file.exists()) {
                        this.file_temp.delete();
                        return;
                    }
                    return;
                }
                Toast.makeText(this, "Failed to get cropped image", 0).show();
            } else if (i2 != 96 || intent == null) {
                Toast.makeText(this, "Crop canceled", 0).show();
            } else {
                Toast.makeText(this, "Crop failed: " + UCrop.getError(intent).getMessage(), 0).show();
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
                    FMS_BBC_SiteDetail_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_SiteDetail_Activity fMS_BBC_SiteDetail_Activity = FMS_BBC_SiteDetail_Activity.this;
                    fMS_BBC_SiteDetail_Activity.editor = fMS_BBC_SiteDetail_Activity.sharedPreferences.edit();
                    FMS_BBC_SiteDetail_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_SiteDetail_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_SiteDetail_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_SiteDetail_Activity.this.startActivity(intent);
                    FMS_BBC_SiteDetail_Activity.this.finish();
                    FMS_BBC_SiteDetail_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101) {
            this.permissionManager_location.onRequestPermissionsResult(i, strArr, iArr);
        } else if (i == 102) {
            this.permissionManager_camera.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    private void checkAndRequestLocationPermission() {
        if (!this.permissionManager_location.arePermissionsGranted()) {
            this.permissionManager_location.requestPermissions();
        } else {
            checkGpsStatus();
        }
    }

    private void checkAndRequestCameraPermission() {
        if (!this.permissionManager_camera.arePermissionsGranted()) {
            this.permissionManager_camera.requestPermissions();
        } else {
            Open_Camera();
        }
    }

    private void checkGpsStatus() {
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) getSystemService("location");
        }
        if (this.locationManager.isProviderEnabled("gps")) {
            getLatLong();
            return;
        }
        Toast.makeText(this, "Location is OFF", 0).show();
        showGpsEnableDialog();
    }

    private void showGpsEnableDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_alert);
        dialog.getWindow().setLayout(-1, -2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        ((TextView) dialog.findViewById(R.id.txt_info)).setText(Html.fromHtml("Your Mobile's <b>GPS</b> is currently <i>Turned-Off</i>.<br>Please <i>Turn It On</i> To Proceed With The <b>Site Inspection</>.", 0));
        ((TextView) dialog.findViewById(R.id.txt_cancel)).setOnClickListener(new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda8(this, dialog));
        ((TextView) dialog.findViewById(R.id.txt_settings)).setOnClickListener(new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda9(this, dialog));
        CustomAnimationUtils.fadeInDialog(this, dialog);
        dialog.show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showGpsEnableDialog$0$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m243lambda$showGpsEnableDialog$0$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(Dialog dialog, View view) {
        CustomAnimationUtils.fadeOutDialog(this, dialog);
        finish();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$showGpsEnableDialog$1$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m244lambda$showGpsEnableDialog$1$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(Dialog dialog, View view) {
        CustomAnimationUtils.fadeOutDialog(this, dialog);
        dialog.dismiss();
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    private void getLatLong() {
        this.progress_dialog.show();
        this.cancellationTokenSource = new CancellationTokenSource();
        LocationServices.getSettingsClient((Activity) this).checkLocationSettings(new LocationSettingsRequest.Builder().addLocationRequest(new LocationRequest.Builder(100, DeviceOrientationRequest.OUTPUT_PERIOD_FAST).setMinUpdateIntervalMillis(2000).setMaxUpdates(1).build()).build()).addOnSuccessListener((Activity) this, new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda4(this)).addOnFailureListener((Activity) this, (OnFailureListener) new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$6$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m239lambda$getLatLong$6$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(LocationSettingsResponse locationSettingsResponse) {
        this.timeoutHandler.postDelayed(new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda0(this), LOCATION_TIMEOUT_MS);
        this.fusedLocationClient.getCurrentLocation(100, this.cancellationTokenSource.getToken()).addOnSuccessListener((Activity) this, new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda1(this)).addOnFailureListener((Activity) this, (OnFailureListener) new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda2(this)).addOnCanceledListener((Activity) this, (OnCanceledListener) new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$2$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m235lambda$getLatLong$2$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity() {
        this.cancellationTokenSource.cancel();
        Toast.makeText(this, "Location request timed out", 0).show();
        this.progress_dialog.cancel();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$3$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m236lambda$getLatLong$3$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(Location location) {
        this.timeoutHandler.removeCallbacksAndMessages((Object) null);
        if (location != null) {
            this.Latitude = location.getLatitude();
            this.Longitude = location.getLongitude();
            Toast.makeText(this, "Latitude: " + this.Latitude + "\nLongitude: " + this.Longitude, 1).show();
            this.progress_dialog.cancel();
            this.txt_lat.setText(Double.toString(this.Latitude));
            this.txt_long.setText(Double.toString(this.Longitude));
            this.txt_geocordinates.setText("OLT-IP : " + this.OLT_IP + " ; GC (" + Double.toString(this.Latitude) + " , " + Double.toString(this.Longitude) + ")");
            this.txt_time.setText(this.currentDate);
            return;
        }
        Toast.makeText(this, "Location unavailable", 0).show();
        this.progress_dialog.cancel();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$4$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m237lambda$getLatLong$4$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(Exception exc) {
        this.timeoutHandler.removeCallbacksAndMessages((Object) null);
        Toast.makeText(this, "Failed to get location: " + exc.getMessage(), 0).show();
        this.progress_dialog.cancel();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$5$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m238lambda$getLatLong$5$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity() {
        this.timeoutHandler.removeCallbacksAndMessages((Object) null);
        Toast.makeText(this, "Location request canceled", 0).show();
        this.progress_dialog.cancel();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLatLong$7$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m240lambda$getLatLong$7$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity(Exception exc) {
        if (exc instanceof ResolvableApiException) {
            try {
                ((ResolvableApiException) exc).startResolutionForResult(this, 301);
            } catch (IntentSender.SendIntentException unused) {
                Toast.makeText(this, "Unable to prompt for location settings", 0).show();
                this.progress_dialog.cancel();
            }
        } else {
            Toast.makeText(this, "Location settings check failed", 0).show();
            this.progress_dialog.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPermissionGranted$8$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m241lambda$onPermissionGranted$8$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity() {
        checkGpsStatus();
        Toast.makeText(this, "Location Permission is Granted", 0).show();
    }

    public void onPermissionGranted(int i) {
        if (i == 101) {
            new Handler(Looper.getMainLooper()).postDelayed(new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda6(this), 500);
        } else if (i == 102) {
            new Handler(Looper.getMainLooper()).postDelayed(new FMS_BBC_SiteDetail_Activity$$ExternalSyntheticLambda7(this), 500);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPermissionGranted$9$bsnl-bsnl_teevra-FMS_BBC_SiteDetail_Activity  reason: not valid java name */
    public /* synthetic */ void m242lambda$onPermissionGranted$9$bsnlbsnl_teevraFMS_BBC_SiteDetail_Activity() {
        Open_Camera();
        Toast.makeText(this, "Camera Permission is Granted", 0).show();
    }

    public void onPermissionDenied(int i) {
        if (i == 101) {
            Toast.makeText(this, "Required permission denied. Exiting...", 0).show();
            finish();
        } else if (i == 102) {
            Toast.makeText(this, "Camera Permission Denied", 0).show();
        }
    }

    public void onPermissionPermanentlyDenied(int i) {
        if (i == 101) {
            Toast.makeText(this, "Required permission denied. Exiting...", 0).show();
            finish();
        } else if (i == 102) {
            Toast.makeText(this, "Camera Permission Denied", 0).show();
        }
    }
}
