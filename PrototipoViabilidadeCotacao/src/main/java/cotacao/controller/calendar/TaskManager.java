package cotacao.controller.calendar;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@ApplicationScoped
@ManagedBean
public class TaskManager {
	
	TimeOfDay inicio = TimeOfDay.hourMinuteAndSecondOfDay(15, 00, 00);
	TimeOfDay fim = TimeOfDay.hourMinuteAndSecondOfDay(15, 00, 03);
	
	public void cron() {

		try {
			
			JobDetail job = JobBuilder.newJob(CalendarJob.class).withIdentity("jobOne", "groupOne").build();
			
			DailyTimeIntervalScheduleBuilder scheduleWeek = 
					DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
									.startingDailyAt(inicio)
									.endingDailyAt(fim)
									.withIntervalInSeconds(2)
									.onMondayThroughFriday();
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("triggerOne", "groupOne")
					.withSchedule(scheduleWeek)
					.build();
	
			Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
			sched.scheduleJob(job, trigger);
			sched.start();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
