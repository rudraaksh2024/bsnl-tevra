package com.google.android.gms.maps.internal;

import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.maps.OnMapReadyCallback;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public interface MapLifecycleDelegate extends LifecycleDelegate {
    void getMapAsync(OnMapReadyCallback onMapReadyCallback);
}
