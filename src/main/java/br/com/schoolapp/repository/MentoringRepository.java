package br.com.schoolapp.repository;

import br.com.schoolapp.model.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
}
