package com.vernum.gamesService.dao;

import com.vernum.gamesService.models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("postgres")
public class GamesDataAccess {

    private ResponseModel responseModel = new ResponseModel();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GamesDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseModel bestSellers(String platform, String region) {
        String sql;
        List<Map<String,Object>> result = new ArrayList<>();

        if(platform.equals("ps4")) {
            switch (region) {
                case "global":
                    sql = "SELECT game, genre, year, publisher, global as sales " +
                            "FROM ps4_gamesales " +
                            "ORDER BY global DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "america":
                    sql = "SELECT game, genre, year, publisher, north_america as sales " +
                            "FROM ps4_gamesales " +
                            "ORDER BY north_america DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "japan":
                    sql = "SELECT game, genre, year, publisher, japan as sales " +
                            "FROM ps4_gamesales " +
                            "ORDER BY japan DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "europe":
                    sql = "SELECT game, genre, year, publisher, europe as sales " +
                            "FROM ps4_gamesales " +
                            "ORDER BY europe DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                default:
                    break;
            }
            responseModel.setPayload(result);
            return responseModel;

        } else if(platform.equals("xbox")) {
            switch (region) {
                case "global":
                    sql = "SELECT game, genre, year, publisher, global as sales " +
                            "FROM xboxone_gamesales " +
                            "ORDER BY global DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "america":
                    sql = "SELECT game, genre, year, publisher, north_america as sales " +
                            "FROM xboxone_gamesales " +
                            "ORDER BY north_america DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "japan":
                    sql = "SELECT game, genre, year, publisher, japan as sales " +
                            "FROM xboxone_gamesales " +
                            "ORDER BY japan DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "europe":
                    sql = "SELECT game, genre, year, publisher, europe as sales " +
                            "FROM xboxone_gamesales " +
                            "ORDER BY europe DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                default:
                    break;
            }
            responseModel.setPayload(result);
            return responseModel;
        } else {
            switch (region) {
                case "global":
                    sql = "SELECT p.game, p.genre, p.year, p.publisher, p.global + x.global as sales " +
                            "FROM ps4_gamesales as p INNER JOIN xboxone_gamesales as x " +
                            "ON p.game = x.game " +
                            "ORDER BY p.global + x.global DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "america":
                    sql = "SELECT p.game, p.genre, p.year, p.publisher, p.north_america + x.north_america as sales " +
                            "FROM ps4_gamesales as p INNER JOIN xboxone_gamesales as x " +
                            "ON p.game = x.game " +
                            "ORDER BY p.north_america + x.north_america DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "japan":
                    sql = "SELECT p.game, p.genre, p.year, p.publisher, p.japan + x.japan as sales " +
                            "FROM ps4_gamesales as p INNER JOIN xboxone_gamesales as x " +
                            "ON p.game = x.game " +
                            "ORDER BY p.japan + x.japan DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                case "europe":
                    sql = "SELECT p.game, p.genre, p.year, p.publisher, p.europe + x.europe as sales " +
                            "FROM ps4_gamesales as p INNER JOIN xboxone_gamesales as x " +
                            "ON p.game = x.game " +
                            "ORDER BY p.europe + x.europe DESC " +
                            "LIMIT 10";
                    result = jdbcTemplate.queryForList(sql);
                    break;
                default:
                    break;
            }
            responseModel.setPayload(result);
            return responseModel;
        }

    }

    public ResponseModel bestPublishers(String platform) {
        String sql;
        List<Map<String, Object>> result = new ArrayList();

        switch(platform) {
            case "ps4":
                sql = "SELECT publisher, SUM(global) as revenue " +
                        "FROM ps4_gamesales " +
                        "GROUP BY publisher " +
                        "ORDER BY SUM(global) DESC " +
                        "LIMIT 5";
                result = jdbcTemplate.queryForList(sql);
                break;
            case "xbox":
                sql = "SELECT publisher, SUM(global) as revenue " +
                        "FROM xboxone_gamesales " +
                        "GROUP BY publisher " +
                        "ORDER BY SUM(global) DESC " +
                        "LIMIT 5";
                result = jdbcTemplate.queryForList(sql);
                break;
            default:
                break;
        }
        responseModel.setPayload(result);
        return responseModel;
    }

    public ResponseModel yearSales(String platform, String genre) {
        String sql;
        List<Map<String, Object>> result = new ArrayList<>();

        switch(platform) {
            case "ps4":
                if(genre != null) {
                    sql = "SELECT p.year, genre, SUM(global) as sales " +
                            "FROM ps4_gamesales as p " +
                            "WHERE genre != 'Misc' " +
                            "AND genre LIKE ? " +
                            "GROUP BY p.year, genre " +
                            "ORDER BY p.year";
                    result = jdbcTemplate.queryForList(sql, "%" + genre + "%");
                } else {
                    sql = "SELECT p.year, SUM(global) as sales " +
                            "FROM ps4_gamesales as p " +
                            "GROUP BY p.year " +
                            "ORDER BY p.year ";
                    result = jdbcTemplate.queryForList(sql);
                }

                break;
            case "xbox":
                if(genre != null) {
                    sql = "SELECT x.year, genre, SUM(global) as sales " +
                            "FROM xboxone_gamesales as x " +
                            "WHERE genre != 'Misc' " +
                            "AND genre LIKE ? " +
                            "GROUP BY x.year, genre " +
                            "ORDER BY x.year";
                    result = jdbcTemplate.queryForList(sql, "%" + genre + "%");
                } else {
                    sql = "SELECT x.year, SUM(global) as sales " +
                            "FROM xboxone_gamesales as x " +
                            "GROUP BY x.year " +
                            "ORDER BY x.year ";
                    result = jdbcTemplate.queryForList(sql);
                }

                break;
            default:
                break;
        }
        responseModel.setPayload(result);
        return responseModel;
    }

    public ResponseModel publisherRevenue(String publisher) {
        String sql = "SELECT c.year, SUM(c.global) as revenue " +
                        "FROM (SELECT DISTINCT p.publisher, p.year, p.game, p.global " +
                                "FROM ps4_gamesales as p JOIN xboxone_gamesales as x " +
                                "ON p.publisher = x.publisher " +
                                "WHERE p.publisher = ? ) as c " +
                        "GROUP BY c.year " +
                        "ORDER BY c.year ";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, publisher);


        responseModel.setPayload(result);
        return responseModel;
    }

    public ResponseModel genres(String platform) {
        String sql;
        List<Map<String, Object>> result = new ArrayList<>();

        switch(platform) {
            case "all":
                sql = "SELECT c.genre, COUNT(*) " +
                        "FROM (" +
                            "SELECT DISTINCT p.game, p.genre " +
                                "FROM ps4_gamesales as p JOIN xboxone_gamesales as x " +
                                "ON p.genre = x.genre " +
                                "WHERE p.genre != 'Misc' ) as c " +
                        "GROUP BY c.genre";

                result = jdbcTemplate.queryForList(sql);
                break;
            case "ps4":
                sql = "SELECT genre, COUNT(*) " +
                        "FROM ps4_gamesales " +
                        "WHERE genre != 'Misc' " +
                        "GROUP BY genre " +
                        "ORDER BY COUNT(*) DESC";
                result = jdbcTemplate.queryForList(sql);
                break;
            case "xbox":
                sql = "SELECT genre, COUNT(*) " +
                        "FROM xboxone_gamesales " +
                        "WHERE genre != 'Misc' " +
                        "GROUP BY genre " +
                        "ORDER BY COUNT(*) DESC";
                result = jdbcTemplate.queryForList(sql);
                break;
        }
        responseModel.setPayload(result);
        return responseModel;
    }

}
