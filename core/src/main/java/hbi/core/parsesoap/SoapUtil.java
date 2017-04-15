package hbi.core.parsesoap;

import com.google.gson.Gson;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dengz on 2017/4/12.
 * zhilong.deng@hand-china.com
 */
public class SoapUtil {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH;mm:ss");

    public  static  void  main(String[] args)
    {
       //String[] result=(String[])request("getReplacePart","plmadmin","abc.1234",XMLType.XSD_STRING,"{ida2a2:106822500}");
        //String[] result=(String[])request("getWNCUser","plmadmin","abc.1234",XMLType.XSD_STRING,"{name:李,number:W27233504022}");
       // String[] result=(String[])new SoapUtil().request("getKeyPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{keysearch:[{oid:"+oid+",number:"+number+",version:"+version+"}]}");
        String[] result=(String[])new SoapUtil().request("getWNCPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{number:2}");
        //Gson gson=new Gson();
        //ResultInfo info=gson.fromJson(result[0],ResultInfo.class);
        //System.out.println(info.getPart().size());
        for (String str:result
             ) {
            System.out.println(str);
        }
    }

    public static Object request(String operation,String username,String password,
                                 QName returnType,Object value){
        System.out.println(sdf.format(new Date()));
        String baseurl = null;
        String rpc = null;
        if (baseurl == null)
            baseurl = "";
        if (rpc == null)
            rpc = "";
        if (baseurl.trim().equals("")) {
            baseurl = "http://plm.teg.cn/Windchill/";
        }
        if (rpc.trim().equals("")) {
            rpc = "servlet/RPC?CLASS=com.infoengine.soap";
        }
        String endpoint = "";
        if (!baseurl.endsWith("/")) {
            baseurl = baseurl + "/";
        }
        if (rpc.startsWith("/")) {
            rpc = rpc.substring(1);
        }
        endpoint = baseurl + rpc;
        String[] result = null;

        try{
            Object paramvalues[]=new Object[1];
            Service service=new Service();
            Call call=(Call)service.createCall();
            call.setUsername(username);
            call.setPassword(password);
            call.setTargetEndpointAddress(new java.net.URL((endpoint)));
            call.setOperationName(new QName(baseurl,operation));
            String paramname="arg1";
            QName type= XMLType.XSD_STRING;
            call.addParameter(new QName(baseurl,paramname),type, ParameterMode.IN);
            paramvalues[0]=value;
            call.setUseSOAPAction(true);
            call.setReturnClass(String[].class);
            call.setSOAPActionURI(baseurl);
            result=(String[])call.invoke(paramvalues);
            System.out.println(sdf.format(new Date()));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return result;
    }
}

