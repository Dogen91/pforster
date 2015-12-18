package ch.pforster.quiz.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ch.pforster.quiz.model.Category;
import ch.pforster.quiz.repo.CategoryRepository;
import ch.pforster.quiz.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Inject
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
}
