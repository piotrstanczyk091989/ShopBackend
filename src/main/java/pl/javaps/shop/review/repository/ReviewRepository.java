package pl.javaps.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javaps.shop.common.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
