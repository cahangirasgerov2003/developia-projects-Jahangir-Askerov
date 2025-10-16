package az.developia.librarian_jahangir_askerov.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.librarian_jahangir_askerov.entity.BorrowedBookEntity;
import az.developia.librarian_jahangir_askerov.enums.LendingStatus;

public interface BorrowedBookDetailsRepository extends JpaRepository<BorrowedBookEntity, Integer> {

//	Method query
	abstract public List<BorrowedBookEntity> findByLibrarianIdentityAndBorrowDateBetween(Integer operatorId,
			LocalDate borrowDate, LocalDate returnDate);

	public abstract List<BorrowedBookEntity> findByLibrarianIdentityAndStatusAndBorrowDateBetween(Integer operatorId,
			LendingStatus status, LocalDate startDate, LocalDate endDate);

}
