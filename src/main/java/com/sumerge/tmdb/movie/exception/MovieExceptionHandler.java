package com.sumerge.tmdb.movie.exception;


import com.sumerge.tmdb.movie.error.MovieErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleMovieNotFoundException(MovieNotFoundException exception)
    {
        MovieErrorResponse errorResponse=new MovieErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handleGenericMovieException(Exception exc)
    {

        // create a StudentErrorResponse
        MovieErrorResponse errorResponse= new MovieErrorResponse();

        //400 Bad request
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());


        // return ResponseEntity
        // error = actual body of the response
        // HttpStatus.NOT_FOUND = actual status of the response
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<MovieErrorResponse> handlePageNotFoundException(PageNotFoundException exception)
    {
        MovieErrorResponse errorResponse=new MovieErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());



        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }


}
