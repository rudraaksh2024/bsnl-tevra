package bsnl.bsnl_teevra.wizards.vsol1_classes;

import java.io.Serializable;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ont_Configure_Setting_Class implements Serializable {
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();

    public JSONObject configureInternet(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formEthernet", str + "/net_eth_links.asp", new Ont_Configure_Payload_Class(str, jSONObject.getString("action"), jSONObject.getString("interface")).ppoeInterfacePayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (Boolean.valueOf(parseDocument.selectFirst("form[action='/boaform/admin/formEthernet']") == null).booleanValue()) {
                return this.objUtils.generateErrorResponse("PPOE Failed");
            }
            return this.objUtils.generateSuccessResponse("PPOE Success");
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureBridge(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formEthernet", str + "/net_eth_links.asp", new Ont_Configure_Payload_Class(str, jSONObject.getString("action"), jSONObject.getString("interface")).bridgeInterfacePayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (Boolean.valueOf(parseDocument.selectFirst("form[action='/boaform/admin/formEthernet']") == null).booleanValue()) {
                return this.objUtils.generateErrorResponse("Bridge Failed");
            }
            return this.objUtils.generateSuccessResponse("Bridge Success");
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureWifiSsid(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formWlanSetup", str + "/wlbasic.asp", new Ont_Configure_Payload_Class().wifiSsidPayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            Element selectFirst = parseDocument.selectFirst("body>blockquote>h4");
            if (selectFirst == null || selectFirst.text().equals("Change setting successfully!")) {
                return this.objUtils.generateSuccessResponse("WIFI SSID Success");
            }
            return this.objUtils.generateErrorResponse(selectFirst.text());
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureWifiPassword(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formWlEncrypt", str + "/wlwpa.asp", new Ont_Configure_Payload_Class(str).wifiPasswordPayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            Element selectFirst = parseDocument.selectFirst("body>blockquote>h4");
            if (selectFirst == null || selectFirst.text().equals("Change setting successfully!")) {
                return this.objUtils.generateSuccessResponse("WIFI Password Success");
            }
            return this.objUtils.generateErrorResponse(selectFirst.text());
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureVoipInterface(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formEthernet", str + "/net_eth_links.asp", new Ont_Configure_Payload_Class(str, jSONObject.getString("action"), jSONObject.getString("interface")).voipInterfacePayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (Boolean.valueOf(parseDocument.selectFirst("form[action='/boaform/admin/formEthernet']") == null).booleanValue()) {
                return this.objUtils.generateErrorResponse("VOIP Interface Failed");
            }
            return this.objUtils.generateSuccessResponse("VOIP Interface Success");
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureVoipSetting(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/voip_e8c_set", str + "/app_voip.asp", new Ont_Configure_Payload_Class(str, jSONObject.getString("action"), jSONObject.getString("interface")).voipSettingPayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (Boolean.valueOf(parseDocument.selectFirst("form[action='/boaform/admin/voip_e8c_set']") == null).booleanValue()) {
                return this.objUtils.generateErrorResponse("VOIP Setting Failed");
            }
            return this.objUtils.generateSuccessResponse("VOIP Setting Success");
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    public JSONObject configureTr69Setting(String str, JSONObject jSONObject) {
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str + "/boaform/admin/formTR069Config", str + "/net_tr069.asp", new Ont_Configure_Payload_Class().tr69SettingPayload(jSONObject));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            Element selectFirst = parseDocument.selectFirst("body > blockquote > h4");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("Tr69 Setting Failed");
            }
            if (!selectFirst.text().equals("Set Success!")) {
                return this.objUtils.generateErrorResponse("Tr69 Setting Failed");
            }
            return this.objUtils.generateSuccessResponse("Tr69 Setting Success");
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }
}
