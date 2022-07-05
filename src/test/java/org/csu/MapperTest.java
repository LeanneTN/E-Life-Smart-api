package org.csu;

import net.sf.jsqlparser.statement.select.Top;
import org.csu.domain.HealthCheck;
import org.csu.domain.Topic;
import org.apache.ibatis.jdbc.Null;
import org.csu.domain.Parking;
import org.csu.domain.ParkingSpace;

import org.csu.domain.User;
import org.csu.domain.Volunteer;
import org.csu.mapper.MenuMapper;
import org.csu.mapper.ParkingMapper;
import org.csu.mapper.ParkingSpaceMapper;
import org.csu.mapper.UserMapper;
import org.csu.service.ForumService;
import org.csu.service.HealthCheckService;
import org.csu.service.VolunteerService;
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
        //加密
//        String encode = passwordEncoder.encode("123456");
//        System.out.println(encode);
        //校验
        boolean matches = passwordEncoder.matches("{noop}123456", "123456");
        System.out.println(matches);
    }


    @Test
    public void testAPI(){
        System.out.println(volunteerService.getLogsGroupById().getData().toString());
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
