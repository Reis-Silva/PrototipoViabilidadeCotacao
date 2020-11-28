package cotacao.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;

import cotacao.dao.gerentes.GerentesJPADAO;
import cotacao.entity.gerentes.Gerentes;

@ApplicationScoped
public class DataUtils extends Thread {

	boolean laco = true;
	boolean envioTempoEmail = true;
	int tempoEnvio;
	
	public String todayAsString() {
		Date today = new Date();

		return dateAsString(today);
	}

	public String dateAsString(Date data) {
		String formato = "MM-dd-yyyy";
		DateFormat df = new SimpleDateFormat(formato);
		String dataAsString = df.format(data);

		return dataAsString;
	}

	public void calendarioEnvioEmail() {
		new Thread() {

			@Override
			public void run() {
				while (laco = true) {
					try {

						Calendar daytime = Calendar.getInstance();
						Calendar hourtime = Calendar.getInstance();
						
						int segunda = daytime.get(Calendar.DAY_OF_MONTH) - daytime.get(Calendar.DAY_OF_WEEK) + 2;
						int sexta = daytime.get(Calendar.DAY_OF_MONTH) + daytime.get(Calendar.DAY_OF_WEEK) - 2;
						int today = hourtime.get(Calendar.DAY_OF_MONTH);
						int hora = hourtime.get(Calendar.HOUR_OF_DAY);
						int minuto = hourtime.get(Calendar.MINUTE);
						int segundo = hourtime.get(Calendar.SECOND);

						int[] listaTempo = { segunda, sexta, today, hora, minuto, segundo };
						
						contrucaoTempoEmail(listaTempo);
						Thread.sleep(2000); 

					} catch (InterruptedException e) {

					}
				}

			}
		}.start();
	}

	public void contrucaoTempoEmail(int[] listaTempo) {

		if (listaTempo[2] >= listaTempo[0] && listaTempo[2] <= listaTempo[1]) {
			
			String hAjuste = null;
			String mAjuste = null;
			String sAjuste = null;
			
			if(listaTempo[3] < 10) {
				String h = String.valueOf(listaTempo[3]);
				hAjuste = "0" + h;
			}else {
				hAjuste = String.valueOf(listaTempo[3]);
			}
			
			if(listaTempo[4] < 10) {
				String m = String.valueOf(listaTempo[4]);
				mAjuste = "0" + m;
			}else {
				mAjuste = String.valueOf(listaTempo[4]);
			}
			
			if(listaTempo[5] < 10) {
				String s = String.valueOf(listaTempo[5]);
				sAjuste = "0" + s;
			}else {
				sAjuste = String.valueOf(listaTempo[5]);
			}
			
			String conc = hAjuste + mAjuste + sAjuste;
			int conversaoHora = Integer.parseInt(conc);
			
			System.out.print("\nHora Atual:" + conversaoHora+"\n\nVerificando a hora de envio padrão (15:00:00)...\n");
			
			if ((conversaoHora) > 150003) {
				envioTempoEmail = true;
			}

			if ((conversaoHora >= 150000) && (conversaoHora  <= 150003) && envioTempoEmail == true) {
				
				try {
					System.out.print("\nEnvio efetuado!\n\n");
					GerentesJPADAO envioEmails = new GerentesJPADAO();
					 envioEmails.emailSearch(Gerentes.class);
					envioTempoEmail = false;
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		}

	}

	
}
