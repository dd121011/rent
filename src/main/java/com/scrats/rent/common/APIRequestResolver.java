package com.scrats.rent.common;

import com.scrats.rent.common.annotation.APIRequestControl;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.entity.UserRole;
import com.scrats.rent.entity.WxSns;
import com.scrats.rent.service.RenterService;
import com.scrats.rent.service.UserRoleService;
import com.scrats.rent.service.WxSnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

/**
 * @Created with scrat.
 * @Description: 增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 17:19.
 */
public class APIRequestResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RenterService renterService;
    @Autowired
    private WxSnsService wxSnsService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(APIRequest.class) && methodParameter.hasParameterAnnotation(APIRequestControl.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        APIRequest apiRequest = (APIRequest) nativeWebRequest.getAttribute("apiRequest", RequestAttributes.SCOPE_REQUEST);
        if (apiRequest != null) {
            List<UserRole> urlist = userRoleService.findListBy("userId", apiRequest.getUser().getUserId());
            for (UserRole ur: urlist) {
                switch (ur.getRoleCode()){
                    //租客
                    case "6001":
                        WxSns wxSns = wxSnsService.findBy("userId",apiRequest.getUser().getUserId());
                        apiRequest.setRenterFlag(true);
                        if(null != wxSns){
                            apiRequest.setOpenid(wxSns.getOpenid());
                        }
                        break;
                    //房东
                    case "6002":
                        apiRequest.setLandlordFlag(true);
                        break;
                    //巡管员
                    case "6003":
                        apiRequest.setGuardFlag(true);
                        break;
                    //管理员
                    case "6004":
                        apiRequest.setAdminFlag(true);
                        break;
                    //超级管理员
                    case "6005":
                        apiRequest.setAdministratorFlag(true);
                        break;
                    default:
                        throw new BusinessException("数据有误,请联系系统管理员");
                }
            }
            return apiRequest;
        }
        throw new MissingServletRequestPartException("@APIRequestControl");
    }
}
