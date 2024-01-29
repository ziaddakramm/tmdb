package com.sumerge.tmdb;

import com.sumerge.tmdb.movie.dao.MovieDAO;
import com.sumerge.tmdb.movie.dao.MovieRepository;
import com.sumerge.tmdb.movie.entities.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmdbApplication.class, args);
	}



//	@Bean
//	//CommandLineRunner--> Executed after the Spring Beans have been loaded
//	//CommandLineRunner interface is used to indicate that a bean should run when it is contained within a Spring application context.
//
//	//Beans injected automatically because of @Bean
//	//no need for autowiring
//	public CommandLineRunner commandLineRunner(MovieDAO movieDAO) {
//		return runner -> {
////            createCourseAndStudents(appDAO);
////            retrieveCourseAndStudents(appDAO);
////            retrieveStudentAndCourses(appDAO);
////            addMoreCoursesToStudent(appDAO);
////            deleteCourse(appDAO);
//			createMovie(movieDAO);
////			retrieveById(movieRepository);
//		};
//
//	}
//
//	private void retrieveById(MovieRepository movieRepository) {
//
//		Optional<Movie> movie=movieRepository.findById(1);
//		System.out.println(movie);
//	}
//
//	private void createMovie(MovieDAO movieDAO) {
////		Movie movie=new Movie(
////				false,
////				"/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
////				"en",
////				"The Shawshank Redemption",
////				"Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
////				127.465,
////				"/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
////				"1994-09-23",
////				"The Shawshank Redemption",
////				false,
////				8.711,
////				25439
////		);
////
//
//
//		Movie movie=new Movie(
//				false,
//				"/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg",
//				"en",
//				"The Godfather 4",
//				"Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
//				112.113,
//				"/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
//				"1972-03-14",
//				"The Godfather",
//				false,
//				8.708,
//				19365
//		);
//
//		movieDAO.save(movie);
//	}
}


//"adult": false,
//		"backdrop_path": "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
//		"genre_ids": [
//		18,
//		80
//		],
//		"id": 278,
//		"original_language": "en",
//		"original_title": "The Shawshank Redemption",
//		"overview": "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
//		"popularity": 127.465,
//		"poster_path": "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
//		"release_date": "1994-09-23",
//		"title": "The Shawshank Redemption",
//		"video": false,
//		"vote_average": 8.711,
//		"vote_count": 25439