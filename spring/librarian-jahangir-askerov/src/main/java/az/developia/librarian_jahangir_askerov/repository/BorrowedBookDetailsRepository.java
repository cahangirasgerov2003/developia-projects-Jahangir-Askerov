package az.developia.librarian_jahangir_askerov.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import az.developia.librarian_jahangir_askerov.entity.BorrowedBookEntity;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;

public interface BorrowedBookDetailsRepository extends JpaRepository<BorrowedBookEntity, Integer> {

//	Method query
	abstract public List<BorrowedBookEntity> findByLibrarianIdentityAndBorrowDateBetween(Integer operatorId,
			LocalDate borrowDate, LocalDate returnDate);

	public abstract List<BorrowedBookEntity> findByLibrarianIdentityAndStatusAndBorrowDateBetween(Integer operatorId,
			LendingStatus status, LocalDate startDate, LocalDate endDate);

//	Native query
	@Query(nativeQuery = true, value = "Select * from borrowed_books_details bbd where bbd.librarian_identity = ?1 and (bbd.actual_return_date is null and bbd.return_date < current_date or bbd.actual_return_date is not null and actual_return_date > return_date)")
	public abstract List<BorrowedBookEntity> findOverdued(Integer operatorId);

}
