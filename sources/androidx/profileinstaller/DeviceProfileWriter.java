package androidx.profileinstaller;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final byte[] mDesiredVersion;
    private boolean mDeviceSupportsAotProfile = false;
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;

    private void result(int i, Object obj) {
        this.mExecutor.execute(new DeviceProfileWriter$$ExternalSyntheticLambda0(this, i, obj));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$result$0$androidx-profileinstaller-DeviceProfileWriter  reason: not valid java name */
    public /* synthetic */ void m231lambda$result$0$androidxprofileinstallerDeviceProfileWriter(int i, Object obj) {
        this.mDiagnostics.onResultReceived(i, obj);
    }

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, String str2, String str3, File file) {
        this.mAssetManager = assetManager;
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
        this.mApkName = str;
        this.mProfileSourceLocation = str2;
        this.mProfileMetaSourceLocation = str3;
        this.mCurProfile = file;
        this.mDesiredVersion = desiredVersion();
    }

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if (this.mDesiredVersion == null) {
            result(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        } else if (!this.mCurProfile.canWrite()) {
            result(4, (Object) null);
            return false;
        } else {
            this.mDeviceSupportsAotProfile = true;
            return true;
        }
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if (!this.mDeviceSupportsAotProfile) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    public DeviceProfileWriter read() {
        FileInputStream createInputStream;
        FileInputStream createInputStream2;
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        if (this.mDesiredVersion == null) {
            return this;
        }
        try {
            AssetFileDescriptor openFd = this.mAssetManager.openFd(this.mProfileSourceLocation);
            try {
                createInputStream2 = openFd.createInputStream();
                this.mProfile = ProfileTranscoder.readProfile(createInputStream2, ProfileTranscoder.readHeader(createInputStream2, ProfileTranscoder.MAGIC_PROF), this.mApkName);
                if (createInputStream2 != null) {
                    createInputStream2.close();
                }
                if (openFd != null) {
                    openFd.close();
                }
                DexProfileData[] dexProfileDataArr = this.mProfile;
                if (dexProfileDataArr != null && requiresMetadata()) {
                    try {
                        AssetFileDescriptor openFd2 = this.mAssetManager.openFd(this.mProfileMetaSourceLocation);
                        try {
                            createInputStream = openFd2.createInputStream();
                            this.mProfile = ProfileTranscoder.readMeta(createInputStream, ProfileTranscoder.readHeader(createInputStream, ProfileTranscoder.MAGIC_PROFM), this.mDesiredVersion, dexProfileDataArr);
                            if (createInputStream != null) {
                                createInputStream.close();
                            }
                            if (openFd2 != null) {
                                openFd2.close();
                            }
                            return this;
                        } catch (Throwable th) {
                            if (openFd2 != null) {
                                openFd2.close();
                            }
                            throw th;
                        }
                    } catch (FileNotFoundException e) {
                        this.mDiagnostics.onResultReceived(9, e);
                    } catch (IOException e2) {
                        this.mDiagnostics.onResultReceived(7, e2);
                    } catch (IllegalStateException e3) {
                        this.mProfile = null;
                        this.mDiagnostics.onResultReceived(8, e3);
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                return this;
            } catch (Throwable th3) {
                if (openFd != null) {
                    openFd.close();
                }
                throw th3;
            }
        } catch (FileNotFoundException e4) {
            this.mDiagnostics.onResultReceived(6, e4);
        } catch (IOException e5) {
            this.mDiagnostics.onResultReceived(7, e5);
        } catch (IllegalStateException e6) {
            this.mDiagnostics.onResultReceived(8, e6);
        } catch (Throwable th4) {
            th3.addSuppressed(th4);
        }
        throw th;
        throw th;
    }

    public DeviceProfileWriter transcodeIfNeeded() {
        ByteArrayOutputStream byteArrayOutputStream;
        DexProfileData[] dexProfileDataArr = this.mProfile;
        byte[] bArr = this.mDesiredVersion;
        if (!(dexProfileDataArr == null || bArr == null)) {
            assertDeviceAllowsProfileInstallerAotWritesCalled();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                ProfileTranscoder.writeHeader(byteArrayOutputStream, bArr);
                if (!ProfileTranscoder.transcodeAndWriteBody(byteArrayOutputStream, bArr, dexProfileDataArr)) {
                    this.mDiagnostics.onResultReceived(5, (Object) null);
                    this.mProfile = null;
                    byteArrayOutputStream.close();
                    return this;
                }
                this.mTranscodedProfile = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                this.mProfile = null;
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
            } catch (IllegalStateException e2) {
                this.mDiagnostics.onResultReceived(8, e2);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return this;
        throw th;
    }

    public boolean write() {
        FileOutputStream fileOutputStream;
        byte[] bArr = this.mTranscodedProfile;
        if (bArr == null) {
            return false;
        }
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(this.mCurProfile);
                Encoding.writeAll(byteArrayInputStream, fileOutputStream);
                result(1, (Object) null);
                fileOutputStream.close();
                byteArrayInputStream.close();
                this.mTranscodedProfile = null;
                this.mProfile = null;
                return true;
            } catch (Throwable th) {
                byteArrayInputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e) {
            result(6, e);
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        } catch (IOException e2) {
            try {
                result(7, e2);
                this.mTranscodedProfile = null;
                this.mProfile = null;
                return false;
            } catch (Throwable th2) {
                this.mTranscodedProfile = null;
                this.mProfile = null;
                throw th2;
            }
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        throw th;
    }

    private static byte[] desiredVersion() {
        switch (Build.VERSION.SDK_INT) {
            case 29:
            case 30:
                return ProfileVersion.V010_P;
            case 31:
            case 32:
            case 33:
                return ProfileVersion.V015_S;
            default:
                return null;
        }
    }

    private static boolean requiresMetadata() {
        switch (Build.VERSION.SDK_INT) {
            case 31:
            case 32:
            case 33:
                return true;
            default:
                return false;
        }
    }
}
