package com.google.firebase.datatransport;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(TransportFactory.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) Context.class)).factory(new TransportRegistrar$$ExternalSyntheticLambda0()).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }
}
