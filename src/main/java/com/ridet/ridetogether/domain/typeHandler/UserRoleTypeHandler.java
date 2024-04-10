package com.ridet.ridetogether.domain.typeHandler;


import com.ridet.ridetogether.UserRole;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h2>UserRole enum의 Mybatis TypeHandler</h2>
 */
@MappedTypes(UserRole.class)
public class UserRoleTypeHandler implements TypeHandler<UserRole>
{
    @Override
    // 지정된 타입의 어떤 값을 DB에 저장할지 설정
    public void setParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    // Column 이름 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public UserRole getResult(ResultSet rs, String columnName) throws SQLException {
        String UserRoleKey = rs.getString(columnName);
        return getUserRole(UserRoleKey);
    }

    @Override
    // Column index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public UserRole getResult(ResultSet rs, int columnIndex) throws SQLException {
        String UserRoleKey = rs.getString(columnIndex);
        return getUserRole(UserRoleKey);
    }

    @Override
    // CallabaleStatement에서 Column index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public UserRole getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String UserRoleKey = cs.getString(columnIndex);
        return getUserRole(UserRoleKey);
    }

    private UserRole getUserRole(String UserRoleKey) {
        UserRole UserRole = null;
        switch (UserRoleKey) {
            case "ROLE_ADMIN":
                UserRole = UserRole.ADMIN;
                break;
            case "ROLE_USER":
                UserRole = UserRole.USER;
                break;
        }
        return UserRole;
    }
}
