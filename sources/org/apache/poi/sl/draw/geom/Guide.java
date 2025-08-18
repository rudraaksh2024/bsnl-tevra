package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public class Guide implements GuideIf {
    private String fmla;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getFmla() {
        return this.fmla;
    }

    public void setFmla(String str) {
        this.fmla = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Guide guide = (Guide) obj;
        if (!Objects.equals(this.name, guide.name) || !Objects.equals(this.fmla, guide.fmla)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.fmla});
    }
}
