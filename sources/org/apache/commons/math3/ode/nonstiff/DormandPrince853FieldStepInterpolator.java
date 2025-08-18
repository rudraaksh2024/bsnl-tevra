package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

class DormandPrince853FieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {
    private final T[][] d;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.commons.math3.RealFieldElement[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v31, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v32, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v36, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v39, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v40, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v43, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v44, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v47, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v51, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v52, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v55, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v56, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v59, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v60, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v63, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v64, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v67, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v68, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v71, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v72, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v28, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v75, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v76, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v79, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v80, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v83, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v84, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v31, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v87, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v88, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v91, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v92, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v97, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v98, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v101, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v102, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v35, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v105, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v106, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v109, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v110, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v113, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v114, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v117, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v118, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v39, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v121, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v122, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v125, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v126, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v41, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v129, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v130, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v42, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v133, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v134, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v43, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v137, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v138, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v44, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v141, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v142, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v45, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v147, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v148, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v46, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v151, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v152, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v47, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v155, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v156, resolved type: org.apache.commons.math3.RealFieldElement} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v48, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v49, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v50, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v51, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v52, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v53, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v54, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v55, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v56, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v57, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v58, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v59, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v60, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v61, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v62, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v63, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v64, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v65, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v66, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v67, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v68, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v69, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v70, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v71, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v72, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v73, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v74, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v75, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v76, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v77, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v78, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v79, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v80, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v81, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v82, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v83, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v84, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v85, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v86, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v87, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v88, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v89, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v90, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v91, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v92, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v93, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v94, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v95, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v96, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: org.apache.commons.math3.RealFieldElement[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    DormandPrince853FieldStepInterpolator(org.apache.commons.math3.Field<T> r25, boolean r26, T[][] r27, org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> r28, org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> r29, org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> r30, org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> r31, org.apache.commons.math3.ode.FieldEquationsMapper<T> r32) {
        /*
            r24 = this;
            r24.<init>(r25, r26, r27, r28, r29, r30, r31, r32)
            r0 = 16
            r1 = 7
            r2 = r25
            java.lang.Object[][] r0 = org.apache.commons.math3.util.MathArrays.buildArray(r2, r1, r0)
            org.apache.commons.math3.RealFieldElement[][] r0 = (org.apache.commons.math3.RealFieldElement[][]) r0
            r3 = r24
            r3.d = r0
            r4 = 0
            r5 = r0[r4]
            r6 = 4681900899696640000(0x40f9741000000000, double:104257.0)
            r8 = 4700998179720527872(0x413d4cf000000000, double:1920240.0)
            r26 = r24
            r27 = r25
            r28 = r6
            r30 = r8
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r4] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r7 = 1
            r5[r7] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r8 = 2
            r5[r8] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r9 = 3
            r5[r9] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r10 = 4
            r5[r10] = r6
            r5 = r0[r4]
            r11 = 4704554410494132224(0x4149ef4f80000000, double:3399327.0)
            r13 = 4694808547731439616(0x41274f8000000000, double:763840.0)
            r28 = r11
            r30 = r13
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r11 = 5
            r5[r11] = r6
            r5 = r0[r4]
            r12 = 4724204815733751808(0x418fbf4000000000, double:6.6578432E7)
            r14 = 4719993061147410432(0x4180c8ae78000000, double:3.5198415E7)
            r28 = r12
            r30 = r14
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r12 = 6
            r5[r12] = r6
            r5 = r0[r4]
            r13 = -4478560177670324224(0xc1d8f53f30c00000, double:-1.674902723E9)
            r15 = 4733623466144563200(0x41b1357670000000, double:2.887164E8)
            r28 = r13
            r30 = r15
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r1] = r6
            r5 = r0[r4]
            r13 = 4812378289926319232(0x42c9008ebfdc2c80, double:5.4980371265625E13)
            r15 = 4820002157685024768(0x42e4166cc96ea800, double:1.76692375811392E14)
            r28 = r13
            r30 = r15
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r13 = 8
            r5[r13] = r6
            r5 = r0[r4]
            r14 = -4528816591546089472(0xc126694e00000000, double:-734375.0)
            r16 = 4706940215434936320(0x4152693000000000, double:4826304.0)
            r28 = r14
            r30 = r16
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 9
            r5[r14] = r6
            r5 = r0[r4]
            r15 = 4730027728416276480(0x41a46f2882000000, double:1.71414593E8)
            r17 = 4740424106556522496(0x41c95e9bec000000, double:8.512614E8)
            r28 = r15
            r30 = r17
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r15 = 10
            r5[r15] = r6
            r5 = r0[r4]
            r16 = 4683978529996537856(0x4100d5a800000000, double:137909.0)
            r18 = 4703878281710010368(0x4147886000000000, double:3084480.0)
            r28 = r16
            r30 = r18
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r16 = 11
            r5[r16] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r17 = 12
            r5[r17] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r18 = 13
            r5[r18] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r19 = 14
            r5[r19] = r6
            r5 = r0[r4]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r20 = 15
            r5[r20] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r4]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r14 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Object r6 = r6.add(r14)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r4] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r7]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r7] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r8]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r8] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r9]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r9] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r10]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r10] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r11]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r11] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r12]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r12] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r1]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r1] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r13]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r13] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r22 = 9
            r6 = r6[r22]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r22] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r21 = 10
            r6 = r6[r21]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r21] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r16]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r16] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r17]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r17] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r18]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r18] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r19]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r19] = r6
            r5 = r0[r7]
            r6 = r0[r4]
            r6 = r6[r20]
            java.lang.Object r6 = r6.negate()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r20] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r4]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            java.lang.Object r6 = r6.subtract(r14)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r4] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r7]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r7] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r8]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r8] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r9]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r9] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r10]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r10] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r11]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r11] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r12]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r12] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r1]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r1] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r13]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r13] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r22 = 9
            r6 = r6[r22]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r22] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r21 = 10
            r6 = r6[r21]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r21] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r16]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r16] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r17]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            java.lang.Object r6 = r6.subtract(r14)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r17] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r18]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r18] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r19]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r19] = r6
            r5 = r0[r8]
            r6 = r0[r4]
            r6 = r6[r20]
            java.lang.Object r6 = r6.multiply((int) r8)
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r20] = r6
            r5 = r0[r9]
            r14 = -4462917252860870656(0xc210886771440000, double:-1.7751989329E10)
            r22 = 4746620333333676032(0x41df620b64000000, double:2.10607656E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r4] = r6
            r5 = r0[r9]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r7] = r6
            r5 = r0[r9]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r8] = r6
            r5 = r0[r9]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r9] = r6
            r5 = r0[r9]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r10] = r6
            r5 = r0[r9]
            r14 = 4751251441729929216(0x41efd60356e00000, double:4.272954039E9)
            r22 = 4754700128353255424(0x41fc169344000000, double:7.53986464E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r11] = r6
            r5 = r0[r9]
            r14 = -4450799167006048256(0xc23b95bd2c000000, double:-1.18476319744E11)
            r22 = 4765364819638484992(0x4221fa0e33b20000, double:3.8604839385E10)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r12] = r6
            r5 = r0[r9]
            r14 = 4784505175948484608(0x4265fa1bae6d6000, double:7.55123450731E11)
            r22 = 4779003725287260160(0x42526e9162040000, double:3.166577316E11)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r1] = r6
            r5 = r0[r9]
            r14 = 4884610084843096442(0x43c99efe0d5d097a, double:3.6923844612348283E18)
            r22 = 4879707806352510463(0x43b8346565578dff, double:1.7441304416342505E18)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r13] = r6
            r5 = r0[r9]
            r14 = -4471741358118273024(0xc1f12eed55f00000, double:-4.612609375E9)
            r22 = 4752344521595944960(0x41f3b829d4000000, double:5.293382976E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 9
            r5[r14] = r6
            r5 = r0[r9]
            r14 = 4791390703519707136(0x427e70757f66b000, double:2.091772278379E12)
            r22 = 4785967621093523456(0x426b2c31b9cd0000, double:9.336445866E11)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 10
            r5[r14] = r6
            r5 = r0[r9]
            r14 = 4746748459158077440(0x41dfd69302400000, double:2.136624137E9)
            r22 = 4749385050020118528(0x41e9348a28000000, double:3.38298912E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r16] = r6
            r5 = r0[r9]
            r14 = -4539943090873434112(0xc0fee1d000000000, double:-126493.0)
            r22 = 4698855781313806336(0x4135b07000000000, double:1421424.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r17] = r6
            r5 = r0[r9]
            r14 = 4726372566258679808(0x419772cec0000000, double:9.835E7)
            r22 = 4707576810118840320(0x4154ac2ac0000000, double:5419179.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r18] = r6
            r5 = r0[r9]
            r14 = -4507539268532436992(0xc17200ead0000000, double:-1.8878125E7)
            r22 = 4701569101133250560(0x413f543000000000, double:2053168.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r19] = r6
            r5 = r0[r9]
            r14 = -4477429225975971840(0xc1dcf9d766c00000, double:-1.944542619E9)
            r22 = 4736133924323852288(0x41ba20b608000000, double:4.38351368E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r20] = r6
            r5 = r0[r10]
            r14 = 4764436674799468544(0x421eade9a4440000, double:3.2941697297E10)
            r22 = 4748915551626067968(0x41e789888b000000, double:3.15911484E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r4] = r6
            r5 = r0[r10]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r7] = r6
            r5 = r0[r10]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r8] = r6
            r5 = r0[r10]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r9] = r6
            r5 = r0[r10]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r10] = r6
            r5 = r0[r10]
            r14 = 4781298115277012992(0x425a954dae54c000, double:4.56696183123E11)
            r22 = 4745692929098514432(0x41dc169344000000, double:1.88496616E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r11] = r6
            r5 = r0[r10]
            r14 = 4805735151119892480(0x42b166a8771c0000, double:1.9132610714624E13)
            r22 = 4772398426019790848(0x423af7154d8b0000, double:1.15814518155E11)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r12] = r6
            r5 = r0[r10]
            r14 = -4403331085160741408(0xc2e439b5388f05e0, double:-1.77904688592943E14)
            r22 = 4781597785424527360(0x425ba5da13060000, double:4.749865974E11)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r1] = r6
            r5 = r0[r10]
            r14 = -4336761896310708178(0xc3d0ba084ef3682e, double:-4.8211399418367652E18)
            r22 = 4866197007470398975(0x4388346565578dff, double:2.18016305204281312E17)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r13] = r6
            r5 = r0[r10]
            r14 = 4763849555687243776(0x421c97ee52240000, double:3.0702015625E10)
            r22 = 4750616179142295552(0x41ed943ebe000000, double:3.970037232E9)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 9
            r5[r14] = r6
            r5 = r0[r10]
            r14 = -4408029005736732544(0xc2d388f959719880, double:-8.5916079474274E13)
            r22 = 4793062716234907648(0x428461254b59c000, double:2.8009337598E12)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 10
            r5[r14] = r6
            r5 = r0[r10]
            r14 = -4470371017521364992(0xc1f60d3e9e700000, double:-5.919468007E9)
            r22 = 4738604190165630976(0x41c2e7679e000000, double:6.3431046E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r16] = r6
            r5 = r0[r10]
            r14 = 4702578364760719360(0x4142ea1b80000000, double:2479159.0)
            r22 = 4684666652476833792(0x4103478000000000, double:157936.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r17] = r6
            r5 = r0[r10]
            r14 = -4507573661825236992(0xc171e1a300000000, double:-1.875E7)
            r22 = 4693419477998501888(0x4122602600000000, double:602131.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r18] = r6
            r5 = r0[r10]
            r14 = -4507452027009236992(0xc172504350000000, double:-1.9203125E7)
            r22 = 4701569101133250560(0x413f543000000000, double:2053168.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r19] = r6
            r5 = r0[r10]
            r14 = 4759529117986586624(0x420d3e8407b80000, double:1.5700361463E10)
            r22 = 4736133924323852288(0x41ba20b608000000, double:4.38351368E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r20] = r6
            r5 = r0[r11]
            r14 = 4757917799659601920(0x42078507bf380000, double:1.2627015655E10)
            r22 = 4738583323570339840(0x41c2d46d3c000000, double:6.31822968E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r4] = r6
            r5 = r0[r11]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r7] = r6
            r5 = r0[r11]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r8] = r6
            r5 = r0[r11]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r9] = r6
            r5 = r0[r11]
            java.lang.Object r6 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r6 = (org.apache.commons.math3.RealFieldElement) r6
            r5[r10] = r6
            r5 = r0[r11]
            r14 = -4453782437604556800(0xc230fc785bb50000, double:-7.2955222965E10)
            r22 = 4730600905995452416(0x41a67875d0000000, double:1.88496616E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r11] = r6
            r5 = r0[r11]
            r14 = -4420307812289609728(0xc2a7e976ea980000, double:-1.314574495232E13)
            r22 = 4769362417915068416(0x42302dd994ed0000, double:6.9488710893E10)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r12] = r6
            r5 = r0[r11]
            r14 = 4808538762122744064(0x42bb5c870505d100, double:3.0084216194513E13)
            r22 = 4767775699325943808(0x422a8abcdf100000, double:5.6998391688E10)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r1] = r6
            r5 = r0[r11]
            r14 = -4354846021153911368(0xc3907a9de1990db8, double:-2.9685876100664064E17)
            r22 = 4852285443321367852(0x4356c7e6f5f80d2c, double:2.5648977082856624E16)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r13] = r6
            r5 = r0[r11]
            r14 = 4738057505966391296(0x41c0f632c8800000, double:5.69140625E8)
            r22 = 4725322923831721984(0x4193b829d4000000, double:8.2709109E7)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 9
            r5[r14] = r6
            r5 = r0[r11]
            r14 = -4462672881881186304(0xc21166a87eb40000, double:-1.8684190637E10)
            r22 = 4760696193033437184(0x421163f6dd500000, double:1.8672891732E10)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r14 = 10
            r5[r14] = r6
            r5 = r0[r11]
            r14 = 4724446142228594688(0x41909abc34000000, double:6.9644045E7)
            r22 = 4725781988928126976(0x419559ae40000000, double:8.9549712E7)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r16] = r6
            r5 = r0[r11]
            r14 = -4510750103136370688(0xc16698ae20000000, double:-1.1847025E7)
            r22 = 4706336738170109952(0x4150445400000000, double:4264272.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r17] = r6
            r5 = r0[r11]
            r14 = -4481879317269184512(0xc1cd2a81c8000000, double:-9.7865E8)
            r22 = 4714989809318232064(0x416f024020000000, double:1.6257537E7)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r18] = r6
            r5 = r0[r11]
            r14 = 4737493222870220800(0x41bef4fc63000000, double:5.19371875E8)
            r22 = 4708371728034693120(0x41577f2400000000, double:6159504.0)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r19] = r6
            r5 = r0[r11]
            r14 = 4752306200598544384(0x41f3954f86900000, double:5.256837225E9)
            r22 = 4736133924323852288(0x41ba20b608000000, double:4.38351368E8)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r20] = r6
            r5 = r0[r12]
            r14 = -4487026827704926208(0xc1bae0df9d000000, double:-4.50944925E8)
            r22 = 4715476423744159744(0x4170bcd2e0000000, double:1.7550638E7)
            r28 = r14
            r30 = r22
            org.apache.commons.math3.RealFieldElement r6 = r26.fraction(r27, r28, r30)
            r5[r4] = r6
            r4 = r0[r12]
            java.lang.Object r5 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r7] = r5
            r4 = r0[r12]
            java.lang.Object r5 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r8] = r5
            r4 = r0[r12]
            java.lang.Object r5 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r9] = r5
            r4 = r0[r12]
            java.lang.Object r5 = r25.getZero()
            org.apache.commons.math3.RealFieldElement r5 = (org.apache.commons.math3.RealFieldElement) r5
            r4[r10] = r5
            r4 = r0[r12]
            r5 = -4464455412314800128(0xc20b1174c9680000, double:-1.4532122925E10)
            r7 = 4726097306368081920(0x41967875d0000000, double:9.4248308E7)
            r28 = r5
            r30 = r7
            org.apache.commons.math3.RealFieldElement r5 = r26.fraction(r27, r28, r30)
            r4[r11] = r5
            r4 = r0[r12]
            r5 = -4440171408105930752(0xc26157a11e000000, double:-5.958769664E11)
            r7 = 4747687755362861056(0x41e32cdbf2e00000, double:2.573655959E9)
            r28 = r5
            r30 = r7
            org.apache.commons.math3.RealFieldElement r5 = r26.fraction(r27, r28, r30)
            r4[r12] = r5
            r4 = r0[r12]
            r5 = 4775496921247350784(0x4245f925f0eb8000, double:1.88748653015E11)
            r7 = 4737634000674226176(0x41bf7505c6000000, double:5.27762886E8)
            r28 = r5
            r30 = r7
            org.apache.commons.math3.RealFieldElement r5 = r26.fraction(r27, r28, r30)
            r4[r1] = r5
            r1 = r0[r12]
            r4 = 4882370047727628485(0x43c1a9b0c4cff0c5, double:2.5454854581152343E18)
            r6 = 4852686208588287487(0x4358346565578dff, double:2.7252038150535164E16)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r1[r13] = r4
            r1 = r0[r12]
            r4 = -4479809868861014016(0xc1d484a8c9400000, double:-1.376953125E9)
            r6 = 4720202600387969024(0x41818741a0000000, double:3.6759604E7)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r5 = 9
            r1[r5] = r4
            r1 = r0[r12]
            r4 = 4767382116993728512(0x422924c6d7f60000, double:5.3995596795E10)
            r6 = 4737481807014920192(0x41beea9a6d000000, double:5.18691437E8)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r5 = 10
            r1[r5] = r4
            r1 = r0[r12]
            r4 = 4731332882809749504(0x41a9123072000000, double:2.10311225E8)
            r6 = 4709325629533716480(0x415ae2b580000000, double:7047894.0)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r1[r16] = r4
            r1 = r0[r12]
            r4 = -4523238713223806976(0xc13a3a5b00000000, double:-1718875.0)
            r6 = 4675659453222092800(0x40e3478000000000, double:39484.0)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r1[r17] = r4
            r1 = r0[r12]
            r4 = 4723053438080909312(0x418ba81400000000, double:5.8E7)
            r6 = 4693419477998501888(0x4122602600000000, double:602131.0)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r1[r18] = r4
            r1 = r0[r12]
            r4 = -4523977447598718976(0xc1379a7b00000000, double:-1546875.0)
            r6 = 4675659453222092800(0x40e3478000000000, double:39484.0)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r4 = r26.fraction(r27, r28, r30)
            r1[r19] = r4
            r0 = r0[r12]
            r4 = -4480291294219862016(0xc1d2cece35c00000, double:-1.262172375E9)
            r6 = 4710787343269756928(0x4160142140000000, double:8429834.0)
            r28 = r4
            r30 = r6
            org.apache.commons.math3.RealFieldElement r1 = r26.fraction(r27, r28, r30)
            r0[r20] = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.DormandPrince853FieldStepInterpolator.<init>(org.apache.commons.math3.Field, boolean, org.apache.commons.math3.RealFieldElement[][], org.apache.commons.math3.ode.FieldODEStateAndDerivative, org.apache.commons.math3.ode.FieldODEStateAndDerivative, org.apache.commons.math3.ode.FieldODEStateAndDerivative, org.apache.commons.math3.ode.FieldODEStateAndDerivative, org.apache.commons.math3.ode.FieldEquationsMapper):void");
    }

    /* access modifiers changed from: protected */
    public DormandPrince853FieldStepInterpolator<T> create(Field<T> field, boolean z, T[][] tArr, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative2, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative3, FieldODEStateAndDerivative<T> fieldODEStateAndDerivative4, FieldEquationsMapper<T> fieldEquationsMapper) {
        return new DormandPrince853FieldStepInterpolator(field, z, tArr, fieldODEStateAndDerivative, fieldODEStateAndDerivative2, fieldODEStateAndDerivative3, fieldODEStateAndDerivative4, fieldEquationsMapper);
    }

    private T fraction(Field<T> field, double d2, double d3) {
        return (RealFieldElement) ((RealFieldElement) ((RealFieldElement) field.getZero()).add(d2)).divide(d3);
    }

    /* access modifiers changed from: protected */
    public FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(FieldEquationsMapper<T> fieldEquationsMapper, T t, T t2, T t3, T t4) throws MaxCountExceededException {
        RealFieldElement[] realFieldElementArr;
        RealFieldElement[] realFieldElementArr2;
        T t5 = t2;
        T t6 = t3;
        RealFieldElement realFieldElement = (RealFieldElement) t.getField().getOne();
        RealFieldElement realFieldElement2 = (RealFieldElement) realFieldElement.subtract(t5);
        RealFieldElement realFieldElement3 = (RealFieldElement) t5.multiply(2);
        RealFieldElement realFieldElement4 = (RealFieldElement) t5.multiply(t5);
        RealFieldElement realFieldElement5 = (RealFieldElement) realFieldElement.subtract(realFieldElement3);
        RealFieldElement realFieldElement6 = (RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-3)).add(2.0d));
        RealFieldElement realFieldElement7 = (RealFieldElement) realFieldElement3.multiply(((RealFieldElement) t5.multiply(realFieldElement3.subtract(3.0d))).add(1.0d));
        RealFieldElement realFieldElement8 = (RealFieldElement) realFieldElement4.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(5)).subtract(8.0d))).add(3.0d));
        RealFieldElement realFieldElement9 = (RealFieldElement) realFieldElement4.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-6)).add(15.0d))).subtract(12.0d))).add(3.0d));
        RealFieldElement realFieldElement10 = (RealFieldElement) realFieldElement4.multiply(t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(((RealFieldElement) t5.multiply(-7)).add(18.0d))).subtract(15.0d))).add(4.0d)));
        if (getGlobalPreviousState() == null || t2.getReal() > 0.5d) {
            RealFieldElement realFieldElement11 = realFieldElement8;
            RealFieldElement realFieldElement12 = (RealFieldElement) t4.negate();
            RealFieldElement realFieldElement13 = (RealFieldElement) ((RealFieldElement) realFieldElement12.multiply(t5)).negate();
            RealFieldElement realFieldElement14 = (RealFieldElement) realFieldElement13.multiply(t5);
            RealFieldElement realFieldElement15 = (RealFieldElement) realFieldElement14.multiply(realFieldElement2);
            RealFieldElement realFieldElement16 = (RealFieldElement) realFieldElement15.multiply(t5);
            RealFieldElement realFieldElement17 = (RealFieldElement) realFieldElement16.multiply(realFieldElement2);
            RealFieldElement realFieldElement18 = (RealFieldElement) realFieldElement17.multiply(t5);
            RealFieldElement[] realFieldElementArr3 = (RealFieldElement[]) MathArrays.buildArray(t.getField(), 16);
            RealFieldElement realFieldElement19 = realFieldElement10;
            RealFieldElement[] realFieldElementArr4 = (RealFieldElement[]) MathArrays.buildArray(t.getField(), 16);
            int i = 0;
            while (i < realFieldElementArr3.length) {
                RealFieldElement realFieldElement20 = realFieldElement12;
                realFieldElementArr3[i] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) realFieldElement12.multiply(this.d[0][i])).add(realFieldElement13.multiply(this.d[1][i]))).add(realFieldElement14.multiply(this.d[2][i]))).add(realFieldElement15.multiply(this.d[3][i]))).add(realFieldElement16.multiply(this.d[4][i]))).add(realFieldElement17.multiply(this.d[5][i]))).add(realFieldElement18.multiply(this.d[6][i]));
                T[][] tArr = this.d;
                realFieldElementArr4[i] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr[0][i].add(realFieldElement5.multiply(tArr[1][i]))).add(realFieldElement6.multiply(this.d[2][i]))).add(realFieldElement7.multiply(this.d[3][i]))).add(realFieldElement11.multiply(this.d[4][i]))).add(realFieldElement9.multiply(this.d[5][i]))).add(realFieldElement19.multiply(this.d[6][i]));
                i++;
                realFieldElement12 = realFieldElement20;
                realFieldElement18 = realFieldElement18;
            }
            realFieldElementArr = currentStateLinearCombination(realFieldElementArr3[0], realFieldElementArr3[1], realFieldElementArr3[2], realFieldElementArr3[3], realFieldElementArr3[4], realFieldElementArr3[5], realFieldElementArr3[6], realFieldElementArr3[7], realFieldElementArr3[8], realFieldElementArr3[9], realFieldElementArr3[10], realFieldElementArr3[11], realFieldElementArr3[12], realFieldElementArr3[13], realFieldElementArr3[14], realFieldElementArr3[15]);
            realFieldElementArr2 = derivativeLinearCombination(realFieldElementArr4[0], realFieldElementArr4[1], realFieldElementArr4[2], realFieldElementArr4[3], realFieldElementArr4[4], realFieldElementArr4[5], realFieldElementArr4[6], realFieldElementArr4[7], realFieldElementArr4[8], realFieldElementArr4[9], realFieldElementArr4[10], realFieldElementArr4[11], realFieldElementArr4[12], realFieldElementArr4[13], realFieldElementArr4[14], realFieldElementArr4[15]);
        } else {
            RealFieldElement realFieldElement21 = (RealFieldElement) t6.multiply(realFieldElement2);
            RealFieldElement realFieldElement22 = (RealFieldElement) realFieldElement21.multiply(t5);
            RealFieldElement realFieldElement23 = (RealFieldElement) realFieldElement22.multiply(realFieldElement2);
            RealFieldElement realFieldElement24 = (RealFieldElement) realFieldElement23.multiply(t5);
            RealFieldElement realFieldElement25 = (RealFieldElement) realFieldElement24.multiply(realFieldElement2);
            RealFieldElement realFieldElement26 = (RealFieldElement) realFieldElement25.multiply(t5);
            RealFieldElement[] realFieldElementArr5 = (RealFieldElement[]) MathArrays.buildArray(t.getField(), 16);
            RealFieldElement[] realFieldElementArr6 = (RealFieldElement[]) MathArrays.buildArray(t.getField(), 16);
            int i2 = 0;
            while (i2 < realFieldElementArr5.length) {
                realFieldElementArr5[i2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) t6.multiply(this.d[0][i2])).add(realFieldElement21.multiply(this.d[1][i2]))).add(realFieldElement22.multiply(this.d[2][i2]))).add(realFieldElement23.multiply(this.d[3][i2]))).add(realFieldElement24.multiply(this.d[4][i2]))).add(realFieldElement25.multiply(this.d[5][i2]))).add(realFieldElement26.multiply(this.d[6][i2]));
                T[][] tArr2 = this.d;
                RealFieldElement realFieldElement27 = realFieldElement21;
                RealFieldElement realFieldElement28 = realFieldElement8;
                realFieldElementArr6[i2] = (RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) ((RealFieldElement) tArr2[0][i2].add(realFieldElement5.multiply(tArr2[1][i2]))).add(realFieldElement6.multiply(this.d[2][i2]))).add(realFieldElement7.multiply(this.d[3][i2]))).add(realFieldElement28.multiply(this.d[4][i2]))).add(realFieldElement9.multiply(this.d[5][i2]))).add(realFieldElement10.multiply(this.d[6][i2]));
                i2++;
                t6 = t3;
                realFieldElement8 = realFieldElement28;
                realFieldElement21 = realFieldElement27;
            }
            realFieldElementArr = previousStateLinearCombination(realFieldElementArr5[0], realFieldElementArr5[1], realFieldElementArr5[2], realFieldElementArr5[3], realFieldElementArr5[4], realFieldElementArr5[5], realFieldElementArr5[6], realFieldElementArr5[7], realFieldElementArr5[8], realFieldElementArr5[9], realFieldElementArr5[10], realFieldElementArr5[11], realFieldElementArr5[12], realFieldElementArr5[13], realFieldElementArr5[14], realFieldElementArr5[15]);
            realFieldElementArr2 = derivativeLinearCombination(realFieldElementArr6[0], realFieldElementArr6[1], realFieldElementArr6[2], realFieldElementArr6[3], realFieldElementArr6[4], realFieldElementArr6[5], realFieldElementArr6[6], realFieldElementArr6[7], realFieldElementArr6[8], realFieldElementArr6[9], realFieldElementArr6[10], realFieldElementArr6[11], realFieldElementArr6[12], realFieldElementArr6[13], realFieldElementArr6[14], realFieldElementArr6[15]);
        }
        return new FieldODEStateAndDerivative<>(t, realFieldElementArr, realFieldElementArr2);
    }
}
