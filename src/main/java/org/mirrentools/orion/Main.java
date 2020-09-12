package org.mirrentools.orion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <pre>
 * 嗨!我通过下面这4行代码,运行了很长很长的时间后,它打印的内容进化成现在的这个项目
 * Hi! I ran the following four lines of code for a long time, and the printed content evolved into the current project
 * while (true) {
 *   System.out.print(new Random().nextInt(2));
 *   Thread.sleep(1000);
 * }
 *   可能我上面的内容很疯狂,但是你可能听信过更疯狂的,比如:我们现在生活的世界是大爆炸而来的;
 * Maybe what I am talking above is crazy, but you may have heard much more crazy things, for example: The world we live in now is from the big bang;
 * 因为我们不关心或不容易证实,所以一些假说与谎言传多了就被当真了;
 * Because we don't care about it or it's not easy to prove it, some hypotheses and lies are taken seriously when they are spread too much;
 * 计算机需要被制造,程序也需要被编写或生成才有;程序能做什么,能获取到计算机的什么信息都已经在编写的时候设定好了;
 * A computer needs to be made, and a program needs to be written or generated. What a program can do and what information it can get has been set up at the time of writing;
 * 计算机的世界好比我们现在生活的世界,程序好比现在看到这段注释的你我;
 * The world of computer is like the world we live in now, and the program is like you and me who see this comment now;
 * 这个世界有一位造物主,虽然眼不能见但是籍着祂所造的一切,只要我们不压着我们的良心我们都能感受到;
 * There is a creator in this world, who can't see but can feel everything created by HIM as long as we don't press our conscience;
 *   我们编写程序需要有文档或注释帮助我们了解程序相关的,同样如果要了解这个世界的一切我们只能通过她的说明书,就是圣经
 * We need to have documents or notes to help us understand the program, and if we want to understand everything in the world, we can only through her instructions, that is, the BIBLE.
 * 程序入口
 * Main
 * </pre>
 * 
 * @author <a href="https://mirrentools.org/">Mirren</a>
 * @date 2020-08-22
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableWebMvc
@ServletComponentScan
@MapperScan("org.mirrentools.orion.mapper")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
