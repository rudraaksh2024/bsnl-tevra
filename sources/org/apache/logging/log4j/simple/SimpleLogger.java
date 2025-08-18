package org.apache.logging.log4j.simple;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.util.PropertiesUtil;

public class SimpleLogger extends AbstractLogger {
    private static final char SPACE = ' ';
    private static final long serialVersionUID = 1;
    private final DateFormat dateFormatter;
    private Level level;
    private final String logName;
    private final boolean showContextMap;
    private final boolean showDateTime;
    private PrintStream stream;

    public SimpleLogger(String str, Level level2, boolean z, boolean z2, boolean z3, boolean z4, String str2, MessageFactory messageFactory, PropertiesUtil propertiesUtil, PrintStream printStream) {
        super(str, messageFactory);
        SimpleDateFormat simpleDateFormat;
        this.level = Level.toLevel(propertiesUtil.getStringProperty("org.apache.logging.log4j.simplelog." + str + ".level"), level2);
        if (z2) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf <= 0 || lastIndexOf >= str.length()) {
                this.logName = str;
            } else {
                this.logName = str.substring(lastIndexOf + 1);
            }
        } else if (z) {
            this.logName = str;
        } else {
            this.logName = null;
        }
        this.showDateTime = z3;
        this.showContextMap = z4;
        this.stream = printStream;
        if (z3) {
            try {
                simpleDateFormat = new SimpleDateFormat(str2);
            } catch (IllegalArgumentException unused) {
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS zzz");
            }
            this.dateFormatter = simpleDateFormat;
            return;
        }
        this.dateFormatter = null;
    }

    public Level getLevel() {
        return this.level;
    }

    public boolean isEnabled(Level level2, Marker marker, Message message, Throwable th) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, CharSequence charSequence, Throwable th) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, Object obj, Throwable th) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object... objArr) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return this.level.intLevel() >= level2.intLevel();
    }

    public boolean isEnabled(Level level2, Marker marker, String str, Throwable th) {
        return this.level.intLevel() >= level2.intLevel();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logMessage(java.lang.String r4, org.apache.logging.log4j.Level r5, org.apache.logging.log4j.Marker r6, org.apache.logging.log4j.message.Message r7, java.lang.Throwable r8) {
        /*
            r3 = this;
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            boolean r6 = r3.showDateTime
            r0 = 32
            if (r6 == 0) goto L_0x0024
            java.util.Date r6 = new java.util.Date
            r6.<init>()
            java.text.DateFormat r1 = r3.dateFormatter
            monitor-enter(r1)
            java.text.DateFormat r2 = r3.dateFormatter     // Catch:{ all -> 0x0021 }
            java.lang.String r6 = r2.format(r6)     // Catch:{ all -> 0x0021 }
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            r4.append(r6)
            r4.append(r0)
            goto L_0x0024
        L_0x0021:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0021 }
            throw r3
        L_0x0024:
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            r4.append(r0)
            java.lang.String r5 = r3.logName
            boolean r5 = org.apache.logging.log4j.util.Strings.isNotEmpty(r5)
            if (r5 == 0) goto L_0x003e
            java.lang.String r5 = r3.logName
            r4.append(r5)
            r4.append(r0)
        L_0x003e:
            java.lang.String r5 = r7.getFormattedMessage()
            r4.append(r5)
            boolean r5 = r3.showContextMap
            if (r5 == 0) goto L_0x0060
            java.util.Map r5 = org.apache.logging.log4j.ThreadContext.getImmutableContext()
            int r6 = r5.size()
            if (r6 <= 0) goto L_0x0060
            r4.append(r0)
            java.lang.String r5 = r5.toString()
            r4.append(r5)
            r4.append(r0)
        L_0x0060:
            java.lang.Object[] r5 = r7.getParameters()
            if (r8 != 0) goto L_0x007c
            if (r5 == 0) goto L_0x007c
            int r6 = r5.length
            if (r6 <= 0) goto L_0x007c
            int r6 = r5.length
            int r6 = r6 + -1
            r6 = r5[r6]
            boolean r6 = r6 instanceof java.lang.Throwable
            if (r6 == 0) goto L_0x007c
            int r6 = r5.length
            int r6 = r6 + -1
            r5 = r5[r6]
            r8 = r5
            java.lang.Throwable r8 = (java.lang.Throwable) r8
        L_0x007c:
            java.io.PrintStream r5 = r3.stream
            java.lang.String r4 = r4.toString()
            r5.println(r4)
            if (r8 == 0) goto L_0x0091
            java.io.PrintStream r4 = r3.stream
            r4.print(r0)
            java.io.PrintStream r3 = r3.stream
            r8.printStackTrace(r3)
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.simple.SimpleLogger.logMessage(java.lang.String, org.apache.logging.log4j.Level, org.apache.logging.log4j.Marker, org.apache.logging.log4j.message.Message, java.lang.Throwable):void");
    }

    public void setLevel(Level level2) {
        if (level2 != null) {
            this.level = level2;
        }
    }

    public void setStream(PrintStream printStream) {
        this.stream = printStream;
    }
}
