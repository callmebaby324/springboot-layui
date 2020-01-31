package com.haiyu.manager.service;

import com.haiyu.manager.dto.MovecarCodeDTO;
import com.haiyu.manager.dto.MovecarSearchDTO;
import com.haiyu.manager.response.PageDataResult;

public interface MovecarService {

    PageDataResult getList(Integer pageNum, Integer pageSize, MovecarSearchDTO movecarSearchDTO);

    void updateStatus(MovecarCodeDTO movecarCodeDTO);
}
