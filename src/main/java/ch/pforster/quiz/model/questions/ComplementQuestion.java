package ch.pforster.quiz.model.questions;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ComplementQuestion extends AbstractQuestion {

	@Basic(optional = false)
	@ElementCollection
	private List<String> answers;

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return null;
	}
}
