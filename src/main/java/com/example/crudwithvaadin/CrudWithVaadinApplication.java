package com.example.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {

	private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CrudWithVaadinApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(PuisiRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Puisi("Aku Ingin", "Aku ingin mencintaimu dengan sederhana\n" +
					"dengan kata yang tak sempat diucapkan\n" +
					"kayu kepada api yang menjadikannya abu\n" +
					"\n" +
					"Aku ingin mencintaimu dengan sederhana\n" +
					"dengan isyarat yang tak sempat disampaikan\n" +
					"awan kepada hujan yang menjadikannya tiada\n\n" +
					"-Sapardi Djoko Damono"));

			repository.save(new Puisi("Yang Fana Adalah Waktu", "Yang fana adalah waktu. Kita abadi memungut detik demi detik, merangkainya seperti bunga\n" +
					"sampai pada suatu hari\n" +
					"kita lupa untuk apa\n" +
					"“Tapi, yang fana adalah waktu, bukan?” tanyamu.\n" +
					"Kita abadi.\n" +
					"\n" +
					"-Sapardi Djoko Damono"));

			repository.save(new Puisi("Adaikata", "Kartika berekor itu mulai masuk kedalam jarak pandang. Merah birunya mengingatkanku pada eunoia akan seorang wanodya. Dia pernah berkata, bersenandung adalah perihal rasa. Dan berkabung adalah sia-sia.\n" +
					"\n" +
					"Ada kalanya kita bercerita di tepian bentala, sampai seorang tuan mengusir kita. Ujarnya, mangata mulai padam, candra pun memudar. Cangkir teh yang kosong tanda perpisahan kita.\n" +
					"\n" +
					"Andaikata pada masanya, \n" +
					"Arete meminjamkan adikara miliknya. \n" +
					"\n" +
					"Sedikit saja, mungkin baskara tidaklah jingga. \n" +
					"\n" +
					"Mungkin, namamu eufoni di kala lara.\n" +
					"\n" +
					"Mungkin, kanigara miliknya tiada kau kenang dalam sukma. \n" +
					"\n" +
					"Mungkin, nayanika itu yang kupandang bersama swastamita. \n" +
					"\n" +
					"Mungkin- \n" +
					"\n" +
					"Mungkin, kita bersama sampai acaram tergeletak tetap di jemari kita. \n" +
					"\n" +
					"Andaikata kala itu aku bertaruh, mungkin kau sudah jadi tempatku berlabuh. \n" +
					"\n" +
					"Andai kesempatanku terulang. Tiada terjadi rasa hanya tinggal tulang. \n" +
					"\n" +
					"Mungkin, aku bisa lari dari mimpi buruk yang yang bernama diriku. \n" +
					"\n" +
					"\n" +
					"\n" +
					"\n" +
					"Seperti adagio yang berputar didalam sukma. \n" +
					"\n" +
					"Aku adalah waktu yang berhenti. \n" +
					"\n" +
					"\n" ));

			repository.save(new Puisi("Bandung", "Dan langkah kembali bertemu\n" +
					"Menerjang jarak dan waktu\n" +
					"“Bandung” sebut mereka\n" +
					"Menyebutnya dengan mata berbinar\n" +
					"Dibayangkannya kota indah nan luas\n" +
					"Bak surga yang memanjakan mata\n" +
					"\n" +
					"“Bandung” kataku\n" +
					"Dengan intonasi datar\n" +
					"Tanpa ada harapan berlebih\n" +
					"Yang dibayangkan hampa\n" +
					"Tak berharap sepeser ekspektasi\n" +
					"\n" +
					"Wahai kaki, tangan, otak, raga, dan hati\n" +
					"Kalau lelah ya istirahat\n" +
					"Kau juga banyak teman untuk berkeluh kesah\n" +
					"Tapi tolong jangan lari dari keharusana"));

			repository.save(new Puisi("Manusia", "Ringan di tangan, berat di tindakan.\n" +
					"Manusia\n" +
					"Mengerti yang dikatakan\n" +
					"Namun tak tahu harus membalas apa.\n" +
					"Manusia\n" +
					"Makhluk lemah dengan hati yang bimbang\n" +
					"Cepat putuskan!\n" +
					"Kau hendak memilih siapa\n" +
					"\n" +
					"Manusia\n" +
					"Makhluk dengan 1001 cara bersembunyi\n" +
					"Menghindar dari masalah masalah yang dibuatnnya\n" +
					"Melempar duri tanda tak peduli\n" +
					"Dasar kalian pembohong"));

			repository.save(new Puisi("Acidalia Planitia","Arunika pada Acidalia Planitia. Kehidupan dalam candramawa. \n" +
					"\n" +
					"Kasih, kamulah diri dengan nayanika nirmala. \n" +
					"\n" +
					"\n" +
					"\n" +
					"Hari berganti, lagi kulihat arunika pada Acidalia Planitia. Beda rupa dengan bumi tempat tinggal kita. Di sana rupanya, senja berwarna biru haru. Tiada entitas yang mutlak memiliki tempat. Rasa, lara, karsa, semuanya melayang dan hilang tak bersisa. \n" +
					"\n" +
					"\n" +
					"\n" +
					"Romansa kita terhalang anca. Tempat kita bukan berada pada asmaraloka. Dunia kita hanya cadramawa tanpa warna-warna. Kamu menjelma dersik yang berdetik, yang sebentar lagi menjadi relik. Kamu menjelma riak air yang sebentar lagi hanya sebatas sair. \n" +
					"\n" +
					"\n" +
					"\n" +
					"Di sinilah aku. Di suatu tempat di alam semesta. Tanpa rasa, tanpa lara, tanpa karsa. Tanpa pula ada bab selanjutnya. \n" +
					"\n" +
					"\n" +
					"30 sept"));
			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Puisi puisi : repository.findAll()) {
				log.info(puisi.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Puisi puisi = repository.findById(1L).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(puisi.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Puisi bauer : repository
					.findByIsiPuisiStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
