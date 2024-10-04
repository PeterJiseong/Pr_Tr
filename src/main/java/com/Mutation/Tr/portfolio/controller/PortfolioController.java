package com.Mutation.Tr.portfolio.controller;

import com.Mutation.Tr.portfolio.dto.PortfolioDTO;
import com.Mutation.Tr.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Value("${portfolio.htmlContentPath}")
    private String portfolioPath;
    @Value("${notion.portfolio.mainPage}")
    private String notionPortfolioMainPage;

    @GetMapping(value = "/input")
    public String writePortfolio(Model model) {

        return portfolioPath +"portfolio_wrireForm";
    }

    @PostMapping(value = "/submit")
    public String submitPortfolio(Model model, PortfolioDTO portfolioDTO) {
        System.err.println(portfolioDTO);
        return portfolioPath +"portfolio_main";
    }

    @GetMapping(value = {"/main","/submit"})
    public String portfolioMain(Model model) {
//        portfolioService.getNotionPage(notionPortfolioMainPage);
//        portfolioService.getDatabase();


        return portfolioPath + "/portfolio_main";
    }


    @ResponseBody
    @GetMapping(value = "/notionMainPage")
    public String notionMainPage() {
        return portfolioService.getNotionPage(notionPortfolioMainPage);
    }


}
