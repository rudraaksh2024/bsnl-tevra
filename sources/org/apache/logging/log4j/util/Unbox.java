package org.apache.logging.log4j.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

public class Unbox {
    private static final int BITS_PER_INT = 32;
    private static final Logger LOGGER = StatusLogger.getLogger();
    /* access modifiers changed from: private */
    public static final int MASK;
    private static final int RINGBUFFER_MIN_SIZE = 32;
    /* access modifiers changed from: private */
    public static final int RINGBUFFER_SIZE;
    private static ThreadLocal<State> threadLocalState = new ThreadLocal<>();
    private static WebSafeState webSafeState = new WebSafeState();

    static {
        int calculateRingBufferSize = calculateRingBufferSize("log4j.unbox.ringbuffer.size");
        RINGBUFFER_SIZE = calculateRingBufferSize;
        MASK = calculateRingBufferSize - 1;
    }

    private static class WebSafeState {
        private final ThreadLocal<int[]> current;
        private final ThreadLocal<StringBuilder[]> ringBuffer;

        private WebSafeState() {
            this.ringBuffer = new ThreadLocal<>();
            this.current = new ThreadLocal<>();
        }

        public StringBuilder getStringBuilder() {
            StringBuilder[] sbArr = this.ringBuffer.get();
            if (sbArr == null) {
                int access$000 = Unbox.RINGBUFFER_SIZE;
                StringBuilder[] sbArr2 = new StringBuilder[access$000];
                for (int i = 0; i < access$000; i++) {
                    sbArr2[i] = new StringBuilder(21);
                }
                this.ringBuffer.set(sbArr2);
                this.current.set(new int[1]);
                sbArr = sbArr2;
            }
            int[] iArr = this.current.get();
            int access$100 = Unbox.MASK;
            int i2 = iArr[0];
            iArr[0] = i2 + 1;
            StringBuilder sb = sbArr[access$100 & i2];
            sb.setLength(0);
            return sb;
        }

        public boolean isBoxedPrimitive(StringBuilder sb) {
            StringBuilder[] sbArr = this.ringBuffer.get();
            if (sbArr == null) {
                return false;
            }
            for (StringBuilder sb2 : sbArr) {
                if (sb == sb2) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class State {
        private int current;
        private final StringBuilder[] ringBuffer = new StringBuilder[Unbox.RINGBUFFER_SIZE];

        State() {
            int i = 0;
            while (true) {
                StringBuilder[] sbArr = this.ringBuffer;
                if (i < sbArr.length) {
                    sbArr[i] = new StringBuilder(21);
                    i++;
                } else {
                    return;
                }
            }
        }

        public StringBuilder getStringBuilder() {
            StringBuilder[] sbArr = this.ringBuffer;
            int access$100 = Unbox.MASK;
            int i = this.current;
            this.current = i + 1;
            StringBuilder sb = sbArr[access$100 & i];
            sb.setLength(0);
            return sb;
        }

        public boolean isBoxedPrimitive(StringBuilder sb) {
            int i = 0;
            while (true) {
                StringBuilder[] sbArr = this.ringBuffer;
                if (i >= sbArr.length) {
                    return false;
                }
                if (sb == sbArr[i]) {
                    return true;
                }
                i++;
            }
        }
    }

    private Unbox() {
    }

    private static int calculateRingBufferSize(String str) {
        String stringProperty = PropertiesUtil.getProperties().getStringProperty(str, String.valueOf(32));
        try {
            int parseInt = Integer.parseInt(stringProperty.trim());
            if (parseInt < 32) {
                LOGGER.warn("Invalid {} {}, using minimum size {}.", (Object) str, (Object) stringProperty, (Object) 32);
                parseInt = 32;
            }
            return ceilingNextPowerOfTwo(parseInt);
        } catch (Exception unused) {
            LOGGER.warn("Invalid {} {}, using default size {}.", (Object) str, (Object) stringProperty, (Object) 32);
            return 32;
        }
    }

    private static int ceilingNextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    public static StringBuilder box(float f) {
        return getSB().append(f);
    }

    public static StringBuilder box(double d) {
        return getSB().append(d);
    }

    public static StringBuilder box(short s) {
        return getSB().append(s);
    }

    public static StringBuilder box(int i) {
        return getSB().append(i);
    }

    public static StringBuilder box(char c) {
        return getSB().append(c);
    }

    public static StringBuilder box(long j) {
        return getSB().append(j);
    }

    public static StringBuilder box(byte b) {
        return getSB().append(b);
    }

    public static StringBuilder box(boolean z) {
        return getSB().append(z);
    }

    private static State getState() {
        State state = threadLocalState.get();
        if (state != null) {
            return state;
        }
        State state2 = new State();
        threadLocalState.set(state2);
        return state2;
    }

    private static StringBuilder getSB() {
        return Constants.ENABLE_THREADLOCALS ? getState().getStringBuilder() : webSafeState.getStringBuilder();
    }

    static int getRingbufferSize() {
        return RINGBUFFER_SIZE;
    }
}
