package com.yt.bishe.controller;


import com.yt.bishe.entity.Shop;
import com.yt.bishe.entity.TradeCar;
import com.yt.bishe.entity.User;
import com.yt.bishe.service.ShopService;
import com.yt.bishe.service.TradeCarService;
import com.yt.bishe.service.UserService;
import com.yt.bishe.utils.MailUtil;
import com.yt.bishe.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public int UserLogin(HttpServletRequest request,String userName,String password){

        String Pwd = Md5.getMd5(password);
        boolean flag = userService.loginCheck(userName,Pwd);

        if (flag){
            request.getSession().setAttribute("userName",userName);
            return 1;
        }

        return 2;
    }

    /**
     * 用户名检测
     * @param name
     * @return
     */
    @RequestMapping("/do_checkUserName")
    @ResponseBody
    public int checkRegisterName( String name){

        if (userService.checkUserName(name))
            return 0;
        else{
            return 1;
        }

    }

    /**
     * 用户注册
     */
    @Autowired
    private ShopService shopService;
    @Autowired
    private TradeCarService tradeCarService;
    @RequestMapping("/doRegist")
    @ResponseBody
    public ModelAndView doRegist(@RequestParam String userName, @RequestParam String password,
                                 @RequestParam String telephone, @RequestParam String email,
                                 @RequestParam String address, ModelAndView modelAndView, User user, Shop shop, HttpServletRequest request){



        user.setUserName(userName);
        user.setPassword(Md5.getMd5(password));
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setAddress(address);
        if ( userService.saveUserInfo(user)){
            //自动为用户生成商铺
            shop.setUserName(userName);
            shop.setShopId(UUID.randomUUID().toString());
            shopService.saveShopInfo(shop);
            //自动为用户生成购物车
            TradeCar tradeCar = new TradeCar();
            tradeCar.setUserName(userName);
            tradeCar.setTradeCarId(UUID.randomUUID().toString()+userName);
            tradeCarService.registerTradeCar(tradeCar);

            request.getSession().setAttribute("userName",userName);
            modelAndView.addObject("userName",userName);
            modelAndView.setViewName("registSuccess");

        }return modelAndView;

    }

    /**
     * 查询用户信息返回给前台
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ModelAndView getUserInfo(ModelAndView modelAndView,HttpServletRequest request){
        String userName = (String)request.getSession().getAttribute("userName");
        User user = userService.getUserInfo(userName);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user");

        return modelAndView;
    }

    /**
     * 修改用户的信息
     */
    @RequestMapping("/reviseUserInfo")
    @ResponseBody
    public ModelAndView reviseUserInfo(User user,ModelAndView modelAndView){
        if(userService.reviseUserInfo(user)){
            modelAndView.addObject("user",user);
            modelAndView.setViewName("user");
        }
        return modelAndView;
    }

    @Autowired
    MailUtil mailUtil;
    /**
     * 获取邮箱验证码
     */
    @RequestMapping("/getCodeByEmail")
    @ResponseBody
    public String getCodeByEmail(HttpServletRequest request){
        String userName = (String) request.getSession().getAttribute("userName");
        String mail = userService.getUserInfo(userName).getEmail();
        String code = String.valueOf(new Random().nextInt(899999)+100000);
        String message = "您的验证码为："+code;
        try {
            mailUtil.sendCodetoMail(mail,message);
            System.out.println("发送成功");
        }catch (Exception e){
            return "";
        }
        return code;
    }

    /**
     * 修改用户密码
     * @param pwd
     */
    @RequestMapping("/revisePassword")
    @ResponseBody
    public String revisePassword(@RequestParam String pwd,HttpServletRequest request){
        String userName = (String) request.getSession().getAttribute("userName");
        try {
            String password = Md5.getMd5(pwd);
            userService.revisePasswrod(userName,password);
            return "ok";
        }catch (Exception e){
            return "";
        }
    }

    @RequestMapping("/getUserNameInSession")
    @ResponseBody
    public ModelAndView getUserNameInSession(HttpServletRequest request,ModelAndView modelAndView){
        String userName = (String)request.getSession().getAttribute("userName");
        System.out.println(userName);
        modelAndView.addObject("userName",userName);
        modelAndView.setViewName("top-part");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,ModelAndView modelAndView){
        request.getSession().invalidate();//移除session中的所有数据
        modelAndView.setViewName("/index");
        return modelAndView;
    }

}
