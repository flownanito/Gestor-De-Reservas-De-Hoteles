
package com.proyect.reservationmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.RoomType;

import jakarta.persistence.LockModeType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
  Optional<RoomType> findByName(String name);
  Optional<RoomType> findByNameAndIdNot(String name, Long id);

  // NUEVO: para "blindar" reservas (bloqueo mientras dura la transacción)
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select rt from RoomType rt where rt.id = :id")
  Optional<RoomType> findByIdForUpdate(@Param("id") Long id);
}

