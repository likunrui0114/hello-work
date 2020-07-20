package com.l319.eduo2o.web.wechat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.l319.eduo2o.dto.UserAccessToken;
import com.l319.eduo2o.dto.WechatAuthExecution;
import com.l319.eduo2o.dto.WechatUser;
import com.l319.eduo2o.enums.WechatAuthStateEnum;
import com.l319.eduo2o.pojo.PersonInfo;
import com.l319.eduo2o.pojo.WechatAuth;
import com.l319.eduo2o.service.PersonInfoService;
import com.l319.eduo2o.service.WechatAuthService;
import com.l319.eduo2o.util.wechat.WechatUtil;

/**
 * 
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx702bfa96033f6fd3&redirect_uri=http://jscs.etesh.xyz/eduo2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx702bfa96033f6fd3&redirect_uri=http://etesh.natapp1.cc/sell/weixin/auth&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx702bfa96033f6fd3&secret=55e3d6dfd9c8be261cbb51b8a8d9f076&code=CODE&grant_type=authorization_code
 * @author likunrui
 * @version 1.0
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {
	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
	private static String FRONTEND = "1";
//	private static String SHOPEND = "2";
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private WechatAuthService wechatAuthService;

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("weixin login get...");
		// 获取微信公众号传输过来的code,通过code可获取access_token,进而获取用户信息
		String code = request.getParameter("code");
		String roleType = request.getParameter("state");
		log.debug("weixin login code:" + code);
		WechatUser user = null;
		String openId = null;
		WechatAuth auth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				// 通过code获取access_token
				token = WechatUtil.getUserAccessToken(code);
				log.debug("weixin login token:" + token.toString());
				// 通过token获取accessToken
				String accessToken = token.getAccessToken();
				openId = token.getOpenId();
				// 通过access_token和openId获取用户昵称等信息
				user = WechatUtil.getUserInfo(accessToken, openId);
				log.debug("weixin login user:" + user.toString());
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (Exception e) {
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
				e.printStackTrace();
			}
		}
		if (auth == null) {
			PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
			auth = new WechatAuth();
			auth.setOpenId(openId);
			if (FRONTEND.equals(roleType)) {
				personInfo.setUserType(1);
			} else {
				personInfo.setUserType(2);
			}
			auth.setPersonInfo(personInfo);
			WechatAuthExecution we = wechatAuthService.register(auth);
			if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			} else {
				personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user", personInfo);
			}
		}
		if (FRONTEND.equals(roleType)) {
			return "frontend/index";
		} else {
			return "shop/shoplist";
		}
	}
}
