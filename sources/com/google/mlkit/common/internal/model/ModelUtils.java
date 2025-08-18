package com.google.mlkit.common.internal.model;

import android.content.Context;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzag;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public class ModelUtils {
    private static final GmsLogger zza = new GmsLogger("ModelUtils", "");

    /* compiled from: com.google.mlkit:common@@18.9.0 */
    public static abstract class AutoMLManifest {
        public abstract String getLabelsFile();

        public abstract String getModelFile();

        public abstract String getModelType();
    }

    /* compiled from: com.google.mlkit:common@@18.9.0 */
    public static abstract class ModelLoggingInfo {
        static ModelLoggingInfo zza(long j, String str, boolean z) {
            return new AutoValue_ModelUtils_ModelLoggingInfo(j, zzag.zzb(str), z);
        }

        public abstract String getHash();

        public abstract long getSize();

        public abstract boolean isManifestModel();
    }

    private ModelUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x00fb A[SYNTHETIC, Splitter:B:82:0x00fb] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x010a A[SYNTHETIC, Splitter:B:90:0x010a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo getModelLoggingInfo(android.content.Context r11, com.google.mlkit.common.model.LocalModel r12) {
        /*
            java.lang.String r0 = r12.getAssetFilePath()
            java.lang.String r1 = r12.getAbsoluteFilePath()
            android.net.Uri r2 = r12.getUri()
            java.lang.String r3 = "Failed to open model file"
            java.lang.String r4 = "ModelUtils"
            r5 = 0
            if (r0 == 0) goto L_0x0047
            boolean r6 = r12.isManifestFile()
            if (r6 == 0) goto L_0x0022
            r6 = 1
            java.lang.String r0 = zzb(r11, r0, r6)
            if (r0 == 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            return r5
        L_0x0022:
            android.content.res.AssetManager r6 = r11.getAssets()     // Catch:{ IOException -> 0x0040 }
            android.content.res.AssetFileDescriptor r6 = r6.openFd(r0)     // Catch:{ IOException -> 0x0040 }
            long r7 = r6.getLength()     // Catch:{ all -> 0x0034 }
            if (r6 == 0) goto L_0x0073
            r6.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0073
        L_0x0034:
            r11 = move-exception
            if (r6 == 0) goto L_0x003f
            r6.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r12 = move-exception
            com.google.mlkit.common.internal.model.zzh.zza(r11, r12)     // Catch:{ IOException -> 0x0040 }
        L_0x003f:
            throw r11     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r3, r11)
            return r5
        L_0x0047:
            if (r1 == 0) goto L_0x0062
            boolean r6 = r12.isManifestFile()
            if (r6 == 0) goto L_0x0058
            r6 = 0
            java.lang.String r1 = zzb(r11, r1, r6)
            if (r1 == 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            return r5
        L_0x0058:
            java.io.File r6 = new java.io.File
            r6.<init>(r1)
            long r7 = r6.length()
            goto L_0x0073
        L_0x0062:
            if (r2 == 0) goto L_0x0128
            java.lang.String r6 = "r"
            android.content.res.AssetFileDescriptor r6 = com.google.android.gms.internal.mlkit_common.zzj.zza(r11, r2, r6)     // Catch:{ IOException -> 0x0121 }
            long r7 = r6.getLength()     // Catch:{ all -> 0x0115 }
            if (r6 == 0) goto L_0x0073
            r6.close()     // Catch:{ IOException -> 0x0121 }
        L_0x0073:
            com.google.mlkit.common.sdkinternal.MlKitContext r6 = com.google.mlkit.common.sdkinternal.MlKitContext.getInstance()
            java.lang.Class<com.google.mlkit.common.sdkinternal.SharedPrefManager> r9 = com.google.mlkit.common.sdkinternal.SharedPrefManager.class
            java.lang.Object r6 = r6.get(r9)
            com.google.mlkit.common.sdkinternal.SharedPrefManager r6 = (com.google.mlkit.common.sdkinternal.SharedPrefManager) r6
            if (r0 == 0) goto L_0x0083
            r9 = r0
            goto L_0x0091
        L_0x0083:
            if (r1 == 0) goto L_0x0087
            r9 = r1
            goto L_0x0091
        L_0x0087:
            java.lang.Object r9 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            android.net.Uri r9 = (android.net.Uri) r9
            java.lang.String r9 = r9.toString()
        L_0x0091:
            java.lang.String r10 = r6.zzb(r9, r7)
            if (r10 == 0) goto L_0x00a0
            boolean r11 = r12.isManifestFile()
            com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo r11 = com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo.zza(r7, r10, r11)
            return r11
        L_0x00a0:
            java.lang.String r10 = "Failed to close model file"
            if (r0 == 0) goto L_0x00b1
            android.content.res.AssetManager r11 = r11.getAssets()     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            java.io.InputStream r11 = r11.open(r0)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            goto L_0x00cc
        L_0x00ad:
            r11 = move-exception
            goto L_0x00f0
        L_0x00af:
            r11 = move-exception
            goto L_0x00f2
        L_0x00b1:
            if (r1 == 0) goto L_0x00be
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            r11.<init>(r0)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            goto L_0x00cc
        L_0x00be:
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            android.net.Uri r0 = (android.net.Uri) r0     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            int r1 = com.google.android.gms.internal.mlkit_common.zzj.zza     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            com.google.android.gms.internal.mlkit_common.zzi r1 = com.google.android.gms.internal.mlkit_common.zzi.zza     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
            java.io.InputStream r11 = com.google.android.gms.internal.mlkit_common.zzj.zzb(r11, r0, r1)     // Catch:{ IOException -> 0x00af, all -> 0x00ad }
        L_0x00cc:
            if (r11 == 0) goto L_0x00d5
            java.lang.String r0 = zzc(r11)     // Catch:{ IOException -> 0x00d3 }
            goto L_0x00d6
        L_0x00d3:
            r12 = move-exception
            goto L_0x00f4
        L_0x00d5:
            r0 = r5
        L_0x00d6:
            if (r0 == 0) goto L_0x00db
            r6.zzc(r9, r7, r0)     // Catch:{ IOException -> 0x00d3 }
        L_0x00db:
            boolean r12 = r12.isManifestFile()     // Catch:{ IOException -> 0x00d3 }
            com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo r12 = com.google.mlkit.common.internal.model.ModelUtils.ModelLoggingInfo.zza(r7, r0, r12)     // Catch:{ IOException -> 0x00d3 }
            if (r11 == 0) goto L_0x00ef
            r11.close()     // Catch:{ IOException -> 0x00e9 }
            goto L_0x00ef
        L_0x00e9:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            r0.e(r4, r10, r11)
        L_0x00ef:
            return r12
        L_0x00f0:
            r12 = r11
            goto L_0x0108
        L_0x00f2:
            r12 = r11
            r11 = r5
        L_0x00f4:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x0106 }
            r0.e(r4, r3, r12)     // Catch:{ all -> 0x0106 }
            if (r11 == 0) goto L_0x0105
            r11.close()     // Catch:{ IOException -> 0x00ff }
            goto L_0x0105
        L_0x00ff:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r10, r11)
        L_0x0105:
            return r5
        L_0x0106:
            r12 = move-exception
            r5 = r11
        L_0x0108:
            if (r5 == 0) goto L_0x0114
            r5.close()     // Catch:{ IOException -> 0x010e }
            goto L_0x0114
        L_0x010e:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            r0.e(r4, r10, r11)
        L_0x0114:
            throw r12
        L_0x0115:
            r11 = move-exception
            if (r6 == 0) goto L_0x0120
            r6.close()     // Catch:{ all -> 0x011c }
            goto L_0x0120
        L_0x011c:
            r12 = move-exception
            com.google.mlkit.common.internal.model.zzh.zza(r11, r12)     // Catch:{ IOException -> 0x0121 }
        L_0x0120:
            throw r11     // Catch:{ IOException -> 0x0121 }
        L_0x0121:
            r11 = move-exception
            com.google.android.gms.common.internal.GmsLogger r12 = zza
            r12.e(r4, r3, r11)
            return r5
        L_0x0128:
            com.google.android.gms.common.internal.GmsLogger r11 = zza
            java.lang.String r12 = "Local model doesn't have any valid path."
            r11.e(r4, r12)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.getModelLoggingInfo(android.content.Context, com.google.mlkit.common.model.LocalModel):com.google.mlkit.common.internal.model.ModelUtils$ModelLoggingInfo");
    }

    public static String getSHA256(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            String zzc = zzc(fileInputStream);
            fileInputStream.close();
            return zzc;
        } catch (IOException e) {
            zza.e("ModelUtils", "Failed to create FileInputStream for model: ".concat(e.toString()));
            return null;
        } catch (Throwable th) {
            zzh.zza(th, th);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002d, code lost:
        if (new java.io.File(r6).exists() == false) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.mlkit.common.internal.model.ModelUtils.AutoMLManifest parseManifestFile(java.lang.String r6, boolean r7, android.content.Context r8) {
        /*
            java.lang.String r0 = "Json string from the manifest file: "
            java.lang.String r1 = java.lang.String.valueOf(r6)
            com.google.android.gms.common.internal.GmsLogger r2 = zza
            java.lang.String r3 = "Manifest file path: "
            java.lang.String r1 = r3.concat(r1)
            java.lang.String r3 = "ModelUtils"
            r2.d(r3, r1)
            r1 = 0
            if (r7 == 0) goto L_0x0024
            android.content.res.AssetManager r4 = r8.getAssets()     // Catch:{ IOException -> 0x002f }
            java.io.InputStream r4 = r4.open(r6)     // Catch:{ IOException -> 0x002f }
            if (r4 == 0) goto L_0x0037
            r4.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0037
        L_0x0024:
            java.io.File r4 = new java.io.File
            r4.<init>(r6)
            boolean r4 = r4.exists()
            if (r4 != 0) goto L_0x0037
        L_0x002f:
            com.google.android.gms.common.internal.GmsLogger r6 = zza
            java.lang.String r7 = "Manifest file does not exist."
            r6.e(r3, r7)
            return r1
        L_0x0037:
            boolean r4 = r6.isEmpty()     // Catch:{ IOException | JSONException -> 0x009d }
            r5 = 0
            if (r4 == 0) goto L_0x0041
            byte[] r6 = new byte[r5]     // Catch:{ IOException | JSONException -> 0x009d }
            goto L_0x0066
        L_0x0041:
            if (r7 == 0) goto L_0x004c
            android.content.res.AssetManager r7 = r8.getAssets()     // Catch:{ IOException | JSONException -> 0x009d }
            java.io.InputStream r6 = r7.open(r6)     // Catch:{ IOException | JSONException -> 0x009d }
            goto L_0x0057
        L_0x004c:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException | JSONException -> 0x009d }
            java.io.File r8 = new java.io.File     // Catch:{ IOException | JSONException -> 0x009d }
            r8.<init>(r6)     // Catch:{ IOException | JSONException -> 0x009d }
            r7.<init>(r8)     // Catch:{ IOException | JSONException -> 0x009d }
            r6 = r7
        L_0x0057:
            int r7 = r6.available()     // Catch:{ all -> 0x0091 }
            byte[] r8 = new byte[r7]     // Catch:{ all -> 0x0091 }
            r6.read(r8, r5, r7)     // Catch:{ all -> 0x0091 }
            if (r6 == 0) goto L_0x0065
            r6.close()     // Catch:{ IOException | JSONException -> 0x009d }
        L_0x0065:
            r6 = r8
        L_0x0066:
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException | JSONException -> 0x009d }
            java.lang.String r8 = "UTF-8"
            r7.<init>(r6, r8)     // Catch:{ IOException | JSONException -> 0x009d }
            java.lang.String r6 = r0.concat(r7)     // Catch:{ IOException | JSONException -> 0x009d }
            r2.d(r3, r6)     // Catch:{ IOException | JSONException -> 0x009d }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ IOException | JSONException -> 0x009d }
            r6.<init>(r7)     // Catch:{ IOException | JSONException -> 0x009d }
            java.lang.String r7 = "modelType"
            java.lang.String r7 = r6.getString(r7)     // Catch:{ IOException | JSONException -> 0x009d }
            java.lang.String r8 = "modelFile"
            java.lang.String r8 = r6.getString(r8)     // Catch:{ IOException | JSONException -> 0x009d }
            java.lang.String r0 = "labelsFile"
            java.lang.String r6 = r6.getString(r0)     // Catch:{ IOException | JSONException -> 0x009d }
            com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest r0 = new com.google.mlkit.common.internal.model.AutoValue_ModelUtils_AutoMLManifest     // Catch:{ IOException | JSONException -> 0x009d }
            r0.<init>(r7, r8, r6)     // Catch:{ IOException | JSONException -> 0x009d }
            return r0
        L_0x0091:
            r7 = move-exception
            if (r6 == 0) goto L_0x009c
            r6.close()     // Catch:{ all -> 0x0098 }
            goto L_0x009c
        L_0x0098:
            r6 = move-exception
            com.google.mlkit.common.internal.model.zzh.zza(r7, r6)     // Catch:{ IOException | JSONException -> 0x009d }
        L_0x009c:
            throw r7     // Catch:{ IOException | JSONException -> 0x009d }
        L_0x009d:
            r6 = move-exception
            com.google.android.gms.common.internal.GmsLogger r7 = zza
            java.lang.String r8 = "Error parsing the manifest file."
            r7.e(r3, r8, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.internal.model.ModelUtils.parseManifestFile(java.lang.String, boolean, android.content.Context):com.google.mlkit.common.internal.model.ModelUtils$AutoMLManifest");
    }

    public static boolean zza(File file, String str) {
        String sha256 = getSHA256(file);
        zza.d("ModelUtils", "Calculated hash value is: ".concat(String.valueOf(sha256)));
        return str.equals(sha256);
    }

    private static String zzb(Context context, String str, boolean z) {
        AutoMLManifest parseManifestFile = parseManifestFile(str, z, context);
        if (parseManifestFile != null) {
            return new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
        }
        zza.e("ModelUtils", "Failed to parse manifest file.");
        return null;
    }

    private static String zzc(InputStream inputStream) {
        int i;
        try {
            MessageDigest instance = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            zza.e("ModelUtils", "Do not have SHA-256 algorithm");
            return null;
        } catch (IOException unused2) {
            zza.e("ModelUtils", "Failed to read model file");
            return null;
        }
    }
}
