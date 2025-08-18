package org.apache.logging.log4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CloseableThreadContext {
    private CloseableThreadContext() {
    }

    public static Instance push(String str) {
        return new Instance().push(str);
    }

    public static Instance push(String str, Object... objArr) {
        return new Instance().push(str, objArr);
    }

    public static Instance put(String str, String str2) {
        return new Instance().put(str, str2);
    }

    public static Instance pushAll(List<String> list) {
        return new Instance().pushAll(list);
    }

    public static Instance putAll(Map<String, String> map) {
        return new Instance().putAll(map);
    }

    public static class Instance implements AutoCloseable {
        private final Map<String, String> originalValues;
        private int pushCount;

        private Instance() {
            this.pushCount = 0;
            this.originalValues = new HashMap();
        }

        public Instance push(String str) {
            ThreadContext.push(str);
            this.pushCount++;
            return this;
        }

        public Instance push(String str, Object[] objArr) {
            ThreadContext.push(str, objArr);
            this.pushCount++;
            return this;
        }

        public Instance put(String str, String str2) {
            if (!this.originalValues.containsKey(str)) {
                this.originalValues.put(str, ThreadContext.get(str));
            }
            ThreadContext.put(str, str2);
            return this;
        }

        public Instance putAll(Map<String, String> map) {
            Map<String, String> context = ThreadContext.getContext();
            ThreadContext.putAll(map);
            for (String next : map.keySet()) {
                if (!this.originalValues.containsKey(next)) {
                    this.originalValues.put(next, context.get(next));
                }
            }
            return this;
        }

        public Instance pushAll(List<String> list) {
            for (String push : list) {
                push(push);
            }
            return this;
        }

        public void close() {
            closeStack();
            closeMap();
        }

        private void closeMap() {
            Iterator<Map.Entry<String, String>> it = this.originalValues.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                String str = (String) next.getKey();
                String str2 = (String) next.getValue();
                if (str2 == null) {
                    ThreadContext.remove(str);
                } else {
                    ThreadContext.put(str, str2);
                }
                it.remove();
            }
        }

        private void closeStack() {
            for (int i = 0; i < this.pushCount; i++) {
                ThreadContext.pop();
            }
            this.pushCount = 0;
        }
    }
}
