package com.huben.service;

import com.huben.dao.RecordDAO;
import com.huben.entity.Record;
import com.huben.utils.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author koishi
 */
public class ReportService {
    public int getDaySpend(Date d, List<Record> monthRawData){
        int daySpend=0;
        for (Record record : monthRawData) {
            if (record.getDate().equals(d)){
                daySpend+=record.getSpend();
            }
        }
        return daySpend;
    }

    public List<Record> listThisMonthRecords(){
        RecordDAO dao = new RecordDAO();
        List<Record> monthRawData = dao.listThisMonth();
        List<Record> result=new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay=DateUtil.thisMonthTotalDay();
        Calendar c= Calendar.getInstance();

        for (int i = 0; i < monthTotalDay; i++) {
            Record r=new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE,i);
            Date eachDayOfThisMonth=c.getTime();
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.setSpend(daySpend);
            result.add(r);
        }
        return result;
    }
}
