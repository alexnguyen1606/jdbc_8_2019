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

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class AssignmentStaffService implements IAssignmentStaffService {
    private IAssignmentStaffRepository assignmentStaffRepository;
    private AssignmentStaffConverter converter;
    public AssignmentStaffService() {
        assignmentStaffRepository = new AssignmentStaffRepository();
        converter = new AssignmentStaffConverter();
    }


    @Override
    public AssignmentStaffDTO save(AssignmentStaffDTO assignmentStaffDTO) {
        if (assignmentStaffDTO.getId()==null){
            AssignmentStaffEntity entity = converter.convertToEntity(assignmentStaffDTO);
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
    public List<AssignmentStaffDTO> delete(Long[] ids) {
        for (Long id : ids){
            assignmentStaffRepository.deleteById(id);
        }
        return findAll();
    }

    @Override
    public List<AssignmentStaffDTO> deleteOne(Long id) {
        if (id!=null){
            assignmentStaffRepository.deleteById(id);
        }
        return findAll();
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
    public List<AssignmentStaffDTO> deleteByBuildingIdAndStaffId(Long buildingId, Long staffId) {
        AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                .setBuildingId(buildingId)
                .setStaffId(staffId)
                .build();
        Map<String,Object> properties = convertToMapProperties(builder);
        assignmentStaffRepository.deleteByBuildingIdAndStaffId(properties);
        return findAll();
    }

    @Override
    public List<AssignmentStaffDTO> findAll() {
        return assignmentStaffRepository.findAll().stream()
                .map(item-> converter.convertToDTO(item)).collect(Collectors.toList());
    }

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
//        for (Long userId : staffs){
//            AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
//                    .setStaffId(userId)
//                    .setBuildingId(buildingId)
//                    .build();
//            AssignmentStaffEntity  assignmentStaffs =
//                    assignmentStaffRepository.findByStaffIdAndBuildingId(convertToMapProperties(builder));
//             if ( assignmentStaffs==null){
//                 AssignmentStaffEntity entity = new AssignmentStaffEntity();
//                 entity.setBuildingId(buildingId);
//                 entity.setStaffId(userId);
//                 assignmentStaffRepository.insert(entity);
//             }
//        }
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
