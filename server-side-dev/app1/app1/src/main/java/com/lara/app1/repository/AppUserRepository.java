package com.lara.app1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lara.app1.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, String>
{
	public AppUser findByTokenAndEmail(String token, String email);
}
