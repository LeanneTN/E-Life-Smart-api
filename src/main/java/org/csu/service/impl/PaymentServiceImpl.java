package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.csu.domain.Payment;
import org.csu.domain.Raw;
import org.csu.domain.User;
import org.csu.service.UserService;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.mapper.PaymentMapper;
import org.csu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult pay(Long uid) {
        System.out.println(uid);
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user", uid);
        queryWrapper.eq("if_paid", false);
        List<Payment> paymentList = paymentMapper.selectList(queryWrapper);
        UpdateWrapper<Payment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("if_paid",false);
        updateWrapper.eq("from_user", uid).set("if_paid", true);
        paymentMapper.update(null,updateWrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "用户所有订单支付成功",paymentList);
    }

    @Override
    public ResponseResult payById(Long id, Payment payment) {
        Payment payment1 = paymentMapper.selectById(id);
        if(payment1!= null&& payment1.isIf_paid()){
            return new ResponseResult(ResponseCode.PAYMENT_EXIST.getCode(), "订单已支付", payment1);
        }else if(payment1!=null&&!payment1.isIf_paid()){
            payment.setIf_paid(true);
            paymentMapper.updateById(payment);
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "支付成功", payment);
        }else if(payment1==null) {
            payment.setIf_paid(true);
            paymentMapper.insert(payment);
            return new ResponseResult(ResponseCode.SUCCESS.getCode(), "支付成功", payment);
        }
        return new ResponseResult(ResponseCode.ERROR.getCode(), "支付失败");
    }

    @Override
    public ResponseResult getOrders(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("from_user",id);
        queryWrapper.eq("if_paid",true);
        List<Payment> paymentList = paymentMapper.selectList(queryWrapper);
        for(int i = 0; i < paymentList.size(); i++){
            paymentList.get(i).setIf_paid(true);
        }
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "用户所有订单获取成功", paymentList);
    }

    @Override
    public ResponseResult getIncome() {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "所有用户订单获取成功", paymentMapper.selectList(null));
    }

    @Override
    public ResponseResult getToPaid(HttpServletRequest req) {
        User user = (User) userService.getLoginUser(req).getData();

        LambdaQueryWrapper<Payment> wrapper = new QueryWrapper<Payment>().lambda();
        wrapper.eq(Payment::getFromUser, user.getId());
        wrapper.eq(Payment::isIf_paid, false);
        List<Payment> payments = paymentMapper.selectList(wrapper);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "成功获取所有未支付缴费", payments);
    }

    @Override
    public ResponseResult payChecked(List<Payment> paymentList) {
        int len = paymentList.size();
        for(int i = 0;i < len;i++){
            paymentList.get(i).setIf_paid(true);
            paymentList.get(i).setFinishTime(new Date());
            paymentMapper.updateById(paymentList.get(i));
        }
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "支付成功");
    }

    //新增订单
    @Override
    public ResponseResult createPayment(Payment payment) {
        if(payment == null){
            return new ResponseResult(ResponseCode.ERROR_DATA.getCode(), "传来的订单数据为空");
        }
        paymentMapper.insert(payment);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "创建订单成功");
    }

    //根据ID删除订单
    @Override
    public ResponseResult deletePaymentById(long id) {
        Payment payment = paymentMapper.selectById(id);
        if(payment==null){
            return new ResponseResult(ResponseCode.PAYMENT_NOT_EXIT.getCode(), "订单不存在");
        }
        paymentMapper.deleteById(id);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "删除订单成功");
    }

    //更新订单
    @Override
    public ResponseResult updatePayment(Payment payment) {
        Payment newPayment = paymentMapper.selectById(payment.getId());
        if(newPayment==null) {
            return new ResponseResult(ResponseCode.PAYMENT_NOT_EXIT.getCode(), "订单不存在");
        }
        paymentMapper.updateById(payment);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "更新订单成功");
    }
}
