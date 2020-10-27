package br.com.cotacao.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {
	
	public <T> void javamail(List<T> mail) {
		
		CotacaoController cotacao = new CotacaoController();
	    Properties props = new Properties();
	    /** Parâmetros de conexão com servidor Gmail */
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");

	    Session session = Session.getDefaultInstance(props,
	      new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication()
	           {
	                 return new PasswordAuthentication("testeemaildesafio@gmail.com",
	                 "senha@123");
	           }
	      });

	    /** Ativa Debug para sessão */
	    session.setDebug(true);

	    try {

	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("testeemaildesafio@gmail.com"));
	      //Remetente
	      Address[] allEmails = null;
	     
	      for(int i=0; i <= mail.size()-1; i++) {
	    	  allEmails = InternetAddress.parse((mail.get(i)).toString());
	      }
	      
	      message.setRecipients(Message.RecipientType.TO, allEmails);
	      
	      message.setSubject("Tabela de Cotação de Moedas");//Assunto
	      
	      cotacao.buscarMoeda();
	      for(int x=0; x < cotacao.getMoedas().size()-1 ; x++) {
		      //message.setText("Tabelas de cotação de moedas");
	    	  message.setText("MoedaOrigem:\t" + 
		       cotacao.getMoedas().get(x).getCotacaoCompra());
	      }
	      

	      /**Método para enviar a mensagem criada*/
	      Transport.send(message);

	      System.out.println("Feito!!!");

	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }

}
