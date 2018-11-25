package cn.techtopic.oblogs.service;

import cn.techtopic.oblogs.dao.UserRepository;
import cn.techtopic.oblogs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class WebReactiveUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        User user = userService.findByUsername(username).get(0);
        return Mono.just(user);
    }
}
