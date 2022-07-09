package org.csu.service.impl;

import org.csu.domain.Acid;
import org.csu.mapper.AcidMapper;
import org.csu.service.AcidService;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcidServiceImpl implements AcidService {
    @Autowired
    private AcidMapper acidMapper;

    @Override
    public ResponseResult getAcid() {
        List<Acid> acidList = acidMapper.selectList(null);
        if(acidList.size() == 0){
            return new ResponseResult(ResponseCode.ACID_NOT_GET.getCode(), "暂无核酸结果");
        }
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "核酸结果收取成功", acidList);
    }

    @Override
    public ResponseResult insertAcid(Acid acid) {
        acidMapper.insert(acid);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "核酸结果插入成功");
    }
}
