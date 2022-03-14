package com.xxs.graduationproject.sys.service;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.Power;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxs.graduationproject.sys.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
public interface IPowerService extends IService<Power> {
    Result selectPowerByName(User user);
}
