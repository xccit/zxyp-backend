package io.xccit.zxyp.model.vo.product;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExcelVo {

	@ExcelProperty(value = "编号" ,index = 0)
	private Long id;

	@ExcelProperty(value = "分类名称" ,index = 1)
	private String name;

	@ExcelProperty(value = "图片地址" ,index = 2)
	private String imageUrl ;

	@ExcelProperty(value = "上级编号" ,index = 3)
	private Long parentId;

	@ExcelProperty(value = "状态" ,index = 4)
	private Integer status;

	@ExcelProperty(value = "排序编号" ,index = 5)
	private Integer orderNum;

}