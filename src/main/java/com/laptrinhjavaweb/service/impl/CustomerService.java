package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.DTO.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.ICustomerRepository;
import com.laptrinhjavaweb.repository.impl.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService implements ICustomerService {
    private CustomerConverter customerConverter ;
    private CustomerRepository customerRepository;

    public CustomerService() {
        customerConverter = new CustomerConverter();
        customerRepository = new CustomerRepository();
    }

    @Override
    public List<CustomerDTO> findAll(Pageable pageable) {

        return customerRepository.findAll(pageable)
                .stream().map(item-> customerConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream().map(item-> customerConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> findAll(CustomerSearchBuilder fieldSearch, Pageable pageable) {
        Map<String,Object> properties = mapToProperties(fieldSearch);
        return customerRepository.findAll(properties,pageable,fieldSearch)
                .stream().map(item-> customerConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        if (id==null){
            return  new CustomerDTO();
        }
        return customerConverter.convertToDTO(customerRepository.findById(id));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        if (customerDTO.getId()==null){
            CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
            customerEntity.setCreatedDate(new Date());
            customerEntity.setModifiedDate(new Date());
            customerEntity.setCreatedBy("abc");
            Long id = customerRepository.save(customerEntity);
            return findById(id);
        }
        return new CustomerDTO();
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        if (customerDTO==null){
            return new CustomerDTO();
        }
        CustomerDTO customerInDB = findById(customerDTO.getId());
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        CustomerEntity customerEntityInDB = customerConverter.convertToEntity(customerInDB);
        customerEntity.setCreatedBy(customerEntityInDB.getCreatedBy());
        customerEntity.setCreatedDate(customerEntityInDB.getCreatedDate());
        customerEntity.setModifiedDate(new Date());
        customerEntity.setModifiedBy("abc");
        customerRepository.update(customerEntity);
        return findById(customerEntity.getId());
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids){
            if (id!=null){
                customerRepository.deleteById(id);
            }
        }
    }

    public Map<String,Object> mapToProperties(CustomerSearchBuilder fieldSearch){
        Map<String,Object> properties = new HashMap<>();
        Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
        try {
        for (Field field : fields){
            if (!field.getName().equals("userId")){
                field.setAccessible(true);
                properties.put(field.getName().toLowerCase(),field.get(fieldSearch));
            }
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
