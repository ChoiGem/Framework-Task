package org.tukorea.free.config;

import org.springframework.context.annotation.ComponentScan;

//설정된 패키지에서 빈을 찾아 등록
@ComponentScan(basePackages = {"org.tukorea.free.service"})
public class RootConfig {

}
