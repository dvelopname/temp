package br.com.c4ll.saml.sso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.xml.security.exceptions.Base64DecodingException;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.messaging.decoder.MessageDecodingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import br.com.c4ll.saml.utils.SAML2Decoder;
import net.shibboleth.utilities.java.support.component.ComponentInitializationException;

@Controller
@RequestMapping("/idp/profile/SAML2")
public class SAML2ProfileRequestController {
	
	@GetMapping("/Redirect/SSO")
	public String redirectSSO(@RequestParam("RelayState") String relayState, @RequestParam("SAMLRequest") String samlRequest) throws InitializationException {
	
		try {
			samlRequest = SAML2Decoder.decodeSamlRequest(samlRequest);
		} catch (MessageDecodingException e) {
			e.printStackTrace();
		} catch (ComponentInitializationException e) {
			e.printStackTrace();
		}
	
		return "login-form";
	}
	
}
