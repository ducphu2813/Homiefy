package com.homeify.auth.UseCases;

import com.homeify.auth.Adapter.RoleAdapter;
import com.homeify.auth.Entities.Role;

import java.util.List;

public class RoleUsecase {

    private final RoleAdapter roleAdapter;

    public RoleUsecase(RoleAdapter roleAdapter) {
        this.roleAdapter = roleAdapter;
    }

    //thêm role
    public Role addRole(Role role) {
        return roleAdapter.addRole(role);
    }

    //sửa role
    public Role updateRole(Role role) {
        return roleAdapter.updateRole(role);
    }

    //xóa role
    public void deleteRole(Role role) {
        roleAdapter.deleteRole(role);
    }

    //lấy tat ca role
    public List<Role> getAllRoles() {
        return roleAdapter.getAllRoles();
    }
}
