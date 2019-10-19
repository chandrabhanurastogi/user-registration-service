package com.gamesys.user.registration;

public enum UserTestEnum {
    UN_OK("BobFrench"),
    UN_NON_ALPHANUMERIC("BobFrench_02"),
    UN_SPACE("Bob French"),
    UN_BLANK(""),

    PW_OK("Password1"),
    PW_LENGTH_LESS_THAN_4("Pw1"),
    PW_NO_UPPER_CASE("password1"),
    PW_NO_NUMBER("Password"),

    DOB_OK("1980-02-21"),
    DOB_LESS_THAN_18YR("2003-02-21"),

    PCN_OK("349293081054422"),
    PCN_LESS_THAN_15("49293081054422"),
    PCN_MORE_THAN_19("34929308105442223456"),
    PCN_NON_DIGIT("efghijklmnopqrst");


    private String val;

    UserTestEnum(String value) {
        this.val = value;
    }

    public String val() {
        return val;
    }
}
