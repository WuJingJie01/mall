package com.ptu.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ptu.mall.domain.dto.UserUpdateDTO;
import com.ptu.mall.domain.po.User;
import com.ptu.mall.domain.vo.UserVO;
import com.ptu.mall.mapper.UserMapper;
import com.ptu.mall.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ptu.mall.utils.MD5Util;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVO getByLogin(String phone, String password) {

        String MD5Password = MD5Util.MD5Encode(password, "UTF-8");
        User user = lambdaQuery()
                .eq(User::getPhone, phone)
                .eq(User::getPassword, MD5Password).one();

        if (user == null) {
            return null;
        }

        return UserVO.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .nickName(user.getNickName()).build();
    }

    @Override
    public boolean updateUser(UserUpdateDTO updateDTO) {
        User user = BeanUtil.copyProperties(updateDTO, User.class);
        return updateById(user);
    }
}
