package com.briup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Category;
import com.briup.service.ICategoryService;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
@Api(description = "栏目相关接口")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	
	@GetMapping("/findAll")
	@ApiOperation("查询所有的栏目信息")
	public Message<List<Category>> findAll(){
		List<Category> categorylist = categoryService.findAll();
		return MessageUtil.success(categorylist);
	}
	
	@DeleteMapping("/deleteById")
	@ApiOperation("根据id删除一个栏目")
	@ApiImplicitParam(name="id",value="栏目id",paramType = "query",dataType = "int",required = true)
	public Message<String> deleteById(Integer id){
		Message<String> message = null;
		try {
			categoryService.deleteById(id);
			message = MessageUtil.success("删除成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	@PutMapping("/saveOrUpdate")
	@ApiOperation("保存或更")
	
	public Message<String> saveOrUpdate(Category category){
		Message<String> message = null;
		try {
			categoryService.saveOrUpdate(category);
			message = MessageUtil.success("保存成功");
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	@GetMapping("/findById")
	@ApiOperation("根据id删除一个栏目")
	@ApiImplicitParam(name="id",value="栏目id",required = true,paramType = "query",dataType = "int")
	public Message<Category> findById(int id){
		Message<Category> message = null;
		try {
			Category category = categoryService.findById(id);
			message = MessageUtil.success(category);
		} catch (Exception e) {
			message = MessageUtil.error(500, e.getMessage());
		}
		return message;
	}
	
	
}
