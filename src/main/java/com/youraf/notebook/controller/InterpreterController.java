package com.youraf.notebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youraf.notebook.exceptions.WrongFormatException;
import com.youraf.notebook.model.Command;
import com.youraf.notebook.service.InterpreterService;

@RestController
public class InterpreterController {
	String regexFormat="%[a-zA-Z]{3,10}\\s.*";
	@Autowired
	private InterpreterService interpreterService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/execute")
	public ResponseEntity execute(@RequestBody Command cmd, HttpSession httpSession){
		try {
			validateCommand(cmd);
			return ResponseEntity
		            .status(HttpStatus.OK)                 
		            .body(interpreterService.getLangage(cmd).execute(cmd)); 
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Message");
		}
	}
	
	public void validateCommand(Command code) throws WrongFormatException {
        if(StringUtils.isEmpty(code.getCode()) || !code.getCode().matches(regexFormat)){
            throw new WrongFormatException();
        }
    }
}

