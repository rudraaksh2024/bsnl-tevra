package org.apache.poi.ss.formula;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.util.Internal;

@Internal
public final class CollaboratingWorkbooksEnvironment {
    public static final CollaboratingWorkbooksEnvironment EMPTY = new CollaboratingWorkbooksEnvironment();
    private final WorkbookEvaluator[] _evaluators;
    private final Map<String, WorkbookEvaluator> _evaluatorsByName;
    private boolean _unhooked;

    public static final class WorkbookNotFoundException extends Exception {
        private static final long serialVersionUID = 8787784539811167941L;

        WorkbookNotFoundException(String str) {
            super(str);
        }
    }

    private CollaboratingWorkbooksEnvironment() {
        this._evaluatorsByName = Collections.emptyMap();
        this._evaluators = new WorkbookEvaluator[0];
    }

    public static void setup(String[] strArr, WorkbookEvaluator[] workbookEvaluatorArr) {
        int length = strArr.length;
        if (workbookEvaluatorArr.length != length) {
            throw new IllegalArgumentException("Number of workbook names is " + length + " but number of evaluators is " + workbookEvaluatorArr.length);
        } else if (length >= 1) {
            new CollaboratingWorkbooksEnvironment(strArr, workbookEvaluatorArr, length);
        } else {
            throw new IllegalArgumentException("Must provide at least one collaborating worbook");
        }
    }

    public static void setup(Map<String, WorkbookEvaluator> map) {
        if (map.size() >= 1) {
            new CollaboratingWorkbooksEnvironment(map, (WorkbookEvaluator[]) map.values().toArray(new WorkbookEvaluator[0]));
            return;
        }
        throw new IllegalArgumentException("Must provide at least one collaborating worbook");
    }

    public static void setupFormulaEvaluator(Map<String, FormulaEvaluator> map) {
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            FormulaEvaluator formulaEvaluator = (FormulaEvaluator) next.getValue();
            if (formulaEvaluator instanceof WorkbookEvaluatorProvider) {
                hashMap.put(str, ((WorkbookEvaluatorProvider) formulaEvaluator)._getWorkbookEvaluator());
            } else {
                throw new IllegalArgumentException("Formula Evaluator " + formulaEvaluator + " provides no WorkbookEvaluator access");
            }
        }
        setup(hashMap);
    }

    private CollaboratingWorkbooksEnvironment(String[] strArr, WorkbookEvaluator[] workbookEvaluatorArr, int i) {
        this(toUniqueMap(strArr, workbookEvaluatorArr, i), workbookEvaluatorArr);
    }

    private static Map<String, WorkbookEvaluator> toUniqueMap(String[] strArr, WorkbookEvaluator[] workbookEvaluatorArr, int i) {
        HashMap hashMap = new HashMap((i * 3) / 2);
        int i2 = 0;
        while (i2 < i) {
            String str = strArr[i2];
            WorkbookEvaluator workbookEvaluator = workbookEvaluatorArr[i2];
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, workbookEvaluator);
                i2++;
            } else {
                throw new IllegalArgumentException("Duplicate workbook name '" + str + "'");
            }
        }
        return hashMap;
    }

    private CollaboratingWorkbooksEnvironment(Map<String, WorkbookEvaluator> map, WorkbookEvaluator[] workbookEvaluatorArr) {
        IdentityHashMap identityHashMap = new IdentityHashMap(workbookEvaluatorArr.length);
        for (Map.Entry next : map.entrySet()) {
            String str = (String) identityHashMap.put(next.getValue(), next.getKey());
            if (str != null) {
                throw new IllegalArgumentException("Attempted to register same workbook under names '" + str + "' and '" + ((String) next.getKey()) + "'");
            }
        }
        unhookOldEnvironments(workbookEvaluatorArr);
        hookNewEnvironment(workbookEvaluatorArr, this);
        this._unhooked = false;
        this._evaluators = (WorkbookEvaluator[]) workbookEvaluatorArr.clone();
        this._evaluatorsByName = map;
    }

    private static void hookNewEnvironment(WorkbookEvaluator[] workbookEvaluatorArr, CollaboratingWorkbooksEnvironment collaboratingWorkbooksEnvironment) {
        int length = workbookEvaluatorArr.length;
        IEvaluationListener evaluationListener = workbookEvaluatorArr[0].getEvaluationListener();
        int length2 = workbookEvaluatorArr.length;
        int i = 0;
        while (i < length2) {
            if (evaluationListener == workbookEvaluatorArr[i].getEvaluationListener()) {
                i++;
            } else {
                throw new RuntimeException("Workbook evaluators must all have the same evaluation listener");
            }
        }
        EvaluationCache evaluationCache = new EvaluationCache(evaluationListener);
        for (int i2 = 0; i2 < length; i2++) {
            workbookEvaluatorArr[i2].attachToEnvironment(collaboratingWorkbooksEnvironment, evaluationCache, i2);
        }
    }

    private void unhookOldEnvironments(WorkbookEvaluator[] workbookEvaluatorArr) {
        HashSet hashSet = new HashSet();
        for (WorkbookEvaluator environment : workbookEvaluatorArr) {
            hashSet.add(environment.getEnvironment());
        }
        int size = hashSet.size();
        CollaboratingWorkbooksEnvironment[] collaboratingWorkbooksEnvironmentArr = new CollaboratingWorkbooksEnvironment[size];
        hashSet.toArray(collaboratingWorkbooksEnvironmentArr);
        for (int i = 0; i < size; i++) {
            collaboratingWorkbooksEnvironmentArr[i].unhook();
        }
    }

    private void unhook() {
        WorkbookEvaluator[] workbookEvaluatorArr = this._evaluators;
        if (workbookEvaluatorArr.length >= 1) {
            for (WorkbookEvaluator detachFromEnvironment : workbookEvaluatorArr) {
                detachFromEnvironment.detachFromEnvironment();
            }
            this._unhooked = true;
        }
    }

    public WorkbookEvaluator getWorkbookEvaluator(String str) throws WorkbookNotFoundException {
        if (!this._unhooked) {
            WorkbookEvaluator workbookEvaluator = this._evaluatorsByName.get(str);
            if (workbookEvaluator != null) {
                return workbookEvaluator;
            }
            StringBuilder sb = new StringBuilder(256);
            sb.append("Could not resolve external workbook name '").append(str).append("'.");
            if (this._evaluators.length >= 1) {
                sb.append(" The following workbook names are valid: (");
                int i = 0;
                for (String append : this._evaluatorsByName.keySet()) {
                    int i2 = i + 1;
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(Chars.QUOTE).append(append).append("'");
                    i = i2;
                }
                sb.append(')');
            } else {
                sb.append(" Workbook environment has not been set up.");
            }
            throw new WorkbookNotFoundException(sb.toString());
        }
        throw new IllegalStateException("This environment has been unhooked");
    }
}
