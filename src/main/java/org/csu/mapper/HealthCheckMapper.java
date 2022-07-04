package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.HealthCheck;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthCheckMapper extends BaseMapper<HealthCheck> {
}
