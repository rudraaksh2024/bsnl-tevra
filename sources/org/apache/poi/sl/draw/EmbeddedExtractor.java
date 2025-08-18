package org.apache.poi.sl.draw;

import java.util.Collections;
import java.util.function.Supplier;

public interface EmbeddedExtractor {

    public static class EmbeddedPart {
        private Supplier<byte[]> data;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public Supplier<byte[]> getData() {
            return this.data;
        }

        public void setData(Supplier<byte[]> supplier) {
            this.data = supplier;
        }
    }

    Iterable<EmbeddedPart> getEmbeddings() {
        return Collections.emptyList();
    }
}
