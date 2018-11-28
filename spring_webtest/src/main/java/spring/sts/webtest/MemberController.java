package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.model.member.MemberDAO;
import spring.model.member.MemberDTO;
import spring.utility.webtest.Utility;


@Controller
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	
	
	
	
	@RequestMapping("/admin/list")
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
		
		 
		List<MemberDTO> list = dao.list(map);
		
		int totalRecord = dao.total(map);
		
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		
		
		return "/member/list";
	}
	
	
	@RequestMapping(value="/member/delete",method=RequestMethod.POST)
	public String delete(HttpServletRequest request,HttpSession session,
			String id, Model model) {
		
		String upDir = request.getRealPath("/member/storage");
		String sid = (String)session.getAttribute("id");
		String grade = (String)session.getAttribute("grade");
		
		String oldfile = dao.getFname(id);
		
		
		boolean flag = dao.delete(id);
		
		
		if(flag) {
			if(oldfile!=null && !oldfile.equals("member.jpg"))
			Utility.deleteFile(upDir, oldfile);
			
			if(sid!=null && !grade.equals("A")) {
				session.invalidate();
				return "redirect:/";
			
		}else {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			return "redirect:/admin/list";
			}
		}else {	
			
			return "/error/error";
		}
		
		
		
		
	}
	
	
	@RequestMapping(value="/member/delete",method=RequestMethod.GET)
	public String delete(String id, HttpSession session, Model model) {
		
		if(id==null) id = (String)session.getAttribute("id");
		
		model.addAttribute("id", id);
		
		
		return "/member/delete";
	}
	
	
	
	
	
	@RequestMapping(value="/member/updateFile",method=RequestMethod.POST)
	public String updateFile(String id ,String oldfile, MultipartFile fnameMF,
			HttpServletRequest request, Model model) {
		
		
		String upDir = request.getRealPath("/member/storage");
		String fname = null;
		if(fnameMF.getSize()>0) {
			if(oldfile!=null && !oldfile.equals("member.jpg"))
				Utility.deleteFile(upDir, oldfile);
			fname = Utility.saveFileSpring(fnameMF, upDir);
		}
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("fname", fname);
		
		boolean flag = dao.updateFile(map);
		
		if(flag) {
			return "redirect:/member/read";
		}else {
			return "/error/error";
		}
	
		
	}
	
	
	@RequestMapping(value="/member/updateFile",method=RequestMethod.GET)
	public String updateFile() {
		
		
		
		return "/member/updateFile";
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/member/pwfind", method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String pwfind(@RequestParam Map<String, String> map) {
		
		String pw = dao.getPwFind(map);
		String str = null;
		if(pw!=null){
			str ="찾으시는 pw는 "+pw+" 입니다.";
		}else{
			str ="잘못된 정보를 입력하였습니다.";
		}
		
		return str;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/member/idfind", method=RequestMethod.GET,
				produces="text/plain;charset=UTF-8")
	public String idfind(@RequestParam Map<String, String> map) {
		
			String id = dao.getIdFind(map);
			String str = null;
			if(id!=null){
				str ="찾으시는 id는 "+id+" 입니다.";
			}else{
				str ="잘못된 정보를 입력하였습니다.";
			}

		return str;
		
	}
	
	
	
	
	
	
	@RequestMapping("/member/idpwfind")
	public String idpwfind() {
		
		
		
		return "/member/idpwfind";
	}
	
	
	
	
	
	@RequestMapping(value="/member/update",method=RequestMethod.POST)
	public String update(MemberDTO dto, HttpSession session,Model model,
			HttpServletRequest request) {
		
		boolean flag = dao.update(dto);
		
		if(flag) {
			if(session.getAttribute("id")!=null 
					&& session.getAttribute("grade").equals("A")) {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				return "redirect:/admin/list";	
		}else {	
			return "redirect:/";
		}
	}else {
			return "/error/error";
		}
		
	}
	

		@RequestMapping("/member/emailProc")
		public String emailProc(String email) {
			
			
			return "/member/email_form";
		}
		
		
		
		
		

	@RequestMapping(value="/member/update",method=RequestMethod.GET)
	public String update(String id, Model model) {
		MemberDTO dto = dao.read(id);
		
		model.addAttribute("dto", dto);
		
		
		
		return "/member/update";
	}
	
	
	
	@RequestMapping(value="/member/updatePasswd",method=RequestMethod.POST)
	public String updatePasswd(Model model,String id,String passwd, String oldpasswd) {
		
		Map map = new HashMap();
	 	map.put("id", id);
	 	map.put("oldpasswd", oldpasswd);
	 	map.put("passwd", passwd);

	 	boolean pflag=dao.checkPasswd(map);
	 	boolean flag=false;
	 	if(pflag)
	 	flag=dao.updatePasswd(map);
	 	
	 	model.addAttribute("pflag", pflag);
	 	model.addAttribute("flag", flag);
		
		
		
		return "/member/updatePasswdProc";
	}
	
	@RequestMapping(value="/member/updatePasswd",method=RequestMethod.GET)
	public String updatePasswd() {
		
		
		
		
		return "/member/updatePasswd";
	}
	
	
	
	
	@RequestMapping("/member/read")
	public String read(String id, HttpSession session, Model model) {
		
		if(id==null) {
			id = (String)session.getAttribute("id");
		}
		MemberDTO dto = dao.read(id);
		
		model.addAttribute("dto", dto);
		
		
		
		
		
		return "/member/read";
				
	}
	
	
	
	
	
	
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		
		
		session.invalidate();
		
		
		
		
		
		return "redirect:/";
	}
	
	
	
	
	
	
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	public String login(String id, String passwd,String c_id, HttpSession session,
		Model model,HttpServletResponse response, HttpServletRequest request) {
		
		
	
		Map map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		
		boolean flag = dao.loginCheck(map);
		
		String url = "/error/passwdError";
		
		
	
		if (flag) {
			String grade = dao.getGrade(id);
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
			
			 // ---------------------------------------------- 
		    // Cookie 저장, Checkbox는 선택하지 않으면 null 임 
		    // ---------------------------------------------- 
		    Cookie cookie = null; 
		       
		   
		       
		    if (c_id != null){  // 처음에는 값이 없음으로 null 체크로 처리
		      cookie = new Cookie("c_id", "Y");    // 아이디 저장 여부 쿠키 
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록 
		   
		      cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키  
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록  
		         
		    }else{ 
		      cookie = new Cookie("c_id", "");     // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		         
		      cookie = new Cookie("c_id_val", ""); // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		    } 
		    
		    url = "redirect:/";
		    // --------------------------------------------- 
		    /**댓글쓰기 페이지로 돌아가기위한 데이터*/
		    String rflag = request.getParameter("flag");
		    String bbsno = request.getParameter("bbsno");
		    String nPage = request.getParameter("nPage");
		    String nowPage = request.getParameter("nowPage");
		    String col = request.getParameter("col");
		    String word = request.getParameter("word");
		    /**댓글쓰기는 페이지로 이동 */
		    
		    if(rflag !=null && !rflag.equals("")) {
		    	
		    	url = "redirect:"+rflag;
		    	model.addAttribute("bbsno", bbsno);
		    	model.addAttribute("nPage", nPage);
		    	model.addAttribute("nowPage", nowPage);
		    	model.addAttribute("col", col);
		    	model.addAttribute("word", word);
		    	
		    }
		}
		
	
		return url;
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/member/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		
		String c_id = "";     // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값
		 
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		 
		if (cookies != null){ 
		 for (int i = 0; i < cookies.length; i++) { 
		   cookie = cookies[i]; 
		 
		   if (cookie.getName().equals("c_id")){ 
		     c_id = cookie.getValue();     // Y 
		   }else if(cookie.getName().equals("c_id_val")){ 
		     c_id_val = cookie.getValue(); // user1... 
		   } 
		 } 
		} 
		
		/*----쿠키설정 내용 끝----------------------------*/
		
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		
			
		
		
		
		return "/member/login";
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/member/emailCheck", method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String emailCheck(String email) {
		boolean flag = dao.duplicateEmail(email);
		String str = null;
		
		if(flag) {
			str = "중복되어서 사용할 수 없습니다.";
		}else {
			str = "중복 아님, 사용 가능합니다.";
		}
		
		return str;
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/member/idCheck", method=RequestMethod.GET,
			produces="text/plain;charset=UTF-8")
	public String idCheck(String id) {
		
		boolean flag = dao.duplicateID(id);
		String str = null;
		
		if(flag) {
			str = "중복되어서 사용할 수 없습니다.";
		}else {
			str = "중복 아님, 사용 가능합니다.";
			
		}
		
		return str;
		
	}
	
		


	
	
	@RequestMapping("/member/createProc")
	public String pcreate(MemberDTO dto, HttpServletRequest request) {
		
		
		String str = null;
		String url = "/member/pcreate";
		
		if (dao.duplicateID(dto.getId())) {
			str = "존재하는 아이디입니다. ID 중복 확인을 하세요";
			request.setAttribute("str", str);
		}
		else if (dao.duplicateEmail(dto.getEmail())) {
			str = "존재하는 이메일입니다. Email 중복 확인을 하세요";
			request.setAttribute("str", str);
		}else {
			
			String upDir = request.getRealPath("/member/storage");
			
			int size = (int)dto.getFnameMF().getSize();
			String fname = null;
			
			if (size > 0) {
				fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
			}
			else {
				fname = "member.jpg";
			}
			
			dto.setFname(fname);
			
			boolean flag = dao.create(dto);
			
			request.setAttribute("flag", flag);
			
			url = "/member/createProc";
					
			
		}
			
		
		return url;
	}
	
	
	
	
	@RequestMapping("/member/create")
	public String create() {
		
		
		
		return "/member/create";
	}
	@RequestMapping("/member/agree")
	public String agree() {
		
		
		
		return "/member/agree";
	}
	
	
	
}
