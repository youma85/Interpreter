package com.youraf.notebook.service;

import com.youraf.notebook.exceptions.LangageNotFoundException;
import com.youraf.notebook.langages.Langage;
import com.youraf.notebook.model.Command;

public interface InterpreterService {

	Langage getLangage(Command code) throws LangageNotFoundException;

}
