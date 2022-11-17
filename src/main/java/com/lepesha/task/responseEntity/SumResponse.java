package com.lepesha.task.responseEntity;

public class SumResponse {
    private Double result;
    private Integer code;
    private String description;

    public SumResponse(Integer code) {
        this.code = code;
        setDescriptionByCode(code);
    }

    public SumResponse(Integer code, Double result) {
        this.code = code;
        setDescriptionByCode(code);
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void setDescriptionByCode(Integer code) {
        switch (code){
            case 0:
                description = "OK";
                break;
            case 12:
                description = "At least one number with such code is missing";
                break;
            default:
                description = "unknown code";
                break;
        }
    }
}
