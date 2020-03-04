package cn.pogotravel.shihe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.pogotravel.shihe.mapper")
public class ShiheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiheApplication.class, args);
    }

}
