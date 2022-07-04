package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userid);
}
