package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.DTO.RentAreaDTO;

public interface IRentAreaService {
    RentAreaDTO save(RentAreaDTO rentAreaDTO);
    RentAreaDTO findOne(Long id);
}
