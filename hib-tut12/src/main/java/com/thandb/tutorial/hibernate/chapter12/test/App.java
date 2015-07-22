package com.thandb.tutorial.hibernate.chapter12.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.thandb.tutorial.hibernate.chapter12.domain.Stock;
import com.thandb.tutorial.hibernate.chapter12.domain.StockDailyRecord;
import com.thandb.tutorial.hibernate.chapter12.util.HibernateUtil;

public class App {
	public static void main(String[] args) {
		/* BEGIN TEST FOR READ ONLY ENTITY */
		Session session = null;
		Transaction tx = null;
		Integer id = new Integer(0);
		try {
			System.out.println("Start");
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			// 1. Mutable in class
			// 1.1 Mutable is true
			/* Add
			
			Stock stock = new Stock();
			stock.setStockCode("7277");
			stock.setStockName("MICROSOFT");
			Integer id = (Integer) session.save(stock);*/
			
			/* Update */
			/*Stock stock = (Stock) session.get(Stock.class, new Integer(36));
			stock.setStockName("Google");
			session.saveOrUpdate(stock);
			
			tx.commit();
			stock = (Stock) session.get(Stock.class, new Integer(36));
			System.out.println("New Stock name: " + stock.getStockName());*/
			
			/* Delete */
			/*Stock stock = (Stock) session.get(Stock.class, new Integer(36));
			session.delete(stock);
			
			tx.commit();
			stock = (Stock) session.get(Stock.class, new Integer(36));
			if(stock == null) {
				System.out.println("Stock is null");
			} else {
				System.out.println("Stock was not deleted. Stock name: " + stock.getStockName());
			}*/
			
			// 1.2 Mutable is false
			/* Add */
			/*Stock stock = new Stock();
			stock.setStockCode("7277");
			stock.setStockName("MICROSOFT");
			id = (Integer) session.save(stock);
			tx.commit();
			System.out.println("ID: " + id);
			stock = (Stock) session.get(Stock.class, new Integer(id));
			if(stock == null) {
				System.out.println("Cannot add the stock.");
			} else {
				System.out.println("Added a new Stock: " + stock.getStockName());
			}
			System.out.println("Done");*/
						
			/* Update */
			/*Stock stock = (Stock) session.get(Stock.class, new Integer(37));
			stock.setStockName("Google");
			session.saveOrUpdate(stock);

			tx.commit();
			stock = (Stock) session.get(Stock.class, new Integer(37));
			if (stock == null) {
				System.out.println();
			} else {
				if ("Google".equals(stock.getStockName())) {
					System.out.println("Stock was not changed. Stock name: " + stock.getStockName());
				} else {
					System.out.println("Stock was changed. Stock name: " + stock.getStockName());
				}
			}*/
						
			/* Delete */
			/*Stock stock = (Stock) session.get(Stock.class, new Integer(37));
			session.delete(stock);

			tx.commit();
			stock = (Stock) session.get(Stock.class, new Integer(37));
			if (stock == null) {
				System.out.println("Stock was deleted.");
			} else {
				System.out.println("Stock was not deleted. Stock name: "
						+ stock.getStockName());
			}*/
			
			/*************************************************/
			// 2.
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		/* END TEST FOR READ ONLY ENTITY */
		
		/* BEGIN TEST FOR ONE TO MANY MAPPING */
		/*
		System.out.println("Hibernate one to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		session.save(stockDailyRecords);

		session.getTransaction().commit();
		System.out.println("Done");
		*/
		/* END TEST FOR ONE TO MANY MAPPING */
	}
}
