package com.example.j2eeapp.services.impl;

import com.example.j2eeapp.dao.CustomerDao;
import com.example.j2eeapp.domain.CustomerEntity;
import com.example.j2eeapp.services.CustomerService;
import org.primefaces.component.inputtext.InputText;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;


    public List<CustomerEntity> findAll() {
        return customerDao.findAll();
    }


    public void doSendMail(final String username, final String password, String to, String subject, String email_body) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(email_body);
			message.setContent(email_body, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("message sent");
			
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
    public boolean createCustomer(CustomerEntity customerEntity) {
        customerEntity.setPassword("customer");
        customerEntity.setRegion(customerEntity.getAgence().getRegion());
        if(customerDao.checkAvailable(customerEntity.getUserName())) {
            customerDao.save(customerEntity);
            doSendMail("customer.box.webmaster@gmail.com", "cusbox12", customerEntity.getUserName(), "Confirmation", "<p style=\"color: red;\">"
            		+ "<h1 style=\"color:purple;\">Saddem from Customer BOX</h1>Mr/Mrs " +customerEntity.getFirstName()+" " +customerEntity.getLastName()+" , We have created your customer account with these Credentials: "
            		+ " <p style=\"color:blue;\" > username: "+customerEntity.getUserName()+"</p><p style=\"color:blue;\" >Password: azerty</p> <h4>For your account security please change your password once logging in for the first time!</h4> <br> Have a nice day!"
            				+ "</p>");
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("customerAddedMsg"), customerEntity.getUserName()), null);
    		getFacesContext().addMessage(null, message);
    		return true;
    		
        }else {
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("userExistsMsg"), customerEntity.getUserName()), null);
    		getFacesContext().addMessage(null, message);
    		return false;
        }

    }

    public void updateCustomer(CustomerEntity customerEntity) {

        customerDao.update(customerEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("customerUpdatedMsg"), customerEntity.getUserName()), null);
		getFacesContext().addMessage(null, message);

    }

    public void deleteCustomer(CustomerEntity customerEntity) {

        customerDao.delete(customerEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("customerDeletedMsg"), customerEntity.getUserName()), null);
		getFacesContext().addMessage(null, message);

    }



    public boolean checkAvailable(AjaxBehaviorEvent event) {


        InputText inputText = (InputText) event.getSource();
        String value = (String) inputText.getValue();

        boolean available=customerDao.checkAvailable(value);

            if (!available) {
                FacesMessage message = constructErrorMessage(null, String.format(getMessageBundle().getString("customerExistsMsg"), value ));
                getFacesContext().addMessage(event.getComponent().getClientId(), message);
            } else {
                FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("customerAvailableMsg"), value ));
                getFacesContext().addMessage(event.getComponent().getClientId(), message);
            }

            return available;


    }


    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    protected FacesMessage constructErrorMessage(String message, String detail){
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail);
    }

    protected FacesMessage constructInfoMessage(String message, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
    }

    protected FacesMessage constructFatalMessage(String message, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail);
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("message-labels");
    }
}
