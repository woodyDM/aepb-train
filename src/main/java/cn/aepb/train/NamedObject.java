package cn.aepb.train;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class NamedObject {

    protected String name;

    public NamedObject(String name) {
        StringUtils.ensureNotEmpty(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedObject that = (NamedObject) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
