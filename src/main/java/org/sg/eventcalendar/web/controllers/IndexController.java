package org.sg.eventcalendar.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	//INDEX
	@RequestMapping
    public String getIndexPage() {		
        return "index";
    }
}
