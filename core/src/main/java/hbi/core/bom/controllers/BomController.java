package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.bom.dto.BomSync;
import hbi.core.bom.service.IOBomService;
import hbi.core.parsesoap.ResultBom;
import hbi.core.parsesoap.ResultBomSync;
import hbi.core.parsesoap.ResultReplace;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.Bom;
import hbi.core.bom.service.IBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BomController extends BaseController {

    @Autowired
    private IBomService service;
    @Autowired
    private IOBomService oservice;
    private List<Bom> bomList = new ArrayList<>();


    @RequestMapping(value = "/bom/bom/query")
    @ResponseBody
    public ResponseData query(Bom dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        String id = request.getParameter("idida2a2");
        String number = request.getParameter("number");
        String version = request.getParameter("version");
        String name = request.getParameter("name");
        String view = request.getParameter("view");
        //2227773156
        //id="2227773156";
        List<Bom> list = getBom(id);
        Bom b = new Bom();
        b.setId(id);
        b.setChildnum(number);
        b.setChildname(name);
        b.setVersion(version);
        b.setView(view);
        list.add(b);
/*
        String[] result=(String[])new SoapUtil().request("getBom","plmadmin","abc.1234",XMLType.XSD_STRING,"{id:"+id+"}");
*/
        ResponseData data = new ResponseData(list);
       /* if(result[0].contains("result\":0"))
        {
            Gson gson=new Gson();
            ResultBom bom=gson.fromJson(result[0],ResultBom.class);
            //data=new ResponseData(list);
            data.setSuccess(true);
        }else
        {
            data=new ResponseData(false);
            data.setMessage("接口数据获取失败！");
        }
*/
        return data;
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
    }

    @RequestMapping(value = "/bom/bom/querysync")
    @ResponseBody
    public ResponseData querysync(HttpServletRequest request, @RequestParam(required = false) String id,
                                  @RequestParam String idida2a2,
                                  @RequestParam String number,
                                  @RequestParam String version,
                                  @RequestParam String name,
                                  @RequestParam String view) {
        BomSync b = new BomSync();
        b.setId(idida2a2);
        List<BomSync> list = new ArrayList<>();
        if (id != null) {
            list = getBomListSync(id);
            for (BomSync bom:list
                 ) {
                if(Integer.parseInt(bom.getTotal())>0)
                {
                    bom.setHasChildren(true);
                }
            }
        } else {
            list = getBomListSync(idida2a2);
            for (BomSync bom:list
                    ) {
                if(Integer.parseInt(bom.getTotal())>0)
                {
                    bom.setHasChildren(true);
                }
            }
            b.setChildnum(number);
            b.setChildname(name);
            b.setVersion(version);
            b.setView(view);
            b.setHasChildren(true);
            list.add(b);
        }
        return new ResponseData(list);
        //2227773156
        //id="2227773156";
           /* List<Bom> list=;
            Bom b=new Bom();
            b.setId(id);
            b.setChildnum(number);
            b.setChildname(name);
            b.setVersion(version);
            b.setView(view);
            list.add(b);*/
/*
        String[] result=(String[])new SoapUtil().request("getBom","plmadmin","abc.1234",XMLType.XSD_STRING,"{id:"+id+"}");
*/
        // ResponseData data=new ResponseData(list);
       /* if(result[0].contains("result\":0"))
        {
            Gson gson=new Gson();
            ResultBom bom=gson.fromJson(result[0],ResultBom.class);
            //data=new ResponseData(list);
            data.setSuccess(true);
        }else
        {
            data=new ResponseData(false);
            data.setMessage("接口数据获取失败！");
        }
*/
        //   return data;
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
    }

    @RequestMapping(value = "/bom/bom/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Bom> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/bom/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Bom> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    public List<Bom> getBom(String id) {
        bomList = new ArrayList<>();
        getBomList(id);
        return bomList;
    }

    public void getBomList(String id) {
        String[] result = (String[]) new SoapUtil().request("getBom", "plmadmin", "abc.1234", XMLType.XSD_STRING, "{id:" + id + "}");
        if (result[0].contains("result\":0")) {
            Gson gson = new Gson();
            ResultBom bom = gson.fromJson(result[0], ResultBom.class);
            bomList.addAll(bom.getBom());
            for (Bom b : bom.getBom()
                    ) {
                if (Integer.parseInt(b.getTotal()) > 0) {
                    getBomList(b.getId());
                }
            }
        }
    }

    public List<BomSync> getBomListSync(String id) {
        List<BomSync> boms = new ArrayList<>();
        String[] result = (String[]) new SoapUtil().request("getBom", "plmadmin", "abc.1234", XMLType.XSD_STRING, "{id:" + id + "}");
        if (result[0].contains("result\":0")) {
            Gson gson = new Gson();
            ResultBomSync bom = gson.fromJson(result[0], ResultBomSync.class);
            boms=bom.getBom();
        }
        return boms;
    }

    @RequestMapping(value = "/bom/bom/sync")
    @ResponseBody
    public ResponseData getsync(HttpServletRequest request, @RequestParam(required = false) String id,
                                @RequestParam String idida2a2,
                                @RequestParam String number,
                                @RequestParam String version,
                                @RequestParam String name,
                                @RequestParam String view
    ) {
        BomSync b = new BomSync();
        b.setId(idida2a2);
        List<BomSync> list = new ArrayList<>();
        if (id != null) {
            if ("设计" == view || "设计".equals(view)) {

                list = oservice.getBomsTSync(id);
            } else {
                list = oservice.getBomsSync(id);
            }
        } else {
            if ("设计" == view || "设计".equals(view)) {
                list = oservice.getBomsTSync(idida2a2);
            } else {
                list = oservice.getBomsSync(idida2a2);
            }
            b.setChildnum(number);
            b.setChildname(name);
            b.setVersion(version);
            b.setView(view);
            b.setHasChildren(true);
            list.add(b);
        }
        return new ResponseData(list);
    }

    @RequestMapping(value = "/bom/bom/test")
    @ResponseBody
    public ResponseData get(HttpServletRequest request) {
        String id = request.getParameter("idida2a2");
        String number = request.getParameter("number");
        String version = request.getParameter("version");
        String name = request.getParameter("name");
        String view = request.getParameter("view");
        Bom b = new Bom();
        b.setId(id);
        b.setChildnum(number);
        b.setChildname(name);
        b.setVersion(version);
        List<Bom> list = new ArrayList<>();
        if ("设计" == view || "设计".equals(view)) {

            list = oservice.getBomsT(id);
        } else {
            list = oservice.getBoms(id);
        }
        list.add(b);
        return new ResponseData(list);
    }





    @RequestMapping(value = "/bom/bom/queryAll")
    @ResponseBody
    public ResponseData queryAll(Bom dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,  HttpServletRequest request) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.select(requestCtx,dto,page,pageSize));
    }

}