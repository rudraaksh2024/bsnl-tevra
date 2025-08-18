package org.apache.xmlbeans;

public interface SchemaLocalElement extends SchemaField, SchemaAnnotated {
    boolean blockExtension();

    boolean blockRestriction();

    boolean blockSubstitution();

    SchemaIdentityConstraint[] getIdentityConstraints();

    boolean isAbstract();
}
