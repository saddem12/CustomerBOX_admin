package com.example.j2eeapp.services.impl;

import com.example.j2eeapp.dao.AgentDao;
import com.example.j2eeapp.domain.AgentEntity;
import com.example.j2eeapp.services.AgentService;
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

public class AgentServiceImpl implements AgentService{
    private AgentDao agentDao;

    public List<AgentEntity> findAll() {
        return agentDao.findAll();
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
    
    public boolean createAgent(AgentEntity agentEntity) {
    	
    	agentEntity.setPassword("customer");
    	
    	
    	agentEntity.setRegion(agentEntity.getAgence().getRegion());
    	
    	
        if(agentDao.checkAvailable(agentEntity.getUserName())) {
            agentDao.save(agentEntity);
            doSendMail("customer.box.webmaster@gmail.com", "cusbox12", agentEntity.getUserName(), "Confirmation", "<p style=\"color: red;\">"
            		+ "<h1 style=\"color:purple;\">Saddem from Customer BOX</h1> Mr/Mrs " +agentEntity.getFirstName()+" " +agentEntity.getLastName()+" , We have created your agent account with these Credentials: "
            		+ " <p style=\"color:blue;\" > username: "+agentEntity.getUserName()+"</p><p style=\"color:blue;\" >Password: azerty</p> <h4>For your account security please change your password once logging in for the first time!</h4> <br> Have a nice day!"
            				+ "</p>");
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("customerAddedMsg"), agentEntity.getUserName()), null);
    		getFacesContext().addMessage(null, message);
    		return true;
    		
        }else {
            FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("userExistsMsg"), agentEntity.getUserName()), null);
    		getFacesContext().addMessage(null, message);
    		return false;
        }

    }

    public void updateAgent(AgentEntity agentEntity) {
        agentDao.update(agentEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agentUpdatedMsg"), agentEntity.getUserName()), null);
		getFacesContext().addMessage(null, message);

    	

    }

    public void deleteAgent(AgentEntity agentEntity) {

        agentDao.delete(agentEntity);
        FacesMessage message = constructErrorMessage(String.format(getMessageBundle().getString("agentDeletedMsg"), agentEntity.getUserName()), null);
		getFacesContext().addMessage(null, message);

    }

    public boolean checkAvailable(AjaxBehaviorEvent event) {


        InputText inputText = (InputText) event.getSource();
        String value = (String) inputText.getValue();

        boolean available=agentDao.checkAvailable(value);

        if (!available) {
            FacesMessage message = constructErrorMessage(null, String.format(getMessageBundle().getString("agentExistsMsg"), value ));
            getFacesContext().addMessage(event.getComponent().getClientId(), message);
        } else {
            FacesMessage message = constructInfoMessage(null, String.format(getMessageBundle().getString("agentAvailableMsg"), value ));
            getFacesContext().addMessage(event.getComponent().getClientId(), message);
        }

        return available;


    }

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(AgentDao agentDao) {
        this.agentDao = agentDao;

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
