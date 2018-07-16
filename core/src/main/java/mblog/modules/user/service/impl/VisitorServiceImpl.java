package mblog.modules.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import mblog.base.utils.ClientIp;
import mblog.base.utils.HttpConnect;
import mblog.modules.user.dao.VisitorDao;
import mblog.modules.user.entity.Visitor;
import mblog.modules.user.service.VisitorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitorServiceImpl implements VisitorService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private VisitorDao visitorDao;

    private static String ipInterface = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    @Override
    @Transactional
    public void saveVisitor(Visitor visitor) {
        String json = "";
        try {
            json = HttpConnect.doGet(ipInterface+visitor.getIp());
            logger.info(json);
            //json = "{\"code\":0,\"data\":{\"ip\":\"61.183.176.172\",\"country\":\"中国\",\"area\":\"\",\"region\":\"湖北\",\"city\":\"武汉\",\"county\":\"XX\",\"isp\":\"电信\",\"country_id\":\"CN\",\"area_id\":\"\",\"region_id\":\"420000\",\"city_id\":\"420100\",\"county_id\":\"xx\",\"isp_id\":\"100017\"}}";
        } catch (Exception e) {
            logger.info("根据ip地址获取详细地域信息接口异常！");
        }
        if(StringUtils.isNotBlank(json)){
            JSONObject jsonObject = JSONObject.parseObject(json);
            String jsonObjectString = jsonObject.getString("data");
            JSONObject data = JSONObject.parseObject(jsonObjectString);
            if(StringUtils.isNotBlank(jsonObjectString)){
                String country = data.getString("country"); // 國家
                String country_id = data.getString("country_id"); // 國家語言
                String region = data.getString("region"); // 省
                String city = data.getString("city"); // 市
                String isp = data.getString("isp"); // 網絡
                visitor.setCountry(country);
                visitor.setCountryId(country_id);
                visitor.setRegion(region);
                visitor.setCity(city);
                visitor.setIsp(isp);
            }
        }
        logger.info("遊客信息:"+visitor);
        visitorDao.save(visitor);
    }
}
