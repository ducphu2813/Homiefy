package com.homeify.auth.Mapper;

import com.homeify.auth.Entities.Users;
import com.homeify.auth.Model.UsersModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {

    //chuyển từ UserModel sang User
    Users toUser(UsersModel userModel);

    //chuyển từ User sang UserModel
    UsersModel toUserModel(Users user);

    //chuyển từ List<UserModel> sang List<User>
    List<Users> toUsers(List<UsersModel> usersModel);

    //chuyển từ List<User> sang List<UserModel>
    List<UsersModel> toUsersModel(List<Users> users);
}
