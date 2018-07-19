package info.chaintech.july.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author shniu
 * @date 2018-06-08 下午6:44
 * @email niushaohan@digcredit.com
 */

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
