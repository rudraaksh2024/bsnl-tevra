package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Report_Misc_Dia2_Fragment extends Fragment implements TextWatcher, View.OnClickListener {
    public static final int STORAGE_PERMISSION_REQ = 1000;
    private static String ccode;
    private static JSONArray jsonArray;
    private static String scode;
    private EditText et_search;
    private ImageView img_download;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_result;
    private TableLayout tbl_detail;
    private TextView txt_header;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = "MOBILE";
        View inflate = layoutInflater.inflate(R.layout.report_misc_dia2_fragment, viewGroup, false);
        String string = getArguments().getString("response");
        String string2 = getArguments().getString("circle");
        String string3 = getArguments().getString("ssa");
        this.tbl_detail = (TableLayout) inflate.findViewById(R.id.tbl_detail);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_download);
        this.img_download = imageView;
        imageView.setOnClickListener(this);
        this.txt_header = (TextView) inflate.findViewById(R.id.txt_header);
        this.et_search = (EditText) inflate.findViewById(R.id.et_search);
        this.lay_result = (LinearLayout) inflate.findViewById(R.id.lay_result);
        this.txt_header.setText("CIRCLE : " + string2 + " | SSA : " + string3);
        try {
            jsonArray = new JSONObject(string).getJSONArray("ROWSET");
            this.tbl_detail.removeAllViews();
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
            TextView textView = new TextView(getActivity());
            textView.setText("SR");
            textView.setTextColor(-1);
            textView.setGravity(17);
            textView.setPadding(20, 15, 20, 15);
            textView.setTypeface((Typeface) null, 1);
            textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView.setBackgroundResource(R.drawable.new_style1);
            TextView textView2 = new TextView(getActivity());
            textView2.setText("USERNAME");
            textView2.setTextColor(-1);
            textView2.setGravity(17);
            textView2.setPadding(20, 15, 20, 15);
            textView2.setTypeface((Typeface) null, 1);
            textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView2.setBackgroundResource(R.drawable.new_style1);
            TextView textView3 = new TextView(getActivity());
            textView3.setText("NAME");
            textView3.setTextColor(-1);
            textView3.setGravity(17);
            textView3.setPadding(20, 15, 20, 15);
            textView3.setTypeface((Typeface) null, 1);
            textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView3.setBackgroundResource(R.drawable.new_style1);
            TextView textView4 = new TextView(getActivity());
            textView4.setText(str);
            textView4.setTextColor(-1);
            textView4.setGravity(17);
            textView4.setPadding(20, 15, 20, 15);
            textView4.setTypeface((Typeface) null, 1);
            textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView4.setBackgroundResource(R.drawable.new_style1);
            TextView textView5 = new TextView(getActivity());
            textView5.setText("LAST LOGIN");
            textView5.setTextColor(-1);
            textView5.setGravity(17);
            textView5.setPadding(20, 15, 20, 15);
            textView5.setTypeface((Typeface) null, 1);
            textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView5.setBackgroundResource(R.drawable.new_style1);
            TextView textView6 = new TextView(getActivity());
            textView6.setText("INACTIVITY");
            textView6.setTextColor(-1);
            textView6.setGravity(17);
            textView6.setPadding(20, 15, 20, 15);
            textView6.setTypeface((Typeface) null, 1);
            textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
            textView6.setBackgroundResource(R.drawable.new_style1);
            tableRow.addView(textView);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableRow.addView(textView4);
            tableRow.addView(textView5);
            tableRow.addView(textView6);
            this.tbl_detail.addView(tableRow);
            int i = 0;
            while (i < jsonArray.length()) {
                JSONObject jSONObject = jsonArray.getJSONObject(i);
                ccode = jSONObject.getString("CCODE").trim();
                scode = jSONObject.getString("SCODE").trim();
                TableRow tableRow2 = new TableRow(getActivity());
                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                TextView textView7 = new TextView(getActivity());
                i++;
                textView7.setText(Integer.toString(i));
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setPadding(20, 15, 20, 15);
                textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView7.setBackgroundResource(R.drawable.style17);
                TextView textView8 = new TextView(getActivity());
                textView8.setText(jSONObject.getString("USERNAME"));
                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView8.setGravity(17);
                textView8.setPadding(20, 15, 20, 15);
                textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView8.setBackgroundResource(R.drawable.style17);
                TextView textView9 = new TextView(getActivity());
                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView9.setGravity(17);
                textView9.setPadding(20, 15, 20, 15);
                textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView9.setBackgroundResource(R.drawable.style17);
                if (jSONObject.getString("DESIGNATION").trim().equals("FRANCHISEE OWNER")) {
                    textView9.setText(jSONObject.getString("NAME") + " (FO)");
                } else if (jSONObject.getString("DESIGNATION").trim().equals("FRANCHISEE MEMBER")) {
                    textView9.setText(jSONObject.getString("NAME") + " (FM)");
                } else {
                    textView9.setText(jSONObject.getString("NAME") + " (" + jSONObject.getString("DESIGNATION").trim() + ")");
                }
                TextView textView10 = new TextView(getActivity());
                textView10.setText(jSONObject.getString(str).toUpperCase());
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setPadding(20, 15, 20, 15);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView10.setBackgroundResource(R.drawable.style17);
                TextView textView11 = new TextView(getActivity());
                textView11.setText(jSONObject.getString("LAST_LOGIN"));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                String str2 = str;
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(getActivity());
                textView12.setText(jSONObject.getString("INACTIVITY") + " Days");
                textView12.setTextColor(SupportMenu.CATEGORY_MASK);
                textView12.setGravity(17);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                tableRow2.addView(textView7);
                tableRow2.addView(textView8);
                tableRow2.addView(textView9);
                tableRow2.addView(textView10);
                tableRow2.addView(textView11);
                tableRow2.addView(textView12);
                this.tbl_detail.addView(tableRow2);
                str = str2;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.et_search.addTextChangedListener(this);
        return inflate;
    }

    public void afterTextChanged(Editable editable) {
        String str;
        String str2;
        int i;
        String str3 = "DESIGNATION";
        String obj = this.et_search.getText().toString();
        this.tbl_detail.removeAllViews();
        TableRow tableRow = new TableRow(getActivity());
        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
        TextView textView = new TextView(getActivity());
        textView.setText("SR");
        textView.setTextColor(-1);
        textView.setGravity(17);
        textView.setPadding(20, 15, 20, 15);
        textView.setTypeface((Typeface) null, 1);
        textView.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView.setBackgroundResource(R.drawable.new_style1);
        TextView textView2 = new TextView(getActivity());
        textView2.setText("USERNAME");
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        textView2.setPadding(20, 15, 20, 15);
        textView2.setTypeface((Typeface) null, 1);
        textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView2.setBackgroundResource(R.drawable.new_style1);
        TextView textView3 = new TextView(getActivity());
        textView3.setText("NAME");
        textView3.setTextColor(-1);
        textView3.setGravity(17);
        textView3.setPadding(20, 15, 20, 15);
        textView3.setTypeface((Typeface) null, 1);
        textView3.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView3.setBackgroundResource(R.drawable.new_style1);
        TextView textView4 = new TextView(getActivity());
        textView4.setText("MOBILE");
        textView4.setTextColor(-1);
        textView4.setGravity(17);
        textView4.setPadding(20, 15, 20, 15);
        textView4.setTypeface((Typeface) null, 1);
        textView4.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView4.setBackgroundResource(R.drawable.new_style1);
        TextView textView5 = new TextView(getActivity());
        textView5.setText("LAST LOGIN");
        textView5.setTextColor(-1);
        textView5.setGravity(17);
        textView5.setPadding(20, 15, 20, 15);
        textView5.setTypeface((Typeface) null, 1);
        textView5.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView5.setBackgroundResource(R.drawable.new_style1);
        TextView textView6 = new TextView(getActivity());
        textView6.setText("INACTIVITY");
        textView6.setTextColor(-1);
        textView6.setGravity(17);
        textView6.setPadding(20, 15, 20, 15);
        textView6.setTypeface((Typeface) null, 1);
        textView6.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
        textView6.setBackgroundResource(R.drawable.new_style1);
        tableRow.addView(textView);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        tableRow.addView(textView4);
        tableRow.addView(textView5);
        tableRow.addView(textView6);
        this.tbl_detail.addView(tableRow);
        int i2 = 0;
        int i3 = 1;
        while (i2 < jsonArray.length()) {
            try {
                JSONObject jSONObject = jsonArray.getJSONObject(i2);
                ccode = jSONObject.getString("CCODE").trim();
                scode = jSONObject.getString("SCODE").trim();
                if (!jSONObject.getString("USERNAME").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("NAME").toUpperCase().contains(obj.toUpperCase())) {
                    if (!jSONObject.getString("MOBILE").toUpperCase().contains(obj.toUpperCase())) {
                        str = str3;
                        str2 = obj;
                        i = i3;
                        i2++;
                        i3 = i;
                        obj = str2;
                        str3 = str;
                    }
                }
                TableRow tableRow2 = new TableRow(getActivity());
                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -2));
                TextView textView7 = new TextView(getActivity());
                i = i3 + 1;
                textView7.setText(Integer.toString(i3));
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setPadding(20, 15, 20, 15);
                textView7.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView7.setBackgroundResource(R.drawable.style17);
                TextView textView8 = new TextView(getActivity());
                textView8.setText(jSONObject.getString("USERNAME"));
                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView8.setGravity(17);
                textView8.setPadding(20, 15, 20, 15);
                textView8.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView8.setBackgroundResource(R.drawable.style17);
                TextView textView9 = new TextView(getActivity());
                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView9.setGravity(17);
                textView9.setPadding(20, 15, 20, 15);
                textView9.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView9.setBackgroundResource(R.drawable.style17);
                if (jSONObject.getString(str3).trim().equals("FRANCHISEE OWNER")) {
                    textView9.setText(jSONObject.getString("NAME") + " (FO)");
                } else if (jSONObject.getString(str3).trim().equals("FRANCHISEE MEMBER")) {
                    textView9.setText(jSONObject.getString("NAME") + " (FM)");
                } else {
                    textView9.setText(jSONObject.getString("NAME") + " (" + jSONObject.getString(str3).trim() + ")");
                }
                TextView textView10 = new TextView(getActivity());
                textView10.setText(jSONObject.getString("MOBILE").toUpperCase());
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                str = str3;
                textView10.setPadding(20, 15, 20, 15);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView10.setBackgroundResource(R.drawable.style17);
                TextView textView11 = new TextView(getActivity());
                textView11.setText(jSONObject.getString("LAST_LOGIN"));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                str2 = obj;
                textView11.setPadding(20, 15, 20, 15);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView11.setBackgroundResource(R.drawable.style17);
                TextView textView12 = new TextView(getActivity());
                textView12.setText(jSONObject.getString("INACTIVITY") + " Days");
                textView12.setTextColor(SupportMenu.CATEGORY_MASK);
                textView12.setGravity(17);
                textView12.setPadding(20, 15, 20, 15);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                textView12.setBackgroundResource(R.drawable.style17);
                tableRow2.addView(textView7);
                tableRow2.addView(textView8);
                tableRow2.addView(textView9);
                tableRow2.addView(textView10);
                tableRow2.addView(textView11);
                tableRow2.addView(textView12);
                this.tbl_detail.addView(tableRow2);
                i2++;
                i3 = i;
                obj = str2;
                str3 = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_download) {
            String format = new SimpleDateFormat("dd MMM yy").format(new Date());
            Bitmap createBitmap = Bitmap.createBitmap(this.lay_result.getWidth(), this.lay_result.getHeight(), Bitmap.Config.ARGB_8888);
            this.lay_result.draw(new Canvas(createBitmap));
            check_for_permission(createBitmap, new File(Environment.getExternalStorageDirectory() + "/BSNL-TEEVRA/" + ccode + "_" + scode + "_DAI(" + format + ").jpg"));
        }
    }

    private void check_for_permission(Bitmap bitmap, File file) {
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File file2 = new File(Environment.getExternalStorageDirectory(), "BSNL-TEEVRA");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                new AlertHelperclass().ptoast("IMAGE SAVED...", getActivity());
            } catch (Exception unused) {
            }
        } else if (shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 1000);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_error)).setText("Action Needs Access Of Device Storage");
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Report_Misc_Dia2_Fragment.this.info_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_update)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse("package:" + Report_Misc_Dia2_Fragment.this.getActivity().getPackageName()));
                    intent.addFlags(268435456);
                    intent.addFlags(BasicMeasure.EXACTLY);
                    intent.addFlags(8388608);
                    Report_Misc_Dia2_Fragment.this.startActivity(intent);
                    Report_Misc_Dia2_Fragment.this.info_dialog.cancel();
                }
            });
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
