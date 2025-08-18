package org.apache.logging.log4j.util;

import java.net.URL;
import java.security.Permission;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;
import org.osgi.framework.AdaptPermission;
import org.osgi.framework.AdminPermission;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.SynchronousBundleListener;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

public class Activator implements BundleActivator, SynchronousBundleListener {
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final SecurityManager SECURITY_MANAGER = System.getSecurityManager();
    private boolean lockingProviderUtil;

    private static void checkPermission(Permission permission) {
        SecurityManager securityManager = SECURITY_MANAGER;
        if (securityManager != null) {
            securityManager.checkPermission(permission);
        }
    }

    private void loadProvider(Bundle bundle) {
        if (bundle.getState() != 1) {
            try {
                checkPermission(new AdminPermission(bundle, "resource"));
                checkPermission(new AdaptPermission(BundleWiring.class.getName(), bundle, "adapt"));
                BundleContext bundleContext = bundle.getBundleContext();
                if (bundleContext == null) {
                    LOGGER.debug("Bundle {} has no context (state={}), skipping loading provider", (Object) bundle.getSymbolicName(), (Object) toStateString(bundle.getState()));
                } else {
                    loadProvider(bundleContext, (BundleWiring) bundle.adapt(BundleWiring.class));
                }
            } catch (SecurityException e) {
                LOGGER.debug("Cannot access bundle [{}] contents. Ignoring.", (Object) bundle.getSymbolicName(), (Object) e);
            } catch (Exception e2) {
                LOGGER.warn("Problem checking bundle {} for Log4j 2 provider.", (Object) bundle.getSymbolicName(), (Object) e2);
            }
        }
    }

    private String toStateString(int i) {
        if (i == 1) {
            return "UNINSTALLED";
        }
        if (i == 2) {
            return "INSTALLED";
        }
        if (i == 4) {
            return "RESOLVED";
        }
        if (i == 8) {
            return "STARTING";
        }
        if (i != 16) {
            return i != 32 ? Integer.toString(i) : "ACTIVE";
        }
        return "STOPPING";
    }

    private void loadProvider(BundleContext bundleContext, BundleWiring bundleWiring) {
        try {
            Provider provider = null;
            for (ServiceReference service : bundleContext.getServiceReferences(Provider.class, "(APIVersion>=2.6.0)")) {
                Provider provider2 = (Provider) bundleContext.getService(service);
                if (provider == null || provider2.getPriority().intValue() > provider.getPriority().intValue()) {
                    provider = provider2;
                }
            }
            if (provider != null) {
                ProviderUtil.addProvider(provider);
            }
        } catch (InvalidSyntaxException e) {
            LOGGER.error("Invalid service filter: (APIVersion>=2.6.0)", (Throwable) e);
        }
        for (URL loadProvider : bundleWiring.findEntries("META-INF", "log4j-provider.properties", 0)) {
            ProviderUtil.loadProvider(loadProvider, bundleWiring.getClassLoader());
        }
    }

    public void start(BundleContext bundleContext) throws Exception {
        ProviderUtil.STARTUP_LOCK.lock();
        this.lockingProviderUtil = true;
        for (BundleWire providerWiring : ((BundleWiring) bundleContext.getBundle().adapt(BundleWiring.class)).getRequiredWires(LoggerContextFactory.class.getName())) {
            loadProvider(bundleContext, providerWiring.getProviderWiring());
        }
        bundleContext.addBundleListener(this);
        for (Bundle loadProvider : bundleContext.getBundles()) {
            loadProvider(loadProvider);
        }
        unlockIfReady();
    }

    private void unlockIfReady() {
        if (this.lockingProviderUtil && !ProviderUtil.PROVIDERS.isEmpty()) {
            ProviderUtil.STARTUP_LOCK.unlock();
            this.lockingProviderUtil = false;
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        bundleContext.removeBundleListener(this);
        unlockIfReady();
    }

    public void bundleChanged(BundleEvent bundleEvent) {
        if (bundleEvent.getType() == 2) {
            loadProvider(bundleEvent.getBundle());
            unlockIfReady();
        }
    }
}
