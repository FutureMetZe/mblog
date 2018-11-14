/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.web.controller.site;

import javax.servlet.http.HttpServletRequest;

import mblog.base.utils.ClientIp;
import mblog.boot.CheckMobile;
import mblog.modules.user.entity.Visitor;
import mblog.modules.user.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import mblog.base.lang.Consts;
import mblog.web.controller.BaseController;

import java.util.Date;

/**
 * @author langhsu
 *
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private VisitorService visitorService;
	
	@RequestMapping(value= {"/", "/index"})
	public String root(ModelMap model, HttpServletRequest request) {
		String order = ServletRequestUtils.getStringParameter(request, "order", Consts.order.NEWEST);
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		model.put("order", order);
		model.put("pn", pn);
		boolean checkMobile = CheckMobile.checkMobile(request);
        //记录访客
		//String ipAddr = ClientIp.getIpAddr(request);
		//Visitor visitor = new Visitor(ipAddr,new Date());
		//visitorService.saveVisitor(visitor);
        return view(Views.INDEX);
	}

}
