package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.Car;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMapper extends BaseMapper<Car> {
}
