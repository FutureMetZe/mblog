package mblog.base.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * https接口调用工具类
 *
 * @author my
 */
public class HttpConnect {

    /**
     * http接口调用
     *
     * @author my
     * @param url
     *            接口地址
     * @param jsonParams
     *            json格式参数
     * @return
     */

    public synchronized static String doPost(String url, String jsonParams) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost(url);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);// 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);// 数据传输时间
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonParams, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        method.setEntity(entity);
        HttpResponse result = httpClient.execute(method);
        String resData = EntityUtils.toString(result.getEntity());
        return resData;
    }

    /**
     * http接口调用
     *
     * @author my
     * @param url
     *            接口地址
     * @return
     */
    public synchronized static String doGet(String url) throws Exception {
        BufferedReader in = null;
        String content = null;
        // 定义HttpClient
        HttpClient client = new DefaultHttpClient();

        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);// 连接时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);// 数据传输时间

        // 实例化HTTP方法
        HttpGet request = new HttpGet();
        request.setURI(new URI(url));
        HttpResponse response = client.execute(request);

        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
        StringBuffer sb = new StringBuffer("");
        String line = "";
        String NL = System.getProperty("line.separator");
        while ((line = in.readLine()) != null) {
            sb.append(line + NL);
        }
        in.close();
        return content = sb.toString();
    }

    /**
     * 传普通表单参数
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public synchronized static String doPost(String url,Map<String,String> params) throws Exception{
        Set<String> keys = params.keySet();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        Iterator<String> i = keys.iterator();
        while(i.hasNext()){
            String key = i.next();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair(key, params.get(key));
            list.add(basicNameValuePair);
        }
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);// 连接时间
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);// 数据传输时间

        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(list));
        HttpResponse httpResponse = httpClient.execute(post);
        HttpEntity entity = httpResponse.getEntity();
        return EntityUtils.toString(entity);
    }
}
