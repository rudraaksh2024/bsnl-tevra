package org.apache.xmlbeans;

public interface InterfaceExtension {

    public interface MethodSignature {
        String[] getExceptionTypes();

        String getName();

        String[] getParameterTypes();

        String getReturnType();
    }

    String getInterface();

    MethodSignature[] getMethods();

    String getStaticHandler();
}
