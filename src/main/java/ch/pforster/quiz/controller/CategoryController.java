package ch.pforster.quiz.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.pforster.quiz.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {
	
	private CategoryService categoryService;
	
	@Inject
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Object[] getCategories(){
		return categoryService.getCategories().toArray();
	}
}
