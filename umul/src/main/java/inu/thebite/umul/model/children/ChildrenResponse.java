package inu.thebite.umul.model.children;

import inu.thebite.umul.domain.Children;
import lombok.Data;

@Data
public class ChildrenResponse {

    private String name;

    private float height;

    private float weight;

    public ChildrenResponse(Children children) {
        this.name = children.getName();
        this.height = children.getHeight();
        this.weight = children.getWeight();
    }
}
