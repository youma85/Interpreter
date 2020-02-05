package com.youraf.notebook.langages;

import com.youraf.notebook.exceptions.WrongFormatException;
import com.youraf.notebook.model.Command;
import com.youraf.notebook.model.Result;

public interface Langage {	
	Result execute(Command cmd) throws WrongFormatException;
}
