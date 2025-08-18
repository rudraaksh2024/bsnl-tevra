package bsnl.bsnl_teevra;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Toast;

public class AlertHelperclass {
    public void ptoast(String str, Context context) {
        Toast toast = new Toast(context);
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.mediumtext));
        textView.setTypeface((Typeface) null, 3);
        textView.setGravity(17);
        textView.setTextColor(context.getResources().getColor(R.color.colorGreen));
        textView.setBackgroundResource(R.drawable.style13);
        textView.setPadding(30, 30, 30, 30);
        toast.setView(textView);
        toast.show();
    }

    public void ntoast(String str, Context context) {
        Toast toast = new Toast(context);
        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.mediumtext));
        textView.setTypeface((Typeface) null, 3);
        textView.setGravity(17);
        textView.setTextColor(context.getResources().getColor(R.color.colorRed));
        textView.setBackgroundResource(R.drawable.style13);
        textView.setPadding(30, 30, 30, 30);
        toast.setView(textView);
        toast.show();
    }
}
