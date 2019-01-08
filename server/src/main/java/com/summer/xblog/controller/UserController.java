package com.summer.xblog.controller;

import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.dto.Register;
import com.summer.xblog.entity.User;
import com.summer.xblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * <h2>添加用户账号</h2>
	 * accountNonExpired，accountNonLocked，credentialsNonExpired，enabled默认均为true
	 */
	@ApiOperation("添加用户")
	@PostMapping("/add")
	public @ResponseBody ResponseEntity<User> add(@RequestParam String username, @RequestParam String password,
												  @RequestParam String role, @RequestParam(required = false) String email) {
		User user = userService.addUser(username, password, role, email);
		return ResponseEntity.ok(user);
	}

	@ApiOperation("注册用户")
	@PostMapping("/register")
	public CommonDTO register(@RequestBody Register register) {
		return userService.register(register);
	}

	@ApiOperation("邮箱激活")
	@GetMapping("activate/{id}")
	public CommonDTO activate(@PathVariable(name="id") String random) {
		final boolean success = userService.activateUser(random);
		if(success) {
			return new CommonDTO(true, "激活成功", null);
		} else {
			return new CommonDTO(false, "激活失败", null);
		}
	}

	@ApiOperation("删除用户")
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<String> delete(@RequestParam String username) {
		userService.deleteUser(username);
		String resMsg = String.format("Delete user named %s success.", username);
		return ResponseEntity.ok(resMsg);
	}

	@ApiOperation("查询用户列表")
	@GetMapping("/query")
	public List<User> query() {
		return userService.queryAll();
	}

	@ApiOperation("获取登陆用户信息")
	@GetMapping()
	public Principal user(Principal user) {
		return user;
	}
}
