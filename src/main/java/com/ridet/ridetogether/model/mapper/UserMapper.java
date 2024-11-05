package com.ridet.ridetogether.model.mapper;

import com.ridet.ridetogether.dto.user.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    int add(User user);

    int delete(int userId);

    User findUserById(int userId);

    User findUserByEmail(String email);


}
