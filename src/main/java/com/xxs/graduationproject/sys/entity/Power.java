package com.xxs.graduationproject.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.shiro.authz.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
public class Power implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "power_id", type = IdType.AUTO)
    private Integer powerId;

    private String powerName;

    private String powerIndex;

    private String powerUrl;

    private Integer parentId;

    private Integer nodeId;

    private String icon;
    @TableField(exist = false)//存储自己菜单权限
    private List<Power> children;

    public List<Power> getChildren() {
        return children;
    }

    public void setChildren(List<Power> childrens) {
        this.children = childrens;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerIndex() {
        return powerIndex;
    }

    public void setPowerIndex(String powerIndex) {
        this.powerIndex = powerIndex;
    }

    public String getPowerUrl() {
        return powerUrl;
    }

    public void setPowerUrl(String powerUrl) {
        this.powerUrl = powerUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Power{" +
        "powerId=" + powerId +
        ", powerName=" + powerName +
        ", powerIndex=" + powerIndex +
        ", powerUrl=" + powerUrl +
        ", parentId=" + parentId +
        ", nodeId=" + nodeId +
        ", icon=" + icon +
        "}";
    }
}
