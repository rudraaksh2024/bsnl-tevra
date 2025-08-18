package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

public interface SubHyperplane<S extends Space> {
    SubHyperplane<S> copySelf();

    Hyperplane<S> getHyperplane();

    double getSize();

    boolean isEmpty();

    SubHyperplane<S> reunite(SubHyperplane<S> subHyperplane);

    @Deprecated
    Side side(Hyperplane<S> hyperplane);

    SplitSubHyperplane<S> split(Hyperplane<S> hyperplane);

    public static class SplitSubHyperplane<U extends Space> {
        private final SubHyperplane<U> minus;
        private final SubHyperplane<U> plus;

        public SplitSubHyperplane(SubHyperplane<U> subHyperplane, SubHyperplane<U> subHyperplane2) {
            this.plus = subHyperplane;
            this.minus = subHyperplane2;
        }

        public SubHyperplane<U> getPlus() {
            return this.plus;
        }

        public SubHyperplane<U> getMinus() {
            return this.minus;
        }

        public Side getSide() {
            SubHyperplane<U> subHyperplane = this.plus;
            if (subHyperplane == null || subHyperplane.isEmpty()) {
                SubHyperplane<U> subHyperplane2 = this.minus;
                if (subHyperplane2 == null || subHyperplane2.isEmpty()) {
                    return Side.HYPER;
                }
                return Side.MINUS;
            }
            SubHyperplane<U> subHyperplane3 = this.minus;
            if (subHyperplane3 == null || subHyperplane3.isEmpty()) {
                return Side.PLUS;
            }
            return Side.BOTH;
        }
    }
}
