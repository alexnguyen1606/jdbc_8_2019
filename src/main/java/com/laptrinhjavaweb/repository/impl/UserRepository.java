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
        StringBuilder sql = new StringBuilder("select u.id,u.fullname from user as u inner join user_role as ur on u.id = ur.userid where u.status = ");
        sql.append(status+" ");
        sql.append("and ur.roleid IN " +
                "( select r.id from role as r where r.id =");
        sql.append(roleId+")");
        return sql.toString();
    }
    private String creStringSqlFindAllByAssignmentStaff(long buildingId){
        StringBuilder sql = new StringBuilder("select u.id,u.fullname from user as u inner join assignmentstaff as a on u.id = a.staffid where  a.buildingid = ");
        sql.append(buildingId);
        return sql.toString();
    }
}
