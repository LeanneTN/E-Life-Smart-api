package org.csu.service;

import org.csu.domain.Acid;
import org.csu.vo.ResponseResult;
import org.springframework.stereotype.Service;

public interface AcidService {
    //获取核酸检测结果接口
    ResponseResult getAcid();

    //插入核酸检测结果接口
    ResponseResult insertAcid(Acid acid);
}
