package dev.patika.VeterinaryManagementSystem.core.result;

import lombok.Getter;

import javax.annotation.processing.Generated;

@Getter
public class Result {
    private boolean sutatus;
    private String message;
    private String code;

    public Result(boolean sutatus, String message, String code) {
        this.sutatus = sutatus;
        this.message = message;
        this.code = code;
    }
}
