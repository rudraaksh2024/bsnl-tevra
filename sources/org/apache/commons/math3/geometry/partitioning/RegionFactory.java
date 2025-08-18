package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

public class RegionFactory<S extends Space> {
    private final RegionFactory<S>.NodesCleaner nodeCleaner = new NodesCleaner(this, (AnonymousClass1) null);

    public Region<S> buildConvex(Hyperplane<S>... hyperplaneArr) {
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            return null;
        }
        Region<S> wholeSpace = hyperplaneArr[0].wholeSpace();
        BSPTree<S> tree = wholeSpace.getTree(false);
        tree.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> hyperplane : hyperplaneArr) {
            if (tree.insertCut(hyperplane)) {
                tree.setAttribute((Object) null);
                tree.getPlus().setAttribute(Boolean.FALSE);
                tree = tree.getMinus();
                tree.setAttribute(Boolean.TRUE);
            } else {
                SubHyperplane<S> wholeHyperplane = hyperplane.wholeHyperplane();
                for (BSPTree<S> bSPTree = tree; bSPTree.getParent() != null && wholeHyperplane != null; bSPTree = bSPTree.getParent()) {
                    Hyperplane<S> hyperplane2 = bSPTree.getParent().getCut().getHyperplane();
                    SubHyperplane.SplitSubHyperplane<S> split = wholeHyperplane.split(hyperplane2);
                    int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$geometry$partitioning$Side[split.getSide().ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            wholeHyperplane = split.getMinus();
                        } else {
                            throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX_HYPERPLANES, new Object[0]);
                        }
                    } else if (!hyperplane.sameOrientationAs(hyperplane2)) {
                        return getComplement(hyperplaneArr[0].wholeSpace());
                    }
                }
            }
        }
        return wholeSpace;
    }

    /* renamed from: org.apache.commons.math3.geometry.partitioning.RegionFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.geometry.partitioning.Side[] r0 = org.apache.commons.math3.geometry.partitioning.Side.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side = r0
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.HYPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$geometry$partitioning$Side     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.geometry.partitioning.Side r1 = org.apache.commons.math3.geometry.partitioning.Side.PLUS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.geometry.partitioning.RegionFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public Region<S> union(Region<S> region, Region<S> region2) {
        BSPTree<S> merge = region.getTree(false).merge(region2.getTree(false), new UnionMerger(this, (AnonymousClass1) null));
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> intersection(Region<S> region, Region<S> region2) {
        BSPTree<S> merge = region.getTree(false).merge(region2.getTree(false), new IntersectionMerger(this, (AnonymousClass1) null));
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> xor(Region<S> region, Region<S> region2) {
        BSPTree<S> merge = region.getTree(false).merge(region2.getTree(false), new XorMerger(this, (AnonymousClass1) null));
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> difference(Region<S> region, Region<S> region2) {
        BSPTree<S> merge = region.getTree(false).merge(region2.getTree(false), new DifferenceMerger(region, region2));
        merge.visit(this.nodeCleaner);
        return region.buildNew(merge);
    }

    public Region<S> getComplement(Region<S> region) {
        return region.buildNew(recurseComplement(region.getTree(false)));
    }

    /* access modifiers changed from: private */
    public BSPTree<S> recurseComplement(BSPTree<S> bSPTree) {
        BoundaryAttribute boundaryAttribute;
        HashMap hashMap = new HashMap();
        BSPTree<S> recurseComplement = recurseComplement(bSPTree, hashMap);
        for (Map.Entry entry : hashMap.entrySet()) {
            if (!(((BSPTree) entry.getKey()).getCut() == null || (boundaryAttribute = (BoundaryAttribute) ((BSPTree) entry.getKey()).getAttribute()) == null)) {
                BoundaryAttribute boundaryAttribute2 = (BoundaryAttribute) ((BSPTree) entry.getValue()).getAttribute();
                Iterator it = boundaryAttribute.getSplitters().iterator();
                while (it.hasNext()) {
                    boundaryAttribute2.getSplitters().add((BSPTree) hashMap.get((BSPTree) it.next()));
                }
            }
        }
        return recurseComplement;
    }

    private BSPTree<S> recurseComplement(BSPTree<S> bSPTree, Map<BSPTree<S>, BSPTree<S>> map) {
        BSPTree<S> bSPTree2;
        if (bSPTree.getCut() == null) {
            bSPTree2 = new BSPTree<>(((Boolean) bSPTree.getAttribute()).booleanValue() ? Boolean.FALSE : Boolean.TRUE);
        } else {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute != null) {
                SubHyperplane subHyperplane = null;
                SubHyperplane copySelf = boundaryAttribute.getPlusInside() == null ? null : boundaryAttribute.getPlusInside().copySelf();
                if (boundaryAttribute.getPlusOutside() != null) {
                    subHyperplane = boundaryAttribute.getPlusOutside().copySelf();
                }
                boundaryAttribute = new BoundaryAttribute(copySelf, subHyperplane, new NodesSet());
            }
            bSPTree2 = new BSPTree<>(bSPTree.getCut().copySelf(), recurseComplement(bSPTree.getPlus(), map), recurseComplement(bSPTree.getMinus(), map), boundaryAttribute);
        }
        map.put(bSPTree, bSPTree2);
        return bSPTree2;
    }

    private class UnionMerger implements BSPTree.LeafMerger<S> {
        private UnionMerger() {
        }

        /* synthetic */ UnionMerger(RegionFactory regionFactory, AnonymousClass1 r2) {
            this();
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
                return bSPTree;
            }
            bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(false));
            return bSPTree2;
        }
    }

    private class IntersectionMerger implements BSPTree.LeafMerger<S> {
        private IntersectionMerger() {
        }

        /* synthetic */ IntersectionMerger(RegionFactory regionFactory, AnonymousClass1 r2) {
            this();
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
                return bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z, new VanishingToLeaf(false));
            return bSPTree;
        }
    }

    private class XorMerger implements BSPTree.LeafMerger<S> {
        private XorMerger() {
        }

        /* synthetic */ XorMerger(RegionFactory regionFactory, AnonymousClass1 r2) {
            this();
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                bSPTree2 = RegionFactory.this.recurseComplement(bSPTree2);
            }
            bSPTree2.insertInTree(bSPTree3, z, new VanishingToLeaf(true));
            return bSPTree2;
        }
    }

    private class DifferenceMerger implements BSPTree.LeafMerger<S>, BSPTree.VanishingCutHandler<S> {
        private final Region<S> region1;
        private final Region<S> region2;

        DifferenceMerger(Region<S> region, Region<S> region3) {
            this.region1 = region.copySelf();
            this.region2 = region3.copySelf();
        }

        public BSPTree<S> merge(BSPTree<S> bSPTree, BSPTree<S> bSPTree2, BSPTree<S> bSPTree3, boolean z, boolean z2) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                RegionFactory regionFactory = RegionFactory.this;
                if (z2) {
                    bSPTree = bSPTree2;
                }
                BSPTree<S> access$400 = regionFactory.recurseComplement(bSPTree);
                access$400.insertInTree(bSPTree3, z, this);
                return access$400;
            }
            if (!z2) {
                bSPTree = bSPTree2;
            }
            bSPTree.insertInTree(bSPTree3, z, this);
            return bSPTree;
        }

        public BSPTree<S> fixNode(BSPTree<S> bSPTree) {
            Point<S> barycenter = this.region1.buildNew(bSPTree.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, (Object) null)).getBarycenter();
            return new BSPTree<>(Boolean.valueOf(this.region1.checkPoint(barycenter) == Region.Location.INSIDE && this.region2.checkPoint(barycenter) == Region.Location.OUTSIDE));
        }
    }

    private class NodesCleaner implements BSPTreeVisitor<S> {
        public void visitLeafNode(BSPTree<S> bSPTree) {
        }

        private NodesCleaner() {
        }

        /* synthetic */ NodesCleaner(RegionFactory regionFactory, AnonymousClass1 r2) {
            this();
        }

        public BSPTreeVisitor.Order visitOrder(BSPTree<S> bSPTree) {
            return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
        }

        public void visitInternalNode(BSPTree<S> bSPTree) {
            bSPTree.setAttribute((Object) null);
        }
    }

    private class VanishingToLeaf implements BSPTree.VanishingCutHandler<S> {
        private final boolean inside;

        VanishingToLeaf(boolean z) {
            this.inside = z;
        }

        public BSPTree<S> fixNode(BSPTree<S> bSPTree) {
            if (bSPTree.getPlus().getAttribute().equals(bSPTree.getMinus().getAttribute())) {
                return new BSPTree<>(bSPTree.getPlus().getAttribute());
            }
            return new BSPTree<>(Boolean.valueOf(this.inside));
        }
    }
}
