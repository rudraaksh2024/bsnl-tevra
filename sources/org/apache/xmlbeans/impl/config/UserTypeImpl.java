package org.apache.xmlbeans.impl.config;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class UserTypeImpl implements UserType {
    private String _javaName;
    private QName _name;
    private String _staticHandler;

    static UserTypeImpl newInstance(Parser parser, Usertypeconfig usertypeconfig) {
        UserTypeImpl userTypeImpl = new UserTypeImpl();
        userTypeImpl._name = usertypeconfig.getName();
        userTypeImpl._javaName = usertypeconfig.getJavaname();
        userTypeImpl._staticHandler = usertypeconfig.getStaticHandler();
        return userTypeImpl;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public QName getName() {
        return this._name;
    }

    public String getStaticHandler() {
        return this._staticHandler;
    }
}
