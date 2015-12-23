
package ch.pforster.quiz.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.pforster.quiz.model.Mode;
import ch.pforster.quiz.model.questions.AbstractQuestion;
import ch.pforster.quiz.model.questions.ComplementQuestion;
import ch.pforster.quiz.model.questions.ImageQuestion;
import ch.pforster.quiz.model.questions.SimpleQuestion;
import ch.pforster.quiz.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    QuestionService questionService;
    
    @Inject
    public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
    
    @RequestMapping(value = "/all/{categoryId}/{mode}", method = RequestMethod.GET)
    public List<AbstractQuestion> getAllByCategoryAndMode(@PathVariable Long categoryId, @PathVariable String mode) {
        Mode quizMode = Mode.valueOf(mode);
    	
        return questionService.getQuestionsByCategoryAndMode(categoryId, quizMode);
    }
    
    @RequestMapping(value = "/addQuestion/simple", method = RequestMethod.POST)
    public SimpleQuestion addSimpleQuestion(@RequestBody SimpleQuestion simpleQuestion) {
        questionService.saveSimpleQuestion(simpleQuestion);

        return simpleQuestion;
    }

    @RequestMapping(value = "/addQuestion/image", method = RequestMethod.POST)
    public ImageQuestion addImageQuestion(@RequestBody ImageQuestion imageQuestion) throws IllegalStateException, IOException {
        questionService.saveImageQuestion(imageQuestion);

        return imageQuestion;
    }
    

    @RequestMapping(value = "/addQuestion/complement", method = RequestMethod.POST)
    public ComplementQuestion addComplementQuestion(@RequestBody ComplementQuestion complementQuestion) {
        questionService.saveComplementQuestion(complementQuestion);

        return complementQuestion;
    }
}
