package ual.jf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerApp {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> main(){
    	ResponseEntity<String> re = new ResponseEntity<String>("Pag. por defecto.\n "
    			+ "8080 = Hello World\n "
    			+ "8081 = Hello World 1\n "
    			+ "8082 = Hello World 2 ", HttpStatus.OK);
    	try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return re;
    }
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello(){
    	ResponseEntity<String> re = new ResponseEntity<String>("Hello world 1", HttpStatus.OK);
    	return re;
    }
}
