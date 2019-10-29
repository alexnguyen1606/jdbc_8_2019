package com.laptrinhjavaweb.DTO;

import java.util.ArrayList;
import java.util.List;

public class AssignmentStaffDTO {
    private Long id;
    private Long buildingId;
    private Long staffId;
    List<UserDTO> users = new ArrayList<>();

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
