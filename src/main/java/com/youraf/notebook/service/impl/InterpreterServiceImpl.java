package com.youraf.notebook.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.youraf.notebook.exceptions.LangageNotFoundException;
import com.youraf.notebook.langages.Langage;
import com.youraf.notebook.langages.impl.SqlLangage;
import com.youraf.notebook.langages.impl.PythonLangage;
import com.youraf.notebook.model.Command;
import com.youraf.notebook.service.InterpreterService;

@Service
public class InterpreterServiceImpl implements InterpreterService {

	@Value("${module.langages}")
	private String[] langages;
	
	private Map<String, Langage> interpreters=new HashMap<String, Langage>();
	
	@Autowired
	private SqlLangage javaLangage;
	@Autowired
	private PythonLangage pythonLangage;
	
	 @PostConstruct
	 public void init(){
		 interpreters.put("%sql", javaLangage);
		 interpreters.put("%python", pythonLangage);
	 }
	 

	@Override
	public Langage getLangage(Command code) throws LangageNotFoundException {
		String languagePrefix = getPrefix(code);

		if (getLangage(languagePrefix)) {
			return interpreters.get(languagePrefix);
		}else {
			throw new LangageNotFoundException();
		}
	}

	private static final String REGEX = " ";

	public String getPrefix(Command code) {
		return code.getCode().split(REGEX)[0];
	}
	
	private boolean getLangage(String languagePrefix) {
		List<String> myList = Arrays.asList(langages);
		return myList.stream().anyMatch(str -> str.equals(languagePrefix));
	}

}
