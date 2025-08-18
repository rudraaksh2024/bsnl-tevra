package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Timer implements Serializable, StringBuilderFormattable {
    private static long NANO_PER_HOUR = 0;
    private static long NANO_PER_MINUTE = 0;
    private static long NANO_PER_SECOND = 1000000000;
    private static final long serialVersionUID = 9175191792439630013L;
    private long elapsedTime;
    private final int iterations;
    private final String name;
    private ThreadLocal<Long> startTime;
    private Status status;

    public enum Status {
        Started,
        Stopped,
        Paused
    }

    static {
        long j = 1000000000 * 60;
        NANO_PER_MINUTE = j;
        NANO_PER_HOUR = j * 60;
    }

    public Timer(String str) {
        this(str, 0);
    }

    public Timer(String str, int i) {
        this.startTime = new ThreadLocal<Long>() {
            /* access modifiers changed from: protected */
            public Long initialValue() {
                return 0L;
            }
        };
        this.name = str;
        this.status = Status.Stopped;
        this.iterations = i <= 0 ? 0 : i;
    }

    public synchronized void start() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.elapsedTime = 0;
        this.status = Status.Started;
    }

    public synchronized void startOrResume() {
        if (this.status == Status.Stopped) {
            start();
        } else {
            resume();
        }
    }

    public synchronized String stop() {
        this.elapsedTime += System.nanoTime() - this.startTime.get().longValue();
        this.startTime.set(0L);
        this.status = Status.Stopped;
        return toString();
    }

    public synchronized void pause() {
        this.elapsedTime += System.nanoTime() - this.startTime.get().longValue();
        this.startTime.set(0L);
        this.status = Status.Paused;
    }

    public synchronized void resume() {
        this.startTime.set(Long.valueOf(System.nanoTime()));
        this.status = Status.Started;
    }

    public String getName() {
        return this.name;
    }

    public long getElapsedTime() {
        return this.elapsedTime / 1000000;
    }

    public long getElapsedNanoTime() {
        return this.elapsedTime;
    }

    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        formatTo(sb);
        return sb.toString();
    }

    /* renamed from: org.apache.logging.log4j.util.Timer$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$logging$log4j$util$Timer$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.logging.log4j.util.Timer$Status[] r0 = org.apache.logging.log4j.util.Timer.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$logging$log4j$util$Timer$Status = r0
                org.apache.logging.log4j.util.Timer$Status r1 = org.apache.logging.log4j.util.Timer.Status.Started     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$logging$log4j$util$Timer$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.logging.log4j.util.Timer$Status r1 = org.apache.logging.log4j.util.Timer.Status.Paused     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$logging$log4j$util$Timer$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.logging.log4j.util.Timer$Status r1 = org.apache.logging.log4j.util.Timer.Status.Stopped     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.Timer.AnonymousClass2.<clinit>():void");
        }
    }

    public void formatTo(StringBuilder sb) {
        StringBuilder sb2 = sb;
        sb2.append("Timer ").append(this.name);
        int i = AnonymousClass2.$SwitchMap$org$apache$logging$log4j$util$Timer$Status[this.status.ordinal()];
        if (i == 1) {
            sb2.append(" started");
        } else if (i == 2) {
            sb2.append(" paused");
        } else if (i != 3) {
            sb2.append(Chars.SPACE).append(this.status);
        } else {
            long j = this.elapsedTime;
            long j2 = NANO_PER_HOUR;
            long j3 = j / j2;
            long j4 = j % j2;
            long j5 = NANO_PER_MINUTE;
            long j6 = j4 / j5;
            long j7 = j4 % j5;
            long j8 = NANO_PER_SECOND;
            long j9 = j7 / j8;
            long j10 = j7 % j8;
            int i2 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
            String str = "";
            String str2 = i2 > 0 ? str + j3 + " hours " : str;
            if (j6 > 0 || i2 > 0) {
                str2 = str2 + j6 + " minutes ";
            }
            sb2.append(" stopped. Elapsed time: ").append((str2 + new DecimalFormat("#0").format(j9) + '.') + new DecimalFormat("000000000").format(j10) + " seconds");
            int i3 = this.iterations;
            if (i3 > 0) {
                long j11 = this.elapsedTime;
                String str3 = "#0";
                long j12 = j11 / ((long) i3);
                long j13 = NANO_PER_HOUR;
                long j14 = j12 / j13;
                long j15 = j12 % j13;
                long j16 = NANO_PER_MINUTE;
                long j17 = j15 / j16;
                long j18 = j15 % j16;
                long j19 = NANO_PER_SECOND;
                String str4 = "000000000";
                long j20 = j18 / j19;
                long j21 = j18 % j19;
                int i4 = (j14 > 0 ? 1 : (j14 == 0 ? 0 : -1));
                if (i4 > 0) {
                    str = str + j14 + " hours ";
                }
                if (j17 > 0 || i4 > 0) {
                    str = str + j17 + " minutes ";
                }
                sb.append(" Average per iteration: ").append((str + new DecimalFormat(str3).format(j20) + '.') + new DecimalFormat(str4).format(j21) + " seconds");
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timer)) {
            return false;
        }
        Timer timer = (Timer) obj;
        if (this.elapsedTime != timer.elapsedTime || this.startTime != timer.startTime) {
            return false;
        }
        String str = this.name;
        if (str == null ? timer.name != null : !str.equals(timer.name)) {
            return false;
        }
        Status status2 = this.status;
        return status2 == null ? timer.status == null : status2.equals(timer.status);
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 29;
        Status status2 = this.status;
        if (status2 != null) {
            i = status2.hashCode();
        }
        int i2 = hashCode + i;
        long longValue = this.startTime.get().longValue();
        long j = this.elapsedTime;
        return (((i2 * 29) + ((int) (longValue ^ (longValue >>> 32)))) * 29) + ((int) (j ^ (j >>> 32)));
    }
}
