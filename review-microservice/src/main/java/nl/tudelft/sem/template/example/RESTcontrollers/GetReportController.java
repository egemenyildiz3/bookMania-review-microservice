package nl.tudelft.sem.template.example.RESTcontrollers;

import nl.tudelft.sem.template.api.GetReportApi;
import nl.tudelft.sem.template.example.repositories.BookDataRepository;
import nl.tudelft.sem.template.example.repositories.ReviewRepository;
import nl.tudelft.sem.template.example.services.GetReportService;
import nl.tudelft.sem.template.example.services.GetReportServiceImpl;
import nl.tudelft.sem.template.example.services.ReviewServiceImpl;
import nl.tudelft.sem.template.model.BookData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@RestController
public class GetReportController implements GetReportApi {

    private final GetReportServiceImpl service;

    public GetReportController(BookDataRepository repo) {
        this.service = new GetReportServiceImpl(repo);
    }

    @Override
    public ResponseEntity<BookData> getReportBookIdUserIdInfoGet(Long bookId, String userId, String info) {
        return GetReportApi.super.getReportBookIdUserIdInfoGet(bookId, userId, info);
    }
}
