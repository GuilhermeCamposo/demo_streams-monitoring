package org.acme.kafka.producer;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Path;
import org.jboss.logging.Logger;

import org.acme.kafka.quarkus.Movie;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/movies")
public class MoviesResource {

  private static final Logger LOGGER = Logger.getLogger(MoviesResource.class);

  @Channel("movies")
  Emitter<Movie> emitter;

  @POST
  public Response enqueueMovie(Movie movie) {
      LOGGER.infof("Sending movie %s to Kafka", movie.getTitle());
      emitter.send(movie);
      return Response.accepted().build();
  }
    
}
