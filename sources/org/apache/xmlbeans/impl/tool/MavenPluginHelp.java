package org.apache.xmlbeans.impl.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.Chars;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MavenPluginHelp extends AbstractMojo {
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static final String PLUGIN_HELP_PATH = "/META-INF/maven/plugin.xml";
    private boolean detail;
    private String goal;
    private int indentSize;
    private int lineLength;

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r2 != null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.w3c.dom.Document build() throws org.apache.maven.plugin.MojoExecutionException {
        /*
            r2 = this;
            org.apache.maven.plugin.logging.Log r0 = r2.getLog()
            java.lang.String r1 = "load plugin-help.xml: /META-INF/maven/plugin.xml"
            r0.debug(r1)
            java.lang.Class r2 = r2.getClass()     // Catch:{ IOException | SAXException -> 0x0030 }
            java.lang.String r0 = "/META-INF/maven/plugin.xml"
            java.io.InputStream r2 = r2.getResourceAsStream(r0)     // Catch:{ IOException | SAXException -> 0x0030 }
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions     // Catch:{ all -> 0x0022 }
            r0.<init>()     // Catch:{ all -> 0x0022 }
            org.w3c.dom.Document r0 = org.apache.xmlbeans.impl.common.DocumentHelper.readDocument((org.apache.xmlbeans.XmlOptions) r0, (java.io.InputStream) r2)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x0021
            r2.close()     // Catch:{ IOException | SAXException -> 0x0030 }
        L_0x0021:
            return r0
        L_0x0022:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r1 = move-exception
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ all -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ IOException | SAXException -> 0x0030 }
        L_0x002f:
            throw r1     // Catch:{ IOException | SAXException -> 0x0030 }
        L_0x0030:
            r2 = move-exception
            org.apache.maven.plugin.MojoExecutionException r0 = new org.apache.maven.plugin.MojoExecutionException
            java.lang.String r1 = r2.getMessage()
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.MavenPluginHelp.build():org.w3c.dom.Document");
    }

    public void execute() throws MojoExecutionException {
        if (this.lineLength <= 0) {
            getLog().warn("The parameter 'lineLength' should be positive, using '80' as default.");
            this.lineLength = 80;
        }
        if (this.indentSize <= 0) {
            getLog().warn("The parameter 'indentSize' should be positive, using '2' as default.");
            this.indentSize = 2;
        }
        Document build = build();
        StringBuilder sb = new StringBuilder();
        Node singleChild = getSingleChild(build, "plugin");
        String value = getValue(singleChild, "name");
        String value2 = getValue(singleChild, "version");
        String str = getValue(singleChild, "groupId") + ParameterizedMessage.ERROR_MSG_SEPARATOR + getValue(singleChild, "artifactId") + ParameterizedMessage.ERROR_MSG_SEPARATOR + value2;
        if (isNotEmpty(value) && !value.contains(str)) {
            append(sb, value + " " + value2, 0);
        } else if (isNotEmpty(value)) {
            append(sb, value, 0);
        } else {
            append(sb, str, 0);
        }
        append(sb, getValue(singleChild, "description"), 1);
        append(sb, "", 0);
        String value3 = getValue(singleChild, "goalPrefix");
        List<Node> findNamedChild = findNamedChild(getSingleChild(singleChild, "mojos"), "mojo");
        String str2 = this.goal;
        if (str2 == null || str2.length() <= 0) {
            append(sb, "This plugin has " + findNamedChild.size() + (findNamedChild.size() > 1 ? " goals:" : " goal:"), 0);
            append(sb, "", 0);
        }
        Iterator<Node> it = findNamedChild.iterator();
        while (it.hasNext()) {
            writeGoal(sb, value3, (Element) it.next());
        }
        if (getLog().isInfoEnabled()) {
            getLog().info(sb.toString());
        }
    }

    private static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    private String getValue(Node node, String str) throws MojoExecutionException {
        return getSingleChild(node, str).getTextContent();
    }

    private Node getSingleChild(Node node, String str) throws MojoExecutionException {
        List<Node> findNamedChild = findNamedChild(node, str);
        if (findNamedChild.isEmpty()) {
            throw new MojoExecutionException("Could not find " + str + " in plugin.xml");
        } else if (findNamedChild.size() <= 1) {
            return findNamedChild.get(0);
        } else {
            throw new MojoExecutionException("Multiple " + str + " in plugin.xml");
        }
    }

    private List<Node> findNamedChild(Node node, String str) {
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (str.equals(item.getNodeName())) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    private Node findSingleChild(Node node, String str) throws MojoExecutionException {
        List<Node> findNamedChild = findNamedChild(node, str);
        if (findNamedChild.isEmpty()) {
            return null;
        }
        if (findNamedChild.size() <= 1) {
            return findNamedChild.get(0);
        }
        throw new MojoExecutionException("Multiple " + str + "in plugin.xml");
    }

    private void writeGoal(StringBuilder sb, String str, Element element) throws MojoExecutionException {
        String value = getValue(element, "goal");
        Node findSingleChild = findSingleChild(element, "configuration");
        Node findSingleChild2 = findSingleChild(element, "description");
        String str2 = this.goal;
        if (str2 == null || str2.length() <= 0 || value.equals(this.goal)) {
            append(sb, str + ParameterizedMessage.ERROR_MSG_SEPARATOR + value, 0);
            Node findSingleChild3 = findSingleChild(element, "deprecated");
            if (findSingleChild3 != null && isNotEmpty(findSingleChild3.getTextContent())) {
                append(sb, "Deprecated. " + findSingleChild3.getTextContent(), 1);
                if (this.detail && findSingleChild2 != null) {
                    append(sb, "", 0);
                    append(sb, findSingleChild2.getTextContent(), 1);
                }
            } else if (findSingleChild2 != null) {
                append(sb, findSingleChild2.getTextContent(), 1);
            }
            append(sb, "", 0);
            if (this.detail) {
                List<Node> findNamedChild = findNamedChild(getSingleChild(element, "parameters"), "parameter");
                append(sb, "Available parameters:", 1);
                append(sb, "", 0);
                for (Node writeParameter : findNamedChild) {
                    writeParameter(sb, writeParameter, findSingleChild);
                }
            }
        }
    }

    private void writeParameter(StringBuilder sb, Node node, Node node2) throws MojoExecutionException {
        String str;
        String value = getValue(node, "name");
        String value2 = getValue(node, "description");
        Element element = node2 != null ? (Element) findSingleChild(node2, value) : null;
        if (element == null || !element.hasAttribute("default-value")) {
            str = "";
        } else {
            str = " (Default: " + element.getAttribute("default-value") + ")";
        }
        append(sb, value + str, 2);
        Node findSingleChild = findSingleChild(node, "deprecated");
        if (findSingleChild != null && isNotEmpty(findSingleChild.getTextContent())) {
            append(sb, "Deprecated. " + findSingleChild.getTextContent(), 3);
            append(sb, "", 0);
        }
        append(sb, value2, 3);
        if ("true".equals(getValue(node, "required"))) {
            append(sb, "Required: Yes", 3);
        }
        if (element != null && isNotEmpty(element.getTextContent())) {
            append(sb, "User property: " + getPropertyFromExpression(element.getTextContent()), 3);
        }
        append(sb, "", 0);
    }

    private static String repeat(String str, int i) {
        StringBuilder sb = new StringBuilder(str.length() * i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(str);
        }
        return sb.toString();
    }

    private void append(StringBuilder sb, String str, int i) {
        for (String append : toLines(str, i, this.indentSize, this.lineLength)) {
            sb.append(append).append(10);
        }
    }

    private static List<String> toLines(String str, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        String repeat = repeat("\t", i);
        String[] split = str.split("(\r\n)|(\r)|(\n)");
        int length = split.length;
        for (int i4 = 0; i4 < length; i4++) {
            toLines((List<String>) arrayList, repeat + split[i4], i2, i3);
        }
        return arrayList;
    }

    private static void toLines(List<String> list, String str, int i, int i2) {
        int indentLevel = getIndentLevel(str);
        StringBuilder sb = new StringBuilder(256);
        for (String str2 : str.split(" +")) {
            if (sb.length() > 0) {
                if (sb.length() + str2.length() >= i2) {
                    list.add(sb.toString());
                    sb.setLength(0);
                    sb.append(repeat(" ", indentLevel * i));
                } else {
                    sb.append(Chars.SPACE);
                }
            }
            for (int i3 = 0; i3 < str2.length(); i3++) {
                char charAt = str2.charAt(i3);
                if (charAt == 9) {
                    sb.append(repeat(" ", i - (sb.length() % i)));
                } else if (charAt == 160) {
                    sb.append(Chars.SPACE);
                } else {
                    sb.append(charAt);
                }
            }
        }
        list.add(sb.toString());
    }

    private static int getIndentLevel(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length() && str.charAt(i) == 9) {
            i2++;
            i++;
        }
        int i3 = i2 + 1;
        int i4 = i3;
        while (i4 <= i2 + 4 && i4 < str.length()) {
            if (str.charAt(i4) == 9) {
                return i3;
            }
            i4++;
        }
        return i2;
    }

    private String getPropertyFromExpression(String str) {
        if (str == null || !str.startsWith("${") || !str.endsWith(VectorFormat.DEFAULT_SUFFIX) || str.substring(2).contains("${")) {
            return null;
        }
        return str.substring(2, str.length() - 1);
    }
}
