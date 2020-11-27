package com.jcom.microservicecoursemanagement.repository;

import com.jcom.microservicecoursemanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
