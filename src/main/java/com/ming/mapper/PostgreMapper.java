package com.ming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liuming
 * @description
 * @date 2023/3/22
 */
@Mapper
@Repository
public interface PostgreMapper extends BaseMapper<GenTable> {
}
