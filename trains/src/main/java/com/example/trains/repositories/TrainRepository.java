package com.example.trains.repositories;

import com.example.trains.models.Train;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainRepository extends CrudRepository<Train, Long> {

    List<Train> findByTosend(String tosend);
    List<Train> findBySendfrom(String sendfrom);

}