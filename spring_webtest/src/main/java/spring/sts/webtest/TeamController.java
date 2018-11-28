package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;
import spring.utility.webtest.Utility;

@Controller
public class TeamController {
	@Autowired
	private TeamDAO dao;
	
	
	
	
	
	
	@RequestMapping("/team/delete")
	public String delete(int no,TeamDTO dto,HttpServletRequest request) {
		
		
		boolean dflag = dao.chenkRefnum(no);
		boolean flag = false;
		
		request.setAttribute("flag", flag);
		request.setAttribute("dflag", dflag);
		if(dao.delete(no)) {
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
		
	}
		
		
	
	
	
	@RequestMapping(value="/team/reply", method=RequestMethod.POST)
	public String reply(TeamDTO dto, Model model) {
		
					Map map = new HashMap();
					
					map.put("grpno", dto.getGrpno());
					map.put("ansnum", dto.getAnsnum());
					
					dao.upAnsnum(map);
					boolean flag = dao.replyCreate(dto);

						
						
					model.addAttribute("flag", flag);
		

						if(dao.replyCreate(dto)) {
							return "redirect:/team/list";
						}else {
							return "/error/error";
						}
						
					}
					
	
	@RequestMapping(value="/team/reply", method=RequestMethod.GET)
	public String reply(int no, Model model) {
		
		
		TeamDTO dto	=dao.replyRead(no);
		
		model.addAttribute("dto", dto);
		
		
		return "/team/reply";
	}
	
	
	
	
	
	@RequestMapping(value="/team/update", method=RequestMethod.POST)
	public String update(TeamDTO dto, Model model) {
		
		boolean flag = dao.update(dto);
		
		model.addAttribute("flag", flag);
		
		if(dao.update(dto)) {
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
		
	}
	
	
	@RequestMapping(value="/team/update", method=RequestMethod.GET)
	public String update(int no, Model model) {
		
		TeamDTO dto = dao.read(no);
		
		
		model.addAttribute("dto", dto);
		
		
		
		
		return "/team/update";
	}
	
	
	
	@RequestMapping(value="/team/create", method=RequestMethod.POST)
	public String create(TeamDTO dto,Model model) {
		
		
	
		
		boolean flag = dao.update(dto);
		
		model.addAttribute("flag", flag);
		if(dao.create(dto)) {
			return "redirect:/team/list";
		}else {
			return "/error/error";
		}
		
	}
	
	
	@RequestMapping(value="/team/create", method=RequestMethod.GET)
	public String create() {
		
		
		
		return "/team/create";
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/team/read")
	public String read(HttpServletRequest request,int no) {
		
		
		TeamDTO dto = dao.read(no);
		
		request.setAttribute("dto", dto);
	
		
		
		
		
		return "/team/read";
		
	}
	
	
	
	
	
	@RequestMapping("/team/list")
	public String list(HttpServletRequest request) {
		
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
			// 검색 관련 처리
			
		if (col.equals("total")) {
			word = "";
		}
			
		int nowPage = 1;
		int recordPerPage = 5;
			// paging 관련 처리
			
		if (request.getParameter("nowPage") != null) {	
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
			// 처음 가져온 nowPage의 값이 null일 경우 형변환을 시도하면 Exception에러가 날 수 있으므로
			
		int sno = ((nowPage-1)*recordPerPage) + 1;
		int eno = nowPage * recordPerPage;
			// DB에서 가져올 레코드의 첫번호, 끝번호 설정
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		

		 
		List<TeamDTO> list = dao.list(map);
		
		int totalRecord = dao.total(map);
		
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		
		
		return "/team/list";
	}
	
	
}
