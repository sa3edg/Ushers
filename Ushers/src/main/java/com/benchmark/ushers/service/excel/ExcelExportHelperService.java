package com.benchmark.ushers.service.excel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportHelperService {

	@Value("${excel.export.usher.sheetName}")
	public String ushersSheetName;
	
	@Value("${usher.usherCode}")
	public String usherCode;

	@Value("${usher.usherType}")
	public String usherType;

	@Value("${usher.usherCaliber}")
	public String usherCaliber;

	@Value("${usher.firstName}")
	public String firstName;

	@Value("${usher.middleName}")
	public String middleName;

	@Value("${usher.lastName}")
	public String lastName;

	@Value("${usher.maritalStatus}")
	public String maritalStatus;

	@Value("${usher.hasKids}")
	public String hasKids;

	@Value("${usher.numberOfKids}")
	public String numberOfKids;

	@Value("${usher.gender}")
	public String gender;

	@Value("${usher.birthDate}")
	public String birthDate;

	@Value("${usher.age}")
	public String age;

	@Value("${usher.address}")
	public String address;

	@Value("${usher.appartmentNumber}")
	public String appartmentNumber;

	@Value("${usher.street}")
	public String street;

	@Value("${usher.area}")
	public String area;

	@Value("${usher.governorate}")
	public String governorate;

	@Value("${usher.preferredLocation}")
	public String preferredLocation;

	@Value("${usher.preferredShift}")
	public String preferredShift;

	@Value("${usher.mobileNumber}")
	public String mobileNumber;

	@Value("${usher.landlineNumber}")
	public String landlineNumber;

	@Value("${usher.height}")
	public String height;

	@Value("${usher.weight}")
	public String weight;

	@Value("${usher.shirtSize}")
	public String shirtSize;

	@Value("${usher.pantsSize}")
	public String pantsSize;

	@Value("${usher.hairType}")
	public String hairType;

	@Value("${usher.languages}")
	public String languages;

	@Value("${usher.referredBy}")
	public String referredBy;

	@Value("${usher.university}")
	public String university;

	@Value("${usher.universityDegree}")
	public String universityDegree;

	@Value("${usher.graduationYear}")
	public String graduationYear;

	@Value("${usher.school}")
	public String school;

	@Value("${usher.facebookAccount}")
	public String facebookAccount;

	@Value("${usher.emailAddress}")
	public String emailAddress;

	@Value("${usher.socialInsurance}")
	public String socialInsurance;

	@Value("${usher.socialInsuranceNumber}")
	public String socialInsuranceNumber;

	@Value("${usher.socialInsuranceDate}")
	public String socialInsuranceDate;

	@Value("${usher.socialInsuranceForm6}")
	public String socialInsuranceForm6;

	@Value("${usher.socialInsuranceExitDate}")
	public String socialInsuranceExitDate;

	@Value("${usher.nationalIdNumber}")
	public String nationalIdNumber;

	@Value("${usher.additionalInformation}")
	public String additionalInformation;

	@Value("${usher.rate}")
	public String rate;
	private static volatile ExcelExportHelperService instance;
	private ExcelExportHelperService(){
	}
	
	public static ExcelExportHelperService getInstance() {
		ExcelExportHelperService result = instance;
	    if (result == null) { // First check (no locking)
	        synchronized(ExcelExportHelperService.class) {
	            result = instance;
	            if (result == null) // Second check (with locking)
	            	instance = result = new ExcelExportHelperService();
	        }
	    }
	    return result;
	}
}
