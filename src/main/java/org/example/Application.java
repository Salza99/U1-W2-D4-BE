package org.example;

import Entities.Customer;
import Entities.Order;
import Entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // clienti
        Customer a = new Customer("Marco", 1);
        Customer b = new Customer("Lucio", 1);
        Customer c = new Customer("Maria", 2);
        Customer d = new Customer("Angelo", 2);
        Customer e = new Customer("Sempronio", 1);
        // Categoria book
        Product bookA = new Product("La storia di Cesare","Books",24.99);
        Product bookB = new Product("Le armi medievali","Books",19.99);
        Product bookC = new Product("Roma: l'impero infinito","Books",240.99);
        Product bookD = new Product("Il guardiano degli innocenti","Books",14.99);
        Product bookE = new Product("La spada del destino","Books",160.99);
        Product bookF = new Product("Il sangue degli elfi","Books",12.99);
        Product bookG = new Product("Frakenstei","Books",240.99);
        Product bookH = new Product("Racconti del terrore","Books",19.99);
        Product bookI = new Product("L'incubo di Hill House","Books",9.99);
        Product bookL = new Product("Le guide del tramonto","Books",120.99);
        Product bookM = new Product("Cronache marziane","Books",16.99);
        //categoria Baby
        Product babyA = new Product("Pannolini","Baby",9.99);
        Product babyB = new Product("fasciatoio","Baby",196.99);
        Product babyC = new Product("Salviette","Baby",6.99);
        Product babyD = new Product("Carrozzina","Baby",186.99);
        Product babyE = new Product("Culla","Baby",124.99);
        //categoria boys
        Product boysA = new Product("t-shirt","Boys",12.99);
        Product boysB = new Product("airpods","Boys",19.99);
        Product boysC = new Product("watch","Boys", 29.99);
        Product boysD = new Product("skateboard","Boys", 29.99);
        Product boysE = new Product("profumo","Boys", 9.99);
        //liste di prodotti
        //libri
        List<Product> listOfBooksA = new ArrayList<>(List.of(bookA, bookB, bookC, bookD, bookE, bookF, bookG, bookH, bookI,bookL,bookM));
        List<Product> listOfBooksB = new ArrayList<>(List.of(bookA, bookB, bookC, bookD, bookE));
        //baby
        List<Product> listOfBabyA = new ArrayList<>(List.of(babyA,babyB,babyE));
        List<Product> listOfBabyB = new ArrayList<>(List.of(babyA,babyE,babyD));
        //boys
        List<Product> listOfBoysA = new ArrayList<>(List.of(boysA,boysC));
        List<Product> listOfBoysB = new ArrayList<>(List.of(boysE,boysA,boysD));

        //ordini
        Order orderA = new Order(LocalDate.of(2020,10,8),a,listOfBooksA);
        Order orderB = new Order(LocalDate.of(2021,2,13),a,listOfBabyB);
        Order orderC = new Order(LocalDate.of(2022,5,29),b,listOfBabyA);
        Order orderD = new Order(LocalDate.of(2021,2,18),c,listOfBooksB);
        Order orderE = new Order(LocalDate.of(2021,3,6),d, listOfBoysB);
        Order orderF = new Order(LocalDate.of(2021,2,2),e, listOfBooksA);
        //lista di ordini
        List<Order> listOfOrder = new ArrayList<>();
        listOfOrder.add(orderA);
        listOfOrder.add(orderB);
        listOfOrder.add(orderC);
        listOfOrder.add(orderD);
        listOfOrder.add(orderE);
        listOfOrder.add(orderF);
        //Esercizio 1
        System.out.println("--------Esercizio 1--------");
        Map<Customer, List<Order>> ordersByClient = listOfOrder.stream().collect(Collectors.groupingBy(Order::getCustomer));
        ordersByClient.forEach((customer, orderList) -> {
            System.out.println("Cliente: " + customer);
            System.out.println("Ordini cliente " + customer + " :" + orderList);
        });
        //Esercizio 2
        System.out.println("--------Esercizio 2--------");
        Map<Customer, Double> totalCartValueClient = new HashMap<>();
        listOfOrder.forEach(order -> {
            Customer keyCustomer = order.getCustomer();
            Double sumOfPrice = order.getProducts().stream().mapToDouble(Product::getPrice).sum();
            totalCartValueClient.merge(keyCustomer, sumOfPrice, Double::sum);
        });

        totalCartValueClient.forEach(((customer, aDouble) -> System.out.println("Cliente: " + customer + " totale carrello: " + aDouble + "€")));
        //Esercizio 3
        System.out.println("--------Esercizio 3--------");
        Optional<Product> expensiveProduct = listOfBoysA.stream().max(Comparator.comparing(Product::getPrice));
        if (expensiveProduct.isPresent()) {
            System.out.println("il prodotto più costoso è: " + expensiveProduct.get().getName() + " con un prezzo di: " + expensiveProduct.get().getPrice() + "€");
        }
        //Esercizio 4
        System.out.println("--------Esercizio 4--------");
        AtomicReference<Double> average = new AtomicReference<>(0.0);
        listOfOrder.stream().forEach(order -> {
            average.updateAndGet(v -> v + order.getProducts().stream().mapToDouble(Product::getPrice).sum());

        });

        System.out.println("La media è: " + average.get() / listOfOrder.size());
    }
}
