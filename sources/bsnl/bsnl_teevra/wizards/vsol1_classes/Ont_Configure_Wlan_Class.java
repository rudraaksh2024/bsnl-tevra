package bsnl.bsnl_teevra.wizards.vsol1_classes;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ont_Configure_Wlan_Class implements Serializable {
    private final Pattern BAND2G5G_SUPPORT_PATTERN;
    private final HashMap<String, String> BAND_MAP;
    private final Pattern BAND_PATTERN;
    private final Pattern MSSID_BAND_PATTERN;
    private final Pattern MSSID_DISABLE_PATTERN;
    private final Pattern MSSID_SSID_PATTERN;
    private final Pattern SSID_PATTERN;
    private final Pattern WLAN_DISABLED_PATTERN;
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();

    public Ont_Configure_Wlan_Class() {
        HashMap<String, String> hashMap = new HashMap<>();
        this.BAND_MAP = hashMap;
        hashMap.put("1", "2.4 GHz (B)");
        hashMap.put(ExifInterface.GPS_MEASUREMENT_2D, "2.4 GHz (G)");
        hashMap.put(ExifInterface.GPS_MEASUREMENT_3D, "2.4 GHz (B+G)");
        hashMap.put("4", "5 GHz (A)");
        hashMap.put("10", "2.4 GHz (G+N)");
        hashMap.put("11", "2.4 GHz (B+G+N)");
        hashMap.put("12", "5 GHz (A+N)");
        hashMap.put("64", "5 GHz (AC)");
        hashMap.put("72", "5 GHz (N+AC)");
        hashMap.put("76", "5 GHz (A+N+AC)");
        this.WLAN_DISABLED_PATTERN = Pattern.compile("wlanDisabled\\[(\\d+)\\]\\=0\\;", 40);
        this.MSSID_DISABLE_PATTERN = Pattern.compile("mssid_disable\\[(\\d+)\\]\\[(\\d+)\\]\\=0\\;", 40);
        this.BAND_PATTERN = Pattern.compile("band\\[(\\d+)\\]\\=(\\d+)\\;", 40);
        this.BAND2G5G_SUPPORT_PATTERN = Pattern.compile("Band2G5GSupport\\[(\\d+)\\]\\=(\\d+)\\;", 40);
        this.SSID_PATTERN = Pattern.compile("ssid_drv\\[(\\d+)\\]\\=\\'(.*?)\\'\\;", 40);
        this.MSSID_BAND_PATTERN = Pattern.compile("mssid_band\\[(\\d+)\\]\\[(\\d+)\\]\\=(\\d+)\\;", 40);
        this.MSSID_SSID_PATTERN = Pattern.compile("mssid_ssid_drv\\[(\\d+)\\]\\[(\\d+)\\]\\=\\'(.*?)\\'\\;", 40);
    }

    private void extractWlanParameters(String str, JSONArray jSONArray) {
        Matcher matcher = this.WLAN_DISABLED_PATTERN.matcher(str);
        while (matcher.find()) {
            String trim = matcher.group(1).trim();
            JSONObject jSONObject = new JSONObject();
            extractBandInfo(str, trim, jSONObject);
            extractSsidInfo(str, trim, jSONObject);
            jSONArray.put(jSONObject);
        }
    }

    private void extractBandInfo(String str, String str2, JSONObject jSONObject) {
        String str3;
        String str4;
        Matcher matcher = this.BAND_PATTERN.matcher(str);
        Matcher matcher2 = this.BAND2G5G_SUPPORT_PATTERN.matcher(str);
        while (true) {
            str3 = null;
            if (matcher.find()) {
                if (matcher.group(1).equals(str2)) {
                    str4 = matcher.group(2).trim();
                    break;
                }
            } else {
                str4 = null;
                break;
            }
        }
        while (true) {
            if (matcher2.find()) {
                if (matcher2.group(1).equals(str2)) {
                    str3 = matcher2.group(2).trim();
                    break;
                }
            } else {
                break;
            }
        }
        if (str4 != null && str3 != null) {
            try {
                jSONObject.put("band", bandMap(str4, str3));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void extractSsidInfo(String str, String str2, JSONObject jSONObject) {
        Matcher matcher = this.SSID_PATTERN.matcher(str);
        while (matcher.find()) {
            if (matcher.group(1).equals(str2)) {
                try {
                    jSONObject.put("ssid", matcher.group(2).trim());
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void extractMssidParameters(String str, JSONArray jSONArray) {
        String str2;
        String str3;
        Matcher matcher = this.MSSID_DISABLE_PATTERN.matcher(str);
        while (matcher.find()) {
            String trim = matcher.group(1).trim();
            String trim2 = matcher.group(2).trim();
            JSONObject jSONObject = new JSONObject();
            Matcher matcher2 = this.MSSID_BAND_PATTERN.matcher(str);
            Matcher matcher3 = this.MSSID_SSID_PATTERN.matcher(str);
            Matcher matcher4 = this.BAND2G5G_SUPPORT_PATTERN.matcher(str);
            while (true) {
                str2 = null;
                if (matcher2.find()) {
                    if (matcher2.group(1).equals(trim) && matcher2.group(2).equals(trim2)) {
                        str3 = matcher2.group(3).trim();
                        break;
                    }
                } else {
                    str3 = null;
                    break;
                }
            }
            while (true) {
                if (matcher4.find()) {
                    if (matcher4.group(1).equals(trim)) {
                        str2 = matcher4.group(2).trim();
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!(str3 == null || str2 == null)) {
                try {
                    jSONObject.put("band", bandMap(str3, str2));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            while (true) {
                if (!matcher3.find()) {
                    break;
                } else if (matcher3.group(1).equals(trim) && matcher3.group(2).equals(trim2)) {
                    try {
                        jSONObject.put("ssid", matcher3.group(3).trim());
                        break;
                    } catch (JSONException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
            jSONArray.put(jSONObject);
        }
    }

    private JSONArray parseParameter(Element element) {
        JSONArray jSONArray = new JSONArray();
        String html = element.html();
        extractWlanParameters(html, jSONArray);
        extractMssidParameters(html, jSONArray);
        return jSONArray;
    }

    private String bandMap(String str, String str2) {
        if ("8".equals(str)) {
            return ExifInterface.GPS_MEASUREMENT_2D.equals(str2) ? "5 GHz (N)" : "2.4 GHz (N)";
        }
        return this.BAND_MAP.getOrDefault(str, "Unknown Band");
    }

    public JSONObject extractWlanParameter(String str) {
        try {
            Element selectFirst = Jsoup.connect(str + "/status_wlan_info_11n_en.asp").get().getElementsByTag("body").first().selectFirst("script");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("No data found");
            }
            JSONArray parseParameter = parseParameter(selectFirst);
            updatePassword(str, parseParameter);
            return this.objUtils.generateSuccessResponse(parseParameter);
        } catch (IOException e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        } catch (Exception e2) {
            return this.objUtils.generateErrorResponse(e2.getMessage());
        }
    }

    private void updatePassword(String str, JSONArray jSONArray) {
        String extractPasswordFromScript;
        try {
            Document document = Jsoup.connect(str + "/wlwpa_en.asp").get();
            Element selectFirst = document.getElementsByTag("body").first().selectFirst("script:not([src])");
            Elements select = document.select("select[name=wpaSSID] > option");
            if (selectFirst == null) {
                return;
            }
            if (!select.isEmpty()) {
                HashMap<String, String> createSsidValueMap = createSsidValueMap(select);
                String html = selectFirst.html();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String optString = jSONObject.optString("ssid");
                    if (createSsidValueMap.containsKey(optString) && (extractPasswordFromScript = extractPasswordFromScript(html, createSsidValueMap.get(optString))) != null) {
                        jSONObject.put("password", extractPasswordFromScript);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    private String extractPasswordFromScript(String str, String str2) {
        Matcher matcher = Pattern.compile("\\_wpaPSK\\[" + str2 + "\\]\\=\\'(.*?)\\'\\;", 32).matcher(str);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private HashMap<String, String> createSsidValueMap(Elements elements) {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            hashMap.put(element.text().trim(), element.val().trim());
        }
        return hashMap;
    }
}
