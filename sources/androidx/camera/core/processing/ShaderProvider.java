package androidx.camera.core.processing;

public interface ShaderProvider {
    public static final ShaderProvider DEFAULT = new ShaderProvider() {
    };

    String createFragmentShader(String str, String str2) {
        return null;
    }
}
