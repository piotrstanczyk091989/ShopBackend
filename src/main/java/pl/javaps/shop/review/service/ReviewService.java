package pl.javaps.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javaps.shop.common.model.Review;
import pl.javaps.shop.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
