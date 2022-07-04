package org.csu;

import org.csu.domain.User;
import org.csu.mapper.MenuMapper;
import org.csu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUserName("123456");
        user.setPassword("123456");
        int insert = userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        //加密
//        String encode = passwordEncoder.encode("123456");
//        System.out.println(encode);
        //校验
        boolean matches = passwordEncoder.matches("{noop}123456", "123456");
        System.out.println(matches);
    }


    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
