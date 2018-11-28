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

import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.utility.webtest.Utility;

@Controller
public class MemoController {
	
	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoService mgr;
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.POST)
	public String delete( Model model, HttpServletRequest request) {
		
		boolean flag = dao.delete(Integer.parseInt(request.getParameter("memono")));
		
		if(flag) {
			model.addAttribute("col",request.getAttribute("col"));
			model.addAttribute("word",request.getAttribute("word"));
			model.addAttribute("nowPage",request.getAttribute("nowPage"));
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
		
		
	}
	
	
	@RequestMapping(value="/memo/delete", method=RequestMethod.GET)
	public String delete() {
		
		
		
		

		return "/memo/delete";
	}
	
	
	
	
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.POST)
	public String reply(MemoDTO dto) {
		
		boolean flag = mgr.reply(dto);
		
		if(flag) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
	
		
		}
	
	}
	
	@RequestMapping(value="/memo/reply", method=RequestMethod.GET)
	public String reply(int memono, Model model) {
		
		
		MemoDTO dto = dao.replyRead(memono);
		
		model.addAttribute("dto", dto);
		
		
		
		
		return "/memo/reply";
	}
	
	
	
	
	@RequestMapping(value="/memo/update", method=RequestMethod.POST)
	public String update(MemoDTO dto, Model model,HttpServletRequest request) {
		
		if(dao.update(dto)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
	
		
		}
	
	}
	@RequestMapping(value="/memo/update", method=RequestMethod.GET)
	public String update(int memono, Model model) {
		
		MemoDTO dto = dao.read(memono);
		
		System.out.println("updateaction, memono :" + memono);
		
		model.addAttribute("dto", dto);
		
		
		
		
		
		return "/memo/update";
	}
	
	
	
	
	@RequestMapping(value="/memo/create", method=RequestMethod.POST)
	public String create(MemoDTO dto) {
		
		if(dao.create(dto)) {
			return "redirect:/memo/list";
		}else {
			return "/error/error";
		}
		
		

	}
	@RequestMapping(value="/memo/create", method=RequestMethod.GET)
	public String create() {
		
		
		
		
		
		return "/memo/create";
	}
	
	
	
	@RequestMapping("/memo/read")
	public String read(int memono, HttpServletRequest request) {
		
		dao.updateViewcnt(memono);
		// 조회수 증가

		MemoDTO dto = dao.read(memono);
		// 번호로 조회한 내용 dto에 할당 
		
		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");
		// content 변수에 조회한 내용을 담고
		// 입력값 중 엔터들을 <br>태그로 바꿔주어 브라우저에서 한 줄 띄워서 출력되게끔
		dto.setContent(content);
	
		request.setAttribute("dto", dto);		
		
		
		
		
		return "/memo/read";
	}
	
	
	
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request) {
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if (col.equals("total")) {
			word = "";
		}
		
		int nowPage = 1;
		int recordPerPage = 5;
		
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		int sno = (nowPage-1)*recordPerPage + 1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		

		List<MemoDTO> list = dao.list(map);
		
		int totalRecord = dao.total(map);
		
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);		
		
		return "/memo/list";
	}
	
	
	
}
