package com.uca.capas.tercerTarea.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public ModelAndView ingresar() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ingresar");
		return mav;
	}
	
	@RequestMapping("/ingreso")
	public ModelAndView ingreso
	(
			@RequestParam(value = "first_name") String firstName, 
			@RequestParam(value = "last_name") String lastName,
			@RequestParam(value = "brith_date") String birthDate,
			@RequestParam(value = "birth_place") String birthPlace,
			@RequestParam String colegio,
			@RequestParam String telephone,
			@RequestParam String cellphone
			
	) throws ParseException {
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<String> errors = new ArrayList<String>();
		
		
		
		if(firstName.length() > 25 || firstName.length() < 1) 
			errors.add("El campo Nombres no puede ser mayor a 25 caracteres ni menor a un caracter");

		if(lastName.length() > 25 || lastName.length() < 1) 
			errors.add("El campo Apellidos no puede ser mayor a 25 caracteres ni menor a un caracter");
		
		
		
		if(compararFecha(birthDate))
			errors.add("Su fecha de nacimiento no puede ser menor del 1 de enero del 2003");
		
		
		if(birthPlace.length() > 25 || birthPlace.length() < 1) 
			errors.add("El campo de lugar de nacimiento no puede ser mayor a 25 caracteres ni menor a un caracter");
		
		

		if(colegio.length() > 100 || colegio.length() < 1) 
			errors.add("El campo de Colegio no puede ser mayor a 100 caracteres ni menor a un caracter");
		
		
		if(telephone.length() != 8) 
			errors.add("El campo de Teléfono fijo solo admite 8 caracteres exactos");
		
		
		if(cellphone.length() != 8) 
			errors.add("El campo de Teléfono móvil solo admite 8 caracteres exactos");
		
		

		if(errors.isEmpty()){
			
			mav.setViewName("succefullPage");
			
			
		} else {
			
			mav.addObject("errorList", errors);
			mav.setViewName("errorPage");
			
		}
			return mav;
	}


	public boolean compararFecha(String fecha_1) throws ParseException {
		String fechaMin = "2003-01-01";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 

		Date fecha_12 = format.parse(fecha_1);
		Date fecha_2 = format.parse(fechaMin);

		if(fecha_12.before(fecha_2)) {
			return true;
		}else {
			return false;
		}
	}
}
