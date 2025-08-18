package org.apache.poi.poifs.nio;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.Objects;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:574)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:220)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:151)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:58)
    */
public final class CleanerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final BufferCleaner CLEANER;
    public static final String UNMAP_NOT_SUPPORTED_REASON;
    public static final boolean UNMAP_SUPPORTED;

    @FunctionalInterface
    public interface BufferCleaner {
        void freeBuffer(ByteBuffer byteBuffer) throws IOException;
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: Unknown instruction: 'invoke-polymorphic' in method: org.apache.poi.poifs.nio.CleanerUtil.lambda$null$0(java.lang.invoke.MethodHandle, java.nio.ByteBuffer):java.lang.Throwable, dex: classes3.dex
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:151)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:286)
        	at jadx.core.ProcessClass.process(ProcessClass.java:36)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:58)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        Caused by: jadx.core.utils.exceptions.DecodeException: Unknown instruction: 'invoke-polymorphic'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:78)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:136)
        	... 4 more
        */
    static /* synthetic */ java.lang.Throwable lambda$null$0(java.lang.invoke.MethodHandle r1, java.nio.ByteBuffer r2) {
        /*
        // Can't load method instructions: Load method exception: Unknown instruction: 'invoke-polymorphic' in method: org.apache.poi.poifs.nio.CleanerUtil.lambda$null$0(java.lang.invoke.MethodHandle, java.nio.ByteBuffer):java.lang.Throwable, dex: classes3.dex
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.nio.CleanerUtil.lambda$null$0(java.lang.invoke.MethodHandle, java.nio.ByteBuffer):java.lang.Throwable");
    }

    private CleanerUtil() {
    }

    public static BufferCleaner getCleaner() {
        return CLEANER;
    }

    static {
        Object doPrivileged = AccessController.doPrivileged(new CleanerUtil$$ExternalSyntheticLambda2());
        if (doPrivileged instanceof BufferCleaner) {
            CLEANER = (BufferCleaner) doPrivileged;
            UNMAP_SUPPORTED = true;
            UNMAP_NOT_SUPPORTED_REASON = null;
            return;
        }
        CLEANER = null;
        UNMAP_SUPPORTED = false;
        UNMAP_NOT_SUPPORTED_REASON = doPrivileged.toString();
    }

    /* access modifiers changed from: private */
    public static Object unmapHackImpl() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            MethodHandle findVirtual = lookup.findVirtual(cls, "invokeCleaner", MethodType.methodType(Void.TYPE, ByteBuffer.class));
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return newBufferCleaner(ByteBuffer.class, findVirtual.bindTo(declaredField.get((Object) null)));
        } catch (SecurityException e) {
            throw e;
        } catch (ReflectiveOperationException | RuntimeException unused) {
            Class<?> cls2 = Class.forName("java.nio.DirectByteBuffer");
            Method method = cls2.getMethod("cleaner", new Class[0]);
            method.setAccessible(true);
            MethodHandle unreflect = lookup.unreflect(method);
            Class<?> returnType = unreflect.type().returnType();
            MethodHandle findVirtual2 = lookup.findVirtual(returnType, "clean", MethodType.methodType(Void.TYPE));
            return newBufferCleaner(cls2, MethodHandles.filterReturnValue(unreflect, MethodHandles.guardWithTest(lookup.findStatic(Objects.class, "nonNull", MethodType.methodType(Boolean.TYPE, Object.class)).asType(MethodType.methodType(Boolean.TYPE, returnType)), findVirtual2, MethodHandles.dropArguments(MethodHandles.constant(Void.class, (Object) null).asType(MethodType.methodType(Void.TYPE)), 0, new Class[]{returnType}))).asType(MethodType.methodType(Void.TYPE, ByteBuffer.class)));
        } catch (SecurityException e2) {
            return "Unmapping is not supported, because not all required permissions are given to the Hadoop JAR file: " + e2 + " [Please grant at least the following permissions: RuntimePermission(\"accessClassInPackage.sun.misc\")  and ReflectPermission(\"suppressAccessChecks\")]";
        } catch (ReflectiveOperationException | RuntimeException e3) {
            return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Hadoop version: " + e3;
        }
    }

    private static BufferCleaner newBufferCleaner(Class<?> cls, MethodHandle methodHandle) {
        return new CleanerUtil$$ExternalSyntheticLambda0(cls, methodHandle);
    }

    static /* synthetic */ void lambda$newBufferCleaner$1(Class cls, MethodHandle methodHandle, ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("unmapping only works with direct buffers");
        } else if (cls.isInstance(byteBuffer)) {
            Throwable th = (Throwable) AccessController.doPrivileged(new CleanerUtil$$ExternalSyntheticLambda1(methodHandle, byteBuffer));
            if (th != null) {
                throw new IOException("Unable to unmap the mapped buffer", th);
            }
        } else {
            throw new IllegalArgumentException("buffer is not an instance of " + cls.getName());
        }
    }
}
