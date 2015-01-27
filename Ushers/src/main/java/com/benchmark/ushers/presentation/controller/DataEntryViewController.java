package com.benchmark.ushers.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.ushers.dao.model.Area;
import com.benchmark.ushers.dao.model.Client;
import com.benchmark.ushers.dao.model.Governorate;
import com.benchmark.ushers.dao.model.PreferredLocation;
import com.benchmark.ushers.dao.model.Product;
import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.ProjectLocation;
import com.benchmark.ushers.dao.model.ProjectType;
import com.benchmark.ushers.dao.model.UserRole;

@Controller
public class DataEntryViewController  extends AbstractViewController{

	@RequestMapping(value = "/governorates", method = RequestMethod.GET)
	public ModelAndView getGovernorates(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
			List<Governorate> governorates = daoService.getGovernorateDaoImpl().findAll();
			model.addObject("governorates", governorates);
			model.setViewName("dataEnttry/governorates");
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
			List<Area> areas = daoService.getAreaDaoImpl().findAll();
			model.addObject("areas", areas);
			model.setViewName("dataEnttry/areas");
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
	
	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ModelAndView getClients(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
			List<Client> areas = daoService.getClientDaoImpl().findAll();
			model.addObject("clients", areas);
			model.setViewName("dataEnttry/clients");
		
		return model;

	}
	
	@RequestMapping(value = "/addClientForm**", method = RequestMethod.GET)
	public ModelAndView addClientForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("client", new Client());
		model.setViewName("dataEnttry/addClient");
		return model;
	}
	
	@RequestMapping(value ="/addClient", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Client client) {
		daoService.getClientDaoImpl().save(client);
		return "redirect:/clients";
	}
	
	@RequestMapping(value = "/editClient**", method = RequestMethod.GET)
	public ModelAndView editClient(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Client client = daoService.getClientDaoImpl().findOne(new Integer(id));
		model.addObject("client", client);
		model.setViewName("dataEnttry/addClient");
		return model;
	}
	
	@RequestMapping("/deleteClient")
	public ModelAndView deleteClient(@RequestParam("id") String id) {
		daoService.getClientDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Client> areas = daoService.getClientDaoImpl().findAll();
		model.addObject("clients", areas);
		model.setViewName("dataEnttry/clients");
		return model;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView getProducts(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
			List<Product> areas = daoService.getProductDaoImpl().findAll();
			model.addObject("products", areas);
			model.setViewName("dataEnttry/products");
		return model;

	}
	
	@RequestMapping(value = "/addProductForm**", method = RequestMethod.GET)
	public ModelAndView addProductForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("product", new Product());
		model.setViewName("dataEnttry/addProduct");
		return model;
	}
	
	@RequestMapping(value ="/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product) {
		daoService.getProductDaoImpl().save(product);
		return "redirect:/products";
	}
	
	@RequestMapping(value = "/editProduct**", method = RequestMethod.GET)
	public ModelAndView editProduct(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Product client = daoService.getProductDaoImpl().findOne(new Integer(id));
		model.addObject("product", client);
		model.setViewName("dataEnttry/addProduct");
		return model;
	}
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("id") String id) {
		daoService.getProductDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<Product> areas = daoService.getProductDaoImpl().findAll();
		model.addObject("products", areas);
		model.setViewName("dataEnttry/products");
		return model;
	}
	
	@RequestMapping(value = "/projectTypes", method = RequestMethod.GET)
	public ModelAndView getPrpjectTypes(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
			List<ProjectType> areas = daoService.getProjectTypeDaoImpl().findAll();
			model.addObject("projectTypes", areas);
			model.setViewName("dataEnttry/projectTypes");
		return model;

	}
	
	@RequestMapping(value = "/addProjectTypeForm**", method = RequestMethod.GET)
	public ModelAndView addProjectTypeForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("projectType", new ProjectType());
		model.setViewName("dataEnttry/addProjectType");
		return model;
	}
	
	@RequestMapping(value ="/addProjectType", method = RequestMethod.POST)
	public String addProjectType(@ModelAttribute("projectType") ProjectType projectType) {
		daoService.getProjectTypeDaoImpl().save(projectType);
		return "redirect:/projectTypes";
	}
	
	@RequestMapping(value = "/editProjectType**", method = RequestMethod.GET)
	public ModelAndView editProjectType(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		ProjectType client = daoService.getProjectTypeDaoImpl().findOne(new Integer(id));
		model.addObject("projectType", client);
		model.setViewName("dataEnttry/addProduct");
		return model;
	}
	
	@RequestMapping("/deleteProjectType")
	public ModelAndView deleteProjectType(@RequestParam("id") String id) {
		daoService.getProjectTypeDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<ProjectType> areas = daoService.getProjectTypeDaoImpl().findAll();
		model.addObject("projectTypes", areas);
		model.setViewName("dataEnttry/projectTypes");
		return model;
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public ModelAndView getPrpjects(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
			List<Project> areas = daoService.getProjectDaoImpl().findAll();
			model.addObject("projects", areas);
			model.setViewName("dataEnttry/projects");
		return model;

	}
	
	@RequestMapping(value = "/addProjectForm**", method = RequestMethod.GET)
	public ModelAndView addProjectForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("project", new Project());
		model.setViewName("dataEnttry/addProject");
		initModelList(model);
		return model;
	}
	
	@RequestMapping(value ="/addProject", method = RequestMethod.POST)
	public String addProject(@ModelAttribute("project") Project project) {
		daoService.storeProject(project);
		return "redirect:/projects";
	}
	
	@RequestMapping(value = "/editProject**", method = RequestMethod.GET)
	public ModelAndView editProject(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Project client = daoService.getProjectDaoImpl().findOne(id);
		model.addObject("project", client);
		model.setViewName("dataEnttry/addProject");
		return model;
	}
	
	@RequestMapping("/deleteProject")
	public ModelAndView deleteProject(@RequestParam("id") String id) {
		daoService.getProjectDaoImpl().delete(id);
		ModelAndView model = new ModelAndView();
		List<Project> areas = daoService.getProjectDaoImpl().findAll();
		model.addObject("projects", areas);
		model.setViewName("dataEnttry/projects");
		return model;
	}
	
	private void initModelList(ModelAndView model) {
		
		// add project clients
		List<Client> clients = daoService.getClientDaoImpl().findAll();
		model.addObject("clients", clients);

		// add project products
		List<Product> products = daoService.getProductDaoImpl()
				.findAll();
		model.addObject("products", products);

		// add project types
		List<ProjectType> projectTypes = daoService
				.getProjectTypeDaoImpl().findAll();
		model.addObject("projectTypes", projectTypes);
	}
	
	@RequestMapping(value = "/projectLocations", method = RequestMethod.GET)
	public ModelAndView getProjectLocations(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		List<ProjectLocation> ProjectLocations = daoService
				.getProjectLocationDaoImpl().findAll();
		model.addObject("ProjectLocations", ProjectLocations);
		model.setViewName("dataEnttry/ProjectLocations");
		return model;

	}

	@RequestMapping(value = "/addProjectLocationForm**", method = RequestMethod.GET)
	public ModelAndView addProjectLocationForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("ProjectLocation", new ProjectLocation());
		model.setViewName("dataEnttry/addProjectLocation");
		initProjectLocation(model);
		return model;
	}

	@RequestMapping(value = "/addProjectLocation", method = RequestMethod.POST)
	public String addProjectLocation(
			@ModelAttribute("ProjectLocation") ProjectLocation ProjectLocation) {
		daoService.getProjectLocationDaoImpl().save(ProjectLocation);
		return "redirect:/projectLocations";
	}

	@RequestMapping(value = "/editProjectLocation**", method = RequestMethod.GET)
	public ModelAndView editProjectLocation(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		ProjectLocation ProjectLocation = daoService
				.getProjectLocationDaoImpl().findOne(new Integer(id));
		model.addObject("ProjectLocation", ProjectLocation);
		model.setViewName("dataEnttry/addProjectLocation");
		return model;
	}

	@RequestMapping("/deleteProjectLocation")
	public ModelAndView deleteProjectLocation(@RequestParam("id") String id) {
		daoService.getProjectLocationDaoImpl().delete(new Integer(id));
		ModelAndView model = new ModelAndView();
		List<ProjectLocation> ProjectLocations = daoService
				.getProjectLocationDaoImpl().findAll();
		model.addObject("ProjectLocations", ProjectLocations);
		model.setViewName("dataEnttry/ProjectLocations");
		return model;
	}

	private void initProjectLocation(ModelAndView model) {
		// add ushers types
		List<String> types = new ArrayList<String>();
		types.add("Smalls stores A");
		types.add("Small stores B");
		types.add("Hypermarket A");
		types.add("Hypermarket B");
		model.addObject("types", types);
	}
}
