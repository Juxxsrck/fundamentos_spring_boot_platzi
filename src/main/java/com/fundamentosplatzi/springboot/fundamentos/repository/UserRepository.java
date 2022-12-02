package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.dto.UserDto;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u From User u Where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1% ")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthdateBetween(LocalDate begin, LocalDate end);

    //List<User> findByNameLikeOrderByIdDesc(String name);

    List<User> findByNameContainingOrderByIdAsc(String name);

    @Query("SELECT new com.fundamentosplatzi.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthdate)" +
            " FROM User u " +
            " WHERE u.birthdate=:parametroFecha " +
            "AND u.email=:parametroEmail ")
    Optional<UserDto> getAllByBirthdateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);
}
