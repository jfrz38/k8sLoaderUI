package ual.jf;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerApp {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello(){
    	ResponseEntity<String> re = new ResponseEntity<String>("Hello world 2", HttpStatus.OK);
    	return re;
    }
}
