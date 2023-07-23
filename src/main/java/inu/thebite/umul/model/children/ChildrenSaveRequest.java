package inu.thebite.umul.model.children;

import lombok.Data;

@Data
public class ChildrenSaveRequest {

    private String name;

    private String birth;

    private String gender;

    private float height;

    private float weight;

    private String memo;
}
