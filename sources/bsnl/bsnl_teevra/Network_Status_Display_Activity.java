package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.internal.view.SupportMenu;
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
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Network_Status_Display_Activity extends AppCompatActivity implements TextWatcher {
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public JSONArray Total_Array;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_search;
    private ImageView img_save;
    /* access modifiers changed from: private */
    public RelativeLayout lay_brief;
    /* access modifiers changed from: private */
    public LinearLayout lay_brief_1;
    /* access modifiers changed from: private */
    public LinearLayout lay_brief_2;
    /* access modifiers changed from: private */
    public LinearLayout lay_detail;
    private LinearLayout lay_network_galance;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TableLayout tbl_detail;
    private ToggleButton toggleButton;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_congrate;
    private TextView txt_element;
    /* access modifiers changed from: private */
    public TextView txt_header;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.network_status_display_activity);
        String stringExtra = getIntent().getStringExtra("ELEMENT");
        String stringExtra2 = getIntent().getStringExtra("CIRCLE");
        String stringExtra3 = getIntent().getStringExtra("SSA");
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Username = sharedPreferences2.getString("username_Key", (String) null);
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
        this.lay_network_galance = (LinearLayout) findViewById(R.id.lay_network_galance);
        this.toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        this.txt_element = (TextView) findViewById(R.id.txt_element);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.txt_congrate = (TextView) findViewById(R.id.txt_congrate);
        this.lay_brief = (RelativeLayout) findViewById(R.id.lay_brief);
        this.lay_detail = (LinearLayout) findViewById(R.id.lay_detail);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.lay_brief_1 = (LinearLayout) findViewById(R.id.lay_brief_1);
        this.lay_brief_2 = (LinearLayout) findViewById(R.id.lay_brief_2);
        this.tbl_detail = (TableLayout) findViewById(R.id.tbl_detail);
        Load_Detail(stringExtra2, stringExtra3, stringExtra);
        this.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (Network_Status_Display_Activity.this.lay_brief.getVisibility() == 0) {
                        Network_Status_Display_Activity.this.lay_brief.setVisibility(8);
                        Network_Status_Display_Activity.this.lay_detail.setVisibility(0);
                        Network_Status_Display_Activity.this.et_search.setEnabled(true);
                        Network_Status_Display_Activity.this.et_search.addTextChangedListener(Network_Status_Display_Activity.this);
                    }
                } else if (Network_Status_Display_Activity.this.lay_detail.getVisibility() == 0) {
                    Network_Status_Display_Activity.this.lay_detail.setVisibility(8);
                    Network_Status_Display_Activity.this.lay_brief.setVisibility(0);
                    Network_Status_Display_Activity.this.et_search.setEnabled(false);
                }
            }
        });
    }

    private void Load_Detail(final String str, final String str2, final String str3) {
        this.toggleButton.setChecked(false);
        this.tbl_detail.removeAllViews();
        this.lay_brief_1.removeAllViews();
        this.txt_element.setText("  (" + str3 + ")");
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        AnonymousClass4 r1 = new StringRequest(1, getString(R.string.serverip) + "network_glance_detail.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                String str2 = "VLAN";
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    JSONArray jSONArray = jSONObject.getJSONArray("OFFLINE");
                    JSONArray unused = Network_Status_Display_Activity.this.Total_Array = jSONObject.getJSONArray("TOTAL");
                    Network_Status_Display_Activity.this.tbl_detail.removeAllViews();
                    TableRow tableRow = new TableRow(Network_Status_Display_Activity.this);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                    TextView textView = new TextView(Network_Status_Display_Activity.this);
                    textView.setText("SR");
                    textView.setTextColor(-1);
                    textView.setGravity(17);
                    textView.setPadding(20, 15, 20, 15);
                    textView.setTypeface((Typeface) null, 1);
                    textView.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView.setBackgroundResource(R.drawable.new_style1);
                    TextView textView2 = new TextView(Network_Status_Display_Activity.this);
                    textView2.setText("DESCRIPTION");
                    textView2.setTextColor(-1);
                    textView2.setGravity(17);
                    textView2.setPadding(20, 15, 20, 15);
                    textView2.setTypeface((Typeface) null, 1);
                    textView2.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView2.setBackgroundResource(R.drawable.new_style1);
                    TextView textView3 = new TextView(Network_Status_Display_Activity.this);
                    textView3.setText(CodePackage.LOCATION);
                    textView3.setTextColor(-1);
                    textView3.setGravity(17);
                    textView3.setPadding(20, 15, 20, 15);
                    textView3.setTypeface((Typeface) null, 1);
                    textView3.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView3.setBackgroundResource(R.drawable.new_style1);
                    TextView textView4 = new TextView(Network_Status_Display_Activity.this);
                    textView4.setText("IP");
                    textView4.setTextColor(-1);
                    textView4.setGravity(17);
                    textView4.setPadding(20, 15, 20, 15);
                    textView4.setTypeface((Typeface) null, 1);
                    textView4.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView4.setBackgroundResource(R.drawable.new_style1);
                    TextView textView5 = new TextView(Network_Status_Display_Activity.this);
                    textView5.setText(str2);
                    textView5.setTextColor(-1);
                    textView5.setGravity(17);
                    textView5.setPadding(20, 15, 20, 15);
                    textView5.setTypeface((Typeface) null, 1);
                    textView5.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView5.setBackgroundResource(R.drawable.new_style1);
                    TextView textView6 = new TextView(Network_Status_Display_Activity.this);
                    textView6.setText("MAKE");
                    textView6.setTextColor(-1);
                    textView6.setGravity(17);
                    textView6.setPadding(20, 15, 20, 15);
                    textView6.setTypeface((Typeface) null, 1);
                    textView6.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView6.setBackgroundResource(R.drawable.new_style1);
                    TextView textView7 = new TextView(Network_Status_Display_Activity.this);
                    textView7.setText("STATUS");
                    textView7.setTextColor(-1);
                    textView7.setGravity(17);
                    textView7.setPadding(20, 15, 20, 15);
                    textView7.setTypeface((Typeface) null, 1);
                    textView7.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView7.setBackgroundResource(R.drawable.new_style1);
                    TextView textView8 = new TextView(Network_Status_Display_Activity.this);
                    textView8.setText("STATUS-DURATION");
                    textView8.setTextColor(-1);
                    textView8.setGravity(17);
                    JSONArray jSONArray2 = jSONArray;
                    textView8.setPadding(20, 15, 20, 15);
                    textView8.setTypeface((Typeface) null, 1);
                    textView8.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView8.setBackgroundResource(R.drawable.new_style1);
                    TextView textView9 = new TextView(Network_Status_Display_Activity.this);
                    textView9.setText("STATUS-SINCE");
                    textView9.setTextColor(-1);
                    textView9.setGravity(17);
                    String str3 = "STATUS";
                    textView9.setPadding(20, 15, 20, 15);
                    textView9.setTypeface((Typeface) null, 1);
                    textView9.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView9.setBackgroundResource(R.drawable.new_style1);
                    TextView textView10 = new TextView(Network_Status_Display_Activity.this);
                    textView10.setText("SAMPLED TIME");
                    textView10.setTextColor(-1);
                    textView10.setGravity(17);
                    String str4 = "MAKE";
                    textView10.setPadding(20, 15, 20, 15);
                    textView10.setTypeface((Typeface) null, 1);
                    textView10.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                    textView10.setBackgroundResource(R.drawable.new_style1);
                    tableRow.addView(textView);
                    tableRow.addView(textView2);
                    tableRow.addView(textView3);
                    tableRow.addView(textView4);
                    tableRow.addView(textView5);
                    tableRow.addView(textView6);
                    tableRow.addView(textView7);
                    tableRow.addView(textView8);
                    tableRow.addView(textView9);
                    tableRow.addView(textView10);
                    Network_Status_Display_Activity.this.tbl_detail.addView(tableRow);
                    int i = 0;
                    while (i < Network_Status_Display_Activity.this.Total_Array.length()) {
                        JSONObject jSONObject2 = Network_Status_Display_Activity.this.Total_Array.getJSONObject(i);
                        TableRow tableRow2 = new TableRow(Network_Status_Display_Activity.this);
                        tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                        TextView textView11 = new TextView(Network_Status_Display_Activity.this);
                        int i2 = i + 1;
                        textView11.setText(Integer.toString(i2));
                        textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView11.setGravity(17);
                        textView11.setPadding(20, 15, 20, 15);
                        textView11.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView11.setBackgroundResource(R.drawable.style17);
                        TextView textView12 = new TextView(Network_Status_Display_Activity.this);
                        textView12.setText(jSONObject2.getString("DESCRIPTION").toUpperCase());
                        textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView12.setGravity(17);
                        textView12.setPadding(20, 15, 20, 15);
                        textView12.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView12.setBackgroundResource(R.drawable.style17);
                        TextView textView13 = new TextView(Network_Status_Display_Activity.this);
                        textView13.setText(jSONObject2.getString(CodePackage.LOCATION));
                        textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView13.setGravity(17);
                        textView13.setPadding(20, 15, 20, 15);
                        textView13.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView13.setBackgroundResource(R.drawable.style17);
                        TextView textView14 = new TextView(Network_Status_Display_Activity.this);
                        textView14.setText(jSONObject2.getString("IP"));
                        textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView14.setGravity(17);
                        textView14.setPadding(20, 15, 20, 15);
                        textView14.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView14.setBackgroundResource(R.drawable.style17);
                        TextView textView15 = new TextView(Network_Status_Display_Activity.this);
                        textView15.setText(jSONObject2.getString(str2));
                        textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView15.setGravity(17);
                        textView15.setPadding(20, 15, 20, 15);
                        textView15.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView15.setBackgroundResource(R.drawable.style17);
                        TextView textView16 = new TextView(Network_Status_Display_Activity.this);
                        String str5 = str4;
                        textView16.setText(jSONObject2.getString(str5));
                        textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView16.setGravity(17);
                        textView16.setPadding(20, 15, 20, 15);
                        textView16.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView16.setBackgroundResource(R.drawable.style17);
                        TextView textView17 = new TextView(Network_Status_Display_Activity.this);
                        String str6 = str3;
                        textView17.setText(jSONObject2.getString(str6));
                        textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView17.setGravity(17);
                        int i3 = i2;
                        textView17.setPadding(20, 15, 20, 15);
                        textView17.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView17.setBackgroundResource(R.drawable.style17);
                        TextView textView18 = new TextView(Network_Status_Display_Activity.this);
                        textView18.setText(jSONObject2.getString("DURATION"));
                        textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView18.setGravity(17);
                        String str7 = str5;
                        textView18.setPadding(20, 15, 20, 15);
                        textView18.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView18.setBackgroundResource(R.drawable.style17);
                        TextView textView19 = new TextView(Network_Status_Display_Activity.this);
                        textView19.setText(jSONObject2.getString("STATUS_SINCE"));
                        textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView19.setGravity(17);
                        String str8 = str2;
                        textView19.setPadding(20, 15, 20, 15);
                        textView19.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView19.setBackgroundResource(R.drawable.style17);
                        TextView textView20 = new TextView(Network_Status_Display_Activity.this);
                        textView20.setText(jSONObject2.getString("SAMPLED_TIME"));
                        textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView20.setGravity(17);
                        TableRow tableRow3 = tableRow2;
                        textView20.setPadding(20, 15, 20, 15);
                        textView20.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        textView20.setBackgroundResource(R.drawable.style17);
                        if (!jSONObject2.getString(str6).equals("OFFLINE")) {
                            if (!jSONObject2.getString(str6).equals("UNMANAGED")) {
                                if (jSONObject2.getString(str6).equals("ONLINE")) {
                                    textView17.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorGreen));
                                    textView17.setTypeface((Typeface) null, 1);
                                }
                                TableRow tableRow4 = tableRow3;
                                tableRow4.addView(textView11);
                                tableRow4.addView(textView12);
                                tableRow4.addView(textView13);
                                tableRow4.addView(textView14);
                                tableRow4.addView(textView15);
                                tableRow4.addView(textView16);
                                tableRow4.addView(textView17);
                                tableRow4.addView(textView18);
                                tableRow4.addView(textView19);
                                tableRow4.addView(textView20);
                                Network_Status_Display_Activity.this.tbl_detail.addView(tableRow4);
                                str3 = str6;
                                i = i3;
                                str4 = str7;
                                str2 = str8;
                            }
                        }
                        textView12.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView12.setTypeface((Typeface) null, 1);
                        textView13.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView13.setTypeface((Typeface) null, 1);
                        textView14.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView14.setTypeface((Typeface) null, 1);
                        textView15.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView15.setTypeface((Typeface) null, 1);
                        textView16.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView16.setTypeface((Typeface) null, 1);
                        textView17.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView17.setTypeface((Typeface) null, 1);
                        textView18.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView18.setTypeface((Typeface) null, 1);
                        textView19.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView19.setTypeface((Typeface) null, 1);
                        textView20.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView20.setTypeface((Typeface) null, 1);
                        TableRow tableRow42 = tableRow3;
                        tableRow42.addView(textView11);
                        tableRow42.addView(textView12);
                        tableRow42.addView(textView13);
                        tableRow42.addView(textView14);
                        tableRow42.addView(textView15);
                        tableRow42.addView(textView16);
                        tableRow42.addView(textView17);
                        tableRow42.addView(textView18);
                        tableRow42.addView(textView19);
                        tableRow42.addView(textView20);
                        Network_Status_Display_Activity.this.tbl_detail.addView(tableRow42);
                        str3 = str6;
                        i = i3;
                        str4 = str7;
                        str2 = str8;
                    }
                    String str9 = str2;
                    String str10 = str4;
                    if (jSONArray2.length() == 0) {
                        Network_Status_Display_Activity.this.lay_brief_1.setVisibility(4);
                        Network_Status_Display_Activity.this.lay_brief_2.setVisibility(0);
                        Network_Status_Display_Activity.this.txt_congrate.setText("CONGRATULATIONS NO " + str3 + " IS DOWN AT PRESENT");
                    } else {
                        Network_Status_Display_Activity.this.lay_brief_2.setVisibility(4);
                        Network_Status_Display_Activity.this.lay_brief_1.setVisibility(0);
                        Network_Status_Display_Activity.this.lay_brief_1.removeAllViews();
                        int i4 = 0;
                        while (i4 < jSONArray2.length()) {
                            JSONArray jSONArray3 = jSONArray2;
                            final JSONObject jSONObject3 = jSONArray3.getJSONObject(i4);
                            final LinearLayout linearLayout = new LinearLayout(Network_Status_Display_Activity.this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.setMargins(0, 10, 0, 10);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.setOrientation(1);
                            linearLayout.setPadding(10, 10, 10, 10);
                            linearLayout.setBackgroundResource(R.drawable.new_style);
                            LinearLayout linearLayout2 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout2.setOrientation(0);
                            linearLayout2.setGravity(17);
                            linearLayout2.setPadding(10, 10, 10, 10);
                            linearLayout2.setBackgroundResource(R.drawable.new_style10);
                            TextView textView21 = new TextView(Network_Status_Display_Activity.this);
                            textView21.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                            int i5 = i4 + 1;
                            textView21.setText(Integer.toString(i5));
                            textView21.setTextColor(-1);
                            textView21.setGravity(17);
                            textView21.setPadding(5, 5, 5, 5);
                            textView21.setTypeface((Typeface) null, 1);
                            textView21.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView21.setBackgroundResource(R.drawable.ic_new_circle_red);
                            LinearLayout linearLayout3 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 9.0f));
                            linearLayout3.setOrientation(1);
                            linearLayout3.setGravity(17);
                            linearLayout3.setPadding(5, 5, 5, 0);
                            TextView textView22 = new TextView(Network_Status_Display_Activity.this);
                            textView22.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView22.setText(jSONObject3.getString("DESCRIPTION").toUpperCase());
                            textView22.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView22.setGravity(17);
                            textView22.setTypeface((Typeface) null, 1);
                            textView22.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            TextView textView23 = new TextView(Network_Status_Display_Activity.this);
                            textView23.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView23.setText("(" + jSONObject3.getString(CodePackage.LOCATION) + ")");
                            textView23.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView23.setGravity(17);
                            textView23.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout3.addView(textView22);
                            linearLayout3.addView(textView23);
                            ImageView imageView = new ImageView(Network_Status_Display_Activity.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            imageView.setPadding(10, 10, 10, 10);
                            imageView.setClickable(true);
                            imageView.setImageResource(R.drawable.ic_new_share);
                            linearLayout2.addView(textView21);
                            linearLayout2.addView(linearLayout3);
                            linearLayout2.addView(imageView);
                            LinearLayout linearLayout4 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout4.setOrientation(0);
                            linearLayout4.setGravity(17);
                            linearLayout4.setPadding(5, 10, 5, 0);
                            TextView textView24 = new TextView(Network_Status_Display_Activity.this);
                            textView24.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView24.setText("IP-DETAIL");
                            textView24.setPaintFlags(8);
                            textView24.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView24.setGravity(17);
                            textView24.setTypeface((Typeface) null, 1);
                            textView24.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView25 = new TextView(Network_Status_Display_Activity.this);
                            textView25.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            String str11 = str9;
                            textView25.setText(str11);
                            textView25.setPaintFlags(8);
                            textView25.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView25.setGravity(17);
                            textView25.setTypeface((Typeface) null, 1);
                            textView25.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView26 = new TextView(Network_Status_Display_Activity.this);
                            int i6 = i5;
                            textView26.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            String str12 = str10;
                            textView26.setText(str12);
                            textView26.setPaintFlags(8);
                            textView26.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView26.setGravity(17);
                            textView26.setTypeface((Typeface) null, 1);
                            textView26.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout4.addView(textView24);
                            linearLayout4.addView(textView25);
                            linearLayout4.addView(textView26);
                            LinearLayout linearLayout5 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout5.setOrientation(0);
                            linearLayout5.setGravity(17);
                            linearLayout5.setPadding(5, 5, 5, 10);
                            TextView textView27 = new TextView(Network_Status_Display_Activity.this);
                            textView27.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView27.setText(jSONObject3.getString("IP"));
                            textView27.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView27.setGravity(17);
                            textView27.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView28 = new TextView(Network_Status_Display_Activity.this);
                            JSONArray jSONArray4 = jSONArray3;
                            textView28.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView28.setText(jSONObject3.getString(str11));
                            textView28.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView28.setGravity(17);
                            textView28.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView29 = new TextView(Network_Status_Display_Activity.this);
                            String str13 = str11;
                            textView29.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView29.setText(jSONObject3.getString(str12));
                            textView29.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView29.setGravity(17);
                            textView29.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout5.addView(textView27);
                            linearLayout5.addView(textView28);
                            linearLayout5.addView(textView29);
                            LinearLayout linearLayout6 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout6.setOrientation(0);
                            linearLayout6.setGravity(17);
                            linearLayout6.setPadding(5, 5, 5, 0);
                            TextView textView30 = new TextView(Network_Status_Display_Activity.this);
                            textView30.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView30.setText("OUTAGE DURATION");
                            textView30.setPaintFlags(8);
                            textView30.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView30.setGravity(17);
                            textView30.setTypeface((Typeface) null, 1);
                            textView30.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView31 = new TextView(Network_Status_Display_Activity.this);
                            textView31.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView31.setText("OUTAGE SINCE");
                            textView31.setPaintFlags(8);
                            textView31.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView31.setGravity(17);
                            textView31.setTypeface((Typeface) null, 1);
                            textView31.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView32 = new TextView(Network_Status_Display_Activity.this);
                            String str14 = str12;
                            textView32.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView32.setText("SAMPLED TIME");
                            textView32.setPaintFlags(8);
                            textView32.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorblack));
                            textView32.setGravity(17);
                            textView32.setTypeface((Typeface) null, 1);
                            textView32.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout6.addView(textView30);
                            linearLayout6.addView(textView31);
                            linearLayout6.addView(textView32);
                            LinearLayout linearLayout7 = new LinearLayout(Network_Status_Display_Activity.this);
                            linearLayout7.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout7.setOrientation(0);
                            linearLayout7.setGravity(17);
                            linearLayout7.setPadding(5, 5, 5, 5);
                            TextView textView33 = new TextView(Network_Status_Display_Activity.this);
                            textView33.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView33.setText(jSONObject3.getString("DURATION"));
                            textView33.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView33.setGravity(17);
                            textView33.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView34 = new TextView(Network_Status_Display_Activity.this);
                            textView34.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView34.setText(jSONObject3.getString("STATUS_SINCE"));
                            textView34.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView34.setGravity(17);
                            textView34.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView35 = new TextView(Network_Status_Display_Activity.this);
                            textView35.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView35.setText(jSONObject3.getString("SAMPLED_TIME"));
                            textView35.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView35.setGravity(17);
                            textView35.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout7.addView(textView33);
                            linearLayout7.addView(textView34);
                            linearLayout7.addView(textView35);
                            TextView textView36 = new TextView(Network_Status_Display_Activity.this);
                            textView36.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView36.setText("BBM DETAIL : " + jSONObject3.getString("BBC").trim());
                            textView36.setPaintFlags(8);
                            textView36.setTextColor(Network_Status_Display_Activity.this.getResources().getColor(R.color.colorGreen));
                            textView36.setGravity(16);
                            textView36.setTypeface((Typeface) null, 1);
                            textView36.setTextSize(0, Network_Status_Display_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView36.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_new_phone1, 0, 0, 0);
                            textView36.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    try {
                                        String trim = jSONObject3.getString("BBC_MOBILE").trim();
                                        if (jSONObject3.getString("BBC_MOBILE").trim().isEmpty()) {
                                            Network_Status_Display_Activity.this.txt_alert.setText("The " + str3 + " Is Not Mapped To Any BBC\nPlease Contact Your Franchisee Manager");
                                            Network_Status_Display_Activity.this.error_dialog.show();
                                            return;
                                        }
                                        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + trim));
                                        intent.setFlags(67108864);
                                        Network_Status_Display_Activity.this.startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Bitmap createBitmap = Bitmap.createBitmap(linearLayout.getWidth(), linearLayout.getHeight(), Bitmap.Config.ARGB_8888);
                                    linearLayout.draw(new Canvas(createBitmap));
                                    new ShareImage().share(createBitmap, new File(Network_Status_Display_Activity.this.getExternalCacheDir(), "network_glance.jpg"), Network_Status_Display_Activity.this);
                                }
                            });
                            linearLayout.addView(linearLayout2);
                            linearLayout.addView(linearLayout4);
                            linearLayout.addView(linearLayout5);
                            linearLayout.addView(linearLayout6);
                            linearLayout.addView(linearLayout7);
                            if (!jSONObject3.getString("BBC_MOBILE").trim().equals("null")) {
                                linearLayout.addView(textView36);
                            }
                            Network_Status_Display_Activity.this.lay_brief_1.addView(linearLayout);
                            jSONArray2 = jSONArray4;
                            i4 = i6;
                            str9 = str13;
                            str10 = str14;
                        }
                    }
                    Network_Status_Display_Activity.this.txt_header.setText("CIRCLE : " + str + " | SSA : " + str2);
                    Network_Status_Display_Activity.this.txt_header.setPaintFlags(8);
                    Network_Status_Display_Activity.this.progress_dialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Network_Status_Display_Activity.this.progress_dialog.cancel();
                Network_Status_Display_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Network_Status_Display_Activity.this.getApplicationContext()));
                Network_Status_Display_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", str4);
                hashMap.put("ssa", str5);
                hashMap.put("element", str6);
                hashMap.put("username", Network_Status_Display_Activity.this.Pref_Username);
                hashMap.put("random_key", Network_Status_Display_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Network_Status_Display_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    private void share(Bitmap bitmap) {
        File file = new File(getApplicationContext().getExternalCacheDir(), File.separator + "test.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            file.setReadable(true, false);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getApplicationContext(), "bsnl.bsnl_teevra.provider", file));
            intent.setFlags(268435456);
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.progress_dialog.show();
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
        String obj = this.et_search.getText().toString();
        this.tbl_detail.removeAllViews();
        this.progress_dialog.cancel();
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
        textView2.setText("DESCRIPTION");
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
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(this);
        textView5.setText("VLAN");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(this);
        textView6.setText("MAKE");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        TextView textView7 = new TextView(this);
        textView7.setText("STATUS");
        String str9 = "MAKE";
        textView7.setTextColor(-1);
        textView7.setGravity(17);
        String str10 = "VLAN";
        textView7.setPadding(20, 15, 20, 15);
        textView7.setTypeface((Typeface) null, 1);
        textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView7.setBackgroundResource(R.drawable.new_style1);
        TextView textView8 = new TextView(this);
        textView8.setText("STATUS-DURATION");
        textView8.setTextColor(-1);
        textView8.setGravity(17);
        String str11 = "IP";
        textView8.setPadding(20, 15, 20, 15);
        textView8.setTypeface((Typeface) null, 1);
        textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView8.setBackgroundResource(R.drawable.new_style1);
        TextView textView9 = new TextView(this);
        textView9.setText("STATUS-SINCE");
        textView9.setTextColor(-1);
        textView9.setGravity(17);
        String str12 = "DESCRIPTION";
        textView9.setPadding(20, 15, 20, 15);
        textView9.setTypeface((Typeface) null, 1);
        textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView9.setBackgroundResource(R.drawable.new_style1);
        TextView textView10 = new TextView(this);
        textView10.setText("SAMPLED TIME");
        textView10.setTextColor(-1);
        textView10.setGravity(17);
        String str13 = CodePackage.LOCATION;
        textView10.setPadding(20, 15, 20, 15);
        textView10.setTypeface((Typeface) null, 1);
        textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView10.setBackgroundResource(R.drawable.new_style1);
        tableRow.addView(textView);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView5);
        tableRow.addView(textView6);
        tableRow.addView(textView7);
        tableRow.addView(textView8);
        tableRow.addView(textView9);
        tableRow.addView(textView10);
        this.tbl_detail.addView(tableRow);
        int i2 = 1;
        int i3 = 0;
        while (i3 < this.Total_Array.length()) {
            try {
                JSONObject jSONObject = this.Total_Array.getJSONObject(i3);
                if (!jSONObject.getString("STATUS").toUpperCase().contains(obj.toUpperCase())) {
                    str8 = str13;
                    if (!jSONObject.getString(str8).toUpperCase().contains(obj.toUpperCase())) {
                        str7 = str12;
                        if (!jSONObject.getString(str7).toUpperCase().contains(obj.toUpperCase())) {
                            str6 = str11;
                            if (!jSONObject.getString(str6).toUpperCase().contains(obj.toUpperCase())) {
                                str5 = str10;
                                if (!jSONObject.getString(str5).toUpperCase().contains(obj.toUpperCase())) {
                                    str4 = str9;
                                    if (!jSONObject.getString(str4).toUpperCase().contains(obj.toUpperCase())) {
                                        str3 = obj;
                                        str13 = str8;
                                        str12 = str7;
                                        str11 = str6;
                                        str2 = str5;
                                        str = str4;
                                        i = i3;
                                        i3 = i + 1;
                                        obj = str3;
                                        str10 = str2;
                                        str9 = str;
                                    }
                                } else {
                                    str4 = str9;
                                }
                            } else {
                                str4 = str9;
                                str5 = str10;
                            }
                        } else {
                            str4 = str9;
                            str5 = str10;
                            str6 = str11;
                        }
                    } else {
                        str4 = str9;
                        str5 = str10;
                        str6 = str11;
                        str7 = str12;
                    }
                } else {
                    str4 = str9;
                    str5 = str10;
                    str6 = str11;
                    str7 = str12;
                    str8 = str13;
                }
                TableRow tableRow2 = new TableRow(this);
                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                TextView textView11 = new TextView(this);
                int i4 = i2 + 1;
                textView11.setText(Integer.toString(i2));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(this);
                textView12.setText(jSONObject.getString(str7).toUpperCase());
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                TextView textView13 = new TextView(this);
                textView13.setText(jSONObject.getString(str8));
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                str3 = obj;
                textView13.setPadding(20, 15, 20, 15);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView13.setBackgroundResource(R.drawable.style17);
                TextView textView14 = new TextView(this);
                textView14.setText(jSONObject.getString(str6));
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                str13 = str8;
                textView14.setPadding(20, 15, 20, 15);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView14.setBackgroundResource(R.drawable.style17);
                TextView textView15 = new TextView(this);
                textView15.setText(jSONObject.getString(str5));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                str12 = str7;
                textView15.setPadding(20, 15, 20, 15);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView15.setBackgroundResource(R.drawable.style17);
                TextView textView16 = new TextView(this);
                textView16.setText(jSONObject.getString(str4));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                str11 = str6;
                textView16.setPadding(20, 15, 20, 15);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView16.setBackgroundResource(R.drawable.style17);
                TextView textView17 = new TextView(this);
                textView17.setText(jSONObject.getString("STATUS"));
                textView17.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView17.setGravity(17);
                str2 = str5;
                textView17.setPadding(20, 15, 20, 15);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView17.setBackgroundResource(R.drawable.style17);
                TextView textView18 = new TextView(this);
                textView18.setText(jSONObject.getString("DURATION"));
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                str = str4;
                textView18.setPadding(20, 15, 20, 15);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView18.setBackgroundResource(R.drawable.style17);
                TextView textView19 = new TextView(this);
                textView19.setText(jSONObject.getString("STATUS_SINCE"));
                textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView19.setGravity(17);
                int i5 = i4;
                textView19.setPadding(20, 15, 20, 15);
                textView19.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView19.setBackgroundResource(R.drawable.style17);
                TextView textView20 = new TextView(this);
                textView20.setText(jSONObject.getString("SAMPLED_TIME"));
                textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView20.setGravity(17);
                i = i3;
                textView20.setPadding(20, 15, 20, 15);
                textView20.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView20.setBackgroundResource(R.drawable.style17);
                if (jSONObject.getString("STATUS").equals("OFFLINE")) {
                    textView12.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView12.setTypeface((Typeface) null, 1);
                    textView13.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView13.setTypeface((Typeface) null, 1);
                    textView14.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView14.setTypeface((Typeface) null, 1);
                    textView15.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView15.setTypeface((Typeface) null, 1);
                    textView16.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView16.setTypeface((Typeface) null, 1);
                    textView17.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView17.setTypeface((Typeface) null, 1);
                    textView18.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView18.setTypeface((Typeface) null, 1);
                    textView19.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView19.setTypeface((Typeface) null, 1);
                    textView20.setTextColor(SupportMenu.CATEGORY_MASK);
                    textView20.setTypeface((Typeface) null, 1);
                } else if (jSONObject.getString("STATUS").equals("ONLINE")) {
                    textView17.setTextColor(getResources().getColor(R.color.colorGreen));
                    textView17.setTypeface((Typeface) null, 1);
                    tableRow2.addView(textView11);
                    tableRow2.addView(textView12);
                    tableRow2.addView(textView13);
                    tableRow2.addView(textView14);
                    tableRow2.addView(textView15);
                    tableRow2.addView(textView16);
                    tableRow2.addView(textView17);
                    tableRow2.addView(textView18);
                    tableRow2.addView(textView19);
                    tableRow2.addView(textView20);
                    this.tbl_detail.addView(tableRow2);
                    i2 = i5;
                    i3 = i + 1;
                    obj = str3;
                    str10 = str2;
                    str9 = str;
                }
                tableRow2.addView(textView11);
                tableRow2.addView(textView12);
                tableRow2.addView(textView13);
                tableRow2.addView(textView14);
                tableRow2.addView(textView15);
                tableRow2.addView(textView16);
                tableRow2.addView(textView17);
                tableRow2.addView(textView18);
                tableRow2.addView(textView19);
                tableRow2.addView(textView20);
                this.tbl_detail.addView(tableRow2);
                i2 = i5;
                i3 = i + 1;
                obj = str3;
                str10 = str2;
                str9 = str;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.progress_dialog.cancel();
    }
}
