package ch.pforster.quiz.service;

import java.io.IOException;
import java.util.List;

import ch.pforster.quiz.model.questions.AbstractQuestion;
import ch.pforster.quiz.model.questions.ComplementQuestion;
import ch.pforster.quiz.model.questions.ImageQuestion;
import ch.pforster.quiz.model.questions.SimpleQuestion;

public interface QuestionService {

	public void saveSimpleQuestion(SimpleQuestion question);

	public void saveImageQuestion(ImageQuestion question) throws IllegalStateException, IOException;

	public void saveComplementQuestion(ComplementQuestion question);

	public List<AbstractQuestion> getAllQuestions();

	public List<SimpleQuestion> getAllSimpleQuestions();

	public List<ImageQuestion> getAllImageQuestions();

	public List<ComplementQuestion> getAllComplementQuestions();

	public List<AbstractQuestion> getQuestionsByCategory(Long categoryId);
}
