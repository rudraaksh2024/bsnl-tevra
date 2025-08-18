package org.apache.logging.log4j.message;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringBuilderFormattable;

@AsynchronouslyFormattable
public class ThreadDumpMessage implements Message, StringBuilderFormattable {
    private static ThreadInfoFactory FACTORY = null;
    private static final long serialVersionUID = -1103400781608841088L;
    private String formattedMessage;
    private volatile Map<ThreadInformation, StackTraceElement[]> threads;
    /* access modifiers changed from: private */
    public final String title;

    public interface ThreadInfoFactory {
        Map<ThreadInformation, StackTraceElement[]> createThreadInfo();
    }

    public Object[] getParameters() {
        return null;
    }

    public Throwable getThrowable() {
        return null;
    }

    public ThreadDumpMessage(String str) {
        this.title = str == null ? "" : str;
        this.threads = getFactory().createThreadInfo();
    }

    private ThreadDumpMessage(String str, String str2) {
        this.formattedMessage = str;
        this.title = str2 == null ? "" : str2;
    }

    private static ThreadInfoFactory getFactory() {
        if (FACTORY == null) {
            FACTORY = initFactory(ThreadDumpMessage.class.getClassLoader());
        }
        return FACTORY;
    }

    private static ThreadInfoFactory initFactory(ClassLoader classLoader) {
        ThreadInfoFactory threadInfoFactory;
        try {
            Iterator<S> it = ServiceLoader.load(ThreadInfoFactory.class, classLoader).iterator();
            threadInfoFactory = null;
            while (threadInfoFactory == null && it.hasNext()) {
                threadInfoFactory = (ThreadInfoFactory) it.next();
            }
        } catch (Exception | LinkageError | ServiceConfigurationError e) {
            StatusLogger.getLogger().info("ThreadDumpMessage uses BasicThreadInfoFactory: could not load extended ThreadInfoFactory: {}", (Object) e.toString());
            threadInfoFactory = null;
        }
        return threadInfoFactory == null ? new BasicThreadInfoFactory() : threadInfoFactory;
    }

    public String toString() {
        return getFormattedMessage();
    }

    public String getFormattedMessage() {
        String str = this.formattedMessage;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(255);
        formatTo(sb);
        return sb.toString();
    }

    public void formatTo(StringBuilder sb) {
        sb.append(this.title);
        if (this.title.length() > 0) {
            sb.append(10);
        }
        for (Map.Entry next : this.threads.entrySet()) {
            ThreadInformation threadInformation = (ThreadInformation) next.getKey();
            threadInformation.printThreadInfo(sb);
            threadInformation.printStack(sb, (StackTraceElement[]) next.getValue());
            sb.append(10);
        }
    }

    public String getFormat() {
        String str = this.title;
        return str == null ? "" : str;
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() {
        return new ThreadDumpMessageProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    private static class ThreadDumpMessageProxy implements Serializable {
        private static final long serialVersionUID = -3476620450287648269L;
        private final String formattedMsg;
        private final String title;

        ThreadDumpMessageProxy(ThreadDumpMessage threadDumpMessage) {
            this.formattedMsg = threadDumpMessage.getFormattedMessage();
            this.title = threadDumpMessage.title;
        }

        /* access modifiers changed from: protected */
        public Object readResolve() {
            return new ThreadDumpMessage(this.formattedMsg, this.title);
        }
    }

    private static class BasicThreadInfoFactory implements ThreadInfoFactory {
        private BasicThreadInfoFactory() {
        }

        public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            HashMap hashMap = new HashMap(allStackTraces.size());
            for (Map.Entry next : allStackTraces.entrySet()) {
                hashMap.put(new BasicThreadInformation((Thread) next.getKey()), next.getValue());
            }
            return hashMap;
        }
    }
}
