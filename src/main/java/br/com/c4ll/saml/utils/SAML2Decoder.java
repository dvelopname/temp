package br.com.c4ll.saml.utils;

import org.opensaml.core.config.InitializationException;
import org.opensaml.messaging.context.MessageContext;
import org.opensaml.messaging.decoder.MessageDecodingException;
import org.opensaml.saml.common.SAMLObject;
import org.opensaml.saml.common.SignableSAMLObject;
import org.opensaml.saml.saml2.core.AuthnRequest;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;

public class SAML2Decoder {

	private static final String EMPTY_CHARACTER = " ";
	private static final String MORE_CHARACTER = "+";

	public static String decodeSamlRequest(String samlRequest)
			throws ComponentInitializationException, MessageDecodingException, InitializationException {
			
		samlRequest = swapSpacesCharactersSamlRequest(samlRequest);
		
		SimpleSAMLMessageDecoder decoder = new SimpleSAMLMessageDecoder();

		decoder.setDefaltedSAMLMessage(samlRequest);
		decoder.initialize();
		decoder.decode();

		MessageContext<SAMLObject> context = decoder.getMessageContext();
		SignableSAMLObject obj = (SignableSAMLObject) context.getMessage();
		
		AuthnRequest request = (AuthnRequest) obj;
		
		System.out.println(request.getIssuer().getValue());
		System.out.println(request.getNameIDPolicy().getFormat());
		System.out.println(request.getID());
		System.out.println(request.getAssertionConsumerServiceURL());
		
		System.out.println();
		return samlRequest;
	}

	private static String swapSpacesCharactersSamlRequest(String samlRequest) {
		return samlRequest.replace(EMPTY_CHARACTER, MORE_CHARACTER);
	}

}
