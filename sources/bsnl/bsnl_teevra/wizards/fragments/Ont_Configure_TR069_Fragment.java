package bsnl.bsnl_teevra.wizards.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.R;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_TR069_Fragment extends Fragment implements View.OnClickListener {
    private String Ont_Url;
    private String PACKAGE_NAME;
    private String Token;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public EditText et_acs_pwd;
    /* access modifiers changed from: private */
    public EditText et_acs_url;
    /* access modifiers changed from: private */
    public EditText et_acs_username;
    /* access modifiers changed from: private */
    public EditText et_inform;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout lay_refresh;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ont_configure_tr069_fragment, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate2);
        this.progress_dialog = builder.create();
        this.lay_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.lay_refresh);
        this.et_acs_url = (EditText) inflate.findViewById(R.id.et_acs_url);
        this.et_acs_username = (EditText) inflate.findViewById(R.id.et_acs_username);
        this.et_acs_pwd = (EditText) inflate.findViewById(R.id.et_acs_pwd);
        this.et_inform = (EditText) inflate.findViewById(R.id.et_inform);
        this.btn_submit = (TextView) inflate.findViewById(R.id.btn_submit);
        get_tr069_info();
        this.lay_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Ont_Configure_TR069_Fragment.this.get_tr069_info();
                Ont_Configure_TR069_Fragment.this.lay_refresh.setRefreshing(false);
            }
        });
        this.btn_submit.setOnClickListener(this);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void get_tr069_info() {
        this.progress_dialog.show();
        final Serializable serializable = getArguments().getSerializable("LOGIN_OBJECT");
        getArguments().getString("IMPORT_CLASS");
        this.PACKAGE_NAME = getArguments().getString("PACKAGE_NAME");
        try {
            final Class<?> cls = serializable.getClass();
            this.Token = (String) cls.getMethod("getToken", new Class[0]).invoke(serializable, new Object[0]);
            this.Ont_Url = (String) cls.getMethod("fetchUrl", new Class[0]).invoke(serializable, new Object[0]);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) cls.getMethod("getTr69Parameters", new Class[0]).invoke(serializable, new Object[0]);
                        Ont_Configure_TR069_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_TR069_Fragment.this.progress_dialog.cancel();
                                try {
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        JSONObject jSONObject = jSONObject.getJSONObject("message");
                                        String string = jSONObject.getString("acsurl");
                                        String string2 = jSONObject.getString("username");
                                        String string3 = jSONObject.getString("password");
                                        String string4 = jSONObject.getString("interval");
                                        Ont_Configure_TR069_Fragment.this.et_acs_url.setText(string);
                                        Ont_Configure_TR069_Fragment.this.et_acs_username.setText(string2);
                                        Ont_Configure_TR069_Fragment.this.et_acs_pwd.setText(string3);
                                        Ont_Configure_TR069_Fragment.this.et_inform.setText(string4);
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
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        }
    }

    public void onClick(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        String trim = this.et_acs_url.getText().toString().trim();
        String trim2 = this.et_acs_username.getText().toString().trim();
        String trim3 = this.et_acs_pwd.getText().toString().trim();
        String trim4 = this.et_inform.getText().toString().trim();
        if (trim.isEmpty()) {
            new AlertHelperclass().ntoast("ACS Url Cannot Be Blank", getActivity());
        } else if (trim4.isEmpty()) {
            new AlertHelperclass().ntoast("Inform Period Cannot Be Blank", getActivity());
        } else {
            this.progress_dialog.show();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("acsurl", trim);
                jSONObject.put("username", trim2);
                jSONObject.put("password", trim3);
                jSONObject.put("interval", trim4);
                jSONObject.put("token", this.Token);
                final Class<?> cls = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Setting_Class");
                final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                final Class[] clsArr = {String.class, JSONObject.class};
                final Object[] objArr = {this.Ont_Url, jSONObject};
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            cls.getMethod("configureTr69Setting", clsArr).invoke(newInstance, objArr);
                            Ont_Configure_TR069_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    Ont_Configure_TR069_Fragment.this.progress_dialog.cancel();
                                    Ont_Configure_TR069_Fragment.this.get_tr069_info();
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
            } catch (ClassNotFoundException e6) {
                throw new RuntimeException(e6);
            }
        }
    }
}
