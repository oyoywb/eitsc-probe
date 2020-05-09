package com.alimu.probe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alimu.probe.dao.DailyStatisticsDao;
import com.alimu.probe.remote.EquipRemoteService;

@Service
@EnableScheduling
public class TimingTaskService {
	@Autowired
	private DailyStatisticsDao dailyStatisticsDao;
	@Autowired
	private EquipRemoteService equipRemoteService;
	@Scheduled(cron = "0 0 23 * * ?")
    public void TimingTask(){
        System.out.println("开始执行数据库备份。。。。。。。。。。。。。。。。。。");
        Long schoolCode=100L;
        int totalCount=equipRemoteService.getPcCount(schoolCode);
        dailyStatisticsDao.dailyPcAnalysis();
        dailyStatisticsDao.dailyPcStateAnalysis(totalCount);
        dailyStatisticsDao.dailySoftAnalysis();
        dailyStatisticsDao.dailyWebAnalysis();
        
    } 
//	@Scheduled(cron="0/5 * *  * * ? ")
//	public void test() {
//		System.out.println("oywb");
//	}
}
