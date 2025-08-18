package bsnl.bsnl_teevra.wizards.vsol2_classes;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Ont_Configure_Utils_Class implements Serializable {
    public List<String> parseParameter(String str, String str2) {
        Matcher matcher = Pattern.compile(str, 40).matcher(str2);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public String extractValue(String str, String str2) {
        Matcher matcher = Pattern.compile("new it\\(\"" + str2 + "\",\\s*\"?([^\"]*?)\"?\\)", 40).matcher(str);
        if (matcher.find()) {
            return matcher.group(1).trim().replaceAll("^\"|\"$", "");
        }
        return "";
    }

    public String decodeBase64(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public JSONObject generateErrorResponse(String str) {
        try {
            return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, false).put("message", str);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject generateSuccessResponse(String str) {
        try {
            return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, true).put("message", str);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject generateSuccessResponse(JSONArray jSONArray) {
        try {
            return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, true).put("message", jSONArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject generateSuccessResponse(JSONObject jSONObject) {
        try {
            return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, true).put("message", jSONObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static int calculateContentLength(HashMap<String, String> hashMap) {
        int i = 0;
        boolean z = true;
        for (Map.Entry next : hashMap.entrySet()) {
            if (!z) {
                i++;
            }
            try {
                i += URLEncoder.encode((String) next.getKey(), StandardCharsets.UTF_8.name()).length() + URLEncoder.encode((String) next.getValue(), StandardCharsets.UTF_8.name()).length() + 1;
            } catch (UnsupportedEncodingException unused) {
            }
            z = false;
        }
        return i;
    }

    public Connection.Response postRequest(String str, String str2, HashMap<String, String> hashMap) {
        try {
            return Jsoup.connect(str).headers(new Ont_Configure_Payload_Class().headers(Integer.toString(calculateContentLength(hashMap)), str2)).data((Map<String, String>) hashMap).execute();
        } catch (IOException | Exception unused) {
            return null;
        }
    }

    public Document parseDocument(Connection.Response response) {
        try {
            return response.parse();
        } catch (IOException | Exception unused) {
            return null;
        }
    }
}
