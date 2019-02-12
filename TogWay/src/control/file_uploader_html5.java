/*package control;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

*//**
 * Servlet implementation class file_uploader_html5
 *//*
@WebServlet("/file_uploader_html5.do")
public class file_uploader_html5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		public String multiplePhotoUpload(HttpServletRequest request) {
	        // 파일정보
	        StringBuffer sb = new StringBuffer();
	        try {
	            // 파일명을 받는다 - 일반 원본파일명
	            String oldName = request.getHeader("file-name");
	            // 파일 기본경로 _ 상세경로
	            String filePath = "C:\\Users\\lenovo\\eclipse-workspace\\TogWay\\WebContent\\smarteditor2-2.8.2.3\\upload\\";
	            String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss")
	                          .format(System.currentTimeMillis()))
	                          .append(UUID.randomUUID().toString())
	                          .append(oldName.substring(oldName.lastIndexOf("."))).toString();
	            InputStream is = request.getInputStream();
	            OutputStream os = new FileOutputStream(filePath + saveName);
	            int numRead;
	            byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	            while ((numRead = is.read(b, 0, b.length)) != -1) {
	                os.write(b, 0, numRead);
	            }
	            os.flush();
	            os.close();
	            // 정보 출력
	            sb = new StringBuffer();
	            sb.append("&bNewLine=true")
	              .append("&sFileName=").append(oldName)
	              .append("&sFileURL=").append("http://localhost:8080/TogWay/smarteditor2-2.8.2.3/upload/")
	        .append(saveName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(sb.toString());
	        System.out.println(sb.toString());
	        return sb.toString();
	    }
	}
	
*/