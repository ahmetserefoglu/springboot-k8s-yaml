package com.javatechie.k8s.job;

import com.javatechie.k8s.service.NotificationService;
import com.javatechie.k8s.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class ReportGenerationScheduler {
    @Autowired
    private ReportService reportService;
    @Autowired
    private NotificationService notificationService;


    public void generateReportAndSendEmail() {
        log.info("ReportGenerationScheduler::generateReportAndSendEmail Execution started on {} ", new Date());
        try {
            //generate report
            byte[] reportContent = reportService.generateReport();
            //send email
            notificationService.sendDailyReports(reportContent);
            log.info("ReportGenerationScheduler::generateReportAndSendEmail Execution ended on {} ", new Date());

        } catch (IOException | MessagingException e) {
            log.error("ReportGenerationScheduler::generateReportAndSendEmail Exception occurred {} ", e.getMessage());
            e.printStackTrace();
        }
    }
}