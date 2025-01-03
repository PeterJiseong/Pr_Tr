package com.Mutation.Tr.board.guestBoard.controller;

import com.Mutation.Tr.board.guestBoard.service.GuestService;
import com.Mutation.Tr.entity.GuestBoard;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/guest")
public class GuestController {

    private final GuestService guestService;

    @GetMapping(value = "/boardList")
    public String boardList(Model model) {
        System.err.println("boardList");
        List<GuestBoard> guestBoardList = guestService.getGuestBoards();
        model.addAttribute("guestBoardList", guestBoardList);
        return "main/board/content/guestBoard";
    }

    @PostMapping(value = "/guestBoardWrite")
    public String guestBoardWrite(Model model, GuestBoard guestBoard  ,
                                  HttpServletRequest request) {

//        System.err.println("content : " + content);

        guestService.saveGuestBoard(guestBoard, request);
        System.err.println(guestBoard);
        List<GuestBoard> guestBoardList = guestService.getGuestBoards();

        model.addAttribute("guestBoardList", guestBoardList);

        return "main/board/replace/guestBoardReplace";
    }
}
