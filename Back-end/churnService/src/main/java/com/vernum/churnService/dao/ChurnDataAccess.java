package com.vernum.churnService.dao;

import com.vernum.churnService.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("postgres")
public class ChurnDataAccess {

    private final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    private final int THIS_MONTH = calendar.get(Calendar.MONTH);
    private final int THIS_WEEK = calendar.get(Calendar.WEEK_OF_YEAR);
    private final double TODAY = calendar.get(Calendar.DAY_OF_MONTH);
    private final int THIS_YEAR = 2016;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChurnDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseModel customerGenderProfile(String city) {

        String sql = "SELECT gender, COUNT(*) as members " +
                "FROM members " +
                ((city != null) ? "WHERE city = ? " : " ") +
                "GROUP BY gender";
        if(city != null)
            return new ResponseModel(jdbcTemplate.queryForList(sql, Integer.parseInt(city)));

        return new ResponseModel(jdbcTemplate.queryForList(sql));
    }

    public ResponseModel transactions(String year) {
        String sql;

        if(year == null) {
            sql = "SELECT date_part('year', transaction_date) as t_date, COUNT(*) as transactions " +
                    "FROM transactions " +
                    "GROUP BY date_part('year', transaction_date) " +
                    "ORDER BY date_part('year', transaction_date)";
            return new ResponseModel(jdbcTemplate.queryForList(sql));
        } else {
            sql = "SELECT date_part('month',transaction_date) as t_date, COUNT(*) as transactions " +
                    "FROM transactions " +
                    "WHERE date_part('year',transaction_date) = ? " +
                    "GROUP BY date_part('month',transaction_date) " +
                    "ORDER BY date_part('month',transaction_date) ASC ";
            return new ResponseModel(jdbcTemplate.queryForList(sql, Double.parseDouble(year)));
        }

    }

    public ResponseModel recentTransactions(String interval, String detailed) {
        String sql;
        List<Map<String,Object>> result = new ArrayList<>();
        switch(interval) {
            case "today":
                if(detailed.equals("0")) {
                    sql = "SELECT COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('doy', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY DATE_PART('doy', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.TODAY, this.THIS_YEAR);

                } else if(detailed.equals("1")){
                    sql = "SELECT payment_method_id as payment_method, COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('doy', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY payment_method_id,DATE_PART('doy', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.TODAY, this.THIS_YEAR);
                }
                return new ResponseModel(result);

            case "this-week":
                if(detailed.equals("0")) {
                    sql = "SELECT COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('week', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY DATE_PART('week', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.THIS_WEEK, this.THIS_YEAR);
                } else if(detailed.equals("1")) {
                    sql = "SELECT payment_method_id as payment_method, COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('week', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY payment_method_id,DATE_PART('week', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.THIS_WEEK, this.THIS_YEAR);
                }

                break;
            case "this-month":
                if(detailed.equals("0")) {
                    sql = "SELECT COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('month', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY DATE_PART('month', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.THIS_MONTH, this.THIS_YEAR);
                } else if(detailed.equals("1")) {
                    sql = "SELECT payment_method_id as payment_method, COUNT(*) as transactions " +
                            "FROM transactions " +
                            "WHERE DATE_PART('month', transaction_date)= ? " +
                            "AND DATE_PART('year', transaction_date)= ?" +
                            "GROUP BY payment_method_id,DATE_PART('month', transaction_date)";
                    result = jdbcTemplate.queryForList(sql, this.THIS_MONTH, this.THIS_YEAR);
                }

                break;
            default:
                break;
        }
        return new ResponseModel(result);
    }

}
