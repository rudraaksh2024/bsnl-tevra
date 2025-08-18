package org.apache.poi.ooxml.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.logging.log4j.message.ParameterizedMessage;

public class IdentifierManager {
    public static final long MAX_ID = 9223372036854775806L;
    public static final long MIN_ID = 0;
    private final long lowerbound;
    private LinkedList<Segment> segments;
    private final long upperbound;

    public IdentifierManager(long j, long j2) {
        if (j > j2) {
            throw new IllegalArgumentException("lowerbound must not be greater than upperbound, had " + j + " and " + j2);
        } else if (j < 0) {
            throw new IllegalArgumentException("lowerbound must be greater than or equal to " + Long.toString(0));
        } else if (j2 <= MAX_ID) {
            this.lowerbound = j;
            this.upperbound = j2;
            LinkedList<Segment> linkedList = new LinkedList<>();
            this.segments = linkedList;
            linkedList.add(new Segment(j, j2));
        } else {
            throw new IllegalArgumentException("upperbound must be less than or equal to " + Long.toString(MAX_ID) + " but had " + j2);
        }
    }

    public long reserve(long j) {
        if (j < this.lowerbound || j > this.upperbound) {
            throw new IllegalArgumentException("Value for parameter 'id' was out of bounds, had " + j + ", but should be within [" + this.lowerbound + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.upperbound + "]");
        }
        verifyIdentifiersLeft();
        if (j == this.upperbound) {
            Segment last = this.segments.getLast();
            long access$000 = last.end;
            long j2 = this.upperbound;
            if (access$000 != j2) {
                return reserveNew();
            }
            long unused = last.end = j2 - 1;
            if (last.start > last.end) {
                this.segments.removeLast();
            }
            return j;
        } else if (j == this.lowerbound) {
            Segment first = this.segments.getFirst();
            long access$100 = first.start;
            long j3 = this.lowerbound;
            if (access$100 != j3) {
                return reserveNew();
            }
            long unused2 = first.start = j3 + 1;
            if (first.end < first.start) {
                this.segments.removeFirst();
            }
            return j;
        } else {
            ListIterator listIterator = this.segments.listIterator();
            while (true) {
                if (!listIterator.hasNext()) {
                    break;
                }
                Segment segment = (Segment) listIterator.next();
                if (segment.end >= j) {
                    if (segment.start <= j) {
                        if (segment.start == j) {
                            long unused3 = segment.start = 1 + j;
                            if (segment.end < segment.start) {
                                listIterator.remove();
                            }
                            return j;
                        } else if (segment.end == j) {
                            long unused4 = segment.end = j - 1;
                            if (segment.start > segment.end) {
                                listIterator.remove();
                            }
                            return j;
                        } else {
                            listIterator.add(new Segment(j + 1, segment.end));
                            long unused5 = segment.end = j - 1;
                            return j;
                        }
                    }
                }
            }
            return reserveNew();
        }
    }

    public long reserveNew() {
        verifyIdentifiersLeft();
        Segment first = this.segments.getFirst();
        long access$100 = first.start;
        long unused = first.start = first.start + 1;
        if (first.start > first.end) {
            this.segments.removeFirst();
        }
        return access$100;
    }

    public boolean release(long j) {
        long j2 = this.lowerbound;
        if (j >= j2) {
            long j3 = this.upperbound;
            if (j <= j3) {
                if (j == j3) {
                    Segment last = this.segments.getLast();
                    long access$000 = last.end;
                    long j4 = this.upperbound;
                    if (access$000 == j4 - 1) {
                        long unused = last.end = j4;
                        return true;
                    } else if (last.end == this.upperbound) {
                        return false;
                    } else {
                        LinkedList<Segment> linkedList = this.segments;
                        long j5 = this.upperbound;
                        linkedList.add(new Segment(j5, j5));
                        return true;
                    }
                } else if (j == j2) {
                    Segment first = this.segments.getFirst();
                    long access$100 = first.start;
                    long j6 = this.lowerbound;
                    if (access$100 == 1 + j6) {
                        long unused2 = first.start = j6;
                        return true;
                    } else if (first.start == this.lowerbound) {
                        return false;
                    } else {
                        LinkedList<Segment> linkedList2 = this.segments;
                        long j7 = this.lowerbound;
                        linkedList2.addFirst(new Segment(j7, j7));
                        return true;
                    }
                } else {
                    long j8 = j + 1;
                    long j9 = j - 1;
                    ListIterator listIterator = this.segments.listIterator();
                    while (true) {
                        if (!listIterator.hasNext()) {
                            break;
                        }
                        Segment segment = (Segment) listIterator.next();
                        if (segment.end >= j9) {
                            if (segment.start > j8) {
                                listIterator.previous();
                                listIterator.add(new Segment(j, j));
                                return true;
                            } else if (segment.start == j8) {
                                long unused3 = segment.start = j;
                                return true;
                            } else if (segment.end == j9) {
                                long unused4 = segment.end = j;
                                if (listIterator.hasNext()) {
                                    Segment segment2 = (Segment) listIterator.next();
                                    if (segment2.start == segment.end + 1) {
                                        long unused5 = segment.end = segment2.end;
                                        listIterator.remove();
                                    }
                                }
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        throw new IllegalArgumentException("Value for parameter 'id' was out of bounds, had " + j + ", but should be within [" + this.lowerbound + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.upperbound + "]");
    }

    public long getRemainingIdentifiers() {
        Iterator it = this.segments.iterator();
        long j = 0;
        while (it.hasNext()) {
            Segment segment = (Segment) it.next();
            j = (j - segment.start) + segment.end + 1;
        }
        return j;
    }

    private void verifyIdentifiersLeft() {
        if (this.segments.isEmpty()) {
            throw new IllegalStateException("No identifiers left");
        }
    }

    private static class Segment {
        /* access modifiers changed from: private */
        public long end;
        /* access modifiers changed from: private */
        public long start;

        public Segment(long j, long j2) {
            this.start = j;
            this.end = j2;
        }

        public String toString() {
            return "[" + this.start + VectorFormat.DEFAULT_SEPARATOR + this.end + "]";
        }
    }
}
