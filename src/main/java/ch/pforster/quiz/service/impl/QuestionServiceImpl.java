package ch.pforster.quiz.service.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.pforster.quiz.model.Category;
import ch.pforster.quiz.model.Media;
import ch.pforster.quiz.model.questions.AbstractQuestion;
import ch.pforster.quiz.model.questions.ComplementQuestion;
import ch.pforster.quiz.model.questions.ImageQuestion;
import ch.pforster.quiz.model.questions.QuestionType;
import ch.pforster.quiz.model.questions.SimpleQuestion;
import ch.pforster.quiz.repo.CategoryRepository;
import ch.pforster.quiz.repo.ComplementQuestionRepository;
import ch.pforster.quiz.repo.ImageQuestionRepository;
import ch.pforster.quiz.repo.SimpleQuestionRepository;
import ch.pforster.quiz.service.MediaService;
import ch.pforster.quiz.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	private SimpleQuestionRepository simpleQuestionRepository;

	private ImageQuestionRepository imageQuestionRepository;

	private ComplementQuestionRepository complementQuestionRepository;

	private CategoryRepository categoryRepository;

	private MediaService mediaService;

	private String imagePath;
	
	private String uploadPath;

	@Inject
	public QuestionServiceImpl(SimpleQuestionRepository simpleQuestionRepository,
			ImageQuestionRepository imageQuestionRepository, ComplementQuestionRepository complementQuestionRepository,
			CategoryRepository categoryRepository, MediaService mediaService) {
		this.simpleQuestionRepository = simpleQuestionRepository;
		this.imageQuestionRepository = imageQuestionRepository;
		this.complementQuestionRepository = complementQuestionRepository;
		this.categoryRepository = categoryRepository;
		this.mediaService = mediaService;
	}

	@Override
	public List<AbstractQuestion> getAllQuestions() {
		List<AbstractQuestion> questions = new LinkedList<>();
		questions.addAll(getAllSimpleQuestions());
		questions.addAll(getAllImageQuestions());
		questions.addAll(getAllComplementQuestions());
		return questions;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSimpleQuestion(SimpleQuestion question) {
		question.setType(QuestionType.SIMPLE);
		question.setCategory(getCategory(question.getCategory()));
		simpleQuestionRepository.save(question);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveImageQuestion(ImageQuestion question) throws IllegalStateException, IOException {
		question.setType(QuestionType.IMAGE);
		question.setCategory(getCategory(question.getCategory()));

		Media media = new Media();
		media.setName(question.getImageUpload());
		question.setImage(media);

		imageQuestionRepository.save(question);

		String source = uploadPath + "\\" + question.getImageUpload();
		String target = imagePath + "\\" + question.getId() + "_" + question.getImageUpload();
		mediaService.moveMedia(source, target);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveComplementQuestion(ComplementQuestion question) {
		question.setType(QuestionType.COMPLEMENT);
		question.setCategory(getCategory(question.getCategory()));
		complementQuestionRepository.save(question);
	}

	@Override
	public List<SimpleQuestion> getAllSimpleQuestions() {
		return simpleQuestionRepository.findAll();
	}

	@Override
	public List<ImageQuestion> getAllImageQuestions() {
		return imageQuestionRepository.findAll();
	}

	@Override
	public List<ComplementQuestion> getAllComplementQuestions() {
		return complementQuestionRepository.findAll();
	}

	@Override
	public List<AbstractQuestion> getQuestionsByCategory(Long categoryId) {
		List<AbstractQuestion> allQuestions = getAllQuestions();
		List<AbstractQuestion> categoryQuestions = new LinkedList<>();
		for (AbstractQuestion question : allQuestions) {
			if (categoryId.equals(question.getCategory().getId())) {
				categoryQuestions.add(question);
			}
		}
		return categoryQuestions;
	}

	private Category getCategory(Category category) {
		if (category == null || category.getId() == null) {
			return category;
		}
		return categoryRepository.findOne(category.getId());
	}

	public String getImagePath() {
		return imagePath;
	}

	@Value("${quiz.images.path}")
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Value("${quiz.images.upload.path}")
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
}
