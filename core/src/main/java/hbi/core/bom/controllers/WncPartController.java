package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultAfter;
import hbi.core.parsesoap.ResultObjs;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.WncPart;
import hbi.core.bom.service.IWncPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class WncPartController extends BaseController{

    @Autowired
    private IWncPartService service;

        /**
         * 搜索WNC系统对象
         * @param dto
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/bom/wnc/part/query")
    @ResponseBody
    public ResponseData query( WncPart dto,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("getWNCPart","plmadmin","abc.1234", XMLType.XSD_STRING,"{number:"+dto.getNumber()+"}");
        Gson gson=new Gson();
        ResultObjs objs=gson.fromJson(result[0],ResultObjs.class);
        ResponseData data=new ResponseData(objs.getObjs());
        if(objs.getResult()==0)
        {
            data.setMessage("成功");
        }else
        {
            data.setMessage("失败");
        }
        return data;
    }

    /**
     * 模糊查询对象
     */
    @RequestMapping(value = "/bom/wnc/part/queryBasic")
    @ResponseBody
    public ResponseData queryBasic(WncPart dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryBasic(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/bom/wnc/part/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<WncPart> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/wnc/part/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<WncPart> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }