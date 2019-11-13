package com.laptrinhjavaweb.builder;

public class RentAreaBuilder {
    private Long buildingId;
    private Integer value;
    private RentAreaBuilder(Builder builder){
        this.buildingId = builder.buildingId;
        this.value = builder.value;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public int getValue() {
        return value;
    }

    public static class Builder{
        private Long buildingId;
        private Integer value;
        public Builder setBuildingId(Long buildingId) {
            this.buildingId = buildingId;
            return this;
        }

        public Builder setValue(Integer value) {
            this.value = value;
            return this;
        }

        public RentAreaBuilder build(){
            return new RentAreaBuilder(this);
        }
    }
}
