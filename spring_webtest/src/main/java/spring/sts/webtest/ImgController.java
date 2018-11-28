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
import org.springframework.web.multipart.MultipartFile;

import spring.model.img.ImgDAO;
import spring.model.img.ImgDTO;
import spring.model.img.ImgService;
import spring.utility.webtest.Utility;



@Controller
public class ImgController {

	@Autowired
	private ImgDAO dao;
	
	@Autowired
	private ImgService ser;
	
	
	

	
	@RequestMapping(value="/img/delete", method=RequestMethod.POST)
	public String delete(int no,String passwd,Model model,HttpServletRequest request) {
		
		String upDir = request.getRealPath("/img/storage");
		String oldfile = request.getParameter("oldfile");
		
		
		Map map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		
	
		
		boolean rflag = dao.checkRefnum(no);
		boolean pflag = dao.passCheck(map);
	
		
		
			if(rflag==false && pflag==true) {
				
					if(dao.delete(no)){
			
					Utility.deleteFile(upDir, oldfile);	
					
					return "redirect:/img/list";
						
					}else {
						return "/error/error";
					}	
					
			}else {
	
			return "/error/error";
			}
	}


	@RequestMapping(value="/img/delete", method=RequestMethod.GET)
	public String delete() {
		
		
		
		return "/img/delete";
	}
	
	
	
	
	
	
	@RequestMapping(value="/img/reply", method=RequestMethod.POST)
	public String reply(ImgDTO dto,HttpServletRequest request, int no) {
		
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setGrpno(Integer.parseInt(request.getParameter("grpno")));
		dto.setIndent(Integer.parseInt(request.getParameter("indent")));
		dto.setAnsnum(Integer.parseInt(request.getParameter("ansnum")));
		dto.setFname(request.getParameter("fname"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setPasswd(request.getParameter("passwd"));
				
	
		boolean flag = ser.reply(dto);
		
		request.setAttribute("flag", flag);
		return "redirect:/img/list";
	}
	
	@RequestMapping(value="/img/reply", method=RequestMethod.GET)
	public String reply(HttpServletRequest request, int no) {
		
		ImgDTO dto = dao.replyRead(no);
		
		request.setAttribute("dto", dto);
		
		return "/img/reply";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/img/update", method=RequestMethod.POST)
	public String update(ImgDTO dto, Model model,HttpServletRequest request,
			String oldfile,MultipartFile fnameMF) {
		
		String upDir = request.getRealPath("/img/storage");
		
		Map map = new HashMap();
		map.put("no", dto.getNo()); 
		map.put("passwd",dto.getPasswd() );
	 				
	

		boolean flag = dao.passCheck2(map);

		if(flag==true){
		 	
		 	String fname = null;
		 	if(fnameMF.getSize() > 0){
		 		
		 		fname = Utility.saveFileSpring(fnameMF, upDir);
		 	}
		 		dto.setFname(fname);	 		
		 		if(dao.update(dto)); {
		 		Utility.deleteFile(upDir, oldfile);	
		 		return "redirect:/img/read";
		 		}
		}else {
			return "/error/error";
		}
		
	}	

	
	@RequestMapping(value="/img/update", method=RequestMethod.GET)
	public String update(int no, Model model) {
		
		ImgDTO dto = dao.read(no); 
		
		model.addAttribute("dto", dto);
		
		
		return "/img/update";
	}
	
	
	
	
	@RequestMapping(value="/img/create", method=RequestMethod.POST)
	public String create(HttpServletRequest request, ImgDTO dto) {
		
		
		String upDir = request.getRealPath("/img/storage");
		
		
		String fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
		
		dto.setFname(fname);
		
		if(dao.create(dto)) {
			return "redirect:/img/list";
		}else {
			Utility.deleteFile(upDir, fname);
			return "/error/error";
		}
	
			

	}
	
	
	@RequestMapping(value="/img/create", method=RequestMethod.GET)
	public String create() {
		
		
		
		return "/img/create";
	}
	
	
	
	
	
	@RequestMapping("/img/read")
	public String read(int no,Model model) {
		
		
		ImgDTO dto = dao.read(no);
		
		dao.upViewcnt(no);
	
		List list = dao.readImg(no);
		int[] noArr = (int[])list.get(0);
		String[] fArr = (String[])list.get(1);
		String content = dto.getContent();	   
		content = content.replaceAll("\r\n", "<br>");
		dto.setContent(content);
		
		model.addAttribute("list", list);
		model.addAttribute("dto", dto);
		model.addAttribute("noArr", noArr);		
		model.addAttribute("fArr", fArr);		
		
		
		
		
		return "/img/read";
	}
	
	
	
	
	
	@RequestMapping("/img/list")
	public String list(HttpServletRequest request) {
		
		String col=Utility.checkNull(request.getParameter("col"));
		String word=Utility.checkNull(request.getParameter("word")); 
		
		if(col.equals("total")) word="";
		//paging관련
		int nowPage =1;
		int recordPerPage=5;
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		//db에서 가져올 레코드의 순번
		int sno =((nowPage-1)*recordPerPage)+1;
		int eno =nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
	
		List<ImgDTO> list = dao.list(map);
		int totalRecord =dao.total(map);
		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);
		
		request.setAttribute("list", list);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);		
		request.setAttribute("paging", paging);		
		
		
		
		
		
		return "/img/list";
	}
	
	
	
	
	
	
	
	
}
