package com.ridet.ridetogether.config;

import com.ridet.ridetogether.domain.typeHandler.GenderTypeHandler;
import com.ridet.ridetogether.domain.typeHandler.UserRoleTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/ 참고
@Configuration
@MapperScan(basePackages = {"com.ridet.ridetogether.domain.mapper"}) // 해당 패키지 아래의 인터페이스들은 전부 mapper로 인식됨
public class MyBatisConfig {
    // applicaiton.properties의 설정을 가져옴
    @Value("${spring.datasource.driverClassName}")
    private String dbDriverClassName;
    @Value("${spring.datasource.url}")
    private String dbJdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        // enum타입을 다루기 위한 Custom TypeHandler 추가
        factoryBean.setTypeHandlers(new TypeHandler[] {
                new GenderTypeHandler(),
                new UserRoleTypeHandler()
        });
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName(this.dbDriverClassName);
        dataSourceBuilder.url(this.dbJdbcUrl);
        dataSourceBuilder.username(this.username);
        dataSourceBuilder.password(this.password);

        return dataSourceBuilder.build();
    }

}
