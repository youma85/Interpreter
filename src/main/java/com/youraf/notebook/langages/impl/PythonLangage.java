package com.youraf.notebook.langages.impl;

import java.io.ByteArrayOutputStream;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youraf.notebook.exceptions.WrongFormatException;
import com.youraf.notebook.langages.Langage;
import com.youraf.notebook.model.Command;
import com.youraf.notebook.model.Result;

@Component
public class PythonLangage  implements Langage {
	private static final String EMPTY = "";
	
	@Autowired
	private PythonInterpreter pythonInterpreter;
	
	@Override
	public Result execute(Command cmd) throws WrongFormatException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		pythonInterpreter.setOut(baos);
		pythonInterpreter.exec(getExtractedCode(cmd));
		Result executionResult = new Result();
		executionResult.setResult(new String(baos.toByteArray()).trim());
		return executionResult;
	}
	

	private String getExtractedCode(Command code) {
		String languagePrefix = code.getCode().split(" ")[0];
		return code.getCode().replace(languagePrefix, EMPTY).trim();
	}
}