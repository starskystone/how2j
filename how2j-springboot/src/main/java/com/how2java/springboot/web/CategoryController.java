package com.how2java.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.mapper.CategoryMapper;
import com.how2java.springboot.pojo.Category;

@Controller
public class CategoryController {
	//@Autowired
	//private CategoryDAO categoryDAO;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@RequestMapping("/listCategoty")
	public String listCategory(Model m) {
	//	List<Category> list = categoryDAO.findAll();
		List<Category> list = categoryMapper.findAll();
		m.addAttribute("cs",list);
		return "listCategory";
	}
}
