package com.ridet.ridetogether.domain.mapper;

import com.ridet.ridetogether.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 생성된 User의 id를 반환함
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO users (email, password, name, gender, role, isActive)" +
            "VALUES (#{email}, #{password}, #{name}, #{gender}, #{role}, #{isActive})")
    int add(@Param("email") String email,
             @Param("password") String password,
             @Param("name") String name,
             @Param("gender") String gender,
             @Param("role") String role,
             @Param("isActive") int isActive);

    @Select("SELECT * FROM users where id = #{userId}")
    User getUserById(@Param("userId") int userId);

    @Select("SELECT * FROM users where email = #{email}")
    User getUserByEmail(@Param("email") String email);

    @Delete("DELETE FROM users where id = #{userId}")
    void delete(@Param("userId") int userId);
}
