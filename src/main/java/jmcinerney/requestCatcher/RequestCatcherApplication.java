package jmcinerney.requestCatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@SpringBootApplication
@RestController
public class RequestCatcherApplication {

	private final Logger log = LoggerFactory.getLogger(RequestCatcherApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RequestCatcherApplication.class, args);
	}

	@GetMapping("/*")
	public ResponseEntity catchAll(HttpServletRequest request) {
		log.info(request.getMethod() + " request received: " + request.getRequestURL());
		System.out.println("Headers:");
		for (Enumeration<?> e = request.getHeaderNames(); e.hasMoreElements();) {
			String nextHeaderName = (String) e.nextElement();
			String headerValue = request.getHeader(nextHeaderName);
			System.out.println(nextHeaderName + ":" + headerValue);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}