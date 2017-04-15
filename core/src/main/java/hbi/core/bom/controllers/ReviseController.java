package hbi.core.bom.controllers;

import com.google.gson.Gson;
import hbi.core.parsesoap.ResultAfter;
import hbi.core.parsesoap.ResultUser;
import hbi.core.parsesoap.SoapUtil;
import org.apache.axis.encoding.XMLType;
import hbi.core.bom.util.HmdmExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.bom.dto.Revise;
import hbi.core.bom.service.IReviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    @Controller
    public class ReviseController extends BaseController{

    @Autowired
    private IReviseService service;

        /**
         * 新建修订版本
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
    @RequestMapping(value = "/bom/revise/query")
    @ResponseBody
    public ResponseData query( @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request,@RequestParam String oid,@RequestParam String number
    ,@RequestParam String name,@RequestParam String type) {
        /*IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));*/
        String[] result=(String[])new SoapUtil().request("revise","plmadmin","abc.1234", XMLType.XSD_STRING,"{revise:[{oid:"+oid+",number:"+number+",name:"+name+",type:"+type+"}]}");
        Gson gson=new Gson();
        ResultAfter after=gson.fromJson(result[0],ResultAfter.class);
        ResponseData data=new ResponseData(after.getAfter());
        if(after.getResult()==0)
        {
            data.setMessage("所有对象修订成功");
        }else if(after.getResult()==-1)
        {
            data.setMessage("部分对象修改失败");
        }else
        {
            data.setMessage("接口错误");

        }
        return data;
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

        /**
         * 下载模板
         * @param response
         * @param request
         * @throws IOException
         */
        @RequestMapping(value = {"/bom/revise/download"},method = {RequestMethod.GET})
        public void printTemplate(HttpServletResponse response, HttpServletRequest request) throws IOException {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("模板导入");
            XSSFRow nRow = null;
            Cell nCell = null;
            XSSFCellStyle nStyle = wb.createCellStyle();
            XSSFFont nFont = wb.createFont();
            byte rowNo = 0;
            int colNo = 0;
            int var15 = rowNo + 1;
            nRow = sheet.createRow(rowNo);
            short colorYello = IndexedColors.YELLOW.getIndex();
            String[] nameArray = new String[]{"oid","编号", "名称","类型","版本","状态","修改者","备注信息"};//字段数据
            int[] lengthArray = new int[]{20,20,20,20,20,20,20,20};//表格中对应字段宽度

            for(int os = 0; os < nameArray.length; ++os) {
                sheet.setColumnWidth(os, lengthArray[os] * 272);
                nCell = nRow.createCell(colNo++);
                nCell.setCellValue(nameArray[os]);
                nCell.setCellStyle(HmdmExcelUtils.title(wb, nStyle, nFont, colorYello));
            }

            ByteArrayOutputStream var16 = new ByteArrayOutputStream();
            wb.write(var16);
            HmdmExcelUtils.download(var16, response, request, "模板.xlsx");
        }



        /**
         * 文件上传
         * @param request
         * @param files
         * @return
         * @throws IOException
         * @throws InvalidFormatException
         */
        @RequestMapping(value = {"/bom/revise/upload"}, method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData upload(HttpServletRequest request, MultipartFile files, @RequestParam Long chginfoId) throws IOException, InvalidFormatException {
            IRequest requestContext = this.createRequestContext(request);
            String fileName = files.getOriginalFilename();
            XSSFWorkbook wb = new XSSFWorkbook(files.getInputStream());
            XSSFSheet sheet = wb.getSheetAt(0);
            ResponseData responseData = new ResponseData();
            XSSFRow nRow = null;
            Cell nCell = null;
            byte beginRowNo = 1;//定义拿取数据的起始行0开始
            int endRowNo = sheet.getLastRowNum();//拿到文件中数据总条数
            nRow = sheet.getRow(0);
            short endColNo = nRow.getLastCellNum();
            if(endRowNo == 0) {
                responseData.setMessage(fileName + "是一个空文件！");
                responseData.setSuccess(false);
                wb.close();
                return responseData;
            } else {
                ArrayList excelList = new ArrayList();

                for(int rData = beginRowNo; rData <= endRowNo; ++rData) {
                    nRow = sheet.getRow(rData);
                    ArrayList e = new ArrayList();

                    for(int j = 0; j <= endColNo; ++j) {
                        nCell = nRow.getCell(j);
                        if(nCell != null) {
                            nCell.setCellType(1);
                            if(StringUtils.isNotBlank(nCell.getStringCellValue())) {
                                e.add(nCell.getStringCellValue());//拿到文件单行数据
                            } else {
                                e.add("");
                            }
                        } else {
                            e.add("");
                        }
                    }

                    excelList.add(e);//拿到文件中所有的值
                }

                ResponseData var24 = new ResponseData();

                ResponseData var25;
                try {

                    service.insterExect(requestContext,excelList,chginfoId);
                    return new ResponseData(true);
                } catch (RuntimeException var21) {
                    var24.setMessage("导入失败！出现异常错误:"+var21.getLocalizedMessage());
                    var24.setSuccess(false);
                    var25 = var24;
                } catch (Exception var22) {
                    var22.printStackTrace();
                    var24.setMessage(var22.getMessage());
                    var24.setSuccess(false);
                    var25 = var24;
                    return var25;
                } finally {
                    wb.close();
                }

                return var25;
            }
        }




    }