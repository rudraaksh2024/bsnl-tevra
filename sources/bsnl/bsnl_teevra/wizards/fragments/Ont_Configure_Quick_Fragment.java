package bsnl.bsnl_teevra.wizards.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.R;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Quick_Fragment extends Fragment implements View.OnClickListener {
    private String Ont_Url;
    private String Token;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public CheckBox chk_bridge;
    private CheckBox chk_pppoe;
    private CheckBox chk_tr069;
    /* access modifiers changed from: private */
    public CheckBox chk_voip;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_bridge_vlan;
    private EditText et_pppoe_pwd;
    private EditText et_pppoe_username;
    private EditText et_pppoe_vlan;
    private EditText et_voip_domain;
    private EditText et_voip_pwd;
    private EditText et_voip_tele;
    private EditText et_voip_vlan;
    /* access modifiers changed from: private */
    public ImageView img_status_bridge;
    /* access modifiers changed from: private */
    public ImageView img_status_pppoe;
    private ImageView img_status_tr069;
    /* access modifiers changed from: private */
    public ImageView img_status_voip;
    /* access modifiers changed from: private */
    public LinearLayout lay_bridge;
    /* access modifiers changed from: private */
    public LinearLayout lay_pppoe;
    /* access modifiers changed from: private */
    public LinearLayout lay_voip;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public JSONObject wan_response;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ont_configure_quick_fragment, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate2);
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        View inflate3 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate3.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate3);
        this.error_dialog = builder2.create();
        this.chk_tr069 = (CheckBox) inflate.findViewById(R.id.chk_tr069);
        this.img_status_tr069 = (ImageView) inflate.findViewById(R.id.img_status_tr069);
        this.chk_pppoe = (CheckBox) inflate.findViewById(R.id.chk_pppoe);
        this.img_status_pppoe = (ImageView) inflate.findViewById(R.id.img_status_pppoe);
        this.lay_pppoe = (LinearLayout) inflate.findViewById(R.id.lay_pppoe);
        this.et_pppoe_vlan = (EditText) inflate.findViewById(R.id.et_pppoe_vlan);
        this.et_pppoe_username = (EditText) inflate.findViewById(R.id.et_pppoe_username);
        this.et_pppoe_pwd = (EditText) inflate.findViewById(R.id.et_pppoe_pwd);
        this.chk_voip = (CheckBox) inflate.findViewById(R.id.chk_voip);
        this.img_status_voip = (ImageView) inflate.findViewById(R.id.img_status_voip);
        this.lay_voip = (LinearLayout) inflate.findViewById(R.id.lay_voip);
        this.et_voip_vlan = (EditText) inflate.findViewById(R.id.et_voip_vlan);
        this.et_voip_tele = (EditText) inflate.findViewById(R.id.et_voip_tele);
        this.et_voip_pwd = (EditText) inflate.findViewById(R.id.et_voip_pwd);
        this.et_voip_domain = (EditText) inflate.findViewById(R.id.et_voip_domain);
        this.chk_bridge = (CheckBox) inflate.findViewById(R.id.chk_bridge);
        this.img_status_bridge = (ImageView) inflate.findViewById(R.id.img_status_bridge);
        this.lay_bridge = (LinearLayout) inflate.findViewById(R.id.lay_bridge);
        this.et_bridge_vlan = (EditText) inflate.findViewById(R.id.et_bridge_vlan);
        this.btn_submit = (TextView) inflate.findViewById(R.id.btn_submit);
        this.chk_pppoe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment = Ont_Configure_Quick_Fragment.this;
                    ont_Configure_Quick_Fragment.layout_visible(ont_Configure_Quick_Fragment.lay_pppoe);
                    return;
                }
                Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment2 = Ont_Configure_Quick_Fragment.this;
                ont_Configure_Quick_Fragment2.layout_gone(ont_Configure_Quick_Fragment2.lay_pppoe);
            }
        });
        this.chk_voip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment = Ont_Configure_Quick_Fragment.this;
                    ont_Configure_Quick_Fragment.layout_visible(ont_Configure_Quick_Fragment.lay_voip);
                    return;
                }
                Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment2 = Ont_Configure_Quick_Fragment.this;
                ont_Configure_Quick_Fragment2.layout_gone(ont_Configure_Quick_Fragment2.lay_voip);
            }
        });
        this.chk_bridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment = Ont_Configure_Quick_Fragment.this;
                    ont_Configure_Quick_Fragment.layout_visible(ont_Configure_Quick_Fragment.lay_bridge);
                    return;
                }
                Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment2 = Ont_Configure_Quick_Fragment.this;
                ont_Configure_Quick_Fragment2.layout_gone(ont_Configure_Quick_Fragment2.lay_bridge);
            }
        });
        this.btn_submit.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        final Boolean valueOf = Boolean.valueOf(this.chk_pppoe.isChecked());
        final Boolean valueOf2 = Boolean.valueOf(this.chk_voip.isChecked());
        final Boolean valueOf3 = Boolean.valueOf(this.chk_bridge.isChecked());
        Boolean.valueOf(this.chk_tr069.isChecked());
        Serializable serializable = getArguments().getSerializable("LOGIN_OBJECT");
        getArguments().getString("IMPORT_CLASS");
        String string = getArguments().getString("PACKAGE_NAME");
        try {
            Class<?> cls = serializable.getClass();
            Object invoke = cls.getMethod("getToken", new Class[0]).invoke(serializable, new Object[0]);
            this.Token = (String) cls.getMethod("getToken", new Class[0]).invoke(serializable, new Object[0]);
            this.Ont_Url = (String) cls.getMethod("fetchUrl", new Class[0]).invoke(serializable, new Object[0]);
            Log.v("TOKEN", invoke.toString());
            final Class<?> cls2 = Class.forName(string + "Ont_Configure_Network_Status_Class");
            final Object newInstance = cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class};
            final Object[] objArr = {this.Ont_Url};
            final Class<?> cls3 = Class.forName(string + "Ont_Configure_Setting_Class");
            this.progress_dialog.show();
            this.wan_response = new JSONObject();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        JSONObject unused = Ont_Configure_Quick_Fragment.this.wan_response = (JSONObject) cls2.getMethod("extractWanInfo", clsArr).invoke(newInstance, objArr);
                        Log.v("WAN-RESPONSE", Ont_Configure_Quick_Fragment.this.wan_response.toString());
                        Ont_Configure_Quick_Fragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                try {
                                    if (Boolean.valueOf(Ont_Configure_Quick_Fragment.this.wan_response.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        if (valueOf.booleanValue()) {
                                            Ont_Configure_Quick_Fragment.this.pppoe_configure(cls3);
                                        } else if (valueOf2.booleanValue()) {
                                            Ont_Configure_Quick_Fragment.this.voip_configure(cls3);
                                        } else if (valueOf3.booleanValue()) {
                                            Ont_Configure_Quick_Fragment.this.bridge_configure(cls3);
                                        } else {
                                            Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                        }
                                        Ont_Configure_Quick_Fragment.this.tr069_configure(cls3);
                                        return;
                                    }
                                    Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                    Ont_Configure_Quick_Fragment.this.txt_alert.setText("Error!!\nFailed To Fetch The Wan Interface Detail");
                                    Ont_Configure_Quick_Fragment.this.error_dialog.show();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException(e4);
        } catch (InstantiationException e5) {
            throw new RuntimeException(e5);
        }
    }

    private String check_services(String str) {
        try {
            Boolean.valueOf(this.wan_response.getBoolean(NotificationCompat.CATEGORY_STATUS));
            JSONArray jSONArray = this.wan_response.getJSONArray("message");
            String str2 = "free";
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (str.equals(jSONObject.getString("vlan").trim())) {
                    str2 = jSONObject.getString("interface").trim();
                }
            }
            return str2;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void pppoe_configure(Class<?> cls) {
        String trim = this.et_pppoe_vlan.getText().toString().trim();
        String trim2 = this.et_pppoe_username.getText().toString().trim();
        String trim3 = this.et_pppoe_pwd.getText().toString().trim();
        AlertHelperclass alertHelperclass = new AlertHelperclass();
        if (trim.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid PPPOE Vlan-Id", getActivity());
        } else if (trim2.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid PPPOE Username", getActivity());
        } else if (trim3.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid PPPOE Password", getActivity());
        } else {
            this.progress_dialog.show();
            String check_services = check_services(trim);
            if (check_services.equals("free")) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", "sv");
                    jSONObject.put("interface", "Add");
                    jSONObject.put("vlan", trim);
                    jSONObject.put("username", trim2);
                    jSONObject.put("password", trim3);
                    jSONObject.put("token", this.Token);
                    final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    final Class[] clsArr = {String.class, JSONObject.class};
                    final Object[] objArr = {this.Ont_Url, jSONObject};
                    final Class<?> cls2 = cls;
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final JSONObject jSONObject = (JSONObject) cls2.getMethod("configureInternet", clsArr).invoke(newInstance, objArr);
                                Ont_Configure_Quick_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                        try {
                                            if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                                Ont_Configure_Quick_Fragment.this.img_status_pppoe.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_ok, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                            } else {
                                                Ont_Configure_Quick_Fragment.this.img_status_pppoe.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_cross, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                            }
                                            Ont_Configure_Quick_Fragment.this.img_status_pppoe.setVisibility(0);
                                            if (Ont_Configure_Quick_Fragment.this.chk_voip.isChecked()) {
                                                Ont_Configure_Quick_Fragment.this.voip_configure(cls2);
                                            }
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException(e3);
                } catch (InstantiationException e4) {
                    throw new RuntimeException(e4);
                } catch (NoSuchMethodException e5) {
                    throw new RuntimeException(e5);
                }
            } else {
                this.progress_dialog.cancel();
                this.txt_alert.setText(Html.fromHtml("<b>PPPOE WAN CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services.toUpperCase() + ")</b> With Vlan <b>" + trim + "</b> is Already Present<br><br> Please Delete It and try again", 63));
                this.error_dialog.show();
                this.img_status_pppoe.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cross, getActivity().getTheme()));
                this.img_status_pppoe.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void voip_configure(Class<?> cls) {
        String trim = this.et_voip_vlan.getText().toString().trim();
        String trim2 = this.et_voip_tele.getText().toString().trim();
        String trim3 = this.et_voip_pwd.getText().toString().trim();
        String trim4 = this.et_voip_domain.getText().toString().trim();
        AlertHelperclass alertHelperclass = new AlertHelperclass();
        if (trim.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid VOIP Vlan-Id", getActivity());
        } else if (trim2.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid VOIP Telephone No", getActivity());
        } else if (trim3.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid VOIP Password", getActivity());
        } else if (trim4.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid VOIP Domain", getActivity());
        } else {
            this.progress_dialog.show();
            String check_services = check_services(trim);
            if (check_services.equals("free")) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("action", "sv");
                    jSONObject.put("interface", "Add");
                    jSONObject.put("vlan", trim);
                    jSONObject.put("username", trim2);
                    jSONObject.put("password", trim3);
                    jSONObject.put("domain", trim4);
                    jSONObject.put("token", this.Token);
                    final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    final Class[] clsArr = {String.class, JSONObject.class};
                    final Object[] objArr = {this.Ont_Url, jSONObject};
                    final Class<?> cls2 = cls;
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final JSONObject jSONObject = (JSONObject) cls2.getMethod("configureVoipInterface", clsArr).invoke(newInstance, objArr);
                                JSONObject jSONObject2 = (JSONObject) cls2.getMethod("configureVoipSetting", clsArr).invoke(newInstance, objArr);
                                Ont_Configure_Quick_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                        try {
                                            if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                                Ont_Configure_Quick_Fragment.this.img_status_voip.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_ok, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                            } else {
                                                Ont_Configure_Quick_Fragment.this.img_status_voip.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_cross, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                            }
                                            Ont_Configure_Quick_Fragment.this.img_status_voip.setVisibility(0);
                                            if (Ont_Configure_Quick_Fragment.this.chk_bridge.isChecked()) {
                                                Ont_Configure_Quick_Fragment.this.bridge_configure(cls2);
                                            }
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException(e3);
                } catch (InstantiationException e4) {
                    throw new RuntimeException(e4);
                } catch (NoSuchMethodException e5) {
                    throw new RuntimeException(e5);
                }
            } else {
                this.progress_dialog.cancel();
                this.txt_alert.setText(Html.fromHtml("<b>VOIP CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services.toUpperCase() + ")</b> With Vlan <b>" + trim + "</b> is Already Present<br><br> Please Delete It and try again", 63));
                this.error_dialog.show();
                this.img_status_voip.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cross, getActivity().getTheme()));
                this.img_status_voip.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void bridge_configure(Class<?> cls) {
        String trim = this.et_bridge_vlan.getText().toString().trim();
        AlertHelperclass alertHelperclass = new AlertHelperclass();
        if (trim.isEmpty()) {
            alertHelperclass.ntoast("Please Enter A Valid Bridged Tagged Vlan-Id", getActivity());
            return;
        }
        this.progress_dialog.show();
        String check_services = check_services(trim);
        if (check_services.equals("free")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("action", "sv");
                jSONObject.put("interface", "Add");
                jSONObject.put("vlan", trim);
                jSONObject.put("token", this.Token);
                final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                final Class[] clsArr = {String.class, JSONObject.class};
                final Object[] objArr = {this.Ont_Url, jSONObject};
                final Class<?> cls2 = cls;
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            final JSONObject jSONObject = (JSONObject) cls2.getMethod("configureBridge", clsArr).invoke(newInstance, objArr);
                            Ont_Configure_Quick_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                                    try {
                                        if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                            Ont_Configure_Quick_Fragment.this.img_status_bridge.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_ok, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                        } else {
                                            Ont_Configure_Quick_Fragment.this.img_status_bridge.setImageDrawable(Ont_Configure_Quick_Fragment.this.getResources().getDrawable(R.drawable.ic_action_cross, Ont_Configure_Quick_Fragment.this.getActivity().getTheme()));
                                        }
                                        Ont_Configure_Quick_Fragment.this.img_status_bridge.setVisibility(0);
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2);
            } catch (IllegalAccessException e3) {
                throw new RuntimeException(e3);
            } catch (InstantiationException e4) {
                throw new RuntimeException(e4);
            } catch (NoSuchMethodException e5) {
                throw new RuntimeException(e5);
            }
        } else {
            this.progress_dialog.cancel();
            this.txt_alert.setText(Html.fromHtml("<b>BRIDGE WAN CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services + ")</b> With Vlan <b>" + trim + "</b> is Already Present<br><br> Please Delete It and try again", 63));
            this.error_dialog.show();
            this.img_status_bridge.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_cross, getActivity().getTheme()));
            this.img_status_bridge.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void tr069_configure(Class<?> cls) {
        this.progress_dialog.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("acsurl", "http://acs.bsnl.in:7547");
            jSONObject.put("username", "acs");
            jSONObject.put("password", "acs@bsnl");
            jSONObject.put("interval", "300");
            jSONObject.put("token", this.Token);
            final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            final Class<?> cls2 = cls;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        cls2.getMethod("configureTr69Setting", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Quick_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Quick_Fragment.this.progress_dialog.cancel();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(e3);
        } catch (InstantiationException e4) {
            throw new RuntimeException(e4);
        } catch (NoSuchMethodException e5) {
            throw new RuntimeException(e5);
        }
    }

    /* access modifiers changed from: private */
    public void layout_gone(final View view) {
        view.animate().alpha(0.0f).setDuration((long) getResources().getInteger(17694720)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public void layout_visible(View view) {
        view.setAlpha(0.0f);
        view.setVisibility(0);
        view.animate().alpha(1.0f).setDuration((long) getResources().getInteger(17694720)).setListener((Animator.AnimatorListener) null);
    }
}
