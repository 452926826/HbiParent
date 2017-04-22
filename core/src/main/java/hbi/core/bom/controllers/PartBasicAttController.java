package hbi.core.bom.controllers;

import com.google.gson.Gson;
import com.hand.hap.system.service.IProfileService;
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
public class PartBasicAttController extends BaseController {

    @Autowired
    private IPartBasicAttService service;

    @Autowired
    private IProfileService profileService;

    /**
     * 查询部件基础信息
     *
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/bom/part/basic/att/querysoap")
    @ResponseBody
    public ResponseData querySoap(@RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request, @RequestParam(defaultValue = "W27233504022") String number) {
        IRequest requestContext = createRequestContext(request);

        String pocBomSoapFlag = profileService.getProfileValue(null, "POC_BOM_SOAP"); //获取配置文件：POC_BOM_SOAP（POC:BOM是否调用SOAP）的值
        //根据配置文件来判断调用soap，还是直接访问数据库
        if (pocBomSoapFlag == null || "".equals(pocBomSoapFlag) || "Y".equals(pocBomSoapFlag)) {
            String[] result = (String[]) new SoapUtil().request("getPartBasicAtt", "plmadmin", "abc.1234", XMLType.XSD_STRING, "{number:" + number + "}");
            Gson gson = new Gson();
            ResultInfo info = gson.fromJson(result[0], ResultInfo.class);
            ResponseData data = new ResponseData(info.getPart());
            if (info.getResult() == 0) {
                data.setMessage("成功");
            } else {
                data.setMessage("失败");
            }
            return data;
        } else {
            if ("W27233504022".equals(number)) {
                number = null;
            }
            PartBasicAtt dto = new PartBasicAtt();
            dto.setNumber(number);
            return new ResponseData(service.select(requestContext, dto, page, pageSize));
        }
    }

    @RequestMapping(value = "/bom/part/basic/att/query")
    @ResponseBody
    public ResponseData query(PartBasicAtt dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/bom/part/basic/att/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<PartBasicAtt> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/bom/part/basic/att/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<PartBasicAtt> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}