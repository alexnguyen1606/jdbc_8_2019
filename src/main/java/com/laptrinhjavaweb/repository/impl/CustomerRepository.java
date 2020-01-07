package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.ICustomerRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CustomerRepository extends SimpleJpaRepository<CustomerEntity> implements ICustomerRepository {

    @Override
    public List<CustomerEntity> findAll(Map<String,Object> properties, Pageable pageable, CustomerSearchBuilder builder) {
        StringBuilder sql = new StringBuilder(" Select * from customer AS A");
        String sqlSpecial =buildSqlSpecial(builder);
        if (StringUtils.isNotBlank(builder.getUserId())){
            sql.append(" INNER JOIN assignmentcustomer as B ON A.id = B.customerid");
        }
        sql.append(" WHERE 1=1");
        sql = this.createSqlFindAll(sql,properties);
        sql.append(sqlSpecial);
        return this.findAll(sql.toString(),pageable);
    }

    @Override
    public Long save(CustomerEntity customerEntity) {
        return this.insert(customerEntity);
    }


    private String buildSqlSpecial(CustomerSearchBuilder builder) {
        StringBuilder sqlSpecial = new StringBuilder("");
        if (StringUtils.isNotBlank(builder.getUserId())){
            sqlSpecial.append(" AND B.userid="+builder.getUserId());
        }
        return sqlSpecial.toString();
    }
}
