package com.wash.car.service.impl;

import com.wash.car.entity.OrderComment;
import com.wash.car.mapper.OrderCommentMapper;
import com.wash.car.service.IOrderCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单评价 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements IOrderCommentService {

}
