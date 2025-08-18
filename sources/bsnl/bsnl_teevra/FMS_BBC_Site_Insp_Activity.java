package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import bsnl.bsnl_teevra.utils.CustomAnimationUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_BBC_Site_Insp_Activity extends AppCompatActivity implements View.OnClickListener {
    public static final int LOCATION_PERMISSION_REQ = 1001;
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
    private TextView btn_submit;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private AlertDialog geo_dialog;
    private ImageView img_cal;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay1;
    /* access modifiers changed from: private */
    public LinearLayout lay2;
    private LinearLayout lay_site_detail;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TabLayout tab_site;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_inspection;
    /* access modifiers changed from: private */
    public ViewPager viewpager_site;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bbc_site_insp_activity);
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
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.img_cal = (ImageView) findViewById(R.id.img_cal);
        this.txt_inspection = (TextView) findViewById(R.id.txt_inspection);
        this.btn_submit = (TextView) findViewById(R.id.btn_submit);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        this.img_cal.setOnClickListener(this);
        this.txt_inspection.setOnClickListener(this);
        this.btn_submit.setOnClickListener(this);
        this.tab_site = (TabLayout) findViewById(R.id.tab_site);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_site);
        this.viewpager_site = viewPager;
        viewPager.setOffscreenPageLimit(2);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_cal || view.getId() == R.id.txt_inspection) {
            Dialog dialog = new Dialog(this, 2131821126);
            dialog.setContentView(R.layout.custom_month_year_picker);
            dialog.getWindow().setLayout(-1, -2);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setCancelable(false);
            TextView textView = (TextView) dialog.findViewById(R.id.title);
            textView.setTextSize(0, getResources().getDimension(R.dimen.bigtext) * 1.5f);
            NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.month_picker);
            numberPicker.setTextSize(getResources().getDimension(R.dimen.bigtext));
            NumberPicker numberPicker2 = (NumberPicker) dialog.findViewById(R.id.year_picker);
            numberPicker2.setTextSize(getResources().getDimension(R.dimen.bigtext));
            String[] shortMonths = new DateFormatSymbols(Locale.ENGLISH).getShortMonths();
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(11);
            numberPicker.setDisplayedValues((String[]) Arrays.copyOf(shortMonths, 12));
            int i = Calendar.getInstance().get(2);
            numberPicker.setValue(i);
            int i2 = Calendar.getInstance().get(1);
            numberPicker2.setMinValue(2022);
            numberPicker2.setMaxValue(i2);
            numberPicker2.setWrapSelectorWheel(false);
            numberPicker2.setValue(i2);
            numberPicker2.setFormatter(new NumberPicker.Formatter() {
                public String format(int i) {
                    return String.format(Locale.ENGLISH, TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(i)});
                }
            });
            textView.setText(shortMonths[i] + " " + i2);
            numberPicker.setOnValueChangedListener(new FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda0(numberPicker, numberPicker2, shortMonths, textView));
            numberPicker2.setOnValueChangedListener(new FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda1(numberPicker, numberPicker2, shortMonths, textView));
            dialog.findViewById(R.id.txt_cancel).setOnClickListener(new FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda2(dialog));
            dialog.findViewById(R.id.txt_set).setOnClickListener(new FMS_BBC_Site_Insp_Activity$$ExternalSyntheticLambda3(this, numberPicker, numberPicker2, shortMonths, dialog));
            CustomAnimationUtils.fadeInDialog(this, dialog);
            dialog.show();
        } else if (view.getId() == R.id.btn_submit) {
            final String trim = this.txt_inspection.getText().toString().trim();
            if (trim.equals("-- Inspection Period --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT  A VALID INSPECTION PERIOD", getApplicationContext());
                return;
            }
            this.lay2.setVisibility(8);
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass4 r3 = new StringRequest(1, getString(R.string.serverip) + "fms/bbc_site_inspection1.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Log.v("SITEINSPECTION", str.toString());
                    FMS_BBC_Site_Insp_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        JSONArray jSONArray = jSONObject.getJSONArray("BSNL");
                        JSONArray jSONArray2 = jSONObject.getJSONArray("BBNL");
                        JSONArray jSONArray3 = jSONObject.getJSONArray("TIP");
                        if (jSONArray.length() == 0 && jSONArray2.length() == 0 && jSONArray3.length() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMS_BBC_Site_Insp_Activity.this);
                            View inflate = FMS_BBC_Site_Insp_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("OK");
                            textView.setBackgroundResource(0);
                            textView.setGravity(5);
                            textView.setPadding(0, 0, 80, 0);
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused = FMS_BBC_Site_Insp_Activity.this.info_dialog = builder.create();
                            FMS_BBC_Site_Insp_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText("\nNo OLT Is Mapped\nPlease contact your Franchisee Manager");
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    FMS_BBC_Site_Insp_Activity.this.info_dialog.cancel();
                                }
                            });
                            return;
                        }
                        FMS_BBC_Site_Insp_Activity fMS_BBC_Site_Insp_Activity = FMS_BBC_Site_Insp_Activity.this;
                        fMS_BBC_Site_Insp_Activity.setupViewPager(fMS_BBC_Site_Insp_Activity.viewpager_site, jSONArray, jSONArray2, jSONArray3);
                        FMS_BBC_Site_Insp_Activity.this.tab_site.setupWithViewPager(FMS_BBC_Site_Insp_Activity.this.viewpager_site);
                        FMS_BBC_Site_Insp_Activity.this.lay2.setVisibility(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FMS_BBC_Site_Insp_Activity.this.progress_dialog.cancel();
                    FMS_BBC_Site_Insp_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_BBC_Site_Insp_Activity.this.getApplicationContext()));
                    FMS_BBC_Site_Insp_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("circle", FMS_BBC_Site_Insp_Activity.this.Pref_Circle);
                    hashMap.put("ssa", FMS_BBC_Site_Insp_Activity.this.Pref_SSA);
                    hashMap.put("fms_username", FMS_BBC_Site_Insp_Activity.this.Pref_Fms_Username);
                    hashMap.put("inspection_period", trim);
                    hashMap.put("username", FMS_BBC_Site_Insp_Activity.this.Pref_Username);
                    hashMap.put("random_key", FMS_BBC_Site_Insp_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", FMS_BBC_Site_Insp_Activity.this.androidId);
                    return hashMap;
                }
            };
            r3.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r3);
        }
    }

    static /* synthetic */ void lambda$onClick$0(NumberPicker numberPicker, NumberPicker numberPicker2, String[] strArr, TextView textView, NumberPicker numberPicker3, int i, int i2) {
        int value = numberPicker.getValue();
        textView.setText(strArr[value] + " " + numberPicker2.getValue());
    }

    static /* synthetic */ void lambda$onClick$1(NumberPicker numberPicker, NumberPicker numberPicker2, String[] strArr, TextView textView, NumberPicker numberPicker3, int i, int i2) {
        int value = numberPicker.getValue();
        textView.setText(strArr[value] + " " + numberPicker2.getValue());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onClick$3$bsnl-bsnl_teevra-FMS_BBC_Site_Insp_Activity  reason: not valid java name */
    public /* synthetic */ void m245lambda$onClick$3$bsnlbsnl_teevraFMS_BBC_Site_Insp_Activity(NumberPicker numberPicker, NumberPicker numberPicker2, String[] strArr, Dialog dialog, View view) {
        int value = numberPicker.getValue();
        String str = strArr[value] + " " + numberPicker2.getValue();
        this.txt_inspection.setText(str);
        Toast.makeText(this, "Insp Period : " + str, 0).show();
        CustomAnimationUtils.fadeOutDialog(this, dialog);
    }

    /* access modifiers changed from: private */
    public void setupViewPager(ViewPager viewPager, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        FMS_BBC_Site_BSNL_Fragment fMS_BBC_Site_BSNL_Fragment = new FMS_BBC_Site_BSNL_Fragment();
        FMS_BBC_Site_BBNL_Fragment fMS_BBC_Site_BBNL_Fragment = new FMS_BBC_Site_BBNL_Fragment();
        FMS_BBC_Site_TIP_Fragment fMS_BBC_Site_TIP_Fragment = new FMS_BBC_Site_TIP_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("BSNL", jSONArray.toString());
        Bundle bundle2 = new Bundle();
        bundle2.putString("BBNL", jSONArray2.toString());
        Bundle bundle3 = new Bundle();
        bundle3.putString("TIP", jSONArray3.toString());
        fMS_BBC_Site_BSNL_Fragment.setArguments(bundle);
        fMS_BBC_Site_BBNL_Fragment.setArguments(bundle2);
        fMS_BBC_Site_TIP_Fragment.setArguments(bundle3);
        if (jSONArray.length() > 0) {
            sectionPageAdapter.addFragments(fMS_BBC_Site_BSNL_Fragment, "BSNL");
        }
        if (jSONArray2.length() > 0) {
            sectionPageAdapter.addFragments(fMS_BBC_Site_BBNL_Fragment, "BBNL");
        }
        if (jSONArray3.length() > 0) {
            sectionPageAdapter.addFragments(fMS_BBC_Site_TIP_Fragment, "TIP");
        }
        viewPager.setAdapter(sectionPageAdapter);
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
                    FMS_BBC_Site_Insp_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_BBC_Site_Insp_Activity fMS_BBC_Site_Insp_Activity = FMS_BBC_Site_Insp_Activity.this;
                    fMS_BBC_Site_Insp_Activity.editor = fMS_BBC_Site_Insp_Activity.sharedPreferences.edit();
                    FMS_BBC_Site_Insp_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_BBC_Site_Insp_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_BBC_Site_Insp_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_BBC_Site_Insp_Activity.this.startActivity(intent);
                    FMS_BBC_Site_Insp_Activity.this.finish();
                    FMS_BBC_Site_Insp_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
