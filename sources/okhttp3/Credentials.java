package okhttp3;

import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.ByteString;
import org.apache.logging.log4j.message.ParameterizedMessage;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return "Basic " + ByteString.encodeString(str + ParameterizedMessage.ERROR_MSG_SEPARATOR + str2, charset).base64();
    }
}
