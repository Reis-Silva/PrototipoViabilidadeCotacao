package br.com.cotacao.controller;

import java.util.ArrayList;
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
	
	
	@SuppressWarnings("null")
	public <T> void javamail(List<T> mail) {
		
		String[] UnidadeMoedas = {"AUD","CAD","CHF","DKK","GBP","JPY","NOK","SEK","USD"};
		
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
	                 "testeemaildesafio@12");
	           }
	      });

	    /** Ativa Debug para sessão */
	    session.setDebug(true);
	    List<T> lista = new ArrayList<T>();
	    try {	    	
			
			   for(int i=0; i <= 8 ; i++) {
				   System.out.println("Teste: "+cotacao.moedaCotacaoAtualEmail(UnidadeMoedas[i]));
				   lista.add((T)cotacao.moedaCotacaoAtualEmail(UnidadeMoedas[i]));
			      }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	    try {
	    	
	    
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("testeemaildesafio@gmail.com"));
	      //Remetente
	      
	      Address[] allEmails = new Address[mail.size()];
	      
	      InternetAddress[] address = null;
	      
	      System.out.print("Email da Tabela: "+mail);
	     
	      
	      for(int i=0; i < mail.size(); i++) {
	    	  
	    	 allEmails = InternetAddress.parse(mail.get(i).toString());
		      System.out.print("\nteste3: "+allEmails.toString()+"\n");
		      
		      
		      message.setRecipients(Message.RecipientType.TO, allEmails);
		      
		      message.setSubject("Tabela diária - Cotação de Moedas");//Assunto
		      
		      message.setContent(lista.toString(),
	    			  "text/html; charset=utf-8");
		      
		      message.setText("Dados diários atualizados");
		      
		      /**Método para enviar a mensagem criada*/
		      Transport.send(message);

		      System.out.println("Feito!!!");
	    	 
	      }
	      
	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	  }

}
