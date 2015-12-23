package ch.pforster.quiz.model.questions;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ch.pforster.quiz.model.AnswerInfo;
import ch.pforster.quiz.model.Category;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table
public abstract class AbstractQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private QuestionType type;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Category category;

	@Basic(optional = false)
	private String question;

	@OneToOne
	private AnswerInfo answerInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public AnswerInfo getAnswerInfo() {
		return answerInfo;
	}

	public void setAnswerInfo(AnswerInfo answerInfo) {
		this.answerInfo = answerInfo;
	}

	public abstract String getAnswer();
}
