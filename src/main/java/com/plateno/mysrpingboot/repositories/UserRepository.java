package com.plateno.mysrpingboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plateno.mysrpingboot.domain.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity , Integer> , UserRepositoryCums {
	
	@Query(" select u from UserEntity u where u.age = ?1 ")
	public List<UserEntity> findUserByAge( Integer age ) ;
}
