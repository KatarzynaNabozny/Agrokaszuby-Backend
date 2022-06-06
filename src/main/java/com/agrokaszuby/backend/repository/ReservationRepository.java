package com.agrokaszuby.backend.repository;

import com.agrokaszuby.backend.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Query(
            value = "select * from reservation r\n" +
                    "join reservation_has_customers rc on rc.reservation_id = r.reservation_id\n" +
                    "join customer c on rc.customer_id = c.customer_id\n" +
                    "where c.email = ?1",
            nativeQuery = true)
    Collection<Reservation> findAllReservationsByCustomerEmail(String email);

    @Query(
            value = "select  * from reservation r\n" +
                    "join reservation_has_customers rc on rc.reservation_id =r.reservation_id\n" +
                    "join customer c on c.customer_id = rc.customer_id\n" +
                    "where r.start_date = ?1 and r.end_date = ?2 and c.email = ?3",
            nativeQuery = true)
    Collection<Reservation> findAllReservationsByCustomerEmailAndStartDateAndEndDate(
            LocalDateTime startDate, LocalDateTime endDate, String email);

    @Override
    Optional<Reservation> findById(Long reservationId);

    @Override
    Reservation save(Reservation reservation);

    @Override
    void deleteById(Long reservationId);

}
