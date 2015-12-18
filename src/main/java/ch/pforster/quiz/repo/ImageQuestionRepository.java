
package ch.pforster.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.pforster.quiz.model.questions.ImageQuestion;

public interface ImageQuestionRepository extends JpaRepository<ImageQuestion, Long> {

}
