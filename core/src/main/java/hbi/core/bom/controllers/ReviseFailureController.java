package hbi.core.bom.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.ReviseFailure;
import hbi.core.bom.service.IReviseFailureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class ReviseFailureController extends BaseController{

    @Autowired
    private IReviseFailureService service;


    @RequestMapping(value = "/bom/revise/failure/query")
    @ResponseBody
    public ResponseData query(ReviseFailure dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/bom/revise/failure/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<ReviseFailure> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/revise/failure/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<ReviseFailure> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }