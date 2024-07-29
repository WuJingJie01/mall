package com.ptu.mall.service;

import com.ptu.mall.domain.dto.UserUpdateDTO;
import com.ptu.mall.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ptu.mall.domain.vo.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuJingJie
 * @since 2024-07-28
 */
public interface IUserService extends IService<User> {

    UserVO getByLogin(String phone, String password);

    boolean updateUser(UserUpdateDTO updateDTO);
}
