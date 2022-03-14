package com.xxs.graduationproject.sys.service.impl;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.Power;
import com.xxs.graduationproject.sys.entity.Role;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.PowerMapper;
import com.xxs.graduationproject.sys.service.IPowerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
@Service
public class PowerService extends ServiceImpl<PowerMapper, Power> implements IPowerService {
    @Autowired(required = false)
    PowerMapper powerMapper;

    @Resource
    private  Result result;

    @Override
    public Result selectPowerByName(User user) {
        //查询权限
        List<Role> roles = user.getRoles();
        List<Power> powers =new ArrayList<>();//创建后端接收权限集合
        for (Role role : roles) {
            powers = powerMapper.queryOneByRoleName(role.getRoleName());
        }
        /*List<Power> list=mapper.selectPowerByName(userName);

        System.err.println(list.toString());*/
        //创建一个前端的权限集合数据
        ArrayList<Power> newList = new ArrayList<>();
        if (powers!=null){
            //遍历数据库的权限信息，构建前端的权限集合数据
            for (Power n:powers){
                //判断一级菜单
                if(n.getParentId()==0){
                    ArrayList<Power> childrenList = new ArrayList<>();
                    //查询一级的子菜单
                    for (Power nn:powers){
                        if (nn.getParentId()==n.getNodeId()){
                            childrenList.add(nn);
                        }
                    }
                    //把子菜单添加到一级菜单里面
                    n.setChildren(childrenList);
                    //添加到前端的权限集合数据
                    newList.add(n);
                }
            }
        }
        if (newList.size()>0){
            result.setData(newList);
            result.setCode(200);
        }
        return result;
    }
}
