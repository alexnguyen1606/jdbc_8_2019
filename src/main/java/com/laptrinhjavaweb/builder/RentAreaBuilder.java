package com.laptrinhjavaweb.builder;

public class RentAreaBuilder {
    private Long buildingId;
    private RentAreaBuilder(Builder builder){
        this.buildingId = builder.buildingId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public static class Builder{
        private Long buildingId;

        public Builder setBuildingId(Long buildingId) {
            this.buildingId = buildingId;
            return this;
        }
        public RentAreaBuilder build(){
            return new RentAreaBuilder(this);
        }
    }
}
