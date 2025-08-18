package bsnl.bsnl_teevra.wizards.vsol2_classes;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ont_Configure_Class implements Serializable {
    private static final Pattern CODE_PATTERN = Pattern.compile("document\\.getElementById\\('check_code'\\)\\.value='(.*?)';", 40);
    private final Ont_Configure_Utils_Class objUtils = new Ont_Configure_Utils_Class();
    private String token = "";
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
        String str3 = this.url + "/boaform/admin/formLogin";
        String str4 = this.url + "/admin/login.asp";
        try {
            Document fetchDocument = fetchDocument(this.url);
            if (!isLoginPage(fetchDocument).booleanValue()) {
                return this.objUtils.generateSuccessResponse("Already Logged-in");
            }
            this.token = extractToken(fetchDocument);
            String extractCodeValue = extractCodeValue(fetchDocument);
            if (this.token != null) {
                if (extractCodeValue != null) {
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
                }
            }
            return this.objUtils.generateErrorResponse("Failed to extract tokens");
        } catch (IOException e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        } catch (Exception e2) {
            return this.objUtils.generateErrorResponse(e2.getMessage());
        }
    }

    public JSONObject logout() {
        HashMap hashMap = new HashMap();
        hashMap.put("csrftoken", this.token);
        Connection.Response postRequest = this.objUtils.postRequest(this.url + "/boaform/admin/formLogout", this.url + "/top.asp", hashMap);
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
    }

    public JSONObject reboot() {
        HashMap hashMap = new HashMap();
        hashMap.put("submit-url", "/mgm_dev_reboot.asp");
        hashMap.put("csrftoken", this.token);
        Connection.Response postRequest = this.objUtils.postRequest(this.url + "/boaform/admin/formReboot", this.url + "/mgm_dev_reboot.asp", hashMap);
        if (postRequest.statusCode() != 200) {
            return this.objUtils.generateErrorResponse(postRequest.statusMessage());
        }
        Document parseDocument = this.objUtils.parseDocument(postRequest);
        if (parseDocument == null) {
            return this.objUtils.generateErrorResponse("No response");
        }
        if (parseDocument.selectFirst("input[name=textname]") == null) {
            return this.objUtils.generateErrorResponse("Reboot Failed");
        }
        return this.objUtils.generateSuccessResponse("Reboot Success");
    }

    public JSONObject getTr69Parameters() {
        try {
            Element selectFirst = Jsoup.connect(this.url + "/net_tr069.asp").get().selectFirst("#tr069Ply");
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
        } catch (Exception e) {
            return this.objUtils.generateErrorResponse(e.getMessage());
        }
    }

    private Document fetchDocument(String str) throws IOException {
        return Jsoup.connect(str).get();
    }

    private Boolean isLoginPage(Document document) {
        return Boolean.valueOf(document.selectFirst("form[action='/boaform/admin/formLogin']") != null);
    }

    private String extractToken(Document document) {
        Element selectFirst = document.selectFirst("input[type=hidden][name=csrftoken]");
        if (selectFirst != null) {
            return selectFirst.attr("value");
        }
        return null;
    }

    private String extractCodeValue(Document document) {
        Matcher matcher = CODE_PATTERN.matcher(document.html());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private HashMap<String, String> createPayload(String str, String str2, String str3) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username1", str);
        hashMap.put("psd1", str2);
        hashMap.put("verification_code", str3);
        hashMap.put("username", str);
        hashMap.put("psd", str2);
        hashMap.put("sec_lang", "0");
        hashMap.put("loginSelinit", "");
        hashMap.put("ismobile", "0");
        hashMap.put("csrftoken", this.token);
        return hashMap;
    }
}
