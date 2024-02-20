package se.ju23.typespeeder.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    List<T> findAll();
}
