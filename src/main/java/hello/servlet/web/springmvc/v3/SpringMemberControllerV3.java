package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {
        //return new ModelAndView("new-form");
        return "new-form";
    }


    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String save(Model model) {
        List<Member> members = memberRepository.findAll();

        //ModelAndView mv = new ModelAndView("members");

        //mv.getModel().put("members",members);
        //mv.addObject("members", members);
        model.addAttribute("members",members);

        return "members";


    }

    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("/save")
    public String members(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {


        Member member = new Member(username, age);
        memberRepository.save(member);

       model.addAttribute("member",member);
        //mv.getModel().put("member",member);
        //mv.addObject("member", member);
        return "save-result";
    }
}
