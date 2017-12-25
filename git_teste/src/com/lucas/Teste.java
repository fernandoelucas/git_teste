package com.lucas;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import com.sun.xml.internal.ws.util.StringUtils;

public class Teste {
	
	public static void main(String args[]) throws UnsupportedEncodingException {
		System.out.println();
		Map<String,String> replaceStrMap= new HashMap<>();
		replaceStrMap.put("teste", "teste1");
		replaceStrMap.put("trackingCode", "teste2");
		replaceStrMap.put("trackingCode", "teste2");
		String replaceStr= "";
		
		System.out.println(replaceTokens(replaceStr, replaceStrMap));
		
	}
	

	@Test
public static String replaceTokens(String replaceStr,Map<String,String> replaceStrMap) throws UnsupportedEncodingException{

	//replaceStrMap.put("${teste}", "teste1");
	//replaceStrMap.put("ai", "teste2");
	replaceStr= "O seu contato foi validado. C\\u00f3digo de refer\\u00eancia: ${trackingCode}. Boa Viagem.";
	
	;
	/*
        for (Map.Entry<String, String> entry : replaceStrMap.entrySet()) {        	
        	replaceStr=replaceStr.replaceAll(entry.getKey(), entry.getValue());  
        	
        }

        return replaceStr;
    }
    */
	String regex = "\\$\\{([^}]*)\\}";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(StringEscapeUtils.unescapeJava(replaceStr));
	boolean result = matcher.find();
	if (result) {
	    StringBuffer sb = new StringBuffer();
	    do {
	        String tokenKey = matcher.group(1);
	        String replacement = Matcher.quoteReplacement(replaceStrMap.get(tokenKey));
	        matcher.appendReplacement(sb, replacement);
	        result = matcher.find();
	    } while (result);
	    matcher.appendTail(sb);
	    return sb.toString();
	}
	return null;
	}
}
