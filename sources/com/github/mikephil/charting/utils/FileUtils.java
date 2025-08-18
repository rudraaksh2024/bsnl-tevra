package com.github.mikephil.charting.utils;

import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<Entry> loadEntriesFromFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[0]), (float) Integer.parseInt(split[1])));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry((float) Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c A[SYNTHETIC, Splitter:B:25:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b A[SYNTHETIC, Splitter:B:31:0x008b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.github.mikephil.charting.data.Entry> loadEntriesFromAssets(android.content.res.AssetManager r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "MPChart-FileUtils"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0072 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0072 }
            java.io.InputStream r7 = r7.open(r8)     // Catch:{ IOException -> 0x0072 }
            java.lang.String r8 = "UTF-8"
            r4.<init>(r7, r8)     // Catch:{ IOException -> 0x0072 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0072 }
            java.lang.String r7 = r3.readLine()     // Catch:{ IOException -> 0x006d, all -> 0x006a }
        L_0x001c:
            if (r7 == 0) goto L_0x0066
            java.lang.String r8 = "#"
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r8 = r7.length     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r2 = 2
            r4 = 0
            r5 = 1
            if (r8 > r2) goto L_0x003f
            com.github.mikephil.charting.data.Entry r8 = new com.github.mikephil.charting.data.Entry     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r2 = r7[r5]     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r7 = r7[r4]     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            float r7 = java.lang.Float.parseFloat(r7)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r8.<init>(r2, r7)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r1.add(r8)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            goto L_0x0061
        L_0x003f:
            int r8 = r7.length     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r8 = r8 - r5
            float[] r2 = new float[r8]     // Catch:{ IOException -> 0x006d, all -> 0x006a }
        L_0x0043:
            if (r4 >= r8) goto L_0x0050
            r6 = r7[r4]     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            float r6 = java.lang.Float.parseFloat(r6)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r2[r4] = r6     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r4 = r4 + 1
            goto L_0x0043
        L_0x0050:
            com.github.mikephil.charting.data.BarEntry r8 = new com.github.mikephil.charting.data.BarEntry     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r4 = r7.length     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r4 = r4 - r5
            r7 = r7[r4]     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            float r7 = (float) r7     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r8.<init>((float) r7, (float[]) r2)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            r1.add(r8)     // Catch:{ IOException -> 0x006d, all -> 0x006a }
        L_0x0061:
            java.lang.String r7 = r3.readLine()     // Catch:{ IOException -> 0x006d, all -> 0x006a }
            goto L_0x001c
        L_0x0066:
            r3.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0088
        L_0x006a:
            r7 = move-exception
            r2 = r3
            goto L_0x0089
        L_0x006d:
            r7 = move-exception
            r2 = r3
            goto L_0x0073
        L_0x0070:
            r7 = move-exception
            goto L_0x0089
        L_0x0072:
            r7 = move-exception
        L_0x0073:
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0070 }
            android.util.Log.e(r0, r7)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0088
        L_0x0080:
            r7 = move-exception
            java.lang.String r7 = r7.toString()
            android.util.Log.e(r0, r7)
        L_0x0088:
            return r1
        L_0x0089:
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0097
        L_0x008f:
            r8 = move-exception
            java.lang.String r8 = r8.toString()
            android.util.Log.e(r0, r8)
        L_0x0097:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.utils.FileUtils.loadEntriesFromAssets(android.content.res.AssetManager, java.lang.String):java.util.List");
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(LOG, e.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry next : list) {
                bufferedWriter.append(next.getY() + "#" + next.getX());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055 A[SYNTHETIC, Splitter:B:18:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0064 A[SYNTHETIC, Splitter:B:24:0x0064] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.github.mikephil.charting.data.BarEntry> loadBarEntriesFromAssets(android.content.res.AssetManager r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "MPChart-FileUtils"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x004b }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x004b }
            java.io.InputStream r5 = r5.open(r6)     // Catch:{ IOException -> 0x004b }
            java.lang.String r6 = "UTF-8"
            r4.<init>(r5, r6)     // Catch:{ IOException -> 0x004b }
            r3.<init>(r4)     // Catch:{ IOException -> 0x004b }
            java.lang.String r5 = r3.readLine()     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
        L_0x001c:
            if (r5 == 0) goto L_0x003f
            java.lang.String r6 = "#"
            java.lang.String[] r5 = r5.split(r6)     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            com.github.mikephil.charting.data.BarEntry r6 = new com.github.mikephil.charting.data.BarEntry     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            r2 = 1
            r2 = r5[r2]     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            r4 = 0
            r5 = r5[r4]     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            float r5 = java.lang.Float.parseFloat(r5)     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            r6.<init>((float) r2, (float) r5)     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            r1.add(r6)     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            java.lang.String r5 = r3.readLine()     // Catch:{ IOException -> 0x0046, all -> 0x0043 }
            goto L_0x001c
        L_0x003f:
            r3.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0061
        L_0x0043:
            r5 = move-exception
            r2 = r3
            goto L_0x0062
        L_0x0046:
            r5 = move-exception
            r2 = r3
            goto L_0x004c
        L_0x0049:
            r5 = move-exception
            goto L_0x0062
        L_0x004b:
            r5 = move-exception
        L_0x004c:
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0049 }
            android.util.Log.e(r0, r5)     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0061
        L_0x0059:
            r5 = move-exception
            java.lang.String r5 = r5.toString()
            android.util.Log.e(r0, r5)
        L_0x0061:
            return r1
        L_0x0062:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x0070
        L_0x0068:
            r6 = move-exception
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r0, r6)
        L_0x0070:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.utils.FileUtils.loadBarEntriesFromAssets(android.content.res.AssetManager, java.lang.String):java.util.List");
    }
}
