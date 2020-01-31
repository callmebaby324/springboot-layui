package com.haiyu.manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * movecar_code
 * @author 
 */
@Data
public class MovecarCodeDTO implements Serializable {
    private String code;

    private String account;

    private String phone;

    private String carNo;

    /**
     * 挪车码状态：0-未激活 1-使用中 2-已停用 3-已删除
     */
    private Integer status;

    /**
     * 申请公司
     */
    private Integer supplier;

    /**
     * 激活时间
     */
    private Date activeTime;

    /**
     * 数据有效性：0-无效 1-有效
     */
    private Integer dataStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String updateBy;

}