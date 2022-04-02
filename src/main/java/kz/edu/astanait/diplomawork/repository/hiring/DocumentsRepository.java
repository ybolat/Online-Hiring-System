package kz.edu.astanait.diplomawork.repository.hiring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
