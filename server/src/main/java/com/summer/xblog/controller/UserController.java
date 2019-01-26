package com.summer.xblog.controller;

import com.summer.xblog.dto.CommonDTO;
import com.summer.xblog.dto.Register;
import com.summer.xblog.entity.User;
import com.summer.xblog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

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
			return CommonDTO.success("激活成功");
		} else {
			return CommonDTO.fail("激活失败");
		}
	}

	@ApiOperation("删除用户")
	@DeleteMapping("/delete")
	public CommonDTO delete(@RequestParam String username) {
		boolean success = userService.deleteUser(username);
		if(success) {
			String resMsg = String.format("删除用户 %s 成功.", username);
			return CommonDTO.success(String.format(resMsg, username));
		} else {
			String errMsg = String.format("删除用户 %s 失败", username);
			return CommonDTO.fail(String.format(errMsg, username));
		}
	}

	@ApiOperation("查询用户列表")
	@GetMapping("/query")
	public CommonDTO<List<User>> query() {
		return userService.queryAll();
	}

	@ApiOperation("获取登陆用户信息")
	@GetMapping()
	public CommonDTO user(Principal user) {
		if(null != user) {
			return CommonDTO.success(user);
		} else {
			return CommonDTO.fail("获取登陆用户信息失败");
		}
	}
}
