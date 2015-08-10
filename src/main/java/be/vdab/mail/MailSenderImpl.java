package be.vdab.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import be.vdab.entities.Filiaal;

@Component
public class MailSenderImpl implements MailSender {
	private final static Logger logger = Logger.getLogger(MailSenderImpl.class
			.getName());
	private final JavaMailSender sender;
	private final String webmaster;

	@Autowired
	public MailSenderImpl(JavaMailSender sender,
			@Value("${webmaster}") String webmaster) {
		this.sender = sender;
		this.webmaster = webmaster;
	}

	@Override
	@Async
	public void nieuwFiliaalMail(Filiaal filiaal, String url) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(webmaster);
			helper.setSubject("Nieuw filiaal");
			helper.setText(String.format(
					"Je kan het nieuwe filiaal <strong>%s</strong> "
							+ "<a href='%s/wijzigen'>hier</a> nazien",
					filiaal.getNaam(), url), true);
			sender.send(message);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "kan mail nieuw filiaal niet versturen",
					ex);
			throw new RuntimeException("Kan mail nieuw filiaal niet versturen",
					ex);
		}
	}

	@Override
	public void aantalFilialenMail(long aantal) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(webmaster);
			helper.setSubject("Aantal filialen");
			helper.setText(String.format(
					"Er zijn <strong>%d</strong> filialen.", aantal), true);
			sender.send(message);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "kan mail aantal filialen niet versturen",
					ex);
			throw new RuntimeException("Kan mail niet versturen", ex);
		}
	}
}
