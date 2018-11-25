package cn.techtopic.oblogs.dao;

import cn.techtopic.oblogs.entity.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Long> {
    List<UserAuthority> findByRole(String role);
}
