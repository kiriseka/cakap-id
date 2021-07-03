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

@SpringComponent
@UIScope
public class PuisiPrinter extends VerticalLayout implements KeyNotifier {

	private final PuisiRepository repository;

	private Puisi puisi;

	// Deklarasi
	TextField judulPuisi = new TextField("Judul");
	TextArea isiPuisi = new TextArea("Isi Puisi");

	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	Binder<Puisi> binder = new Binder<>(Puisi.class);
	private ChangeHandler changeHandler;

	@Autowired
	public PuisiPrinter(PuisiRepository repository) {
		this.repository = repository;

		add(judulPuisi, isiPuisi);

		// Binding
		binder.bindInstanceFields(this);

		setSpacing(true);

		//Isi Puisi Set Size
		isiPuisi.getStyle().set("maxHeight", "300px");
		isiPuisi.getStyle().set("minHeight", "300px");
		isiPuisi.getStyle().set("minWidth", "800px");

		isiPuisi.setReadOnly(true);
		judulPuisi.setReadOnly(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		// Listener per tombol
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
			puisi = repository.findById(c.getId()).get();
		}
		else {
			puisi = c;
		}
		cancel.setVisible(persisted);

		// Auto Binding
		binder.setBean(puisi);

		setVisible(true);

		judulPuisi.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		changeHandler = h;
	}

}
