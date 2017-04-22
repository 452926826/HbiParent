package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultDoc;
import hbi.core.parsesoap.ResultUser;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.WncUser;
import hbi.core.bom.service.IWncUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class WncUserController extends BaseController{

    @Autowired
    private IWncUserService service;

        /**
         * 搜索WNC系统用户
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/bom/wnc/user/query")
    @ResponseBody
    public ResponseData query( @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,WncUser dto) {
       /* IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("getWNCUser","plmadmin","abc.1234", XMLType.XSD_STRING,"{name:"+dto.getName()+",number:"+dto.getNumber()+"}");
        Gson gson=new Gson();
        ResultUser user=gson.fromJson(result[0],ResultUser.class);
        ResponseData data=new ResponseData(user.getUser());
        if(user.getResult()==0)
        {
            data.setMessage("成功");
        }else
        {
            data.setMessage("失败");
        }
        return data;
    }

    /**
     * 模糊查询用户
     */
    @RequestMapping(value = "/bom/wnc/user/queryBasic")
    @ResponseBody
    public ResponseData queryBasic(WncUser dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryBasic(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/bom/wnc/user/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<WncUser> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/wnc/user/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<WncUser> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }