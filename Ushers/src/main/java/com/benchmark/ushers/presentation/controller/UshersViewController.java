package com.benchmark.ushers.presentation.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/addUsher", method = RequestMethod.POST)
	public String addUsher(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
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

		// add ushers Caliber
		List<String> maritalStatus = new ArrayList<String>();
		maritalStatus.add("Married");
		maritalStatus.add("Single");
		maritalStatus.add("Widow");
		maritalStatus.add("Divorced");
		model.addObject("maritalStatus", maritalStatus);
	}

}
