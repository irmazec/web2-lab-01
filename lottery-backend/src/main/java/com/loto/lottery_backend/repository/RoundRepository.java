package com.loto.lottery_backend.repository;

import com.loto.lottery_backend.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID>, JpaSpecificationExecutor<Round> {
    Optional<Round> findFirstByOrderByStartedAtDesc();
}
