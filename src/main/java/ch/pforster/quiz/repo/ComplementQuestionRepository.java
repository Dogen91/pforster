
package ch.pforster.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.pforster.quiz.model.questions.ComplementQuestion;

public interface ComplementQuestionRepository extends JpaRepository<ComplementQuestion, Long> {

}
