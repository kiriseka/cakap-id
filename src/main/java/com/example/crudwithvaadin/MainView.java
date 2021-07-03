package com.example.crudwithvaadin;

import com.example.crudwithvaadin.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route(value = "main")
@PageTitle(value = "Cakap.id")
public class MainView extends VerticalLayout {

	private final PuisiRepository repo;

	private final PuisiEditor editor;

	final Grid<Puisi> grid;

	final TextField filter;

	private final Button btnCari;

	private final Button btnExit;





	public MainView(PuisiRepository repo, PuisiEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Puisi.class);
		this.filter = new TextField();
		this.btnCari = new Button("Tambah Puisi", VaadinIcon.PLUS.create());
		this.btnExit = new Button("Keluar", VaadinIcon.EXIT.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, btnCari, btnExit);
		VerticalLayout view= new VerticalLayout(actions,grid);
		HorizontalLayout all = new HorizontalLayout(view,editor);
		add(all);

		grid.setHeight("300px");
		grid.setColumns("id", "judulPuisi","isiPuisi");

		// Header
		grid.getColumnByKey("id").setWidth("75px").setHeader("ID").setFlexGrow(0);
		grid.getColumnByKey("judulPuisi").setWidth("250px").setFlexGrow(0);

		// Search
		filter.setPlaceholder("Cari Judul Puisi");

		// Logic
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));

		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});

		btnCari.addClickListener(e -> editor.editCustomer(new Puisi("", "")));

		// Logout
		btnExit.addClickListener(event -> UI.getCurrent().navigate(LoginView.class));

		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		listCustomers(null);
	}

	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByIsiPuisiStartsWithIgnoreCase(filterText));
		}
	}

}
