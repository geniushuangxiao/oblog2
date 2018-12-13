package com.summer.xblog.dao;

import com.summer.xblog.entity.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
    List<UserAuthority> findByRole(String role);
}
