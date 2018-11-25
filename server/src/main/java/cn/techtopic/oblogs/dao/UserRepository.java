package cn.techtopic.oblogs.dao;

import cn.techtopic.oblogs.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String name);
    List<User> findByEmail(String email);
    void deleteByUsername(String username);
}
