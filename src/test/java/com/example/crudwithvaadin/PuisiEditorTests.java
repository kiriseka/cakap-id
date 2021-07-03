package com.example.crudwithvaadin;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.then;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PuisiEditorTests {

	private static final String FIRST_NAME = "Marcin";
	private static final String LAST_NAME = "Grzejszczak";

	@Mock
	PuisiRepository puisiRepository;
	@InjectMocks
	PuisiEditor editor;
	@Mock PuisiEditor.ChangeHandler changeHandler;

	@BeforeEach
	public void init() {
		editor.setChangeHandler(changeHandler);
	}

	@Test
	public void shouldStoreCustomerInRepoWhenEditorSaveClicked() {
		emptyCustomerWasSetToForm();

		this.editor.judulPuisi.setValue(FIRST_NAME);
		this.editor.isiPuisi.setValue(LAST_NAME);

		this.editor.save();

		then(this.puisiRepository).should().save(argThat(customerMatchesEditorFields()));
	}

	@Test
	public void shouldDeleteCustomerFromRepoWhenEditorDeleteClicked() {
		customerDataWasFilled();

		editor.delete();

		then(this.puisiRepository).should().delete(argThat(customerMatchesEditorFields()));
	}

	private void emptyCustomerWasSetToForm() {
		this.editor.editCustomer(new Puisi());
	}
	private void customerDataWasFilled() {
		this.editor.editCustomer(new Puisi(FIRST_NAME, LAST_NAME));
	}

	private ArgumentMatcher<Puisi> customerMatchesEditorFields() {
		return customer -> FIRST_NAME.equals(customer.getJudulPuisi()) && LAST_NAME.equals(customer.getIsiPuisi());
	}

}
