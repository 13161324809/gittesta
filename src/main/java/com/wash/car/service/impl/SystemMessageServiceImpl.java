package com.wash.car.service.impl;

import com.wash.car.entity.SystemMessage;
import com.wash.car.mapper.SystemMessageMapper;
import com.wash.car.service.ISystemMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统通知 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class SystemMessageServiceImpl extends ServiceImpl<SystemMessageMapper, SystemMessage> implements ISystemMessageService {

}
