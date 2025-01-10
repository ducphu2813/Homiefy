package com.homeify.auth.authapi.Controller;

import com.homeify.auth.Entities.Users;
import com.homeify.auth.UseCases.UserUsecase;
import com.homeify.auth.authapi.DTO.UsersDTO;
import com.homeify.auth.authapi.Mapper.UserDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    //inject use case
    private final UserUsecase userUsecase;

    //mapper
    private final UserDTOMapper usersMapper;

    public UsersController(UserUsecase userUsecase, UserDTOMapper usersMapper) {
        this.userUsecase = userUsecase;
        this.usersMapper = usersMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<UsersDTO> getAll() {

        List<Users> users = userUsecase.getAllUsers();

        //chuyển từ List<Users> sang List<UsersDTO>
        //dùng for
        List<UsersDTO> usersDTOs = new ArrayList<>();
        for (Users u : users) {
            UsersDTO usersDTO = usersMapper.toUserDTO(u);
            usersDTOs.add(usersDTO);
        }

        return usersDTOs;
    }

    //thêm
    @PostMapping("/add")
    public UsersDTO add(@RequestBody UsersDTO usersDTO) {

        Users u = usersMapper.toUser(usersDTO);

        u = userUsecase.addUser(u);

        return usersDTO;

    }

    //sửa
    @PutMapping("/update")
    public UsersDTO update(@RequestBody UsersDTO usersDTO, @RequestParam String id) {

        Users u = usersMapper.toUser(usersDTO);

        u = userUsecase.updateUser(u, id);

        return usersDTO;
    }

    //xóa
    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        userUsecase.deleteUser(id);
    }


}
