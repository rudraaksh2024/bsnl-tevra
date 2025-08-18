package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PMS_Setting_Tip_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    private ArrayAdapter<String> adapter_fms_username;
    private ArrayAdapter<String> adapter_vendor;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_pms_tip;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_firmaname;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_username;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_tip_pms;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    private Spinner sp_tip_vendor;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private AlertDialog uplink_dialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_setting_tip_activity);
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
        this.sp_tip_vendor = (Spinner) findViewById(R.id.sp_tip_vendor);
        this.btn_pms_tip = (TextView) findViewById(R.id.btn_pms_tip);
        this.lay_tip_pms = (LinearLayout) findViewById(R.id.lay_tip_pms);
        this.fms_firmaname = new ArrayList<>();
        this.fms_username = new ArrayList<>();
        this.fms_firmaname.add("-- VENDOR --");
        this.fms_username.add("-- FMS-USERNAME --");
        this.adapter_vendor = new ArrayAdapter<>(this, R.layout.spinner_item, this.fms_firmaname);
        this.adapter_fms_username = new ArrayAdapter<>(this, R.layout.spinner_item, this.fms_username);
        this.sp_tip_vendor.setAdapter(this.adapter_vendor);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "tip_vendor_populate_new.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                PMS_Setting_Tip_Activity.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        PMS_Setting_Tip_Activity.this.fms_firmaname.add(jSONObject.getString("FMS_FIRM_NAME"));
                        PMS_Setting_Tip_Activity.this.fms_username.add(jSONObject.getString("FMS_USERNAME"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                PMS_Setting_Tip_Activity.this.progress_dialog.cancel();
                PMS_Setting_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Setting_Tip_Activity.this.getApplicationContext()));
                PMS_Setting_Tip_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", PMS_Setting_Tip_Activity.this.Pref_Circle);
                hashMap.put("ssa", PMS_Setting_Tip_Activity.this.Pref_SSA);
                hashMap.put("username", PMS_Setting_Tip_Activity.this.Pref_Username);
                hashMap.put("random_key", PMS_Setting_Tip_Activity.this.Pref_Randkey);
                hashMap.put("device_id", PMS_Setting_Tip_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.imageView.setOnClickListener(this);
        this.btn_pms_tip.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.btn_pms_tip) {
            this.lay_tip_pms.removeAllViews();
            String str = getString(R.string.serverip) + "PMS-TIP/pms_inventory_tip.php";
            final String str2 = this.adapter_fms_username.getItem(this.sp_tip_vendor.getSelectedItemPosition()).toString();
            final String obj = this.sp_tip_vendor.getSelectedItem().toString();
            if (str2.equals("-- FMS-USERNAME --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT VENDOR CODE !!", this);
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass6 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.widget.TextView} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: bsnl.bsnl_teevra.PMS_Setting_Tip_Activity$4} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: bsnl.bsnl_teevra.PMS_Setting_Tip_Activity$4} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: bsnl.bsnl_teevra.PMS_Setting_Tip_Activity$4} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onResponse(java.lang.String r22) {
                    /*
                        r21 = this;
                        r1 = r21
                        java.lang.String r0 = "UPLINK"
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r2 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this
                        android.app.AlertDialog r2 = r2.progress_dialog
                        r2.cancel()
                        r2 = 0
                        org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x021f }
                        r4 = r22
                        r3.<init>(r4)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r4 = "ROWSET"
                        org.json.JSONArray r3 = r3.getJSONArray(r4)     // Catch:{ JSONException -> 0x021f }
                        int r4 = r3.length()     // Catch:{ JSONException -> 0x021f }
                        if (r4 != 0) goto L_0x0030
                        bsnl.bsnl_teevra.AlertHelperclass r0 = new bsnl.bsnl_teevra.AlertHelperclass     // Catch:{ JSONException -> 0x021f }
                        r0.<init>()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r3 = "No Olt Is Mapped\nKindly Contact Nodal Officer"
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r4 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021f }
                        r0.ntoast(r3, r4)     // Catch:{ JSONException -> 0x021f }
                        r3 = r1
                        goto L_0x0224
                    L_0x0030:
                        android.widget.LinearLayout r4 = new android.widget.LinearLayout     // Catch:{ JSONException -> 0x021f }
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r5 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021f }
                        r4.<init>(r5)     // Catch:{ JSONException -> 0x021f }
                        android.widget.LinearLayout$LayoutParams r5 = new android.widget.LinearLayout$LayoutParams     // Catch:{ JSONException -> 0x021f }
                        r6 = -2
                        r7 = -1
                        r5.<init>(r7, r6)     // Catch:{ JSONException -> 0x021f }
                        r8 = 10
                        r5.setMargins(r2, r8, r2, r8)     // Catch:{ JSONException -> 0x021f }
                        r4.setLayoutParams(r5)     // Catch:{ JSONException -> 0x021f }
                        r5 = 1
                        r4.setOrientation(r5)     // Catch:{ JSONException -> 0x021f }
                        r9 = 17
                        r4.setGravity(r9)     // Catch:{ JSONException -> 0x021f }
                        r10 = 2131165509(0x7f070145, float:1.7945237E38)
                        r4.setBackgroundResource(r10)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r10 = new android.widget.TextView     // Catch:{ JSONException -> 0x021f }
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r11 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021f }
                        r10.<init>(r11)     // Catch:{ JSONException -> 0x021f }
                        android.widget.LinearLayout$LayoutParams r11 = new android.widget.LinearLayout$LayoutParams     // Catch:{ JSONException -> 0x021f }
                        r11.<init>(r7, r6)     // Catch:{ JSONException -> 0x021f }
                        r11.setMargins(r2, r2, r2, r8)     // Catch:{ JSONException -> 0x021f }
                        r10.setLayoutParams(r11)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r11 = r10     // Catch:{ JSONException -> 0x021f }
                        r10.setText(r11)     // Catch:{ JSONException -> 0x021f }
                        r10.setTextColor(r7)     // Catch:{ JSONException -> 0x021f }
                        r10.setGravity(r9)     // Catch:{ JSONException -> 0x021f }
                        r9 = 15
                        r10.setPadding(r9, r9, r9, r9)     // Catch:{ JSONException -> 0x021f }
                        r9 = 0
                        r10.setTypeface(r9, r5)     // Catch:{ JSONException -> 0x021f }
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r5 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021f }
                        android.content.res.Resources r5 = r5.getResources()     // Catch:{ JSONException -> 0x021f }
                        r11 = 2131100011(0x7f06016b, float:1.7812391E38)
                        float r5 = r5.getDimension(r11)     // Catch:{ JSONException -> 0x021f }
                        r10.setTextSize(r2, r5)     // Catch:{ JSONException -> 0x021f }
                        r5 = 2131165512(0x7f070148, float:1.7945243E38)
                        r10.setBackgroundResource(r5)     // Catch:{ JSONException -> 0x021f }
                        r4.addView(r10)     // Catch:{ JSONException -> 0x021f }
                        r5 = r2
                    L_0x0095:
                        int r10 = r3.length()     // Catch:{ JSONException -> 0x021f }
                        if (r5 >= r10) goto L_0x0211
                        org.json.JSONObject r10 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r11 = "LOCATION"
                        java.lang.String r11 = r10.getString(r11)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r11 = r11.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r12 = "OLT_IP"
                        java.lang.String r12 = r10.getString(r12)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r12 = r12.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r13 = "OLT_VLAN"
                        java.lang.String r13 = r10.getString(r13)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r13 = r13.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r14 = "OLT_MAKE"
                        java.lang.String r14 = r10.getString(r14)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r14 = r14.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r15 = "ELEMENT_TYPE"
                        java.lang.String r15 = r10.getString(r15)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r15 = r15.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r2 = "OLT_CHIPSET"
                        java.lang.String r2 = r10.getString(r2)     // Catch:{ JSONException -> 0x021f }
                        r2.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r2 = "SNMP_ENABLED"
                        java.lang.String r2 = r10.getString(r2)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r2 = r2.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r16 = r10.getString(r0)     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r9 = r16.trim()     // Catch:{ JSONException -> 0x021f }
                        java.lang.String r8 = "OID_CHIPSET"
                        java.lang.String r8 = r10.getString(r8)     // Catch:{ JSONException -> 0x021f }
                        r8.trim()     // Catch:{ JSONException -> 0x021f }
                        android.widget.LinearLayout$LayoutParams r8 = new android.widget.LinearLayout$LayoutParams     // Catch:{ JSONException -> 0x021f }
                        r8.<init>(r7, r6)     // Catch:{ JSONException -> 0x021f }
                        r10 = 5
                        r6 = 10
                        r8.setMargins(r6, r10, r6, r6)     // Catch:{ JSONException -> 0x021f }
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r10 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021f }
                        android.view.LayoutInflater r10 = r10.getLayoutInflater()     // Catch:{ JSONException -> 0x021f }
                        r6 = 2131427396(0x7f0b0044, float:1.8476407E38)
                        r7 = 0
                        android.view.View r6 = r10.inflate(r6, r7)     // Catch:{ JSONException -> 0x021f }
                        r6.setLayoutParams(r8)     // Catch:{ JSONException -> 0x021f }
                        r8 = 2131231968(0x7f0804e0, float:1.8080032E38)
                        android.view.View r8 = r6.findViewById(r8)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r8 = (android.widget.TextView) r8     // Catch:{ JSONException -> 0x021f }
                        r10 = 2131231797(0x7f080435, float:1.8079685E38)
                        android.view.View r10 = r6.findViewById(r10)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r10 = (android.widget.TextView) r10     // Catch:{ JSONException -> 0x021f }
                        r7 = 2131231880(0x7f080488, float:1.8079854E38)
                        android.view.View r7 = r6.findViewById(r7)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r7 = (android.widget.TextView) r7     // Catch:{ JSONException -> 0x021f }
                        r17 = r3
                        r3 = 2131231806(0x7f08043e, float:1.8079703E38)
                        android.view.View r3 = r6.findViewById(r3)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r3 = (android.widget.TextView) r3     // Catch:{ JSONException -> 0x021f }
                        r18 = r4
                        r4 = 2131231886(0x7f08048e, float:1.8079866E38)
                        android.view.View r4 = r6.findViewById(r4)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r4 = (android.widget.TextView) r4     // Catch:{ JSONException -> 0x021f }
                        r19 = r2
                        r2 = 2131232027(0x7f08051b, float:1.8080152E38)
                        android.view.View r2 = r6.findViewById(r2)     // Catch:{ JSONException -> 0x021f }
                        android.widget.TextView r2 = (android.widget.TextView) r2     // Catch:{ JSONException -> 0x021f }
                        r1 = 2131231809(0x7f080441, float:1.807971E38)
                        android.view.View r1 = r6.findViewById(r1)     // Catch:{ JSONException -> 0x020d }
                        android.widget.TextView r1 = (android.widget.TextView) r1     // Catch:{ JSONException -> 0x020d }
                        int r5 = r5 + 1
                        r20 = r6
                        java.lang.String r6 = java.lang.String.valueOf(r5)     // Catch:{ JSONException -> 0x020d }
                        r8.setText(r6)     // Catch:{ JSONException -> 0x020d }
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x020d }
                        r6.<init>()     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r8 = "OLT-IP : "
                        java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ JSONException -> 0x020d }
                        java.lang.StringBuilder r6 = r6.append(r12)     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r8 = " | OLT-VLAN : "
                        java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ JSONException -> 0x020d }
                        java.lang.StringBuilder r6 = r6.append(r13)     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x020d }
                        r10.setText(r6)     // Catch:{ JSONException -> 0x020d }
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x020d }
                        r6.<init>()     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r8 = "("
                        java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ JSONException -> 0x020d }
                        java.lang.StringBuilder r6 = r6.append(r11)     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r8 = ")"
                        java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ JSONException -> 0x020d }
                        java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x020d }
                        r7.setText(r6)     // Catch:{ JSONException -> 0x020d }
                        r3.setText(r15)     // Catch:{ JSONException -> 0x020d }
                        r4.setText(r14)     // Catch:{ JSONException -> 0x020d }
                        boolean r3 = r9.equals(r0)     // Catch:{ JSONException -> 0x020d }
                        if (r3 == 0) goto L_0x01c1
                        java.lang.String r3 = "N/A"
                        r2.setText(r3)     // Catch:{ JSONException -> 0x020d }
                        r3 = r21
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r4 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021d }
                        android.content.res.Resources r4 = r4.getResources()     // Catch:{ JSONException -> 0x021d }
                        r6 = 2131034167(0x7f050037, float:1.7678844E38)
                        int r4 = r4.getColor(r6)     // Catch:{ JSONException -> 0x021d }
                        r2.setTextColor(r4)     // Catch:{ JSONException -> 0x021d }
                        goto L_0x01da
                    L_0x01c1:
                        r3 = r21
                        java.lang.String r4 = r9.toUpperCase()     // Catch:{ JSONException -> 0x021d }
                        r2.setText(r4)     // Catch:{ JSONException -> 0x021d }
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r4 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021d }
                        android.content.res.Resources r4 = r4.getResources()     // Catch:{ JSONException -> 0x021d }
                        r6 = 2131034163(0x7f050033, float:1.7678836E38)
                        int r4 = r4.getColor(r6)     // Catch:{ JSONException -> 0x021d }
                        r2.setTextColor(r4)     // Catch:{ JSONException -> 0x021d }
                    L_0x01da:
                        java.lang.String r2 = "FALSE"
                        r4 = r19
                        boolean r2 = r4.equals(r2)     // Catch:{ JSONException -> 0x021d }
                        if (r2 != 0) goto L_0x01f1
                        boolean r2 = r9.equals(r0)     // Catch:{ JSONException -> 0x021d }
                        if (r2 == 0) goto L_0x01eb
                        goto L_0x01f1
                    L_0x01eb:
                        r2 = 8
                        r1.setVisibility(r2)     // Catch:{ JSONException -> 0x021d }
                        goto L_0x01fa
                    L_0x01f1:
                        java.lang.String r2 = "The Olt Is Not Configured For PMS"
                        r1.setText(r2)     // Catch:{ JSONException -> 0x021d }
                        r2 = 0
                        r1.setVisibility(r2)     // Catch:{ JSONException -> 0x021d }
                    L_0x01fa:
                        r1 = r18
                        r2 = r20
                        r1.addView(r2)     // Catch:{ JSONException -> 0x021d }
                        r4 = r1
                        r1 = r3
                        r3 = r17
                        r2 = 0
                        r6 = -2
                        r7 = -1
                        r8 = 10
                        r9 = 0
                        goto L_0x0095
                    L_0x020d:
                        r0 = move-exception
                        r3 = r21
                        goto L_0x0221
                    L_0x0211:
                        r3 = r1
                        r1 = r4
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r0 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this     // Catch:{ JSONException -> 0x021d }
                        android.widget.LinearLayout r0 = r0.lay_tip_pms     // Catch:{ JSONException -> 0x021d }
                        r0.addView(r1)     // Catch:{ JSONException -> 0x021d }
                        goto L_0x0224
                    L_0x021d:
                        r0 = move-exception
                        goto L_0x0221
                    L_0x021f:
                        r0 = move-exception
                        r3 = r1
                    L_0x0221:
                        r0.printStackTrace()
                    L_0x0224:
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r0 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this
                        android.app.AlertDialog r0 = r0.progress_dialog
                        r0.cancel()
                        bsnl.bsnl_teevra.PMS_Setting_Tip_Activity r0 = bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.this
                        android.widget.LinearLayout r0 = r0.lay_tip_pms
                        r1 = 0
                        r0.setVisibility(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: bsnl.bsnl_teevra.PMS_Setting_Tip_Activity.AnonymousClass4.onResponse(java.lang.String):void");
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    PMS_Setting_Tip_Activity.this.progress_dialog.cancel();
                    PMS_Setting_Tip_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Setting_Tip_Activity.this.getApplicationContext()));
                    PMS_Setting_Tip_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("vendor", str2);
                    hashMap.put("circle", PMS_Setting_Tip_Activity.this.Pref_Circle);
                    hashMap.put("ssa", PMS_Setting_Tip_Activity.this.Pref_SSA);
                    hashMap.put("username", PMS_Setting_Tip_Activity.this.Pref_Username);
                    hashMap.put("random_key", PMS_Setting_Tip_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", PMS_Setting_Tip_Activity.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, PMS_Setting_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
