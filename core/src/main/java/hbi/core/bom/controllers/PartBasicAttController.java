package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultInfo;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.PartBasicAtt;
import hbi.core.bom.service.IPartBasicAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class PartBasicAttController extends BaseController{

    @Autowired
    private IPartBasicAttService service;

        /**
         * 查询部件基础信息
         * @param dto
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/bom/part/basic/att/query")
    @ResponseBody
    public ResponseData query(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,@RequestParam(defaultValue = "W27233504022") String number) {
        IRequest requestContext = createRequestContext(request);
        String[] result=(String[])new SoapUtil().request("getPartBasicAtt","plmadmin","abc.1234", XMLType.XSD_STRING,"{number:"+number+"}");
        Gson gson=new Gson();
        ResultInfo info=gson.fromJson(result[0],ResultInfo.class);
        //service.select(requestContext,dto,page,pageSize);
        ResponseData data=new ResponseData(info.getPart());
        if(info.getResult()==0)
        {
            data.setMessage("成功");
        }else
        {
            data.setMessage("失败");
        }
        return data;
    }

    @RequestMapping(value = "/bom/part/basic/att/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<PartBasicAtt> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/part/basic/att/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<PartBasicAtt> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }