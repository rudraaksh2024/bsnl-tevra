package org.apache.logging.log4j;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public final class MarkerManager {
    /* access modifiers changed from: private */
    public static final ConcurrentMap<String, Marker> MARKERS = new ConcurrentHashMap();

    private MarkerManager() {
    }

    public static void clear() {
        MARKERS.clear();
    }

    public static boolean exists(String str) {
        return MARKERS.containsKey(str);
    }

    public static Marker getMarker(String str) {
        ConcurrentMap<String, Marker> concurrentMap = MARKERS;
        Marker marker = (Marker) concurrentMap.get(str);
        if (marker != null) {
            return marker;
        }
        concurrentMap.putIfAbsent(str, new Log4jMarker(str));
        return (Marker) concurrentMap.get(str);
    }

    @Deprecated
    public static Marker getMarker(String str, String str2) {
        Marker marker = (Marker) MARKERS.get(str2);
        if (marker != null) {
            return getMarker(str, marker);
        }
        throw new IllegalArgumentException("Parent Marker " + str2 + " has not been defined");
    }

    @Deprecated
    public static Marker getMarker(String str, Marker marker) {
        return getMarker(str).addParents(marker);
    }

    public static class Log4jMarker implements Marker, StringBuilderFormattable {
        private static final long serialVersionUID = 100;
        private final String name;
        private volatile Marker[] parents;

        private Log4jMarker() {
            this.name = null;
            this.parents = null;
        }

        public Log4jMarker(String str) {
            MarkerManager.requireNonNull(str, "Marker name cannot be null.");
            this.name = str;
            this.parents = null;
        }

        public synchronized Marker addParents(Marker... markerArr) {
            int i;
            MarkerManager.requireNonNull(markerArr, "A parent marker must be specified");
            Marker[] markerArr2 = this.parents;
            int length = markerArr.length;
            if (markerArr2 != null) {
                int i2 = 0;
                for (Marker marker : markerArr) {
                    if (!contains(marker, markerArr2) && !marker.isInstanceOf((Marker) this)) {
                        i2++;
                    }
                }
                if (i2 == 0) {
                    return this;
                }
                length = markerArr2.length + i2;
            }
            Marker[] markerArr3 = new Marker[length];
            if (markerArr2 != null) {
                System.arraycopy(markerArr2, 0, markerArr3, 0, markerArr2.length);
            }
            if (markerArr2 == null) {
                i = 0;
            } else {
                i = markerArr2.length;
            }
            for (Marker marker2 : markerArr) {
                if (markerArr2 == null || (!contains(marker2, markerArr2) && !marker2.isInstanceOf((Marker) this))) {
                    markerArr3[i] = marker2;
                    i++;
                }
            }
            this.parents = markerArr3;
            return this;
        }

        public synchronized boolean remove(Marker marker) {
            MarkerManager.requireNonNull(marker, "A parent marker must be specified");
            Marker[] markerArr = this.parents;
            if (markerArr == null) {
                return false;
            }
            if (r2 != 1) {
                int i = r2 - 1;
                Marker[] markerArr2 = new Marker[i];
                int i2 = 0;
                for (Marker marker2 : markerArr) {
                    if (!marker2.equals(marker)) {
                        if (i2 == i) {
                            return false;
                        }
                        int i3 = i2 + 1;
                        markerArr2[i2] = marker2;
                        i2 = i3;
                    }
                }
                this.parents = markerArr2;
                return true;
            } else if (!markerArr[0].equals(marker)) {
                return false;
            } else {
                this.parents = null;
                return true;
            }
        }

        public Marker setParents(Marker... markerArr) {
            if (markerArr == null || markerArr.length == 0) {
                this.parents = null;
            } else {
                Marker[] markerArr2 = new Marker[markerArr.length];
                System.arraycopy(markerArr, 0, markerArr2, 0, markerArr.length);
                this.parents = markerArr2;
            }
            return this;
        }

        public String getName() {
            return this.name;
        }

        public Marker[] getParents() {
            Marker[] markerArr = this.parents;
            if (markerArr == null) {
                return null;
            }
            return (Marker[]) Arrays.copyOf(markerArr, markerArr.length);
        }

        public boolean hasParents() {
            return this.parents != null;
        }

        public boolean isInstanceOf(Marker marker) {
            MarkerManager.requireNonNull(marker, "A marker parameter is required");
            if (this == marker) {
                return true;
            }
            Marker[] markerArr = this.parents;
            if (markerArr != null) {
                if (r2 == 1) {
                    return checkParent(markerArr[0], marker);
                }
                if (r2 != 2) {
                    for (Marker checkParent : markerArr) {
                        if (checkParent(checkParent, marker)) {
                            return true;
                        }
                    }
                } else if (checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        public boolean isInstanceOf(String str) {
            Marker[] markerArr;
            MarkerManager.requireNonNull(str, "A marker name is required");
            if (str.equals(getName())) {
                return true;
            }
            Marker marker = (Marker) MarkerManager.MARKERS.get(str);
            if (!(marker == null || (markerArr = this.parents) == null)) {
                if (r2 == 1) {
                    return checkParent(markerArr[0], marker);
                }
                if (r2 != 2) {
                    for (Marker checkParent : markerArr) {
                        if (checkParent(checkParent, marker)) {
                            return true;
                        }
                    }
                } else if (checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        private static boolean checkParent(Marker marker, Marker marker2) {
            Marker[] markerArr;
            if (marker == marker2) {
                return true;
            }
            if (marker instanceof Log4jMarker) {
                markerArr = ((Log4jMarker) marker).parents;
            } else {
                markerArr = marker.getParents();
            }
            if (markerArr != null) {
                if (r2 == 1) {
                    return checkParent(markerArr[0], marker2);
                }
                if (r2 != 2) {
                    for (Marker checkParent : markerArr) {
                        if (checkParent(checkParent, marker2)) {
                            return true;
                        }
                    }
                } else if (checkParent(markerArr[0], marker2) || checkParent(markerArr[1], marker2)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        private static boolean contains(Marker marker, Marker... markerArr) {
            for (Marker marker2 : markerArr) {
                if (marker2 == marker) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Marker)) {
                return false;
            }
            return this.name.equals(((Marker) obj).getName());
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            formatTo(sb);
            return sb.toString();
        }

        public void formatTo(StringBuilder sb) {
            sb.append(this.name);
            Marker[] markerArr = this.parents;
            if (markerArr != null) {
                addParentInfo(sb, markerArr);
            }
        }

        private static void addParentInfo(StringBuilder sb, Marker... markerArr) {
            sb.append("[ ");
            int length = markerArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                Log4jMarker log4jMarker = markerArr[i];
                if (!z) {
                    sb.append(", ");
                }
                sb.append(log4jMarker.getName());
                Marker[] parents2 = log4jMarker instanceof Log4jMarker ? log4jMarker.parents : log4jMarker.getParents();
                if (parents2 != null) {
                    addParentInfo(sb, parents2);
                }
                i++;
                z = false;
            }
            sb.append(" ]");
        }
    }

    /* access modifiers changed from: private */
    public static void requireNonNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
}
