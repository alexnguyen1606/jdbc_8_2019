package com.laptrinhjavaweb.enums;

public enum BuildingTypesEnum {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyễn Căn"),
    NOI_THAT("Nội thất")
    ;

    private final String value;

    BuildingTypesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
