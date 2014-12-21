package com.benchmark.ushers.presentation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.ushers.dao.model.Area;
import com.benchmark.ushers.dao.model.Governorate;
import com.benchmark.ushers.dao.model.PreferredLocation;
import com.benchmark.ushers.dao.model.UserRole;

@Controller
public class DataEntryViewController  extends AbstractViewController{

	@RequestMapping(value = "/governorates", method = RequestMethod.GET)
	public ModelAndView getGovernorates(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.DATA_ENTRY_ROLE)) {
			List<Governorate> governorates = daoService.getGovernorateDaoImpl().findAll();
			model.addObject("governorates", governorates);
			model.setViewName("dataEnttry/governorates");
		}
		return model;

	}
	
	@RequestMapping(value = "/addGovernorateForm**", method = RequestMethod.GET)
	public ModelAndView addGovernorateForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("governorate", new Governorate());
		model.setViewName("dataEnttry/addGovernorate");
		return model;
	}
	
	@RequestMapping(value ="/addGovernorate", method = RequestMethod.POST)
	public String addGovernorate(@ModelAttribute("governorate") Governorate governorate) {
		daoService.getGovernorateDaoImpl().save(governorate);
		return "redirect:/governorates";
	}
	
	@RequestMapping(value = "/editGovernorate**", method = RequestMethod.GET)
	public ModelAndView editGovernorate(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Governorate governorate = daoService.getGovernorateDaoImpl().findOne(new Integer(id));
		model.addObject("governorate", governorate);
		model.setViewName("dataEnttry/addGovernorate");
		return model;
	}
	
	@RequestMapping("/deleteGovernorate")
	public ModelAndView deleteGovernorate(@RequestParam("id") String id) {
		daoService.getGovernorateDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Governorate> governorates = daoService.getGovernorateDaoImpl().findAll();
		model.addObject("governorates", governorates);
		model.setViewName("dataEnttry/governorates");
		return model;
	}
	
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public ModelAndView getAreas(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.DATA_ENTRY_ROLE)) {
			List<Area> areas = daoService.getAreaDaoImpl().findAll();
			model.addObject("areas", areas);
			model.setViewName("dataEnttry/areas");
		}
		return model;

	}
	
	@RequestMapping(value = "/addAreaForm**", method = RequestMethod.GET)
	public ModelAndView addAreaForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("area", new Area());
		model.setViewName("dataEnttry/addArea");
		return model;
	}
	
	@RequestMapping(value ="/addArea", method = RequestMethod.POST)
	public String addArea(@ModelAttribute("area") Area area) {
		daoService.getAreaDaoImpl().save(area);
		return "redirect:/areas";
	}
	
	@RequestMapping(value = "/editArea**", method = RequestMethod.GET)
	public ModelAndView editArea(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Area area = daoService.getAreaDaoImpl().findOne(new Integer(id));
		model.addObject("area", area);
		model.setViewName("dataEnttry/addArea");
		return model;
	}
	
	@RequestMapping("/deleteArea")
	public ModelAndView deleteArea(@RequestParam("id") String id) {
		daoService.getAreaDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Area> areas = daoService.getAreaDaoImpl().findAll();
		model.addObject("areas", areas);
		model.setViewName("dataEnttry/areas");
		return model;
	}
	
	@RequestMapping(value = "/preferredLocations", method = RequestMethod.GET)
	public ModelAndView getPreferredLocations(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.DATA_ENTRY_ROLE)) {
			List<PreferredLocation> areas = daoService.getPreferredLocationDaoImpl().findAll();
			model.addObject("locations", areas);
			model.setViewName("dataEnttry/preferredLocations");
		}
		return model;

	}
	
	@RequestMapping(value = "/addPreferredLocationsForm**", method = RequestMethod.GET)
	public ModelAndView addPreferredLocationsForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("location", new PreferredLocation());
		model.setViewName("dataEnttry/addPreferredLocation");
		return model;
	}
	
	@RequestMapping(value ="/addPreferredLocations", method = RequestMethod.POST)
	public String addPreferredLocations(@ModelAttribute("area") PreferredLocation area) {
		daoService.getPreferredLocationDaoImpl().save(area);
		return "redirect:/preferredLocations";
	}
	
	@RequestMapping(value = "/editPreferredLocations**", method = RequestMethod.GET)
	public ModelAndView editPreferredLocations(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		PreferredLocation location = daoService.getPreferredLocationDaoImpl().findOne(new Integer(id));
		model.addObject("location", location);
		model.setViewName("dataEnttry/addPreferredLocation");
		return model;
	}
	
	@RequestMapping("/deletePreferredLocations")
	public ModelAndView deletePreferredLocations(@RequestParam("id") String id) {
		daoService.getPreferredLocationDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<PreferredLocation> locations = daoService.getPreferredLocationDaoImpl().findAll();
		model.addObject("locations", locations);
		model.setViewName("dataEnttry/preferredLocations");
		return model;
	}
}
