package com.softwareverification.realestate.repository;

import com.softwareverification.realestate.entity.SavedHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedHistoryRepository  extends JpaRepository<SavedHistory, Integer> {
}
