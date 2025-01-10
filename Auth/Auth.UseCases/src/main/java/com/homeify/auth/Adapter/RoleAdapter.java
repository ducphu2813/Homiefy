package com.homeify.auth.Adapter;

import com.homeify.auth.Entities.Role;

import java.util.List;

public interface RoleAdapter {

    //thêm role
    Role addRole(Role role);

    //sửa role
    Role updateRole(Role role);

    //xóa role
    void deleteRole(Role role);

    //lấy tat ca role
    List<Role> getAllRoles();
}
