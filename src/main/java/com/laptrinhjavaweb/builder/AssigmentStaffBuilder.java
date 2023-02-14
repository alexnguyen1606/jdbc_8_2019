package com.laptrinhjavaweb.builder;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

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
    
    private void validateName(String name){
        if (name==null){
            throw new RuntimeException("Tên không được bỏ trống");
        }
        
    }
    
    private static void validateDateOfBirth(String dateOfBirth){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            dateTimeFormatter.parse(dateOfBirth);
        }catch (Exception e){
            throw new RuntimeException("Ngày sinh k hợp lệ");
        }
    }

  public static void main(String[] args) {
    
    
    String a ="16-06-1997a";
    validateDateOfBirth(a);
  }
}
