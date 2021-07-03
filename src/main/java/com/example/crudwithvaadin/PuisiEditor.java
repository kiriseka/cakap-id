package com.example.crudwithvaadin;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in userView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class PuisiEditor extends VerticalLayout implements KeyNotifier {

	private final PuisiRepository repository;

	/**
	 * The currently edited customer
	 */
	private Puisi puisi;

	/* Fields to edit properties in Customer entity */
	TextField judulPuisi = new TextField("Judul");
	TextArea isiPuisi = new TextArea("Isi Puisi");




	/* Action buttons */
	// TODO why more code?
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	Binder<Puisi> binder = new Binder<>(Puisi.class);
	private ChangeHandler changeHandler;

	@Autowired
	public PuisiEditor(PuisiRepository repository) {
		this.repository = repository;

		add(judulPuisi, isiPuisi, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		//Isi Puisi Set Size
		isiPuisi.getStyle().set("maxHeight", "300px");
		isiPuisi.getStyle().set("minHeight", "300px");
		isiPuisi.getStyle().set("minWidth", "800px");


		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

//		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		cancel.addClickListener(e -> editCustomer(puisi));
		setVisible(false);
	}

	void delete() {
		repository.delete(puisi);
		changeHandler.onChange();
	}

	void save() {
		repository.save(puisi);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	public final void editCustomer(Puisi c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			puisi = repository.findById(c.getId()).get();
		}
		else {
			puisi = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(puisi);

		setVisible(true);

		// Focus first name initially
		judulPuisi.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
