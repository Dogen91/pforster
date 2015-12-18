
package ch.pforster.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.pforster.quiz.model.questions.SimpleQuestion;

public interface SimpleQuestionRepository extends JpaRepository<SimpleQuestion, Long> {

}
