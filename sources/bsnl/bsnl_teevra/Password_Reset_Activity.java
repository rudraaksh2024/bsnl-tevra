package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class Password_Reset_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Mobile;
    private Button btn_otp_submit;
    private Button btn_submit;
    private CountDownTimer cTimer = null;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_otp1;
    /* access modifiers changed from: private */
    public EditText et_otp2;
    /* access modifiers changed from: private */
    public EditText et_otp3;
    /* access modifiers changed from: private */
    public EditText et_otp4;
    /* access modifiers changed from: private */
    public EditText et_otp5;
    /* access modifiers changed from: private */
    public EditText et_otp6;
    private EditText et_password;
    private EditText et_username;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_otp;
    /* access modifiers changed from: private */
    public LinearLayout lay_password_reset;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private RelativeLayout relativeLayout;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView tv_resendotp;
    /* access modifiers changed from: private */
    public TextView tv_timer;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.password_reset_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        String string = sharedPreferences2.getString("username_Key", (String) null);
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
        this.et_username = (EditText) findViewById(R.id.et_username);
        this.et_password = (EditText) findViewById(R.id.et_password);
        this.btn_submit = (Button) findViewById(R.id.btn_submit);
        this.lay_password_reset = (LinearLayout) findViewById(R.id.lay_password_reset);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.lay_progressbar);
        this.relativeLayout = relativeLayout2;
        relativeLayout2.setVisibility(8);
        this.et_otp1 = (EditText) findViewById(R.id.et_otp1);
        this.et_otp2 = (EditText) findViewById(R.id.et_otp2);
        this.et_otp3 = (EditText) findViewById(R.id.et_otp3);
        this.et_otp4 = (EditText) findViewById(R.id.et_otp4);
        this.et_otp5 = (EditText) findViewById(R.id.et_otp5);
        this.et_otp6 = (EditText) findViewById(R.id.et_otp6);
        this.btn_otp_submit = (Button) findViewById(R.id.btn_otp_submit);
        this.tv_resendotp = (TextView) findViewById(R.id.tv_resendotp);
        this.tv_timer = (TextView) findViewById(R.id.tv_timer);
        this.tv_resendotp.setPaintFlags(8);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_otp);
        this.lay_otp = linearLayout;
        linearLayout.setVisibility(8);
        if (string != null) {
            this.et_username.setText(string);
            this.et_username.setEnabled(false);
        }
        this.btn_submit.setOnClickListener(this);
        this.btn_otp_submit.setOnClickListener(this);
        this.tv_resendotp.setOnClickListener(this);
        this.et_otp1.addTextChangedListener(new GenericTextWatcher(this.et_otp1));
        this.et_otp2.addTextChangedListener(new GenericTextWatcher(this.et_otp2));
        this.et_otp3.addTextChangedListener(new GenericTextWatcher(this.et_otp3));
        this.et_otp4.addTextChangedListener(new GenericTextWatcher(this.et_otp4));
        this.et_otp5.addTextChangedListener(new GenericTextWatcher(this.et_otp5));
        this.et_otp6.addTextChangedListener(new GenericTextWatcher(this.et_otp6));
    }

    public void onClick(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.lay_password_reset.getWindowToken(), 2);
        final String string = Settings.Secure.getString(getContentResolver(), "android_id");
        String str = getString(R.string.serverip) + "password_reset_validation.php";
        String str2 = getString(R.string.serverip) + "password_reset.php";
        final String trim = this.et_username.getText().toString().trim();
        final String trim2 = this.et_password.getText().toString().trim();
        if (view.getId() == R.id.btn_submit) {
            if (trim.equals("") || trim2.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID USERNAME & PASSWORD", getApplicationContext());
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass3 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                public void onResponse(String str) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("success")) {
                            String unused = Password_Reset_Activity.this.Mobile = jSONObject.getString("mobile");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Password_Reset_Activity.this);
                            View inflate = Password_Reset_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("VERIFY-OTP");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused2 = Password_Reset_Activity.this.info_dialog = builder.create();
                            Password_Reset_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("An 6-Digit OTP Is Sent To Your Registered Mobile"));
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Password_Reset_Activity.this.info_dialog.cancel();
                                }
                            });
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Password_Reset_Activity.this.lay_password_reset.setVisibility(8);
                                    Password_Reset_Activity.this.lay_otp.setVisibility(0);
                                    Password_Reset_Activity.this.startTimer();
                                    Password_Reset_Activity.this.info_dialog.cancel();
                                }
                            });
                            return;
                        }
                        Password_Reset_Activity.this.txt_alert.setText(jSONObject.getString("errorlog"));
                        Password_Reset_Activity.this.error_dialog.show();
                        Password_Reset_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Password_Reset_Activity.this.startActivity(new Intent(Password_Reset_Activity.this, Login_Activity.class));
                                Password_Reset_Activity.this.finish();
                                Password_Reset_Activity.this.error_dialog.cancel();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    Password_Reset_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Password_Reset_Activity.this.getApplicationContext()));
                    Password_Reset_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("username", trim);
                    hashMap.put("password", trim2);
                    hashMap.put("deviceid", string);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        } else if (view.getId() == R.id.tv_resendotp) {
            this.progress_dialog.show();
            RequestQueue newRequestQueue2 = Volley.newRequestQueue(this);
            AnonymousClass6 r02 = new StringRequest(1, str, new Response.Listener<String>() {
                public void onResponse(String str) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("success")) {
                            Password_Reset_Activity.this.tv_resendotp.setVisibility(8);
                            Password_Reset_Activity.this.cancelTimer();
                            Password_Reset_Activity.this.startTimer();
                            String unused = Password_Reset_Activity.this.Mobile = jSONObject.getString("mobile");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Password_Reset_Activity.this);
                            View inflate = Password_Reset_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("VERIFY-OTP");
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused2 = Password_Reset_Activity.this.info_dialog = builder.create();
                            Password_Reset_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("An 6-Digit OTP Is ReSent To Your Registered Mobile"));
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Password_Reset_Activity.this.info_dialog.cancel();
                                }
                            });
                            return;
                        }
                        Password_Reset_Activity.this.txt_alert.setText(jSONObject.getString("errorlog"));
                        Password_Reset_Activity.this.error_dialog.show();
                        Password_Reset_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Password_Reset_Activity.this.startActivity(new Intent(Password_Reset_Activity.this, Login_Activity.class));
                                Password_Reset_Activity.this.finish();
                                Password_Reset_Activity.this.error_dialog.cancel();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    Password_Reset_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Password_Reset_Activity.this.getApplicationContext()));
                    Password_Reset_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("username", trim);
                    hashMap.put("password", trim2);
                    hashMap.put("deviceid", string);
                    return hashMap;
                }
            };
            r02.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue2.add(r02);
        } else if (view.getId() == R.id.btn_otp_submit) {
            cancelTimer();
            String trim3 = this.et_otp1.getText().toString().trim();
            String trim4 = this.et_otp2.getText().toString().trim();
            String trim5 = this.et_otp3.getText().toString().trim();
            String trim6 = this.et_otp4.getText().toString().trim();
            String trim7 = this.et_otp5.getText().toString().trim();
            String trim8 = this.et_otp6.getText().toString().trim();
            if (trim3.isEmpty() || trim4.isEmpty() || trim5.isEmpty() || trim6.isEmpty() || trim7.isEmpty() || trim8.isEmpty()) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID 6 DIGIT OTP", getApplicationContext());
                return;
            }
            this.progress_dialog.show();
            final String str3 = trim3 + trim4 + trim5 + trim6 + trim7 + trim8;
            RequestQueue newRequestQueue3 = Volley.newRequestQueue(this);
            AnonymousClass9 r03 = new StringRequest(1, str2, new Response.Listener<String>() {
                public void onResponse(String str) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("success")) {
                            new AlertHelperclass().ptoast("PASSWORD IS RESET\nREDIRECTING TO LOGIN", Password_Reset_Activity.this.getApplicationContext());
                            Password_Reset_Activity.this.startActivity(new Intent(Password_Reset_Activity.this, Login_Activity.class));
                            Password_Reset_Activity.this.finish();
                            return;
                        }
                        Password_Reset_Activity.this.txt_alert.setText(jSONObject.getString("errorlog"));
                        Password_Reset_Activity.this.error_dialog.show();
                        Password_Reset_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Password_Reset_Activity.this.startActivity(new Intent(Password_Reset_Activity.this, Login_Activity.class));
                                Password_Reset_Activity.this.finish();
                                Password_Reset_Activity.this.error_dialog.cancel();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Password_Reset_Activity.this.progress_dialog.cancel();
                    Password_Reset_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Password_Reset_Activity.this.getApplicationContext()));
                    Password_Reset_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("username", trim);
                    hashMap.put("password", trim2);
                    hashMap.put("mobile", Password_Reset_Activity.this.Mobile);
                    hashMap.put("deviceid", string);
                    hashMap.put("otp", str3);
                    return hashMap;
                }
            };
            r03.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue3.add(r03);
        }
    }

    /* access modifiers changed from: private */
    public void startTimer() {
        AnonymousClass10 r0 = new CountDownTimer(120000, 1000) {
            public void onTick(long j) {
                long j2 = j / 1000;
                Password_Reset_Activity.this.tv_timer.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(j2 / 60)}) + ParameterizedMessage.ERROR_MSG_SEPARATOR + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(j2 % 60)}));
            }

            public void onFinish() {
                Password_Reset_Activity.this.txt_alert.setText("ERROR\n\nSession Expired, Please Try Again");
                Password_Reset_Activity.this.error_dialog.show();
                Password_Reset_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Password_Reset_Activity.this.finish();
                        Password_Reset_Activity.this.overridePendingTransition(0, 0);
                        Password_Reset_Activity.this.startActivity(Password_Reset_Activity.this.getIntent());
                        Password_Reset_Activity.this.overridePendingTransition(0, 0);
                    }
                });
            }
        };
        this.cTimer = r0;
        r0.start();
    }

    public void cancelTimer() {
        CountDownTimer countDownTimer = this.cTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private class GenericTextWatcher implements TextWatcher {
        private final View view;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public GenericTextWatcher(View view2) {
            this.view = view2;
        }

        public void afterTextChanged(Editable editable) {
            EditText editText;
            EditText editText2;
            boolean z = false;
            EditText editText3 = null;
            switch (this.view.getId()) {
                case R.id.et_otp1 /*2131231037*/:
                    if (Password_Reset_Activity.this.et_otp2.getText().length() == 1 && Password_Reset_Activity.this.et_otp3.getText().length() == 1 && Password_Reset_Activity.this.et_otp4.getText().length() == 1 && Password_Reset_Activity.this.et_otp5.getText().length() == 1 && Password_Reset_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText = Password_Reset_Activity.this.et_otp2;
                    break;
                case R.id.et_otp2 /*2131231038*/:
                    if (Password_Reset_Activity.this.et_otp1.getText().length() == 1 && Password_Reset_Activity.this.et_otp3.getText().length() == 1 && Password_Reset_Activity.this.et_otp4.getText().length() == 1 && Password_Reset_Activity.this.et_otp5.getText().length() == 1 && Password_Reset_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Password_Reset_Activity.this.et_otp3;
                    editText2 = Password_Reset_Activity.this.et_otp1;
                    break;
                case R.id.et_otp3 /*2131231039*/:
                    if (Password_Reset_Activity.this.et_otp1.getText().length() == 1 && Password_Reset_Activity.this.et_otp2.getText().length() == 1 && Password_Reset_Activity.this.et_otp4.getText().length() == 1 && Password_Reset_Activity.this.et_otp5.getText().length() == 1 && Password_Reset_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Password_Reset_Activity.this.et_otp4;
                    editText2 = Password_Reset_Activity.this.et_otp2;
                    break;
                case R.id.et_otp4 /*2131231040*/:
                    if (Password_Reset_Activity.this.et_otp1.getText().length() == 1 && Password_Reset_Activity.this.et_otp2.getText().length() == 1 && Password_Reset_Activity.this.et_otp3.getText().length() == 1 && Password_Reset_Activity.this.et_otp5.getText().length() == 1 && Password_Reset_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Password_Reset_Activity.this.et_otp5;
                    editText2 = Password_Reset_Activity.this.et_otp3;
                    break;
                case R.id.et_otp5 /*2131231041*/:
                    if (Password_Reset_Activity.this.et_otp1.getText().length() == 1 && Password_Reset_Activity.this.et_otp2.getText().length() == 1 && Password_Reset_Activity.this.et_otp3.getText().length() == 1 && Password_Reset_Activity.this.et_otp4.getText().length() == 1 && Password_Reset_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Password_Reset_Activity.this.et_otp6;
                    editText2 = Password_Reset_Activity.this.et_otp4;
                    break;
                case R.id.et_otp6 /*2131231042*/:
                    if (Password_Reset_Activity.this.et_otp1.getText().length() == 1 && Password_Reset_Activity.this.et_otp2.getText().length() == 1 && Password_Reset_Activity.this.et_otp3.getText().length() == 1 && Password_Reset_Activity.this.et_otp4.getText().length() == 1 && Password_Reset_Activity.this.et_otp5.getText().length() == 1) {
                        z = true;
                    }
                    editText2 = Password_Reset_Activity.this.et_otp5;
                    break;
                default:
                    editText = null;
                    break;
            }
            EditText editText4 = editText3;
            editText3 = editText2;
            editText = editText4;
            if (editable.length() > 1) {
                if (z) {
                    if (editText != null) {
                        editText.getText().clear();
                        moveToNextEdit(editText, (EditText) this.view);
                        return;
                    }
                    stayOnCurrentEdit((EditText) this.view);
                } else if (editText == null) {
                } else {
                    if (editText.getText().length() == 0) {
                        moveToNextEdit(editText, (EditText) this.view);
                    } else {
                        stayOnCurrentEdit((EditText) this.view);
                    }
                }
            } else if (editable.length() < 1 && editText3 != null) {
                moveToPreviousEdit(editText3);
            }
        }

        private void stayOnCurrentEdit(EditText editText) {
            editText.setText(editText.getText().toString().substring(0, 1));
            editText.setSelection(editText.getText().length());
        }

        private void moveToPreviousEdit(EditText editText) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
        }

        private void moveToNextEdit(EditText editText, EditText editText2) {
            editText.setText(editText2.getText().toString().substring(1, 2));
            editText.requestFocus();
            editText.setSelection(editText.getText().length());
            editText2.setText(editText2.getText().toString().substring(0, 1));
        }
    }
}
