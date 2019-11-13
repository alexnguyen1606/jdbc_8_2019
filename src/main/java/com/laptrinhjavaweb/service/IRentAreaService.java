package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.DTO.RentAreaDTO;

import java.util.List;

public interface IRentAreaService {
    RentAreaDTO save(RentAreaDTO rentAreaDTO);
    RentAreaDTO findOne(Long id);
    void deleteByBuildingId(Long buildingId);
    void deleteOne(Long id);
    List<RentAreaDTO> findByBuildingId(Long buildingId);
    String getAllAreaRentByBuildingId(Long buildingId);
    void saveAll(Long buildingId,String areaRent);
    void updateAll(Long buildingId,String areaRent);
}
