package bsnl.bsnl_teevra.wizards.vsol1_classes;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ont_Configure_Wan_Class implements Serializable {
    private static final Pattern INTERFACE_PATTERN = Pattern.compile("it_nr\\(\"([^\"]*?)\"\\s*,", 40);
    private final HashMap<String, String> ipModeMap = createIpModeMap();
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();

    private static HashMap<String, String> createIpModeMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("0", "BRIDGE");
        hashMap.put("1", "IPOE");
        hashMap.put(ExifInterface.GPS_MEASUREMENT_2D, "PPPOE");
        return hashMap;
    }

    private JSONArray parseWanParameter(List<String> list, String str) {
        JSONArray jSONArray = new JSONArray();
        JSONObject voipParams = getVoipParams(str);
        try {
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                String extractInterfaceName = extractInterfaceName(next);
                if (extractInterfaceName == null) {
                    break;
                }
                JSONObject jSONObject = new JSONObject();
                String extractValue = this.objUtils.extractValue(next, "cmode");
                String extractValue2 = this.objUtils.extractValue(next, "vid");
                String extractValue3 = this.objUtils.extractValue(next, "encodePppUserName");
                String extractValue4 = this.objUtils.extractValue(next, "pppPassword");
                jSONObject.put("interface", extractInterfaceName);
                jSONObject.put("vlan", extractValue2);
                if (extractValue.equals("1")) {
                    jSONObject.put("username", voipParams.getString("username"));
                    jSONObject.put("password", voipParams.getString("password"));
                    jSONObject.put("domain", voipParams.getString("domain"));
                } else {
                    jSONObject.put("username", this.objUtils.decodeBase64(extractValue3));
                    jSONObject.put("password", extractValue4);
                    jSONObject.put("domain", "");
                }
                jSONObject.put("protocol", this.ipModeMap.get(extractValue));
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Exception e) {
            e.printStackTrace();
            return jSONArray;
        }
    }

    private String extractInterfaceName(String str) {
        Matcher matcher = INTERFACE_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private JSONObject getVoipParams(String str) {
        String str2;
        String str3;
        String str4 = str + "/app_voip_en.asp";
        JSONObject jSONObject = new JSONObject();
        try {
            Document document = Jsoup.connect(str4).get();
            if (document == null) {
                return jSONObject;
            }
            Element first = document.getElementsByAttributeValue("name", "port1_login_id").first();
            Element first2 = document.getElementsByAttributeValue("name", "port1_password").first();
            Element first3 = document.getElementsByAttributeValue("name", "proxy0_domain_name").first();
            String str5 = "";
            if (first == null) {
                str2 = str5;
            } else {
                str2 = first.val();
            }
            if (first2 == null) {
                str3 = str5;
            } else {
                str3 = first2.val();
            }
            if (first3 != null) {
                str5 = first3.val();
            }
            jSONObject.put("username", str2);
            jSONObject.put("password", str3);
            jSONObject.put("domain", str5);
            return jSONObject;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return jSONObject;
        }
    }

    public JSONObject extractWanParameter(String str) {
        try {
            Element selectFirst = Jsoup.connect(str + "/net_eth_links_en.asp").get().selectFirst("script:not([src])");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("No data found");
            }
            return this.objUtils.generateSuccessResponse(parseWanParameter(this.objUtils.parseParameter("it_nr\\(.*?\\)\\;", selectFirst.data()), str));
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }
}
