package bsnl.bsnl_teevra.wizards.vsol2_classes;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlinx.coroutines.DebugKt;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ont_Configure_Payload_Class implements Serializable {
    private final String baseUrl;
    private final String interfaceName;
    private final String interfaceType;
    private final Ont_Configure_Utils_Class objUtils;
    private final String submitUrlInterface;

    public Ont_Configure_Payload_Class(String str, String str2, String str3) {
        this.baseUrl = str;
        this.submitUrlInterface = str + "/net_eth_links.asp";
        this.objUtils = new Ont_Configure_Utils_Class();
        this.interfaceName = "rm".equals(str2) ? str3 : "";
        this.interfaceType = "rm".equals(str2) ? Integer.toString(Integer.parseInt(str3.split("_")[0]) - 1) : "new";
    }

    public Ont_Configure_Payload_Class() {
        this("", "", "");
    }

    public Ont_Configure_Payload_Class(String str) {
        this(str, "", "");
    }

    public HashMap<String, String> wifiSsidPayload(JSONObject jSONObject) {
        Object obj;
        HashMap<String, String> hashMap = new HashMap<>();
        String optString = jSONObject.optString("band");
        String optString2 = jSONObject.optString("ssid");
        String optString3 = jSONObject.optString("token");
        String str = optString.contains("5 GHz") ? "75" : "10";
        boolean contains = optString.contains("5 GHz");
        String str2 = ExifInterface.GPS_MEASUREMENT_2D;
        String str3 = contains ? str2 : "1";
        try {
            String str4 = optString.contains("5 GHz") ? "20" : "0";
            if (optString.contains("5 GHz")) {
                obj = "0";
            } else {
                obj = "1";
            }
            if (!optString.contains("5 GHz")) {
                str2 = "1";
            }
            hashMap.put("band", str);
            hashMap.put("mode", "0");
            hashMap.put("ssid", optString2);
            hashMap.put("chanwid", str3);
            hashMap.put("chan", str4);
            hashMap.put("txpower", "0");
            hashMap.put("wl_limitstanum", "0");
            hashMap.put("wl_stanum", "");
            hashMap.put("regdomain_demo", "15");
            hashMap.put("submit-url", "/admin/wlbasic.asp");
            hashMap.put("save", "Apply Changes");
            hashMap.put("basicrates", "15");
            hashMap.put("operrates", "4095");
            hashMap.put("wlan_idx", obj);
            hashMap.put("Band2G5GSupport", str2);
            hashMap.put("wlanBand2G5GSelect", "");
            hashMap.put("dfs_enable", "1");
            hashMap.put("csrftoken", optString3);
        } catch (Exception unused) {
        }
        return hashMap;
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

    public HashMap<String, String> wifiPasswordPayload(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject.optString("band");
            String optString2 = jSONObject.optString("ssid");
            String optString3 = jSONObject.optString("password");
            String optString4 = jSONObject.optString("token");
            String str = optString.contains("5 GHz") ? "0" : "1";
            String ssidValue = ssidValue(optString, optString2);
            hashMap.put("dotIEEE80211W", "0");
            hashMap.put("sha256", "0");
            hashMap.put("ciphersuite_a", "1");
            hashMap.put("wlanDisabled", "OFF");
            hashMap.put("isNmode", "1");
            hashMap.put("wpaSSID", ssidValue);
            hashMap.put("security_method", "6");
            hashMap.put("auth_type", "both");
            hashMap.put("wepEnabled", "ON");
            hashMap.put("length0", "1");
            hashMap.put("format0", "1");
            hashMap.put("key0", "*****");
            hashMap.put("wpaAuth", "psk");
            hashMap.put("ciphersuite_t", "1");
            hashMap.put("wpa2ciphersuite_t", "1");
            hashMap.put("wpa2ciphersuite_a", "1");
            hashMap.put("gk_rekey", "86400");
            hashMap.put("pskFormat", "0");
            hashMap.put("pskValue", optString3);
            hashMap.put("wapiPskFormat", "0");
            hashMap.put("wapiPskValue", "");
            hashMap.put("wepKeyLen", "wep64");
            hashMap.put("radiusIP", "0.0.0.0");
            hashMap.put("radiusPort", "1812");
            hashMap.put("radiusPass", "");
            hashMap.put("wapiASIP", "0.0.0.0");
            hashMap.put("wlan_idx", str);
            hashMap.put("submit-url", "/admin/wlwpa.asp");
            hashMap.put("save", "Apply Changes");
            hashMap.put("csrftoken", optString4);
            System.out.println(hashMap);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> ppoeInterfacePayload(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject2.optString("username");
            String optString2 = jSONObject2.optString("password");
            String optString3 = jSONObject2.optString("vlan");
            String optString4 = jSONObject2.optString("action");
            String optString5 = jSONObject2.optString("token");
            String encodeBase64 = this.objUtils.encodeBase64(optString);
            String encodeBase642 = this.objUtils.encodeBase64(optString2);
            hashMap.put("lkname", this.interfaceType);
            hashMap.put("lkmode", "1");
            hashMap.put("IpProtocolType", ExifInterface.GPS_MEASUREMENT_3D);
            hashMap.put("ipmode", ExifInterface.GPS_MEASUREMENT_2D);
            hashMap.put("PPPoEProxyMaxUser", "0");
            hashMap.put("napt", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("vlan", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("vid", optString3);
            hashMap.put("vprio", "1");
            hashMap.put("mtu", "1492");
            hashMap.put("pppUsername", "");
            hashMap.put("pppPassword", "");
            hashMap.put("pppServiceName", "Internet");
            hashMap.put("pppCtype", "0");
            hashMap.put("ipAddr", "0.0.0.0");
            hashMap.put("netMask", "0.0.0.0");
            hashMap.put("remoteIpAddr", "0.0.0.0");
            hashMap.put("dnsMode", "1");
            hashMap.put("v4dns1", "");
            hashMap.put("v4dns2", "");
            hashMap.put("applicationtype", "0");
            hashMap.put("chkpt", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("AddrMode", "1");
            hashMap.put("iapd", "ON");
            hashMap.put("dslite_aftr_mode", "0");
            hashMap.put("dslite_aftr_hostname", "::");
            hashMap.put("Ipv6Addr", "");
            hashMap.put("Ipv6PrefixLen", "");
            hashMap.put("Ipv6Gateway", "");
            hashMap.put("dnsv6Mode", "1");
            hashMap.put("Ipv6Dns1", "");
            hashMap.put("Ipv6Dns2", "");
            hashMap.put("cmode", ExifInterface.GPS_MEASUREMENT_2D);
            hashMap.put("ipDhcp", "0");
            hashMap.put("itfGroup", "529");
            hashMap.put("encodePppUserName", encodeBase64);
            hashMap.put("encodePppPassword", encodeBase642);
            hashMap.put("lst", this.interfaceName);
            hashMap.put("action", optString4);
            hashMap.put("submit-url", this.submitUrlInterface);
            hashMap.put("acnameflag", "none");
            hashMap.put("csrftoken", optString5);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> voipInterfacePayload(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject.optString("action");
            String optString2 = jSONObject.optString("token");
            String optString3 = jSONObject.optString("vlan");
            hashMap.put("lkname", this.interfaceType);
            hashMap.put("lkmode", "1");
            hashMap.put("IpProtocolType", ExifInterface.GPS_MEASUREMENT_3D);
            hashMap.put("ipmode", "0");
            hashMap.put("PPPoEProxyMaxUser", "0");
            hashMap.put("vlan", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("vid", optString3);
            hashMap.put("vprio", "1");
            hashMap.put("mtu", "1500");
            hashMap.put("pppUsername", "");
            hashMap.put("pppPassword", "");
            hashMap.put("pppServiceName", "");
            hashMap.put("pppCtype", "0");
            hashMap.put("ipAddr", "0.0.0.0");
            hashMap.put("netMask", "255.255.255.0");
            hashMap.put("remoteIpAddr", "0.0.0.0");
            hashMap.put("dnsMode", "1");
            hashMap.put("applicationtype", "4");
            hashMap.put("chkpt", "");
            hashMap.put("AddrMode", "1");
            hashMap.put("dslite_aftr_mode", "0");
            hashMap.put("dslite_aftr_hostname", "::");
            hashMap.put("Ipv6Addr", "");
            hashMap.put("Ipv6PrefixLen", "");
            hashMap.put("Ipv6Gateway", "");
            hashMap.put("dnsv6Mode", "1");
            hashMap.put("cmode", "1");
            hashMap.put("ipDhcp", "1");
            hashMap.put("itfGroup", "0");
            hashMap.put("encodePppUserName", "");
            hashMap.put("encodePppPassword", "");
            hashMap.put("lst", this.interfaceName);
            hashMap.put("action", optString);
            hashMap.put("submit-url", this.submitUrlInterface);
            hashMap.put("acnameflag", "none");
            hashMap.put("csrftoken", optString2);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> bridgeInterfacePayload(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject.optString("vlan");
            String optString2 = jSONObject.optString("action");
            String optString3 = jSONObject.optString("token");
            hashMap.put("lkname", this.interfaceType);
            hashMap.put("lkmode", "0");
            hashMap.put("IpProtocolType", ExifInterface.GPS_MEASUREMENT_3D);
            hashMap.put("ipmode", "0");
            hashMap.put("PPPoEProxyMaxUser", "0");
            hashMap.put("vlan", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("vid", optString);
            hashMap.put("vprio", "1");
            hashMap.put("mtu", "1500");
            hashMap.put("pppUsername", "");
            hashMap.put("pppPassword", "");
            hashMap.put("pppServiceName", "");
            hashMap.put("pppCtype", "0");
            hashMap.put("ipAddr", "0.0.0.0");
            hashMap.put("netMask", "0.0.0.0");
            hashMap.put("remoteIpAddr", "0.0.0.0");
            hashMap.put("dnsMode", "1");
            hashMap.put("v4dns1", "");
            hashMap.put("v4dns2", "");
            hashMap.put("applicationtype", "1");
            hashMap.put("disableLanDhcp", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("chkpt", "");
            hashMap.put("chkpt", DebugKt.DEBUG_PROPERTY_VALUE_ON);
            hashMap.put("AddrMode", "1");
            hashMap.put("iapd", "ON");
            hashMap.put("dslite_aftr_mode", "0");
            hashMap.put("dslite_aftr_hostname", "::");
            hashMap.put("Ipv6Addr", "");
            hashMap.put("Ipv6PrefixLen", "");
            hashMap.put("Ipv6Gateway", "");
            hashMap.put("dnsv6Mode", "1");
            hashMap.put("Ipv6Dns1", "");
            hashMap.put("Ipv6Dns2", "");
            hashMap.put("cmode", "0");
            hashMap.put("ipDhcp", "0");
            hashMap.put("itfGroup", ExifInterface.GPS_MEASUREMENT_2D);
            hashMap.put("encodePppUserName", "");
            hashMap.put("encodePppPassword", "");
            hashMap.put("lst", this.interfaceName);
            hashMap.put("action", optString2);
            hashMap.put("submit-url", this.submitUrlInterface);
            hashMap.put("acnameflag", "none");
            hashMap.put("csrftoken", optString3);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> voipSettingPayload(JSONObject jSONObject) {
        String str;
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject.optString("action");
            String optString2 = jSONObject.optString("token");
            String optString3 = jSONObject.optString("vlan");
            String optString4 = jSONObject.optString("domain");
            String optString5 = jSONObject.optString("password");
            String optString6 = jSONObject.optString("username");
            if (optString.equals("rm")) {
                optString4 = "";
                str = optString4;
                optString5 = str;
            } else {
                str = (optString3.equals("1849") ? new StringBuilder("%2B91").append(optString6) : new StringBuilder("91").append(optString6)).toString();
            }
            hashMap.put("max_voip_ports", "1");
            hashMap.put("servertype", "Soft Switch SIP");
            hashMap.put("register_addr", optString4);
            hashMap.put("register_port", "5060");
            hashMap.put("backupreg_addr", optString4);
            hashMap.put("backupreg_port", "5060");
            hashMap.put("proxy0_addr", optString4);
            hashMap.put("proxy0_port", "5060");
            hashMap.put("proxy0_obEnable", "enable");
            hashMap.put("proxy0_obAddr", optString4);
            hashMap.put("proxy0_obPort", "5060");
            hashMap.put("proxy0_domain_name", optString4);
            hashMap.put("proxy0_reg_expire", "3600");
            hashMap.put("proxy1_enable", "enable");
            hashMap.put("proxy1_addr", optString4);
            hashMap.put("proxy1_port", "5060");
            hashMap.put("proxy1_obEnable", "enable");
            hashMap.put("proxy1_obAddr", optString4);
            hashMap.put("proxy1_obPort", "5060");
            hashMap.put("proxy1_domain_name", optString4);
            hashMap.put("proxy1_reg_expire", "3600");
            hashMap.put("port1_account_enable", "enable");
            hashMap.put("port1_number", str);
            hashMap.put("port1_login_id", str);
            hashMap.put("port1_password", optString5);
            hashMap.put("submit-url", "/app_voip.asp");
            hashMap.put("csrftoken", optString2);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> tr69SettingPayload(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String optString = jSONObject.optString("acsurl");
            String optString2 = jSONObject.optString("interval");
            String optString3 = jSONObject.optString("username");
            String optString4 = jSONObject.optString("password");
            String optString5 = jSONObject.optString("token");
            hashMap.put("acsCwmpFlag", "1");
            hashMap.put("acsURL", optString);
            hashMap.put("acsUser", optString3);
            hashMap.put("acsPwd", optString4);
            hashMap.put("certauth", "0");
            hashMap.put("inform", "1");
            hashMap.put("informInterval", optString2);
            hashMap.put("connReqUser", "");
            hashMap.put("connReqPwd", "");
            hashMap.put("stun_enable", "0");
            hashMap.put("stunsvraddr", "");
            hashMap.put("stunsvrport", "");
            hashMap.put("stunsvruname", "");
            hashMap.put("stunsvrupasswd", "");
            hashMap.put("applyTr069Config", "applyTr069Config");
            hashMap.put("action", "sv");
            hashMap.put("submit-url", "/net_tr069.asp");
            hashMap.put("csrftoken", optString5);
        } catch (Exception unused) {
        }
        return hashMap;
    }

    public HashMap<String, String> headers(String str, String str2) {
        String str3;
        String str4;
        HashMap<String, String> hashMap = new HashMap<>();
        Matcher matcher = Pattern.compile("(https?)://([^/]+)").matcher(str2);
        if (matcher.find()) {
            String group = matcher.group(1);
            str4 = matcher.group(2);
            str3 = group + "://" + str4;
        } else {
            str4 = "192.168.1.1";
            str3 = "http://192.168.1.1";
        }
        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        hashMap.put("Accept-Encoding", "gzip, deflate");
        hashMap.put("Accept-Language", "en-US,en;q=0.9");
        hashMap.put("Cache-Control", "max-age=0");
        hashMap.put("Connection", "keep-alive");
        hashMap.put("Content-Length", str);
        hashMap.put("Content-Type", HttpConnection.FORM_URL_ENCODED);
        hashMap.put("Host", str4);
        hashMap.put("Origin", str3);
        hashMap.put("Referer", str2);
        hashMap.put("Upgrade-Insecure-Requests", "1");
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        return hashMap;
    }
}
