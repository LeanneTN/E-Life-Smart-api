package org.csu;

import net.sf.jsqlparser.statement.select.Top;
import org.csu.domain.*;
import org.apache.ibatis.jdbc.Null;

import org.csu.mapper.*;
import org.csu.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private HealthCheckService healthCheckService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;
    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AcidService acidService;

    @Test
    public void testPasswordEncoder(){
        String s1 = passwordEncoder.encode("1234");
        String s2 = passwordEncoder.encode("1234");

        System.out.println(passwordEncoder.matches( "1234", s1));

    }


    @Test
    public void testUpdateParkingSpace(){
//        ParkingSpace parkingSpace = new ParkingSpace("A01", "","0");
//        parkingSpaceMapper.updateById(parkingSpace);
        List<Parking> parkings = parkingMapper.selectList(null);
        System.out.println(parkings);
    }


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
    public void TestBCryptPasswordEncoder() {
        System.out.println(paymentService.getIncome().getData().toString());
    }


    @Test
    public void testAPI(){
        Acid acid = new Acid();
        acid.setUid(11L);
        acid.setName("zhangsan");
        acid.setResult("negative");
        acidService.insertAcid(acid);
        acidService.getAcid();
    }

    @Test
    public void forumTest(){
        //测试创建话题
//        Topic topic=new Topic();
//        topic.setFromUser(123);
//        topic.setTitle("测试");
//        forumService.createTopic(topic);
        int a=1;
        long b=(int)a;
        //测试举报话题
        forumService.reportTopicById(b);
    }

}
