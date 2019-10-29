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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return assignmentStaffRepository.findAll()
                .stream().map(item->converter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<AssignmentStaffDTO> deleteOne(Long id) {
        assignmentStaffRepository.deleteById(id);
        return assignmentStaffRepository.findAll()
                .stream().map(item->converter.convertToDTO(item)).collect(Collectors.toList());
    }
    public void saveAssignment(Long buildingId, AssignmentStaffDTO assignmentStaffDTO){
        ArrayList<UserDTO> users = (ArrayList<UserDTO>) assignmentStaffDTO.getUsers();
        for (UserDTO user : users){
            AssigmentStaffBuilder builder = new AssigmentStaffBuilder.Builder()
                    .setStaffId(user.getId())
                    .setBuildingId(buildingId)
                    .build();
            List<AssignmentStaffEntity>  assignmentStaffs =
                    assignmentStaffRepository.findByStaffIdAndBuildingId(convertToMapProperties(builder));
            if (user.getChecked().equals("checked")){
             if ( assignmentStaffs.get(0)==null){
                 AssignmentStaffEntity entity = new AssignmentStaffEntity();
                 entity.setBuildingId(buildingId);
                 entity.setStaffId(user.getId());
                 assignmentStaffRepository.insert(entity);
             }
            }
            else {
                if (assignmentStaffs.size()>0 && assignmentStaffs.get(0)!=null ){
                    deleteOne(assignmentStaffs.get(0).getId());
                }
            }
        }
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
