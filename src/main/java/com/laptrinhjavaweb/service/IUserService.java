package com.laptrinhjavaweb.service;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.*;
public interface IUserService {

	public ArrayList<UserDTO> findAll();
	UserDTO save(UserDTO userDTO);
	UserDTO findOne(Long id);
	List<UserDTO> findByStatusAndRoleIdAndBuildingId(int status,long roleId,Long buildingId);
	List<UserDTO> findByStatusAndRoleAndCustomerId(int status,Long roleId,Long customerId);
	List<UserDTO> findByStatus(int status);
	List<UserDTO> findByStatusAndRole(int status,long roleId);
}
