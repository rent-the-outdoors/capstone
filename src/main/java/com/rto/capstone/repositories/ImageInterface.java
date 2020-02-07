package com.rto.capstone.repositories;

import com.rto.capstone.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageInterface extends JpaRepository<Image, Long> {
}
