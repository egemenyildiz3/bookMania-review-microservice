package nl.tudelft.sem.template.review.restcontrollers;

import java.util.List;
import nl.tudelft.sem.template.api.ReviewApi;
import nl.tudelft.sem.template.model.Review;
import nl.tudelft.sem.template.review.repositories.BookDataRepository;
import nl.tudelft.sem.template.review.repositories.CommentRepository;
import nl.tudelft.sem.template.review.repositories.ReviewRepository;
import nl.tudelft.sem.template.review.services.CommentServiceImpl;
import nl.tudelft.sem.template.review.services.CommunicationServiceImpl;
import nl.tudelft.sem.template.review.services.GetReportServiceImpl;
import nl.tudelft.sem.template.review.services.ReviewServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController implements ReviewApi {

    private final ReviewServiceImpl service;

    /**
     * Initializes a new instance of the ReviewServiceImpl class with the provided dependencies.
     *
     * @param bookDataRepository    The BookDataRepository instance for accessing book data.
     * @param repo                  The ReviewRepository instance for data storage and retrieval.
     * @param cr                    The CommentRepository instance for comment data storage and retrieval.
     */
    public ReviewController(BookDataRepository bookDataRepository, ReviewRepository repo, CommentRepository cr,
                            ReviewServiceImpl service) {
        CommunicationServiceImpl communicationService = new CommunicationServiceImpl();
        CommentServiceImpl commentService = new CommentServiceImpl(cr, repo, communicationService);
        GetReportServiceImpl getReportService = new GetReportServiceImpl(bookDataRepository,
                communicationService, commentService);

        this.service = service != null ? service : new ReviewServiceImpl(getReportService, repo, communicationService);
    }



    @Override
    public ResponseEntity<String> reviewDeleteReviewIdUserIdDelete(Long reviewId, Long userId) {
        return service.delete(reviewId, userId);
    }

    @Override
    public ResponseEntity<Review> reviewPost(Review review) {
        return service.add(review);
    }

    @Override
    public ResponseEntity<Review> reviewReviewIdGet(Long reviewId) {
        return service.get(reviewId);
    }

    @Override
    public ResponseEntity<List<Review>> reviewSeeAllBookIdFilterGet(Long bookId, String filter) {
        return service.seeAll(bookId, filter);
    }

    @Override
    public ResponseEntity<Review> reviewUpdateUserIdPut(Long userId, Review review) {
        return service.update(userId, review);
    }

    @Override
    public ResponseEntity<String> reviewSpoilerReviewIdPut(Long reviewId) {
        return service.addSpoiler(reviewId);
    }

    @Override
    public ResponseEntity<String> reviewVoteReviewIdVotePut(Long reviewId, Integer body) {
        return service.addVote(reviewId, body);
    }

    @Override
    public ResponseEntity<List<Review>> reviewMostUpvotedUserIdGet(Long userId) {
        return service.mostUpvotedReviews(userId);
    }

    @Override
    public ResponseEntity<String> reviewPinReviewIdPinValuePut(Long reviewId, Boolean body) {
        return service.pinReview(reviewId, body);
    }
}
