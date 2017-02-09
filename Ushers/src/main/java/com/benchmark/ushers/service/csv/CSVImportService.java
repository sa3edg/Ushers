package com.benchmark.ushers.service.csv;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.benchmark.ushers.common.util.DateUtil;
import com.benchmark.ushers.dao.impl.UsherDaoImpl;
import com.benchmark.ushers.dao.model.Usher;
import com.benchmark.ushers.service.DaoService;

@Service
public class CSVImportService{
	private static final Logger logger = LoggerFactory.getLogger(CSVImportService.class);
	
	@Value("${csv.import.seprator}")
    private char seprator;
	
	@Autowired  
	private UsherDaoImpl usherDao;
	
	private static volatile CSVImportService instance;
	private CSVImportService(){
	}
	
	public static CSVImportService getInstance() {
		CSVImportService result = instance;
	    if (result == null) { // First check (no locking)
	        synchronized(CSVImportService.class) {
	            result = instance;
	            if (result == null) // Second check (with locking)
	            	instance = result = new CSVImportService();
	        }
	    }
	    return result;
	}
	
	public void importUsherCSVFile(InputStream input) throws Exception{
		List<Usher> ushers = readUsherCSVFile(input);
		usherDao.save(ushers);
	}
	public List<Usher> readUsherCSVFile(InputStream input) throws Exception{
		CSVReader csvReader = null;
		List<Usher> ushers = new ArrayList<Usher>();
        try {
//        	ColumnPositionMappingStrategy<Usher> mapper = new ColumnPositionMappingStrategy<Usher>();
//        	mapper.setType(Usher.class);
//        	String[] columns = new String[] {"usherCode", "usherType", "usherCaliber", "firstName"}; // the fields to bind do in your JavaBean
//        	mapper.setColumnMapping(columns);
//        	 
//        	CsvToBean<Usher> csv = new CsvToBean<Usher>();
        	
        	logger.debug("CSV data separator"+ this.seprator);
            csvReader = new CSVReader(new InputStreamReader(input), this.seprator);
//            List<Usher> list = csv.parse(mapper, csvReader);
            List<String[]> records = csvReader.readAll();
            Iterator<String[]> iterator = records.iterator();
            //skip header row
            iterator.next();
            while(iterator.hasNext()){
                String[] record = iterator.next();
                Usher usher = new Usher();
                usher.setUsherCode(record[0]);
    			usher.setUsherType(record[1]);
    			usher.setUsherCaliber(record[2]);
    			usher.setFirstName(record[3]);
    			usher.setMiddleName(record[4]);
    			usher.setLastName(record[5]);
    			usher.setMaritalStatus(record[6]);
    			usher.setHasKids(Boolean.valueOf(record[7]));
    			usher.setNumberOfKids(Integer.valueOf(record[8]));
    			usher.setGender(record[9]);
    			usher.setBirthDate(DateUtil.getSimpleDateFromString(record[10]));
    			usher.setAge(Integer.valueOf(record[11]));
    			usher.setAddress(record[12]);
    			usher.setAppartmentNumber(record[13]);
    			usher.setStreet(record[14]);
    			usher.setArea(record[15]);
    			usher.setGovernorate(record[16]);
    			usher.setPreferredLocation(record[17]);
    			usher.setPreferredShift(record[18]);
    			usher.setMobileNumber(record[19]);
    			usher.setLandlineNumber(record[20]);
    			usher.setHeight(record[21]);
    			usher.setWeight(record[22]);
    			usher.setShirtSize(record[23]);
    			usher.setPantsSize(record[24]);
    			usher.setHairType(record[25]);
    			usher.setLanguages(record[26]);
    			usher.setReferredBy(record[27]);
    			usher.setUniversity(record[28]);
    			usher.setUniversityDegree(record[29]);
    			usher.setGraduationYear(record[30]);
    			usher.setSchool(record[31]);
    			usher.setFacebookAccount(record[32]);
    			usher.setEmailAddress(record[33]);
    			usher.setSocialInsurance(Boolean.valueOf(record[34]));
    			usher.setSocialInsuranceNumber(record[35]);
    			usher.setSocialInsuranceDate(DateUtil.getSimpleDateFromString(record[36]));
    			usher.setSocialInsuranceForm6(Boolean.valueOf(record[37]));
    			usher.setSocialInsuranceExitDate(DateUtil.getSimpleDateFromString(record[38]));
    			usher.setNationalIdNumber(record[39]);
    			usher.setAdditionalInformation(record[40]);
    			usher.setRate(record[41]);
                ushers.add(usher);
            }
 
        } catch (Exception e) {
        	logger.debug("Exception in CSV import: "+e.getMessage());
        }finally{
        	csvReader.close();
        }
        logger.debug("Ushers count in CSV file is: "+ushers.size());
        return ushers;
	}

}
