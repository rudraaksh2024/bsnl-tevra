package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.PreviewView;
import bsnl.bsnl_teevra.utils.BarcodeScannerHelper;
import bsnl.bsnl_teevra.utils.CustomAnimationUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.poi.xddf.usermodel.Angles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Provision_Activity extends AppCompatActivity implements TextWatcher {
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_fms_provision;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public JSONArray provision_array;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_provision_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
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
        this.lay_fms_provision = (LinearLayout) findViewById(R.id.lay_fms_provision);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_provision_order.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                AnonymousClass1 r0 = this;
                String str2 = "PENDING_DURATION";
                FMS_Provision_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = 0;
                    ViewGroup viewGroup = null;
                    if (jSONObject.getBoolean("STATUS")) {
                        JSONArray unused = FMS_Provision_Activity.this.provision_array = jSONObject.getJSONArray("ROWSET");
                        new AlertHelperclass().ptoast("TOTAL " + FMS_Provision_Activity.this.provision_array.length() + " PROVISION ORDER LOADED ", FMS_Provision_Activity.this.getApplicationContext());
                        while (i < FMS_Provision_Activity.this.provision_array.length()) {
                            JSONObject jSONObject2 = FMS_Provision_Activity.this.provision_array.getJSONObject(i);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.setMargins(10, 15, 10, 15);
                            View inflate = FMS_Provision_Activity.this.getLayoutInflater().inflate(R.layout.custom_fms_provision1, viewGroup);
                            inflate.setLayoutParams(layoutParams);
                            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_share);
                            String str3 = str2;
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_orderDuration);
                            jSONObject2.getString("PHONE_NO").trim();
                            i++;
                            View view = inflate;
                            ((TextView) inflate.findViewById(R.id.txt_srno)).setText(String.valueOf(i));
                            ((TextView) inflate.findViewById(R.id.txt_telephoneNo)).setText(jSONObject2.getString("PHONE_NO").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_orderNo)).setText("(ORDER NO : " + jSONObject2.getString("ORDER_NO") + ")");
                            ((TextView) inflate.findViewById(R.id.txt_customerName)).setText(jSONObject2.getString("CUSTOMER_NAME").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_customerAddress)).setText(jSONObject2.getString("CUSTOMER_ADDRESS").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_customerMobile)).setText(jSONObject2.getString("MOBILE_NO").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_serviceType)).setText(jSONObject2.getString("SERVICE_TYPE").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_orderType)).setText(jSONObject2.getString("ORDER_TYPE").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_orderSubType)).setText(jSONObject2.getString("ORDER_SUB_TYPE").toUpperCase());
                            ((TextView) inflate.findViewById(R.id.txt_orderBookedOn)).setText(jSONObject2.getString("ORDER_CREATED_DATE"));
                            ((TextView) inflate.findViewById(R.id.txt_orderAssignedOn)).setText(jSONObject2.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                            String str4 = str3;
                            textView.setText(jSONObject2.getString(str4) + " Hrs");
                            if (Integer.valueOf(jSONObject2.getString(str4)).intValue() > 24) {
                                r0 = this;
                                textView.setTextColor(FMS_Provision_Activity.this.getResources().getColor(R.color.colorRed));
                            } else {
                                r0 = this;
                                textView.setTextColor(FMS_Provision_Activity.this.getResources().getColor(R.color.colorGreen));
                            }
                            final View view2 = view;
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
                                    view2.draw(new Canvas(createBitmap));
                                    new ShareImage().share(createBitmap, new File(FMS_Provision_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "fms_provision.jpg"), FMS_Provision_Activity.this);
                                }
                            });
                            FMS_Provision_Activity.this.lay_fms_provision.addView(view2);
                            view2.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                }
                            });
                            str2 = str4;
                            viewGroup = null;
                        }
                        return;
                    }
                    new AlertHelperclass().ptoast("NO PENDING PROVISON ORDER", FMS_Provision_Activity.this.getApplicationContext());
                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Provision_Activity.this);
                    View inflate2 = FMS_Provision_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
                    TextView textView2 = (TextView) inflate2.findViewById(R.id.txt_update);
                    textView2.setText("OK");
                    builder.setCancelable(false);
                    builder.setView(inflate2);
                    AlertDialog unused2 = FMS_Provision_Activity.this.info_dialog = builder.create();
                    FMS_Provision_Activity.this.info_dialog.show();
                    ((TextView) inflate2.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate2.findViewById(R.id.txt_error)).setText("No Pending Provision Orders");
                    textView2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_Provision_Activity.this.info_dialog.cancel();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Provision_Activity.this.getApplicationContext());
                FMS_Provision_Activity.this.progress_dialog.cancel();
                FMS_Provision_Activity.this.txt_alert.setText(onErrorResponse);
                FMS_Provision_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_Provision_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Provision_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Provision_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Provision_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void Provision_Form(String str) {
        StringRequest stringRequest;
        final Dialog dialog = new Dialog(this, 2131821126);
        dialog.setContentView(R.layout.custom_fms_provision2);
        dialog.getWindow().setLayout(-1, -2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        CustomAnimationUtils.fadeInDialog(this, dialog);
        dialog.show();
        ((TextInputLayout) dialog.findViewById(R.id.telephoneInputLayout)).getEditText().setText(str);
        TextInputLayout textInputLayout = (TextInputLayout) dialog.findViewById(R.id.bbUserIdInputLayout);
        final TextInputLayout textInputLayout2 = (TextInputLayout) dialog.findViewById(R.id.serialNumberInputLayout);
        textInputLayout2.setEndIconMode(-1);
        textInputLayout2.setEndIconTintList((ColorStateList) null);
        textInputLayout2.setEndIconDrawable((int) R.drawable.ic_new_scan);
        TextInputLayout textInputLayout3 = (TextInputLayout) dialog.findViewById(R.id.ipDropdownMenu);
        TextInputLayout textInputLayout4 = (TextInputLayout) dialog.findViewById(R.id.vlanDropdownMenu);
        TextInputLayout textInputLayout5 = (TextInputLayout) dialog.findViewById(R.id.cVlanInputLayout);
        textInputLayout5.setEndIconMode(-1);
        textInputLayout5.setEndIconTintList((ColorStateList) null);
        textInputLayout5.setEndIconDrawable((int) R.drawable.ic_new_search);
        CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.checkbox_confirm);
        checkBox.setEnabled(false);
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.sp_OltIp);
        final AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) dialog.findViewById(R.id.sp_OltVlan);
        final TextInputLayout textInputLayout6 = textInputLayout5;
        final CheckBox checkBox2 = checkBox;
        final TextView textView = (TextView) dialog.findViewById(R.id.txt_vlan_validation);
        new StringRequest(1, getString(R.string.serverip) + "fms/provision/inventory.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_Provision_Activity.this.progress_dialog.cancel();
                final ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                final ArrayList arrayList3 = new ArrayList();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        arrayList.add(jSONObject.getString("OLT_IP"));
                        arrayList2.add(jSONObject.getString("OLT_VLAN"));
                        arrayList3.add(jSONObject.getString("NAS_IP"));
                    }
                    HashSet hashSet = new HashSet(arrayList);
                    HashSet hashSet2 = new HashSet(arrayList2);
                    new HashSet(arrayList3);
                    autoCompleteTextView.setAdapter(new ArrayAdapter(FMS_Provision_Activity.this, 17367050, new ArrayList(hashSet)));
                    autoCompleteTextView2.setAdapter(new ArrayAdapter(FMS_Provision_Activity.this, 17367050, new ArrayList(hashSet2)));
                    ArrayList arrayList4 = arrayList3;
                    autoCompleteTextView.setOnItemClickListener(new FMS_Provision_Activity$4$$ExternalSyntheticLambda0(this, arrayList, autoCompleteTextView2, arrayList2, arrayList4));
                    autoCompleteTextView2.setOnItemClickListener(new FMS_Provision_Activity$4$$ExternalSyntheticLambda1(this, arrayList2, autoCompleteTextView, arrayList, arrayList4));
                    textInputLayout6.getEditText().setOnFocusChangeListener(new FMS_Provision_Activity$4$$ExternalSyntheticLambda2(checkBox2, textView));
                    textInputLayout6.setEndIconOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            final int parseInt = textInputLayout6.getEditText().getText().toString().trim().isEmpty() ? 0 : Integer.parseInt(textInputLayout6.getEditText().getText().toString().trim());
                            final String trim = autoCompleteTextView.getText().toString().trim();
                            final String trim2 = autoCompleteTextView2.getText().toString().trim();
                            if (trim.isEmpty() || trim.equals("")) {
                                Toast.makeText(FMS_Provision_Activity.this, "Please Select Olt from Drop Down", 0).show();
                            } else if (trim2.isEmpty() || trim2.equals("")) {
                                Toast.makeText(FMS_Provision_Activity.this, "Please Select Olt from Drop Down", 0).show();
                            } else if (parseInt < 128) {
                                Toast.makeText(FMS_Provision_Activity.this, "Please Enter a Valid Custommer Vlan", 0).show();
                            } else {
                                String str = (String) arrayList3.get(arrayList.indexOf(trim));
                                textInputLayout6.getEditText().clearFocus();
                                FMS_Provision_Activity.this.progress_dialog.show();
                                RequestQueue newRequestQueue = Volley.newRequestQueue(FMS_Provision_Activity.this);
                                final String str2 = str;
                                AnonymousClass3 r1 = new StringRequest(1, FMS_Provision_Activity.this.getString(R.string.serverip) + "fms/provision/vlan_inventory_unique.php", new Response.Listener<String>() {
                                    public void onResponse(String str) {
                                        FMS_Provision_Activity.this.progress_dialog.cancel();
                                        checkBox2.setEnabled(true);
                                        try {
                                            JSONObject jSONObject = new JSONObject(str);
                                            Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("success"));
                                            int i = jSONObject.getInt("count");
                                            String string = jSONObject.getString("error");
                                            if (!valueOf.booleanValue()) {
                                                textView.setText(string);
                                                textView.setTextColor(FMS_Provision_Activity.this.getResources().getColor(R.color.colorRed));
                                            } else if (i > 0) {
                                                textView.setText("The Customer Vlan Is Being Userd By " + jSONObject.getJSONArray("rowset").getJSONObject(0).getString("telephone"));
                                                textView.setTextColor(FMS_Provision_Activity.this.getResources().getColor(R.color.colorRed));
                                            } else {
                                                textView.setText("The Customer Vlan Is Available For Use");
                                                textView.setTextColor(FMS_Provision_Activity.this.getResources().getColor(R.color.colorGreen));
                                            }
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError volleyError) {
                                        FMS_Provision_Activity.this.progress_dialog.cancel();
                                        FMS_Provision_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Provision_Activity.this.getApplicationContext()));
                                        FMS_Provision_Activity.this.error_dialog.show();
                                    }
                                }) {
                                    /* access modifiers changed from: protected */
                                    public Map<String, String> getParams() throws AuthFailureError {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("nas_ip", str2);
                                        hashMap.put("olt_ip", trim);
                                        hashMap.put("olt_vlan", trim2);
                                        hashMap.put("c_vlan", String.valueOf(parseInt));
                                        hashMap.put("username", FMS_Provision_Activity.this.Pref_Username);
                                        hashMap.put("random_key", FMS_Provision_Activity.this.Pref_Randkey);
                                        hashMap.put("device_id", FMS_Provision_Activity.this.androidId);
                                        return hashMap;
                                    }
                                };
                                r1.setRetryPolicy(new DefaultRetryPolicy(Angles.OOXML_DEGREE, 1, 1.0f));
                                newRequestQueue.add(r1);
                                Toast.makeText(FMS_Provision_Activity.this, "Nas_Ip : " + str, 0).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onResponse$0$bsnl-bsnl_teevra-FMS_Provision_Activity$4  reason: not valid java name */
            public /* synthetic */ void m246lambda$onResponse$0$bsnlbsnl_teevraFMS_Provision_Activity$4(List list, AutoCompleteTextView autoCompleteTextView, List list2, List list3, AdapterView adapterView, View view, int i, long j) {
                String str = (String) adapterView.getItemAtPosition(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (((String) list.get(i2)).equals(str)) {
                        autoCompleteTextView.setText((CharSequence) list2.get(i2), false);
                        FMS_Provision_Activity.this.GetDetailedInventory((String) list.get(i2), (String) list2.get(i2), (String) list3.get(i2));
                        return;
                    }
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onResponse$1$bsnl-bsnl_teevra-FMS_Provision_Activity$4  reason: not valid java name */
            public /* synthetic */ void m247lambda$onResponse$1$bsnlbsnl_teevraFMS_Provision_Activity$4(List list, AutoCompleteTextView autoCompleteTextView, List list2, List list3, AdapterView adapterView, View view, int i, long j) {
                String str = (String) adapterView.getItemAtPosition(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (((String) list.get(i2)).equals(str)) {
                        autoCompleteTextView.setText((CharSequence) list2.get(i2), false);
                        FMS_Provision_Activity.this.GetDetailedInventory((String) list2.get(i2), (String) list.get(i2), (String) list3.get(i2));
                        return;
                    }
                }
            }

            static /* synthetic */ void lambda$onResponse$2(CheckBox checkBox, TextView textView, View view, boolean z) {
                if (z) {
                    checkBox.setEnabled(false);
                    checkBox.setChecked(false);
                    textView.setText("");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Provision_Activity.this.progress_dialog.cancel();
                FMS_Provision_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Provision_Activity.this.getApplicationContext()));
                FMS_Provision_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_Provision_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Provision_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Provision_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Provision_Activity.this.androidId);
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(Angles.OOXML_DEGREE, 1, 1.0f));
        newRequestQueue.add(stringRequest);
        textInputLayout2.setEndIconOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final BarcodeScannerHelper[] barcodeScannerHelperArr = new BarcodeScannerHelper[1];
                final Dialog dialog = new Dialog(FMS_Provision_Activity.this, 2131821126);
                dialog.setContentView(R.layout.custom_preview);
                dialog.getWindow().setLayout(-1, -2);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCancelable(false);
                CustomAnimationUtils.fadeInDialog(FMS_Provision_Activity.this, dialog);
                dialog.show();
                Handler handler = new Handler();
                AnonymousClass1 r9 = new Runnable() {
                    public void run() {
                        BarcodeScannerHelper barcodeScannerHelper = barcodeScannerHelperArr[0];
                        if (barcodeScannerHelper != null) {
                            barcodeScannerHelper.shutdown();
                        }
                        CustomAnimationUtils.fadeOutDialog(FMS_Provision_Activity.this, dialog);
                        Toast.makeText(FMS_Provision_Activity.this, "No barcode detected", 0).show();
                    }
                };
                final Handler handler2 = handler;
                final AnonymousClass1 r3 = r9;
                final BarcodeScannerHelper[] barcodeScannerHelperArr2 = barcodeScannerHelperArr;
                AnonymousClass2 r0 = new BarcodeScannerHelper.OnBarcodeDetectedListener() {
                    public void onBarcodeDetected(String str) {
                        FMS_Provision_Activity.this.runOnUiThread(new FMS_Provision_Activity$7$2$$ExternalSyntheticLambda0(this, handler2, r3, textInputLayout2, str, barcodeScannerHelperArr2, dialog));
                    }

                    /* access modifiers changed from: package-private */
                    /* renamed from: lambda$onBarcodeDetected$0$bsnl-bsnl_teevra-FMS_Provision_Activity$7$2  reason: not valid java name */
                    public /* synthetic */ void m248lambda$onBarcodeDetected$0$bsnlbsnl_teevraFMS_Provision_Activity$7$2(Handler handler, Runnable runnable, TextInputLayout textInputLayout, String str, BarcodeScannerHelper[] barcodeScannerHelperArr, Dialog dialog) {
                        handler.removeCallbacks(runnable);
                        textInputLayout.getEditText().setText(str);
                        Toast.makeText(FMS_Provision_Activity.this, "Detected: " + str, 0).show();
                        BarcodeScannerHelper barcodeScannerHelper = barcodeScannerHelperArr[0];
                        if (barcodeScannerHelper != null) {
                            barcodeScannerHelper.shutdown();
                        }
                        CustomAnimationUtils.fadeOutDialog(FMS_Provision_Activity.this, dialog);
                    }
                };
                FMS_Provision_Activity fMS_Provision_Activity = FMS_Provision_Activity.this;
                BarcodeScannerHelper barcodeScannerHelper = new BarcodeScannerHelper(fMS_Provision_Activity, fMS_Provision_Activity, (PreviewView) dialog.findViewById(R.id.previewView), r0);
                barcodeScannerHelperArr[0] = barcodeScannerHelper;
                barcodeScannerHelper.startCamera();
                handler.postDelayed(r9, 30000);
            }
        });
        ((TextView) dialog.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CustomAnimationUtils.fadeOutDialog(FMS_Provision_Activity.this, dialog);
            }
        });
        ((TextView) dialog.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Dialog dialog = new Dialog(FMS_Provision_Activity.this, 2131821126);
                dialog.setContentView(R.layout.custom_fms_provision4);
                dialog.getWindow().setLayout(-1, -2);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCancelable(true);
                CustomAnimationUtils.fadeInDialog(FMS_Provision_Activity.this, dialog);
                dialog.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void GetDetailedInventory(String str, String str2, String str3) {
        Toast.makeText(this, "NAS-IP : " + str3, 0).show();
        if (str.isEmpty() || str.equals("")) {
            Toast.makeText(this, "Please Select Olt from Drop Down", 0).show();
        } else if (str2.isEmpty() || str2.equals("")) {
            Toast.makeText(this, "Please Select Olt from Drop Down", 0).show();
        } else {
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            final String str4 = str3;
            final String str5 = str;
            final String str6 = str2;
            AnonymousClass12 r1 = new StringRequest(1, getString(R.string.serverip) + "fms/provision/vlan_inventory.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Log.v("INVENTORY", str.toString());
                    FMS_Provision_Activity.this.progress_dialog.cancel();
                    try {
                        Boolean.valueOf(new JSONObject(str).getBoolean("success"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FMS_Provision_Activity.this.progress_dialog.cancel();
                    FMS_Provision_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Provision_Activity.this.getApplicationContext()));
                    FMS_Provision_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("nas_ip", str4);
                    hashMap.put("olt_ip", str5);
                    hashMap.put("olt_vlan", str6);
                    hashMap.put("username", FMS_Provision_Activity.this.Pref_Username);
                    hashMap.put("random_key", FMS_Provision_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", FMS_Provision_Activity.this.androidId);
                    return hashMap;
                }
            };
            r1.setRetryPolicy(new DefaultRetryPolicy(Angles.OOXML_DEGREE, 1, 1.0f));
            newRequestQueue.add(r1);
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
                    FMS_Provision_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Provision_Activity fMS_Provision_Activity = FMS_Provision_Activity.this;
                    SharedPreferences.Editor unused = fMS_Provision_Activity.editor = fMS_Provision_Activity.sharedPreferences.edit();
                    FMS_Provision_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_Provision_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_Provision_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_Provision_Activity.this.startActivity(intent);
                    FMS_Provision_Activity.this.finish();
                    FMS_Provision_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void afterTextChanged(Editable editable) {
        String str;
        int i;
        FMS_Provision_Activity fMS_Provision_Activity;
        FMS_Provision_Activity fMS_Provision_Activity2 = this;
        String obj = fMS_Provision_Activity2.et_search.getText().toString();
        fMS_Provision_Activity2.lay_fms_provision.removeAllViews();
        int i2 = 1;
        int i3 = 0;
        while (i3 < fMS_Provision_Activity2.provision_array.length()) {
            try {
                JSONObject jSONObject = fMS_Provision_Activity2.provision_array.getJSONObject(i3);
                if (!jSONObject.getString("PHONE_NO").toUpperCase().contains(obj.toUpperCase())) {
                    if (!jSONObject.getString("ORDER_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("CUSTOMER_NAME").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("MOBILE_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("SERVICE_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("ORDER_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("ORDER_SUB_TYPE").toUpperCase().contains(obj.toUpperCase())) {
                        if (!jSONObject.getString("PENDING_DURATION").toUpperCase().contains(obj.toUpperCase())) {
                            str = obj;
                            i = i3;
                            fMS_Provision_Activity = fMS_Provision_Activity2;
                            i3 = i + 1;
                            fMS_Provision_Activity2 = fMS_Provision_Activity;
                            obj = str;
                        }
                    }
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(10, 10, 10, 20);
                str = obj;
                View inflate = getLayoutInflater().inflate(R.layout.custom_fms_provision1, (ViewGroup) null);
                inflate.setLayoutParams(layoutParams);
                i = i3;
                ImageView imageView = (ImageView) inflate.findViewById(R.id.img_share);
                TextView textView = (TextView) inflate.findViewById(R.id.txt_orderType);
                TextView textView2 = (TextView) inflate.findViewById(R.id.txt_orderSubType);
                TextView textView3 = (TextView) inflate.findViewById(R.id.txt_orderBookedOn);
                TextView textView4 = (TextView) inflate.findViewById(R.id.txt_orderAssignedOn);
                TextView textView5 = (TextView) inflate.findViewById(R.id.txt_orderDuration);
                View view = inflate;
                String trim = jSONObject.getString("PHONE_NO").trim();
                ((TextView) inflate.findViewById(R.id.txt_srno)).setText(String.valueOf(i2));
                ((TextView) inflate.findViewById(R.id.txt_telephoneNo)).setText(jSONObject.getString("PHONE_NO").toUpperCase());
                ((TextView) inflate.findViewById(R.id.txt_orderNo)).setText("(ORDER NO : " + jSONObject.getString("ORDER_NO") + ")");
                ((TextView) inflate.findViewById(R.id.txt_customerName)).setText(jSONObject.getString("CUSTOMER_NAME").toUpperCase());
                ((TextView) inflate.findViewById(R.id.txt_customerAddress)).setText(jSONObject.getString("CUSTOMER_ADDRESS").toUpperCase());
                ((TextView) inflate.findViewById(R.id.txt_customerMobile)).setText(jSONObject.getString("MOBILE_NO").toUpperCase());
                ((TextView) inflate.findViewById(R.id.txt_serviceType)).setText(jSONObject.getString("SERVICE_TYPE").toUpperCase());
                textView.setText(jSONObject.getString("ORDER_TYPE").toUpperCase());
                textView2.setText(jSONObject.getString("ORDER_SUB_TYPE").toUpperCase());
                textView3.setText(jSONObject.getString("ORDER_CREATED_DATE"));
                textView4.setText(jSONObject.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                String str2 = "PENDING_DURATION";
                textView5.setText(jSONObject.getString(str2) + " Hrs");
                if (Integer.valueOf(jSONObject.getString(str2)).intValue() > 24) {
                    textView5.setTextColor(getResources().getColor(R.color.colorRed));
                } else {
                    textView5.setTextColor(getResources().getColor(R.color.colorGreen));
                }
                fMS_Provision_Activity = this;
                final View view2 = view;
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
                        view2.draw(new Canvas(createBitmap));
                        new ShareImage().share(createBitmap, new File(FMS_Provision_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "fms_provision.jpg"), FMS_Provision_Activity.this);
                    }
                });
                fMS_Provision_Activity.lay_fms_provision.addView(view2);
                final String str3 = trim;
                view2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_Provision_Activity.this.Provision_Form(str3);
                    }
                });
                i2++;
                i3 = i + 1;
                fMS_Provision_Activity2 = fMS_Provision_Activity;
                obj = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
