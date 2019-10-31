package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.DTO.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IUserRepository;

import java.util.List;
import java.util.Map;

public class UserRepository extends SimpleJpaRepository<UserEntity> implements IUserRepository {


    @Override
    public Long save(UserEntity userEntity) {
        return this.insert(userEntity);
    }

    @Override
    public List<UserEntity> findAllByStatusAndRole(int status, long roleId) {
        String sql = createdSqlFindAllByStatusAndRole(status,roleId);
        return this.findAll(sql);
    }

    @Override
    public List<UserEntity> findAllByAssigmentStaff(long builingId) {
        String sql = creStringSqlFindAllByAssignmentStaff(builingId);
        return this.findAll(sql);
    }

    private String createdSqlFindAllByStatusAndRole(int status,long roleId){
        StringBuilder sql = new StringBuilder("select A.id,A.fullname from user as A inner join user_role as B on A.id = B.userid where A.status = ");
        sql.append(status+" ");
        sql.append("and B.roleid IN " +
                "( select r.id from role as r where r.id =");
        sql.append(roleId+")");
        return sql.toString();
    }
    private String creStringSqlFindAllByAssignmentStaff(long buildingId){
        StringBuilder sql = new StringBuilder("select A.id,A.fullname from user as A inner join assignmentstaff as B " +
                "ON A.id = B.staffid where  B.buildingid = ");
        sql.append(buildingId);
        return sql.toString();
    }
}
