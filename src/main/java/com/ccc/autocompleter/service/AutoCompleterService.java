package com.ccc.autocompleter.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ccc.quicksearch.QuickSearch;
@Service("autoCompleterService")
public class AutoCompleterService implements ApplicationContextAware {
	private static Logger log=LoggerFactory.getLogger(AutoCompleterService.class);
	private String fileName;
	private QuickSearch quickSearch;
	private ApplicationContext context;
	@PostConstruct
	public void init() {
		URL u=null;
		Resource resource;
		try {
			resource=context.getResource("classpath:model.json");
			u=resource.getURL();
		}catch(IOException e) {
			e.printStackTrace();
			log.error("Error opening model file: ",e);
		}

		Assert.notNull(u,"Url to model was not found");
		fileName=u.getFile();
		log.info("Initiating auto completer...");
		try {
			quickSearch=QuickSearch.newSearch(new File(fileName));
		} catch (IOException e) {
			log.error("error loading file: ",e);
		}
		log.info("Finished initiating auto completer service");
	}

	public String nextCharacter(String word) {
		if(log.isDebugEnabled())
			log.debug("Quick search most likely word for: " + word);
		return quickSearch.otherMostLikely(word);
	}

	public List<String> words(String word) {
		return quickSearch.mostLikelyWords(word);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context=applicationContext;
	}

}
