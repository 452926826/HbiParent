package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultDel;
import hbi.core.parsesoap.ResultDescriton;
import hbi.core.parsesoap.ResultObjs;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.DeletePart;
import hbi.core.bom.service.IDeletePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class DeletePartController extends BaseController{

    @Autowired
    private IDeletePartService service;

        /**
         *
         * @param page
         * @param pageSize
         * @param request
         * @return
         *///
    @RequestMapping(value = "/bom/delete/part/query")
    @ResponseBody
    public ResponseData query(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,@RequestParam String soid,@RequestParam String toid) {
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("getDeletePart","plmadmin","abc.1234", XMLType.XSD_STRING,"{del:[{soid:"+soid+",toid:"+toid+"}]}");
        Gson gson=new Gson();
        ResultDel del=gson.fromJson(result[0],ResultDel.class);
        ResponseData data=new ResponseData(del.getDel());
        if(del.getResult()==0)
        {
            data.setMessage("成功");
        }else
        {
            data.setMessage("失败");
        }
        return data;
    }

        /**
         * 提交变更任务信息至WNC
         * @param request
         * @return
         */

    @RequestMapping(value = "/bom/delete/part/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,
    @RequestParam String name,
                               @RequestParam String processor,
                               @RequestParam String inworksol1,
                               @RequestParam String stocksol1,
                               @RequestParam String testsol1,
                               @RequestParam String softwaresol1,
                               @RequestParam String oid1,
                               @RequestParam String chgdescription,
                               @RequestParam String inworksol2,
                               @RequestParam String stocksol2,
                               @RequestParam String testsol2,
                               @RequestParam String softwaresol2,
                               @RequestParam String isdeletema,
                               @RequestParam String description1,
                               @RequestParam String oid2,
                               @RequestParam String description2,
                               @RequestParam String oid3,
                               @RequestParam String pnumber,
                               @RequestParam String pname,
                               @RequestParam String solution,
                               @RequestParam String description3
    ){
      /*  IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));*/
        String[] result=(String[])new SoapUtil().request("submit","plmadmin","abc.1234", XMLType.XSD_STRING,"{chginfo:{name:"+name+",processor:"+processor+",inworksol:"+inworksol1+",testsol:"+testsol1+",softwaresol:"+softwaresol1+"},\n" +
                "          before:[{oid:"+oid1+",chgdescription:"+chgdescription+",inworksol:"+inworksol2+",\n" +
                "         ,stocksol:"+stocksol2+",testsol:"+testsol2+",softwaresol:"+softwaresol2+",\n" +
                "         isdeletema:"+isdeletema+",description:"+description1+"}],\n" +
                "         after:[{oid:"+oid2+",description:"+description2+"}],\n" +
                "          delobjinfo:[{oid:"+oid3+",pnumber:"+pnumber+",pname:"+pname+",solution:"+solution+",description:"+description3+"}]}");
        Gson gson=new Gson();
        ResultDescriton des=gson.fromJson(result[0],ResultDescriton.class);
        ResponseData data=new ResponseData();
        if(des.getResult()==0)
        {
            data.setMessage(des.getDescriton());
        }else
        {
            data.setMessage(des.getDescriton());
        }
        return data;
    }

    @RequestMapping(value = "/bom/delete/part/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<DeletePart> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }