package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.DTO.RentAreaDTO;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;

import java.util.Date;

public class RentAreaService implements IRentAreaService {
    private IRentAreaRepository rentAreaRepository;
    private RentAreaConverter converter;
    public RentAreaService() {
        rentAreaRepository = new RentAreaRepository();
        converter = new RentAreaConverter();
    }

    @Override
    public RentAreaDTO save(RentAreaDTO rentAreaDTO) {
        if (rentAreaDTO.getId()==null){
            RentAreaEntity entity = converter.convertToEntity(rentAreaDTO);
            entity.setCreatedDate(new Date());
            entity.setModifiedDate(new Date());
            entity.setCreatedBy("abc");
            Long id = rentAreaRepository.insert(entity);
            return findOne(id);
        }
        return new RentAreaDTO();
    }

    @Override
    public RentAreaDTO findOne(Long id) {
        if (id == null || rentAreaRepository.findById(id) == null) {
            return new RentAreaDTO();
        } else {
            return converter.convertToDTO(rentAreaRepository.findById(id));
        }
    }
}
