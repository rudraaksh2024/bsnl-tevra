package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class FMS_BBC_Site_BBNL_Fragment extends Fragment {
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private AlertDialog geo_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_site_bbnl;
    private AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0204 A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x020a A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0247 A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x024c A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0260 A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0267 A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x027d A[Catch:{ JSONException -> 0x02b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0284 A[Catch:{ JSONException -> 0x02b4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r25, android.view.ViewGroup r26, android.os.Bundle r27) {
        /*
            r24 = this;
            r0 = r24
            java.lang.String r8 = "LOCATION"
            java.lang.String r9 = "null"
            java.lang.String r10 = "INSP_PERIOD"
            java.lang.String r11 = "OLT_IP"
            java.lang.String r12 = "N"
            r1 = 2131427435(0x7f0b006b, float:1.8476486E38)
            r13 = 0
            r2 = r25
            r3 = r26
            android.view.View r14 = r2.inflate(r1, r3, r13)
            android.os.Bundle r1 = r24.getArguments()
            java.lang.String r2 = "BBNL"
            java.lang.String r1 = r1.getString(r2)
            r2 = 2131231280(0x7f080230, float:1.8078637E38)
            android.view.View r2 = r14.findViewById(r2)
            android.widget.LinearLayout r2 = (android.widget.LinearLayout) r2
            r0.lay_site_bbnl = r2
            androidx.fragment.app.FragmentActivity r2 = r24.getActivity()
            java.lang.String r3 = "myloginpreference"
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r13)
            r0.sharedPreferences = r2
            java.lang.String r3 = "role_Key"
            r15 = 0
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Role = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "access_level_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Access_Level = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "circle_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Circle = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "ssa_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_SSA = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "username_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Username = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "rand_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Randkey = r2
            android.content.SharedPreferences r2 = r0.sharedPreferences
            java.lang.String r3 = "fms_username_Key"
            java.lang.String r2 = r2.getString(r3, r15)
            r0.Pref_Fms_Username = r2
            androidx.fragment.app.FragmentActivity r2 = r24.getActivity()
            android.content.ContentResolver r2 = r2.getContentResolver()
            java.lang.String r3 = "android_id"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)
            r0.androidId = r2
            android.app.AlertDialog$Builder r2 = new android.app.AlertDialog$Builder
            androidx.fragment.app.FragmentActivity r3 = r24.getActivity()
            r2.<init>(r3)
            android.view.LayoutInflater r3 = r24.getLayoutInflater()
            r4 = 2131427405(0x7f0b004d, float:1.8476425E38)
            android.view.View r3 = r3.inflate(r4, r15)
            r2.setCancelable(r13)
            r2.setView(r3)
            android.app.AlertDialog r2 = r2.create()
            r0.progress_dialog = r2
            android.app.AlertDialog$Builder r2 = new android.app.AlertDialog$Builder
            androidx.fragment.app.FragmentActivity r3 = r24.getActivity()
            r2.<init>(r3)
            android.view.LayoutInflater r3 = r24.getLayoutInflater()
            r4 = 2131427374(0x7f0b002e, float:1.8476362E38)
            android.view.View r3 = r3.inflate(r4, r15)
            r4 = 2131231809(0x7f080441, float:1.807971E38)
            android.view.View r4 = r3.findViewById(r4)
            android.widget.TextView r4 = (android.widget.TextView) r4
            r0.txt_alert = r4
            r2.setCancelable(r13)
            java.lang.String r4 = "Retry"
            r2.setNegativeButton(r4, r15)
            r2.setView(r3)
            android.app.AlertDialog r2 = r2.create()
            r0.error_dialog = r2
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x02b9 }
            r7.<init>(r1)     // Catch:{ JSONException -> 0x02b9 }
            r1 = r13
        L_0x00e4:
            int r2 = r7.length()     // Catch:{ JSONException -> 0x02b9 }
            if (r1 >= r2) goto L_0x02b6
            org.json.JSONObject r2 = r7.getJSONObject(r1)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ JSONException -> 0x02b9 }
            r4 = -1
            r5 = -2
            r3.<init>(r4, r5)     // Catch:{ JSONException -> 0x02b9 }
            r4 = 5
            r5 = 10
            r6 = 15
            r3.setMargins(r6, r4, r6, r5)     // Catch:{ JSONException -> 0x02b9 }
            android.view.LayoutInflater r4 = r24.getLayoutInflater()     // Catch:{ JSONException -> 0x02b9 }
            r5 = 2131427368(0x7f0b0028, float:1.847635E38)
            android.view.View r6 = r4.inflate(r5, r15)     // Catch:{ JSONException -> 0x02b9 }
            r6.setLayoutParams(r3)     // Catch:{ JSONException -> 0x02b9 }
            r3 = 2131231968(0x7f0804e0, float:1.8080032E38)
            android.view.View r3 = r6.findViewById(r3)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.TextView r3 = (android.widget.TextView) r3     // Catch:{ JSONException -> 0x02b9 }
            r4 = 2131231797(0x7f080435, float:1.8079685E38)
            android.view.View r4 = r6.findViewById(r4)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.TextView r4 = (android.widget.TextView) r4     // Catch:{ JSONException -> 0x02b9 }
            r5 = 2131231880(0x7f080488, float:1.8079854E38)
            android.view.View r5 = r6.findViewById(r5)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.TextView r5 = (android.widget.TextView) r5     // Catch:{ JSONException -> 0x02b9 }
            r15 = 2131232033(0x7f080521, float:1.8080164E38)
            android.view.View r15 = r6.findViewById(r15)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.TextView r15 = (android.widget.TextView) r15     // Catch:{ JSONException -> 0x02b9 }
            r13 = 2131231227(0x7f0801fb, float:1.807853E38)
            android.view.View r13 = r6.findViewById(r13)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.LinearLayout r13 = (android.widget.LinearLayout) r13     // Catch:{ JSONException -> 0x02b9 }
            r26 = r7
            r7 = 2131231847(0x7f080467, float:1.8079787E38)
            android.view.View r7 = r6.findViewById(r7)     // Catch:{ JSONException -> 0x02b9 }
            android.widget.TextView r7 = (android.widget.TextView) r7     // Catch:{ JSONException -> 0x02b9 }
            r16 = r14
            r14 = 2131230902(0x7f0800b6, float:1.807787E38)
            android.view.View r14 = r6.findViewById(r14)     // Catch:{ JSONException -> 0x02b4 }
            android.widget.TextView r14 = (android.widget.TextView) r14     // Catch:{ JSONException -> 0x02b4 }
            r0 = 2131230908(0x7f0800bc, float:1.8077882E38)
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ JSONException -> 0x02b4 }
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch:{ JSONException -> 0x02b4 }
            r17 = r0
            r0 = 2131230900(0x7f0800b4, float:1.8077866E38)
            android.view.View r0 = r6.findViewById(r0)     // Catch:{ JSONException -> 0x02b4 }
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r18 = r2.getString(r11)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r18 = r18.trim()     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r19 = r2.getString(r10)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r19 = r19.trim()     // Catch:{ JSONException -> 0x02b4 }
            r20 = r6
            java.lang.String r6 = "OLT_OWNER"
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r21 = r6.trim()     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r6 = "OLT_LAT"
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r6 = r6.trim()     // Catch:{ JSONException -> 0x02b4 }
            r22 = r0
            java.lang.String r0 = "OLT_LONG"
            java.lang.String r0 = r2.getString(r0)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r0 = r0.trim()     // Catch:{ JSONException -> 0x02b4 }
            boolean r23 = r6.isEmpty()     // Catch:{ JSONException -> 0x02b4 }
            if (r23 != 0) goto L_0x01b6
            boolean r23 = r6.equals(r9)     // Catch:{ JSONException -> 0x02b4 }
            if (r23 != 0) goto L_0x01b6
            boolean r23 = r0.isEmpty()     // Catch:{ JSONException -> 0x02b4 }
            if (r23 != 0) goto L_0x01b6
            boolean r23 = r0.equals(r9)     // Catch:{ JSONException -> 0x02b4 }
            if (r23 == 0) goto L_0x01ad
            goto L_0x01b6
        L_0x01ad:
            r23 = r9
            r9 = 2131165518(0x7f07014e, float:1.7945255E38)
            r13.setBackgroundResource(r9)     // Catch:{ JSONException -> 0x02b4 }
            goto L_0x01be
        L_0x01b6:
            r23 = r9
            r9 = 2131165519(0x7f07014f, float:1.7945257E38)
            r13.setBackgroundResource(r9)     // Catch:{ JSONException -> 0x02b4 }
        L_0x01be:
            int r9 = r1 + 1
            java.lang.String r1 = java.lang.String.valueOf(r9)     // Catch:{ JSONException -> 0x02b4 }
            r3.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x02b4 }
            r1.<init>()     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = "OLT-IP : "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = r2.getString(r11)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = " | OLT-VLAN : "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = "OLT_VLAN"
            java.lang.String r3 = r2.getString(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x02b4 }
            r4.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = "(BBNL)"
            r15.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r2.getString(r8)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.trim()     // Catch:{ JSONException -> 0x02b4 }
            boolean r1 = r1.isEmpty()     // Catch:{ JSONException -> 0x02b4 }
            if (r1 == 0) goto L_0x020a
            java.lang.String r1 = "Site Inspected By"
            r5.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
            goto L_0x022a
        L_0x020a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x02b4 }
            r1.<init>()     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = "Site ("
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = r2.getString(r8)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r3 = ") Inspected By"
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.toString()     // Catch:{ JSONException -> 0x02b4 }
            r5.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
        L_0x022a:
            java.lang.String r1 = r2.getString(r10)     // Catch:{ JSONException -> 0x02b4 }
            r7.setText(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = "INSP_BBC"
            java.lang.String r1 = r2.getString(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.trim()     // Catch:{ JSONException -> 0x02b4 }
            boolean r1 = r1.equals(r12)     // Catch:{ JSONException -> 0x02b4 }
            r3 = 2131165400(0x7f0700d8, float:1.7945016E38)
            r4 = 2131165396(0x7f0700d4, float:1.7945008E38)
            if (r1 == 0) goto L_0x024c
            r1 = 0
            r14.setCompoundDrawablesWithIntrinsicBounds(r3, r1, r1, r1)     // Catch:{ JSONException -> 0x02b4 }
            goto L_0x0250
        L_0x024c:
            r1 = 0
            r14.setCompoundDrawablesWithIntrinsicBounds(r4, r1, r1, r1)     // Catch:{ JSONException -> 0x02b4 }
        L_0x0250:
            java.lang.String r1 = "INSP_OAHEAD"
            java.lang.String r1 = r2.getString(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.trim()     // Catch:{ JSONException -> 0x02b4 }
            boolean r1 = r1.equals(r12)     // Catch:{ JSONException -> 0x02b4 }
            if (r1 == 0) goto L_0x0267
            r1 = r17
            r5 = 0
            r1.setCompoundDrawablesWithIntrinsicBounds(r3, r5, r5, r5)     // Catch:{ JSONException -> 0x02b4 }
            goto L_0x026d
        L_0x0267:
            r1 = r17
            r5 = 0
            r1.setCompoundDrawablesWithIntrinsicBounds(r4, r5, r5, r5)     // Catch:{ JSONException -> 0x02b4 }
        L_0x026d:
            java.lang.String r1 = "INSP_BAHEAD"
            java.lang.String r1 = r2.getString(r1)     // Catch:{ JSONException -> 0x02b4 }
            java.lang.String r1 = r1.trim()     // Catch:{ JSONException -> 0x02b4 }
            boolean r1 = r1.equals(r12)     // Catch:{ JSONException -> 0x02b4 }
            if (r1 == 0) goto L_0x0284
            r1 = r22
            r13 = 0
            r1.setCompoundDrawablesWithIntrinsicBounds(r3, r13, r13, r13)     // Catch:{ JSONException -> 0x02b4 }
            goto L_0x028a
        L_0x0284:
            r1 = r22
            r13 = 0
            r1.setCompoundDrawablesWithIntrinsicBounds(r4, r13, r13, r13)     // Catch:{ JSONException -> 0x02b4 }
        L_0x028a:
            r14 = r24
            android.widget.LinearLayout r1 = r14.lay_site_bbnl     // Catch:{ JSONException -> 0x02b4 }
            r7 = r20
            r1.addView(r7)     // Catch:{ JSONException -> 0x02b4 }
            bsnl.bsnl_teevra.FMS_BBC_Site_BBNL_Fragment$1 r15 = new bsnl.bsnl_teevra.FMS_BBC_Site_BBNL_Fragment$1     // Catch:{ JSONException -> 0x02b4 }
            r1 = r15
            r2 = r24
            r3 = r6
            r4 = r0
            r5 = r19
            r0 = r7
            r6 = r18
            r17 = r26
            r7 = r21
            r1.<init>(r3, r4, r5, r6, r7)     // Catch:{ JSONException -> 0x02b4 }
            r0.setOnClickListener(r15)     // Catch:{ JSONException -> 0x02b4 }
            r1 = r9
            r0 = r14
            r14 = r16
            r7 = r17
            r9 = r23
            r15 = 0
            goto L_0x00e4
        L_0x02b4:
            r0 = move-exception
            goto L_0x02bc
        L_0x02b6:
            r16 = r14
            goto L_0x02bf
        L_0x02b9:
            r0 = move-exception
            r16 = r14
        L_0x02bc:
            r0.printStackTrace()
        L_0x02bf:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: bsnl.bsnl_teevra.FMS_BBC_Site_BBNL_Fragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }
}
