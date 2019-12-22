package cn.aepb.train;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class NamedObject {

    protected String name;

    public NamedObject(String name) {
        StringUtils.ensureNotEmpty(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
