package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.Power;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
@Mapper
public interface PowerMapper extends BaseMapper<Power> {
    List<Power> queryOneByRoleName(String roleName);
}
