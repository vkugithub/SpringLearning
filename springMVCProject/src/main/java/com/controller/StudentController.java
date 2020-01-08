package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.Student;
import com.model.Student1;
import com.service.StudentServiceImpl;

@RequestMapping("/student")
@Controller
public class StudentController {
	
	private static final String APP_JSON="application/json";
	private static final String APP_XML="application/xml";
	
	@Autowired
	StudentServiceImpl studentService;
	
	

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("student") Student1 student,
			BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("command", student);
			return "form";
		}
		studentService.saveStudentDetails(student);
		model.put("command", student);
		model.addAttribute("name", "Student Name");
		return "formresult";
	}
	
	/*
	 * Method used to populate the Section list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("sections")
	public List<String> initializeSections() {

		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Research");
		return sections;
	}

	/*
	 * Method used to populate the country list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	/*
	 * Method used to populate the subjects list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("subjects")
	public List<String> initializeSubjects() {

		List<String> subjects = new ArrayList<String>();
		subjects.add("Physics");
		subjects.add("Chemistry");
		subjects.add("Life Science");
		subjects.add("Political Science");
		subjects.add("Computer Science");
		subjects.add("Mathmatics");
		return subjects;
	}

	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView student(@ModelAttribute("Spring") Student student) {
		return new ModelAndView("enroll", "student", new Student());
//		return new ModelAndView("student", "command", new Student());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveRegistration(@Valid Student student,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
//			model.put("command", result);
			return "enroll";
		}

		model.addAttribute("success", "Dear " + student.getFirstName()
				+ " , your Registration completed successfully");
		return "success";
	}
	
	@RequestMapping(value = "/studentRestFul", method = RequestMethod.POST,
			produces = APP_JSON, consumes=APP_JSON)
	@ResponseBody
	public Student1 studeRestFul(@RequestBody Student1 student) {
		
		studentService.saveStudentDetails(student);
//		student.setName("RestFul");
		return student;
	}
	
	@RequestMapping(value = "/studentRestFulMock", method = RequestMethod.POST,
			produces = APP_JSON, consumes=APP_JSON)
	@ResponseBody
	public Student1 studeRestFulMock(@ModelAttribute("Spring") Student1 student) {
		
		studentService.saveStudentDetails(student);
//		student.setName("RestFulMock");
		return student;
	}
}
