package info.chaintech.july.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * 用户表
 *
 * @author shniu
 * @date 2018/7/7
 */

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;

    /**
     * 用户名, 使用手机号
     */
    @Column(length = 32, unique = true, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 32)
    private String realName;

    @Column(length = 10)
    private String sex;

    @Column(length = 64)
    private String email;

    @CreatedDate
    private Date createdOn;

    @LastModifiedDate
    private Date updatedOn;

    /**
     * 是否禁用
     */
    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    public boolean getEnabled() {
        return enabled;
    }
}
