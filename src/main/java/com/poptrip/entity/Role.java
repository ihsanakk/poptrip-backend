package com.poptrip.entity;

import com.poptrip.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ERole roleName;

    public Role(ERole roleName) {
        this.roleName = roleName;
    }
}
