package org.apache.logging.log4j.message;

import java.lang.Thread;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilders;

class BasicThreadInformation implements ThreadInformation {
    private static final int HASH_MULTIPLIER = 31;
    private static final int HASH_SHIFT = 32;
    private final long id;
    private final boolean isAlive;
    private final boolean isDaemon;
    private final String longName;
    private final String name;
    private final int priority;
    private final Thread.State state;
    private final String threadGroupName;

    BasicThreadInformation(Thread thread) {
        String str;
        this.id = thread.getId();
        this.name = thread.getName();
        this.longName = thread.toString();
        this.state = thread.getState();
        this.priority = thread.getPriority();
        this.isAlive = thread.isAlive();
        this.isDaemon = thread.isDaemon();
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null) {
            str = null;
        } else {
            str = threadGroup.getName();
        }
        this.threadGroupName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicThreadInformation basicThreadInformation = (BasicThreadInformation) obj;
        if (this.id != basicThreadInformation.id) {
            return false;
        }
        String str = this.name;
        return str == null ? basicThreadInformation.name == null : str.equals(basicThreadInformation.name);
    }

    public int hashCode() {
        long j = this.id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.name;
        return i + (str != null ? str.hashCode() : 0);
    }

    public void printThreadInfo(StringBuilder sb) {
        StringBuilders.appendDqValue(sb, this.name).append(Chars.SPACE);
        if (this.isDaemon) {
            sb.append("daemon ");
        }
        sb.append("prio=").append(this.priority).append(" tid=").append(this.id).append(Chars.SPACE);
        String str = this.threadGroupName;
        if (str != null) {
            StringBuilders.appendKeyDqValue(sb, "group", str);
        }
        sb.append(10);
        sb.append("\tThread state: ").append(this.state.name()).append(10);
    }

    public void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr) {
        for (StackTraceElement append : stackTraceElementArr) {
            sb.append("\tat ").append(append).append(10);
        }
    }
}
