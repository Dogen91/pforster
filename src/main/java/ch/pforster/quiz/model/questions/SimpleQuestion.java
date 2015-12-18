package ch.pforster.quiz.model.questions;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class SimpleQuestion extends AbstractQuestion {

	@Basic(optional = false)
	private String answer;

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String getAnswer() {
		return this.answer;
	}

}
