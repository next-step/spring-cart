package cart.controller;

import cart.domain.Member;
import cart.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/settings")
  public ModelAndView findAll(ModelAndView model) {
    List<Member> members = memberService.findAll();
    model.addObject("members", members);
    model.setViewName("settings");
    return model;
  }
}
