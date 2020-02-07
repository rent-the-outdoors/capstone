package com.rto.capstone.repositories;

import com.rto.capstone.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityInterface extends JpaRepository<Activity, Long> {
}
