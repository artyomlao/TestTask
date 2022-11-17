package com.lepesha.task.responseEntity;

public class BasicResponse {
    private Integer code;
    private String description;

    public BasicResponse(Integer code) {
        this.code = code;
        setDescriptionByCode(code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
            case 10:
                description = "Number with such code already exists";
                break;
            case 11:
                description = "Number with such code is missing";
                break;
            default:
                description = "unknown code";
        }
    }
}
