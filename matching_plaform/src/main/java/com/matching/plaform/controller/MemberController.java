package com.matching.plaform.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.matching.plaform.domain.Member;
import com.matching.plaform.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/login")
	public String login(Model model,@RequestParam("memberId")String id,
			@RequestParam("memberPass")String pass,
			HttpSession session, HttpServletResponse rs)throws ServletException, IOException {
		
		int revalue = memberService.login(id, pass);
		
		if(revalue == 0) {
			rs.setContentType("text/html; charset=utf-8");
			PrintWriter out = rs.getWriter();
			out.println("<script>");
			out.println(" alert('존재하지 않는 아이디 입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return null;
		} else if(revalue == 2) {
			rs.setContentType("text/html; charset=utf-8");
			PrintWriter out = rs.getWriter();
			out.println("<script>");
			out.println(" alert('비밀번호가 다릅니다.');");
			out.println(" location.href='loginView'");
			out.println("</script>");
			return null;		
		}
		Member member = memberService.getMember(id);

		 model.addAttribute("member", member);
	    session.setAttribute("isLogin", true); 
	    model.addAttribute("isLogin", true);
		System.out.println("member.name : " + member.getName());
		
		return "redirect:/homePage";
	}
	
	@GetMapping("/memberLogout")
	public String logout(HttpSession session) {

	session.invalidate();

	return "redirect:/loginView";
	}
	
	@RequestMapping("/DuplicateCheck")
	public String DuplicateCheck(Model model, @RequestParam("memberId") String id) {
	model.addAttribute("id", id);
	
	return "member/DuplicateCheck";
	// return "member/DuplicateCheck.html";
	}
}
