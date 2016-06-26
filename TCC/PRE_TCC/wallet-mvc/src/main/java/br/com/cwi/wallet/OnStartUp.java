/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet;

import br.com.cwi.wallet.infraestrutura.webservice.JobBancoCotacao;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author victo
 */
@Component
public class OnStartUp implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     * @param event
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        JobDetail job = JobBuilder.newJob(JobBancoCotacao.class)
                .withIdentity("VerificaBanco", "group1").build();

        Trigger trigger = newTrigger()
                .withIdentity("1VezAoDia", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();

        Scheduler scheduler;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();scheduler.scheduleJob(job, trigger);
            
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }        
    }
}
