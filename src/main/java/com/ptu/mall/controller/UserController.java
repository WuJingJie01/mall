package com.ptu.mall.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ptu.mall.domain.dto.PageQueryDTO;
import com.ptu.mall.domain.dto.UserDTO;
import com.ptu.mall.domain.dto.UserRegDTO;
import com.ptu.mall.domain.dto.UserUpdateDTO;
import com.ptu.mall.domain.po.User;
import com.ptu.mall.domain.vo.ResponseResult;
import com.ptu.mall.domain.vo.UserVO;
import com.ptu.mall.service.IUserService;
import com.ptu.mall.utils.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final HttpSession httpSession;
    @PostMapping("/login")
    public ResponseResult login(UserDTO userDTO) {
        // 空判断
        if (StrUtil.isBlank(userDTO.getPhone())) {
            return ResponseResult.failResult("手机号不能为空");
        }
        if (StrUtil.isEmpty(userDTO.getPassword())) {
            return ResponseResult.failResult("密码不能为空");
        }
        if (StrUtil.isEmpty(userDTO.getVerifyCode())) {
            return ResponseResult.failResult("验证码不能为空");
        }

        // 逻辑性判断
        if (!userDTO.getPhone().matches("^\\d{11}$")) {
            // 不是手机号
            return ResponseResult.failResult("手机号格式错误");
        }

        // 验证码判断
        if (!userDTO.getVerifyCode().toLowerCase().
                equals(httpSession.getAttribute("verifyCode").toString())) {
            // 验证码错误
            return ResponseResult.failResult("验证码错误");
        }

        // 数据库中是否存在指定数据
        UserVO userVO = userService.getByLogin(userDTO.getPhone(), userDTO.getPassword());
        if (userVO == null) {
            return ResponseResult.failResult("手机号或密码错误");
        }

        httpSession.setAttribute("userId", userVO.getId());

        return ResponseResult.okResult(userVO);
    }

    @PostMapping("/reg")
    public ResponseResult reg(UserRegDTO userRegDTO) {
        // 判空
        if (StrUtil.isEmpty(userRegDTO.getPhone())) {
            return ResponseResult.failResult("手机号不能为空");
        }
        if (StrUtil.isEmpty(userRegDTO.getPassword())) {
            return ResponseResult.failResult("密码不能为空");
        }
        if (StrUtil.isEmpty(userRegDTO.getPassword2())) {
            return ResponseResult.failResult("请输入确认密码");
        }
        if (StrUtil.isEmpty(userRegDTO.getVerifyCode())) {
            return ResponseResult.failResult("验证码不能为空");
        }

        // 密码是否相同
        if (!userRegDTO.getPassword().equals(userRegDTO.getPassword2())) {
            return ResponseResult.failResult("输入的两次密码不同");
        }

        // 手机号是否合规
        if (!userRegDTO.getPhone().matches("^\\d{11}$")) {
            return ResponseResult.failResult("手机号格式错了");
        }

        // 判断验证码
        String verifyCode = httpSession.getAttribute("verifyCode").toString();
        if (!userRegDTO.getVerifyCode().toLowerCase().equals(verifyCode)) {
            return ResponseResult.failResult("验证码错误");
        }

        // 判断数据库中是否存在该用户
        Long count = userService.lambdaQuery().eq(User::getPhone, userRegDTO.getPhone()).count();
        if (count != 0) {
            return ResponseResult.failResult("用户注册过了");
        }

        // 新增数据库数据
        String passwordMD5 = MD5Util.MD5Encode(userRegDTO.getPassword(), "UTF-8");

        User user = User.builder()
                .phone(userRegDTO.getPhone())
                .password(passwordMD5)
                .nickName("用户" + UUID.randomUUID())
                .build();
        boolean isAdd = userService.save(user);
        if (isAdd) {
            return ResponseResult.okResult();
        } else {
            return ResponseResult.failResult("注册失败");
        }

    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        httpSession.removeAttribute("userId");
        return ResponseResult.okResult();
    }

    @GetMapping("/isLogin")
    public ResponseResult isLogin() {
        Object userId = httpSession.getAttribute("userId");
        if (userId == null) {
            return ResponseResult.failResult("未登录");
        }
        User user = userService.getById((Integer) userId);
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        return ResponseResult.okResult(userVO);
    }


    @GetMapping("/admin/list")
    public ResponseResult getUsers(PageQueryDTO pageQueryDTO) {
        Page<User> page = userService.page(Page.of(pageQueryDTO.getPageNum(), pageQueryDTO.getPageSize()));
        List<User> records = page.getRecords();
        List<UserVO> userVOS = BeanUtil.copyToList(records, UserVO.class);
        return ResponseResult.okResult(userVOS);
    }

    @GetMapping("/admin/{id}")
    public ResponseResult getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseResult.failResult("查找失败");
        }
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        return ResponseResult.okResult(userVO);
    }

    @PostMapping("/admin/update")
    public ResponseResult updateUser(@RequestBody UserUpdateDTO updateDTO) {
        boolean flag = userService.updateUser(updateDTO);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("更新失败");
    }

    @PostMapping("/admin/delete")
    public ResponseResult deleteBatches(@RequestBody List<Integer> ids, HttpSession session) {
        for (Integer id : ids) {
            if (Integer.parseInt(session.getAttribute("userId").toString()) == id) {
                return ResponseResult.failResult("不能删除自己");
            }
        }
        boolean flag = userService.removeBatchByIds(ids);
        if (flag) {
            return ResponseResult.okResult();
        }
        return ResponseResult.failResult("删除失败");
    }
}
