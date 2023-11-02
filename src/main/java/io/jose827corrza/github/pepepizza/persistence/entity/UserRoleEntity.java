package io.jose827corrza.github.pepepizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_role")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(nullable = false, length = 25)
    private String username;

    @Id
    @Column(nullable = false, length = 25)
    private String role;

    @Column(nullable = false, name = "granted_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime grantedTime;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;
}
