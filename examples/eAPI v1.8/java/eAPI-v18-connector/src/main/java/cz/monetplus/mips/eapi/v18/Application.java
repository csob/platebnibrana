package cz.monetplus.mips.eapi.v18;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Application implements CommandLineRunner {
	
	@Autowired
	private RunModeProvider runModeProvider;
	
	@Autowired
	private ApplicationContext context;
	
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	
	@Override
	public void run(String... args) {
		ArgsConfig config = new ArgsConfig();
		CmdLineParser parser = new CmdLineParser(config);
		try {
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
			return;
		}
		
    	try {
			RunMode rm = runModeProvider.find(config.runMode);
			if (rm == null) {
				throw new RuntimeException("Unknown run mode ...");
			}

			rm.proc(config);
		} catch(Exception e) {
			LOG.error("Failed: {}", getExceptionMessages(e));
			SpringApplication.exit(context, new StaticExitCodeGenerator(-1));
			return;
		}
		SpringApplication.exit(context, new StaticExitCodeGenerator(0));
	}
	
    /**
 	 * Helper method to extract all exception message from stack trace
 	 * @param t
 	 * @return
 	 */
     public static String getExceptionMessages(Throwable throwable)
     {
     	Throwable t = throwable; 
     	StringBuilder sb = new StringBuilder(); 
     	while (t != null)
     	{
     		sb.append(t.getClass().getSimpleName());
     		sb.append(": ");
     		sb.append(t.getMessage());
     		t = t.getCause(); 
     		if (t != null)
     		{
     			sb.append(" > ");
     		}
     	}
     	return sb.toString();
     }
	

	public static void main(String[] args) {
		new SpringApplicationBuilder()
			.bannerMode(Banner.Mode.OFF)
			.sources(Application.class)
			.run(args);
	}
}
