package ru.skillbox.zerone.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.zerone.support.model.dto.ChoiceDto;
import ru.skillbox.zerone.support.service.ChoiceService;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

  private final ChoiceService choiceService;

  @GetMapping("/{id}")
  public String doAnswer(@PathVariable Long id, Model model) {
    ChoiceDto dto = choiceService.getRequestById(id);
    model.addAttribute("request", dto);
    choiceService.sendEmail(dto);
    return "answer";
  }

  @PostMapping("/{id}")
  public String answer(@PathVariable Long id, ChoiceDto choiceDto) {
    choiceService.processAnswer(choiceDto);
    return "redirect:/choice/";
  }
}
