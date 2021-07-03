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

//	private final PuisiPrinter printer;

	final Grid<Puisi> grid;

	final TextField filter;

	private final Button btnCari;

	private final Button btnExit;


//	final TextArea puisi;



	public MainView(PuisiRepository repo, PuisiEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Puisi.class);
		this.filter = new TextField();
//		this.printer = printer;
//		this.puisi = new TextArea();
		this.btnCari = new Button("Tambah Puisi", VaadinIcon.PLUS.create());
		this.btnExit = new Button("Keluar", VaadinIcon.EXIT.create());



		//Puisi TextArea
//		puisi.getStyle().set("maxHeight", "250px");
//		puisi.getStyle().set("minWidth", "600px");
//		String isi = editor.isiPuisi.getElement().toString();
//		puisi.setValue(isi);




		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, btnCari, btnExit);
//		HorizontalLayout bottom = new HorizontalLayout(editor,printer);

		VerticalLayout view= new VerticalLayout(actions,grid);

		HorizontalLayout all = new HorizontalLayout(view,editor);

		add(all);
//		add(actions, grid, editor);

		grid.setHeight("300px");
		grid.setColumns("id", "judulPuisi","isiPuisi");



		// Header
		grid.getColumnByKey("id").setWidth("75px").setHeader("ID").setFlexGrow(0);
		grid.getColumnByKey("judulPuisi").setWidth("250px").setFlexGrow(0);
//		grid.getColumnByKey("judulPuisi").setHeader("Nama Mahasiswa");
//		grid.getColumnByKey("isiPuisi").setHeader("Nim Mahasiswa");

		//Set Header
//		grid.addColumn(Customer::getId).setHeader("Id Mahasiswa");
//		grid.addColumn(Customer::getFirstName).setHeader("Nama");
//		grid.addColumn(Customer::getLastName).setHeader("Nim");



		filter.setPlaceholder("Cari Judul Puisi");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listCustomers(e.getValue()));


		// Connect selected Customer to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editCustomer(e.getValue());
		});

		// Instantiate and edit new Customer the new button is clicked
		btnCari.addClickListener(e -> editor.editCustomer(new Puisi("", "")));

		//Logout
		btnExit.addClickListener(event -> UI.getCurrent().navigate(LoginView.class));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

		// Initialize listing
		listCustomers(null);
	}

	// tag::listCustomers[]
	void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByIsiPuisiStartsWithIgnoreCase(filterText));
		}
	}
	// end::listCustomers[]

}
