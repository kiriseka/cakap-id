package com.example.crudwithvaadin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@SpringBootTest(classes = MainViewTests.Config.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MainViewTests {

	@Autowired
	PuisiRepository repository;

	VaadinRequest vaadinRequest = Mockito.mock(VaadinRequest.class);

	PuisiEditor editor;

//	PuisiPrinter printer;

	MainView mainView;

	@BeforeEach
	public void setup() {
		this.editor = new PuisiEditor(this.repository);
		this.mainView = new MainView(this.repository, editor);
	}

	@Test
	public void shouldInitializeTheGridWithCustomerRepositoryData() {
		int customerCount = (int) this.repository.count();

		then(mainView.grid.getColumns()).hasSize(3);
		then(getCustomersInGrid()).hasSize(customerCount);
	}

	private List<Puisi> getCustomersInGrid() {
		ListDataProvider<Puisi> ldp = (ListDataProvider) mainView.grid.getDataProvider();
		return new ArrayList<>(ldp.getItems());
	}

	@Test
	public void shouldFillOutTheGridWithNewData() {
		int initialCustomerCount = (int) this.repository.count();

		customerDataWasFilled(editor, "Marcin", "Grzejszczak");

		this.editor.save();

		then(getCustomersInGrid()).hasSize(initialCustomerCount + 1);

		then(getCustomersInGrid().get(getCustomersInGrid().size() - 1))
			.extracting("judulPuisi", "isiPuisi")
			.containsExactly("Marcin", "Grzejszczak");

	}

	@Test
	public void shouldFilterOutTheGridWithTheProvidedLastName() {

		this.repository.save(new Puisi("Josh", "Long"));

		mainView.listCustomers("Long");

		then(getCustomersInGrid()).hasSize(1);
		then(getCustomersInGrid().get(getCustomersInGrid().size() - 1))
			.extracting("judulPuisi", "isiPuisi")
			.containsExactly("Josh", "Long");
	}

	@Test
	public void shouldInitializeWithInvisibleEditor() {

		then(this.editor.isVisible()).isFalse();
	}

	@Test
	public void shouldMakeEditorVisible() {
		Puisi first = getCustomersInGrid().get(0);
		this.mainView.grid.select(first);

		then(this.editor.isVisible()).isTrue();
	}

	private void customerDataWasFilled(PuisiEditor editor, String firstName,
									   String lastName) {
		this.editor.judulPuisi.setValue(firstName);
		this.editor.isiPuisi.setValue(lastName);
		editor.editCustomer(new Puisi(firstName, lastName));
	}

	@Configuration
	@EnableAutoConfiguration(exclude = com.vaadin.flow.spring.SpringBootAutoConfiguration.class)
	static class Config {

		@Autowired
		PuisiRepository repository;

		@PostConstruct
		public void initializeData() {
			this.repository.save(new Puisi("Jack", "Bauer"));
			this.repository.save(new Puisi("Chloe", "O'Brian"));
			this.repository.save(new Puisi("Kim", "Bauer"));
			this.repository.save(new Puisi("David", "Palmer"));
			this.repository.save(new Puisi("Michelle", "Dessler"));
		}
	}
}
