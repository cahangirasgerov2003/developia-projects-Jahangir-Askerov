package az.developia.librarian_jahangir_askerov.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_jahangir_askerov.entity.LendingEntity;
import jakarta.transaction.Transactional;

@RestController
@Transactional(rollbackOn = Exception.class)
public interface LendingRepository extends JpaRepository<LendingEntity, Integer> {
//	Native query

	@Query(nativeQuery = true, value = "Select count(*) from book_lendings where student_id = ?1 and status = 'ACTIVE'")
	public abstract Long countActiveLoansByStudentId(Integer student_id);

	@Query(nativeQuery = true, value = "Select * from book_lendings where librarian_id = ?1")
	public abstract List<LendingEntity> findLoansByLibrarianId(Integer operatorId);

}
