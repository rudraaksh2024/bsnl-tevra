package bsnl.bsnl_teevra.wizards.vsol2_classes;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ont_Configure_Wlan_Class implements Serializable {
    private final Pattern BAND2G5G_SUPPORT_PATTERN = Pattern.compile("Band2G5GSupport\\[(\\d+)\\]\\=(\\d+)\\;", 40);
    private final HashMap<String, String> BAND_MAP;
    private final Pattern BAND_PATTERN = Pattern.compile("band\\[(\\d+)\\]\\=(\\d+)\\;", 40);
    private final Pattern MSSID_BAND_PATTERN = Pattern.compile("mssid_band\\[(\\d+)\\]\\[(\\d+)\\]\\=(\\d+)\\;", 40);
    private final Pattern MSSID_DISABLE_PATTERN = Pattern.compile("mssid_disable\\[(\\d+)\\]\\[(\\d+)\\]\\=0\\;", 40);
    private final Pattern MSSID_SSID_PATTERN = Pattern.compile("mssid_ssid_drv\\[(\\d+)\\]\\[(\\d+)\\]\\=\\'(.*?)\\'\\;", 40);
    private final Pattern SSID_PATTERN = Pattern.compile("ssid_drv\\[(\\d+)\\]\\=\\'(.*?)\\'\\;", 40);
    private final Pattern WLAN_DISABLED_PATTERN = Pattern.compile("wlanDisabled\\[(\\d+)\\]\\=0\\;", 40);
    private String baseUrl;

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
    }

    private void extractWlanParameters(String str, JSONArray jSONArray) {
        Matcher matcher = this.WLAN_DISABLED_PATTERN.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            JSONObject jSONObject = new JSONObject();
            extractBandInfo(str, group, jSONObject);
            extractSsidInfo(str, group, jSONObject);
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
                    str4 = matcher.group(2);
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
                    str3 = matcher2.group(2);
                    break;
                }
            } else {
                break;
            }
        }
        if (str4 != null && str3 != null) {
            try {
                jSONObject.put("band", bandMap(str4, str3));
            } catch (JSONException unused) {
            }
        }
    }

    private void extractSsidInfo(String str, String str2, JSONObject jSONObject) {
        Matcher matcher = this.SSID_PATTERN.matcher(str);
        while (matcher.find()) {
            if (matcher.group(1).equals(str2)) {
                try {
                    jSONObject.put("ssid", matcher.group(2));
                    return;
                } catch (JSONException unused) {
                    return;
                }
            }
        }
    }

    private void extractMssidParameters(String str, JSONArray jSONArray) {
        String str2;
        String str3;
        Matcher matcher = this.MSSID_DISABLE_PATTERN.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            JSONObject jSONObject = new JSONObject();
            Matcher matcher2 = this.MSSID_BAND_PATTERN.matcher(str);
            Matcher matcher3 = this.MSSID_SSID_PATTERN.matcher(str);
            Matcher matcher4 = this.BAND2G5G_SUPPORT_PATTERN.matcher(str);
            while (true) {
                str2 = null;
                if (matcher2.find()) {
                    if (matcher2.group(1).equals(group) && matcher2.group(2).equals(group2)) {
                        str3 = matcher2.group(3);
                        break;
                    }
                } else {
                    str3 = null;
                    break;
                }
            }
            while (true) {
                if (matcher4.find()) {
                    if (matcher4.group(1).equals(group)) {
                        str2 = matcher4.group(2);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!(str3 == null || str2 == null)) {
                try {
                    jSONObject.put("band", bandMap(str3, str2));
                } catch (JSONException unused) {
                }
            }
            while (true) {
                if (!matcher3.find()) {
                    break;
                } else if (matcher3.group(1).equals(group) && matcher3.group(2).equals(group2)) {
                    try {
                        jSONObject.put("ssid", matcher3.group(3));
                        break;
                    } catch (JSONException unused2) {
                    }
                }
            }
            jSONArray.put(jSONObject);
        }
    }

    private JSONArray parseParameter(Elements elements) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            String html = ((Element) it.next()).html();
            extractWlanParameters(html, jSONArray);
            extractMssidParameters(html, jSONArray);
        }
        return jSONArray;
    }

    private String bandMap(String str, String str2) {
        if ("8".equals(str)) {
            return ExifInterface.GPS_MEASUREMENT_2D.equals(str2) ? "5 GHz (N)" : "2.4 GHz (N)";
        }
        return this.BAND_MAP.getOrDefault(str, "Unknown Band");
    }

    public JSONObject extractWlanParameter(String str) {
        this.baseUrl = str;
        String str2 = str + "/status_wlan_info_11n.asp";
        Ont_Configure_Utils_Class ont_Configure_Utils_Class = new Ont_Configure_Utils_Class();
        try {
            Elements select = Jsoup.connect(str2).get().getElementsByClass("data_common data_common_notitle").select("script");
            if (select.isEmpty()) {
                return ont_Configure_Utils_Class.generateErrorResponse("No data found");
            }
            JSONArray parseParameter = parseParameter(select);
            updatePassword(parseParameter);
            return ont_Configure_Utils_Class.generateSuccessResponse(parseParameter);
        } catch (IOException e) {
            return ont_Configure_Utils_Class.generateErrorResponse(e.getMessage());
        } catch (Exception e2) {
            return ont_Configure_Utils_Class.generateErrorResponse(e2.getMessage());
        }
    }

    private void updatePassword(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("ssid");
                String optString2 = jSONObject.optString("band");
                jSONObject.put("password", extractWifiPassword(optString2, ssidValue(optString2, optString)));
            } catch (JSONException unused) {
            }
        }
    }

    private String ssidValue(String str, String str2) {
        int indexOf;
        try {
            Elements select = Jsoup.connect(String.format("%s?redirect-url=%s&wlan_idx=%s", new Object[]{this.baseUrl + "/boaform/formWlanRedirect", URLEncoder.encode("/wlwpa.asp", StandardCharsets.UTF_8.toString()), URLEncoder.encode(str.contains("5 GHz") ? "0" : "1", StandardCharsets.UTF_8.toString())})).get().select("select[name=wpaSSID] > option");
            HashMap hashMap = new HashMap(select.size());
            Iterator it = select.iterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                String text = element.text();
                if (!(text == null || (indexOf = text.indexOf(45)) == -1 || indexOf >= text.length() - 1)) {
                    hashMap.put(text.substring(indexOf + 1).trim(), element.attr("value"));
                }
            }
            return (String) hashMap.getOrDefault(str2, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private String extractWifiPassword(String str, String str2) {
        try {
            Elements select = Jsoup.connect(String.format("%s?redirect-url=%s&wlan_idx=%s", new Object[]{this.baseUrl + "/boaform/formWlanRedirect", URLEncoder.encode("/wlwpa.asp", StandardCharsets.UTF_8.toString()), URLEncoder.encode(str.contains("5 GHz") ? "0" : "1", StandardCharsets.UTF_8.toString())})).get().select("script:not([src])");
            if (select.isEmpty()) {
                return "No data found";
            }
            Pattern compile = Pattern.compile("\\_wpaPSK\\[" + str2 + "\\]\\=\\'(.*?)\\'\\;", 40);
            Iterator it = select.iterator();
            while (it.hasNext()) {
                Matcher matcher = compile.matcher(((Element) it.next()).html());
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
            return "";
        } catch (IOException e) {
            return e.getMessage();
        } catch (Exception e2) {
            return e2.getMessage();
        }
    }
}
