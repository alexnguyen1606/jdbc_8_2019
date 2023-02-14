package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.DTO.AssignmentStaffDTO;
import com.laptrinhjavaweb.DTO.BuildingDTO;
import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.DTO.UserDTO;
import com.laptrinhjavaweb.builder.AssigmentStaffBuilder;
import com.laptrinhjavaweb.converter.AssignmentStaffConverter;
import com.laptrinhjavaweb.entity.AssignmentStaffEntity;
import com.laptrinhjavaweb.repository.IAssignmentStaffRepository;
import com.laptrinhjavaweb.repository.impl.AssignmentStaffRepository;
import com.laptrinhjavaweb.service.IAssignmentStaffService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
public class AssignmentStaffService implements IAssignmentStaffService {
    @Inject
    private IAssignmentStaffRepository assignmentStaffRepository;
    @Inject
    private AssignmentStaffConverter converter;


    @Override
    public AssignmentStaffDTO save(AssignmentStaffDTO assignmentStaffDTO) {
        if (assignmentStaffDTO.getId()==null){
            AssignmentStaffEntity entity = converter.convertToEntity(assignmentStaffDTO);
            entity.setCreatedDate(new Date());
            entity.setModifiedDate(new Date());
            Long id = assignmentStaffRepository.insert(entity);
            return findOne(id);
        }
        return new AssignmentStaffDTO();
    }

    @Override
    public AssignmentStaffDTO findOne(Long id) {
        if (id==null || assignmentStaffRepository.findById(id)==null){
            return new AssignmentStaffDTO();
        }else {
         return converter.convertToDTO(assignmentStaffRepository.findById(id));
        }
    }

    @Override
    public AssignmentStaffDTO update(AssignmentStaffDTO assignmentStaffDTO) {
        return null;
    }

    @Override
    public void delete(Long[] ids) {
     if (ids.length>0){
        for (Long id : ids) {
            assignmentStaffRepository.deleteById(id);
        }
    }
    }

    @Override
    public void deleteOne(Long id) {
        if (id!=null){
            assignmentStaffRepository.deleteById(id);
        }
    }

    @Override
    public List<AssignmentStaffDTO> findByBuildingId(Long buidlingId) {
        AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                .setBuildingId(buidlingId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        return assignmentStaffRepository.findByBuildingId(properties)
                .stream().map(item-> converter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public void deleteByBuildingIdAndStaffId(Long buildingId, Long staffId) {
        AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                .setBuildingId(buildingId)
                .setStaffId(staffId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        assignmentStaffRepository.deleteByBuildingIdAndStaffId(properties);
    }

    @Override
    public void deleteByBuildingId(Long buildingId) {
        AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                .setBuildingId(buildingId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        assignmentStaffRepository.deleteByBuildingId(properties);
    }

    @Override
    public List<AssignmentStaffDTO> findAll() {
        return assignmentStaffRepository.findAll().stream()
                .map(item-> converter.convertToDTO(item)).collect(Collectors.toList());
    }
    @Override
    public void assignBuilding(Long buildingId, Long[] staffs){
        ArrayList<Long> staffsAssignInDB = new ArrayList<>();
        findByBuildingId(buildingId).stream().
                map(item-> staffsAssignInDB.add(item.getStaffId())).collect(Collectors.toList());
        ArrayList<Long> staffsAssign = new ArrayList<>(Arrays.asList(staffs));

        for (Long item : staffsAssignInDB){
            if (!staffsAssign.contains(item)){
                deleteByBuildingIdAndStaffId(buildingId,item);
            }
        }
        
        for (Long item : staffsAssign){
            if (!staffsAssignInDB.contains(item) && item!=null){
              AssignmentStaffDTO dto = new AssignmentStaffDTO();
              dto.setStaffId(item);
              dto.setBuildingId(buildingId);
              this.save(dto);
            }
        }
    }

    @Override
    public boolean existAssignment(Long buildingId, Long staffId) {
        if (findByBuildingIdAndStaffId(buildingId,staffId).getId() != null ){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public AssignmentStaffDTO findByBuildingIdAndStaffId(Long buildingId, Long staffId) {
        AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                .setBuildingId(buildingId)
                .setStaffId(staffId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        return converter.convertToDTO(assignmentStaffRepository.findByBuildingIdAndStaffId(properties));
    }

    private Map<String,Object> convertToMapProperties(AssigmentStaffBuilder assigmentStaffBuilder){
        Map<String,Object> properties = new HashMap<>();
        try {
        Field[] fields = assigmentStaffBuilder.getClass().getDeclaredFields();
        for(Field field : fields){
            if (field.getName().equals("buildingId")||field.getName().equals("staffId")){
                field.setAccessible(true);
                properties.put(field.getName().toLowerCase(),field.get(assigmentStaffBuilder));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return properties;
    }

}
