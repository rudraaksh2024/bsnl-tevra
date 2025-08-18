package org.apache.poi.ss.formula.function;

import java.util.Map;
import java.util.Set;

public final class FunctionMetadataRegistry {
    public static final int FUNCTION_INDEX_CHOOSE = 100;
    public static final short FUNCTION_INDEX_EXTERNAL = 255;
    public static final int FUNCTION_INDEX_IF = 1;
    public static final short FUNCTION_INDEX_INDIRECT = 148;
    public static final short FUNCTION_INDEX_SUM = 4;
    public static final String FUNCTION_NAME_IF = "IF";
    private static FunctionMetadataRegistry _instance;
    private static FunctionMetadataRegistry _instanceCetab;
    private final FunctionMetadata[] _functionDataByIndex;
    private final Map<String, FunctionMetadata> _functionDataByName;

    private static FunctionMetadataRegistry getInstance() {
        if (_instance == null) {
            _instance = FunctionMetadataReader.createRegistry();
        }
        return _instance;
    }

    private static FunctionMetadataRegistry getInstanceCetab() {
        if (_instanceCetab == null) {
            _instanceCetab = FunctionMetadataReader.createRegistryCetab();
        }
        return _instanceCetab;
    }

    FunctionMetadataRegistry(FunctionMetadata[] functionMetadataArr, Map<String, FunctionMetadata> map) {
        FunctionMetadata[] functionMetadataArr2;
        if (functionMetadataArr == null) {
            functionMetadataArr2 = null;
        } else {
            functionMetadataArr2 = (FunctionMetadata[]) functionMetadataArr.clone();
        }
        this._functionDataByIndex = functionMetadataArr2;
        this._functionDataByName = map;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getAllFunctionNames() {
        return this._functionDataByName.keySet();
    }

    public static FunctionMetadata getFunctionByIndex(int i) {
        return getInstance().getFunctionByIndexInternal(i);
    }

    public static FunctionMetadata getCetabFunctionByIndex(int i) {
        return getInstanceCetab().getFunctionByIndexInternal(i);
    }

    private FunctionMetadata getFunctionByIndexInternal(int i) {
        return this._functionDataByIndex[i];
    }

    public static short lookupIndexByName(String str) {
        FunctionMetadata functionByNameInternal = getInstance().getFunctionByNameInternal(str);
        if (functionByNameInternal == null && (functionByNameInternal = getInstanceCetab().getFunctionByNameInternal(str)) == null) {
            return -1;
        }
        return (short) functionByNameInternal.getIndex();
    }

    private FunctionMetadata getFunctionByNameInternal(String str) {
        return this._functionDataByName.get(str);
    }

    public static FunctionMetadata getFunctionByName(String str) {
        FunctionMetadata functionByNameInternal = getInstance().getFunctionByNameInternal(str);
        return functionByNameInternal == null ? getInstanceCetab().getFunctionByNameInternal(str) : functionByNameInternal;
    }
}
