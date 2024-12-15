package com.matching.plaform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.matching.plaform.domain.Board;
import com.matching.plaform.domain.Reply;
import com.matching.plaform.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private static final String DEFAULT_PATH = "src/main/resources/static/files/";

	 @GetMapping({ "/", "/homePage" })
	    public String boardbyCategory(Model model,
          @RequestParam(value = "categoryCode", defaultValue="1") int categoryCode,
          @RequestParam(value= "type", required=false, defaultValue="null")String type,
          @RequestParam(value= "keyword", required=false, defaultValue="null")String keyword) {
		 
		 
		 Map<String, Object> modelMap = boardService.boardbyCategory(categoryCode, type, keyword);
		 
	        Board board = boardService.getDetail(categoryCode);
	        model.addAllAttributes(modelMap);
	        model.addAttribute("board", board);
	        return "views/homePage";
	    }
	
	   @GetMapping("/detailView")
	    public String getDetail(Model model, @RequestParam("boardNo") int boardNo,
	    		@RequestParam(value="type", defaultValue="null") String type,
	    		@RequestParam(value="keyword", defaultValue="null") String keyword) {
		   
		   boolean searchOption = (type.equals("null") || keyword.equals("null")) ? false : true;
		   
	        Board board = boardService.getDetail(boardNo);
	        
	        List<Reply> replyList = boardService.replyList(boardNo);
	        
	        model.addAttribute("searchOption", searchOption);
	        model.addAttribute("board", board);	        
	        model.addAttribute("rList", replyList);
	        
	        if(searchOption) {
	        	model.addAttribute("type", type);
	        	model.addAttribute("keyword", keyword);
        	}
	        
	        return "views/detailView";
	    }
	
	@GetMapping("/writeBoard")
	public String writeBoard(Model model, @RequestParam("categoryCode") int categoryCode,
			HttpSession session) {
		
		String memberId = (String)session.getAttribute("memberId");
		Board board = boardService.getDetail(categoryCode);
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("board", board);
	    return "views/writeBoard";
	}

	
	@PostMapping("/writeBoard")
	public String writeBoard(Board board,
			@RequestParam(value="addFile", required=false) MultipartFile multipartFile)
					throws IOException {
		
		System.out.println("originName : " + multipartFile.getOriginalFilename());
		System.out.println("name : " + multipartFile.getName());
		
		if (multipartFile != null && !multipartFile.isEmpty()) {
			File parent = new File(DEFAULT_PATH);
			
			if (!parent.isDirectory() && !parent.exists()) {
				parent.mkdirs();
			}
			UUID uid = UUID.randomUUID();
			
			String extension = StringUtils.getFilenameExtension(
			multipartFile.getOriginalFilename());
			
			String saveName = uid.toString() + "." + extension;
			
			File file = new File(parent.getAbsolutePath(), saveName);
			log.info("file abs path : " + file.getAbsolutePath());
			log.info("file path : " + file.getPath());
			
			multipartFile.transferTo(file);
			
			board.setBoardFile1(saveName);
			} else {
			log.info("File 업로드 안됨");
			}
		
		boardService.writeBoard(board);
		return "redirect:/homePage?categoryCode=" + board.getCategoryCode();
	}
	
	@PostMapping("/updateForm")
	public String updateBoard(Model model, @RequestParam("categoryCode")int categoryCode,
			@RequestParam("boardNo")int boardNo, HttpSession session) {
		Board board = boardService.getDetail(boardNo);
		String memberId = (String)session.getAttribute("memberId");
		
		model.addAttribute("board", board);
		model.addAttribute("memberId", memberId);
		
		return "views/updateBoard";
	}
	
	@PostMapping("/update")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:/detailView?boardNo=" + board.getBoardNo();
	}
	
	@PostMapping("/delete")
	public String deleteBoard(Board board,
			@RequestParam("boardNo")int boardNo, @RequestParam("categoryCode")int categoryCode){
		boardService.deleteBoard(boardNo);
		return "redirect:/homePage?categoryCode=" + board.getCategoryCode();
	}
	
	@GetMapping("/fileDownload")
	public void download(HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		
	    String fileName = request.getParameter("fileName");
	    log.info("fileName : " + fileName);
	    
	    File parent = new File(DEFAULT_PATH);
	    
	    File file = new File(parent.getAbsolutePath(), fileName);
	    log.info("file.getName() : " + file.getName());
	    
	    response.setContentType("application/download; charset=UTF-8");
	    response.setContentLength((int) file.length());
	    
	    fileName = URLEncoder.encode(file.getName(), "UTF-8");
	    log.info("다운로드 fileName : " + fileName);
	    
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	    
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    
	    OutputStream out = response.getOutputStream();
	    FileInputStream fis = null;
	    fis = new FileInputStream(file);
	    FileCopyUtils.copy(fis, out);
	    
	    if (fis != null) {
	        fis.close();
	    }

	    out.flush();
	}

}
