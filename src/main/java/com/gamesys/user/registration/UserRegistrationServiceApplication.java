package com.gamesys.user.registration;

import com.gamesys.user.registration.model.IssuerStatusEnum;
import com.gamesys.user.registration.model.persistence.PaymentIssuer;
import com.gamesys.user.registration.repository.PaymentIssuerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class UserRegistrationServiceApplication implements ApplicationRunner {

	@Autowired
	private
	PaymentIssuerRepository paymentIssuerRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
		log.info("NonOptionArgs: {}", args.getNonOptionArgs());
		log.info("OptionNames: {}", args.getOptionNames());

		if(args.containsOption("iin.blocked")){
			final List<PaymentIssuer> blockedIins = new ArrayList<>();
			for (String s : args.getOptionValues("iin.blocked")) {
        Arrays.stream(s.split(";"))
            .collect(Collectors.toList())
            .forEach(
                s1 -> {
                  final String blocked = IssuerStatusEnum.BLOCKED.name();
                  blockedIins.add(PaymentIssuer.builder().issuerNumber(s1).status(blocked).build());
                });
			}

			Iterable<PaymentIssuer> df = blockedIins.size()>0 ? saveALl(blockedIins) : null;
		}
	}

	private Iterable<PaymentIssuer> saveALl(List<PaymentIssuer> blockedIin) {
		return paymentIssuerRepository.saveAll(blockedIin);
	}
}
