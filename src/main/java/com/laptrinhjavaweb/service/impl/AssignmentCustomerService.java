package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.DTO.AssignmentCustomerDTO;
import com.laptrinhjavaweb.builder.AssignmentCustomerBuilder;
import com.laptrinhjavaweb.converter.AssignmentCustomerConverter;
import com.laptrinhjavaweb.entity.AssignmentCustomerEntity;
import com.laptrinhjavaweb.repository.IAssignmentCustomerRepository;
import com.laptrinhjavaweb.repository.impl.AssignmentCustomerRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
@ManagedBean
public class AssignmentCustomerService implements IAssignmentCustomerService {
  @Inject
  private AssignmentCustomerConverter converter;
  @Inject
  private IAssignmentCustomerRepository assignmentCustomerRepository;

  public AssignmentCustomerService() {
    assignmentCustomerRepository = new AssignmentCustomerRepository();
    converter = new AssignmentCustomerConverter();
  }

  @Override
  public boolean existAssigment(Long customerId, Long userId) {
    return findByCustomerIdAndUserId(customerId, userId).getId() != null;
  }

  @Override
  public void assignCustomer(Long customerId, Long[] staffs) {
    ArrayList<Long> users = new ArrayList<>(Arrays.asList(staffs));
    for (Long userId : users) {
      if (!existAssigment(customerId, userId)) {
        AssignmentCustomerDTO assignmentCustomer = new AssignmentCustomerDTO();
        assignmentCustomer.setUserId(userId);
        assignmentCustomer.setCustomerId(customerId);
        save(assignmentCustomer);
      }
    }
    ArrayList<Long> staffAssignInDB = new ArrayList<>();
    findByCustomerId(customerId)
            .forEach(item -> staffAssignInDB.add(item.getUserId()));
    for (Long userId : staffAssignInDB) {
      if (!users.contains(userId)) {
        deleteByCustomerIdAndUserId(customerId, userId);
      }
    }
  }

  @Override
  public AssignmentCustomerDTO save(AssignmentCustomerDTO assignmentCustomerDTO) {
    if (assignmentCustomerDTO.getId() == null) {
      AssignmentCustomerEntity entity = converter.convertToEntity(assignmentCustomerDTO);
      entity.setCreatedDate(new Date());
      entity.setModifiedDate(new Date());
      entity.setCreatedBy("abc");
      Long id = assignmentCustomerRepository.insert(entity);
      return findOne(id);
    }
    return new AssignmentCustomerDTO();
  }

  @Override
  public void deleteByCustomerIdAndUserId(Long customerId, Long userId) {
    AssignmentCustomerBuilder builder =
        new AssignmentCustomerBuilder.Builder().setCustomerId(customerId).setUserId(userId).build();
    Map<String, Object> properties = convertToProperties(builder);
    assignmentCustomerRepository.deleteSpecial(properties);
  }

  @Override
  public List<AssignmentCustomerDTO> findByCustomerId(Long customerId) {
    AssignmentCustomerBuilder builder =
        new AssignmentCustomerBuilder.Builder().setCustomerId(customerId).build();
    Map<String, Object> properties = convertToProperties(builder);
    return assignmentCustomerRepository.finAll(properties).stream()
        .map(item -> converter.convertToDTO(item))
        .collect(Collectors.toList());
  }

  @Override
  public AssignmentCustomerDTO findByCustomerIdAndUserId(Long customerId, Long userId) {
    AssignmentCustomerBuilder builder =
        new AssignmentCustomerBuilder.Builder().setCustomerId(customerId).setUserId(userId).build();
    Map<String, Object> properties = convertToProperties(builder);
    return converter.convertToDTO(
        assignmentCustomerRepository.findByCustomerIdAndUserId(properties));
  }

  @Override
  public AssignmentCustomerDTO findOne(Long id) {
    return converter.convertToDTO(assignmentCustomerRepository.findById(id));
  }

  private Map<String, Object> convertToProperties(AssignmentCustomerBuilder builder) {
    Map<String, Object> properties = new HashMap<>();
    Field[] fields = builder.getClass().getDeclaredFields();
    try {
      for (Field field : fields) {
        if (field.getName().equals("customerId") || field.getName().equals("userId")) {
          field.setAccessible(true);
          properties.put(field.getName().toLowerCase(), field.get(builder));
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    return properties;
  }
}
