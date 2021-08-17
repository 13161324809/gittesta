package com.wash.car.service.impl;

import com.wash.car.entity.User;
import com.wash.car.mapper.UserMapper;
import com.wash.car.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
