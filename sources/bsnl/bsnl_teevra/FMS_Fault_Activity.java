package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
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
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Fault_Activity extends AppCompatActivity implements TextWatcher {
    /* access modifiers changed from: private */
    public String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    /* access modifiers changed from: private */
    public String Pref_Fms_UserId;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Fms_Zone;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public JSONArray fault_array;
    /* access modifiers changed from: private */
    public AlertDialog fault_asign_dialog;
    private ArrayList<String> fault_assign_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fault_clearby_list;
    /* access modifiers changed from: private */
    public AlertDialog fault_close_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> fault_clrcode_list;
    /* access modifiers changed from: private */
    public ArrayList<String> fault_code_list;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_fms_fault;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_fault_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_UserId = this.sharedPreferences.getString("fms_userid_key", (String) null);
        this.Pref_Fms_Role = this.sharedPreferences.getString("fms_role_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Zone = this.sharedPreferences.getString("fms_user_zone_Key", (String) null);
        this.Pref_Fms_TeamId = this.sharedPreferences.getString("fms_teamid_key", (String) null);
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
        EditText editText = (EditText) findViewById(R.id.et_search);
        this.et_search = editText;
        editText.addTextChangedListener(this);
        this.lay_fms_fault = (LinearLayout) findViewById(R.id.lay_fms_fault);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_fault_order.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                String str2 = "PENDING_DURATION";
                String str3 = "SHORT_CODE";
                String str4 = "DOCKET_NO";
                String str5 = "PHONE_NO";
                String str6 = "SERVICE_TYPE";
                FMS_Fault_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getString("STATUS").equals("SUCCESS")) {
                        JSONArray unused = FMS_Fault_Activity.this.fault_array = jSONObject.getJSONArray("ROWSET");
                        new AlertHelperclass().ptoast("TOTAL " + FMS_Fault_Activity.this.fault_array.length() + " FAULT LOADED ", FMS_Fault_Activity.this.getApplicationContext());
                        int i = 0;
                        while (i < FMS_Fault_Activity.this.fault_array.length()) {
                            JSONObject jSONObject2 = FMS_Fault_Activity.this.fault_array.getJSONObject(i);
                            String string = jSONObject2.getString(str6);
                            String trim = jSONObject2.getString(str5).trim();
                            String trim2 = jSONObject2.getString(str4).trim();
                            LinearLayout linearLayout = new LinearLayout(FMS_Fault_Activity.this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.setMargins(0, 10, 0, 10);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.setOrientation(1);
                            linearLayout.setPadding(10, 10, 10, 10);
                            linearLayout.setBackgroundResource(R.drawable.new_style);
                            LinearLayout linearLayout2 = new LinearLayout(FMS_Fault_Activity.this);
                            String str7 = string;
                            LinearLayout linearLayout3 = linearLayout;
                            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout2.setOrientation(0);
                            linearLayout2.setGravity(17);
                            linearLayout2.setPadding(10, 10, 10, 10);
                            linearLayout2.setBackgroundResource(R.drawable.new_style10);
                            TextView textView = new TextView(FMS_Fault_Activity.this);
                            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                            int i2 = i + 1;
                            textView.setText(Integer.toString(i2));
                            textView.setTextColor(-1);
                            textView.setGravity(17);
                            textView.setPadding(5, 5, 5, 5);
                            textView.setTypeface((Typeface) null, 1);
                            textView.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView.setBackgroundResource(R.drawable.ic_new_circle_red);
                            LinearLayout linearLayout4 = new LinearLayout(FMS_Fault_Activity.this);
                            int i3 = i2;
                            String str8 = trim;
                            String str9 = trim2;
                            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 9.0f));
                            linearLayout4.setOrientation(1);
                            linearLayout4.setGravity(17);
                            linearLayout4.setPadding(5, 5, 5, 0);
                            TextView textView2 = new TextView(FMS_Fault_Activity.this);
                            textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView2.setText(jSONObject2.getString(str5).toUpperCase());
                            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView2.setGravity(17);
                            textView2.setTypeface((Typeface) null, 1);
                            textView2.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            TextView textView3 = new TextView(FMS_Fault_Activity.this);
                            String str10 = str5;
                            textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView3.setText("(DOCKET NO : " + jSONObject2.getString(str4) + ")");
                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView3.setGravity(17);
                            textView3.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout4.addView(textView2);
                            linearLayout4.addView(textView3);
                            ImageView imageView = new ImageView(FMS_Fault_Activity.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            imageView.setPadding(10, 10, 10, 10);
                            imageView.setClickable(true);
                            imageView.setImageResource(R.drawable.ic_new_share);
                            linearLayout2.addView(textView);
                            linearLayout2.addView(linearLayout4);
                            linearLayout2.addView(imageView);
                            LinearLayout linearLayout5 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout5.setOrientation(0);
                            linearLayout5.setPadding(5, 10, 5, 5);
                            TextView textView4 = new TextView(FMS_Fault_Activity.this);
                            textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView4.setText("CONTACT DETAILS : ");
                            textView4.setPaintFlags(8);
                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView4.setGravity(1);
                            textView4.setTypeface((Typeface) null, 1);
                            textView4.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView5 = new TextView(FMS_Fault_Activity.this);
                            String str11 = str4;
                            textView5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                            textView5.setText(Html.fromHtml("<b>" + jSONObject2.getString("CUSTOMER_NAME").trim() + "</b><br>" + jSONObject2.getString("ADDRESS").trim() + "<br>" + jSONObject2.getString("MOBILE_NO")));
                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView5.setGravity(3);
                            textView5.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout5.addView(textView4);
                            linearLayout5.addView(textView5);
                            LinearLayout linearLayout6 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout6.setOrientation(0);
                            linearLayout6.setGravity(17);
                            linearLayout6.setPadding(5, 10, 5, 0);
                            TextView textView6 = new TextView(FMS_Fault_Activity.this);
                            textView6.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView6.setText("SERVICE TYPE");
                            textView6.setPaintFlags(8);
                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView6.setGravity(17);
                            textView6.setTypeface((Typeface) null, 1);
                            textView6.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView7 = new TextView(FMS_Fault_Activity.this);
                            LinearLayout linearLayout7 = linearLayout5;
                            textView7.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView7.setText("COM SUB TYPE");
                            textView7.setPaintFlags(8);
                            textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView7.setGravity(17);
                            textView7.setTypeface((Typeface) null, 1);
                            textView7.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView8 = new TextView(FMS_Fault_Activity.this);
                            LinearLayout linearLayout8 = linearLayout2;
                            textView8.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView8.setText("FAULT ASSIGNED TO");
                            textView8.setPaintFlags(8);
                            textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView8.setGravity(17);
                            textView8.setTypeface((Typeface) null, 1);
                            textView8.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout6.addView(textView6);
                            linearLayout6.addView(textView7);
                            linearLayout6.addView(textView8);
                            LinearLayout linearLayout9 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout9.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout9.setOrientation(0);
                            linearLayout9.setGravity(17);
                            linearLayout9.setPadding(5, 5, 5, 10);
                            TextView textView9 = new TextView(FMS_Fault_Activity.this);
                            textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView9.setText(jSONObject2.getString(str6).toUpperCase());
                            textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView9.setGravity(17);
                            textView9.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView10 = new TextView(FMS_Fault_Activity.this);
                            textView10.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView10.setText(jSONObject2.getString("COMP_SUB_TYPE").toUpperCase());
                            textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView10.setGravity(17);
                            textView10.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView11 = new TextView(FMS_Fault_Activity.this);
                            String str12 = str6;
                            textView11.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            if (jSONObject2.getString(str3).equals("SELECT")) {
                                textView11.setText("UNASSIGNED");
                                textView11.setTextColor(FMS_Fault_Activity.this.getResources().getColor(R.color.colorRed));
                            } else {
                                textView11.setText(jSONObject2.getString(str3));
                                textView11.setTextColor(FMS_Fault_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            textView11.setTypeface((Typeface) null, 1);
                            textView11.setGravity(17);
                            textView11.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout9.addView(textView9);
                            linearLayout9.addView(textView10);
                            linearLayout9.addView(textView11);
                            LinearLayout linearLayout10 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout10.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout10.setOrientation(0);
                            linearLayout10.setGravity(17);
                            linearLayout10.setPadding(5, 10, 5, 0);
                            TextView textView12 = new TextView(FMS_Fault_Activity.this);
                            textView12.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView12.setText("COM LODGED ON");
                            textView12.setPaintFlags(8);
                            textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView12.setGravity(17);
                            textView12.setTypeface((Typeface) null, 1);
                            textView12.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView13 = new TextView(FMS_Fault_Activity.this);
                            textView13.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView13.setText("ASSIGNED TO FMS");
                            textView13.setPaintFlags(8);
                            textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView13.setGravity(17);
                            textView13.setTypeface((Typeface) null, 1);
                            textView13.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView14 = new TextView(FMS_Fault_Activity.this);
                            String str13 = str3;
                            textView14.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView14.setText("PENDING");
                            textView14.setPaintFlags(8);
                            textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView14.setGravity(17);
                            textView14.setTypeface((Typeface) null, 1);
                            textView14.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout10.addView(textView12);
                            linearLayout10.addView(textView13);
                            linearLayout10.addView(textView14);
                            LinearLayout linearLayout11 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout11.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout11.setOrientation(0);
                            linearLayout11.setGravity(17);
                            linearLayout11.setPadding(5, 5, 5, 10);
                            TextView textView15 = new TextView(FMS_Fault_Activity.this);
                            textView15.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView15.setText(jSONObject2.getString("COMP_BKD_DATE"));
                            textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView15.setGravity(17);
                            textView15.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView16 = new TextView(FMS_Fault_Activity.this);
                            textView16.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView16.setText(jSONObject2.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                            textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView16.setGravity(17);
                            textView16.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView17 = new TextView(FMS_Fault_Activity.this);
                            LinearLayout linearLayout12 = linearLayout10;
                            textView17.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView17.setText(jSONObject2.getString(str2) + " Hrs");
                            if (Integer.valueOf(jSONObject2.getString(str2)).intValue() > 8) {
                                textView17.setTextColor(FMS_Fault_Activity.this.getResources().getColor(R.color.colorRed));
                            } else {
                                textView17.setTextColor(FMS_Fault_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            textView17.setTypeface((Typeface) null, 1);
                            textView17.setGravity(17);
                            textView17.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout11.addView(textView15);
                            linearLayout11.addView(textView16);
                            linearLayout11.addView(textView17);
                            LinearLayout linearLayout13 = new LinearLayout(FMS_Fault_Activity.this);
                            linearLayout13.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout13.setOrientation(0);
                            linearLayout13.setGravity(17);
                            linearLayout13.setPadding(5, 10, 5, 0);
                            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                            layoutParams2.setMargins(150, 20, 150, 20);
                            TextView textView18 = new TextView(FMS_Fault_Activity.this);
                            textView18.setLayoutParams(layoutParams2);
                            textView18.setText("ASSIGN");
                            textView18.setTextColor(-1);
                            textView18.setGravity(17);
                            textView18.setTypeface((Typeface) null, 1);
                            textView18.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView18.setPadding(20, 20, 20, 20);
                            textView18.setBackgroundResource(R.drawable.button);
                            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                            layoutParams3.setMargins(150, 20, 150, 20);
                            TextView textView19 = new TextView(FMS_Fault_Activity.this);
                            textView19.setLayoutParams(layoutParams3);
                            textView19.setText("CLOSE");
                            textView19.setTextColor(-1);
                            textView19.setGravity(17);
                            textView19.setTypeface((Typeface) null, 1);
                            textView19.setTextSize(0, FMS_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView19.setPadding(20, 20, 20, 20);
                            textView19.setBackgroundResource(R.drawable.button);
                            linearLayout13.addView(textView18);
                            linearLayout13.addView(textView19);
                            final String string2 = jSONObject2.getString("ASSIGNED_TO");
                            final String str14 = str8;
                            final String str15 = str9;
                            textView18.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (FMS_Fault_Activity.this.Pref_Fms_Role.equals("FMS-ADMIN")) {
                                        FMS_Fault_Activity.this.team_assign(str14, str15);
                                        return;
                                    }
                                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                                    View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_error);
                                    TextView textView2 = (TextView) inflate.findViewById(R.id.txt_submit);
                                    ((TextView) inflate.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
                                    textView.setText("You Can Close/Assign Only Unassigned Fault \nOr\n Fault Assigned To You");
                                    textView.setText("Only Admin Can Assign Faults");
                                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                                    textView2.setText("OK");
                                    builder.setCancelable(false);
                                    builder.setView(inflate);
                                    AlertDialog unused = FMS_Fault_Activity.this.logout_dialog = builder.create();
                                    FMS_Fault_Activity.this.logout_dialog.show();
                                    textView2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            FMS_Fault_Activity.this.logout_dialog.cancel();
                                        }
                                    });
                                }
                            });
                            final String str16 = str7;
                            String str17 = str2;
                            AnonymousClass2 r8 = r1;
                            final String str18 = str14;
                            final String str19 = str15;
                            AnonymousClass2 r1 = new View.OnClickListener() {
                                public void onClick(View view) {
                                    if (string2.equals(FMS_Fault_Activity.this.Pref_Fms_UserId) || string2.equals("null") || FMS_Fault_Activity.this.Pref_Fms_Role.equals("FMS-ADMIN")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                                        View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_fms_fault_close, (ViewGroup) null);
                                        FMS_Fault_Activity.this.progress_dialog.show();
                                        FMS_Fault_Activity.this.fault_code(str16, inflate);
                                        FMS_Fault_Activity.this.fault_clrcode(str16, inflate);
                                        FMS_Fault_Activity.this.fault_clearby_by(str16, inflate);
                                        final Spinner spinner = (Spinner) inflate.findViewById(R.id.sp_fms_fault_code);
                                        final Spinner spinner2 = (Spinner) inflate.findViewById(R.id.sp_fms_fault_clrcode);
                                        final EditText editText = (EditText) inflate.findViewById(R.id.et_fault_comment);
                                        final Spinner spinner3 = (Spinner) inflate.findViewById(R.id.sp_fms_fault_clearby);
                                        ((TextView) inflate.findViewById(R.id.txt_phn)).setText(str18);
                                        ((TextView) inflate.findViewById(R.id.txt_docket)).setText("(DOCKET NO : " + str19 + ")");
                                        builder.setCancelable(false);
                                        builder.setView(inflate);
                                        AlertDialog unused = FMS_Fault_Activity.this.fault_close_dialog = builder.create();
                                        FMS_Fault_Activity.this.progress_dialog.cancel();
                                        FMS_Fault_Activity.this.fault_close_dialog.show();
                                        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                FMS_Fault_Activity.this.fault_close_dialog.cancel();
                                            }
                                        });
                                        ((TextView) inflate.findViewById(R.id.txt_close)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                String obj = spinner.getSelectedItem().toString();
                                                String obj2 = spinner2.getSelectedItem().toString();
                                                final String obj3 = editText.getText().toString();
                                                String obj4 = spinner3.getSelectedItem().toString();
                                                if (obj.equals("-- FAULT CODE --")) {
                                                    new AlertHelperclass().ntoast("Please Select A Valid Fault Code", FMS_Fault_Activity.this.getApplicationContext());
                                                } else if (obj2.equals("-- FAULT CLR CODE --")) {
                                                    new AlertHelperclass().ntoast("Please Select A Valid Fault Clearance Code", FMS_Fault_Activity.this.getApplicationContext());
                                                } else if (obj3.equals("")) {
                                                    new AlertHelperclass().ntoast("Please Enter Task Comment", FMS_Fault_Activity.this.getApplicationContext());
                                                } else if (obj4.equals("-- TEAM MEMBER --")) {
                                                    new AlertHelperclass().ntoast("Please Seect A valid Team Member", FMS_Fault_Activity.this.getApplicationContext());
                                                } else {
                                                    final String trim = obj.split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                                    final String trim2 = obj2.split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                                    final String trim3 = obj4.split("\\(")[1].split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                                    final String trim4 = obj4.split("\\(")[1].split(ProcessIdUtil.DEFAULT_PROCESSID)[1].split("\\)")[0].trim();
                                                    FMS_Fault_Activity.this.progress_dialog.show();
                                                    RequestQueue newRequestQueue = Volley.newRequestQueue(FMS_Fault_Activity.this);
                                                    AnonymousClass3 r2 = new StringRequest(1, FMS_Fault_Activity.this.getString(R.string.serverip) + "/fms/fms_fault_close.php", new Response.Listener<String>() {
                                                        public void onResponse(String str) {
                                                            FMS_Fault_Activity.this.progress_dialog.cancel();
                                                            try {
                                                                if (new JSONArray(str).getString(0).contains("SUCCESS")) {
                                                                    new AlertHelperclass().ptoast("Order Is Successfully Closed", FMS_Fault_Activity.this.getApplicationContext());
                                                                    Intent intent = new Intent(FMS_Fault_Activity.this, FMS_Fault_Activity.class);
                                                                    intent.setFlags(67108864);
                                                                    FMS_Fault_Activity.this.startActivity(intent);
                                                                    FMS_Fault_Activity.this.finish();
                                                                    return;
                                                                }
                                                                FMS_Fault_Activity.this.txt_alert.setText("FAILED\nFault Order Closure Failed, Please Try Again");
                                                                FMS_Fault_Activity.this.error_dialog.show();
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                        public void onErrorResponse(VolleyError volleyError) {
                                                            String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                                                            FMS_Fault_Activity.this.progress_dialog.cancel();
                                                            FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                                                            FMS_Fault_Activity.this.error_dialog.show();
                                                        }
                                                    }) {
                                                        /* access modifiers changed from: protected */
                                                        public Map<String, String> getParams() throws AuthFailureError {
                                                            HashMap hashMap = new HashMap();
                                                            hashMap.put("p_phone", str18);
                                                            hashMap.put("p_docket", str19);
                                                            hashMap.put("p_fault_code", trim);
                                                            hashMap.put("p_clearence_code", trim2);
                                                            hashMap.put("p_task_comments", obj3);
                                                            hashMap.put("p_username", FMS_Fault_Activity.this.Pref_Fms_Username);
                                                            hashMap.put("p_assigned_to", trim4);
                                                            hashMap.put("p_short_code", trim3);
                                                            hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                                                            hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                                                            hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                                                            return hashMap;
                                                        }
                                                    };
                                                    r2.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                                    newRequestQueue.add(r2);
                                                }
                                            }
                                        });
                                        return;
                                    }
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(FMS_Fault_Activity.this);
                                    View inflate2 = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                                    TextView textView = (TextView) inflate2.findViewById(R.id.txt_submit);
                                    ((TextView) inflate2.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
                                    ((TextView) inflate2.findViewById(R.id.txt_error)).setText("You Can Close Only Unassigned Fault \nOr\n Fault Assigned To You");
                                    ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
                                    textView.setText("OK");
                                    builder2.setCancelable(false);
                                    builder2.setView(inflate2);
                                    AlertDialog unused2 = FMS_Fault_Activity.this.logout_dialog = builder2.create();
                                    FMS_Fault_Activity.this.logout_dialog.show();
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            FMS_Fault_Activity.this.logout_dialog.cancel();
                                        }
                                    });
                                }
                            };
                            textView19.setOnClickListener(r8);
                            final LinearLayout linearLayout14 = linearLayout3;
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Bitmap createBitmap = Bitmap.createBitmap(linearLayout14.getWidth(), linearLayout14.getHeight(), Bitmap.Config.ARGB_8888);
                                    linearLayout14.draw(new Canvas(createBitmap));
                                    new ShareImage().share(createBitmap, new File(FMS_Fault_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "fms_provision.jpg"), FMS_Fault_Activity.this);
                                }
                            });
                            linearLayout14.addView(linearLayout8);
                            linearLayout14.addView(linearLayout7);
                            linearLayout14.addView(linearLayout6);
                            linearLayout14.addView(linearLayout9);
                            linearLayout14.addView(linearLayout12);
                            linearLayout14.addView(linearLayout11);
                            linearLayout14.addView(linearLayout13);
                            FMS_Fault_Activity.this.lay_fms_fault.addView(linearLayout14);
                            str2 = str17;
                            i = i3;
                            str5 = str10;
                            str4 = str11;
                            str6 = str12;
                            str3 = str13;
                        }
                        return;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                    View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                    TextView textView20 = (TextView) inflate.findViewById(R.id.txt_update);
                    textView20.setText("OK");
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog unused2 = FMS_Fault_Activity.this.info_dialog = builder.create();
                    FMS_Fault_Activity.this.info_dialog.show();
                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("No Pending Fault Orders");
                    textView20.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_Fault_Activity.this.info_dialog.cancel();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                FMS_Fault_Activity.this.progress_dialog.cancel();
                FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                FMS_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_Fault_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void fault_clearby_by(String str, View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.fault_clearby_list = arrayList;
        arrayList.add("-- TEAM MEMBER --");
        ((Spinner) view.findViewById(R.id.sp_fms_fault_clearby)).setAdapter(new ArrayAdapter(this, R.layout.spinner_item, this.fault_clearby_list));
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass6 r0 = new StringRequest(1, getString(R.string.serverip) + "/fms/fms_team_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        FMS_Fault_Activity.this.fault_clearby_list.add(jSONObject.getString("NAME").toUpperCase() + " (" + jSONObject.getString("SHORT_CODE") + " - " + jSONObject.getString("USERID") + ")");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                FMS_Fault_Activity.this.progress_dialog.cancel();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                FMS_Fault_Activity.this.progress_dialog.cancel();
                FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                FMS_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_username", FMS_Fault_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.getCache().clear();
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void fault_clrcode(String str, View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.fault_clrcode_list = arrayList;
        arrayList.add("-- FAULT CLR CODE --");
        ((Spinner) view.findViewById(R.id.sp_fms_fault_clrcode)).setAdapter(new ArrayAdapter(this, R.layout.spinner_item, this.fault_clrcode_list));
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str2 = str;
        AnonymousClass9 r0 = new StringRequest(1, getString(R.string.serverip) + "/fms/fms_fltclrcodes.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        FMS_Fault_Activity.this.fault_clrcode_list.add(jSONObject.getString("SHORT_CODE") + " - " + jSONObject.getString("CLEARANCE_CODE"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                FMS_Fault_Activity.this.progress_dialog.cancel();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                FMS_Fault_Activity.this.progress_dialog.cancel();
                FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                FMS_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("zone", FMS_Fault_Activity.this.Pref_Fms_Zone);
                hashMap.put("svtype", str2);
                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.getCache().clear();
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void fault_code(String str, View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        this.fault_code_list = arrayList;
        arrayList.add("-- FAULT CODE --");
        ((Spinner) view.findViewById(R.id.sp_fms_fault_code)).setAdapter(new ArrayAdapter(this, R.layout.spinner_item, this.fault_code_list));
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str2 = str;
        AnonymousClass12 r0 = new StringRequest(1, getString(R.string.serverip) + "/fms/fms_fltcodes.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        FMS_Fault_Activity.this.fault_code_list.add(jSONObject.getString("FAULT_CODE") + " - " + jSONObject.getString("DESCRIPTION"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                FMS_Fault_Activity.this.progress_dialog.cancel();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                FMS_Fault_Activity.this.progress_dialog.cancel();
                FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                FMS_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("zone", FMS_Fault_Activity.this.Pref_Fms_Zone);
                hashMap.put("svtype", str2);
                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void team_assign(String str, String str2) {
        this.progress_dialog.show();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final ArrayList arrayList4 = new ArrayList();
        arrayList.add("-- TEAM MEMBER --");
        arrayList2.add("-- TEAM MEMBER --");
        arrayList3.add("-- TEAM MEMBER --");
        arrayList4.add("-- TEAM MEMBER --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass15 r0 = new StringRequest(1, getString(R.string.serverip) + "/fms/fms_team_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_Fault_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(jSONObject.getString("SHORT_CODE"));
                        arrayList2.add(jSONObject.getString("USERID"));
                        arrayList3.add(jSONObject.getString("NAME").toUpperCase());
                        arrayList4.add(jSONObject.getString("TEAM_ID").toUpperCase());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_fms_fault_assign, (ViewGroup) null);
                builder.setCancelable(false);
                builder.setView(inflate);
                AlertDialog unused = FMS_Fault_Activity.this.fault_asign_dialog = builder.create();
                final Spinner spinner = (Spinner) inflate.findViewById(R.id.sp_fault_assign);
                final ArrayAdapter arrayAdapter = new ArrayAdapter(FMS_Fault_Activity.this, R.layout.spinner_item, arrayList3);
                spinner.setAdapter(arrayAdapter);
                ((TextView) inflate.findViewById(R.id.txt_phn)).setText(str3);
                ((TextView) inflate.findViewById(R.id.txt_docket)).setText("(DOCKET NO : " + str4 + ")");
                FMS_Fault_Activity.this.fault_asign_dialog.show();
                ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_Fault_Activity.this.fault_asign_dialog.cancel();
                    }
                });
                ((TextView) inflate.findViewById(R.id.txt_assign)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_Fault_Activity.this.fault_asign_dialog.cancel();
                        final String trim = spinner.getSelectedItem().toString().trim();
                        if (trim.equals("-- TEAM MEMBER --")) {
                            new AlertHelperclass().ntoast("Please Select A Valid Team Member", FMS_Fault_Activity.this.getApplicationContext());
                            return;
                        }
                        int position = arrayAdapter.getPosition(trim);
                        final String trim2 = ((String) arrayList.get(position)).trim();
                        final String trim3 = ((String) arrayList2.get(position)).trim();
                        ((String) arrayList4.get(position)).trim();
                        FMS_Fault_Activity.this.progress_dialog.show();
                        RequestQueue newRequestQueue = Volley.newRequestQueue(FMS_Fault_Activity.this);
                        AnonymousClass3 r2 = new StringRequest(1, FMS_Fault_Activity.this.getString(R.string.serverip) + "/fms/fms_fault_assign.php", new Response.Listener<String>() {
                            public void onResponse(String str) {
                                FMS_Fault_Activity.this.progress_dialog.cancel();
                                try {
                                    if (new JSONArray(str).getString(0).contains("SUCCESS")) {
                                        new AlertHelperclass().ptoast("Fault Order Is Asigned To " + trim, FMS_Fault_Activity.this.getApplicationContext());
                                        FMS_Fault_Activity.this.startActivity(new Intent(FMS_Fault_Activity.this, FMS_Fault_Activity.class));
                                        FMS_Fault_Activity.this.finish();
                                        return;
                                    }
                                    FMS_Fault_Activity.this.txt_alert.setText("FAILED\nFault Order Assignment failed,\nPlease Try Again");
                                    FMS_Fault_Activity.this.error_dialog.show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError volleyError) {
                                VolleyErrorHelper volleyErrorHelper = new VolleyErrorHelper();
                                FMS_Fault_Activity.this.progress_dialog.cancel();
                                FMS_Fault_Activity.this.txt_alert.setText(volleyErrorHelper.onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext()));
                                FMS_Fault_Activity.this.error_dialog.show();
                            }
                        }) {
                            /* access modifiers changed from: protected */
                            public Map<String, String> getParams() throws AuthFailureError {
                                HashMap hashMap = new HashMap();
                                hashMap.put("p_phone", str3);
                                hashMap.put("p_docket", str4);
                                hashMap.put("p_assigned_to", trim3);
                                hashMap.put("p_short_code", trim2);
                                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                                return hashMap;
                            }
                        };
                        r2.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                        newRequestQueue.add(r2);
                    }
                });
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Fault_Activity.this.progress_dialog.cancel();
                FMS_Fault_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext()));
                FMS_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_username", FMS_Fault_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.getCache().clear();
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
                    FMS_Fault_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Fault_Activity fMS_Fault_Activity = FMS_Fault_Activity.this;
                    fMS_Fault_Activity.editor = fMS_Fault_Activity.sharedPreferences.edit();
                    FMS_Fault_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_Fault_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_Fault_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_Fault_Activity.this.startActivity(intent);
                    FMS_Fault_Activity.this.finish();
                    FMS_Fault_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void afterTextChanged(Editable editable) {
        String str;
        int i;
        String str2;
        String str3 = "PHONE_NO";
        String obj = this.et_search.getText().toString();
        this.lay_fms_fault.removeAllViews();
        int i2 = 0;
        int i3 = 1;
        while (i2 < this.fault_array.length()) {
            try {
                JSONObject jSONObject = this.fault_array.getJSONObject(i2);
                if (!jSONObject.getString(str3).toUpperCase().contains(obj.toUpperCase())) {
                    if (!jSONObject.getString("DOCKET_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("CUSTOMER_NAME").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("MOBILE_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("SERVICE_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("COMP_SUB_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("SHORT_CODE").toUpperCase().contains(obj.toUpperCase())) {
                        if (!jSONObject.getString("PENDING_DURATION").toUpperCase().contains(obj.toUpperCase())) {
                            str = str3;
                            str2 = obj;
                            i = i2;
                            i2 = i + 1;
                            obj = str2;
                            str3 = str;
                        }
                    }
                }
                String string = jSONObject.getString("SERVICE_TYPE");
                String trim = jSONObject.getString(str3).trim();
                String trim2 = jSONObject.getString("DOCKET_NO").trim();
                str2 = obj;
                LinearLayout linearLayout = new LinearLayout(this);
                i = i2;
                String str4 = string;
                String str5 = trim;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 10, 0, 10);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setOrientation(1);
                linearLayout.setPadding(10, 10, 10, 10);
                linearLayout.setBackgroundResource(R.drawable.new_style);
                LinearLayout linearLayout2 = new LinearLayout(this);
                LinearLayout linearLayout3 = linearLayout;
                String str6 = trim2;
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout2.setOrientation(0);
                linearLayout2.setGravity(17);
                linearLayout2.setPadding(10, 10, 10, 10);
                linearLayout2.setBackgroundResource(R.drawable.new_style10);
                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                textView.setText(Integer.toString(i3));
                textView.setTextColor(-1);
                textView.setGravity(17);
                textView.setPadding(5, 5, 5, 5);
                textView.setTypeface((Typeface) null, 1);
                textView.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView.setBackgroundResource(R.drawable.ic_new_circle_red);
                LinearLayout linearLayout4 = new LinearLayout(this);
                int i4 = i3;
                String str7 = "PENDING_DURATION";
                linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 9.0f));
                linearLayout4.setOrientation(1);
                linearLayout4.setGravity(17);
                linearLayout4.setPadding(5, 5, 5, 0);
                TextView textView2 = new TextView(this);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView2.setText(jSONObject.getString(str3).toUpperCase());
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setGravity(17);
                textView2.setTypeface((Typeface) null, 1);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                TextView textView3 = new TextView(this);
                str = str3;
                textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView3.setText("(DOCKET NO : " + jSONObject.getString("DOCKET_NO") + ")");
                textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView3.setGravity(17);
                textView3.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout4.addView(textView2);
                linearLayout4.addView(textView3);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                imageView.setPadding(10, 10, 10, 10);
                imageView.setClickable(true);
                imageView.setImageResource(R.drawable.ic_new_share);
                linearLayout2.addView(textView);
                linearLayout2.addView(linearLayout4);
                linearLayout2.addView(imageView);
                LinearLayout linearLayout5 = new LinearLayout(this);
                linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout5.setOrientation(0);
                linearLayout5.setPadding(5, 10, 5, 5);
                TextView textView4 = new TextView(this);
                textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView4.setText("CONTACT DETAILS : ");
                textView4.setPaintFlags(8);
                textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView4.setGravity(1);
                textView4.setTypeface((Typeface) null, 1);
                textView4.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView5 = new TextView(this);
                textView5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                textView5.setText(Html.fromHtml("<b>" + jSONObject.getString("CUSTOMER_NAME").trim() + "</b><br>" + jSONObject.getString("ADDRESS").trim() + "<br>" + jSONObject.getString("MOBILE_NO")));
                textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView5.setGravity(3);
                textView5.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout5.addView(textView4);
                linearLayout5.addView(textView5);
                LinearLayout linearLayout6 = new LinearLayout(this);
                linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout6.setOrientation(0);
                linearLayout6.setGravity(17);
                linearLayout6.setPadding(5, 10, 5, 0);
                TextView textView6 = new TextView(this);
                textView6.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView6.setText("SERVICE TYPE");
                textView6.setPaintFlags(8);
                textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView6.setGravity(17);
                textView6.setTypeface((Typeface) null, 1);
                textView6.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView7 = new TextView(this);
                textView7.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView7.setText("COM SUB TYPE");
                textView7.setPaintFlags(8);
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setTypeface((Typeface) null, 1);
                textView7.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView8 = new TextView(this);
                textView8.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView8.setText("FAULT ASSIGNED TO");
                textView8.setPaintFlags(8);
                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView8.setGravity(17);
                textView8.setTypeface((Typeface) null, 1);
                textView8.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout6.addView(textView6);
                linearLayout6.addView(textView7);
                linearLayout6.addView(textView8);
                LinearLayout linearLayout7 = new LinearLayout(this);
                linearLayout7.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout7.setOrientation(0);
                linearLayout7.setGravity(17);
                linearLayout7.setPadding(5, 5, 5, 10);
                TextView textView9 = new TextView(this);
                textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView9.setText(jSONObject.getString("SERVICE_TYPE").toUpperCase());
                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView9.setGravity(17);
                textView9.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView10 = new TextView(this);
                textView10.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView10.setText(jSONObject.getString("COMP_SUB_TYPE").toUpperCase());
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView11 = new TextView(this);
                textView11.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                String str8 = "SHORT_CODE";
                if (jSONObject.getString(str8).equals("SELECT")) {
                    textView11.setText("UNASSIGNED");
                    textView11.setTextColor(getResources().getColor(R.color.colorRed));
                } else {
                    textView11.setText(jSONObject.getString(str8));
                    textView11.setTextColor(getResources().getColor(R.color.colorGreen));
                }
                textView11.setTypeface((Typeface) null, 1);
                textView11.setGravity(17);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout7.addView(textView9);
                linearLayout7.addView(textView10);
                linearLayout7.addView(textView11);
                LinearLayout linearLayout8 = new LinearLayout(this);
                linearLayout8.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout8.setOrientation(0);
                linearLayout8.setGravity(17);
                linearLayout8.setPadding(5, 10, 5, 0);
                TextView textView12 = new TextView(this);
                textView12.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView12.setText("COM LODGED ON");
                textView12.setPaintFlags(8);
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setTypeface((Typeface) null, 1);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView13 = new TextView(this);
                textView13.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView13.setText("ASSIGNED TO FMS");
                textView13.setPaintFlags(8);
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setTypeface((Typeface) null, 1);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView14 = new TextView(this);
                textView14.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView14.setText("PENDING");
                textView14.setPaintFlags(8);
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                textView14.setTypeface((Typeface) null, 1);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout8.addView(textView12);
                linearLayout8.addView(textView13);
                linearLayout8.addView(textView14);
                LinearLayout linearLayout9 = new LinearLayout(this);
                linearLayout9.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout9.setOrientation(0);
                linearLayout9.setGravity(17);
                linearLayout9.setPadding(5, 5, 5, 10);
                TextView textView15 = new TextView(this);
                textView15.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView15.setText(jSONObject.getString("COMP_BKD_DATE"));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView16 = new TextView(this);
                textView16.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView16.setText(jSONObject.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView17 = new TextView(this);
                LinearLayout linearLayout10 = linearLayout8;
                textView17.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                String str9 = str7;
                textView17.setText(jSONObject.getString(str9) + " Hrs");
                if (Integer.valueOf(jSONObject.getString(str9)).intValue() > 8) {
                    textView17.setTextColor(getResources().getColor(R.color.colorRed));
                } else {
                    textView17.setTextColor(getResources().getColor(R.color.colorGreen));
                }
                textView17.setTypeface((Typeface) null, 1);
                textView17.setGravity(17);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout9.addView(textView15);
                linearLayout9.addView(textView16);
                linearLayout9.addView(textView17);
                LinearLayout linearLayout11 = new LinearLayout(this);
                linearLayout11.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout11.setOrientation(0);
                linearLayout11.setGravity(17);
                linearLayout11.setPadding(5, 10, 5, 0);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams2.setMargins(150, 20, 150, 20);
                TextView textView18 = new TextView(this);
                textView18.setLayoutParams(layoutParams2);
                textView18.setText("ASSIGN");
                textView18.setTextColor(-1);
                textView18.setGravity(17);
                textView18.setTypeface((Typeface) null, 1);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView18.setPadding(20, 20, 20, 20);
                textView18.setBackgroundResource(R.drawable.button);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
                layoutParams3.setMargins(150, 20, 150, 20);
                TextView textView19 = new TextView(this);
                textView19.setLayoutParams(layoutParams3);
                textView19.setText("CLOSE");
                textView19.setTextColor(-1);
                textView19.setGravity(17);
                textView19.setTypeface((Typeface) null, 1);
                textView19.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView19.setPadding(20, 20, 20, 20);
                textView19.setBackgroundResource(R.drawable.button);
                linearLayout11.addView(textView18);
                linearLayout11.addView(textView19);
                final String string2 = jSONObject.getString("ASSIGNED_TO");
                final String str10 = str5;
                final String str11 = str6;
                textView18.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (FMS_Fault_Activity.this.Pref_Fms_Role.equals("FMS-ADMIN")) {
                            FMS_Fault_Activity.this.team_assign(str10, str11);
                            return;
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                        View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                        TextView textView = (TextView) inflate.findViewById(R.id.txt_error);
                        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_submit);
                        ((TextView) inflate.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
                        textView.setText("You Can Close/Assign Only Unassigned Fault \nOr\n Fault Assigned To You");
                        textView.setText("Only Admin Can Assign Faults");
                        ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                        textView2.setText("OK");
                        builder.setCancelable(false);
                        builder.setView(inflate);
                        AlertDialog unused = FMS_Fault_Activity.this.logout_dialog = builder.create();
                        FMS_Fault_Activity.this.logout_dialog.show();
                        textView2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                FMS_Fault_Activity.this.logout_dialog.cancel();
                            }
                        });
                    }
                });
                String str12 = str11;
                final String str13 = str4;
                AnonymousClass19 r15 = r1;
                final String str14 = str10;
                final String str15 = str12;
                AnonymousClass19 r1 = new View.OnClickListener() {
                    public void onClick(View view) {
                        if (string2.equals(FMS_Fault_Activity.this.Pref_Fms_UserId) || string2.equals("null")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Fault_Activity.this);
                            View inflate = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_fms_fault_close, (ViewGroup) null);
                            FMS_Fault_Activity.this.progress_dialog.show();
                            FMS_Fault_Activity.this.fault_code(str13, inflate);
                            FMS_Fault_Activity.this.fault_clrcode(str13, inflate);
                            FMS_Fault_Activity.this.fault_clearby_by(str13, inflate);
                            final Spinner spinner = (Spinner) inflate.findViewById(R.id.sp_fms_fault_code);
                            final Spinner spinner2 = (Spinner) inflate.findViewById(R.id.sp_fms_fault_clrcode);
                            final EditText editText = (EditText) inflate.findViewById(R.id.et_fault_comment);
                            final Spinner spinner3 = (Spinner) inflate.findViewById(R.id.sp_fms_fault_clearby);
                            ((TextView) inflate.findViewById(R.id.txt_phn)).setText(str14);
                            ((TextView) inflate.findViewById(R.id.txt_docket)).setText("(" + str15 + ")");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused = FMS_Fault_Activity.this.fault_close_dialog = builder.create();
                            FMS_Fault_Activity.this.progress_dialog.cancel();
                            FMS_Fault_Activity.this.fault_close_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    FMS_Fault_Activity.this.fault_close_dialog.cancel();
                                }
                            });
                            ((TextView) inflate.findViewById(R.id.txt_close)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    String obj = spinner.getSelectedItem().toString();
                                    String obj2 = spinner2.getSelectedItem().toString();
                                    final String obj3 = editText.getText().toString();
                                    String obj4 = spinner3.getSelectedItem().toString();
                                    if (obj.equals("-- FAULT CODE --")) {
                                        new AlertHelperclass().ntoast("Please Select A Valid Fault Code", FMS_Fault_Activity.this.getApplicationContext());
                                    } else if (obj2.equals("-- FAULT CLR CODE --")) {
                                        new AlertHelperclass().ntoast("Please Select A Valid Fault Clearance Code", FMS_Fault_Activity.this.getApplicationContext());
                                    } else if (obj3.equals("")) {
                                        new AlertHelperclass().ntoast("Please Enter Task Comment", FMS_Fault_Activity.this.getApplicationContext());
                                    } else if (obj4.equals("-- TEAM MEMBER --")) {
                                        new AlertHelperclass().ntoast("Please Seect A valid Team Member", FMS_Fault_Activity.this.getApplicationContext());
                                    } else {
                                        final String trim = obj.split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                        final String trim2 = obj2.split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                        final String trim3 = obj4.split("\\(")[1].split(ProcessIdUtil.DEFAULT_PROCESSID)[0].trim();
                                        final String trim4 = obj4.split("\\(")[1].split(ProcessIdUtil.DEFAULT_PROCESSID)[1].split("\\)")[0].trim();
                                        FMS_Fault_Activity.this.progress_dialog.show();
                                        RequestQueue newRequestQueue = Volley.newRequestQueue(FMS_Fault_Activity.this);
                                        AnonymousClass3 r2 = new StringRequest(1, FMS_Fault_Activity.this.getString(R.string.serverip) + "/fms/fms_fault_close.php", new Response.Listener<String>() {
                                            public void onResponse(String str) {
                                                FMS_Fault_Activity.this.progress_dialog.cancel();
                                                try {
                                                    if (new JSONArray(str).getString(0).contains("SUCCESS")) {
                                                        new AlertHelperclass().ptoast("Order Is Successfully Closed", FMS_Fault_Activity.this.getApplicationContext());
                                                        Intent intent = new Intent(FMS_Fault_Activity.this, FMS_Fault_Activity.class);
                                                        intent.setFlags(67108864);
                                                        FMS_Fault_Activity.this.startActivity(intent);
                                                        FMS_Fault_Activity.this.finish();
                                                        return;
                                                    }
                                                    FMS_Fault_Activity.this.txt_alert.setText("FAILED\nFault Order Closure Failed, Please Try Again");
                                                    FMS_Fault_Activity.this.error_dialog.show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                            public void onErrorResponse(VolleyError volleyError) {
                                                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Fault_Activity.this.getApplicationContext());
                                                FMS_Fault_Activity.this.progress_dialog.cancel();
                                                FMS_Fault_Activity.this.txt_alert.setText(onErrorResponse);
                                                FMS_Fault_Activity.this.error_dialog.show();
                                            }
                                        }) {
                                            /* access modifiers changed from: protected */
                                            public Map<String, String> getParams() throws AuthFailureError {
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("p_phone", str14);
                                                hashMap.put("p_docket", str15);
                                                hashMap.put("p_fault_code", trim);
                                                hashMap.put("p_clearence_code", trim2);
                                                hashMap.put("p_task_comments", obj3);
                                                hashMap.put("p_username", FMS_Fault_Activity.this.Pref_Fms_Username);
                                                hashMap.put("p_assigned_to", trim4);
                                                hashMap.put("p_short_code", trim3);
                                                hashMap.put("username", FMS_Fault_Activity.this.Pref_Username);
                                                hashMap.put("random_key", FMS_Fault_Activity.this.Pref_Randkey);
                                                hashMap.put("device_id", FMS_Fault_Activity.this.androidId);
                                                return hashMap;
                                            }
                                        };
                                        r2.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                        newRequestQueue.add(r2);
                                    }
                                }
                            });
                            return;
                        }
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(FMS_Fault_Activity.this);
                        View inflate2 = FMS_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                        TextView textView = (TextView) inflate2.findViewById(R.id.txt_submit);
                        ((TextView) inflate2.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
                        ((TextView) inflate2.findViewById(R.id.txt_error)).setText("You Can Close Only Unassigned Fault \nOr\n Fault Assigned To You");
                        ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
                        textView.setText("OK");
                        builder2.setCancelable(false);
                        builder2.setView(inflate2);
                        AlertDialog unused2 = FMS_Fault_Activity.this.logout_dialog = builder2.create();
                        FMS_Fault_Activity.this.logout_dialog.show();
                        textView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                FMS_Fault_Activity.this.logout_dialog.cancel();
                            }
                        });
                    }
                };
                textView19.setOnClickListener(r15);
                final LinearLayout linearLayout12 = linearLayout3;
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Bitmap createBitmap = Bitmap.createBitmap(linearLayout12.getWidth(), linearLayout12.getHeight(), Bitmap.Config.ARGB_8888);
                        linearLayout12.draw(new Canvas(createBitmap));
                        new ShareImage().share(createBitmap, new File(FMS_Fault_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "fms_provision.jpg"), FMS_Fault_Activity.this);
                    }
                });
                linearLayout12.addView(linearLayout2);
                linearLayout12.addView(linearLayout5);
                linearLayout12.addView(linearLayout6);
                linearLayout12.addView(linearLayout7);
                linearLayout12.addView(linearLayout10);
                linearLayout12.addView(linearLayout9);
                linearLayout12.addView(linearLayout11);
                this.lay_fms_fault.addView(linearLayout12);
                i3 = i4 + 1;
                i2 = i + 1;
                obj = str2;
                str3 = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
