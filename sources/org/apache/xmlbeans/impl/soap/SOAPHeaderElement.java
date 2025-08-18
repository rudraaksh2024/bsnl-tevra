package org.apache.xmlbeans.impl.soap;

public interface SOAPHeaderElement extends SOAPElement {
    String getActor();

    boolean getMustUnderstand();

    void setActor(String str);

    void setMustUnderstand(boolean z);
}
