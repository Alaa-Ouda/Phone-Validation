package com.jumia.repositories;

import com.jumia.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT phone FROM Customer")
    List<String> findPhones();

    @Query("SELECT phone FROM Customer WHERE phone LIKE CONCAT(CONCAT('(', :countryCode, ')'), '%')")
    List<String> findPhoneByCountryCode(@Param("countryCode") String countryCode);

}
