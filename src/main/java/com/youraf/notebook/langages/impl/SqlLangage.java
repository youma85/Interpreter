package com.youraf.notebook.langages.impl;

import org.springframework.stereotype.Component;

import com.youraf.notebook.langages.Langage;
import com.youraf.notebook.model.Command;
import com.youraf.notebook.model.Result;

@Component
public class SqlLangage implements Langage{
	@Override
	public Result execute(Command code) {
		Result executionResult = new Result();
		executionResult.setResult("The SQL langage is not yet ready");
		return executionResult;
	}
}