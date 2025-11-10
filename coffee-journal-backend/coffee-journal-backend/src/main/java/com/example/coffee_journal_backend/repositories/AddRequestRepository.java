package com.example.coffee_journal_backend.repositories;

import com.example.coffee_journal_backend.models.AddRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddRequestRepository extends JpaRepository<AddRequest, Integer> {
}
