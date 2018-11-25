package cn.techtopic.oblogs.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true) // 方法级别权限控制开关
public class ExtendedGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
}
