package hbi.core.bom.util;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * execl导入导入工具类
 * 
 * @author ligang.sun
 *
 */
public class HmdmExcelUtils {

	/**
	 * 下载的方法
	 * 
	 * @param byteArrayOutputStream
	 *            流
	 * @param response
	 *            相应
	 * @param request
	 *            请求
	 * @param returnName
	 *            文件名称
	 * @throws IOException
	 */
	public static void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response,
	        HttpServletRequest request, String returnName) throws IOException {
		// 处理浏览器差异导致的下载文件名称乱码的问题
		String userAgent = request.getHeader("USER-AGENT");
		String enocodeType = "";
		if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
			enocodeType = "iso8859-1";
		} else {
			enocodeType = "utf8";
		}

		// response.setContentType("application/octet-stream;charset=utf-8");
		// response.setContentType("application/vnd.ms-excel");//application/octet-stream
		response.setContentType("application/x-download");
		returnName = response.encodeURL(new String(returnName.getBytes(), enocodeType)); // 保存的文件名,必须和页面编码一致,否则乱码
		response.addHeader("Content-Disposition", "attachment;filename=" + returnName);
		response.setContentLength(byteArrayOutputStream.size());

		ServletOutputStream outputstream = response.getOutputStream(); // 取得输出流
		byteArrayOutputStream.writeTo(outputstream); // 写到输出流
		byteArrayOutputStream.close(); // 关闭
		outputstream.flush(); // 刷数据
	}

	/**
	 * 下拉
	 * 
	 * @param sheet
	 *            sheet对象
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 *            范围
	 * @param listOfValues
	 *            下拉 的内容
	 * @param errorStr
	 *            错误提示信息
	 * @return
	 */
	public static DataValidation setDropDownList(XSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol,
	        String[] listOfValues, String errorStr) {
		XSSFDataValidationHelper helper = new XSSFDataValidationHelper(sheet);
		DataValidationConstraint constraint = helper.createExplicitListConstraint(listOfValues);
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
		DataValidation dropDownList = helper.createValidation(constraint, regions);
		// 设置填写不正确的提示信息
		dropDownList.createErrorBox("error", errorStr);
		dropDownList.setShowErrorBox(true);
		dropDownList.setSuppressDropDownArrow(true);
		return dropDownList;
	}

	/**
	 * 样式
	 * 
	 * @param wb
	 * @param nStyle
	 *            样式
	 * @param nFont
	 *            字体
	 * @param colorValue
	 *            背景颜色
	 * @return
	 */
	public static CellStyle title(Workbook wb, CellStyle nStyle, Font nFont, short colorValue) {
		nFont.setFontName("宋体");
		nFont.setFontHeightInPoints((short) 10);
		nFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		nStyle.setFillPattern(XSSFCellStyle.FINE_DOTS);
		nStyle.setFillForegroundColor(colorValue);// 设置背景颜色
		nStyle.setFillBackgroundColor(colorValue);
		nStyle.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		nStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		// 表格线
		nStyle.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		nStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		nStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		nStyle.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		nStyle.setFont(nFont);
		return nStyle;
	}
}
