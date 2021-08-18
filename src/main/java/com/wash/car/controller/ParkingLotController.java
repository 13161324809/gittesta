package com.wash.car.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wash.car.config.role.AuthPassport;
import com.wash.car.entity.ParkingLot;
import com.wash.car.service.IParkingLotService;
import com.wash.car.util.Constant;
import com.wash.car.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * <p>
 * 停车场 前端控制器
 * </p>
 *
 * @author wash-car
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/parkingLot")
@Api(tags = "停车场")
public class ParkingLotController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IParkingLotService iParkingLotService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @AuthPassport(menu = "parkingLot")
    @ResponseBody
    @ApiOperation(value="停车场列表")
    public ResultUtils list(ParkingLot ParkingLot, Page page) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<ParkingLot> q = new QueryWrapper<ParkingLot>();
            q.setEntity(ParkingLot);
            q.orderByDesc("id");
            IPage<ParkingLot> list = iParkingLotService.page(page,q);
            resultUtils.put("data",list);
        }catch (Exception e){
            logger.info("查询停车场信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/query",method = RequestMethod.GET)
    @AuthPassport(menu = "parkingLot")
    @ResponseBody
    public ResultUtils query(String id) {
        ResultUtils resultUtils = ResultUtils.ok();
        try{
            QueryWrapper<ParkingLot> q = new QueryWrapper<ParkingLot>();
            q.eq("id", id);

            ParkingLot ParkingLot = iParkingLotService.getOne(q);
            resultUtils.put("data",ParkingLot);
        }catch (Exception e){
            logger.info("查询停车场信息方法异常",e);
            return ResultUtils.error(999999,"查询失败，请稍后再试");
        }
        return resultUtils;
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @AuthPassport(menu = "parkingLot")
    @ResponseBody
    public ResultUtils update(@RequestBody ParkingLot ParkingLot, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            ParkingLot.setUpdateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            ParkingLot.setUpdateTime(LocalDateTime.now());

            iParkingLotService.updateById(ParkingLot);
        }catch (Exception e){
            logger.info("修改停车场信息方法异常",e);
            return ResultUtils.error(999999,"修改失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @AuthPassport(menu = "parkingLot")
    @ResponseBody
    public ResultUtils insert(ParkingLot ParkingLot, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            ParkingLot.setCreateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            ParkingLot.setCreateTime(LocalDateTime.now());

            iParkingLotService.save(ParkingLot);
        }catch (Exception e){
            logger.info("新增停车场信息方法异常",e);
            return ResultUtils.error(999999,"新增失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @AuthPassport(menu = "parkingLot")
    @ResponseBody
    public ResultUtils delete(ParkingLot ParkingLot, HttpServletRequest request) {
        try{
            HttpSession session = request.getSession();
            ParkingLot.setUpdateBy(Integer.valueOf(session.getAttribute(Constant.SESSION_USER_ID)+""));
            ParkingLot.setUpdateTime(LocalDateTime.now());
            ParkingLot.setIsDelete(Constant.IS_DELETE_1);
            iParkingLotService.updateById(ParkingLot);
        }catch (Exception e){
            logger.info("删除停车场信息方法异常",e);
            return ResultUtils.error(999999,"删除失败，请稍后再试");
        }
        return ResultUtils.ok();
    }

}
