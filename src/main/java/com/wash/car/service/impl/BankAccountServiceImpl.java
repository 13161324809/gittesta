package com.wash.car.service.impl;

import com.wash.car.entity.BankAccount;
import com.wash.car.mapper.BankAccountMapper;
import com.wash.car.service.IBankAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 银行卡 服务实现类
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@Service
public class BankAccountServiceImpl extends ServiceImpl<BankAccountMapper, BankAccount> implements IBankAccountService {

}
