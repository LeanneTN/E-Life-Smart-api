package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
