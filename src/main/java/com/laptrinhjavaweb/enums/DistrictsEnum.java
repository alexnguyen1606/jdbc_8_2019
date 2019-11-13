package com.laptrinhjavaweb.enums;

public enum DistrictsEnum {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_7("Quận 7")
    ;
    private final String value;
    DistrictsEnum(String levelCode) {
        this.value= levelCode;
    }

    public String getValue() {
        return value;
    }
}
