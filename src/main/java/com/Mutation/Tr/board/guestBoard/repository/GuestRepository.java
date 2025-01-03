package com.Mutation.Tr.board.guestBoard.repository;


import com.Mutation.Tr.entity.GuestBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<GuestBoard, Long> {

    GuestBoard findByName(String name);

    GuestBoard findByEmail(String email);

    GuestBoard findById(long id);

}
