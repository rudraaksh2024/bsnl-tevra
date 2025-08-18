package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;

class BoundaryBuilder<S extends Space> implements BSPTreeVisitor<S> {
    public void visitLeafNode(BSPTree<S> bSPTree) {
    }

    BoundaryBuilder() {
    }

    public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
        return BSPTreeVisitor.Order.PLUS_MINUS_SUB;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree<S> r7) {
        /*
            r6 = this;
            org.apache.commons.math3.geometry.partitioning.Characterization r6 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r0 = r7.getPlus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r1 = r7.getCut()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r1 = r1.copySelf()
            r6.<init>(r0, r1)
            boolean r0 = r6.touchOutside()
            r1 = 0
            if (r0 == 0) goto L_0x0043
            org.apache.commons.math3.geometry.partitioning.Characterization r0 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r2 = r7.getMinus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r3 = r6.outsideTouching()
            r0.<init>(r2, r3)
            boolean r2 = r0.touchInside()
            if (r2 == 0) goto L_0x0043
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r2 = r0.insideTouching()
            org.apache.commons.math3.geometry.partitioning.NodesSet r3 = new org.apache.commons.math3.geometry.partitioning.NodesSet
            r3.<init>()
            org.apache.commons.math3.geometry.partitioning.NodesSet r0 = r0.getInsideSplitters()
            r3.addAll(r0)
            org.apache.commons.math3.geometry.partitioning.NodesSet r0 = r6.getOutsideSplitters()
            r3.addAll(r0)
            goto L_0x0045
        L_0x0043:
            r2 = r1
            r3 = r2
        L_0x0045:
            boolean r0 = r6.touchInside()
            if (r0 == 0) goto L_0x0077
            org.apache.commons.math3.geometry.partitioning.Characterization r0 = new org.apache.commons.math3.geometry.partitioning.Characterization
            org.apache.commons.math3.geometry.partitioning.BSPTree r4 = r7.getMinus()
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r5 = r6.insideTouching()
            r0.<init>(r4, r5)
            boolean r4 = r0.touchOutside()
            if (r4 == 0) goto L_0x0077
            org.apache.commons.math3.geometry.partitioning.SubHyperplane r1 = r0.outsideTouching()
            if (r3 != 0) goto L_0x0069
            org.apache.commons.math3.geometry.partitioning.NodesSet r3 = new org.apache.commons.math3.geometry.partitioning.NodesSet
            r3.<init>()
        L_0x0069:
            org.apache.commons.math3.geometry.partitioning.NodesSet r0 = r0.getOutsideSplitters()
            r3.addAll(r0)
            org.apache.commons.math3.geometry.partitioning.NodesSet r6 = r6.getInsideSplitters()
            r3.addAll(r6)
        L_0x0077:
            if (r3 == 0) goto L_0x0087
            org.apache.commons.math3.geometry.partitioning.BSPTree r6 = r7.getParent()
        L_0x007d:
            if (r6 == 0) goto L_0x0087
            r3.add(r6)
            org.apache.commons.math3.geometry.partitioning.BSPTree r6 = r6.getParent()
            goto L_0x007d
        L_0x0087:
            org.apache.commons.math3.geometry.partitioning.BoundaryAttribute r6 = new org.apache.commons.math3.geometry.partitioning.BoundaryAttribute
            r6.<init>(r2, r1, r3)
            r7.setAttribute(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.BoundaryBuilder.visitInternalNode(org.apache.commons.math3.geometry.partitioning.BSPTree):void");
    }
}
