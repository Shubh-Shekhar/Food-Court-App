package com.niit.UserService.repository;

import com.niit.UserService.model.Favourites;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourites, String> {

}
