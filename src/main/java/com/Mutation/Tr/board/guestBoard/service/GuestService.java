package com.Mutation.Tr.board.guestBoard.service;

import com.Mutation.Tr.board.guestBoard.repository.GuestRepository;
import com.Mutation.Tr.entity.GuestBoard;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public List<GuestBoard> getGuestBoards() {

        List<GuestBoard> list = guestRepository.findAll();
//        System.err.println("iterator 전 ");
//        Iterator<GuestBoard> it = list.stream().iterator();
//        System.err.println("iterator 후");
//        while (it.hasNext()) {
//
//            GuestBoard board = it.next();
//            String ip = board.getIp();
//            ip = ip.substring(0,7);
//            board.setIp(ip);
//        }

        return list;
    }

    public GuestBoard saveGuestBoard(GuestBoard guestBoard, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        guestBoard.setRealIp(ip);
        guestBoard.setIp(ip.substring(0,6));

        return guestRepository.save(guestBoard);
    }
}
