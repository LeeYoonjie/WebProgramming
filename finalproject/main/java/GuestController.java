package finalproject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/GuestControl")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GuestDAO dao;
	private ServletContext ctx;
	
	// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "finalproject/guestList.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new GuestDAO();
		ctx = getServletContext();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		dao = new GuestDAO();
		
		// 자바 리플렉션을 사용해 if, switch 없이 요청에 따라 구현 메서드가 실행되도록 함.
		Method m;
		String view = null;
		
		// action 파라미터 없이 접근한 경우
		if (action == null) {
			action = "listGuest";
		}
		
		try {
			// 현재 클래스에서 action 이름과 HttpServletRequest 를 파라미터로 하는 메서드 찾음
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			
			// 메서드 실행후 리턴값 받아옴
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			// 에러 로그를 남기고 view 를 로그인 화면으로 지정, 앞에서와 같이 redirection 사용도 가능.
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// POST 요청 처리후에는 리디렉션 방법으로 이동 할 수 있어야 함.
		if(view.startsWith("redirect:/")) {
			// redirect/ 문자열 이후 경로만 가지고 옴
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			// 지정된 뷰로 포워딩, 포워딩시 컨텍스트경로는 필요없음.
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);	
		}
	}
    
	public String addGuest(HttpServletRequest request) {
		Guest g = new Guest();
		try { 
			BeanUtils.populate(g, request.getParameterMap());
			dao.addGuest(g);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("방명록 추가 과정에서 문제 발생!!");
			request.setAttribute("error", "방명록이 정상적으로 등록되지 않았습니다!!");
			return listGuest(request);
		}
		
		return "redirect:/GuestControl?action=listGuest";
	}
	
	public String deleteGuest(HttpServletRequest request) {
    	int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delGuest(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("방명록 삭제 과정에서 문제 발생!!");
			request.setAttribute("error", "방명록이 정상적으로 삭제되지 않았습니다!!");
			return listGuest(request);
		}
		return "redirect:/GuestControl?action=listGuest";
	}
	
	public String updateGuest(HttpServletRequest request) {
	    int aid = Integer.parseInt(request.getParameter("aid"));
	    try {
	        Guest existingGuest = dao.getGuest(aid);
	        BeanUtils.populate(existingGuest, request.getParameterMap());
	        dao.updateGuest(existingGuest);
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("방명록 수정 과정에서 문제 발생!!");
	        request.setAttribute("error", "방명록을 정상적으로 수정하지 못했습니다!!");
	        return listGuest(request);
	    }

	    return "redirect:/GuestControl?action=listGuest";
	}
	
	public String listGuest(HttpServletRequest request) {
    	List<Guest> list;
		try {
			list = dao.getAll();
	    	request.setAttribute("guestlist", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("방명록 생성 과정에서 문제 발생!!");
			request.setAttribute("error", "방명록 목록이 정상적으로 처리되지 않았습니다!!");
		}
    	return "finalproject/guestList.jsp";
    }
    
    public String getGuest(HttpServletRequest request) {
        int aid = Integer.parseInt(request.getParameter("aid"));
        try {
			Guest g = dao.getGuest(aid);
			request.setAttribute("guest", g);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("방명록을 가져오는 과정에서 문제 발생!!");
			request.setAttribute("error", "방명록을 정상적으로 가져오지 못했습니다!!");
		}
    	return "finalproject/guestupdate.jsp";
    }
    
    
}