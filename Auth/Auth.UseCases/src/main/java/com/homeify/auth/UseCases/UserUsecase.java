package com.homeify.auth.UseCases;

import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.Entities.Users;

import java.util.List;

public class UserUsecase {

    private final UsersAdapter userAdapter;

    public UserUsecase(UsersAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    //thêm user
    public Users addUser(Users user) {
        user.setId(String.valueOf(System.currentTimeMillis()));

        return userAdapter.addUser(user);
    }

    //sửa user
    public Users updateUser(Users user, String userId) {

        //tìm user theo id
        Users userById = userAdapter.findUserById(userId);

        user.setId(userById.getId());

        return userAdapter.updateUser(user);
    }

    //xóa user
    public void deleteUser(String userId) {
        userAdapter.deleteUser(userId);
    }

    //lấy tat ca user
    public List<Users> getAllUsers() {
        return userAdapter.getAllUsers();
    }
}
