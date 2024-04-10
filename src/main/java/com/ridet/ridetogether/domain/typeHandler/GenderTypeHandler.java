package com.ridet.ridetogether.domain.typeHandler;


import com.ridet.ridetogether.Gender;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <h2>Gender enum의 Mybatis TypeHandler</h2>
 */
@MappedTypes(Gender.class)
public class GenderTypeHandler implements TypeHandler<Gender>
{
    @Override
    // 지정된 타입의 어떤 값을 DB에 저장할지 설정
    public void setParameter(PreparedStatement ps, int i, Gender parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    // Column 이름 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public Gender getResult(ResultSet rs, String columnName) throws SQLException {
        String genderKey = rs.getString(columnName);
        return getGender(genderKey);
    }

    @Override
    // Column index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public Gender getResult(ResultSet rs, int columnIndex) throws SQLException {
        String genderKey = rs.getString(columnIndex);
        return getGender(genderKey);
    }

    @Override
    // CallabaleStatement에서 Column index 기반으로 조회된 값을 활용해서 실제 반환할 객체 구성하기
    public Gender getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String genderKey = cs.getString(columnIndex);
        return getGender(genderKey);
    }

    private Gender getGender(String genderKey) {
        Gender gender = null;
        switch (genderKey) {
            case "MALE":
                gender = Gender.MALE;
                break;
            case "FEMALE":
                gender = Gender.FEMALE;
                break;
        }
        return gender;
    }
}
