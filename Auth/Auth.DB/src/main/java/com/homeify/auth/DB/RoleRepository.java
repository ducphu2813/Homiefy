package com.homeify.auth.DB;

import com.homeify.auth.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, String> {
}
