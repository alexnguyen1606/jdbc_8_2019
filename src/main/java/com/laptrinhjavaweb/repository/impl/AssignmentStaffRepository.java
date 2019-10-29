package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IAssignmentStaffRepository;

import java.util.List;
import java.util.Map;

public class AssignmentStaffRepository extends SimpleJpaRepository<AssignmentStaffEntity>
        implements IAssignmentStaffRepository {


    @Override
    public void deleteById(Long id) {
        deleteById(id,new String());
    }

    @Override
    public AssignmentStaffEntity findByStaffIdAndBuildingId(Map<String,Object> properties) {
        List<AssignmentStaffEntity> assignmentStaff = this.finAll(properties);
        return assignmentStaff.size()>0 ? assignmentStaff.get(0) : new AssignmentStaffEntity();
    }
    private String buildSqlFindSpecial(){
        StringBuilder sql = new StringBuilder("");
        return sql.toString();
    }
//    private String buildDeleteSpecial(Map<String, Object> properties){
//        StringBuilder sql = new StringBuilder("");
//        for (Map.Entry item : properties.entrySet()){
//            sql.append(" AND");
//            sql.append(" "+item.getKey()+" =");
//            sql.append(item.getValue());
//        }
//        return sql.toString();
//    }
}
