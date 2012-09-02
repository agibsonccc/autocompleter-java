package com.ccc.autocompleter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccc.autocompleter.service.AutoCompleterService;

/**
 * 
 * @author Adam Gibson
 *
 */
@Controller
@RequestMapping("/autocomplete")
public class HomeController {
	private static Logger log=LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private AutoCompleterService autoCompleterService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String home(Model model) {

		return "home";
	}
	/**
	 * Performs quick search relative to the query term passed in
	 * @param word the query term to ask quick search for
	 * @return a json response for auto complete
	 */
	@RequestMapping(value = "/nextchar", method = RequestMethod.GET)
	public @ResponseBody List<Map<String,String>> home(@RequestParam("q") String word) {
		//	String c=autoCompleterService.nextCharacter(word);
		List<String> list=autoCompleterService.words(word);
		List<Map<String,String>> retList = new ArrayList<Map<String,String>>();
		for(int i=0;i<list.size();i++) {
			Map<String,String> ret = new HashMap<String,String>();
			ret.put("id",String.valueOf(i));
			ret.put("value",word + list.get(i));
			retList.add(ret);
		}

		if(log.isDebugEnabled())
			log.debug("Returned: " + word); 
		return retList;
	}

}
