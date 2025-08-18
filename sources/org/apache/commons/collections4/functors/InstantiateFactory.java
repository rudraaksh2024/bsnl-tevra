package org.apache.commons.collections4.functors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;

public class InstantiateFactory<T> implements Factory<T> {
    private final Object[] iArgs;
    private final Class<T> iClassToInstantiate;
    private transient Constructor<T> iConstructor = null;
    private final Class<?>[] iParamTypes;

    public static <T> Factory<T> instantiateFactory(Class<T> cls, Class<?>[] clsArr, Object[] objArr) {
        if (cls == null) {
            throw new NullPointerException("Class to instantiate must not be null");
        } else if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        } else if (clsArr == null || clsArr.length == 0) {
            return new InstantiateFactory(cls);
        } else {
            return new InstantiateFactory(cls, clsArr, objArr);
        }
    }

    public InstantiateFactory(Class<T> cls) {
        this.iClassToInstantiate = cls;
        this.iParamTypes = null;
        this.iArgs = null;
        findConstructor();
    }

    public InstantiateFactory(Class<T> cls, Class<?>[] clsArr, Object[] objArr) {
        this.iClassToInstantiate = cls;
        this.iParamTypes = (Class[]) clsArr.clone();
        this.iArgs = (Object[]) objArr.clone();
        findConstructor();
    }

    private void findConstructor() {
        try {
            this.iConstructor = this.iClassToInstantiate.getConstructor(this.iParamTypes);
        } catch (NoSuchMethodException unused) {
            throw new IllegalArgumentException("InstantiateFactory: The constructor must exist and be public ");
        }
    }

    public T create() {
        if (this.iConstructor == null) {
            findConstructor();
        }
        try {
            return this.iConstructor.newInstance(this.iArgs);
        } catch (InstantiationException e) {
            throw new FunctorException("InstantiateFactory: InstantiationException", e);
        } catch (IllegalAccessException e2) {
            throw new FunctorException("InstantiateFactory: Constructor must be public", e2);
        } catch (InvocationTargetException e3) {
            throw new FunctorException("InstantiateFactory: Constructor threw an exception", e3);
        }
    }
}
