package com.wash.car.service.impl;

import com.wash.car.entity.TransactionDetails;
import com.wash.car.mapper.TransactionDetailsMapper;
import com.wash.car.service.ITransactionDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流水表 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class TransactionDetailsServiceImpl extends ServiceImpl<TransactionDetailsMapper, TransactionDetails> implements ITransactionDetailsService {

}
