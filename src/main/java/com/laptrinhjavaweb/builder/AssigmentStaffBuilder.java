package com.laptrinhjavaweb.builder;

public class AssigmentStaffBuilder {
    private Long buildingId;
    private Long staffId;

    private AssigmentStaffBuilder(Builder builder) {
        this.buildingId = builder.buildingId;
        this.staffId = builder.staffId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public Long getStaffid() {
        return staffId;
    }

    public static class Builder{
       private Long buildingId;
       private Long staffId;
        public AssigmentStaffBuilder build(){
            return new AssigmentStaffBuilder(this);
        }
        public Builder setBuildingId(Long buildingId) {
            this.buildingId = buildingId;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
    }

}
