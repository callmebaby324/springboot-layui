package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.dao.MovecarCodeMapper;
import com.haiyu.manager.dto.MovecarCodeDTO;
import com.haiyu.manager.dto.MovecarSearchDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.MovecarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovecarServiceImpl implements MovecarService {

    @Autowired
    private MovecarCodeMapper movecarCodeMapper;

    @Override
    public PageDataResult getList(Integer pageNum, Integer pageSize, MovecarSearchDTO movecarSearchDTO) {
        PageDataResult pageDataResult = new PageDataResult();
        List<MovecarCodeDTO> list = movecarCodeMapper.selectByCondition(movecarSearchDTO);

        PageHelper.startPage(pageNum, pageSize);

        if(list.size() != 0){
            PageInfo<MovecarCodeDTO> pageInfo = new PageInfo<>(list);
            pageDataResult.setList(list);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public void updateStatus(MovecarCodeDTO movecarCodeDTO) {
        movecarCodeDTO.setStatus(movecarCodeDTO.getStatus()==1?2:1);
        movecarCodeMapper.updateStatus(movecarCodeDTO);
    }
}
