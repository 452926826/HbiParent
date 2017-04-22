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
        //
        //String str="{chginfo:{name:变测试,processor:'wt.org.WTUser:22227',inworksol:按图改制,stocksol:报废,testsol:无需变更,softwaresol:无需变更},before:[{oid:'wt.part.WTPart:2239091372',chgdescription:undefined,inworksol:undefined,,stocksol:undefined,testsol:undefined,softwaresol:undefined,isdeletema:undefined,description:null},{oid:'wt.part.WTPart:894309444',chgdescription:undefined,inworksol:undefined,,stocksol:undefined,testsol:undefined,softwaresol:undefined,isdeletema:undefined,description:null}],after:[{oid:'wt.part.WTPart:609345571',description:null},{oid:'wt.part.WTPart:775467352',description:null},{oid:'wt.part.WTPart:406269045',description:null},{oid:'wt.part.WTPart:1101352983',description:null},{oid:'wt.part.WTPart:894309444',description:null},{oid:'wt.part.WTPart:2239087248',description:null},{oid:'wt.part.WTPart:1461837568',description:null},{oid:'wt.part.WTPart:2239090702',description:null}],delobjinfo:[{oid:'DELETE_TEST',pnumber:TG047,pname:电源,solution:按备注说明执行,description:123},{oid:'DELETE_TEST2',pnumber:TG048,pname:电源,solution:报废,description:321}]}}";
       // System.out.println(str.substring(195));
        //String[] result=(String[])request("getPartBasicAtt","plmadmin","abc.1234",XMLType.XSD_STRING,"{number:W27233504022}");

       // String[] result=(String[])request("getReplacePart","plmadmin","abc.1234",XMLType.XSD_STRING,"{ida2a2:106822500}");
        //String[] result=(String[])request("getWNCUser","plmadmin","abc.1234",XMLType.XSD_STRING,"{name:李,number:W27233504022}");
       //String[] result=(String[])new SoapUtil().request("getKeyPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{keysearch:[{oid:'wt.part.WTPart:2222108880',number:12,version:A.1 (设计)}]}");
       // String[] result=(String[])new SoapUtil().request("revise","plmadmin","abc.1234", XMLType.XSD_STRING,"{revise:[{oid:'wt.part.WTPart:406269045',number:252239-1,name:气缸,type:部件}]}");
        //String[] result=(String[])new SoapUtil().request("getDeletePart","plmadmin","abc.1234", XMLType.XSD_STRING,"{del:[{soid:'wt.part.WTPart:2222108880',toid:'wt.part.WTPart:2238881931'}]}");
        //String[] result=(String[])new SoapUtil().request("getWNCPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{number:2000000670}");
        //Gson gson=new Gson();
        //ResultInfo info=gson.fromJson(result[0],ResultInfo.class);
        //System.out.println(info.getPart().size());
        String[] result=(String[])request("getBom","plmadmin","abc.1234",XMLType.XSD_STRING,"{id:2227773156}");//960251207
       // String[] result=(String[])request("getAsynBom","plmadmin","abc.1234",XMLType.XSD_STRING,"{id:960251207}");//

        for (String str:result
             ) {
            System.out.println(str);
        }
    }

    public static Object request(String operation,String username,String password,
                                 QName returnType,Object value){
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
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.toString());
        }
        return result;
    }
}

