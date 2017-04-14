package hbi.core.bom.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.Revise;
import hbi.core.bom.service.IReviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class ReviseController extends BaseController{

    @Autowired
    private IReviseService service;


    @RequestMapping(value = "/bom/revise/query")
    @ResponseBody
    public ResponseData query(Revise dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }


    @RequestMapping(value = "/bom/revise/queryBasic")
    @ResponseBody
    public ResponseData queryBasic(Revise dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryBasic(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/bom/revise/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Revise> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/revise/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Revise> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }