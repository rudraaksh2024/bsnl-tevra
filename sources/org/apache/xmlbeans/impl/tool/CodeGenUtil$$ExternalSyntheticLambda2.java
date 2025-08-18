package org.apache.xmlbeans.impl.tool;

import java.io.BufferedReader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CodeGenUtil$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ BufferedReader f$0;
    public final /* synthetic */ StringBuilder f$1;

    public /* synthetic */ CodeGenUtil$$ExternalSyntheticLambda2(BufferedReader bufferedReader, StringBuilder sb) {
        this.f$0 = bufferedReader;
        this.f$1 = sb;
    }

    public final void run() {
        this.f$0.lines().forEach(new CodeGenUtil$$ExternalSyntheticLambda0(this.f$1));
    }
}
