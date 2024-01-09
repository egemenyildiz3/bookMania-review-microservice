package nl.tudelft.sem.template.example.RESTcontrollers;

import nl.tudelft.sem.template.api.ReportedApi;
import nl.tudelft.sem.template.example.repositories.ReportReviewRepository;
import nl.tudelft.sem.template.example.repositories.ReviewRepository;
import nl.tudelft.sem.template.example.services.ReportReviewServiceImpl;
import nl.tudelft.sem.template.model.ReportReview;
import nl.tudelft.sem.template.example.repositories.CommentRepository;
import nl.tudelft.sem.template.example.repositories.ReportCommentRepository;
import nl.tudelft.sem.template.example.services.ReportCommentServiceImpl;
import nl.tudelft.sem.template.model.ReportComment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportedController implements ReportedApi{
    private final ReportReviewServiceImpl reportReviewService;
    private final ReportCommentServiceImpl reportCommentService;

    public ReportedController(ReportReviewRepository reportReviewRepository, ReviewRepository reviewRepository,
                              ReportCommentRepository reportCommentRepository, CommentRepository commentRepository) {
        this.reportReviewService = new ReportReviewServiceImpl(reportReviewRepository, reviewRepository);
        this.reportCommentService = new ReportCommentServiceImpl(reportCommentRepository, commentRepository);
    }

    @Override
    public ResponseEntity<List<ReportReview>> reportedReviewsUserIdGet(Long userId) {
        return reportReviewService.getAllReportedReviews(userId);
    }

    @Override
    public ResponseEntity<List<ReportComment>> reportedCommentsUserIdGet(Long userId) {
        return reportCommentService.getAllReportedComments(userId);
    }
}