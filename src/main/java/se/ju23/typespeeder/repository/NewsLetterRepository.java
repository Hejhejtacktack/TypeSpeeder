package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.NewsLetter;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter, Integer> {
}
