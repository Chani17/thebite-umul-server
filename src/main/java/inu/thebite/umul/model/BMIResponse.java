package inu.thebite.umul.model;

import lombok.Data;

@Data
public class BMIResponse {

    private double bmi;

    private String result;

    public BMIResponse(double bmi, String result) {
        this.bmi = bmi;
        this.result = result;
    }
}
