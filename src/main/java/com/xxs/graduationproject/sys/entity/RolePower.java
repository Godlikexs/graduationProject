package com.xxs.graduationproject.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
public class RolePower implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色权限ID
     */
      @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;


    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePower{" +
        "rolePermissionId=" + rolePermissionId +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
