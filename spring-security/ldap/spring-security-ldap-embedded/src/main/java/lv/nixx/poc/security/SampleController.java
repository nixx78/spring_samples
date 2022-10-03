package lv.nixx.poc.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/secured")
	public String getSecuredEndpoint() {
		return "SecuredEndpoint:response";
	}
}
