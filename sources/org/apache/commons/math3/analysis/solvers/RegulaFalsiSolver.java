package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.BaseSecantSolver;

public class RegulaFalsiSolver extends BaseSecantSolver {
    public RegulaFalsiSolver() {
        super(1.0E-6d, BaseSecantSolver.Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d) {
        super(d, BaseSecantSolver.Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d, double d2) {
        super(d, d2, BaseSecantSolver.Method.REGULA_FALSI);
    }

    public RegulaFalsiSolver(double d, double d2, double d3) {
        super(d, d2, d3, BaseSecantSolver.Method.REGULA_FALSI);
    }
}
