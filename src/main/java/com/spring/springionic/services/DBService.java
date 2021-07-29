package com.spring.springionic.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.spring.springionic.domain.Address;
import com.spring.springionic.domain.AppOrder;
import com.spring.springionic.domain.BilletPayment;
import com.spring.springionic.domain.Category;
import com.spring.springionic.domain.City;
import com.spring.springionic.domain.Client;
import com.spring.springionic.domain.CreditCardPayment;
import com.spring.springionic.domain.ItemOrder;
import com.spring.springionic.domain.Payment;
import com.spring.springionic.domain.Product;
import com.spring.springionic.domain.State;
import com.spring.springionic.domain.enums.ClientType;
import com.spring.springionic.domain.enums.PaymentStatus;
import com.spring.springionic.repositories.AddressRepository;
import com.spring.springionic.repositories.CategoryRepository;
import com.spring.springionic.repositories.CityRepository;
import com.spring.springionic.repositories.ClientRepository;
import com.spring.springionic.repositories.ItemOrderRepository;
import com.spring.springionic.repositories.OrderRepository;
import com.spring.springionic.repositories.PaymentRepository;
import com.spring.springionic.repositories.ProductRepository;
import com.spring.springionic.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;

    public void instantiateTestDatabase() throws ParseException{
        Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Bed");
		Category cat4 = new Category(null, "Pet");
		Category cat5 = new Category(null, "Hardware");
		Category cat6 = new Category(null, "Software");
		Category cat7 = new Category(null, "Education");
		Category cat8 = new Category(null, "Clothes");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Office table", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "TV", 1200.00);
		Product p8 = new Product(null, "Hoe", 800.00);
		Product p9 = new Product(null, "Mood Light", 100.00);
		Product p10 = new Product(null, "Pendant", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);


		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "alves.rodrigow@gmail.com", "36378912377", ClientType.NATURALPERSON);
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address a1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(a1, a2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		AppOrder ord1 = new AppOrder(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
		AppOrder ord2 = new AppOrder(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

		Payment pay1= new CreditCardPayment(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);

		Payment pay2 = new BilletPayment(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		ItemOrder io1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
		ItemOrder io2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
		ItemOrder io3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);

		ord1.getItems().addAll(Arrays.asList(io1, io2));
		ord2.getItems().addAll(Arrays.asList(io3));

		p1.getItems().addAll(Arrays.asList(io1));
		p2.getItems().addAll(Arrays.asList(io3));
		p3.getItems().addAll(Arrays.asList(io2));

		itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));

    }
}
