package kotlin.io.path;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lkotlin/io/path/DefaultCopyActionContext;", "Lkotlin/io/path/CopyActionContext;", "()V", "copyToIgnoringExistingDirectory", "Lkotlin/io/path/CopyActionResult;", "Ljava/nio/file/Path;", "target", "followLinks", "", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PathRecursiveFunctions.kt */
final class DefaultCopyActionContext implements CopyActionContext {
    public static final DefaultCopyActionContext INSTANCE = new DefaultCopyActionContext();

    private DefaultCopyActionContext() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0036, code lost:
        if (java.nio.file.Files.isDirectory(r5, (java.nio.file.LinkOption[]) java.util.Arrays.copyOf(new java.nio.file.LinkOption[]{java.nio.file.LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlin.io.path.CopyActionResult copyToIgnoringExistingDirectory(java.nio.file.Path r4, java.nio.file.Path r5, boolean r6) {
        /*
            r3 = this;
            java.lang.String r3 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
            java.lang.String r3 = "target"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
            kotlin.io.path.LinkFollowing r3 = kotlin.io.path.LinkFollowing.INSTANCE
            java.nio.file.LinkOption[] r3 = r3.toLinkOptions(r6)
            int r6 = r3.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r3, r6)
            java.nio.file.LinkOption[] r6 = (java.nio.file.LinkOption[]) r6
            int r0 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r0)
            java.nio.file.LinkOption[] r6 = (java.nio.file.LinkOption[]) r6
            boolean r6 = java.nio.file.Files.isDirectory(r4, r6)
            if (r6 == 0) goto L_0x0038
            r6 = 1
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r6]
            r1 = 0
            java.nio.file.LinkOption r2 = java.nio.file.LinkOption.NOFOLLOW_LINKS
            r0[r1] = r2
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r0, r6)
            java.nio.file.LinkOption[] r6 = (java.nio.file.LinkOption[]) r6
            boolean r6 = java.nio.file.Files.isDirectory(r5, r6)
            if (r6 != 0) goto L_0x004f
        L_0x0038:
            int r6 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r6)
            java.nio.file.CopyOption[] r3 = (java.nio.file.CopyOption[]) r3
            int r6 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r6)
            java.nio.file.CopyOption[] r3 = (java.nio.file.CopyOption[]) r3
            java.nio.file.Path r3 = java.nio.file.Files.copy(r4, r5, r3)
            java.lang.String r4 = "copy(this, target, *options)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
        L_0x004f:
            kotlin.io.path.CopyActionResult r3 = kotlin.io.path.CopyActionResult.CONTINUE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.DefaultCopyActionContext.copyToIgnoringExistingDirectory(java.nio.file.Path, java.nio.file.Path, boolean):kotlin.io.path.CopyActionResult");
    }
}
