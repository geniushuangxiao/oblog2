package cn.techtopic.oblogs.service;

import cn.techtopic.oblogs.dao.UserAuthorityRepository;
import cn.techtopic.oblogs.dao.UserRepository;
import cn.techtopic.oblogs.dto.CommonDTO;
import cn.techtopic.oblogs.dto.Register;
import cn.techtopic.oblogs.entity.User;
import cn.techtopic.oblogs.entity.UserAuthority;
import cn.techtopic.oblogs.tools.Validators;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private UserAuthorityRepository userAuthorityRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MailService mailService;

	@Autowired
	private ActivateLinkCache linkCache;

	@Secured({ "ROLE_ADMIN" })
	public User addUser(String username, String password, String role, String email) {
		return privateAddUser(username, password, role, email);
	}
	public CommonDTO register(Register register) {
		if(linkCache.full()) {
			log.warn("系统可能正被垃圾注册攻击。", register);
			return new CommonDTO(false, "系统繁忙，请稍后再试", null);
		}
		//检查注册信息是否合乎要求
		if(Validators.nullOrEmpty(register.getUsername())
				|| Validators.nullOrEmpty(register.getPassword())
				|| Validators.nullOrEmpty(register.getPassword2())
				|| Validators.nullOrEmpty(register.getEmail())
				|| !register.getPassword().equals(register.getPassword2())) {
			return new CommonDTO(false, "参数不合法", null);
		}
		//检查用户名是否已经被注册
		final boolean exist = !userRepository.findByUsername(register.getUsername()).isEmpty();
		if(exist) {
			return new CommonDTO(false, String.format("用户名%s已经被注册", register.getUsername()), null);
		}
		//检查邮箱是否已经被注册
		final boolean emailUsed = !userRepository.findByEmail(register.getEmail()).isEmpty();
		if(emailUsed) {
			return new CommonDTO(false, String.format("邮箱%s已经被注册", register.getEmail()), null);
		}
		//注册用户
		final User user = this.privateAddUser(register.getUsername(), register.getPassword(), "USER", register.getEmail(), false);
		//生成注册账号激活链接，并发送到用户邮箱
		try {
			SecureRandom random = SecureRandom.getInstanceStrong();
			boolean success = false;
			//尝试10次，保证发送给每个注册用户链接都不同，且不无限循环
			for(int i = 0; i < 10; i ++) {
				String randomNum = String.valueOf(random.nextLong());
				if(!linkCache.exists(randomNum)) {
					this.mailService.sendSimpleEmail(register.getEmail(), randomNum);
					this.linkCache.push(user, randomNum);
					success = true;
					break;
				}
			}
			if(!success) {
				return new CommonDTO(false, "包含激活链接的邮件发送失败，激活码生成失败，请稍后再试", null);
			}
		} catch (NoSuchAlgorithmException e) {
			//激活链接发送失败，注册失败，删除数据库中脏数据
			userRepository.delete(user);
			return new CommonDTO(false, "包含激活链接的邮件发送失败，请稍后再试", null);
		}
		return new CommonDTO(true, "", null);
	}

	/**
	 * 激活用户账户
	 * @param random 随机激活码
	 * @return 成功返回true，失败返回false
	 */
	public boolean activateUser(String random) {
		if(linkCache.exists(random)) {
			final ActivateLink activateLink = linkCache.get(random);
			final Optional<User> userOptional = userRepository.findById(activateLink.getUserId());
			if(userOptional.isPresent()) {
				final User user = userOptional.get();
				user.setEnabled(true);
				userRepository.save(user);
				//激活成功，从缓存删除激活链接
				linkCache.remove(activateLink);
				return true;
			}
		}
		return false;
	}
	/**未进行权限验证，仅供软件启动初始化类DefaultUserInit使用*/
	User privateAddUser(String username, String password, String role, String email) {
		return this.privateAddUser(username, password, role, email, true);
	}

	/**未进行权限验证，仅供软件启动初始化类DefaultUserInit使用*/
	private User privateAddUser(String username, String password, String role, String email, boolean enabled) {
		String encryptedPassword = encoder.encode(password);
		Set<UserAuthority> roleSet = new HashSet<>();
		UserAuthority userAuthority = userAuthorityRepository.findByRole(role).get(0);
		roleSet.add(userAuthority);
		final long current = System.currentTimeMillis();
		User user = new User(username, encryptedPassword, email, current, roleSet, enabled);
		userRepository.save(user);
		user.eraseCredentials(); // 将密码擦除
		return user;
	}

	@Secured({ "ROLE_ADMIN" })
	public List<User> queryAll() {
		List<User> result = new ArrayList<>();
		userRepository.findAll().forEach(result::add);
		return result;
	}

	@Secured({ "ROLE_ADMIN" })
	public void deleteUser(String username) {
		userRepository.deleteByUsername(username);
	}

}
