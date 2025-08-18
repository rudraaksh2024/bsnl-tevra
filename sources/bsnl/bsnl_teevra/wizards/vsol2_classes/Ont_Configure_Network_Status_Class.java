package bsnl.bsnl_teevra.wizards.vsol2_classes;

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

    public JSONObject extractVoipInfo(String str) {
        try {
            Elements select = Jsoup.connect(str + "/status_voip_info.asp").get().select("body>blockquote>div.data_common.data_common_notitle>table>tbody>tr");
            select.isEmpty();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, ((Element) select.get(0)).selectFirst("td").text());
            jSONObject.put("number", ((Element) select.get(1)).selectFirst("td").text());
            return this.objUtils.generateSuccessResponse(jSONObject);
        } catch (IOException unused) {
            return new JSONObject();
        } catch (Exception unused2) {
            return new JSONObject();
        }
    }

    public JSONObject extractWanInfo(String str) {
        try {
            Element selectFirst = Jsoup.connect(str + "/status_net_connet_info.asp").get().selectFirst("script:not([src])");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("No data found");
            }
            return this.objUtils.generateSuccessResponse(parseWanInfo(this.objUtils.parseParameter("it_nr\\(.*?\\)\\;", selectFirst.data())));
        } catch (IOException e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        } catch (Exception e2) {
            return this.objUtils.generateErrorResponse(e2.getMessage());
        }
    }
}
