package net.personaldiary.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.personaldiary.springboot.entity.Diary;

/*This repository will provide CRUD operation for "Diary" entity */
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
