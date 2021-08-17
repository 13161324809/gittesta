package com.wash.car.service.impl;

import com.wash.car.entity.Order;
import com.wash.car.mapper.OrderMapper;
import com.wash.car.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
