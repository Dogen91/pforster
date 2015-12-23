
package ch.pforster.quiz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QuestionRepository<T> extends JpaRepository<T, Long> {
	
	List<T> findByCategoryId(Long id);

}
