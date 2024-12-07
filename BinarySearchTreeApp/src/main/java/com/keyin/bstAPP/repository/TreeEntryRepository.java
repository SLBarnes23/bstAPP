package com.keyin.bstAPP.repository;

import com.keyin.bstAPP.model.TreeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeEntryRepository extends JpaRepository<TreeEntry, Long> {
}
