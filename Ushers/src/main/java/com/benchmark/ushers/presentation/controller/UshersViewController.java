package com.benchmark.ushers.presentation.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.ushers.dao.model.Area;
import com.benchmark.ushers.dao.model.Governorate;
import com.benchmark.ushers.dao.model.PreferredLocation;
import com.benchmark.ushers.dao.model.UserRole;
import com.benchmark.ushers.dao.model.Usher;

@Controller
public class UshersViewController extends AbstractViewController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
	@RequestMapping(value = "/ushers", method = RequestMethod.GET)
	public ModelAndView getGovernorates(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.USHER_ROLE)) {
			List<Usher> ushers = daoService.getUsherDaoImpl().findAll();
			model.addObject("ushers", ushers);
			model.setViewName("ushers/ushers");
		}
		return model;

	}

	@RequestMapping(value = "/addUsherForm**", method = RequestMethod.GET)
	public ModelAndView addUsherForm() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usher", new Usher());
		modelView.setViewName("ushers/addUsher");
		initModelList(modelView);
		return modelView;
	}
	
	@RequestMapping(value = "/viewUsher", method = RequestMethod.GET)
	public ModelAndView viewUsher(@RequestParam("id") String id) {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usher", daoService.getUsherDaoImpl().findOne(id));
		modelView.setViewName("ushers/usherProfile");
		initModelList(modelView);
		return modelView;
	}


	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/addUsher", method = RequestMethod.POST)
	public String addUsher(@ModelAttribute("usher") Usher usher,
			@RequestParam("file") MultipartFile[] files) throws Exception {

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			byte[] bytes = file.getBytes();
			switch (i) {
			case 0:
				usher.setPhoto1(bytes);
				break;
			case 1:
				usher.setPhoto2(bytes);
				break;
			case 2:
				usher.setPhoto3(bytes);
				break;
			case 3:
				usher.setPhoto4(bytes);
				break;
			default:
			}
		}
		daoService.storeUsher(usher);
		return "redirect:/ushers";
	}

	private void initModelList(ModelAndView model) {
		// add ushers types
		List<String> ushersTypes = new ArrayList<String>();
		ushersTypes.add("Instore");
		ushersTypes.add("Activiation");
		model.addObject("usherTypes", ushersTypes);

		// add ushers Caliber
		List<String> ushersCaliber = new ArrayList<String>();
		ushersCaliber.add("Smalls stores A");
		ushersCaliber.add("Small stores B");
		ushersCaliber.add("Hypermarket A");
		ushersCaliber.add("Hypermarket B");
		model.addObject("ushersCalibers", ushersCaliber);

		// add ushers marital Status
		List<String> maritalStatus = new ArrayList<String>();
		maritalStatus.add("Married");
		maritalStatus.add("Single");
		maritalStatus.add("Widow");
		maritalStatus.add("Divorced");
		model.addObject("maritalStatus", maritalStatus);

		// add ushers gender
		List<String> gender = new ArrayList<String>();
		gender.add("Male");
		gender.add("Female");
		model.addObject("gender", gender);

		// add ushers areas
		List<Area> areas = daoService.getAreaDaoImpl().findAll();
		model.addObject("areas", areas);

		// add ushers governorate
		List<Governorate> governorates = daoService.getGovernorateDaoImpl()
				.findAll();
		model.addObject("governorates", governorates);

		// add ushers preferred Locations
		List<PreferredLocation> preferredLocations = daoService
				.getPreferredLocationDaoImpl().findAll();
		model.addObject("preferredLocations", preferredLocations);

		// add ushers preferred Shifts
		List<String> preferredShift = new ArrayList<String>();
		preferredShift.add("Morning");
		preferredShift.add("Night");
		preferredShift.add("Between");
		model.addObject("preferredShifts", preferredShift);

		// add ushers preferred Shifts
		List<String> shirtSizes = new ArrayList<String>();
		shirtSizes.add("XS");
		shirtSizes.add("S");
		shirtSizes.add("M");
		shirtSizes.add("L");
		shirtSizes.add("XL");
		shirtSizes.add("XXL");
		shirtSizes.add("XXXL");
		model.addObject("shirtSizes", shirtSizes);

		// add ushers preferred Shifts
		List<String> pantsSizes = new ArrayList<String>();
		pantsSizes.add("XS");
		pantsSizes.add("S");
		pantsSizes.add("M");
		pantsSizes.add("L");
		pantsSizes.add("XL");
		pantsSizes.add("XXL");
		pantsSizes.add("XXXL");
		model.addObject("pantsSizes", pantsSizes);

		// add ushers preferred Shifts
		List<String> hairTypes = new ArrayList<String>();
		hairTypes.add("Normal");
		hairTypes.add("Good");
		hairTypes.add("Veiled");
		model.addObject("hairTypes", hairTypes);

		// add ushers preferred Shifts
		List<String> languages = new ArrayList<String>();
		languages.add("English");
		languages.add("French");
		languages.add("German");
		languages.add("None");
		model.addObject("languages", languages);

		// add ushers preferred Shifts
		List<String> universityDegrees = new ArrayList<String>();
		universityDegrees.add("Graduate");
		universityDegrees.add("Ungraduate");
		model.addObject("universityDegrees", universityDegrees);

		List<String> socialInsurance = new ArrayList<String>();
		socialInsurance.add("Yes");
		socialInsurance.add("No");
		model.addObject("socialInsurance", socialInsurance);

		List<String> socialInsuranceForm6 = new ArrayList<String>();
		socialInsuranceForm6.add("Yes");
		socialInsuranceForm6.add("No");
		model.addObject("socialInsuranceForm6", socialInsuranceForm6);

		List<String> rates = new ArrayList<String>();
		rates.add("Poor");
		rates.add("Good");
		rates.add("Very good");
		rates.add("Excellent");
		model.addObject("rates", rates);
	}

}
