package org.apache.poi.hpsf;

import com.google.android.gms.location.DeviceOrientationRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Filetime {
    private static final BigInteger EPOCH_DIFF = BigInteger.valueOf(-11644473600000L);
    private static final BigInteger NANO_100 = BigInteger.valueOf(DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
    private long fileTime;

    public Filetime() {
    }

    public Filetime(Date date) {
        this.fileTime = dateToFileTime(date);
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this.fileTime = littleEndianByteArrayInputStream.readLong();
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[8];
        LittleEndian.putLong(bArr, 0, this.fileTime);
        return bArr;
    }

    public int write(OutputStream outputStream) throws IOException {
        outputStream.write(toByteArray());
        return 8;
    }

    public Date getJavaValue() {
        return filetimeToDate(this.fileTime);
    }

    public static Date filetimeToDate(long j) {
        return new Date((j < 0 ? twoComplement(j) : BigInteger.valueOf(j)).divide(NANO_100).add(EPOCH_DIFF).longValue());
    }

    public static long dateToFileTime(Date date) {
        return BigInteger.valueOf(date.getTime()).subtract(EPOCH_DIFF).multiply(NANO_100).longValue();
    }

    public static boolean isUndefined(Date date) {
        return date == null || dateToFileTime(date) == 0;
    }

    private static BigInteger twoComplement(long j) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) (j < 0 ? 0 : -1);
        bArr[1] = (byte) ((int) ((j >> 56) & 255));
        bArr[2] = (byte) ((int) ((j >> 48) & 255));
        bArr[3] = (byte) ((int) ((j >> 40) & 255));
        bArr[4] = (byte) ((int) ((j >> 32) & 255));
        bArr[5] = (byte) ((int) ((j >> 24) & 255));
        bArr[6] = (byte) ((int) ((j >> 16) & 255));
        bArr[7] = (byte) ((int) ((j >> 8) & 255));
        bArr[8] = (byte) ((int) (j & 255));
        return new BigInteger(bArr);
    }
}
