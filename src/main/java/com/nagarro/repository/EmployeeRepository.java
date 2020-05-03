package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import com.nagarro.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByUserName(String username);

	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update employee e set e.password =:password where e.user_name =:userName",nativeQuery = true)
    void updatePassword(@Param("userName") String userName, @Param("password") String password);

	
}
