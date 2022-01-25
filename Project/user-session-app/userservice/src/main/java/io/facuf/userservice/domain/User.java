package io.facuf.userservice.domain;


import io.facuf.userservice.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_app")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role", name = "role", nullable = false)
    @Type(type = "pgsql_enum")
    private Role role;

    public Set<String> getPermissions() {
        return this.role.permissions();
    }
}
