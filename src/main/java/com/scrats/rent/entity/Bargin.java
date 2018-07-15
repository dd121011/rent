package com.scrats.rent.entity;

import com.scrats.rent.base.entity.BaseEntity;
import com.scrats.rent.constant.SexType;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/18 08:37.
 */
@Data
public class Bargin extends BaseEntity {

    private static final long serialVersionUID = 5489343774578320267L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer barginId;//主键
    private String barginNo;//合同编号,32位
    private String name;//姓名
    private String sex;//性別, 0-保密, 1-男, 2-女
    private String phone;//手机号码
    private String idCard;//identification card 身份证号
    private String idCardPic;//身份证正面
    private String idCardPicBack;//身份证反面
    private Integer guaranty;//押金月份
    private Integer pay;//租金月份
    private Integer rentDay;//交租日
    private Integer rentFee;//租金[分/月]
    private Integer guarantyFee;//押金[分]
    private Integer total;//首次缴费[分]
    private String facilities;//配套设施id字符串[,隔开]
    private Integer roomId;//房间id,一个合同对应一个房间，一个房间对应多个合同
    private Integer buildingId;//房子Id
    private Integer userId;//租客的userId
    private Integer renterId;//租客的renterId
    private Integer landlordId;//房东Id,签署合同的房东
    private Long liveTs;//入住时间，13位时间戳
    private Long leaveTs;//退租时间，13位时间戳

    @Transient
    private List<BarginExtra> barginExtraList;
    @Transient
    private List<DepositIterm> depositItermList;

    public String getSexName(){
        return null == sex ? "" : SexType.fromValue(sex).getName();
    }

}
