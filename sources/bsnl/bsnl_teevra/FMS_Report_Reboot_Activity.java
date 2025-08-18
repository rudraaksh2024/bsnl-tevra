package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Report_Reboot_Activity extends AppCompatActivity implements View.OnClickListener {
    public int NUM_ITEMS_PAGE = 100;
    private String Pref_Circle;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Fms_role;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public JSONArray Row_Array;
    public int TOTAL_LIST_ITEMS = 0;
    /* access modifiers changed from: private */
    public CustomBaseAdapter5 adapter;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_submit;
    private TextView[] btns;
    private int countt = 0;
    private CustomBaseAdapter5 customBaseAdapter;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView img_cal1;
    private ImageView img_cal2;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    /* access modifiers changed from: private */
    public ListView listview;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private int search_count = 0;
    private JSONArray searched_array;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_end_date;
    private TextView txt_header;
    /* access modifiers changed from: private */
    public TextView txt_start_date;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_report_reboot_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        SharedPreferences sharedPreferences3 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences3;
        this.Pref_Fms_Username = sharedPreferences3.getString("fms_username_Key", (String) null);
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
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        this.img_cal1 = (ImageView) findViewById(R.id.img_cal1);
        this.img_cal2 = (ImageView) findViewById(R.id.img_cal2);
        this.txt_start_date = (TextView) findViewById(R.id.txt_start_date);
        this.txt_end_date = (TextView) findViewById(R.id.txt_end_date);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        this.listview = (ListView) findViewById(R.id.listView);
        this.img_cal1.setOnClickListener(this);
        this.img_cal2.setOnClickListener(this);
        this.txt_start_date.setOnClickListener(this);
        this.txt_end_date.setOnClickListener(this);
        this.btn_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_cal1 || view.getId() == R.id.txt_start_date) {
            Calendar instance = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    FMS_Report_Reboot_Activity.this.txt_start_date.setText(String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2 + 1), Integer.valueOf(i3)}));
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
                    FMS_Report_Reboot_Activity.this.txt_end_date.setText(String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2 + 1), Integer.valueOf(i3)}));
                }
            }, instance2.get(1), instance2.get(2), instance2.get(5));
            datePickerDialog2.getDatePicker().setMaxDate(System.currentTimeMillis());
            instance2.add(5, -30);
            datePickerDialog2.getDatePicker().setMinDate(instance2.getTimeInMillis());
            datePickerDialog2.show();
        } else if (view.getId() == R.id.btn_submit) {
            final String trim = this.txt_start_date.getText().toString().trim();
            final String trim2 = this.txt_end_date.getText().toString().trim();
            if (trim.equals("-- SELECT START DATE --")) {
                new AlertHelperclass().ntoast("Please Select A Valid Start Date", getApplicationContext());
            } else if (trim2.equals("-- SELECT END DATE --")) {
                new AlertHelperclass().ntoast("Please Select A Valid End Date", getApplicationContext());
            } else {
                this.progress_dialog.show();
                this.lay2.setVisibility(8);
                this.listview.setAdapter((ListAdapter) null);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass5 r5 = new StringRequest(1, getString(R.string.serverip) + "report/olt_reboot.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        FMS_Report_Reboot_Activity.this.progress_dialog.cancel();
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.getBoolean("SUCCESS")) {
                                JSONArray unused = FMS_Report_Reboot_Activity.this.Row_Array = jSONObject.getJSONArray("ROWSET");
                                CustomBaseAdapter5 unused2 = FMS_Report_Reboot_Activity.this.adapter = new CustomBaseAdapter5(FMS_Report_Reboot_Activity.this.getApplicationContext(), FMS_Report_Reboot_Activity.this.Row_Array, 0, FMS_Report_Reboot_Activity.this);
                                FMS_Report_Reboot_Activity.this.listview.setAdapter(FMS_Report_Reboot_Activity.this.adapter);
                                FMS_Report_Reboot_Activity.this.lay2.setVisibility(0);
                                return;
                            }
                            String string = jSONObject.getString("ERROR");
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Report_Reboot_Activity.this);
                            View inflate = FMS_Report_Reboot_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setBackgroundResource(0);
                            textView.setText("OK");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused3 = FMS_Report_Reboot_Activity.this.info_dialog = builder.create();
                            FMS_Report_Reboot_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml(string));
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    FMS_Report_Reboot_Activity.this.info_dialog.cancel();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        FMS_Report_Reboot_Activity.this.progress_dialog.cancel();
                        FMS_Report_Reboot_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Report_Reboot_Activity.this.getApplicationContext()));
                        FMS_Report_Reboot_Activity.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("fms_username", FMS_Report_Reboot_Activity.this.Pref_Fms_Username);
                        hashMap.put("start_date", trim);
                        hashMap.put("end_date", trim2);
                        hashMap.put("username", FMS_Report_Reboot_Activity.this.Pref_Username);
                        hashMap.put("random_key", FMS_Report_Reboot_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", FMS_Report_Reboot_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r5.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r5);
            }
        }
    }

    private void loadList(int i) {
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
                    FMS_Report_Reboot_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Report_Reboot_Activity fMS_Report_Reboot_Activity = FMS_Report_Reboot_Activity.this;
                    fMS_Report_Reboot_Activity.editor = fMS_Report_Reboot_Activity.sharedPreferences.edit();
                    FMS_Report_Reboot_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_Report_Reboot_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_Report_Reboot_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_Report_Reboot_Activity.this.startActivity(intent);
                    FMS_Report_Reboot_Activity.this.finish();
                    FMS_Report_Reboot_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
