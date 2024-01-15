package nl.tudelft.sem.template.review.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import nl.tudelft.sem.template.model.Comment;
import nl.tudelft.sem.template.model.Review;
import nl.tudelft.sem.template.review.exceptions.CustomBadRequestException;
import nl.tudelft.sem.template.review.repositories.CommentRepository;
import nl.tudelft.sem.template.review.repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final ReviewRepository reviewRepository;

    public CommentServiceImpl(CommentRepository repository, ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.repository = repository;
    }

    private static final List<String> profanities =
            Arrays.asList("fuck", "shit", "motherfucker", "bastard", "cunt", "bitch");

    /**
     * Checks if a string contains any profanities from the defined profanities list.
     *
     * @param text - The text to check
     * @return - True if profanities were found, false otherwise
     */
    public static boolean checkProfanities(String text) {
        if (text != null) {
            for (String character : profanities) {
                if (text.toLowerCase().contains(character)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<Comment> add(Comment comment) {

        if (comment == null || !reviewRepository.existsById(comment.getReviewId())) {
            return ResponseEntity.badRequest().build();
        }
        if (checkProfanities(comment.getText())) {
            return ResponseEntity.badRequest().build();
        }

        comment.setId(0L);
        comment.setDownvote(0L);
        comment.setUpvote(0L);
        comment.setTimeCreated(LocalDate.now());
        comment.setReportList(new ArrayList<>());

        Review review = reviewRepository.findById(comment.getReviewId()).get();

        review.addCommentListItem(comment);
        reviewRepository.save(review);
        review.getCommentList().sort(Comparator.comparing(Comment::getTimeCreated));
        return ResponseEntity.ok(review.getCommentList().get(review.getCommentList().size() - 1));
    }

    @Override
    public ResponseEntity<Comment> get(Long commentId) {
        if (!repository.existsById(commentId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repository.findById(commentId).get());
    }

    @Override
    public ResponseEntity<List<Comment>> getAll(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            return  ResponseEntity.badRequest().build();
        }

        List<Comment> comments = repository.findAll().stream()
                .filter(c -> c.getReviewId().equals(reviewId))
                .collect(Collectors.toList());

        return ResponseEntity.ok(comments);

    }

    @Override
    public ResponseEntity<Comment> update(Long userId, Comment comment) {
        if (comment == null || !Objects.equals(comment.getUserId(), userId) || !repository.existsById(comment.getId())) {
            return ResponseEntity.badRequest().header("blah").build();
        }

        if (checkProfanities(comment.getText())) {
            return ResponseEntity.badRequest().build();
        }
        Comment dataCom = repository.getOne(comment.getId());
        dataCom.setText(comment.getText());
        Comment updated = repository.save(dataCom);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<String> delete(Long commentId, Long userId) {
        if (!repository.existsById(commentId)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Comment> optionalComment = repository.findById(commentId);
        Comment comment;
        if (optionalComment.isPresent()) {
            comment = optionalComment.get();
        } else {
            throw new CustomBadRequestException("Cannot find comment.");
        }

        Review rev = reviewRepository.getOne(comment.getReviewId());
        if (Objects.equals(userId, comment.getUserId())) {
            //repository.deleteById(commentId);
            rev.getCommentList().remove(comment);
            reviewRepository.save(rev);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Finds the most upvoted comment for a book.
     *
     * @param bookId - The book whose comments to look for
     * @return - The id of the most upvoted comment of null if no comment was found
     */
    public ResponseEntity<Long> findMostUpvotedComment(Long bookId) {
        List<Comment> allComments = repository.findAll();

        Optional<Comment> result = allComments.stream().filter(x -> {
            Review associatedReview = reviewRepository.getOne(x.getReviewId());
            return Objects.equals(associatedReview.getBookId(), bookId);
        })
                .max(Comparator.comparingLong(Comment::getUpvote));

        return result.map(comment -> ResponseEntity.ok(comment.getId()))
                .orElseGet(() -> ResponseEntity.badRequest().body(null));

    }

    @Override
    public ResponseEntity<String> addVote(Long commentId, Integer body) {
        if (!repository.existsById(commentId) || get(commentId).getBody() == null) {
            return ResponseEntity.badRequest().body("Comment id does not exist.");
        }

        if (!(List.of(0, 1).contains(body))) {
            return ResponseEntity.badRequest().body("The only accepted bodies are 0 for downvote and 1 for upvote.");
        }
        Comment comment = get(commentId).getBody();
        assert comment != null;
        checkBody(comment, body);
        repository.save(comment);
        return ResponseEntity.ok("Vote added, new vote values are:\nupvotes: "
                + comment.getUpvote() + "\ndownvotes: " + comment.getDownvote());
    }

    private void checkBody(Comment comment, Integer body) {
        if (body == 1) {
            comment.upvote(comment.getUpvote() + 1);
        } else {
            comment.downvote(comment.getDownvote() + 1);
        }
    }
}
