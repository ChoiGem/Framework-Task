package org.tukorea.free.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

//MyBatisConfig.java 설정을 포함
@Import(JpaConfig.class)
//설정된 패키지에서 빈을 찾아 등록
@ComponentScan(basePackages = {"org.tukorea.free.persistence", "org.tukorea.free.service"})
public class RootConfig {

}
