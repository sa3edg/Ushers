package com.benchmark.ushers.dao.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Persistable;
import org.springframework.web.multipart.MultipartFile;

public class Usher implements Persistable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ACTIVATION = "Activiation";
	public static final String INSTORE = "Instore";
	private transient boolean persisted;

	private String usherCode = "";
	private String usherType = "";
	private String usherCaliber = "";
	private String firstName = "";
	private String middleName = "";
	private String lastName= "";
	private String maritalStatus = "";
	private boolean hasKids;
	private Integer numberOfKids;
	private String gender = "";
	private Date birthDate;
	private Integer age;
	private String address = "";
	private String appartmentNumber = "";
	private String street = "";
	private String area = "";
	private String governorate = "";
	private String preferredLocation = "";
	private String preferredShift = "";
	private String mobileNumber = "";
	private String landlineNumber = "";
	private String height = "";
	private String weight = "";
	private String shirtSize = "";
	private String pantsSize = "";
	private String hairType = "";
	private String languages = "";
	private String referredBy = "";
	private String university = "";
	private String universityDegree = "";
	private String graduationYear = "";
	private String school = "";
	private String facebookAccount = "";
	private String emailAddress = "";
	private boolean socialInsurance;
	private String socialInsuranceNumber = "";
	private Date socialInsuranceDate;
	private boolean socialInsuranceForm6;
	private Date socialInsuranceExitDate;
	private String nationalIdNumber = "";
	private String additionalInformation = "";
	private String rate = "";
	private byte[] photo1;
	private byte[] photo2;
	private byte[] photo3;
	private byte[] photo4;
	
	private List<MultipartFile> files;
	//private String hasKidsString = "";

	@Override
	public String getId() {
		return getUsherCode();
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	public Usher withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public String getPantsSize() {
		return pantsSize;
	}

	public void setPantsSize(String pantsSize) {
		this.pantsSize = pantsSize;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isSocialInsurance() {
		return socialInsurance;
	}

	public void setSocialInsurance(boolean socialInsurance) {
		this.socialInsurance = socialInsurance;
	}

	public String getSocialInsuranceNumber() {
		return socialInsuranceNumber;
	}

	public void setSocialInsuranceNumber(String socialInsuranceNumber) {
		this.socialInsuranceNumber = socialInsuranceNumber;
	}

	public Date getSocialInsuranceDate() {
		return socialInsuranceDate;
	}

	public void setSocialInsuranceDate(Date socialInsuranceDate) {
		this.socialInsuranceDate = socialInsuranceDate;
	}

	public boolean isSocialInsuranceForm6() {
		return socialInsuranceForm6;
	}

	public void setSocialInsuranceForm6(boolean socialInsuranceForm6) {
		this.socialInsuranceForm6 = socialInsuranceForm6;
	}

	public Date getSocialInsuranceExitDate() {
		return socialInsuranceExitDate;
	}

	public void setSocialInsuranceExitDate(Date socialInsuranceExitDate) {
		this.socialInsuranceExitDate = socialInsuranceExitDate;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getUsherCode() {
		return usherCode;
	}

	public void setUsherCode(String usherCode) {
		this.usherCode = usherCode;
	}

	public String getUsherType() {
		return usherType;
	}

	public void setUsherType(String usherType) {
		this.usherType = usherType;
	}

	public String getUsherCaliber() {
		return usherCaliber;
	}

	public void setUsherCaliber(String usherCaliber) {
		this.usherCaliber = usherCaliber;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean isHasKids() {
		return hasKids;
	}

	public void setHasKids(boolean hasKids) {
		this.hasKids = hasKids;
	}

	public Integer getNumberOfKids() {
		return numberOfKids;
	}

	public void setNumberOfKids(Integer numberOfKids) {
		this.numberOfKids = numberOfKids;
	}

	public String getAppartmentNumber() {
		return appartmentNumber;
	}

	public void setAppartmentNumber(String appartmentNumber) {
		this.appartmentNumber = appartmentNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getGovernorate() {
		return governorate;
	}

	public void setGovernorate(String governorate) {
		this.governorate = governorate;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public String getPreferredShift() {
		return preferredShift;
	}

	public void setPreferredShift(String preferredShift) {
		this.preferredShift = preferredShift;
	}

	public String getLandlineNumber() {
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}


	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getUniversityDegree() {
		return universityDegree;
	}

	public void setUniversityDegree(String universityDegree) {
		this.universityDegree = universityDegree;
	}

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public byte[] getPhoto3() {
		return photo3;
	}

	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}

	public byte[] getPhoto4() {
		return photo4;
	}

	public void setPhoto4(byte[] photo4) {
		this.photo4 = photo4;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
