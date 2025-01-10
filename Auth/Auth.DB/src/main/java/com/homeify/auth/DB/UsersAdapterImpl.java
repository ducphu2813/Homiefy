package com.homeify.auth.DB;

import com.homeify.auth.Adapter.UsersAdapter;
import com.homeify.auth.Entities.Users;
import com.homeify.auth.Mapper.UsersMapper;
import com.homeify.auth.Model.UsersModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersAdapterImpl implements UsersAdapter {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersAdapterImpl(UsersRepository usersRepository
                            , UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional
    public Users addUser(Users user) {
        //dùng mapper
        UsersModel userModel = usersMapper.toUserModel(user);

        userModel = usersRepository.save(userModel);

        return usersMapper.toUser(userModel);
    }

    @Override
    @Transactional
    public Users updateUser(Users user) {
        //dùng mapper
        UsersModel userModel = usersMapper.toUserModel(user);

        userModel = usersRepository.save(userModel);

        return usersMapper.toUser(userModel);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {

        usersRepository.deleteById(userId);
    }

    @Override
    public List<Users> getAllUsers() {
        List<UsersModel> usersModel = usersRepository.findAll();

        return usersMapper.toUsers(usersModel);
    }

    @Override
    public Users findUserById(String userId) {
        UsersModel userModel = usersRepository.findById(userId).orElse(null);

        return usersMapper.toUser(userModel);
    }
}
