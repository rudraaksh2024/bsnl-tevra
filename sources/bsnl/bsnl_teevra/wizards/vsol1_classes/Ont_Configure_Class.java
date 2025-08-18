package bsnl.bsnl_teevra.wizards.vsol1_classes;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ont_Configure_Class implements Serializable {
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();
    private String token = null;
    private final String url;

    public Ont_Configure_Class(String str) {
        this.url = str;
    }

    public String getToken() {
        return this.token;
    }

    public String fetchUrl() {
        return this.url;
    }

    public JSONObject login(String str, String str2) {
        String str3 = this.url + "/boaform/admin/formLogin_en";
        String str4 = this.url + "/admin/login_en.asp";
        try {
            Document fetchDocument = fetchDocument(this.url);
            if (!isLoginPage(fetchDocument).booleanValue()) {
                return this.objUtils.generateSuccessResponse("Already Logged-in");
            }
            this.token = extractToken(fetchDocument);
            String extractCodeValue = extractCodeValue(fetchDocument);
            if (extractCodeValue == null) {
                return this.objUtils.generateErrorResponse("Failed to extract tokens");
            }
            Connection.Response postRequest = this.objUtils.postRequest(str3, str4, createPayload(str, str2, extractCodeValue));
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            Element selectFirst = parseDocument.selectFirst("body>blockquote>form>table>tbody>tr:nth-child(1)>td>h4");
            if (selectFirst != null) {
                return this.objUtils.generateErrorResponse(selectFirst.text());
            }
            return this.objUtils.generateSuccessResponse("Login Success");
        } catch (IOException e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        } catch (Exception e2) {
            return this.objUtils.generateErrorResponse(e2.getMessage());
        }
    }

    public JSONObject logout() {
        String str = this.url + "/boaform/admin/formLogout";
        String str2 = this.url + "/top_en.asp";
        HashMap hashMap = new HashMap();
        hashMap.put("csrftoken", this.token);
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str, str2, hashMap);
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (!isLoginPage(parseDocument).booleanValue()) {
                return this.objUtils.generateErrorResponse("Logout Failed");
            }
            return this.objUtils.generateSuccessResponse("Logout Success");
        } catch (Exception unused) {
            return this.objUtils.generateErrorResponse("Logout Failed");
        }
    }

    private String extractToken(Document document) {
        Element selectFirst = document.selectFirst("input[type=hidden][name=csrftoken]");
        if (selectFirst != null) {
            return selectFirst.attr("value");
        }
        return null;
    }

    public JSONObject reboot() {
        String str = this.url + "/boaform/admin/formReboot";
        String str2 = this.url + "/mgm_dev_reboot_en.asp";
        HashMap hashMap = new HashMap();
        hashMap.put("submit-url", " /mgm_dev_reboot_en.asp");
        hashMap.put("csrftoken", this.token);
        try {
            Connection.Response postRequest = this.objUtils.postRequest(str, str2, hashMap);
            if (postRequest.statusCode() != 200) {
                return this.objUtils.generateErrorResponse(postRequest.statusMessage());
            }
            Document parseDocument = this.objUtils.parseDocument(postRequest);
            if (parseDocument == null) {
                return this.objUtils.generateErrorResponse("No response");
            }
            if (parseDocument.getElementById("cntdwn") == null) {
                return this.objUtils.generateErrorResponse("Reboot Failed");
            }
            return this.objUtils.generateSuccessResponse("Reboot Success");
        } catch (Exception unused) {
            return this.objUtils.generateErrorResponse("Reboot Failed");
        }
    }

    private Document fetchDocument(String str) throws IOException {
        return Jsoup.connect(str).get();
    }

    private Boolean isLoginPage(Document document) {
        return Boolean.valueOf(document.selectFirst("form[action='/boaform/admin/formLogin_en']") != null);
    }

    private String extractCodeValue(Document document) {
        Element elementById = document.getElementById("check_code");
        if (elementById != null) {
            return elementById.val();
        }
        return null;
    }

    private HashMap<String, String> createPayload(String str, String str2, String str3) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", str);
        hashMap.put("psd", str2);
        hashMap.put("verification_code", str3);
        hashMap.put("csrftoken", this.token);
        return hashMap;
    }

    public JSONObject getTr69Parameters() {
        try {
            Element selectFirst = Jsoup.connect(this.url + "/net_tr069_en.asp").get().selectFirst("#tr069Ply");
            if (selectFirst == null) {
                return this.objUtils.generateErrorResponse("No data found");
            }
            Element selectFirst2 = selectFirst.selectFirst("input[name=acsURL]");
            Element selectFirst3 = selectFirst.selectFirst("input[name=acsUser]");
            Element selectFirst4 = selectFirst.selectFirst("input[name=acsPwd]");
            Element selectFirst5 = selectFirst.selectFirst("input[name=informInterval]");
            JSONObject jSONObject = new JSONObject();
            if (selectFirst2 != null) {
                jSONObject.put("acsurl", selectFirst2.attr("value"));
            }
            if (selectFirst3 != null) {
                jSONObject.put("username", selectFirst3.attr("value"));
            }
            if (selectFirst4 != null) {
                jSONObject.put("password", selectFirst4.attr("value"));
            }
            if (selectFirst5 != null) {
                jSONObject.put("interval", selectFirst5.attr("value"));
            }
            return this.objUtils.generateSuccessResponse(jSONObject);
        } catch (Exception unused) {
            return this.objUtils.generateErrorResponse("TR069 Failed");
        }
    }
}
