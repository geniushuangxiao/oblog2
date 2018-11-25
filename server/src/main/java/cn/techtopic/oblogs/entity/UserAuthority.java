package cn.techtopic.oblogs.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String role;

    @Override
    public String getAuthority() {
        if (role.startsWith("ROLE_")) { // 若数据库中添加了ROLE_前缀，可直接返回给权限校验
            return role;
        }
        return String.format("ROLE_%s", role);// 数据库中没加ROLE_前缀，权限校验时，须要加前缀
    }
}
