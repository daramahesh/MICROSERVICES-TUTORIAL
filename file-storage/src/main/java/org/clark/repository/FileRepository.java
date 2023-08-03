package org.clark.repository;

import org.clark.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {

    FileData findByFileName(String fileName);
}
