package com.haiyu.manager.dao;

import com.haiyu.manager.dto.AdminUserDTO;
import com.haiyu.manager.dto.MovecarCodeDTO;
import com.haiyu.manager.dto.MovecarSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovecarCodeMapper {
    int deleteByPrimaryKey(String code);

    int insert(MovecarCodeDTO record);

    int insertSelective(MovecarCodeDTO record);

    MovecarCodeDTO selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(MovecarCodeDTO record);

    int updateByPrimaryKey(MovecarCodeDTO record);

    List<MovecarCodeDTO> selectByCondition(MovecarSearchDTO movecarSearchDTO);

    void updateStatus(MovecarCodeDTO movecarCodeDTO);
}