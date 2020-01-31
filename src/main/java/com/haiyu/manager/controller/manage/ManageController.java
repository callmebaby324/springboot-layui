package com.haiyu.manager.controller.manage;

import com.haiyu.manager.dto.MovecarCodeDTO;
import com.haiyu.manager.dto.MovecarSearchDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.MovecarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @Title: ManageController
 * @Description: 挪车码管理
 * @author: baby
 * @version: 1.0
 * @date: 2018/11/21 13:43
 */
@Controller
@Slf4j
@RequestMapping("manage")
public class ManageController {

    @Autowired
    private MovecarService movecarService;
    /**
     * 跳转到挪车码管理
     * @return
     */
    @RequestMapping("/codeManage")
    public String toPage() {
        log.info("进入角色管理");
        return "/manage/codeManage";
    }

    /**
     * 挪车码列表
     * @param pageNum
     * @param pageSize
     * @param movecarSearchDTO
     * @return
     */
    @RequestMapping("/codeList")
    @ResponseBody
    public PageDataResult getList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize,
                                  MovecarSearchDTO movecarSearchDTO) {
        log.info("[挪车码列表]in={}",movecarSearchDTO);
        PageDataResult result = movecarService.getList(pageNum,pageSize,movecarSearchDTO);
        return result;
    }


    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String, String> updateStatus(MovecarCodeDTO movecarCodeDTO) {
        log.info("[停用/恢复挪车码]in={}",movecarCodeDTO);
        Map<String,String> result = new HashMap<>();
        if(movecarCodeDTO==null || StringUtils.isEmpty(movecarCodeDTO.getCode())
                || StringUtils.isEmpty(movecarCodeDTO.getStatus())){
            result.put("code","-99");
            result.put("msg","缺少参数");
            return result;
        }
        movecarService.updateStatus(movecarCodeDTO);
        result.put("code","0");
        result.put("msg","操作成功");
        return result;
    }


}
