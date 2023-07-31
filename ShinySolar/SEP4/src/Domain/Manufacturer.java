package Domain;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Manufacturer
{
	private String name;
	private String address;
	private String phone;
	private String email;
	private String website;
	private SimpleIntegerProperty idInfo;
	private SimpleStringProperty nameInfo;
	private  SimpleStringProperty addressInfo;
	private SimpleStringProperty phoneInfo;
	private SimpleStringProperty emailInfo;
	private SimpleStringProperty websiteInfo;
	public Manufacturer(String name, String address, String phone, String email, String website) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.website = website;
	}
	public Manufacturer(String id, String name, String address, String phone, String email, String website) {
		this.idInfo = new SimpleIntegerProperty(Integer.parseInt(id));
		this.nameInfo = new SimpleStringProperty(name);
		this.addressInfo = new SimpleStringProperty(address);
		this.phoneInfo = new SimpleStringProperty(phone);
		this.emailInfo = new SimpleStringProperty(email);
		this.websiteInfo = new SimpleStringProperty(website);
	}
	public int getIdInfo() {
		return idInfo.get();
	}
	public void setIdInfo(String id) {
		this.idInfo = new SimpleIntegerProperty(Integer.parseInt(id));
	}
	public String getNameInfo() {
		return nameInfo.get();
	}
	public void setNameInfo(String nameInfo) {
		this.nameInfo = new SimpleStringProperty(nameInfo);
	}
	public String getAddressInfo() {
		return addressInfo.get();
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = new SimpleStringProperty(addressInfo);
	}
	public String getPhoneInfo() {
		return phoneInfo.get();
	}
	public void setPhoneInfo(String phoneInfo) {
		this.phoneInfo = new SimpleStringProperty(phoneInfo);
	}
	public String getEmailInfo() {
		return emailInfo.get();
	}
	public void setEmailInfo(String emailInfo) {
		this.emailInfo = new SimpleStringProperty(emailInfo);
	}
	public String getWebsiteInfo() {
		return websiteInfo.get();
	}
	public void setWebsiteInfo(String websiteInfo) {
		this.websiteInfo = new SimpleStringProperty(websiteInfo)	;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void  setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}