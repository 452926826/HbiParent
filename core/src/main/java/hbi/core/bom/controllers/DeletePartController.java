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
    public ResponseData query(DeletePart dto,@RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));

    }
        @RequestMapping(value = "/bom/delete/part/sync")
        @ResponseBody
        public ResponseData sync(HttpServletRequest request,@RequestParam String param) {
            IRequest requestContext = createRequestContext(request);
            System.out.println(param);
            String[] result=(String[])new SoapUtil().request("getDeletePart","plmadmin","abc.1234", XMLType.XSD_STRING,param);
            ResponseData data=null;
            if(result[0].contains("result\":-1"))
            {
                data=new ResponseData(false);
                data.setMessage("获取失败");
            }else
            {
                Gson gson=new Gson();
                ResultDel del=gson.fromJson(result[0],ResultDel.class);
                data=new ResponseData(true);
                if(del.getDel().size()>0) {
                    data = new ResponseData(del.getDel());
                    data.setMessage("成功");
                }
                else
                {
                    data=new ResponseData(false);
                    data.setMessage("接口无数据！");
                }
                for (DeletePart de:del.getDel()
                     ) {
                    service.insertSelective(requestContext,de);
                }
            }

            /**
             * 将对象转为DeletePart存到本地数据库！
             */
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
                               @RequestBody List<DeletePart>dto
    ){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value="/sync/submit")
    @ResponseBody
    public ResponseData submit(HttpServletRequest request,@RequestParam String param)
    {
        String[] result=(String[])new SoapUtil().request("submit","plmadmin","abc.1234", XMLType.XSD_STRING,param);

        System.out.println(param);
        ResponseData data=new ResponseData();
        if(result[0].contains("result:-1"))
        {
            data.setSuccess(false);
            data.setMessage(result[0].substring(23));
        }else
        {
            Gson gson=new Gson();
            ResultDescriton des=gson.fromJson(result[0],ResultDescriton.class);
            data.setSuccess(true);
            data.setMessage("提交成功");
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