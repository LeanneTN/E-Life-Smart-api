package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.VolunteerLog;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerMapper extends BaseMapper<VolunteerLog> {
}
