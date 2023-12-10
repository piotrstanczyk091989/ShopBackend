package pl.javaps.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javaps.shop.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
