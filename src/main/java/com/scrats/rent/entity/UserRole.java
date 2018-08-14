package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import com.scrats.rent.constant.UserType;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 用户角色实体类
 * Created by lol on 2018/8/14.
 */
@Data
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 3121772453614993016L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;//主键
    private String roleCode;//角色code
    private Integer userId;//用户id

    @Transient
    private User user;

    public UserRole() {
    }

    public UserRole(UserType userType, Integer userId) {
        this.roleCode = userType.getValue();
        this.userId = userId;
    }
}
