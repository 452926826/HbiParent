package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultBom;
import hbi.core.parsesoap.ResultDoc;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.RelatedDoc;
import hbi.core.bom.service.IRelatedDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class RelatedDocController extends BaseController{

    @Autowired
    private IRelatedDocService service;

        /**
         * 获取产品关联文
         * @param dto
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/bom/related/doc/query")
    @ResponseBody
    public ResponseData query(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,@RequestParam String ida2a2) {
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("getRelatedDoc","plmadmin","abc.1234", XMLType.XSD_STRING,"{ida2a2:"+ida2a2+"}");
        Gson gson=new Gson();
        ResultDoc doc=gson.fromJson(result[0],ResultDoc.class);
        ResponseData data=new ResponseData(doc.getDoc());
        if(doc.getResult()==0)
        {
            data.setMessage("成功");
        }else
        {
            data.setMessage("失败");
        }
        return data;
    }

    @RequestMapping(value = "/bom/related/doc/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<RelatedDoc> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/related/doc/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<RelatedDoc> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }