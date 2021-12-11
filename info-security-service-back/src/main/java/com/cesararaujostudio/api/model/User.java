package com.cesararaujostudio.api.model;

import com.cesararaujostudio.api.bases.BaseEntity;
import com.cesararaujostudio.api.domain.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String encryptedPassword;

    private String city;

    private String phone;

    private LocalDate birthdate;

    private String comments;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @Transient
    private String newPassword;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<Role> roles;
}