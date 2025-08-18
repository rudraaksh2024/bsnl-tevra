package bsnl.bsnl_teevra.wizards.vsol1_classes;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ont_Configure_Network_Status_Class implements Serializable {
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();

    public JSONObject extractVoipInfo(String str) {
        String str2 = str + "/status_voip_info_en.asp";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("number", "");
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, "");
            Elements select = Jsoup.connect(str2).get().select("body > blockquote > div > table > tbody > tr > td");
            if (select.isEmpty()) {
                return this.objUtils.generateErrorResponse(jSONObject);
            }
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, ((Element) select.get(1)).text());
            jSONObject.put("number", ((Element) select.get(3)).text());
            return this.objUtils.generateSuccessResponse(jSONObject);
        } catch (IOException unused) {
            return this.objUtils.generateErrorResponse(jSONObject);
        } catch (Exception unused2) {
            return this.objUtils.generateErrorResponse(jSONObject);
        }
    }

    public JSONObject extractWanInfo(String str) {
        try {
            Element selectFirst = Jsoup.connect(str + "/status_net_connet_info_en.asp").get().selectFirst("script:not([src])");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("No data found");
            }
            return this.objUtils.generateSuccessResponse(parseWanInfo(this.objUtils.parseParameter("it_nr\\(.*?\\)\\;", selectFirst.html())));
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    private JSONArray parseWanInfo(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String next : list) {
            JSONObject jSONObject = new JSONObject();
            String extractValue = this.objUtils.extractValue(next, "servName");
            String extractValue2 = this.objUtils.extractValue(next, "protocol");
            String extractValue3 = this.objUtils.extractValue(next, "vlanId");
            String extractValue4 = this.objUtils.extractValue(next, "strStatus");
            String extractValue5 = this.objUtils.extractValue(next, "ipAddr");
            String extractValue6 = this.objUtils.extractValue(next, "gateway");
            String extractValue7 = this.objUtils.extractValue(next, "MacAddr");
            try {
                jSONObject.put("interface", extractValue);
                jSONObject.put("protocol", extractValue2);
                jSONObject.put("vlan", extractValue3);
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, extractValue4);
                jSONObject.put("ip", extractValue5);
                jSONObject.put("gateway", extractValue6);
                jSONObject.put("mac", extractValue7);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return jSONArray;
    }
}
