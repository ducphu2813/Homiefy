package com.homeify.auth.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "roles")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
