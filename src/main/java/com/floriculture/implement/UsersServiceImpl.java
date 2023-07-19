package com.floriculture.implement;

import com.floriculture.mysql.Users;
import com.floriculture.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UserService{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users save(Users curso) {
        return usersRepository.save(curso);
    }
}
