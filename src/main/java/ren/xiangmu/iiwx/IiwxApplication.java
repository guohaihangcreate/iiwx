package ren.xiangmu.iiwx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("ren.xiangmu.iiwx.mapper")
public class IiwxApplication {

	public static void main(String[] args) {
		SpringApplication.run(IiwxApplication.class, args);
	}
	
}
