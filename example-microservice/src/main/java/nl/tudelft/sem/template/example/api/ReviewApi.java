/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package nl.tudelft.sem.template.example.api;

import nl.tudelft.sem.template.example.api.ApiUtil;
import nl.tudelft.sem.template.example.model.Review;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-05T16:23:47.330644900+01:00[Europe/Amsterdam]")
@Validated
@Tag(name = "review", description = "actions for reviews")
public interface ReviewApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /review/delete/{reviewId}/{userId} : deletes a review
     * deletes the review from review database, book and user
     *
     * @param reviewId id of review to be deleted (required)
     * @param userId user who tries to delete review (required)
     * @return Successful operation (status code 200)
     *         or cannot find review (status code 400)
     *         or permission denied - not owner or admin (status code 403)
     */
    @Operation(
        operationId = "reviewDeleteReviewIdUserIdDelete",
        summary = "deletes a review",
        description = "deletes the review from review database, book and user",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "cannot find review"),
            @ApiResponse(responseCode = "403", description = "permission denied - not owner or admin")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/review/delete/{reviewId}/{userId}"
    )
    default ResponseEntity<Void> reviewDeleteReviewIdUserIdDelete(
        @Parameter(name = "reviewId", description = "id of review to be deleted", required = true, in = ParameterIn.PATH) @PathVariable("reviewId") Long reviewId,
        @Parameter(name = "userId", description = "user who tries to delete review", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /review/mostUpvoted/{userId} : get the top reviews for the user
     * searches the database for the reviews that are made by that user and returns top three most recent ones
     *
     * @param userId the user for which we are getting top reviews (required)
     * @return Successful operation (status code 200)
     *         or cannot find user (status code 400)
     */
    @Operation(
        operationId = "reviewMostUpvotedUserIdGet",
        summary = "get the top reviews for the user",
        description = "searches the database for the reviews that are made by that user and returns top three most recent ones",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class)))
            }),
            @ApiResponse(responseCode = "400", description = "cannot find user")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/review/mostUpvoted/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<List<Review>> reviewMostUpvotedUserIdGet(
        @Parameter(name = "userId", description = "the user for which we are getting top reviews", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"commentList\" : [ 0, 0 ], \"pinned\" : false, \"rating\" : 4, \"title\" : \"wow\", \"userId\" : 10, \"upvote\" : 453, \"bookNotion\" : \"POSITIVE\", \"bookId\" : 1234, \"downvote\" : 345, \"spoiler\" : true, \"timeCreated\" : \"2013-10-24T00:00:00.000+00:00\", \"id\" : 10, \"text\" : \"nice book\", \"lastEditTime\" : \"2013-10-24T00:00:00.000+00:00\" }, { \"commentList\" : [ 0, 0 ], \"pinned\" : false, \"rating\" : 4, \"title\" : \"wow\", \"userId\" : 10, \"upvote\" : 453, \"bookNotion\" : \"POSITIVE\", \"bookId\" : 1234, \"downvote\" : 345, \"spoiler\" : true, \"timeCreated\" : \"2013-10-24T00:00:00.000+00:00\", \"id\" : 10, \"text\" : \"nice book\", \"lastEditTime\" : \"2013-10-24T00:00:00.000+00:00\" } ]";
                    nl.tudelft.sem.template.example.api.ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /review/pin/{reviewId} : pinning a review
     * places the review at the start of the list of reviews for the book
     *
     * @param reviewId the reviewId of the desired review to pin, set pinned boolean to true (required)
     * @param body the value to which we set the pinned boolean of the review (required)
     * @return Successful operation (status code 200)
     *         or Invalid review (status code 400)
     */
    @Operation(
        operationId = "reviewPinReviewIdPut",
        summary = "pinning a review",
        description = "places the review at the start of the list of reviews for the book",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid review")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/review/pin/{reviewId}",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> reviewPinReviewIdPut(
        @Parameter(name = "reviewId", description = "the reviewId of the desired review to pin, set pinned boolean to true", required = true, in = ParameterIn.PATH) @PathVariable("reviewId") Long reviewId,
        @Parameter(name = "body", description = "the value to which we set the pinned boolean of the review", required = true) @Valid @RequestBody Boolean body
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /review : create a new review to a specific book
     * creates and stores a new review in the database that is associated with the user who created it and the book it was created to - only adds review when there are no profanities
     *
     * @param review Create a new review for a book (required)
     * @return Successful operation (status code 200)
     *         or Internal server error - cannot save review (status code 405)
     *         or Invalid book or user (status code 400)
     *         or profanities detected (status code 406)
     */
    @Operation(
        operationId = "reviewPost",
        summary = "create a new review to a specific book",
        description = "creates and stores a new review in the database that is associated with the user who created it and the book it was created to - only adds review when there are no profanities",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "405", description = "Internal server error - cannot save review"),
            @ApiResponse(responseCode = "400", description = "Invalid book or user"),
            @ApiResponse(responseCode = "406", description = "profanities detected")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/review",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Review> reviewPost(
        @Parameter(name = "Review", description = "Create a new review for a book", required = true) @Valid @RequestBody Review review
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"commentList\" : [ 0, 0 ], \"pinned\" : false, \"rating\" : 4, \"title\" : \"wow\", \"userId\" : 10, \"upvote\" : 453, \"bookNotion\" : \"POSITIVE\", \"bookId\" : 1234, \"downvote\" : 345, \"spoiler\" : true, \"timeCreated\" : \"2013-10-24T00:00:00.000+00:00\", \"id\" : 10, \"text\" : \"nice book\", \"lastEditTime\" : \"2013-10-24T00:00:00.000+00:00\" }";
                    nl.tudelft.sem.template.example.api.ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /review/{reviewId} : getting a review
     * display the review with this reviewId from the database
     *
     * @param reviewId the reviewId we are searching for (required)
     * @return Successful operation (status code 200)
     *         or cannot find review (status code 400)
     */
    @Operation(
        operationId = "reviewReviewIdGet",
        summary = "getting a review",
        description = "display the review with this reviewId from the database",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "400", description = "cannot find review")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/review/{reviewId}",
        produces = { "application/json" }
    )
    default ResponseEntity<Review> reviewReviewIdGet(
        @Parameter(name = "reviewId", description = "the reviewId we are searching for", required = true, in = ParameterIn.PATH) @PathVariable("reviewId") Long reviewId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"commentList\" : [ 0, 0 ], \"pinned\" : false, \"rating\" : 4, \"title\" : \"wow\", \"userId\" : 10, \"upvote\" : 453, \"bookNotion\" : \"POSITIVE\", \"bookId\" : 1234, \"downvote\" : 345, \"spoiler\" : true, \"timeCreated\" : \"2013-10-24T00:00:00.000+00:00\", \"id\" : 10, \"text\" : \"nice book\", \"lastEditTime\" : \"2013-10-24T00:00:00.000+00:00\" }";
                    nl.tudelft.sem.template.example.api.ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /review/spoiler/{reviewId} : adding a spoiler flag to the review
     * setting the spoiler value in the review to true
     *
     * @param reviewId the reviewId of the desired review to add a spoiler warning to (required)
     * @return Successful operation (status code 200)
     *         or Invalid review (status code 400)
     */
    @Operation(
        operationId = "reviewSpoilerReviewIdPut",
        summary = "adding a spoiler flag to the review",
        description = "setting the spoiler value in the review to true",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid review")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/review/spoiler/{reviewId}"
    )
    default ResponseEntity<Void> reviewSpoilerReviewIdPut(
        @Parameter(name = "reviewId", description = "the reviewId of the desired review to add a spoiler warning to", required = true, in = ParameterIn.PATH) @PathVariable("reviewId") Long reviewId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /review/update/{userId} : updates a review
     * applies the changes made to the review, check for profanities in text
     *
     * @param userId user who tries to edit review (required)
     * @param review updates a review for the book (required)
     * @return Successful operation (status code 200)
     *         or Invalid input (status code 405)
     *         or cannot find user (status code 400)
     *         or permission denied - not owner or admin (status code 403)
     *         or profanities detected (status code 406)
     */
    @Operation(
        operationId = "reviewUpdateUserIdPut",
        summary = "updates a review",
        description = "applies the changes made to the review, check for profanities in text",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "400", description = "cannot find user"),
            @ApiResponse(responseCode = "403", description = "permission denied - not owner or admin"),
            @ApiResponse(responseCode = "406", description = "profanities detected")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/review/update/{userId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Review> reviewUpdateUserIdPut(
        @Parameter(name = "userId", description = "user who tries to edit review", required = true, in = ParameterIn.PATH) @PathVariable("userId") Long userId,
        @Parameter(name = "Review", description = "updates a review for the book", required = true) @Valid @RequestBody Review review
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"commentList\" : [ 0, 0 ], \"pinned\" : false, \"rating\" : 4, \"title\" : \"wow\", \"userId\" : 10, \"upvote\" : 453, \"bookNotion\" : \"POSITIVE\", \"bookId\" : 1234, \"downvote\" : 345, \"spoiler\" : true, \"timeCreated\" : \"2013-10-24T00:00:00.000+00:00\", \"id\" : 10, \"text\" : \"nice book\", \"lastEditTime\" : \"2013-10-24T00:00:00.000+00:00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /review/vote/{reviewId} : upvoting or downvoting a review
     * sending either a 0 or 1 to server for downvoting or upvoting a review respectfully
     *
     * @param reviewId the reviewId of the desired review for upvote or downvote (required)
     * @param body 1 for upvote or 0 for downvote (optional)
     * @return Successful operation (status code 200)
     *         or Invalid review (status code 400)
     */
    @Operation(
        operationId = "reviewVoteReviewIdPut",
        summary = "upvoting or downvoting a review",
        description = "sending either a 0 or 1 to server for downvoting or upvoting a review respectfully",
        tags = { "review" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid review")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/review/vote/{reviewId}",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> reviewVoteReviewIdPut(
        @Parameter(name = "reviewId", description = "the reviewId of the desired review for upvote or downvote", required = true, in = ParameterIn.PATH) @PathVariable("reviewId") Long reviewId,
        @Parameter(name = "body", description = "1 for upvote or 0 for downvote") @Valid @RequestBody(required = false) Integer body
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
