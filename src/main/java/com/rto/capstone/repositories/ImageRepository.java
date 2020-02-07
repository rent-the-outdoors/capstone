package com.rto.capstone.repositories;

import com.rto.capstone.models.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<PlaceImage, Long> {
}
