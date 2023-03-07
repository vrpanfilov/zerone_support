package ru.skillbox.zerone.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.zerone.support.model.dto.ChoiceListDto;
import ru.skillbox.zerone.support.service.ChoiceService;

@Controller
@RequestMapping("/choice")
@RequiredArgsConstructor
public class ChoiceController {

  private final ChoiceService choiceService;

  @GetMapping
  public String doChoice(Model model) {
    ChoiceListDto requests = choiceService.getChoiceListDto();
    model.addAttribute("requests", requests);
    return "choice";
  }
}
