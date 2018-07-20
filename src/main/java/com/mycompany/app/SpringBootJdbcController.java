package com.mycompany.app;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.web.bind.annotation.RestController;  

@RestController  
public class SpringBootJdbcController {  
    @Autowired  
    JdbcTemplate jdbc;    
    @RequestMapping("/insert")  
    public String index(){  
        jdbc.execute("insert into unit(name, exp, bblvl, type)values('Tsovinar', 200, 5, 'Breaker')");  
        return"data inserted Successfully";  
    }  

    @Autowired
    UnitDAOImpl unitDAO;
    @RequestMapping("/getlist")
    public List<Unit> list(){
        return unitDAO.getAll();
    }
}  