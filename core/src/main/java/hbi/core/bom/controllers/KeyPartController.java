package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultObjs;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.KeyPart;
import hbi.core.bom.service.IKeyPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class KeyPartController extends BaseController{

    @Autowired
    private IKeyPartService service;

        /**
         *
         * @param page
         * @param pageSize
         * @param request
         * @return
         *///{keysearch:[{oid:受影响对象唯一标识,number:编号,version:版本}]}
    @RequestMapping(value = "/bom/key/part/query")
    @ResponseBody
    public ResponseData query(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,@RequestParam String oid,@RequestParam String number,@RequestParam String version) {
       /* IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("getKeyPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{keysearch:[{oid:"+oid+",number:"+number+",version:"+version+"}]}");

        Gson gson=new Gson();
        ResultObjs objs=gson.fromJson(result[0],ResultObjs.class);
        ResponseData data=null;
        if(objs.getResult()==0)
        {
            data=new ResponseData(objs.getObjs());
            data.setMessage("成功");
        }else
        {
            data=new ResponseData(false);
            data.setMessage(objs.getDescripton());
        }
        return data;

    }

    @RequestMapping(value = "/bom/key/part/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<KeyPart> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/key/part/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<KeyPart> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }