package ch.pforster.quiz.model.questions;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ch.pforster.quiz.model.Media;

@Entity
@Table
public class ImageQuestion extends AbstractQuestion {
	
	@Basic(optional = false)
	private String answer;
	
	@Transient
	private String imageUpload;
	
	@Basic(optional = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Media image;

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String getAnswer() {
		return this.answer;
	}

    public String getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }
}
