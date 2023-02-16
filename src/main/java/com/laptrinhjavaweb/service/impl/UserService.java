package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.builder.UserBuilder;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;
import com.laptrinhjavaweb.repository.impl.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import com.laptrinhjavaweb.service.IAssignmentStaffService;
import com.laptrinhjavaweb.service.IUserService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class UserService implements IUserService {

    @Inject
    private IUserRepository userRepository;
    @Inject
    private IAssignmentStaffService assignmentStaffService;
    @Inject
    private IAssignmentCustomerService assignmentCustomerService;
    @Inject
    private UserConverter converter;

    public UserService() {
        converter = new UserConverter();
        userRepository = new UserRepository();
        assignmentStaffService = new AssignmentStaffService();
        assignmentCustomerService = new AssignmentCustomerService();
    }

    @Override
    public ArrayList<UserDTO> findAll() {
        return (ArrayList<UserDTO>) userRepository.findAll().stream()
                .map(item -> converter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            UserEntity userEntity = converter.covertToEntity(userDTO);
            userEntity.setCreatedDate(new Date());
            userEntity.setModifiedDate(new Date());
            Long id = userRepository.save(userEntity);
            return findOne(id);
        }
        return new UserDTO();
    }

    @Override
    public UserDTO findOne(Long id) {
        if (id == null || userRepository.findById(id) == null) {
            return new UserDTO();
        } else {
            return converter.convertToDTO(userRepository.findById(id));
        }

    }

    @Override
    public List<UserDTO> findByStatusAndRoleIdAndBuildingId(int status, long roleId, Long buildingId) {
        List<UserDTO> staffs = findByStatusAndRole(status, roleId);
        for (UserDTO staff : staffs) {
            if (assignmentStaffService.existAssignment(buildingId, staff.getId())) {
                staff.setChecked("checked");
            } else {
                staff.setChecked("");
            }
        }
//		Map<Long, Object> listA = staffs.stream().collect(
//				Collectors.toMap(UserDTO::getId, item -> item)
//		);
//
//		List<UserDTO> listB = userRepository.findAllByAssigmentStaff(buildingId)
//				.stream().map(item -> converter.convertToDTO(item))
//				.collect(Collectors.toList());
//		List<UserDTO> result = new ArrayList<>();
//		for (Map.Entry item : listA.entrySet()) {
//			UserDTO userListA = (UserDTO) item.getValue();
//			for (UserDTO userDTO : listB) {
//				if (item.getKey() == userDTO.getId()) {
//					userListA.setChecked("checked");
//				}
//			}
//			result.add(userListA);
//		}
//		return result;
//	}
        return staffs;
    }

    @Override
    public List<UserDTO> findByStatusAndRoleAndCustomerId(int status, Long roleId, Long customerId) {
        List<UserDTO> staffs = findByStatusAndRole(status, roleId);
        for (UserDTO staff : staffs) {
            if (assignmentCustomerService.existAssigment(customerId, staff.getId())) {
                staff.setChecked("checked");
            } else {
                staff.setChecked("");
            }
        }
        return staffs;
    }


    @Override
    public List<UserDTO> findByStatus(int status) {
        UserBuilder builder = new UserBuilder.Builder()
                .setStatus(status)
                .build();
        Map<String, Object> properties = convertToProperties(builder);
        return userRepository.finAll(properties).stream()
                .map(item -> converter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByStatusAndRole(int status, long roleId) {
        return userRepository.findAllByStatusAndRole(status, roleId).stream()
                .map(item -> converter.convertToDTO(item)).collect(Collectors.toList());
    }

    private Map<String, Object> convertToProperties(UserBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        try {
            Field[] fields = builder.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("status")) {
                    properties.put(field.getName().toLowerCase(), field.get(builder));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return properties;
    }

}
