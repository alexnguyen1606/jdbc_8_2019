package com.laptrinhjavaweb.repository;
import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;;
public interface IUserRepository extends JpaRepository<UserEntity> {
    Long save(UserEntity userEntity);
    List<UserEntity> findAllByStatusAndRole(int status,long roleId);
    List<UserEntity> findAllByAssigmentStaff(long builingId);
}
