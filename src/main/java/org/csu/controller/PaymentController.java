package org.csu.controller;

import cn.hutool.core.util.RandomUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.jsonwebtoken.Claims;
import org.csu.domain.AlipayConfig;
import org.csu.domain.Payment;
import org.csu.uitls.JwtUtil;
import org.csu.vo.ResponseResult;
import org.csu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    //支付行为pay是指为订单order付钱，他们都是sys_payment中的条目，未支付订单和已支付订单的区别在于
    //是否if_paid项是否为真

    //支付该用户的所有缴费项
    @PostMapping("/pay")
    public ResponseResult pay(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return paymentService.pay(userId);
    }

    //支付某条缴费单
    @PostMapping("/payById")
    public ResponseResult payById(HttpServletRequest request, @RequestBody Payment payment){
        String token = request.getHeader("token");
        Long id = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            id = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return paymentService.payById(id, payment);
    }

    //获取某个用户的所有支付的订单，前端可以进行统计信息的展示(用户)
    @GetMapping("/orders")
    public ResponseResult getOrders(HttpServletRequest request){
        String token = request.getHeader("token");
        Long id = null;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            id = Long.valueOf(claims.getSubject());
        }catch (Exception e){
            e.printStackTrace();
        }
        return paymentService.getOrders(id);
    }

    //获取全部订单(后台管理使用)
    @GetMapping("/income")
    @PreAuthorize("hasAuthority('system:payment:income')")
    public ResponseResult getIncome(){
        return paymentService.getIncome();
    }


    @RequestMapping("/pay/alipay")
    public ResponseResult payController(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Payment payment) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String newOrderId = DigestUtils.md5DigestAsHex(RandomUtil.randomNumbers(5).getBytes(StandardCharsets.UTF_8));

        String totalPrice = Double.toString(payment.getSum());
        String billSubject = payment.getToAdmin()+" "+payment.getType();
        String describe = payment.getFromUser() + " " + payment.getType() + " " + payment.getTime();

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = newOrderId;
        //付款金额，必填
        String total_amount = totalPrice;
        //订单名称，必填
        String subject = billSubject;
        //商品描述，可空
        String body = describe;

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

        return paymentService.payById(payment.getId(), payment);
    }
}
