package ch.pforster.quiz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AnswerInfo {

	@Id
	@GeneratedValue
	private Long id;

	private Date lastAnswered;
	
	private int numberOfAnswers;
	
	private int numberOfCorrectAnswers;
	
	private LearningLevel learningLevel = LearningLevel.NEW; 
	
	private Date nextRepetition;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastAnswered() {
		return lastAnswered;
	}

	public void setLastAnswered(Date lastAnswered) {
		this.lastAnswered = lastAnswered;
	}

	public int getNumberOfAnswers() {
		return numberOfAnswers;
	}

	public void setNumberOfAnswers(int numberOfAnswers) {
		this.numberOfAnswers = numberOfAnswers;
	}

	public int getNumberOfCorrectAnswers() {
		return numberOfCorrectAnswers;
	}

	public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
		this.numberOfCorrectAnswers = numberOfCorrectAnswers;
	}

	public LearningLevel getLearningLevel() {
		return learningLevel;
	}

	public void setLearningLevel(LearningLevel learningLevel) {
		this.learningLevel = learningLevel;
	}

	public Date getNextRepetition() {
		return nextRepetition;
	}

	public void setNextRepetition(Date nextRepetition) {
		this.nextRepetition = nextRepetition;
	}

}
