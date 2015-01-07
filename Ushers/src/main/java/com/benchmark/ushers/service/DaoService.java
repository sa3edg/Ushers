package com.benchmark.ushers.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benchmark.ushers.common.util.UsherUtil;
import com.benchmark.ushers.dao.impl.AreaDaoImpl;
import com.benchmark.ushers.dao.impl.ClientsDaoImpl;
import com.benchmark.ushers.dao.impl.GovernorateDaoImpl;
import com.benchmark.ushers.dao.impl.PreferredLocationDaoImpl;
import com.benchmark.ushers.dao.impl.ProductsDaoImpl;
import com.benchmark.ushers.dao.impl.ProjectDaoImpl;
import com.benchmark.ushers.dao.impl.ProjectTypeDaoImpl;
import com.benchmark.ushers.dao.impl.UserDaoImpl;
import com.benchmark.ushers.dao.impl.UserRoleDaoImpl;
import com.benchmark.ushers.dao.impl.UsherDaoImpl;
import com.benchmark.ushers.dao.model.Project;
import com.benchmark.ushers.dao.model.User;
import com.benchmark.ushers.dao.model.UserRole;
import com.benchmark.ushers.dao.model.Usher;

@Service
public class DaoService {
	
	@Autowired  
	private ProjectDaoImpl projectDao;
	
	@Autowired  
	private UsherDaoImpl usherDao;
	
	@Autowired  
	private GovernorateDaoImpl governorateDaoImpl;
	
	@Autowired  
	private AreaDaoImpl areaDaoImpl;
	
	@Autowired  
	private PreferredLocationDaoImpl preferredLocationDaoImpl;
	
	@Autowired  
	private UserRoleDaoImpl userRoleDao;
	
	@Autowired  
	private UserDaoImpl userDao;
	
	@Autowired  
	private ClientsDaoImpl clientDao;
	
	@Autowired  
	private ProductsDaoImpl productDao;
	
	@Autowired  
	private ProjectTypeDaoImpl projectTypeDao;
	
	private static volatile DaoService instance;
	
	private DaoService(){
	}
	
	public static DaoService getInstance() {
		DaoService result = instance;
	    if (result == null) { // First check (no locking)
	        synchronized(DaoService.class) {
	            result = instance;
	            if (result == null) // Second check (with locking)
	            	instance = result = new DaoService();
	        }
	    }
	    return result;
	}
	public List<User> getUsers(){
		return userDao.getUsers();
	}
	public List<UserRole> getNotAdminUsers(){
		return userRoleDao.getNotAdminUsers();
	}
	public void addUser(User user){
		user.setEnabled(true);
		userDao.save(user);
		UserRole userRole = new UserRole();
		userRole.setUserName(user.getUserName());
		userRole.setRole(user.getRole());
		userRoleDao.insert(userRole);
	}
	
	public void deleteUser(String id){
		userRoleDao.delete(id);
		userDao.delete(id);
	}
	
	public GovernorateDaoImpl getGovernorateDaoImpl(){
		return governorateDaoImpl;
	}
	
	public AreaDaoImpl getAreaDaoImpl(){
		return areaDaoImpl;
	}
	
	public PreferredLocationDaoImpl getPreferredLocationDaoImpl(){
		return preferredLocationDaoImpl;
	}
	
	public UsherDaoImpl getUsherDaoImpl(){
		return usherDao;
	}
	
	public ClientsDaoImpl getClientDaoImpl(){
		return clientDao;
	}
	
	public ProductsDaoImpl getProductDaoImpl(){
		return productDao;
	}
	
	public ProjectTypeDaoImpl getProjectTypeDaoImpl(){
		return projectTypeDao;
	}
	
	public ProjectDaoImpl getProjectDaoImpl(){
		return projectDao;
	}
	public void storeProject(Project project){
		if(StringUtils.isEmpty(project.getProjectCode())){
			String lastProjectCode = projectDao.getLastProjectrCode();
			project.setProjectCode(UsherUtil.getNextProjectCode(lastProjectCode));
			projectDao.save(project);
		}else{
			projectDao.updateProject(project);
		}
		
	}
	
	public void storeUsher(Usher usher){
		if(StringUtils.isEmpty(usher.getUsherCode())){
			String lastUsherCode = usherDao.getLastUsherCode();
			usher.setUsherCode(UsherUtil.getNextUsherCode(usher.getUsherType(), lastUsherCode));
			usherDao.save(usher);
		}else{
			usherDao.updateUsher(usher);
		}
		
	}
//	public List<Project> getAllProjects(){
//		return projectDao.findAll();
//	}
//	public void updateProject(Project project){
//		projectDao.save(project);
//	}
//	public void deleteProjectById(Integer id){
//		projectDao.delete(id);
//	}
//	public void deleteProject(Project project){
//		projectDao.delete(project);
//	}
//	public void deleteAllProjects(){
//		projectDao.deleteAll();
//	}
//	

//	public List<Usher> getAllUshers(){
//		return usherDao.findAll();
//	}
//	public void updateUsher(Usher usher){
//		usherDao.save(usher);
//	}
//	public void deleteUsherById(String id){
//		usherDao.delete(id);
//	}
//	public void deleteUsher(Usher usher){
//		usherDao.delete(usher);
//	}
//	public void deleteAllUshers(){
//		usherDao.deleteAll();
//	}
	

}
