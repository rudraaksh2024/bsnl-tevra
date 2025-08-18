package com.graphbuilder.math;

import com.graphbuilder.struc.Stack;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

public class ExpressionTree {
    private ExpressionTree() {
    }

    public static Expression parse(String str) {
        if (str != null) {
            return build(str, 0);
        }
        throw new ExpressionParseException("Expression string cannot be null.", -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cb, code lost:
        if (r4 <= '9') goto L_0x00bd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.graphbuilder.math.Expression build(java.lang.String r16, int r17) {
        /*
            r0 = r16
            java.lang.String r1 = r16.trim()
            int r1 = r1.length()
            if (r1 != 0) goto L_0x000e
            r0 = 0
            return r0
        L_0x000e:
            com.graphbuilder.struc.Stack r1 = new com.graphbuilder.struc.Stack
            r1.<init>()
            com.graphbuilder.struc.Stack r2 = new com.graphbuilder.struc.Stack
            r2.<init>()
            r5 = 0
            r6 = 1
            r7 = 0
            r8 = 0
        L_0x001c:
            int r9 = r16.length()
            if (r5 >= r9) goto L_0x027b
            char r9 = r0.charAt(r5)
            r10 = 32
            if (r9 == r10) goto L_0x0277
            r11 = 9
            if (r9 == r11) goto L_0x0277
            r12 = 10
            if (r9 != r12) goto L_0x0034
            goto L_0x0277
        L_0x0034:
            java.lang.String r13 = "("
            r14 = 47
            r15 = 42
            r3 = 94
            r4 = 41
            r12 = 43
            r11 = 45
            if (r6 == 0) goto L_0x0207
            r10 = 40
            if (r9 != r10) goto L_0x0057
            if (r8 != 0) goto L_0x004f
            r2.push(r13)
            goto L_0x0277
        L_0x004f:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.String r1 = "Open bracket found after negate."
            r0.<init>(r1, r5)
            throw r0
        L_0x0057:
            if (r7 != 0) goto L_0x0064
            if (r9 == r12) goto L_0x005d
            if (r9 != r11) goto L_0x0064
        L_0x005d:
            r3 = 1
            r7 = 1
            if (r9 != r11) goto L_0x0278
            r8 = 1
            goto L_0x0278
        L_0x0064:
            r6 = 46
            r7 = 57
            r13 = 48
            if (r9 < r13) goto L_0x006e
            if (r9 <= r7) goto L_0x0070
        L_0x006e:
            if (r9 != r6) goto L_0x00ff
        L_0x0070:
            int r3 = r5 + 1
        L_0x0072:
            int r4 = r16.length()
            if (r3 >= r4) goto L_0x00cd
            char r4 = r0.charAt(r3)
            if (r4 < r13) goto L_0x0080
            if (r4 <= r7) goto L_0x0082
        L_0x0080:
            if (r4 != r6) goto L_0x0085
        L_0x0082:
            int r3 = r3 + 1
            goto L_0x0072
        L_0x0085:
            r6 = 101(0x65, float:1.42E-43)
            if (r4 == r6) goto L_0x008d
            r6 = 69
            if (r4 != r6) goto L_0x00cd
        L_0x008d:
            int r3 = r3 + 1
            int r4 = r16.length()
            if (r3 >= r4) goto L_0x00bf
            char r4 = r0.charAt(r3)
            if (r4 == r12) goto L_0x00bd
            if (r4 == r11) goto L_0x00bd
            if (r4 < r13) goto L_0x00a2
            if (r4 > r7) goto L_0x00a2
            goto L_0x00bd
        L_0x00a2:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected digit, plus sign or minus sign but found: "
            r1.<init>(r2)
            java.lang.String r2 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r3 = r3 + r17
            r0.<init>(r1, r3)
            throw r0
        L_0x00bd:
            int r3 = r3 + 1
        L_0x00bf:
            int r4 = r16.length()
            if (r3 >= r4) goto L_0x00cd
            char r4 = r0.charAt(r3)
            if (r4 < r13) goto L_0x00cd
            if (r4 <= r7) goto L_0x00bd
        L_0x00cd:
            java.lang.String r4 = r0.substring(r5, r3)
            double r4 = java.lang.Double.parseDouble(r4)     // Catch:{ all -> 0x00e8 }
            if (r8 == 0) goto L_0x00d8
            double r4 = -r4
        L_0x00d8:
            com.graphbuilder.math.ValNode r6 = new com.graphbuilder.math.ValNode
            r6.<init>(r4)
            r1.push(r6)
            int r5 = r3 + -1
        L_0x00e2:
            r3 = 1
            r6 = 0
            r7 = 0
            r8 = 0
            goto L_0x0278
        L_0x00e8:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Improperly formatted value: "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            int r5 = r5 + r17
            r0.<init>(r1, r5)
            throw r0
        L_0x00ff:
            r6 = 44
            if (r9 == r6) goto L_0x01ec
            if (r9 == r4) goto L_0x01ec
            if (r9 == r3) goto L_0x01ec
            if (r9 == r15) goto L_0x01ec
            if (r9 == r14) goto L_0x01ec
            if (r9 == r12) goto L_0x01ec
            if (r9 == r11) goto L_0x01ec
            int r7 = r5 + 1
        L_0x0111:
            int r13 = r16.length()
            if (r7 >= r13) goto L_0x013a
            char r9 = r0.charAt(r7)
            if (r9 == r6) goto L_0x013a
            r13 = 32
            if (r9 == r13) goto L_0x013a
            r13 = 9
            if (r9 == r13) goto L_0x013a
            r13 = 10
            if (r9 == r13) goto L_0x013a
            if (r9 == r10) goto L_0x013a
            if (r9 == r4) goto L_0x013a
            if (r9 == r3) goto L_0x013a
            if (r9 == r15) goto L_0x013a
            if (r9 == r14) goto L_0x013a
            if (r9 == r12) goto L_0x013a
            if (r9 == r11) goto L_0x013a
            int r7 = r7 + 1
            goto L_0x0111
        L_0x013a:
            int r3 = r16.length()
            if (r7 >= r3) goto L_0x01db
            r3 = r7
            r11 = 32
        L_0x0143:
            r12 = 9
            r13 = 10
            if (r9 == r11) goto L_0x014d
            if (r9 == r12) goto L_0x014d
            if (r9 != r13) goto L_0x0155
        L_0x014d:
            int r3 = r3 + 1
            int r14 = r16.length()
            if (r3 != r14) goto L_0x01d5
        L_0x0155:
            if (r9 != r10) goto L_0x01c4
            com.graphbuilder.math.FuncNode r9 = new com.graphbuilder.math.FuncNode
            java.lang.String r7 = r0.substring(r5, r7)
            r9.<init>(r7, r8)
            int r7 = r3 + 1
            r8 = 1
        L_0x0163:
            java.lang.String r11 = "Incomplete function."
            if (r8 == 0) goto L_0x01a4
            int r3 = r3 + 1
            int r12 = r16.length()
            if (r3 >= r12) goto L_0x019a
            char r12 = r0.charAt(r3)
            if (r12 != r4) goto L_0x0178
            int r8 = r8 + -1
            goto L_0x0163
        L_0x0178:
            if (r12 != r10) goto L_0x017d
            int r8 = r8 + 1
            goto L_0x0163
        L_0x017d:
            if (r12 != r6) goto L_0x0163
            r12 = 1
            if (r8 != r12) goto L_0x0163
            java.lang.String r12 = r0.substring(r7, r3)
            com.graphbuilder.math.Expression r12 = build((java.lang.String) r12, (int) r7)
            if (r12 == 0) goto L_0x0192
            r9.add(r12)
            int r7 = r3 + 1
            goto L_0x0163
        L_0x0192:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            int r7 = r7 + r17
            r0.<init>(r11, r7)
            throw r0
        L_0x019a:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.String r1 = "Missing function close bracket."
            int r5 = r5 + r17
            r0.<init>(r1, r5)
            throw r0
        L_0x01a4:
            java.lang.String r4 = r0.substring(r7, r3)
            com.graphbuilder.math.Expression r4 = build((java.lang.String) r4, (int) r7)
            if (r4 != 0) goto L_0x01bd
            int r4 = r9.numChildren()
            if (r4 > 0) goto L_0x01b5
            goto L_0x01c0
        L_0x01b5:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            int r7 = r7 + r17
            r0.<init>(r11, r7)
            throw r0
        L_0x01bd:
            r9.add(r4)
        L_0x01c0:
            r1.push(r9)
            goto L_0x01d2
        L_0x01c4:
            com.graphbuilder.math.VarNode r4 = new com.graphbuilder.math.VarNode
            java.lang.String r5 = r0.substring(r5, r7)
            r4.<init>(r5, r8)
            r1.push(r4)
            int r3 = r3 + -1
        L_0x01d2:
            r5 = r3
            goto L_0x00e2
        L_0x01d5:
            char r9 = r0.charAt(r3)
            goto L_0x0143
        L_0x01db:
            com.graphbuilder.math.VarNode r3 = new com.graphbuilder.math.VarNode
            java.lang.String r4 = r0.substring(r5, r7)
            r3.<init>(r4, r8)
            r1.push(r3)
            int r7 = r7 + -1
            r5 = r7
            goto L_0x00e2
        L_0x01ec:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected character: "
            r1.<init>(r2)
            java.lang.String r2 = java.lang.String.valueOf(r9)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r5 = r5 + r17
            r0.<init>(r1, r5)
            throw r0
        L_0x0207:
            if (r9 != r4) goto L_0x0247
            com.graphbuilder.struc.Stack r3 = new com.graphbuilder.struc.Stack
            r3.<init>()
            com.graphbuilder.struc.Stack r4 = new com.graphbuilder.struc.Stack
            r4.<init>()
        L_0x0213:
            boolean r9 = r2.isEmpty()
            if (r9 != 0) goto L_0x023d
            java.lang.Object r9 = r2.pop()
            boolean r10 = r9.equals(r13)
            if (r10 == 0) goto L_0x0232
            java.lang.Object r9 = r1.pop()
            r3.addToTail(r9)
            com.graphbuilder.math.Expression r3 = build((com.graphbuilder.struc.Stack) r3, (com.graphbuilder.struc.Stack) r4)
            r1.push(r3)
            goto L_0x0277
        L_0x0232:
            java.lang.Object r10 = r1.pop()
            r3.addToTail(r10)
            r4.addToTail(r9)
            goto L_0x0213
        L_0x023d:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.String r1 = "Missing open bracket."
            int r5 = r5 + r17
            r0.<init>(r1, r5)
            throw r0
        L_0x0247:
            if (r9 == r3) goto L_0x026d
            if (r9 == r15) goto L_0x026d
            if (r9 == r14) goto L_0x026d
            if (r9 == r12) goto L_0x026d
            if (r9 != r11) goto L_0x0252
            goto L_0x026d
        L_0x0252:
            com.graphbuilder.math.ExpressionParseException r0 = new com.graphbuilder.math.ExpressionParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected operator or close bracket but found: "
            r1.<init>(r2)
            java.lang.String r2 = java.lang.String.valueOf(r9)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            int r5 = r5 + r17
            r0.<init>(r1, r5)
            throw r0
        L_0x026d:
            java.lang.String r3 = java.lang.String.valueOf(r9)
            r2.push(r3)
            r3 = 1
            r6 = 1
            goto L_0x0278
        L_0x0277:
            r3 = 1
        L_0x0278:
            int r5 = r5 + r3
            goto L_0x001c
        L_0x027b:
            r3 = 1
            int r4 = r1.size()
            int r5 = r2.size()
            int r5 = r5 + r3
            if (r4 != r5) goto L_0x028c
            com.graphbuilder.math.Expression r0 = build((com.graphbuilder.struc.Stack) r1, (com.graphbuilder.struc.Stack) r2)
            return r0
        L_0x028c:
            com.graphbuilder.math.ExpressionParseException r1 = new com.graphbuilder.math.ExpressionParseException
            int r0 = r16.length()
            int r0 = r17 + r0
            java.lang.String r2 = "Incomplete expression."
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.math.ExpressionTree.build(java.lang.String, int):com.graphbuilder.math.Expression");
    }

    private static Expression build(Stack stack, Stack stack2) {
        Stack stack3 = new Stack();
        Stack stack4 = new Stack();
        while (!stack2.isEmpty()) {
            Object removeTail = stack2.removeTail();
            Object removeTail2 = stack.removeTail();
            Object removeTail3 = stack.removeTail();
            if (removeTail.equals("^")) {
                stack.addToTail(new PowNode((Expression) removeTail2, (Expression) removeTail3));
            } else {
                stack.addToTail(removeTail3);
                stack4.push(removeTail);
                stack3.push(removeTail2);
            }
        }
        stack3.push(stack.pop());
        while (!stack4.isEmpty()) {
            Object removeTail4 = stack4.removeTail();
            Object removeTail5 = stack3.removeTail();
            Object removeTail6 = stack3.removeTail();
            if (removeTail4.equals("*")) {
                stack3.addToTail(new MultNode((Expression) removeTail5, (Expression) removeTail6));
            } else if (removeTail4.equals(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                stack3.addToTail(new DivNode((Expression) removeTail5, (Expression) removeTail6));
            } else {
                stack3.addToTail(removeTail6);
                stack2.push(removeTail4);
                stack.push(removeTail5);
            }
        }
        stack.push(stack3.pop());
        while (!stack2.isEmpty()) {
            Object removeTail7 = stack2.removeTail();
            Object removeTail8 = stack.removeTail();
            Object removeTail9 = stack.removeTail();
            if (removeTail7.equals("+")) {
                stack.addToTail(new AddNode((Expression) removeTail8, (Expression) removeTail9));
            } else if (removeTail7.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
                stack.addToTail(new SubNode((Expression) removeTail8, (Expression) removeTail9));
            } else {
                throw new ExpressionParseException("Unknown operator: " + removeTail7, -1);
            }
        }
        return (Expression) stack.pop();
    }
}
