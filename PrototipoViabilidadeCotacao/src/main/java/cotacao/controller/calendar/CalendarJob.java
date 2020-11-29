package cotacao.controller.calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cotacao.dao.gerente.GerenteJPADAO;
import cotacao.entity.gerente.Gerente;


public class CalendarJob implements Job{
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		GerenteJPADAO envioEmails = new GerenteJPADAO();
		envioEmails.emailSearch(Gerente.class);
	}
}
